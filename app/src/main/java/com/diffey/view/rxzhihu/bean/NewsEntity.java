package com.diffey.view.rxzhihu.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diff on 2016/2/3.
 */
public class NewsEntity implements Parcelable {

    private String date;

    private ArrayList<StoriesEntity> stories;

    private List<TopStoriesEntity> top_stories;


    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(ArrayList<StoriesEntity> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<StoriesEntity> getStories() {
        return stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeList(this.stories);
        dest.writeList(this.top_stories);
    }

    public NewsEntity() {
    }

    protected NewsEntity(Parcel in) {
        this.date = in.readString();
        this.stories = new ArrayList<StoriesEntity>();
        in.readList(this.stories, List.class.getClassLoader());
        this.top_stories = new ArrayList<TopStoriesEntity>();
        in.readList(this.top_stories, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewsEntity> CREATOR = new Parcelable.Creator<NewsEntity>() {
        public NewsEntity createFromParcel(Parcel source) {
            return new NewsEntity(source);
        }

        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    @Override
    public String toString() {
        return "NewsEntity{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
