package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class KmlTrack extends KmlLineString {
    public final ArrayList<Long> c;
    public final HashMap<String, String> d;

    public KmlTrack(ArrayList<LatLng> arrayList, ArrayList<Double> arrayList2, ArrayList<Long> arrayList3, HashMap<String, String> hashMap) {
        super(arrayList, arrayList2);
        this.c = arrayList3;
        this.d = hashMap;
    }

    public HashMap<String, String> getProperties() {
        return this.d;
    }

    public ArrayList<Long> getTimestamps() {
        return this.c;
    }
}
