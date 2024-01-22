package com.mappls.sdk.maps.style.sources;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.LibraryLoader;
import com.mappls.sdk.maps.utils.ThreadUtils;
/* loaded from: classes11.dex */
public abstract class Source {
    private static final String TAG = "Mbgl-Source";
    public boolean detached;
    @Keep
    private long nativePtr;

    static {
        LibraryLoader.load();
    }

    @Keep
    public Source(long j) {
        checkThread();
        this.nativePtr = j;
    }

    public void checkThread() {
        ThreadUtils.checkThread(TAG);
    }

    @NonNull
    public String getAttribution() {
        checkThread();
        return nativeGetAttribution();
    }

    @NonNull
    public String getId() {
        checkThread();
        return nativeGetId();
    }

    @Nullable
    public Integer getMaxOverscaleFactorForParentTiles() {
        return nativeGetMaxOverscaleFactorForParentTiles();
    }

    @NonNull
    public Long getMinimumTileUpdateInterval() {
        return nativeGetMinimumTileUpdateInterval();
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    @Nullable
    public Integer getPrefetchZoomDelta() {
        return nativeGetPrefetchZoomDelta();
    }

    @NonNull
    public Boolean isVolatile() {
        return nativeIsVolatile();
    }

    @NonNull
    @Keep
    public native String nativeGetAttribution();

    @NonNull
    @Keep
    public native String nativeGetId();

    @NonNull
    @Keep
    public native Integer nativeGetMaxOverscaleFactorForParentTiles();

    @NonNull
    @Keep
    public native Long nativeGetMinimumTileUpdateInterval();

    @NonNull
    @Keep
    public native Integer nativeGetPrefetchZoomDelta();

    @NonNull
    @Keep
    public native Boolean nativeIsVolatile();

    @NonNull
    @Keep
    public native void nativeSetMaxOverscaleFactorForParentTiles(Integer num);

    @NonNull
    @Keep
    public native void nativeSetMinimumTileUpdateInterval(Long l);

    @NonNull
    @Keep
    public native void nativeSetPrefetchZoomDelta(Integer num);

    @NonNull
    @Keep
    public native void nativeSetVolatile(Boolean bool);

    public void setDetached() {
        this.detached = true;
    }

    public void setMaxOverscaleFactorForParentTiles(@Nullable Integer num) {
        nativeSetMaxOverscaleFactorForParentTiles(num);
    }

    public void setMinimumTileUpdateInterval(Long l) {
        nativeSetMinimumTileUpdateInterval(l);
    }

    public void setPrefetchZoomDelta(@Nullable Integer num) {
        nativeSetPrefetchZoomDelta(num);
    }

    public void setVolatile(Boolean bool) {
        nativeSetVolatile(bool);
    }

    public Source() {
        checkThread();
    }
}
