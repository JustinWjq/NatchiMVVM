package com.wanandroid.natchikotlin.ui.dashboard

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.ToastUtils
import com.wanandroid.commonlib.utils.LogUtils
import com.wanandroid.natchikotlin.Point
import com.wanandroid.natchikotlin.PointEvaluator
import com.wanandroid.natchikotlin.R
import com.wanandroid.natchikotlin.data.LoadState
import com.wanandroid.natchikotlin.ui.home.adpter.MultipleItem
import com.wanandroid.natchikotlin.ui.home.adpter.MultipleItemQuickAdapter
import kotlinx.android.synthetic.main.dashboard_fragment.*

/*
* 多类型item的显示
* 可编辑
* 数据统一存储
*
*
* */

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.dashboard_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        val bt: Button = root.findViewById(R.id.bt)
        val et: EditText = root.findViewById(R.id.edittext)
        et.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s1: Editable?) {
                LogUtils.d("TextWatcher","afterTextChanged--${s1.toString()}")
                //本想通过setSpan来改变文本，但是找了下好像不支持插入文本，文本内容不可变，这可以提一下setSpan可以改变文本的样式啥的，一大堆，可以自行度娘，这里不解释
//                s1?.setSpan(UnderlineSpan(),0,s1.length,Spanned.SPAN_INCLUSIVE_INCLUSIVE)

                //看了下源码的解释说，建议在这个方法修改s的值，但是要小心判断，不然回导致这个方法无限的循坏

                //beforeTextChanged onTextChanged 不建议修改，那老大都这么说了，那小弟只能这么做了


                //不无限循环，最重要的一点就是，要判断好，不然你修改了s的值，s又回到这个方法，这个时候你没有判断好，那么无限的循环，你的程序就会卡死了
                //首先先把inputtype设置为 numberDecimal，这样输入框里最多只存在一个点，当然你想自己写判断也行，我是偷懒了
                var s = s1.toString()
                // 你先要考虑可能出现的情况，比如
                // 1. 如果只有.00,需要什么处理，
                // 2. 光标的位置，需要怎么变化
                // 3. 0是第一位的时候，第二是数字，应该怎么做，
                // 4. 当用户删除小数点后一位时，是否需要补全，会造成无法输入小数点后第二位的情况
                // 5. 0.00允许吗

                // 这些其实都是在调试中出现的问题，有些情况还需要跟产品确认下体验
                //
                 if (s.contains(".")) {
                     val indexOf = s.indexOf(".")
                     LogUtils.d("indexOf","--$indexOf")
                     LogUtils.d("indexOf","--${s.substring(s.indexOf("."))}")

                     if (s.length == 3 && indexOf ==0) {
                         et.setText("")
                         return
                     }

                     if (s.indexOf(".")>=2&&s.subSequence(0,1).toString()=="0"){
                         s = s.subSequence(1,
                             s.length).toString()

                         et.setText(s)
                         et.setSelection(s.indexOf("."))
                     }
                     // 判断小数点后只能输入两位
                     if (s.length - 1 - s.indexOf(".") > 2) {
                         //去除小数点后面三位的数字
                         s = s.subSequence(0,
                             s.indexOf(".").plus(3)
                         ).toString()
                         et.setText(s)
                         et.setSelection(s.length)
                     }

                } else {
                    if (s.isNotEmpty()) {
                        et.setText("$s.00")
                        et.setSelection(s.length)
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                LogUtils.d("TextWatcher","beforeTextChanged--${s.toString()}--start--${start}--count--${count}--after${after}")

            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                LogUtils.d("TextWatcher","onTextChanged--${s.toString()}--start--${start}--before--${before}--count--${count}")

            }

        })


        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })
        dashboardViewModel.loadState.observe(this, Observer {
            when (it) {
                is LoadState.Loading -> {
//                    progress_bar.visibility = View.VISIBLE

                }
                is LoadState.Success -> {
//                    progress_view.setProgressWithAnim(0.8f)

                    progress_bar.visibility = View.GONE
                }
                is LoadState.Error -> {
//                    progress_bar.visibility = View.GONE
                    ToastUtils.showLong(it.error)
                }
                else -> {
                }
            }
        })
        dashboardViewModel.imageData.observe(this, Observer {
            ToastUtils.showLong("收到数据大小----${it.size}")

        })
        bt.setOnClickListener {
            ToastUtils.showLong("开始动画！！！")

//            startAnimator(bt)
            startAnimatorObject(bt)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel.getData()
        radarView.start()
    }


    var mAdapter: MultipleItemQuickAdapter? = null
    fun initData() {
        var list = ArrayList<MultipleItem>()
        list.apply {
            add(MultipleItem(1).apply {
                name = "1231"
            })
            add(MultipleItem(2).apply {
                name = "1231"
            })
            add(MultipleItem(3).apply {
                name = "1231"
            })
        }
        mAdapter = MultipleItemQuickAdapter(list)

        recyclerView.adapter = mAdapter
        mAdapter?.setNewData(list)
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            view.findViewById<TextView>(R.id.tv).text = "12313"
        }

    }

    fun startAnimator(bt: Button) {
        val anim = ValueAnimator.ofInt(bt.x.toInt(), 200)
        anim.duration = 1000
        anim.addUpdateListener {
            val animatedValue = it.animatedValue as Int
            Log.i("addUpdateListener", "" + it.animatedValue)
            bt.x = animatedValue.toFloat()
            bt.requestLayout()
        }

        anim.start()
    }
    fun startAnimatorObject(bt: Button) {
        var startPoint = Point(bt.x,bt.y)
        var endPoint = Point(bt.x+100f,bt.y+100f)
        val anim = ValueAnimator.ofObject(PointEvaluator(),startPoint,endPoint)
        val anim1 = ObjectAnimator.ofFloat(bt,"scaleX",1f,0.5f)
        val anim2 = ObjectAnimator.ofFloat(bt,"scaleY",1f,0.5f)


        anim.addUpdateListener {
            val animatedValue = it.animatedValue as Point
            Log.i("addUpdateListener", "" + animatedValue.x)
            Log.i("addUpdateListener", "" + animatedValue.y)
            bt.x = animatedValue.x
            bt.y = animatedValue.y
            bt.requestLayout()
        }

        val animSet = AnimatorSet()
        animSet.play(anim).with(anim1).with(anim2)
        animSet.duration = 1000
        animSet.start()


    }

}