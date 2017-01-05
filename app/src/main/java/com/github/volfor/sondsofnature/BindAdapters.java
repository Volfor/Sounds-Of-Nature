package com.github.volfor.sondsofnature;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class BindAdapters {

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter("image")
    public static void bindImage(ImageView v, @DrawableRes int id) {
        Glide.with(v.getContext())
                .load(id)
                .into(v);
    }

    @BindingAdapter("font")
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), String.format("fonts/%s.ttf", fontName)));
    }

}
