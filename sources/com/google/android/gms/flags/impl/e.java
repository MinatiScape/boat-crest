package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class e implements Callable<SharedPreferences> {
    public final /* synthetic */ Context h;

    public e(Context context) {
        this.h = context;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ SharedPreferences call() throws Exception {
        return this.h.getSharedPreferences("google_sdk_flags", 0);
    }
}
