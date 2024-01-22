package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.DailyDistanceData;
import com.coveiot.sdk.ble.api.model.DistanceHourlyData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.GetDistanceDataReq;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class DistanceDataRes extends BaseResponse {
    public DistanceDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final boolean b() {
        String str = this.c.getDataList().get(0);
        return str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA).length > 5;
    }

    @Nullable
    public final DailyDistanceData c() {
        int i;
        int i2;
        int i3;
        Locale locale;
        DistanceDataRes distanceDataRes = this;
        DailyDistanceData dailyDistanceData = new DailyDistanceData();
        ArrayList arrayList = new ArrayList();
        ArrayList<String> dataList = distanceDataRes.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                    Byte.parseByte(split[0]);
                    int length = split.length;
                    for (int i4 = 12; i4 < length; i4++) {
                        arrayList.add(Byte.valueOf(Byte.parseByte(split[i4].trim())));
                    }
                } else {
                    int length2 = split.length;
                    for (int i5 = 4; i5 < length2; i5++) {
                        arrayList.add(Byte.valueOf(Byte.parseByte(split[i5].trim())));
                    }
                }
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        int i6 = ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList.get(1)).byteValue(), ((Byte) arrayList.get(0)).byteValue()}).getInt();
        ArrayList arrayList2 = new ArrayList();
        int i7 = 1;
        while (true) {
            int i8 = i7 + 1;
            byte byteValue = ((Byte) arrayList.get(i8)).byteValue();
            int i9 = i8 + 1;
            byte byteValue2 = ((Byte) arrayList.get(i9)).byteValue();
            int i10 = i9 + 1;
            byte byteValue3 = ((Byte) arrayList.get(i10)).byteValue();
            i7 = i10 + 1;
            arrayList2.add(Integer.valueOf((int) ByteBuffer.wrap(new byte[]{((Byte) arrayList.get(i7)).byteValue(), byteValue3, byteValue2, byteValue}).getFloat()));
            if (i7 >= arrayList.size() - 1) {
                break;
            }
            distanceDataRes = this;
        }
        dailyDistanceData.setDate(BleUtils.getDateFormater("yyyy-MM-dd").format(((GetDistanceDataReq) distanceDataRes.b).getmDate()));
        dailyDistanceData.setInterval(i6);
        if (i6 <= 60) {
            i2 = 60 / i6;
            i = 0;
        } else {
            i = -(i6 / 60);
            i2 = i;
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i11 < arrayList2.size()) {
            if (i >= i2 || i < 0) {
                i3 = i2;
                while (i < 0) {
                    DistanceHourlyData distanceHourlyData = new DistanceHourlyData();
                    if (i == -1) {
                        arrayList4.add(arrayList2.get(i11));
                    } else {
                        arrayList4.add(0);
                    }
                    int intValue = ((Integer) arrayList2.get(i11)).intValue();
                    distanceHourlyData.setDate(dailyDistanceData.getDate());
                    StringBuilder sb = new StringBuilder();
                    Locale locale2 = Locale.ENGLISH;
                    sb.append(String.format(locale2, "%02d", Integer.valueOf(i13)));
                    sb.append(":00:00");
                    distanceHourlyData.setStartHour(sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    i13++;
                    sb2.append(String.format(locale2, "%02d", Integer.valueOf(i13)));
                    sb2.append(":00:00");
                    distanceHourlyData.setEndHour(sb2.toString());
                    distanceHourlyData.setCodeValues(arrayList4);
                    distanceHourlyData.setDistanceValue(intValue);
                    arrayList3.add(distanceHourlyData);
                    i++;
                    arrayList4 = new ArrayList();
                    i12 = 0;
                }
                if (i == 0) {
                    i = i3;
                }
            } else {
                arrayList4.add(arrayList2.get(i11));
                i12 += ((Integer) arrayList2.get(i11)).intValue();
                i++;
                if (i == i2) {
                    DistanceHourlyData distanceHourlyData2 = new DistanceHourlyData();
                    distanceHourlyData2.setDate(dailyDistanceData.getDate());
                    StringBuilder sb3 = new StringBuilder();
                    i3 = i2;
                    sb3.append(String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i13)));
                    sb3.append(":00:00");
                    distanceHourlyData2.setStartHour(sb3.toString());
                    i13++;
                    if (i13 == 24) {
                        distanceHourlyData2.setEndHour(String.format(locale, "%02d", 0) + ":00:00");
                    } else {
                        distanceHourlyData2.setEndHour(String.format(locale, "%02d", Integer.valueOf(i13)) + ":00:00");
                    }
                    distanceHourlyData2.setCodeValues(arrayList4);
                    distanceHourlyData2.setDistanceValue(i12);
                    arrayList3.add(distanceHourlyData2);
                    arrayList4 = new ArrayList();
                    i = 0;
                    i12 = 0;
                } else {
                    i3 = i2;
                }
            }
            i11++;
            i2 = i3;
        }
        dailyDistanceData.setDistanceHourlyDataList(arrayList3);
        LogHelper.d("distance", String.valueOf(dailyDistanceData));
        return dailyDistanceData;
    }

    public DailyDistanceData getDistanceData() {
        return c();
    }

    public boolean isSuccess() {
        return b();
    }
}
