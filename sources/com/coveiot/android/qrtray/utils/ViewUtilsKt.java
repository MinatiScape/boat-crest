package com.coveiot.android.qrtray.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.coveiot.android.qrtray.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ViewUtilsKt {
    public static final void disable(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<this>");
        button.setAlpha(0.5f);
        button.setClickable(false);
        button.setEnabled(false);
    }

    public static final void drawableEnd(@NotNull TextView textView, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    public static final void drawableStart(@NotNull TextView textView, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
    }

    public static final void emptyDrawable(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public static final void enable(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<this>");
        button.setAlpha(1.0f);
        button.setClickable(true);
        button.setEnabled(true);
    }

    public static final void gone(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public static final void goneIF(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 8 : 0);
    }

    public static final void setBackgroundExt(@NotNull View view, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setBackgroundResource(i);
    }

    @BindingAdapter({"itemImage"})
    public static final void setImage(@NotNull ImageView imageView, @Nullable String str) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        RequestBuilder<Drawable> m30load = Glide.with(imageView).m30load(str);
        RequestOptions requestOptions = new RequestOptions();
        int i = R.drawable.rounded_grey_background_16dp;
        m30load.apply((BaseRequestOptions<?>) requestOptions.placeholder(i).error(i).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
    }

    public static final void toast(@NotNull Context context, @NotNull CharSequence message) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(context, message, 1).show();
    }

    public static final void visible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }

    public static final void visibleIf(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 0 : 8);
    }
}
