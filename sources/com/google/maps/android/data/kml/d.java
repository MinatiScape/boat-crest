package com.google.maps.android.data.kml;

import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.maps.style.layers.Property;
import java.io.IOException;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes10.dex */
public class d {
    public static void a(XmlPullParser xmlPullParser, KmlStyle kmlStyle) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("BalloonStyle")) {
                return;
            }
            if (eventType == 2 && xmlPullParser.getName().equals("text")) {
                kmlStyle.n(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
    }

    public static void b(XmlPullParser xmlPullParser, KmlStyle kmlStyle) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("IconStyle")) {
                return;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("heading")) {
                    kmlStyle.i(Float.parseFloat(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals("Icon")) {
                    h(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("hotSpot")) {
                    g(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("scale")) {
                    kmlStyle.l(Double.parseDouble(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals("color")) {
                    kmlStyle.p(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("colorMode")) {
                    kmlStyle.k(xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static void c(XmlPullParser xmlPullParser, KmlStyle kmlStyle) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("LineStyle")) {
                return;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("color")) {
                    kmlStyle.r(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals(Property.ICON_TEXT_FIT_WIDTH)) {
                    kmlStyle.u(Float.valueOf(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals("colorMode")) {
                    kmlStyle.o(xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static void d(XmlPullParser xmlPullParser, KmlStyle kmlStyle) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("PolyStyle")) {
                return;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("color")) {
                    kmlStyle.h(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("outline")) {
                    kmlStyle.q(KmlBoolean.parseBoolean(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals("fill")) {
                    kmlStyle.setFill(KmlBoolean.parseBoolean(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals("colorMode")) {
                    kmlStyle.s(xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static KmlStyle e(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        KmlStyle kmlStyle = new KmlStyle();
        i(xmlPullParser.getAttributeValue(null, "id"), kmlStyle);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Style")) {
                return kmlStyle;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("IconStyle")) {
                    b(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("LineStyle")) {
                    c(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("PolyStyle")) {
                    d(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("BalloonStyle")) {
                    a(xmlPullParser, kmlStyle);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static HashMap<String, String> f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        String str = MqttTopic.MULTI_LEVEL_WILDCARD + xmlPullParser.getAttributeValue(null, "id");
        int eventType = xmlPullParser.getEventType();
        boolean z = false;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("StyleMap")) {
                return hashMap;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals(Constants.KEY_KEY) && xmlPullParser.nextText().equals(Constants.PRIORITY_NORMAL)) {
                    z = true;
                } else if (xmlPullParser.getName().equals("styleUrl") && z) {
                    hashMap.put(str, xmlPullParser.nextText());
                    z = false;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public static void g(XmlPullParser xmlPullParser, KmlStyle kmlStyle) throws XmlPullParserException {
        if (xmlPullParser.isEmptyElementTag()) {
            return;
        }
        kmlStyle.j(Float.parseFloat(xmlPullParser.getAttributeValue(null, "x")), Float.parseFloat(xmlPullParser.getAttributeValue(null, EllipticCurveJsonWebKey.Y_MEMBER_NAME)), xmlPullParser.getAttributeValue(null, "xunits"), xmlPullParser.getAttributeValue(null, "yunits"));
    }

    public static void h(XmlPullParser xmlPullParser, KmlStyle kmlStyle) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Icon")) {
                return;
            }
            if (eventType == 2 && xmlPullParser.getName().equals("href")) {
                kmlStyle.m(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
    }

    public static void i(String str, KmlStyle kmlStyle) {
        if (str != null) {
            kmlStyle.t(MqttTopic.MULTI_LEVEL_WILDCARD + str);
        }
    }
}
