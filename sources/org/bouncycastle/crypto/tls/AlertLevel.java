package org.bouncycastle.crypto.tls;
/* loaded from: classes13.dex */
public class AlertLevel {
    public static final short fatal = 2;
    public static final short warning = 1;

    public static String getName(short s) {
        return s != 1 ? s != 2 ? "UNKNOWN" : "fatal" : "warning";
    }

    public static String getText(short s) {
        return getName(s) + "(" + ((int) s) + ")";
    }
}
