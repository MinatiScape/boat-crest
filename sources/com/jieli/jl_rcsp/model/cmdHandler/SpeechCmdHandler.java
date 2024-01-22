package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.model.command.speech.CancelSpeechCmd;
import com.jieli.jl_rcsp.model.command.speech.StartSpeechCmd;
import com.jieli.jl_rcsp.model.command.speech.StopSpeechCmd;
import com.jieli.jl_rcsp.model.parameter.StartSpeechParam;
import com.jieli.jl_rcsp.model.parameter.StopSpeechParam;
import com.jieli.jl_rcsp.model.response.StartSpeechResponse;
import com.jieli.jl_rcsp.tool.CommandHelper;
/* loaded from: classes11.dex */
public class SpeechCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        byte b;
        if (basePacket == null) {
            return null;
        }
        int opCode = basePacket.getOpCode();
        if (opCode == 210 || opCode == 4 || opCode == 5) {
            byte[] paramData = basePacket.getParamData();
            byte b2 = 16;
            byte b3 = 0;
            if (basePacket.getType() != 1) {
                CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, opCode, basePacket.getOpCodeSn());
                if (opCode == 4) {
                    StartSpeechResponse startSpeechResponse = new StartSpeechResponse();
                    startSpeechResponse.setRawData(paramData);
                    if (command != null) {
                        StartSpeechCmd startSpeechCmd = (StartSpeechCmd) command;
                        startSpeechCmd.setStatus(basePacket.getStatus());
                        startSpeechCmd.setResponse(startSpeechResponse);
                        return startSpeechCmd;
                    }
                    StartSpeechCmd startSpeechCmd2 = new StartSpeechCmd(new StartSpeechParam((byte) 1, (byte) 16, (byte) 1));
                    startSpeechCmd2.setOpCodeSn(basePacket.getOpCodeSn());
                    startSpeechCmd2.setStatus(basePacket.getStatus());
                    startSpeechCmd2.setResponse(startSpeechResponse);
                    return startSpeechCmd2;
                } else if (opCode == 5) {
                    CommonResponse commonResponse = new CommonResponse();
                    commonResponse.setRawData(paramData);
                    if (command != null) {
                        StopSpeechCmd stopSpeechCmd = (StopSpeechCmd) command;
                        stopSpeechCmd.setStatus(basePacket.getStatus());
                        stopSpeechCmd.setResponse(commonResponse);
                        return stopSpeechCmd;
                    }
                    StopSpeechCmd stopSpeechCmd2 = new StopSpeechCmd(new StopSpeechParam().setReason((byte) 0));
                    stopSpeechCmd2.setOpCodeSn(basePacket.getOpCodeSn());
                    stopSpeechCmd2.setStatus(basePacket.getStatus());
                    stopSpeechCmd2.setResponse(commonResponse);
                    return stopSpeechCmd2;
                } else if (opCode == 210) {
                    CommonResponse commonResponse2 = new CommonResponse();
                    commonResponse2.setRawData(paramData);
                    if (command != null) {
                        CancelSpeechCmd cancelSpeechCmd = (CancelSpeechCmd) command;
                        cancelSpeechCmd.setStatus(basePacket.getStatus());
                        cancelSpeechCmd.setResponse(commonResponse2);
                        return cancelSpeechCmd;
                    }
                    CancelSpeechCmd cancelSpeechCmd2 = new CancelSpeechCmd();
                    cancelSpeechCmd2.setOpCodeSn(basePacket.getOpCodeSn());
                    cancelSpeechCmd2.setStatus(basePacket.getStatus());
                    cancelSpeechCmd2.setResponse(commonResponse2);
                    return cancelSpeechCmd2;
                }
            } else if (opCode == 4) {
                if (paramData == null || paramData.length < 2) {
                    b = 1;
                } else {
                    byte b4 = paramData[0];
                    b2 = paramData[1];
                    r9 = b4;
                    b = paramData.length >= 3 ? paramData[2] : (byte) 1;
                }
                return new StartSpeechCmd(new StartSpeechParam(r9, b2, b)).setOpCodeSn(basePacket.getOpCodeSn());
            } else if (opCode == 5) {
                if (paramData != null && paramData.length > 0) {
                    b3 = paramData[0];
                }
                return new StopSpeechCmd(new StopSpeechParam().setReason(b3)).setOpCodeSn(basePacket.getOpCodeSn());
            } else if (opCode == 210) {
                return new CancelSpeechCmd().setOpCodeSn(basePacket.getOpCodeSn());
            }
        }
        return null;
    }
}
