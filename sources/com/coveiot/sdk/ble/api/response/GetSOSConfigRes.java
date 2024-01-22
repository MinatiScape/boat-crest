package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleGetSOSConfig;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class GetSOSConfigRes extends BaseResponse implements Serializable {
    private static final String f = GetSOSConfigRes.class.getSimpleName();
    private BleGetSOSConfig e;

    public GetSOSConfigRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    private void a() {
        int i;
        int i2;
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                i = 4;
                i2 = 0;
                if (!it.hasNext()) {
                    break;
                }
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                        Byte.parseByte(split[0]);
                        int length = split.length;
                        for (int i3 = 12; i3 < length; i3++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i3].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        while (i < length2) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i].trim())));
                            i++;
                        }
                    }
                } catch (Exception e) {
                    LogHelper.d(f, e.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            byte byteValue = ((Byte) arrayList.get(1)).byteValue();
            Boolean bool = Boolean.FALSE;
            if (byteValue == 1) {
                bool = Boolean.TRUE;
            }
            byte byteValue2 = ((Byte) arrayList.get(2)).byteValue();
            int byteValue3 = ((Byte) arrayList.get(3)).byteValue();
            byte[] bArr = new byte[byteValue3];
            int i4 = 0;
            while (i4 < byteValue3) {
                bArr[i4] = ((Byte) arrayList.get(i)).byteValue();
                i4++;
                i++;
            }
            String str2 = new String(bArr, StandardCharsets.UTF_8);
            int i5 = i + 1;
            int byteValue4 = ((Byte) arrayList.get(i)).byteValue();
            byte[] bArr2 = new byte[byteValue4];
            while (i2 < byteValue4) {
                bArr2[i2] = ((Byte) arrayList.get(i5)).byteValue();
                i2++;
                i5++;
            }
            BleGetSOSConfig bleGetSOSConfig = new BleGetSOSConfig(bool.booleanValue(), byteValue2, byteValue3, str2, byteValue4, new String(bArr2, StandardCharsets.UTF_8));
            bleGetSOSConfig.toString();
            this.e = bleGetSOSConfig;
        }
    }

    public BleGetSOSConfig getBleGetSOSConfig() {
        a();
        return this.e;
    }
}
