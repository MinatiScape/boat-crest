package com.coveiot.khtouchdb.converter;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import java.util.Date;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class DateConverter {
    @NotNull
    public static final DateConverter INSTANCE = new DateConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final Long fromDate(@Nullable Date date) {
        if (date != null) {
            return Long.valueOf(date.getTime());
        }
        return null;
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList2(@Nullable List<Integer> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<Integer> fromStr2(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, Integer.TYPE);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final Date toDate(@Nullable Long l) {
        if (l == null) {
            return null;
        }
        return new Date(l.longValue());
    }
}
