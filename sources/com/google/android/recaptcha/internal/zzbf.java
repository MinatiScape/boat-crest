package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzbf extends zzbc {
    @NotNull
    private final zzbe zza;
    @NotNull
    private final String zzb;

    public zzbf(@NotNull zzbe zzbeVar, @NotNull String str, @Nullable Object obj) {
        super(obj);
        this.zza = zzbeVar;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzbc
    public final boolean zza(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr) {
        List emptyList;
        if (Intrinsics.areEqual(method.getName(), this.zzb)) {
            zzbe zzbeVar = this.zza;
            if (objArr == null || (emptyList = ArraysKt___ArraysJvmKt.asList(objArr)) == null) {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            zzbeVar.zzb(emptyList);
            return true;
        }
        return false;
    }
}
