package com.coveiot.android.bleabstract.formatter.eastapex;

import android.content.Context;
import com.apex.bluetooth.model.EABlePressureData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.StressDayData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.StressTimeLogBeanData;
import com.coveiot.kheastapexdb.stress.EntityEAStressData;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Triple;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastApexStressFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3350a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexStressFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.eastapex.EastApexStressFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexStressFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3351a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexStressFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexStressFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexStressFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3351a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EastApexStressFormatter(Context context) {
        this.f3350a = context;
    }

    public /* synthetic */ EastApexStressFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Integer, Double> a(ArrayList<EntityEAStressData> arrayList) {
        Intrinsics.checkNotNull(arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((EntityEAStressData) next).getStressValue() > 0) {
                arrayList2.add(next);
            }
        }
        double d = 0.0d;
        if (arrayList2.isEmpty()) {
            return new Triple<>(0, 0, Double.valueOf(0.0d));
        }
        int stressValue = ((EntityEAStressData) arrayList2.get(0)).getStressValue();
        int stressValue2 = ((EntityEAStressData) arrayList2.get(0)).getStressValue();
        Iterator<EntityEAStressData> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            EntityEAStressData next2 = it2.next();
            if (next2.getStressValue() > 0 && stressValue > next2.getStressValue()) {
                stressValue = next2.getStressValue();
            }
            if (stressValue2 < next2.getStressValue()) {
                stressValue2 = next2.getStressValue();
            }
            d += next2.getStressValue();
        }
        return new Triple<>(Integer.valueOf(stressValue), Integer.valueOf(stressValue2), Double.valueOf(d / arrayList.size()));
    }

    @Nullable
    public final ArrayList<EntityEAStressData> convertEAStressDataToEntity(@Nullable List<? extends EABlePressureData> list) {
        if (list != null) {
            ArrayList<EntityEAStressData> arrayList = new ArrayList<>();
            Iterator<? extends EABlePressureData> it = list.iterator();
            while (it.hasNext()) {
                EABlePressureData next = it.next();
                Long valueOf = next != null ? Long.valueOf(next.time_stamp) : null;
                Intrinsics.checkNotNull(valueOf);
                EntityEAStressData entityEAStressData = new EntityEAStressData(valueOf.longValue(), BleApiManager.getInstance(this.f3350a).getBleApi().getMacAddress());
                int i = next.stess_value;
                if (i != 65535) {
                    entityEAStressData.setStressValue(i);
                    entityEAStressData.setLevel(next.e_type.value);
                    entityEAStressData.setTimeStamp(next.time_stamp * 1000);
                    arrayList.add(entityEAStressData);
                }
            }
            return arrayList;
        }
        return null;
    }

    @NotNull
    public final ArrayList<StressResponse> convertStressData(@NotNull List<EntityEAStressData> stressDataList) {
        ArrayList<StressResponse> arrayList;
        int i;
        StressDayData stressDayData;
        StressTimeLogBeanData stressTimeLogBeanData;
        TreeMap treeMap;
        int i2;
        Intrinsics.checkNotNullParameter(stressDataList, "stressDataList");
        ArrayList<StressResponse> arrayList2 = new ArrayList<>();
        HashMap hashMap = new HashMap();
        for (EntityEAStressData entityEAStressData : stressDataList) {
            String dateFromTimeStamp = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityEAStressData.getTimeStamp());
            if (hashMap.containsKey(dateFromTimeStamp)) {
                List list = (List) hashMap.get(dateFromTimeStamp);
                if (list != null) {
                    list.add(entityEAStressData);
                }
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(entityEAStressData);
                Intrinsics.checkNotNull(dateFromTimeStamp);
                hashMap.put(dateFromTimeStamp, arrayList3);
            }
        }
        int size = hashMap.keySet().size();
        int i3 = 0;
        int i4 = 0;
        while (i4 < size) {
            Set keySet = hashMap.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "hrDateMap.keys");
            Object obj = CollectionsKt___CollectionsKt.toList(keySet).get(i4);
            Intrinsics.checkNotNullExpressionValue(obj, "hrDateMap.keys.toList()[i]");
            Object obj2 = hashMap.get((String) obj);
            Intrinsics.checkNotNull(obj2);
            ArrayList<EntityEAStressData> arrayList4 = (ArrayList) obj2;
            StressDayData stressDayData2 = new StressDayData();
            stressDayData2.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(arrayList4.get(i3).getTimeStamp());
            stressDayData2.mActivityType = ActivityType.STRESS.toString();
            stressDayData2.setmMacAddress(BleApiManager.getInstance(this.f3350a).getBleApi().getMacAddress());
            Triple<Integer, Integer, Double> a2 = a(arrayList4);
            stressDayData2.setMinStress(a2.getFirst().intValue());
            stressDayData2.setMaxStress(a2.getSecond().intValue());
            stressDayData2.setAvgStress(a2.getThird().doubleValue());
            StressTimeLogBeanData stressTimeLogBeanData2 = new StressTimeLogBeanData();
            ArrayList arrayList5 = new ArrayList();
            TreeMap treeMap2 = new TreeMap();
            for (EntityEAStressData entityEAStressData2 : arrayList4) {
                int i5 = i4;
                int i6 = BleApiUtils.INSTANCE.getCalendarFromTimeStamp(entityEAStressData2.getTimeStamp()).get(11);
                if (treeMap2.containsKey(Integer.valueOf(i6))) {
                    ArrayList arrayList6 = (ArrayList) treeMap2.get(Integer.valueOf(i6));
                    if (arrayList6 != null) {
                        arrayList6.add(entityEAStressData2);
                    }
                } else {
                    ArrayList arrayList7 = new ArrayList();
                    arrayList7.add(entityEAStressData2);
                    treeMap2.put(Integer.valueOf(i6), arrayList7);
                }
                i4 = i5;
            }
            int i7 = i4;
            int i8 = 0;
            while (i8 < 24) {
                if (treeMap2.keySet().contains(Integer.valueOf(i8))) {
                    Object obj3 = treeMap2.get(Integer.valueOf(i8));
                    Intrinsics.checkNotNull(obj3);
                    ArrayList<EntityEAStressData> arrayList8 = (ArrayList) obj3;
                    i = size;
                    StressHourData stressHourData = new StressHourData();
                    treeMap = treeMap2;
                    BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
                    i2 = i7;
                    stressDayData = stressDayData2;
                    stressTimeLogBeanData = stressTimeLogBeanData2;
                    stressHourData.setDate(bleApiUtils.getDateFromTimeStamp(arrayList8.get(0).getTimeStamp()));
                    int i9 = bleApiUtils.getCalendarFromTimeStamp(arrayList8.get(0).getTimeStamp()).get(11);
                    StringBuilder sb = new StringBuilder();
                    Locale locale = Locale.ENGLISH;
                    arrayList = arrayList2;
                    String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    sb.append(format);
                    sb.append(":00:00");
                    stressHourData.setStartHour(sb.toString());
                    if (i8 == 23) {
                        stressHourData.setEndHour("00:00:00");
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                        sb2.append(format2);
                        sb2.append(":00:00");
                        stressHourData.setEndHour(sb2.toString());
                    }
                    stressHourData.setMacAddress(BleApiManager.getInstance(this.f3350a).getBleApi().getMacAddress());
                    stressHourData.setActivityType(ActivityType.STRESS.toString());
                    ArrayList<Integer> arrayList9 = new ArrayList<>(Collections.nCopies(60, 0));
                    Intrinsics.checkNotNull(arrayList8);
                    Iterator<EntityEAStressData> it = arrayList8.iterator();
                    while (it.hasNext()) {
                        EntityEAStressData next = it.next();
                        arrayList9.set(BleApiUtils.INSTANCE.getCalendarFromTimeStamp(next.getTimeStamp()).get(12), Integer.valueOf(next.getStressValue()));
                    }
                    stressHourData.setMinuteWiseData(arrayList9);
                    stressHourData.setMinStress(a(arrayList8).getFirst().intValue());
                    stressHourData.setMaxStress(a(arrayList8).getSecond().intValue());
                    stressHourData.setAvgStress(a(arrayList8).getThird().doubleValue());
                    arrayList5.add(stressHourData);
                } else {
                    arrayList = arrayList2;
                    i = size;
                    stressDayData = stressDayData2;
                    stressTimeLogBeanData = stressTimeLogBeanData2;
                    treeMap = treeMap2;
                    i2 = i7;
                    long timeStamp = arrayList4.get(0).getTimeStamp();
                    StressHourData stressHourData2 = new StressHourData();
                    stressHourData2.setDate(BleApiUtils.INSTANCE.getDateFromTimeStamp(timeStamp));
                    StringBuilder sb3 = new StringBuilder();
                    Locale locale2 = Locale.ENGLISH;
                    String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                    sb3.append(format3);
                    sb3.append(":00:00");
                    stressHourData2.setStartHour(sb3.toString());
                    if (i8 == 23) {
                        stressHourData2.setEndHour("00:00:00");
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                        sb4.append(format4);
                        sb4.append(":00:00");
                        stressHourData2.setEndHour(sb4.toString());
                    }
                    stressHourData2.setMacAddress(BleApiManager.getInstance(this.f3350a).getBleApi().getMacAddress());
                    stressHourData2.setActivityType(ActivityType.STRESS.toString());
                    stressHourData2.setMinuteWiseData(new ArrayList<>(Collections.nCopies(60, 0)));
                    stressHourData2.setMinStress(0);
                    stressHourData2.setMaxStress(0);
                    stressHourData2.setAvgStress(0.0d);
                    arrayList5.add(stressHourData2);
                }
                i8++;
                size = i;
                treeMap2 = treeMap;
                i7 = i2;
                arrayList2 = arrayList;
                stressDayData2 = stressDayData;
                stressTimeLogBeanData2 = stressTimeLogBeanData;
            }
            ArrayList<StressResponse> arrayList10 = arrayList2;
            int i10 = size;
            StressDayData stressDayData3 = stressDayData2;
            StressTimeLogBeanData stressTimeLogBeanData3 = stressTimeLogBeanData2;
            int i11 = i7;
            stressTimeLogBeanData3.setLogBeans(arrayList5);
            stressDayData3.timeLogBean = stressTimeLogBeanData3;
            StressResponse stressResponse = new StressResponse();
            stressResponse.setStressDayData(stressDayData3);
            if (i11 == hashMap.size() - 1) {
                stressResponse.setComplete(true);
                LogHelper.d("EastapexBleService", "Stress Complete");
            }
            arrayList10.add(stressResponse);
            i4 = i11 + 1;
            arrayList2 = arrayList10;
            i3 = 0;
            size = i10;
        }
        return arrayList2;
    }

    @NotNull
    public final Context getContext() {
        return this.f3350a;
    }
}
