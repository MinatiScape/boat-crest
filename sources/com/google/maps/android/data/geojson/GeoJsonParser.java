package com.google.maps.android.data.geojson;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.kml.KmlPolygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class GeoJsonParser {

    /* renamed from: a  reason: collision with root package name */
    public final JSONObject f11550a;
    public final ArrayList<GeoJsonFeature> b = new ArrayList<>();
    public LatLngBounds c = null;

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final LatLng f11551a;
        public final Double b;

        public a(LatLng latLng, Double d) {
            this.f11551a = latLng;
            this.b = d;
        }
    }

    public GeoJsonParser(JSONObject jSONObject) {
        this.f11550a = jSONObject;
        p();
    }

    public static Geometry a(String str, JSONArray jSONArray) throws JSONException {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2116761119:
                if (str.equals("MultiPolygon")) {
                    c = 0;
                    break;
                }
                break;
            case -1065891849:
                if (str.equals("MultiPoint")) {
                    c = 1;
                    break;
                }
                break;
            case -627102946:
                if (str.equals("MultiLineString")) {
                    c = 2;
                    break;
                }
                break;
            case 77292912:
                if (str.equals("Point")) {
                    c = 3;
                    break;
                }
                break;
            case 1267133722:
                if (str.equals(KmlPolygon.GEOMETRY_TYPE)) {
                    c = 4;
                    break;
                }
                break;
            case 1806700869:
                if (str.equals("LineString")) {
                    c = 5;
                    break;
                }
                break;
            case 1950410960:
                if (str.equals("GeometryCollection")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return f(jSONArray);
            case 1:
                return e(jSONArray);
            case 2:
                return d(jSONArray);
            case 3:
                return g(jSONArray);
            case 4:
                return h(jSONArray);
            case 5:
                return c(jSONArray);
            case 6:
                return b(jSONArray);
            default:
                return null;
        }
    }

    public static GeoJsonGeometryCollection b(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Geometry parseGeometry = parseGeometry(jSONArray.getJSONObject(i));
            if (parseGeometry != null) {
                arrayList.add(parseGeometry);
            }
        }
        return new GeoJsonGeometryCollection(arrayList);
    }

    public static GeoJsonLineString c(JSONArray jSONArray) throws JSONException {
        ArrayList<a> l = l(jSONArray);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<a> it = l.iterator();
        while (it.hasNext()) {
            a next = it.next();
            arrayList.add(next.f11551a);
            Double d = next.b;
            if (d != null) {
                arrayList2.add(d);
            }
        }
        return new GeoJsonLineString(arrayList, arrayList2);
    }

    public static GeoJsonMultiLineString d(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(c(jSONArray.getJSONArray(i)));
        }
        return new GeoJsonMultiLineString(arrayList);
    }

    public static GeoJsonMultiPoint e(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(g(jSONArray.getJSONArray(i)));
        }
        return new GeoJsonMultiPoint(arrayList);
    }

    public static GeoJsonMultiPolygon f(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(h(jSONArray.getJSONArray(i)));
        }
        return new GeoJsonMultiPolygon(arrayList);
    }

    public static GeoJsonPoint g(JSONArray jSONArray) throws JSONException {
        a k = k(jSONArray);
        return new GeoJsonPoint(k.f11551a, k.b);
    }

    public static GeoJsonPolygon h(JSONArray jSONArray) throws JSONException {
        return new GeoJsonPolygon(m(jSONArray));
    }

    public static boolean i(String str) {
        return str.matches("Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|GeometryCollection");
    }

    public static LatLngBounds j(JSONArray jSONArray) throws JSONException {
        return new LatLngBounds(new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0)), new LatLng(jSONArray.getDouble(3), jSONArray.getDouble(2)));
    }

    public static a k(JSONArray jSONArray) throws JSONException {
        return new a(new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0)), jSONArray.length() < 3 ? null : Double.valueOf(jSONArray.getDouble(2)));
    }

    public static ArrayList<a> l(JSONArray jSONArray) throws JSONException {
        ArrayList<a> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(k(jSONArray.getJSONArray(i)));
        }
        return arrayList;
    }

    public static ArrayList<ArrayList<LatLng>> m(JSONArray jSONArray) throws JSONException {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            ArrayList<a> l = l(jSONArray.getJSONArray(i));
            ArrayList<LatLng> arrayList2 = new ArrayList<>();
            Iterator<a> it = l.iterator();
            while (it.hasNext()) {
                arrayList2.add(it.next().f11551a);
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public static GeoJsonFeature n(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String string = jSONObject.has("id") ? jSONObject.getString("id") : null;
            LatLngBounds j = jSONObject.has("bbox") ? j(jSONObject.getJSONArray("bbox")) : null;
            Geometry parseGeometry = (!jSONObject.has("geometry") || jSONObject.isNull("geometry")) ? null : parseGeometry(jSONObject.getJSONObject("geometry"));
            if (jSONObject.has("properties") && !jSONObject.isNull("properties")) {
                hashMap = r(jSONObject.getJSONObject("properties"));
            }
            return new GeoJsonFeature(parseGeometry, string, hashMap, j);
        } catch (JSONException unused) {
            Log.w("GeoJsonParser", "Feature could not be successfully parsed " + jSONObject.toString());
            return null;
        }
    }

    public static Geometry parseGeometry(JSONObject jSONObject) {
        String string;
        JSONArray jSONArray;
        try {
            string = jSONObject.getString("type");
        } catch (JSONException unused) {
        }
        if (string.equals("GeometryCollection")) {
            jSONArray = jSONObject.getJSONArray("geometries");
        } else {
            if (i(string)) {
                jSONArray = jSONObject.getJSONArray("coordinates");
            }
            return null;
        }
        return a(string, jSONArray);
    }

    public static GeoJsonFeature q(JSONObject jSONObject) {
        Geometry parseGeometry = parseGeometry(jSONObject);
        if (parseGeometry != null) {
            return new GeoJsonFeature(parseGeometry, null, new HashMap(), null);
        }
        Log.w("GeoJsonParser", "Geometry could not be parsed");
        return null;
    }

    public static HashMap<String, String> r(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.isNull(next) ? null : jSONObject.getString(next));
        }
        return hashMap;
    }

    public LatLngBounds getBoundingBox() {
        return this.c;
    }

    public ArrayList<GeoJsonFeature> getFeatures() {
        return this.b;
    }

    public final ArrayList<GeoJsonFeature> o(JSONObject jSONObject) {
        ArrayList<GeoJsonFeature> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("features");
            if (jSONObject.has("bbox")) {
                this.c = j(jSONObject.getJSONArray("bbox"));
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.getString("type").equals("Feature")) {
                        GeoJsonFeature n = n(jSONObject2);
                        if (n != null) {
                            arrayList.add(n);
                        } else {
                            Log.w("GeoJsonParser", "Index of Feature in Feature Collection that could not be created: " + i);
                        }
                    }
                } catch (JSONException unused) {
                    Log.w("GeoJsonParser", "Index of Feature in Feature Collection that could not be created: " + i);
                }
            }
            return arrayList;
        } catch (JSONException unused2) {
            Log.w("GeoJsonParser", "Feature Collection could not be created.");
            return arrayList;
        }
    }

    public final void p() {
        try {
            String string = this.f11550a.getString("type");
            if (string.equals("Feature")) {
                GeoJsonFeature n = n(this.f11550a);
                if (n != null) {
                    this.b.add(n);
                }
            } else if (string.equals("FeatureCollection")) {
                this.b.addAll(o(this.f11550a));
            } else if (i(string)) {
                GeoJsonFeature q = q(this.f11550a);
                if (q != null) {
                    this.b.add(q);
                }
            } else {
                Log.w("GeoJsonParser", "GeoJSON file could not be parsed.");
            }
        } catch (JSONException unused) {
            Log.w("GeoJsonParser", "GeoJSON file could not be parsed.");
        }
    }
}
