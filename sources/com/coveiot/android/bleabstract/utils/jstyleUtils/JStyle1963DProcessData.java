package com.coveiot.android.bleabstract.utils.jstyleUtils;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.jstyle1963dsdk.model.JstyleDynamicHRData;
import com.coveiot.android.jstyle1963dsdk.model.JstyleHRData;
import com.coveiot.android.jstyle1963dsdk.model.JstyleHrvData;
import com.coveiot.android.jstyle1963dsdk.model.JstyleSleepData;
import com.coveiot.android.jstyle1963dsdk.model.JstyleSpo2Data;
import com.coveiot.android.jstyle1963dsdk.model.JstyleStepData;
import com.coveiot.android.jstyle1963dsdk.model.JstyleTemperatureData;
import com.coveiot.khjstyledb.heartrate.KHJstyleEntitySessionHeartRateData;
import com.coveiot.khjstyledb.walk.KHJstyleHourlyWalkData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class JStyle1963DProcessData {

    /* renamed from: a  reason: collision with root package name */
    public static String f4060a = "JStyle1963DProcessData";
    @NotNull
    public static final JStyle1963DProcessData INSTANCE = new JStyle1963DProcessData();
    @NotNull
    public static final SimpleDateFormat b = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.ENGLISH);

    public static final ArrayList access$getEntityHeartRateSessionData(JStyle1963DProcessData jStyle1963DProcessData, ArrayList arrayList, String str) {
        jStyle1963DProcessData.getClass();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            JstyleDynamicHRData jstyleDynamicHRData = (JstyleDynamicHRData) it.next();
            KHJstyleEntitySessionHeartRateData kHJstyleEntitySessionHeartRateData = new KHJstyleEntitySessionHeartRateData();
            kHJstyleEntitySessionHeartRateData.serialNo = str;
            kHJstyleEntitySessionHeartRateData.setHeartRate(jstyleDynamicHRData.getHrValue());
            kHJstyleEntitySessionHeartRateData.setTimeStamp(jstyleDynamicHRData.getTimeStamp());
            arrayList2.add(kHJstyleEntitySessionHeartRateData);
        }
        return arrayList2;
    }

    @JvmStatic
    public static final void insertHourlyStepsData(@NotNull Context context, @NotNull StepsDayData stepsDayData) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stepsDayData, "stepsDayData");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new JStyle1963DProcessData$insertHourlyStepsData$1(stepsDayData, context, null), 2, null);
    }

    @JvmStatic
    public static final void insertSessionHRData(@NotNull Context context, @NotNull ArrayList<JstyleDynamicHRData> hrDataList, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hrDataList, "hrDataList");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new JStyle1963DProcessData$insertSessionHRData$1(hrDataList, serialNo, context, null), 2, null);
    }

    @JvmStatic
    @Nullable
    public static final List<JstyleDynamicHRData> processDynamicHRData(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList arrayList = new ArrayList();
        for (Map map : (List) obj) {
            String str = (String) map.get("date");
            String str2 = (String) map.get(DeviceKey.ArrayDynamicHR);
            if (str2 == null) {
                str2 = "[0]";
            }
            String replace$default = m.replace$default(StringsKt__StringsKt.trim(str2).toString(), HexStringBuilder.DEFAULT_SEPARATOR, Constants.SEPARATOR_COMMA, false, 4, (Object) null);
            if (!m.startsWith$default(replace$default, "[", false, 2, null)) {
                replace$default = '[' + replace$default + ']';
            }
            ArrayList<Integer> integerArrayListFromString = JStyleUtils.getIntegerArrayListFromString(replace$default);
            int size = integerArrayListFromString.size();
            for (int i = 0; i < size; i++) {
                JstyleDynamicHRData jstyleDynamicHRData = new JstyleDynamicHRData();
                Intrinsics.checkNotNull(str);
                Calendar addMinutesToDate = JStyleUtils.addMinutesToDate(str, i, b);
                addMinutesToDate.set(13, 0);
                jstyleDynamicHRData.setTimeStamp(addMinutesToDate.getTimeInMillis());
                jstyleDynamicHRData.setHrValue(integerArrayListFromString.get(i).intValue());
                if (jstyleDynamicHRData.getHrValue() != 0) {
                    arrayList.add(jstyleDynamicHRData);
                }
            }
        }
        return arrayList;
    }

    @JvmStatic
    @NotNull
    public static final Map<String, ArrayList<JstyleSleepData>> processSleepData(@NotNull Object obj, int i) {
        int i2;
        String str;
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList arrayList = new ArrayList();
        List<? extends Map<String, String>> list = (List) obj;
        Collections.reverse(list);
        Iterator<Map<String, String>> it = INSTANCE.a(list, i).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map<String, String> next = it.next();
            JstyleSleepData jstyleSleepData = new JstyleSleepData();
            String str2 = next.get("date");
            String str3 = next.get(DeviceKey.ArraySleep);
            if (str3 != null) {
                str = m.replace$default(StringsKt__StringsKt.trim(str3).toString(), HexStringBuilder.DEFAULT_SEPARATOR, Constants.SEPARATOR_COMMA, false, 4, (Object) null);
                if (!m.startsWith$default(str, "[", false, 2, null)) {
                    str = '[' + str + ']';
                }
            } else {
                str = "[-1]";
            }
            Intrinsics.checkNotNull(str2);
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            String str4 = strArr[0];
            String str5 = strArr[1];
            jstyleSleepData.setDate(str4);
            jstyleSleepData.setTime(str5);
            jstyleSleepData.setDetailSleep(str);
            arrayList.add(jstyleSleepData);
        }
        TreeMap treeMap = new TreeMap();
        int size = arrayList.size();
        for (i2 = 0; i2 < size; i2++) {
            String str6 = ((JstyleSleepData) arrayList.get(i2)).getDate() + ' ';
            if (treeMap.containsKey(str6)) {
                ArrayList arrayList2 = (ArrayList) treeMap.get(str6);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i2));
                treeMap.put(str6, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i2));
                treeMap.put(str6, arrayList3);
            }
        }
        return treeMap;
    }

    @JvmStatic
    @Nullable
    public static final ArrayList<JstyleSpo2Data> processSpo2Data(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList<JstyleSpo2Data> arrayList = new ArrayList<>();
        for (Map map : (List) obj) {
            JstyleSpo2Data jstyleSpo2Data = new JstyleSpo2Data();
            String str = (String) map.get("date");
            String str2 = (String) map.get(DeviceKey.Blood_oxygen);
            if (str2 == null) {
                str2 = BleConst.GetDeviceTime;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(b.parse(str));
            long timeInMillis = calendar.getTimeInMillis();
            jstyleSpo2Data.setSpo2(Integer.valueOf(Integer.parseInt(str2)));
            jstyleSpo2Data.setDateTime(Long.valueOf(timeInMillis));
            arrayList.add(jstyleSpo2Data);
        }
        return arrayList;
    }

    @JvmStatic
    @NotNull
    public static final Map<String, ArrayList<JstyleHRData>> processStaticHRData(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList arrayList = new ArrayList();
        for (Map map : (List) obj) {
            JstyleHRData jstyleHRData = new JstyleHRData();
            String str = (String) map.get("date");
            String str2 = (String) map.get(DeviceKey.StaticHR);
            if (str2 == null) {
                str2 = BleConst.GetDeviceTime;
            }
            Intrinsics.checkNotNull(str);
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            String str3 = strArr[0];
            String str4 = strArr[1];
            jstyleHRData.setDate(str3);
            jstyleHRData.setTime(str4);
            jstyleHRData.setHrValue(Integer.valueOf(Integer.parseInt(str2)));
            arrayList.add(jstyleHRData);
        }
        TreeMap treeMap = new TreeMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(((JstyleHRData) arrayList.get(i)).getDate());
            sb.append(' ');
            String time = ((JstyleHRData) arrayList.get(i)).getTime();
            Intrinsics.checkNotNull(time);
            Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            sb.append(((String[]) array2)[0]);
            String sb2 = sb.toString();
            if (treeMap.containsKey(sb2)) {
                ArrayList arrayList2 = (ArrayList) treeMap.get(sb2);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i));
                treeMap.put(sb2, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i));
                treeMap.put(sb2, arrayList3);
            }
        }
        return treeMap;
    }

    @JvmStatic
    @Nullable
    public static final ArrayList<JstyleHrvData> processStaticHrvData(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList<JstyleHrvData> arrayList = new ArrayList<>();
        for (Map map : (List) obj) {
            JstyleHrvData jstyleHrvData = new JstyleHrvData();
            String str = (String) map.get("date");
            String str2 = (String) map.get(DeviceKey.lowBP);
            String str3 = (String) map.get(DeviceKey.highBP);
            String str4 = (String) map.get(DeviceKey.HeartRate);
            if (str2 == null) {
                str2 = BleConst.GetDeviceTime;
            }
            if (str3 == null) {
                str3 = BleConst.GetDeviceTime;
            }
            if (str4 == null) {
                str4 = BleConst.GetDeviceTime;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(b.parse(str));
            jstyleHrvData.setDateTime(Long.valueOf(calendar.getTimeInMillis()));
            jstyleHrvData.setSystolicbp(Integer.valueOf(Integer.parseInt(str2)));
            jstyleHrvData.setDiastolicbp(Integer.valueOf(Integer.parseInt(str3)));
            jstyleHrvData.setHr(Integer.valueOf(Integer.parseInt(str4)));
            arrayList.add(jstyleHrvData);
        }
        return arrayList;
    }

    @JvmStatic
    @NotNull
    public static final Map<String, ArrayList<JstyleStepData>> processStepData(@NotNull Object obj) {
        String str;
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList arrayList = new ArrayList();
        Iterator<Map<String, String>> it = INSTANCE.b((List) obj, 1).iterator();
        while (it.hasNext()) {
            Map<String, String> next = it.next();
            JstyleStepData jstyleStepData = new JstyleStepData();
            String str2 = next.get("date");
            String str3 = next.get(DeviceKey.KDetailMinterStep);
            String str4 = next.get("distance");
            String str5 = next.get("calories");
            String str6 = next.get(DeviceKey.ArraySteps);
            if (str6 != null) {
                str = m.replace$default(StringsKt__StringsKt.trim(str6).toString(), HexStringBuilder.DEFAULT_SEPARATOR, Constants.SEPARATOR_COMMA, false, 4, (Object) null);
                if (!m.startsWith$default(str, "[", false, 2, null)) {
                    str = '[' + str + ']';
                }
            } else {
                str = "[0]";
            }
            Intrinsics.checkNotNull(str2);
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            String str7 = strArr[0];
            String str8 = strArr[1];
            jstyleStepData.setDate(str7);
            jstyleStepData.setTime(str8);
            jstyleStepData.setTotalStep(str3);
            jstyleStepData.setDistance(str4);
            jstyleStepData.setCal(str5);
            jstyleStepData.setDetailStep(str);
            arrayList.add(jstyleStepData);
        }
        TreeMap treeMap = new TreeMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(((JstyleStepData) arrayList.get(i)).getDate());
            sb.append(' ');
            String time = ((JstyleStepData) arrayList.get(i)).getTime();
            Intrinsics.checkNotNull(time);
            Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            sb.append(((String[]) array2)[0]);
            String sb2 = sb.toString();
            if (treeMap.containsKey(sb2)) {
                ArrayList arrayList2 = (ArrayList) treeMap.get(sb2);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i));
                treeMap.put(sb2, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i));
                treeMap.put(sb2, arrayList3);
            }
        }
        return treeMap;
    }

    @JvmStatic
    @NotNull
    public static final Map<String, ArrayList<JstyleTemperatureData>> processTemperatureData(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        ArrayList arrayList = new ArrayList();
        for (Map map : (List) obj) {
            JstyleTemperatureData jstyleTemperatureData = new JstyleTemperatureData();
            String str = (String) map.get("date");
            String str2 = (String) map.get(DeviceKey.TempData);
            if (str2 == null) {
                str2 = IdManager.DEFAULT_VERSION_NAME;
            }
            Intrinsics.checkNotNull(str);
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            String str3 = strArr[0];
            String str4 = strArr[1];
            jstyleTemperatureData.setDate(str3);
            jstyleTemperatureData.setTime(str4);
            jstyleTemperatureData.setTemperatureValue(Double.valueOf(Double.parseDouble(str2)));
            arrayList.add(jstyleTemperatureData);
        }
        TreeMap treeMap = new TreeMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(((JstyleTemperatureData) arrayList.get(i)).getDate());
            sb.append(' ');
            String time = ((JstyleTemperatureData) arrayList.get(i)).getTime();
            Intrinsics.checkNotNull(time);
            Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
            Intrinsics.checkNotNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            sb.append(((String[]) array2)[0]);
            String sb2 = sb.toString();
            if (treeMap.containsKey(sb2)) {
                ArrayList arrayList2 = (ArrayList) treeMap.get(sb2);
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(arrayList.get(i));
                treeMap.put(sb2, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(arrayList.get(i));
                treeMap.put(sb2, arrayList3);
            }
        }
        return treeMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ArrayList<Map<String, String>> a(List<? extends Map<String, String>> list, int i) {
        Iterator it;
        String str;
        boolean z;
        boolean z2;
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<kotlin.collections.Map<kotlin.String, kotlin.String>>");
        ArrayList arrayList2 = (ArrayList) list;
        System.out.println(Arrays.toString(arrayList2.toArray()));
        if (arrayList2.size() > 0) {
            int size = arrayList2.size();
            int i2 = 0;
            while (i2 < size) {
                arrayList.add(arrayList2.get(i2));
                int i3 = i2 + 1;
                if (i3 < arrayList2.size()) {
                    ArrayList<Integer> convertSleepDetailsStringToArrayList = JStyleUtils.convertSleepDetailsStringToArrayList(String.valueOf(((Map) arrayList2.get(i2)).get(DeviceKey.ArraySleep)));
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = b;
                    calendar.setTime(simpleDateFormat.parse((String) ((Map) arrayList2.get(i3)).get("date")));
                    Object obj = ((Map) arrayList2.get(i2)).get("date");
                    Intrinsics.checkNotNull(obj);
                    Calendar addMinutesToDate = JStyleUtils.addMinutesToDate((String) obj, convertSleepDetailsStringToArrayList.size() * 5, simpleDateFormat);
                    if (calendar.getTimeInMillis() < addMinutesToDate.getTimeInMillis()) {
                        HashMap hashMap = new HashMap();
                        String format = simpleDateFormat.format(addMinutesToDate.getTime());
                        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(expectedTime.time)");
                        hashMap.put("date", format);
                        hashMap.put(DeviceKey.ArraySleep, String.valueOf(((Map) arrayList2.get(i3)).get(DeviceKey.ArraySleep)));
                        arrayList2.set(i3, hashMap);
                    }
                }
                i2 = i3;
            }
        }
        Iterator it2 = arrayList2.iterator();
        String str2 = BleConst.GetDeviceTime;
        while (it2.hasNext()) {
            Map map = (Map) it2.next();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            String str3 = (String) map.get("date");
            String str4 = (String) map.get(DeviceKey.ArraySleep);
            if (str3 != null && str4 != null) {
                SimpleDateFormat simpleDateFormat2 = b;
                if (!JStyleUtils.isEndTimeInSameDay(str3, str4, i, simpleDateFormat2)) {
                    Calendar timeToMidNight = JStyleUtils.setTimeToMidNight(str3, simpleDateFormat2);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(simpleDateFormat2.parse(str3));
                    String format2 = simpleDateFormat2.format(timeToMidNight.getTime());
                    Intrinsics.checkNotNullExpressionValue(format2, "simpleDateFormat.format(date)");
                    Intrinsics.checkNotNullExpressionValue(calendar2, "calendar");
                    int minutesDifference = JStyleUtils.getMinutesDifference(calendar2, timeToMidNight);
                    ArrayList<Integer> convertSleepDetailsStringToArrayList2 = JStyleUtils.convertSleepDetailsStringToArrayList(str4);
                    int i4 = minutesDifference / i;
                    int i5 = minutesDifference % i;
                    if (i5 == 0) {
                        if (convertSleepDetailsStringToArrayList2.size() > i4) {
                            List<Integer> subList = convertSleepDetailsStringToArrayList2.subList(i4, convertSleepDetailsStringToArrayList2.size());
                            Intrinsics.checkNotNullExpressionValue(subList, "sleepValuesList.subList(…                        )");
                            if (subList.size() > 1) {
                                str2 = JStyleUtils.getStringFromIntegerArrayList(subList);
                                Intrinsics.checkNotNull(str2);
                                z = false;
                            } else {
                                z = false;
                                str2 = String.valueOf(subList.get(0).intValue());
                            }
                            if (!m.startsWith$default(str2, "[", z, 2, null)) {
                                str2 = '[' + str2 + ']';
                            }
                        }
                        hashMap2.put("date", format2);
                        hashMap2.put(DeviceKey.ArraySleep, str2);
                        arrayList.add(hashMap2);
                    } else {
                        String valueOf = String.valueOf(convertSleepDetailsStringToArrayList2.get(i4).intValue());
                        it = it2;
                        str = str2;
                        if (!m.startsWith$default(valueOf, "[", false, 2, null)) {
                            valueOf = '[' + valueOf + ']';
                        }
                        hashMap3.put("date", format2);
                        hashMap3.put(DeviceKey.ArraySleep, valueOf);
                        arrayList.add(hashMap3);
                        String format3 = simpleDateFormat2.format(JStyleUtils.addMinutesToDate(format2, i - i5, simpleDateFormat2).getTime());
                        Intrinsics.checkNotNullExpressionValue(format3, "simpleDateFormat.format(date2)");
                        int i6 = i4 + 1;
                        if (convertSleepDetailsStringToArrayList2.size() > i6) {
                            List<Integer> subList2 = convertSleepDetailsStringToArrayList2.subList(i6, convertSleepDetailsStringToArrayList2.size());
                            Intrinsics.checkNotNullExpressionValue(subList2, "sleepValuesList.subList(…                        )");
                            if (subList2.size() > 1) {
                                str2 = JStyleUtils.getStringFromIntegerArrayList(subList2);
                                Intrinsics.checkNotNull(str2);
                                z2 = false;
                            } else {
                                z2 = false;
                                str2 = String.valueOf(subList2.get(0).intValue());
                            }
                            if (!m.startsWith$default(str2, "[", z2, 2, null)) {
                                str2 = '[' + str2 + ']';
                            }
                            hashMap2.put("date", format3);
                            hashMap2.put(DeviceKey.ArraySleep, str2);
                            arrayList.add(hashMap2);
                            it2 = it;
                        } else {
                            it2 = it;
                            str2 = str;
                        }
                    }
                }
            }
            it = it2;
            str = str2;
            it2 = it;
            str2 = str;
        }
        return arrayList;
    }

    public final ArrayList<Map<String, String>> b(List<? extends Map<String, String>> list, int i) {
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        arrayList.addAll(list);
        String str = BleConst.GetDeviceTime;
        for (Map<String, String> map : list) {
            HashMap hashMap = new HashMap();
            String str2 = map.get("date");
            String str3 = map.get(DeviceKey.ArraySteps);
            if (str2 != null && str3 != null) {
                SimpleDateFormat simpleDateFormat = b;
                if (!JStyleUtils.isEndTimeInSameHour(str2, str3, i, simpleDateFormat)) {
                    Calendar timeToNextHour = JStyleUtils.setTimeToNextHour(str2, simpleDateFormat);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(simpleDateFormat.parse(str2));
                    String format = simpleDateFormat.format(timeToNextHour.getTime());
                    Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(date)");
                    Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
                    int minutesDifference = JStyleUtils.getMinutesDifference(calendar, timeToNextHour);
                    ArrayList<Integer> convertStepsDetailsStringToArrayList = JStyleUtils.convertStepsDetailsStringToArrayList(str3);
                    int i2 = minutesDifference / i;
                    if (convertStepsDetailsStringToArrayList.size() > i2) {
                        List<Integer> subList = convertStepsDetailsStringToArrayList.subList(i2, convertStepsDetailsStringToArrayList.size());
                        Intrinsics.checkNotNullExpressionValue(subList, "stepValuesList.subList(\n…                        )");
                        if (subList.size() > 1) {
                            str = JStyleUtils.getStringFromIntegerArrayList(subList);
                            Intrinsics.checkNotNull(str);
                        } else {
                            str = String.valueOf(subList.get(0).intValue());
                        }
                        if (!m.startsWith$default(str, "[", false, 2, null)) {
                            str = '[' + str + ']';
                        }
                    }
                    hashMap.put("date", format);
                    hashMap.put(DeviceKey.ArraySteps, str);
                    hashMap.put(DeviceKey.KDetailMinterStep, BleConst.GetDeviceTime);
                    hashMap.put("distance", BleConst.GetDeviceTime);
                    hashMap.put("calories", BleConst.GetDeviceTime);
                    arrayList.add(hashMap);
                }
            }
        }
        return arrayList;
    }

    @NotNull
    public final List<KHJstyleHourlyWalkData> getHourlyStepsData(@NotNull StepsDayData stepsDayData) {
        Intrinsics.checkNotNullParameter(stepsDayData, "stepsDayData");
        ArrayList arrayList = new ArrayList();
        if (stepsDayData.getTimeLogBean() != null) {
            List<StepsHourData> logBeans = stepsDayData.getTimeLogBean().getLogBeans();
            Intrinsics.checkNotNullExpressionValue(logBeans, "stepsDayData.getTimeLogBean().logBeans");
            if (!logBeans.isEmpty()) {
                for (StepsHourData stepsHourData : stepsDayData.getTimeLogBean().getLogBeans()) {
                    KHJstyleHourlyWalkData kHJstyleHourlyWalkData = new KHJstyleHourlyWalkData();
                    kHJstyleHourlyWalkData.mDate = stepsDayData.mDate;
                    kHJstyleHourlyWalkData.setStartTime(stepsHourData.getStartHour());
                    kHJstyleHourlyWalkData.setEndTime(stepsHourData.getEndHour());
                    kHJstyleHourlyWalkData.setMacAddress(stepsDayData.getmMacAddress());
                    kHJstyleHourlyWalkData.setIntervelValue(stepsHourData.mStepsPerHour);
                    kHJstyleHourlyWalkData.setCalories((int) stepsHourData.getCaloriesPerHour());
                    kHJstyleHourlyWalkData.setDistance((int) stepsHourData.getmistancePerHour());
                    kHJstyleHourlyWalkData.setCodevalue(stepsHourData.getmMinuteWiseData());
                    arrayList.add(kHJstyleHourlyWalkData);
                }
            }
        }
        return arrayList;
    }

    public final String getTAG() {
        return f4060a;
    }

    public final void setTAG(String str) {
        f4060a = str;
    }
}
