package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcz {
    @NotNull
    private List zza = CollectionsKt__CollectionsKt.emptyList();

    public final void sss(@NotNull int[] iArr) {
        zzb(iArr);
    }

    public final int zza(@NotNull int[] iArr) {
        Iterator it = CollectionsKt___CollectionsKt.plus((Collection) this.zza, (Iterable) ArraysKt___ArraysKt.toList(iArr)).iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = Integer.valueOf(((Number) next).intValue() ^ ((Number) it.next()).intValue());
            }
            return ((Number) next).intValue();
        }
        throw new UnsupportedOperationException("Empty collection can't be reduced.");
    }

    public final void zzb(@NotNull int[] iArr) {
        this.zza = ArraysKt___ArraysKt.toList(iArr);
    }
}
