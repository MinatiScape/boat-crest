package com.mappls.sdk.maps.style.sources;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.style.expressions.Expression;
import java.util.HashMap;
/* loaded from: classes11.dex */
public class GeoJsonOptions extends HashMap<String, Object> {
    @NonNull
    public GeoJsonOptions withBuffer(int i) {
        put("buffer", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public GeoJsonOptions withCluster(boolean z) {
        put("cluster", Boolean.valueOf(z));
        return this;
    }

    @NonNull
    public GeoJsonOptions withClusterMaxZoom(int i) {
        put("clusterMaxZoom", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public GeoJsonOptions withClusterProperty(String str, Expression expression, Expression expression2) {
        HashMap hashMap = containsKey("clusterProperties") ? (HashMap) get("clusterProperties") : new HashMap();
        hashMap.put(str, new Object[]{expression instanceof Expression.ExpressionLiteral ? ((Expression.ExpressionLiteral) expression).toValue() : expression.toArray(), expression2.toArray()});
        put("clusterProperties", hashMap);
        return this;
    }

    @NonNull
    public GeoJsonOptions withClusterRadius(int i) {
        put("clusterRadius", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public GeoJsonOptions withLineMetrics(boolean z) {
        put("lineMetrics", Boolean.valueOf(z));
        return this;
    }

    @NonNull
    public GeoJsonOptions withMaxZoom(int i) {
        put("maxzoom", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public GeoJsonOptions withMinZoom(int i) {
        put("minzoom", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public GeoJsonOptions withTolerance(float f) {
        put("tolerance", Float.valueOf(f));
        return this;
    }
}
