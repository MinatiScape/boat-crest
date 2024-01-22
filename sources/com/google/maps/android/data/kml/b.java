package com.google.maps.android.data.kml;

import androidx.constraintlayout.motion.widget.Key;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.maps.android.data.Geometry;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes10.dex */
public class b {

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final LatLng f11559a;
        public final Double b;

        public a(LatLng latLng, Double d) {
            this.f11559a = latLng;
            this.b = d;
        }
    }

    public static a a(String str) {
        return b(str, Constants.SEPARATOR_COMMA);
    }

    public static a b(String str, String str2) {
        String[] split = str.split(str2);
        return new a(new LatLng(Double.parseDouble(split[1]), Double.parseDouble(split[0])), split.length > 2 ? Double.valueOf(Double.parseDouble(split[2])) : null);
    }

    public static ArrayList<a> c(String str) {
        ArrayList<a> arrayList = new ArrayList<>();
        for (String str2 : str.trim().split("(\\s+)")) {
            arrayList.add(a(str2));
        }
        return arrayList;
    }

    public static ArrayList<LatLng> d(String str) {
        ArrayList<a> c = c(str);
        ArrayList<LatLng> arrayList = new ArrayList<>();
        Iterator<a> it = c.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().f11559a);
        }
        return arrayList;
    }

    public static Geometry e(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(str)) {
                return null;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Point")) {
                    return l(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("LineString")) {
                    return h(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("Track")) {
                    return n(xmlPullParser);
                }
                if (xmlPullParser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                    return m(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("MultiGeometry")) {
                    return i(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("MultiTrack")) {
                    return j(xmlPullParser);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static KmlGroundOverlay f(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        int eventType = xmlPullParser.getEventType();
        String str = null;
        float f = 0.0f;
        int i = 1;
        float f2 = 0.0f;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("GroundOverlay")) {
                return new KmlGroundOverlay(str, g((Double) hashMap2.get("north"), (Double) hashMap2.get("south"), (Double) hashMap2.get("east"), (Double) hashMap2.get("west")), f2, i, hashMap, f);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Icon")) {
                    str = o(xmlPullParser);
                } else if (xmlPullParser.getName().equals("drawOrder")) {
                    f2 = Float.parseFloat(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("visibility")) {
                    i = Integer.parseInt(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("ExtendedData")) {
                    hashMap.putAll(q(xmlPullParser));
                } else if (xmlPullParser.getName().equals(Key.ROTATION)) {
                    f = p(xmlPullParser);
                } else if (!xmlPullParser.getName().matches("name|description|drawOrder|visibility|open|address|phoneNumber") && !xmlPullParser.getName().equals("color")) {
                    if (xmlPullParser.getName().matches("north|south|east|west")) {
                        hashMap2.put(xmlPullParser.getName(), Double.valueOf(Double.parseDouble(xmlPullParser.nextText())));
                    }
                } else {
                    hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static LatLngBounds g(Double d, Double d2, Double d3, Double d4) {
        return new LatLngBounds(new LatLng(d2.doubleValue(), d4.doubleValue()), new LatLng(d.doubleValue(), d3.doubleValue()));
    }

    public static KmlLineString h(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("LineString")) {
                return new KmlLineString(arrayList, arrayList2);
            }
            if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                for (a aVar : c(xmlPullParser.nextText())) {
                    arrayList.add(aVar.f11559a);
                    Double d = aVar.b;
                    if (d != null) {
                        arrayList2.add(d);
                    }
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static KmlMultiGeometry i(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int next = xmlPullParser.next();
        while (true) {
            if (next == 3 && xmlPullParser.getName().equals("MultiGeometry")) {
                return new KmlMultiGeometry(arrayList);
            }
            if (next == 2 && xmlPullParser.getName().matches("Point|LineString|Polygon|MultiGeometry|Track|MultiTrack")) {
                arrayList.add(e(xmlPullParser, xmlPullParser.getName()));
            }
            next = xmlPullParser.next();
        }
    }

    public static KmlMultiTrack j(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int next = xmlPullParser.next();
        while (true) {
            if (next == 3 && xmlPullParser.getName().equals("MultiTrack")) {
                return new KmlMultiTrack(arrayList);
            }
            if (next == 2 && xmlPullParser.getName().matches("Track")) {
                arrayList.add(n(xmlPullParser));
            }
            next = xmlPullParser.next();
        }
    }

    public static KmlPlacemark k(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        Geometry geometry = null;
        String str = null;
        KmlStyle kmlStyle = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Placemark")) {
                return new KmlPlacemark(geometry, str, kmlStyle, hashMap);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("styleUrl")) {
                    str = xmlPullParser.nextText();
                } else if (xmlPullParser.getName().matches("Point|LineString|Polygon|MultiGeometry|Track|MultiTrack")) {
                    geometry = e(xmlPullParser, xmlPullParser.getName());
                } else if (xmlPullParser.getName().matches("name|description|drawOrder|visibility|open|address|phoneNumber")) {
                    hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("ExtendedData")) {
                    hashMap.putAll(q(xmlPullParser));
                } else if (xmlPullParser.getName().equals("Style")) {
                    kmlStyle = d.e(xmlPullParser);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static KmlPoint l(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        a aVar = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Point")) {
                return new KmlPoint(aVar.f11559a, aVar.b);
            }
            if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                aVar = a(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
    }

    public static KmlPolygon m(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        boolean z = false;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                return new KmlPolygon(arrayList, arrayList2);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().matches("outerBoundaryIs|innerBoundaryIs")) {
                    z = xmlPullParser.getName().equals("outerBoundaryIs");
                } else if (xmlPullParser.getName().equals("coordinates")) {
                    if (z) {
                        arrayList = d(xmlPullParser.nextText());
                    } else {
                        arrayList2.add(d(xmlPullParser.nextText()));
                    }
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static KmlTrack n(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Track")) {
                return new KmlTrack(arrayList, arrayList2, arrayList3, hashMap);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("coord")) {
                    a b = b(xmlPullParser.nextText(), HexStringBuilder.DEFAULT_SEPARATOR);
                    arrayList.add(b.f11559a);
                    Double d = b.b;
                    if (d != null) {
                        arrayList2.add(d);
                    }
                } else if (xmlPullParser.getName().equals("when")) {
                    try {
                        arrayList3.add(Long.valueOf(simpleDateFormat.parse(xmlPullParser.nextText()).getTime()));
                    } catch (ParseException e) {
                        throw new XmlPullParserException("Invalid date", xmlPullParser, e);
                    }
                } else if (xmlPullParser.getName().equals("ExtendedData")) {
                    hashMap.putAll(q(xmlPullParser));
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static String o(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Icon")) {
                return null;
            }
            if (eventType == 2 && xmlPullParser.getName().equals("href")) {
                return xmlPullParser.nextText();
            }
            eventType = xmlPullParser.next();
        }
    }

    public static float p(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        return -Float.parseFloat(xmlPullParser.nextText());
    }

    public static HashMap<String, String> q(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        int eventType = xmlPullParser.getEventType();
        String str = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("ExtendedData")) {
                return hashMap;
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
