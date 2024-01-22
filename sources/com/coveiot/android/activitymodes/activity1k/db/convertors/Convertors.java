package com.coveiot.android.activitymodes.activity1k.db.convertors;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class Convertors implements Serializable {

    /* loaded from: classes2.dex */
    public class a extends TypeToken<ArrayList<String>> {
    }

    @TypeConverter
    public static String convertMetricListToString(List<String> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<String> convertStringToMetricList(String str) {
        return (List) new Gson().fromJson(str, new a().getType());
    }
}
