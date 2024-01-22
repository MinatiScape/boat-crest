package org.bouncycastle.util.encoders;

import com.crrepa.c.a;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes13.dex */
public class Base64Encoder implements Encoder {
    public final byte[] encodingTable = {65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public byte padding = 61;
    public final byte[] decodingTable = new byte[128];

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    public final int a(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        byte b = this.padding;
        if (c3 == b) {
            if (c4 == b) {
                byte[] bArr = this.decodingTable;
                byte b2 = bArr[c];
                byte b3 = bArr[c2];
                if ((b2 | b3) >= 0) {
                    outputStream.write((b2 << 2) | (b3 >> 4));
                    return 1;
                }
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else if (c4 == b) {
            byte[] bArr2 = this.decodingTable;
            byte b4 = bArr2[c];
            byte b5 = bArr2[c2];
            byte b6 = bArr2[c3];
            if ((b4 | b5 | b6) >= 0) {
                outputStream.write((b4 << 2) | (b5 >> 4));
                outputStream.write((b5 << 4) | (b6 >> 2));
                return 2;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else {
            byte[] bArr3 = this.decodingTable;
            byte b7 = bArr3[c];
            byte b8 = bArr3[c2];
            byte b9 = bArr3[c3];
            byte b10 = bArr3[c4];
            if ((b7 | b8 | b9 | b10) >= 0) {
                outputStream.write((b7 << 2) | (b8 >> 4));
                outputStream.write((b8 << 4) | (b9 >> 2));
                outputStream.write((b9 << 6) | b10);
                return 3;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        }
    }

    public final boolean b(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public final int c(String str, int i, int i2) {
        while (i < i2 && b(str.charAt(i))) {
            i++;
        }
        return i;
    }

    public final int d(byte[] bArr, int i, int i2) {
        while (i < i2 && b((char) bArr[i])) {
            i++;
        }
        return i;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && b(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        int i2 = length;
        int i3 = 0;
        while (i2 > 0 && i3 != 4) {
            if (!b(str.charAt(i2 - 1))) {
                i3++;
            }
            i2--;
        }
        int c = c(str, 0, i2);
        while (c < i2) {
            int i4 = c + 1;
            byte b = this.decodingTable[str.charAt(c)];
            int c2 = c(str, i4, i2);
            int i5 = c2 + 1;
            byte b2 = this.decodingTable[str.charAt(c2)];
            int c3 = c(str, i5, i2);
            int i6 = c3 + 1;
            byte b3 = this.decodingTable[str.charAt(c3)];
            int c4 = c(str, i6, i2);
            int i7 = c4 + 1;
            byte b4 = this.decodingTable[str.charAt(c4)];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i += 3;
            c = c(str, i7, i2);
        }
        int c5 = c(str, c, length);
        int c6 = c(str, c5 + 1, length);
        int c7 = c(str, c6 + 1, length);
        return i + a(outputStream, str.charAt(c5), str.charAt(c6), str.charAt(c7), str.charAt(c(str, c7 + 1, length)));
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3 = i2 + i;
        while (i3 > i && b((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        int i5 = i3;
        int i6 = 0;
        while (i5 > i && i6 != 4) {
            if (!b((char) bArr[i5 - 1])) {
                i6++;
            }
            i5--;
        }
        int d = d(bArr, i, i5);
        while (d < i5) {
            int i7 = d + 1;
            byte b = this.decodingTable[bArr[d]];
            int d2 = d(bArr, i7, i5);
            int i8 = d2 + 1;
            byte b2 = this.decodingTable[bArr[d2]];
            int d3 = d(bArr, i8, i5);
            int i9 = d3 + 1;
            byte b3 = this.decodingTable[bArr[d3]];
            int d4 = d(bArr, i9, i5);
            int i10 = d4 + 1;
            byte b4 = this.decodingTable[bArr[d4]];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i4 += 3;
            d = d(bArr, i10, i5);
        }
        int d5 = d(bArr, d, i3);
        int d6 = d(bArr, d5 + 1, i3);
        int d7 = d(bArr, d6 + 1, i3);
        return i4 + a(outputStream, (char) bArr[d5], (char) bArr[d6], (char) bArr[d7], (char) bArr[d(bArr, d7 + 1, i3)]);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a3  */
    @Override // org.bouncycastle.util.encoders.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int encode(byte[] r10, int r11, int r12, java.io.OutputStream r13) throws java.io.IOException {
        /*
            r9 = this;
            int r0 = r12 % 3
            int r12 = r12 - r0
            r1 = r11
        L4:
            int r2 = r11 + r12
            r3 = 4
            r4 = 2
            if (r1 >= r2) goto L4c
            r2 = r10[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r5 = r1 + 1
            r5 = r10[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r6 = r1 + 2
            r6 = r10[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte[] r7 = r9.encodingTable
            int r8 = r2 >>> 2
            r8 = r8 & 63
            r7 = r7[r8]
            r13.write(r7)
            byte[] r7 = r9.encodingTable
            int r2 = r2 << r3
            int r3 = r5 >>> 4
            r2 = r2 | r3
            r2 = r2 & 63
            r2 = r7[r2]
            r13.write(r2)
            byte[] r2 = r9.encodingTable
            int r3 = r5 << 2
            int r4 = r6 >>> 6
            r3 = r3 | r4
            r3 = r3 & 63
            r2 = r2[r3]
            r13.write(r2)
            byte[] r2 = r9.encodingTable
            r3 = r6 & 63
            r2 = r2[r3]
            r13.write(r2)
            int r1 = r1 + 3
            goto L4
        L4c:
            r11 = 1
            if (r0 == r11) goto L7b
            if (r0 == r4) goto L52
            goto L9e
        L52:
            r1 = r10[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r2 + r11
            r10 = r10[r2]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r11 = r1 >>> 2
            r11 = r11 & 63
            int r1 = r1 << r3
            int r2 = r10 >>> 4
            r1 = r1 | r2
            r1 = r1 & 63
            int r10 = r10 << r4
            r10 = r10 & 63
            byte[] r2 = r9.encodingTable
            r11 = r2[r11]
            r13.write(r11)
            byte[] r11 = r9.encodingTable
            r11 = r11[r1]
            r13.write(r11)
            byte[] r11 = r9.encodingTable
            r10 = r11[r10]
            goto L96
        L7b:
            r10 = r10[r2]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r11 = r10 >>> 2
            r11 = r11 & 63
            int r10 = r10 << r3
            r10 = r10 & 63
            byte[] r1 = r9.encodingTable
            r11 = r1[r11]
            r13.write(r11)
            byte[] r11 = r9.encodingTable
            r10 = r11[r10]
            r13.write(r10)
            byte r10 = r9.padding
        L96:
            r13.write(r10)
            byte r10 = r9.padding
            r13.write(r10)
        L9e:
            int r12 = r12 / 3
            int r12 = r12 * r3
            if (r0 != 0) goto La4
            r3 = 0
        La4:
            int r12 = r12 + r3
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.util.encoders.Base64Encoder.encode(byte[], int, int, java.io.OutputStream):int");
    }

    public void initialiseDecodingTable() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i >= bArr2.length) {
                return;
            }
            this.decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }
}
