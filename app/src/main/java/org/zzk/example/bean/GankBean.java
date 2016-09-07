package org.zzk.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zwl on 16/9/6.
 */
public class GankBean implements Parcelable {

    public String error;
    public List<Gank> results;


    public static class Gank implements Parcelable{
        public String _id;
        public String createAt;
        public String desc;
        public String publisheAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createAt);
            dest.writeString(this.desc);
            dest.writeString(this.publisheAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }

        public Gank() {
        }

        protected Gank(Parcel in) {
            this._id = in.readString();
            this.createAt = in.readString();
            this.desc = in.readString();
            this.publisheAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Creator<Gank> CREATOR = new Creator<Gank>() {
            @Override
            public Gank createFromParcel(Parcel source) {
                return new Gank(source);
            }

            @Override
            public Gank[] newArray(int size) {
                return new Gank[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
        dest.writeTypedList(this.results);
    }

    public GankBean() {
    }

    protected GankBean(Parcel in) {
        this.error = in.readString();
        this.results = in.createTypedArrayList(Gank.CREATOR);
    }

    public static final Parcelable.Creator<GankBean> CREATOR = new Parcelable.Creator<GankBean>() {
        @Override
        public GankBean createFromParcel(Parcel source) {
            return new GankBean(source);
        }

        @Override
        public GankBean[] newArray(int size) {
            return new GankBean[size];
        }
    };
}
