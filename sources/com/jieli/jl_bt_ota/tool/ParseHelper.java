package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.jieli.jl_bt_ota.constant.Command;
import com.jieli.jl_bt_ota.interfaces.command.ICmdHandler;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.cmdHandler.RcspCmdHandler;
import com.jieli.jl_bt_ota.model.parameter.tws.NotifyAdvInfoParam;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class ParseHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12371a = "ParseHelper";
    private static final byte b = -2;
    private static final byte c = -36;
    private static final byte d = -70;
    private static final byte e = -17;
    private static byte[] g;
    private static int h;
    private static final byte[] f = {-2, -36, -70};
    private static final char[] i = "0123456789ABCDEF".toCharArray();

    private static int a(byte[] bArr, int i2, int i3) {
        int length = bArr.length;
        while (i2 < length) {
            if (bArr[i2] == -2) {
                int i4 = length - i2;
                byte[] bArr2 = f;
                if (i4 < bArr2.length) {
                    b(bArr, i2, i4);
                    return -1;
                }
                int length2 = bArr2.length;
                byte[] bArr3 = new byte[length2];
                System.arraycopy(bArr, i2, bArr3, 0, length2);
                if (!Arrays.equals(bArr3, bArr2)) {
                    continue;
                } else if (i4 <= bArr2.length + 4) {
                    b(bArr, i2, i4);
                    return -1;
                } else {
                    int length3 = bArr2.length + i2;
                    byte[] bArr4 = new byte[2];
                    System.arraycopy(bArr, length3 + 2, bArr4, 0, 2);
                    int bytesToInt = CHexConver.bytesToInt(bArr4[0], bArr4[1]);
                    if (bytesToInt > i3 - 8) {
                        JL_Log.e(f12371a, String.format(Locale.getDefault(), "findPacketData :: data length[%d] over MAX_RECEIVE_MTU[%d], cast away", Integer.valueOf(bytesToInt), Integer.valueOf(i3)));
                    } else if (i4 <= bArr2.length + 4 + bytesToInt) {
                        int i5 = length - length3;
                        byte[] bArr5 = new byte[i5];
                        System.arraycopy(bArr, length3, bArr5, 0, i5);
                        int a2 = a(bArr5, 0, i3);
                        String str = f12371a;
                        JL_Log.i(str, "findValidRcspHeadIndex : data not enough, check left data, index = " + a2);
                        if (a2 < bArr2.length) {
                            b(bArr, i2, i4);
                            return -1;
                        }
                        int i6 = length3 + a2;
                        JL_Log.w(str, "findValidRcspHeadIndex : found headIndex = " + i6);
                        return i6;
                    } else if (bArr[length3 + 4 + bytesToInt] == -17) {
                        return length3;
                    }
                    i2 = length3 - 1;
                }
            }
            i2++;
        }
        return -1;
    }

    private static byte[] b(byte[] bArr) {
        int length = bArr.length;
        int i2 = h;
        if (i2 > 0) {
            byte[] bArr2 = new byte[i2 + length];
            System.arraycopy(g, 0, bArr2, 0, i2);
            System.arraycopy(bArr, 0, bArr2, h, length);
            h = 0;
            return bArr2;
        }
        return (byte[]) bArr.clone();
    }

    private static BasePacket c(byte[] bArr) {
        if (bArr != null) {
            int i2 = 4;
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
                        i2 = 5;
                    }
                    basePacket.setOpCodeSn(CHexConver.byteToInt(bArr[i2]));
                    int i3 = i2 + 1;
                    if (byteToInt == 1) {
                        basePacket.setXmOpCode(CHexConver.byteToInt(bArr[i3]));
                        i3++;
                    }
                    int i4 = bytesToInt - (i3 - 4);
                    byte[] bArr2 = new byte[i4];
                    System.arraycopy(bArr, i3, bArr2, 0, i4);
                    basePacket.setParamData(bArr2);
                    JL_Log.d(f12371a, String.format(Locale.getDefault(), "-parsePacketData- packet type : %d, opCode : %d, sn :%d", Integer.valueOf(basePacket.getType()), Integer.valueOf(basePacket.getOpCode()), Integer.valueOf(basePacket.getOpCodeSn())));
                    return basePacket;
                }
                return basePacket;
            }
        }
        return null;
    }

    public static BasePacket convert2BasePacket(CommandBase commandBase, int i2) {
        if (commandBase == null) {
            return null;
        }
        int i3 = 0;
        boolean z = i2 == 1;
        int type = commandBase.getType();
        if (z && (type == 2 || type == 3)) {
            i3 = 1;
        }
        BasePacket status = new BasePacket().setType(i2).setHasResponse(i3).setOpCode(commandBase.getId()).setOpCodeSn(commandBase.getOpCodeSn()).setStatus(commandBase.getStatus());
        int i4 = z ? 1 : 2;
        if (commandBase.getParam() != null) {
            if (status.getOpCode() == 1) {
                status.setXmOpCode(commandBase.getParam().getXmOpCode());
                i4++;
            }
            byte[] paramData = commandBase.getParam().getParamData();
            if (paramData != null && paramData.length > 0) {
                status.setParamData(paramData);
                i4 += paramData.length;
            }
        }
        status.setParamLen(i4);
        return status;
    }

    public static CommandBase convert2Command(BasePacket basePacket, CommandBase commandBase) {
        if (basePacket != null) {
            CommandBase a2 = a(basePacket, commandBase);
            return a2 != null ? a2 : new RcspCmdHandler().parseDataToCmd(basePacket, commandBase);
        }
        return null;
    }

    public static int convertVersionByString(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        JL_Log.i(f12371a, "convertVersionByString :: version = " + str);
        String[] split = str.replace(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "").replace(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "").split("\\.");
        int length = split.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < split.length; i2++) {
            String str2 = split[i2];
            if (TextUtils.isDigitsOnly(str2)) {
                iArr[i2] = Integer.parseInt(str2);
            }
        }
        if (length == 4) {
            byte[] booleanArray = CHexConver.getBooleanArray((byte) iArr[0]);
            byte[] booleanArray2 = CHexConver.getBooleanArray((byte) iArr[1]);
            byte[] bArr = new byte[8];
            System.arraycopy(booleanArray, 4, bArr, 0, 4);
            System.arraycopy(booleanArray2, 4, bArr, 4, 4);
            byte[] booleanArray3 = CHexConver.getBooleanArray((byte) iArr[2]);
            byte[] booleanArray4 = CHexConver.getBooleanArray((byte) iArr[3]);
            byte[] bArr2 = new byte[8];
            System.arraycopy(booleanArray3, 4, bArr2, 0, 4);
            System.arraycopy(booleanArray4, 4, bArr2, 4, 4);
            JL_Log.i(f12371a, "convertVersionByString :: versionCode : 0, heightValue : " + CHexConver.byte2HexStr(bArr) + ", lowValue : " + CHexConver.byte2HexStr(bArr2));
            return CHexConver.bytesToInt((byte) a(bArr), (byte) a(bArr2));
        }
        return 0;
    }

    public static ArrayList<BasePacket> findPacketData(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
        return findPacketData(i2, bArr);
    }

    public static String hexDataCovetToAddress(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length == 6) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                char[] cArr = i;
                sb.append(cArr[(bArr[i2] & 255) >> 4]);
                sb.append(cArr[bArr[i2] & 15]);
                if (i2 != bArr.length - 1) {
                    sb.append(":");
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] packSendBasePacket(com.jieli.jl_bt_ota.model.base.BasePacket r16) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.tool.ParseHelper.packSendBasePacket(com.jieli.jl_bt_ota.model.base.BasePacket):byte[]");
    }

    public static void parseNotifyADVInfo(NotifyAdvInfoParam notifyAdvInfoParam, BasePacket basePacket) {
        byte[] paramData;
        if (basePacket == null || (paramData = basePacket.getParamData()) == null || paramData.length < 18) {
            return;
        }
        byte[] bArr = new byte[2];
        System.arraycopy(paramData, 0, bArr, 0, 2);
        int bytesToInt = CHexConver.bytesToInt(bArr[0], bArr[1]);
        System.arraycopy(paramData, 2, bArr, 0, 2);
        int bytesToInt2 = CHexConver.bytesToInt(bArr[0], bArr[1]);
        System.arraycopy(paramData, 4, bArr, 0, 2);
        int bytesToInt3 = CHexConver.bytesToInt(bArr[0], bArr[1]);
        byte b2 = paramData[6];
        byte[] bArr2 = new byte[6];
        System.arraycopy(paramData, 7, bArr2, 0, 6);
        String hexDataCovetToAddress = hexDataCovetToAddress(bArr2);
        int byteToInt = CHexConver.byteToInt(paramData[13]);
        byte b3 = paramData[14];
        int i2 = (b3 >> 7) & 1;
        int i3 = b3 & Byte.MAX_VALUE;
        byte b4 = paramData[15];
        int i4 = (b4 >> 7) & 1;
        int i5 = b4 & Byte.MAX_VALUE;
        byte b5 = paramData[16];
        int i6 = (b5 >> 7) & 1;
        notifyAdvInfoParam.setVid(bytesToInt).setUid(bytesToInt2).setPid(bytesToInt3).setDeviceType((b2 >> 4) & 255).setVersion(b2 & 15).setEdrAddr(hexDataCovetToAddress).setAction(byteToInt).setLeftCharging(i2 == 1).setLeftDeviceQuantity(i3).setRightCharging(i4 == 1).setRightDeviceQuantity(i5).setDeviceCharging(i6 == 1).setChargingBinQuantity(b5 & Byte.MAX_VALUE).setSeq(CHexConver.byteToInt(paramData[17]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:0x0543, code lost:
        com.jieli.jl_bt_ota.util.JL_Log.e(com.jieli.jl_bt_ota.tool.ParseHelper.f12371a, java.lang.String.format(java.util.Locale.getDefault(), "parseTargetInfo :: data length[%d] over paramDataLen[%d], cast away", java.lang.Integer.valueOf(r6), java.lang.Integer.valueOf(r3)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0560, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parseTargetInfo(com.jieli.jl_bt_ota.model.response.TargetInfoResponse r16, com.jieli.jl_bt_ota.model.base.BasePacket r17) {
        /*
            Method dump skipped, instructions count: 1404
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.tool.ParseHelper.parseTargetInfo(com.jieli.jl_bt_ota.model.response.TargetInfoResponse, com.jieli.jl_bt_ota.model.base.BasePacket):void");
    }

    public static ArrayList<BasePacket> findPacketData(int i2, byte[] bArr) {
        if (i2 == 0 || bArr == null || bArr.length == 0) {
            return null;
        }
        ArrayList<BasePacket> arrayList = new ArrayList<>();
        byte[] b2 = b(bArr);
        int length = b2.length;
        String str = f12371a;
        JL_Log.d(str, "findPacketData : data : " + CHexConver.byte2HexStr(b2));
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            int a2 = a(b2, i3, i2);
            if (a2 < f.length) {
                JL_Log.w(f12371a, "-findPacketData- not find head data : ");
                break;
            }
            String str2 = f12371a;
            JL_Log.i(str2, "-findPacketData- prefixIndex = " + a2);
            int bytesToInt = CHexConver.bytesToInt(b2, a2 + 2, 2);
            int i4 = bytesToInt + 4;
            byte[] bArr2 = new byte[i4];
            System.arraycopy(b2, a2, bArr2, 0, i4);
            BasePacket c2 = c(bArr2);
            if (c2 != null) {
                arrayList.add(c2);
            }
            i3 = a2 + 4 + bytesToInt + 1;
        }
        return arrayList;
    }

    private static void b(byte[] bArr, int i2, int i3) {
        if (bArr == null || bArr.length <= 0 || i2 < 0 || i3 <= 0 || i2 + i3 > bArr.length) {
            return;
        }
        byte[] bArr2 = new byte[i3];
        g = bArr2;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        h = i3;
    }

    private static CommandBase a(BasePacket basePacket, CommandBase commandBase) {
        Map<Integer, ICmdHandler> validCommandList;
        ICmdHandler iCmdHandler;
        if (basePacket == null || (validCommandList = Command.getValidCommandList()) == null || (iCmdHandler = validCommandList.get(Integer.valueOf(basePacket.getOpCode()))) == null) {
            return null;
        }
        return iCmdHandler.parseDataToCmd(basePacket, commandBase);
    }

    private static int a(byte[] bArr) {
        if (bArr == null || bArr.length != 8) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(b2 & 255);
        }
        try {
            return Integer.valueOf(sb.toString(), 2).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
