package com.example.anton.kudago;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by anton_000 on 21.06.2015.
 */
public class AsyncNewsLoader implements NewsLoader {

    Drawable mImage = null;
    String   mTitle = null;
    String   mContent = null;

    AsyncNewsLoader(String title, String content, Drawable image) {
        this.mImage   = mImage;
        this.mTitle   = title;
        this.mContent = content;
    }

    @Override
    public Drawable getNewsIcon() {
        return mImage;
    }

    @Override
    public String getNewsTitle() {
        return mTitle;
    }

    @Override
    public String getNewsContent() {
        return mContent;
    }

    @Override
    public List<CommentsLoader> getComments() {
        return null;
    }
}
