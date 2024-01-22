package com.jieli.jl_rcsp.tool.datahandles;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.jieli.jl_rcsp.constant.Command;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.cmdHandler.RcspCmdHandler;
import com.jieli.jl_rcsp.model.device.AttrBean;
import com.jieli.jl_rcsp.model.parameter.NotifyAdvInfoParam;
import com.jieli.jl_rcsp.model.response.ADVInfoResponse;
import com.jieli.jl_rcsp.model.response.SysInfoResponse;
import com.jieli.jl_rcsp.tool.DeviceStatusManager;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class ParseHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12503a = "ParseHelper";
    private static final char[] b = "0123456789ABCDEF".toCharArray();

    private static CommandBase a(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        Map<Integer, ICmdHandler> validCommandList;
        ICmdHandler iCmdHandler;
        if (basePacket == null || (validCommandList = Command.getValidCommandList()) == null || (iCmdHandler = validCommandList.get(Integer.valueOf(basePacket.getOpCode()))) == null) {
            return null;
        }
        return iCmdHandler.parseDataToCmd(bluetoothDevice, basePacket);
    }

    private static List<ADVInfoResponse.KeySettings> b(byte[] bArr) {
        int i;
        if (bArr == null || bArr.length < 3) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr2 = new byte[3];
        int i2 = 0;
        while (i2 < bArr.length && (i = i2 + 3) <= bArr.length) {
            System.arraycopy(bArr, i2, bArr2, 0, 3);
            ADVInfoResponse.KeySettings keySettings = new ADVInfoResponse.KeySettings();
            keySettings.setKeyNum(CHexConver.byteToInt(bArr2[0]));
            keySettings.setAction(CHexConver.byteToInt(bArr2[1]));
            keySettings.setFunction(CHexConver.byteToInt(bArr2[2]));
            arrayList.add(keySettings);
            i2 = i;
        }
        return arrayList;
    }

    private static List<ADVInfoResponse.LedSettings> c(byte[] bArr) {
        int i;
        ArrayList arrayList = null;
        if (bArr != null && bArr.length >= 2) {
            byte[] bArr2 = new byte[2];
            int i2 = 0;
            while (i2 < bArr.length && (i = i2 + 2) <= bArr.length) {
                System.arraycopy(bArr, i2, bArr2, 0, 2);
                ADVInfoResponse.LedSettings ledSettings = new ADVInfoResponse.LedSettings();
                ledSettings.setScene(CHexConver.byteToInt(bArr2[0]));
                ledSettings.setEffect(CHexConver.byteToInt(bArr2[1]));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(ledSettings);
                i2 = i;
            }
        }
        return arrayList;
    }

    public static BasePacket convert2BasePacket(CommandBase commandBase, int i) {
        if (commandBase == null) {
            return null;
        }
        int i2 = 0;
        boolean z = i == 1;
        int type = commandBase.getType();
        if (z && (type == 2 || type == 3)) {
            i2 = 1;
        }
        BasePacket status = new BasePacket().setType(i).setHasResponse(i2).setOpCode(commandBase.getId()).setOpCodeSn(commandBase.getOpCodeSn()).setStatus(commandBase.getStatus());
        int i3 = z ? 1 : 2;
        if (commandBase.getParam() != null) {
            if (status.getOpCode() == 1) {
                status.setXmOpCode(commandBase.getParam().getXmOpCode());
                i3++;
            }
            byte[] paramData = commandBase.getParam().getParamData();
            if (paramData != null && paramData.length > 0) {
                status.setParamData(paramData);
                i3 += paramData.length;
            }
        }
        status.setParamLen(i3);
        return status;
    }

    public static CommandBase convert2Command(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        if (basePacket != null) {
            CommandBase a2 = a(bluetoothDevice, basePacket);
            return a2 != null ? a2 : new RcspCmdHandler().parseDataToCmd(bluetoothDevice, basePacket);
        }
        return null;
    }

    public static int convertVersionByString(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        JL_Log.i(f12503a, "convertVersionByString :: version = " + str);
        String[] split = str.replace(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "").replace(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "").split("\\.");
        int length = split.length;
        int[] iArr = new int[length];
        for (int i = 0; i < split.length; i++) {
            String str2 = split[i];
            if (TextUtils.isDigitsOnly(str2)) {
                iArr[i] = Integer.parseInt(str2);
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
            JL_Log.i(f12503a, "convertVersionByString :: versionCode : 0, heightValue : " + CHexConver.byte2HexStr(bArr, 8) + ", lowValue : " + CHexConver.byte2HexStr(bArr2, 8));
            return CHexConver.bytesToInt((byte) a(bArr), (byte) a(bArr2));
        }
        return 0;
    }

    public static List<AttrBean> coverParamDataToAttrBeans(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < bArr.length) {
                int i2 = i + 1;
                int byteToInt = CHexConver.byteToInt(bArr[i]);
                if (byteToInt < 1) {
                    return arrayList;
                }
                AttrBean attrBean = new AttrBean();
                int i3 = i2 + 1;
                attrBean.setType(bArr[i2]);
                int i4 = byteToInt - 1;
                byte[] bArr2 = new byte[i4];
                if (bArr.length - i3 < i4) {
                    return arrayList;
                }
                System.arraycopy(bArr, i3, bArr2, 0, i4);
                attrBean.setAttrData(bArr2);
                i = i4 + i3;
                arrayList.add(attrBean);
            }
            return arrayList;
        }
        return new ArrayList();
    }

    public static String hexDataCovetToAddress(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length == 6) {
            for (int i = 0; i < bArr.length; i++) {
                char[] cArr = b;
                sb.append(cArr[(bArr[i] & 255) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
                if (i != bArr.length - 1) {
                    sb.append(":");
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] packSendBasePacket(com.jieli.jl_rcsp.model.base.BasePacket r16) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_rcsp.tool.datahandles.ParseHelper.packSendBasePacket(com.jieli.jl_rcsp.model.base.BasePacket):byte[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0185, code lost:
        com.jieli.jl_rcsp.util.JL_Log.e(com.jieli.jl_rcsp.tool.datahandles.ParseHelper.f12503a, java.lang.String.format(java.util.Locale.getDefault(), "parseADVInfo :: data length[%d] over MAX_COMMUNICATION_MTU[%d], cast away", java.lang.Integer.valueOf(r4), java.lang.Integer.valueOf(a(r11))));
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01a6, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parseADVInfo(android.bluetooth.BluetoothDevice r11, com.jieli.jl_rcsp.model.response.ADVInfoResponse r12, com.jieli.jl_rcsp.model.base.BasePacket r13) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_rcsp.tool.datahandles.ParseHelper.parseADVInfo(android.bluetooth.BluetoothDevice, com.jieli.jl_rcsp.model.response.ADVInfoResponse, com.jieli.jl_rcsp.model.base.BasePacket):void");
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
        int i = (b3 >> 7) & 1;
        int i2 = b3 & Byte.MAX_VALUE;
        byte b4 = paramData[15];
        int i3 = (b4 >> 7) & 1;
        int i4 = b4 & Byte.MAX_VALUE;
        byte b5 = paramData[16];
        int i5 = (b5 >> 7) & 1;
        notifyAdvInfoParam.setVid(bytesToInt).setUid(bytesToInt2).setPid(bytesToInt3).setDeviceType((b2 >> 4) & 255).setVersion(b2 & 15).setEdrAddr(hexDataCovetToAddress).setAction(byteToInt).setLeftCharging(i == 1).setLeftDeviceQuantity(i2).setRightCharging(i3 == 1).setRightDeviceQuantity(i4).setDeviceCharging(i5 == 1).setChargingBinQuantity(b5 & Byte.MAX_VALUE).setSeq(CHexConver.byteToInt(paramData[17]));
    }

    public static void parseSysInfo(SysInfoResponse sysInfoResponse, BasePacket basePacket) {
        byte[] paramData = basePacket.getParamData();
        if (paramData == null || sysInfoResponse == null || paramData.length <= 0) {
            return;
        }
        byte b2 = paramData[0];
        if (paramData.length > 1) {
            int length = paramData.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(paramData, 1, bArr, 0, length);
            List<AttrBean> coverParamDataToAttrBeans = coverParamDataToAttrBeans(bArr);
            sysInfoResponse.setFunction(b2);
            sysInfoResponse.setAttrs(coverParamDataToAttrBeans);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:176:0x0682, code lost:
        com.jieli.jl_rcsp.util.JL_Log.e(com.jieli.jl_rcsp.tool.datahandles.ParseHelper.f12503a, java.lang.String.format(java.util.Locale.getDefault(), "parseTargetInfo :: data length[%d] over MAX_COMMUNICATION_MTU[%d], cast away", java.lang.Integer.valueOf(r7), java.lang.Integer.valueOf(a(r16))));
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x06a4, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parseTargetInfo(android.bluetooth.BluetoothDevice r16, com.jieli.jl_rcsp.model.response.TargetInfoResponse r17, com.jieli.jl_rcsp.model.base.BasePacket r18) {
        /*
            Method dump skipped, instructions count: 1774
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_rcsp.tool.datahandles.ParseHelper.parseTargetInfo(android.bluetooth.BluetoothDevice, com.jieli.jl_rcsp.model.response.TargetInfoResponse, com.jieli.jl_rcsp.model.base.BasePacket):void");
    }

    public static int setDeviceMaxReceiveMtu(BluetoothDevice bluetoothDevice) {
        return DeviceStatusManager.getInstance().getMaxReceiveMtu(bluetoothDevice);
    }

    private static int a(BluetoothDevice bluetoothDevice) {
        return DeviceStatusManager.getInstance().getMaxCommunicationMtu(bluetoothDevice);
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
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
