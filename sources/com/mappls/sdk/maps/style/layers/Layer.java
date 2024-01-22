package com.mappls.sdk.maps.style.layers;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.mappls.sdk.maps.LibraryLoader;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.types.Formatted;
import com.mappls.sdk.maps.utils.ThreadUtils;
/* loaded from: classes11.dex */
public abstract class Layer {

    /* renamed from: a  reason: collision with root package name */
    public boolean f12848a;
    @Keep
    private boolean invalidated;
    @Keep
    private long nativePtr;

    static {
        LibraryLoader.load();
    }

    @Keep
    public Layer(long j) {
        checkThread();
        this.nativePtr = j;
    }

    @Nullable
    public final Object a(@Nullable Object obj) {
        if (obj instanceof Expression) {
            return ((Expression) obj).toArray();
        }
        return obj instanceof Formatted ? ((Formatted) obj).toArray() : obj;
    }

    public void checkThread() {
        ThreadUtils.checkThread("Mbgl-Layer");
    }

    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public String getId() {
        checkThread();
        return nativeGetId();
    }

    public float getMaxZoom() {
        checkThread();
        return nativeGetMaxZoom();
    }

    public float getMinZoom() {
        checkThread();
        return nativeGetMinZoom();
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    @NonNull
    public PropertyValue<String> getVisibility() {
        checkThread();
        return new PaintPropertyValue("visibility", (String) nativeGetVisibility());
    }

    public boolean isDetached() {
        return this.f12848a;
    }

    @Nullable
    @Keep
    public native JsonElement nativeGetFilter();

    @NonNull
    @Keep
    public native String nativeGetId();

    @Keep
    public native float nativeGetMaxZoom();

    @Keep
    public native float nativeGetMinZoom();

    @NonNull
    @Keep
    public native String nativeGetSourceId();

    @NonNull
    @Keep
    public native String nativeGetSourceLayer();

    @NonNull
    @Keep
    public native Object nativeGetVisibility();

    @Keep
    public native void nativeSetFilter(Object[] objArr);

    @Keep
    public native void nativeSetLayoutProperty(String str, Object obj);

    @Keep
    public native void nativeSetMaxZoom(float f);

    @Keep
    public native void nativeSetMinZoom(float f);

    @Keep
    public native void nativeSetPaintProperty(String str, Object obj);

    @Keep
    public native void nativeSetSourceLayer(String str);

    public void setDetached() {
        this.f12848a = true;
    }

    public void setMaxZoom(float f) {
        checkThread();
        nativeSetMaxZoom(f);
    }

    public void setMinZoom(float f) {
        checkThread();
        nativeSetMinZoom(f);
    }

    public void setProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        if (this.f12848a) {
            return;
        }
        checkThread();
        if (propertyValueArr.length == 0) {
            return;
        }
        for (PropertyValue<?> propertyValue : propertyValueArr) {
            Object a2 = a(propertyValue.value);
            if (propertyValue instanceof PaintPropertyValue) {
                nativeSetPaintProperty(propertyValue.name, a2);
            } else {
                nativeSetLayoutProperty(propertyValue.name, a2);
            }
        }
    }

    public Layer() {
        checkThread();
    }
}
