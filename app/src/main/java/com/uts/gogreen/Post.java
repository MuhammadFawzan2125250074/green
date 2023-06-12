package com.uts.gogreen;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private String id;
    private String namapohon;
    private String alamat;
    private String user_id;
    private String create_date;
    private String modified_date;
    private String username;


    protected Post(Parcel in) {
        id = in.readString();
        namapohon = in.readString();
        alamat = in.readString();
        user_id = in.readString();
        create_date = in.readString();
        modified_date = in.readString();
        username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(namapohon);
        dest.writeString(alamat);
        dest.writeString(user_id);
        dest.writeString(create_date);
        dest.writeString(modified_date);
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamapohon() {
        return namapohon;
    }

    public void setNamapohon(String namapohon) {
        this.namapohon = namapohon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
