package com.example.devblogandroidapp.adapter;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import android.graphics.Bitmap;
import com.makeramen.roundedimageview.RoundedImageView;

public class BindingAdapters {
    @BindingAdapter("android:src")
    public static void setImageSrc(RoundedImageView view, ObservableField<Bitmap> bitmapField) {
        Bitmap bitmap = bitmapField != null ? bitmapField.get() : null;
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
        }
    }
}