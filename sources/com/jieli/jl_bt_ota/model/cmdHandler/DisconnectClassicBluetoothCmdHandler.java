package com.jieli.jl_bt_ota.model.cmdHandler;

import com.jieli.jl_bt_ota.interfaces.command.ICmdHandler;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.base.CommonResponse;
import com.jieli.jl_bt_ota.model.command.DisconnectClassicBluetoothCmd;
/* loaded from: classes11.dex */
public class DisconnectClassicBluetoothCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_bt_ota.interfaces.command.ICmdHandler
    public CommandBase parseDataToCmd(BasePacket basePacket, CommandBase commandBase) {
        if (basePacket != null && basePacket.getOpCode() == 6) {
            byte[] paramData = basePacket.getParamData();
            if (basePacket.getType() == 1) {
                return new DisconnectClassicBluetoothCmd().setOpCodeSn(basePacket.getOpCodeSn());
            }
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setRawData(paramData);
            DisconnectClassicBluetoothCmd disconnectClassicBluetoothCmd = commandBase instanceof DisconnectClassicBluetoothCmd ? (DisconnectClassicBluetoothCmd) commandBase : new DisconnectClassicBluetoothCmd();
            disconnectClassicBluetoothCmd.setOpCodeSn(basePacket.getOpCodeSn());
            disconnectClassicBluetoothCmd.setStatus(basePacket.getStatus());
            disconnectClassicBluetoothCmd.setResponse(commonResponse);
            return disconnectClassicBluetoothCmd;
        }
        return null;
    }
}
