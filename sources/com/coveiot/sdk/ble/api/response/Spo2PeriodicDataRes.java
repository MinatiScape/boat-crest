package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.sdk.ble.api.model.Spo2Data;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.CommandBytes;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.Spo2DailyData;
import com.coveiot.sdk.ble.model.Spo2HourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanSpo2;
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
public class Spo2PeriodicDataRes extends BaseResponse {
    public Spo2Data e;

    /* renamed from: com.coveiot.sdk.ble.api.response.Spo2PeriodicDataRes$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7564a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7564a = iArr;
            try {
                iArr[ResponseType.GET_PERIODIC_SPO2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public Spo2PeriodicDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Spo2Data b() {
        Date time;
        int i;
        ArrayList arrayList;
        int i2;
        int i3;
        int i4;
        String[] split;
        ResponseData responseData = this.c;
        this.e = new Spo2Data();
        Calendar calendar = Calendar.getInstance();
        int i5 = 0;
        calendar.set(11, 0);
        int i6 = 12;
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(6, -responseData.getDay());
        int endTime = responseData.getEndTime();
        int startTime = responseData.getStartTime();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList<String> dataList = responseData.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d("spo2", "dataList " + dataList.size(), moduleNames.getModuleName());
        Calendar calendar2 = Calendar.getInstance();
        if (responseData.getDay() == 0) {
            time = calendar2.getTime();
        } else {
            calendar2.add(6, -responseData.getDay());
            time = calendar2.getTime();
        }
        String format = BleUtils.getDateFormater("yyyy-MM-dd").format(time);
        LogHelper.d("date is ", format, moduleNames.getModuleName());
        ActivityType activityType = ActivityType.PERIODIC_SPO2;
        if (responseData.getResponseType() != null) {
            int i7 = AnonymousClass1.f7564a[responseData.getResponseType().ordinal()];
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (true) {
            i = 1;
            if (!it.hasNext()) {
                break;
            }
            try {
                String str = (String) it.next();
                split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
            if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                int length = split.length;
                int i8 = i6;
                while (i8 < length) {
                    arrayList4.add(Byte.valueOf(Byte.parseByte(split[i8].trim())));
                    i8++;
                    i6 = 12;
                }
            } else {
                int length2 = split.length;
                for (int i9 = 4; i9 < length2; i9++) {
                    arrayList4.add(Byte.valueOf(Byte.parseByte(split[i9].trim())));
                }
                i6 = 12;
            }
        }
        LogHelper.d("spo2", "filteredDataList " + arrayList4.size(), ModuleNames.BLEABSTRACT.getModuleName());
        for (int i10 = 0; i10 < arrayList4.size(); i10++) {
            if (((Byte) arrayList4.get(i10)).byteValue() == -1) {
                arrayList2.add(0);
            } else {
                arrayList2.add(Integer.valueOf(((Byte) arrayList4.get(i10)).byteValue() & 255));
            }
        }
        LogHelper.d("spo2", "fiveMinList " + arrayList2.size(), ModuleNames.BLEABSTRACT.getModuleName());
        int i11 = 0;
        int i12 = 0;
        while (i11 < arrayList2.size()) {
            Spo2HourlyData spo2HourlyData = new Spo2HourlyData();
            spo2HourlyData.setDate(format);
            spo2HourlyData.setMacAddress(responseData.getMacAddress());
            spo2HourlyData.setActivityType(activityType.toString());
            ArrayList arrayList5 = new ArrayList();
            for (int i13 = i5; i13 < 12 && (i4 = i11 + i13) < arrayList2.size(); i13++) {
                ((Integer) arrayList2.get(i4)).intValue();
                arrayList5.add(arrayList2.get(i4));
            }
            spo2HourlyData.setSpo2MinuteData(arrayList5);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[i];
            int i14 = startTime + i12;
            objArr[0] = Integer.valueOf(i14);
            sb.append(String.format(locale, "%02d", objArr));
            sb.append(":00:00");
            spo2HourlyData.setStartHour(sb.toString());
            if (i12 == 23) {
                spo2HourlyData.setEndHour("00:00:00");
                arrayList = arrayList2;
                i3 = 0;
                i2 = 0;
            } else {
                StringBuilder sb2 = new StringBuilder();
                int i15 = i12;
                arrayList = arrayList2;
                Integer valueOf = Integer.valueOf(i14 + 1);
                i2 = 0;
                sb2.append(String.format(locale, "%02d", valueOf));
                sb2.append(":00:00");
                spo2HourlyData.setEndHour(sb2.toString());
                i3 = i15;
            }
            spo2HourlyData.setMacAddress(responseData.getMacAddress());
            arrayList3.add(spo2HourlyData);
            i12 = i3 + 1;
            i11 += 12;
            i5 = i2;
            arrayList2 = arrayList;
            i = 1;
        }
        if (endTime == 23 && startTime == 0) {
            if (arrayList3.size() > 24) {
                c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
                throw new RuntimeException("Data not proper");
            }
        } else if ((endTime - startTime) + 1 < arrayList3.size()) {
            c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
            throw new RuntimeException("Data not proper");
        }
        if (ActivityType.PERIODIC_SPO2 != activityType || arrayList3.size() <= 0) {
            return null;
        }
        Spo2DailyData spo2DailyData = new Spo2DailyData();
        spo2DailyData.setmMacAddress(responseData.getMacAddress());
        spo2DailyData.setmActivityType(activityType.toString());
        spo2DailyData.setmDate(format);
        TimeLogBeanSpo2 timeLogBeanSpo2 = new TimeLogBeanSpo2();
        timeLogBeanSpo2.setLogBeans(arrayList3);
        spo2DailyData.setTimeLogBean(timeLogBeanSpo2);
        this.e.setDailyData(spo2DailyData);
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

    public Spo2Data getPeriodicSpo2Data() {
        return b();
    }
}
