package com.coveiot.sdk.ble.api.response;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.LiveEcgData;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class RawEcgRes extends BaseResponse {
    public RawEcgRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    @Nullable
    public final LiveEcgData b() {
        ArrayList arrayList = new ArrayList();
        ArrayList<String> dataList = this.c.getDataList();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(dataList);
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
            int length = split.length;
            for (int i = 4; i < length; i++) {
                arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
            }
        }
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i2 = 0; i2 < arrayList.size() - 1; i2 += 2) {
            arrayList2.add(Integer.valueOf(ByteBuffer.wrap(new byte[]{((Byte) arrayList.get(i2 + 1)).byteValue(), ((Byte) arrayList.get(i2)).byteValue()}).getShort()));
        }
        LogHelper.d("RawEcgRes", "max: " + arrayList2, ModuleNames.BLEABSTRACT.getModuleName());
        LiveEcgData liveEcgData = new LiveEcgData();
        liveEcgData.setLiveEcg(arrayList2);
        return liveEcgData;
    }

    public LiveEcgData getRawECGData() {
        return b();
    }
}
