package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleQuickReply;
import com.coveiot.sdk.ble.api.model.QuickReply;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetQuickReplyListRes extends BaseResponse {
    public BleQuickReply e;

    public GetQuickReplyListRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public BleQuickReply getBleQuickReply() {
        processResponse();
        LogHelper.i("Quick Reply List : ", this.e.toString());
        return this.e;
    }

    public void processResponse() {
        int i;
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if (((Byte.parseByte(split[2].trim()) & 255) | ((Byte.parseByte(split[3].trim()) & 255) << 8)) == 0) {
                        Byte.parseByte(split[0]);
                        int length = split.length;
                        for (int i2 = 12; i2 < length; i2++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        for (int i3 = 4; i3 < length2; i3++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i3].trim())));
                        }
                    }
                } catch (Exception e) {
                    LogHelper.d("Exception", e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            boolean z = ((Byte) arrayList.get(0)).byteValue() == 1;
            byte byteValue = ((Byte) arrayList.get(1)).byteValue();
            ArrayList arrayList2 = new ArrayList();
            int i4 = 1;
            for (int i5 = 0; i5 < byteValue; i5++) {
                int i6 = i4 + 1;
                byte byteValue2 = ((Byte) arrayList.get(i6)).byteValue();
                int i7 = i6 + 1;
                ArrayList arrayList3 = new ArrayList();
                int i8 = i7;
                while (true) {
                    i = byteValue2 + i7;
                    if (i8 >= i) {
                        break;
                    }
                    arrayList3.add(arrayList.get(i8));
                    i8++;
                }
                byte[] bArr = new byte[arrayList3.size()];
                int i9 = -1;
                for (int i10 = 0; i10 < arrayList3.size(); i10++) {
                    i9++;
                    bArr[i9] = ((Byte) arrayList3.get(i10)).byteValue();
                }
                arrayList2.add(new QuickReply(byteValue2, new String(bArr, StandardCharsets.UTF_16LE)));
                i4 = i - 1;
            }
            this.e = new BleQuickReply(z, byteValue, arrayList2);
        }
    }
}
