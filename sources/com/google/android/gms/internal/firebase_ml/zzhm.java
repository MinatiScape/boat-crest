package com.google.android.gms.internal.firebase_ml;

import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzhm {

    /* renamed from: a  reason: collision with root package name */
    public static final Map<Character, o0> f8772a = new HashMap();

    static {
        o0.values();
    }

    public static String a(String str, Iterator<?> it, boolean z, o0 o0Var) {
        String str2;
        if (it.hasNext()) {
            StringBuilder sb = new StringBuilder();
            if (z) {
                str2 = o0Var.zzgo();
            } else {
                if (o0Var.zzgp()) {
                    sb.append(zzjw.zzas(str));
                    sb.append("=");
                }
                str2 = Constants.SEPARATOR_COMMA;
            }
            while (it.hasNext()) {
                if (z && o0Var.zzgp()) {
                    sb.append(zzjw.zzas(str));
                    sb.append("=");
                }
                sb.append(o0Var.zzak(it.next().toString()));
                if (it.hasNext()) {
                    sb.append(str2);
                }
            }
            return sb.toString();
        }
        return "";
    }

    public static Map<String, Object> b(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : zzix.zzf(obj).entrySet()) {
            Object value = entry.getValue();
            if (value != null && !zzix.isNull(value)) {
                linkedHashMap.put(entry.getKey(), value);
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f8 A[EDGE_INSN: B:89:0x01f8->B:87:0x01f8 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String zza(java.lang.String r12, java.lang.String r13, java.lang.Object r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 516
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzhm.zza(java.lang.String, java.lang.String, java.lang.Object, boolean):java.lang.String");
    }
}
