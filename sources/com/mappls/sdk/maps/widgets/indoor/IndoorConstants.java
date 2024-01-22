package com.mappls.sdk.maps.widgets.indoor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class IndoorConstants {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f12863a = {"indoor_units", "indoor_venue", "indoor_building_outline", "footprints_indoor_3d_1_floor", "Indoor_othpoi", "indoor_entry_exit", "indoor_poi_withouticon", "indoor_poi2", "indoor_levelsname", "indoor_SHPMAL", "footprints_indoor_2_3floors", "Indoor_MISC1", "Indoor_MISC2", "Indoor_MISC3", "Indoor_MISC4", "Indoor_MISC5"};

    public static String getFloorName(int i) {
        if (i == 0) {
            return "G";
        }
        return i + "";
    }

    public static List<Floor> getFloors(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        if (i2 < 0) {
            i += i2;
        }
        while (i2 < i) {
            arrayList.add(new Floor(Integer.valueOf(i2), getFloorName(i2), getInternalFloorName(i2)));
            i2++;
        }
        return arrayList;
    }

    public static String getInternalFloorName(int i) {
        Locale locale = Locale.getDefault();
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(Math.abs(i > -1 ? i + 1 : i));
        String format = String.format(locale, "%03d", objArr);
        String str = i < 0 ? "B" : "L";
        return str + format;
    }

    public static List<String> getPossibleFloors() {
        ArrayList arrayList = new ArrayList();
        for (int i = 120; i > -20; i--) {
            arrayList.add(getInternalFloorName(i));
        }
        return arrayList;
    }
}
