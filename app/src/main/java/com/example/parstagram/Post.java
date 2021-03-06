package com.example.parstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_PROFILE_PIC = "profilePic";
    public static final String KEY_USERS_LIKED = "likedBy";

    public Post() {}

    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE,parseFile);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser (ParseUser user){
        put(KEY_USER,user);
    }

    public String likedCountDisplayText() {
        String likesText = String.valueOf(getLike().size());
        likesText += getLike().size() == 1 ? " like" : " likes";
        return likesText;
    }

    public List<String> getLike() {
        List<String> likedBy = getList(KEY_USERS_LIKED);
        if(likedBy == null) {
            likedBy = new ArrayList<>();
        }
        return likedBy;
    }

    public void setUserLike(List<String> likedBy) {
        put(KEY_USERS_LIKED, likedBy);
    }

}
