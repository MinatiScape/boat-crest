package com.yalantis.ucrop.callback;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.yalantis.ucrop.model.ExifInfo;
/* loaded from: classes12.dex */
public interface BitmapLoadCallback {
    void onBitmapLoaded(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo, @NonNull String str, @Nullable String str2);

    void onFailure(@NonNull Exception exc);
}
