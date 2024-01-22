package com.mappls.sdk.navigation.camera;

import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmName(name = "NullUtils")
/* loaded from: classes11.dex */
public final class NullUtils {
    @Nullable
    public static final <R1, T> T ifNonNull(@Nullable R1 r1, @NotNull Function1<? super R1, ? extends T> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        if (r1 != null) {
            return func.invoke(r1);
        }
        return null;
    }

    @Nullable
    public static final <R1, R2, T> T ifNonNull(@Nullable R1 r1, @Nullable R2 r2, @NotNull Function2<? super R1, ? super R2, ? extends T> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        if (r1 == null || r2 == null) {
            return null;
        }
        return func.invoke(r1, r2);
    }

    @Nullable
    public static final <R1, R2, R3, T> T ifNonNull(@Nullable R1 r1, @Nullable R2 r2, @Nullable R3 r3, @NotNull Function3<? super R1, ? super R2, ? super R3, ? extends T> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        if (r1 == null || r2 == null || r3 == null) {
            return null;
        }
        return func.invoke(r1, r2, r3);
    }

    @Nullable
    public static final <R1, R2, R3, R4, T> T ifNonNull(@Nullable R1 r1, @Nullable R2 r2, @Nullable R3 r3, @Nullable R4 r4, @NotNull Function4<? super R1, ? super R2, ? super R3, ? super R4, ? extends T> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        if (r1 == null || r2 == null || r3 == null || r4 == null) {
            return null;
        }
        return func.invoke(r1, r2, r3, r4);
    }

    @Nullable
    public static final <R1, R2, R3, R4, R5, T> T ifNonNull(@Nullable R1 r1, @Nullable R2 r2, @Nullable R3 r3, @Nullable R4 r4, @Nullable R5 r5, @NotNull Function5<? super R1, ? super R2, ? super R3, ? super R4, ? super R5, ? extends T> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        if (r1 == null || r2 == null || r3 == null || r4 == null || r5 == null) {
            return null;
        }
        return func.invoke(r1, r2, r3, r4, r5);
    }

    @Nullable
    public static final <R1, R2, R3, R4, R5, R6, T> T ifNonNull(@Nullable R1 r1, @Nullable R2 r2, @Nullable R3 r3, @Nullable R4 r4, @Nullable R5 r5, @Nullable R6 r6, @NotNull Function6<? super R1, ? super R2, ? super R3, ? super R4, ? super R5, ? super R6, ? extends T> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        if (r1 == null || r2 == null || r3 == null || r4 == null || r5 == null || r6 == null) {
            return null;
        }
        return func.invoke(r1, r2, r3, r4, r5, r6);
    }

    @Nullable
    public static final <R1, R2, R3, R4, R5, R6, R7, T> T ifNonNull(@Nullable R1 r1, @Nullable R2 r2, @Nullable R3 r3, @Nullable R4 r4, @Nullable R5 r5, @Nullable R6 r6, @Nullable R7 r7, @NotNull Function7<? super R1, ? super R2, ? super R3, ? super R4, ? super R5, ? super R6, ? super R7, ? extends T> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        if (r1 == null || r2 == null || r3 == null || r4 == null || r5 == null || r6 == null || r7 == null) {
            return null;
        }
        return func.invoke(r1, r2, r3, r4, r5, r6, r7);
    }
}
