package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.SleepData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.SleepDailyData;
import com.coveiot.sdk.ble.model.SleepHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanSleep;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class SleepDataRes extends BaseResponse {
    public static final String g = "com.coveiot.sdk.ble.api.response.SleepDataRes";
    public SleepData e;
    public ArrayList<Byte> f;

    /* renamed from: com.coveiot.sdk.ble.api.response.SleepDataRes$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7563a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7563a = iArr;
            try {
                iArr[ResponseType.ONE_MIN_SLEEP_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7563a[ResponseType.TEN_MIN_SLEEP_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7563a[ResponseType.ONE_MIN_UV_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SleepDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
        this.f = new ArrayList<>();
    }

    public SleepData getSleepData() {
        Date time;
        int i;
        ActivityType activityType;
        ResponseData responseData;
        String str;
        ActivityType activityType2;
        int i2;
        ArrayList arrayList;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Locale locale;
        Locale locale2;
        String str2;
        String[] split;
        SleepDataRes sleepDataRes = this;
        ResponseData responseData2 = sleepDataRes.c;
        sleepDataRes.e = new SleepData();
        ActivityType activityType3 = ActivityType.SLEEP;
        int startTime = responseData2.getStartTime();
        responseData2.getEndTime();
        Calendar calendar = Calendar.getInstance();
        int i9 = 12;
        int i10 = 1;
        if (responseData2.getDay() == 0) {
            if (Calendar.getInstance().get(11) < 12) {
                calendar.add(6, -responseData2.getDay());
            } else {
                calendar.add(6, (-responseData2.getDay()) + 1);
            }
            time = calendar.getTime();
        } else {
            if (Calendar.getInstance().get(11) < 12) {
                calendar.add(6, -responseData2.getDay());
            } else {
                calendar.add(6, (-responseData2.getDay()) + 1);
            }
            time = calendar.getTime();
        }
        SimpleDateFormat dateFormater = BleUtils.getDateFormater("yyyy-MM-dd");
        String format = dateFormater.format(time);
        LogHelper.d(g, "date is ++ " + format, ModuleNames.BLEABSTRACT.getModuleName());
        calendar.add(6, -1);
        String format2 = dateFormater.format(calendar.getTime());
        if (responseData2.getResponseType() != null) {
            int i11 = AnonymousClass1.f7563a[responseData2.getResponseType().ordinal()];
            char c = 3;
            char c2 = 0;
            if (i11 == 1) {
                i = 15;
                activityType = activityType3;
            } else if (i11 != 2) {
                if (i11 == 3) {
                    activityType3 = ActivityType.UV;
                }
                activityType = activityType3;
                i = 0;
            } else {
                activityType = activityType3;
                i = 6;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList<String> dataList = responseData2.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    split = ((String) it.next()).substring(i10, str2.length() - 1).split(Constants.SEPARATOR_COMMA);
                } catch (Exception e) {
                    LogHelper.d(g, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
                if (Byte.parseByte(split[c2]) == Byte.MAX_VALUE) {
                    if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[c].trim()) & 255) << 8)) == 0) {
                        int length = split.length;
                        int i12 = 12;
                        while (i12 < length) {
                            sleepDataRes.f.add(Byte.valueOf(Byte.parseByte(split[i12].trim())));
                            i12++;
                            i10 = 1;
                            c = 3;
                        }
                        c2 = 0;
                    } else {
                        int length2 = split.length;
                        for (int i13 = 4; i13 < length2; i13++) {
                            sleepDataRes.f.add(Byte.valueOf(Byte.parseByte(split[i13].trim())));
                        }
                    }
                } else {
                    int length3 = split.length;
                    for (int i14 = 4; i14 < length3; i14++) {
                        sleepDataRes.f.add(Byte.valueOf(Byte.parseByte(split[i14].trim())));
                    }
                }
                i10 = 1;
                c = 3;
                c2 = 0;
            }
            if (activityType != ActivityType.SLEEP) {
                responseData = responseData2;
                str = format;
                activityType2 = activityType;
                i2 = i;
                arrayList = arrayList2;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
            } else if (sleepDataRes.f.size() >= i) {
                int i15 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
                while (i15 < sleepDataRes.f.size()) {
                    String str3 = (startTime < i9 || startTime > 23) ? format : format2;
                    SleepHourlyData sleepHourlyData = new SleepHourlyData();
                    String str4 = format2;
                    sleepHourlyData.setActivityType(activityType.toString());
                    sleepHourlyData.setDate(str3);
                    sleepHourlyData.setMacAddress(responseData2.getMacAddress());
                    ArrayList<Integer> arrayList3 = new ArrayList<>();
                    ResponseData responseData3 = responseData2;
                    String str5 = format;
                    ActivityType activityType4 = activityType;
                    int i16 = i7;
                    ArrayList arrayList4 = arrayList2;
                    int i17 = 0;
                    int i18 = 0;
                    int i19 = 0;
                    int i20 = 0;
                    int i21 = 0;
                    int i22 = 0;
                    int i23 = 0;
                    while (i20 < i) {
                        int i24 = i;
                        int i25 = i6;
                        int i26 = i15 + i20;
                        if (sleepDataRes.f.size() > i26) {
                            Byte b = sleepDataRes.f.get(i26);
                            int byteValue = ((b.byteValue() & 192) >> 6) & 255;
                            int byteValue2 = ((b.byteValue() & 48) >> 4) & 255;
                            int i27 = i15;
                            int byteValue3 = ((b.byteValue() & 12) >> 2) & 255;
                            int byteValue4 = b.byteValue() & 3 & 255;
                            int i28 = i5;
                            arrayList3.add(Integer.valueOf(byteValue));
                            arrayList3.add(Integer.valueOf(byteValue2));
                            arrayList3.add(Integer.valueOf(byteValue3));
                            arrayList3.add(Integer.valueOf(byteValue4));
                            if (byteValue == 0) {
                                i22++;
                            } else if (byteValue == 1) {
                                i19++;
                            } else if (byteValue == 2) {
                                i18++;
                            } else if (byteValue == 3) {
                                i23++;
                            } else if (byteValue > 3) {
                                i21++;
                            }
                            if (byteValue2 == 0) {
                                i22++;
                            } else if (byteValue2 == 1) {
                                i19++;
                            } else if (byteValue2 == 2) {
                                i18++;
                            } else if (byteValue2 == 3) {
                                i23++;
                            } else if (byteValue2 > 3) {
                                i21++;
                            }
                            if (byteValue3 == 0) {
                                i22++;
                            } else if (byteValue3 == 1) {
                                i19++;
                            } else if (byteValue3 == 2) {
                                i18++;
                            } else if (byteValue3 == 3) {
                                i23++;
                            } else if (byteValue3 > 3) {
                                i21++;
                            }
                            if (byteValue4 == 0) {
                                i22++;
                            } else if (byteValue4 == 1) {
                                i19++;
                            } else {
                                if (byteValue4 == 2) {
                                    i18++;
                                } else if (byteValue4 == 3) {
                                    i23++;
                                } else if (byteValue4 > 3) {
                                    i21++;
                                }
                                i17 += i18 + i19 + i23;
                                i20++;
                                sleepDataRes = this;
                                i = i24;
                                i6 = i25;
                                i5 = i28;
                                i15 = i27;
                            }
                            i17 += i18 + i19 + i23;
                            i20++;
                            sleepDataRes = this;
                            i = i24;
                            i6 = i25;
                            i5 = i28;
                            i15 = i27;
                        } else {
                            sleepDataRes = this;
                            i = i24;
                            i6 = i25;
                        }
                    }
                    int i29 = i15;
                    int i30 = i5;
                    int i31 = i6;
                    int i32 = i;
                    if (startTime >= 23) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format(Locale.ENGLISH, "%02d", Integer.valueOf(startTime)));
                        sb.append(":00:00");
                        sleepHourlyData.setStartHour(sb.toString());
                        sleepHourlyData.setEndHour(String.format(locale2, "%02d", Integer.valueOf(startTime + 1)) + ":00:00");
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(String.format(Locale.ENGLISH, "%02d", Integer.valueOf(startTime)));
                        sb2.append(":00:00");
                        sleepHourlyData.setStartHour(sb2.toString());
                        sleepHourlyData.setEndHour(String.format(locale, "%02d", Integer.valueOf(startTime + 1)) + ":00:00");
                    }
                    i3 += i18;
                    i4 += i19;
                    i5 = i30 + i23;
                    i6 = i31 + i3 + i4 + i5;
                    if (i18 <= 0 && i19 <= 0) {
                        sleepHourlyData.setAwakePerHour(0);
                        int i33 = i32 * 4;
                        sleepHourlyData.setUnSleepPerHour(i33);
                        i8 += i33;
                        i7 = i16;
                    } else {
                        sleepHourlyData.setAwakePerHour(i22);
                        sleepHourlyData.setUnSleepPerHour(i21);
                        i8 += i21;
                        i7 = i16 + i22;
                    }
                    sleepHourlyData.setMinuteWiseData(arrayList3);
                    sleepHourlyData.setDeepSleepPerHour(i18);
                    sleepHourlyData.setLightSleepPerHour(i19);
                    sleepHourlyData.setTotalSleepPerHour(i17);
                    sleepHourlyData.setMacAddress(responseData3.getMacAddress());
                    LogHelper.d("SleepDataRes for", sleepHourlyData.getDate() + "-----", ModuleNames.BLEABSTRACT.getModuleName());
                    arrayList4.add(sleepHourlyData);
                    startTime = startTime >= 23 ? 0 : startTime + 1;
                    i15 = i29 + i32;
                    arrayList2 = arrayList4;
                    i9 = 12;
                    format2 = str4;
                    format = str5;
                    activityType = activityType4;
                    responseData2 = responseData3;
                    i = i32;
                    sleepDataRes = this;
                }
                responseData = responseData2;
                str = format;
                activityType2 = activityType;
                i2 = i;
                arrayList = arrayList2;
            } else {
                throw new RuntimeException("Invalid data packets");
            }
            ActivityType activityType5 = activityType2;
            if (ActivityType.SLEEP != activityType5 || arrayList.size() <= 0) {
                sleepDataRes = this;
            } else {
                int i34 = i8;
                int i35 = 0;
                for (int i36 = 0; i36 < arrayList.size(); i36++) {
                    int i37 = 60;
                    i35 = ((SleepHourlyData) arrayList.get(i36)).getDeepSleepPerHour() == 60 ? i35 + 1 : 0;
                    if (i35 > 3) {
                        int i38 = 0;
                        while (i38 < i35) {
                            int i39 = i36 - i38;
                            if (((SleepHourlyData) arrayList.get(i39)).getDeepSleepPerHour() != 0) {
                                i3 -= 60;
                                i34 += 60;
                                SleepHourlyData sleepHourlyData2 = new SleepHourlyData();
                                sleepHourlyData2.setUnSleepPerHour(i2 * 10);
                                sleepHourlyData2.setDeepSleepPerHour(0);
                                sleepHourlyData2.setLightSleepPerHour(0);
                                sleepHourlyData2.setAwakePerHour(0);
                                sleepHourlyData2.setDate(((SleepHourlyData) arrayList.get(i39)).getDate());
                                sleepHourlyData2.setStartHour(((SleepHourlyData) arrayList.get(i39)).getStartHour());
                                sleepHourlyData2.setEndHour(((SleepHourlyData) arrayList.get(i39)).getEndHour());
                                sleepHourlyData2.setMacAddress(responseData.getMacAddress());
                                ArrayList<Integer> arrayList5 = new ArrayList<>();
                                int i40 = 0;
                                while (i40 < i37) {
                                    arrayList5.add(-1);
                                    i40++;
                                    i37 = 60;
                                }
                                sleepHourlyData2.setMinuteWiseData(arrayList5);
                                arrayList.set(i39, sleepHourlyData2);
                            }
                            i38++;
                            i37 = 60;
                        }
                    }
                }
                SleepDailyData sleepDailyData = new SleepDailyData();
                sleepDailyData.setmMacAddress(responseData.getMacAddress());
                sleepDailyData.setmActivityType(activityType5.toString());
                sleepDailyData.setmDate(str);
                sleepDailyData.setmDeepSleep(i3);
                sleepDailyData.setmLightSleep(i4);
                sleepDailyData.setmAwakeTime(i7);
                sleepDailyData.setmUnSleep(i34);
                sleepDailyData.setmRemSleep(i5);
                sleepDailyData.setmUnSleep(i34);
                sleepDailyData.setmTotalSleep(i6);
                TimeLogBeanSleep timeLogBeanSleep = new TimeLogBeanSleep();
                timeLogBeanSleep.setLogBeans(arrayList);
                sleepDailyData.setTimeLogBean(timeLogBeanSleep);
                sleepDataRes = this;
                sleepDataRes.e.setDailyData(sleepDailyData);
            }
        }
        return sleepDataRes.e;
    }

    public void setSleepData(SleepData sleepData) {
        this.e = sleepData;
    }
}
