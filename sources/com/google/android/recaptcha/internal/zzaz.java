package com.google.android.recaptcha.internal;

import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzaz {
    @NotNull
    public static final zzaz zza = new zzaz();
    @Nullable
    private static Set zzb;
    @Nullable
    private static Set zzc;
    @Nullable
    private static Long zzd;
    private static int zze;

    private zzaz() {
    }

    public static final void zza(@NotNull zzmi zzmiVar) {
        zzb = CollectionsKt___CollectionsKt.toSet(zzmiVar.zzf().zzi());
        zzc = CollectionsKt___CollectionsKt.toSet(zzmiVar.zzg().zzi());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0149  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object zzb(@org.jetbrains.annotations.NotNull java.lang.String r15, @org.jetbrains.annotations.NotNull java.lang.String r16, @org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull android.content.Context r19, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzq r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r21) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaz.zzb(java.lang.String, java.lang.String, java.lang.String, java.lang.String, android.content.Context, com.google.android.recaptcha.internal.zzq, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final boolean zzc(@NotNull String str) {
        Set set = zzb;
        if (set != null && zzc != null) {
            Intrinsics.checkNotNull(set, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
            if (set.isEmpty()) {
                return true;
            }
            Set set2 = zzc;
            Intrinsics.checkNotNull(set2, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
            if (zzd(str, set2)) {
                return false;
            }
            return zzd(str, set);
        }
        if (zzd == null) {
            zzd = Long.valueOf(System.currentTimeMillis());
        }
        zze++;
        return true;
    }

    private static final boolean zzd(String str, Set set) {
        String str2 = "";
        for (String str3 : StringsKt__StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null)) {
            String concat = str2.concat(String.valueOf(str3));
            if (set.contains(concat)) {
                return true;
            }
            str2 = concat.concat(".");
        }
        return false;
    }
}
