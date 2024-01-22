package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.nfc.NFCOperationCmd;
import com.jieli.jl_rcsp.model.command.nfc.NFCOperationNoResponseCmd;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
/* loaded from: classes11.dex */
public class NFCOperationCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        NFCOperationCmd nFCOperationCmd;
        int i;
        int i2;
        int i3;
        short s;
        int i4;
        int i5;
        byte[] paramData = basePacket.getParamData();
        NFCOperationCmd.Response response = null;
        r1 = null;
        String str = null;
        r1 = null;
        String str2 = null;
        byte[] bArr = null;
        response = null;
        if (paramData == null || paramData.length == 0) {
            return null;
        }
        int i6 = 0;
        if (basePacket.getType() == 1) {
            int opCodeSn = basePacket.getOpCodeSn();
            if (paramData.length >= 4) {
                i = CHexConver.bytesToInt(paramData, 0, 4);
                i2 = 4;
            } else {
                i = 0;
                i2 = 0;
            }
            if (paramData.length > i2) {
                i3 = CHexConver.byteToInt(paramData[i2]);
                i2++;
            } else {
                i3 = 0;
            }
            switch (i3) {
                case 0:
                    return new NFCOperationCmd(new NFCOperationCmd.StartSyncParam(i)).setOpCodeSn(opCodeSn);
                case 1:
                    return new NFCOperationNoResponseCmd(new NFCOperationNoResponseCmd.StopSyncParam(i, paramData.length > i2 ? CHexConver.byteToInt(paramData[i2]) : 0)).setOpCodeSn(opCodeSn);
                case 2:
                    int i7 = i2 + 2;
                    if (paramData.length >= i7) {
                        s = CHexConver.bytesToShort(paramData[i2], paramData[i2 + 1]);
                        i2 = i7;
                    } else {
                        s = 0;
                    }
                    int i8 = i2 + 4;
                    if (paramData.length >= i8) {
                        i4 = CHexConver.bytesToInt(paramData, i2, 4);
                        i2 = i8;
                    } else {
                        i4 = 0;
                    }
                    if (paramData.length > i2) {
                        int length = paramData.length - i2;
                        byte[] bArr2 = new byte[length];
                        System.arraycopy(paramData, i2, bArr2, 0, length);
                        bArr = bArr2;
                    }
                    return new NFCOperationCmd(new NFCOperationCmd.ModifyMsgParam(i, s, i4, bArr)).setOpCodeSn(opCodeSn);
                case 3:
                    return new NFCOperationCmd(new NFCOperationCmd.DeleteMsgParam(i, paramData.length >= i2 + 2 ? CHexConver.bytesToShort(paramData[i2], paramData[i2 + 1]) : (short) 0)).setOpCodeSn(opCodeSn);
                case 4:
                    return new NFCOperationCmd(new NFCOperationCmd.NotifyNfcParam(i)).setOpCodeSn(opCodeSn);
                case 5:
                    if (paramData.length > i2) {
                        i5 = CHexConver.byteToInt(paramData[i2]);
                        i2++;
                    } else {
                        i5 = 0;
                    }
                    if (i5 == 1) {
                        return new NFCOperationCmd(new NFCOperationCmd.SetDefaultNfcParam(i, paramData.length >= i2 + 2 ? CHexConver.bytesToShort(paramData[i2], paramData[i2 + 1]) : (short) 0)).setOpCodeSn(opCodeSn);
                    } else if (i5 != 2) {
                        return new NFCOperationCmd(new NFCOperationCmd.GetDefaultNfcParam(i)).setOpCodeSn(opCodeSn);
                    } else {
                        return new NFCOperationNoResponseCmd(new NFCOperationNoResponseCmd.NotifyDefaultNfcParam(i, paramData.length >= i2 + 2 ? CHexConver.bytesToShort(paramData[i2], paramData[i2 + 1]) : (short) 0)).setOpCodeSn(opCodeSn);
                    }
                case 6:
                    int i9 = i2 + 4;
                    if (paramData.length >= i9) {
                        i6 = CHexConver.bytesToInt(paramData, i2, 4);
                        i2 = i9;
                    }
                    if (paramData.length > i2) {
                        try {
                            str2 = new String(paramData, i2, paramData.length - i2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return new NFCOperationCmd(new NFCOperationCmd.InsertFileParam(i, i6, str2)).setOpCodeSn(opCodeSn);
                case 7:
                    if (paramData.length > i2) {
                        try {
                            str = new String(paramData, i2, paramData.length - i2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    return new NFCOperationCmd(new NFCOperationCmd.InsertFileEndParam(i, str)).setOpCodeSn(opCodeSn);
                default:
                    JL_Log.e("NFCOperationCmdHandler", "未定义命令: " + i3);
                    return null;
            }
        }
        CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
        if (command != null) {
            nFCOperationCmd = (NFCOperationCmd) command;
            if (((NFCOperationCmd.Param) nFCOperationCmd.getParam()).getOp() == 5) {
                response = new NFCOperationCmd.GetDefaultNfcResponse(CHexConver.byteToInt(paramData[0]), paramData.length >= 3 ? CHexConver.bytesToShort(paramData[1], paramData[2]) : (short) 0);
            }
        } else {
            nFCOperationCmd = new NFCOperationCmd(new NFCOperationCmd.Param(0, 0));
        }
        if (response == null) {
            response = new NFCOperationCmd.Response(CHexConver.byteToInt(paramData[0]));
            response.setRawData(paramData);
        }
        nFCOperationCmd.setOpCodeSn(basePacket.getOpCodeSn());
        nFCOperationCmd.setStatus(basePacket.getStatus());
        nFCOperationCmd.setResponse(response);
        return nFCOperationCmd;
    }
}
