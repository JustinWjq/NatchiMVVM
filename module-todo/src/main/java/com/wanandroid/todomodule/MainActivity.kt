package com.wanandroid.todomodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wanandroid.common.router.RouterActivityPath

@Route(path = RouterActivityPath.Todo.PAGER_MAIN)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.getInstance().inject(this)
    }
}
