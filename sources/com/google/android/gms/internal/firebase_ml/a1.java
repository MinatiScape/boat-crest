package com.google.android.gms.internal.firebase_ml;

import java.util.Comparator;
/* loaded from: classes7.dex */
public final class a1 implements Comparator<String> {
    public a1(zziv zzivVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (zzmf.equal(str3, str4)) {
            return 0;
        }
        if (str3 == null) {
            return -1;
        }
        if (str4 == null) {
            return 1;
        }
        return str3.compareTo(str4);
    }
}
