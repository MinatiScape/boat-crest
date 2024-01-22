package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.sdk.ble.api.model.StepsData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.CommandBytes;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.StepsDailyData;
import com.coveiot.sdk.ble.model.StepsHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanSteps;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
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
public class WalkDataRes extends BaseResponse {
    public StepsData e;

    /* renamed from: com.coveiot.sdk.ble.api.response.WalkDataRes$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7566a;

        static {
            int[] iArr = new int[ResponseType.values().length];
            f7566a = iArr;
            try {
                iArr[ResponseType.FIVE_MIN_WALK_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public WalkDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final StepsData b() {
        Date time;
        String str;
        int i;
        int i2;
        String[] split;
        ResponseData responseData = this.c;
        this.e = new StepsData();
        Calendar calendar = Calendar.getInstance();
        int i3 = 0;
        calendar.set(11, 0);
        int i4 = 12;
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
            int i5 = AnonymousClass1.f7566a[responseData.getResponseType().ordinal()];
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str2 = (String) it.next();
                split = str2.substring(1, str2.length() - 1).split(Constants.SEPARATOR_COMMA);
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
            if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                int length = split.length;
                int i6 = i4;
                while (i6 < length) {
                    arrayList3.add(Byte.valueOf(Byte.parseByte(split[i6].trim())));
                    i6++;
                    i4 = 12;
                }
            } else {
                int length2 = split.length;
                for (int i7 = 4; i7 < length2; i7++) {
                    arrayList3.add(Byte.valueOf(Byte.parseByte(split[i7].trim())));
                }
                i4 = 12;
            }
        }
        LogHelper.d("WalkRes", "filteredDataList " + arrayList3.size(), ModuleNames.BLEABSTRACT.getModuleName());
        for (int i8 = 0; i8 < arrayList3.size() - 1; i8 += 2) {
            if (((Byte) arrayList3.get(i8)).byteValue() == -1 && ((Byte) arrayList3.get(i8 + 1)).byteValue() == -1) {
                arrayList.add(0);
            } else {
                int byteValue = (((Byte) arrayList3.get(i8)).byteValue() & 255) | ((((Byte) arrayList3.get(i8 + 1)).byteValue() & 255) << 8);
                if (byteValue <= 3000) {
                    arrayList.add(Integer.valueOf(byteValue));
                } else {
                    c("5 minutes steps greater than 3000", this.c.getDataList(), getBaseRequest().getCommandBytes());
                    throw new RuntimeException("5 minutes steps greater than 3000");
                }
            }
        }
        LogHelper.d("WalkRes", "fiveMinList " + arrayList.size(), ModuleNames.BLEABSTRACT.getModuleName());
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i9 < arrayList.size()) {
            StepsHourlyData stepsHourlyData = new StepsHourlyData();
            stepsHourlyData.setDate(format);
            stepsHourlyData.setMacAddress(responseData.getMacAddress());
            stepsHourlyData.setActivityType(activityType.toString());
            ArrayList arrayList4 = new ArrayList();
            for (int i12 = i3; i12 < 12; i12++) {
                int i13 = i9 + i12;
                i3 += ((Integer) arrayList.get(i13)).intValue();
                arrayList4.add(arrayList.get(i13));
            }
            int i14 = i11 + i3;
            stepsHourlyData.setStepsPerHour(i3);
            stepsHourlyData.setMinuteWiseData(arrayList4);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            ArrayList arrayList5 = arrayList;
            int i15 = startTime + i10;
            sb.append(String.format(locale, "%02d", Integer.valueOf(i15)));
            sb.append(":00:00");
            stepsHourlyData.setStartHour(sb.toString());
            if (i10 == 23) {
                stepsHourlyData.setEndHour("00:00:00");
                str = format;
                i2 = 0;
                i = 0;
            } else {
                StringBuilder sb2 = new StringBuilder();
                int i16 = i10;
                str = format;
                Integer valueOf = Integer.valueOf(i15 + 1);
                i = 0;
                sb2.append(String.format(locale, "%02d", valueOf));
                sb2.append(":00:00");
                stepsHourlyData.setEndHour(sb2.toString());
                i2 = i16;
            }
            stepsHourlyData.setCaloriesPerHour(AppUtils.calculateCalories(i3, responseData.getHeight(), responseData.getWeight(), responseData.getStrideLength()));
            stepsHourlyData.setDistancePerHour(AppUtils.calculateDistance(i3, responseData.getHeight(), responseData.getStrideLength()));
            stepsHourlyData.setMacAddress(responseData.getMacAddress());
            arrayList2.add(stepsHourlyData);
            i10 = i2 + 1;
            i9 += 12;
            i3 = i;
            arrayList = arrayList5;
            i11 = i14;
            format = str;
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
        if (ActivityType.WALK != activityType || arrayList2.size() <= 0) {
            return null;
        }
        StepsDailyData stepsDailyData = new StepsDailyData();
        stepsDailyData.setmMacAddress(responseData.getMacAddress());
        stepsDailyData.setmActivityType(activityType.toString());
        stepsDailyData.setmDate(str3);
        stepsDailyData.setmSteps(i11);
        if (i11 <= 70000) {
            BleUtils.getCalories(i11, responseData.getHeight(), responseData.getWeight());
            stepsDailyData.setmCalories(AppUtils.calculateCalories(i11, responseData.getHeight(), responseData.getWeight(), responseData.getStrideLength()));
            stepsDailyData.setmDistance(AppUtils.calculateDistance(i11, responseData.getHeight(), responseData.getStrideLength()));
            TimeLogBeanSteps timeLogBeanSteps = new TimeLogBeanSteps();
            timeLogBeanSteps.setLogBeans(arrayList2);
            stepsDailyData.setTimeLogBean(timeLogBeanSteps);
            this.e.setDailyData(stepsDailyData);
            return this.e;
        }
        c("day level steps greater than 70000", this.c.getDataList(), getBaseRequest().getCommandBytes());
        throw new RuntimeException("day level steps greater than 70000");
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

    public StepsData getStepsData() {
        return b();
    }
}
