package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public abstract class Utf8 {

    /* renamed from: a  reason: collision with root package name */
    public static Utf8 f1277a;

    /* loaded from: classes.dex */
    public static class a {
        public static void a(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws IllegalArgumentException {
            if (!f(b2) && (((b << 28) + (b2 + com.htsmart.wristband2.a.a.a.J1)) >> 30) == 0 && !f(b3) && !f(b4)) {
                int k = ((b & 7) << 18) | (k(b2) << 12) | (k(b3) << 6) | k(b4);
                cArr[i] = e(k);
                cArr[i + 1] = j(k);
                return;
            }
            throw new IllegalArgumentException("Invalid UTF-8");
        }

        public static void b(byte b, char[] cArr, int i) {
            cArr[i] = (char) b;
        }

        public static void c(byte b, byte b2, byte b3, char[] cArr, int i) throws IllegalArgumentException {
            if (!f(b2) && ((b != -32 || b2 >= -96) && ((b != -19 || b2 < -96) && !f(b3)))) {
                cArr[i] = (char) (((b & 15) << 12) | (k(b2) << 6) | k(b3));
                return;
            }
            throw new IllegalArgumentException("Invalid UTF-8");
        }

        public static void d(byte b, byte b2, char[] cArr, int i) throws IllegalArgumentException {
            if (b >= -62) {
                if (!f(b2)) {
                    cArr[i] = (char) (((b & 31) << 6) | k(b2));
                    return;
                }
                throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
            throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
        }

        public static char e(int i) {
            return (char) ((i >>> 10) + okio.Utf8.HIGH_SURROGATE_HEADER);
        }

        public static boolean f(byte b) {
            return b > -65;
        }

        public static boolean g(byte b) {
            return b >= 0;
        }

        public static boolean h(byte b) {
            return b < -16;
        }

        public static boolean i(byte b) {
            return b < -32;
        }

        public static char j(int i) {
            return (char) ((i & 1023) + okio.Utf8.LOG_SURROGATE_HEADER);
        }

        public static int k(byte b) {
            return b & 63;
        }
    }

    public static Utf8 getDefault() {
        if (f1277a == null) {
            f1277a = new Utf8Safe();
        }
        return f1277a;
    }

    public static void setDefault(Utf8 utf8) {
        f1277a = utf8;
    }

    public abstract String decodeUtf8(ByteBuffer byteBuffer, int i, int i2);

    public abstract void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer);

    public abstract int encodedLength(CharSequence charSequence);
}
