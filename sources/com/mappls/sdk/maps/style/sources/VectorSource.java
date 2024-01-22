package com.mappls.sdk.maps.style.sources;

import android.net.Uri;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.UiThread;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.maps.style.expressions.Expression;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@UiThread
/* loaded from: classes11.dex */
public class VectorSource extends Source {
    @Keep
    public VectorSource(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Feature[] querySourceFeatures(String[] strArr, Object[] objArr);

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
    public native void initialize(String str, Object obj);

    @NonNull
    @Keep
    public native String nativeGetUrl();

    @NonNull
    public List<Feature> querySourceFeatures(@Size(min = 1) String[] strArr, @Nullable Expression expression) {
        checkThread();
        Feature[] querySourceFeatures = querySourceFeatures(strArr, expression != null ? expression.toArray() : null);
        return querySourceFeatures != null ? Arrays.asList(querySourceFeatures) : new ArrayList();
    }

    @Deprecated
    public VectorSource(String str, URL url) {
        this(str, url.toExternalForm());
    }

    public VectorSource(String str, Uri uri) {
        this(str, uri.toString());
    }

    public VectorSource(String str, String str2) {
        initialize(str, str2);
    }

    public VectorSource(String str, TileSet tileSet) {
        initialize(str, tileSet.a());
    }
}
