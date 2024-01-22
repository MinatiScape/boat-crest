package com.google.zxing.oned.rss.expanded.decoders;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
/* loaded from: classes11.dex */
public final class c extends h {
    public c(BitArray bitArray) {
        super(bitArray);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() throws NotFoundException, FormatException {
        if (getInformation().getSize() >= 48) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            int f = getGeneralDecoder().f(48, 2);
            sb.append("(392");
            sb.append(f);
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
            sb.append(getGeneralDecoder().c(50, null).b());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
