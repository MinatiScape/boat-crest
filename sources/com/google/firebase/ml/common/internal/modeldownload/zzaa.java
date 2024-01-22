package com.google.firebase.ml.common.internal.modeldownload;

import android.net.Uri;
import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public final class zzaa {

    /* renamed from: a  reason: collision with root package name */
    public final String f11387a;
    public final Uri b;
    public final String c;
    public final zzn d;

    public zzaa(@NonNull String str, @NonNull Uri uri, @NonNull String str2, @NonNull zzn zznVar) {
        this.f11387a = str;
        this.b = uri;
        this.c = str2;
        this.d = zznVar;
    }

    public final String getModelHash() {
        return this.c;
    }

    public final String zzpl() {
        return this.f11387a;
    }

    public final zzn zzpm() {
        return this.d;
    }
}
