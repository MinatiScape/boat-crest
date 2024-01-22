package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ActivityGPSSample;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.activity.KhActivityRepository;
import com.coveiot.khsmadb.activity.KhBleActivity;
import com.coveiot.khsmadb.heartrate.KhBleHeartRate;
import com.coveiot.khsmadb.heartrate.KhHeartRateRepository;
import com.coveiot.khsmadb.location.KhLocation;
import com.coveiot.khsmadb.location.KhLocationRepository;
import com.coveiot.khsmadb.workout.KhBleWorkout;
import com.google.android.gms.maps.model.LatLng;
import com.szabh.smable3.entity.BleWorkout2;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMAWorkout2Formatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3335a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMAWorkout2Formatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMAWorkout2Formatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMAWorkout2Formatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3336a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMAWorkout2Formatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMAWorkout2Formatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMAWorkout2Formatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3336a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMAWorkout2Formatter(Context context) {
        this.f3335a = context;
    }

    public /* synthetic */ SMAWorkout2Formatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f3335a;
    }

    @NotNull
    public final List<KhBleWorkout> getKhBleWorkoutData(@NotNull String macAddress, @Nullable List<BleWorkout2> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleWorkout2 bleWorkout2 : list) {
                ArrayList arrayList2 = arrayList;
                arrayList2.add(new KhBleWorkout(bleWorkout2.getMStart(), bleWorkout2.getMEnd(), bleWorkout2.getMDuration(), bleWorkout2.getMAltitude(), bleWorkout2.getMAirPressure(), bleWorkout2.getMSpm(), bleWorkout2.getMMode(), bleWorkout2.getMStep(), bleWorkout2.getMDistance(), bleWorkout2.getMCalorie(), bleWorkout2.getMSpeed(), bleWorkout2.getMPace(), macAddress, bleWorkout2.getMAvgBpm(), bleWorkout2.getMMaxBpm(), bleWorkout2.getMMinBpm(), 0, bleWorkout2.getMMaxSpm(), bleWorkout2.getMMinSpm(), bleWorkout2.getMMaxPace(), bleWorkout2.getMMinPace(), 65536, null));
                arrayList = arrayList2;
            }
        }
        return arrayList;
    }

    @Nullable
    public final List<ActivityModeSummaryResponse> getSportModeHistoryData(@NotNull String macAddress, @Nullable List<KhBleWorkout> list) {
        boolean z;
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        if (list == null) {
            return null;
        }
        Iterator<KhBleWorkout> it = list.iterator();
        while (it.hasNext()) {
            KhBleWorkout next = it.next();
            ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
            SmaUtils smaUtils = SmaUtils.INSTANCE;
            smaUtils.convertSDKTime(next.getMStart());
            int mStep = next.getMStep();
            int mDistance = next.getMDistance();
            int mCalorie = next.getMCalorie();
            int mDuration = next.getMDuration();
            String valueOf = String.valueOf(next.getMMode());
            int mPace = next.getMPace();
            activityModeSummaryResponse.setStartDateTime(Long.valueOf(smaUtils.convertSDKTimeToCalender(next.getMStart()).getTimeInMillis()));
            activityModeSummaryResponse.setEndDateTime(Long.valueOf(smaUtils.convertSDKTimeToCalender(next.getMEnd()).getTimeInMillis()));
            activityModeSummaryResponse.setTotalSteps(mStep);
            double d = 1000;
            activityModeSummaryResponse.setTotalDistance(mDistance / d);
            activityModeSummaryResponse.setTotalCalories(mCalorie / d);
            activityModeSummaryResponse.setActivityDuration(mDuration);
            if (valueOf != null) {
                String activityMode = smaUtils.getActivityMode(Integer.parseInt(valueOf));
                Intrinsics.checkNotNull(activityMode);
                activityModeSummaryResponse.setActivityMode(activityMode);
                activityModeSummaryResponse.setActivitySite(smaUtils.getActivitySite(next.getMMode()));
            }
            activityModeSummaryResponse.setPaceInSeconds(mPace);
            activityModeSummaryResponse.setAvgPace(mPace);
            activityModeSummaryResponse.setMaxPace(next.getMMaxPace());
            activityModeSummaryResponse.setMinPace(next.getMMinPace());
            activityModeSummaryResponse.setAvgSpeed(next.getMSpeed());
            activityModeSummaryResponse.setAvgStepFrequency(next.getMSpm());
            activityModeSummaryResponse.setMaxStepFrequency(next.getMMaxSpm());
            List<KhBleActivity> activityListBetweenTime = KhActivityRepository.Companion.getInstance(this.f3335a).getActivityListBetweenTime(macAddress, next.getMStart(), next.getMEnd());
            if (!(activityListBetweenTime == null || activityListBetweenTime.isEmpty())) {
                activityModeSummaryResponse.setStepsSampleList(new ArrayList());
                int mStep2 = ((KhBleActivity) CollectionsKt___CollectionsKt.first((List<? extends Object>) activityListBetweenTime)).getMStep();
                int mCalorie2 = ((KhBleActivity) CollectionsKt___CollectionsKt.first((List<? extends Object>) activityListBetweenTime)).getMCalorie();
                double mDistance2 = ((KhBleActivity) CollectionsKt___CollectionsKt.first((List<? extends Object>) activityListBetweenTime)).getMDistance();
                for (KhBleActivity khBleActivity : activityListBetweenTime) {
                    ActivityStepsSample activityStepsSample = new ActivityStepsSample();
                    activityStepsSample.setStepsValue(khBleActivity.getMStep() - mStep2);
                    activityStepsSample.setCalories((khBleActivity.getMCalorie() - mCalorie2) / 10000.0d);
                    String format = decimalFormat.format(((khBleActivity.getMDistance() - mDistance2) / 10000.0d) / d);
                    Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format((kh…stance) / 10000.0 / 1000)");
                    activityStepsSample.setDistance(Double.parseDouble(format));
                    mStep2 = khBleActivity.getMStep();
                    mCalorie2 = khBleActivity.getMCalorie();
                    mDistance2 = khBleActivity.getMDistance();
                    DecimalFormat decimalFormat2 = decimalFormat;
                    activityStepsSample.setStepsTimeStamp(SmaUtils.INSTANCE.convertSDKTimeToCalender(khBleActivity.getMTime()).getTimeInMillis());
                    List<ActivityStepsSample> stepsSampleList = activityModeSummaryResponse.getStepsSampleList();
                    Intrinsics.checkNotNull(stepsSampleList, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.android.bleabstract.models.ActivityStepsSample>");
                    TypeIntrinsics.asMutableList(stepsSampleList).add(activityStepsSample);
                    it = it;
                    decimalFormat = decimalFormat2;
                }
            }
            DecimalFormat decimalFormat3 = decimalFormat;
            Iterator<KhBleWorkout> it2 = it;
            List<KhBleHeartRate> heartRateDataListBetweenTime = KhHeartRateRepository.Companion.getInstance(this.f3335a).getHeartRateDataListBetweenTime(macAddress, next.getMStart(), next.getMEnd());
            if (heartRateDataListBetweenTime != null && (!heartRateDataListBetweenTime.isEmpty())) {
                activityModeSummaryResponse.setHeartRateSampleList(new ArrayList());
                int i = 0;
                int i2 = 0;
                for (KhBleHeartRate khBleHeartRate : heartRateDataListBetweenTime) {
                    ActivityHeartRateSample activityHeartRateSample = new ActivityHeartRateSample();
                    activityHeartRateSample.setHrValue(khBleHeartRate.getMBpm());
                    activityHeartRateSample.setHrTimeStamp(SmaUtils.INSTANCE.convertSDKTimeToCalender(khBleHeartRate.getMTime()).getTimeInMillis());
                    List<ActivityHeartRateSample> heartRateSampleList = activityModeSummaryResponse.getHeartRateSampleList();
                    Intrinsics.checkNotNull(heartRateSampleList, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.android.bleabstract.models.ActivityHeartRateSample>");
                    TypeIntrinsics.asMutableList(heartRateSampleList).add(activityHeartRateSample);
                    if (i2 < khBleHeartRate.getMBpm()) {
                        i2 = khBleHeartRate.getMBpm();
                    }
                    if (i == 0 || (i > khBleHeartRate.getMBpm() && khBleHeartRate.getMBpm() != 0)) {
                        i = khBleHeartRate.getMBpm();
                    }
                    khBleHeartRate.getMBpm();
                }
                activityModeSummaryResponse.setHeartRate(next.getMAvgBpm());
                activityModeSummaryResponse.setMinHeartRate(next.getMMinBpm());
                activityModeSummaryResponse.setMaxHeartRate(next.getMMaxBpm());
            }
            activityModeSummaryResponse.setGpsSampleList(new ArrayList());
            List<KhLocation> locationDataListBetweenTime = KhLocationRepository.Companion.getInstance(this.f3335a).getLocationDataListBetweenTime(macAddress, next.getMStart(), next.getMEnd());
            if (locationDataListBetweenTime != null) {
                z = true;
                if (!locationDataListBetweenTime.isEmpty()) {
                    activityModeSummaryResponse.setLowSamplingRate(1);
                }
            } else {
                z = true;
            }
            if (locationDataListBetweenTime != null && (locationDataListBetweenTime.isEmpty() ^ z)) {
                for (KhLocation khLocation : locationDataListBetweenTime) {
                    ActivityGPSSample activityGPSSample = new ActivityGPSSample();
                    activityGPSSample.setGpsTimeStamp(SmaUtils.INSTANCE.convertSDKTimeToCalender(khLocation.getMTime()).getTimeInMillis());
                    activityGPSSample.setLocation(new LatLng(khLocation.getMLatitude(), khLocation.getMLongitude()));
                    List<ActivityGPSSample> gpsSampleList = activityModeSummaryResponse.getGpsSampleList();
                    Intrinsics.checkNotNull(gpsSampleList, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.android.bleabstract.models.ActivityGPSSample>");
                    TypeIntrinsics.asMutableList(gpsSampleList).add(activityGPSSample);
                }
            }
            activityModeSummaryResponse.setMacAddress(BleApiManager.getInstance(this.f3335a).getBleApi().getMacAddress());
            activityModeSummaryResponse.setSessionID(UUID.randomUUID().toString());
            arrayList.add(activityModeSummaryResponse);
            it = it2;
            decimalFormat = decimalFormat3;
        }
        return arrayList;
    }
}
