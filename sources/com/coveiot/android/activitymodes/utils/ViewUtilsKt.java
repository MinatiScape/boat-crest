package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
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
import com.coveiot.android.activitymodes.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewUtilsKt {
    public static final void drawableEnd(@NotNull TextView textView, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    public static final void gone(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    @BindingAdapter({"itemImage"})
    public static final void setImage(@NotNull ImageView imageView, @Nullable String str) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Log.d("ivPlanImg", "setImage: " + str);
        RequestBuilder<Drawable> m30load = Glide.with(imageView).m30load(str);
        RequestOptions requestOptions = new RequestOptions();
        int i = R.drawable.rounded_grey_background_16dp;
        m30load.apply((BaseRequestOptions<?>) requestOptions.placeholder(i).error(i).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
    }

    public static final void toast(@NotNull Context context, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(context, message, 0).show();
    }

    public static final void visible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }
}
