package com.google.zxing.datamatrix.encoder;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.zxing.Dimension;
import java.util.Arrays;
/* loaded from: classes11.dex */
public final class HighLevelEncoder {
    public static int a(float[] fArr, int[] iArr, int i, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        for (int i2 = 0; i2 < 6; i2++) {
            iArr[i2] = (int) Math.ceil(fArr[i2]);
            int i3 = iArr[i2];
            if (i > i3) {
                Arrays.fill(bArr, (byte) 0);
                i = i3;
            }
            if (i == i3) {
                bArr[i2] = (byte) (bArr[i2] + 1);
            }
        }
        return i;
    }

    public static int b(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            i += bArr[i2];
        }
        return i;
    }

    public static void c(char c) {
        String hexString;
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hexString.length()) + Integer.toHexString(c)) + HexStringBuilder.COMMENT_END_CHAR);
    }

    public static boolean d(char c) {
        return c >= '0' && c <= '9';
    }

    public static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (d(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    public static boolean e(char c) {
        return c >= 128 && c <= 255;
    }

    public static String encodeHighLevel(String str) {
        return encodeHighLevel(str, SymbolShapeHint.FORCE_NONE, null, null);
    }

    public static boolean f(char c) {
        if (c != ' ') {
            if (c < '0' || c > '9') {
                return c >= 'A' && c <= 'Z';
            }
            return true;
        }
        return true;
    }

    public static boolean g(char c) {
        return c >= ' ' && c <= '^';
    }

    public static boolean h(char c) {
        if (c != ' ') {
            if (c < '0' || c > '9') {
                return c >= 'a' && c <= 'z';
            }
            return true;
        }
        return true;
    }

    public static boolean i(char c) {
        if (k(c) || c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    public static boolean j(char c) {
        return false;
    }

    public static boolean k(char c) {
        return c == '\r' || c == '*' || c == '>';
    }

    public static int l(CharSequence charSequence, int i, int i2) {
        float[] fArr;
        char c;
        if (i >= charSequence.length()) {
            return i2;
        }
        if (i2 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr[i2] = 0.0f;
        }
        int i3 = 0;
        while (true) {
            int i4 = i + i3;
            if (i4 == charSequence.length()) {
                byte[] bArr = new byte[6];
                int[] iArr = new int[6];
                int a2 = a(fArr, iArr, Integer.MAX_VALUE, bArr);
                int b = b(bArr);
                if (iArr[0] == a2) {
                    return 0;
                }
                if (b != 1 || bArr[5] <= 0) {
                    if (b != 1 || bArr[4] <= 0) {
                        if (b != 1 || bArr[2] <= 0) {
                            return (b != 1 || bArr[3] <= 0) ? 1 : 3;
                        }
                        return 2;
                    }
                    return 4;
                }
                return 5;
            }
            char charAt = charSequence.charAt(i4);
            i3++;
            if (d(charAt)) {
                fArr[0] = fArr[0] + 0.5f;
            } else if (e(charAt)) {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 1.0f;
            }
            if (f(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (e(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (h(charAt)) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (e(charAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + 1.3333334f;
            }
            if (i(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (e(charAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (g(charAt)) {
                fArr[4] = fArr[4] + 0.75f;
            } else if (e(charAt)) {
                fArr[4] = fArr[4] + 4.25f;
            } else {
                fArr[4] = fArr[4] + 3.25f;
            }
            if (j(charAt)) {
                c = 5;
                fArr[5] = fArr[5] + 4.0f;
            } else {
                c = 5;
                fArr[5] = fArr[5] + 1.0f;
            }
            if (i3 >= 4) {
                int[] iArr2 = new int[6];
                byte[] bArr2 = new byte[6];
                a(fArr, iArr2, Integer.MAX_VALUE, bArr2);
                int b2 = b(bArr2);
                if (iArr2[0] < iArr2[c] && iArr2[0] < iArr2[1] && iArr2[0] < iArr2[2] && iArr2[0] < iArr2[3] && iArr2[0] < iArr2[4]) {
                    return 0;
                }
                if (iArr2[5] < iArr2[0] || bArr2[1] + bArr2[2] + bArr2[3] + bArr2[4] == 0) {
                    return 5;
                }
                if (b2 == 1 && bArr2[4] > 0) {
                    return 4;
                }
                if (b2 == 1 && bArr2[2] > 0) {
                    return 2;
                }
                if (b2 == 1 && bArr2[3] > 0) {
                    return 3;
                }
                if (iArr2[1] + 1 < iArr2[0] && iArr2[1] + 1 < iArr2[5] && iArr2[1] + 1 < iArr2[4] && iArr2[1] + 1 < iArr2[2]) {
                    if (iArr2[1] < iArr2[3]) {
                        return 1;
                    }
                    if (iArr2[1] == iArr2[3]) {
                        for (int i5 = i + i3 + 1; i5 < charSequence.length(); i5++) {
                            char charAt2 = charSequence.charAt(i5);
                            if (k(charAt2)) {
                                return 3;
                            }
                            if (!i(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
    }

    public static char m(int i) {
        int i2 = ((i * 149) % 253) + 1 + 129;
        if (i2 > 254) {
            i2 -= 254;
        }
        return (char) i2;
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2) {
        int i = 0;
        f[] fVarArr = {new a(), new c(), new h(), new i(), new e(), new b()};
        g gVar = new g(str);
        gVar.n(symbolShapeHint);
        gVar.l(dimension, dimension2);
        if (str.startsWith("[)>\u001e05\u001d") && str.endsWith("\u001e\u0004")) {
            gVar.r((char) 236);
            gVar.m(2);
            gVar.f += 7;
        } else if (str.startsWith("[)>\u001e06\u001d") && str.endsWith("\u001e\u0004")) {
            gVar.r((char) 237);
            gVar.m(2);
            gVar.f += 7;
        }
        while (gVar.i()) {
            fVarArr[i].a(gVar);
            if (gVar.e() >= 0) {
                i = gVar.e();
                gVar.j();
            }
        }
        int a2 = gVar.a();
        gVar.p();
        int dataCapacity = gVar.g().getDataCapacity();
        if (a2 < dataCapacity && i != 0 && i != 5 && i != 4) {
            gVar.r((char) 254);
        }
        StringBuilder b = gVar.b();
        if (b.length() < dataCapacity) {
            b.append((char) 129);
        }
        while (b.length() < dataCapacity) {
            b.append(m(b.length() + 1));
        }
        return gVar.b().toString();
    }
}
