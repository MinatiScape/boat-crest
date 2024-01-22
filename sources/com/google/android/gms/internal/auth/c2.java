package com.google.android.gms.internal.auth;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public final class c2 extends j2 {
    public c2(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.auth.j2
    public final void a() {
        if (!k()) {
            for (int i = 0; i < b(); i++) {
                Map.Entry h = h(i);
                if (((zzeo) h.getKey()).zzc()) {
                    h.setValue(Collections.unmodifiableList((List) h.getValue()));
                }
            }
            for (Map.Entry entry : d()) {
                if (((zzeo) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.a();
    }
}
