package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.sdk.ble.api.model.StressData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.CommandBytes;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.model.StressDailyData;
import com.coveiot.sdk.ble.model.StressHRVHolder;
import com.coveiot.sdk.ble.model.StressHourlyData;
import com.coveiot.sdk.ble.model.TimeLogBeanStress;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class StressDataRes extends BaseResponse {
    public StressData e;

    public StressDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final double a(ArrayList<StressHourlyData> arrayList) {
        int size = arrayList.size();
        Double valueOf = Double.valueOf(0.0d);
        if (size > 0) {
            if (arrayList.size() > 0) {
                int i = 0;
                Double d = valueOf;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (arrayList.get(i2).getAvgStress() > 0.0d) {
                        d = Double.valueOf(d.doubleValue() + arrayList.get(i2).getAvgStress());
                        i++;
                    }
                }
                if (i > 0) {
                    valueOf = Double.valueOf(d.doubleValue() / i);
                }
            }
            return valueOf.doubleValue();
        }
        return 0.0d;
    }

    @Nullable
    public final StressData b() {
        Date time;
        int i;
        ArrayList arrayList;
        int i2;
        byte[] bArr;
        int i3;
        double d;
        byte b;
        double d2;
        double d3;
        String str;
        ResponseData responseData = this.c;
        this.e = new StressData();
        Calendar calendar = Calendar.getInstance();
        int i4 = 0;
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(6, -responseData.getDay());
        int endTime = responseData.getEndTime();
        int startTime = responseData.getStartTime();
        new ArrayList();
        ArrayList<StressHourlyData> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<String> dataList = responseData.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(EcgStyleReportUtil.UserInfoKey.STRESS, "dataList " + dataList.size(), moduleNames.getModuleName());
        Calendar calendar2 = Calendar.getInstance();
        if (responseData.getDay() == 0) {
            time = calendar2.getTime();
        } else {
            calendar2.add(6, -responseData.getDay());
            time = calendar2.getTime();
        }
        String format = BleUtils.getDateFormater("yyyy-MM-dd").format(time);
        LogHelper.d("date is ", format, moduleNames.getModuleName());
        ActivityType activityType = ActivityType.STRESS;
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String[] split = ((String) it.next()).substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                    if (Byte.parseByte(split[0].trim()) == Byte.MAX_VALUE) {
                        int length = split.length;
                        for (int i5 = 13; i5 < length; i5++) {
                            arrayList3.add(Byte.valueOf(Byte.parseByte(split[i5].trim())));
                        }
                    }
                } else if (responseData.getDataList().size() == 1) {
                    int length2 = split.length;
                    for (int i6 = 5; i6 < length2; i6++) {
                        arrayList3.add(Byte.valueOf(Byte.parseByte(split[i6].trim())));
                    }
                } else {
                    int length3 = split.length;
                    for (int i7 = 4; i7 < length3; i7++) {
                        arrayList3.add(Byte.valueOf(Byte.parseByte(split[i7].trim())));
                    }
                }
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        LogHelper.d(EcgStyleReportUtil.UserInfoKey.STRESS, "filteredDataList " + arrayList3.size(), ModuleNames.BLEABSTRACT.getModuleName());
        if (arrayList3.size() < 5) {
            return null;
        }
        int i8 = 0;
        int i9 = 0;
        double d4 = 0.0d;
        double d5 = 0.0d;
        double d6 = 0.0d;
        double d7 = 0.0d;
        while (i8 < arrayList3.size()) {
            ArrayList<StressHRVHolder> arrayList4 = new ArrayList<>();
            int i10 = i4;
            while (i10 < 4) {
                byte byteValue = ((Byte) arrayList3.get(i8 + 0)).byteValue();
                double d8 = d4;
                if (ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList3.get(i8 + 2)).byteValue(), ((Byte) arrayList3.get(i8 + 1)).byteValue()}).getInt() != 65535) {
                    d = ByteBuffer.wrap(bArr).getInt() / 100.0f;
                    i3 = 4;
                } else {
                    i3 = 4;
                    d = 0.0d;
                }
                byte[] bArr2 = new byte[i3];
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = ((Byte) arrayList3.get(i8 + 4)).byteValue();
                bArr2[3] = ((Byte) arrayList3.get(i8 + 3)).byteValue();
                double d9 = ByteBuffer.wrap(bArr2).getInt() != 65535 ? ByteBuffer.wrap(bArr2).getInt() / 100.0f : 0.0d;
                if (byteValue == 0 || byteValue == -1) {
                    b = 2;
                    d2 = 0.0d;
                    d3 = 0.0d;
                } else {
                    d3 = d;
                    b = 2;
                    d2 = d9;
                }
                if (byteValue == b) {
                    d4 = d2;
                    d5 = d3;
                } else {
                    d4 = d8;
                }
                if (byteValue == 3) {
                    d6 = d2;
                    d7 = d3;
                }
                if (byteValue == 3) {
                    arrayList4.add(new StressHRVHolder(byteValue, -1.0d, d3));
                } else {
                    arrayList4.add(new StressHRVHolder(byteValue, d2, d3));
                }
                i10++;
                i8 += 5;
            }
            double d10 = d4;
            StressHourlyData stressHourlyData = new StressHourlyData(arrayList4);
            stressHourlyData.setDate(format);
            stressHourlyData.setMacAddress(responseData.getMacAddress());
            stressHourlyData.setActivityType(activityType.toString());
            stressHourlyData.setStressHRVHolders(arrayList4);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            int i11 = startTime + i9;
            sb.append(String.format(locale, "%02d", Integer.valueOf(i11)));
            sb.append(":00:00");
            stressHourlyData.setStartHour(sb.toString());
            if (i9 == 23) {
                stressHourlyData.setEndHour("00:00:00");
                i = i8;
                arrayList = arrayList3;
                i9 = 0;
                i2 = 0;
            } else {
                StringBuilder sb2 = new StringBuilder();
                i = i8;
                arrayList = arrayList3;
                i2 = 0;
                sb2.append(String.format(locale, "%02d", Integer.valueOf(i11 + 1)));
                sb2.append(":00:00");
                stressHourlyData.setEndHour(sb2.toString());
            }
            stressHourlyData.setMacAddress(responseData.getMacAddress());
            arrayList2.add(stressHourlyData);
            i9++;
            i8 = i;
            i4 = i2;
            d4 = d10;
            arrayList3 = arrayList;
        }
        if (endTime == 23 && startTime == 0) {
            if (arrayList2.size() > 24) {
                c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
                throw new RuntimeException("Data not proper");
            }
        } else if ((endTime - startTime) + 1 < arrayList2.size()) {
            c("Data not proper", this.c.getDataList(), getBaseRequest().getCommandBytes());
            throw new RuntimeException("Data not proper");
        }
        if (arrayList2.size() > 0) {
            StressDailyData stressDailyData = new StressDailyData();
            stressDailyData.setmMacAddress(responseData.getMacAddress());
            stressDailyData.setmActivityType(activityType.toString());
            stressDailyData.setmDate(format);
            stressDailyData.setMaxStress(d(arrayList2));
            stressDailyData.setMinStress(e(arrayList2));
            stressDailyData.setAvgStress(a(arrayList2));
            stressDailyData.setMaxHrv(getMaxHRVPerDay(arrayList2));
            stressDailyData.setMinHrv(getMinHRVPerDay(arrayList2));
            stressDailyData.setAvgHrv(getAvgHRVPerDay(arrayList2));
            stressDailyData.setBaselineHrv(d5);
            stressDailyData.setBaselineStress(d4);
            stressDailyData.setReadinessHrv(d7);
            stressDailyData.setReadinessStress(d6);
            TimeLogBeanStress timeLogBeanStress = new TimeLogBeanStress();
            timeLogBeanStress.setLogBeans(arrayList2);
            stressDailyData.setTimeLogBean(timeLogBeanStress);
            this.e.setDailyData(stressDailyData);
            return this.e;
        }
        return null;
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

    public final double d(ArrayList<StressHourlyData> arrayList) {
        if (arrayList.size() > 0) {
            double maxStress = arrayList.get(0).getMaxStress();
            for (int i = 0; i < arrayList.size(); i++) {
                if (maxStress < arrayList.get(i).getMaxStress()) {
                    maxStress = arrayList.get(i).getMaxStress();
                }
            }
            return maxStress;
        }
        return 0.0d;
    }

    public final double e(ArrayList<StressHourlyData> arrayList) {
        if (arrayList.size() > 0) {
            Double valueOf = Double.valueOf(0.0d);
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    break;
                } else if (arrayList.get(i).getMinStress() > 0.0d) {
                    valueOf = Double.valueOf(arrayList.get(i).getMinStress());
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (valueOf.doubleValue() > arrayList.get(i2).getMinStress() && arrayList.get(i2).getMinStress() > 0.0d) {
                    valueOf = Double.valueOf(arrayList.get(i2).getMinStress());
                }
            }
            return valueOf.doubleValue();
        }
        return 0.0d;
    }

    public double getAvgHRVPerDay(ArrayList<StressHourlyData> arrayList) {
        Double valueOf = Double.valueOf(0.0d);
        if (arrayList.size() > 0) {
            Double d = valueOf;
            int i = 0;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (arrayList.get(i2).getAvgHRV() > 0.0d) {
                    d = Double.valueOf(d.doubleValue() + arrayList.get(i2).getAvgHRV());
                    i++;
                }
            }
            if (i > 0) {
                valueOf = Double.valueOf(d.doubleValue() / i);
            }
        }
        return valueOf.doubleValue();
    }

    public double getMaxHRVPerDay(ArrayList<StressHourlyData> arrayList) {
        Double valueOf = Double.valueOf(0.0d);
        if (arrayList.size() > 0) {
            Double valueOf2 = Double.valueOf(arrayList.get(0).getMaxHRV());
            for (int i = 0; i < arrayList.size(); i++) {
                if (valueOf2.doubleValue() < arrayList.get(i).getMaxHRV()) {
                    valueOf2 = Double.valueOf(arrayList.get(i).getMaxHRV());
                }
            }
            valueOf = valueOf2;
        }
        return valueOf.doubleValue();
    }

    public double getMinHRVPerDay(ArrayList<StressHourlyData> arrayList) {
        double d;
        if (arrayList.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    d = 0.0d;
                    break;
                } else if (arrayList.get(i).getMinHRV() > 0.0d) {
                    d = arrayList.get(i).getMinHRV();
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (d > arrayList.get(i2).getMinHRV() && arrayList.get(i2).getMinHRV() > 0.0d) {
                    d = arrayList.get(i2).getMinHRV();
                }
            }
            return d;
        }
        return 0.0d;
    }

    public StressData getStressData() {
        return b();
    }
}
