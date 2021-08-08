package com.example.backgroundwork.utils.parser

/**
 *
 * @author Omelyuk Anton
 */
interface JsonParser {

    fun <T> serialize(json: String): T

    fun <T> deserialize(obj: T): String

}