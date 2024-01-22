package com.github.siyamed.shapeimageview.path.parser;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.messaging.Constants;
import com.mappls.sdk.maps.style.layers.Property;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes9.dex */
public class SvgToPath {
    public static final String n = "SvgToPath";
    public static final Matrix o = new Matrix();
    public final XmlPullParser b;
    public float j;
    public float k;
    public Path l;

    /* renamed from: a  reason: collision with root package name */
    public HashMap<String, String> f7969a = new HashMap<>();
    public final RectF c = new RectF();
    public float d = 72.0f;
    public boolean e = false;
    public int f = 0;
    public boolean g = false;
    public final Deque<Path> h = new LinkedList();
    public final Deque<Matrix> i = new LinkedList();
    public PathInfo m = null;

    public SvgToPath(XmlPullParser xmlPullParser) {
        this.b = xmlPullParser;
    }

    public static PathInfo d(InputStream inputStream, boolean z, float f) {
        try {
            KXmlParser kXmlParser = new KXmlParser();
            SvgToPath svgToPath = new SvgToPath(kXmlParser);
            svgToPath.k(f);
            if (z) {
                kXmlParser.setInput(new InputStreamReader(inputStream));
                svgToPath.g();
            } else {
                a aVar = new a(inputStream);
                KXmlParser kXmlParser2 = new KXmlParser();
                kXmlParser2.setInput(new InputStreamReader(aVar.b()));
                b bVar = new b(kXmlParser2);
                bVar.c();
                svgToPath.f7969a = bVar.f7971a;
                kXmlParser.setInput(new InputStreamReader(aVar.b()));
                svgToPath.g();
            }
            return svgToPath.m;
        } catch (Exception e) {
            String str = n;
            Log.w(str, "Parse error: " + e);
            throw new RuntimeException(e);
        }
    }

    public static PathInfo getSVGFromInputStream(InputStream inputStream) {
        return d(inputStream, true, 72.0f);
    }

    public void a() {
        String name = this.b.getName();
        if (this.g) {
            if (name.equals("defs")) {
                this.g = false;
            }
        } else if (name.equals("svg")) {
            Path e = e();
            e.transform(f());
            this.m = new PathInfo(e, this.j, this.k);
        } else if (name.equals("g")) {
            if (this.e) {
                int i = this.f - 1;
                this.f = i;
                if (i == 0) {
                    this.e = false;
                }
            }
            Path e2 = e();
            e2.transform(f());
            this.l.addPath(e2);
        }
    }

    public final Float b(String str, XmlPullParser xmlPullParser) {
        return c(str, xmlPullParser, null);
    }

    public final Float c(String str, XmlPullParser xmlPullParser, Float f) {
        Float a2 = d.a(str, xmlPullParser, this.d, this.j, this.k);
        return a2 == null ? f : a2;
    }

    public final Path e() {
        Path pop = this.h.pop();
        this.l = this.h.peek();
        return pop;
    }

    public final Matrix f() {
        return this.i.pop();
    }

    public void g() throws XmlPullParserException, IOException {
        int eventType = this.b.getEventType();
        do {
            if (eventType == 2) {
                m();
            } else if (eventType == 3) {
                a();
            }
            eventType = this.b.next();
        } while (eventType != 1);
    }

    public final void h() {
        Path path = new Path();
        this.l = path;
        this.h.add(path);
    }

    public final void i(Matrix matrix) {
        if (matrix == null) {
            matrix = o;
        }
        this.i.push(matrix);
    }

    public final void j(XmlPullParser xmlPullParser) {
        String c = d.c("transform", xmlPullParser);
        this.i.push(c == null ? o : g.a(c));
    }

    public void k(float f) {
        this.d = f;
    }

    public final String l(XmlPullParser xmlPullParser) {
        String str = "";
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            str = str + HexStringBuilder.DEFAULT_SEPARATOR + xmlPullParser.getAttributeName(i) + "='" + xmlPullParser.getAttributeValue(i) + "'";
        }
        return str;
    }

    public void m() {
        ArrayList<Float> arrayList;
        String name = this.b.getName();
        if (this.g) {
            return;
        }
        if (name.equals("svg")) {
            this.j = Math.round(c(Property.ICON_TEXT_FIT_WIDTH, this.b, Float.valueOf(0.0f)).floatValue());
            this.k = Math.round(c(Property.ICON_TEXT_FIT_HEIGHT, this.b, Float.valueOf(0.0f)).floatValue());
            c a2 = c.a("viewBox", this.b);
            h();
            Matrix matrix = o;
            if (a2 != null && (arrayList = a2.f7973a) != null && arrayList.size() == 4) {
                float f = this.j;
                if (f >= 0.1f && this.k >= -0.1f) {
                    matrix.setScale(f / (a2.f7973a.get(2).floatValue() - a2.f7973a.get(0).floatValue()), this.k / (a2.f7973a.get(3).floatValue() - a2.f7973a.get(1).floatValue()));
                } else {
                    this.j = a2.f7973a.get(2).floatValue() - a2.f7973a.get(0).floatValue();
                    this.j = a2.f7973a.get(3).floatValue() - a2.f7973a.get(3).floatValue();
                }
            }
            i(matrix);
        } else if (name.equals("defs")) {
            this.g = true;
        } else if (name.equals(JsonWebKey.USE_PARAMETER)) {
            String c = d.c("xlink:href", this.b);
            String c2 = d.c("transform", this.b);
            String c3 = d.c("x", this.b);
            String c4 = d.c(EllipticCurveJsonWebKey.Y_MEMBER_NAME, this.b);
            if (c2 != null || c3 != null || c4 != null) {
                if (c2 != null) {
                    d.b(c2);
                }
                if (c3 != null || c4 != null) {
                    if (c3 != null) {
                        d.b(c3);
                    }
                    if (c4 != null) {
                        d.b(c4);
                    }
                }
            }
            for (int i = 0; i < this.b.getAttributeCount(); i++) {
                String attributeName = this.b.getAttributeName(i);
                if (!"x".equals(attributeName) && !EllipticCurveJsonWebKey.Y_MEMBER_NAME.equals(attributeName) && !Property.ICON_TEXT_FIT_WIDTH.equals(attributeName) && !Property.ICON_TEXT_FIT_HEIGHT.equals(attributeName) && !"xlink:href".equals(attributeName) && !"transform".equals(attributeName)) {
                    d.b(this.b.getAttributeValue(i));
                }
            }
            this.f7969a.get(c.substring(1));
        } else if (name.equals("g")) {
            if (this.e) {
                this.f++;
            }
            if ("none".equals(d.c(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, this.b)) && !this.e) {
                this.e = true;
                this.f = 1;
            }
            j(this.b);
            h();
        } else if (!this.e && name.equals("rect")) {
            Float c5 = c("x", this.b, Float.valueOf(0.0f));
            Float c6 = c(EllipticCurveJsonWebKey.Y_MEMBER_NAME, this.b, Float.valueOf(0.0f));
            Float b = b(Property.ICON_TEXT_FIT_WIDTH, this.b);
            Float b2 = b(Property.ICON_TEXT_FIT_HEIGHT, this.b);
            Float c7 = c("rx", this.b, Float.valueOf(0.0f));
            Float c8 = c("ry", this.b, Float.valueOf(0.0f));
            Path path = new Path();
            if (c7.floatValue() <= 0.0f && c8.floatValue() <= 0.0f) {
                path.addRect(c5.floatValue(), c6.floatValue(), c5.floatValue() + b.floatValue(), c6.floatValue() + b2.floatValue(), Path.Direction.CW);
            } else {
                this.c.set(c5.floatValue(), c6.floatValue(), c5.floatValue() + b.floatValue(), c6.floatValue() + b2.floatValue());
                path.addRoundRect(this.c, c7.floatValue(), c8.floatValue(), Path.Direction.CW);
            }
            j(this.b);
            path.transform(f());
            this.l.addPath(path);
        } else if (!this.e && name.equals("line")) {
            Float b3 = b("x1", this.b);
            Float b4 = b("x2", this.b);
            Float b5 = b("y1", this.b);
            Float b6 = b("y2", this.b);
            Path path2 = new Path();
            path2.moveTo(b3.floatValue(), b5.floatValue());
            path2.lineTo(b4.floatValue(), b6.floatValue());
            j(this.b);
            path2.transform(f());
            this.l.addPath(path2);
        } else if (!this.e && name.equals("circle")) {
            Float b7 = b("cx", this.b);
            Float b8 = b("cy", this.b);
            Float b9 = b(RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, this.b);
            if (b7 == null || b8 == null || b9 == null) {
                return;
            }
            Path path3 = new Path();
            path3.addCircle(b7.floatValue(), b8.floatValue(), b9.floatValue(), Path.Direction.CW);
            j(this.b);
            path3.transform(f());
            this.l.addPath(path3);
        } else if (!this.e && name.equals("ellipse")) {
            Float b10 = b("cx", this.b);
            Float b11 = b("cy", this.b);
            Float b12 = b("rx", this.b);
            Float b13 = b("ry", this.b);
            if (b10 == null || b11 == null || b12 == null || b13 == null) {
                return;
            }
            this.c.set(b10.floatValue() - b12.floatValue(), b11.floatValue() - b13.floatValue(), b10.floatValue() + b12.floatValue(), b11.floatValue() + b13.floatValue());
            Path path4 = new Path();
            path4.addOval(this.c, Path.Direction.CW);
            j(this.b);
            path4.transform(f());
            this.l.addPath(path4);
        } else if (!this.e && (name.equals("polygon") || name.equals("polyline"))) {
            c a3 = c.a("points", this.b);
            if (a3 != null) {
                Path path5 = new Path();
                ArrayList<Float> arrayList2 = a3.f7973a;
                if (arrayList2.size() > 1) {
                    path5.moveTo(arrayList2.get(0).floatValue(), arrayList2.get(1).floatValue());
                    for (int i2 = 2; i2 < arrayList2.size(); i2 += 2) {
                        path5.lineTo(arrayList2.get(i2).floatValue(), arrayList2.get(i2 + 1).floatValue());
                    }
                    if (name.equals("polygon")) {
                        path5.close();
                    }
                    j(this.b);
                    path5.transform(f());
                    this.l.addPath(path5);
                }
            }
        } else if (!this.e && name.equals("path")) {
            Path a4 = f.a(d.c("d", this.b));
            j(this.b);
            a4.transform(f());
            this.l.addPath(a4);
        } else if ((this.e || !name.equals("metadata")) && !this.e) {
            Log.d(n, String.format("Unrecognized tag: %s (%s)", name, l(this.b)));
        }
    }
}
