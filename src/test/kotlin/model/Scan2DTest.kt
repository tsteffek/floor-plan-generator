package model

import io.kotlintest.matchers.asClue
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.*
import model.geometry.Point
import mu.KotlinLogging
import kotlin.math.sqrt

private val logger by lazy { KotlinLogging.logger {} }

class Scan2DTest : FreeSpec() {

    init {
        "companion object" - {

            val testScanner = Scanner("TestScanner-Total", false, 45.0, true, 100)
            val tsv = """
                id	step size	distance	quality
                1	0	1	54
                2	1	1.41421356	58
                13	2	1	62
            """.trimIndent()
        }

        "pointCloud" - {
            "from total values" - {

                val testScanner = Scanner("TestScanner-Total", false, 45.0, true, 100)
                val tsv = """
                    id	step size	distance	quality
                    1	0	1	54
                    2	1	1.41421356	58
                    13	2	1	62
                """.trimIndent()
                val tsvReverse = """
                    id	step size	distance	quality
                    1	0	1	54
                    2	1	1.41421356	58
                    13	2	1	62
                """.trimIndent()

                val targetPointCloud = table(
                    headers("Point"),
                    row(Point(1.0, 0.0, 54)),
                    row(Point(sqrt(2.0), 45.0, 58)),
                    row(Point(1.0, -90.0, 62))
                )

                "should be computed" {
                    val scan = Scan2D.fromTSV(tsv, testScanner)
                    scan.pointCloud.size shouldBe 3
                    scan.pointCloud.asClue {
                        forAll(targetPointCloud) {
                            scan.pointCloud shouldContain it
                        }
                    }
                }

                "should be computed, even reverse" {
                    val scan = Scan2D.fromTSV(tsvReverse, testScanner)
                    scan.pointCloud.size shouldBe 3
                    scan.pointCloud.asClue {
                        forAll(targetPointCloud) {
                            scan.pointCloud shouldContain it
                        }
                    }
                }
            }

            "from incremental values" - {

                val testScannerInc = Scanner("TestScanner-Inc", true, 45.0, true, 100)

                val tsvInc = """
                    id	step size	distance	quality
                    13	0	1	54
                    2	1	1.41421356	58
                    1	1	1	62
                """.trimIndent()

                val tsvIncReverse = """
                    id	step size	distance	quality
                    13	0	1	54
                    2	1	1.41421356	58
                    1	1	1	62
                """.trimIndent()

                val targetPointCloud = table(
                    headers("Point"),
                    row(Point(1.0, 0.0, 54)),
                    row(Point(1.0, -1.0, 58)),
                    row(Point(0.0, -1.0, 62))
                )

                "should be computed" {
                    val scan = Scan2D.fromTSV(tsvInc, testScannerInc)
                    scan.pointCloud.size shouldBe 3
                    scan.pointCloud.asClue {
                        forAll(targetPointCloud) {
                            scan.pointCloud shouldContain it
                        }
                    }
                }

                "should be computed, even reverse" {
                    val scan = Scan2D.fromTSV(tsvIncReverse, testScannerInc)
                    scan.pointCloud.size shouldBe 3
                    scan.pointCloud.asClue {
                        forAll(targetPointCloud) {
                            scan.pointCloud shouldContain it
                        }
                    }
                }
            }
        }
    }

}
