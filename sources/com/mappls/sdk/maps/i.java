package com.mappls.sdk.maps;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.annotations.Icon;
import com.mappls.sdk.maps.annotations.IconFactory;
import com.mappls.sdk.maps.annotations.Marker;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Icon, Integer> f12729a = new HashMap();
    public s b;
    public int c;
    public int d;

    public i(s sVar) {
        this.b = sVar;
    }

    public final void a(@NonNull Icon icon) {
        b(icon, true);
    }

    public final void b(@NonNull Icon icon, boolean z) {
        if (!this.f12729a.keySet().contains(icon)) {
            this.f12729a.put(icon, 1);
            if (z) {
                i(icon);
                return;
            }
            return;
        }
        Map<Icon, Integer> map = this.f12729a;
        map.put(icon, Integer.valueOf(map.get(icon).intValue() + 1));
    }

    public void c(@NonNull Marker marker, @NonNull MapplsMap mapplsMap) {
        Icon icon = marker.getIcon();
        if (icon == null) {
            icon = h(marker);
        }
        a(icon);
        m(marker, mapplsMap, icon);
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.c;
    }

    public int f(@NonNull Icon icon) {
        return (int) (this.b.v(icon.getId()) * this.b.getPixelRatio());
    }

    public void g(@NonNull Icon icon) {
        Integer num = this.f12729a.get(icon);
        if (num != null) {
            Integer valueOf = Integer.valueOf(num.intValue() - 1);
            if (valueOf.intValue() == 0) {
                l(icon);
            } else {
                q(icon, valueOf.intValue());
            }
        }
    }

    public final Icon h(Marker marker) {
        Icon defaultMarker = IconFactory.getInstance(Mappls.getApplicationContext()).defaultMarker();
        Bitmap bitmap = defaultMarker.getBitmap();
        n(bitmap.getWidth(), bitmap.getHeight() / 2);
        marker.setIcon(defaultMarker);
        return defaultMarker;
    }

    public final void i(Icon icon) {
        Bitmap bitmap = icon.getBitmap();
        this.b.g0(icon.getId(), bitmap.getWidth(), bitmap.getHeight(), icon.getScale(), icon.toBytes());
    }

    public Icon j(@NonNull Marker marker) {
        Icon icon = marker.getIcon();
        if (icon == null) {
            icon = h(marker);
        } else {
            p(icon);
        }
        a(icon);
        return icon;
    }

    public void k() {
        for (Icon icon : this.f12729a.keySet()) {
            i(icon);
        }
    }

    public final void l(Icon icon) {
        this.b.r0(icon.getId());
        this.f12729a.remove(icon);
    }

    public final void m(Marker marker, @NonNull MapplsMap mapplsMap, @NonNull Icon icon) {
        Marker marker2 = marker.getId() != -1 ? (Marker) mapplsMap.getAnnotation(marker.getId()) : null;
        if (marker2 == null || marker2.getIcon() == null || marker2.getIcon() != marker.getIcon()) {
            marker.setTopOffsetPixels(f(icon));
        }
    }

    public final void n(int i, int i2) {
        if (i > this.c) {
            this.c = i;
        }
        if (i2 > this.d) {
            this.d = i2;
        }
    }

    public final void o(Bitmap bitmap) {
        n(bitmap.getWidth(), bitmap.getHeight());
    }

    public final void p(Icon icon) {
        o(icon.getBitmap());
    }

    public final void q(Icon icon, int i) {
        this.f12729a.put(icon, Integer.valueOf(i));
    }
}
