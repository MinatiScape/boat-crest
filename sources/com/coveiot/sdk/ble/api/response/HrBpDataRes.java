package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.sdk.ble.api.model.BpHrMinData;
import com.coveiot.sdk.ble.api.model.HrBpData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.CommandBytes;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.HrBpDailyData;
import com.coveiot.sdk.ble.model.HrBpHourlyData;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.TimeLogBeansHrBp;
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
public class HrBpDataRes extends BaseResponse {
    public HrBpData e;

    /* renamed from: com.coveiot.sdk.ble.api.response.HrBpDataRes$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7555a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7555a = iArr;
            try {
                iArr[ResponseType.HISTORY_FOR_AUTOMATIC_HR_BP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public HrBpDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    @Nullable
    public final HrBpData b() {
        Date time;
        int i;
        int i2;
        int i3;
        String str;
        String str2;
        ResponseData responseData = this.c;
        this.e = new HrBpData();
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
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
        LogHelper.d("HeartRateRes", "dataList " + dataList.size(), moduleNames.getModuleName());
        Calendar calendar2 = Calendar.getInstance();
        if (responseData.getDay() == 0) {
            time = calendar2.getTime();
        } else {
            calendar2.add(6, -responseData.getDay());
            time = calendar2.getTime();
        }
        String format = BleUtils.getDateFormater("yyyy-MM-dd").format(time);
        LogHelper.d("date is ", format, moduleNames.getModuleName());
        ActivityType activityType = ActivityType.HR_BP;
        if (responseData.getResponseType() != null) {
            int i4 = AnonymousClass1.f7555a[responseData.getResponseType().ordinal()];
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (true) {
            i2 = 1;
            if (!it.hasNext()) {
                break;
            }
            try {
                String[] split = ((String) it.next()).substring(1, str2.length() - 1).split(Constants.SEPARATOR_COMMA);
                if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                    int length = split.length;
                    int i5 = 12;
                    while (i5 < length) {
                        int i6 = length;
                        byte parseByte = Byte.parseByte(split[i5].trim());
                        if (parseByte != -1) {
                            arrayList3.add(Byte.valueOf(parseByte));
                        } else {
                            arrayList3.add((byte) 0);
                        }
                        i5++;
                        length = i6;
                    }
                } else {
                    int length2 = split.length;
                    int i7 = 4;
                    while (i7 < length2) {
                        String[] strArr = split;
                        byte parseByte2 = Byte.parseByte(split[i7].trim());
                        if (parseByte2 != -1) {
                            arrayList3.add(Byte.valueOf(parseByte2));
                        } else {
                            arrayList3.add((byte) 0);
                        }
                        i7++;
                        split = strArr;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LogHelper.d("HeartRateRes", "filteredDataList " + arrayList3.size(), ModuleNames.BLEABSTRACT.getModuleName());
        for (int i8 = 0; i8 < arrayList3.size(); i8++) {
            arrayList.add(Integer.valueOf(((Byte) arrayList3.get(i8)).byteValue() & 255));
        }
        int timeInterval = (60 / responseData.getTimeInterval()) * 4;
        ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
        LogHelper.d("Fitered Data", arrayList.size() + "", moduleNames2.getModuleName());
        LogHelper.d("HeartRateRes", "fiveMinList " + arrayList.size(), moduleNames2.getModuleName());
        int i9 = 0;
        int i10 = 0;
        for (i = 3; i9 < arrayList.size() - i; i = 3) {
            HrBpHourlyData hrBpHourlyData = new HrBpHourlyData();
            hrBpHourlyData.setDate(format);
            hrBpHourlyData.setMacAddress(responseData.getMacAddress());
            hrBpHourlyData.setActivityType(activityType.toString());
            ArrayList<BpHrMinData> arrayList4 = new ArrayList<>();
            int i11 = 0;
            while (i11 < timeInterval) {
                BpHrMinData bpHrMinData = new BpHrMinData();
                bpHrMinData.setmHr(((Integer) arrayList.get(i9 + i11)).intValue());
                int i12 = i11 + 1;
                bpHrMinData.setmDbp(((Integer) arrayList.get(i9 + i12)).intValue());
                int i13 = i12 + 1;
                bpHrMinData.setmSbp(((Integer) arrayList.get(i9 + i13)).intValue());
                int i14 = i13 + 1;
                bpHrMinData.setmRr(((Integer) arrayList.get(i9 + i14)).intValue());
                arrayList4.add(bpHrMinData);
                i2 = 1;
                i11 = i14 + 1;
            }
            hrBpHourlyData.setBpHrMinData(arrayList4);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[i2];
            int i15 = startTime + i10;
            objArr[0] = Integer.valueOf(i15);
            ArrayList arrayList5 = arrayList;
            sb.append(String.format(locale, "%02d", objArr));
            sb.append(":00:00");
            hrBpHourlyData.setStartHour(sb.toString());
            if (i10 == 23) {
                hrBpHourlyData.setEndHour("00:00:00");
                str = format;
                i3 = 0;
            } else {
                StringBuilder sb2 = new StringBuilder();
                i3 = i10;
                str = format;
                sb2.append(String.format(locale, "%02d", Integer.valueOf(i15 + 1)));
                sb2.append(":00:00");
                hrBpHourlyData.setEndHour(sb2.toString());
            }
            hrBpHourlyData.setMacAddress(responseData.getMacAddress());
            arrayList2.add(hrBpHourlyData);
            i9 += timeInterval;
            i10 = i3 + 1;
            arrayList = arrayList5;
            format = str;
            i2 = 1;
        }
        String str3 = format;
        if (endTime == 23 && startTime == 0) {
            if (arrayList2.size() > 24) {
                c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
                throw new RuntimeException("Data not proper");
            }
        } else if ((endTime - startTime) + 1 < arrayList2.size()) {
            c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
            throw new RuntimeException("Data not proper");
        }
        if (ActivityType.HR_BP != activityType || arrayList2.size() <= 0) {
            return null;
        }
        HrBpDailyData hrBpDailyData = new HrBpDailyData();
        hrBpDailyData.setmMacAddress(responseData.getMacAddress());
        hrBpDailyData.setmActivityType(activityType.toString());
        hrBpDailyData.setmDate(str3);
        TimeLogBeansHrBp timeLogBeansHrBp = new TimeLogBeansHrBp();
        timeLogBeansHrBp.setLogBeans(arrayList2);
        hrBpDailyData.setTimeLogBean(timeLogBeansHrBp);
        this.e.setDailyData(hrBpDailyData);
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

    public HrBpData getHrBpData() {
        return b();
    }
}
