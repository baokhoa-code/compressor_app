package com.example.compress_video_app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NormalVideo implements Parcelable {
    public static final Creator<NormalVideo> CREATOR = new Creator<NormalVideo>() {
        @Override
        public NormalVideo createFromParcel(Parcel in) {
            return new NormalVideo(in);
        }

        @Override
        public NormalVideo[] newArray(int size) {
            return new NormalVideo[size];
        }
    };
    private String id;
    private String title;
    private String displayName;
    private String size;
    private String duration;
    private String path;
    private String dateAdded;
    private String bitrate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public NormalVideo(String id, String title, String displayName, String size, String duration, String path, String dateAdded, String bitrate) {
        this.id = id;
        this.title = title;
        this.displayName = displayName;
        this.size = size;
        this.duration = duration;
        this.path = path;
        this.dateAdded = dateAdded;
        this.bitrate = bitrate;
    }

    protected NormalVideo(Parcel in) {
        id = in.readString();
        title = in.readString();
        displayName = in.readString();
        size = in.readString();
        duration = in.readString();
        path = in.readString();
        dateAdded = in.readString();
        bitrate = in.readString();
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(displayName);
        dest.writeString(size);
        dest.writeString(duration);
        dest.writeString(path);
        dest.writeString(dateAdded);
        dest.writeString(bitrate);
    }

    @Override
    public String toString() {
        return "NormalVideo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", displayName='" + displayName + '\'' +
                ", size='" + size + '\'' +
                ", duration='" + duration + '\'' +
                ", path='" + path + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", bitrate='" + bitrate + '\'' +
                '}';
    }
}