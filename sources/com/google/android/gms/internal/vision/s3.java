package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes10.dex */
public final class s3<FieldDescriptorType> extends t3<FieldDescriptorType, Object> {
    public s3(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.vision.t3
    public final void m() {
        if (!a()) {
            for (int i = 0; i < n(); i++) {
                Map.Entry<FieldDescriptorType, Object> i2 = i(i);
                if (((zzgk) i2.getKey()).zzfu()) {
                    i2.setValue(Collections.unmodifiableList((List) i2.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : o()) {
                if (((zzgk) entry.getKey()).zzfu()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.m();
    }
}
