package com.google.android.gms.internal.firebase_ml;

import java.nio.charset.Charset;
import org.apache.commons.codec.CharEncoding;
/* loaded from: classes7.dex */
public final class zzlz {
    public static final Charset UTF_8;

    static {
        Charset.forName("US-ASCII");
        Charset.forName("ISO-8859-1");
        UTF_8 = Charset.forName("UTF-8");
        Charset.forName(CharEncoding.UTF_16BE);
        Charset.forName(CharEncoding.UTF_16LE);
        Charset.forName(CharEncoding.UTF_16);
    }
}
