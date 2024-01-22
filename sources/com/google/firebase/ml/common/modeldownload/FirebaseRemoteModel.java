package com.google.firebase.ml.common.modeldownload;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.EnumMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class FirebaseRemoteModel {
    public static final Map<BaseModel, String> d;
    @VisibleForTesting
    public static final Map<BaseModel, String> e;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f11407a;
    @Nullable
    public final BaseModel b;
    public String c;

    static {
        EnumMap enumMap = new EnumMap(BaseModel.class);
        d = enumMap;
        EnumMap enumMap2 = new EnumMap(BaseModel.class);
        e = enumMap2;
        BaseModel baseModel = BaseModel.FACE_DETECTION;
        enumMap2.put((EnumMap) baseModel, (BaseModel) "face_detector_model_m41");
        BaseModel baseModel2 = BaseModel.SMART_REPLY;
        enumMap2.put((EnumMap) baseModel2, (BaseModel) "smart_reply_model_m41");
        BaseModel baseModel3 = BaseModel.TRANSLATE;
        enumMap2.put((EnumMap) baseModel3, (BaseModel) "translate_model_m41");
        enumMap.put((EnumMap) baseModel, (BaseModel) "modelHash");
        enumMap.put((EnumMap) baseModel2, (BaseModel) "smart_reply_model_hash");
        enumMap.put((EnumMap) baseModel3, (BaseModel) "modelHash");
    }

    @KeepForSdk
    public FirebaseRemoteModel(@Nullable String str, @Nullable BaseModel baseModel) {
        Preconditions.checkArgument(TextUtils.isEmpty(str) == (baseModel != null), "One of cloud model name and base model cannot be empty");
        this.f11407a = str;
        this.b = baseModel;
    }

    @KeepForSdk
    public boolean baseModelHashMatches(@NonNull String str) {
        BaseModel baseModel = this.b;
        if (baseModel == null) {
            return false;
        }
        return str.equals(d.get(baseModel));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseRemoteModel) {
            FirebaseRemoteModel firebaseRemoteModel = (FirebaseRemoteModel) obj;
            return Objects.equal(this.f11407a, firebaseRemoteModel.f11407a) && Objects.equal(this.b, firebaseRemoteModel.b);
        }
        return false;
    }

    @KeepForSdk
    public String getModelHash() {
        return this.c;
    }

    @Nullable
    @KeepForSdk
    public String getModelName() {
        return this.f11407a;
    }

    @KeepForSdk
    public String getModelNameForBackend() {
        String str = this.f11407a;
        return str != null ? str : e.get(this.b);
    }

    @KeepForSdk
    public String getUniqueModelNameForPersist() {
        String str = this.f11407a;
        if (str != null) {
            return str;
        }
        String valueOf = String.valueOf(e.get(this.b));
        return valueOf.length() != 0 ? "COM.GOOGLE.BASE_".concat(valueOf) : new String("COM.GOOGLE.BASE_");
    }

    public int hashCode() {
        return Objects.hashCode(this.f11407a, this.b);
    }

    @KeepForSdk
    public boolean isBaseModel() {
        return this.b != null;
    }

    @KeepForSdk
    public void setModelHash(@NonNull String str) {
        this.c = str;
    }
}
