//package model.geometry
//
///**
// * Input: P – data points
// * Output: H∗– model instances, L∗– labeling
// * 1: H0 := InstanceGeneration(P); i := 1;
// * 2: repeat
// * 3: Hi := ModeSeeking(Hi−1); ⊲ by Median-Shift
// * 4: Li := Labeling(Hi, P); ⊲ by α-expansion
// * 5: Hi := ModelFitting(Hi, Li, P); ⊲ by Weiszfeld
// * 6: i := i + 1;
// * 7: until !Convergence(Hi, Li)
// * 8: H∗ := Hi−1, L∗ := Li−1;
// * 9: H∗, L∗ := ModelValidation(H∗, L∗) ⊲ Alg. 2
// */
//fun multi_x(points: Array<out Point>): Map<GeometricObject, List<Point>> {
//
//}
//
//
///**
// * E(L) = Ed(L) + wgEg(L) + wcEc(L)
// */
//abstract class Energy(weight_c: Double, weight_g: Double) {
//    fun energy(): Double =
//        data_energy() + weight_g * geometric_energy + weight_c * complexity_energy
//
//    /**
//     * Ed(L) = Sum [p∈P] (φL(p)(θL(p), p))
//     */
//    fun data_energy()
//
//    /**
//     * Eg(L) : (P → H) → R = Sum [(p,q)∈ N] wpq * Boolean( L(p) != L(q) ).toInt()
//     * N := Neighbourhood Graph, Eg increases energy if neighbouring points are differently labeled
//     */
//    fun geometric_energy()
//}
//
///**
// * L ∈ P → H
// */
//abstract class Label(point: Point, objectClass: GeometricObject)
//
//open class GeometricObject()
//
///**
// * θ := [α c]T
// */
//abstract class Line(angle: Double, constant: Double) : GeometricObject() {
//
//    /**
//     * φ := | cos(α)x + sin(α)y + c|
//     */
//    abstract fun distanceToPoint(point: Point): Double
//
//    companion object {
//        /**
//         * τ
//         */
//        fun fromPoints(vararg points: Point): Line {
//        }
//    }
//}
//
//
///**
// * θ := [cx cy r]T
// */
//abstract class Circle(x: Double, y: Double, radius: Double) : GeometricObject() {
//
//    /**
//     * φ := |r − sqrt( (cx − x)^2 + (cy − y)^2 )|
//     */
//    abstract fun distanceToPoint(point: Point): Double
//
//    companion object {
//        /**
//         * τ
//         */
//        fun fromPoints(vararg points: Point): Line {
//        }
//    }
//}
//
///**
// * θ := ∅
// */
//abstract class Outlier(x: Double, y: Double, radius: Double) : GeometricObject() {
//
//    /**
//     * φ := k
//     */
//    abstract fun distanceToPoint(point: Point): Double
//
//    companion object {
//        /**
//         * τ := ∅
//         */
//        fun fromPoints(vararg points: Point): Line {
//        }
//    }
//}
