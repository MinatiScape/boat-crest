package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.watch.PushInfoDataToDeviceCmd;
import com.jieli.jl_rcsp.model.command.watch.ReceiveHealthDataCmd;
import com.jieli.jl_rcsp.model.command.watch.RequestHealthDataCmd;
import com.jieli.jl_rcsp.model.command.watch.SensorLogCmd;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class HealthDataCmdHandler implements ICmdHandler {
    private final String tag = getClass().getSimpleName();

    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        PushInfoDataToDeviceCmd pushInfoDataToDeviceCmd;
        byte[] paramData = basePacket.getParamData();
        if (basePacket.getStatus() != 0 && basePacket.getType() == 0) {
            CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
            command.setStatus(basePacket.getStatus());
            return command;
        } else if (paramData != null && paramData.length != 0) {
            if (basePacket.getOpCode() == 160) {
                if (basePacket.getType() == 1) {
                    int bytesToInt = CHexConver.bytesToInt(paramData, 0, 4);
                    byte b = paramData[4];
                    int length = paramData.length - 5;
                    byte[] bArr = new byte[length];
                    System.arraycopy(paramData, 5, bArr, 0, length);
                    RequestHealthDataCmd requestHealthDataCmd = new RequestHealthDataCmd(basePacket.getHasResponse() == 1 ? 2 : 1, new RequestHealthDataCmd.Param(bytesToInt, b, bArr));
                    requestHealthDataCmd.setOpCodeSn(basePacket.getOpCodeSn());
                    return requestHealthDataCmd;
                }
                CommandBase command2 = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
                if (command2 != null) {
                    int length2 = paramData.length - 2;
                    byte[] bArr2 = new byte[length2];
                    System.arraycopy(paramData, 2, bArr2, 0, length2);
                    RequestHealthDataCmd.Response response = new RequestHealthDataCmd.Response(paramData[0], paramData[1], bArr2);
                    response.setRawData(paramData);
                    response.setXmOpCode(basePacket.getXmOpCode());
                    command2.setStatus(basePacket.getStatus());
                    RequestHealthDataCmd requestHealthDataCmd2 = (RequestHealthDataCmd) command2;
                    requestHealthDataCmd2.setResponse(response);
                    return requestHealthDataCmd2;
                }
            } else if (basePacket.getOpCode() == 161) {
                CommandBase command3 = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
                if (command3 != null) {
                    pushInfoDataToDeviceCmd = (PushInfoDataToDeviceCmd) command3;
                } else {
                    PushInfoDataToDeviceCmd pushInfoDataToDeviceCmd2 = new PushInfoDataToDeviceCmd(new PushInfoDataToDeviceCmd.Param(null));
                    pushInfoDataToDeviceCmd2.setOpCodeSn(basePacket.getOpCodeSn());
                    pushInfoDataToDeviceCmd = pushInfoDataToDeviceCmd2;
                }
                pushInfoDataToDeviceCmd.setStatus(basePacket.getStatus());
                pushInfoDataToDeviceCmd.setResponse(new PushInfoDataToDeviceCmd.Response(paramData[0], paramData.length > 1 ? paramData[1] : (byte) -1));
                return pushInfoDataToDeviceCmd;
            } else if (basePacket.getOpCode() == 162) {
                ReceiveHealthDataCmd receiveHealthDataCmd = new ReceiveHealthDataCmd(new ReceiveHealthDataCmd.Param(basePacket.getParamData()));
                receiveHealthDataCmd.setOpCodeSn(basePacket.getOpCodeSn());
                return receiveHealthDataCmd;
            } else if (basePacket.getOpCode() == 163) {
                SensorLogCmd sensorLogCmd = new SensorLogCmd(new SensorLogCmd.Param(basePacket.getParamData()));
                sensorLogCmd.setOpCodeSn(basePacket.getOpCodeSn());
                return sensorLogCmd;
            }
            return null;
        } else {
            CommandBase command4 = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
            command4.setStatus(basePacket.getStatus());
            return command4;
        }
    }
}
