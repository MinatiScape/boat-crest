package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleGetButtonFunctionData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetButtonFunctionRes extends BaseResponse {
    public BleGetButtonFunctionData e;

    public GetButtonFunctionRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleGetButtonFunctionData getBleButtonFunctionData() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                BleGetButtonFunctionData bleGetButtonFunctionData = new BleGetButtonFunctionData();
                bleGetButtonFunctionData.setVersionNumber(Byte.parseByte(split[5].trim()) & 255);
                bleGetButtonFunctionData.setPosition(Byte.parseByte(split[6].trim()) & 255);
                bleGetButtonFunctionData.setShortPress(Byte.parseByte(split[7].trim()) & 255);
                bleGetButtonFunctionData.setLongPress(Byte.parseByte(split[8].trim()) & 255);
                this.e = bleGetButtonFunctionData;
                return bleGetButtonFunctionData;
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        this.e.toString();
        return null;
    }
}
