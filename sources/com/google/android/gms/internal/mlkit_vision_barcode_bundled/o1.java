package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Map;
/* loaded from: classes8.dex */
public final class o1 extends v1 {
    public o1(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.v1
    public final void a() {
        if (!k()) {
            for (int i = 0; i < b(); i++) {
                ((zzds) h(i).getKey()).zzg();
            }
            for (Map.Entry entry : d()) {
                ((zzds) entry.getKey()).zzg();
            }
        }
        super.a();
    }
}
