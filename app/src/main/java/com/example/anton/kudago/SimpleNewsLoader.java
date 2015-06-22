package com.example.anton.kudago;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by anton_000 on 21.06.2015.
 */
public class SimpleNewsLoader implements NewsLoader {

    Drawable mImage = null;
    String   mTitle = null;
    String   mContent = null;

    SimpleNewsLoader(String title, String content, Drawable image) {
        this.mImage   = image;
        this.mTitle   = title;
        this.mContent = content;
    }

    @Override
    public Drawable getIcon() {
        return mImage;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getContent() {
        return mContent;
    }
}
