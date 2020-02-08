package bin

import algorithms.fitLines
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.double
import com.github.ajalt.clikt.parameters.types.int
import com.github.sh0nk.matplotlib4j.Plot
import math.distance
import model.NeighborhoodGraph
import model.Scan2D
import model.Scanner
import model.geometry.LineSegment
import java.io.File


fun main(args: Array<String>) = CLI().main(args)

class CLI : CliktCommand() {

    companion object {
        private const val DEFAULT_ROTATION = 0.0
        private const val DEFAULT_DIAGRAM = true
        private const val DEFAULT_MAX_QUALITY = 100
        private const val DEFAULT_INCREMENTAL = true
        private const val DEFAULT_STEP_ANGLE = 0.018
        private const val DEFAULT_CLOCKWISE = true
    }

    /* Scan options */
    private val scanPath by option(
        "-p",
        "--path",
        help = "Path to scan"
    ).required()
    private val outPath by option(
        "-o",
        "--outpath",
        help = "Path for output [no output]"
    )
    private val diagram by option(
        "-s",
        "--show",
        help = "Whether a diagram will be shown/saved [show]"
    ).flag("-d", "--dont-show", default = DEFAULT_DIAGRAM)
    private val rotation by option(
        "-r",
        "--rotate",
        help = "Rotates the result by amount of degrees [0.0]"
    ).double().default(DEFAULT_ROTATION)

    /* Scanner options */
    private val scannerId by option(
        "-n",
        "--name",
        help = "Scanner name [defaultScanner]"
    ).default("defaultScanner")
    private val qualityMax by option(
        "-q",
        "--max-quality",
        help = "Maximum of quality accepted [100]"
    ).int().default(DEFAULT_MAX_QUALITY)
    private val incremental by option(
        "-i",
        "--incremental",
        help = "Whether the scanner works incrementally or with total values [incremental]"
    ).flag("-t", "--total", default = DEFAULT_INCREMENTAL)
    private val stepAngle by option(
        "-a",
        "--angle",
        help = "The angle the scanner rotates per step [0.018]"
    ).double().default(DEFAULT_STEP_ANGLE)
    private val clockwise by option(
        "-c",
        "--clockwise",
        help = "Whether the scanner rotates clockwise or counterclockwise [clockwise]"
    ).flag("--counterclockwise", default = DEFAULT_CLOCKWISE)

    override fun run() {
        val file = File(scanPath)

        val scanner = Scanner(scannerId, incremental, stepAngle, clockwise, qualityMax)
        val scan = Scan2D.fromTSV(file, scanner)
        val rotatedScan = Scan2D(scan.rotateBy(rotation), scanner)

        val graph = NeighborhoodGraph.fromPolarPoints(rotatedScan.pointCloud)
        val lineSegments = fitLines(graph).map { LineSegment.fromSeveralPoints(it) }

        if (diagram) {
            plot(rotatedScan, lineSegments, file)
        }
    }

    private fun plot(
        rotatedScan: Scan2D,
        lineSegments: List<LineSegment>,
        file: File
    ) {
        val x = rotatedScan.pointCloud.map { it.x }
        val y = rotatedScan.pointCloud.map { it.y }

        val linePoints = lineSegments.map {
            Pair(
                listOf(it.startPoint.x, it.endPoint.x),
                listOf(it.startPoint.y, it.endPoint.y)
            )
        }
        val labels = lineSegments.map { distance(it.startPoint, it.endPoint) }

        val plt: Plot = Plot.create()

        //            plt.plot().add(x, y, "-")
        plt.plot().add(x, y, ".").linewidth("0")

        linePoints.forEachIndexed { index, it ->
            plt.plot().add(it.first, it.second).label(labels[index].toString()).linestyle("-")
        }
        plt.legend()

        plt.title(file.nameWithoutExtension)
        plt.show()

        if (!outPath.isNullOrBlank()) {
            plt.savefig(outPath + file.nameWithoutExtension)
            plt.executeSilently()
        }
    }
}
