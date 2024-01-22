package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Nullable;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes11.dex */
public class h implements c<SymbolLayer> {
    public static final AtomicLong c = new AtomicLong(0);

    /* renamed from: a  reason: collision with root package name */
    public final String f13088a;
    public final String b;

    public h() {
        long incrementAndGet = c.incrementAndGet();
        this.f13088a = String.format("mappls-android-symbol-layer-%s", Long.valueOf(incrementAndGet));
        this.b = String.format("mappls-android-symbol-source-%s", Long.valueOf(incrementAndGet));
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
        return this.f13088a;
    }

    @Override // com.mappls.sdk.plugin.annotation.c
    /* renamed from: d */
    public SymbolLayer c() {
        return new SymbolLayer(this.f13088a, this.b);
    }
}
