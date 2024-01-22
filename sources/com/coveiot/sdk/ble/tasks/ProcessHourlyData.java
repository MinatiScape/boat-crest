package com.coveiot.sdk.ble.tasks;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.StepsData;
import com.coveiot.sdk.ble.api.response.WalkDataRes;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.StepsDailyData;
import com.coveiot.sdk.ble.model.StepsHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanSteps;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class ProcessHourlyData implements Runnable {
    public final Context h;
    public final String i;
    public final Calendar j;
    public final SimpleDateFormat k;
    public final ResponseData l;
    public CommandObject m;
    public ArrayList<Byte> n = new ArrayList<>();

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7582a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7582a = iArr;
            try {
                iArr[ResponseType.HOURLY_WALK_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7582a[ResponseType.HOURLY_RUN_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7582a[ResponseType.HOURLY_HIKE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7582a[ResponseType.HOURLY_BIKE_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7582a[ResponseType.HOURLY_SWIM_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public ProcessHourlyData(Context context, String str, ResponseData responseData, CommandObject commandObject) {
        this.h = context;
        this.i = str;
        this.m = commandObject;
        Calendar calendar = Calendar.getInstance();
        this.j = calendar;
        this.l = responseData;
        calendar.setTimeZone(TimeZone.getDefault());
        this.k = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    }

    @Override // java.lang.Runnable
    public void run() {
        Date time;
        int i;
        char c;
        ResponseData responseData = this.l;
        Calendar calendar = Calendar.getInstance();
        char c2 = 0;
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(6, -responseData.getDay());
        responseData.getEndTime();
        int startTime = responseData.getStartTime();
        ArrayList arrayList = new ArrayList();
        ArrayList<String> dataList = responseData.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        if (responseData.getDay() == 0) {
            time = this.j.getTime();
        } else {
            this.j.add(6, -responseData.getDay());
            time = this.j.getTime();
        }
        String format = this.k.format(time);
        LogHelper.d("date is ", format, ModuleNames.BLEABSTRACT.getModuleName());
        if (copyOnWriteArrayList.size() > 0) {
            byte[] bytes = ((String) copyOnWriteArrayList.get(0)).getBytes();
            byte b = bytes[10];
            byte b2 = bytes[11];
        }
        ActivityType activityType = ActivityType.WALK;
        if (responseData.getResponseType() != null) {
            int i2 = a.f7582a[responseData.getResponseType().ordinal()];
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                    int length = split.length;
                    for (int i3 = 12; i3 < length; i3++) {
                        this.n.add(Byte.valueOf(Byte.parseByte(split[i3].trim())));
                    }
                } else {
                    int length2 = split.length;
                    for (int i4 = 4; i4 < length2; i4++) {
                        this.n.add(Byte.valueOf(Byte.parseByte(split[i4].trim())));
                    }
                }
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < this.n.size() - 1) {
            int byteValue = (this.n.get(i5).byteValue() & 255) | ((this.n.get(i5 + 1).byteValue() & 255) << 8);
            i6 += byteValue;
            StepsHourlyData stepsHourlyData = new StepsHourlyData();
            stepsHourlyData.setDate(format);
            stepsHourlyData.setMacAddress(this.i);
            stepsHourlyData.setActivityType(activityType.toString());
            stepsHourlyData.setStepsPerHour(byteValue);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[1];
            objArr[c2] = Integer.valueOf(startTime + i7);
            sb.append(String.format(locale, "%02d", objArr));
            sb.append(":00:00");
            stepsHourlyData.setStartHour(sb.toString());
            i7++;
            int i8 = startTime + i7;
            if (i8 > 23) {
                stepsHourlyData.setEndHour("00:00:00");
                i = startTime;
                i7 = 0;
                c = 0;
            } else {
                StringBuilder sb2 = new StringBuilder();
                i = startTime;
                c = 0;
                sb2.append(String.format(locale, "%02d", Integer.valueOf(i8)));
                sb2.append(":00:00");
                stepsHourlyData.setEndHour(sb2.toString());
            }
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            arrayList2.add(Integer.valueOf(byteValue));
            stepsHourlyData.setMinuteWiseData(arrayList2);
            stepsHourlyData.setCaloriesPerHour(BleUtils.getCalories(byteValue, this.h));
            stepsHourlyData.setDistancePerHour(BleUtils.getMeters(byteValue, this.h));
            stepsHourlyData.setMacAddress(this.i);
            arrayList.add(stepsHourlyData);
            i5 += 2;
            c2 = c;
            startTime = i;
        }
        if (ActivityType.WALK != activityType || arrayList.size() <= 0) {
            return;
        }
        StepsDailyData stepsDailyData = new StepsDailyData();
        stepsDailyData.setmMacAddress(this.i);
        stepsDailyData.setmActivityType(activityType.toString());
        stepsDailyData.setmDate(format);
        stepsDailyData.setmSteps(i6);
        BleUtils.getCalories(i6, this.h);
        stepsDailyData.setmCalories(BleUtils.getCalories(i6, this.h));
        stepsDailyData.setmDistance(BleUtils.getMeters(i6, this.h));
        TimeLogBeanSteps timeLogBeanSteps = new TimeLogBeanSteps();
        timeLogBeanSteps.setLogBeans(arrayList);
        stepsDailyData.setTimeLogBean(timeLogBeanSteps);
        WalkDataRes walkDataRes = new WalkDataRes(CommandType.WALK, this.m.getBaseRequest());
        new StepsData().setDailyData(stepsDailyData);
        this.m.getResponseListener().onResponse(walkDataRes);
    }
}
