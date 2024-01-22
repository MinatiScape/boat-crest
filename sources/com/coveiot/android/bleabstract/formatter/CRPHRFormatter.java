package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.bean.CRPHeartRateInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import kotlin.Triple;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CRPHRFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3301a;
    public final String b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<CRPHRFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.CRPHRFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, CRPHRFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3302a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, CRPHRFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public CRPHRFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CRPHRFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3302a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CRPHRFormatter(Context context) {
        this.f3301a = context;
        this.b = CRPHRFormatter.class.getSimpleName();
    }

    public /* synthetic */ CRPHRFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<Integer> a(int i, List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && (!list.isEmpty())) {
            try {
                ArrayList<Number> arrayList2 = new ArrayList();
                int size = (i * 60) / list.size();
                int i2 = size > 30 ? 1 : 30 / size;
                int i3 = 1;
                for (Integer num : list) {
                    int intValue = num.intValue();
                    if (i3 > i2) {
                        i3 = 1;
                    }
                    if (intValue > 0) {
                        arrayList2.add(Integer.valueOf(intValue));
                    }
                    if (i3 == i2) {
                        int i4 = 0;
                        if (arrayList2.size() > 0) {
                            for (Number number : arrayList2) {
                                i4 += number.intValue();
                            }
                            arrayList.add(Integer.valueOf(i4 / arrayList2.size()));
                        } else {
                            arrayList.add(0);
                        }
                        arrayList2.clear();
                    }
                    i3++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    @NotNull
    public final ArrayList<HeartRateResponse> convertData(@NotNull List<? extends CRPHeartRateInfo> crpHeartRateInfos, int i) {
        CRPHeartRateInfo cRPHeartRateInfo;
        String str;
        Intrinsics.checkNotNullParameter(crpHeartRateInfos, "crpHeartRateInfos");
        ArrayList<HeartRateResponse> arrayList = new ArrayList<>();
        Iterator<? extends CRPHeartRateInfo> it = crpHeartRateInfos.iterator();
        while (it.hasNext()) {
            CRPHeartRateInfo next = it.next();
            HeartRateDayData heartRateDayData = new HeartRateDayData();
            String str2 = "yyyy-MM-dd";
            heartRateDayData.mDate = AppUtils.formatDate(new Date(next.getStartTime()), "yyyy-MM-dd");
            heartRateDayData.mActivityType = ActivityType.HEART_RATE.toString();
            heartRateDayData.setmMacAddress(BleApiManager.getInstance(this.f3301a).getBleApi().getMacAddress());
            List<Integer> heartRateList = next.getHeartRateList();
            Intrinsics.checkNotNullExpressionValue(heartRateList, "heartRateInfo.heartRateList");
            int i2 = 24;
            Triple<Integer, Integer, Integer> a2 = a(a(24, heartRateList));
            heartRateDayData.setMinHeartRate(a2.getFirst().intValue());
            heartRateDayData.setMaxHeartRate(a2.getSecond().intValue());
            heartRateDayData.setAvgHeartRate(a2.getThird().intValue());
            HeartRateTimeLogBeanData heartRateTimeLogBeanData = new HeartRateTimeLogBeanData();
            ArrayList arrayList2 = new ArrayList();
            TreeMap treeMap = new TreeMap();
            int timeInterval = 60 / next.getTimeInterval();
            int i3 = 1;
            int i4 = 1;
            int i5 = 0;
            for (Integer heartRate : next.getHeartRateList()) {
                if (i4 > timeInterval) {
                    i5++;
                    i4 = 1;
                }
                if (treeMap.containsKey(Integer.valueOf(i5))) {
                    List list = (List) treeMap.get(Integer.valueOf(i5));
                    if (list != null) {
                        Intrinsics.checkNotNullExpressionValue(heartRate, "heartRate");
                        list.add(heartRate);
                    }
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(heartRate);
                    treeMap.put(Integer.valueOf(i5), arrayList3);
                }
                i4++;
                i2 = 24;
            }
            int i6 = i2;
            int i7 = 0;
            while (i7 < i6) {
                long startTime = next.getStartTime();
                Object obj = treeMap.get(Integer.valueOf(i7));
                Intrinsics.checkNotNull(obj);
                List<Integer> list2 = (List) obj;
                HeartRateHourData heartRateHourData = new HeartRateHourData();
                heartRateHourData.setDate(AppUtils.formatDate(new Date(startTime), str2));
                StringBuilder sb = new StringBuilder();
                Locale locale = Locale.ENGLISH;
                Object[] objArr = new Object[i3];
                objArr[0] = Integer.valueOf(i7);
                String format = String.format(locale, "%02d", Arrays.copyOf(objArr, i3));
                Iterator<? extends CRPHeartRateInfo> it2 = it;
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(":00:00");
                heartRateHourData.setStartHour(sb.toString());
                if (i7 == 23) {
                    heartRateHourData.setEndHour("00:00:00");
                    cRPHeartRateInfo = next;
                    str = str2;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    cRPHeartRateInfo = next;
                    str = str2;
                    String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i7 + 1)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    sb2.append(format2);
                    sb2.append(":00:00");
                    heartRateHourData.setEndHour(sb2.toString());
                }
                heartRateHourData.setMacAddress(BleApiManager.getInstance(this.f3301a).getBleApi().getMacAddress());
                heartRateHourData.setActivityType(ActivityType.HEART_RATE.toString());
                ArrayList<Integer> arrayList4 = new ArrayList<>(Collections.nCopies(60, 0));
                int size = 60 / list2.size();
                int i8 = 0;
                for (Integer num : list2) {
                    int intValue = num.intValue();
                    for (int i9 = 0; i9 < size; i9++) {
                        arrayList4.set(i8, Integer.valueOf(intValue));
                        i8++;
                    }
                }
                heartRateHourData.setMinuteWiseData(arrayList4);
                Triple<Integer, Integer, Integer> a3 = a(a(1, list2));
                heartRateHourData.setMinHeartRatePerHour(a3.getFirst().intValue());
                heartRateHourData.setMaxHeartRatePerHour(a3.getSecond().intValue());
                heartRateHourData.setAvgHeartRatePerHour(a3.getThird().intValue());
                arrayList2.add(heartRateHourData);
                i7++;
                it = it2;
                next = cRPHeartRateInfo;
                str2 = str;
                i6 = 24;
                i3 = 1;
            }
            Iterator<? extends CRPHeartRateInfo> it3 = it;
            heartRateTimeLogBeanData.setLogBeans(arrayList2);
            heartRateDayData.timeLogBean = heartRateTimeLogBeanData;
            HeartRateResponse heartRateResponse = new HeartRateResponse();
            heartRateResponse.setHeartRateData(heartRateDayData);
            heartRateResponse.setComplete(i == 1);
            LogHelper.d(this.b, "Heartrate Complete");
            arrayList.add(heartRateResponse);
            it = it3;
        }
        return arrayList;
    }

    @NotNull
    public final LiveHealthData convertToLiveHeartRateData(int i) {
        String str = this.b;
        LogHelper.d(str, "LIVE HEART RATE = " + i + ' ', ModuleNames.BLEABSTRACT.getModuleName());
        LiveHealthData liveHealthData = new LiveHealthData();
        liveHealthData.setHeartRate(i);
        return liveHealthData;
    }

    @NotNull
    public final Context getContext() {
        return this.f3301a;
    }

    public final String getTAG() {
        return this.b;
    }

    public final Triple<Integer, Integer, Integer> a(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((Number) next).intValue() > 0) {
                arrayList.add(next);
            }
        }
        if (arrayList.isEmpty()) {
            return new Triple<>(0, 0, 0);
        }
        int intValue = ((Number) arrayList.get(0)).intValue();
        int intValue2 = ((Number) arrayList.get(0)).intValue();
        Iterator it2 = arrayList.iterator();
        int i = 0;
        while (it2.hasNext()) {
            int intValue3 = ((Number) it2.next()).intValue();
            if (1 <= intValue3 && intValue3 < intValue) {
                intValue = intValue3;
            }
            if (intValue2 < intValue3) {
                intValue2 = intValue3;
            }
            i += intValue3;
        }
        if (i != 0) {
            i /= arrayList.size();
        }
        return new Triple<>(Integer.valueOf(intValue), Integer.valueOf(intValue2), Integer.valueOf(i));
    }
}
