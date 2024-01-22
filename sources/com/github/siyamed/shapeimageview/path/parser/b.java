package com.github.siyamed.shapeimageview.path.parser;

import android.util.Log;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes9.dex */
public class b {
    public static final String d = SvgToPath.n;

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, String> f7971a = new HashMap<>();
    public final Stack<a> b = new Stack<>();
    public final XmlPullParser c;

    /* loaded from: classes9.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f7972a;
        public int b = 0;
        public final StringBuilder c = new StringBuilder();

        public a(b bVar, String str) {
            this.f7972a = str;
        }
    }

    public b(XmlPullParser xmlPullParser) {
        this.c = xmlPullParser;
    }

    public final void a(StringBuilder sb, String str, XmlPullParser xmlPullParser) {
        sb.append("<");
        sb.append(str);
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(xmlPullParser.getAttributeName(i));
            sb.append("='");
            sb.append(d.b(xmlPullParser.getAttributeValue(i)));
            sb.append("'");
        }
        sb.append(">");
    }

    public void b() {
        String name = this.c.getName();
        if (this.b.size() > 0) {
            a lastElement = this.b.lastElement();
            lastElement.c.append("</");
            lastElement.c.append(name);
            lastElement.c.append(">");
            int i = lastElement.b - 1;
            lastElement.b = i;
            if (i == 0) {
                String sb = lastElement.c.toString();
                this.f7971a.put(lastElement.f7972a, sb);
                this.b.pop();
                if (this.b.size() > 0) {
                    this.b.lastElement().c.append(sb);
                }
                Log.w(d, sb);
            }
        }
    }

    public void c() throws XmlPullParserException, IOException {
        int eventType = this.c.getEventType();
        do {
            if (eventType != 0 && eventType != 1) {
                if (eventType == 2) {
                    d();
                } else if (eventType == 3) {
                    b();
                }
            }
            eventType = this.c.next();
        } while (eventType != 1);
    }

    public void d() {
        String name = this.c.getName();
        String c = d.c("id", this.c);
        if (c != null) {
            this.b.push(new a(this, c));
        }
        if (this.b.size() > 0) {
            a lastElement = this.b.lastElement();
            lastElement.b++;
            a(lastElement.c, name, this.c);
        }
    }
}
