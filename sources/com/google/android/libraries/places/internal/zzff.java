package com.google.android.libraries.places.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzff extends zzfg {
    private final char zza = '.';

    public zzff(char c) {
    }

    public final String toString() {
        String zzd;
        zzd = zzfd.zzd(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(zzd).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(zzd);
        sb.append("')");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzfd
    public final boolean zzb(char c) {
        return c == this.zza;
    }
}
