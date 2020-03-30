# WanAndroidKotlin
一个有态度的WanAndroid

## 项目架构
- Repository（LocalDataSource，RemoteDataSource）
### UI（ViewModel，Activity，Fragment，XML）
    - ViewModel (设计用来以可感知生命周期的方式存储和管理 UI 相关数据)
    -LiveData (一个数据持有类 数据可以被观察者订阅 
                能够感知组件（Fragment、Activity、Service）的生命周期
                只有在组件出于激活状态（STARTED、RESUMED）才会通知观察者有数据更新
                )
- UseCase（根据实际业务逻辑处理数据）

### Repository


## 从这里能学到什么
- 协程搭配网络框架更香
- Jetpack 架构组件 AAC

## 屏幕适配方案- 今日头条

## 添加组件化 ARouter
[组件化文章](https://juejin.im/post/5b5f17976fb9a04fa775658d#heading-6)
- 不同模块  负责自己的业务实现
- 模块之间的交互 
- 一个host 壳模块 管理其他业务逻辑module


## git仓库
git submodule 管理子模块

## 换肤

## 国际化

*** 
不使用navigation的原因是，每次返回会重新渲染，再加上项目中使用了组件化，
所以就直接这里使用组件化的，页面跳转更好


#### 遇到的问题


