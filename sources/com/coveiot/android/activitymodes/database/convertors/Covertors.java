package com.coveiot.android.activitymodes.database.convertors;

import androidx.room.TypeConverter;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.database.models.HeartRateZoneRanges;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class Covertors {

    /* loaded from: classes2.dex */
    public class a extends TypeToken<TimeSpentHeartRateZone> {
    }

    /* loaded from: classes2.dex */
    public class b extends TypeToken<HeartRateZoneRanges> {
    }

    /* loaded from: classes2.dex */
    public class c extends TypeToken<ArrayList<Integer>> {
    }

    /* loaded from: classes2.dex */
    public class d extends TypeToken<ArrayList<EntityPreparationActivity>> {
    }

    /* loaded from: classes2.dex */
    public class e extends TypeToken<ArrayList<String>> {
    }

    @TypeConverter
    public static String fromArrayLisr(List<String> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<String> fromString(String str) {
        return (List) new Gson().fromJson(str, new e().getType());
    }

    @TypeConverter
    public static String frommArrayLisr(List<Integer> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static ArrayList<Integer> frommString(String str) {
        return (ArrayList) new Gson().fromJson(str, new c().getType());
    }

    @TypeConverter
    public static HeartRateZoneRanges geHeartRateZoneRangesFrom(String str) {
        return (HeartRateZoneRanges) new Gson().fromJson(str, new b().getType());
    }

    @TypeConverter
    public static String getHeartRateZoneRangesStringFrom(HeartRateZoneRanges heartRateZoneRanges) {
        return new Gson().toJson(heartRateZoneRanges);
    }

    @TypeConverter
    public static TimeSpentHeartRateZone getHeartRateZoneTimeListFrom(String str) {
        return (TimeSpentHeartRateZone) new Gson().fromJson(str, new a().getType());
    }

    @TypeConverter
    public static String getHeartRateZoneTimeStringFrom(TimeSpentHeartRateZone timeSpentHeartRateZone) {
        return new Gson().toJson(timeSpentHeartRateZone);
    }

    @TypeConverter
    public static List<EntityPreparationActivity> getPreparationPlanActivityListFron(String str) {
        return (List) new Gson().fromJson(str, new d().getType());
    }

    @TypeConverter
    public static String getPreparationPlanActivityListStringFron(List<EntityPreparationActivity> list) {
        return new Gson().toJson(list);
    }
}
