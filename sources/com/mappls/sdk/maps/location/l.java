package com.mappls.sdk.maps.location;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.o;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.style.expressions.Expression;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes11.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public int f12774a;
    public final MapplsMap b;
    public final com.mappls.sdk.maps.location.d c;
    public LocationComponentOptions d;
    public final OnRenderModeChangedListener e;
    public final boolean f;
    public boolean h;
    public k i;
    public m j;
    public boolean g = true;
    public final o.b<LatLng> k = new a();
    public final o.b<Float> l = new b();
    public final o.b<Float> m = new c();
    public final o.b<Float> n = new d();
    public final o.b<Float> o = new e();

    /* loaded from: classes11.dex */
    public class a implements o.b<LatLng> {
        public a() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(LatLng latLng) {
            l.this.j.n(latLng);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements o.b<Float> {
        public b() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            l.this.j.d(f);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements o.b<Float> {
        public c() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            l.this.j.f(f);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements o.b<Float> {
        public d() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            l.this.j.p(f);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements o.b<Float> {
        public e() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            l.this.j.i(f.floatValue(), l.this.d.pulseFadeEnabled().booleanValue() ? Float.valueOf(1.0f - ((f.floatValue() / 100.0f) * 3.0f)) : null);
        }
    }

    public l(MapplsMap mapplsMap, Style style, f fVar, com.mappls.sdk.maps.location.e eVar, com.mappls.sdk.maps.location.d dVar, @NonNull LocationComponentOptions locationComponentOptions, @NonNull OnRenderModeChangedListener onRenderModeChangedListener, boolean z) {
        this.b = mapplsMap;
        this.c = dVar;
        this.e = onRenderModeChangedListener;
        this.f = z;
        boolean enableStaleState = locationComponentOptions.enableStaleState();
        this.h = enableStaleState;
        if (z) {
            this.j = fVar.g();
        } else {
            this.j = fVar.h(eVar, enableStaleState);
        }
        l(style, locationComponentOptions);
    }

    public void c(boolean z) {
        this.j.b(z);
    }

    public void d(@NonNull LocationComponentOptions locationComponentOptions) {
        if (this.i.b(locationComponentOptions.layerAbove(), locationComponentOptions.layerBelow())) {
            this.j.o();
            this.j.q(this.i);
            if (this.g) {
                k();
            }
        }
        this.d = locationComponentOptions;
        t(locationComponentOptions);
        this.j.k(locationComponentOptions.accuracyAlpha(), locationComponentOptions.accuracyColor());
        u(locationComponentOptions);
        this.j.l(locationComponentOptions);
        h(locationComponentOptions);
        if (this.g) {
            return;
        }
        s();
    }

    @NonNull
    public final String e(@Nullable String str, @NonNull String str2) {
        if (str != null) {
            if (this.f) {
                Logger.e("Mbgl-LocationLayerController", str + " replacement ID provided for an unsupported specialized location layer");
                return str2;
            }
            return str;
        }
        return str2;
    }

    public void f(double d2) {
        if (this.f12774a != 8) {
            this.j.e(d2);
        }
    }

    public void g(double d2) {
        this.j.h(d2);
    }

    public final void h(LocationComponentOptions locationComponentOptions) {
        this.j.r(e(this.f12774a == 8 ? locationComponentOptions.gpsName() : locationComponentOptions.foregroundName(), "mappls-location-icon"), e(locationComponentOptions.foregroundStaleName(), "mappls-location-stale-icon"), e(locationComponentOptions.backgroundName(), "mappls-location-stroke-icon"), e(locationComponentOptions.backgroundStaleName(), "mappls-location-background-stale-icon"), e(locationComponentOptions.bearingName(), "mappls-location-bearing-icon"));
    }

    public Set<com.mappls.sdk.maps.location.a> i() {
        HashSet hashSet = new HashSet();
        hashSet.add(new com.mappls.sdk.maps.location.a(0, this.k));
        int i = this.f12774a;
        if (i == 8) {
            hashSet.add(new com.mappls.sdk.maps.location.a(2, this.l));
        } else if (i == 4) {
            hashSet.add(new com.mappls.sdk.maps.location.a(3, this.m));
        }
        int i2 = this.f12774a;
        if (i2 == 4 || i2 == 18) {
            hashSet.add(new com.mappls.sdk.maps.location.a(6, this.n));
        }
        if (this.d.pulseEnabled().booleanValue()) {
            hashSet.add(new com.mappls.sdk.maps.location.a(9, this.o));
        }
        return hashSet;
    }

    public int j() {
        return this.f12774a;
    }

    public void k() {
        this.g = true;
        this.j.hide();
    }

    public void l(Style style, LocationComponentOptions locationComponentOptions) {
        this.i = new k(style, locationComponentOptions.layerAbove(), locationComponentOptions.layerBelow());
        this.j.m(style);
        this.j.q(this.i);
        d(locationComponentOptions);
        if (this.g) {
            k();
        } else {
            s();
        }
    }

    public boolean m() {
        return this.f12774a == 4;
    }

    public boolean n() {
        return this.g;
    }

    public boolean o(@NonNull LatLng latLng) {
        return !this.b.queryRenderedFeatures(this.b.getProjection().toScreenLocation(latLng), LocationComponentConstants.BACKGROUND_LAYER, LocationComponentConstants.FOREGROUND_LAYER, LocationComponentConstants.BEARING_LAYER).isEmpty();
    }

    public void p(float f) {
        this.j.d(Float.valueOf(f));
    }

    public void q(boolean z) {
        this.h = z;
        this.j.j(z, this.f12774a);
    }

    public void r(int i) {
        if (this.f12774a == i) {
            return;
        }
        this.f12774a = i;
        t(this.d);
        h(this.d);
        if (!this.g) {
            s();
        }
        this.e.onRenderModeChanged(i);
    }

    public void s() {
        this.g = false;
        this.j.g(this.f12774a, this.h);
    }

    public final void t(LocationComponentOptions locationComponentOptions) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap b2 = locationComponentOptions.elevation() > 0.0f ? this.c.b(locationComponentOptions) : null;
        Bitmap a2 = this.c.a(locationComponentOptions.backgroundDrawable(), locationComponentOptions.backgroundTintColor());
        Bitmap a3 = this.c.a(locationComponentOptions.backgroundDrawableStale(), locationComponentOptions.backgroundStaleTintColor());
        Bitmap a4 = this.c.a(locationComponentOptions.bearingDrawable(), locationComponentOptions.bearingTintColor());
        Bitmap a5 = this.c.a(locationComponentOptions.foregroundDrawable(), locationComponentOptions.foregroundTintColor());
        Bitmap a6 = this.c.a(locationComponentOptions.foregroundDrawableStale(), locationComponentOptions.foregroundStaleTintColor());
        if (this.f12774a == 8) {
            Bitmap a7 = this.c.a(locationComponentOptions.gpsDrawable(), locationComponentOptions.foregroundTintColor());
            bitmap2 = this.c.a(locationComponentOptions.gpsStaleDrawable(), locationComponentOptions.foregroundStaleTintColor());
            bitmap = a7;
        } else {
            bitmap = a5;
            bitmap2 = a6;
        }
        this.j.a(this.f12774a, b2, a2, a3, a4, bitmap, bitmap2);
    }

    public final void u(@NonNull LocationComponentOptions locationComponentOptions) {
        this.j.c(Expression.interpolate(Expression.linear(), Expression.zoom(), Expression.stop(Double.valueOf(this.b.getMinZoomLevel()), Float.valueOf(locationComponentOptions.minZoomIconScale())), Expression.stop(Double.valueOf(this.b.getMaxZoomLevel()), Float.valueOf(locationComponentOptions.maxZoomIconScale()))));
    }
}
