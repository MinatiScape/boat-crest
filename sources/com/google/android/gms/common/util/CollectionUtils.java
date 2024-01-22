package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.InlineMe;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
@KeepForSdk
/* loaded from: classes6.dex */
public final class CollectionUtils {
    public static Map a(int i, boolean z) {
        if (i <= 256) {
            return new ArrayMap(i);
        }
        return new HashMap(i, 1.0f);
    }

    public static Set b(int i, boolean z) {
        if (i <= (true != z ? 256 : 128)) {
            return new ArraySet(i);
        }
        return new HashSet(i, true != z ? 1.0f : 0.75f);
    }

    @KeepForSdk
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        if (collection == null) {
            return true;
        }
        return collection.isEmpty();
    }

    @NonNull
    @Deprecated
    @InlineMe(imports = {"java.util.Collections"}, replacement = "Collections.emptyList()")
    @KeepForSdk
    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    @NonNull
    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(@NonNull K k, @NonNull V v, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3) {
        Map a2 = a(3, false);
        a2.put(k, v);
        a2.put(k2, v2);
        a2.put(k3, v3);
        return Collections.unmodifiableMap(a2);
    }

    @NonNull
    @KeepForSdk
    public static <K, V> Map<K, V> mapOfKeyValueArrays(@NonNull K[] kArr, @NonNull V[] vArr) {
        int length = kArr.length;
        int length2 = vArr.length;
        if (length != length2) {
            throw new IllegalArgumentException("Key and values array lengths not equal: " + length + " != " + length2);
        } else if (length != 0) {
            if (length != 1) {
                Map a2 = a(length, false);
                for (int i = 0; i < kArr.length; i++) {
                    a2.put(kArr[i], vArr[i]);
                }
                return Collections.unmodifiableMap(a2);
            }
            return Collections.singletonMap(kArr[0], vArr[0]);
        } else {
            return Collections.emptyMap();
        }
    }

    @NonNull
    @KeepForSdk
    public static <T> Set<T> mutableSetOfWithSize(int i) {
        if (i == 0) {
            return new ArraySet();
        }
        return b(i, true);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(@NonNull T t, @NonNull T t2, @NonNull T t3) {
        Set b = b(3, false);
        b.add(t);
        b.add(t2);
        b.add(t3);
        return Collections.unmodifiableSet(b);
    }

    @NonNull
    @Deprecated
    @InlineMe(imports = {"java.util.Collections"}, replacement = "Collections.singletonList(item)")
    @KeepForSdk
    public static <T> List<T> listOf(@NonNull T t) {
        return Collections.singletonList(t);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(@NonNull T... tArr) {
        int length = tArr.length;
        if (length != 0) {
            if (length != 1) {
                return Collections.unmodifiableList(Arrays.asList(tArr));
            }
            return Collections.singletonList(tArr[0]);
        }
        return Collections.emptyList();
    }

    @NonNull
    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(@NonNull K k, @NonNull V v, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3, @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5, @NonNull K k6, @NonNull V v6) {
        Map a2 = a(6, false);
        a2.put(k, v);
        a2.put(k2, v2);
        a2.put(k3, v3);
        a2.put(k4, v4);
        a2.put(k5, v5);
        a2.put(k6, v6);
        return Collections.unmodifiableMap(a2);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(@NonNull T... tArr) {
        int length = tArr.length;
        if (length != 0) {
            if (length != 1) {
                if (length == 2) {
                    T t = tArr[0];
                    T t2 = tArr[1];
                    Set b = b(2, false);
                    b.add(t);
                    b.add(t2);
                    return Collections.unmodifiableSet(b);
                } else if (length != 3) {
                    if (length != 4) {
                        Set b2 = b(length, false);
                        Collections.addAll(b2, tArr);
                        return Collections.unmodifiableSet(b2);
                    }
                    T t3 = tArr[0];
                    T t4 = tArr[1];
                    T t5 = tArr[2];
                    T t6 = tArr[3];
                    Set b3 = b(4, false);
                    b3.add(t3);
                    b3.add(t4);
                    b3.add(t5);
                    b3.add(t6);
                    return Collections.unmodifiableSet(b3);
                } else {
                    return setOf(tArr[0], tArr[1], tArr[2]);
                }
            }
            return Collections.singleton(tArr[0]);
        }
        return Collections.emptySet();
    }
}
