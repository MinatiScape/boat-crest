package com.coveiot.android.khmatrixdb.converter;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.android.khmatrixdb.workout.KhMatrixSportItemData;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SportItemConverter {
    @NotNull
    public static final SportItemConverter INSTANCE = new SportItemConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList(@Nullable List<KhMatrixSportItemData> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KhMatrixSportItemData> fromStr(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KhMatrixSportItemData.class);
    }
}
