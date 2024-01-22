package com.google.android.gms.analytics;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
@VisibleForTesting
/* loaded from: classes6.dex */
public abstract class zzj<T extends zzj> {
    public static String zza(@Nullable Object obj) {
        return zzd(obj, 0);
    }

    public static String zzb(@Nullable Map map) {
        return zzd(map, 1);
    }

    public static String zzd(@Nullable Object obj, int i) {
        if (i > 10) {
            return "ERROR: Recursive toString calls";
        }
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return TextUtils.isEmpty((String) obj) ? "" : obj.toString();
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == 0 ? "" : obj.toString();
        } else if (obj instanceof Long) {
            return ((Long) obj).longValue() == 0 ? "" : obj.toString();
        } else if (obj instanceof Double) {
            return ((Double) obj).doubleValue() == 0.0d ? "" : obj.toString();
        } else if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue() ? "" : obj.toString();
        } else if (obj instanceof List) {
            StringBuilder sb = new StringBuilder();
            if (i > 0) {
                sb.append("[");
            }
            int length = sb.length();
            for (Object obj2 : (List) obj) {
                if (sb.length() > length) {
                    sb.append(", ");
                }
                sb.append(zzd(obj2, i + 1));
            }
            if (i > 0) {
                sb.append("]");
            }
            return sb.toString();
        } else if (obj instanceof Map) {
            StringBuilder sb2 = new StringBuilder();
            boolean z = false;
            int i2 = 0;
            for (Map.Entry entry : new TreeMap((Map) obj).entrySet()) {
                String zzd = zzd(entry.getValue(), i + 1);
                if (!TextUtils.isEmpty(zzd)) {
                    if (i > 0 && !z) {
                        sb2.append("{");
                        i2 = sb2.length();
                        z = true;
                    }
                    if (sb2.length() > i2) {
                        sb2.append(", ");
                    }
                    sb2.append((String) entry.getKey());
                    sb2.append('=');
                    sb2.append(zzd);
                }
            }
            if (z) {
                sb2.append("}");
            }
            return sb2.toString();
        } else {
            return obj.toString();
        }
    }

    public abstract void zzc(T t);
}
