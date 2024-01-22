package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class a implements Callable<Boolean> {
    public final /* synthetic */ SharedPreferences h;
    public final /* synthetic */ String i;
    public final /* synthetic */ Boolean j;

    public a(SharedPreferences sharedPreferences, String str, Boolean bool) {
        this.h = sharedPreferences;
        this.i = str;
        this.j = bool;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Boolean call() throws Exception {
        return Boolean.valueOf(this.h.getBoolean(this.i, this.j.booleanValue()));
    }
}
