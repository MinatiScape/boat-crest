package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class g6 extends zzqa {

    /* renamed from: a  reason: collision with root package name */
    public zzlm f9204a;
    public String b;
    public boolean c;
    public boolean d;
    public ModelType e;
    public zzls f;
    public int g;
    public byte h;

    public final zzqa a(String str) {
        this.b = "NA";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqa
    public final zzqa zza(zzls zzlsVar) {
        Objects.requireNonNull(zzlsVar, "Null downloadStatus");
        this.f = zzlsVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqa
    public final zzqa zzb(zzlm zzlmVar) {
        Objects.requireNonNull(zzlmVar, "Null errorCode");
        this.f9204a = zzlmVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqa
    public final zzqa zzc(int i) {
        this.g = i;
        this.h = (byte) (this.h | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqa
    public final zzqa zzd(ModelType modelType) {
        Objects.requireNonNull(modelType, "Null modelType");
        this.e = modelType;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqa
    public final zzqa zze(boolean z) {
        this.d = z;
        this.h = (byte) (this.h | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqa
    public final zzqa zzf(boolean z) {
        this.c = z;
        this.h = (byte) (this.h | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzqa
    public final zzqb zzh() {
        zzlm zzlmVar;
        String str;
        ModelType modelType;
        zzls zzlsVar;
        if (this.h == 7 && (zzlmVar = this.f9204a) != null && (str = this.b) != null && (modelType = this.e) != null && (zzlsVar = this.f) != null) {
            return new h6(zzlmVar, str, this.c, this.d, modelType, zzlsVar, this.g, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f9204a == null) {
            sb.append(" errorCode");
        }
        if (this.b == null) {
            sb.append(" tfliteSchemaVersion");
        }
        if ((this.h & 1) == 0) {
            sb.append(" shouldLogRoughDownloadTime");
        }
        if ((this.h & 2) == 0) {
            sb.append(" shouldLogExactDownloadTime");
        }
        if (this.e == null) {
            sb.append(" modelType");
        }
        if (this.f == null) {
            sb.append(" downloadStatus");
        }
        if ((this.h & 4) == 0) {
            sb.append(" failureStatusCode");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
