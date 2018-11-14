package com.comic_con.museum.ar.persistence.responses

/**
 * This is a sample response. Note that it must extend WebResponsePayload
 */

class TestResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
): WebResponsePayload

class User(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val avatar: String
)