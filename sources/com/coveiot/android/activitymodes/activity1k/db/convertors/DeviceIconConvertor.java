package com.coveiot.android.activitymodes.activity1k.db.convertors;

import androidx.room.TypeConverter;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class DeviceIconConvertor {

    /* loaded from: classes2.dex */
    public class a extends TypeToken<ArrayList<DeviceIconModel>> {
    }

    @TypeConverter
    public static String convertDeviceIconsListToString(List<DeviceIconModel> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<DeviceIconModel> convertStringToDeviceIconsList(String str) {
        return (List) new Gson().fromJson(str, new a().getType());
    }
}
