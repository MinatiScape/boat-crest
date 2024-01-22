package com.mappls.sdk.maps.utils;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.exceptions.CalledFromWorkerThreadException;
/* loaded from: classes11.dex */
public class ThreadUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f12857a;

    public static void checkThread(@NonNull String str) {
        Boolean bool = f12857a;
        if (bool != null) {
            if (bool.booleanValue() && Looper.myLooper() != Looper.getMainLooper()) {
                throw new CalledFromWorkerThreadException(String.format("%s interactions should happen on the UI thread.", str));
            }
            return;
        }
        throw new IllegalStateException("ThreadUtils isn't correctly initialised");
    }

    public static ThreadUtils init(@NonNull Context context) {
        f12857a = Boolean.valueOf((context.getApplicationInfo().flags & 2) != 0);
        return null;
    }
}
