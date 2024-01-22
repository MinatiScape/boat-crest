package com.google.android.material.transition.platform;

import androidx.annotation.RequiresApi;
@RequiresApi(21)
/* loaded from: classes10.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public final int f10432a;
    public final int b;
    public final boolean c;

    public c(int i, int i2, boolean z) {
        this.f10432a = i;
        this.b = i2;
        this.c = z;
    }

    public static c a(int i, int i2) {
        return new c(i, i2, true);
    }

    public static c b(int i, int i2) {
        return new c(i, i2, false);
    }
}
