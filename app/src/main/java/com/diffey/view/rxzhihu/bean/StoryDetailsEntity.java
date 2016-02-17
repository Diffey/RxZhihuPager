package com.diffey.view.rxzhihu.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by diff on 2016/2/4.
 */
public class StoryDetailsEntity implements Parcelable {

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> css;

    public void setBody(String body) {
        this.body = body;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public String getBody() {
        return body;
    }

    public String getImage_source() {
        return image_source;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<String> getCss() {
        return css;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
        dest.writeString(this.image_source);
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.share_url);
        dest.writeString(this.ga_prefix);
        dest.writeInt(this.type);
        dest.writeInt(this.id);
        dest.writeStringList(this.css);
    }

    public StoryDetailsEntity() {
    }

    protected StoryDetailsEntity(Parcel in) {
        this.body = in.readString();
        this.image_source = in.readString();
        this.title = in.readString();
        this.image = in.readString();
        this.share_url = in.readString();
        this.ga_prefix = in.readString();
        this.type = in.readInt();
        this.id = in.readInt();
        this.css = in.createStringArrayList();
    }

    public static final Parcelable.Creator<StoryDetailsEntity> CREATOR = new Parcelable.Creator<StoryDetailsEntity>() {
        public StoryDetailsEntity createFromParcel(Parcel source) {
            return new StoryDetailsEntity(source);
        }

        public StoryDetailsEntity[] newArray(int size) {
            return new StoryDetailsEntity[size];
        }
    };
}
