package com.mappls.sdk.plugin.directions;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static Map<String, String> f13091a = new HashMap();

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r11 >= 20.0d) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:
        r11 = com.mappls.sdk.plugin.directions.a.a((float) java.lang.Math.round(r11), com.mappls.sdk.plugin.directions.c.f13091a.get(r1), false, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0049, code lost:
        r11 = com.mappls.sdk.plugin.directions.a.a(((float) java.lang.Math.round(r11 * 10.0d)) / 10.0f, r1, true, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0063, code lost:
        if (r11 >= 20.0d) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(double r11, com.mappls.sdk.plugin.directions.SpeedUnitType r13) {
        /*
            java.util.Map<java.lang.String, java.lang.String> r0 = com.mappls.sdk.plugin.directions.c.f13091a
            boolean r0 = r0.isEmpty()
            java.lang.String r1 = "miles per hour"
            java.lang.String r2 = "kilometer per hour"
            if (r0 == 0) goto L1a
            java.util.Map<java.lang.String, java.lang.String> r0 = com.mappls.sdk.plugin.directions.c.f13091a
            java.lang.String r3 = "km/h"
            r0.put(r2, r3)
            java.util.Map<java.lang.String, java.lang.String> r0 = com.mappls.sdk.plugin.directions.c.f13091a
            java.lang.String r3 = "mph"
            r0.put(r1, r3)
        L1a:
            com.mappls.sdk.plugin.directions.SpeedUnitType r0 = com.mappls.sdk.plugin.directions.SpeedUnitType.KILOMETERS_PER_HOUR
            if (r13 != r0) goto L1f
            r1 = r2
        L1f:
            r2 = 4615288897914535936(0x400cccccc0000000, double:3.5999999046325684)
            double r11 = r11 * r2
            r2 = 1092616192(0x41200000, float:10.0)
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            r5 = 4626322717216342016(0x4034000000000000, double:20.0)
            r7 = 0
            r8 = 1
            if (r13 != r0) goto L55
            int r13 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r13 < 0) goto L49
        L33:
            long r11 = java.lang.Math.round(r11)
            float r11 = (float) r11
            java.util.Map<java.lang.String, java.lang.String> r12 = com.mappls.sdk.plugin.directions.c.f13091a
            java.lang.Object r12 = r12.get(r1)
            java.lang.String r12 = (java.lang.String) r12
            com.mappls.sdk.plugin.directions.a$a r11 = com.mappls.sdk.plugin.directions.a.a(r11, r12, r7, r7)
        L44:
            java.lang.String r11 = r11.a()
            return r11
        L49:
            double r11 = r11 * r3
            long r11 = java.lang.Math.round(r11)
            float r11 = (float) r11
            float r11 = r11 / r2
            com.mappls.sdk.plugin.directions.a$a r11 = com.mappls.sdk.plugin.directions.a.a(r11, r1, r8, r8)
            goto L44
        L55:
            r9 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r11 = r11 * r9
            r9 = 4654792785184948224(0x4099256040000000, double:1609.343994140625)
            double r11 = r11 / r9
            int r13 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r13 < 0) goto L49
            goto L33
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.plugin.directions.c.a(double, com.mappls.sdk.plugin.directions.SpeedUnitType):java.lang.String");
    }
}
