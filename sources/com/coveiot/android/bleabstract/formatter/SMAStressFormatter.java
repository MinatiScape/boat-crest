package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.StressDayData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.StressTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.stress.KhBlePressure;
import com.coveiot.khsmadb.stress.KhStressRepository;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.LogHelper;
import com.szabh.smable3.entity.BlePressure;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
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
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMAStressFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3331a;
    public final String b;
    @NotNull
    public final DecimalFormat c;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMAStressFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMAStressFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMAStressFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3332a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMAStressFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMAStressFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMAStressFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3332a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMAStressFormatter(Context context) {
        this.f3331a = context;
        this.b = SMAStressFormatter.class.getSimpleName();
        this.c = new DecimalFormat("#.##");
    }

    public /* synthetic */ SMAStressFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<StressHourData> a(List<KhBlePressure> list) {
        int i;
        ArrayList arrayList = new ArrayList();
        TreeMap treeMap = new TreeMap();
        Iterator<KhBlePressure> it = list.iterator();
        while (true) {
            i = 11;
            if (!it.hasNext()) {
                break;
            }
            KhBlePressure next = it.next();
            int i2 = SmaUtils.INSTANCE.convertSDKTimeToCalender(next.getMTime()).get(11);
            if (treeMap.containsKey(Integer.valueOf(i2))) {
                ArrayList arrayList2 = (ArrayList) treeMap.get(Integer.valueOf(i2));
                if (arrayList2 != null) {
                    arrayList2.add(next);
                }
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(next);
                treeMap.put(Integer.valueOf(i2), arrayList3);
            }
        }
        int i3 = 0;
        while (i3 < 24) {
            if (treeMap.keySet().contains(Integer.valueOf(i3))) {
                Object obj = treeMap.get(Integer.valueOf(i3));
                Intrinsics.checkNotNull(obj);
                ArrayList<KhBlePressure> arrayList4 = (ArrayList) obj;
                StressHourData stressHourData = new StressHourData();
                SmaUtils smaUtils = SmaUtils.INSTANCE;
                stressHourData.setDate(smaUtils.convertSDKTimeToDate(arrayList4.get(0).getMTime()));
                int i4 = smaUtils.convertSDKTimeToCalender(arrayList4.get(0).getMTime()).get(i);
                StringBuilder sb = new StringBuilder();
                Locale locale = Locale.ENGLISH;
                String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(":00:00");
                stressHourData.setStartHour(sb.toString());
                if (i3 == 23) {
                    stressHourData.setEndHour("00:00:00");
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4 + 1)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    sb2.append(format2);
                    sb2.append(":00:00");
                    stressHourData.setEndHour(sb2.toString());
                }
                stressHourData.setMacAddress(BleApiManager.getInstance(this.f3331a).getBleApi().getMacAddress());
                stressHourData.setActivityType(ActivityType.STRESS.toString());
                ArrayList<Integer> arrayList5 = new ArrayList<>(Collections.nCopies(60, 0));
                Intrinsics.checkNotNull(arrayList4);
                Iterator<KhBlePressure> it2 = arrayList4.iterator();
                while (it2.hasNext()) {
                    KhBlePressure next2 = it2.next();
                    arrayList5.set(SmaUtils.INSTANCE.convertSDKTimeToCalender(next2.getMTime()).get(12), Integer.valueOf(next2.getMValue()));
                }
                stressHourData.setMinuteWiseData(arrayList5);
                stressHourData.setMinStress(a(arrayList4).getFirst().intValue());
                stressHourData.setMaxStress(a(arrayList4).getSecond().intValue());
                stressHourData.setAvgStress(a(arrayList4).getThird().doubleValue());
                arrayList.add(stressHourData);
            } else {
                int mTime = list.get(0).getMTime();
                StressHourData stressHourData2 = new StressHourData();
                stressHourData2.setDate(SmaUtils.INSTANCE.convertSDKTimeToDate(mTime));
                StringBuilder sb3 = new StringBuilder();
                Locale locale2 = Locale.ENGLISH;
                String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                sb3.append(format3);
                sb3.append(":00:00");
                stressHourData2.setStartHour(sb3.toString());
                if (i3 == 23) {
                    stressHourData2.setEndHour("00:00:00");
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3 + 1)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                    sb4.append(format4);
                    sb4.append(":00:00");
                    stressHourData2.setEndHour(sb4.toString());
                }
                stressHourData2.setMacAddress(BleApiManager.getInstance(this.f3331a).getBleApi().getMacAddress());
                stressHourData2.setActivityType(ActivityType.STRESS.toString());
                stressHourData2.setMinuteWiseData(new ArrayList<>(Collections.nCopies(60, 0)));
                stressHourData2.setMinStress(0);
                stressHourData2.setMaxStress(0);
                stressHourData2.setAvgStress(0.0d);
                arrayList.add(stressHourData2);
            }
            i3++;
            i = 11;
        }
        return arrayList;
    }

    @Nullable
    public final Object checkAndUpdateStressDataFromServerToDb(@NotNull List<? extends StressHourData> list, @NotNull Continuation<? super Unit> continuation) {
        for (StressHourData stressHourData : list) {
            if (stressHourData.getDate() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(stressHourData.getTimeStamp());
                String str = this.b;
                LogHelper.d(str, "startTime-> " + stressHourData.getStartHour());
                String str2 = this.b;
                LogHelper.d(str2, "inCalendar-> " + calendar.getTime());
                if (!Intrinsics.areEqual(stressHourData.getDate(), SmaUtils.INSTANCE.convertTimeToDate(System.currentTimeMillis())) || calendar.get(11) <= Calendar.getInstance().get(11)) {
                    ArrayList<Integer> arrayList = stressHourData.mMinuteWiseData;
                    int i = 0;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        Iterator<Integer> it = stressHourData.mMinuteWiseData.iterator();
                        while (it.hasNext()) {
                            int i2 = i + 1;
                            Integer value = it.next();
                            Intrinsics.checkNotNullExpressionValue(value, "value");
                            if (value.intValue() > 0) {
                                Calendar calendar2 = Calendar.getInstance();
                                calendar2.setTimeInMillis(stressHourData.getTimeStamp());
                                calendar2.set(12, i);
                                long timeInMillis = calendar2.getTimeInMillis();
                                SmaUtils smaUtils = SmaUtils.INSTANCE;
                                int timeInMillis2 = (int) ((timeInMillis - smaUtils.getCalenderFor2000().getTimeInMillis()) / 1000);
                                String str3 = this.b;
                                LogHelper.d(str3, "sdkTime-> " + timeInMillis2 + ", " + smaUtils.convertSDKTimeToCalender(timeInMillis2).getTime());
                                int intValue = value.intValue();
                                String str4 = stressHourData.getmMacAddress();
                                Intrinsics.checkNotNullExpressionValue(str4, "stressHourData.getmMacAddress()");
                                KhStressRepository.Companion.getInstance(this.f3331a).insertAll(e.listOf(new KhBlePressure(timeInMillis2, intValue, str4, null, 8, null)));
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
    public final ArrayList<StressResponse> convertData(@NotNull List<KhBlePressure> stressDataList, int i) {
        Intrinsics.checkNotNullParameter(stressDataList, "stressDataList");
        this.c.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        ArrayList<StressResponse> arrayList = new ArrayList<>();
        HashMap hashMap = new HashMap();
        for (KhBlePressure khBlePressure : stressDataList) {
            String convertSDKTimeToDate = SmaUtils.INSTANCE.convertSDKTimeToDate(khBlePressure.getMTime());
            if (hashMap.containsKey(convertSDKTimeToDate)) {
                ArrayList arrayList2 = (ArrayList) hashMap.get(convertSDKTimeToDate);
                if (arrayList2 != null) {
                    arrayList2.add(khBlePressure);
                }
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(khBlePressure);
                hashMap.put(convertSDKTimeToDate, arrayList3);
            }
        }
        int size = hashMap.keySet().size();
        for (int i2 = 0; i2 < size; i2++) {
            Set keySet = hashMap.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "hrDateMap.keys");
            Object obj = CollectionsKt___CollectionsKt.toList(keySet).get(i2);
            Intrinsics.checkNotNullExpressionValue(obj, "hrDateMap.keys.toList()[i]");
            Object obj2 = hashMap.get((String) obj);
            Intrinsics.checkNotNull(obj2);
            ArrayList<KhBlePressure> arrayList4 = (ArrayList) obj2;
            StressDayData stressDayData = new StressDayData();
            stressDayData.mDate = SmaUtils.INSTANCE.convertSDKTimeToDate(arrayList4.get(0).getMTime());
            stressDayData.mActivityType = ActivityType.STRESS.toString();
            String macAddress = BleApiManager.getInstance(this.f3331a).getBleApi().getMacAddress();
            stressDayData.setmMacAddress(macAddress);
            List<StressHourData> a2 = a((List<KhBlePressure>) arrayList4);
            ArrayList arrayList5 = new ArrayList();
            Iterator it = ((ArrayList) a2).iterator();
            while (it.hasNext()) {
                arrayList5.add(new KhBlePressure(0, (int) ((StressHourData) it.next()).getAvgStress(), macAddress, null));
            }
            Triple<Integer, Integer, Double> a3 = a(arrayList4);
            stressDayData.setMinStress(a3.getFirst().intValue());
            stressDayData.setMaxStress(a3.getSecond().intValue());
            stressDayData.setAvgStress(a3.getThird().doubleValue());
            StressTimeLogBeanData stressTimeLogBeanData = new StressTimeLogBeanData();
            stressTimeLogBeanData.setLogBeans(a((List<KhBlePressure>) arrayList4));
            stressDayData.timeLogBean = stressTimeLogBeanData;
            StressResponse stressResponse = new StressResponse();
            stressResponse.setStressDayData(stressDayData);
            if (i2 == hashMap.size() - 1 && i == 0) {
                stressResponse.setComplete(true);
                LogHelper.d("SmaBleService", "Stress Complete");
            }
            arrayList.add(stressResponse);
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3331a;
    }

    @NotNull
    public final List<KhBlePressure> getKhBlePressure(@NotNull String macAddress, @Nullable List<BlePressure> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BlePressure blePressure : list) {
                arrayList.add(new KhBlePressure(blePressure.getMTime(), blePressure.getMValue(), macAddress, null, 8, null));
            }
        }
        return arrayList;
    }

    public final String getTAG() {
        return this.b;
    }

    public final Triple<Integer, Integer, Double> a(ArrayList<KhBlePressure> arrayList) {
        Intrinsics.checkNotNull(arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((KhBlePressure) next).getMValue() > 0) {
                arrayList2.add(next);
            }
        }
        double d = 0.0d;
        if (arrayList2.isEmpty()) {
            return new Triple<>(0, 0, Double.valueOf(0.0d));
        }
        int mValue = ((KhBlePressure) arrayList2.get(0)).getMValue();
        int mValue2 = ((KhBlePressure) arrayList2.get(0)).getMValue();
        Iterator<KhBlePressure> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            KhBlePressure next2 = it2.next();
            if (next2.getMValue() > 0 && mValue > next2.getMValue()) {
                mValue = next2.getMValue();
            }
            if (mValue2 < next2.getMValue()) {
                mValue2 = next2.getMValue();
            }
            d += next2.getMValue();
        }
        return new Triple<>(Integer.valueOf(mValue), Integer.valueOf(mValue2), Double.valueOf(d / arrayList.size()));
    }
}
