package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleNavigationDisclaimerData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetNavigationDisclaimerRes extends BaseResponse {
    public GetNavigationDisclaimerRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleNavigationDisclaimerData getBleNavigationDisclaimerData() {
        BleNavigationDisclaimerData bleNavigationDisclaimerData = new BleNavigationDisclaimerData();
        if (this.c.getDataList() != null) {
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    ArrayList arrayList = new ArrayList();
                    int i = 5;
                    while (true) {
                        if (i >= split.length) {
                            i = 0;
                            break;
                        } else if (split[i].trim().equals(BleConst.GetDeviceTime)) {
                            break;
                        } else {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                            i++;
                        }
                    }
                    byte[] bArr = new byte[arrayList.size()];
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        bArr[i2] = ((Byte) arrayList.get(i2)).byteValue();
                    }
                    String str2 = new String(bArr, StandardCharsets.UTF_8);
                    Boolean valueOf = Boolean.valueOf(split[i + 1].trim().equals("1"));
                    if (valueOf.booleanValue()) {
                        int i3 = i + 2;
                        String[] strArr = new String[split.length - i3];
                        String[] strArr2 = (String[]) Arrays.copyOfRange(split, i3, split.length);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(1, Byte.parseByte(strArr2[0].trim()) & (Byte.parseByte(strArr2[1].trim()) + 25500) & 255);
                        calendar.set(2, (Byte.parseByte(strArr2[2].trim()) & 255) - 1);
                        calendar.set(5, Byte.parseByte(strArr2[3].trim()) & 255);
                        calendar.set(11, Byte.parseByte(strArr2[4].trim()) & 255);
                        calendar.set(12, Byte.parseByte(strArr2[5].trim()) & 255);
                        calendar.set(13, Byte.parseByte(strArr2[6].trim()) & 255);
                        int parseByte = Byte.parseByte(strArr2[8].trim()) & (Byte.parseByte(strArr2[9].trim()) + 15300) & 15300;
                        if (strArr2[7].trim().equals("-")) {
                            parseByte *= -1;
                        }
                        TimeZone timeZone = TimeZone.getTimeZone("GMT");
                        timeZone.setRawOffset(parseByte);
                        calendar.setTimeZone(timeZone);
                        bleNavigationDisclaimerData.setCalendar(calendar);
                    }
                    bleNavigationDisclaimerData.setVersionText(str2);
                    bleNavigationDisclaimerData.setUserAccepted(valueOf.booleanValue());
                    return bleNavigationDisclaimerData;
                } catch (Exception e) {
                    LogHelper.d("Exception", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            return null;
        }
        return null;
    }
}
