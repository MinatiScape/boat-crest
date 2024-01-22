package com.mappls.sdk.maps.style.sources;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class TileSet {

    /* renamed from: a  reason: collision with root package name */
    public final String f12852a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public final String[] i;
    public String[] j;
    public String[] k;
    public Float l;
    public Float m;
    public Float[] n;
    public Float[] o;
    public String p;

    public TileSet(String str, String... strArr) {
        this.f12852a = str;
        this.i = strArr;
    }

    @NonNull
    public Map<String, Object> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("tilejson", this.f12852a);
        hashMap.put("tiles", this.i);
        String str = this.b;
        if (str != null) {
            hashMap.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        }
        String str2 = this.c;
        if (str2 != null) {
            hashMap.put(SavingTrackHelper.POINT_COL_DESCRIPTION, str2);
        }
        String str3 = this.d;
        if (str3 != null) {
            hashMap.put("version", str3);
        }
        String str4 = this.e;
        if (str4 != null) {
            hashMap.put("attribution", str4);
        }
        String str5 = this.f;
        if (str5 != null) {
            hashMap.put("template", str5);
        }
        String str6 = this.g;
        if (str6 != null) {
            hashMap.put("legend", str6);
        }
        String str7 = this.h;
        if (str7 != null) {
            hashMap.put("scheme", str7);
        }
        String[] strArr = this.j;
        if (strArr != null) {
            hashMap.put("grids", strArr);
        }
        String[] strArr2 = this.k;
        if (strArr2 != null) {
            hashMap.put("data", strArr2);
        }
        Float f = this.l;
        if (f != null) {
            hashMap.put("minzoom", f);
        }
        Float f2 = this.m;
        if (f2 != null) {
            hashMap.put("maxzoom", f2);
        }
        Float[] fArr = this.n;
        if (fArr != null) {
            hashMap.put("bounds", fArr);
        }
        Float[] fArr2 = this.o;
        if (fArr2 != null) {
            hashMap.put("center", fArr2);
        }
        String str8 = this.p;
        if (str8 != null) {
            hashMap.put("encoding", str8);
        }
        return hashMap;
    }

    public String getAttribution() {
        return this.e;
    }

    public Float[] getBounds() {
        return this.n;
    }

    public Float[] getCenter() {
        return this.o;
    }

    public String[] getData() {
        return this.k;
    }

    public String getDescription() {
        return this.c;
    }

    public String getEncoding() {
        return this.p;
    }

    public String[] getGrids() {
        return this.j;
    }

    public String getLegend() {
        return this.g;
    }

    public float getMaxZoom() {
        return this.m.floatValue();
    }

    public float getMinZoom() {
        return this.l.floatValue();
    }

    public String getName() {
        return this.b;
    }

    public String getScheme() {
        return this.h;
    }

    public String getTemplate() {
        return this.f;
    }

    public String getTilejson() {
        return this.f12852a;
    }

    public String[] getTiles() {
        return this.i;
    }

    public String getVersion() {
        return this.d;
    }

    public void setAttribution(String str) {
        this.e = str;
    }

    public void setBounds(@Size(4) Float... fArr) {
        this.n = fArr;
    }

    public void setCenter(@Size(2) Float... fArr) {
        this.o = fArr;
    }

    public void setData(String... strArr) {
        this.k = strArr;
    }

    public void setDescription(String str) {
        this.c = str;
    }

    public void setEncoding(String str) {
        this.p = str;
    }

    public void setGrids(String... strArr) {
        this.j = strArr;
    }

    public void setLegend(String str) {
        this.g = str;
    }

    public void setMaxZoom(float f) {
        this.m = Float.valueOf(f);
    }

    public void setMinZoom(float f) {
        this.l = Float.valueOf(f);
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setScheme(String str) {
        this.h = str;
    }

    public void setTemplate(String str) {
        this.f = str;
    }

    public void setVersion(String str) {
        this.d = str;
    }

    public void setCenter(@NonNull LatLng latLng) {
        this.o = new Float[]{Float.valueOf((float) latLng.getLongitude()), Float.valueOf((float) latLng.getLatitude())};
    }
}
