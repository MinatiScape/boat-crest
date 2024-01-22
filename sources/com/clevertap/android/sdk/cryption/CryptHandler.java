package com.clevertap.android.sdk.cryption;

import com.clevertap.android.sdk.Constants;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CryptHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public EncryptionLevel f2600a;
    @NotNull
    public Crypt b;
    @NotNull
    public String c;
    public int d;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final boolean isTextEncrypted(@NotNull String plainText) {
            Intrinsics.checkNotNullParameter(plainText, "plainText");
            return StringsKt__StringsKt.startsWith$default((CharSequence) plainText, '[', false, 2, (Object) null) && StringsKt__StringsKt.endsWith$default((CharSequence) plainText, ']', false, 2, (Object) null);
        }
    }

    /* loaded from: classes2.dex */
    public enum EncryptionAlgorithm {
        AES
    }

    /* loaded from: classes2.dex */
    public enum EncryptionLevel {
        NONE(0),
        MEDIUM(1);
        
        private final int value;

        EncryptionLevel(int i) {
            this.value = i;
        }

        public final int intValue() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EncryptionLevel.values().length];
            try {
                iArr[EncryptionLevel.MEDIUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CryptHandler(int i, @NotNull EncryptionAlgorithm encryptionType, @NotNull String accountID) {
        Intrinsics.checkNotNullParameter(encryptionType, "encryptionType");
        Intrinsics.checkNotNullParameter(accountID, "accountID");
        this.f2600a = EncryptionLevel.values()[i];
        this.c = accountID;
        this.d = 0;
        this.b = CryptFactory.Companion.getCrypt(encryptionType);
    }

    @JvmStatic
    public static final boolean isTextEncrypted(@NotNull String str) {
        return Companion.isTextEncrypted(str);
    }

    @Nullable
    public final String decrypt(@NotNull String cipherText, @NotNull String key) {
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        Intrinsics.checkNotNullParameter(key, "key");
        if (Companion.isTextEncrypted(cipherText)) {
            if (WhenMappings.$EnumSwitchMapping$0[this.f2600a.ordinal()] == 1) {
                return Constants.MEDIUM_CRYPT_KEYS.contains(key) ? this.b.decryptInternal(cipherText, this.c) : cipherText;
            }
            return this.b.decryptInternal(cipherText, this.c);
        }
        return cipherText;
    }

    @Nullable
    public final String encrypt(@NotNull String plainText, @NotNull String key) {
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        Intrinsics.checkNotNullParameter(key, "key");
        return (WhenMappings.$EnumSwitchMapping$0[this.f2600a.ordinal()] == 1 && Constants.MEDIUM_CRYPT_KEYS.contains(key) && !Companion.isTextEncrypted(plainText)) ? this.b.encryptInternal(plainText, this.c) : plainText;
    }

    public final int getEncryptionFlagStatus() {
        return this.d;
    }

    public final void setEncryptionFlagStatus(int i) {
        this.d = i;
    }
}
