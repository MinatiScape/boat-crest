package com.coveiot.android.bleabstract.formatter.eastapex;

import android.content.Context;
import com.apex.bluetooth.model.EABleMultiData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone;
import com.coveiot.android.bleabstract.utils.eastapexUtils.EastApexUtils;
import com.coveiot.kheastapexdb.activity.EntityEAActivityData;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastApexActivityFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3340a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexActivityFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.eastapex.EastApexActivityFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexActivityFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3341a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexActivityFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexActivityFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexActivityFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3341a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EastApexActivityFormatter(Context context) {
        this.f3340a = context;
    }

    public /* synthetic */ EastApexActivityFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final ArrayList<EntityEAActivityData> convertEAActivityDataToEntity(@Nullable List<? extends EABleMultiData> list) {
        if (list != null) {
            ArrayList<EntityEAActivityData> arrayList = new ArrayList<>();
            Iterator<? extends EABleMultiData> it = list.iterator();
            while (it.hasNext()) {
                EABleMultiData next = it.next();
                Long valueOf = next != null ? Long.valueOf(next.begin_time_stamp) : null;
                Intrinsics.checkNotNull(valueOf);
                long j = 1000;
                EntityEAActivityData entityEAActivityData = new EntityEAActivityData(valueOf.longValue() * j, BleApiManager.getInstance(this.f3340a).getBleApi().getMacAddress());
                entityEAActivityData.setActivityType(next.e_type.value);
                entityEAActivityData.setEndTimestamp(next.end_time_stamp * j);
                entityEAActivityData.setSteps(next.steps);
                entityEAActivityData.setCalorie(next.calorie);
                entityEAActivityData.setDistance(next.distance);
                entityEAActivityData.setDuration(next.duration);
                entityEAActivityData.setTrainingEffectNormal(next.training_effect_normal);
                entityEAActivityData.setTrainingEffectWarmUp(next.training_effect_warmUp);
                entityEAActivityData.setTrainingEffectFatConsumption(next.training_effect_fatconsumption);
                entityEAActivityData.setTrainingEffectAerobic(next.training_effect_aerobic);
                entityEAActivityData.setTrainingEffectAnaerobic(next.training_effect_anaerobic);
                entityEAActivityData.setTrainingEffectLimit(next.training_effect_limit);
                entityEAActivityData.setTrainingEffectFatConsumption(next.training_effect_fatconsumption);
                entityEAActivityData.setTrainingEffectAerobic(next.training_effect_aerobic);
                entityEAActivityData.setAvgHeartRate(next.average_heart_rate);
                entityEAActivityData.setAvgTemperature(next.average_temperature);
                entityEAActivityData.setAvgSpeed(next.average_speed);
                entityEAActivityData.setAvgPace(next.average_pace);
                entityEAActivityData.setAvgStepFreq(next.average_step_freq);
                entityEAActivityData.setAvgStride(next.average_stride);
                entityEAActivityData.setAvgAltitude(next.average_altitude);
                entityEAActivityData.setMaxHeartRate(next.average_heart_rate_max);
                entityEAActivityData.setMinHeartRate(next.average_heart_rate_min);
                entityEAActivityData.setTimeStamp(next.begin_time_stamp * j);
                arrayList.add(entityEAActivityData);
            }
            return arrayList;
        }
        return null;
    }

    @NotNull
    public final ActivityModeSummaryResponse getActivityModeSummaryData(@NotNull EntityEAActivityData activityResponseData) {
        Intrinsics.checkNotNullParameter(activityResponseData, "activityResponseData");
        ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
        activityModeSummaryResponse.setMacAddress(activityResponseData.getMacAddress());
        activityModeSummaryResponse.setActivityDuration(activityResponseData.getDuration());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(activityResponseData.getTimeStamp());
        if (activityResponseData.getDuration() != 0) {
            calendar.add(13, activityResponseData.getDuration());
        }
        activityModeSummaryResponse.setStartDateTime(Long.valueOf(activityResponseData.getTimeStamp()));
        activityModeSummaryResponse.setEndDateTime(Long.valueOf(calendar.getTimeInMillis()));
        activityModeSummaryResponse.setDate(BleApiUtils.INSTANCE.getDateFromTimeStamp(activityResponseData.getTimeStamp()));
        activityModeSummaryResponse.setTotalSteps(activityResponseData.getSteps());
        activityModeSummaryResponse.setAvgPace(activityResponseData.getAvgPace());
        activityModeSummaryResponse.setTotalCalories(activityResponseData.getCalorie() / 1000);
        activityModeSummaryResponse.setTotalDistance(activityResponseData.getDistance() / 100000);
        EastApexUtils eastApexUtils = EastApexUtils.INSTANCE;
        activityModeSummaryResponse.setActivityMode(eastApexUtils.getActivityMode(activityResponseData.getActivityType()));
        activityModeSummaryResponse.setActivitySite(eastApexUtils.getActivitySite(activityResponseData.getActivityType()));
        LogHelper.d("EastApexActivityFormatter", "getActivityModeSummaryData entity data activityType " + activityResponseData.getActivityType() + "  converted data activityMode " + activityModeSummaryResponse.getActivityMode() + " activitySite " + activityModeSummaryResponse.getActivitySite());
        if (activityResponseData.getMinHeartRate() > 0) {
            activityModeSummaryResponse.setMinHeartRate(activityResponseData.getMinHeartRate());
        }
        if (activityResponseData.getMaxHeartRate() > 0) {
            activityModeSummaryResponse.setMaxHeartRate(activityResponseData.getMaxHeartRate());
        }
        if (activityResponseData.getAvgHeartRate() > 0) {
            activityModeSummaryResponse.setHeartRate(activityResponseData.getAvgHeartRate());
        }
        activityModeSummaryResponse.setLowSamplingRate(1);
        activityModeSummaryResponse.setAvgStepFrequency(activityResponseData.getAvgStepFreq());
        activityModeSummaryResponse.setAvgSpeed(activityResponseData.getAvgSpeed());
        activityModeSummaryResponse.setAvgStrideLength(activityResponseData.getAvgStride());
        TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
        timeSpentHeartRateZone.setZone0TimeInSecs(activityResponseData.getTrainingEffectWarmUp());
        timeSpentHeartRateZone.setZone1TimeInSecs(activityResponseData.getTrainingEffectFatConsumption());
        timeSpentHeartRateZone.setZone2TimeInSecs(activityResponseData.getTrainingEffectAerobic());
        timeSpentHeartRateZone.setZone3TimeInSecs(activityResponseData.getTrainingEffectAnaerobic());
        timeSpentHeartRateZone.setZone4TimeInSecs(activityResponseData.getTrainingEffectLimit());
        activityModeSummaryResponse.setHeartRateTimeZone(timeSpentHeartRateZone);
        return activityModeSummaryResponse;
    }

    @NotNull
    public final Context getContext() {
        return this.f3340a;
    }
}
