package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzbd extends zzbc {
    @NotNull
    private final Function2 zza;
    @NotNull
    private final String zzb;

    public zzbd(@NotNull Function2 function2, @NotNull String str, @Nullable Object obj) {
        super(obj);
        this.zza = function2;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzbc
    public final boolean zza(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr) {
        Collection emptyList;
        if (Intrinsics.areEqual(method.getName(), this.zzb)) {
            zznd zzf = zzng.zzf();
            if (objArr != null) {
                emptyList = new ArrayList(objArr.length);
                for (Object obj2 : objArr) {
                    zzne zzf2 = zznf.zzf();
                    zzf2.zzv(obj2.toString());
                    emptyList.add((zznf) zzf2.zzj());
                }
            } else {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            zzf.zzd(emptyList);
            Function2 function2 = this.zza;
            byte[] zzd = ((zzng) zzf.zzj()).zzd();
            function2.invoke(objArr, zzek.zzh().zzi(zzd, 0, zzd.length));
            return true;
        }
        return false;
    }
}
