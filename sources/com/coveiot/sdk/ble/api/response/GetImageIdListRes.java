package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetImageIdListRes extends BaseResponse {
    public List<Integer> e;

    public GetImageIdListRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public List<Integer> getImageIdList() {
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
                    LogHelper.d("ContentValues", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            byte byteValue = ((Byte) arrayList.get(0)).byteValue();
            ArrayList arrayList2 = new ArrayList();
            int i3 = 0;
            int i4 = 0;
            while (i3 < byteValue) {
                int i5 = i4 + 2;
                arrayList2.add(Integer.valueOf(ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList.get(i5)).byteValue(), ((Byte) arrayList.get(i4 + 1)).byteValue()}).getInt()));
                i3++;
                i4 = i5;
            }
            this.e = (List) arrayList2.clone();
        }
    }
}
