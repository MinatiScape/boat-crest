package com.google.maps.android.data.kml;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes10.dex */
public class a {
    public static KmlContainer a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap4 = new HashMap();
        HashMap hashMap5 = new HashMap();
        String attributeValue = xmlPullParser.getAttributeValue(null, "id") != null ? xmlPullParser.getAttributeValue(null, "id") : null;
        xmlPullParser.next();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(name)) {
                return new KmlContainer(hashMap, hashMap2, hashMap3, hashMap4, arrayList, hashMap5, attributeValue);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().matches("altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when")) {
                    c.g(xmlPullParser);
                } else if (xmlPullParser.getName().matches("Folder|Document")) {
                    arrayList.add(a(xmlPullParser));
                } else if (xmlPullParser.getName().matches("name|description|visibility|open|address|phoneNumber")) {
                    hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("StyleMap")) {
                    e(xmlPullParser, hashMap4);
                } else if (xmlPullParser.getName().equals("Style")) {
                    d(xmlPullParser, hashMap2);
                } else if (xmlPullParser.getName().equals("Placemark")) {
                    c(xmlPullParser, hashMap3);
                } else if (xmlPullParser.getName().equals("ExtendedData")) {
                    f(xmlPullParser, hashMap);
                } else if (xmlPullParser.getName().equals("GroundOverlay")) {
                    hashMap5.put(b.f(xmlPullParser), null);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static KmlContainer b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return a(xmlPullParser);
    }

    public static void c(XmlPullParser xmlPullParser, HashMap<KmlPlacemark, Object> hashMap) throws XmlPullParserException, IOException {
        hashMap.put(b.k(xmlPullParser), null);
    }

    public static void d(XmlPullParser xmlPullParser, HashMap<String, KmlStyle> hashMap) throws XmlPullParserException, IOException {
        if (xmlPullParser.getAttributeValue(null, "id") != null) {
            KmlStyle e = d.e(xmlPullParser);
            hashMap.put(e.f(), e);
        }
    }

    public static void e(XmlPullParser xmlPullParser, HashMap<String, String> hashMap) throws XmlPullParserException, IOException {
        hashMap.putAll(d.f(xmlPullParser));
    }

    public static void f(XmlPullParser xmlPullParser, HashMap<String, String> hashMap) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        String str = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("ExtendedData")) {
                return;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Data")) {
                    str = xmlPullParser.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else if (xmlPullParser.getName().equals("value") && str != null) {
                    hashMap.put(str, xmlPullParser.nextText());
                    str = null;
                }
            }
            eventType = xmlPullParser.next();
        }
    }
}
