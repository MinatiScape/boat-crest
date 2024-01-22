package com.polidea.rxandroidble2.internal.connection;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class e implements PayloadSizeLimitProvider {

    /* renamed from: a  reason: collision with root package name */
    public final int f13431a;

    public e(int i) {
        this.f13431a = i;
    }

    @Override // com.polidea.rxandroidble2.internal.connection.PayloadSizeLimitProvider
    public int getPayloadSizeLimit() {
        return this.f13431a;
    }
}
