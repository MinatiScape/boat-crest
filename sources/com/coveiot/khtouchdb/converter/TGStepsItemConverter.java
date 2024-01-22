package com.coveiot.khtouchdb.converter;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.khtouchdb.walk.model.KHTGStepDataItemBean;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class TGStepsItemConverter {
    @NotNull
    public static final TGStepsItemConverter INSTANCE = new TGStepsItemConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList(@Nullable List<KHTGStepDataItemBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGStepDataItemBean> fromStr(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGStepDataItemBean.class);
    }
}
