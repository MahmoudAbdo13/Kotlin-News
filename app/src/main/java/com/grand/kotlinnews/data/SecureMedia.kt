package com.grand.kotlinnews.data

data class SecureMedia(
    val oembed: Oembed,
    val type: String
) {
    data class Oembed(
        val thumbnail_url: String,
    )
}