package yellowc.app.allrank.data.remote.datasourceimpl

import android.os.AsyncTask
import android.util.Log
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
            JSOUP_NETFLIX -> {
                Timber.e(type)
                return getNetflix(url)
            }
            JSOUP_SEARCHED -> {
                Timber.e(type)
                return getTrends(url)
            }
            JSOUP_STEAM_MOST_PLAYED -> {
                Timber.e(type)
                return getMostPlay(url)
            }
            JSOUP_STEAM_TOP_SELLER -> {
                Timber.e(type)
                return getTopSeller(url)
            }
            else -> {
                return emptyList()
            }
        }

    }


    private suspend fun getTopSeller(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val tables = doc.body().select("table.clamp-list tbody")
        var cnt = 0
        for (tbody in tables) {
            val tr : Elements = tbody.select("tr:not(.spacer)")
            for (element in tr) {
                cnt+=1
                val img = element.select("td.clamp-image-wrap").select("a").select("img").attr("src")
                val content = element.select("td.clamp-summary-wrap")
                val title = content.select("a.title").select("h3").text()
                val rate = content.select("div.clamp-score-wrap").select("a.metascore_anchor").select("div.metascore_w large game positive").text()
                val platform = content.select("span.data").text()

                val temp = JsoupResponse(
                    title = title,
                    rank = cnt.toString(),
                    owner = platform,
                    description = rate,
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

    private suspend fun getNetflix(url: String): List<JsoupResponse> {
        return emptyList()
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