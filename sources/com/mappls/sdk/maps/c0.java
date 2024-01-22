package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import java.util.List;
/* loaded from: classes11.dex */
public class c0 {

    /* renamed from: a  reason: collision with root package name */
    public MapplsMap f12687a;
    public boolean b = true;
    public boolean c = true;
    public boolean d = true;
    public boolean e = true;
    public boolean f = true;
    public boolean g = true;
    public boolean h = true;
    public boolean i = true;
    public boolean j = true;
    public boolean k = true;
    public boolean l = true;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {
        public a() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            c0.this.o(style);
        }
    }

    public c0(MapplsMap mapplsMap) {
        this.f12687a = mapplsMap;
    }

    public boolean b() {
        return this.h & h();
    }

    public boolean c() {
        return this.j & h();
    }

    public boolean d() {
        return this.b;
    }

    public boolean e() {
        return this.f & h();
    }

    public boolean f() {
        return this.c & h();
    }

    public boolean g() {
        return this.g & h();
    }

    public boolean h() {
        return this.l;
    }

    public boolean i() {
        return this.e & h();
    }

    public boolean j() {
        return this.d & h();
    }

    public boolean k() {
        return this.k & h();
    }

    public boolean l() {
        return this.i & h();
    }

    public void m(boolean z) {
        this.b = z;
        p();
    }

    public void n(boolean z) {
        this.l = z;
        p();
    }

    public final void o(@NonNull Style style) {
        List<Layer> layers = style.getLayers();
        if (layers.size() > 0) {
            for (Layer layer : layers) {
                String id = layer.getId();
                id.hashCode();
                char c = 65535;
                switch (id.hashCode()) {
                    case -1868425955:
                        if (id.equals("Taj_Mahal")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1671746793:
                        if (id.equals("Buddha_statue")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -868758264:
                        if (id.equals("statue_of_unity")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -785727559:
                        if (id.equals("red_fort")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -756356032:
                        if (id.equals("Akshardham")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 99340:
                        if (id.equals("dem")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 757421341:
                        if (id.equals("Lotus_Temple")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 1277660756:
                        if (id.equals("India Gate")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 1288874090:
                        if (id.equals("Gateway_of_India")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case 1367775329:
                        if (id.equals("Qutub_Minar")) {
                            c = '\t';
                            break;
                        }
                        break;
                }
                String str = Property.VISIBLE;
                switch (c) {
                    case 0:
                        PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                        if (!l()) {
                            str = "none";
                        }
                        propertyValueArr[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr);
                        break;
                    case 1:
                        PropertyValue<?>[] propertyValueArr2 = new PropertyValue[1];
                        if (!c()) {
                            str = "none";
                        }
                        propertyValueArr2[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr2);
                        break;
                    case 2:
                        PropertyValue<?>[] propertyValueArr3 = new PropertyValue[1];
                        if (!k()) {
                            str = "none";
                        }
                        propertyValueArr3[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr3);
                        break;
                    case 3:
                        PropertyValue<?>[] propertyValueArr4 = new PropertyValue[1];
                        if (!j()) {
                            str = "none";
                        }
                        propertyValueArr4[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr4);
                        break;
                    case 4:
                        PropertyValue<?>[] propertyValueArr5 = new PropertyValue[1];
                        if (!b()) {
                            str = "none";
                        }
                        propertyValueArr5[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr5);
                        break;
                    case 5:
                        PropertyValue<?>[] propertyValueArr6 = new PropertyValue[1];
                        if (!d()) {
                            str = "none";
                        }
                        propertyValueArr6[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr6);
                        break;
                    case 6:
                        PropertyValue<?>[] propertyValueArr7 = new PropertyValue[1];
                        if (!g()) {
                            str = "none";
                        }
                        propertyValueArr7[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr7);
                        break;
                    case 7:
                        PropertyValue<?>[] propertyValueArr8 = new PropertyValue[1];
                        if (!f()) {
                            str = "none";
                        }
                        propertyValueArr8[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr8);
                        break;
                    case '\b':
                        PropertyValue<?>[] propertyValueArr9 = new PropertyValue[1];
                        if (!e()) {
                            str = "none";
                        }
                        propertyValueArr9[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr9);
                        break;
                    case '\t':
                        PropertyValue<?>[] propertyValueArr10 = new PropertyValue[1];
                        if (!i()) {
                            str = "none";
                        }
                        propertyValueArr10[0] = PropertyFactory.visibility(str);
                        layer.setProperties(propertyValueArr10);
                        break;
                }
            }
        }
    }

    public final void p() {
        this.f12687a.getStyle(new a());
    }
}
