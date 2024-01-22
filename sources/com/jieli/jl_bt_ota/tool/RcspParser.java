package com.jieli.jl_bt_ota.tool;

import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
/* loaded from: classes11.dex */
public class RcspParser {

    /* renamed from: a  reason: collision with root package name */
    private final String f12381a = RcspParser.class.getSimpleName();
    private volatile byte[] b = new byte[0];
    private final byte[] c = {-2, -36, -70};

    private byte[] a(byte[] bArr) {
        int length = bArr.length;
        int length2 = this.b.length;
        if (length2 > 0) {
            byte[] bArr2 = new byte[length2 + length];
            System.arraycopy(this.b, 0, bArr2, 0, length2);
            System.arraycopy(bArr, 0, bArr2, length2, length);
            return bArr2;
        }
        return (byte[]) bArr.clone();
    }

    private BasePacket b(byte[] bArr) {
        if (bArr != null) {
            int i = 4;
            if (bArr.length >= 4) {
                byte[] booleanArrayBig = CHexConver.getBooleanArrayBig(bArr[0]);
                int byteToInt = CHexConver.byteToInt(bArr[1]);
                int bytesToInt = CHexConver.bytesToInt(bArr, 2, 2);
                BasePacket basePacket = new BasePacket();
                int byteToInt2 = CHexConver.byteToInt(booleanArrayBig[7]);
                int byteToInt3 = CHexConver.byteToInt(booleanArrayBig[6]);
                basePacket.setType(byteToInt2);
                basePacket.setHasResponse(byteToInt3);
                basePacket.setOpCode(byteToInt);
                basePacket.setParamLen(bytesToInt);
                if (bytesToInt > 0) {
                    if (byteToInt2 == 0) {
                        basePacket.setStatus(CHexConver.byteToInt(bArr[4]));
                        i = 5;
                    }
                    basePacket.setOpCodeSn(CHexConver.byteToInt(bArr[i]));
                    int i2 = i + 1;
                    if (byteToInt == 1) {
                        basePacket.setXmOpCode(CHexConver.byteToInt(bArr[i2]));
                        i2++;
                    }
                    int i3 = bytesToInt - (i2 - 4);
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, i2, bArr2, 0, i3);
                    basePacket.setParamData(bArr2);
                    JL_Log.d(this.f12381a, String.format(Locale.getDefault(), "-parsePacketData- packet type : %d, opCode : %d, sn :%d", Integer.valueOf(basePacket.getType()), Integer.valueOf(basePacket.getOpCode()), Integer.valueOf(basePacket.getOpCodeSn())));
                    return basePacket;
                }
                return basePacket;
            }
        }
        return null;
    }

    public ArrayList<BasePacket> findPacketData(int i, byte[] bArr) {
        if (i == 0 || bArr == null || bArr.length == 0) {
            return null;
        }
        ArrayList<BasePacket> arrayList = new ArrayList<>();
        byte[] a2 = a(bArr);
        int length = a2.length;
        String str = this.f12381a;
        JL_Log.i(str, "-findPacketData- mtu = " + i);
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            int a3 = a(a2, i2, i);
            if (a3 < this.c.length) {
                JL_Log.w(this.f12381a, "-findPacketData- not find head data : ");
                break;
            }
            String str2 = this.f12381a;
            JL_Log.i(str2, "-findPacketData- prefixIndex = " + a3);
            int bytesToInt = CHexConver.bytesToInt(a2, a3 + 2, 2);
            int i3 = bytesToInt + 4;
            byte[] bArr2 = new byte[i3];
            System.arraycopy(a2, a3, bArr2, 0, i3);
            BasePacket b = b(bArr2);
            if (b != null) {
                arrayList.add(b);
            }
            i2 = a3 + 4 + bytesToInt + 1;
        }
        return arrayList;
    }

    public void release() {
        this.b = new byte[0];
    }

    private int a(byte[] bArr, int i, int i2) {
        int length = bArr.length;
        while (i < length) {
            if (bArr[i] == -2) {
                int i3 = length - i;
                byte[] bArr2 = this.c;
                if (i3 < bArr2.length) {
                    b(bArr, i, i3);
                    return -1;
                }
                int length2 = bArr2.length;
                byte[] bArr3 = new byte[length2];
                System.arraycopy(bArr, i, bArr3, 0, length2);
                if (Arrays.equals(bArr3, this.c)) {
                    byte[] bArr4 = this.c;
                    if (i3 <= bArr4.length + 4) {
                        b(bArr, i, i3);
                        return -1;
                    }
                    int length3 = bArr4.length + i;
                    byte[] bArr5 = new byte[2];
                    System.arraycopy(bArr, length3 + 2, bArr5, 0, 2);
                    int bytesToInt = CHexConver.bytesToInt(bArr5[0], bArr5[1]);
                    if (bytesToInt > i2 - 8) {
                        JL_Log.e(this.f12381a, String.format(Locale.getDefault(), "findPacketData :: data length[%d] over MAX_RECEIVE_MTU[%d], cast away", Integer.valueOf(bytesToInt), Integer.valueOf(i2)));
                    } else if (i3 <= this.c.length + 4 + bytesToInt) {
                        b(bArr, i, i3);
                        return -1;
                    } else if (bArr[length3 + 4 + bytesToInt] == -17) {
                        return length3;
                    }
                    i = length3 - 1;
                } else {
                    continue;
                }
            }
            i++;
        }
        return -1;
    }

    private void b(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length <= 0 || i < 0 || i2 <= 0 || i + i2 > bArr.length) {
            return;
        }
        this.b = new byte[i2];
        System.arraycopy(bArr, i, this.b, 0, i2);
    }
}
