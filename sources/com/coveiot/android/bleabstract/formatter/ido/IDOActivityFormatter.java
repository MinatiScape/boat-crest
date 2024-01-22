package com.coveiot.android.bleabstract.formatter.ido;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.khidodb.activities.EntityHealthActivityV3;
import com.coveiot.khidodb.activities.EntityHealthSwimV3;
import com.coveiot.khidodb.activities.KHHealthActivityV3ItemKMSpeed;
import com.coveiot.khidodb.activities.KHHealthActivityV3StepsItem;
import com.coveiot.khidodb.activities.KHHealthSwimV3Detail;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOActivityFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3352a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOActivityFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.ido.IDOActivityFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOActivityFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3353a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOActivityFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOActivityFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOActivityFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3353a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOActivityFormatter(Context context) {
        this.f3352a = context;
    }

    public /* synthetic */ IDOActivityFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final EntityHealthActivityV3 convertHealthActivityToEntity(@NotNull HealthActivityV3 healthActivityV3) {
        Intrinsics.checkNotNullParameter(healthActivityV3, "healthActivityV3");
        EntityHealthActivityV3 entityHealthActivityV3 = new EntityHealthActivityV3(healthActivityV3.day, healthActivityV3.month, healthActivityV3.year, healthActivityV3.hour, healthActivityV3.minute, healthActivityV3.second, IDOUtils.INSTANCE.getMacAddress(this.f3352a));
        entityHealthActivityV3.setHr_data_interval_minute(healthActivityV3.hr_data_interval_minute);
        entityHealthActivityV3.setType(healthActivityV3.type);
        entityHealthActivityV3.setStep(healthActivityV3.step);
        entityHealthActivityV3.setDurations(healthActivityV3.durations);
        entityHealthActivityV3.setCalories(healthActivityV3.calories);
        entityHealthActivityV3.setDistance(healthActivityV3.distance);
        entityHealthActivityV3.setAvg_hr_value(healthActivityV3.avg_hr_value);
        entityHealthActivityV3.setMax_hr_value(healthActivityV3.max_hr_value);
        entityHealthActivityV3.setWarmUpMins(healthActivityV3.warmUpMins);
        entityHealthActivityV3.setWarm_up_time(healthActivityV3.warm_up_time);
        entityHealthActivityV3.setBurn_fat_mins(healthActivityV3.burn_fat_mins);
        entityHealthActivityV3.setFat_burning_time(healthActivityV3.fat_burning_time);
        entityHealthActivityV3.setAerobic_mins(healthActivityV3.aerobic_mins);
        entityHealthActivityV3.setAerobic_exercise_time(healthActivityV3.aerobic_exercise_time);
        entityHealthActivityV3.setAnaerobicMins(healthActivityV3.anaerobicMins);
        entityHealthActivityV3.setAnaerobic_exercise_time(healthActivityV3.anaerobic_exercise_time);
        entityHealthActivityV3.setLimit_mins(healthActivityV3.limit_mins);
        entityHealthActivityV3.setExtreme_exercise_time(healthActivityV3.extreme_exercise_time);
        entityHealthActivityV3.setHr_data_vlaue(healthActivityV3.hr_data_vlaue);
        entityHealthActivityV3.setFast_km_speed(healthActivityV3.fast_km_speed);
        entityHealthActivityV3.setItems_mi_speed(healthActivityV3.items_mi_speed);
        entityHealthActivityV3.setFrequency_items(healthActivityV3.frequency_items);
        entityHealthActivityV3.setKm_speed(healthActivityV3.km_speed);
        entityHealthActivityV3.setAvg_speed(healthActivityV3.avg_speed);
        entityHealthActivityV3.setMax_speed(healthActivityV3.max_speed);
        entityHealthActivityV3.setAvg_step_frequency(healthActivityV3.avg_step_frequency);
        entityHealthActivityV3.setMax_step_frequency(healthActivityV3.max_step_frequency);
        entityHealthActivityV3.setAvg_step_stride(healthActivityV3.avg_step_stride);
        entityHealthActivityV3.setMax_step_stride(healthActivityV3.max_step_stride);
        entityHealthActivityV3.setSport_start_type(healthActivityV3.sport_start_type);
        entityHealthActivityV3.setConnect_app(healthActivityV3.connect_app);
        entityHealthActivityV3.setAvg_pace_speed(healthActivityV3.avg_pace_speed);
        entityHealthActivityV3.setFast_pace_speed(healthActivityV3.fast_pace_speed);
        entityHealthActivityV3.setTraining_effect(healthActivityV3.training_effect);
        entityHealthActivityV3.setVO2max(healthActivityV3.vO2max);
        entityHealthActivityV3.setRecovery_time_year(healthActivityV3.recovery_time_year);
        entityHealthActivityV3.setRecovery_time_mon(healthActivityV3.recovery_time_mon);
        entityHealthActivityV3.setRecovery_time_day(healthActivityV3.recovery_time_day);
        entityHealthActivityV3.setRecovery_time_hour(healthActivityV3.recovery_time_hour);
        entityHealthActivityV3.setRecovery_time_min(healthActivityV3.recovery_time_min);
        entityHealthActivityV3.setRecovery_time_s(healthActivityV3.recovery_time_s);
        entityHealthActivityV3.setMin_hr_value(healthActivityV3.min_hr_value);
        entityHealthActivityV3.setPace_speed_items(healthActivityV3.pace_speed_items);
        entityHealthActivityV3.setPaddle_number_items(healthActivityV3.paddle_number_items);
        entityHealthActivityV3.setPaddle_frequency_items(healthActivityV3.paddle_frequency_items);
        entityHealthActivityV3.setTread_frequency_items(healthActivityV3.tread_frequency_items);
        entityHealthActivityV3.setPaddle_number_count(healthActivityV3.paddle_number_count);
        entityHealthActivityV3.setPaddle_frequency_count(healthActivityV3.paddle_frequency_count);
        entityHealthActivityV3.setTread_frequency_count(healthActivityV3.tread_frequency_count);
        entityHealthActivityV3.setEnd_month(healthActivityV3.end_month);
        entityHealthActivityV3.setEnd_day(healthActivityV3.end_day);
        entityHealthActivityV3.setEnd_hour(healthActivityV3.end_hour);
        entityHealthActivityV3.setEnd_minute(healthActivityV3.end_minute);
        if (healthActivityV3.items != null) {
            ArrayList arrayList = new ArrayList();
            int size = healthActivityV3.items.size();
            for (int i = 0; i < size; i++) {
                KHHealthActivityV3StepsItem kHHealthActivityV3StepsItem = new KHHealthActivityV3StepsItem();
                kHHealthActivityV3StepsItem.setSteps(healthActivityV3.items.get(i).steps);
                kHHealthActivityV3StepsItem.setCalories(healthActivityV3.items.get(i).calories);
                kHHealthActivityV3StepsItem.setDistance(healthActivityV3.items.get(i).distance);
                arrayList.add(kHHealthActivityV3StepsItem);
            }
            entityHealthActivityV3.setItems(arrayList);
        }
        if (healthActivityV3.items_km_speed != null) {
            ArrayList arrayList2 = new ArrayList();
            int size2 = healthActivityV3.items_km_speed.size();
            for (int i2 = 0; i2 < size2; i2++) {
                KHHealthActivityV3ItemKMSpeed kHHealthActivityV3ItemKMSpeed = new KHHealthActivityV3ItemKMSpeed();
                kHHealthActivityV3ItemKMSpeed.setSecond(healthActivityV3.items_km_speed.get(i2).second);
                arrayList2.add(kHHealthActivityV3ItemKMSpeed);
            }
            entityHealthActivityV3.setItems_km_speed(arrayList2);
        }
        entityHealthActivityV3.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(healthActivityV3.year, healthActivityV3.month, healthActivityV3.day, healthActivityV3.hour, healthActivityV3.minute, healthActivityV3.second));
        return entityHealthActivityV3;
    }

    @Nullable
    public final EntityHealthSwimV3 convertHealthSwimActivityToEntity(@NotNull HealthSwimming healthSwimming) {
        Intrinsics.checkNotNullParameter(healthSwimming, "healthSwimming");
        EntityHealthSwimV3 entityHealthSwimV3 = new EntityHealthSwimV3(healthSwimming.day, healthSwimming.month, healthSwimming.year, healthSwimming.hour, healthSwimming.minute, healthSwimming.second, IDOUtils.INSTANCE.getMacAddress(this.f3352a));
        entityHealthSwimV3.setType(healthSwimming.type);
        entityHealthSwimV3.setDurations(healthSwimming.duration);
        entityHealthSwimV3.setCalories(healthSwimming.calories);
        entityHealthSwimV3.setDistance(healthSwimming.distance);
        entityHealthSwimV3.setTrips(healthSwimming.trips);
        entityHealthSwimV3.setAverageSWOLF(healthSwimming.averageSWOLF);
        entityHealthSwimV3.setTotalStrokesNumber(healthSwimming.totalStrokesNumber);
        entityHealthSwimV3.setSwimmingPosture(healthSwimming.swimmingPosture);
        entityHealthSwimV3.setPoolDistance(healthSwimming.poolDistance);
        entityHealthSwimV3.setConfirmDistance(healthSwimming.confirmDistance);
        entityHealthSwimV3.setAvg_speed(healthSwimming.avg_speed);
        entityHealthSwimV3.setAvg_frequency(healthSwimming.avg_frequency);
        if (healthSwimming.items != null) {
            ArrayList arrayList = new ArrayList();
            int size = healthSwimming.items.size();
            for (int i = 0; i < size; i++) {
                KHHealthSwimV3Detail kHHealthSwimV3Detail = new KHHealthSwimV3Detail();
                kHHealthSwimV3Detail.setDuration(healthSwimming.items.get(i).duration);
                kHHealthSwimV3Detail.setStrokesNumber(healthSwimming.items.get(i).strokesNumber);
                kHHealthSwimV3Detail.setSwolf(healthSwimming.items.get(i).swolf);
                kHHealthSwimV3Detail.setSwimmingPosture(healthSwimming.items.get(i).swimmingPosture);
                kHHealthSwimV3Detail.setDistance(healthSwimming.items.get(i).distance);
                kHHealthSwimV3Detail.setFrequency(healthSwimming.items.get(i).frequency);
                kHHealthSwimV3Detail.setSpeed(healthSwimming.items.get(i).speed);
                kHHealthSwimV3Detail.setStop_time(healthSwimming.items.get(i).stop_time);
                kHHealthSwimV3Detail.setDifference_time(healthSwimming.items.get(i).difference_time);
                arrayList.add(kHHealthSwimV3Detail);
            }
            entityHealthSwimV3.setItems(arrayList);
        }
        entityHealthSwimV3.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(healthSwimming.year, healthSwimming.month, healthSwimming.day, healthSwimming.hour, healthSwimming.minute, healthSwimming.second));
        return entityHealthSwimV3;
    }

    @NotNull
    public final List<ActivityModeSummaryResponse> getActivityModeSummaryData(@NotNull List<EntityHealthActivityV3> healthActivityV3List, @Nullable List<EntityHealthSwimV3> list) {
        Intrinsics.checkNotNullParameter(healthActivityV3List, "healthActivityV3List");
        ArrayList arrayList = new ArrayList();
        for (EntityHealthActivityV3 entityHealthActivityV3 : healthActivityV3List) {
            ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
            activityModeSummaryResponse.setMacAddress(entityHealthActivityV3.getMacAddress());
            activityModeSummaryResponse.setActivityDuration(entityHealthActivityV3.getDurations());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(entityHealthActivityV3.getTimestamp());
            if (entityHealthActivityV3.getDurations() != 0) {
                calendar.add(13, entityHealthActivityV3.getDurations());
            }
            activityModeSummaryResponse.setStartDateTime(Long.valueOf(entityHealthActivityV3.getTimestamp()));
            activityModeSummaryResponse.setEndDateTime(Long.valueOf(calendar.getTimeInMillis()));
            IDOUtils iDOUtils = IDOUtils.INSTANCE;
            activityModeSummaryResponse.setDate(iDOUtils.convertTimestampToDate(entityHealthActivityV3.getTimestamp()));
            activityModeSummaryResponse.setTotalSteps(entityHealthActivityV3.getStep());
            activityModeSummaryResponse.setTotalCalories(entityHealthActivityV3.getCalories());
            activityModeSummaryResponse.setTotalDistance(entityHealthActivityV3.getDistance() / 1000);
            activityModeSummaryResponse.setActivityMode(iDOUtils.getActivityMode(entityHealthActivityV3.getType()));
            activityModeSummaryResponse.setActivitySite(iDOUtils.getActivitySite(entityHealthActivityV3.getType()));
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int[] hr_data_vlaue = entityHealthActivityV3.getHr_data_vlaue();
            Intrinsics.checkNotNull(hr_data_vlaue);
            int length = hr_data_vlaue.length;
            for (int i = 0; i < length; i++) {
                ActivityHeartRateSample activityHeartRateSample = new ActivityHeartRateSample();
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(entityHealthActivityV3.getTimestamp());
                calendar2.add(13, i * 5);
                activityHeartRateSample.setHrTimeStamp(calendar2.getTimeInMillis());
                activityHeartRateSample.setHrValue(hr_data_vlaue[i]);
                arrayList2.add(activityHeartRateSample);
            }
            List<Integer> frequency_items = entityHealthActivityV3.getFrequency_items();
            Intrinsics.checkNotNull(frequency_items);
            int size = frequency_items.size();
            for (int i2 = 0; i2 < size; i2++) {
                ActivityStepsSample activityStepsSample = new ActivityStepsSample();
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTimeInMillis(entityHealthActivityV3.getTimestamp());
                calendar3.add(12, i2);
                activityStepsSample.setStepsTimeStamp(calendar3.getTimeInMillis());
                activityStepsSample.setStepsValue(frequency_items.get(i2).intValue());
                arrayList3.add(activityStepsSample);
            }
            activityModeSummaryResponse.setHeartRateSampleList(arrayList2);
            activityModeSummaryResponse.setStepsSampleList(arrayList3);
            ArrayList arrayList4 = new ArrayList();
            List<ActivityHeartRateSample> heartRateSampleList = activityModeSummaryResponse.getHeartRateSampleList();
            Intrinsics.checkNotNull(heartRateSampleList);
            int size2 = heartRateSampleList.size();
            for (int i3 = 0; i3 < size2; i3++) {
                List<ActivityHeartRateSample> heartRateSampleList2 = activityModeSummaryResponse.getHeartRateSampleList();
                Intrinsics.checkNotNull(heartRateSampleList2);
                arrayList4.add(Integer.valueOf(heartRateSampleList2.get(i3).getHrValue()));
            }
            if (arrayList4.size() > 0) {
                Integer minValueFromList = BleApiUtils.INSTANCE.getMinValueFromList(arrayList4);
                Intrinsics.checkNotNull(minValueFromList);
                activityModeSummaryResponse.setMinHeartRate(minValueFromList.intValue());
            }
            if (arrayList4.size() > 0) {
                Integer maxValueFromList = BleApiUtils.INSTANCE.getMaxValueFromList(arrayList4);
                Intrinsics.checkNotNull(maxValueFromList);
                activityModeSummaryResponse.setMaxHeartRate(maxValueFromList.intValue());
            }
            if (arrayList4.size() > 0) {
                Integer avgValueFromList = BleApiUtils.INSTANCE.getAvgValueFromList(arrayList4);
                Intrinsics.checkNotNull(avgValueFromList);
                activityModeSummaryResponse.setHeartRate(avgValueFromList.intValue());
            }
            activityModeSummaryResponse.setLowSamplingRate(5);
            if (entityHealthActivityV3.getConnect_app() == 1) {
                activityModeSummaryResponse.setAppConnectivityCode("CONNECTED");
            } else {
                activityModeSummaryResponse.setAppConnectivityCode("DISCONNECTED");
            }
            activityModeSummaryResponse.setAvgStepFrequency(entityHealthActivityV3.getAvg_step_frequency());
            activityModeSummaryResponse.setMaxStepFrequency(entityHealthActivityV3.getMax_step_frequency());
            activityModeSummaryResponse.setAvgSpeed(entityHealthActivityV3.getAvg_speed());
            activityModeSummaryResponse.setMaxSpeed(entityHealthActivityV3.getMax_speed());
            activityModeSummaryResponse.setAvgStrideLength(entityHealthActivityV3.getAvg_step_stride());
            activityModeSummaryResponse.setMaxStrideLength(entityHealthActivityV3.getMax_step_stride());
            activityModeSummaryResponse.setAvgPace(entityHealthActivityV3.getKm_speed());
            activityModeSummaryResponse.setMaxPace(entityHealthActivityV3.getFast_km_speed());
            TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
            timeSpentHeartRateZone.setZone0TimeInSecs((entityHealthActivityV3.getWarmUpMins() * 60) + entityHealthActivityV3.getWarm_up_time());
            timeSpentHeartRateZone.setZone1TimeInSecs((entityHealthActivityV3.getBurn_fat_mins() * 60) + entityHealthActivityV3.getFat_burning_time());
            timeSpentHeartRateZone.setZone2TimeInSecs((entityHealthActivityV3.getAerobic_mins() * 60) + entityHealthActivityV3.getAerobic_exercise_time());
            timeSpentHeartRateZone.setZone3TimeInSecs((entityHealthActivityV3.getAnaerobicMins() * 60) + entityHealthActivityV3.getAnaerobic_exercise_time());
            timeSpentHeartRateZone.setZone4TimeInSecs((entityHealthActivityV3.getLimit_mins() * 60) + entityHealthActivityV3.getExtreme_exercise_time());
            activityModeSummaryResponse.setHeartRateTimeZone(timeSpentHeartRateZone);
            arrayList.add(activityModeSummaryResponse);
        }
        if (list != null) {
            for (EntityHealthSwimV3 entityHealthSwimV3 : list) {
                ActivityModeSummaryResponse activityModeSummaryResponse2 = new ActivityModeSummaryResponse();
                activityModeSummaryResponse2.setMacAddress(entityHealthSwimV3.getMacAddress());
                activityModeSummaryResponse2.setActivityDuration(entityHealthSwimV3.getDurations());
                Calendar calendar4 = Calendar.getInstance();
                calendar4.setTimeInMillis(entityHealthSwimV3.getTimestamp());
                if (entityHealthSwimV3.getDurations() != 0) {
                    calendar4.add(13, entityHealthSwimV3.getDurations());
                }
                activityModeSummaryResponse2.setStartDateTime(Long.valueOf(entityHealthSwimV3.getTimestamp()));
                activityModeSummaryResponse2.setEndDateTime(Long.valueOf(calendar4.getTimeInMillis()));
                activityModeSummaryResponse2.setDate(IDOUtils.INSTANCE.convertTimestampToDate(entityHealthSwimV3.getTimestamp()));
                activityModeSummaryResponse2.setTotalCalories(entityHealthSwimV3.getCalories());
                activityModeSummaryResponse2.setTotalDistance(entityHealthSwimV3.getDistance() / 1000);
                activityModeSummaryResponse2.setActivityMode(CoveApiConstants.SWIM);
                if (entityHealthSwimV3.getType() == 1) {
                    activityModeSummaryResponse2.setActivitySite("INDOOR");
                } else {
                    activityModeSummaryResponse2.setActivitySite("OUTDOOR");
                }
                activityModeSummaryResponse2.setTrips(entityHealthSwimV3.getTrips());
                activityModeSummaryResponse2.setAverageSWOLF(entityHealthSwimV3.getAverageSWOLF());
                activityModeSummaryResponse2.setTotalStrokesNumber(entityHealthSwimV3.getTotalStrokesNumber());
                activityModeSummaryResponse2.setSwimmingPosture(entityHealthSwimV3.getSwimmingPosture());
                activityModeSummaryResponse2.setPoolDistance(entityHealthSwimV3.getPoolDistance());
                activityModeSummaryResponse2.setConfirmDistance(entityHealthSwimV3.getConfirmDistance());
                activityModeSummaryResponse2.setAvgFrequency(entityHealthSwimV3.getAvg_frequency());
                activityModeSummaryResponse2.setAvgSpeed(entityHealthSwimV3.getAvg_speed());
                arrayList.add(activityModeSummaryResponse2);
            }
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3352a;
    }
}
