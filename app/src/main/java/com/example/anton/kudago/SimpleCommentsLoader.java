package com.example.anton.kudago;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by anton_000 on 21.06.2015.
 */
public class SimpleCommentsLoader implements CommentsLoader {

    String   mAuthor  = null;
    String   mComment = null;
    Drawable mImage   = null;
    Date     mDate    = null;

    public SimpleCommentsLoader(String author, String comment, Date date, Drawable image){
        mAuthor  = author;
        mComment = comment;
        mImage   = image;
        mDate    = date;
    }

    @Override
    public String getAuthor() {
        return mAuthor;
    }

    @Override
    public String getContent() {
        return mComment;
    }

    @Override
    public Drawable getAvatar() {
        return mImage;
    }

    @Override
    public Date getDate() {
        return mDate;
    }
}
