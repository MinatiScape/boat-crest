package com.coveiot.android.leonardo.performance;

import android.content.Context;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.khperformancecalculator.model.KHPActivityData;
import com.coveiot.android.khperformancecalculator.model.KHPSleepData;
import com.coveiot.android.leonardo.utils.GraphsUtility;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.sleepalgorithm.filtering.CZ0ParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.IDOParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MatrixParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MoyangParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.SmaParsedSleepDataF2;
import com.coveiot.android.sleepalgorithm.filtering.StrideParsedSleepDataV2NoAlgo;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepAlgoWithREM;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.utility.AppUtils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PerformanceDataFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<String> getAllActivityMode() {
            return CollectionsKt__CollectionsKt.mutableListOf(ActivityMode.WALK.name(), ActivityMode.RUN.name(), ActivityMode.BADMINTON.name(), ActivityMode.BASKETBALL.name(), ActivityMode.CLIMBING.name(), ActivityMode.CYCLE.name(), ActivityMode.DANCE.name(), ActivityMode.FOOTBALL.name(), ActivityMode.HIKING.name(), ActivityMode.MEDITATION.name(), ActivityMode.TENNIS.name(), ActivityMode.TREADMILL.name(), ActivityMode.WORKOUT.name(), ActivityMode.YOGA.name(), ActivityMode.SKIPPING.name(), ActivityMode.FREE_EXERCISE.name(), ActivityMode.ELLIPTICAL.name(), ActivityMode.ROWING.name());
        }

        @Nullable
        public final KHPActivityData getKHPActivityData(@NotNull EntityWorkoutSession entityWorkoutSession, @NotNull Context context) {
            String physicalActivityName;
            int intValue;
            int intValue2;
            float floatValue;
            int intValue3;
            float floatValue2;
            int intValue4;
            String swimmingStyle;
            int intValue5;
            int intValue6;
            int intValue7;
            int intValue8;
            int intValue9;
            float floatValue3;
            Integer categoryId;
            Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
            Intrinsics.checkNotNullParameter(context, "context");
            KHPActivityData kHPActivityData = null;
            if (!UserDataManager.getInstance(context).isOneKSupported() || (entityWorkoutSession.getCategoryId() != null && ((categoryId = entityWorkoutSession.getCategoryId()) == null || categoryId.intValue() != -1))) {
                if (entityWorkoutSession.getActivity_type() != null) {
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    if (!companion.isCZDevice(context) && !companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                        physicalActivityName = entityWorkoutSession.getActivity_type();
                        Intrinsics.checkNotNull(physicalActivityName);
                    } else if (UserDataManager.getInstance(context).isOneKSupported()) {
                        if (PreferenceManager.getCustomMessageConfiguration(context).getFilterBy().equals(WorkoutConstants.CATEGORY)) {
                            Integer categoryId2 = entityWorkoutSession.getCategoryId();
                            Intrinsics.checkNotNull(categoryId2);
                            physicalActivityName = PhysicalActivityRepository.Companion.getInstance(context).getPhysicalActivityCategoryName(categoryId2.intValue());
                        } else {
                            Integer activityId = entityWorkoutSession.getActivityId();
                            Intrinsics.checkNotNull(activityId);
                            int intValue10 = activityId.intValue();
                            Integer categoryId3 = entityWorkoutSession.getCategoryId();
                            Intrinsics.checkNotNull(categoryId3);
                            physicalActivityName = PhysicalActivityRepository.Companion.getInstance(context).getPhysicalActivityName(intValue10, categoryId3.intValue());
                        }
                    } else {
                        Integer activityId2 = entityWorkoutSession.getActivityId();
                        Intrinsics.checkNotNull(activityId2);
                        int intValue11 = activityId2.intValue();
                        Integer categoryId4 = entityWorkoutSession.getCategoryId();
                        Intrinsics.checkNotNull(categoryId4);
                        physicalActivityName = PhysicalActivityRepository.Companion.getInstance(context).getPhysicalActivityName(intValue11, categoryId4.intValue());
                    }
                    String str = physicalActivityName;
                    long start_time = entityWorkoutSession.getStart_time();
                    long end_time = entityWorkoutSession.getEnd_time();
                    float total_calories = entityWorkoutSession.getTotal_calories();
                    int total_distance = entityWorkoutSession.getTotal_distance();
                    int total_steps = entityWorkoutSession.getTotal_steps();
                    int avg_hr = entityWorkoutSession.getAvg_hr();
                    int min_hr = entityWorkoutSession.getMin_hr();
                    int max_hr = entityWorkoutSession.getMax_hr();
                    float pace = entityWorkoutSession.getPace();
                    String indoor_outdoor = entityWorkoutSession.getIndoor_outdoor();
                    int session_duration = entityWorkoutSession.getSession_duration();
                    if (entityWorkoutSession.getActivityId() == null) {
                        intValue = 0;
                    } else {
                        Integer activityId3 = entityWorkoutSession.getActivityId();
                        Intrinsics.checkNotNull(activityId3);
                        intValue = activityId3.intValue();
                    }
                    if (entityWorkoutSession.getCategoryId() == null) {
                        intValue2 = 0;
                    } else {
                        Integer categoryId5 = entityWorkoutSession.getCategoryId();
                        Intrinsics.checkNotNull(categoryId5);
                        intValue2 = categoryId5.intValue();
                    }
                    if (entityWorkoutSession.getAvgSpeed() == null) {
                        floatValue = 0.0f;
                    } else {
                        Float avgSpeed = entityWorkoutSession.getAvgSpeed();
                        Intrinsics.checkNotNull(avgSpeed);
                        floatValue = avgSpeed.floatValue();
                    }
                    if (entityWorkoutSession.getAvgStrideLength() == null) {
                        intValue3 = 0;
                    } else {
                        Integer avgStrideLength = entityWorkoutSession.getAvgStrideLength();
                        Intrinsics.checkNotNull(avgStrideLength);
                        intValue3 = avgStrideLength.intValue();
                    }
                    if (entityWorkoutSession.getMaxPace() == null) {
                        floatValue2 = 0.0f;
                    } else {
                        Float maxPace = entityWorkoutSession.getMaxPace();
                        Intrinsics.checkNotNull(maxPace);
                        floatValue2 = maxPace.floatValue();
                    }
                    if (entityWorkoutSession.getMaxStepFrequency() == null) {
                        intValue4 = 0;
                    } else {
                        Integer maxStepFrequency = entityWorkoutSession.getMaxStepFrequency();
                        Intrinsics.checkNotNull(maxStepFrequency);
                        intValue4 = maxStepFrequency.intValue();
                    }
                    if (entityWorkoutSession.getSwimmingStyle() == null) {
                        swimmingStyle = "";
                    } else {
                        swimmingStyle = entityWorkoutSession.getSwimmingStyle();
                        Intrinsics.checkNotNull(swimmingStyle);
                    }
                    String str2 = swimmingStyle;
                    if (entityWorkoutSession.getTotalStrokes() == null) {
                        intValue5 = 0;
                    } else {
                        Integer totalStrokes = entityWorkoutSession.getTotalStrokes();
                        Intrinsics.checkNotNull(totalStrokes);
                        intValue5 = totalStrokes.intValue();
                    }
                    if (entityWorkoutSession.getTotalLaps() == null) {
                        intValue6 = 0;
                    } else {
                        Integer totalLaps = entityWorkoutSession.getTotalLaps();
                        Intrinsics.checkNotNull(totalLaps);
                        intValue6 = totalLaps.intValue();
                    }
                    if (entityWorkoutSession.getAvgSwolf() == null) {
                        intValue7 = 0;
                    } else {
                        Integer avgSwolf = entityWorkoutSession.getAvgSwolf();
                        Intrinsics.checkNotNull(avgSwolf);
                        intValue7 = avgSwolf.intValue();
                    }
                    String session_id = entityWorkoutSession.getSession_id();
                    if (entityWorkoutSession.getAvgStrokeFreq() == null) {
                        intValue8 = 0;
                    } else {
                        Integer avgStrokeFreq = entityWorkoutSession.getAvgStrokeFreq();
                        Intrinsics.checkNotNull(avgStrokeFreq);
                        intValue8 = avgStrokeFreq.intValue();
                    }
                    if (entityWorkoutSession.getAvgStepFrequency() == null) {
                        intValue9 = 0;
                    } else {
                        Integer avgStepFrequency = entityWorkoutSession.getAvgStepFrequency();
                        Intrinsics.checkNotNull(avgStepFrequency);
                        intValue9 = avgStepFrequency.intValue();
                    }
                    if (entityWorkoutSession.getMinPace() == null) {
                        floatValue3 = 0.0f;
                    } else {
                        Float minPace = entityWorkoutSession.getMinPace();
                        Intrinsics.checkNotNull(minPace);
                        floatValue3 = minPace.floatValue();
                    }
                    kHPActivityData = new KHPActivityData(str, start_time, end_time, total_calories, total_distance, total_steps, avg_hr, min_hr, max_hr, pace, indoor_outdoor, session_duration, intValue, intValue2, floatValue, intValue3, floatValue2, intValue4, str2, intValue5, intValue6, intValue7, session_id, intValue8, intValue9, floatValue3);
                }
                return kHPActivityData;
            }
            return null;
        }

        @Nullable
        public final KHPSleepData getKHPSleepData(@NotNull Calendar cal, @NotNull List<? extends SleepDataModelForLastNight> sleepDataModelForLastNightLst, @NotNull Context context) {
            SmaParsedSleepDataF2 smaParsedSleepDataF2;
            SleepDataModel sleepDataModel;
            MoyangParsedSleepData moyangParsedSleepData;
            IDOParsedSleepData iDOParsedSleepData;
            MatrixParsedSleepData matrixParsedSleepData;
            CZ0ParsedSleepData cZ0ParsedSleepData;
            StrideParsedSleepDataV2NoAlgo strideParsedSleepDataV2NoAlgo;
            JStyleParsedSleepData jStyleParsedSleepData;
            JStyleSleepAlgoWithREM jStyleSleepAlgoWithREM;
            Intrinsics.checkNotNullParameter(cal, "cal");
            Intrinsics.checkNotNullParameter(sleepDataModelForLastNightLst, "sleepDataModelForLastNightLst");
            Intrinsics.checkNotNullParameter(context, "context");
            byte[] bArr = new byte[1440];
            Arrays.fill(bArr, 0, 1440, (byte) -1);
            int size = sleepDataModelForLastNightLst.size();
            for (int i = 0; i < size; i++) {
                int size2 = sleepDataModelForLastNightLst.get(i).getCodevalue().size();
                for (int i2 = 0; i2 < size2; i2++) {
                    int timeIndex = GraphsUtility.getTimeIndex(sleepDataModelForLastNightLst.get(i), i2);
                    if (timeIndex < 1440) {
                        Integer num = sleepDataModelForLastNightLst.get(i).getCodevalue().get(i2);
                        Intrinsics.checkNotNullExpressionValue(num, "sleepDataModelForLastNig…t.get(i).codevalue.get(j)");
                        int intValue = num.intValue();
                        if (intValue > -1) {
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            if (companion.isJstyleDevice(context) || companion.isSmaDevice(context) || companion.isMoyangDevice(context) || companion.isMatrixDevice(context) || companion.isIDODevice(context) || companion.isCZDevice(context) || companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
                                bArr[timeIndex] = (byte) intValue;
                            } else if (intValue != 3) {
                                bArr[timeIndex] = (byte) intValue;
                            } else {
                                bArr[timeIndex] = -1;
                            }
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
                    if (jStyleSleepAlgoWithREM != null && jStyleSleepAlgoWithREM.getSleepDataModel() != null) {
                        sleepDataModel = jStyleSleepAlgoWithREM.getSleepDataModel();
                    }
                    sleepDataModel = null;
                } else {
                    try {
                        jStyleParsedSleepData = new JStyleParsedSleepData(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        jStyleParsedSleepData = null;
                    }
                    if (jStyleParsedSleepData != null && jStyleParsedSleepData.getSleepDataModel() != null) {
                        sleepDataModel = jStyleParsedSleepData.getSleepDataModel();
                    }
                    sleepDataModel = null;
                }
            } else if (companion2.isSmaDevice(context)) {
                try {
                    smaParsedSleepDataF2 = new SmaParsedSleepDataF2(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    smaParsedSleepDataF2 = null;
                }
                if (smaParsedSleepDataF2 != null && smaParsedSleepDataF2.getSleepDataModel() != null) {
                    sleepDataModel = smaParsedSleepDataF2.getSleepDataModel();
                }
                sleepDataModel = null;
            } else if (companion2.isMoyangDevice(context)) {
                try {
                    moyangParsedSleepData = new MoyangParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e4) {
                    e4.printStackTrace();
                    moyangParsedSleepData = null;
                }
                if (moyangParsedSleepData != null && moyangParsedSleepData.getSleepDataModel() != null) {
                    sleepDataModel = moyangParsedSleepData.getSleepDataModel();
                }
                sleepDataModel = null;
            } else if (companion2.isIDODevice(context)) {
                try {
                    iDOParsedSleepData = new IDOParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e5) {
                    e5.printStackTrace();
                    iDOParsedSleepData = null;
                }
                if (iDOParsedSleepData != null && iDOParsedSleepData.getSleepDataModel() != null) {
                    sleepDataModel = iDOParsedSleepData.getSleepDataModel();
                }
                sleepDataModel = null;
            } else if (companion2.isMatrixDevice(context)) {
                try {
                    matrixParsedSleepData = new MatrixParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e6) {
                    e6.printStackTrace();
                    matrixParsedSleepData = null;
                }
                if (matrixParsedSleepData != null && matrixParsedSleepData.getSleepDataModel() != null) {
                    sleepDataModel = matrixParsedSleepData.getSleepDataModel();
                }
                sleepDataModel = null;
            } else if (companion2.isKaHaDeviceWithRem(context)) {
                try {
                    cZ0ParsedSleepData = new CZ0ParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e7) {
                    e7.printStackTrace();
                    cZ0ParsedSleepData = null;
                }
                Intrinsics.checkNotNull(cZ0ParsedSleepData);
                sleepDataModel = cZ0ParsedSleepData.getSleepDataModel();
            } else {
                try {
                    strideParsedSleepDataV2NoAlgo = new StrideParsedSleepDataV2NoAlgo(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e8) {
                    e8.printStackTrace();
                    strideParsedSleepDataV2NoAlgo = null;
                }
                if (strideParsedSleepDataV2NoAlgo != null && strideParsedSleepDataV2NoAlgo.getSleepDataModel() != null) {
                    sleepDataModel = strideParsedSleepDataV2NoAlgo.getSleepDataModel();
                }
                sleepDataModel = null;
            }
            if (sleepDataModel != null) {
                EntityProfile latestProfileTillDate = getLatestProfileTillDate(cal, context);
                int i3 = latestProfileTillDate != null ? latestProfileTillDate.sleepTarget : 0;
                int countTotalSleep = sleepDataModel.getCountTotalSleep();
                String formatDate = AppUtils.formatDate(new Date(cal.getTimeInMillis()), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …dd\"\n                    )");
                return new KHPSleepData(countTotalSleep, i3, formatDate);
            }
            return null;
        }

        @Nullable
        public final EntityProfile getLatestProfileTillDate(@NotNull Calendar cal, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(cal, "cal");
            Intrinsics.checkNotNullParameter(context, "context");
            EntityProfile latestProfileDataTillDate = ProfileRepository.getInstance().getLatestProfileDataTillDate(context, Long.valueOf(cal.getTimeInMillis()));
            return latestProfileDataTillDate == null ? ProfileRepository.getInstance().getLatestProfileData(context) : latestProfileDataTillDate;
        }

        public final boolean isTimeInCurrentWeek(long j) {
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(3);
            int i2 = calendar.get(1);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(j);
            return i == calendar2.get(3) && i2 == calendar2.get(1);
        }
    }
}
