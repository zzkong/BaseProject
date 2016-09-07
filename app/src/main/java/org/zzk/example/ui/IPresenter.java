package org.zzk.example.ui;

/**
 * Created by zwl on 16/9/5.
 */
public interface IPresenter<T extends IView> {

    void attachView(T view);

    void detachView();
}
