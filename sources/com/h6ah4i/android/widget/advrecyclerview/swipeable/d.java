package com.h6ah4i.android.widget.advrecyclerview.swipeable;
/* loaded from: classes11.dex */
public class d {
    public static boolean a(int i) {
        return e(i) == 2;
    }

    public static boolean b(int i) {
        return f(i) == 2;
    }

    public static boolean c(int i) {
        return g(i) == 2;
    }

    public static boolean d(int i) {
        return h(i) == 2;
    }

    public static int e(int i) {
        return (i >>> 18) & 3;
    }

    public static int f(int i) {
        return (i >>> 0) & 3;
    }

    public static int g(int i) {
        return (i >>> 12) & 3;
    }

    public static int h(int i) {
        return (i >>> 6) & 3;
    }
}
