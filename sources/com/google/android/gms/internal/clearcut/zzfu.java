package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzfu;
import java.io.IOException;
/* loaded from: classes7.dex */
public class zzfu<M extends zzfu<M>> extends zzfz {
    public zzfw zzrj;

    @Override // com.google.android.gms.internal.clearcut.zzfz
    public void zza(zzfs zzfsVar) throws IOException {
        if (this.zzrj == null) {
            return;
        }
        for (int i = 0; i < this.zzrj.a(); i++) {
            this.zzrj.b(i).b(zzfsVar);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfz
    public int zzen() {
        if (this.zzrj != null) {
            for (int i = 0; i < this.zzrj.a(); i++) {
                this.zzrj.b(i).c();
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: zzeo */
    public M clone() throws CloneNotSupportedException {
        M m = (M) super.clone();
        zzfy.zza(this, m);
        return m;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfz
    public /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzfu) clone();
    }
}
