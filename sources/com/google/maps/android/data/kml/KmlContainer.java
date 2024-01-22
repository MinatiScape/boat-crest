package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlay;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class KmlContainer {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, String> f11553a;
    public final HashMap<KmlPlacemark, Object> b;
    public final ArrayList<KmlContainer> c;
    public final HashMap<KmlGroundOverlay, GroundOverlay> d;
    public final HashMap<String, String> e;
    public HashMap<String, KmlStyle> f;
    public String g;

    public KmlContainer(HashMap<String, String> hashMap, HashMap<String, KmlStyle> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, HashMap<String, String> hashMap4, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap5, String str) {
        this.f11553a = hashMap;
        this.b = hashMap3;
        this.f = hashMap2;
        this.e = hashMap4;
        this.c = arrayList;
        this.d = hashMap5;
        this.g = str;
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> a() {
        return this.d;
    }

    public HashMap<KmlPlacemark, Object> b() {
        return this.b;
    }

    public HashMap<String, String> c() {
        return this.e;
    }

    public HashMap<String, KmlStyle> d() {
        return this.f;
    }

    public void e(KmlPlacemark kmlPlacemark, Object obj) {
        this.b.put(kmlPlacemark, obj);
    }

    public String getContainerId() {
        return this.g;
    }

    public Iterable<KmlContainer> getContainers() {
        return this.c;
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return this.d.keySet();
    }

    public Iterable<KmlPlacemark> getPlacemarks() {
        return this.b.keySet();
    }

    public Iterable<String> getProperties() {
        return this.f11553a.keySet();
    }

    public String getProperty(String str) {
        return this.f11553a.get(str);
    }

    public KmlStyle getStyle(String str) {
        return this.f.get(str);
    }

    public String getStyleIdFromMap(String str) {
        return this.e.get(str);
    }

    public boolean hasContainers() {
        return this.c.size() > 0;
    }

    public boolean hasPlacemarks() {
        return this.b.size() > 0;
    }

    public boolean hasProperties() {
        return this.f11553a.size() > 0;
    }

    public boolean hasProperty(String str) {
        return this.f11553a.containsKey(str);
    }

    public String toString() {
        return "Container{\n properties=" + this.f11553a + ",\n placemarks=" + this.b + ",\n containers=" + this.c + ",\n ground overlays=" + this.d + ",\n style maps=" + this.e + ",\n styles=" + this.f + "\n}\n";
    }
}
