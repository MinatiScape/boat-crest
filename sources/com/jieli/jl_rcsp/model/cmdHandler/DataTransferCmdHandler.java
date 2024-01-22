package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.BaseResponse;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.data.DataTransferCmd;
import com.jieli.jl_rcsp.tool.CommandHelper;
/* loaded from: classes11.dex */
public class DataTransferCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        BaseResponse readDataResponse;
        DataTransferCmd.Param readDataParam;
        if (basePacket == null || basePacket.getOpCode() != 48) {
            return null;
        }
        int i = basePacket.getHasResponse() == 0 ? 1 : 2;
        byte[] paramData = basePacket.getParamData();
        if (basePacket.getType() == 1) {
            if (new DataTransferCmd.Param(paramData).getOp() == 1) {
                readDataParam = new DataTransferCmd.DataTransferParam(paramData);
            } else if (new DataTransferCmd.TransferParam(paramData).getWay() == 1) {
                readDataParam = new DataTransferCmd.SendDataParam(paramData);
            } else {
                readDataParam = new DataTransferCmd.ReadDataParam(paramData);
            }
            return new DataTransferCmd(i, readDataParam).setOpCodeSn(basePacket.getOpCodeSn());
        }
        CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
        DataTransferCmd dataTransferCmd = command == null ? new DataTransferCmd(new DataTransferCmd.Param(new byte[0])) : (DataTransferCmd) command;
        if (new DataTransferCmd.Response(paramData).getOp() == 1) {
            readDataResponse = new DataTransferCmd.DataTransferResponse(paramData);
        } else if (new DataTransferCmd.TransferParamResponse(paramData).getWay() == 1) {
            readDataResponse = new DataTransferCmd.SendDataResponse(paramData);
        } else {
            readDataResponse = new DataTransferCmd.ReadDataResponse(paramData);
        }
        dataTransferCmd.setResponse(readDataResponse);
        dataTransferCmd.setStatus(basePacket.getStatus());
        dataTransferCmd.setOpCodeSn(basePacket.getOpCodeSn());
        return dataTransferCmd;
    }
}
