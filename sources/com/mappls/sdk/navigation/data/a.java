package com.mappls.sdk.navigation.data;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.common.b;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.n;
import com.mappls.sdk.navigation.util.c;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public String f12896a;
    public String b;
    public String c;
    public String d;
    public double e;
    public double f;
    public double g;
    public double h;

    public a(double d, double d2) {
        this(FirebaseAnalytics.Param.LOCATION, "");
        this.e = d;
        this.g = d;
        this.f = d2;
        this.h = d2;
    }

    public a(double d, double d2, double d3, double d4) {
        this(FirebaseAnalytics.Param.LOCATION, "");
        this.e = d3;
        this.g = d;
        this.f = d4;
        this.h = d2;
    }

    public a(String str, String str2) {
        this.c = "";
        this.e = 0.0d;
        this.f = 0.0d;
        this.g = 0.0d;
        this.h = 0.0d;
        this.f12896a = str;
        this.b = str2;
        if (str2 == null) {
            this.b = "";
        }
    }

    public a(String str, String str2, String str3) {
        this.c = "";
        this.e = 0.0d;
        this.f = 0.0d;
        this.g = 0.0d;
        this.h = 0.0d;
        this.f12896a = str;
        this.b = str3;
        this.d = str2;
        if (str3 == null) {
            this.b = "";
        }
    }

    public static a a(LatLng latLng, String str) {
        a aVar;
        int indexOf;
        String trim;
        if (str == null || str.length() <= 0 || (indexOf = str.indexOf(35)) < 0) {
            aVar = null;
        } else {
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(35, i);
            if (indexOf2 > 0) {
                trim = str.substring(i, indexOf2).trim();
                str.substring(indexOf2 + 1).getClass();
            } else {
                trim = str.substring(i).trim();
            }
            String substring = str.substring(0, indexOf);
            aVar = substring.contains(".") ? new a(substring.substring(0, substring.indexOf(46)), substring.substring(substring.indexOf(46) + 1), trim) : new a(substring, trim);
        }
        if (aVar == null) {
            aVar = new a(FirebaseAnalytics.Param.LOCATION, "");
        }
        if (aVar.g() && latLng != null) {
            aVar.e = latLng.getLatitude();
            aVar.f = latLng.getLongitude();
        }
        return aVar;
    }

    public static String a(a aVar) {
        if (aVar == null) {
            return "";
        }
        String str = aVar.f12896a;
        if (!com.mappls.sdk.navigation.util.a.a(aVar.d)) {
            str = str + '.' + aVar.d;
        }
        String str2 = str + MqttTopic.MULTI_LEVEL_WILDCARD + aVar.b;
        if (com.mappls.sdk.navigation.util.a.a((String) null)) {
            return str2;
        }
        return str2 + MqttTopic.MULTI_LEVEL_WILDCARD + ((String) null);
    }

    public final double a() {
        return this.e;
    }

    public final void a(double d) {
        this.e = d;
    }

    public final void a(String str) {
        this.b = str;
        if (str == null) {
            this.b = "";
        }
    }

    public final double b() {
        return this.f;
    }

    public final void b(double d) {
        this.f = d;
    }

    public final void b(String str) {
        this.c = str;
        if (str == null) {
            this.c = "";
        }
    }

    public final double c() {
        return this.g;
    }

    public final double d() {
        return this.h;
    }

    @NonNull
    public final String e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return com.mappls.sdk.navigation.util.a.a(aVar.b, this.b) && com.mappls.sdk.navigation.util.a.a(aVar.f12896a, this.f12896a) && com.mappls.sdk.navigation.util.a.a(Double.valueOf(aVar.e), Double.valueOf(this.e)) && com.mappls.sdk.navigation.util.a.a(Double.valueOf(aVar.f), Double.valueOf(this.f)) && com.mappls.sdk.navigation.util.a.a(aVar.d, this.d);
    }

    public final String f() {
        return this.c;
    }

    public final boolean g() {
        return FirebaseAnalytics.Param.LOCATION.equals(this.f12896a);
    }

    public final int hashCode() {
        String str = this.b;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.f12896a;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.d;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        double d = this.e;
        int hashCode4 = (hashCode3 + (d == 0.0d ? 0 : new Double(d).hashCode())) * 31;
        double d2 = this.f;
        return hashCode4 + (d2 != 0.0d ? new Double(d2).hashCode() : 0);
    }

    public static a a(String str, String str2) {
        a aVar;
        int indexOf;
        if (str == null || str.length() <= 0 || (indexOf = str.indexOf(35)) < 0) {
            aVar = null;
        } else {
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(35, i);
            String trim = (indexOf2 > 0 ? str.substring(i, indexOf2) : str.substring(i)).trim();
            String substring = str.substring(0, indexOf);
            aVar = substring.contains(".") ? new a(substring.substring(0, substring.indexOf(46)), substring.substring(substring.indexOf(46) + 1), trim) : new a(substring, trim);
        }
        if (aVar == null) {
            aVar = new a(FirebaseAnalytics.Param.LOCATION, "");
        }
        if (aVar.g() && str2 != null && str2.contains(Constants.SEPARATOR_COMMA)) {
            aVar.e = Double.parseDouble(str2.split(Constants.SEPARATOR_COMMA)[1]);
            aVar.f = Double.parseDouble(str2.split(Constants.SEPARATOR_COMMA)[0]);
        }
        return aVar;
    }

    public static String a(NavigationApplication navigationApplication) {
        return n.a(navigationApplication, R.string.mappls_shared_string_ellipsis, h.a(""));
    }

    public static String a(LocationPoint locationPoint, NavigationApplication navigationApplication) {
        String string;
        b bVar;
        a pointDescription = locationPoint.getPointDescription(navigationApplication);
        if (!pointDescription.g()) {
            if (com.mappls.sdk.navigation.util.a.a(pointDescription.d)) {
                return pointDescription.c;
            }
            if (com.mappls.sdk.navigation.util.a.a(pointDescription.c)) {
                return pointDescription.d;
            }
            return pointDescription.d.trim() + ": " + pointDescription.c;
        } else if (com.mappls.sdk.navigation.util.a.a(pointDescription.c) || pointDescription.c.equals(navigationApplication.getString(R.string.mappls_no_address_found))) {
            double d = pointDescription.e;
            double d2 = pointDescription.f;
            int intValue = ((Integer) ((NavigationApplication) navigationApplication.getApplicationContext()).k().n0.get()).intValue();
            if (intValue == 3) {
                b.a(new com.mappls.sdk.navigation.common.a(d, d2), new b());
                string = bVar.c + "" + bVar.d + HexStringBuilder.DEFAULT_SEPARATOR + ((long) bVar.b) + HexStringBuilder.DEFAULT_SEPARATOR + ((long) bVar.f12892a);
            } else {
                try {
                    string = navigationApplication.getString(R.string.mappls_short_location_on_map, c.a(d, intValue), c.a(d2, intValue));
                } catch (RuntimeException e) {
                    NavigationLogger.d(e);
                    string = navigationApplication.getString(R.string.mappls_short_location_on_map, 0, 0);
                }
            }
            return string.replace('\n', ' ');
        } else {
            return pointDescription.c;
        }
    }
}
