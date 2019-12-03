package io

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

private fun getTSVReader() = csvReader {
    charset = "UTF-8"
    quoteChar = '"'
    delimiter = '\t'
    escapeChar = '\\'
}

fun readFromTSV(tsv: File): List<Map<String, String>> =
    getTSVReader().readAllWithHeader(tsv)

fun readFromTSV(tsv: String): List<Map<String, String>> =
    getTSVReader().readAllWithHeader(tsv)
