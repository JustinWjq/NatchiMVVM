package com.wanandroid.natchikotlin.ui.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

import com.wanandroid.natchikotlin.R


/**
 * Created by JustinWjq
 *
 * @date 2020-01-03.
 * description：
 */
class BubbleProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var mPaintProgress: Paint? = null
    private var mPaintBubble: Paint? = null
    private var mPaintProgressStr: Paint? = null
    private var mPathMeasure: PathMeasure? = null
    private var mPathSrc: Path? = null
    private var mPathDst: Path? = null
    private var mPathBubble: Path? = null
    private var mColorProgressBg: Int = 0//进度条的背景颜色
    private var mColorProgress: Int = 0//进度条的进度颜色
    private var mColorProgressStr = Color.RED//进度条的进度文字的颜色
    private val mProgressHeight = 10f
    private var mProgress = 0f//进度条的进度
    private val mBubbleTriangleHeight = 15f//气泡底部小三角高度
    private val mBubbleRectRound = 5f//气泡的圆角
    private var mProgressStr = "0%"//显示进度的字符串
    private val mTextSize = 40f//进度条文字大小
    private var mFontMetricsInt: Paint.FontMetricsInt? = null
    private val mProgressStrMargin = 30f//气泡的边距
    var mShowBubble = false

    init {
        init()
    }

    private fun init() {
        mPaintProgress = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
            strokeWidth = mProgressHeight
        }


        mPaintBubble = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeCap = Paint.Cap.ROUND//设置线头为圆角
            style = Paint.Style.FILL
            strokeJoin = Paint.Join.ROUND//设置拐角为圆角
        }


        mPaintProgressStr = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeWidth = 1f
            style = Paint.Style.FILL_AND_STROKE
            color = mColorProgressStr
            textSize = mTextSize//设置字体大小
            textAlign = Paint.Align.CENTER//将文字水平居中
        }

        mColorProgressStr = resources.getColor(R.color.color_FA6400)

        mPathSrc = Path()
        mPathDst = Path()
        mPathBubble = Path()
        mPathMeasure = PathMeasure()

        mColorProgressBg = resources.getColor(R.color.color_ECECEC)
        mColorProgress = resources.getColor(R.color.red)
        mPaintBubble!!.color = resources.getColor(R.color.color_FFECEC)//设置气泡的颜色

        mFontMetricsInt = mPaintProgressStr!!.fontMetricsInt
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPathSrc!!.moveTo(0f, 0f)
        mPathSrc!!.lineTo(w.toFloat(), 0f)//进度条位置在控件整体底部，且距离控件左边和右边各20像素
        mPathMeasure!!.setPath(mPathSrc, false)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 100
        var desiredHeight = 130
//        if (mShowBubble) {
//            desiredHeight = 130
//        } else {
//            desiredHeight = 10
//        }


        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        val width: Int
        val height: Int

        //Measure Width
        if (widthMode == View.MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize
        } else if (widthMode == View.MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize)
        } else {
            //Be whatever you want
            width = desiredWidth
        }

        //Measure Height
        if (heightMode == View.MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize
        } else if (heightMode == View.MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize)
        } else {
            //Be whatever you want
            height = desiredHeight
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画进度条
        drawProgress(canvas)
        //画气泡
        if (mShowBubble) {

        }
        drawBubble(canvas)
    }


    private fun drawBubble(canvas: Canvas) {
        val rect = Rect()
        mPaintProgressStr!!.getTextBounds(
            mProgressStr,
            0,
            mProgressStr.length,
            rect
        )//返回包围整个字符串的最小的一个Rect区域，以此计算出文字的高度和宽度
        val width = (rect.width() + mProgressStrMargin).toInt()//计算字符串宽度(加上设置的边距)
        val height = (rect.height() + mProgressStrMargin).toInt()//计算字符串高度(加上设置的边距)
        mPathBubble!!.reset()
        val p = FloatArray(2)//用于存储点坐标的数组
        val t = FloatArray(2)
        val stop = mPathMeasure!!.length * mProgress//计算进度条的进度
        mPathMeasure!!.getPosTan(stop, p, t)//获取进度所对应点的左边
        mPathBubble!!.moveTo(p[0], p[1] + mProgressHeight)
        mPathBubble!!.lineTo(
            p[0] + mBubbleTriangleHeight,
            p[1] + mBubbleTriangleHeight + mProgressHeight
        )//假设底部小三角为等腰直角三角形，那么三角形的高度就等于底边长度的1/2
        mPathBubble!!.lineTo(
            p[0] - mBubbleTriangleHeight,
            p[1] + mBubbleTriangleHeight + mProgressHeight
        )
        mPathBubble!!.close()//使路径闭合从而形成三角形
        //这里是计算文字所在矩形的位置及大小
        //left:因为设置的气泡底部三角形为等腰直角三角形，所以矩形的左边位置为，
        //      进度所在的横坐标 - 底部三角形高度 - 矩形圆角的半径(不减去圆角半径的话显得不够圆润)，
        //      而(mProgress*width)则是为了不断改变气泡底部的三角形与气泡顶部矩形的相对位置
        //      否则在进度条开始或结束位置可能为显示不全
        //top:进度所在的高度 - 底部三角形高度 - 进度条高度 - 矩形高度
        //right:矩形右边位置的计算原理与左边相同，同样((1-mProgress)*width)也是为了不断改变气泡底部的三角形与气泡顶部矩形的相对位置（与left相对应）
        //bottom:这个就简单了，与top相比小了一个矩形的高度
        val rectF = RectF(
            p[0] + mBubbleTriangleHeight + mBubbleRectRound / 2 + (1 - mProgress) * width,
            p[1] + mBubbleTriangleHeight + mProgressHeight + height.toFloat(),
            p[0] - mBubbleTriangleHeight - mBubbleRectRound / 2 - mProgress * width,
            p[1] + mBubbleTriangleHeight + mProgressHeight
        )
        mPathBubble!!.addRoundRect(
            rectF,
            mBubbleRectRound,
            mBubbleRectRound,
            Path.Direction.CW
        )//添加矩形路径
        canvas.drawPath(mPathBubble!!, mPaintBubble!!)//绘制气泡
        val i =
            (mFontMetricsInt!!.bottom - mFontMetricsInt!!.ascent) / 2 - mFontMetricsInt!!.bottom//让文字垂直居中
        canvas.drawText(
            mProgressStr,
            rectF.centerX(),
            rectF.centerY() + i,
            mPaintProgressStr!!
        )//绘制文字（将文字绘制在气泡矩形的中心点位置）
    }

    private fun drawProgress(canvas: Canvas) {
        mPathDst!!.reset()
        mPaintProgress!!.color = mColorProgressBg
        canvas.drawPath(mPathSrc!!, mPaintProgress!!)//绘制进度背景（灰色部分）
        val stop = mPathMeasure!!.length * mProgress//计算进度条的进度
        mPathMeasure!!.getSegment(0f, stop, mPathDst, true)//得到与进度对应的路径
        mPaintProgress!!.color = mColorProgress
        canvas.drawPath(mPathDst!!, mPaintProgress!!)//绘制进度
    }

    /**
     * 设置进度
     * @param progress
     */
    fun setProgress(progress: Float) {
        mProgress = progress
        if (progress == 0.9f) {
            mProgressStr = "就差一步啦！"
        } else {
            mProgressStr = (progress * 100).toInt().toString() + "%"
        }

        invalidate()//设置完进度进行重绘
    }

    fun setShowBubble(showBubble: Boolean) {
        this.mShowBubble = showBubble
        requestLayout()
    }

    /**
     * 设置动画进度
     */
    fun setProgressWithAnim(progress: Float) {
        ObjectAnimator.ofFloat(this, "progress", progress).setDuration(2000).start()
    }
}
