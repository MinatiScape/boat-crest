package com.google.android.libraries.places.api.net;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FetchPhotoResponse {
    @NonNull
    public static FetchPhotoResponse newInstance(@NonNull Bitmap bitmap) {
        return new zzc(bitmap);
    }

    @NonNull
    public abstract Bitmap getBitmap();
}
