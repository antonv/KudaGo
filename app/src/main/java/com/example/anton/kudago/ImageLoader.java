package com.example.anton.kudago;

import android.graphics.drawable.Drawable;

/**
 * Created by anton_000 on 21.06.2015.
 */
public interface ImageLoader {

    /**
     * Created by anton_000 on 21.06.2015.
     */
    int getImagesCount();

    /**
     * Created by anton_000 on 21.06.2015.
     */
    Drawable getImage(int idx);
}
