# BaseProject
基本项目框架，项目采用MVP+RxJAVA+Okhttp+Retrofit+dagger2实现。项目中带有最基本的BaseActivity，BaseFragment封装

基本功能:1.滑动返回，所有的activity都extends SwipeBackAppCompatActivity，方便实现滑动返回。
         2.BaseActivity  BaseMvpActivity。如果你想再项目中使用MVP模式 Activity只需extends BaseMvpActivity就可以，
         如果你觉得界面逻辑简单，不想使用MVP模式，可以直接extends BaseActivity。同理，BaseLazyFragment  BaseFragment是一样。
         3.懒加载BaseLazyFragment ，BaseLazyFragment里面提供了两个方法
         
