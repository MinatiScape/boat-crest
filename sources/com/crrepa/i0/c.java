package com.crrepa.i0;

import android.util.Log;
/* loaded from: classes9.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f7728a = false;

    public static int a(Object obj) {
        if (!f7728a || obj == null) {
            return -1;
        }
        return Log.d("MoYoung", obj.toString());
    }

    public static int b(Object obj) {
        if (!f7728a || obj == null) {
            return -1;
        }
        return Log.e("MoYoung", obj.toString());
    }

    public static int c(Object obj) {
        if (!f7728a || obj == null) {
            return -1;
        }
        return Log.i("MoYoung", obj.toString());
    }
}
