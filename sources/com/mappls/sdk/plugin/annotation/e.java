package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Nullable;
import com.mappls.sdk.maps.style.layers.FillLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
public class e implements c<FillLayer> {
    public static final AtomicLong c = new AtomicLong(0);

    /* renamed from: a  reason: collision with root package name */
    public final String f13086a;
    public final String b;

    public e() {
        long incrementAndGet = c.incrementAndGet();
        this.f13086a = String.format("mappls-android-fill-layer-%s", Long.valueOf(incrementAndGet));
        this.b = String.format("mappls-android-fill-source-%s", Long.valueOf(incrementAndGet));
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
        return this.f13086a;
    }

    @Override // com.mappls.sdk.plugin.annotation.c
    /* renamed from: d */
    public FillLayer c() {
        return new FillLayer(this.f13086a, this.b);
    }
}
