package com.coveiot.android.bleabstract.formatter.matrix;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.matrixUtils.MatrixUtils;
import com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRate;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.bean.data.HeartRateData;
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
public final class MatrixHeartRateFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3366a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixHeartRateFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.matrix.MatrixHeartRateFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixHeartRateFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3367a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixHeartRateFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixHeartRateFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixHeartRateFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3367a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MatrixHeartRateFormatter(Context context) {
        this.f3366a = context;
    }

    public /* synthetic */ MatrixHeartRateFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<KhMatrixHeartRate> arrayList) {
        Intrinsics.checkNotNull(arrayList);
        int mBpm = arrayList.get(0).getMBpm();
        int mBpm2 = arrayList.get(0).getMBpm();
        Iterator<KhMatrixHeartRate> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KhMatrixHeartRate next = it.next();
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

    public final ArrayList<Integer> b(ArrayList<KhMatrixHeartRate> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>(Collections.nCopies(60, 0));
        Intrinsics.checkNotNull(arrayList);
        h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.matrix.MatrixHeartRateFormatter$getMinuteWiseHrData$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(Long.valueOf(((KhMatrixHeartRate) t).getMTime()), Long.valueOf(((KhMatrixHeartRate) t2).getMTime()));
            }
        });
        Iterator<KhMatrixHeartRate> it = arrayList.iterator();
        while (it.hasNext()) {
            KhMatrixHeartRate next = it.next();
            arrayList2.set(MatrixUtils.INSTANCE.convertSDKTimeToCalender(next.getMTime()).get(12), Integer.valueOf(next.getMBpm()));
        }
        return arrayList2;
    }

    @NotNull
    public final ArrayList<HeartRateResponse> convertData(@NotNull List<KhMatrixHeartRate> hearRateDataList) {
        ArrayList<HeartRateResponse> arrayList;
        int i;
        HeartRateDayData heartRateDayData;
        HeartRateTimeLogBeanData heartRateTimeLogBeanData;
        TreeMap treeMap;
        int i2;
        Intrinsics.checkNotNullParameter(hearRateDataList, "hearRateDataList");
        ArrayList<HeartRateResponse> arrayList2 = new ArrayList<>();
        HashMap hashMap = new HashMap();
        for (KhMatrixHeartRate khMatrixHeartRate : hearRateDataList) {
            String convertSDKTimeToDate = MatrixUtils.INSTANCE.convertSDKTimeToDate(khMatrixHeartRate.getMTime());
            if (hashMap.containsKey(convertSDKTimeToDate)) {
                List list = (List) hashMap.get(convertSDKTimeToDate);
                if (list != null) {
                    list.add(khMatrixHeartRate);
                }
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(khMatrixHeartRate);
                hashMap.put(convertSDKTimeToDate, arrayList3);
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
            List<KhMatrixHeartRate> list2 = (List) obj2;
            HeartRateDayData heartRateDayData2 = new HeartRateDayData();
            heartRateDayData2.mDate = MatrixUtils.INSTANCE.convertSDKTimeToDate(((KhMatrixHeartRate) list2.get(i3)).getMTime());
            heartRateDayData2.mActivityType = ActivityType.HEART_RATE.toString();
            heartRateDayData2.setmMacAddress(BleApiManager.getInstance(this.f3366a).getBleApi().getMacAddress());
            Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.khmatrixdb.heartrate.KhMatrixHeartRate>");
            Triple<Integer, Integer, Integer> a2 = a((ArrayList) list2);
            heartRateDayData2.setMinHeartRate(a2.getFirst().intValue());
            heartRateDayData2.setMaxHeartRate(a2.getSecond().intValue());
            heartRateDayData2.setAvgHeartRate(a2.getThird().intValue());
            HeartRateTimeLogBeanData heartRateTimeLogBeanData2 = new HeartRateTimeLogBeanData();
            ArrayList arrayList4 = new ArrayList();
            TreeMap treeMap2 = new TreeMap();
            for (KhMatrixHeartRate khMatrixHeartRate2 : list2) {
                int i5 = i4;
                int i6 = MatrixUtils.INSTANCE.convertSDKTimeToCalender(khMatrixHeartRate2.getMTime()).get(11);
                if (treeMap2.containsKey(Integer.valueOf(i6))) {
                    ArrayList arrayList5 = (ArrayList) treeMap2.get(Integer.valueOf(i6));
                    if (arrayList5 != null) {
                        arrayList5.add(khMatrixHeartRate2);
                    }
                } else {
                    ArrayList arrayList6 = new ArrayList();
                    arrayList6.add(khMatrixHeartRate2);
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
                    ArrayList<KhMatrixHeartRate> arrayList7 = (ArrayList) obj3;
                    i = size;
                    HeartRateHourData heartRateHourData = new HeartRateHourData();
                    treeMap = treeMap2;
                    MatrixUtils matrixUtils = MatrixUtils.INSTANCE;
                    i2 = i7;
                    heartRateDayData = heartRateDayData2;
                    heartRateTimeLogBeanData = heartRateTimeLogBeanData2;
                    heartRateHourData.setDate(matrixUtils.convertSDKTimeToDate(arrayList7.get(0).getMTime()));
                    int i9 = matrixUtils.convertSDKTimeToCalender(arrayList7.get(0).getMTime()).get(11);
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
                    heartRateHourData.setMacAddress(BleApiManager.getInstance(this.f3366a).getBleApi().getMacAddress());
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
                    long mTime = ((KhMatrixHeartRate) list2.get(0)).getMTime();
                    HeartRateHourData heartRateHourData2 = new HeartRateHourData();
                    heartRateHourData2.setDate(MatrixUtils.INSTANCE.convertSDKTimeToDate(mTime));
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
                    heartRateHourData2.setMacAddress(BleApiManager.getInstance(this.f3366a).getBleApi().getMacAddress());
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
                LogHelper.d("MatrixBleService", "Heartrate Complete");
            }
            arrayList8.add(heartRateResponse);
            i4 = i11 + 1;
            arrayList2 = arrayList8;
            i3 = 0;
            size = i10;
        }
        return arrayList2;
    }

    @NotNull
    public final Context getContext() {
        return this.f3366a;
    }

    @NotNull
    public final List<KhMatrixHeartRate> getMatrixBleHeartRate(@Nullable List<? extends HeartRateData> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            String macAddress = BleApiManager.getInstance(this.f3366a).getBleApi().getMacAddress();
            for (HeartRateData heartRateData : list) {
                arrayList.add(new KhMatrixHeartRate(macAddress, heartRateData.getTimeStamp(), heartRateData.getHeartRate(), null, 8, null));
            }
        }
        return arrayList;
    }
}
