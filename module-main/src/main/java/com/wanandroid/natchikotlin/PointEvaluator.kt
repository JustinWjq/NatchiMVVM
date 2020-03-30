package com.wanandroid.natchikotlin

import android.animation.TypeEvaluator

/**
 * Created by JustinWjq
 * @date 2020-03-06.
 * description：
 */
class PointEvaluator :TypeEvaluator<Point> {

    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        var startPoint = startValue
        var endPoint = endValue

        var x = startPoint?.x?.plus(fraction*(endPoint?.x?.minus(startPoint.x)!!))
        var y = startPoint?.y?.plus(fraction*(endPoint?.y?.minus(startPoint.y)!!))

        return  Point(x!!,y!!)
    }

}