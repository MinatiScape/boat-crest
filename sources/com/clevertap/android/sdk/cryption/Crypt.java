package com.clevertap.android.sdk.cryption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public abstract class Crypt {
    @Nullable
    public abstract String decryptInternal(@NotNull String str, @NotNull String str2);

    @Nullable
    public abstract String encryptInternal(@NotNull String str, @NotNull String str2);

    @Nullable
    public abstract byte[] parseCipherText(@NotNull String str);
}
