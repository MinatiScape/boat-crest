package com.clevertap.android.sdk;

import android.content.Context;
import androidx.annotation.RestrictTo;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public final class CTStringResources {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2568a;
    @NotNull
    public String[] b;

    public CTStringResources(@NotNull Context context, @NotNull int... sRID) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sRID, "sRID");
        this.f2568a = context;
        int length = sRID.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            String string = this.f2568a.getString(sRID[i]);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(sRID[it])");
            strArr[i] = string;
        }
        this.b = strArr;
    }

    @Nullable
    public final String component1() {
        return (String) ArraysKt___ArraysKt.getOrNull(this.b, 0);
    }

    @Nullable
    public final String component2() {
        return (String) ArraysKt___ArraysKt.getOrNull(this.b, 1);
    }

    @Nullable
    public final String component3() {
        return (String) ArraysKt___ArraysKt.getOrNull(this.b, 2);
    }

    @Nullable
    public final String component4() {
        return (String) ArraysKt___ArraysKt.getOrNull(this.b, 3);
    }

    @Nullable
    public final String component5() {
        return (String) ArraysKt___ArraysKt.getOrNull(this.b, 4);
    }
}
