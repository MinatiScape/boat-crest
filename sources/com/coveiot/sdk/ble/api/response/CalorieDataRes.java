package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.CalorieHourlyData;
import com.coveiot.sdk.ble.api.model.DailyCalorieData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.GetCalorieDataReq;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class CalorieDataRes extends BaseResponse {
    public CalorieDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final boolean b() {
        String str = this.c.getDataList().get(0);
        return str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA).length > 5;
    }

    @Nullable
    public final DailyCalorieData c() {
        boolean z;
        int i;
        int i2;
        int i3;
        boolean z2;
        boolean z3;
        Locale locale;
        CalorieDataRes calorieDataRes = this;
        DailyCalorieData dailyCalorieData = new DailyCalorieData();
        ArrayList arrayList = new ArrayList();
        ArrayList<String> dataList = calorieDataRes.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (true) {
            z = false;
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
            arrayList2.add(Float.valueOf(ByteBuffer.wrap(new byte[]{((Byte) arrayList.get(i7)).byteValue(), byteValue3, byteValue2, byteValue}).getFloat()));
            if (i7 >= arrayList.size() - 1) {
                break;
            }
            calorieDataRes = this;
        }
        dailyCalorieData.setDate(BleUtils.getDateFormater("yyyy-MM-dd").format(((GetCalorieDataReq) calorieDataRes.b).getmDate()));
        dailyCalorieData.setInterval(i6);
        if (i6 <= 60) {
            i2 = 60 / i6;
            i = 0;
        } else {
            i = -(i6 / 60);
            i2 = i;
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Float valueOf = Float.valueOf(0.0f);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        int i11 = 0;
        int i12 = 0;
        while (i11 < arrayList2.size()) {
            if (i >= i2 || i < 0) {
                i3 = i2;
                while (i < 0) {
                    CalorieHourlyData calorieHourlyData = new CalorieHourlyData();
                    if (i == -1) {
                        arrayList4.add(Float.valueOf(decimalFormat.format(arrayList2.get(i11))));
                    } else {
                        arrayList4.add(Float.valueOf(0.0f));
                    }
                    Float valueOf2 = Float.valueOf(Float.parseFloat(decimalFormat.format(arrayList2.get(i11))));
                    calorieHourlyData.setDate(dailyCalorieData.getDate());
                    StringBuilder sb = new StringBuilder();
                    Locale locale2 = Locale.ENGLISH;
                    sb.append(String.format(locale2, "%02d", Integer.valueOf(i12)));
                    sb.append(":00:00");
                    calorieHourlyData.setStartHour(sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    i12++;
                    sb2.append(String.format(locale2, "%02d", Integer.valueOf(i12)));
                    sb2.append(":00:00");
                    calorieHourlyData.setEndHour(sb2.toString());
                    calorieHourlyData.setCodeValues(arrayList4);
                    calorieHourlyData.setCalorieValue(valueOf2);
                    arrayList3.add(calorieHourlyData);
                    i++;
                    arrayList4 = new ArrayList();
                    valueOf = Float.valueOf(0.0f);
                }
                z2 = false;
                z3 = false;
                if (i == 0) {
                    i = i3;
                }
            } else {
                arrayList4.add(Float.valueOf(decimalFormat.format(arrayList2.get(i11))));
                Float valueOf3 = Float.valueOf(valueOf.floatValue() + Float.parseFloat(decimalFormat.format(arrayList2.get(i11))));
                i++;
                if (i == i2) {
                    CalorieHourlyData calorieHourlyData2 = new CalorieHourlyData();
                    calorieHourlyData2.setDate(dailyCalorieData.getDate());
                    StringBuilder sb3 = new StringBuilder();
                    i3 = i2;
                    sb3.append(String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i12)));
                    sb3.append(":00:00");
                    calorieHourlyData2.setStartHour(sb3.toString());
                    i12++;
                    if (i12 == 24) {
                        calorieHourlyData2.setEndHour(String.format(locale, "%02d", 0) + ":00:00");
                    } else {
                        calorieHourlyData2.setEndHour(String.format(locale, "%02d", Integer.valueOf(i12)) + ":00:00");
                    }
                    calorieHourlyData2.setCodeValues(arrayList4);
                    calorieHourlyData2.setCalorieValue(valueOf3);
                    arrayList3.add(calorieHourlyData2);
                    ArrayList arrayList5 = new ArrayList();
                    valueOf = Float.valueOf(0.0f);
                    z3 = false;
                    arrayList4 = arrayList5;
                    z2 = false;
                    i = 0;
                } else {
                    i3 = i2;
                    valueOf = valueOf3;
                    z3 = z;
                    z2 = false;
                }
            }
            i11++;
            z = z3;
            i2 = i3;
        }
        dailyCalorieData.setHourlyDataList(arrayList3);
        LogHelper.d("calorie", String.valueOf(dailyCalorieData));
        return dailyCalorieData;
    }

    public DailyCalorieData getCalorieData() {
        return c();
    }

    public boolean isSuccess() {
        return b();
    }
}
