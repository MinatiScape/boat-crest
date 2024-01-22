package okio;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class _Base64Kt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f14320a;
    @NotNull
    public static final byte[] b;

    static {
        ByteString.Companion companion = ByteString.Companion;
        f14320a = companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$okio();
        b = companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$okio();
    }

    @Nullable
    public static final byte[] decodeBase64ToArray(@NotNull String str) {
        int i;
        char charAt;
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        while (length > 0 && ((charAt = str.charAt(length - 1)) == '=' || charAt == '\n' || charAt == '\r' || charAt == ' ' || charAt == '\t')) {
            length--;
        }
        int i2 = (int) ((length * 6) / 8);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            boolean z = true;
            if (i3 < length) {
                char charAt2 = str.charAt(i3);
                if ('A' <= charAt2 && charAt2 < '[') {
                    i = charAt2 - 'A';
                } else {
                    if ('a' <= charAt2 && charAt2 < '{') {
                        i = charAt2 - 'G';
                    } else {
                        if (('0' > charAt2 || charAt2 >= ':') ? false : false) {
                            i = charAt2 + 4;
                        } else if (charAt2 == '+' || charAt2 == '-') {
                            i = 62;
                        } else if (charAt2 == '/' || charAt2 == '_') {
                            i = 63;
                        } else {
                            if (charAt2 != '\n' && charAt2 != '\r' && charAt2 != ' ' && charAt2 != '\t') {
                                return null;
                            }
                            i3++;
                        }
                    }
                }
                i5 = (i5 << 6) | i;
                i4++;
                if (i4 % 4 == 0) {
                    int i7 = i6 + 1;
                    bArr[i6] = (byte) (i5 >> 16);
                    int i8 = i7 + 1;
                    bArr[i7] = (byte) (i5 >> 8);
                    bArr[i8] = (byte) i5;
                    i6 = i8 + 1;
                }
                i3++;
            } else {
                int i9 = i4 % 4;
                if (i9 != 1) {
                    if (i9 == 2) {
                        bArr[i6] = (byte) ((i5 << 12) >> 16);
                        i6++;
                    } else if (i9 == 3) {
                        int i10 = i5 << 6;
                        int i11 = i6 + 1;
                        bArr[i6] = (byte) (i10 >> 16);
                        i6 = i11 + 1;
                        bArr[i11] = (byte) (i10 >> 8);
                    }
                    if (i6 == i2) {
                        return bArr;
                    }
                    byte[] copyOf = Arrays.copyOf(bArr, i6);
                    Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                    return copyOf;
                }
                return null;
            }
        }
    }

    @NotNull
    public static final String encodeBase64(@NotNull byte[] bArr, @NotNull byte[] map) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        byte[] bArr2 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            byte b2 = bArr[i];
            int i4 = i3 + 1;
            byte b3 = bArr[i3];
            int i5 = i4 + 1;
            byte b4 = bArr[i4];
            int i6 = i2 + 1;
            bArr2[i2] = map[(b2 & 255) >> 2];
            int i7 = i6 + 1;
            bArr2[i6] = map[((b2 & 3) << 4) | ((b3 & 255) >> 4)];
            int i8 = i7 + 1;
            bArr2[i7] = map[((b3 & 15) << 2) | ((b4 & 255) >> 6)];
            i2 = i8 + 1;
            bArr2[i8] = map[b4 & 63];
            i = i5;
        }
        int length2 = bArr.length - length;
        if (length2 == 1) {
            byte b5 = bArr[i];
            int i9 = i2 + 1;
            bArr2[i2] = map[(b5 & 255) >> 2];
            int i10 = i9 + 1;
            bArr2[i9] = map[(b5 & 3) << 4];
            byte b6 = (byte) 61;
            bArr2[i10] = b6;
            bArr2[i10 + 1] = b6;
        } else if (length2 == 2) {
            int i11 = i + 1;
            byte b7 = bArr[i];
            byte b8 = bArr[i11];
            int i12 = i2 + 1;
            bArr2[i2] = map[(b7 & 255) >> 2];
            int i13 = i12 + 1;
            bArr2[i12] = map[((b7 & 3) << 4) | ((b8 & 255) >> 4)];
            bArr2[i13] = map[(b8 & 15) << 2];
            bArr2[i13 + 1] = (byte) 61;
        }
        return _JvmPlatformKt.toUtf8String(bArr2);
    }

    public static /* synthetic */ String encodeBase64$default(byte[] bArr, byte[] bArr2, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr2 = f14320a;
        }
        return encodeBase64(bArr, bArr2);
    }

    @NotNull
    public static final byte[] getBASE64() {
        return f14320a;
    }

    public static /* synthetic */ void getBASE64$annotations() {
    }

    @NotNull
    public static final byte[] getBASE64_URL_SAFE() {
        return b;
    }

    public static /* synthetic */ void getBASE64_URL_SAFE$annotations() {
    }
}
