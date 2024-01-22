package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import com.google.firebase.ml.common.internal.modeldownload.zzaa;
import com.google.firebase.ml.common.internal.modeldownload.zzn;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
import java.util.UUID;
/* loaded from: classes7.dex */
public class zzqu {
    public static final GmsLogger c = new GmsLogger("SharedPrefManager", "");
    public static final Component<?> zzbja = Component.builder(zzqu.class).add(Dependency.required(zzqf.class)).add(Dependency.required(Context.class)).factory(w4.f8748a).build();

    /* renamed from: a  reason: collision with root package name */
    public final Context f8798a;
    public final String b;

    public zzqu(@NonNull zzqf zzqfVar, @NonNull Context context) {
        this.f8798a = context;
        this.b = zzqfVar.getPersistenceKey();
    }

    public static zzn b(String str) {
        if (str == null) {
            return zzn.UNKNOWN;
        }
        try {
            return zzn.zzby(str);
        } catch (IllegalArgumentException unused) {
            c.e("SharedPrefManager", str.length() != 0 ? "Invalid model type ".concat(str) : new String("Invalid model type "));
            return zzn.UNKNOWN;
        }
    }

    public static final /* synthetic */ zzqu c(ComponentContainer componentContainer) {
        return new zzqu((zzqf) componentContainer.get(zzqf.class), (Context) componentContainer.get(Context.class));
    }

    public static zzqu zzb(@NonNull zzqf zzqfVar) {
        return (zzqu) zzqfVar.get(zzqu.class);
    }

    public final SharedPreferences a() {
        return this.f8798a.getSharedPreferences("com.google.firebase.ml.internal", 0);
    }

    @Nullable
    public final synchronized Long zza(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        long j = a().getLong(String.format("downloading_model_id_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), -1L);
        if (j < 0) {
            return null;
        }
        return Long.valueOf(j);
    }

    public final synchronized void zzar(boolean z) {
        a().edit().putBoolean(String.format("logging_%s_%s", "vision", this.b), z).apply();
    }

    public final synchronized void zzas(boolean z) {
        a().edit().putBoolean(String.format("logging_%s_%s", "model", this.b), z).apply();
    }

    public final synchronized zzn zzbw(@NonNull String str) {
        return b(a().getString(String.format("downloading_model_type_%s", str), ""));
    }

    @Nullable
    public final synchronized String zzc(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        return a().getString(String.format("current_model_hash_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), null);
    }

    public final synchronized zzn zzd(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        return b(a().getString(String.format("current_model_type_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), zzn.UNKNOWN.name()));
    }

    @Nullable
    public final synchronized String zze(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        return a().getString(String.format("bad_hash_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), null);
    }

    public final synchronized long zzf(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        return a().getLong(String.format("downloading_begin_time_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), 0L);
    }

    public final synchronized long zzg(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        return a().getLong(String.format("model_first_use_time_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), 0L);
    }

    public final synchronized void zzh(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        a().edit().remove(String.format("downloading_model_id_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist())).remove(String.format("downloading_model_hash_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist())).remove(String.format("downloading_model_type_%s", zzb(firebaseRemoteModel))).remove(String.format("downloading_begin_time_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist())).remove(String.format("model_first_use_time_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist())).apply();
    }

    @WorkerThread
    public final synchronized void zzi(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        a().edit().remove(String.format("current_model_hash_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist())).remove(String.format("current_model_type_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist())).commit();
    }

    public final synchronized boolean zzop() {
        return a().getBoolean(String.format("logging_%s_%s", "vision", this.b), true);
    }

    public final synchronized boolean zzoq() {
        return a().getBoolean(String.format("logging_%s_%s", "model", this.b), true);
    }

    @Nullable
    public final synchronized String zzor() {
        return a().getString("app_version", null);
    }

    public final synchronized String zzos() {
        String string = a().getString("ml_sdk_instance_id", null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        a().edit().putString("ml_sdk_instance_id", uuid).apply();
        return uuid;
    }

    @Nullable
    public final synchronized String zzb(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        return a().getString(String.format("downloading_model_hash_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), null);
    }

    public final synchronized void zza(@NonNull FirebaseRemoteModel firebaseRemoteModel, long j) {
        a().edit().putLong(String.format("model_first_use_time_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), j).apply();
    }

    public final synchronized void zza(@NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull String str, @NonNull zzn zznVar) {
        a().edit().putString(String.format("current_model_hash_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), str).putString(String.format("current_model_type_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), zznVar.name()).apply();
    }

    public final synchronized void zza(long j, @NonNull zzaa zzaaVar) {
        String zzpl = zzaaVar.zzpl();
        String modelHash = zzaaVar.getModelHash();
        a().edit().putString(String.format("downloading_model_hash_%s_%s", this.b, zzpl), modelHash).putLong(String.format("downloading_model_id_%s_%s", this.b, zzpl), j).putString(String.format("downloading_model_type_%s", modelHash), zzaaVar.zzpm().name()).putLong(String.format("downloading_begin_time_%s_%s", this.b, zzpl), SystemClock.elapsedRealtime()).apply();
    }

    public final synchronized void zza(@NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull String str, @NonNull String str2) {
        a().edit().putString(String.format("bad_hash_%s_%s", this.b, firebaseRemoteModel.getUniqueModelNameForPersist()), str).putString("app_version", str2).apply();
    }
}
