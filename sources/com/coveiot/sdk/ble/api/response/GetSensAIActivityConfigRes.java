package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleGetSensAIActivityConfig;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetSensAIActivityConfigRes extends BaseResponse {
    public BleGetSensAIActivityConfig e;

    public GetSensAIActivityConfigRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleGetSensAIActivityConfig getBleGetSensAIActivityConfig() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                int parseByte = Byte.parseByte(split[4].trim()) & 255;
                ArrayList arrayList = new ArrayList();
                for (int i = 5; i < split.length; i++) {
                    arrayList.add(split[i]);
                }
                BleGetSensAIActivityConfig bleGetSensAIActivityConfig = new BleGetSensAIActivityConfig();
                bleGetSensAIActivityConfig.setActivityNumber(parseByte);
                bleGetSensAIActivityConfig.setType(arrayList);
                this.e = bleGetSensAIActivityConfig;
                return bleGetSensAIActivityConfig;
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        LogHelper.i("Sens AI Activity Config : ", this.e.toString());
        return null;
    }
}
