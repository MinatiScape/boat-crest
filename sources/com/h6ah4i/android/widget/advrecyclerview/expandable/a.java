package com.h6ah4i.android.widget.advrecyclerview.expandable;
/* loaded from: classes11.dex */
public class a {
    public static int a(long j) {
        return (int) (j >>> 32);
    }

    public static long b(int i, int i2) {
        return (i & 4294967295L) | (i2 << 32);
    }

    public static long c(int i) {
        return (i & 4294967295L) | (-4294967296L);
    }

    public static int d(long j) {
        return (int) (j & 4294967295L);
    }
}
