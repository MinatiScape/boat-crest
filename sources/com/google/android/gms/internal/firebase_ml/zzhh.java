package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
/* loaded from: classes7.dex */
public abstract class zzhh {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f8770a = Logger.getLogger(zzhh.class.getName());
    public static final String[] b;

    static {
        String[] strArr = {"DELETE", "GET", "POST", "PUT"};
        b = strArr;
        Arrays.sort(strArr);
    }

    public final zzhb zza(zzhe zzheVar) {
        return new zzhb(this, zzheVar);
    }

    public boolean zzaj(String str) throws IOException {
        return Arrays.binarySearch(b, str) >= 0;
    }

    public abstract zzhk zzc(String str, String str2) throws IOException;
}
