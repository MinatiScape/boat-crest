package com.coveiot.khidodb.converts;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateHighLowItem;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateSecondItem;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateSecond_Interval;
import java.util.Date;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class HealthHeartRateItemConverter {
    @NotNull
    public static final HealthHeartRateItemConverter INSTANCE = new HealthHeartRateItemConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList(@Nullable List<KHHealthHeartRateSecond_Interval> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList2(@Nullable List<KHHealthHeartRateSecondItem> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList3(@Nullable List<KHHealthHeartRateHighLowItem> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList4(@Nullable Date date) {
        if (date == null) {
            return null;
        }
        return JSON.toJSONString(date);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList5(@Nullable Long l) {
        if (l == null) {
            return null;
        }
        return JSON.toJSONString(l);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHHealthHeartRateSecond_Interval> fromStr(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHHealthHeartRateSecond_Interval.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHHealthHeartRateSecondItem> fromStr2(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHHealthHeartRateSecondItem.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHHealthHeartRateHighLowItem> fromStr3(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHHealthHeartRateHighLowItem.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final Date fromStr4(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (Date) JSON.parseObject(str, Date.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final Long fromStr5(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (Long) JSON.parseObject(str, Long.TYPE);
    }
}
