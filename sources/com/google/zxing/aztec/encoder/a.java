package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import kotlin.text.Typography;
/* loaded from: classes11.dex */
public final class a extends d {
    public final short c;
    public final short d;

    public a(d dVar, int i, int i2) {
        super(dVar);
        this.c = (short) i;
        this.d = (short) i2;
    }

    @Override // com.google.zxing.aztec.encoder.d
    public void c(BitArray bitArray, byte[] bArr) {
        int i = 0;
        while (true) {
            short s = this.d;
            if (i >= s) {
                return;
            }
            if (i == 0 || (i == 31 && s <= 62)) {
                bitArray.appendBits(31, 5);
                short s2 = this.d;
                if (s2 > 62) {
                    bitArray.appendBits(s2 - 31, 16);
                } else if (i == 0) {
                    bitArray.appendBits(Math.min((int) s2, 31), 5);
                } else {
                    bitArray.appendBits(s2 - 31, 5);
                }
            }
            bitArray.appendBits(bArr[this.c + i], 8);
            i++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append((int) this.c);
        sb.append("::");
        sb.append((this.c + this.d) - 1);
        sb.append(Typography.greater);
        return sb.toString();
    }
}
