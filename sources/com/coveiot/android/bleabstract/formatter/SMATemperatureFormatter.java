package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.TemperatureDayData;
import com.coveiot.android.bleabstract.response.TemperatureHourData;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.response.TemperatureTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.temperature.KhBleTemperature;
import com.coveiot.khsmadb.temperature.KhTemperatureRepository;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.LogHelper;
import com.szabh.smable3.entity.BleTemperature;
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
public final class SMATemperatureFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3333a;
    public final String b;
    @NotNull
    public final DecimalFormat c;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMATemperatureFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMATemperatureFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMATemperatureFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3334a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMATemperatureFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMATemperatureFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMATemperatureFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3334a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMATemperatureFormatter(Context context) {
        this.f3333a = context;
        this.b = SMATemperatureFormatter.class.getSimpleName();
        this.c = new DecimalFormat("#.##");
    }

    public /* synthetic */ SMATemperatureFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Double, Double, Double> a(ArrayList<KhBleTemperature> arrayList) {
        DecimalFormat decimalFormat = this.c;
        Intrinsics.checkNotNull(arrayList);
        String format = decimalFormat.format(arrayList.get(0).getMTemperature() * 0.1d);
        Intrinsics.checkNotNullExpressionValue(format, "df.format(minuteWiseHrDa….get(0).mTemperature*0.1)");
        double parseDouble = Double.parseDouble(format);
        String format2 = this.c.format(arrayList.get(0).getMTemperature() * 0.1d);
        Intrinsics.checkNotNullExpressionValue(format2, "df.format(minuteWiseHrDa….get(0).mTemperature*0.1)");
        double parseDouble2 = Double.parseDouble(format2);
        Iterator<KhBleTemperature> it = arrayList.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            KhBleTemperature next = it.next();
            if (next.getMTemperature() > 0 && parseDouble > next.getMTemperature()) {
                String format3 = this.c.format(next.getMTemperature() * 0.1d);
                Intrinsics.checkNotNullExpressionValue(format3, "df.format(hrData.mTemperature*0.1)");
                parseDouble = Double.parseDouble(format3);
            }
            if (parseDouble2 < next.getMTemperature()) {
                String format4 = this.c.format(next.getMTemperature() * 0.1d);
                Intrinsics.checkNotNullExpressionValue(format4, "df.format(hrData.mTemperature*0.1)");
                parseDouble2 = Double.parseDouble(format4);
            }
            String format5 = this.c.format(next.getMTemperature() * 0.1d);
            Intrinsics.checkNotNullExpressionValue(format5, "df.format(hrData.mTemperature*0.1)");
            d += Double.parseDouble(format5);
        }
        return new Triple<>(Double.valueOf(parseDouble), Double.valueOf(parseDouble2), Double.valueOf(d / arrayList.size()));
    }

    @Nullable
    public final Object checkAndUpdateTemperatureDataFromServerToDb(@NotNull List<? extends TemperatureHourData> list, @NotNull Continuation<? super Unit> continuation) {
        for (TemperatureHourData temperatureHourData : list) {
            if (temperatureHourData.getDate() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(temperatureHourData.getTimeStamp());
                String str = this.b;
                LogHelper.d(str, "startTime-> " + temperatureHourData.getStartHour());
                String str2 = this.b;
                LogHelper.d(str2, "inCalendar-> " + calendar.getTime());
                if (!Intrinsics.areEqual(temperatureHourData.getDate(), SmaUtils.INSTANCE.convertTimeToDate(System.currentTimeMillis())) || calendar.get(11) <= Calendar.getInstance().get(11)) {
                    ArrayList<Double> arrayList = temperatureHourData.mMinuteWiseData;
                    int i = 0;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        Iterator<Double> it = temperatureHourData.mMinuteWiseData.iterator();
                        while (it.hasNext()) {
                            int i2 = i + 1;
                            Double value = it.next();
                            Intrinsics.checkNotNullExpressionValue(value, "value");
                            if (value.doubleValue() > 0.0d) {
                                Calendar calendar2 = Calendar.getInstance();
                                calendar2.setTimeInMillis(temperatureHourData.getTimeStamp());
                                calendar2.set(12, i);
                                long timeInMillis = calendar2.getTimeInMillis();
                                SmaUtils smaUtils = SmaUtils.INSTANCE;
                                int timeInMillis2 = (int) ((timeInMillis - smaUtils.getCalenderFor2000().getTimeInMillis()) / 1000);
                                String str3 = this.b;
                                LogHelper.d(str3, "sdkTime-> " + timeInMillis2 + ", " + smaUtils.convertSDKTimeToCalender(timeInMillis2).getTime());
                                String str4 = temperatureHourData.getmMacAddress();
                                Intrinsics.checkNotNullExpressionValue(str4, "hourlyTemperatureData.getmMacAddress()");
                                KhTemperatureRepository.Companion.getInstance(this.f3333a).insertTemperatureData(e.listOf(new KhBleTemperature(timeInMillis2, (int) (value.doubleValue() * ((double) 10)), str4, null, 8, null)));
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
    public final ArrayList<TemperatureResponse> convertData(@NotNull List<KhBleTemperature> temperatureDataList, int i) {
        HashMap hashMap;
        int i2;
        TemperatureDayData temperatureDayData;
        TreeMap treeMap;
        int i3;
        KhBleTemperature next;
        Intrinsics.checkNotNullParameter(temperatureDataList, "temperatureDataList");
        this.c.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        ArrayList<TemperatureResponse> arrayList = new ArrayList<>();
        HashMap hashMap2 = new HashMap();
        for (KhBleTemperature khBleTemperature : temperatureDataList) {
            String convertSDKTimeToDate = SmaUtils.INSTANCE.convertSDKTimeToDate(khBleTemperature.getMTime());
            if (hashMap2.containsKey(convertSDKTimeToDate)) {
                List list = (List) hashMap2.get(convertSDKTimeToDate);
                if (list != null) {
                    list.add(khBleTemperature);
                }
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(khBleTemperature);
                hashMap2.put(convertSDKTimeToDate, arrayList2);
            }
        }
        int size = hashMap2.keySet().size();
        int i4 = 0;
        int i5 = 0;
        while (i5 < size) {
            Set keySet = hashMap2.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "hrDateMap.keys");
            Object obj = CollectionsKt___CollectionsKt.toList(keySet).get(i5);
            Intrinsics.checkNotNullExpressionValue(obj, "hrDateMap.keys.toList()[i]");
            Object obj2 = hashMap2.get((String) obj);
            Intrinsics.checkNotNull(obj2);
            List<KhBleTemperature> list2 = (List) obj2;
            TemperatureDayData temperatureDayData2 = new TemperatureDayData();
            temperatureDayData2.mDate = SmaUtils.INSTANCE.convertSDKTimeToDate(((KhBleTemperature) list2.get(i4)).getMTime());
            temperatureDayData2.mActivityType = ActivityType.TEMPERATURE.toString();
            temperatureDayData2.setmMacAddress(BleApiManager.getInstance(this.f3333a).getBleApi().getMacAddress());
            Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.khsmadb.temperature.KhBleTemperature>");
            Triple<Double, Double, Double> a2 = a((ArrayList) list2);
            temperatureDayData2.setMinTemperature(a2.getFirst().doubleValue());
            temperatureDayData2.setMaxTemperature(a2.getSecond().doubleValue());
            TemperatureTimeLogBeanData temperatureTimeLogBeanData = new TemperatureTimeLogBeanData();
            ArrayList arrayList3 = new ArrayList();
            TreeMap treeMap2 = new TreeMap();
            for (KhBleTemperature khBleTemperature2 : list2) {
                int i6 = SmaUtils.INSTANCE.convertSDKTimeToCalender(khBleTemperature2.getMTime()).get(11);
                if (treeMap2.containsKey(Integer.valueOf(i6))) {
                    ArrayList arrayList4 = (ArrayList) treeMap2.get(Integer.valueOf(i6));
                    if (arrayList4 != null) {
                        arrayList4.add(khBleTemperature2);
                    }
                } else {
                    ArrayList arrayList5 = new ArrayList();
                    arrayList5.add(khBleTemperature2);
                    treeMap2.put(Integer.valueOf(i6), arrayList5);
                }
            }
            int i7 = i4;
            while (i7 < 24) {
                ArrayList<TemperatureResponse> arrayList6 = arrayList;
                int i8 = size;
                if (treeMap2.keySet().contains(Integer.valueOf(i7))) {
                    Object obj3 = treeMap2.get(Integer.valueOf(i7));
                    Intrinsics.checkNotNull(obj3);
                    ArrayList<KhBleTemperature> arrayList7 = (ArrayList) obj3;
                    TemperatureHourData temperatureHourData = new TemperatureHourData();
                    SmaUtils smaUtils = SmaUtils.INSTANCE;
                    treeMap = treeMap2;
                    temperatureHourData.setDate(smaUtils.convertSDKTimeToDate(arrayList7.get(0).getMTime()));
                    int i9 = smaUtils.convertSDKTimeToCalender(arrayList7.get(0).getMTime()).get(11);
                    StringBuilder sb = new StringBuilder();
                    i2 = i5;
                    Locale locale = Locale.ENGLISH;
                    hashMap = hashMap2;
                    temperatureDayData = temperatureDayData2;
                    String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    sb.append(format);
                    sb.append(":00:00");
                    temperatureHourData.setStartHour(sb.toString());
                    if (i7 == 23) {
                        temperatureHourData.setEndHour("00:00:00");
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i9 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                        sb2.append(format2);
                        sb2.append(":00:00");
                        temperatureHourData.setEndHour(sb2.toString());
                    }
                    temperatureHourData.setMacAddress(BleApiManager.getInstance(this.f3333a).getBleApi().getMacAddress());
                    temperatureHourData.setActivityType(ActivityType.TEMPERATURE.toString());
                    ArrayList<Double> arrayList8 = new ArrayList<>(Collections.nCopies(60, Double.valueOf(0.0d)));
                    Intrinsics.checkNotNull(arrayList7);
                    Iterator<KhBleTemperature> it = arrayList7.iterator();
                    while (it.hasNext()) {
                        int i10 = SmaUtils.INSTANCE.convertSDKTimeToCalender(it.next().getMTime()).get(12);
                        String format3 = this.c.format(next.getMTemperature() * 0.1d);
                        Intrinsics.checkNotNullExpressionValue(format3, "df.format(data.mTemperature*0.1)");
                        arrayList8.set(i10, Double.valueOf(Double.parseDouble(format3)));
                    }
                    temperatureHourData.setMinuteWiseData(arrayList8);
                    temperatureHourData.setMinTemperaturePerHour(a(arrayList7).getFirst().doubleValue());
                    temperatureHourData.setMaxTemperaturePerHour(a(arrayList7).getSecond().doubleValue());
                    temperatureHourData.setAvgTemperaturePerHour(a(arrayList7).getThird().doubleValue());
                    arrayList3.add(temperatureHourData);
                    i3 = 0;
                } else {
                    hashMap = hashMap2;
                    i2 = i5;
                    temperatureDayData = temperatureDayData2;
                    treeMap = treeMap2;
                    int mTime = ((KhBleTemperature) list2.get(0)).getMTime();
                    TemperatureHourData temperatureHourData2 = new TemperatureHourData();
                    temperatureHourData2.setDate(SmaUtils.INSTANCE.convertSDKTimeToDate(mTime));
                    StringBuilder sb3 = new StringBuilder();
                    Locale locale2 = Locale.ENGLISH;
                    String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i7)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                    sb3.append(format4);
                    sb3.append(":00:00");
                    temperatureHourData2.setStartHour(sb3.toString());
                    if (i7 == 23) {
                        temperatureHourData2.setEndHour("00:00:00");
                        i3 = 0;
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        i3 = 0;
                        String format5 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i7 + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
                        sb4.append(format5);
                        sb4.append(":00:00");
                        temperatureHourData2.setEndHour(sb4.toString());
                    }
                    temperatureHourData2.setMacAddress(BleApiManager.getInstance(this.f3333a).getBleApi().getMacAddress());
                    temperatureHourData2.setActivityType(ActivityType.TEMPERATURE.toString());
                    temperatureHourData2.setMinuteWiseData(new ArrayList<>(Collections.nCopies(60, Double.valueOf(0.0d))));
                    temperatureHourData2.setMinTemperaturePerHour(0.0d);
                    temperatureHourData2.setMaxTemperaturePerHour(0.0d);
                    temperatureHourData2.setAvgTemperaturePerHour(0.0d);
                    arrayList3.add(temperatureHourData2);
                }
                i7++;
                i4 = i3;
                arrayList = arrayList6;
                size = i8;
                treeMap2 = treeMap;
                i5 = i2;
                hashMap2 = hashMap;
                temperatureDayData2 = temperatureDayData;
            }
            ArrayList<TemperatureResponse> arrayList9 = arrayList;
            int i11 = size;
            HashMap hashMap3 = hashMap2;
            int i12 = i4;
            int i13 = i5;
            TemperatureDayData temperatureDayData3 = temperatureDayData2;
            temperatureTimeLogBeanData.setLogBeans(arrayList3);
            temperatureDayData3.timeLogBean = temperatureTimeLogBeanData;
            TemperatureResponse temperatureResponse = new TemperatureResponse();
            temperatureResponse.setTemperatureData(temperatureDayData3);
            if (i13 == hashMap3.size() - 1 && i == 0) {
                temperatureResponse.setComplete(true);
                LogHelper.d("SmaBleService", "Temperature Complete");
            }
            arrayList = arrayList9;
            arrayList.add(temperatureResponse);
            i5 = i13 + 1;
            i4 = i12;
            size = i11;
            hashMap2 = hashMap3;
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3333a;
    }

    @NotNull
    public final List<KhBleTemperature> getKhBleTemperature(@NotNull String macAddress, @Nullable List<BleTemperature> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleTemperature bleTemperature : list) {
                arrayList.add(new KhBleTemperature(bleTemperature.getMTime(), bleTemperature.getMTemperature(), macAddress, null, 8, null));
            }
        }
        return arrayList;
    }

    public final String getTAG() {
        return this.b;
    }
}
