package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.model.command.NotifyPrepareEnvCmd;
import com.jieli.jl_rcsp.model.command.file_op.CancelLargeFileTransferCmd;
import com.jieli.jl_rcsp.model.command.file_op.LargeFileTransferGetNameCmd;
import com.jieli.jl_rcsp.model.command.file_op.LargeFileTransferOpCmd;
import com.jieli.jl_rcsp.model.command.file_op.StartLargeFileTransferCmd;
import com.jieli.jl_rcsp.model.command.file_op.StopLargeFileTransferCmd;
import com.jieli.jl_rcsp.model.parameter.LargeFileTransferOpParam;
import com.jieli.jl_rcsp.model.parameter.StartLargeFileTransferParam;
import com.jieli.jl_rcsp.model.response.StartLargeFileTransferResponse;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.util.CHexConver;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class LargeFileTransferCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        StopLargeFileTransferCmd stopLargeFileTransferCmd;
        LargeFileTransferOpCmd largeFileTransferOpCmd;
        CancelLargeFileTransferCmd cancelLargeFileTransferCmd;
        LargeFileTransferGetNameCmd largeFileTransferGetNameCmd;
        NotifyPrepareEnvCmd notifyPrepareEnvCmd;
        short s;
        int i;
        short s2;
        byte[] bArr = null;
        if (basePacket == null) {
            return null;
        }
        int opCode = basePacket.getOpCode();
        byte[] paramData = basePacket.getParamData();
        int i2 = 0;
        byte b = 0;
        if (basePacket.getType() == 1) {
            switch (opCode) {
                case 27:
                    if (paramData == null || paramData.length <= 5) {
                        s = 0;
                    } else {
                        i2 = CHexConver.bytesToInt(paramData, 0, 4);
                        s = CHexConver.bytesToShort(paramData[4], paramData[5]);
                        bArr = Arrays.copyOfRange(paramData, 6, paramData.length - 6);
                    }
                    return new StartLargeFileTransferCmd(new StartLargeFileTransferParam(bArr, i2, s)).setOpCodeSn(basePacket.getOpCodeSn());
                case 28:
                    StopLargeFileTransferCmd.Param param = new StopLargeFileTransferCmd.Param(paramData[0]);
                    param.setParamData(paramData);
                    StopLargeFileTransferCmd stopLargeFileTransferCmd2 = new StopLargeFileTransferCmd(param);
                    stopLargeFileTransferCmd2.setOpCodeSn(basePacket.getOpCodeSn());
                    return stopLargeFileTransferCmd2;
                case 29:
                    if (paramData == null || paramData.length <= 0) {
                        i = 0;
                        s2 = 0;
                    } else {
                        b = paramData[0];
                        s2 = CHexConver.bytesToShort(paramData[1], paramData[2]);
                        i = CHexConver.bytesToInt(paramData, 3, 4);
                    }
                    LargeFileTransferOpCmd largeFileTransferOpCmd2 = new LargeFileTransferOpCmd(new LargeFileTransferOpParam(b, s2, i));
                    largeFileTransferOpCmd2.setOpCodeSn(basePacket.getOpCodeSn());
                    return largeFileTransferOpCmd2;
                case 30:
                    return new CancelLargeFileTransferCmd().setOpCodeSn(basePacket.getOpCodeSn());
                case 32:
                    return new LargeFileTransferGetNameCmd(new LargeFileTransferGetNameCmd.Param("", 0)).setOpCodeSn(basePacket.getOpCodeSn());
                case 33:
                    return new NotifyPrepareEnvCmd(new NotifyPrepareEnvCmd.Param(paramData[0])).setOpCodeSn(basePacket.getOpCodeSn());
            }
        }
        CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, opCode, basePacket.getOpCodeSn());
        switch (opCode) {
            case 27:
                StartLargeFileTransferResponse startLargeFileTransferResponse = new StartLargeFileTransferResponse();
                startLargeFileTransferResponse.setRawData(paramData);
                if (paramData.length > 0) {
                    startLargeFileTransferResponse.setTransferMtu(CHexConver.bytesToShort(paramData[0], paramData[1]));
                }
                if (command != null) {
                    StartLargeFileTransferCmd startLargeFileTransferCmd = (StartLargeFileTransferCmd) command;
                    startLargeFileTransferCmd.setStatus(basePacket.getStatus());
                    startLargeFileTransferCmd.setResponse(startLargeFileTransferResponse);
                    return startLargeFileTransferCmd;
                }
                StartLargeFileTransferCmd startLargeFileTransferCmd2 = new StartLargeFileTransferCmd(new StartLargeFileTransferParam(new byte[0], 0, (short) 0));
                startLargeFileTransferCmd2.setOpCodeSn(basePacket.getOpCodeSn());
                startLargeFileTransferCmd2.setStatus(basePacket.getStatus());
                startLargeFileTransferCmd2.setResponse(startLargeFileTransferResponse);
                return startLargeFileTransferCmd2;
            case 28:
                if (command != null) {
                    stopLargeFileTransferCmd = (StopLargeFileTransferCmd) command;
                } else {
                    stopLargeFileTransferCmd = new StopLargeFileTransferCmd(new StopLargeFileTransferCmd.Param(0));
                }
                stopLargeFileTransferCmd.setOpCodeSn(basePacket.getOpCodeSn());
                stopLargeFileTransferCmd.setStatus(basePacket.getStatus());
                stopLargeFileTransferCmd.setResponse(new CommonResponse());
                return stopLargeFileTransferCmd;
            case 29:
                if (command != null) {
                    largeFileTransferOpCmd = (LargeFileTransferOpCmd) command;
                } else {
                    largeFileTransferOpCmd = new LargeFileTransferOpCmd(new LargeFileTransferOpParam((byte) 0, (short) 0, 0));
                }
                largeFileTransferOpCmd.setOpCodeSn(basePacket.getOpCodeSn());
                largeFileTransferOpCmd.setStatus(basePacket.getStatus());
                return largeFileTransferOpCmd;
            case 30:
                if (command != null) {
                    cancelLargeFileTransferCmd = (CancelLargeFileTransferCmd) command;
                } else {
                    cancelLargeFileTransferCmd = new CancelLargeFileTransferCmd();
                }
                cancelLargeFileTransferCmd.setStatus(basePacket.getStatus());
                cancelLargeFileTransferCmd.setOpCodeSn(basePacket.getOpCodeSn());
                cancelLargeFileTransferCmd.setResponse(new CommonResponse());
                return cancelLargeFileTransferCmd;
            case 32:
                if (command != null) {
                    largeFileTransferGetNameCmd = (LargeFileTransferGetNameCmd) command;
                } else {
                    largeFileTransferGetNameCmd = new LargeFileTransferGetNameCmd(new LargeFileTransferGetNameCmd.Param("", 0));
                }
                largeFileTransferGetNameCmd.setResponse(new CommonResponse());
                largeFileTransferGetNameCmd.setOpCodeSn(basePacket.getOpCodeSn());
                largeFileTransferGetNameCmd.setStatus(basePacket.getStatus());
                return largeFileTransferGetNameCmd;
            case 33:
                if (command != null) {
                    notifyPrepareEnvCmd = (NotifyPrepareEnvCmd) command;
                } else {
                    notifyPrepareEnvCmd = new NotifyPrepareEnvCmd(new NotifyPrepareEnvCmd.Param((byte) 0));
                }
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setRawData(paramData);
                notifyPrepareEnvCmd.setResponse(commonResponse);
                notifyPrepareEnvCmd.setOpCodeSn(basePacket.getOpCodeSn());
                notifyPrepareEnvCmd.setStatus(basePacket.getStatus());
                return notifyPrepareEnvCmd;
        }
        return null;
    }
}
