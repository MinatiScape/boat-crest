package com.clevertap.android.sdk.cryption;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.keys.AesKey;
/* loaded from: classes2.dex */
public final class AESCrypt extends Crypt {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f2599a;
    @NotNull
    public static final String b;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        String str = "L" + RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME + "3fz";
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder()\n        …\").append(\"z\").toString()");
        f2599a = str;
        String str2 = "bL" + RsaJsonWebKey.FACTOR_CRT_COEFFICIENT + "i2";
        Intrinsics.checkNotNullExpressionValue(str2, "StringBuilder()\n        …\"i\").append(2).toString()");
        b = str2;
    }

    public final String a(String str) {
        return f2599a + str + b;
    }

    public final byte[] b(int i, String str, byte[] bArr) {
        try {
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            byte[] bytes = Constants.CRYPTION_SALT.getBytes(UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            Charset UTF_82 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_82, "UTF_8");
            byte[] bytes2 = Constants.CRYPTION_IV.getBytes(UTF_82);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            char[] charArray = str.toCharArray();
            Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBEWithMD5And128BitAES-CBC-OpenSSL").generateSecret(new PBEKeySpec(charArray, bytes, 1000, 256)).getEncoded(), AesKey.ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(i, secretKeySpec, new IvParameterSpec(bytes2));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            Logger.v("Unable to perform crypt operation", e);
            return null;
        }
    }

    @Override // com.clevertap.android.sdk.cryption.Crypt
    @Nullable
    public String decryptInternal(@NotNull String cipherText, @NotNull String accountID) {
        byte[] b2;
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        Intrinsics.checkNotNullParameter(accountID, "accountID");
        byte[] parseCipherText = parseCipherText(cipherText);
        if (parseCipherText == null || (b2 = b(2, a(accountID), parseCipherText)) == null) {
            return null;
        }
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        return new String(b2, UTF_8);
    }

    @Override // com.clevertap.android.sdk.cryption.Crypt
    @Nullable
    public String encryptInternal(@NotNull String plainText, @NotNull String accountID) {
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        Intrinsics.checkNotNullParameter(accountID, "accountID");
        String a2 = a(accountID);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = plainText.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] b2 = b(1, a2, bytes);
        if (b2 != null) {
            String arrays = Arrays.toString(b2);
            Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
            return arrays;
        }
        return null;
    }

    @Override // com.clevertap.android.sdk.cryption.Crypt
    @Nullable
    public byte[] parseCipherText(@NotNull String cipherText) {
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        try {
            String substring = cipherText.substring(1, cipherText.length() - 1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            List<String> split = new Regex("\\s*,\\s*").split(StringsKt__StringsKt.trim(substring).toString(), 0);
            byte[] bArr = new byte[split.size()];
            int size = split.size();
            for (int i = 0; i < size; i++) {
                bArr[i] = Byte.parseByte(split.get(i));
            }
            return bArr;
        } catch (Exception e) {
            Logger.v("Unable to parse cipher text", e);
            return null;
        }
    }
}
