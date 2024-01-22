package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class b implements Callable<Integer> {
    public final /* synthetic */ SharedPreferences h;
    public final /* synthetic */ String i;
    public final /* synthetic */ Integer j;

    public b(SharedPreferences sharedPreferences, String str, Integer num) {
        this.h = sharedPreferences;
        this.i = str;
        this.j = num;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Integer call() throws Exception {
        return Integer.valueOf(this.h.getInt(this.i, this.j.intValue()));
    }
}
