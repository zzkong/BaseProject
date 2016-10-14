package org.zzk.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zwl on 16/10/13.
 */

public class ImageListBean implements Parcelable{
    public String col;
    public String tag;
    public String tag3;
    public String sort;
    public int totalNum;
    public int startIndex;
    public int returnNumber;
    public List<ImageBean> imgs;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.col);
        dest.writeString(this.tag);
        dest.writeString(this.tag3);
        dest.writeString(this.sort);
        dest.writeInt(this.totalNum);
        dest.writeInt(this.startIndex);
        dest.writeInt(this.returnNumber);
        dest.writeTypedList(this.imgs);
    }

    public ImageListBean() {
    }

    protected ImageListBean(Parcel in) {
        this.col = in.readString();
        this.tag = in.readString();
        this.tag3 = in.readString();
        this.sort = in.readString();
        this.totalNum = in.readInt();
        this.startIndex = in.readInt();
        this.returnNumber = in.readInt();
        this.imgs = in.createTypedArrayList(ImageBean.CREATOR);
    }

    public static final Creator<ImageListBean> CREATOR = new Creator<ImageListBean>() {
        @Override
        public ImageListBean createFromParcel(Parcel source) {
            return new ImageListBean(source);
        }

        @Override
        public ImageListBean[] newArray(int size) {
            return new ImageListBean[size];
        }
    };
}
