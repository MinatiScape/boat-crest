package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class d implements Callable<String> {
    public final /* synthetic */ SharedPreferences h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;

    public d(SharedPreferences sharedPreferences, String str, String str2) {
        this.h = sharedPreferences;
        this.i = str;
        this.j = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        return this.h.getString(this.i, this.j);
    }
}
