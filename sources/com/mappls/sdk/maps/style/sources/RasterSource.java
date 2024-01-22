package com.mappls.sdk.maps.style.sources;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.URI;
import java.net.URL;
/* loaded from: classes11.dex */
public class RasterSource extends Source {
    public static final int DEFAULT_TILE_SIZE = 512;

    @Keep
    public RasterSource(long j) {
        super(j);
    }

    @Keep
    public native void finalize() throws Throwable;

    @Nullable
    public String getUri() {
        checkThread();
        return nativeGetUrl();
    }

    @Nullable
    @Deprecated
    public String getUrl() {
        checkThread();
        return nativeGetUrl();
    }

    @Keep
    public native void initialize(String str, Object obj, int i);

    @NonNull
    @Keep
    public native String nativeGetUrl();

    public RasterSource(String str, URL url) {
        this(str, url.toExternalForm());
    }

    public RasterSource(String str, URI uri) {
        this(str, uri.toString());
    }

    public RasterSource(String str, String str2) {
        initialize(str, str2, 512);
    }

    public RasterSource(String str, String str2, int i) {
        initialize(str, str2, i);
    }

    public RasterSource(String str, TileSet tileSet) {
        initialize(str, tileSet.a(), 512);
    }

    public RasterSource(String str, TileSet tileSet, int i) {
        initialize(str, tileSet.a(), i);
    }
}
