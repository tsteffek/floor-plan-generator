package io

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class Reader {
    companion object {
        private fun getTSVReader() = csvReader {
            charset = "UTF-8"
            quoteChar = '"'
            delimiter = '\t'
            escapeChar = '\\'
        }

        fun fromTSV(tsv: File): List<Map<String, String>> =
            getTSVReader().readAllWithHeader(tsv)

        fun fromTSV(tsv: String): List<Map<String, String>> =
            getTSVReader().readAllWithHeader(tsv)
    }
}
