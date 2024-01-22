package com.google.zxing.oned.rss.expanded.decoders;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
/* loaded from: classes11.dex */
public final class e extends i {
    public final String c;
    public final String d;

    public e(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.c = str2;
        this.d = str;
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.i
    public void d(StringBuilder sb, int i) {
        sb.append(HexStringBuilder.COMMENT_BEGIN_CHAR);
        sb.append(this.d);
        sb.append(i / 100000);
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.i
    public int e(int i) {
        return i % 100000;
    }

    public final void g(StringBuilder sb, int i) {
        int f = getGeneralDecoder().f(i, 16);
        if (f == 38400) {
            return;
        }
        sb.append(HexStringBuilder.COMMENT_BEGIN_CHAR);
        sb.append(this.c);
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        int i2 = f % 32;
        int i3 = f / 32;
        int i4 = (i3 % 12) + 1;
        int i5 = i3 / 12;
        if (i5 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i5);
        if (i4 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i4);
        if (i2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i2);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() throws NotFoundException {
        if (getInformation().getSize() == 84) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            f(sb, 48, 20);
            g(sb, 68);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
