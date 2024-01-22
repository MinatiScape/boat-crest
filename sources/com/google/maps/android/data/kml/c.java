package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes10.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public final XmlPullParser f11560a;
    public final HashMap<KmlPlacemark, Object> b = new HashMap<>();
    public final ArrayList<KmlContainer> c = new ArrayList<>();
    public final HashMap<String, KmlStyle> d = new HashMap<>();
    public final HashMap<String, String> e = new HashMap<>();
    public final HashMap<KmlGroundOverlay, GroundOverlay> f = new HashMap<>();

    public c(XmlPullParser xmlPullParser) {
        this.f11560a = xmlPullParser;
    }

    public static void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    public ArrayList<KmlContainer> a() {
        return this.c;
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> b() {
        return this.f;
    }

    public HashMap<KmlPlacemark, Object> c() {
        return this.b;
    }

    public HashMap<String, String> d() {
        return this.e;
    }

    public HashMap<String, KmlStyle> e() {
        return this.d;
    }

    public void f() throws XmlPullParserException, IOException {
        int eventType = this.f11560a.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                if (this.f11560a.getName().matches("altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|NetworkLink|NetworkLinkControl|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when")) {
                    g(this.f11560a);
                }
                if (this.f11560a.getName().matches("Folder|Document")) {
                    this.c.add(a.b(this.f11560a));
                }
                if (this.f11560a.getName().equals("Style")) {
                    KmlStyle e = d.e(this.f11560a);
                    this.d.put(e.f(), e);
                }
                if (this.f11560a.getName().equals("StyleMap")) {
                    this.e.putAll(d.f(this.f11560a));
                }
                if (this.f11560a.getName().equals("Placemark")) {
                    this.b.put(b.k(this.f11560a), null);
                }
                if (this.f11560a.getName().equals("GroundOverlay")) {
                    this.f.put(b.f(this.f11560a), null);
                }
            }
            eventType = this.f11560a.next();
        }
        this.d.put(null, new KmlStyle());
    }
}
