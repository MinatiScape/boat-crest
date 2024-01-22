package com.mappls.sdk.navigation.location;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public final class b {
    public static d a(@NonNull Context context) {
        return Build.VERSION.SDK_INT > 23 ? new c(context) : new f(context);
    }
}
