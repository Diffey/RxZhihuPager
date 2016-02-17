package com.diffey.view.rxzhihu.db.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by diff on 2016/2/16.
 */
public class NewBean implements Parcelable {
    private int id;

    public NewBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NewBean{" +
                "id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    protected NewBean(Parcel in) {
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<NewBean> CREATOR = new Parcelable.Creator<NewBean>() {
        public NewBean createFromParcel(Parcel source) {
            return new NewBean(source);
        }

        public NewBean[] newArray(int size) {
            return new NewBean[size];
        }
    };
}
