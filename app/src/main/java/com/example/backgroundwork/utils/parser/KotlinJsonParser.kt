package com.example.backgroundwork.utils.parser

import kotlinx.serialization.json.Json

class KotlinJsonParser(
    private val json: Json
) : JsonParser {

    override fun <T> serialize(json: String): T {
        TODO("Not yet implemented")
    }


    override fun <T> deserialize(obj: T): String {
        TODO("Not yet implemented")
    }

}