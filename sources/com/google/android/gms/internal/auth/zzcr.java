package com.google.android.gms.internal.auth;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
/* loaded from: classes6.dex */
public final class zzcr {
    @GuardedBy("PhenotypeConstants.class")

    /* renamed from: a  reason: collision with root package name */
    public static final ArrayMap f8557a = new ArrayMap();

    public static synchronized Uri zza(String str) {
        synchronized (zzcr.class) {
            ArrayMap arrayMap = f8557a;
            Uri uri = (Uri) arrayMap.get("com.google.android.gms.auth_account");
            if (uri == null) {
                Uri parse = Uri.parse("content://com.google.android.gms.phenotype/".concat(String.valueOf(Uri.encode("com.google.android.gms.auth_account"))));
                arrayMap.put("com.google.android.gms.auth_account", parse);
                return parse;
            }
            return uri;
        }
    }
}
