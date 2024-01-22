package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.internal.model.ModelUtils;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class a extends ModelUtils.AutoMLManifest {

    /* renamed from: a  reason: collision with root package name */
    public final String f11570a;
    public final String b;
    public final String c;

    public a(String str, String str2, String str3) {
        Objects.requireNonNull(str, "Null modelType");
        this.f11570a = str;
        Objects.requireNonNull(str2, "Null modelFile");
        this.b = str2;
        Objects.requireNonNull(str3, "Null labelsFile");
        this.c = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.AutoMLManifest) {
            ModelUtils.AutoMLManifest autoMLManifest = (ModelUtils.AutoMLManifest) obj;
            if (this.f11570a.equals(autoMLManifest.getModelType()) && this.b.equals(autoMLManifest.getModelFile()) && this.c.equals(autoMLManifest.getLabelsFile())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    @KeepForSdk
    public String getLabelsFile() {
        return this.c;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    @KeepForSdk
    public String getModelFile() {
        return this.b;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    @KeepForSdk
    public String getModelType() {
        return this.f11570a;
    }

    public final int hashCode() {
        return ((((this.f11570a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        String str = this.f11570a;
        String str2 = this.b;
        String str3 = this.c;
        return "AutoMLManifest{modelType=" + str + ", modelFile=" + str2 + ", labelsFile=" + str3 + "}";
    }
}
