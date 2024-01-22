package com.blankj.utilcode.util;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class DebouncingUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Map<String, Long> f2251a = new ConcurrentHashMap(64);

    public DebouncingUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void a(long j) {
        Map<String, Long> map = f2251a;
        if (map.size() < 64) {
            return;
        }
        Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (j >= it.next().getValue().longValue()) {
                it.remove();
            }
        }
    }

    public static boolean isValid(@NonNull View view) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return isValid(view, 1000L);
    }

    public static boolean isValid(@NonNull View view, long j) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return isValid(String.valueOf(view.hashCode()), j);
    }

    public static boolean isValid(@NonNull String str, long j) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("The key is null.");
        }
        if (j >= 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            a(elapsedRealtime);
            Map<String, Long> map = f2251a;
            Long l = map.get(str);
            if (l == null || elapsedRealtime >= l.longValue()) {
                map.put(str, Long.valueOf(elapsedRealtime + j));
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("The duration is less than 0.");
    }
}
