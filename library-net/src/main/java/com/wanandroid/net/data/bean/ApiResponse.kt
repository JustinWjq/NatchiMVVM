package com.wanandroid.net.data.bean

/**
 * Created by JustinWjq
 * @date 2019-10-15.
 * description：
 *      {
 *       data:[],//或者{}
 *       errorCode:0,
 *       errorMsg:""
 *      }
 */

data class ApiResponse<T>(
    var data: T?,
    var errorCode: Int,
    var errorMsg: String
)