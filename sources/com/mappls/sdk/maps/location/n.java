package com.mappls.sdk.maps.location;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.LayoutPropertyValue;
import com.mappls.sdk.maps.style.layers.PaintPropertyValue;
import com.mappls.sdk.maps.style.layers.PropertyValue;
/* loaded from: classes11.dex */
public class n {
    public static PropertyValue<Float> a(Float f) {
        return new PaintPropertyValue("accuracy-radius", f);
    }

    public static PropertyValue<Expression> b(Expression expression) {
        return new PaintPropertyValue("accuracy-radius-border-color", expression);
    }

    public static PropertyValue<Expression> c(Expression expression) {
        return new PaintPropertyValue("accuracy-radius-color", expression);
    }

    public static PropertyValue<Double> d(Double d) {
        return new PaintPropertyValue("bearing", d);
    }

    public static PropertyValue<String> e(String str) {
        return new LayoutPropertyValue("bearing-image", str);
    }

    public static PropertyValue<Expression> f(Expression expression) {
        return new PaintPropertyValue("bearing-image-size", expression);
    }

    public static PropertyValue<Float> g(Float f) {
        return new PaintPropertyValue("image-tilt-displacement", f);
    }

    public static PropertyValue<Double[]> h(Double[] dArr) {
        return new PaintPropertyValue(FirebaseAnalytics.Param.LOCATION, dArr);
    }

    public static PropertyValue<Float> i(Float f) {
        return new PaintPropertyValue("perspective-compensation", f);
    }

    public static PropertyValue<String> j(String str) {
        return new LayoutPropertyValue("shadow-image", str);
    }

    public static PropertyValue<Expression> k(Expression expression) {
        return new PaintPropertyValue("shadow-image-size", expression);
    }

    public static PropertyValue<String> l(String str) {
        return new LayoutPropertyValue("top-image", str);
    }

    public static PropertyValue<Expression> m(Expression expression) {
        return new PaintPropertyValue("top-image-size", expression);
    }

    public static PropertyValue<String> n(String str) {
        return new LayoutPropertyValue("visibility", str);
    }
}
