package com.coveiot.android.dashboard2.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.listener.LastNightSleepDataListener;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.sleepalgorithm.filtering.CZ0ParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.EastApexParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.IDOParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MatrixParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MoyangParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.SmaParsedSleepDataF2;
import com.coveiot.android.sleepalgorithm.filtering.StrideParsedSleepDataV2NoAlgo;
import com.coveiot.android.sleepalgorithm.filtering.TouchELXParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepAlgoWithREM;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.android.theme.model.SmallHealthCardInfo;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.LastNightSleepData;
import com.coveiot.repository.sleep.SleepRepository;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SleepDataHelper {
    @NotNull
    public static final SleepDataHelper INSTANCE = new SleepDataHelper();

    @JvmStatic
    @Nullable
    public static final SleepDataModel getSleepDataModel(@NotNull Context context, @Nullable List<? extends SleepDataModelForLastNight> list) {
        SmaParsedSleepDataF2 smaParsedSleepDataF2;
        MoyangParsedSleepData moyangParsedSleepData;
        MatrixParsedSleepData matrixParsedSleepData;
        IDOParsedSleepData iDOParsedSleepData;
        TouchELXParsedSleepData touchELXParsedSleepData;
        EastApexParsedSleepData eastApexParsedSleepData;
        StrideParsedSleepDataV2NoAlgo strideParsedSleepDataV2NoAlgo;
        JStyleParsedSleepData jStyleParsedSleepData;
        JStyleSleepAlgoWithREM jStyleSleepAlgoWithREM;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNull(list);
        CZ0ParsedSleepData cZ0ParsedSleepData = null;
        if (list.isEmpty()) {
            return null;
        }
        byte[] bArr = new byte[1440];
        Arrays.fill(bArr, 0, 1440, (byte) -1);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int size2 = list.get(i).getCodevalue().size();
            for (int i2 = 0; i2 < size2; i2++) {
                int timeIndex = INSTANCE.getTimeIndex(list.get(i), i2);
                if (timeIndex < 1440) {
                    Integer num = list.get(i).getCodevalue().get(i2);
                    if (num != null) {
                        if (num.intValue() > -1) {
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            if (!companion.isJstyleDevice(context) && !companion.isSmaDevice(context) && !companion.isMoyangDevice(context) && !companion.isCZDevice(context) && !companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isMatrixDevice(context) && !companion.isIDODevice(context) && !companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context) && !companion.isBESDevice(context)) {
                                if (num.intValue() != 3) {
                                    bArr[timeIndex] = (byte) num.intValue();
                                } else {
                                    bArr[timeIndex] = -1;
                                }
                            } else {
                                bArr[timeIndex] = (byte) num.intValue();
                            }
                        }
                    } else {
                        bArr[timeIndex] = -1;
                    }
                }
            }
        }
        DeviceUtils.Companion companion2 = DeviceUtils.Companion;
        if (companion2.isJstyleDevice(context)) {
            if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
                try {
                    jStyleSleepAlgoWithREM = new JStyleSleepAlgoWithREM(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e) {
                    e.printStackTrace();
                    jStyleSleepAlgoWithREM = null;
                }
                if (jStyleSleepAlgoWithREM == null || jStyleSleepAlgoWithREM.getSleepDataModel() == null) {
                    return null;
                }
                return jStyleSleepAlgoWithREM.getSleepDataModel();
            }
            try {
                jStyleParsedSleepData = new JStyleParsedSleepData(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e2) {
                e2.printStackTrace();
                jStyleParsedSleepData = null;
            }
            if (jStyleParsedSleepData == null || jStyleParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return jStyleParsedSleepData.getSleepDataModel();
        } else if (companion2.isSmaDevice(context)) {
            try {
                smaParsedSleepDataF2 = new SmaParsedSleepDataF2(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e3) {
                e3.printStackTrace();
                smaParsedSleepDataF2 = null;
            }
            if (smaParsedSleepDataF2 == null || smaParsedSleepDataF2.getSleepDataModel() == null) {
                return null;
            }
            return smaParsedSleepDataF2.getSleepDataModel();
        } else if (companion2.isMoyangDevice(context)) {
            try {
                moyangParsedSleepData = new MoyangParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e4) {
                e4.printStackTrace();
                moyangParsedSleepData = null;
            }
            if (moyangParsedSleepData == null || moyangParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return moyangParsedSleepData.getSleepDataModel();
        } else if (companion2.isMatrixDevice(context)) {
            try {
                matrixParsedSleepData = new MatrixParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e5) {
                e5.printStackTrace();
                matrixParsedSleepData = null;
            }
            if (matrixParsedSleepData == null || matrixParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return matrixParsedSleepData.getSleepDataModel();
        } else if (companion2.isIDODevice(context)) {
            try {
                iDOParsedSleepData = new IDOParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e6) {
                e6.printStackTrace();
                iDOParsedSleepData = null;
            }
            if (iDOParsedSleepData == null || iDOParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return iDOParsedSleepData.getSleepDataModel();
        } else if (companion2.isTouchELXDevice(context)) {
            try {
                touchELXParsedSleepData = new TouchELXParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e7) {
                e7.printStackTrace();
                touchELXParsedSleepData = null;
            }
            if (touchELXParsedSleepData == null || touchELXParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return touchELXParsedSleepData.getSleepDataModel();
        } else if (companion2.isKaHaDeviceWithRem(context)) {
            try {
                cZ0ParsedSleepData = new CZ0ParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e8) {
                e8.printStackTrace();
            }
            Intrinsics.checkNotNull(cZ0ParsedSleepData);
            return cZ0ParsedSleepData.getSleepDataModel();
        } else if (companion2.isEastApexDevice(context)) {
            try {
                eastApexParsedSleepData = new EastApexParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e9) {
                e9.printStackTrace();
                eastApexParsedSleepData = null;
            }
            if (eastApexParsedSleepData == null || eastApexParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return eastApexParsedSleepData.getSleepDataModel();
        } else {
            try {
                strideParsedSleepDataV2NoAlgo = new StrideParsedSleepDataV2NoAlgo(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e10) {
                e10.printStackTrace();
                strideParsedSleepDataV2NoAlgo = null;
            }
            if (strideParsedSleepDataV2NoAlgo == null || strideParsedSleepDataV2NoAlgo.getSleepDataModel() == null) {
                return null;
            }
            return strideParsedSleepDataV2NoAlgo.getSleepDataModel();
        }
    }

    public static /* synthetic */ SmallHealthCardInfo getSleepSmallHealthCardInfo$default(SleepDataHelper sleepDataHelper, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return sleepDataHelper.getSleepSmallHealthCardInfo(context, i);
    }

    public final void getLastNightSleepData(@NotNull Context context, @Nullable List<? extends SleepDataModelForLastNight> list, @Nullable LastNightSleepDataListener lastNightSleepDataListener) {
        SleepDataModel sleepDataModel;
        Intrinsics.checkNotNullParameter(context, "context");
        LastNightSleepData lastNightSleepData = new LastNightSleepData();
        boolean z = false;
        if (list != null && (!list.isEmpty())) {
            z = true;
        }
        if (z && (sleepDataModel = getSleepDataModel(context, list)) != null) {
            lastNightSleepData = new LastNightSleepData(sleepDataModel.getSleepStartHour(), sleepDataModel.getSleepEndHour(), sleepDataModel.getCountTotalSleep());
        }
        UserDataManager.getInstance(context).saveLastNightSleepData(BleApiManager.getInstance(context).getBleApi().getMacAddress(), lastNightSleepData);
        if (lastNightSleepDataListener != null) {
            lastNightSleepDataListener.onData(lastNightSleepData);
        }
    }

    public final void getSleepDataByDate(@NotNull Context context, @NotNull Calendar calendar, @Nullable LastNightSleepDataListener lastNightSleepDataListener) {
        SleepDataModel sleepDataModel;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        LastNightSleepData lastNightSleepData = new LastNightSleepData();
        List<SleepDataModelForLastNight> lastNignthSleepDataWithOutLiveData = SleepRepository.Companion.getInstance(context).getLastNignthSleepDataWithOutLiveData(calendar, BleApiManager.getInstance(context).getBleApi().getMacAddress());
        boolean z = false;
        if (lastNignthSleepDataWithOutLiveData != null && (!lastNignthSleepDataWithOutLiveData.isEmpty())) {
            z = true;
        }
        if (z && (sleepDataModel = getSleepDataModel(context, lastNignthSleepDataWithOutLiveData)) != null) {
            lastNightSleepData = new LastNightSleepData(sleepDataModel.getSleepStartHour(), sleepDataModel.getSleepEndHour(), sleepDataModel.getCountTotalSleep());
        }
        if (lastNightSleepDataListener != null) {
            lastNightSleepDataListener.onData(lastNightSleepData);
        }
    }

    @NotNull
    public final SmallHealthCardInfo getSleepSmallHealthCardInfo(@NotNull Context context, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(context, "context");
        FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(context).getFitnessGoalSleepData();
        Drawable drawable = context.getDrawable(R.drawable.ic_sleep_home);
        String valueOf = String.valueOf(i / 60);
        String string = context.getString(R.string.hr);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.hr)");
        String lowerCase = string.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        String valueOf2 = String.valueOf(lowerCase);
        String valueOf3 = String.valueOf(i % 60);
        String string2 = context.getString(R.string.m);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.m)");
        String lowerCase2 = string2.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        String valueOf4 = String.valueOf(lowerCase2);
        String string3 = context.getString(R.string.sleep_camel_case);
        if (fitnessGoalSleepData != null) {
            Integer num = fitnessGoalSleepData.target;
            Intrinsics.checkNotNullExpressionValue(num, "sleepTarget.target");
            if (num.intValue() > 0) {
                Integer num2 = fitnessGoalSleepData.target;
                Intrinsics.checkNotNullExpressionValue(num2, "sleepTarget.target");
                i2 = (i * 100) / num2.intValue();
                return new SmallHealthCardInfo(drawable, valueOf, valueOf2, valueOf3, valueOf4, string3, null, i2, null, null, true, 832, null);
            }
        }
        i2 = 0;
        return new SmallHealthCardInfo(drawable, valueOf, valueOf2, valueOf3, valueOf4, string3, null, i2, null, null, true, 832, null);
    }

    public final int getTimeIndex(@NotNull SleepDataModelForLastNight sleepDataModelForLastNight, int i) {
        int parseInt;
        int parseInt2;
        Intrinsics.checkNotNullParameter(sleepDataModelForLastNight, "sleepDataModelForLastNight");
        String startTime = sleepDataModelForLastNight.getStartTime();
        Intrinsics.checkNotNullExpressionValue(startTime, "sleepDataModelForLastNight.startTime");
        String[] strArr = (String[]) new Regex(":").split(startTime, 0).toArray(new String[0]);
        if (strArr.length > 1) {
            if (Integer.parseInt(strArr[0]) < 12) {
                parseInt = (Integer.parseInt(strArr[0]) + 12) * 60;
                parseInt2 = Integer.parseInt(strArr[1]);
            } else {
                parseInt = (Integer.parseInt(strArr[0]) - 12) * 60;
                parseInt2 = Integer.parseInt(strArr[1]);
            }
            return parseInt + parseInt2 + i;
        }
        return -1;
    }
}
