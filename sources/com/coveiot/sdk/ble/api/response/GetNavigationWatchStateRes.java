package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.NavigationWatchStateData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetNavigationWatchStateRes extends BaseResponse {
    public NavigationWatchStateData e;

    public GetNavigationWatchStateRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public NavigationWatchStateData getNavigationWatchStateData() {
        if (this.c.getDataList() != null) {
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    this.e = new NavigationWatchStateData();
                    if (split[5].trim().equals("2")) {
                        this.e.setStarted(Boolean.FALSE);
                    } else {
                        this.e.setStarted(Boolean.TRUE);
                        int parseByte = Byte.parseByte(split[6].trim()) & 255;
                        String[] strArr = (String[]) Arrays.copyOfRange(split, 7, parseByte + 7);
                        byte[] bArr = new byte[strArr.length];
                        for (int i = 0; i < strArr.length; i++) {
                            bArr[i] = Byte.parseByte(strArr[i].trim());
                        }
                        this.e.setSource(new String(bArr, StandardCharsets.UTF_16LE));
                        int i2 = parseByte + 6 + 1;
                        int parseByte2 = Byte.parseByte(split[i2].trim()) & 255;
                        int i3 = i2 + 1;
                        String[] strArr2 = (String[]) Arrays.copyOfRange(split, i3, i3 + parseByte2);
                        byte[] bArr2 = new byte[strArr2.length];
                        for (int i4 = 0; i4 < strArr2.length; i4++) {
                            bArr2[i4] = Byte.parseByte(strArr2[i4].trim());
                        }
                        this.e.setDestination(new String(bArr2, StandardCharsets.UTF_16LE));
                        this.e.setMode(Byte.parseByte(split[i2 + parseByte2 + 1].trim()) & 255);
                    }
                } catch (Exception e) {
                    LogHelper.d("Exception", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
        }
        return this.e;
    }
}
