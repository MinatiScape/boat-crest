package com.google.android.recaptcha.internal;

import com.clevertap.android.sdk.Constants;
import java.util.Collection;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbs implements zzca {
    @NotNull
    public static final zzbs zza = new zzbs();

    private zzbs() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        String joinToString$default;
        String str;
        if (zznlVarArr.length == 1) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                if (zza2 instanceof int[]) {
                    joinToString$default = ArraysKt___ArraysKt.joinToString$default((int[]) zza2, (CharSequence) Constants.SEPARATOR_COMMA, (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                } else {
                    if (zza2 instanceof byte[]) {
                        str = new String((byte[]) zza2, Charsets.UTF_8);
                    } else if (zza2 instanceof long[]) {
                        joinToString$default = ArraysKt___ArraysKt.joinToString$default((long[]) zza2, (CharSequence) Constants.SEPARATOR_COMMA, (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof short[]) {
                        joinToString$default = ArraysKt___ArraysKt.joinToString$default((short[]) zza2, (CharSequence) Constants.SEPARATOR_COMMA, (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof float[]) {
                        joinToString$default = ArraysKt___ArraysKt.joinToString$default((float[]) zza2, (CharSequence) Constants.SEPARATOR_COMMA, (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof double[]) {
                        joinToString$default = ArraysKt___ArraysKt.joinToString$default((double[]) zza2, Constants.SEPARATOR_COMMA, "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (zza2 instanceof char[]) {
                        str = new String((char[]) zza2);
                    } else if (zza2 instanceof Object[]) {
                        joinToString$default = ArraysKt___ArraysKt.joinToString$default((Object[]) zza2, Constants.SEPARATOR_COMMA, "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    } else if (!(zza2 instanceof Collection)) {
                        throw new zzs(4, 5, null);
                    } else {
                        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default((Iterable) zza2, Constants.SEPARATOR_COMMA, "[", "]", 0, null, null, 56, null);
                    }
                    joinToString$default = str;
                }
                zzbhVar.zze().zzf(i, joinToString$default);
                return;
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
