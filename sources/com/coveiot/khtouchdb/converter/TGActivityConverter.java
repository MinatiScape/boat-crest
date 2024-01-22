package com.coveiot.khtouchdb.converter;

import android.text.TextUtils;
import androidx.room.TypeConverter;
import com.alibaba.fastjson.JSON;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutEventBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutFootBallAvgPaceBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutGpsBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutHeartRateBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutKeepTrackBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutKeepTrackItemBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutPaceBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRealTimeDataBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRealTimeDataItemBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRowingBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutSwimBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutSwimItemBean;
import java.util.List;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class TGActivityConverter {
    @NotNull
    public static final TGActivityConverter INSTANCE = new TGActivityConverter();

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList(@Nullable List<KHTGWorkoutEventBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList12(@Nullable List<KHTGWorkoutRealTimeDataItemBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList13(@Nullable List<KHTGWorkoutKeepTrackItemBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList2(@Nullable List<KHTGWorkoutHeartRateBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList3(@Nullable List<KHTGWorkoutPaceBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList4(@Nullable List<KHTGWorkoutRowingBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList5(@Nullable List<KHTGWorkoutGpsBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromList6(@Nullable List<KHTGWorkoutSwimItemBean> list) {
        if (list == null) {
            return null;
        }
        return JSON.toJSONString(list);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromObj(@Nullable KHTGWorkoutSwimBean kHTGWorkoutSwimBean) {
        if (kHTGWorkoutSwimBean == null) {
            return null;
        }
        return JSON.toJSONString(kHTGWorkoutSwimBean);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromObj2(@Nullable KHTGWorkoutFootBallAvgPaceBean kHTGWorkoutFootBallAvgPaceBean) {
        if (kHTGWorkoutFootBallAvgPaceBean == null) {
            return null;
        }
        return JSON.toJSONString(kHTGWorkoutFootBallAvgPaceBean);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromObj3(@Nullable KHTGWorkoutRealTimeDataBean kHTGWorkoutRealTimeDataBean) {
        if (kHTGWorkoutRealTimeDataBean == null) {
            return null;
        }
        return JSON.toJSONString(kHTGWorkoutRealTimeDataBean);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final String fromObj4(@Nullable KHTGWorkoutKeepTrackBean kHTGWorkoutKeepTrackBean) {
        if (kHTGWorkoutKeepTrackBean == null) {
            return null;
        }
        return JSON.toJSONString(kHTGWorkoutKeepTrackBean);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutEventBean> fromStr(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutEventBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final KHTGWorkoutKeepTrackBean fromStr10(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (KHTGWorkoutKeepTrackBean) JSON.parseObject(str, KHTGWorkoutKeepTrackBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final int[] fromStr11(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (int[]) JSON.parseObject(str, int[].class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutRealTimeDataItemBean> fromStr12(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutRealTimeDataItemBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutKeepTrackItemBean> fromStr13(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutKeepTrackItemBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutHeartRateBean> fromStr2(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutHeartRateBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutPaceBean> fromStr3(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutPaceBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutRowingBean> fromStr4(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutRowingBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutGpsBean> fromStr5(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutGpsBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final List<KHTGWorkoutSwimItemBean> fromStr6(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, KHTGWorkoutSwimItemBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final KHTGWorkoutSwimBean fromStr7(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (KHTGWorkoutSwimBean) JSON.parseObject(str, KHTGWorkoutSwimBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final KHTGWorkoutFootBallAvgPaceBean fromStr8(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (KHTGWorkoutFootBallAvgPaceBean) JSON.parseObject(str, KHTGWorkoutFootBallAvgPaceBean.class);
    }

    @JvmStatic
    @TypeConverter
    @Nullable
    public static final KHTGWorkoutRealTimeDataBean fromStr9(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (KHTGWorkoutRealTimeDataBean) JSON.parseObject(str, KHTGWorkoutRealTimeDataBean.class);
    }
}
