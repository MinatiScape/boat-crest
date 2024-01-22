package com.touchgui.sdk.internal;

import android.os.Build;
import java.util.Calendar;
import java.util.Date;
/* loaded from: classes12.dex */
public abstract class h2 {
    public static Date a(int i, int i2, int i3) {
        return Build.VERSION.SDK_INT >= 26 ? new Calendar.Builder().setDate(i, i2 - 1, i3).build().getTime() : new Date(i - 1900, i2 - 1, i3);
    }

    public static Date a(int i, int i2, int i3, int i4, int i5, int i6) {
        return Build.VERSION.SDK_INT >= 26 ? new Calendar.Builder().setDate(i, i2 - 1, i3).setTimeOfDay(i4, i5, i6).build().getTime() : new Date(i - 1900, i2 - 1, i3, i4, i5, i6);
    }
}
