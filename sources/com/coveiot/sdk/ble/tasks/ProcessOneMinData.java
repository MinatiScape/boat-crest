package com.coveiot.sdk.ble.tasks;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.SleepData;
import com.coveiot.sdk.ble.api.response.SleepDataRes;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.helper.BleCmdManager;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.SleepDailyData;
import com.coveiot.sdk.ble.model.SleepHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanSleep;
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
public class ProcessOneMinData implements Runnable {
    public static final int AWAKE_SLEEP_30 = 30;
    public static final int AWAKE_SLEEP_CUTOFF_30 = 30;
    public static final int DEEP_SLEEP_50 = 50;
    public static final int DEEP_SLEEP_CUTOFF_50 = 50;
    public static final int LIGHT_SLEEP_50 = 50;
    public static final int LIGHT_SLEEP_CUTOFF_50 = 50;
    public static final int MINIMUM_HOURS_FILTERING = 4;
    public static final String u = ProcessOneMinData.class.getSimpleName();
    public final Context h;
    public final String i;
    public final Calendar j;
    public final SimpleDateFormat k;
    public final ResponseData l;
    public ArrayList<Byte> m = new ArrayList<>();
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public CommandObject t;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7583a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7583a = iArr;
            try {
                iArr[ResponseType.ONE_MIN_SLEEP_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7583a[ResponseType.TEN_MIN_SLEEP_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7583a[ResponseType.ONE_MIN_UV_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ProcessOneMinData(Context context, String str, ResponseData responseData, CommandObject commandObject) {
        this.h = context;
        this.i = str;
        Calendar calendar = Calendar.getInstance();
        this.j = calendar;
        this.l = responseData;
        this.t = commandObject;
        calendar.setTimeZone(TimeZone.getDefault());
        this.k = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    }

    @Override // java.lang.Runnable
    public void run() {
        Date time;
        int i;
        int i2;
        char c;
        String[] split;
        ResponseData responseData = this.l;
        ActivityType activityType = ActivityType.SLEEP;
        int startTime = responseData.getStartTime();
        responseData.getEndTime();
        Calendar calendar = Calendar.getInstance();
        int i3 = 0;
        calendar.set(11, 0);
        int i4 = 12;
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(6, -responseData.getDay());
        if (responseData.getDay() == 0) {
            time = this.j.getTime();
        } else {
            this.j.add(6, -responseData.getDay());
            time = this.j.getTime();
        }
        String format = this.k.format(time);
        LogHelper.d(u, "date is ++ " + format, ModuleNames.BLEABSTRACT.getModuleName());
        if (responseData.getResponseType() != null) {
            int i5 = a.f7583a[responseData.getResponseType().ordinal()];
            char c2 = 2;
            int i6 = 1;
            if (i5 == 1) {
                this.n = 60;
            } else if (i5 == 2) {
                this.n = 6;
            } else if (i5 == 3) {
                activityType = ActivityType.UV;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = responseData.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            if (copyOnWriteArrayList.size() > 0) {
                byte[] bytes = ((String) copyOnWriteArrayList.get(0)).getBytes();
                byte b = bytes[5];
                byte b2 = bytes[6];
            }
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    String str = (String) it.next();
                    split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                } catch (Exception e) {
                    LogHelper.d(u, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
                if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                    int length = split.length;
                    int i7 = i4;
                    while (i7 < length) {
                        this.m.add(Byte.valueOf(Byte.parseByte(split[i7].trim())));
                        i7++;
                        i4 = 12;
                    }
                } else {
                    int length2 = split.length;
                    for (int i8 = 4; i8 < length2; i8++) {
                        this.m.add(Byte.valueOf(Byte.parseByte(split[i8].trim())));
                    }
                    i4 = 12;
                }
            }
            if (activityType == ActivityType.SLEEP) {
                int i9 = 0;
                int i10 = 0;
                while (i9 < this.m.size()) {
                    SleepHourlyData sleepHourlyData = new SleepHourlyData();
                    sleepHourlyData.setActivityType(activityType.toString());
                    sleepHourlyData.setDate(format);
                    sleepHourlyData.setMacAddress(this.i);
                    ArrayList<Integer> arrayList2 = new ArrayList<>();
                    int i11 = i3;
                    int i12 = i11;
                    int i13 = i12;
                    int i14 = i13;
                    int i15 = i14;
                    while (i12 < this.n) {
                        int i16 = i9 + i12;
                        if (this.m.size() > i16) {
                            arrayList2.add(Integer.valueOf(this.m.get(i16).byteValue()));
                            if (Integer.valueOf(this.m.get(i16).byteValue()).intValue() == 0) {
                                i3 += 10;
                            } else if (Integer.valueOf(this.m.get(i16).byteValue()).intValue() == i6) {
                                i11 += 10;
                            } else {
                                c = 2;
                                if (Integer.valueOf(this.m.get(i16).byteValue()).intValue() == 2) {
                                    i13 += 10;
                                } else if (Integer.valueOf(this.m.get(i16).byteValue()).intValue() >= 3) {
                                    i15 += 10;
                                }
                                i14 += i13 + i11;
                                i12++;
                                c2 = c;
                            }
                            c = 2;
                            i14 += i13 + i11;
                            i12++;
                            c2 = c;
                        } else {
                            c2 = 2;
                        }
                        i6 = 1;
                    }
                    int i17 = i14;
                    int i18 = i15;
                    StringBuilder sb = new StringBuilder();
                    Locale locale = Locale.ENGLISH;
                    ActivityType activityType2 = activityType;
                    String str2 = format;
                    int i19 = startTime + i10;
                    int i20 = startTime;
                    sb.append(String.format(locale, "%02d", Integer.valueOf(i19)));
                    sb.append(":00:00");
                    sleepHourlyData.setStartHour(sb.toString());
                    if (i10 >= 23) {
                        sleepHourlyData.setEndHour("00:00:00");
                        i = i9;
                        i2 = 0;
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        i = i9;
                        sb2.append(String.format(locale, "%02d", Integer.valueOf(i19 + 1)));
                        sb2.append(":00:00");
                        sleepHourlyData.setEndHour(sb2.toString());
                        i2 = i10;
                    }
                    BleCmdManager.getInstance().getSleepIntervalTime(this.h);
                    int i21 = this.p + i13;
                    this.p = i21;
                    int i22 = this.q + i11;
                    this.q = i22;
                    this.r += i21 + i22;
                    if (i13 <= 0 && i11 <= 0) {
                        sleepHourlyData.setAwakePerHour(0);
                        sleepHourlyData.setUnSleepPerHour(this.n * 10);
                        this.s += this.n * 10;
                    } else {
                        sleepHourlyData.setAwakePerHour(i3);
                        sleepHourlyData.setUnSleepPerHour(i18);
                        this.o += i3;
                        this.s += i18;
                    }
                    sleepHourlyData.setMinuteWiseData(arrayList2);
                    sleepHourlyData.setDeepSleepPerHour(i13);
                    sleepHourlyData.setLightSleepPerHour(i11);
                    sleepHourlyData.setTotalSleepPerHour(i17);
                    sleepHourlyData.setMacAddress(this.i);
                    arrayList.add(sleepHourlyData);
                    i10 = i2 + 1;
                    i9 = i + this.n;
                    format = str2;
                    activityType = activityType2;
                    startTime = i20;
                    i3 = 0;
                    c2 = 2;
                    i6 = 1;
                }
            }
            ActivityType activityType3 = activityType;
            String str3 = format;
            if (ActivityType.SLEEP != activityType3 || arrayList.size() <= 0) {
                return;
            }
            int i23 = 0;
            for (int i24 = 0; i24 < arrayList.size(); i24++) {
                i23 = ((SleepHourlyData) arrayList.get(i24)).getDeepSleepPerHour() == 60 ? i23 + 1 : 0;
                if (i23 > 3) {
                    for (int i25 = 0; i25 < i23; i25++) {
                        int i26 = i24 - i25;
                        if (((SleepHourlyData) arrayList.get(i26)).getDeepSleepPerHour() != 0) {
                            this.p -= 60;
                            this.s += 60;
                            SleepHourlyData sleepHourlyData2 = new SleepHourlyData();
                            sleepHourlyData2.setUnSleepPerHour(this.n * 10);
                            sleepHourlyData2.setDeepSleepPerHour(0);
                            sleepHourlyData2.setLightSleepPerHour(0);
                            sleepHourlyData2.setAwakePerHour(0);
                            sleepHourlyData2.setMacAddress(this.i);
                            ArrayList<Integer> arrayList3 = new ArrayList<>();
                            for (int i27 = 0; i27 < this.n; i27++) {
                                arrayList3.add(3);
                            }
                            sleepHourlyData2.setMinuteWiseData(arrayList3);
                            arrayList.set(i26, sleepHourlyData2);
                        }
                    }
                }
            }
            SleepDailyData sleepDailyData = new SleepDailyData();
            sleepDailyData.setmMacAddress(this.i);
            sleepDailyData.setmActivityType(activityType3.toString());
            sleepDailyData.setmDate(str3);
            sleepDailyData.setmDeepSleep(this.p);
            sleepDailyData.setmLightSleep(this.q);
            sleepDailyData.setmAwakeTime(this.o);
            sleepDailyData.setmUnSleep(this.s);
            sleepDailyData.setmTotalSleep(this.r);
            TimeLogBeanSleep timeLogBeanSleep = new TimeLogBeanSleep();
            timeLogBeanSleep.setLogBeans(arrayList);
            sleepDailyData.setTimeLogBean(timeLogBeanSleep);
            SleepDataRes sleepDataRes = new SleepDataRes(CommandType.SLEEP, this.t.getBaseRequest());
            SleepData sleepData = new SleepData();
            sleepData.setDailyData(sleepDailyData);
            sleepDataRes.setSleepData(sleepData);
            this.t.getResponseListener().onResponse(sleepDataRes);
        }
    }
}
