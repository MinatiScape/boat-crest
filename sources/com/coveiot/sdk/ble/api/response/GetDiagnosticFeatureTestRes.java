package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleGetDiagnosticFeatureTest;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetDiagnosticFeatureTestRes extends BaseResponse {
    public GetDiagnosticFeatureTestRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleGetDiagnosticFeatureTest getBleGetDiagnosticFeatureTest() {
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            try {
                String str = (String) it.next();
                String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                BleGetDiagnosticFeatureTest bleGetDiagnosticFeatureTest = new BleGetDiagnosticFeatureTest();
                bleGetDiagnosticFeatureTest.setDeploy(Byte.parseByte(split[4].trim()) & 255);
                bleGetDiagnosticFeatureTest.setFeature(Byte.parseByte(split[5].trim()) & 255);
                bleGetDiagnosticFeatureTest.setStatus(Byte.parseByte(split[6].trim()) & 255);
                LogHelper.i("Ble Diagnostic Feature Test : ", bleGetDiagnosticFeatureTest.toString());
                return bleGetDiagnosticFeatureTest;
            } catch (Exception e) {
                LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        return null;
    }
}
