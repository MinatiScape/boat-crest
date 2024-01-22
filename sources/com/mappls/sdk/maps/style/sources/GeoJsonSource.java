package com.mappls.sdk.maps.style.sources;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.maps.style.expressions.Expression;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@UiThread
/* loaded from: classes11.dex */
public class GeoJsonSource extends Source {
    @Keep
    public GeoJsonSource(long j) {
        super(j);
    }

    @Keep
    private native Feature[] nativeGetClusterChildren(Feature feature);

    @Keep
    private native int nativeGetClusterExpansionZoom(Feature feature);

    @Keep
    private native Feature[] nativeGetClusterLeaves(Feature feature, long j, long j2);

    @Keep
    private native void nativeSetFeature(Feature feature);

    @Keep
    private native void nativeSetFeatureCollection(FeatureCollection featureCollection);

    @Keep
    private native void nativeSetGeoJsonString(String str);

    @Keep
    private native void nativeSetGeometry(Geometry geometry);

    @NonNull
    @Keep
    private native Feature[] querySourceFeatures(Object[] objArr);

    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public FeatureCollection getClusterChildren(@NonNull Feature feature) {
        checkThread();
        return FeatureCollection.fromFeatures(nativeGetClusterChildren(feature));
    }

    public int getClusterExpansionZoom(@NonNull Feature feature) {
        checkThread();
        return nativeGetClusterExpansionZoom(feature);
    }

    @NonNull
    public FeatureCollection getClusterLeaves(@NonNull Feature feature, long j, long j2) {
        checkThread();
        return FeatureCollection.fromFeatures(nativeGetClusterLeaves(feature, j, j2));
    }

    @Nullable
    public String getUri() {
        checkThread();
        return nativeGetUrl();
    }

    @Nullable
    public String getUrl() {
        checkThread();
        return nativeGetUrl();
    }

    @Keep
    public native void initialize(String str, Object obj);

    @NonNull
    @Keep
    public native String nativeGetUrl();

    @Keep
    public native void nativeSetUrl(String str);

    @NonNull
    public List<Feature> querySourceFeatures(@Nullable Expression expression) {
        checkThread();
        Feature[] querySourceFeatures = querySourceFeatures(expression != null ? expression.toArray() : null);
        return querySourceFeatures != null ? Arrays.asList(querySourceFeatures) : new ArrayList();
    }

    public void setGeoJson(Feature feature) {
        if (this.detached) {
            return;
        }
        checkThread();
        nativeSetFeature(feature);
    }

    public void setUri(@NonNull URI uri) {
        setUri(uri.toString());
    }

    @Deprecated
    public void setUrl(@NonNull URL url) {
        checkThread();
        setUrl(url.toExternalForm());
    }

    public GeoJsonSource(String str) {
        initialize(str, null);
        setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public void setUri(String str) {
        checkThread();
        nativeSetUrl(str);
    }

    @Deprecated
    public void setUrl(String str) {
        checkThread();
        nativeSetUrl(str);
    }

    public void setGeoJson(Geometry geometry) {
        if (this.detached) {
            return;
        }
        checkThread();
        nativeSetGeometry(geometry);
    }

    public GeoJsonSource(String str, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public void setGeoJson(@Nullable FeatureCollection featureCollection) {
        if (this.detached) {
            return;
        }
        checkThread();
        if (featureCollection != null && featureCollection.features() != null) {
            nativeSetFeatureCollection(FeatureCollection.fromFeatures(new ArrayList(featureCollection.features())));
        } else {
            nativeSetFeatureCollection(featureCollection);
        }
    }

    public GeoJsonSource(String str, @Nullable String str2) {
        if (str2 != null && !str2.startsWith("http")) {
            initialize(str, null);
            setGeoJson(str2);
            return;
        }
        throw new IllegalArgumentException("Expected a raw json body");
    }

    public GeoJsonSource(String str, @Nullable String str2, GeoJsonOptions geoJsonOptions) {
        if (str2 != null && !str2.startsWith("http") && !str2.startsWith("asset") && !str2.startsWith("file")) {
            initialize(str, geoJsonOptions);
            setGeoJson(str2);
            return;
        }
        throw new IllegalArgumentException("Expected a raw json body");
    }

    public void setGeoJson(String str) {
        if (this.detached) {
            return;
        }
        checkThread();
        nativeSetGeoJsonString(str);
    }

    @Deprecated
    public GeoJsonSource(String str, URL url) {
        initialize(str, null);
        nativeSetUrl(url.toExternalForm());
    }

    @Deprecated
    public GeoJsonSource(String str, URL url, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        nativeSetUrl(url.toExternalForm());
    }

    public GeoJsonSource(String str, URI uri) {
        initialize(str, null);
        nativeSetUrl(uri.toString());
    }

    public GeoJsonSource(String str, URI uri, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        nativeSetUrl(uri.toString());
    }

    public GeoJsonSource(String str, FeatureCollection featureCollection) {
        initialize(str, null);
        setGeoJson(featureCollection);
    }

    public GeoJsonSource(String str, FeatureCollection featureCollection, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(featureCollection);
    }

    public GeoJsonSource(String str, Feature feature) {
        initialize(str, null);
        setGeoJson(feature);
    }

    public GeoJsonSource(String str, Feature feature, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(feature);
    }

    public GeoJsonSource(String str, Geometry geometry) {
        initialize(str, null);
        setGeoJson(geometry);
    }

    public GeoJsonSource(String str, Geometry geometry, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(geometry);
    }
}
