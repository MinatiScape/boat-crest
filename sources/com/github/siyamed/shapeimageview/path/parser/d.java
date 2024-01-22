package com.github.siyamed.shapeimageview.path.parser;

import com.mappls.sdk.maps.style.layers.Property;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes9.dex */
public class d {
    public static final Float a(String str, XmlPullParser xmlPullParser, float f, float f2, float f3) {
        float f4;
        String c = c(str, xmlPullParser);
        if (c == null) {
            return null;
        }
        if (c.endsWith("px")) {
            return Float.valueOf(Float.parseFloat(c.substring(0, c.length() - 2)));
        }
        if (c.endsWith("pt")) {
            return Float.valueOf((Float.valueOf(c.substring(0, c.length() - 2)).floatValue() * f) / 72.0f);
        }
        if (c.endsWith("pc")) {
            return Float.valueOf((Float.valueOf(c.substring(0, c.length() - 2)).floatValue() * f) / 6.0f);
        }
        if (c.endsWith("cm")) {
            return Float.valueOf((Float.valueOf(c.substring(0, c.length() - 2)).floatValue() * f) / 2.54f);
        }
        if (c.endsWith("mm")) {
            return Float.valueOf((Float.valueOf(c.substring(0, c.length() - 2)).floatValue() * f) / 254.0f);
        }
        if (c.endsWith("in")) {
            return Float.valueOf(Float.valueOf(c.substring(0, c.length() - 2)).floatValue() * f);
        }
        if (c.endsWith("%")) {
            Float valueOf = Float.valueOf(c.substring(0, c.length() - 1));
            if (str.contains("x") || str.equals(Property.ICON_TEXT_FIT_WIDTH)) {
                f4 = f2 / 100.0f;
            } else {
                f4 = (str.contains(EllipticCurveJsonWebKey.Y_MEMBER_NAME) || str.equals(Property.ICON_TEXT_FIT_HEIGHT)) ? f3 / 100.0f : (f3 + f2) / 2.0f;
            }
            return Float.valueOf(valueOf.floatValue() * f4);
        }
        return Float.valueOf(c);
    }

    public static final String b(String str) {
        return str.replaceAll("\"", "&quot;").replaceAll("'", "&apos").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&", "&amp;");
    }

    public static final String c(String str, XmlPullParser xmlPullParser) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (xmlPullParser.getAttributeName(i).equals(str)) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }
}
