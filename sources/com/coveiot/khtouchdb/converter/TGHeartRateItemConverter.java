package com.coveiot.khtouchdb.converter;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.khtouchdb.heartrate.model.KHTGHRDataItemBean;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class TGHeartRateItemConverter {
    @NotNull
    public static final TGHeartRateItemConverter INSTANCE = new TGHeartRateItemConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList(@Nullable List<KHTGHRDataItemBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGHRDataItemBean> fromStr(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGHRDataItemBean.class);
    }
}
