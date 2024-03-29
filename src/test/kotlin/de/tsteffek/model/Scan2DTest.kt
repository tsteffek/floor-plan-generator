package de.tsteffek.model

import io.kotlintest.assertSoftly
import io.kotlintest.data.forall
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.*
import de.tsteffek.model.geometry.PolarPoint
import mu.KotlinLogging
import java.lang.IllegalArgumentException
import kotlin.math.sqrt

private val logger by lazy { KotlinLogging.logger {} }

class Scan2DTest : FreeSpec({
    "companion object" - {
        "fromTSV" - {
            "from total values" - {
                val testScanner =
                    Scanner("TestScanner-Total", false, 45.0, false, 100)
                val tsv = """
                    id	step size	distance	quality
                    1	0	1	1
                    2	1	1.41421356	2
                    13	2	1	3
                """.trimIndent()
                val tsvReverse = """
                    id	step size	distance	quality
                    1	0	1	1
                    2	1	1.41421356	2
                    13	2	1	3
                """.trimIndent()

                val targetPointCloud = listOf(
                    PolarPoint(0.0, 1.0, 1),
                    PolarPoint(Math.toRadians(45.0), sqrt(2.0), 2),
                    PolarPoint(Math.toRadians(90.0), 1.0, 3)
                )

                listOf(
                    row("normal tsv", Scan2D.fromTSV(tsv, testScanner)),
                    row("reverse tsv", Scan2D.fromTSV(tsvReverse, testScanner))
                ).map { (method, scan) ->
                    "should create the pointCloud correctly from $method" {
                        assertSoftly {
                            scan.pointCloud.size shouldBe 3
                            scan.pointCloud shouldContainExactly targetPointCloud
                        }
                    }
                }
            }

            "from incremental values" - {
                val testScannerInc =
                    Scanner("TestScanner-Inc", true, 45.0, false, 100)
                val tsvInc = """
                    id	step size	distance	quality
                    13	0	1	1
                    2	1	1.41421356	2
                    1	1	1	3
                """.trimIndent()
                val tsvIncReverse = """
                    id	step size	distance	quality
                    13	0	1	1
                    2	1	1.41421356	2
                    1	1	1	3
                """.trimIndent()

                val targetPointCloud = listOf(
                    PolarPoint(0.0, 1.0, 1),
                    PolarPoint(Math.toRadians(45.0), sqrt(2.0), 2),
                    PolarPoint(Math.toRadians(90.0), 1.0, 3)
                )

                listOf(
                    row("normal tsv", Scan2D.fromTSV(tsvInc, testScannerInc)),
                    row("reverse tsv", Scan2D.fromTSV(tsvIncReverse, testScannerInc))
                ).map { (method, scan) ->
                    "should create the pointCloud correctly from $method" {
                        assertSoftly {
                            scan.pointCloud.size shouldBe 3
                            scan.pointCloud shouldContainExactly targetPointCloud
                        }
                    }
                }
            }

            "from clockwise data" - {
                val testScanner =
                    Scanner("TestScanner-Total", false, 45.0, true, 100)
                val tsv = """
                    id	step size	distance	quality
                    1	0	1	1
                    2	1	1.41421356	2
                    13	2	1	3
                """.trimIndent()
                val tsvReverse = """
                    id	step size	distance	quality
                    1	0	1	1
                    2	1	1.41421356	2
                    13	2	1	3
                """.trimIndent()

                val targetPointCloud = listOf(
                    PolarPoint(0.0, 1.0, 1),
                    PolarPoint(Math.toRadians(-45.0), sqrt(2.0), 2),
                    PolarPoint(Math.toRadians(-90.0), 1.0, 3)
                )

                listOf(
                    row("normal clockwise tsv", Scan2D.fromTSV(tsv, testScanner)),
                    row("reverse clockwise tsv", Scan2D.fromTSV(tsvReverse, testScanner))
                ).map { (method, scan) ->
                    "should create the pointCloud correctly from $method" {
                        assertSoftly {
                            scan.pointCloud.size shouldBe 3
                            scan.pointCloud shouldContainExactly targetPointCloud
                        }
                    }
                }
            }

            "filters quality above threshold" {
                val qualityMax = 100
                val testScanner =
                    Scanner("TestScanner-Total", false, 45.0, false, qualityMax)
                val tsv = """
                    id	step size	distance	quality
                    1	0	1	1
                    2	1	1.41421356	$qualityMax
                    13	2	1	3
                """.trimIndent()

                val targetPointCloud = listOf(
                    PolarPoint(0.0, 1.0, 1),
                    PolarPoint(Math.toRadians(90.0), 1.0, 3)
                )

                val scan = Scan2D.fromTSV(tsv, testScanner)
                assertSoftly {
                    scan.pointCloud.size shouldBe 2
                    scan.pointCloud shouldContainExactly targetPointCloud
                }
            }

            "filters NaN distance or quality" {
                val qualityMax = 100
                val testScanner =
                    Scanner("TestScanner-Total", false, 45.0, false, qualityMax)
                val tsv = """
                    id	step size	distance	quality
                    1	0	1	1
                    2	1	NaN	2
                    2	1	x	2
                    3	1.5	1.41421356	NaN
                    13	2	1	3
                """.trimIndent()

                val targetPointCloud = listOf(
                    PolarPoint(0.0, 1.0, 1),
                    PolarPoint(Math.toRadians(90.0), 1.0, 3)
                )

                val scan = Scan2D.fromTSV(tsv, testScanner)
                assertSoftly {
                    scan.pointCloud.size shouldBe 2
                    scan.pointCloud shouldContainExactly targetPointCloud
                }
            }

            "throws error on missing id" {
                val testScanner =
                    Scanner("TestScanner-Total", false, 45.0, false, 100)
                forall(
                    row(
                        """
                    id	step size	distance	quality
                    NaN	0	1	1
                """.trimIndent()
                    ),
                    row(
                        """
                    id	step size	distance	quality
                    	0	1	1
                """.trimIndent()
                    )
                ) { tsv ->
                    shouldThrow<IllegalArgumentException> {
                        Scan2D.fromTSV(tsv, testScanner)
                    }
                }
            }

            "throws error on missing step size" {
                val testScanner =
                    Scanner("TestScanner-Total", false, 45.0, false, 100)
                forall(
                    row(
                        """
                    id	step size	distance	quality
                    0	NaN	1	1
                """.trimIndent()
                    ),
                    row(
                        """
                    id	step size	distance	quality
                    0		1	1
                """.trimIndent()
                    )
                ) { tsv ->
                    shouldThrow<IllegalArgumentException> {
                        Scan2D.fromTSV(tsv, testScanner)
                    }
                }
            }
        }
    }

    "rotateBy" {
        val testScanner = Scanner("TestScanner-Total", false, 45.0, false, 100)
        val tsv = """
                    id	step size	distance	quality
                    1	0	1	1
                    2	1	1.41421356	2
                    13	2	1	3
                """.trimIndent()
        val scan = Scan2D.fromTSV(tsv, testScanner)

        forall(
            row(
                Math.toRadians(0.0),
                listOf(
                    PolarPoint(0.0, 1.0, 1),
                    PolarPoint(Math.toRadians(45.0), sqrt(2.0), 2),
                    PolarPoint(Math.toRadians(90.0), 1.0, 3)
                )
            ),
            row(
                Math.toRadians(45.0),
                listOf(
                    PolarPoint(Math.toRadians(45.0), 1.0, 1),
                    PolarPoint(Math.toRadians(45.0 + 45.0), sqrt(2.0), 2),
                    PolarPoint(Math.toRadians(90.0 + 45.0), 1.0, 3)
                )
            ),
            row(
                Math.toRadians(270.0),
                listOf(
                    PolarPoint(Math.toRadians(270.0), 1.0, 1),
                    PolarPoint(Math.toRadians(45.0 + 270.0), sqrt(2.0), 2),
                    PolarPoint(Math.toRadians(90.0 + 270.0), 1.0, 3)
                )
            )
        ) { rotation, targetPointCloud ->
            assertSoftly {
                val pointCloud = scan.rotateBy(rotation)
                pointCloud shouldContainExactly targetPointCloud
            }
        }
    }
})
