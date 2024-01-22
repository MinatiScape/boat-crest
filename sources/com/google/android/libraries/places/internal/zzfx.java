package com.google.android.libraries.places.internal;
/* loaded from: classes10.dex */
abstract class zzfx extends zzfc<String> {
    public final CharSequence zza;
    private final zzfd zzb;
    private final boolean zzc;
    private int zzd = 0;
    private int zze;

    public zzfx(zzfs zzfsVar, CharSequence charSequence) {
        zzfd zzfdVar;
        int i;
        zzfdVar = zzfsVar.zza;
        this.zzb = zzfdVar;
        this.zzc = false;
        i = zzfsVar.zzd;
        this.zze = i;
        this.zza = charSequence;
    }

    public abstract int zza(int i);

    @Override // com.google.android.libraries.places.internal.zzfc
    public final /* synthetic */ String zza() {
        int i = this.zzd;
        while (true) {
            int i2 = this.zzd;
            if (i2 != -1) {
                int zza = zza(i2);
                if (zza == -1) {
                    zza = this.zza.length();
                    this.zzd = -1;
                } else {
                    this.zzd = zzb(zza);
                }
                int i3 = this.zzd;
                if (i3 == i) {
                    int i4 = i3 + 1;
                    this.zzd = i4;
                    if (i4 > this.zza.length()) {
                        this.zzd = -1;
                    }
                } else {
                    while (i < zza && this.zzb.zzb(this.zza.charAt(i))) {
                        i++;
                    }
                    while (zza > i && this.zzb.zzb(this.zza.charAt(zza - 1))) {
                        zza--;
                    }
                    int i5 = this.zze;
                    if (i5 == 1) {
                        zza = this.zza.length();
                        this.zzd = -1;
                        while (zza > i && this.zzb.zzb(this.zza.charAt(zza - 1))) {
                            zza--;
                        }
                    } else {
                        this.zze = i5 - 1;
                    }
                    return this.zza.subSequence(i, zza).toString();
                }
            } else {
                zzb();
                return null;
            }
        }
    }

    public abstract int zzb(int i);
}
