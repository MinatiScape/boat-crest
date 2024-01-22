package org.bouncycastle.pqc.crypto.newhope;

import com.jieli.jl_rcsp.constant.RcspErrorCode;
import kotlin.UShort;
/* loaded from: classes13.dex */
public class g {
    public static short a(short s) {
        int i = s & UShort.MAX_VALUE;
        return (short) (i - (((i * 5) >>> 16) * RcspErrorCode.ERR_PARSE_DATA));
    }

    public static short b(int i) {
        return (short) (((((i * 12287) & 262143) * RcspErrorCode.ERR_PARSE_DATA) + i) >>> 18);
    }
}
