package com.mappls.sdk.maps.location;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.maps.utils.ColorUtils;
/* loaded from: classes11.dex */
public class b implements m {

    /* renamed from: a  reason: collision with root package name */
    public Style f12750a;
    public final f b;
    public Layer c;
    @Nullable
    public LatLng d;
    public double e = 0.0d;
    public float f = 0.0f;

    public b(f fVar) {
        this.b = fVar;
    }

    @Override // com.mappls.sdk.maps.location.m
    public void a(int i, @Nullable Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6) {
        if (bitmap != null) {
            this.f12750a.addImage("mappls-location-shadow-icon", bitmap);
        } else {
            this.f12750a.removeImage("mappls-location-shadow-icon");
        }
        this.f12750a.addImage("mappls-location-icon", bitmap5);
        this.f12750a.addImage("mappls-location-stale-icon", bitmap6);
        if (i == 4) {
            this.f12750a.addImage("mappls-location-bearing-icon", BitmapUtils.mergeBitmap(bitmap4, bitmap2, (bitmap4.getWidth() - bitmap2.getWidth()) / 2.0f, (bitmap4.getHeight() - bitmap2.getHeight()) / 2.0f));
            this.f12750a.addImage("mappls-location-bearing-stale-icon", BitmapUtils.mergeBitmap(bitmap4, bitmap3, (bitmap4.getWidth() - bitmap3.getWidth()) / 2.0f, (bitmap4.getHeight() - bitmap3.getHeight()) / 2.0f));
            return;
        }
        this.f12750a.addImage("mappls-location-stroke-icon", bitmap2);
        this.f12750a.addImage("mappls-location-background-stale-icon", bitmap3);
        this.f12750a.addImage("mappls-location-bearing-icon", bitmap4);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void b(boolean z) {
    }

    @Override // com.mappls.sdk.maps.location.m
    public void c(Expression expression) {
        this.c.setProperties(n.k(expression), n.f(expression), n.m(expression));
    }

    @Override // com.mappls.sdk.maps.location.m
    public void d(Float f) {
        t(f.floatValue());
    }

    @Override // com.mappls.sdk.maps.location.m
    public void e(double d) {
    }

    @Override // com.mappls.sdk.maps.location.m
    public void f(Float f) {
        t(f.floatValue());
    }

    @Override // com.mappls.sdk.maps.location.m
    public void g(int i, boolean z) {
        s(i, z);
        v(true);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void h(double d) {
    }

    @Override // com.mappls.sdk.maps.location.m
    public void hide() {
        v(false);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void i(float f, @Nullable Float f2) {
    }

    @Override // com.mappls.sdk.maps.location.m
    public void j(boolean z, int i) {
        s(i, z);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void k(float f, int i) {
        float[] colorToRgbaArray = ColorUtils.colorToRgbaArray(i);
        colorToRgbaArray[3] = f;
        Expression rgba = Expression.rgba(Float.valueOf(colorToRgbaArray[0]), Float.valueOf(colorToRgbaArray[1]), Float.valueOf(colorToRgbaArray[2]), Float.valueOf(colorToRgbaArray[3]));
        this.c.setProperties(n.c(rgba), n.b(rgba));
    }

    @Override // com.mappls.sdk.maps.location.m
    public void l(LocationComponentOptions locationComponentOptions) {
    }

    @Override // com.mappls.sdk.maps.location.m
    public void m(Style style) {
        this.f12750a = style;
        this.c = this.b.c();
        LatLng latLng = this.d;
        if (latLng != null) {
            n(latLng);
        }
        t(this.e);
        p(Float.valueOf(this.f));
    }

    @Override // com.mappls.sdk.maps.location.m
    public void n(LatLng latLng) {
        u(latLng);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void o() {
        this.f12750a.removeLayer(this.c);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void p(Float f) {
        this.c.setProperties(n.a(f));
        this.f = f.floatValue();
    }

    @Override // com.mappls.sdk.maps.location.m
    public void q(k kVar) {
        kVar.a(this.c);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void r(String str, String str2, String str3, String str4, String str5) {
    }

    public final void s(int i, boolean z) {
        String str;
        String str2;
        String str3 = "mappls-location-shadow-icon";
        String str4 = "";
        if (i != 4) {
            if (i == 8) {
                str = z ? "mappls-location-stale-icon" : "mappls-location-icon";
                str3 = z ? "mappls-location-background-stale-icon" : "mappls-location-stroke-icon";
                p(Float.valueOf(0.0f));
            } else if (i != 18) {
                str3 = "";
                str = str3;
            } else {
                str = z ? "mappls-location-stale-icon" : "mappls-location-icon";
                str2 = z ? "mappls-location-background-stale-icon" : "mappls-location-stroke-icon";
            }
            this.c.setProperties(n.l(str4), n.e(str), n.j(str3));
        }
        str = z ? "mappls-location-stale-icon" : "mappls-location-icon";
        str2 = z ? "mappls-location-bearing-stale-icon" : "mappls-location-bearing-icon";
        String str5 = str2;
        str4 = str;
        str = str5;
        this.c.setProperties(n.l(str4), n.e(str), n.j(str3));
    }

    public final void t(double d) {
        this.c.setProperties(n.d(Double.valueOf(d)));
        this.e = d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void u(LatLng latLng) {
        this.c.setProperties(n.h(new Double[]{Double.valueOf(latLng.getLatitude()), Double.valueOf(latLng.getLongitude()), Double.valueOf(0.0d)}));
        this.d = latLng;
    }

    public final void v(boolean z) {
        Layer layer = this.c;
        PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
        propertyValueArr[0] = n.n(z ? Property.VISIBLE : "none");
        layer.setProperties(propertyValueArr);
    }
}
