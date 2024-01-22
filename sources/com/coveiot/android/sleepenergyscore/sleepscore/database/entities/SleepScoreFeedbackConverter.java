package com.coveiot.android.sleepenergyscore.sleepscore.database.entities;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SleepScoreFeedbackConverter {
    @NotNull
    public static final SleepScoreFeedbackConverter INSTANCE = new SleepScoreFeedbackConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromFeedbackDataList(@Nullable ArrayList<QuestionAnswerSleepData> arrayList) {
        Gson gson = new Gson();
        if (arrayList == null) {
            return null;
        }
        return gson.toJson(arrayList);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final ArrayList<QuestionAnswerSleepData> toFeedbackData(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<QuestionAnswerSleepData>>() { // from class: com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreFeedbackConverter$toFeedbackData$1
        }.getType());
    }
}
