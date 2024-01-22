package com.google.mlkit.common.sdkinternal;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes10.dex */
public class ModelInfo {

    /* renamed from: a  reason: collision with root package name */
    public final String f11590a;
    public final Uri b;
    public final String c;
    public final ModelType d;

    @KeepForSdk
    public ModelInfo(@NonNull String str, @NonNull Uri uri, @NonNull String str2, @NonNull ModelType modelType) {
        this.f11590a = str;
        this.b = uri;
        this.c = str2;
        this.d = modelType;
    }

    @NonNull
    @KeepForSdk
    public String getModelHash() {
        return this.c;
    }

    @NonNull
    @KeepForSdk
    public String getModelNameForPersist() {
        return this.f11590a;
    }

    @NonNull
    @KeepForSdk
    public ModelType getModelType() {
        return this.d;
    }

    @NonNull
    @KeepForSdk
    public Uri getModelUri() {
        return this.b;
    }
}
