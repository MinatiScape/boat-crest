package com.google.android.gms.internal.firebase_ml;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes7.dex */
public final class g8<FieldDescriptorType> extends h8<FieldDescriptorType, Object> {
    public g8(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.firebase_ml.h8
    public final void m() {
        if (!a()) {
            for (int i = 0; i < n(); i++) {
                Map.Entry<FieldDescriptorType, Object> k = k(i);
                if (((zzwt) k.getKey()).zzuk()) {
                    k.setValue(Collections.unmodifiableList((List) k.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : o()) {
                if (((zzwt) entry.getKey()).zzuk()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.m();
    }
}
