package com.coveiot.android.qrtray.utils;

import android.content.Intent;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class GetGalleryIntentKt {
    @NotNull
    public static final Intent getGalleryIntent() {
        Intent intent = new Intent();
        intent.setType(com.crrepa.r.a.d);
        intent.setAction("android.intent.action.GET_CONTENT");
        return intent;
    }
}
