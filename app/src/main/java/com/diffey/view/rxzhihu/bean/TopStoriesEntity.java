package com.diffey.view.rxzhihu.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TopStoriesEntity implements Parcelable {
    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getImage() {
        return image;
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

    @Override
    public String toString() {
        return "TopStoriesEntity{" +
                "ga_prefix='" + ga_prefix + '\'' +
                ", image='" + image + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeInt(this.type);
        dest.writeInt(this.id);
        dest.writeString(this.ga_prefix);
        dest.writeString(this.title);
    }

    public TopStoriesEntity() {
    }

    protected TopStoriesEntity(Parcel in) {
        this.image = in.readString();
        this.type = in.readInt();
        this.id = in.readInt();
        this.ga_prefix = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<TopStoriesEntity> CREATOR = new Parcelable.Creator<TopStoriesEntity>() {
        public TopStoriesEntity createFromParcel(Parcel source) {
            return new TopStoriesEntity(source);
        }

        public TopStoriesEntity[] newArray(int size) {
            return new TopStoriesEntity[size];
        }
    };
}