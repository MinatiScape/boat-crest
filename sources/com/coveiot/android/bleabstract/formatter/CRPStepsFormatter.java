package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.android.crpsdk.model.KhCRPStepInfo;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.bean.CRPStepInfo;
import com.crrepa.ble.conn.bean.CRPStepsCategoryInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CRPStepsFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3307a;
    public final String b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<CRPStepsFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.CRPStepsFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, CRPStepsFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3308a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, CRPStepsFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public CRPStepsFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CRPStepsFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3308a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CRPStepsFormatter(Context context) {
        this.f3307a = context;
        this.b = CRPStepsFormatter.class.getSimpleName();
    }

    public /* synthetic */ CRPStepsFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final ArrayList<StepsResponse> convertStepData(@NotNull CRPStepsCategoryInfo crpStepsCategoryInfo, int i) {
        KhCRPStepInfo todayStepsData;
        boolean z;
        List<Integer> stepsList;
        Intrinsics.checkNotNullParameter(crpStepsCategoryInfo, "crpStepsCategoryInfo");
        LogHelper.d(this.b, "convertStepData->dataType " + crpStepsCategoryInfo.getDateType() + ", interval " + crpStepsCategoryInfo.getTimeInterval() + ", steps " + crpStepsCategoryInfo.getStepsList());
        ArrayList<StepsResponse> arrayList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        if (crpStepsCategoryInfo.getDateType() == 2) {
            calendar.add(6, -1);
            todayStepsData = PreferenceManagerCRP.getInstance(this.f3307a).getYesterdayStepsData();
        } else {
            todayStepsData = PreferenceManagerCRP.getInstance(this.f3307a).getTodayStepsData();
        }
        StepsResponse stepsResponse = new StepsResponse();
        int i2 = 1;
        if (todayStepsData != null) {
            StepsDayData stepsDayData = new StepsDayData();
            stepsDayData.setmDate(AppUtils.formatDate(new Date(calendar.getTimeInMillis()), "yyyy-MM-dd"));
            stepsDayData.setmActivityType(ActivityType.WALK.name());
            stepsDayData.setmMacAddress(BleApiManager.getInstance(this.f3307a).getBleApi().getMacAddress());
            stepsDayData.mSteps = todayStepsData.getSteps();
            stepsDayData.mDistance = todayStepsData.getDistance();
            stepsDayData.mCalories = todayStepsData.getCalories();
            StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
            stepsTimeLogBeanData.setLogBeans(new ArrayList());
            stepsDayData.setTimeLogBean(stepsTimeLogBeanData);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Intrinsics.checkNotNullExpressionValue(crpStepsCategoryInfo.getStepsList(), "crpStepsCategoryInfo.stepsList");
            int i3 = 24;
            if (!stepsList.isEmpty()) {
                int size = crpStepsCategoryInfo.getStepsList().size() / 24;
                int i4 = 1;
                int i5 = 0;
                for (Integer num : crpStepsCategoryInfo.getStepsList()) {
                    if (i4 > size) {
                        i5++;
                        i4 = 1;
                    }
                    if (linkedHashMap.containsKey(Integer.valueOf(i5))) {
                        Object obj = linkedHashMap.get(Integer.valueOf(i5));
                        Intrinsics.checkNotNull(obj);
                        ((ArrayList) obj).add(num);
                    } else {
                        linkedHashMap.put(Integer.valueOf(i5), new ArrayList());
                        Object obj2 = linkedHashMap.get(Integer.valueOf(i5));
                        Intrinsics.checkNotNull(obj2);
                        ((ArrayList) obj2).add(num);
                    }
                    i4++;
                }
            }
            Iterator it = linkedHashMap.keySet().iterator();
            while (it.hasNext()) {
                Integer num2 = (Integer) it.next();
                StepsHourData stepsHourData = new StepsHourData();
                stepsHourData.setDate(AppUtils.formatDate(new Date(calendar.getTimeInMillis()), "yyyy-MM-dd"));
                Object obj3 = linkedHashMap.get(num2);
                Intrinsics.checkNotNull(obj3);
                stepsHourData.mStepsPerHour = CollectionsKt___CollectionsKt.sumOfInt((Iterable) obj3);
                int intValue = num2.intValue() + i2;
                if (intValue == i3) {
                    intValue = 0;
                }
                StringBuilder sb = new StringBuilder();
                Locale locale = Locale.ENGLISH;
                Object[] objArr = new Object[i2];
                objArr[0] = num2;
                String format = String.format(locale, "%02d", Arrays.copyOf(objArr, i2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(":00:00");
                stepsHourData.setStartHour(sb.toString());
                StringBuilder sb2 = new StringBuilder();
                Iterator it2 = it;
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(intValue)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb2.append(format2);
                sb2.append(":00:00");
                stepsHourData.setEndHour(sb2.toString());
                Object obj4 = linkedHashMap.get(num2);
                Intrinsics.checkNotNull(obj4);
                stepsHourData.mMinuteWiseData = (ArrayList) obj4;
                stepsDayData.timeLogBean.getLogBeans().add(stepsHourData);
                it = it2;
                calendar = calendar;
                i2 = 1;
                i3 = 24;
            }
            stepsResponse.setStepsDayData(stepsDayData);
            z = true;
        } else {
            z = true;
        }
        stepsResponse.setCaloriesDistanceCalculatedFromBand(z);
        stepsResponse.setDistanceIsInMetresFromBand(z);
        if (i == z) {
            stepsResponse.setComplete(z);
            LogHelper.d(this.b, "Activities Complete");
        }
        arrayList.add(stepsResponse);
        return arrayList;
    }

    @NotNull
    public final LiveStepsData convertToLiveStepsData(@NotNull CRPStepInfo cRPStepInfo) {
        Intrinsics.checkNotNullParameter(cRPStepInfo, "cRPStepInfo");
        String str = this.b;
        LogHelper.d(str, "LIVE DATA STEPS = " + cRPStepInfo.getSteps() + " Calories = " + cRPStepInfo.getCalories() + " Distance = " + cRPStepInfo.getDistance(), ModuleNames.BLEABSTRACT.getModuleName());
        LiveStepsData liveStepsData = new LiveStepsData();
        liveStepsData.setLiveSteps(cRPStepInfo.getSteps());
        liveStepsData.setMeters(cRPStepInfo.getDistance());
        liveStepsData.setCalories((double) cRPStepInfo.getCalories());
        return liveStepsData;
    }

    @NotNull
    public final Context getContext() {
        return this.f3307a;
    }

    public final String getTAG() {
        return this.b;
    }
}
