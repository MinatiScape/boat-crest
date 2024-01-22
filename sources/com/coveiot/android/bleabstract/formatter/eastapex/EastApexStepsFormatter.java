package com.coveiot.android.bleabstract.formatter.eastapex;

import android.content.Context;
import com.apex.bluetooth.model.EABleDailyData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.kheastapexdb.walk.EntityEAStepsData;
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
public final class EastApexStepsFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3348a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexStepsFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.eastapex.EastApexStepsFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexStepsFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3349a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexStepsFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexStepsFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexStepsFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3349a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EastApexStepsFormatter(Context context) {
        this.f3348a = context;
    }

    public /* synthetic */ EastApexStepsFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Float, Double> a(ArrayList<EntityEAStepsData> arrayList) {
        Intrinsics.checkNotNull(arrayList);
        Iterator<EntityEAStepsData> it = arrayList.iterator();
        int i = 0;
        float f = 0.0f;
        double d = 0.0d;
        while (it.hasNext()) {
            EntityEAStepsData next = it.next();
            i += next.getSteps();
            f += next.getDistance() / 100;
            d += next.getCalorie() / 1000.0d;
        }
        return new Triple<>(Integer.valueOf(i), Float.valueOf(f), Double.valueOf(d));
    }

    public final ArrayList<Integer> b(ArrayList<EntityEAStepsData> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>(Collections.nCopies(60, 0));
        Intrinsics.checkNotNull(arrayList);
        h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.eastapex.EastApexStepsFormatter$getMinuteWiseStepsData$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(Long.valueOf(((EntityEAStepsData) t).getTimeStamp()), Long.valueOf(((EntityEAStepsData) t2).getTimeStamp()));
            }
        });
        Iterator<EntityEAStepsData> it = arrayList.iterator();
        while (it.hasNext()) {
            EntityEAStepsData next = it.next();
            arrayList2.set(BleApiUtils.INSTANCE.getCalendarFromTimeStamp(next.getTimeStamp()).get(12), Integer.valueOf(next.getSteps()));
        }
        return arrayList2;
    }

    @Nullable
    public final ArrayList<EntityEAStepsData> convertEAStepDataToEntity(@Nullable List<? extends EABleDailyData> list) {
        if (list != null) {
            ArrayList<EntityEAStepsData> arrayList = new ArrayList<>();
            Iterator<? extends EABleDailyData> it = list.iterator();
            while (it.hasNext()) {
                EABleDailyData next = it.next();
                Long valueOf = next != null ? Long.valueOf(next.time_stamp) : null;
                Intrinsics.checkNotNull(valueOf);
                EntityEAStepsData entityEAStepsData = new EntityEAStepsData(valueOf.longValue(), BleApiManager.getInstance(this.f3348a).getBleApi().getMacAddress());
                entityEAStepsData.setSteps(next.steps);
                entityEAStepsData.setCalorie(next.calorie);
                entityEAStepsData.setDistance(next.distance);
                entityEAStepsData.setDuration(next.duration);
                entityEAStepsData.setAvgHeartRate(next.average_heart_rate);
                entityEAStepsData.setTimeStamp(next.time_stamp * 1000);
                arrayList.add(entityEAStepsData);
            }
            return arrayList;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3348a;
    }

    @NotNull
    public final ArrayList<StepsResponse> getStepResponseList(@Nullable List<EntityEAStepsData> list) {
        Triple<Integer, Float, Double> a2;
        ArrayList<StepsResponse> arrayList;
        HashMap hashMap;
        int i;
        StepsDayData stepsDayData;
        TreeMap treeMap;
        int i2;
        ArrayList<StepsResponse> arrayList2 = new ArrayList<>();
        Intrinsics.checkNotNull(list);
        HashMap hashMap2 = new HashMap();
        for (EntityEAStepsData entityEAStepsData : list) {
            String dateFromTimeStamp = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityEAStepsData.getTimeStamp());
            if (hashMap2.containsKey(dateFromTimeStamp)) {
                List list2 = (List) hashMap2.get(dateFromTimeStamp);
                if (list2 != null) {
                    list2.add(entityEAStepsData);
                }
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(entityEAStepsData);
                Intrinsics.checkNotNull(dateFromTimeStamp);
                hashMap2.put(dateFromTimeStamp, arrayList3);
            }
        }
        int size = hashMap2.keySet().size();
        int i3 = 0;
        int i4 = 0;
        while (i4 < size) {
            Set keySet = hashMap2.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "stepsDateMap.keys");
            Object obj = CollectionsKt___CollectionsKt.toList(keySet).get(i4);
            Intrinsics.checkNotNullExpressionValue(obj, "stepsDateMap.keys.toList()[i]");
            Object obj2 = hashMap2.get((String) obj);
            Intrinsics.checkNotNull(obj2);
            List<EntityEAStepsData> list3 = (List) obj2;
            StepsDayData stepsDayData2 = new StepsDayData();
            stepsDayData2.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(((EntityEAStepsData) list3.get(i3)).getTimeStamp());
            stepsDayData2.setmMacAddress(BleApiManager.getInstance(this.f3348a).getBleApi().getMacAddress());
            Intrinsics.checkNotNull(list3, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.kheastapexdb.walk.EntityEAStepsData>");
            stepsDayData2.mSteps = a((ArrayList) list3).getFirst().intValue();
            stepsDayData2.mDistance = a2.getSecond().floatValue();
            stepsDayData2.mCalories = (int) a2.getThird().doubleValue();
            StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
            ArrayList arrayList4 = new ArrayList();
            TreeMap treeMap2 = new TreeMap();
            for (EntityEAStepsData entityEAStepsData2 : list3) {
                int i5 = i4;
                int i6 = BleApiUtils.INSTANCE.getCalendarFromTimeStamp(entityEAStepsData2.getTimeStamp()).get(11);
                if (treeMap2.containsKey(Integer.valueOf(i6))) {
                    ArrayList arrayList5 = (ArrayList) treeMap2.get(Integer.valueOf(i6));
                    if (arrayList5 != null) {
                        arrayList5.add(entityEAStepsData2);
                    }
                } else {
                    ArrayList arrayList6 = new ArrayList();
                    arrayList6.add(entityEAStepsData2);
                    treeMap2.put(Integer.valueOf(i6), arrayList6);
                }
                i4 = i5;
            }
            int i7 = i4;
            int i8 = 0;
            while (i8 < 24) {
                if (treeMap2.keySet().contains(Integer.valueOf(i8))) {
                    Object obj3 = treeMap2.get(Integer.valueOf(i8));
                    Intrinsics.checkNotNull(obj3);
                    ArrayList<EntityEAStepsData> arrayList7 = (ArrayList) obj3;
                    i = size;
                    StepsHourData stepsHourData = new StepsHourData();
                    treeMap = treeMap2;
                    BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
                    i2 = i7;
                    arrayList = arrayList2;
                    hashMap = hashMap2;
                    stepsHourData.setDate(bleApiUtils.getDateFromTimeStamp(arrayList7.get(0).getTimeStamp()));
                    int i9 = bleApiUtils.getCalendarFromTimeStamp(arrayList7.get(0).getTimeStamp()).get(11);
                    StringBuilder sb = new StringBuilder();
                    Locale locale = Locale.ENGLISH;
                    stepsDayData = stepsDayData2;
                    String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    sb.append(format);
                    sb.append(":00:00");
                    stepsHourData.setStartHour(sb.toString());
                    if (i8 == 23) {
                        stepsHourData.setEndHour("00:00:00");
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                        sb2.append(format2);
                        sb2.append(":00:00");
                        stepsHourData.setEndHour(sb2.toString());
                    }
                    stepsHourData.setMacAddress(BleApiManager.getInstance(this.f3348a).getBleApi().getMacAddress());
                    stepsHourData.mMinuteWiseData = b(arrayList7);
                    stepsHourData.setStepsPerHour(a(arrayList7).getFirst().intValue());
                    stepsHourData.mDistancePerHour = a(arrayList7).getSecond().floatValue();
                    stepsHourData.setCaloriesPerHour(a(arrayList7).getThird().doubleValue());
                    arrayList4.add(stepsHourData);
                } else {
                    arrayList = arrayList2;
                    hashMap = hashMap2;
                    i = size;
                    stepsDayData = stepsDayData2;
                    treeMap = treeMap2;
                    i2 = i7;
                    long timeStamp = ((EntityEAStepsData) list3.get(0)).getTimeStamp();
                    StepsHourData stepsHourData2 = new StepsHourData();
                    stepsHourData2.setDate(BleApiUtils.INSTANCE.getDateFromTimeStamp(timeStamp));
                    StringBuilder sb3 = new StringBuilder();
                    Locale locale2 = Locale.ENGLISH;
                    String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                    sb3.append(format3);
                    sb3.append(":00:00");
                    stepsHourData2.setStartHour(sb3.toString());
                    if (i8 == 23) {
                        stepsHourData2.setEndHour("00:00:00");
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                        sb4.append(format4);
                        sb4.append(":00:00");
                        stepsHourData2.setEndHour(sb4.toString());
                    }
                    stepsHourData2.setMacAddress(BleApiManager.getInstance(this.f3348a).getBleApi().getMacAddress());
                    stepsHourData2.setMinuteWiseData(new ArrayList<>(Collections.nCopies(60, 0)));
                    stepsHourData2.setStepsPerHour(0);
                    stepsHourData2.mDistancePerHour = 0.0d;
                    stepsHourData2.setCaloriesPerHour(0.0d);
                    arrayList4.add(stepsHourData2);
                }
                i8++;
                size = i;
                treeMap2 = treeMap;
                i7 = i2;
                stepsDayData2 = stepsDayData;
                arrayList2 = arrayList;
                hashMap2 = hashMap;
            }
            ArrayList<StepsResponse> arrayList8 = arrayList2;
            HashMap hashMap3 = hashMap2;
            int i10 = size;
            StepsDayData stepsDayData3 = stepsDayData2;
            int i11 = i7;
            stepsTimeLogBeanData.setLogBeans(arrayList4);
            stepsDayData3.timeLogBean = stepsTimeLogBeanData;
            StepsResponse stepsResponse = new StepsResponse();
            stepsResponse.setStepsDayData(stepsDayData3);
            stepsResponse.setCaloriesDistanceCalculatedFromBand(true);
            stepsResponse.setDistanceIsInMetresFromBand(true);
            if (i11 == hashMap3.size() - 1) {
                stepsResponse.setComplete(true);
            }
            arrayList2 = arrayList8;
            arrayList2.add(stepsResponse);
            i4 = i11 + 1;
            i3 = 0;
            size = i10;
            hashMap2 = hashMap3;
        }
        return arrayList2;
    }
}
