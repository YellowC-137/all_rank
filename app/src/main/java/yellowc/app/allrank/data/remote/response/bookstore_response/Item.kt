package yellowc.app.allrank.data.remote.response.bookstore_response

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val additionalLink: String,
    val author: String,
    val categoryId: String?=null,
    val categoryName: String,
    val coverLargeUrl: String,
    val coverSmallUrl: String,
    val customerReviewRank: Double,
    val description: String,
    val discountRate: String,
    val isbn: String,
    val itemId: Int,
    val link: String,
    val mileage: String,
    val mileageRate: String,
    val mobileLink: String,
    val priceSales: Int,
    val priceStandard: Int,
    val pubDate: String,
    val publisher: String,
    val rank: Int,
    val reviewCount: Int,
    val saleStatus: String,
    val title: String,
    val translator: String
)