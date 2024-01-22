package com.google.android.gms.common.internal;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes6.dex */
public final class ResourceUtils {
    static {
        new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
    }
}
