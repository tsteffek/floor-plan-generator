package bin

import com.github.sh0nk.matplotlib4j.Plot
import model.Scan2D
import model.Scanner
import java.io.File

fun main(args: Array<String>) {
    val scan = Scan2D.fromTSV(File("./test scans/Scan_oben_1.tsv"), Scanner("ScannerV1", true, 0.9 / 50, true, 100))
    // val scan = Scan2D.fromTSV(tsv, Scanner("ScannerV1", true, 0.9, true))
    val pointCloud = scan.rotateBy(16.4)
    val x = pointCloud.map { it.x }
    val y = pointCloud.map { it.y }

    val plt: Plot = Plot.create()
    plt.plot().add(x, y, "-")//.label("sin")
    plt.plot().add(x, y, ".")
    plt.title("Gerd's upper sleeping room")
    //plt.legend().loc("upper right")
    plt.show()
}


