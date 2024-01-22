package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.heartrate.KhBleHeartRate;
import com.coveiot.khsmadb.heartrate.KhHeartRateRepository;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.LogHelper;
import com.szabh.smable3.entity.BleHeartRate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.e;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMAHRFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3323a;
    public final String b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMAHRFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMAHRFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMAHRFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3324a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMAHRFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMAHRFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMAHRFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3324a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMAHRFormatter(Context context) {
        this.f3323a = context;
        this.b = SMAHRFormatter.class.getSimpleName();
    }

    public /* synthetic */ SMAHRFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<KhBleHeartRate> arrayList) {
        Intrinsics.checkNotNull(arrayList);
        int mBpm = arrayList.get(0).getMBpm();
        int mBpm2 = arrayList.get(0).getMBpm();
        Iterator<KhBleHeartRate> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KhBleHeartRate next = it.next();
            int mBpm3 = next.getMBpm();
            boolean z = true;
            if (1 > mBpm3 || mBpm3 >= mBpm) {
                z = false;
            }
            if (z) {
                mBpm = next.getMBpm();
            }
            if (mBpm2 < next.getMBpm()) {
                mBpm2 = next.getMBpm();
            }
            i += next.getMBpm();
        }
        if (i != 0) {
            i /= arrayList.size();
        }
        return new Triple<>(Integer.valueOf(mBpm), Integer.valueOf(mBpm2), Integer.valueOf(i));
    }

    public final ArrayList<Integer> b(ArrayList<KhBleHeartRate> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>(Collections.nCopies(60, 0));
        Intrinsics.checkNotNull(arrayList);
        h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.SMAHRFormatter$getMinuteWiseHrData$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(Integer.valueOf(((KhBleHeartRate) t).getMTime()), Integer.valueOf(((KhBleHeartRate) t2).getMTime()));
            }
        });
        Iterator<KhBleHeartRate> it = arrayList.iterator();
        while (it.hasNext()) {
            KhBleHeartRate next = it.next();
            arrayList2.set(SmaUtils.INSTANCE.convertSDKTimeToCalender(next.getMTime()).get(12), Integer.valueOf(next.getMBpm()));
        }
        return arrayList2;
    }

    @Nullable
    public final Object checkAndUpdateHRDataFromServerToDb(@NotNull List<? extends HeartRateHourData> list, @NotNull Continuation<? super Unit> continuation) {
        for (HeartRateHourData heartRateHourData : list) {
            if (heartRateHourData.getDate() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(heartRateHourData.getTimeStamp());
                String str = this.b;
                LogHelper.d(str, "startTime-> " + heartRateHourData.getStartHour());
                String str2 = this.b;
                LogHelper.d(str2, "inCalendar-> " + calendar.getTime());
                if (!Intrinsics.areEqual(heartRateHourData.getDate(), SmaUtils.INSTANCE.convertTimeToDate(System.currentTimeMillis())) || calendar.get(11) <= Calendar.getInstance().get(11)) {
                    ArrayList<Integer> arrayList = heartRateHourData.mMinuteWiseData;
                    int i = 0;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        Iterator<Integer> it = heartRateHourData.mMinuteWiseData.iterator();
                        while (it.hasNext()) {
                            int i2 = i + 1;
                            Integer value = it.next();
                            if (value == null || value.intValue() != 0) {
                                Calendar calendar2 = Calendar.getInstance();
                                calendar2.setTimeInMillis(heartRateHourData.getTimeStamp());
                                calendar2.set(12, i);
                                long timeInMillis = calendar2.getTimeInMillis();
                                SmaUtils smaUtils = SmaUtils.INSTANCE;
                                int timeInMillis2 = (int) ((timeInMillis - smaUtils.getCalenderFor2000().getTimeInMillis()) / 1000);
                                String str3 = this.b;
                                LogHelper.d(str3, "sdkTime-> " + timeInMillis2 + ", " + smaUtils.convertSDKTimeToCalender(timeInMillis2).getTime());
                                Intrinsics.checkNotNullExpressionValue(value, "value");
                                int intValue = value.intValue();
                                String str4 = heartRateHourData.getmMacAddress();
                                Intrinsics.checkNotNullExpressionValue(str4, "hourlyHeartRateData.getmMacAddress()");
                                KhHeartRateRepository.Companion.getInstance(this.f3323a).insertHeartRateData(e.listOf(new KhBleHeartRate(timeInMillis2, intValue, str4, null, 8, null)));
                            }
                            i = i2;
                        }
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    @NotNull
    public final ArrayList<HeartRateResponse> convertData(@NotNull List<KhBleHeartRate> hearRateDataList, int i) {
        ArrayList<HeartRateResponse> arrayList;
        int i2;
        HashMap hashMap;
        int i3;
        HeartRateDayData heartRateDayData;
        TreeMap treeMap;
        int i4;
        Intrinsics.checkNotNullParameter(hearRateDataList, "hearRateDataList");
        ArrayList<HeartRateResponse> arrayList2 = new ArrayList<>();
        HashMap hashMap2 = new HashMap();
        for (KhBleHeartRate khBleHeartRate : hearRateDataList) {
            String convertSDKTimeToDate = SmaUtils.INSTANCE.convertSDKTimeToDate(khBleHeartRate.getMTime());
            if (hashMap2.containsKey(convertSDKTimeToDate)) {
                List list = (List) hashMap2.get(convertSDKTimeToDate);
                if (list != null) {
                    list.add(khBleHeartRate);
                }
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(khBleHeartRate);
                hashMap2.put(convertSDKTimeToDate, arrayList3);
            }
        }
        int size = hashMap2.keySet().size();
        int i5 = 0;
        int i6 = 0;
        while (i6 < size) {
            Set keySet = hashMap2.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "hrDateMap.keys");
            Object obj = CollectionsKt___CollectionsKt.toList(keySet).get(i6);
            Intrinsics.checkNotNullExpressionValue(obj, "hrDateMap.keys.toList()[i]");
            Object obj2 = hashMap2.get((String) obj);
            Intrinsics.checkNotNull(obj2);
            List<KhBleHeartRate> list2 = (List) obj2;
            HeartRateDayData heartRateDayData2 = new HeartRateDayData();
            heartRateDayData2.mDate = SmaUtils.INSTANCE.convertSDKTimeToDate(((KhBleHeartRate) list2.get(i5)).getMTime());
            heartRateDayData2.mActivityType = ActivityType.HEART_RATE.toString();
            heartRateDayData2.setmMacAddress(BleApiManager.getInstance(this.f3323a).getBleApi().getMacAddress());
            Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.khsmadb.heartrate.KhBleHeartRate>");
            Triple<Integer, Integer, Integer> a2 = a((ArrayList) list2);
            heartRateDayData2.setMinHeartRate(a2.getFirst().intValue());
            heartRateDayData2.setMaxHeartRate(a2.getSecond().intValue());
            heartRateDayData2.setAvgHeartRate(a2.getThird().intValue());
            HeartRateTimeLogBeanData heartRateTimeLogBeanData = new HeartRateTimeLogBeanData();
            ArrayList arrayList4 = new ArrayList();
            TreeMap treeMap2 = new TreeMap();
            for (KhBleHeartRate khBleHeartRate2 : list2) {
                int i7 = SmaUtils.INSTANCE.convertSDKTimeToCalender(khBleHeartRate2.getMTime()).get(11);
                if (treeMap2.containsKey(Integer.valueOf(i7))) {
                    ArrayList arrayList5 = (ArrayList) treeMap2.get(Integer.valueOf(i7));
                    if (arrayList5 != null) {
                        arrayList5.add(khBleHeartRate2);
                    }
                } else {
                    ArrayList arrayList6 = new ArrayList();
                    arrayList6.add(khBleHeartRate2);
                    treeMap2.put(Integer.valueOf(i7), arrayList6);
                }
            }
            int i8 = i5;
            while (i8 < 24) {
                if (treeMap2.keySet().contains(Integer.valueOf(i8))) {
                    Object obj3 = treeMap2.get(Integer.valueOf(i8));
                    Intrinsics.checkNotNull(obj3);
                    ArrayList<KhBleHeartRate> arrayList7 = (ArrayList) obj3;
                    i2 = size;
                    HeartRateHourData heartRateHourData = new HeartRateHourData();
                    treeMap = treeMap2;
                    SmaUtils smaUtils = SmaUtils.INSTANCE;
                    arrayList = arrayList2;
                    heartRateHourData.setDate(smaUtils.convertSDKTimeToDate(arrayList7.get(0).getMTime()));
                    int i9 = smaUtils.convertSDKTimeToCalender(arrayList7.get(0).getMTime()).get(11);
                    StringBuilder sb = new StringBuilder();
                    i3 = i6;
                    Locale locale = Locale.ENGLISH;
                    hashMap = hashMap2;
                    heartRateDayData = heartRateDayData2;
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
                    heartRateHourData.setMacAddress(BleApiManager.getInstance(this.f3323a).getBleApi().getMacAddress());
                    heartRateHourData.setActivityType(ActivityType.HEART_RATE.toString());
                    heartRateHourData.setMinuteWiseData(b(arrayList7));
                    heartRateHourData.setMinHeartRatePerHour(a(arrayList7).getFirst().intValue());
                    heartRateHourData.setMaxHeartRatePerHour(a(arrayList7).getSecond().intValue());
                    heartRateHourData.setAvgHeartRatePerHour(a(arrayList7).getThird().intValue());
                    arrayList4.add(heartRateHourData);
                    i4 = 0;
                } else {
                    arrayList = arrayList2;
                    i2 = size;
                    hashMap = hashMap2;
                    i3 = i6;
                    heartRateDayData = heartRateDayData2;
                    treeMap = treeMap2;
                    int mTime = ((KhBleHeartRate) list2.get(0)).getMTime();
                    HeartRateHourData heartRateHourData2 = new HeartRateHourData();
                    heartRateHourData2.setDate(SmaUtils.INSTANCE.convertSDKTimeToDate(mTime));
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
                    heartRateHourData2.setMacAddress(BleApiManager.getInstance(this.f3323a).getBleApi().getMacAddress());
                    heartRateHourData2.setActivityType(ActivityType.HEART_RATE.toString());
                    i4 = 0;
                    heartRateHourData2.setMinuteWiseData(new ArrayList<>(Collections.nCopies(60, 0)));
                    heartRateHourData2.setMinHeartRatePerHour(0);
                    heartRateHourData2.setMaxHeartRatePerHour(0);
                    heartRateHourData2.setAvgHeartRatePerHour(0);
                    arrayList4.add(heartRateHourData2);
                }
                i8++;
                i5 = i4;
                size = i2;
                treeMap2 = treeMap;
                arrayList2 = arrayList;
                i6 = i3;
                hashMap2 = hashMap;
                heartRateDayData2 = heartRateDayData;
            }
            ArrayList<HeartRateResponse> arrayList8 = arrayList2;
            int i10 = size;
            HashMap hashMap3 = hashMap2;
            int i11 = i5;
            HeartRateDayData heartRateDayData3 = heartRateDayData2;
            heartRateTimeLogBeanData.setLogBeans(arrayList4);
            heartRateDayData3.timeLogBean = heartRateTimeLogBeanData;
            HeartRateResponse heartRateResponse = new HeartRateResponse();
            heartRateResponse.setHeartRateData(heartRateDayData3);
            if (i6 == hashMap3.size() - 1 && i == 0) {
                heartRateResponse.setComplete(true);
                LogHelper.d("SmaBleService", "Heartrate Complete");
            }
            arrayList2 = arrayList8;
            arrayList2.add(heartRateResponse);
            i6++;
            i5 = i11;
            size = i10;
            hashMap2 = hashMap3;
        }
        return arrayList2;
    }

    @NotNull
    public final Context getContext() {
        return this.f3323a;
    }

    @NotNull
    public final List<KhBleHeartRate> getKhBleHR(@NotNull String macAddress, @Nullable List<BleHeartRate> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleHeartRate bleHeartRate : list) {
                arrayList.add(new KhBleHeartRate(bleHeartRate.getMTime(), bleHeartRate.getMBpm(), macAddress, null, 8, null));
            }
        }
        return arrayList;
    }

    public final String getTAG() {
        return this.b;
    }
}
