package com.coveiot.android.respiratoryrate.database.entities;

import androidx.room.TypeConverter;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateDataConverter {
    @NotNull
    public static final RespiratoryRateDataConverter INSTANCE = new RespiratoryRateDataConverter();

    private RespiratoryRateDataConverter() {
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromFloatArrayList(@Nullable ArrayList<Float> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromIntArrayList(@Nullable ArrayList<Integer> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromRespiratoryBaseUnitsData(@Nullable RespiratoryRateData.BaseUnits baseUnits) {
        Gson gson = new Gson();
        if (baseUnits == null) {
            return null;
        }
        return gson.toJson(baseUnits);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromRespiratoryData(@Nullable RespiratoryRateData respiratoryRateData) {
        Gson gson = new Gson();
        if (respiratoryRateData == null) {
            return null;
        }
        return gson.toJson(respiratoryRateData);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromRespiratorySourceData(@Nullable RespiratoryRateData.Source source) {
        Gson gson = new Gson();
        if (source == null) {
            return null;
        }
        return gson.toJson(source);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final ArrayList<Float> fromStringFloatList(@Nullable String str) {
        Type type = new TypeToken<ArrayList<Float>>() { // from class: com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter$fromStringFloatList$listType$1
        }.getType();
        Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<ArrayList<Float?>?>() {}.type");
        return (ArrayList) new Gson().fromJson(str, type);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final ArrayList<Integer> fromStringIntList(@Nullable String str) {
        Type type = new TypeToken<ArrayList<Integer>>() { // from class: com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter$fromStringIntList$listType$1
        }.getType();
        Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<ArrayList<Int?>?>() {}.type");
        return (ArrayList) new Gson().fromJson(str, type);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final RespiratoryRateData.BaseUnits toRespiratoryRateBaseUnitsData(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return (RespiratoryRateData.BaseUnits) new Gson().fromJson(str, new TypeToken<RespiratoryRateData.BaseUnits>() { // from class: com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter$toRespiratoryRateBaseUnitsData$1
        }.getType());
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final RespiratoryRateData toRespiratoryRateData(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return (RespiratoryRateData) new Gson().fromJson(str, new TypeToken<RespiratoryRateData>() { // from class: com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter$toRespiratoryRateData$1
        }.getType());
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final RespiratoryRateData.Source toRespiratoryRateSourceData(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return (RespiratoryRateData.Source) new Gson().fromJson(str, new TypeToken<RespiratoryRateData.Source>() { // from class: com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter$toRespiratoryRateSourceData$1
        }.getType());
    }
}
