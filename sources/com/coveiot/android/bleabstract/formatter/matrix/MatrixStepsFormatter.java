package com.coveiot.android.bleabstract.formatter.matrix;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.matrixUtils.MatrixUtils;
import com.coveiot.android.khmatrixdb.steps.KhMatrixStepsData;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.bean.data.StepData;
import com.htsmart.wristband2.bean.data.TodayTotalData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Triple;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MatrixStepsFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3374a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixStepsFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.matrix.MatrixStepsFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixStepsFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3375a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixStepsFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixStepsFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixStepsFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3375a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MatrixStepsFormatter(Context context) {
        this.f3374a = context;
    }

    public /* synthetic */ MatrixStepsFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Float, Float> a(ArrayList<KhMatrixStepsData> arrayList) {
        Intrinsics.checkNotNull(arrayList);
        Iterator<KhMatrixStepsData> it = arrayList.iterator();
        float f = 0.0f;
        int i = 0;
        float f2 = 0.0f;
        while (it.hasNext()) {
            KhMatrixStepsData next = it.next();
            i += next.getSteps();
            f2 += next.getDistance();
            f += next.getCalories();
        }
        return new Triple<>(Integer.valueOf(i), Float.valueOf(f2), Float.valueOf(f));
    }

    public final ArrayList<Integer> b(ArrayList<KhMatrixStepsData> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>(Collections.nCopies(60, 0));
        Intrinsics.checkNotNull(arrayList);
        h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.matrix.MatrixStepsFormatter$getMinuteWiseStepsData$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(Long.valueOf(((KhMatrixStepsData) t).getMTime()), Long.valueOf(((KhMatrixStepsData) t2).getMTime()));
            }
        });
        Iterator<KhMatrixStepsData> it = arrayList.iterator();
        while (it.hasNext()) {
            KhMatrixStepsData next = it.next();
            arrayList2.set(MatrixUtils.INSTANCE.convertSDKTimeToCalender(next.getMTime()).get(12), Integer.valueOf(next.getSteps()));
        }
        return arrayList2;
    }

    @NotNull
    public final ArrayList<StepsResponse> convertData(@Nullable List<KhMatrixStepsData> list) {
        HashMap hashMap;
        int i;
        int i2;
        TreeMap treeMap;
        int i3;
        int i4 = 0;
        double d = 0.001d;
        if (!(list == null || list.isEmpty())) {
            ArrayList<StepsResponse> arrayList = new ArrayList<>();
            HashMap hashMap2 = new HashMap();
            for (KhMatrixStepsData khMatrixStepsData : list) {
                String convertSDKTimeToDate = MatrixUtils.INSTANCE.convertSDKTimeToDate(khMatrixStepsData.getMTime());
                if (hashMap2.containsKey(convertSDKTimeToDate)) {
                    List list2 = (List) hashMap2.get(convertSDKTimeToDate);
                    if (list2 != null) {
                        list2.add(khMatrixStepsData);
                    }
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(khMatrixStepsData);
                    hashMap2.put(convertSDKTimeToDate, arrayList2);
                }
            }
            int size = hashMap2.keySet().size();
            int i5 = 0;
            while (i5 < size) {
                Set keySet = hashMap2.keySet();
                Intrinsics.checkNotNullExpressionValue(keySet, "stepsDateMap.keys");
                Object obj = CollectionsKt___CollectionsKt.toList(keySet).get(i5);
                Intrinsics.checkNotNullExpressionValue(obj, "stepsDateMap.keys.toList()[i]");
                Object obj2 = hashMap2.get((String) obj);
                Intrinsics.checkNotNull(obj2);
                List<KhMatrixStepsData> list3 = (List) obj2;
                StepsDayData stepsDayData = new StepsDayData();
                stepsDayData.mDate = MatrixUtils.INSTANCE.convertSDKTimeToDate(((KhMatrixStepsData) list3.get(i4)).getMTime());
                stepsDayData.mActivityType = ActivityType.WALK.toString();
                stepsDayData.setmMacAddress(BleApiManager.getInstance(this.f3374a).getBleApi().getMacAddress());
                Intrinsics.checkNotNull(list3, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.khmatrixdb.steps.KhMatrixStepsData>");
                Triple<Integer, Float, Float> a2 = a((ArrayList) list3);
                if (BleUtils.isToday(stepsDayData.mDate)) {
                    TodayTotalData todayTotalData = PreferenceManagerMatrix.getInstance(this.f3374a).getTodayTotalData();
                    if (todayTotalData != null) {
                        stepsDayData.mSteps = todayTotalData.getStep();
                        stepsDayData.mDistance = todayTotalData.getDistance() * d;
                        stepsDayData.mCalories = todayTotalData.getCalorie() / 1000;
                    }
                } else {
                    stepsDayData.mSteps = a2.getFirst().intValue();
                    stepsDayData.mDistance = a2.getSecond().floatValue();
                    stepsDayData.mCalories = a2.getThird().floatValue();
                }
                StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
                ArrayList arrayList3 = new ArrayList();
                TreeMap treeMap2 = new TreeMap();
                for (KhMatrixStepsData khMatrixStepsData2 : list3) {
                    ArrayList<StepsResponse> arrayList4 = arrayList;
                    int i6 = MatrixUtils.INSTANCE.convertSDKTimeToCalender(khMatrixStepsData2.getMTime()).get(11);
                    if (treeMap2.containsKey(Integer.valueOf(i6))) {
                        ArrayList arrayList5 = (ArrayList) treeMap2.get(Integer.valueOf(i6));
                        if (arrayList5 != null) {
                            arrayList5.add(khMatrixStepsData2);
                        }
                    } else {
                        ArrayList arrayList6 = new ArrayList();
                        arrayList6.add(khMatrixStepsData2);
                        treeMap2.put(Integer.valueOf(i6), arrayList6);
                    }
                    arrayList = arrayList4;
                }
                ArrayList<StepsResponse> arrayList7 = arrayList;
                int i7 = i4;
                while (i7 < 24) {
                    if (treeMap2.keySet().contains(Integer.valueOf(i7))) {
                        Object obj3 = treeMap2.get(Integer.valueOf(i7));
                        Intrinsics.checkNotNull(obj3);
                        ArrayList<KhMatrixStepsData> arrayList8 = (ArrayList) obj3;
                        StepsHourData stepsHourData = new StepsHourData();
                        i = size;
                        MatrixUtils matrixUtils = MatrixUtils.INSTANCE;
                        treeMap = treeMap2;
                        stepsHourData.setDate(matrixUtils.convertSDKTimeToDate(arrayList8.get(0).getMTime()));
                        int i8 = matrixUtils.convertSDKTimeToCalender(arrayList8.get(0).getMTime()).get(11);
                        StringBuilder sb = new StringBuilder();
                        Locale locale = Locale.ENGLISH;
                        hashMap = hashMap2;
                        i2 = i5;
                        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                        sb.append(format);
                        sb.append(":00:00");
                        stepsHourData.setStartHour(sb.toString());
                        if (i7 == 23) {
                            stepsHourData.setEndHour("00:00:00");
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8 + 1)}, 1));
                            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                            sb2.append(format2);
                            sb2.append(":00:00");
                            stepsHourData.setEndHour(sb2.toString());
                        }
                        stepsHourData.setMacAddress(BleApiManager.getInstance(this.f3374a).getBleApi().getMacAddress());
                        stepsHourData.setActivityType(ActivityType.WALK.toString());
                        stepsHourData.setMinuteWiseData(b(arrayList8));
                        stepsHourData.setStepsPerHour(a(arrayList8).getFirst().intValue());
                        stepsHourData.setDistancePerHour(a(arrayList8).getSecond().floatValue());
                        stepsHourData.setCaloriesPerHour(a(arrayList8).getThird().floatValue());
                        arrayList3.add(stepsHourData);
                        i3 = 0;
                    } else {
                        hashMap = hashMap2;
                        i = size;
                        i2 = i5;
                        treeMap = treeMap2;
                        long mTime = ((KhMatrixStepsData) list3.get(i4)).getMTime();
                        StepsHourData stepsHourData2 = new StepsHourData();
                        stepsHourData2.setDate(MatrixUtils.INSTANCE.convertSDKTimeToDate(mTime));
                        StringBuilder sb3 = new StringBuilder();
                        Locale locale2 = Locale.ENGLISH;
                        String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i7)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                        sb3.append(format3);
                        sb3.append(":00:00");
                        stepsHourData2.setStartHour(sb3.toString());
                        if (i7 == 23) {
                            stepsHourData2.setEndHour("00:00:00");
                        } else {
                            StringBuilder sb4 = new StringBuilder();
                            String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i7 + 1)}, 1));
                            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                            sb4.append(format4);
                            sb4.append(":00:00");
                            stepsHourData2.setEndHour(sb4.toString());
                        }
                        stepsHourData2.setMacAddress(BleApiManager.getInstance(this.f3374a).getBleApi().getMacAddress());
                        stepsHourData2.setActivityType(ActivityType.WALK.toString());
                        i3 = 0;
                        stepsHourData2.setMinuteWiseData(new ArrayList<>(Collections.nCopies(60, 0)));
                        stepsHourData2.setStepsPerHour(0);
                        stepsHourData2.setDistancePerHour(0.0d);
                        stepsHourData2.setCaloriesPerHour(0.0d);
                        arrayList3.add(stepsHourData2);
                    }
                    i7++;
                    i4 = i3;
                    size = i;
                    treeMap2 = treeMap;
                    i5 = i2;
                    hashMap2 = hashMap;
                }
                int i9 = i4;
                HashMap hashMap3 = hashMap2;
                int i10 = size;
                int i11 = i5;
                stepsTimeLogBeanData.setLogBeans(arrayList3);
                stepsDayData.timeLogBean = stepsTimeLogBeanData;
                StepsResponse stepsResponse = new StepsResponse();
                stepsResponse.setStepsDayData(stepsDayData);
                stepsResponse.setDailyWalkDataCalculateFromBand(true);
                stepsResponse.setCaloriesDistanceCalculatedFromBand(true);
                if (i11 == hashMap3.size() - 1) {
                    stepsResponse.setComplete(true);
                    LogHelper.d("MatrixBleService", "Steps Complete");
                }
                arrayList7.add(stepsResponse);
                i5 = i11 + 1;
                arrayList = arrayList7;
                i4 = i9;
                size = i10;
                hashMap2 = hashMap3;
                d = 0.001d;
            }
            return arrayList;
        }
        ArrayList<StepsResponse> arrayList9 = new ArrayList<>();
        TodayTotalData todayTotalData2 = PreferenceManagerMatrix.getInstance(this.f3374a).getTodayTotalData();
        StepsDayData stepsDayData2 = null;
        if (todayTotalData2 != null) {
            StepsDayData stepsDayData3 = new StepsDayData();
            stepsDayData3.mSteps = todayTotalData2.getStep();
            stepsDayData3.mDistance = todayTotalData2.getDistance() * 0.001d;
            stepsDayData3.mCalories = todayTotalData2.getCalorie() / 1000;
            stepsDayData3.mDate = MatrixUtils.INSTANCE.convertSDKTimeToDate(todayTotalData2.getTimeStamp());
            stepsDayData3.mActivityType = ActivityType.WALK.toString();
            stepsDayData3.setmMacAddress(BleApiManager.getInstance(this.f3374a).getBleApi().getMacAddress());
            stepsDayData3.timeLogBean = null;
            stepsDayData2 = stepsDayData3;
        }
        if (stepsDayData2 != null) {
            StepsResponse stepsResponse2 = new StepsResponse();
            stepsResponse2.setStepsDayData(stepsDayData2);
            stepsResponse2.setDailyWalkDataCalculateFromBand(true);
            stepsResponse2.setCaloriesDistanceCalculatedFromBand(true);
            stepsResponse2.setComplete(true);
            arrayList9.add(stepsResponse2);
        }
        return arrayList9;
    }

    @NotNull
    public final Context getContext() {
        return this.f3374a;
    }

    @NotNull
    public final List<KhMatrixStepsData> getMatrixBleSteps(@Nullable List<? extends StepData> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            String macAddress = BleApiManager.getInstance(this.f3374a).getBleApi().getMacAddress();
            for (StepData stepData : list) {
                arrayList.add(new KhMatrixStepsData(macAddress, stepData.getTimeStamp(), stepData.getStep(), stepData.getDistance(), stepData.getCalories(), null, 32, null));
            }
        }
        return arrayList;
    }
}
