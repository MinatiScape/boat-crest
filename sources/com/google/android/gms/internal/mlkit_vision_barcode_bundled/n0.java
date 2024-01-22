package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class n0 extends o0 {
    public /* synthetic */ n0(zzez zzezVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.o0
    public final void a(Object obj, long j) {
        ((zzel) g2.k(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.o0
    public final void b(Object obj, Object obj2, long j) {
        zzel zzelVar = (zzel) g2.k(obj, j);
        zzel zzelVar2 = (zzel) g2.k(obj2, j);
        int size = zzelVar.size();
        int size2 = zzelVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzelVar.zzc()) {
                zzelVar = zzelVar.zzd(size2 + size);
            }
            zzelVar.addAll(zzelVar2);
        }
        if (size > 0) {
            zzelVar2 = zzelVar;
        }
        g2.x(obj, j, zzelVar2);
    }
}
