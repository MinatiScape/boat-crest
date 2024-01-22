package com.google.zxing.oned.rss.expanded.decoders;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
/* loaded from: classes11.dex */
public final class d extends h {
    public d(BitArray bitArray) {
        super(bitArray);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() throws NotFoundException, FormatException {
        if (getInformation().getSize() >= 48) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            int f = getGeneralDecoder().f(48, 2);
            sb.append("(393");
            sb.append(f);
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
            int f2 = getGeneralDecoder().f(50, 10);
            if (f2 / 100 == 0) {
                sb.append('0');
            }
            if (f2 / 10 == 0) {
                sb.append('0');
            }
            sb.append(f2);
            sb.append(getGeneralDecoder().c(60, null).b());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
