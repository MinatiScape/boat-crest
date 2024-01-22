package com.google.android.gms.internal.firebase_ml;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes7.dex */
public final class k0 {
    public static final k0 b = new k0();

    /* renamed from: a  reason: collision with root package name */
    public final String f8696a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public k0() {
        /*
            r4 = this;
            java.lang.String r0 = "java.version"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            java.lang.String r1 = "9"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L11
            java.lang.String r0 = "9.0.0"
            goto L15
        L11:
            java.lang.String r0 = e(r0)
        L15:
            com.google.android.gms.internal.firebase_ml.zzmt r1 = com.google.android.gms.internal.firebase_ml.zzmt.OS_NAME
            java.lang.String r1 = r1.value()
            com.google.android.gms.internal.firebase_ml.zzmt r2 = com.google.android.gms.internal.firebase_ml.zzmt.OS_VERSION
            java.lang.String r2 = r2.value()
            java.lang.String r3 = com.google.android.gms.internal.firebase_ml.zzgc.VERSION
            r4.<init>(r0, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.k0.<init>():void");
    }

    public static k0 a() {
        return b;
    }

    public static String d(String str) {
        return str.toLowerCase().replaceAll("[^\\w\\d\\-]", "-");
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("(\\d+\\.\\d+\\.\\d+).*").matcher(str);
        return matcher.find() ? matcher.group(1) : str;
    }

    public final String c(String str) {
        return String.format(this.f8696a, d(str));
    }

    public k0(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder("java/");
        sb.append(e(str));
        sb.append(" http-google-%s/");
        sb.append(e(str4));
        if (str2 != null && str3 != null) {
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(d(str2));
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(e(str3));
        }
        this.f8696a = sb.toString();
    }
}
