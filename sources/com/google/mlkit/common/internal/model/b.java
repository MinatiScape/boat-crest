package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.internal.model.ModelUtils;
/* loaded from: classes10.dex */
public final class b extends ModelUtils.ModelLoggingInfo {

    /* renamed from: a  reason: collision with root package name */
    public final long f11571a;
    public final String b;
    public final boolean c;

    public b(long j, String str, boolean z) {
        this.f11571a = j;
        this.b = str;
        this.c = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.ModelLoggingInfo) {
            ModelUtils.ModelLoggingInfo modelLoggingInfo = (ModelUtils.ModelLoggingInfo) obj;
            if (this.f11571a == modelLoggingInfo.getSize() && this.b.equals(modelLoggingInfo.getHash()) && this.c == modelLoggingInfo.isManifestModel()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo
    @KeepForSdk
    public String getHash() {
        return this.b;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo
    @KeepForSdk
    public long getSize() {
        return this.f11571a;
    }

    public final int hashCode() {
        long j = this.f11571a;
        return ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ (true != this.c ? 1237 : 1231);
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo
    @KeepForSdk
    public boolean isManifestModel() {
        return this.c;
    }

    public final String toString() {
        long j = this.f11571a;
        String str = this.b;
        boolean z = this.c;
        return "ModelLoggingInfo{size=" + j + ", hash=" + str + ", manifestModel=" + z + "}";
    }
}
