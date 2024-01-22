package com.goodix.ble.libcomx.util;
/* loaded from: classes6.dex */
public class HexStringParser {
    /* JADX WARN: Removed duplicated region for block: B:58:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0098 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int parse(java.lang.CharSequence r12, byte[] r13, int r14, int r15) {
        /*
            r0 = 0
            if (r12 == 0) goto La8
            if (r13 == 0) goto La8
            if (r14 < 0) goto La8
            r1 = 1
            if (r15 >= r1) goto Lc
            goto La8
        Lc:
            int r2 = r12.length()
            int r15 = r15 + r14
            int r3 = r13.length
            if (r15 <= r3) goto L15
            int r15 = r13.length
        L15:
            r3 = r0
            r4 = r3
            r5 = r4
            r6 = r5
        L19:
            if (r3 >= r2) goto L9b
            if (r14 < r15) goto L1f
            goto L9b
        L1f:
            char r7 = r12.charAt(r3)
            r8 = 40
            if (r7 != r8) goto L44
            int r9 = r3 + 1
            if (r9 >= r2) goto L44
            r7 = r1
            r3 = r9
        L2d:
            if (r3 >= r2) goto L98
            char r9 = r12.charAt(r3)
            if (r9 != r8) goto L38
            int r7 = r7 + 1
            goto L41
        L38:
            r10 = 41
            if (r9 != r10) goto L41
            int r7 = r7 + (-1)
            if (r7 > 0) goto L41
            goto L98
        L41:
            int r3 = r3 + 1
            goto L2d
        L44:
            r8 = 48
            if (r7 != r8) goto L5a
            int r9 = r3 + 1
            if (r9 >= r2) goto L5a
            char r10 = r12.charAt(r9)
            r11 = 120(0x78, float:1.68E-43)
            if (r10 == r11) goto L58
            r11 = 88
            if (r10 != r11) goto L5a
        L58:
            r3 = r9
            goto L98
        L5a:
            if (r7 < r8) goto L68
            r8 = 57
            if (r7 > r8) goto L68
            int r5 = r5 << 4
            int r4 = r4 + 4
            int r7 = r7 + (-48)
        L66:
            r5 = r5 | r7
            goto L88
        L68:
            r8 = 65
            if (r7 < r8) goto L79
            r8 = 70
            if (r7 > r8) goto L79
            int r5 = r5 << 4
            int r4 = r4 + 4
            int r7 = r7 + (-65)
        L76:
            int r7 = r7 + 10
            goto L66
        L79:
            r8 = 97
            if (r7 < r8) goto L88
            r8 = 102(0x66, float:1.43E-43)
            if (r7 > r8) goto L88
            int r5 = r5 << 4
            int r4 = r4 + 4
            int r7 = r7 + (-97)
            goto L76
        L88:
            r7 = 8
            if (r4 < r7) goto L98
            int r4 = r14 + 1
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r13[r14] = r5
            int r6 = r6 + 1
            r5 = r0
            r14 = r4
            r4 = r5
        L98:
            int r3 = r3 + r1
            goto L19
        L9b:
            r12 = 4
            if (r4 != r12) goto La7
            if (r14 >= r15) goto La7
            r12 = r5 & 15
            byte r12 = (byte) r12
            r13[r14] = r12
            int r6 = r6 + 1
        La7:
            return r6
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.goodix.ble.libcomx.util.HexStringParser.parse(java.lang.CharSequence, byte[], int, int):int");
    }

    public static int parseInt(CharSequence charSequence, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        char charAt;
        int i7;
        int i8 = 0;
        if (charSequence != null && i >= 0 && i2 >= 1) {
            int length = charSequence.length();
            int i9 = i2 + i;
            if (i > length) {
                i = length;
            }
            if (i9 <= length) {
                length = i9;
            }
            while (i < length) {
                char charAt2 = charSequence.charAt(i);
                if (charAt2 == '(' && (i7 = i + 1) < length) {
                    int i10 = 1;
                    i = i7;
                    while (i < length) {
                        char charAt3 = charSequence.charAt(i);
                        if (charAt3 != '(') {
                            if (charAt3 == ')' && i10 - 1 <= 0) {
                                break;
                            }
                        } else {
                            i10++;
                        }
                        i++;
                    }
                } else if (charAt2 == '0' && (i6 = i + 1) < length && ((charAt = charSequence.charAt(i6)) == 'x' || charAt == 'X')) {
                    i = i6;
                } else {
                    if (charAt2 < '0' || charAt2 > '9') {
                        if (charAt2 >= 'A' && charAt2 <= 'F') {
                            i3 = i8 << 4;
                            i4 = charAt2 - 'A';
                        } else if (charAt2 >= 'a' && charAt2 <= 'f') {
                            i3 = i8 << 4;
                            i4 = charAt2 - 'a';
                        }
                        i5 = i4 + 10;
                    } else {
                        i3 = i8 << 4;
                        i5 = charAt2 - '0';
                    }
                    i8 = i5 | i3;
                }
                i++;
            }
        }
        return i8;
    }

    public static int parseInt(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return 0;
        }
        return parseInt(charSequence, 0, charSequence.length());
    }
}
