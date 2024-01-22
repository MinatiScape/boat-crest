package com.apex.bluetooth.utils;

import com.apex.bluetooth.model.EABleAgps;
import com.htsmart.wristband2.a.a.a;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes.dex */
public class AgpsUtils {
    public final String TAG = AgpsUtils.class.getSimpleName();

    private byte[] packageAltitudeReference(double d) {
        byte[] bArr = new byte[6];
        if (d < 0.0d) {
            bArr[0] = 0;
        } else {
            bArr[0] = 16;
        }
        int floor = (int) Math.floor(Math.abs(d));
        bArr[1] = (byte) (floor >> 16);
        bArr[2] = (byte) (floor >> 8);
        bArr[3] = (byte) floor;
        short abs = (short) ((Math.abs(d) % 1.0d) * Math.pow(10.0d, 4.0d));
        bArr[4] = (byte) (abs >> 8);
        bArr[5] = (byte) abs;
        return bArr;
    }

    private byte[] packageReference(double d) {
        byte[] bArr = new byte[6];
        if (d < 0.0d) {
            bArr[0] = 0;
        } else {
            bArr[0] = 16;
        }
        bArr[1] = (byte) Math.floor(Math.abs(d));
        int abs = (int) ((Math.abs(d) % 1.0d) * Math.pow(10.0d, 6.0d));
        bArr[2] = (byte) (abs >> 24);
        bArr[3] = (byte) (abs >> 16);
        bArr[4] = (byte) (abs >> 8);
        bArr[5] = (byte) abs;
        return bArr;
    }

    private byte[] packageStructure(short s, int i, byte b, int i2) {
        return new byte[]{(byte) (s >> 8), (byte) s, (byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i, b, (byte) (i2 >> 24), (byte) (i2 >> 16), (byte) (i2 >> 8), (byte) i2};
    }

    public byte[] packageAgpsFile(List<EABleAgps> list, double d, double d2, double d3) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i = 0;
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        byte[] bArr5 = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).getAgpsData() != null && list.get(i2).getAgpsType() != null) {
                if (list.get(i2).getAgpsType() == EABleAgps.AgpsType.gps) {
                    bArr = list.get(i2).getAgpsData();
                    j = list.get(i2).getAgpsData().length;
                } else if (list.get(i2).getAgpsType() == EABleAgps.AgpsType.galileo) {
                    bArr3 = list.get(i2).getAgpsData();
                    j2 = list.get(i2).getAgpsData().length;
                } else if (list.get(i2).getAgpsType() == EABleAgps.AgpsType.beidou) {
                    bArr2 = list.get(i2).getAgpsData();
                    i = list.get(i2).getAgpsData().length;
                } else if (list.get(i2).getAgpsType() == EABleAgps.AgpsType.glonass) {
                    bArr4 = list.get(i2).getAgpsData();
                    j3 = list.get(i2).getAgpsData().length;
                } else if (list.get(i2).getAgpsType() == EABleAgps.AgpsType.qzss) {
                    bArr5 = list.get(i2).getAgpsData();
                    j4 = list.get(i2).getAgpsData().length;
                }
            }
        }
        long j5 = i + j;
        byte[] bArr6 = bArr;
        byte[] bArr7 = bArr2;
        long j6 = j5 + j2;
        long j7 = j6 + j3;
        int i3 = (int) (j7 + j4);
        if (i3 <= 0) {
            return null;
        }
        int i4 = i3 + 85;
        int timeInMillis = (int) (Calendar.getInstance().getTimeInMillis() / 1000);
        byte[] bArr8 = {(byte) (timeInMillis >> 24), (byte) (timeInMillis >> 16), (byte) (timeInMillis >> 8), (byte) timeInMillis};
        byte[] packageReference = packageReference(d);
        byte[] packageReference2 = packageReference(d2);
        byte[] packageAltitudeReference = packageAltitudeReference(d3);
        int i5 = (int) j;
        byte[] packageStructure = packageStructure((short) i5, timeInMillis, (byte) 16, 83);
        int i6 = i5 + 83;
        byte[] packageStructure2 = packageStructure((short) i, timeInMillis, (byte) 48, i6);
        int i7 = ((int) j5) + 83;
        byte[] packageStructure3 = packageStructure((short) j2, timeInMillis, (byte) 32, i7);
        int i8 = ((int) j6) + 83;
        byte[] packageStructure4 = packageStructure((short) j3, timeInMillis, (byte) 64, i8);
        int i9 = ((int) j7) + 83;
        byte[] packageStructure5 = packageStructure((short) j4, timeInMillis, a.d1, i9);
        byte[] bArr9 = new byte[i4];
        bArr9[0] = -2;
        bArr9[1] = -2;
        System.arraycopy(new byte[]{(byte) (i4 >> 24), (byte) (i4 >> 16), (byte) (i4 >> 8), (byte) i4}, 0, bArr9, 2, 4);
        System.arraycopy(bArr8, 0, bArr9, 6, 4);
        System.arraycopy(packageReference, 0, bArr9, 10, packageReference.length);
        System.arraycopy(packageReference2, 0, bArr9, 16, packageReference2.length);
        System.arraycopy(packageAltitudeReference, 0, bArr9, 22, packageAltitudeReference.length);
        System.arraycopy(packageStructure, 0, bArr9, 28, packageStructure.length);
        System.arraycopy(packageStructure2, 0, bArr9, 39, packageStructure2.length);
        System.arraycopy(packageStructure3, 0, bArr9, 50, packageStructure3.length);
        System.arraycopy(packageStructure4, 0, bArr9, 61, packageStructure4.length);
        System.arraycopy(packageStructure5, 0, bArr9, 72, packageStructure5.length);
        if (bArr6 != null) {
            System.arraycopy(bArr6, 0, bArr9, 83, bArr6.length);
        }
        if (bArr7 != null) {
            System.arraycopy(bArr7, 0, bArr9, i6, bArr7.length);
        }
        byte[] bArr10 = bArr3;
        if (bArr10 != null) {
            System.arraycopy(bArr10, 0, bArr9, i7, bArr10.length);
        }
        byte[] bArr11 = bArr4;
        if (bArr11 != null) {
            System.arraycopy(bArr11, 0, bArr9, i8, bArr11.length);
        }
        byte[] bArr12 = bArr5;
        if (bArr12 != null) {
            System.arraycopy(bArr12, 0, bArr9, i9, bArr12.length);
        }
        bArr9[i3 + 83] = -3;
        bArr9[i3 + 84] = -3;
        return bArr9;
    }
}
