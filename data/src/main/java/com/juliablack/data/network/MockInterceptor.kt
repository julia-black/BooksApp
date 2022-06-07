package com.juliablack.data.network

import okhttp3.*

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("/api/v1/items") -> itemsJson
            else -> ""
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

const val itemsJson = """
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
},
{
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
    "link": "/api/v1/items/1",
    "title": "Book 17"
}]
"""