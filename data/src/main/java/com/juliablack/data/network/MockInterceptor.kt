package com.juliablack.data.network

import okhttp3.*

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val method = chain.request().method()
        val responseString = if (method == GET) {
            when {
                uri.isListRequest() -> {
                    val match = regexItems.find(uri)!!
                    val (offset, count) = match.destructured
                    val countInt = count.toInt()
                    when (offset.toInt()) {
                        0 -> itemsJsonPages[0]
                        countInt -> itemsJsonPages[1]
                        countInt + countInt -> itemsJsonPages[2]
                        else -> ""
                    }
                }
                uri.isDetailsRequest() -> DETAILS_JSON
                else -> ""
            }
        } else {
            ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}

private fun String.isDetailsRequest() = contains(Regex("/api/v1/items/\\d+"))

private fun String.isListRequest() = contains(regexItems)

private val regexItems = Regex("/api/v1/items\\?offset=(\\d+)&count=(\\d+)")

private val itemsJsonPages = listOf(
    """
[{
    "id": 1,
    "link": "/api/v1/items/1",
    "title": "Book 1"
},
{
    "id": 2,
    "link": "/api/v1/items/2",
    "title": "Book 2"
},
{
    "id": 3,
    "link": "/api/v1/items/3",
    "title": "Book 3"
},
{
    "id": 4,
    "link": "/api/v1/items/4",
    "title": "Book 4"
},
{
    "id": 5,
    "link": "/api/v1/items/5",
    "title": "Book 5"
},
{
    "id": 6,
    "link": "/api/v1/items/6",
    "title": "Book 6"
},
{
    "id": 7,
    "link": "/api/v1/items/7",
    "title": "Book 7"
},
{
    "id": 8,
    "link": "/api/v1/items/8",
    "title": "Book 8"
},
{
    "id": 9,
    "link": "/api/v1/items/9",
    "title": "Book 9"
},
{
    "id": 10,
    "link": "/api/v1/items/10",
    "title": "Book 10"
}]""", """
[{
    "id": 11,
    "link": "/api/v1/items/11",
    "title": "Book 11"
},
{
    "id": 12,
    "link": "/api/v1/items/12",
    "title": "Book 12"
},
{
    "id": 13,
    "link": "/api/v1/items/13",
    "title": "Book 13"
},
{
    "id": 14,
    "link": "/api/v1/items/14",
    "title": "Book 14"
},
{
    "id": 15,
    "link": "/api/v1/items/15",
    "title": "Book 15"
},
{
    "id": 16,
    "link": "/api/v1/items/16",
    "title": "Book 16"
},{
    "id": 17,
    "link": "/api/v1/items/17",
    "title": "Book 17"
},
{
    "id": 18,
    "link": "/api/v1/items/18",
    "title": "Book 18"
},
{
    "id": 19,
    "link": "/api/v1/items/19",
    "title": "Book 19"
},
{
    "id": 20,
    "link": "/api/v1/items/20",
    "title": "Book 20"
}]
""", """
[{
    "id": 21,
    "link": "/api/v1/items/21",
    "title": "Book 21"
},
{
    "id": 22,
    "link": "/api/v1/items/22",
    "title": "Book 22"
}]
"""
)

private const val DETAILS_JSON = """
{
    "id": 1,
    "image": "https://mir-s3-cdn-cf.behance.net/project_modules/disp/8bc21955316461.59b89b39bc96d.jpg",
    "title": "1984",
    "author": "George Orwell",
    "price": 5.99
}
"""

private const val GET = "GET"