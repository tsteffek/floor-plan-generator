package model

import io.kotlintest.TestCase
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.*
import mu.KotlinLogging

private val logger by lazy { KotlinLogging.logger {} }

class Scan2DTest : StringSpec() {

    private lateinit var tsv: String
    private lateinit var tsvInc: String
    private lateinit var testScanner: Scanner
    private lateinit var testScannerInc: Scanner
    private lateinit var targetPointCloud: Table1<Point>

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)

        testScanner = Scanner("TestScanner-Total", false, 45.0, true)
        tsv = """
            id	step size	distance	quality
            1	0	1	54
            2	1	1.41421356	58
            13	2	1	62
        """.trimIndent()

        testScannerInc = Scanner("TestScanner-Inc", true, 45.0, true)
        tsvInc = """
            id	step size	distance	quality
            1	0	1	54
            2	1	1.41421356	58
            13	1	1	62
        """.trimIndent()

        targetPointCloud = table(
            headers("Point"),
            row(Point(0.0, 1.0, 54)),
            row(Point(1.0, 1.0, 58)),
            row(Point(1.0, 0.0, 62))
        )
    }

    init {
        "csv should load into rawData" {
            val scan = Scan2D.fromTSV(tsv, testScanner)
            scan.rawData.size shouldBe 3
            scan.rawData.first() shouldBe Scan2D.ScanData(1, 0.0, 1.0, 54)
            scan.rawData.last() shouldBe Scan2D.ScanData(13, 1.0, 1.0, 62)
        }

        "pointCloud should be computed from total values" {
            val scan = Scan2D.fromTSV(tsv, testScanner)
            scan.pointCloud.size shouldBe 3
            forAll(targetPointCloud) {
                scan.pointCloud shouldContain it
            }
        }

        "pointCloud should be computed from incremental values" {
            val scan = Scan2D.fromTSV(tsvInc, testScannerInc)
            scan.pointCloud.size shouldBe 3
            forAll(targetPointCloud) {
                scan.pointCloud shouldContain it
            }
        }
    }

}
