package okhttp3.internal.http2;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import okhttp3.internal.Util;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Http2 {
    public static final int FLAG_ACK = 1;
    public static final int FLAG_COMPRESSED = 32;
    public static final int FLAG_END_HEADERS = 4;
    public static final int FLAG_END_PUSH_PROMISE = 4;
    public static final int FLAG_END_STREAM = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_PADDED = 8;
    public static final int FLAG_PRIORITY = 32;
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final int TYPE_CONTINUATION = 9;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 1;
    public static final int TYPE_PING = 6;
    public static final int TYPE_PRIORITY = 2;
    public static final int TYPE_PUSH_PROMISE = 5;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_WINDOW_UPDATE = 8;
    @NotNull
    public static final String[] c;
    @NotNull
    public static final Http2 INSTANCE = new Http2();
    @JvmField
    @NotNull
    public static final ByteString CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f14281a = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    @NotNull
    public static final String[] b = new String[64];

    static {
        String[] strArr = new String[256];
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            String binaryString = Integer.toBinaryString(i2);
            Intrinsics.checkNotNullExpressionValue(binaryString, "toBinaryString(it)");
            strArr[i2] = m.replace$default(Util.format("%8s", binaryString), ' ', '0', false, 4, (Object) null);
        }
        c = strArr;
        String[] strArr2 = b;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i3 = 0;
        while (i3 < 1) {
            int i4 = iArr[i3];
            i3++;
            String[] strArr3 = b;
            strArr3[i4 | 8] = Intrinsics.stringPlus(strArr3[i4], "|PADDED");
        }
        String[] strArr4 = b;
        strArr4[4] = "END_HEADERS";
        strArr4[32] = "PRIORITY";
        strArr4[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        int i5 = 0;
        while (i5 < 3) {
            int i6 = iArr2[i5];
            i5++;
            int i7 = 0;
            while (i7 < 1) {
                int i8 = iArr[i7];
                i7++;
                String[] strArr5 = b;
                int i9 = i8 | i6;
                StringBuilder sb = new StringBuilder();
                sb.append((Object) strArr5[i8]);
                sb.append('|');
                sb.append((Object) strArr5[i6]);
                strArr5[i9] = sb.toString();
                strArr5[i9 | 8] = ((Object) strArr5[i8]) + '|' + ((Object) strArr5[i6]) + "|PADDED";
            }
        }
        int length = b.length;
        while (i < length) {
            int i10 = i + 1;
            String[] strArr6 = b;
            if (strArr6[i] == null) {
                strArr6[i] = c[i];
            }
            i = i10;
        }
    }

    @NotNull
    public final String formatFlags(int i, int i2) {
        String str;
        if (i2 == 0) {
            return "";
        }
        if (i != 2 && i != 3) {
            if (i == 4 || i == 6) {
                return i2 == 1 ? "ACK" : c[i2];
            } else if (i != 7 && i != 8) {
                String[] strArr = b;
                if (i2 < strArr.length) {
                    str = strArr[i2];
                    Intrinsics.checkNotNull(str);
                } else {
                    str = c[i2];
                }
                String str2 = str;
                if (i != 5 || (i2 & 4) == 0) {
                    return (i != 0 || (i2 & 32) == 0) ? str2 : m.replace$default(str2, "PRIORITY", "COMPRESSED", false, 4, (Object) null);
                }
                return m.replace$default(str2, "HEADERS", "PUSH_PROMISE", false, 4, (Object) null);
            }
        }
        return c[i2];
    }

    @NotNull
    public final String formattedType$okhttp(int i) {
        String[] strArr = f14281a;
        return i < strArr.length ? strArr[i] : Util.format("0x%02x", Integer.valueOf(i));
    }

    @NotNull
    public final String frameLog(boolean z, int i, int i2, int i3, int i4) {
        return Util.format("%s 0x%08x %5d %-13s %s", z ? "<<" : ">>", Integer.valueOf(i), Integer.valueOf(i2), formattedType$okhttp(i3), formatFlags(i3, i4));
    }
}
