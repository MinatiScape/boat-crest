package com.google.android.gms.internal.fido;

import java.util.Arrays;
/* loaded from: classes7.dex */
public final class zzbj extends zzbk {
    public final String h;

    public zzbj(String str) {
        this.h = str;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzbk zzbkVar = (zzbk) obj;
        zzbkVar.zza();
        String str = this.h;
        int length = str.length();
        String str2 = ((zzbj) zzbkVar).h;
        return length != str2.length() ? str.length() - str2.length() : str.compareTo(str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && zzbj.class == obj.getClass()) {
            return this.h.equals(((zzbj) obj).h);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{3, this.h});
    }

    public final String toString() {
        String str = this.h;
        return "\"" + str + "\"";
    }

    @Override // com.google.android.gms.internal.fido.zzbk
    public final int zza() {
        return 3;
    }
}
