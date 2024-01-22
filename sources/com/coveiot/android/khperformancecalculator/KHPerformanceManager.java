package com.coveiot.android.khperformancecalculator;

import android.content.Context;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.khperformancecalculator.model.DeviceType;
import com.coveiot.android.khperformancecalculator.model.KHPActivityData;
import com.coveiot.android.khperformancecalculator.model.KHPSleepData;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class KHPerformanceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4638a;
    public final String b;

    /* loaded from: classes.dex */
    public static final class Companion extends SingletonHolder<KHPerformanceManager, Context> {

        /* loaded from: classes.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHPerformanceManager> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHPerformanceManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHPerformanceManager invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHPerformanceManager(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHPerformanceManager(Context context) {
        this.f4638a = context;
        this.b = KHPerformanceManager.class.getSimpleName();
    }

    public /* synthetic */ KHPerformanceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final List<KHPActivityData> getBestActivities(@NotNull DeviceType deviceType, @NotNull List<KHPActivityData> inKHPActivities) {
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(inKHPActivities, "inKHPActivities");
        ArrayList arrayList = new ArrayList();
        if (!inKHPActivities.isEmpty()) {
            Map<String, KHPActivityData> bestActivities = KHPerformancePreferenceManager.getInstance(this.f4638a).getBestActivities();
            if (bestActivities == null) {
                bestActivities = new LinkedHashMap<>();
            }
            for (KHPActivityData kHPActivityData : inKHPActivities) {
                String type = kHPActivityData.getType();
                if (Intrinsics.areEqual(kHPActivityData.getType(), CoveApiConstants.CYCLE) && deviceType == DeviceType.SMA && kHPActivityData.getIndoorOutdoor() != null && Intrinsics.areEqual(kHPActivityData.getIndoorOutdoor(), "INDOOR")) {
                    type = "SPINNING";
                }
                KHPActivityData kHPActivityData2 = bestActivities.get(type);
                if (kHPActivityData2 == null || kHPActivityData.getCalorie() > kHPActivityData2.getCalorie()) {
                    bestActivities.put(type, kHPActivityData);
                    arrayList.add(kHPActivityData);
                }
            }
            KHPerformancePreferenceManager.getInstance(this.f4638a).setBestActivities(bestActivities);
        }
        return arrayList;
    }

    public final int getDaysDifferenceFromActivity(@NotNull KHPActivityData KHPActivityData) {
        Intrinsics.checkNotNullParameter(KHPActivityData, "KHPActivityData");
        if (KHPActivityData.getEndTime() > 0) {
            return (int) ((System.currentTimeMillis() - KHPActivityData.getEndTime()) / ((long) TimeConstants.DAY));
        }
        return 0;
    }

    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final SleepTarget getWeeklySleepTargetStatus(int i, @NotNull List<KHPSleepData> weeklySleepData) {
        Intrinsics.checkNotNullParameter(weeklySleepData, "weeklySleepData");
        SleepTarget sleepTarget = SleepTarget.NOT_ACHIEVED;
        int i2 = 0;
        if (!weeklySleepData.isEmpty()) {
            int size = weeklySleepData.size();
            int i3 = 0;
            while (i2 < size) {
                if (i2 == weeklySleepData.size() - 1) {
                    Map<String, Integer> lastSleepTarget = KHPerformancePreferenceManager.getInstance(this.f4638a).getLastSleepTarget();
                    Intrinsics.checkNotNullExpressionValue(lastSleepTarget, "getInstance(context).lastSleepTarget");
                    lastSleepTarget.put(weeklySleepData.get(i2).getDate(), Integer.valueOf(weeklySleepData.get(i2).getTargetSleep()));
                }
                weeklySleepData.get(i2).getTargetSleep();
                if (weeklySleepData.get(i2).getTargetSleep() > 0) {
                    if (weeklySleepData.get(i2).getTotalSleep() >= weeklySleepData.get(i2).getTargetSleep()) {
                        i3++;
                    }
                } else {
                    LogHelper.d(this.b, "No sleep target for " + weeklySleepData.get(i2).getDate());
                }
                i2++;
            }
            i2 = i3;
        }
        return i2 >= i ? SleepTarget.ACHIEVED : sleepTarget;
    }
}
