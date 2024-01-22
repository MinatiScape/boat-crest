package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.phone.NotifyPhoneNumberPlayModeCmd;
import com.jieli.jl_rcsp.model.parameter.NotifyPhoneNumberPlayModeParam;
import com.jieli.jl_rcsp.model.response.NotifyPhoneNumberPlayModeResponse;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class NotifyPhoneNumberPlayModeCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        if (basePacket != null && basePacket.getOpCode() == 241) {
            byte[] paramData = basePacket.getParamData();
            int i = 0;
            if (basePacket.getType() == 1) {
                if (paramData != null && paramData.length > 0) {
                    i = CHexConver.byteToInt(paramData[0]);
                }
                return new NotifyPhoneNumberPlayModeCmd(new NotifyPhoneNumberPlayModeParam(i)).setOpCodeSn(basePacket.getOpCodeSn());
            }
            CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
            int i2 = -1;
            if (paramData != null && paramData.length > 0) {
                i2 = CHexConver.byteToInt(paramData[0]);
            }
            NotifyPhoneNumberPlayModeResponse notifyPhoneNumberPlayModeResponse = new NotifyPhoneNumberPlayModeResponse(i2);
            notifyPhoneNumberPlayModeResponse.setRawData(paramData);
            if (command != null) {
                NotifyPhoneNumberPlayModeCmd notifyPhoneNumberPlayModeCmd = (NotifyPhoneNumberPlayModeCmd) command;
                notifyPhoneNumberPlayModeCmd.setStatus(basePacket.getStatus());
                notifyPhoneNumberPlayModeCmd.setResponse(notifyPhoneNumberPlayModeResponse);
                return notifyPhoneNumberPlayModeCmd;
            }
            NotifyPhoneNumberPlayModeCmd notifyPhoneNumberPlayModeCmd2 = new NotifyPhoneNumberPlayModeCmd(new NotifyPhoneNumberPlayModeParam(0));
            notifyPhoneNumberPlayModeCmd2.setOpCodeSn(basePacket.getOpCodeSn());
            notifyPhoneNumberPlayModeCmd2.setStatus(basePacket.getStatus());
            notifyPhoneNumberPlayModeCmd2.setResponse(notifyPhoneNumberPlayModeResponse);
            return notifyPhoneNumberPlayModeCmd2;
        }
        return null;
    }
}
