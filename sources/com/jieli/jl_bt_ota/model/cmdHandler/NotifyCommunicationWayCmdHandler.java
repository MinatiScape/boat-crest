package com.jieli.jl_bt_ota.model.cmdHandler;

import com.jieli.jl_bt_ota.interfaces.command.ICmdHandler;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.command.NotifyCommunicationWayCmd;
import com.jieli.jl_bt_ota.model.parameter.NotifyCommunicationWayParam;
import com.jieli.jl_bt_ota.model.response.NotifyCommunicationWayResponse;
import com.jieli.jl_bt_ota.util.CHexConver;
/* loaded from: classes11.dex */
public class NotifyCommunicationWayCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_bt_ota.interfaces.command.ICmdHandler
    public CommandBase parseDataToCmd(BasePacket basePacket, CommandBase commandBase) {
        int i;
        if (basePacket != null && basePacket.getOpCode() == 11) {
            byte[] paramData = basePacket.getParamData();
            if (basePacket.getType() == 1) {
                if (paramData == null || paramData.length <= 0) {
                    i = 0;
                } else {
                    r3 = CHexConver.byteToInt(paramData[0]);
                    i = paramData.length > 1 ? CHexConver.byteToInt(paramData[1]) : 0;
                }
                return new NotifyCommunicationWayCmd(new NotifyCommunicationWayParam(r3, i)).setOpCodeSn(basePacket.getOpCodeSn());
            }
            NotifyCommunicationWayResponse notifyCommunicationWayResponse = new NotifyCommunicationWayResponse();
            notifyCommunicationWayResponse.setRawData(paramData);
            if (paramData != null && paramData.length >= 1) {
                notifyCommunicationWayResponse.setFlag(CHexConver.byteToInt(paramData[0]));
            }
            NotifyCommunicationWayCmd notifyCommunicationWayCmd = commandBase instanceof NotifyCommunicationWayCmd ? (NotifyCommunicationWayCmd) commandBase : new NotifyCommunicationWayCmd(new NotifyCommunicationWayParam(0, 0));
            notifyCommunicationWayCmd.setOpCodeSn(basePacket.getOpCodeSn());
            notifyCommunicationWayCmd.setStatus(basePacket.getStatus());
            notifyCommunicationWayCmd.setResponse(notifyCommunicationWayResponse);
            return notifyCommunicationWayCmd;
        }
        return null;
    }
}
