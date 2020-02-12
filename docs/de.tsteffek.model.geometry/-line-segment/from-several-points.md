[docs](../../index.md) / [de.tsteffek.model.geometry](../index.md) / [LineSegment](index.md) / [fromSeveralPoints](./from-several-points.md)

# fromSeveralPoints

`fun fromSeveralPoints(points: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`PolarPoint`](../-polar-point/index.md)`>): `[`LineSegment`](index.md)

Fits a line between several [PolarPoint](../-polar-point/index.md)s.
Utilizes the natural ordering of PolarPoints to determine ending and starting point.
Algorithm used: Least Squares following
https://www.varsitytutors.com/hotmath/hotmath_help/topics/line-of-best-fit

