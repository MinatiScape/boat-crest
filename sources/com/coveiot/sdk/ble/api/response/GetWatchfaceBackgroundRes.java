package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleWatchFaceBackgroundInfo;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetWatchfaceBackgroundRes extends BaseResponse {
    public static final String f = "GetWatchfaceBackgroundRes";
    public ArrayList<BleWatchFaceBackgroundInfo> e;

    public GetWatchfaceBackgroundRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public ArrayList<BleWatchFaceBackgroundInfo> getBleWatchFaceBackgroundInfos() {
        processResponse();
        return this.e;
    }

    public final void processResponse() {
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
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
                        for (int i = 12; i < length; i++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        for (int i2 = 4; i2 < length2; i2++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                        }
                    }
                } catch (Exception e) {
                    LogHelper.d(f, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            this.e = new ArrayList<>();
            for (int i3 = 1; i3 < arrayList.size(); i3 += 4) {
                this.e.add(new BleWatchFaceBackgroundInfo(ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList.get(i3 + 1)).byteValue(), ((Byte) arrayList.get(i3)).byteValue()}).getInt(), ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList.get(i3 + 3)).byteValue(), ((Byte) arrayList.get(i3 + 2)).byteValue()}).getInt()));
            }
        }
    }
}
