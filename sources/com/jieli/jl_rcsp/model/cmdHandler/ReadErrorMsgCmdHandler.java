package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.ReadErrorMsgCmd;
import com.jieli.jl_rcsp.tool.CommandHelper;
/* loaded from: classes11.dex */
public class ReadErrorMsgCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        if (basePacket == null || basePacket.getOpCode() != 41) {
            return null;
        }
        if (basePacket.getType() == 1) {
            ReadErrorMsgCmd.ReadErrorMsgParam readErrorMsgParam = new ReadErrorMsgCmd.ReadErrorMsgParam();
            readErrorMsgParam.parseData(basePacket.getParamData());
            return new ReadErrorMsgCmd(readErrorMsgParam).setOpCodeSn(basePacket.getOpCodeSn());
        }
        CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
        ReadErrorMsgCmd readErrorMsgCmd = command == null ? new ReadErrorMsgCmd(new ReadErrorMsgCmd.ReadErrorMsgParam()) : (ReadErrorMsgCmd) command;
        ReadErrorMsgCmd.ReadErrorMsgResponse readErrorMsgResponse = new ReadErrorMsgCmd.ReadErrorMsgResponse();
        readErrorMsgResponse.parseData(basePacket.getParamData());
        readErrorMsgCmd.setStatus(basePacket.getStatus());
        readErrorMsgCmd.setOpCodeSn(basePacket.getOpCodeSn());
        readErrorMsgCmd.setResponse(readErrorMsgResponse);
        return readErrorMsgCmd;
    }
}
