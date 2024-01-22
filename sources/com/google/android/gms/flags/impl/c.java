package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class c implements Callable<Long> {
    public final /* synthetic */ SharedPreferences h;
    public final /* synthetic */ String i;
    public final /* synthetic */ Long j;

    public c(SharedPreferences sharedPreferences, String str, Long l) {
        this.h = sharedPreferences;
        this.i = str;
        this.j = l;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Long call() throws Exception {
        return Long.valueOf(this.h.getLong(this.i, this.j.longValue()));
    }
}
