package com.diffey.view.rxzhihu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by diff on 2016/2/15.
 */
public class TREntity implements Parcelable {

    private int code;
    private String text;

    public void setCode(int code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "TREntity{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.text);
    }

    public TREntity() {
    }

    protected TREntity(Parcel in) {
        this.code = in.readInt();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<TREntity> CREATOR = new Parcelable.Creator<TREntity>() {
        public TREntity createFromParcel(Parcel source) {
            return new TREntity(source);
        }

        public TREntity[] newArray(int size) {
            return new TREntity[size];
        }
    };
}
