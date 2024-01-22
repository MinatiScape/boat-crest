package com.yalantis.ucrop.callback;

import android.net.Uri;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface BitmapCropCallback {
    void onBitmapCropped(@NonNull Uri uri, int i, int i2, int i3, int i4);

    void onCropFailure(@NonNull Throwable th);
}
