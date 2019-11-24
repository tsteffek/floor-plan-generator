package bin

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.double
import com.github.ajalt.clikt.parameters.types.int
import com.github.sh0nk.matplotlib4j.Plot
import model.Scan2D
import model.Scanner
import java.io.File

fun main(args: Array<String>) = CLI().main(args)

class CLI : CliktCommand() {
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
    ).flag("-d", "--dont-show", default = true)
    private val rotation by option(
        "-r",
        "--rotate",
        help = "Rotates the result by amount of degrees [0.0]"
    ).double().default(0.0)

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
    ).int().default(100)
    private val incremental by option(
        "-i",
        "--incremental",
        help = "Whether the scanner works incrementally or with total values [incremental]"
    ).flag("-t", "--total", default = true)
    private val stepAngle by option(
        "-a",
        "--angle",
        help = "The angle the scanner rotates per step [0.018]"
    ).double().default(0.018)
    private val clockwise by option(
        "-c",
        "--clockwise",
        help = "Whether the scanner rotates clockwise or counterclockwise [clockwise]"
    ).flag("--counterclockwise", default = true)

    override fun run() {
        val file = File(scanPath)

        val scan = Scan2D.fromTSV(
            file,
            Scanner(scannerId, incremental, stepAngle, clockwise, qualityMax)
        )
        val pointCloud = scan.rotateBy(rotation)

        if (diagram) {
            val x = pointCloud.map { it.x }
            val y = pointCloud.map { it.y }

            val plt: Plot = Plot.create()
            plt.plot().add(x, y, "-")
            plt.plot().add(x, y, ".")
            plt.title(file.nameWithoutExtension)
            plt.show()

            if (!outPath.isNullOrBlank()) {
                plt.savefig(outPath + file.nameWithoutExtension)
                plt.executeSilently()
            }
        }
    }
}
