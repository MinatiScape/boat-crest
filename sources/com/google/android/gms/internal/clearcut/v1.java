package com.google.android.gms.internal.clearcut;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes7.dex */
public final class v1<FieldDescriptorType> extends u1<FieldDescriptorType, Object> {
    public v1(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.clearcut.u1
    public final void r() {
        if (!a()) {
            for (int i = 0; i < m(); i++) {
                Map.Entry<FieldDescriptorType, Object> h = h(i);
                if (((zzca) h.getKey()).zzaw()) {
                    h.setValue(Collections.unmodifiableList((List) h.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : n()) {
                if (((zzca) entry.getKey()).zzaw()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.r();
    }
}
