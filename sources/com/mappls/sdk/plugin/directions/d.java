package com.mappls.sdk.plugin.directions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mappls.sdk.services.api.directions.models.IntersectionLanes;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
/* loaded from: classes11.dex */
public class d {
    public static final Logger c = Logger.getLogger(d.class.getSimpleName());

    /* renamed from: a  reason: collision with root package name */
    public JsonObject f13092a;
    public JsonObject b;

    public d(String str, String str2) {
        InputStream resourceAsStream = d.class.getClassLoader().getResourceAsStream(String.format("translations/%s.json", str));
        if (resourceAsStream == null) {
            throw new RuntimeException("Translation not found for language: " + str);
        }
        JsonObject asJsonObject = JsonParser.parseReader(new InputStreamReader(resourceAsStream)).getAsJsonObject();
        this.f13092a = asJsonObject;
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(str2);
        this.b = asJsonObject2;
        if (asJsonObject2 != null) {
            return;
        }
        throw new RuntimeException("Version not found for value: " + str2);
    }

    public static String f(String str) {
        if (str.length() == 0) {
            return "";
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public JsonObject a() {
        return this.f13092a;
    }

    public String b(LegStep legStep) {
        if (legStep.intersections() == null || legStep.intersections().size() == 0 || legStep.intersections().get(0).lanes() == null || legStep.intersections().get(0).lanes().size() == 0) {
            throw new RuntimeException("No lanes object");
        }
        StringBuilder sb = new StringBuilder();
        Boolean bool = null;
        for (IntersectionLanes intersectionLanes : legStep.intersections().get(0).lanes()) {
            if (bool == null || bool != intersectionLanes.valid()) {
                sb.append(intersectionLanes.valid().booleanValue() ? "o" : "x");
                bool = intersectionLanes.valid();
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0105, code lost:
        if (r1.getAsJsonObject("name_exit") != null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0129, code lost:
        if (r1.getAsJsonObject("exit") != null) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String c(com.mappls.sdk.services.api.directions.models.LegStep r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.plugin.directions.d.c(com.mappls.sdk.services.api.directions.models.LegStep, boolean):java.lang.String");
    }

    public String d(Double d) {
        JsonObject asJsonObject;
        String str;
        JsonPrimitive asJsonPrimitive;
        if (d == null) {
            return "";
        }
        if (d.doubleValue() < 0.0d || d.doubleValue() > 20.0d) {
            if (d.doubleValue() > 20.0d && d.doubleValue() < 70.0d) {
                asJsonObject = g().getAsJsonObject("constants").getAsJsonObject("direction");
                str = "northeast";
            } else if (d.doubleValue() >= 70.0d && d.doubleValue() <= 110.0d) {
                asJsonObject = g().getAsJsonObject("constants").getAsJsonObject("direction");
                str = "east";
            } else if (d.doubleValue() > 110.0d && d.doubleValue() < 160.0d) {
                asJsonObject = g().getAsJsonObject("constants").getAsJsonObject("direction");
                str = "southeast";
            } else if (d.doubleValue() >= 160.0d && d.doubleValue() <= 200.0d) {
                asJsonObject = g().getAsJsonObject("constants").getAsJsonObject("direction");
                str = "south";
            } else if (d.doubleValue() > 200.0d && d.doubleValue() < 250.0d) {
                asJsonObject = g().getAsJsonObject("constants").getAsJsonObject("direction");
                str = "southwest";
            } else if (d.doubleValue() >= 250.0d && d.doubleValue() <= 290.0d) {
                asJsonObject = g().getAsJsonObject("constants").getAsJsonObject("direction");
                str = "west";
            } else if (d.doubleValue() > 290.0d && d.doubleValue() < 340.0d) {
                asJsonObject = g().getAsJsonObject("constants").getAsJsonObject("direction");
                str = "northwest";
            } else if (d.doubleValue() < 340.0d || d.doubleValue() > 360.0d) {
                throw new RuntimeException("Degree is invalid: " + d);
            }
            asJsonPrimitive = asJsonObject.getAsJsonPrimitive(str);
            return asJsonPrimitive.getAsString();
        }
        asJsonPrimitive = g().getAsJsonObject("constants").getAsJsonObject("direction").getAsJsonPrimitive("north");
        return asJsonPrimitive.getAsString();
    }

    public String e(Integer num) {
        try {
            return g().getAsJsonObject("constants").getAsJsonObject("ordinalize").getAsJsonPrimitive(String.valueOf(num)).getAsString();
        } catch (Exception unused) {
            return "";
        }
    }

    public JsonObject g() {
        return this.b;
    }

    public final boolean h(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'A' || charAt > 'Z') && ((charAt < 'a' || charAt > 'z') && charAt != ' '))) {
                i++;
            }
        }
        return i <= 0;
    }
}
