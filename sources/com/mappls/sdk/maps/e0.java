package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import java.util.List;
/* loaded from: classes11.dex */
public class e0 implements MapView.OnDidFinishLoadingStyleListener {
    public MapplsMap h;
    public boolean i = false;
    public boolean j = true;
    public boolean k = true;
    public boolean l = true;
    public boolean m = true;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {
        public a() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            e0.this.w(style);
        }
    }

    public e0(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap) {
        this.h = mapplsMap;
        mapView.addOnDidFinishLoadingStyleListener(this);
    }

    public void b(boolean z) {
        this.k = z;
        x();
    }

    public void c(boolean z) {
        this.m = z;
        x();
    }

    public void d(boolean z) {
        this.j = z;
        x();
    }

    public void e(boolean z) {
        this.n = z;
        x();
    }

    public void f(boolean z) {
        this.o = z;
        x();
    }

    public void g(boolean z) {
        this.p = z;
        x();
    }

    public void h(boolean z) {
        this.q = z;
        x();
    }

    public void i(boolean z) {
        this.r = z;
        x();
    }

    public void j(boolean z) {
        this.l = z;
        x();
    }

    public boolean k() {
        return this.i;
    }

    public boolean l() {
        return this.k;
    }

    public boolean m() {
        return this.m;
    }

    public boolean n() {
        return this.j;
    }

    public boolean o() {
        return this.n;
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public void onDidFinishLoadingStyle() {
        u();
    }

    public boolean p() {
        return this.o;
    }

    public boolean q() {
        return this.p;
    }

    public boolean r() {
        return this.q;
    }

    public boolean s() {
        return this.r;
    }

    public boolean t() {
        return this.l;
    }

    public final void u() {
        x();
    }

    public void v(boolean z) {
        this.i = z;
        x();
    }

    public final void w(@NonNull Style style) {
        List<Layer> layers = style.getLayers();
        if (layers.size() > 0) {
            for (Layer layer : layers) {
                String id = layer.getId();
                id.hashCode();
                char c = 65535;
                switch (id.hashCode()) {
                    case -1418677347:
                        if (id.equals("Traffic_stopicon")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -376946244:
                        if (id.equals("Traffic_freeflow")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 488357337:
                        if (id.equals("Traffic_closure")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1002362512:
                        if (id.equals("Traffic_oth1")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1002362513:
                        if (id.equals("Traffic_oth2")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1002362514:
                        if (id.equals("Traffic_oth3")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1002362515:
                        if (id.equals("Traffic_oth4")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 1002362516:
                        if (id.equals("Traffic_oth5")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 1043448837:
                        if (id.equals("Traffic_nonfreeflow")) {
                            c = '\b';
                            break;
                        }
                        break;
                }
                String str = Property.VISIBLE;
                switch (c) {
                    case 0:
                        PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                        propertyValueArr[0] = PropertyFactory.visibility((t() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr);
                        break;
                    case 1:
                        PropertyValue<?>[] propertyValueArr2 = new PropertyValue[1];
                        propertyValueArr2[0] = PropertyFactory.visibility((m() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr2);
                        break;
                    case 2:
                        PropertyValue<?>[] propertyValueArr3 = new PropertyValue[1];
                        propertyValueArr3[0] = PropertyFactory.visibility((l() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr3);
                        break;
                    case 3:
                        PropertyValue<?>[] propertyValueArr4 = new PropertyValue[1];
                        propertyValueArr4[0] = PropertyFactory.visibility((o() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr4);
                        break;
                    case 4:
                        PropertyValue<?>[] propertyValueArr5 = new PropertyValue[1];
                        propertyValueArr5[0] = PropertyFactory.visibility((p() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr5);
                        break;
                    case 5:
                        PropertyValue<?>[] propertyValueArr6 = new PropertyValue[1];
                        propertyValueArr6[0] = PropertyFactory.visibility((q() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr6);
                        break;
                    case 6:
                        PropertyValue<?>[] propertyValueArr7 = new PropertyValue[1];
                        propertyValueArr7[0] = PropertyFactory.visibility((r() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr7);
                        break;
                    case 7:
                        PropertyValue<?>[] propertyValueArr8 = new PropertyValue[1];
                        propertyValueArr8[0] = PropertyFactory.visibility((s() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr8);
                        break;
                    case '\b':
                        PropertyValue<?>[] propertyValueArr9 = new PropertyValue[1];
                        propertyValueArr9[0] = PropertyFactory.visibility((n() && k()) ? "none" : "none");
                        layer.setProperties(propertyValueArr9);
                        break;
                }
            }
        }
    }

    public final void x() {
        this.h.getStyle(new a());
    }
}
