package com.coveiot.android.sleepenergyscore.energymeter.database.entities;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyDataConverter {
    @NotNull
    public static final EnergyDataConverter INSTANCE = new EnergyDataConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromEnergyDataList(@Nullable ArrayList<EnergyData> arrayList) {
        Gson gson = new Gson();
        if (arrayList == null) {
            return null;
        }
        return gson.toJson(arrayList);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromFeedbackDataList(@Nullable ArrayList<QuestionAnswerData> arrayList) {
        Gson gson = new Gson();
        if (arrayList == null) {
            return null;
        }
        return gson.toJson(arrayList);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final ArrayList<EnergyData> toEnergyData(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<EnergyData>>() { // from class: com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyDataConverter$toEnergyData$1
        }.getType());
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final ArrayList<QuestionAnswerData> toFeedbackData(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<QuestionAnswerData>>() { // from class: com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyDataConverter$toFeedbackData$1
        }.getType());
    }
}
