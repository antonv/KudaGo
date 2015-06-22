package com.example.anton.kudago;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by anton_000 on 21.06.2015.
 */
public interface CommentsLoader {
    
    String getAuthor();

    String getContent();

    Drawable getAvatar();

    Date getDate();
}
