package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.models.ActivityGPSSample;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.models.ManualBpReading;
import com.coveiot.android.bleabstract.models.ManualHRVAndStressReading;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.HRVHourData;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.PeriodicSpo2Response;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.response.Spo2DayData;
import com.coveiot.android.bleabstract.response.Spo2HourData;
import com.coveiot.android.bleabstract.response.Spo2TimeLogBeanData;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.TemperatureDayData;
import com.coveiot.android.bleabstract.response.TemperatureHourData;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.response.TemperatureTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.jstyle2301a.model.JstyleHRData;
import com.coveiot.android.jstyle2301a.model.JstyleHrvData;
import com.coveiot.android.jstyle2301a.model.JstyleSleepData;
import com.coveiot.android.jstyle2301a.model.JstyleSpo2Data;
import com.coveiot.android.jstyle2301a.model.JstyleStepData;
import com.coveiot.android.jstyle2301a.model.JstyleTemperatureData;
import com.coveiot.khjstyledb.gps.KHJstyleGPSRepository;
import com.coveiot.khjstyledb.gps.model.KHSessionGPSData;
import com.coveiot.khjstyledb.heartrate.KHJstyleHRRepository;
import com.coveiot.khjstyledb.heartrate.model.SessionHR;
import com.coveiot.khjstyledb.walk.KHJstyleHourlyWalkData;
import com.coveiot.khjstyledb.walk.KHJstyleWalkDBRepository;
import com.coveiot.khjstyledb.walk.model.SessionSteps;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.maps.model.LatLng;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class JStyle2301AFormatter {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3315a;
    public final String b = JStyle2301AFormatter.class.getSimpleName();
    @NotNull
    public final DecimalFormat c = new DecimalFormat("0.00");

    public JStyle2301AFormatter(@Nullable String str) {
        this.f3315a = str;
    }

    public final List<HeartRateHourData> a(Map<String, ? extends ArrayList<JstyleHRData>> map) {
        String formattedDate;
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            ArrayList<JstyleHRData> arrayList2 = map.get(str);
            ArrayList<Integer> emptyHourCodedValuesList = JStyleUtils.getEmptyHourCodedValuesList();
            if (arrayList2 != null && arrayList2.size() > 0) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    JstyleHRData jstyleHRData = arrayList2.get(i);
                    String time = jstyleHRData != null ? jstyleHRData.getTime() : null;
                    Intrinsics.checkNotNull(time);
                    Object[] array = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    int parseInt = Integer.parseInt(((String[]) array)[1]);
                    JstyleHRData jstyleHRData2 = arrayList2.get(i);
                    Integer hrValue = jstyleHRData2 != null ? jstyleHRData2.getHrValue() : null;
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    sb.append(hrValue);
                    sb.append(']');
                    ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(sb.toString());
                    int size2 = integerArrayListFromString.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (parseInt < 60) {
                            if (integerArrayListFromString.get(i2) == null) {
                                integerArrayListFromString.set(i2, 0);
                            }
                            emptyHourCodedValuesList.set(parseInt, integerArrayListFromString.get(i2));
                            parseInt++;
                        }
                    }
                }
            }
            Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array2;
            String str2 = strArr[0];
            int parseInt2 = Integer.parseInt(strArr[1]);
            int i3 = parseInt2 + 1;
            if (i3 == 24) {
                i3 = 0;
            }
            StringBuilder sb2 = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(parseInt2)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb2.append(format);
            sb2.append(":00:00");
            String sb3 = sb2.toString();
            StringBuilder sb4 = new StringBuilder();
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb4.append(format2);
            sb4.append(":00:00");
            String sb5 = sb4.toString();
            try {
                formattedDate = JStyleUtils.getFormattedDate(str2);
            } catch (ParseException unused) {
                formattedDate = JStyleUtils.getFormattedDate(str2, "yyyy-MM-dd");
            }
            Integer minValueFromList = JStyleUtils.getMinValueFromList(emptyHourCodedValuesList);
            Integer maxValueFromList = JStyleUtils.getMaxValueFromList(emptyHourCodedValuesList);
            Integer avgValueFromList = JStyleUtils.getAvgValueFromList(emptyHourCodedValuesList);
            HeartRateHourData heartRateHourData = new HeartRateHourData();
            heartRateHourData.setDate(formattedDate);
            heartRateHourData.setStartHour(sb3);
            heartRateHourData.setEndHour(sb5);
            if (maxValueFromList != null) {
                heartRateHourData.maxHeartRatePerHour = maxValueFromList.intValue();
            }
            if (minValueFromList != null) {
                heartRateHourData.minHeartRatePerHour = minValueFromList.intValue();
            }
            if (avgValueFromList != null) {
                heartRateHourData.avgHeartRatePerHour = avgValueFromList.intValue();
            }
            heartRateHourData.setMinuteWiseData(emptyHourCodedValuesList);
            heartRateHourData.setActivityType(ActivityType.HEART_RATE.toString());
            arrayList.add(heartRateHourData);
        }
        return arrayList;
    }

    public final List<SleepHourData> b(Map<String, ? extends ArrayList<JstyleSleepData>> map) {
        int i;
        int i2;
        ArrayList<Integer> arrayList;
        ArrayList arrayList2 = new ArrayList();
        Set<String> keySet = map.keySet();
        ArrayList arrayList3 = new ArrayList();
        Iterator<String> it = keySet.iterator();
        while (true) {
            i = 0;
            i2 = 1;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            ArrayList<JstyleSleepData> arrayList4 = map.get(next);
            List sortedWith = arrayList4 != null ? CollectionsKt___CollectionsKt.sortedWith(arrayList4, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.JStyle2301AFormatter$convertRawResponseToSleepDayData$$inlined$compareBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    JstyleSleepData jstyleSleepData = (JstyleSleepData) t;
                    JstyleSleepData jstyleSleepData2 = (JstyleSleepData) t2;
                    return f.compareValues(jstyleSleepData != null ? jstyleSleepData.getTime() : null, jstyleSleepData2 != null ? jstyleSleepData2.getTime() : null);
                }
            }) : null;
            ArrayList<Integer> emptyDaySleepCodedValuesList = JStyleUtils.getEmptyDaySleepCodedValuesList();
            if (sortedWith != null && sortedWith.size() > 0) {
                int size = sortedWith.size();
                for (int i3 = 0; i3 < size; i3++) {
                    JstyleSleepData jstyleSleepData = (JstyleSleepData) sortedWith.get(i3);
                    String time = jstyleSleepData != null ? jstyleSleepData.getTime() : null;
                    Intrinsics.checkNotNull(time);
                    Object[] array = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    String[] strArr = (String[]) array;
                    int parseInt = (Integer.parseInt(strArr[0]) * 60) + Integer.parseInt(strArr[1]);
                    JstyleSleepData jstyleSleepData2 = (JstyleSleepData) sortedWith.get(i3);
                    ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(String.valueOf(jstyleSleepData2 != null ? jstyleSleepData2.getDetailSleep() : null));
                    int size2 = integerArrayListFromString.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        if (parseInt <= 1439) {
                            emptyDaySleepCodedValuesList.set(parseInt, integerArrayListFromString.get(i4));
                            parseInt++;
                        }
                    }
                }
            }
            JstyleSleepData jstyleSleepData3 = new JstyleSleepData();
            jstyleSleepData3.setDate(next);
            jstyleSleepData3.setDetailSleep(emptyDaySleepCodedValuesList.toString());
            arrayList3.add(jstyleSleepData3);
        }
        LogHelper.d(this.b, "sleepDataArrayList" + arrayList3.toArray(), ModuleNames.BLEABSTRACT.getModuleName());
        int size3 = arrayList3.size();
        int i5 = 0;
        while (i5 < size3) {
            String detailSleep = ((JstyleSleepData) arrayList3.get(i5)).getDetailSleep();
            Intrinsics.checkNotNull(detailSleep);
            ArrayList<Integer> integerArrayListFromString2 = JStyleUtils.getIntegerArrayListFromString(new Regex("null").replace(detailSleep, BleConst.GetDeviceTime));
            int i6 = i;
            while (i6 < 24) {
                int i7 = i6 + 1;
                int i8 = i7 == 24 ? i : i7;
                StringBuilder sb = new StringBuilder();
                Locale locale = Locale.ENGLISH;
                Object[] objArr = new Object[i2];
                objArr[i] = Integer.valueOf(i6);
                String format = String.format(locale, "%02d", Arrays.copyOf(objArr, i2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(":00:00");
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                int i9 = size3;
                Object[] objArr2 = new Object[i2];
                objArr2[0] = Integer.valueOf(i8);
                String format2 = String.format(locale, "%02d", Arrays.copyOf(objArr2, i2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb3.append(format2);
                sb3.append(":00:00");
                String sb4 = sb3.toString();
                ArrayList<Integer> arrayList5 = new ArrayList<>();
                int i10 = i6 * 60;
                arrayList5.addAll(integerArrayListFromString2.subList(i10, i10 + 60));
                int size4 = arrayList5.size();
                int i11 = 0;
                int i12 = 0;
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                while (i11 < size4) {
                    Integer num = arrayList5.get(i11);
                    Intrinsics.checkNotNullExpressionValue(num, "hourCodedValuesList[k]");
                    int intValue = num.intValue();
                    if (intValue == -1) {
                        i14++;
                    } else if (intValue < 0 || intValue > 2) {
                        arrayList = integerArrayListFromString2;
                        if (intValue > 2 && intValue <= 8) {
                            i12++;
                        } else if (intValue <= 8 || intValue > 20) {
                            i15++;
                        }
                        i11++;
                        integerArrayListFromString2 = arrayList;
                    } else {
                        i13++;
                    }
                    arrayList = integerArrayListFromString2;
                    i11++;
                    integerArrayListFromString2 = arrayList;
                }
                SleepHourData sleepHourData = new SleepHourData();
                String date = ((JstyleSleepData) arrayList3.get(i5)).getDate();
                Intrinsics.checkNotNull(date);
                sleepHourData.setDate(JStyleUtils.getFormattedDate(StringsKt__StringsKt.trim(date).toString(), "yyyy-MM-dd"));
                sleepHourData.setStartHour(sb2);
                sleepHourData.setEndHour(sb4);
                sleepHourData.setMinuteWiseData(arrayList5);
                sleepHourData.setAwakePerHour(i15);
                sleepHourData.setLightSleepPerHour(i12);
                sleepHourData.setDeepSleepPerHour(i13);
                sleepHourData.setUnSleepPerHour(i14);
                sleepHourData.setTotalSleepPerHour(i12 + i13);
                arrayList2.add(sleepHourData);
                size3 = i9;
                i6 = i7;
                i = 0;
                integerArrayListFromString2 = integerArrayListFromString2;
                i2 = 1;
            }
            i5++;
            i2 = 1;
        }
        return arrayList2;
    }

    public final List<Spo2HourData> c(Map<String, ? extends ArrayList<JstyleSpo2Data>> map) {
        String formattedDate;
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            ArrayList<JstyleSpo2Data> arrayList2 = map.get(str);
            ArrayList<Integer> emptyHourCodedValuesList = JStyleUtils.getEmptyHourCodedValuesList();
            if (arrayList2 != null && arrayList2.size() > 0) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    JstyleSpo2Data jstyleSpo2Data = arrayList2.get(i);
                    Long dateTime = jstyleSpo2Data != null ? jstyleSpo2Data.getDateTime() : null;
                    Intrinsics.checkNotNull(dateTime);
                    long longValue = dateTime.longValue();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(longValue);
                    int i2 = calendar.get(12);
                    JstyleSpo2Data jstyleSpo2Data2 = arrayList2.get(i);
                    Integer spo2 = jstyleSpo2Data2 != null ? jstyleSpo2Data2.getSpo2() : null;
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    sb.append(spo2);
                    sb.append(']');
                    ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(sb.toString());
                    int size2 = integerArrayListFromString.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        if (i2 < 60) {
                            if (integerArrayListFromString.get(i3) == null) {
                                integerArrayListFromString.set(i3, 0);
                            }
                            emptyHourCodedValuesList.set(i2, integerArrayListFromString.get(i3));
                            i2++;
                        }
                    }
                }
            }
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            String str2 = strArr[0];
            int parseInt = Integer.parseInt(strArr[1]);
            int i4 = parseInt + 1;
            if (i4 == 24) {
                i4 = 0;
            }
            StringBuilder sb2 = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(parseInt)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb2.append(format);
            sb2.append(":00:00");
            String sb3 = sb2.toString();
            StringBuilder sb4 = new StringBuilder();
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb4.append(format2);
            sb4.append(":00:00");
            String sb5 = sb4.toString();
            try {
                formattedDate = JStyleUtils.getFormattedDate(str2);
            } catch (ParseException unused) {
                formattedDate = JStyleUtils.getFormattedDate(str2, "yyyy-MM-dd");
            }
            Integer minValueFromList = JStyleUtils.getMinValueFromList(emptyHourCodedValuesList);
            Integer maxValueFromList = JStyleUtils.getMaxValueFromList(emptyHourCodedValuesList);
            Integer avgValueFromList = JStyleUtils.getAvgValueFromList(emptyHourCodedValuesList);
            Spo2HourData spo2HourData = new Spo2HourData();
            spo2HourData.setDate(formattedDate);
            spo2HourData.setStartHour(sb3);
            spo2HourData.setEndHour(sb5);
            if (maxValueFromList != null) {
                spo2HourData.maxSpo2PerHour = maxValueFromList.intValue();
            }
            if (minValueFromList != null) {
                spo2HourData.minSpo2PerHour = minValueFromList.intValue();
            }
            if (avgValueFromList != null) {
                spo2HourData.avgSpo2PerHour = avgValueFromList.intValue();
            }
            spo2HourData.setMinuteWiseData(emptyHourCodedValuesList);
            spo2HourData.setActivityType(ActivityType.HEART_RATE.toString());
            arrayList.add(spo2HourData);
        }
        return arrayList;
    }

    @NotNull
    public final LiveHealthData convertToLiveHealthData(@NotNull Map<String, String> realTimeData) {
        Intrinsics.checkNotNullParameter(realTimeData, "realTimeData");
        String str = realTimeData.get(DeviceKey.HeartRate);
        String str2 = this.b;
        LogHelper.d(str2, "LIVE DATA HEART RATE = " + str, ModuleNames.BLEABSTRACT.getModuleName());
        LiveHealthData liveHealthData = new LiveHealthData();
        liveHealthData.setHeartRate(str != null ? Integer.parseInt(str) : 0);
        return liveHealthData;
    }

    @NotNull
    public final LiveStepsData convertToLiveStepsData(@NotNull Map<String, String> realTimeData) {
        Intrinsics.checkNotNullParameter(realTimeData, "realTimeData");
        String str = realTimeData.get(DeviceKey.Step);
        String str2 = realTimeData.get("calories");
        String str3 = realTimeData.get("distance");
        String str4 = this.b;
        LogHelper.d(str4, "LIVE DATA STEPS = " + str + " Calories = " + str2 + " Distance = " + str3, ModuleNames.BLEABSTRACT.getModuleName());
        LiveStepsData liveStepsData = new LiveStepsData();
        int parseInt = str != null ? Integer.parseInt(str) : 0;
        double parseDouble = str2 != null ? Double.parseDouble(str2) : 0.0d;
        double parseDouble2 = str3 != null ? Double.parseDouble(str3) * 1000 : 0.0d;
        liveStepsData.setLiveSteps(parseInt);
        liveStepsData.setMeters((int) parseDouble2);
        liveStepsData.setCalories(parseDouble);
        return liveStepsData;
    }

    @NotNull
    public final LiveTemperatureData convertToLiveTemperatureData(@NotNull Map<String, String> realTimeData) {
        Intrinsics.checkNotNullParameter(realTimeData, "realTimeData");
        String str = realTimeData.get("TempData");
        String str2 = this.b;
        LogHelper.d(str2, "LIVE DATA TEMPERATURE = " + str, ModuleNames.BLEABSTRACT.getModuleName());
        LiveTemperatureData liveTemperatureData = new LiveTemperatureData();
        liveTemperatureData.setTemperature(str != null ? Double.parseDouble(str) : 0.0d);
        return liveTemperatureData;
    }

    public final List<StepsHourData> d(Map<String, ? extends ArrayList<JstyleStepData>> map) {
        Iterator<String> it;
        double d;
        double d2;
        String formattedDate;
        int i;
        ArrayList<JstyleStepData> arrayList;
        JStyle2301AFormatter jStyle2301AFormatter = this;
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            ArrayList<JstyleStepData> arrayList3 = map.get(next);
            ArrayList<Integer> emptyHourCodedValuesList = JStyleUtils.getEmptyHourCodedValuesList();
            double d3 = 0.0d;
            int i2 = 0;
            if (arrayList3 == null || arrayList3.size() <= 0) {
                it = it2;
                d = 0.0d;
                d2 = 0.0d;
            } else {
                int size = arrayList3.size();
                d2 = 0.0d;
                int i3 = 0;
                while (i3 < size) {
                    JstyleStepData jstyleStepData = arrayList3.get(i3);
                    String time = jstyleStepData != null ? jstyleStepData.getTime() : null;
                    Intrinsics.checkNotNull(time);
                    List split$default = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null);
                    Iterator<String> it3 = it2;
                    Object[] array = split$default.toArray(new String[i2]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    int parseInt = Integer.parseInt(((String[]) array)[1]);
                    JstyleStepData jstyleStepData2 = arrayList3.get(i3);
                    ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(jstyleStepData2 != null ? jstyleStepData2.getDetailStep() : null);
                    JstyleStepData jstyleStepData3 = arrayList3.get(i3);
                    String totalStep = jstyleStepData3 != null ? jstyleStepData3.getTotalStep() : null;
                    Intrinsics.checkNotNull(totalStep);
                    Integer.parseInt(totalStep);
                    JstyleStepData jstyleStepData4 = arrayList3.get(i3);
                    String cal = jstyleStepData4 != null ? jstyleStepData4.getCal() : null;
                    Intrinsics.checkNotNull(cal);
                    double parseDouble = d2 + Double.parseDouble(cal);
                    JstyleStepData jstyleStepData5 = arrayList3.get(i3);
                    String distance = jstyleStepData5 != null ? jstyleStepData5.getDistance() : null;
                    Intrinsics.checkNotNull(distance);
                    jStyle2301AFormatter.c.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
                    String format = jStyle2301AFormatter.c.format(parseDouble);
                    Intrinsics.checkNotNullExpressionValue(format, "df.format(totalCaloriesPerHour)");
                    d2 = Double.parseDouble(format);
                    String format2 = jStyle2301AFormatter.c.format(d3 + Double.parseDouble(distance));
                    Intrinsics.checkNotNullExpressionValue(format2, "df.format(totalDistancePerHour)");
                    d3 = Double.parseDouble(format2);
                    int size2 = integerArrayListFromString.size();
                    int i4 = parseInt;
                    int i5 = 0;
                    while (i5 < size2) {
                        if (i4 < 60) {
                            int intValue = emptyHourCodedValuesList.get(i4).intValue();
                            i = size2;
                            Integer num = integerArrayListFromString.get(i5);
                            arrayList = arrayList3;
                            Intrinsics.checkNotNullExpressionValue(num, "stepValuesList[m]");
                            emptyHourCodedValuesList.set(i4, Integer.valueOf(intValue + num.intValue()));
                            i4++;
                        } else {
                            i = size2;
                            arrayList = arrayList3;
                        }
                        i5++;
                        size2 = i;
                        arrayList3 = arrayList;
                    }
                    i3++;
                    jStyle2301AFormatter = this;
                    it2 = it3;
                    i2 = 0;
                }
                it = it2;
                d = d3;
            }
            Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) next, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array2;
            String str = strArr[0];
            int parseInt2 = Integer.parseInt(strArr[1]);
            int i6 = parseInt2 + 1;
            if (i6 == 24) {
                i6 = 0;
            }
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(parseInt2)}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            sb.append(format3);
            sb.append(":00:00");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i6)}, 1));
            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
            sb3.append(format4);
            sb3.append(":00:00");
            String sb4 = sb3.toString();
            try {
                formattedDate = JStyleUtils.getFormattedDate(str);
            } catch (ParseException unused) {
                formattedDate = JStyleUtils.getFormattedDate(str, "yyyy-MM-dd");
            }
            StepsHourData stepsHourData = new StepsHourData();
            stepsHourData.setCaloriesPerHour(d2);
            stepsHourData.setDistancePerHour(d);
            stepsHourData.setStepsPerHour(CollectionsKt___CollectionsKt.sumOfInt(emptyHourCodedValuesList));
            stepsHourData.setDate(formattedDate);
            stepsHourData.setStartHour(sb2);
            stepsHourData.setEndHour(sb4);
            stepsHourData.setMinuteWiseData(emptyHourCodedValuesList);
            stepsHourData.setActivityType(ActivityType.WALK.toString());
            arrayList2.add(stepsHourData);
            jStyle2301AFormatter = this;
            it2 = it;
        }
        return arrayList2;
    }

    public final List<TemperatureHourData> e(Map<String, ? extends ArrayList<JstyleTemperatureData>> map) {
        String formattedDate;
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            ArrayList<JstyleTemperatureData> arrayList2 = map.get(str);
            ArrayList<Double> emptyHourCodedValuesListofDouble = JStyleUtils.getEmptyHourCodedValuesListofDouble();
            if (arrayList2 != null && arrayList2.size() > 0) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    String time = arrayList2.get(i).getTime();
                    Intrinsics.checkNotNull(time);
                    Object[] array = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    int parseInt = Integer.parseInt(((String[]) array)[1]);
                    Double temperatureValue = arrayList2.get(i).getTemperatureValue();
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    sb.append(temperatureValue);
                    sb.append(']');
                    ArrayList<Double> doubleArrayListFromString = JStyleUtils.getDoubleArrayListFromString(sb.toString());
                    int size2 = doubleArrayListFromString.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (parseInt < 60) {
                            if (doubleArrayListFromString.get(i2) == null) {
                                doubleArrayListFromString.set(i2, Double.valueOf(0.0d));
                            }
                            emptyHourCodedValuesListofDouble.set(parseInt, doubleArrayListFromString.get(i2));
                            parseInt++;
                        }
                    }
                }
            }
            Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array2;
            String str2 = strArr[0];
            int parseInt2 = Integer.parseInt(strArr[1]);
            int i3 = parseInt2 + 1;
            if (i3 == 24) {
                i3 = 0;
            }
            StringBuilder sb2 = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(parseInt2)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb2.append(format);
            sb2.append(":00:00");
            String sb3 = sb2.toString();
            StringBuilder sb4 = new StringBuilder();
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb4.append(format2);
            sb4.append(":00:00");
            String sb5 = sb4.toString();
            try {
                formattedDate = JStyleUtils.getFormattedDate(str2);
            } catch (ParseException unused) {
                formattedDate = JStyleUtils.getFormattedDate(str2, "yyyy-MM-dd");
            }
            Double minValueFromListofDouble = JStyleUtils.getMinValueFromListofDouble(emptyHourCodedValuesListofDouble);
            Double maxValueFromListofDouble = JStyleUtils.getMaxValueFromListofDouble(emptyHourCodedValuesListofDouble);
            Double avgValueFromListofDouble = JStyleUtils.getAvgValueFromListofDouble(emptyHourCodedValuesListofDouble);
            TemperatureHourData temperatureHourData = new TemperatureHourData();
            temperatureHourData.setDate(formattedDate);
            temperatureHourData.setStartHour(sb3);
            temperatureHourData.setEndHour(sb5);
            if (maxValueFromListofDouble != null) {
                temperatureHourData.maxTemperaturePerHour = maxValueFromListofDouble.doubleValue();
            }
            if (minValueFromListofDouble != null) {
                temperatureHourData.minTemperaturePerHour = minValueFromListofDouble.doubleValue();
            }
            if (avgValueFromListofDouble != null) {
                temperatureHourData.avgTemperaturePerHour = avgValueFromListofDouble.doubleValue();
            }
            temperatureHourData.setMinuteWiseData(emptyHourCodedValuesListofDouble);
            temperatureHourData.setActivityType("TEMPERATURE");
            arrayList.add(temperatureHourData);
        }
        return arrayList;
    }

    public final List<HRVHourData> f(Map<String, ? extends ArrayList<JstyleHrvData>> map) {
        String formattedDate;
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            ArrayList<JstyleHrvData> arrayList2 = map.get(str);
            ArrayList<Double> emptyHourCodedValuesListofDouble = JStyleUtils.getEmptyHourCodedValuesListofDouble();
            if (arrayList2 != null && arrayList2.size() > 0) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    JstyleHrvData jstyleHrvData = arrayList2.get(i);
                    Long dateTime = jstyleHrvData != null ? jstyleHrvData.getDateTime() : null;
                    Intrinsics.checkNotNull(dateTime);
                    long longValue = dateTime.longValue();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(longValue);
                    int i2 = calendar.get(12);
                    JstyleHrvData jstyleHrvData2 = arrayList2.get(i);
                    Integer hrv = jstyleHrvData2 != null ? jstyleHrvData2.getHrv() : null;
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    sb.append(hrv);
                    sb.append(']');
                    ArrayList<Double> doubleArrayListFromString = JStyleUtils.getDoubleArrayListFromString(sb.toString());
                    int size2 = doubleArrayListFromString.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        if (i2 < 60) {
                            if (doubleArrayListFromString.get(i3) == null) {
                                doubleArrayListFromString.set(i3, Double.valueOf(0.0d));
                            }
                            emptyHourCodedValuesListofDouble.set(i2, doubleArrayListFromString.get(i3));
                            i2++;
                        }
                    }
                }
            }
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            String str2 = strArr[0];
            int parseInt = Integer.parseInt(strArr[1]);
            int i4 = parseInt + 1;
            if (i4 == 24) {
                i4 = 0;
            }
            StringBuilder sb2 = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(parseInt)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb2.append(format);
            sb2.append(":00:00");
            String sb3 = sb2.toString();
            StringBuilder sb4 = new StringBuilder();
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb4.append(format2);
            sb4.append(":00:00");
            String sb5 = sb4.toString();
            try {
                formattedDate = JStyleUtils.getFormattedDate(str2);
            } catch (ParseException unused) {
                formattedDate = JStyleUtils.getFormattedDate(str2, "yyyy-MM-dd");
            }
            Double minValueFromListofDouble = JStyleUtils.getMinValueFromListofDouble(emptyHourCodedValuesListofDouble);
            Double maxValueFromListofDouble = JStyleUtils.getMaxValueFromListofDouble(emptyHourCodedValuesListofDouble);
            Double avgValueFromListofDouble = JStyleUtils.getAvgValueFromListofDouble(emptyHourCodedValuesListofDouble);
            HRVHourData hRVHourData = new HRVHourData();
            hRVHourData.setDate(formattedDate);
            hRVHourData.setStartHour(sb3);
            hRVHourData.setEndHour(sb5);
            if (maxValueFromListofDouble != null) {
                hRVHourData.setMaxHRV(maxValueFromListofDouble.doubleValue());
            }
            if (minValueFromListofDouble != null) {
                hRVHourData.setMinHRV(minValueFromListofDouble.doubleValue());
            }
            if (avgValueFromListofDouble != null) {
                hRVHourData.setAvgHRV(avgValueFromListofDouble.doubleValue());
            }
            hRVHourData.setMinuteWiseData(emptyHourCodedValuesListofDouble);
            hRVHourData.setActivityType(ActivityType.STRESS.toString());
            arrayList.add(hRVHourData);
        }
        return arrayList;
    }

    public final List<StressHourData> g(Map<String, ? extends ArrayList<JstyleHrvData>> map) {
        String formattedDate;
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            ArrayList<JstyleHrvData> arrayList2 = map.get(str);
            ArrayList<Integer> emptyHourCodedValuesList = JStyleUtils.getEmptyHourCodedValuesList();
            if (arrayList2 != null && arrayList2.size() > 0) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    JstyleHrvData jstyleHrvData = arrayList2.get(i);
                    Long dateTime = jstyleHrvData != null ? jstyleHrvData.getDateTime() : null;
                    Intrinsics.checkNotNull(dateTime);
                    long longValue = dateTime.longValue();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(longValue);
                    int i2 = calendar.get(12);
                    JstyleHrvData jstyleHrvData2 = arrayList2.get(i);
                    Integer stress = jstyleHrvData2 != null ? jstyleHrvData2.getStress() : null;
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    sb.append(stress);
                    sb.append(']');
                    ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(sb.toString());
                    int size2 = integerArrayListFromString.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        if (i2 < 60) {
                            if (integerArrayListFromString.get(i3) == null) {
                                integerArrayListFromString.set(i3, 0);
                            }
                            emptyHourCodedValuesList.set(i2, integerArrayListFromString.get(i3));
                            i2++;
                        }
                    }
                }
            }
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            String str2 = strArr[0];
            int parseInt = Integer.parseInt(strArr[1]);
            int i4 = parseInt + 1;
            if (i4 == 24) {
                i4 = 0;
            }
            StringBuilder sb2 = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(parseInt)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb2.append(format);
            sb2.append(":00:00");
            String sb3 = sb2.toString();
            StringBuilder sb4 = new StringBuilder();
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb4.append(format2);
            sb4.append(":00:00");
            String sb5 = sb4.toString();
            try {
                formattedDate = JStyleUtils.getFormattedDate(str2);
            } catch (ParseException unused) {
                formattedDate = JStyleUtils.getFormattedDate(str2, "yyyy-MM-dd");
            }
            Integer minValueFromList = JStyleUtils.getMinValueFromList(emptyHourCodedValuesList);
            Integer maxValueFromList = JStyleUtils.getMaxValueFromList(emptyHourCodedValuesList);
            Integer avgValueFromList = JStyleUtils.getAvgValueFromList(emptyHourCodedValuesList);
            StressHourData stressHourData = new StressHourData();
            stressHourData.setDate(formattedDate);
            stressHourData.setStartHour(sb3);
            stressHourData.setEndHour(sb5);
            if (maxValueFromList != null) {
                stressHourData.setMaxStress(maxValueFromList.intValue());
            }
            if (minValueFromList != null) {
                stressHourData.setMinStress(minValueFromList.intValue());
            }
            if (avgValueFromList != null) {
                stressHourData.setAvgStress(avgValueFromList.intValue());
            }
            stressHourData.setMinuteWiseData(emptyHourCodedValuesList);
            stressHourData.setActivityType(ActivityType.HEART_RATE.toString());
            arrayList.add(stressHourData);
        }
        return arrayList;
    }

    @Nullable
    public final List<HeartRateResponse> getHRNoDataList(@NotNull String date, @Nullable String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        HeartRateDayData heartRateDayData = new HeartRateDayData();
        heartRateDayData.setAvgHeartRate(0);
        heartRateDayData.setMaxHeartRate(0);
        heartRateDayData.setMinHeartRate(0);
        heartRateDayData.setmActivityType(ActivityType.HEART_RATE.toString());
        heartRateDayData.setmDate(date);
        heartRateDayData.setmMacAddress(str);
        HeartRateTimeLogBeanData heartRateTimeLogBeanData = new HeartRateTimeLogBeanData();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < 24; i++) {
            HeartRateHourData heartRateHourData = new HeartRateHourData();
            heartRateHourData.setMinHeartRatePerHour(0);
            heartRateHourData.setMaxHeartRatePerHour(0);
            heartRateHourData.setAvgHeartRatePerHour(0);
            heartRateHourData.setActivityType(ActivityType.HEART_RATE.toString());
            heartRateHourData.setDate(date);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(":00:00");
            heartRateHourData.setStartHour(sb.toString());
            if (i == 23) {
                heartRateHourData.setEndHour("00:00:00");
            } else {
                StringBuilder sb2 = new StringBuilder();
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i + 1)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb2.append(format2);
                sb2.append(":00:00");
                heartRateHourData.setEndHour(sb2.toString());
            }
            heartRateHourData.setMacAddress(str);
            heartRateHourData.setMinuteWiseData(JStyleUtils.getEmptyHourCodedValuesList());
            arrayList2.add(heartRateHourData);
        }
        HeartRateResponse heartRateResponse = new HeartRateResponse();
        heartRateTimeLogBeanData.setLogBeans(arrayList2);
        heartRateDayData.setTimeLogBean(heartRateTimeLogBeanData);
        heartRateResponse.setHeartRateData(heartRateDayData);
        arrayList.add(heartRateResponse);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x02dc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02da  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.coveiot.android.bleabstract.response.StressResponse> getHRVResponseFor1790And1939(@org.jetbrains.annotations.NotNull java.util.Map<java.lang.String, ? extends java.util.ArrayList<com.coveiot.android.jstyle2301a.model.JstyleHrvData>> r25) {
        /*
            Method dump skipped, instructions count: 802
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.formatter.JStyle2301AFormatter.getHRVResponseFor1790And1939(java.util.Map):java.util.List");
    }

    @Nullable
    public final List<HeartRateResponse> getHeartRateResponseFor1790And1939(@NotNull Map<String, ? extends ArrayList<JstyleHRData>> stepsArrayListMap) {
        TreeMap treeMap;
        Intrinsics.checkNotNullParameter(stepsArrayListMap, "stepsArrayListMap");
        List<HeartRateHourData> a2 = a(stepsArrayListMap);
        TreeMap treeMap2 = new TreeMap();
        ArrayList arrayList = (ArrayList) a2;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            String key = ((HeartRateHourData) arrayList.get(i)).getDate();
            if (treeMap2.containsKey(key)) {
                ArrayList arrayList2 = (ArrayList) treeMap2.get(key);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap2.put(key, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap2.put(key, arrayList3);
            }
        }
        Intrinsics.checkNotNull(treeMap2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, java.util.ArrayList<com.coveiot.android.bleabstract.response.HeartRateHourData>>");
        ArrayList arrayList4 = new ArrayList();
        for (String str : treeMap2.keySet()) {
            ArrayList arrayList5 = (ArrayList) treeMap2.get(str);
            HeartRateDayData heartRateDayData = new HeartRateDayData();
            heartRateDayData.setmActivityType(ActivityType.HEART_RATE.toString());
            heartRateDayData.setmDate(str);
            heartRateDayData.setmMacAddress(this.f3315a);
            HeartRateTimeLogBeanData heartRateTimeLogBeanData = new HeartRateTimeLogBeanData();
            ArrayList arrayList6 = new ArrayList();
            if (arrayList5 == null || arrayList5.size() <= 0) {
                treeMap = treeMap2;
            } else {
                int size2 = arrayList5.size();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (i2 < size2) {
                    Object obj = arrayList5.get(i2);
                    Intrinsics.checkNotNullExpressionValue(obj, "hrHourDataArrayList[i]");
                    HeartRateHourData heartRateHourData = (HeartRateHourData) obj;
                    HeartRateHourData heartRateHourData2 = new HeartRateHourData();
                    TreeMap treeMap3 = treeMap2;
                    heartRateHourData2.setMinHeartRatePerHour(heartRateHourData.getMinHeartRatePerHour());
                    heartRateHourData2.setMaxHeartRatePerHour(heartRateHourData.getMaxHeartRatePerHour());
                    heartRateHourData2.setAvgHeartRatePerHour(heartRateHourData.getAvgHeartRatePerHour());
                    heartRateHourData2.setActivityType(heartRateHourData.getmActivityType());
                    heartRateHourData2.setDate(heartRateHourData.getDate());
                    heartRateHourData2.setStartHour(heartRateHourData.getStartHour());
                    heartRateHourData2.setEndHour(heartRateHourData.getEndHour());
                    heartRateHourData2.setMacAddress(heartRateHourData.getmMacAddress());
                    heartRateHourData2.setMinuteWiseData(heartRateHourData.getmMinuteWiseData());
                    if (i5 == 0) {
                        i5 = heartRateHourData2.getMinHeartRatePerHour();
                    } else if (i5 > heartRateHourData2.getMinHeartRatePerHour() && heartRateHourData2.getMinHeartRatePerHour() != 0) {
                        i5 = heartRateHourData2.getMinHeartRatePerHour();
                    }
                    if (i4 == 0) {
                        i4 = heartRateHourData2.getMaxHeartRatePerHour();
                    } else if (i4 < heartRateHourData2.getMaxHeartRatePerHour()) {
                        i4 = heartRateHourData2.getMaxHeartRatePerHour();
                    }
                    int i7 = heartRateHourData.avgHeartRatePerHour;
                    i6 += i7;
                    if (i7 != 0) {
                        i3++;
                    }
                    arrayList6.add(heartRateHourData2);
                    i2++;
                    treeMap2 = treeMap3;
                }
                treeMap = treeMap2;
                heartRateDayData.setMaxHeartRate(i4);
                heartRateDayData.setMinHeartRate(i5);
                if (i3 > 0) {
                    heartRateDayData.setAvgHeartRate(i6 / i3);
                } else {
                    heartRateDayData.setAvgHeartRate(0);
                    HeartRateResponse heartRateResponse = new HeartRateResponse();
                    heartRateTimeLogBeanData.setLogBeans(arrayList6);
                    heartRateDayData.setTimeLogBean(heartRateTimeLogBeanData);
                    heartRateResponse.setHeartRateData(heartRateDayData);
                    arrayList4.add(heartRateResponse);
                    treeMap2 = treeMap;
                }
            }
            HeartRateResponse heartRateResponse2 = new HeartRateResponse();
            heartRateTimeLogBeanData.setLogBeans(arrayList6);
            heartRateDayData.setTimeLogBean(heartRateTimeLogBeanData);
            heartRateResponse2.setHeartRateData(heartRateDayData);
            arrayList4.add(heartRateResponse2);
            treeMap2 = treeMap;
        }
        return arrayList4;
    }

    @Nullable
    public final ArrayList<JstyleHrvData> getHrvNoDataList(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList<JstyleHrvData> arrayList = new ArrayList<>();
        JstyleHrvData jstyleHrvData = new JstyleHrvData();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(date));
        jstyleHrvData.setDateTime(Long.valueOf(calendar.getTimeInMillis()));
        jstyleHrvData.setSystolicbp(0);
        jstyleHrvData.setDiastolicbp(0);
        jstyleHrvData.setHr(0);
        arrayList.add(jstyleHrvData);
        return arrayList;
    }

    @Nullable
    public final ArrayList<ManualBpReading> getManualBpReadingList(@Nullable ArrayList<JstyleHrvData> arrayList) {
        ManualBpReading manualBpReading;
        if (arrayList != null) {
            ArrayList<ManualBpReading> arrayList2 = new ArrayList<>();
            Iterator<JstyleHrvData> it = arrayList.iterator();
            while (it.hasNext()) {
                JstyleHrvData next = it.next();
                if (next != null) {
                    Long dateTime = next.getDateTime();
                    Intrinsics.checkNotNull(dateTime);
                    long longValue = dateTime.longValue();
                    Integer systolicbp = next.getSystolicbp();
                    Intrinsics.checkNotNull(systolicbp);
                    int intValue = systolicbp.intValue();
                    Integer diastolicbp = next.getDiastolicbp();
                    Intrinsics.checkNotNull(diastolicbp);
                    int intValue2 = diastolicbp.intValue();
                    Integer hr = next.getHr();
                    Intrinsics.checkNotNull(hr);
                    manualBpReading = new ManualBpReading(longValue, intValue, intValue2, hr.intValue());
                } else {
                    manualBpReading = null;
                }
                arrayList2.add(manualBpReading);
            }
            return arrayList2;
        }
        return null;
    }

    @NotNull
    public final ArrayList<ManualHRVAndStressReading> getManualHrvStressReadingList(@Nullable ArrayList<JstyleHrvData> arrayList) {
        ArrayList<ManualHRVAndStressReading> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            arrayList2 = new ArrayList<>();
            Iterator<JstyleHrvData> it = arrayList.iterator();
            while (it.hasNext()) {
                JstyleHrvData next = it.next();
                if (next != null) {
                    ManualHRVAndStressReading manualHRVAndStressReading = new ManualHRVAndStressReading();
                    Integer stress = next.getStress();
                    Intrinsics.checkNotNull(stress);
                    manualHRVAndStressReading.setStress(stress.intValue());
                    Integer hrv = next.getHrv();
                    Intrinsics.checkNotNull(hrv);
                    manualHRVAndStressReading.setHrv(hrv.intValue());
                    Long dateTime = next.getDateTime();
                    Intrinsics.checkNotNull(dateTime);
                    manualHRVAndStressReading.setTimeStamp(dateTime.longValue());
                    Integer hr = next.getHr();
                    Intrinsics.checkNotNull(hr);
                    manualHRVAndStressReading.setHr(hr.intValue());
                    Integer vascularAging = next.getVascularAging();
                    Intrinsics.checkNotNull(vascularAging);
                    manualHRVAndStressReading.setVascularAging(vascularAging.intValue());
                    arrayList2.add(manualHRVAndStressReading);
                }
            }
        }
        return arrayList2;
    }

    @NotNull
    public final ArrayList<ManualSpo2Reading> getManualSpo2ReadingList(@Nullable ArrayList<JstyleSpo2Data> arrayList) {
        ArrayList<ManualSpo2Reading> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            arrayList2 = new ArrayList<>();
            Iterator<JstyleSpo2Data> it = arrayList.iterator();
            while (it.hasNext()) {
                JstyleSpo2Data next = it.next();
                if (next != null) {
                    ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
                    manualSpo2Reading.setLevelInterpretation(false);
                    Integer spo2 = next.getSpo2();
                    Intrinsics.checkNotNull(spo2);
                    manualSpo2Reading.setSpo2(spo2.intValue());
                    Long dateTime = next.getDateTime();
                    Intrinsics.checkNotNull(dateTime);
                    manualSpo2Reading.setTimeStamp(dateTime.longValue());
                    arrayList2.add(manualSpo2Reading);
                }
            }
        }
        return arrayList2;
    }

    @NotNull
    public final List<SleepResponse> getSleepNoDataList(@NotNull String date, @Nullable String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        SleepDayData sleepDayData = new SleepDayData();
        sleepDayData.setmDeepSleep(0);
        sleepDayData.setmLightSleep(0);
        sleepDayData.setmTotalSleep(0);
        sleepDayData.setmAwakeTime(0);
        sleepDayData.setmUnSleep(0);
        sleepDayData.setmActivityType(ActivityType.SLEEP.toString());
        sleepDayData.setmDate(date);
        sleepDayData.setmMacAddress(str);
        SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < 24; i++) {
            SleepHourData sleepHourData = new SleepHourData();
            sleepHourData.setTotalSleepPerHour(0);
            sleepHourData.setDeepSleepPerHour(0);
            sleepHourData.setLightSleepPerHour(0);
            sleepHourData.setAwakePerHour(0);
            sleepHourData.setActivityType(ActivityType.SLEEP.toString());
            sleepHourData.setDate(date);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(":00:00");
            sleepHourData.setStartHour(sb.toString());
            if (i == 23) {
                sleepHourData.setEndHour("00:00:00");
            } else {
                StringBuilder sb2 = new StringBuilder();
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i + 1)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb2.append(format2);
                sb2.append(":00:00");
                sleepHourData.setEndHour(sb2.toString());
            }
            sleepHourData.setMacAddress(str);
            sleepHourData.setMinuteWiseData(JStyleUtils.getEmptySleepHourCodedValuesList());
            arrayList2.add(sleepHourData);
        }
        SleepResponse sleepResponse = new SleepResponse();
        sleepTimeLogBeanData.setLogBeans(arrayList2);
        sleepDayData.setTimeLogBean(sleepTimeLogBeanData);
        sleepResponse.setSleepDayData(sleepDayData);
        arrayList.add(sleepResponse);
        return arrayList;
    }

    @Nullable
    public final List<SleepResponse> getSleepResponseFor1790And1939(@NotNull Map<String, ? extends ArrayList<JstyleSleepData>> sleepArrayListMap) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(sleepArrayListMap, "sleepArrayListMap");
        List<SleepHourData> b = b(sleepArrayListMap);
        TreeMap treeMap = new TreeMap();
        ArrayList arrayList = (ArrayList) b;
        int size = arrayList.size();
        for (int i6 = 0; i6 < size; i6++) {
            String key = ((SleepHourData) arrayList.get(i6)).getDate();
            if (treeMap.containsKey(key)) {
                ArrayList arrayList2 = (ArrayList) treeMap.get(key);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i6));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap.put(key, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i6));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap.put(key, arrayList3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (String str : treeMap.keySet()) {
            ArrayList arrayList5 = (ArrayList) treeMap.get(str);
            if (arrayList5 == null || arrayList5.size() <= 0) {
                i = 0;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
            } else {
                int size2 = arrayList5.size();
                i = 0;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                for (int i7 = 0; i7 < size2; i7++) {
                    i += ((SleepHourData) arrayList5.get(i7)).getTotalSleepPerHour();
                    i3 += ((SleepHourData) arrayList5.get(i7)).getDeepSleepPerHour();
                    i2 += ((SleepHourData) arrayList5.get(i7)).getLightSleepPerHour();
                    i4 += ((SleepHourData) arrayList5.get(i7)).getAwakePerHour();
                    i5 += ((SleepHourData) arrayList5.get(i7)).getUnSleepPerHour();
                }
            }
            SleepDayData sleepDayData = new SleepDayData();
            sleepDayData.setmDeepSleep(i3);
            sleepDayData.setmLightSleep(i2);
            sleepDayData.setmTotalSleep(i);
            sleepDayData.setmAwakeTime(i4);
            sleepDayData.setmUnSleep(i5);
            sleepDayData.setmActivityType(ActivityType.SLEEP.toString());
            sleepDayData.setmDate(str);
            sleepDayData.setmMacAddress(this.f3315a);
            SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
            ArrayList arrayList6 = new ArrayList();
            if (arrayList5 != null && arrayList5.size() > 0) {
                int size3 = arrayList5.size();
                for (int i8 = 0; i8 < size3; i8++) {
                    Object obj = arrayList5.get(i8);
                    Intrinsics.checkNotNullExpressionValue(obj, "sleepHourDataArrayList[i]");
                    SleepHourData sleepHourData = (SleepHourData) obj;
                    SleepHourData sleepHourData2 = new SleepHourData();
                    sleepHourData2.setTotalSleepPerHour(sleepHourData.getTotalSleepPerHour());
                    sleepHourData2.setDeepSleepPerHour(sleepHourData.getDeepSleepPerHour());
                    sleepHourData2.setLightSleepPerHour(sleepHourData.getLightSleepPerHour());
                    sleepHourData2.setAwakePerHour(sleepHourData.getAwakePerHour());
                    sleepHourData2.setActivityType(sleepHourData.getmActivityType());
                    sleepHourData2.setDate(sleepHourData.getDate());
                    sleepHourData2.setStartHour(sleepHourData.getStartHour());
                    sleepHourData2.setEndHour(sleepHourData.getEndHour());
                    sleepHourData2.setMacAddress(sleepHourData.getmMacAddress());
                    sleepHourData2.setMinuteWiseData(sleepHourData.getmMinuteWiseData());
                    arrayList6.add(sleepHourData2);
                }
            }
            SleepResponse sleepResponse = new SleepResponse();
            sleepTimeLogBeanData.setLogBeans(arrayList6);
            sleepDayData.setTimeLogBean(sleepTimeLogBeanData);
            sleepResponse.setSleepDayData(sleepDayData);
            arrayList4.add(sleepResponse);
        }
        return arrayList4;
    }

    @Nullable
    public final ArrayList<JstyleSpo2Data> getSpo2NoDataList(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList<JstyleSpo2Data> arrayList = new ArrayList<>();
        JstyleSpo2Data jstyleSpo2Data = new JstyleSpo2Data();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(date));
        jstyleSpo2Data.setDateTime(Long.valueOf(calendar.getTimeInMillis()));
        jstyleSpo2Data.setSpo2(0);
        arrayList.add(jstyleSpo2Data);
        return arrayList;
    }

    @Nullable
    public final List<PeriodicSpo2Response> getSpo2ResponseFor1790And1939(@NotNull Map<String, ? extends ArrayList<JstyleSpo2Data>> stepsArrayListMap) {
        TreeMap treeMap;
        Iterator it;
        Intrinsics.checkNotNullParameter(stepsArrayListMap, "stepsArrayListMap");
        List<Spo2HourData> c = c(stepsArrayListMap);
        TreeMap treeMap2 = new TreeMap();
        ArrayList arrayList = (ArrayList) c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            String key = ((Spo2HourData) arrayList.get(i)).getDate();
            if (treeMap2.containsKey(key)) {
                ArrayList arrayList2 = (ArrayList) treeMap2.get(key);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap2.put(key, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap2.put(key, arrayList3);
            }
        }
        Intrinsics.checkNotNull(treeMap2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, java.util.ArrayList<com.coveiot.android.bleabstract.response.Spo2HourData>>");
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = treeMap2.keySet().iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            ArrayList arrayList5 = (ArrayList) treeMap2.get(str);
            Spo2DayData spo2DayData = new Spo2DayData();
            spo2DayData.setmActivityType(ActivityType.HEART_RATE.toString());
            spo2DayData.setmDate(str);
            spo2DayData.setmMacAddress(this.f3315a);
            Spo2TimeLogBeanData spo2TimeLogBeanData = new Spo2TimeLogBeanData();
            ArrayList arrayList6 = new ArrayList();
            if (arrayList5 == null || arrayList5.size() <= 0) {
                treeMap = treeMap2;
                it = it2;
            } else {
                int size2 = arrayList5.size();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                double d = 0.0d;
                while (i2 < size2) {
                    Object obj = arrayList5.get(i2);
                    Intrinsics.checkNotNullExpressionValue(obj, "hrHourDataArrayList[i]");
                    Spo2HourData spo2HourData = (Spo2HourData) obj;
                    Spo2HourData spo2HourData2 = new Spo2HourData();
                    spo2HourData2.setMinSpo2PerHour(spo2HourData.getMinSpo2PerHour());
                    spo2HourData2.setMaxSpo2PerHour(spo2HourData.getMaxSpo2PerHour());
                    TreeMap treeMap3 = treeMap2;
                    Iterator it3 = it2;
                    spo2HourData2.setAvgSpo2PerHour(spo2HourData.getAvgSpo2PerHour());
                    spo2HourData2.setActivityType(spo2HourData.getmActivityType());
                    spo2HourData2.setDate(spo2HourData.getDate());
                    spo2HourData2.setStartHour(spo2HourData.getStartHour());
                    spo2HourData2.setEndHour(spo2HourData.getEndHour());
                    spo2HourData2.setMacAddress(spo2HourData.getmMacAddress());
                    spo2HourData2.setMinuteWiseData(spo2HourData.getmMinuteWiseData());
                    if (i5 == 0) {
                        i5 = spo2HourData2.getMinSpo2PerHour();
                    } else if (i5 > spo2HourData2.getMinSpo2PerHour() && spo2HourData2.getMinSpo2PerHour() != 0) {
                        i5 = spo2HourData2.getMinSpo2PerHour();
                    }
                    if (i4 == 0) {
                        i4 = spo2HourData2.getMaxSpo2PerHour();
                    } else if (i4 < spo2HourData2.getMaxSpo2PerHour()) {
                        i4 = spo2HourData2.getMaxSpo2PerHour();
                    }
                    double d2 = spo2HourData.avgSpo2PerHour;
                    d += d2;
                    if (!(d2 == 0.0d)) {
                        i3++;
                    }
                    arrayList6.add(spo2HourData2);
                    i2++;
                    treeMap2 = treeMap3;
                    it2 = it3;
                }
                treeMap = treeMap2;
                it = it2;
                spo2DayData.setMaxSPo2(i4);
                spo2DayData.setMinSpo2(i5);
                if (i3 > 0) {
                    spo2DayData.setAvgSpo2(d / i3);
                } else {
                    spo2DayData.setAvgSpo2(0.0d);
                }
            }
            PeriodicSpo2Response periodicSpo2Response = new PeriodicSpo2Response();
            spo2TimeLogBeanData.setLogBeans(arrayList6);
            spo2DayData.setTimeLogBean(spo2TimeLogBeanData);
            periodicSpo2Response.setSpo2DayData(spo2DayData);
            arrayList4.add(periodicSpo2Response);
            treeMap2 = treeMap;
            it2 = it;
        }
        return arrayList4;
    }

    @NotNull
    public final List<ActivityModeSummaryResponse> getSportModeHistoryData(@NotNull Object obj, @NotNull Context context) {
        Iterator it;
        String str;
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = ((List) obj).iterator();
        while (it2.hasNext()) {
            Map map = (Map) it2.next();
            ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
            String str2 = (String) map.get("date");
            String str3 = (String) map.get(DeviceKey.Step);
            String str4 = (String) map.get("distance");
            String str5 = (String) map.get("calories");
            String str6 = (String) map.get(DeviceKey.ActiveMinutes);
            String str7 = (String) map.get(DeviceKey.ActivityMode);
            String str8 = (String) map.get(DeviceKey.HeartRate);
            if (str2 != null) {
                it = it2;
                str = "yyyy-MM-dd";
                Object[] array = StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
                Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                String[] strArr = (String[]) array;
                String str9 = strArr[0];
                String str10 = strArr[1];
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(JStyleUtils.getDateFromString(str2, "yyyy.MM.dd HH:mm:ss"));
                calendar.set(13, 0);
                long timeInMillis = calendar.getTimeInMillis();
                if (str6 != null) {
                    calendar.add(13, Integer.parseInt(str6));
                }
                long timeInMillis2 = calendar.getTimeInMillis();
                try {
                    activityModeSummaryResponse.setDate(JStyleUtils.getFormattedDate(str9));
                } catch (ParseException unused) {
                    activityModeSummaryResponse.setDate(JStyleUtils.getFormattedDate(str9, str));
                }
                activityModeSummaryResponse.setStartDateTime(Long.valueOf(timeInMillis));
                activityModeSummaryResponse.setEndDateTime(Long.valueOf(timeInMillis2));
            } else {
                it = it2;
                str = "yyyy-MM-dd";
            }
            if (str3 != null) {
                activityModeSummaryResponse.setTotalSteps(Integer.parseInt(str3));
            }
            if (str4 != null) {
                activityModeSummaryResponse.setTotalDistance(Double.parseDouble(str4));
            }
            if (str5 != null) {
                activityModeSummaryResponse.setTotalCalories(Double.parseDouble(str5));
            }
            if (str6 != null) {
                activityModeSummaryResponse.setActivityDuration(Integer.parseInt(str6));
            }
            if (str7 != null) {
                activityModeSummaryResponse.setActivityMode(JStyleUtils.getActivityModeFor1860(Integer.parseInt(str7)));
            }
            if (str8 != null) {
                activityModeSummaryResponse.setHeartRate(Integer.parseInt(str8));
            }
            activityModeSummaryResponse.setMacAddress(this.f3315a);
            activityModeSummaryResponse.setSessionID(UUID.randomUUID().toString());
            KHJstyleHRRepository singletonHolder = KHJstyleHRRepository.Companion.getInstance(context);
            Long startDateTime = activityModeSummaryResponse.getStartDateTime();
            Intrinsics.checkNotNull(startDateTime);
            long longValue = startDateTime.longValue();
            Long endDateTime = activityModeSummaryResponse.getEndDateTime();
            Intrinsics.checkNotNull(endDateTime);
            long longValue2 = endDateTime.longValue();
            String macAddress = activityModeSummaryResponse.getMacAddress();
            Intrinsics.checkNotNull(macAddress);
            List<SessionHR> hRDataListBetweenTime = singletonHolder.getHRDataListBetweenTime(longValue, longValue2, macAddress);
            ArrayList arrayList2 = new ArrayList();
            Intrinsics.checkNotNull(hRDataListBetweenTime);
            int size = hRDataListBetweenTime.size();
            for (int i = 0; i < size; i++) {
                ActivityHeartRateSample activityHeartRateSample = new ActivityHeartRateSample();
                activityHeartRateSample.setHrTimeStamp(hRDataListBetweenTime.get(i).getHrTimeStamp());
                activityHeartRateSample.setHrValue(hRDataListBetweenTime.get(i).getHrValue());
                arrayList2.add(activityHeartRateSample);
            }
            activityModeSummaryResponse.setHeartRateSampleList(arrayList2);
            ArrayList arrayList3 = new ArrayList();
            List<ActivityHeartRateSample> heartRateSampleList = activityModeSummaryResponse.getHeartRateSampleList();
            Intrinsics.checkNotNull(heartRateSampleList);
            int size2 = heartRateSampleList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                List<ActivityHeartRateSample> heartRateSampleList2 = activityModeSummaryResponse.getHeartRateSampleList();
                Intrinsics.checkNotNull(heartRateSampleList2);
                arrayList3.add(Integer.valueOf(heartRateSampleList2.get(i2).getHrValue()));
            }
            if (arrayList3.size() > 0) {
                Integer minValueFromList = JStyleUtils.getMinValueFromList(arrayList3);
                Intrinsics.checkNotNull(minValueFromList);
                activityModeSummaryResponse.setMinHeartRate(minValueFromList.intValue());
            }
            if (arrayList3.size() > 0) {
                Integer maxValueFromList = JStyleUtils.getMaxValueFromList(arrayList3);
                Intrinsics.checkNotNull(maxValueFromList);
                activityModeSummaryResponse.setMaxHeartRate(maxValueFromList.intValue());
            }
            Calendar calendar2 = Calendar.getInstance();
            Long startDateTime2 = activityModeSummaryResponse.getStartDateTime();
            Intrinsics.checkNotNull(startDateTime2);
            calendar2.setTimeInMillis(startDateTime2.longValue());
            int i3 = calendar2.get(11);
            int i4 = calendar2.get(12);
            Calendar calendar3 = Calendar.getInstance();
            Long endDateTime2 = activityModeSummaryResponse.getEndDateTime();
            Intrinsics.checkNotNull(endDateTime2);
            calendar3.setTimeInMillis(endDateTime2.longValue());
            int i5 = calendar3.get(11);
            calendar3.get(12);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(calendar3.getTimeInMillis() - calendar2.getTimeInMillis());
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            sb.append(new SimpleDateFormat(str, locale).format(Long.valueOf(calendar2.getTimeInMillis())));
            sb.append(' ');
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(":00:00");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            ArrayList arrayList4 = arrayList;
            sb3.append(new SimpleDateFormat(str, locale).format(Long.valueOf(calendar3.getTimeInMillis())));
            sb3.append(' ');
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i5)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb3.append(format2);
            sb3.append(":00:00");
            String sb4 = sb3.toString();
            String macAddress2 = activityModeSummaryResponse.getMacAddress();
            Intrinsics.checkNotNull(macAddress2);
            List<KHJstyleHourlyWalkData> hourlyStepsValueBetween = KHJstyleWalkDBRepository.Companion.getInstance(context).getHourlyStepsValueBetween(sb2, sb4, macAddress2);
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            int size3 = hourlyStepsValueBetween.size();
            for (int i6 = 0; i6 < size3; i6++) {
                new ArrayList();
                ArrayList<Integer> codevalue = hourlyStepsValueBetween.get(i6).getCodevalue();
                Intrinsics.checkNotNullExpressionValue(codevalue, "walkDataList.get(i).codevalue");
                arrayList5.addAll(codevalue);
            }
            int i7 = ((int) minutes) + i4 + 1;
            if (arrayList5.size() > i7) {
                arrayList6.addAll(arrayList5.subList(i4, i7));
            }
            ArrayList arrayList7 = new ArrayList();
            int size4 = arrayList6.size();
            for (int i8 = 0; i8 < size4; i8++) {
                SessionSteps sessionSteps = new SessionSteps();
                Object obj2 = arrayList6.get(i8);
                Intrinsics.checkNotNullExpressionValue(obj2, "mWalkCodedValuesList.get(i)");
                sessionSteps.setStepsValue(((Number) obj2).intValue());
                if (i8 != 0) {
                    calendar2.add(12, 1);
                }
                sessionSteps.setStepsTimeStamp(calendar2.getTimeInMillis());
                arrayList7.add(sessionSteps);
            }
            ArrayList arrayList8 = new ArrayList();
            int size5 = arrayList7.size();
            for (int i9 = 0; i9 < size5; i9++) {
                ActivityStepsSample activityStepsSample = new ActivityStepsSample();
                activityStepsSample.setStepsTimeStamp(((SessionSteps) arrayList7.get(i9)).getStepsTimeStamp());
                activityStepsSample.setStepsValue(((SessionSteps) arrayList7.get(i9)).getStepsValue());
                arrayList8.add(activityStepsSample);
            }
            activityModeSummaryResponse.setStepsSampleList(arrayList8);
            KHJstyleGPSRepository singletonHolder2 = KHJstyleGPSRepository.Companion.getInstance(context);
            Long startDateTime3 = activityModeSummaryResponse.getStartDateTime();
            Intrinsics.checkNotNull(startDateTime3);
            long longValue3 = startDateTime3.longValue();
            Long endDateTime3 = activityModeSummaryResponse.getEndDateTime();
            Intrinsics.checkNotNull(endDateTime3);
            long longValue4 = endDateTime3.longValue();
            String macAddress3 = activityModeSummaryResponse.getMacAddress();
            Intrinsics.checkNotNull(macAddress3);
            List<KHSessionGPSData> gPSDataListBetweenTime = singletonHolder2.getGPSDataListBetweenTime(longValue3, longValue4, macAddress3);
            ArrayList arrayList9 = new ArrayList();
            Intrinsics.checkNotNull(gPSDataListBetweenTime);
            int size6 = gPSDataListBetweenTime.size();
            for (int i10 = 0; i10 < size6; i10++) {
                ActivityGPSSample activityGPSSample = new ActivityGPSSample();
                activityGPSSample.setGpsTimeStamp(gPSDataListBetweenTime.get(i10).getGpsTimeStamp());
                activityGPSSample.setLocation(new LatLng(gPSDataListBetweenTime.get(i10).getLatitudeValue(), gPSDataListBetweenTime.get(i10).getLongitudeValue()));
                arrayList9.add(activityGPSSample);
            }
            activityModeSummaryResponse.setGpsSampleList(arrayList9);
            if (gPSDataListBetweenTime.size() > 0) {
                activityModeSummaryResponse.setLowSamplingRate(1);
            }
            arrayList4.add(activityModeSummaryResponse);
            arrayList = arrayList4;
            it2 = it;
        }
        return arrayList;
    }

    @Nullable
    public final List<StepsResponse> getStepsNoDataList(@NotNull String date, @Nullable String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        StepsDayData stepsDayData = new StepsDayData();
        double d = 0.0d;
        stepsDayData.setmCalories(0.0d);
        stepsDayData.setmDistance(0.0d);
        stepsDayData.setmSteps(0);
        stepsDayData.setmActivityType(ActivityType.WALK.toString());
        stepsDayData.setmMacAddress(str);
        stepsDayData.setmDate(date);
        StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (i < 24) {
            StepsHourData stepsHourData = new StepsHourData();
            stepsHourData.setStepsPerHour(0);
            stepsHourData.setCaloriesPerHour(d);
            stepsHourData.setDistancePerHour(d);
            stepsHourData.setActivityType(ActivityType.WALK.toString());
            stepsHourData.setDate(date);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(":00:00");
            stepsHourData.setStartHour(sb.toString());
            if (i == 23) {
                stepsHourData.setEndHour("00:00:00");
            } else {
                StringBuilder sb2 = new StringBuilder();
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i + 1)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb2.append(format2);
                sb2.append(":00:00");
                stepsHourData.setEndHour(sb2.toString());
            }
            stepsHourData.setMacAddress(str);
            stepsHourData.setMinuteWiseData(JStyleUtils.getEmptyHourCodedValuesList());
            arrayList2.add(stepsHourData);
            i++;
            d = 0.0d;
        }
        StepsResponse stepsResponse = new StepsResponse();
        stepsTimeLogBeanData.setLogBeans(arrayList2);
        stepsDayData.setTimeLogBean(stepsTimeLogBeanData);
        stepsResponse.setStepsDayData(stepsDayData);
        arrayList.add(stepsResponse);
        return arrayList;
    }

    @Nullable
    public final List<StepsResponse> getStepsResponseFor1790And1939(@NotNull Map<String, ? extends ArrayList<JstyleStepData>> stepsArrayListMap) {
        double d;
        int i;
        Intrinsics.checkNotNullParameter(stepsArrayListMap, "stepsArrayListMap");
        List<StepsHourData> d2 = d(stepsArrayListMap);
        TreeMap treeMap = new TreeMap();
        ArrayList arrayList = (ArrayList) d2;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            String key = ((StepsHourData) arrayList.get(i2)).getDate();
            if (treeMap.containsKey(key)) {
                ArrayList arrayList2 = (ArrayList) treeMap.get(key);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i2));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap.put(key, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i2));
                Intrinsics.checkNotNullExpressionValue(key, "key");
                treeMap.put(key, arrayList3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (String str : treeMap.keySet()) {
            ArrayList arrayList5 = (ArrayList) treeMap.get(str);
            double d3 = 0.0d;
            if (arrayList5 == null || arrayList5.size() <= 0) {
                d = 0.0d;
                i = 0;
            } else {
                int size2 = arrayList5.size();
                d = 0.0d;
                i = 0;
                for (int i3 = 0; i3 < size2; i3++) {
                    i += ((StepsHourData) arrayList5.get(i3)).getStepsPerHour();
                    double caloriesPerHour = d + ((StepsHourData) arrayList5.get(i3)).getCaloriesPerHour();
                    double d4 = d3 + ((StepsHourData) arrayList5.get(i3)).getmistancePerHour();
                    this.c.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
                    String format = this.c.format(caloriesPerHour);
                    Intrinsics.checkNotNullExpressionValue(format, "df.format(totalCaloriesPerDay)");
                    d = Double.parseDouble(format);
                    String format2 = this.c.format(d4);
                    Intrinsics.checkNotNullExpressionValue(format2, "df.format(totalDistancePerDay)");
                    d3 = Double.parseDouble(format2);
                }
            }
            StepsDayData stepsDayData = new StepsDayData();
            stepsDayData.setmCalories(d);
            stepsDayData.setmDistance(d3);
            stepsDayData.setmSteps(i);
            stepsDayData.setmActivityType(ActivityType.WALK.toString());
            stepsDayData.setmDate(str);
            stepsDayData.setmMacAddress(this.f3315a);
            StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
            ArrayList arrayList6 = new ArrayList();
            if (arrayList5 != null && arrayList5.size() > 0) {
                int size3 = arrayList5.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    Object obj = arrayList5.get(i4);
                    Intrinsics.checkNotNullExpressionValue(obj, "stepsHourDataArrayList[i]");
                    StepsHourData stepsHourData = (StepsHourData) obj;
                    StepsHourData stepsHourData2 = new StepsHourData();
                    stepsHourData2.setStepsPerHour(stepsHourData.getStepsPerHour());
                    stepsHourData2.setCaloriesPerHour(stepsHourData.getCaloriesPerHour());
                    stepsHourData2.setDistancePerHour(stepsHourData.getmistancePerHour());
                    stepsHourData2.setActivityType(stepsHourData.getmActivityType());
                    stepsHourData2.setDate(stepsHourData.getDate());
                    stepsHourData2.setStartHour(stepsHourData.getStartHour());
                    stepsHourData2.setEndHour(stepsHourData.getEndHour());
                    stepsHourData2.setMacAddress(stepsHourData.getmMacAddress());
                    stepsHourData2.setMinuteWiseData(stepsHourData.getmMinuteWiseData());
                    arrayList6.add(stepsHourData2);
                }
            }
            StepsResponse stepsResponse = new StepsResponse();
            stepsTimeLogBeanData.setLogBeans(arrayList6);
            stepsDayData.setTimeLogBean(stepsTimeLogBeanData);
            stepsResponse.setStepsDayData(stepsDayData);
            arrayList4.add(stepsResponse);
        }
        return arrayList4;
    }

    @Nullable
    public final List<TemperatureResponse> getTemperatureNoDataList(@NotNull String date, @Nullable String str) {
        char c;
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        TemperatureDayData temperatureDayData = new TemperatureDayData();
        double d = 0.0d;
        temperatureDayData.setAvgTemperature(0.0d);
        temperatureDayData.setMaxTemperature(0.0d);
        temperatureDayData.setMinTemperature(0.0d);
        temperatureDayData.setmActivityType("TEMPERATURE");
        temperatureDayData.setmDate(date);
        temperatureDayData.setmMacAddress(str);
        TemperatureTimeLogBeanData temperatureTimeLogBeanData = new TemperatureTimeLogBeanData();
        ArrayList arrayList2 = new ArrayList();
        char c2 = 0;
        int i = 0;
        while (i < 24) {
            TemperatureHourData temperatureHourData = new TemperatureHourData();
            temperatureHourData.setMinTemperaturePerHour(d);
            temperatureHourData.setMaxTemperaturePerHour(d);
            temperatureHourData.setAvgTemperaturePerHour(d);
            temperatureHourData.setActivityType("TEMPERATURE");
            temperatureHourData.setDate(date);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[1];
            objArr[c2] = Integer.valueOf(i);
            String format = String.format(locale, "%02d", Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(":00:00");
            temperatureHourData.setStartHour(sb.toString());
            if (i == 23) {
                temperatureHourData.setEndHour("00:00:00");
                c = c2;
            } else {
                StringBuilder sb2 = new StringBuilder();
                c = 0;
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i + 1)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb2.append(format2);
                sb2.append(":00:00");
                temperatureHourData.setEndHour(sb2.toString());
            }
            temperatureHourData.setMacAddress(str);
            temperatureHourData.setMinuteWiseData(JStyleUtils.getEmptyHourCodedValuesListofDouble());
            arrayList2.add(temperatureHourData);
            i++;
            c2 = c;
            d = 0.0d;
        }
        TemperatureResponse temperatureResponse = new TemperatureResponse();
        temperatureTimeLogBeanData.setLogBeans(arrayList2);
        temperatureDayData.setTimeLogBean(temperatureTimeLogBeanData);
        temperatureResponse.setTemperatureData(temperatureDayData);
        arrayList.add(temperatureResponse);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x015a A[SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.coveiot.android.bleabstract.response.TemperatureResponse> getTemperatureResponseFor1860(@org.jetbrains.annotations.NotNull java.util.Map<java.lang.String, ? extends java.util.ArrayList<com.coveiot.android.jstyle2301a.model.JstyleTemperatureData>> r25) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.formatter.JStyle2301AFormatter.getTemperatureResponseFor1860(java.util.Map):java.util.List");
    }
}
