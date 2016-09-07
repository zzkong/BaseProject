# BaseProject
基本项目框架，项目采用MVP+RxJAVA+Okhttp+Retrofit+dagger2实现。项目中带有最基本的BaseActivity，BaseFragment封装

基本功能:1. 滑动返回，所有的activity都extends SwipeBackAppCompatActivity，方便实现滑动返回。
         2. BaseActivity  BaseMvpActivity。如果你想再项目中使用MVP模式 Activity只需extends BaseMvpActivity就可以，
         如果你觉得界面逻辑简单，不想使用MVP模式，可以直接extends BaseActivity。同理，BaseLazyFragment  BaseFragment是一样。
         - 3.懒加载BaseLazyFragment ，BaseLazyFragment里面提供了两个方法
          ![image](/Images/5F1E0C95-412A-4CFC-A321-7C580C256F06.png)
          如果你需要懒加载，就在lazyLoadData()方法里面获取数据，如果不用懒加载，直接在initEventAndData()里面获取数据即可。
         - 4.上拉下拉，上拉刷新建议使用liaohuqiu大大写的，地址https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh，这里推荐两款基于Ultra-Pull-To-Refresh的上拉下拉项目。 https://github.com/Chanven/CommonPullToRefresh https://github.com/pengjianbo/LoadingViewFinal 本项目使用的是LoadingViewFinal，建议以module的形式添加进去，方便定制下拉刷新样式等。
         - 5.项目中使用BaseAdapter 地址：https://github.com/hongyangAndroid/baseAdapter
         6.实现了状态栏，过渡页秒去白屏等功能。
         7.项目主框架代码大量参考了https://github.com/gzsll/TLint  https://github.com/zj-wukewei/Hot http://wuxiaolong.me/2016/06/12/mvpRetrofitRxjava/
         8.所有代码非原创，我只是个搬运工。练手工，如有不妥请留言联系。
         
