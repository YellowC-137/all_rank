package yellowc.app.allrank.data.remote.datasourceimpl

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import timber.log.Timber
import yellowc.app.allrank.data.remote.api.JsoupService
import yellowc.app.allrank.data.remote.response.jsoup_response.JsoupResponse
import yellowc.app.allrank.util.*
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
            JSOUP_TREND -> {
                Timber.e(type)
                return getTrends(url)
            }
            JSOUP_NEWS -> {
                Timber.e(type)
                return getNews(url)
            }
            JSOUP_STEAM_MOST_PLAYED -> {
                Timber.e(type)
                return getMostPlay(url)
            }
            JSOUP_METACRITIC -> {
                Timber.e(type)
                return getMeta(url)
            }
            JSOUP_FOREIGN -> {
                Timber.e(type)
                return getBillboard(url)
            }
            else -> {
                return emptyList()
            }
        }

    }

    private fun getTrends(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val div = doc.body().select("div.col-sm-12")
        val table = div.select("table.table-hover.table-striped").first()
        val trs = table!!.select("tr:not([class])")
        for (tr in trs) {
            val rank = tr.select("span.realtimeKeyRank").text()
            val td = tr.select("td.ellipsis100")
            val title = td.select("a").attr("title")
            val temp = JsoupResponse(
                rank = rank,
                title = title,
                owner = "",
                ImgUrl = "",
                description = "",
                link = ""
            )
            result.add(temp)
        }

        return result
    }

    private fun getNews(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val items = doc.select("item")
        var rank = 1
        for (item in items) {
            val title = item.select("title").text()
            val link = item.select("link").text()
            val description = item.select("description").text()
            val source = item.select("source").text()
            val temp = JsoupResponse(
                rank = "",
                title = title,
                owner = source,
                ImgUrl = "",
                description = "",
                link = link
            )
            result.add(temp)
            rank += 1
        }

        return result
    }

    private fun getBillboard(url: String): List<JsoupResponse> {

        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val body = doc.body().getElementsByClass("chart-positions")
        val trs = body.select("tr:not([class])")
        for (td in trs) {
            val rank = td.select("span.position").text()
            val covers = td.select("div.cover").select("img").attr("src")
            val title = td.select("div.title").select("a").text()
            val artist = td.select("div.artist").select("a").text()
            val label = td.select("span.label").text()

            if (!rank.isNullOrBlank()) {
                val temp = JsoupResponse(
                    rank = rank,
                    title = title,
                    ImgUrl = covers,
                    owner = artist,
                    description = "$label Records",
                    link = ""
                )
                result.add(temp)
            }
        }
        return result
    }


    private fun getMeta(url: String): List<JsoupResponse> {
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
                val platform = content.select("span.data").text()

                val temp = JsoupResponse(
                    title = title,
                    rank = cnt.toString(),
                    owner = platform,
                    description = "$rate 점",
                    ImgUrl = img,
                    link = ""
                )
                result.add(temp)
            }
        }
        return result
    }


    private fun getMostPlay(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val tbody = doc.selectFirst("tbody")!!
        val trs = tbody.select("tbody tr:nth-child(n+2)")
        var cnt = 1

        for (tr in trs) {
            val img = tr.child(0).select("a").select("img").attr("src")
            val title = tr.child(1).select("a").text()
            val player = tr.child(2).text()
            val max = tr.child(3).text()
            val temp = JsoupResponse(
                rank = cnt.toString(),
                title = title,
                ImgUrl = img,
                owner = "현재 플레이어 수 :$player 명",
                description = "이주 최대 플레이어 수: $max 명",
                link = ""
            )
            cnt += 1
            result.add(temp)
        }

        return result
    }


    private fun getReservation(url: String): List<JsoupResponse> {
        val result = ArrayList<JsoupResponse>()
        val doc: Document = Jsoup.connect(url).get()
        val body = doc.body().getElementsByClass("sect-movie-chart")
        val ols = body.select("ol")
        for (ol in ols) {
            val li = ol.select("li")
            for (element in li) {
                val images = element.select("div.box-image")
                val contents = element.select("div.box-contents")
                val _rank = images.select("strong.rank").text()
                val regex = "No.(\\d+)".toRegex()
                val matchRank = regex.find(_rank)
                val rank = matchRank?.groupValues?.get(1)

                val img = images.select("a").select("span.thumb-image").select("img").attr("src")
                val title = contents.select("a").select("strong.title").text()
                val reservate = contents.select("div.score").select("span").text()
                val date = contents.select("span.txt-info").select("strong").text()

                val pattern = "(\\d+\\.\\d+%).*".toRegex()
                val matchReserv = pattern.find(reservate)
                val reservation = matchReserv?.groupValues?.get(1)

                if (!reservation.isNullOrBlank()) {
                    val temp = JsoupResponse(
                        rank = rank!!,
                        title = title,
                        ImgUrl = img,
                        owner = "예매율: $reservation",
                        description = date,
                        link = ""
                    )
                    result.add(temp)
                }

            }


        }
        return result
    }

    private fun getMelons(url: String): List<JsoupResponse> {
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
                description = albums[i].select("a").text(),
                link = ""
            )
            result.add(temp)
        }
        return result
    }

}