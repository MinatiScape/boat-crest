package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Nullable;
import com.mappls.sdk.maps.style.layers.LineLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
public class f implements c<LineLayer> {
    public static final AtomicLong c = new AtomicLong(0);

    /* renamed from: a  reason: collision with root package name */
    public final String f13087a;
    public final String b;

    public f() {
        long incrementAndGet = c.incrementAndGet();
        this.f13087a = String.format("mappls-android-line-layer-%s", Long.valueOf(incrementAndGet));
        this.b = String.format("mappls-android-line-source-%s", Long.valueOf(incrementAndGet));
    }

    @Override // com.mappls.sdk.plugin.annotation.c
    public GeoJsonSource a(@Nullable GeoJsonOptions geoJsonOptions) {
        return new GeoJsonSource(this.b, geoJsonOptions);
    }

    @Override // com.mappls.sdk.plugin.annotation.c
    public String a() {
        return this.b;
    }

    @Override // com.mappls.sdk.plugin.annotation.c
    public String b() {
        return this.f13087a;
    }

    @Override // com.mappls.sdk.plugin.annotation.c
    /* renamed from: d */
    public LineLayer c() {
        return new LineLayer(this.f13087a, this.b);
    }
}
