package yellowc.app.allrank.data.remote.datasourceimpl

import android.os.AsyncTask
import android.util.Log
import kotlinx.serialization.json.JsonNull.content
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import timber.log.Timber
import yellowc.app.allrank.data.remote.api.JsoupService
import yellowc.app.allrank.data.remote.response.jsoup_response.JsoupResponse
import yellowc.app.allrank.util.*
import java.io.IOException
import javax.inject.Inject

class JsoupServicesImpl @Inject constructor() : JsoupService {

    override suspend fun startCrawling(url: String, type: String): List<JsoupResponse> {
        when (type) {
            JSOUP_KOREAN_MUSIC -> {
                Timber.e(type)
                return getMelons(url)
            }
            JSOUP_RESERVATION -> {
                Timber.e(type)
                return getReservation(url)
            }
            JSOUP_SEARCHED -> {
                Timber.e(type)
                return getTrends(url)
            }
            JSOUP_STEAM_MOST_PLAYED -> {
                Timber.e(type)
                return getMostPlay(url)
            }
            JSOUP_METACRITIC -> {
                Timber.e(type)
                return getMeta(url)
            }
            JSOUP_FOREIGN->{
                Timber.e(type)
                return getBillboard(url)
            }
            else -> {
                return emptyList()
            }
        }

    }

    private fun getBillboard(url: String): List<JsoupResponse> {

        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val body = doc.body().getElementsByClass("chart-positions")
        val trs = body.select("tr:not([class])")
        for (td in trs){
            val rank = td.select("span.position").text()
            val covers = td.select("div.cover").select("img").attr("src")
            val title = td.select("div.title").select("a").text()
            val artist = td.select("div.artist").select("a").text()
            val label = td.select("span.label").text()

            if (!rank.isNullOrBlank()){
                val temp = JsoupResponse(
                    rank = rank,
                    title = title,
                    ImgUrl = covers,
                    owner = artist,
                    description = "$label Records"
                )
                result.add(temp)
            }
        }
        return result
    }


    private suspend fun getMeta(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val tables = doc.body().select("table.clamp-list tbody")
        var cnt = 0
        for (tbody in tables) {
            val tr: Elements = tbody.select("tr:not(.spacer)")
            for (element in tr) {
                cnt += 1
                val img =
                    element.select("td.clamp-image-wrap").select("a").select("img").attr("src")
                val content = element.select("td.clamp-summary-wrap")
                val title = content.select("a.title").select("h3").text()
                val rate = content.select("div.clamp-score-wrap")
                    .select("a.metascore_anchor")
                    .select("div.metascore_w.large.game.positive")
                    .text()
                //content.select("div.clamp-score-wrap").select("a.metascore_anchor").select("div.metascore_w large game positive").text()
                val platform = content.select("span.data").text()

                val temp = JsoupResponse(
                    title = title,
                    rank = cnt.toString(),
                    owner = platform,
                    description = "$rate 점",
                    ImgUrl = img
                )
                result.add(temp)
                Timber.e("$title,$platform,$rate")

            }
        }
        return result
    }

    private suspend fun getMostPlay(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        Timber.e(doc.title())
        val body = doc.body().getElementsByClass("content")
        val tbody: Element = doc.selectFirst("tbody")!!

        for ((index, tr) in tbody.children().withIndex()) {
            // 홀수 번째 tr은 class가 "odd"인 tr
            if (index % 2 == 0) {
                val tds: Elements = tr.getElementsByTag("td")
                val rank: Element = tds[0] // rank
                val title: Element = tds[1] //title
                val num: Element = tds[2] // number of people
                val temp = JsoupResponse(
                    rank = rank.text(),
                    title = title.select("a").text(),
                    ImgUrl = "",
                    description = "${num.text()} 명",
                    owner = "현재 플레이 인원"
                )
                result.add(temp)
            } else {
                // 짝수 번째 tr은 class가 없는 tr
                val tds: Elements = tr.children()
                val rank: Element = tds[0] // rank
                val title: Element = tds[1] //title
                val num: Element = tds[2] // number of people
                val temp = JsoupResponse(
                    rank = rank.text(),
                    title = title.select("a").text(),
                    ImgUrl = "",
                    description = "${num.text()} 명",
                    owner = "현재 플레이 인원"
                )
                result.add(temp)
            }
        }


        return result
    }

    private suspend fun getTrends(url: String): List<JsoupResponse> {

        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        Timber.e(doc.title())

        val a = doc.body().getElementsByClass("title title-break")
        Timber.e("${a.size}")


        return result
    }


    private suspend fun getReservation(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val body = doc.body().getElementsByClass("sect-movie-chart")
        val ols = body.select("ol")
        Timber.e("")
        for (ol in ols){
            val li = ol.select("li")
            for (element in li){
                val images =element.select("div.box-image")
                val contents = element.select("div.box-contents")
                val rank = images.select("strong.rank").text()
                val img = images.select("a").select("span.thumb-image").select("img").attr("src")
                val title = contents.select("a").select("strong.title").text()
                val reservate = contents.select("div.score").select("span").text()
                val date = contents.select("span.txt-info").select("strong").text()

                val pattern = "(\\d+\\.\\d+%).*".toRegex()
                val matchResult = pattern.find(reservate)
                val reservation = matchResult?.groupValues?.get(1)

                Timber.e("$rank, $title, $reservate, $date")
                if (!reservation.isNullOrBlank()){
                    val temp = JsoupResponse(
                        rank = rank,
                        title = title,
                        ImgUrl = img,
                        owner = "예매율: $reservation",
                        description = date
                    )
                    result.add(temp)
                }

            }


        }
        return result
    }

    private suspend fun getMelons(url: String): List<JsoupResponse> {

        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()

        val _ranks = doc.body().getElementsByClass("rank") //맨 처음 순위 text도 추가가 된다. 이 크기만 101
        val ranks = _ranks.subList(1, 101)
        val covers = doc.body().getElementsByClass("image_typeAll")
        val songs = doc.body().getElementsByClass("ellipsis rank01") //.select("a").text()
        val artists = doc.body().getElementsByClass("checkEllipsis") //.select("a").text()
        val albums = doc.body().getElementsByClass("ellipsis rank03")
        for (i in 0..99) {
            val temp = JsoupResponse(
                rank = ranks[i].text(),
                title = songs[i].select("a").text(),
                ImgUrl = covers[i].select("img").attr("src").toString(),
                owner = artists[i].select("a").text(),
                description = albums[i].select("a").text()
            )
            result.add(temp)
        }


        return result
    }

}