package com.example.anton.kudago;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by anton_000 on 21.06.2015.
 */
public interface NewsLoader {

    Drawable getNewsIcon();

    String getNewsTitle();

    String getNewsContent();

    List<CommentsLoader> getComments();
}
