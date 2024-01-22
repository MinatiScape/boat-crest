package com.coveiot.android.respiratoryrate.database;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class Converter {
    @TypeConverter
    @Nullable
    public final String fromListIntToString(@Nullable List<Integer> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    @Nullable
    public final List<Integer> frommStringToListInt(@Nullable String str) {
        return (List) new Gson().fromJson(str, new TypeToken<ArrayList<Integer>>() { // from class: com.coveiot.android.respiratoryrate.database.Converter$frommStringToListInt$listType$1
        }.getType());
    }
}
