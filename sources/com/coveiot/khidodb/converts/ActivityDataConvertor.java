package com.coveiot.khidodb.converts;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.khidodb.activities.KHHealthActivityV3ItemKMSpeed;
import com.coveiot.khidodb.activities.KHHealthActivityV3StepsItem;
import com.coveiot.khidodb.activities.KHHealthSwimV3Detail;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityDataConvertor {
    @NotNull
    public static final ActivityDataConvertor INSTANCE = new ActivityDataConvertor();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList(@Nullable List<KHHealthActivityV3StepsItem> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList2(@Nullable List<KHHealthActivityV3ItemKMSpeed> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList3(@Nullable int[] iArr) {
        if (iArr == null) {
            return null;
        }
        return JSON.toJSONString(iArr);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList4(@Nullable List<KHHealthSwimV3Detail> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHHealthActivityV3StepsItem> fromStr(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHHealthActivityV3StepsItem.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHHealthActivityV3ItemKMSpeed> fromStr2(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHHealthActivityV3ItemKMSpeed.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final int[] fromStr3(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (int[]) JSON.parseObject(str, int[].class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHHealthSwimV3Detail> fromStr4(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHHealthSwimV3Detail.class);
    }
}
