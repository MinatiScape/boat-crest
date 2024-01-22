package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.flags.zze;
/* loaded from: classes6.dex */
public final class zzj {

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f8471a;

    public static SharedPreferences zza(Context context) throws Exception {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (f8471a == null) {
                f8471a = (SharedPreferences) zze.zza(new e(context));
            }
            sharedPreferences = f8471a;
        }
        return sharedPreferences;
    }
}
