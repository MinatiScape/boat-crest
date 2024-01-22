package com.mappls.sdk.maps;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.annotations.InfoWindow;
import com.mappls.sdk.maps.annotations.Marker;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public final List<InfoWindow> f12730a = new ArrayList();
    @Nullable
    public MapplsMap.InfoWindowAdapter b;
    public boolean c;
    @Nullable
    public MapplsMap.OnInfoWindowClickListener d;
    @Nullable
    public MapplsMap.OnInfoWindowLongClickListener e;
    @Nullable
    public MapplsMap.OnInfoWindowCloseListener f;

    public void a(InfoWindow infoWindow) {
        this.f12730a.add(infoWindow);
    }

    @Nullable
    public MapplsMap.InfoWindowAdapter b() {
        return this.b;
    }

    @Nullable
    public MapplsMap.OnInfoWindowClickListener c() {
        return this.d;
    }

    @Nullable
    public MapplsMap.OnInfoWindowCloseListener d() {
        return this.f;
    }

    @Nullable
    public MapplsMap.OnInfoWindowLongClickListener e() {
        return this.e;
    }

    public boolean f() {
        return this.c;
    }

    public boolean g(@Nullable Marker marker) {
        return (marker == null || (TextUtils.isEmpty(marker.getTitle()) && TextUtils.isEmpty(marker.getSnippet()))) ? false : true;
    }

    public void h(boolean z) {
        this.c = z;
    }

    public void i(@Nullable MapplsMap.InfoWindowAdapter infoWindowAdapter) {
        this.b = infoWindowAdapter;
    }

    public void j(@Nullable MapplsMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.d = onInfoWindowClickListener;
    }

    public void k(@Nullable MapplsMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.f = onInfoWindowCloseListener;
    }

    public void l(@Nullable MapplsMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.e = onInfoWindowLongClickListener;
    }

    public void m() {
        if (this.f12730a.isEmpty()) {
            return;
        }
        for (InfoWindow infoWindow : this.f12730a) {
            infoWindow.update();
        }
    }
}
