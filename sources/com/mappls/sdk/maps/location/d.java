package com.mappls.sdk.maps.location;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.utils.BitmapUtils;
/* loaded from: classes11.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public final Context f12752a;

    public d(Context context) {
        this.f12752a = context;
    }

    public Bitmap a(@DrawableRes int i, @ColorInt Integer num) {
        return BitmapUtils.getBitmapFromDrawable(BitmapUtils.getDrawableFromRes(this.f12752a, i, num));
    }

    public Bitmap b(@NonNull LocationComponentOptions locationComponentOptions) {
        return Utils.c(BitmapUtils.getDrawableFromRes(this.f12752a, R.drawable.mappls_maps_user_icon_shadow), locationComponentOptions.elevation());
    }
}
