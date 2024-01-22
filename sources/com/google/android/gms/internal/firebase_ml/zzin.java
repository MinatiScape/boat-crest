package com.google.android.gms.internal.firebase_ml;

import java.nio.charset.Charset;
import org.apache.commons.codec.CharEncoding;
/* loaded from: classes7.dex */
public final class zzin {
    @Deprecated
    public static final Charset UTF_8;

    static {
        Charset.forName("ISO-8859-1");
        Charset.forName("US-ASCII");
        Charset.forName(CharEncoding.UTF_16);
        Charset.forName(CharEncoding.UTF_16BE);
        Charset.forName(CharEncoding.UTF_16LE);
        UTF_8 = Charset.forName("UTF-8");
    }
}
