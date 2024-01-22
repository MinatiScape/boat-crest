package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.StepsData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.StepsDailyData;
import com.coveiot.sdk.ble.model.StepsHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanSteps;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class RunningDataRes extends BaseResponse {
    public StepsData e;

    /* renamed from: com.coveiot.sdk.ble.api.response.RunningDataRes$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7558a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7558a = iArr;
            try {
                iArr[ResponseType.FIVE_MIN_RUN_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public RunningDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final StepsData b() {
        Date time;
        int i;
        int i2;
        int i3;
        ResponseData responseData = this.c;
        this.e = new StepsData();
        Calendar calendar = Calendar.getInstance();
        int i4 = 0;
        calendar.set(11, 0);
        int i5 = 12;
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(6, -responseData.getDay());
        responseData.getEndTime();
        int startTime = responseData.getStartTime();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<String> dataList = responseData.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Calendar calendar2 = Calendar.getInstance();
        if (responseData.getDay() == 0) {
            time = calendar2.getTime();
        } else {
            calendar2.add(6, -responseData.getDay());
            time = calendar2.getTime();
        }
        String format = BleUtils.getDateFormater("yyyy-MM-dd").format(time);
        LogHelper.d("date is ", format, ModuleNames.BLEABSTRACT.getModuleName());
        ActivityType activityType = ActivityType.RUN;
        if (responseData.getResponseType() != null) {
            int i6 = AnonymousClass1.f7558a[responseData.getResponseType().ordinal()];
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (true) {
            i = 1;
            if (!it.hasNext()) {
                break;
            }
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                    int length = split.length;
                    for (int i7 = 12; i7 < length; i7++) {
                        arrayList3.add(Byte.valueOf(Byte.parseByte(split[i7].trim())));
                    }
                } else {
                    int length2 = split.length;
                    for (int i8 = 4; i8 < length2; i8++) {
                        arrayList3.add(Byte.valueOf(Byte.parseByte(split[i8].trim())));
                    }
                }
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        for (int i9 = 0; i9 < arrayList3.size() - 1; i9 += 2) {
            arrayList.add(Integer.valueOf((((Byte) arrayList3.get(i9)).byteValue() & 255) | ((((Byte) arrayList3.get(i9 + 1)).byteValue() & 255) << 8)));
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < arrayList.size()) {
            StepsHourlyData stepsHourlyData = new StepsHourlyData();
            stepsHourlyData.setDate(format);
            stepsHourlyData.setMacAddress(responseData.getMacAddress());
            stepsHourlyData.setActivityType(activityType.toString());
            ArrayList arrayList4 = new ArrayList();
            int i12 = i4;
            int i13 = i12;
            while (i12 < i5) {
                int i14 = i10 + i12;
                i13 += ((Integer) arrayList.get(i14)).intValue();
                arrayList4.add(arrayList.get(i14));
                i12++;
                i5 = 12;
            }
            stepsHourlyData.setStepsPerHour(i13);
            stepsHourlyData.setMinuteWiseData(arrayList4);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[i];
            int i15 = startTime + i11;
            objArr[i4] = Integer.valueOf(i15);
            sb.append(String.format(locale, "%02d", objArr));
            sb.append(":00:00");
            stepsHourlyData.setStartHour(sb.toString());
            if (i11 == 23) {
                stepsHourlyData.setEndHour("00:00:00");
                i2 = startTime;
                i11 = 0;
                i3 = 0;
            } else {
                StringBuilder sb2 = new StringBuilder();
                i2 = startTime;
                Object[] objArr2 = new Object[i];
                i3 = 0;
                objArr2[0] = Integer.valueOf(i15 + 1);
                sb2.append(String.format(locale, "%02d", objArr2));
                sb2.append(":00:00");
                stepsHourlyData.setEndHour(sb2.toString());
            }
            stepsHourlyData.setCaloriesPerHour(BleUtils.getCalories(i3, responseData.getHeight(), responseData.getWeight()));
            stepsHourlyData.setDistancePerHour(BleUtils.getMeters(i3, responseData.getHeight()));
            stepsHourlyData.setMacAddress(responseData.getMacAddress());
            arrayList2.add(stepsHourlyData);
            i11++;
            i10 += 12;
            i = 1;
            startTime = i2;
            i5 = 12;
            i4 = 0;
        }
        if (ActivityType.RUN != activityType || arrayList2.size() <= 0) {
            return null;
        }
        StepsDailyData stepsDailyData = new StepsDailyData();
        stepsDailyData.setmMacAddress(responseData.getMacAddress());
        stepsDailyData.setmActivityType(activityType.toString());
        stepsDailyData.setmDate(format);
        stepsDailyData.setmSteps(0);
        BleUtils.getCalories(0, responseData.getHeight(), responseData.getWeight());
        stepsDailyData.setmCalories(BleUtils.getCalories(0, responseData.getHeight(), responseData.getWeight()));
        stepsDailyData.setmDistance(BleUtils.getMeters(0, responseData.getHeight()));
        TimeLogBeanSteps timeLogBeanSteps = new TimeLogBeanSteps();
        timeLogBeanSteps.setLogBeans(arrayList2);
        stepsDailyData.setTimeLogBean(timeLogBeanSteps);
        this.e.setDailyData(stepsDailyData);
        return this.e;
    }

    public StepsData getStepsData() {
        return b();
    }
}
