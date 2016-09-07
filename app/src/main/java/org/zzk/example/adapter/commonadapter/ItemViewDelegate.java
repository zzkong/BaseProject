package org.zzk.example.adapter.commonadapter;

/**
 * Created by zwl on 16/8/16.
 */
public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);
}
