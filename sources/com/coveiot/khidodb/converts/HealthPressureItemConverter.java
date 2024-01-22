package com.coveiot.khidodb.converts;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.khidodb.stress.model.KHHealthPressureItem;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class HealthPressureItemConverter {
    @NotNull
    public static final HealthPressureItemConverter INSTANCE = new HealthPressureItemConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList(@Nullable List<KHHealthPressureItem> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHHealthPressureItem> fromStr(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHHealthPressureItem.class);
    }
}
