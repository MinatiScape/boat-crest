package com.touchgui.sdk.internal;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes12.dex */
public final class j6 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public static j6 f13783a;

    public j6() {
        super(Looper.getMainLooper());
    }

    public static Handler a() {
        if (f13783a == null) {
            f13783a = new j6();
        }
        return f13783a;
    }
}
