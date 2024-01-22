package com.google.mlkit.common.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzaa;
import com.google.android.gms.internal.mlkit_common.zzz;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.BaseModel;
import java.util.EnumMap;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class RemoteModel {
    public static final Map e = new EnumMap(BaseModel.class);
    @NonNull
    @VisibleForTesting
    public static final Map zza = new EnumMap(BaseModel.class);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f11579a;
    @Nullable
    public final BaseModel b;
    public final ModelType c;
    public String d;

    @KeepForSdk
    public RemoteModel(@Nullable String str, @Nullable BaseModel baseModel, @NonNull ModelType modelType) {
        Preconditions.checkArgument(TextUtils.isEmpty(str) == (baseModel != null), "One of cloud model name and base model cannot be empty");
        this.f11579a = str;
        this.b = baseModel;
        this.c = modelType;
    }

    @KeepForSdk
    public boolean baseModelHashMatches(@NonNull String str) {
        BaseModel baseModel = this.b;
        if (baseModel == null) {
            return false;
        }
        return str.equals(e.get(baseModel));
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RemoteModel) {
            RemoteModel remoteModel = (RemoteModel) obj;
            return Objects.equal(this.f11579a, remoteModel.f11579a) && Objects.equal(this.b, remoteModel.b) && Objects.equal(this.c, remoteModel.c);
        }
        return false;
    }

    @NonNull
    @KeepForSdk
    public String getModelHash() {
        return this.d;
    }

    @Nullable
    @KeepForSdk
    public String getModelName() {
        return this.f11579a;
    }

    @NonNull
    @KeepForSdk
    public String getModelNameForBackend() {
        String str = this.f11579a;
        return str != null ? str : (String) zza.get(this.b);
    }

    @NonNull
    @KeepForSdk
    public ModelType getModelType() {
        return this.c;
    }

    @NonNull
    @KeepForSdk
    public String getUniqueModelNameForPersist() {
        String str = this.f11579a;
        return str != null ? str : "COM.GOOGLE.BASE_".concat(String.valueOf((String) zza.get(this.b)));
    }

    public int hashCode() {
        return Objects.hashCode(this.f11579a, this.b, this.c);
    }

    @KeepForSdk
    public boolean isBaseModel() {
        return this.b != null;
    }

    @KeepForSdk
    public void setModelHash(@NonNull String str) {
        this.d = str;
    }

    @NonNull
    public String toString() {
        zzz zzb = zzaa.zzb("RemoteModel");
        zzb.zza("modelName", this.f11579a);
        zzb.zza("baseModel", this.b);
        zzb.zza("modelType", this.c);
        return zzb.toString();
    }
}
