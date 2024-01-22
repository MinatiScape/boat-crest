package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.RrData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.RrDailyData;
import com.coveiot.sdk.ble.model.RrHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeansRr;
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
public class RrDataRes extends BaseResponse {
    public RrData e;

    /* renamed from: com.coveiot.sdk.ble.api.response.RrDataRes$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7557a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7557a = iArr;
            try {
                iArr[ResponseType.FIVE_MIN_RR_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public RrDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final RrData b() {
        Date time;
        int i;
        ResponseData responseData = this.c;
        this.e = new RrData();
        Calendar calendar = Calendar.getInstance();
        int i2 = 0;
        calendar.set(11, 0);
        int i3 = 12;
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
        ActivityType activityType = ActivityType.RR;
        if (responseData.getResponseType() != null) {
            int i4 = AnonymousClass1.f7557a[responseData.getResponseType().ordinal()];
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                    int length = split.length;
                    for (int i5 = 12; i5 < length; i5++) {
                        arrayList3.add(Byte.valueOf(Byte.parseByte(split[i5].trim())));
                    }
                } else {
                    int length2 = split.length;
                    for (int i6 = 4; i6 < length2; i6++) {
                        arrayList3.add(Byte.valueOf(Byte.parseByte(split[i6].trim())));
                    }
                }
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        for (int i7 = 0; i7 < arrayList3.size() - 1; i7++) {
            arrayList.add(Integer.valueOf(((Byte) arrayList3.get(i7)).byteValue() & 255));
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < arrayList.size()) {
            RrHourlyData rrHourlyData = new RrHourlyData();
            rrHourlyData.setDate(format);
            rrHourlyData.setMacAddress(responseData.getMacAddress());
            rrHourlyData.setActivityType(activityType.toString());
            ArrayList arrayList4 = new ArrayList();
            for (int i10 = i2; i10 < i3; i10++) {
                arrayList4.add(arrayList.get(i8 + i10));
            }
            rrHourlyData.setMinuteWiseData(arrayList4);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[1];
            int i11 = startTime + i9;
            objArr[i2] = Integer.valueOf(i11);
            sb.append(String.format(locale, "%02d", objArr));
            sb.append(":00:00");
            rrHourlyData.setStartHour(sb.toString());
            if (i9 == 23) {
                rrHourlyData.setEndHour("00:00:00");
                i9 = i2;
                i = i9;
            } else {
                StringBuilder sb2 = new StringBuilder();
                i = 0;
                sb2.append(String.format(locale, "%02d", Integer.valueOf(i11 + 1)));
                sb2.append(":00:00");
                rrHourlyData.setEndHour(sb2.toString());
            }
            rrHourlyData.setMacAddress(responseData.getMacAddress());
            arrayList2.add(rrHourlyData);
            i9++;
            i8 += 12;
            i2 = i;
            i3 = 12;
        }
        if (ActivityType.RR != activityType || arrayList2.size() <= 0) {
            return null;
        }
        RrDailyData rrDailyData = new RrDailyData();
        rrDailyData.setmMacAddress(responseData.getMacAddress());
        rrDailyData.setmActivityType(activityType.toString());
        rrDailyData.setmDate(format);
        TimeLogBeansRr timeLogBeansRr = new TimeLogBeansRr();
        timeLogBeansRr.setLogBeans(arrayList2);
        rrDailyData.setTimeLogBean(timeLogBeansRr);
        this.e.setDailyData(rrDailyData);
        return this.e;
    }

    public RrData getRrData() {
        return b();
    }
}
