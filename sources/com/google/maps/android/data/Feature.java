package com.google.maps.android.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
/* loaded from: classes10.dex */
public class Feature extends Observable {
    public final Map<String, String> h;
    public Geometry i;
    public String mId;

    public Feature(Geometry geometry, String str, Map<String, String> map) {
        this.i = geometry;
        this.mId = str;
        if (map == null) {
            this.h = new HashMap();
        } else {
            this.h = map;
        }
    }

    public Geometry getGeometry() {
        return this.i;
    }

    public String getId() {
        return this.mId;
    }

    public Iterable getProperties() {
        return this.h.entrySet();
    }

    public String getProperty(String str) {
        return this.h.get(str);
    }

    public Iterable<String> getPropertyKeys() {
        return this.h.keySet();
    }

    public boolean hasGeometry() {
        return this.i != null;
    }

    public boolean hasProperties() {
        return this.h.size() > 0;
    }

    public boolean hasProperty(String str) {
        return this.h.containsKey(str);
    }

    public String removeProperty(String str) {
        return this.h.remove(str);
    }

    public void setGeometry(Geometry geometry) {
        this.i = geometry;
    }

    public String setProperty(String str, String str2) {
        return this.h.put(str, str2);
    }
}
