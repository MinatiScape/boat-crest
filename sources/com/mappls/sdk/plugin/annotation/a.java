package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Nullable;
import com.mappls.sdk.maps.style.layers.CircleLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
public class a implements c<CircleLayer> {
    public static final AtomicLong c = new AtomicLong(0);

    /* renamed from: a  reason: collision with root package name */
    public final String f13083a;
    public final String b;

    public a() {
        long incrementAndGet = c.incrementAndGet();
        this.f13083a = String.format("mappls-android-circle-layer-%s", Long.valueOf(incrementAndGet));
        this.b = String.format("mappls-android-circle-source-%s", Long.valueOf(incrementAndGet));
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
        return this.f13083a;
    }

    @Override // com.mappls.sdk.plugin.annotation.c
    /* renamed from: d */
    public CircleLayer c() {
        return new CircleLayer(this.f13083a, this.b);
    }
}
