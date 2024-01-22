package com.crrepa.ble.scan;

import com.crrepa.ble.scan.bean.CRPScanRecordInfo;
import com.jstyle.blesdk1860.constant.BleConst;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes9.dex */
public class CRPScanRecordParser {
    public static CRPScanRecordInfo parseScanRecord(byte[] bArr) {
        byte b;
        if (bArr == null) {
            return null;
        }
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (order.remaining() > 2 && (b = order.get()) != 0) {
            int i = (byte) (b - 1);
            if (order.get() == 22) {
                if (i < 8) {
                    return null;
                }
                byte[] bArr2 = new byte[i];
                order.get(bArr2, 0, i);
                byte[] bArr3 = new byte[3];
                System.arraycopy(bArr2, 2, bArr3, 0, 3);
                String str = new String(bArr3);
                str.replace(BleConst.GetDeviceTime, "");
                byte[] bArr4 = new byte[1];
                System.arraycopy(bArr2, 5, bArr4, 0, 1);
                byte b2 = bArr4[0];
                byte[] bArr5 = new byte[1];
                System.arraycopy(bArr2, 6, bArr5, 0, 1);
                byte b3 = bArr5[0];
                int i2 = i - 7;
                byte[] bArr6 = new byte[i2];
                System.arraycopy(bArr2, 7, bArr6, 0, i2);
                byte b4 = 1 < i2 ? bArr6[1] : bArr6[0];
                CRPScanRecordInfo cRPScanRecordInfo = new CRPScanRecordInfo();
                cRPScanRecordInfo.setFirmwareType(str);
                cRPScanRecordInfo.setPlatform(CRPScanRecordInfo.McuPlatform.getInstance(b2));
                cRPScanRecordInfo.setChipId(b3);
                cRPScanRecordInfo.setFunction(CRPScanRecordInfo.BandFunction.getInstance(b4));
                return cRPScanRecordInfo;
            }
            int position = order.position() + i;
            if (i <= 0 || position >= order.limit()) {
                return null;
            }
            order.position(position);
        }
        return null;
    }
}
