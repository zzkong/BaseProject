package org.zzk.example.adapter.commonadapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by zwl on 16/8/16.
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T>{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

    public List<T> getDataList() {
        return mDatas;
    }

    public void removeNotify(T t) {
        mDatas.remove(t);
        notifyDataSetChanged();
    }

    public void clearNotify() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void clearAddallNotify(List<T> ts) {
        mDatas.clear();
        mDatas.addAll(ts);
        notifyDataSetChanged();
    }

    public void addAllNotify(List<T> ts) {
        mDatas.addAll(ts);
        notifyItemRangeChanged(mDatas.size() - 20, 20);
    }
}
