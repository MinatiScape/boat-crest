package com.google.android.gms.internal.fitness;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes8.dex */
public final class b4<FieldDescriptorType> extends y3<FieldDescriptorType, Object> {
    public b4(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.fitness.y3
    public final void j() {
        if (!a()) {
            for (int i = 0; i < n(); i++) {
                Map.Entry<FieldDescriptorType, Object> h = h(i);
                if (((zzgv) h.getKey()).zzbn()) {
                    h.setValue(Collections.unmodifiableList((List) h.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : o()) {
                if (((zzgv) entry.getKey()).zzbn()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.j();
    }
}
