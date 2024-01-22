package com.mappls.sdk.navigation.data;

import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.navigation.h;
/* loaded from: classes11.dex */
public class WayPoint {

    /* renamed from: a  reason: collision with root package name */
    public String f12895a;
    public Double b;
    public Double c;
    public Double d;
    public Double e;
    public String f;
    public String g;
    public int h;
    public int i;

    public WayPoint() {
    }

    public WayPoint(double d, double d2, double d3, double d4, String str, String str2) {
        this.b = Double.valueOf(d);
        this.c = Double.valueOf(d2);
        this.d = Double.valueOf(d3);
        this.e = Double.valueOf(d4);
        this.f = str;
        this.g = str2;
    }

    public WayPoint(double d, double d2, String str) {
        this.b = Double.valueOf(d);
        this.c = Double.valueOf(d2);
        this.d = Double.valueOf(d);
        this.e = Double.valueOf(d2);
        this.f = str;
        this.g = str;
    }

    public WayPoint(double d, double d2, String str, String str2) {
        this.b = Double.valueOf(d);
        this.c = Double.valueOf(d2);
        this.d = Double.valueOf(d);
        this.e = Double.valueOf(d2);
        this.f = str;
        this.g = str2;
    }

    public WayPoint(String str, String str2) {
        if (str.contains(Constants.SEPARATOR_COMMA)) {
            this.c = Double.valueOf(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[0]));
            this.b = Double.valueOf(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[1]));
        } else {
            this.f12895a = str;
        }
        this.f = str2;
        this.g = str2;
    }

    public WayPoint(String str, String str2, String str3) {
        if (str.contains(Constants.SEPARATOR_COMMA)) {
            this.c = Double.valueOf(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[0]));
            Double valueOf = Double.valueOf(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[1]));
            this.b = valueOf;
            this.d = valueOf;
            this.e = this.c;
        } else {
            this.f12895a = str;
        }
        this.f = str2;
        this.g = str3;
    }

    public Double getEntryLatitude() {
        return this.b;
    }

    public Double getEntryLongitude() {
        return this.c;
    }

    public int getIndex() {
        return this.h;
    }

    public int getIndexOnPath() {
        return this.i;
    }

    public Double getLatitude() {
        return this.d;
    }

    public Double getLongitude() {
        return this.e;
    }

    public String getMapplsPin() {
        return this.f12895a;
    }

    public String getSpokenName() {
        return this.g;
    }

    public String getVisualName() {
        return this.f;
    }

    public String getVisualValue() {
        StringBuilder sb;
        Double d;
        if (this.f12895a != null) {
            sb = new StringBuilder();
            sb.append(this.f12895a);
        } else {
            if (this.b != null) {
                sb = new StringBuilder();
                sb.append(this.b);
                sb.append(Constants.SEPARATOR_COMMA);
                d = this.c;
            } else if (this.d == null) {
                return toString();
            } else {
                sb = new StringBuilder();
                sb.append(this.d);
                sb.append(Constants.SEPARATOR_COMMA);
                d = this.e;
            }
            sb.append(d);
        }
        sb.append(";");
        sb.append(this.g);
        sb.append(";");
        sb.append(this.f);
        return sb.toString();
    }

    public boolean isValidCoordinates() {
        return this.f12895a == null;
    }

    public WayPoint setEntryLatitude(double d) {
        this.f12895a = null;
        this.b = Double.valueOf(d);
        return this;
    }

    public WayPoint setEntryLongitude(double d) {
        this.f12895a = null;
        this.c = Double.valueOf(d);
        return this;
    }

    public void setIndex(int i) {
        this.h = i;
    }

    public void setIndexOnPath(int i) {
        this.i = i;
    }

    public WayPoint setLatitude(Double d) {
        this.d = d;
        return this;
    }

    public WayPoint setLongitude(Double d) {
        this.e = d;
        return this;
    }

    public void setMapplsPin(String str) {
        if (!str.contains(Constants.SEPARATOR_COMMA)) {
            this.f12895a = str;
            return;
        }
        this.c = Double.valueOf(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[0]));
        this.b = Double.valueOf(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[1]));
    }

    public void setSpokenName(String str) {
        this.g = str;
    }

    public WayPoint setVisualName(String str) {
        this.f = str;
        return this;
    }

    public String toString() {
        StringBuilder a2 = h.a("WayPoint{mapplsPin='");
        a2.append(this.f12895a);
        a2.append('\'');
        a2.append(", entryLatitude=");
        a2.append(this.b);
        a2.append(", entryLongitude=");
        a2.append(this.c);
        a2.append(", latitude=");
        a2.append(this.d);
        a2.append(", longitude=");
        a2.append(this.e);
        a2.append(", visualName='");
        a2.append(this.f);
        a2.append('\'');
        a2.append(", spokenName='");
        a2.append(this.g);
        a2.append('\'');
        a2.append(", index=");
        a2.append(this.h);
        a2.append(", indexOnPath=");
        a2.append(this.i);
        a2.append('}');
        return a2.toString();
    }
}
