package com.wanandroid.commonlib.utils

import android.util.Log
import com.wanandroid.commonlib.BuildConfig

/**
 * Created by JustinWjq
 * @date 2019-11-11.
 * description：
 */

class LogUtils {
    companion object {

        fun e(tag: String="wananzhuo", log: Any) {
            if (BuildConfig.DEBUG) {
                Log.e(tag, log.toString())
            }
        }

        fun d(tag: String="wananzhuo", log: Any) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, log.toString())
            }
        }

        fun i( log: Any) {
            if (BuildConfig.DEBUG) {
                Log.i("wananzhuo", log.toString())
            }
        }
    }
}