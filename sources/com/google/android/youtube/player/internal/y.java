package com.google.android.youtube.player.internal;

import android.util.Log;
/* loaded from: classes10.dex */
public final class y {
    public static void a(String str, Throwable th) {
        Log.e("YouTubeAndroidPlayerAPI", str, th);
    }

    public static void a(String str, Object... objArr) {
        Log.w("YouTubeAndroidPlayerAPI", String.format(str, objArr));
    }
}
