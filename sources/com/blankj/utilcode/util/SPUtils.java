package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
@SuppressLint({"ApplySharedPref"})
/* loaded from: classes.dex */
public final class SPUtils {
    public static final Map<String, SPUtils> b = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f2285a;

    public SPUtils(String str, int i) {
        this.f2285a = Utils.getApp().getSharedPreferences(str, i);
    }

    public static boolean a(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static SPUtils getInstance() {
        return getInstance("", 0);
    }

    public void clear() {
        clear(false);
    }

    public boolean contains(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return this.f2285a.contains(str);
    }

    public Map<String, ?> getAll() {
        return this.f2285a.getAll();
    }

    public boolean getBoolean(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getBoolean(str, false);
    }

    public float getFloat(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getFloat(str, -1.0f);
    }

    public int getInt(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getInt(str, -1);
    }

    public long getLong(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getLong(str, -1L);
    }

    public String getString(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getString(str, "");
    }

    public Set<String> getStringSet(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getStringSet(str, Collections.emptySet());
    }

    public void put(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, str2, false);
    }

    public void remove(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        remove(str, false);
    }

    public static SPUtils getInstance(int i) {
        return getInstance("", i);
    }

    public void clear(boolean z) {
        if (z) {
            this.f2285a.edit().clear().commit();
        } else {
            this.f2285a.edit().clear().apply();
        }
    }

    public static SPUtils getInstance(String str) {
        return getInstance(str, 0);
    }

    public boolean getBoolean(@NonNull String str, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return this.f2285a.getBoolean(str, z);
    }

    public float getFloat(@NonNull String str, float f) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return this.f2285a.getFloat(str, f);
    }

    public int getInt(@NonNull String str, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return this.f2285a.getInt(str, i);
    }

    public long getLong(@NonNull String str, long j) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return this.f2285a.getLong(str, j);
    }

    public String getString(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return this.f2285a.getString(str, str2);
    }

    public Set<String> getStringSet(@NonNull String str, Set<String> set) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return this.f2285a.getStringSet(str, set);
    }

    public void put(@NonNull String str, String str2, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (z) {
            this.f2285a.edit().putString(str, str2).commit();
        } else {
            this.f2285a.edit().putString(str, str2).apply();
        }
    }

    public void remove(@NonNull String str, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (z) {
            this.f2285a.edit().remove(str).commit();
        } else {
            this.f2285a.edit().remove(str).apply();
        }
    }

    public static SPUtils getInstance(String str, int i) {
        if (a(str)) {
            str = "spUtils";
        }
        Map<String, SPUtils> map = b;
        SPUtils sPUtils = map.get(str);
        if (sPUtils == null) {
            synchronized (SPUtils.class) {
                sPUtils = map.get(str);
                if (sPUtils == null) {
                    sPUtils = new SPUtils(str, i);
                    map.put(str, sPUtils);
                }
            }
        }
        return sPUtils;
    }

    public void put(@NonNull String str, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, i, false);
    }

    public void put(@NonNull String str, int i, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (z) {
            this.f2285a.edit().putInt(str, i).commit();
        } else {
            this.f2285a.edit().putInt(str, i).apply();
        }
    }

    public void put(@NonNull String str, long j) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, j, false);
    }

    public void put(@NonNull String str, long j, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (z) {
            this.f2285a.edit().putLong(str, j).commit();
        } else {
            this.f2285a.edit().putLong(str, j).apply();
        }
    }

    public void put(@NonNull String str, float f) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, f, false);
    }

    public void put(@NonNull String str, float f, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (z) {
            this.f2285a.edit().putFloat(str, f).commit();
        } else {
            this.f2285a.edit().putFloat(str, f).apply();
        }
    }

    public void put(@NonNull String str, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, z, false);
    }

    public void put(@NonNull String str, boolean z, boolean z2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (z2) {
            this.f2285a.edit().putBoolean(str, z).commit();
        } else {
            this.f2285a.edit().putBoolean(str, z).apply();
        }
    }

    public void put(@NonNull String str, Set<String> set) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, set, false);
    }

    public void put(@NonNull String str, Set<String> set, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (z) {
            this.f2285a.edit().putStringSet(str, set).commit();
        } else {
            this.f2285a.edit().putStringSet(str, set).apply();
        }
    }
}
