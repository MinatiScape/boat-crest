package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.sdk.ble.api.model.TemperatureData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.CommandBytes;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.TemperatureDailyData;
import com.coveiot.sdk.ble.model.TemperatureHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanTemperature;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class TemperatureDataRes extends BaseResponse {
    public TemperatureData e;

    /* renamed from: com.coveiot.sdk.ble.api.response.TemperatureDataRes$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7565a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7565a = iArr;
            try {
                iArr[ResponseType.FIVE_MIN_WALK_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public TemperatureDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    @Nullable
    public final TemperatureData b() {
        Date time;
        int i;
        int i2;
        int i3;
        String[] split;
        ResponseData responseData = this.c;
        this.e = new TemperatureData();
        Calendar calendar = Calendar.getInstance();
        int i4 = 0;
        calendar.set(11, 0);
        int i5 = 12;
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(6, -responseData.getDay());
        int endTime = responseData.getEndTime();
        int startTime = responseData.getStartTime();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<String> dataList = responseData.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d("WalkRes", "dataList " + dataList.size(), moduleNames.getModuleName());
        Calendar calendar2 = Calendar.getInstance();
        if (responseData.getDay() == 0) {
            time = calendar2.getTime();
        } else {
            calendar2.add(6, -responseData.getDay());
            time = calendar2.getTime();
        }
        String format = BleUtils.getDateFormater("yyyy-MM-dd").format(time);
        LogHelper.d("date is ", format, moduleNames.getModuleName());
        ActivityType activityType = ActivityType.WALK;
        if (responseData.getResponseType() != null) {
            int i6 = AnonymousClass1.f7565a[responseData.getResponseType().ordinal()];
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
            if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                int length = split.length;
                int i7 = i5;
                while (i7 < length) {
                    arrayList3.add(Byte.valueOf(Byte.parseByte(split[i7].trim())));
                    i7++;
                    i5 = 12;
                }
            } else {
                int length2 = split.length;
                for (int i8 = 4; i8 < length2; i8++) {
                    arrayList3.add(Byte.valueOf(Byte.parseByte(split[i8].trim())));
                }
                i5 = 12;
            }
        }
        LogHelper.d("WalkRes", "filteredDataList " + arrayList3.size(), ModuleNames.BLEABSTRACT.getModuleName());
        for (int i9 = 0; i9 < arrayList3.size() - 1; i9 += 2) {
            if (((Byte) arrayList3.get(i9)).byteValue() == -1 && ((Byte) arrayList3.get(i9 + 1)).byteValue() == -1) {
                arrayList.add(0);
            } else {
                arrayList.add(Integer.valueOf((((Byte) arrayList3.get(i9)).byteValue() & 255) | ((((Byte) arrayList3.get(i9 + 1)).byteValue() & 255) << 8)));
            }
        }
        LogHelper.d("WalkRes", "fiveMinList " + arrayList.size(), ModuleNames.BLEABSTRACT.getModuleName());
        int i10 = 0;
        int i11 = 0;
        while (i10 < arrayList.size()) {
            TemperatureHourlyData temperatureHourlyData = new TemperatureHourlyData();
            temperatureHourlyData.setDate(format);
            temperatureHourlyData.setMacAddress(responseData.getMacAddress());
            temperatureHourlyData.setActivityType(activityType.toString());
            ArrayList<Double> arrayList4 = new ArrayList<>();
            int i12 = i4;
            while (i12 < 12) {
                ((Integer) arrayList.get(i10 + i12)).intValue();
                arrayList4.add(Double.valueOf(((Integer) arrayList.get(i3)).intValue() / 10.0d));
                i12++;
                endTime = endTime;
            }
            int i13 = endTime;
            temperatureHourlyData.setTemperatureMinuteData(arrayList4);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            int i14 = startTime + i11;
            sb.append(String.format(locale, "%02d", Integer.valueOf(i14)));
            sb.append(":00:00");
            temperatureHourlyData.setStartHour(sb.toString());
            if (i11 == 23) {
                temperatureHourlyData.setEndHour("00:00:00");
                i2 = 0;
                i = 0;
            } else {
                StringBuilder sb2 = new StringBuilder();
                i = 0;
                sb2.append(String.format(locale, "%02d", Integer.valueOf(i14 + 1)));
                sb2.append(":00:00");
                temperatureHourlyData.setEndHour(sb2.toString());
                i2 = i11;
            }
            temperatureHourlyData.setMacAddress(responseData.getMacAddress());
            arrayList2.add(temperatureHourlyData);
            i11 = i2 + 1;
            i10 += 12;
            i4 = i;
            endTime = i13;
        }
        int i15 = endTime;
        if (i15 == 23 && startTime == 0) {
            if (arrayList2.size() > 24) {
                c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
                throw new RuntimeException("Data not proper");
            }
        } else if ((i15 - startTime) + 1 < arrayList2.size()) {
            c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
            throw new RuntimeException("Data not proper");
        }
        if (ActivityType.WALK != activityType || arrayList2.size() <= 0) {
            return null;
        }
        TemperatureDailyData temperatureDailyData = new TemperatureDailyData();
        temperatureDailyData.setmMacAddress(responseData.getMacAddress());
        temperatureDailyData.setmActivityType(activityType.toString());
        temperatureDailyData.setmDate(format);
        TimeLogBeanTemperature timeLogBeanTemperature = new TimeLogBeanTemperature();
        timeLogBeanTemperature.setLogBeans(arrayList2);
        temperatureDailyData.setTimeLogBean(timeLogBeanTemperature);
        this.e.setDailyData(temperatureDailyData);
        return this.e;
    }

    public final void c(String str, ArrayList<String> arrayList, List<CommandBytes> list) {
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(str);
            arrayList.add(0, "Command sent" + Arrays.toString(list.get(0).getCommandData()));
            analyticsLog.setData(arrayList);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TemperatureData getTemperatureData() {
        return b();
    }
}
