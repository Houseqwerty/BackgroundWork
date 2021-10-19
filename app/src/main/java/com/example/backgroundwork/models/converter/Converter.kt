package com.example.backgroundwork.models.converter

/**
 * Конверетер из типа данных [F] в тип данных [T]
 *
 * @author Omelyuk Anton
 */
interface Converter<F, T> {

    /**
     * Конвеертировать из типа данных [F] в тип данных [T]
     *
     * @return [T]
     */
    fun convert(from: F): T
}