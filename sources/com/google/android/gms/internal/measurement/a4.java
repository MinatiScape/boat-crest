package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class a4 extends i4 {
    public a4(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.measurement.i4
    public final void a() {
        if (!k()) {
            for (int i = 0; i < b(); i++) {
                Map.Entry h = h(i);
                if (((zzjp) h.getKey()).zzc()) {
                    h.setValue(Collections.unmodifiableList((List) h.getValue()));
                }
            }
            for (Map.Entry entry : d()) {
                if (((zzjp) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.a();
    }
}
