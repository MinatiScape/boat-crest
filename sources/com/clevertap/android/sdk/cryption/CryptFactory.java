package com.clevertap.android.sdk.cryption;

import com.clevertap.android.sdk.cryption.CryptHandler;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CryptFactory {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion {

        /* loaded from: classes2.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[CryptHandler.EncryptionAlgorithm.values().length];
                try {
                    iArr[CryptHandler.EncryptionAlgorithm.AES.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Crypt getCrypt(@NotNull CryptHandler.EncryptionAlgorithm type) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (WhenMappings.$EnumSwitchMapping$0[type.ordinal()] == 1) {
                return new AESCrypt();
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    @JvmStatic
    @NotNull
    public static final Crypt getCrypt(@NotNull CryptHandler.EncryptionAlgorithm encryptionAlgorithm) {
        return Companion.getCrypt(encryptionAlgorithm);
    }
}
