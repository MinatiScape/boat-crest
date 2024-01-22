package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.blankj.utilcode.constant.CacheConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class CacheDoubleUtils implements CacheConstants {
    public static final Map<String, CacheDoubleUtils> c = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final CacheMemoryUtils f2241a;
    public final CacheDiskUtils b;

    public CacheDoubleUtils(CacheMemoryUtils cacheMemoryUtils, CacheDiskUtils cacheDiskUtils) {
        this.f2241a = cacheMemoryUtils;
        this.b = cacheDiskUtils;
    }

    public static CacheDoubleUtils getInstance() {
        return getInstance(CacheMemoryUtils.getInstance(), CacheDiskUtils.getInstance());
    }

    public void clear() {
        this.f2241a.clear();
        this.b.clear();
    }

    public Bitmap getBitmap(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getBitmap(str, null);
    }

    public byte[] getBytes(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getBytes(str, null);
    }

    public int getCacheDiskCount() {
        return this.b.getCacheCount();
    }

    public long getCacheDiskSize() {
        return this.b.getCacheSize();
    }

    public int getCacheMemoryCount() {
        return this.f2241a.getCacheCount();
    }

    public Drawable getDrawable(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getDrawable(str, null);
    }

    public JSONArray getJSONArray(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getJSONArray(str, null);
    }

    public JSONObject getJSONObject(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getJSONObject(str, null);
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(creator, "Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) getParcelable(str, creator, null);
    }

    public Object getSerializable(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getSerializable(str, null);
    }

    public String getString(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getString(str, null);
    }

    public void put(@NonNull String str, byte[] bArr) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, bArr, -1);
    }

    public void remove(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.remove(str);
        this.b.remove(str);
    }

    public static CacheDoubleUtils getInstance(@NonNull CacheMemoryUtils cacheMemoryUtils, @NonNull CacheDiskUtils cacheDiskUtils) {
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cacheDiskUtils, "Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        String str = cacheDiskUtils.toString() + "_" + cacheMemoryUtils.toString();
        Map<String, CacheDoubleUtils> map = c;
        CacheDoubleUtils cacheDoubleUtils = map.get(str);
        if (cacheDoubleUtils == null) {
            synchronized (CacheDoubleUtils.class) {
                cacheDoubleUtils = map.get(str);
                if (cacheDoubleUtils == null) {
                    cacheDoubleUtils = new CacheDoubleUtils(cacheMemoryUtils, cacheDiskUtils);
                    map.put(str, cacheDoubleUtils);
                }
            }
        }
        return cacheDoubleUtils;
    }

    public Bitmap getBitmap(@NonNull String str, Bitmap bitmap) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Bitmap bitmap2 = (Bitmap) this.f2241a.get(str);
        if (bitmap2 != null) {
            return bitmap2;
        }
        Bitmap bitmap3 = this.b.getBitmap(str);
        if (bitmap3 != null) {
            this.f2241a.put(str, bitmap3);
            return bitmap3;
        }
        return bitmap;
    }

    public byte[] getBytes(@NonNull String str, byte[] bArr) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] bArr2 = (byte[]) this.f2241a.get(str);
        if (bArr2 != null) {
            return bArr2;
        }
        byte[] bytes = this.b.getBytes(str);
        if (bytes != null) {
            this.f2241a.put(str, bytes);
            return bytes;
        }
        return bArr;
    }

    public Drawable getDrawable(@NonNull String str, Drawable drawable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Drawable drawable2 = (Drawable) this.f2241a.get(str);
        if (drawable2 != null) {
            return drawable2;
        }
        Drawable drawable3 = this.b.getDrawable(str);
        if (drawable3 != null) {
            this.f2241a.put(str, drawable3);
            return drawable3;
        }
        return drawable;
    }

    public JSONArray getJSONArray(@NonNull String str, JSONArray jSONArray) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        JSONArray jSONArray2 = (JSONArray) this.f2241a.get(str);
        if (jSONArray2 != null) {
            return jSONArray2;
        }
        JSONArray jSONArray3 = this.b.getJSONArray(str);
        if (jSONArray3 != null) {
            this.f2241a.put(str, jSONArray3);
            return jSONArray3;
        }
        return jSONArray;
    }

    public JSONObject getJSONObject(@NonNull String str, JSONObject jSONObject) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        JSONObject jSONObject2 = (JSONObject) this.f2241a.get(str);
        if (jSONObject2 != null) {
            return jSONObject2;
        }
        JSONObject jSONObject3 = this.b.getJSONObject(str);
        if (jSONObject3 != null) {
            this.f2241a.put(str, jSONObject3);
            return jSONObject3;
        }
        return jSONObject;
    }

    public Object getSerializable(@NonNull String str, Object obj) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Object obj2 = this.f2241a.get(str);
        if (obj2 != null) {
            return obj2;
        }
        Object serializable = this.b.getSerializable(str);
        if (serializable != null) {
            this.f2241a.put(str, serializable);
            return serializable;
        }
        return obj;
    }

    public String getString(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        String str3 = (String) this.f2241a.get(str);
        if (str3 != null) {
            return str3;
        }
        String string = this.b.getString(str);
        if (string != null) {
            this.f2241a.put(str, string);
            return string;
        }
        return str2;
    }

    public void put(@NonNull String str, byte[] bArr, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, bArr, i);
        this.b.put(str, bArr, i);
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(creator, "Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        T t2 = (T) this.f2241a.get(str);
        if (t2 != null) {
            return t2;
        }
        T t3 = (T) this.b.getParcelable(str, creator);
        if (t3 != null) {
            this.f2241a.put(str, t3);
            return t3;
        }
        return t;
    }

    public void put(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, str2, -1);
    }

    public void put(@NonNull String str, String str2, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, str2, i);
        this.b.put(str, str2, i);
    }

    public void put(@NonNull String str, JSONObject jSONObject) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, jSONObject, -1);
    }

    public void put(@NonNull String str, JSONObject jSONObject, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, jSONObject, i);
        this.b.put(str, jSONObject, i);
    }

    public void put(@NonNull String str, JSONArray jSONArray) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, jSONArray, -1);
    }

    public void put(@NonNull String str, JSONArray jSONArray, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, jSONArray, i);
        this.b.put(str, jSONArray, i);
    }

    public void put(@NonNull String str, Bitmap bitmap) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, bitmap, -1);
    }

    public void put(@NonNull String str, Bitmap bitmap, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, bitmap, i);
        this.b.put(str, bitmap, i);
    }

    public void put(@NonNull String str, Drawable drawable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, drawable, -1);
    }

    public void put(@NonNull String str, Drawable drawable, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, drawable, i);
        this.b.put(str, drawable, i);
    }

    public void put(@NonNull String str, Parcelable parcelable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, parcelable, -1);
    }

    public void put(@NonNull String str, Parcelable parcelable, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, parcelable, i);
        this.b.put(str, parcelable, i);
    }

    public void put(@NonNull String str, Serializable serializable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, serializable, -1);
    }

    public void put(@NonNull String str, Serializable serializable, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.f2241a.put(str, serializable, i);
        this.b.put(str, serializable, i);
    }
}
