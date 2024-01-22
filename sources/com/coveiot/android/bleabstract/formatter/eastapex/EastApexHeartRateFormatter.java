package com.coveiot.android.bleabstract.formatter.eastapex;

import android.content.Context;
import com.apex.bluetooth.model.EABleHeartData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.kheastapexdb.heartrate.EntityEAHeartRateData;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.LogHelper;
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
public final class EastApexHeartRateFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3342a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexHeartRateFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.eastapex.EastApexHeartRateFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexHeartRateFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3343a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexHeartRateFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexHeartRateFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexHeartRateFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3343a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EastApexHeartRateFormatter(Context context) {
        this.f3342a = context;
    }

    public /* synthetic */ EastApexHeartRateFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<EntityEAHeartRateData> arrayList) {
        Intrinsics.checkNotNull(arrayList);
        int hrValue = arrayList.get(0).getHrValue();
        int hrValue2 = arrayList.get(0).getHrValue();
        Iterator<EntityEAHeartRateData> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            EntityEAHeartRateData next = it.next();
            int hrValue3 = next.getHrValue();
            boolean z = true;
            if (1 > hrValue3 || hrValue3 >= hrValue) {
                z = false;
            }
            if (z) {
                hrValue = next.getHrValue();
            }
            if (hrValue2 < next.getHrValue()) {
                hrValue2 = next.getHrValue();
            }
            i += next.getHrValue();
        }
        if (i != 0) {
            i /= arrayList.size();
        }
        return new Triple<>(Integer.valueOf(hrValue), Integer.valueOf(hrValue2), Integer.valueOf(i));
    }

    public final ArrayList<Integer> b(ArrayList<EntityEAHeartRateData> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>(Collections.nCopies(60, 0));
        Intrinsics.checkNotNull(arrayList);
        h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.eastapex.EastApexHeartRateFormatter$getMinuteWiseHrData$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(Long.valueOf(((EntityEAHeartRateData) t).getTimeStamp()), Long.valueOf(((EntityEAHeartRateData) t2).getTimeStamp()));
            }
        });
        Iterator<EntityEAHeartRateData> it = arrayList.iterator();
        while (it.hasNext()) {
            EntityEAHeartRateData next = it.next();
            arrayList2.set(BleApiUtils.INSTANCE.getCalendarFromTimeStamp(next.getTimeStamp()).get(12), Integer.valueOf(next.getHrValue()));
        }
        return arrayList2;
    }

    @NotNull
    public final ArrayList<HeartRateResponse> convertData(@NotNull List<EntityEAHeartRateData> hearRateDataList) {
        ArrayList<HeartRateResponse> arrayList;
        int i;
        HeartRateDayData heartRateDayData;
        HeartRateTimeLogBeanData heartRateTimeLogBeanData;
        TreeMap treeMap;
        int i2;
        Intrinsics.checkNotNullParameter(hearRateDataList, "hearRateDataList");
        ArrayList<HeartRateResponse> arrayList2 = new ArrayList<>();
        HashMap hashMap = new HashMap();
        for (EntityEAHeartRateData entityEAHeartRateData : hearRateDataList) {
            String dateFromTimeStamp = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityEAHeartRateData.getTimeStamp());
            if (hashMap.containsKey(dateFromTimeStamp)) {
                List list = (List) hashMap.get(dateFromTimeStamp);
                if (list != null) {
                    list.add(entityEAHeartRateData);
                }
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(entityEAHeartRateData);
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
            List<EntityEAHeartRateData> list2 = (List) obj2;
            HeartRateDayData heartRateDayData2 = new HeartRateDayData();
            heartRateDayData2.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(((EntityEAHeartRateData) list2.get(i3)).getTimeStamp());
            heartRateDayData2.mActivityType = ActivityType.HEART_RATE.toString();
            heartRateDayData2.setmMacAddress(BleApiManager.getInstance(this.f3342a).getBleApi().getMacAddress());
            Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.kheastapexdb.heartrate.EntityEAHeartRateData>");
            Triple<Integer, Integer, Integer> a2 = a((ArrayList) list2);
            heartRateDayData2.setMinHeartRate(a2.getFirst().intValue());
            heartRateDayData2.setMaxHeartRate(a2.getSecond().intValue());
            heartRateDayData2.setAvgHeartRate(a2.getThird().intValue());
            HeartRateTimeLogBeanData heartRateTimeLogBeanData2 = new HeartRateTimeLogBeanData();
            ArrayList arrayList4 = new ArrayList();
            TreeMap treeMap2 = new TreeMap();
            for (EntityEAHeartRateData entityEAHeartRateData2 : list2) {
                int i5 = i4;
                int i6 = BleApiUtils.INSTANCE.getCalendarFromTimeStamp(entityEAHeartRateData2.getTimeStamp()).get(11);
                if (treeMap2.containsKey(Integer.valueOf(i6))) {
                    ArrayList arrayList5 = (ArrayList) treeMap2.get(Integer.valueOf(i6));
                    if (arrayList5 != null) {
                        arrayList5.add(entityEAHeartRateData2);
                    }
                } else {
                    ArrayList arrayList6 = new ArrayList();
                    arrayList6.add(entityEAHeartRateData2);
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
                    ArrayList<EntityEAHeartRateData> arrayList7 = (ArrayList) obj3;
                    i = size;
                    HeartRateHourData heartRateHourData = new HeartRateHourData();
                    treeMap = treeMap2;
                    BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
                    i2 = i7;
                    heartRateDayData = heartRateDayData2;
                    heartRateTimeLogBeanData = heartRateTimeLogBeanData2;
                    heartRateHourData.setDate(bleApiUtils.getDateFromTimeStamp(arrayList7.get(0).getTimeStamp()));
                    int i9 = bleApiUtils.getCalendarFromTimeStamp(arrayList7.get(0).getTimeStamp()).get(11);
                    StringBuilder sb = new StringBuilder();
                    Locale locale = Locale.ENGLISH;
                    arrayList = arrayList2;
                    String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    sb.append(format);
                    sb.append(":00:00");
                    heartRateHourData.setStartHour(sb.toString());
                    if (i8 == 23) {
                        heartRateHourData.setEndHour("00:00:00");
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                        sb2.append(format2);
                        sb2.append(":00:00");
                        heartRateHourData.setEndHour(sb2.toString());
                    }
                    heartRateHourData.setMacAddress(BleApiManager.getInstance(this.f3342a).getBleApi().getMacAddress());
                    heartRateHourData.setActivityType(ActivityType.HEART_RATE.toString());
                    heartRateHourData.setMinuteWiseData(b(arrayList7));
                    heartRateHourData.setMinHeartRatePerHour(a(arrayList7).getFirst().intValue());
                    heartRateHourData.setMaxHeartRatePerHour(a(arrayList7).getSecond().intValue());
                    heartRateHourData.setAvgHeartRatePerHour(a(arrayList7).getThird().intValue());
                    arrayList4.add(heartRateHourData);
                } else {
                    arrayList = arrayList2;
                    i = size;
                    heartRateDayData = heartRateDayData2;
                    heartRateTimeLogBeanData = heartRateTimeLogBeanData2;
                    treeMap = treeMap2;
                    i2 = i7;
                    long timeStamp = ((EntityEAHeartRateData) list2.get(0)).getTimeStamp();
                    HeartRateHourData heartRateHourData2 = new HeartRateHourData();
                    heartRateHourData2.setDate(BleApiUtils.INSTANCE.getDateFromTimeStamp(timeStamp));
                    StringBuilder sb3 = new StringBuilder();
                    Locale locale2 = Locale.ENGLISH;
                    String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                    sb3.append(format3);
                    sb3.append(":00:00");
                    heartRateHourData2.setStartHour(sb3.toString());
                    if (i8 == 23) {
                        heartRateHourData2.setEndHour("00:00:00");
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i8 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                        sb4.append(format4);
                        sb4.append(":00:00");
                        heartRateHourData2.setEndHour(sb4.toString());
                    }
                    heartRateHourData2.setMacAddress(BleApiManager.getInstance(this.f3342a).getBleApi().getMacAddress());
                    heartRateHourData2.setActivityType(ActivityType.HEART_RATE.toString());
                    heartRateHourData2.setMinuteWiseData(new ArrayList<>(Collections.nCopies(60, 0)));
                    heartRateHourData2.setMinHeartRatePerHour(0);
                    heartRateHourData2.setMaxHeartRatePerHour(0);
                    heartRateHourData2.setAvgHeartRatePerHour(0);
                    arrayList4.add(heartRateHourData2);
                }
                i8++;
                size = i;
                treeMap2 = treeMap;
                i7 = i2;
                arrayList2 = arrayList;
                heartRateDayData2 = heartRateDayData;
                heartRateTimeLogBeanData2 = heartRateTimeLogBeanData;
            }
            ArrayList<HeartRateResponse> arrayList8 = arrayList2;
            int i10 = size;
            HeartRateDayData heartRateDayData3 = heartRateDayData2;
            HeartRateTimeLogBeanData heartRateTimeLogBeanData3 = heartRateTimeLogBeanData2;
            int i11 = i7;
            heartRateTimeLogBeanData3.setLogBeans(arrayList4);
            heartRateDayData3.timeLogBean = heartRateTimeLogBeanData3;
            HeartRateResponse heartRateResponse = new HeartRateResponse();
            heartRateResponse.setHeartRateData(heartRateDayData3);
            if (i11 == hashMap.size() - 1) {
                heartRateResponse.setComplete(true);
                LogHelper.d("EastApexHeartRateFormatter", "Heart Rate Complete");
            }
            arrayList8.add(heartRateResponse);
            i4 = i11 + 1;
            arrayList2 = arrayList8;
            i3 = 0;
            size = i10;
        }
        return arrayList2;
    }

    @Nullable
    public final ArrayList<EntityEAHeartRateData> convertEAHRDataToEntity(@Nullable List<? extends EABleHeartData> list) {
        if (list != null) {
            ArrayList<EntityEAHeartRateData> arrayList = new ArrayList<>();
            Iterator<? extends EABleHeartData> it = list.iterator();
            while (it.hasNext()) {
                EABleHeartData next = it.next();
                Long valueOf = next != null ? Long.valueOf(next.time_stamp) : null;
                Intrinsics.checkNotNull(valueOf);
                EntityEAHeartRateData entityEAHeartRateData = new EntityEAHeartRateData(valueOf.longValue(), BleApiManager.getInstance(this.f3342a).getBleApi().getMacAddress());
                entityEAHeartRateData.setHrValue(next.hr_value);
                entityEAHeartRateData.setTimeStamp(next.time_stamp * 1000);
                arrayList.add(entityEAHeartRateData);
            }
            return arrayList;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3342a;
    }
}
