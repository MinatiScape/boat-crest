package com.coveiot.android.khmatrixdb.converter;

import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepItem;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SleepItemConverter {
    @NotNull
    public static final SleepItemConverter INSTANCE = new SleepItemConverter();

    @JvmStatic
    @TypeConverter
    @NotNull
    public static final String fromList(@Nullable List<KhMatrixSleepItem> list) {
        String jSONString = JSON.toJSONString(list);
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(list)");
        return jSONString;
    }

    @JvmStatic
    @TypeConverter
    @NotNull
    public static final List<KhMatrixSleepItem> fromStr(@Nullable String str) {
        List<KhMatrixSleepItem> parseArray = JSON.parseArray(str, KhMatrixSleepItem.class);
        Intrinsics.checkNotNullExpressionValue(parseArray, "parseArray(json, KhMatrixSleepItem::class.java)");
        return parseArray;
    }
}
