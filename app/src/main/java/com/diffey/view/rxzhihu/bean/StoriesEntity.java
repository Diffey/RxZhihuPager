package com.diffey.view.rxzhihu.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StoriesEntity implements Parcelable {
    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;
    private boolean isRead = false;

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getImages() {
        return images;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "StoriesEntity{" +
                "ga_prefix='" + ga_prefix + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", images=" + images +
                ", isRead=" + isRead +
                '}';
    }

    public StoriesEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeInt(this.id);
        dest.writeString(this.ga_prefix);
        dest.writeString(this.title);
        dest.writeStringList(this.images);
        dest.writeByte(isRead ? (byte) 1 : (byte) 0);
    }

    protected StoriesEntity(Parcel in) {
        this.type = in.readInt();
        this.id = in.readInt();
        this.ga_prefix = in.readString();
        this.title = in.readString();
        this.images = in.createStringArrayList();
        this.isRead = in.readByte() != 0;
    }

    public static final Creator<StoriesEntity> CREATOR = new Creator<StoriesEntity>() {
        public StoriesEntity createFromParcel(Parcel source) {
            return new StoriesEntity(source);
        }

        public StoriesEntity[] newArray(int size) {
            return new StoriesEntity[size];
        }
    };
}
