package com.google.maps.android.data.kml;

import android.graphics.Color;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes10.dex */
public class KmlStyle extends Style {
    public String e;
    public boolean c = true;
    public boolean d = true;
    public String g = null;

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, String> f11558a = new HashMap<>();
    public final HashSet<String> b = new HashSet<>();
    public double f = 1.0d;
    @VisibleForTesting
    public float k = 0.0f;
    public boolean h = false;
    public boolean i = false;
    public boolean j = false;

    public static String a(String str) {
        String trim = str.trim();
        if (trim.length() > 6) {
            return trim.substring(0, 2) + trim.substring(6, 8) + trim.substring(4, 6) + trim.substring(2, 4);
        }
        return trim.substring(4, 6) + trim.substring(2, 4) + trim.substring(0, 2);
    }

    public static MarkerOptions b(MarkerOptions markerOptions, boolean z, float f) {
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.rotation(markerOptions.getRotation());
        markerOptions2.anchor(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        if (z) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(e(computeRandomColor((int) f))));
        }
        markerOptions2.icon(markerOptions.getIcon());
        return markerOptions2;
    }

    public static PolygonOptions c(PolygonOptions polygonOptions, boolean z, boolean z2) {
        float f;
        PolygonOptions polygonOptions2 = new PolygonOptions();
        if (z) {
            polygonOptions2.fillColor(polygonOptions.getFillColor());
        }
        if (z2) {
            polygonOptions2.strokeColor(polygonOptions.getStrokeColor());
            f = polygonOptions.getStrokeWidth();
        } else {
            f = 0.0f;
        }
        polygonOptions2.strokeWidth(f);
        polygonOptions2.clickable(polygonOptions.isClickable());
        return polygonOptions2;
    }

    public static int computeRandomColor(int i) {
        Random random = new Random();
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        if (red != 0) {
            red = random.nextInt(red);
        }
        if (blue != 0) {
            blue = random.nextInt(blue);
        }
        if (green != 0) {
            green = random.nextInt(green);
        }
        return Color.rgb(red, green, blue);
    }

    public static PolylineOptions d(PolylineOptions polylineOptions) {
        PolylineOptions polylineOptions2 = new PolylineOptions();
        polylineOptions2.color(polylineOptions.getColor());
        polylineOptions2.width(polylineOptions.getWidth());
        polylineOptions2.clickable(polylineOptions.isClickable());
        return polylineOptions2;
    }

    public static float e(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr[0];
    }

    public String f() {
        return this.g;
    }

    public boolean g() {
        return this.h;
    }

    public HashMap<String, String> getBalloonOptions() {
        return this.f11558a;
    }

    public double getIconScale() {
        return this.f;
    }

    public String getIconUrl() {
        return this.e;
    }

    public MarkerOptions getMarkerOptions() {
        return b(this.mMarkerOptions, g(), this.k);
    }

    public PolygonOptions getPolygonOptions() {
        return c(this.mPolygonOptions, this.c, this.d);
    }

    public PolylineOptions getPolylineOptions() {
        return d(this.mPolylineOptions);
    }

    public void h(String str) {
        setPolygonFillColor(Color.parseColor(MqttTopic.MULTI_LEVEL_WILDCARD + a(str)));
        this.b.add("fillColor");
    }

    public boolean hasBalloonStyle() {
        return this.f11558a.size() > 0;
    }

    public boolean hasFill() {
        return this.c;
    }

    public boolean hasOutline() {
        return this.d;
    }

    public void i(float f) {
        setMarkerRotation(f);
        this.b.add("heading");
    }

    public boolean isLineRandomColorMode() {
        return this.i;
    }

    public boolean isPolyRandomColorMode() {
        return this.j;
    }

    public boolean isStyleSet(String str) {
        return this.b.contains(str);
    }

    public void j(float f, float f2, String str, String str2) {
        setMarkerHotSpot(f, f2, str, str2);
        this.b.add("hotSpot");
    }

    public void k(String str) {
        this.h = str.equals("random");
        this.b.add("iconColorMode");
    }

    public void l(double d) {
        this.f = d;
        this.b.add("iconScale");
    }

    public void m(String str) {
        this.e = str;
        this.b.add("iconUrl");
    }

    public void n(String str) {
        this.f11558a.put("text", str);
    }

    public void o(String str) {
        this.i = str.equals("random");
        this.b.add("lineColorMode");
    }

    public void p(String str) {
        float e = e(Color.parseColor(MqttTopic.MULTI_LEVEL_WILDCARD + a(str)));
        this.k = e;
        this.mMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(e));
        this.b.add("markerColor");
    }

    public void q(boolean z) {
        this.d = z;
        this.b.add("outline");
    }

    public void r(String str) {
        PolylineOptions polylineOptions = this.mPolylineOptions;
        polylineOptions.color(Color.parseColor(MqttTopic.MULTI_LEVEL_WILDCARD + a(str)));
        PolygonOptions polygonOptions = this.mPolygonOptions;
        polygonOptions.strokeColor(Color.parseColor(MqttTopic.MULTI_LEVEL_WILDCARD + a(str)));
        this.b.add("outlineColor");
    }

    public void s(String str) {
        this.j = str.equals("random");
        this.b.add("polyColorMode");
    }

    public void setFill(boolean z) {
        this.c = z;
    }

    public void t(String str) {
        this.g = str;
    }

    public String toString() {
        return "Style{\n balloon options=" + this.f11558a + ",\n fill=" + this.c + ",\n outline=" + this.d + ",\n icon url=" + this.e + ",\n scale=" + this.f + ",\n style id=" + this.g + "\n}\n";
    }

    public void u(Float f) {
        setLineStringWidth(f.floatValue());
        setPolygonStrokeWidth(f.floatValue());
        this.b.add(Property.ICON_TEXT_FIT_WIDTH);
    }
}
