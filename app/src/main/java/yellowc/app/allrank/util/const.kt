package yellowc.app.allrank.util

const val RESERVATION_URL = "http://www.cgv.co.kr/movies/?lt=1&ft=0"
const val MELON_CHART_URL = "https://www.melon.com/chart/"
const val METACRITIC_URL = "https://www.metacritic.com/browse/games/score/metascore/90day/all/filtered?sort=desc"
const val STEAM_MOST_PLAYED_URL = "https://steamplayercount.com/popular"
const val TREND_URL ="https://keyzard.org/realtimekeyword"
const val NEWS_URL = "https://news.google.com/rss/search?q=+when:1d&hl=ko&gl=KR&ceid=KR:ko"
const val LIBRARY_API_URL ="http://data4library.kr/api/"
const val BOOK_STORE_API_URL = "http://book.interpark.com/api/"
const val FOREIGN_MUSIC_URL = "https://www.officialcharts.com/charts/billboard-hot-100-chart/"
const val MOVIE_BOXOFFICE_API_URL = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
const val MOVIE_SEARCH_NAVER_API_URL = "https://openapi.naver.com/v1/search/"

//Types
const val JSOUP_KOREAN_MUSIC = "KOREAN"
const val JSOUP_METACRITIC ="TOP_SELLER"
const val JSOUP_STEAM_MOST_PLAYED ="MOST_PLAYED"
const val JSOUP_RESERVATION = "RESERVATION"
const val JSOUP_TREND = "TREND"
const val JSOUP_FOREIGN = "BILLBOARD"
const val JSOUP_NEWS = "NEWS"

//notification
const val NOTIFICATION_CHANNEL_NAME = "ALLRANK"
const val NOTIFICATION_CHANNEL_DESCRIPTION = "NOW_RANKS"
const val CHANNEL_ID ="ALLRANKCHANNEL"
const val NOTIFICATION_ID = 137
val NOTIFICATION_TITLE: CharSequence = "오늘의 주요 알림"


//rankDiff
const val UP = "UP"
const val DOWN = "DOWN"