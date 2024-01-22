package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.BaseResponse;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.file_op.DeviceExtendParamCmd;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
/* loaded from: classes11.dex */
public class DeviceExtendParamCmdHandler implements ICmdHandler {
    private final String tag = getClass().getSimpleName();

    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        DeviceExtendParamCmd deviceExtendParamCmd;
        BaseResponse response;
        byte[] paramData = basePacket.getParamData();
        DeviceExtendParamCmd.Param param = null;
        if (paramData != null && paramData.length >= 1) {
            if (basePacket.getType() == 1) {
                byte b = paramData[0];
                if (b == 0) {
                    param = new DeviceExtendParamCmd.FileTransferParam(CHexConver.bytesToInt(paramData, 1, 4), paramData[5] == 1);
                } else if (b == 1) {
                    param = new DeviceExtendParamCmd.DeleteFileParam(CHexConver.bytesToInt(paramData, 1, 4));
                } else if (b == 2) {
                    param = new DeviceExtendParamCmd.ReadFileParam(CHexConver.bytesToInt(paramData, 1, 4));
                }
                return new DeviceExtendParamCmd(param);
            }
            CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
            if (command != null) {
                deviceExtendParamCmd = (DeviceExtendParamCmd) command;
            } else {
                deviceExtendParamCmd = new DeviceExtendParamCmd(new DeviceExtendParamCmd.Param(paramData[0]));
            }
            if (paramData[0] == 0) {
                response = new DeviceExtendParamCmd.FileTransferResponse(paramData[1] == 1);
            } else {
                response = new DeviceExtendParamCmd.Response(paramData[0]);
            }
            deviceExtendParamCmd.setStatus(basePacket.getStatus());
            deviceExtendParamCmd.setOpCodeSn(basePacket.getOpCodeSn());
            deviceExtendParamCmd.setResponse(response);
            return deviceExtendParamCmd;
        }
        String str = this.tag;
        JL_Log.e(str, "异常：数据包内容为空，cmd id = " + String.format("%02x", Integer.valueOf(basePacket.getOpCode())));
        return null;
    }
}
