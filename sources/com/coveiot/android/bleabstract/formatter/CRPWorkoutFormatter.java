package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.crpsdk.KhCRPUtil;
import com.coveiot.android.crpsdk.model.KhCRPHeartRateInfo;
import com.coveiot.android.crpsdk.model.KhCRPWorkoutInfo;
import com.coveiot.utils.utility.AppUtils;
import com.crrepa.ble.conn.bean.CRPMovementHeartRateInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPWorkoutFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3309a;
    public final String b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<CRPWorkoutFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.CRPWorkoutFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, CRPWorkoutFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3310a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, CRPWorkoutFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public CRPWorkoutFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CRPWorkoutFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3310a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CRPWorkoutFormatter(Context context) {
        this.f3309a = context;
        this.b = CRPWorkoutFormatter.class.getSimpleName();
    }

    public /* synthetic */ CRPWorkoutFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final List<ActivityModeSummaryResponse> convertWorkoutData(@NotNull String macAddress, @NotNull List<CRPMovementHeartRateInfo> crpMovementHeartRateInfoList) {
        Integer distance;
        Integer distance2;
        Integer calories;
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(crpMovementHeartRateInfoList, "crpMovementHeartRateInfoList");
        ArrayList arrayList = new ArrayList();
        for (CRPMovementHeartRateInfo cRPMovementHeartRateInfo : crpMovementHeartRateInfoList) {
            KhCRPWorkoutInfo khCRPWorkoutInfo = PreferenceManagerCRP.getInstance(this.f3309a).getKhCRPWorkoutInfo(Long.valueOf(cRPMovementHeartRateInfo.getStartTime()), Long.valueOf(cRPMovementHeartRateInfo.getEndTime()));
            if (khCRPWorkoutInfo != null && khCRPWorkoutInfo.getSyncStatus() != 1) {
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
                activityModeSummaryResponse.setSessionID(uuid);
                Long endTime = khCRPWorkoutInfo.getEndTime();
                Intrinsics.checkNotNull(endTime);
                activityModeSummaryResponse.setDate(AppUtils.formatDate(new Date(endTime.longValue()), "yyyy-MM-dd"));
                Long startTime = khCRPWorkoutInfo.getStartTime();
                Intrinsics.checkNotNull(startTime);
                activityModeSummaryResponse.setStartDateTime(startTime);
                Long endTime2 = khCRPWorkoutInfo.getEndTime();
                Intrinsics.checkNotNull(endTime2);
                activityModeSummaryResponse.setEndDateTime(endTime2);
                Integer validTime = khCRPWorkoutInfo.getValidTime();
                Intrinsics.checkNotNull(validTime);
                activityModeSummaryResponse.setActivityDuration(validTime.intValue());
                activityModeSummaryResponse.setActivityMode(KhCRPUtil.INSTANCE.getActivityMode(khCRPWorkoutInfo.getType()));
                Integer calories2 = khCRPWorkoutInfo.getCalories();
                if (calories2 != null) {
                    calories2.intValue();
                    Intrinsics.checkNotNull(khCRPWorkoutInfo.getCalories());
                    activityModeSummaryResponse.setTotalCalories(calories.intValue());
                }
                Integer distance3 = khCRPWorkoutInfo.getDistance();
                if (distance3 != null) {
                    distance3.intValue();
                    Intrinsics.checkNotNull(khCRPWorkoutInfo.getDistance());
                    activityModeSummaryResponse.setTotalDistance(distance2.intValue() / 1000);
                }
                Integer steps = khCRPWorkoutInfo.getSteps();
                if (steps != null) {
                    steps.intValue();
                    Integer steps2 = khCRPWorkoutInfo.getSteps();
                    Intrinsics.checkNotNull(steps2);
                    activityModeSummaryResponse.setTotalSteps(steps2.intValue());
                }
                activityModeSummaryResponse.setMacAddress(macAddress);
                if (activityModeSummaryResponse.getTotalDistance() > 0.0d) {
                    Integer validTime2 = khCRPWorkoutInfo.getValidTime();
                    Intrinsics.checkNotNull(validTime2);
                    double intValue = validTime2.intValue();
                    Intrinsics.checkNotNull(khCRPWorkoutInfo.getDistance());
                    activityModeSummaryResponse.setPaceInSeconds((int) (intValue / (distance.intValue() / 1000)));
                }
                Long startTime2 = khCRPWorkoutInfo.getStartTime();
                Intrinsics.checkNotNull(startTime2);
                long longValue = startTime2.longValue();
                Long endTime3 = khCRPWorkoutInfo.getEndTime();
                Intrinsics.checkNotNull(endTime3);
                KhCRPHeartRateInfo sessionHeartRate = getSessionHeartRate(longValue, endTime3.longValue());
                if ((sessionHeartRate != null ? sessionHeartRate.getHeartRateList() : null) != null) {
                    List<Integer> heartRateList = sessionHeartRate.getHeartRateList();
                    Intrinsics.checkNotNull(heartRateList);
                    if (true ^ heartRateList.isEmpty()) {
                        List<Integer> heartRateList2 = sessionHeartRate.getHeartRateList();
                        Intrinsics.checkNotNull(heartRateList2);
                        List sorted = CollectionsKt___CollectionsKt.sorted(heartRateList2);
                        activityModeSummaryResponse.setHeartRate((int) CollectionsKt___CollectionsKt.averageOfInt(sorted));
                        activityModeSummaryResponse.setMinHeartRate(((Number) CollectionsKt___CollectionsKt.first((List<? extends Object>) sorted)).intValue());
                        activityModeSummaryResponse.setMaxHeartRate(((Number) CollectionsKt___CollectionsKt.last((List<? extends Object>) sorted)).intValue());
                        ArrayList arrayList2 = new ArrayList();
                        Intrinsics.checkNotNull(sessionHeartRate);
                        List<Integer> heartRateList3 = sessionHeartRate.getHeartRateList();
                        Intrinsics.checkNotNull(heartRateList3);
                        int i = 0;
                        for (Integer num : heartRateList3) {
                            int intValue2 = num.intValue();
                            ActivityHeartRateSample activityHeartRateSample = new ActivityHeartRateSample();
                            activityHeartRateSample.setHrValue(intValue2);
                            Calendar calendar = Calendar.getInstance();
                            Long startTime3 = sessionHeartRate.getStartTime();
                            Intrinsics.checkNotNull(startTime3);
                            calendar.setTimeInMillis(startTime3.longValue());
                            Integer timeInterval = sessionHeartRate.getTimeInterval();
                            Intrinsics.checkNotNull(timeInterval);
                            calendar.add(12, i * timeInterval.intValue());
                            activityHeartRateSample.setHrTimeStamp(calendar.getTimeInMillis());
                            arrayList2.add(activityHeartRateSample);
                            i++;
                        }
                        activityModeSummaryResponse.setHeartRateSampleList(arrayList2);
                    }
                }
                arrayList.add(activityModeSummaryResponse);
            }
        }
        if (!arrayList.isEmpty()) {
            ((ActivityModeSummaryResponse) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList)).setComplete(true);
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3309a;
    }

    @Nullable
    public final KhCRPHeartRateInfo getSessionHeartRate(long j, long j2) {
        boolean z;
        List<KhCRPHeartRateInfo> partHeartRateData = PreferenceManagerCRP.getInstance(this.f3309a).getPartHeartRateData();
        if (partHeartRateData != null && partHeartRateData.size() > 0) {
            for (KhCRPHeartRateInfo khCRPHeartRateInfo : partHeartRateData) {
                Long startTime = khCRPHeartRateInfo.getStartTime();
                Intrinsics.checkNotNull(startTime);
                long longValue = startTime.longValue();
                if (j > longValue || longValue > j2) {
                    z = false;
                    continue;
                } else {
                    z = true;
                    continue;
                }
                if (z) {
                    return khCRPHeartRateInfo;
                }
            }
        }
        return null;
    }

    public final String getTAG() {
        return this.b;
    }
}
