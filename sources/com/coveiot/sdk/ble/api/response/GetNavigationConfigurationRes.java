package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleNavigationConfigurationData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetNavigationConfigurationRes extends BaseResponse {
    public BleNavigationConfigurationData e;

    public GetNavigationConfigurationRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleNavigationConfigurationData getBleNavigationConfigurationData() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                boolean z = true;
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                int parseByte = Byte.parseByte(split[5].trim()) & 255;
                int parseByte2 = Byte.parseByte(split[6].trim()) & 255;
                int parseByte3 = Byte.parseByte(split[7].trim()) & 255;
                BleNavigationConfigurationData bleNavigationConfigurationData = new BleNavigationConfigurationData();
                bleNavigationConfigurationData.setAudioEnabled(parseByte == 1);
                bleNavigationConfigurationData.setHapticEnabled(parseByte2 == 1);
                if (parseByte3 != 1) {
                    z = false;
                }
                bleNavigationConfigurationData.setAODEnabled(z);
                this.e = bleNavigationConfigurationData;
                return bleNavigationConfigurationData;
            } catch (Exception e) {
                LogHelper.d("bleNavigationConfig", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        this.e.toString();
        return null;
    }
}
