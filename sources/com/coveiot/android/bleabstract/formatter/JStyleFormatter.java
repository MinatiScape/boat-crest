package com.coveiot.android.bleabstract.formatter;

import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.jstylesdk.model.JstyleHRData;
import com.coveiot.android.jstylesdk.model.JstyleSleepData;
import com.coveiot.android.jstylesdk.model.JstyleStepData;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class JStyleFormatter {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3316a;
    public final String b = JStyleFormatter.class.getSimpleName();
    @NotNull
    public final DecimalFormat c = new DecimalFormat("0.00");

    public JStyleFormatter(@Nullable String str) {
        this.f3316a = str;
    }

    public final List<HeartRateHourData> a(Map<String, ? extends ArrayList<JstyleHRData>> map) {
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            ArrayList<JstyleHRData> arrayList2 = map.get(str);
            ArrayList<Integer> emptyHourCodedValuesList = JStyleUtils.getEmptyHourCodedValuesList();
            if (arrayList2 != null && arrayList2.size() > 0) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    String time = arrayList2.get(i).getTime();
                    Intrinsics.checkNotNull(time);
                    Object[] array = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    int parseInt = Integer.parseInt(((String[]) array)[1]);
                    Integer hrValue = arrayList2.get(i).getHrValue();
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
            String formattedDate = JStyleUtils.getFormattedDate(str2);
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
        Iterator<String> it;
        ArrayList arrayList = new ArrayList();
        Set<String> keySet = map.keySet();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it2 = keySet.iterator();
        while (true) {
            int i2 = 0;
            i = 1;
            if (!it2.hasNext()) {
                break;
            }
            String next = it2.next();
            ArrayList<JstyleSleepData> arrayList3 = map.get(next);
            List sortedWith = arrayList3 != null ? CollectionsKt___CollectionsKt.sortedWith(arrayList3, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.JStyleFormatter$convertRawResponseToSleepDayData$$inlined$compareBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return f.compareValues(((JstyleSleepData) t).getTime(), ((JstyleSleepData) t2).getTime());
                }
            }) : null;
            ArrayList<Integer> emptyDaySleepCodedValuesList = JStyleUtils.getEmptyDaySleepCodedValuesList();
            if (sortedWith != null && sortedWith.size() > 0) {
                int size = sortedWith.size();
                int i3 = 0;
                while (i3 < size) {
                    String time = ((JstyleSleepData) sortedWith.get(i3)).getTime();
                    Intrinsics.checkNotNull(time);
                    Object[] array = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[i2]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    String[] strArr = (String[]) array;
                    int parseInt = (Integer.parseInt(strArr[i2]) * 60) + Integer.parseInt(strArr[1]);
                    ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(String.valueOf(((JstyleSleepData) sortedWith.get(i3)).getDetailSleep()));
                    int size2 = integerArrayListFromString.size();
                    int i4 = i2;
                    while (i4 < size2) {
                        if (parseInt <= 1439) {
                            int i5 = 1440 - parseInt;
                            if (i5 >= 5) {
                                i5 = 5;
                            }
                            int i6 = i5 - 1;
                            if (i6 >= 0) {
                                it = it2;
                                while (true) {
                                    emptyDaySleepCodedValuesList.set(parseInt, integerArrayListFromString.get(i4));
                                    parseInt++;
                                    int i7 = i7 != i6 ? i7 + 1 : 0;
                                }
                                i4++;
                                it2 = it;
                            }
                        }
                        it = it2;
                        i4++;
                        it2 = it;
                    }
                    i3++;
                    i2 = 0;
                }
            }
            Iterator<String> it3 = it2;
            JstyleSleepData jstyleSleepData = new JstyleSleepData();
            jstyleSleepData.setDate(next);
            jstyleSleepData.setDetailSleep(emptyDaySleepCodedValuesList.toString());
            arrayList2.add(jstyleSleepData);
            it2 = it3;
        }
        LogHelper.d(this.b, "sleepDataArrayList" + arrayList2.toArray(), ModuleNames.BLEABSTRACT.getModuleName());
        int size3 = arrayList2.size();
        int i8 = 0;
        while (i8 < size3) {
            String detailSleep = ((JstyleSleepData) arrayList2.get(i8)).getDetailSleep();
            Intrinsics.checkNotNull(detailSleep);
            ArrayList<Integer> integerArrayListFromString2 = JStyleUtils.getIntegerArrayListFromString(new Regex("null").replace(detailSleep, BleConst.GetDeviceTime));
            int i9 = 0;
            while (i9 < 24) {
                int i10 = i9 + 1;
                int i11 = i10 == 24 ? 0 : i10;
                StringBuilder sb = new StringBuilder();
                Locale locale = Locale.ENGLISH;
                Object[] objArr = new Object[i];
                objArr[0] = Integer.valueOf(i9);
                String format = String.format(locale, "%02d", Arrays.copyOf(objArr, i));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(":00:00");
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                Object[] objArr2 = new Object[i];
                objArr2[0] = Integer.valueOf(i11);
                String format2 = String.format(locale, "%02d", Arrays.copyOf(objArr2, i));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb3.append(format2);
                sb3.append(":00:00");
                String sb4 = sb3.toString();
                ArrayList<Integer> arrayList4 = new ArrayList<>();
                int i12 = i9 * 60;
                arrayList4.addAll(integerArrayListFromString2.subList(i12, i12 + 60));
                int size4 = arrayList4.size();
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                while (i16 < size4) {
                    Integer num = arrayList4.get(i16);
                    int i18 = size3;
                    Intrinsics.checkNotNullExpressionValue(num, "hourCodedValuesList[k]");
                    int intValue = num.intValue();
                    if (intValue == -1) {
                        i15++;
                    } else {
                        if (intValue >= 0 && intValue < 15) {
                            i14++;
                        } else {
                            if (15 <= intValue && intValue < 201) {
                                i13++;
                            } else {
                                i17++;
                            }
                        }
                    }
                    i16++;
                    size3 = i18;
                }
                int i19 = size3;
                SleepHourData sleepHourData = new SleepHourData();
                String date = ((JstyleSleepData) arrayList2.get(i8)).getDate();
                Intrinsics.checkNotNull(date);
                sleepHourData.setDate(JStyleUtils.getFormattedDate(StringsKt__StringsKt.trim(date).toString()));
                sleepHourData.setStartHour(sb2);
                sleepHourData.setEndHour(sb4);
                sleepHourData.setMinuteWiseData(arrayList4);
                sleepHourData.setAwakePerHour(i17);
                sleepHourData.setLightSleepPerHour(i13);
                sleepHourData.setDeepSleepPerHour(i14);
                sleepHourData.setUnSleepPerHour(i15);
                sleepHourData.setTotalSleepPerHour(i13 + i14);
                arrayList.add(sleepHourData);
                size3 = i19;
                i9 = i10;
                i = 1;
            }
            i8++;
            i = 1;
        }
        return arrayList;
    }

    public final List<StepsHourData> c(Map<String, ? extends ArrayList<JstyleStepData>> map) {
        Iterator<String> it;
        double d;
        double d2;
        int i;
        ArrayList<JstyleStepData> arrayList;
        JStyleFormatter jStyleFormatter = this;
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
                    String time = arrayList3.get(i3).getTime();
                    Intrinsics.checkNotNull(time);
                    List split$default = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null);
                    Iterator<String> it3 = it2;
                    Object[] array = split$default.toArray(new String[i2]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    int parseInt = Integer.parseInt(((String[]) array)[1]);
                    ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(arrayList3.get(i3).getDetailStep());
                    String totalStep = arrayList3.get(i3).getTotalStep();
                    Intrinsics.checkNotNull(totalStep);
                    Integer.parseInt(totalStep);
                    String cal = arrayList3.get(i3).getCal();
                    Intrinsics.checkNotNull(cal);
                    double parseDouble = d2 + Double.parseDouble(cal);
                    String distance = arrayList3.get(i3).getDistance();
                    Intrinsics.checkNotNull(distance);
                    jStyleFormatter.c.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
                    String format = jStyleFormatter.c.format(parseDouble);
                    Intrinsics.checkNotNullExpressionValue(format, "df.format(totalCaloriesPerHour)");
                    d2 = Double.parseDouble(format);
                    String format2 = jStyleFormatter.c.format(d3 + Double.parseDouble(distance));
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
                    jStyleFormatter = this;
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
            String formattedDate = JStyleUtils.getFormattedDate(str);
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
            jStyleFormatter = this;
            it2 = it;
        }
        return arrayList2;
    }

    @NotNull
    public final LiveHealthData convertToLiveHealthData(@NotNull Map<String, String> realTimeData) {
        Intrinsics.checkNotNullParameter(realTimeData, "realTimeData");
        String str = realTimeData.get("heartValue");
        String str2 = this.b;
        LogHelper.d(str2, "LIVE DATA HEART RATE = " + str, ModuleNames.BLEABSTRACT.getModuleName());
        LiveHealthData liveHealthData = new LiveHealthData();
        liveHealthData.setHeartRate(str != null ? Integer.parseInt(str) : 0);
        return liveHealthData;
    }

    @NotNull
    public final LiveStepsData convertToLiveStepsData(@NotNull Map<String, String> realTimeData) {
        Intrinsics.checkNotNullParameter(realTimeData, "realTimeData");
        String str = realTimeData.get("totalSteps");
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

    /* JADX WARN: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x014e  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.android.bleabstract.response.ECGResultResponse getECGResultResponse(@org.jetbrains.annotations.NotNull java.util.Map<java.lang.String, java.lang.String> r11, @org.jetbrains.annotations.NotNull java.util.List<java.lang.Integer> r12, @org.jetbrains.annotations.NotNull java.util.List<java.lang.Float> r13) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.formatter.JStyleFormatter.getECGResultResponse(java.util.Map, java.util.List, java.util.List):com.coveiot.android.bleabstract.response.ECGResultResponse");
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
            heartRateDayData.setmMacAddress(this.f3316a);
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
            sleepDayData.setmMacAddress(this.f3316a);
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

    @NotNull
    public final List<ActivityModeSummaryResponse> getSportModeHistoryData(@NotNull Object obj) {
        Iterator it;
        String str;
        int i;
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = ((List) obj).iterator();
        while (it2.hasNext()) {
            Map map = (Map) it2.next();
            ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
            String str2 = (String) map.get("historyDate");
            String str3 = (String) map.get("totalSteps");
            String str4 = (String) map.get("distance");
            String str5 = (String) map.get("calories");
            String str6 = (String) map.get(DeviceKey.ActiveMinutes);
            String str7 = (String) map.get(DeviceKey.ActivityMode);
            String str8 = (String) map.get("heartValue");
            String str9 = (String) map.get("sportModelSpeed");
            if (str2 != null) {
                it = it2;
                str = "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>";
                i = 0;
                Object[] array = StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
                Intrinsics.checkNotNull(array, str);
                String[] strArr = (String[]) array;
                String str10 = strArr[0];
                String str11 = strArr[1];
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(JStyleUtils.getDateFromString(str2, "yy.MM.dd HH:mm:ss"));
                long timeInMillis = calendar.getTimeInMillis();
                if (str6 != null) {
                    calendar.add(13, Integer.parseInt(str6));
                }
                long timeInMillis2 = calendar.getTimeInMillis();
                activityModeSummaryResponse.setDate(JStyleUtils.getFormattedDate(str10));
                activityModeSummaryResponse.setStartDateTime(Long.valueOf(timeInMillis));
                activityModeSummaryResponse.setEndDateTime(Long.valueOf(timeInMillis2));
            } else {
                it = it2;
                str = "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>";
                i = 0;
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
                activityModeSummaryResponse.setActivityMode(JStyleUtils.getActivityModeFor1790(Integer.parseInt(str7)));
            }
            if (str8 != null) {
                activityModeSummaryResponse.setHeartRate(Integer.parseInt(str8));
            }
            if (str9 != null) {
                int i2 = i;
                Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) str9, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[i2]);
                Intrinsics.checkNotNull(array2, str);
                String[] strArr2 = (String[]) array2;
                String str12 = strArr2[i2];
                String substring = strArr2[1].substring(i2, strArr2[1].length() - 1);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                activityModeSummaryResponse.setPaceInSeconds((Integer.parseInt(str12) * 60) + Integer.parseInt(substring));
            }
            activityModeSummaryResponse.setMacAddress(this.f3316a);
            activityModeSummaryResponse.setSessionID(UUID.randomUUID().toString());
            arrayList.add(activityModeSummaryResponse);
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
        List<StepsHourData> c = c(stepsArrayListMap);
        TreeMap treeMap = new TreeMap();
        ArrayList arrayList = (ArrayList) c;
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
            double d2 = 0.0d;
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
                    double d3 = d2 + ((StepsHourData) arrayList5.get(i3)).getmistancePerHour();
                    this.c.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
                    String format = this.c.format(caloriesPerHour);
                    Intrinsics.checkNotNullExpressionValue(format, "df.format(totalCaloriesPerDay)");
                    d = Double.parseDouble(format);
                    String format2 = this.c.format(d3);
                    Intrinsics.checkNotNullExpressionValue(format2, "df.format(totalDistancePerDay)");
                    d2 = Double.parseDouble(format2);
                }
            }
            StepsDayData stepsDayData = new StepsDayData();
            stepsDayData.setmCalories(d);
            stepsDayData.setmDistance(d2);
            stepsDayData.setmSteps(i);
            stepsDayData.setmActivityType(ActivityType.WALK.toString());
            stepsDayData.setmDate(str);
            stepsDayData.setmMacAddress(this.f3316a);
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
}
