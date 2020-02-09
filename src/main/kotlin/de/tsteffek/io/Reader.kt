package de.tsteffek.io

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

/**
 * Reads the given [File] as TSV, assumes a header line.
 * @param tsv a [File] containing tab-separated values
 * @return a [List] of [Map]s which represent a single line respectively
 */
fun readFromTSV(tsv: File): List<Map<String, String>> =
    getTSVReader().readAllWithHeader(tsv)

/**
 * Reads the given [String] as TSV file, assumes a header line.
 * @param tsv a [String] with the path and name to a file containing
 * tab-separated values
 * @return a [List] of [Map]s which represent a single line respectively
 */
fun readFromTSV(tsv: String): List<Map<String, String>> =
    getTSVReader().readAllWithHeader(tsv)

private fun getTSVReader() = csvReader {
    charset = "UTF-8"
    quoteChar = '"'
    delimiter = '\t'
    escapeChar = '\\'
}
