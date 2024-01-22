package com.jieli.jl_bt_ota.model.cmdHandler;

import com.jieli.jl_bt_ota.interfaces.command.ICmdHandler;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.command.GetTargetInfoCmd;
import com.jieli.jl_bt_ota.model.parameter.GetTargetInfoParam;
import com.jieli.jl_bt_ota.model.response.TargetInfoResponse;
import com.jieli.jl_bt_ota.tool.ParseHelper;
import com.jieli.jl_bt_ota.util.CHexConver;
/* loaded from: classes11.dex */
public class GetTargetInfoCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_bt_ota.interfaces.command.ICmdHandler
    public CommandBase parseDataToCmd(BasePacket basePacket, CommandBase commandBase) {
        int i;
        if (basePacket != null && basePacket.getOpCode() == 3) {
            byte[] paramData = basePacket.getParamData();
            byte b = 0;
            if (basePacket.getType() == 1) {
                if (paramData == null || paramData.length < 4) {
                    i = 0;
                } else {
                    byte[] bArr = new byte[4];
                    System.arraycopy(paramData, 0, bArr, 0, 4);
                    i = CHexConver.bytesToInt(bArr);
                    if (paramData.length >= 5) {
                        b = paramData[4];
                    }
                }
                return new GetTargetInfoCmd(new GetTargetInfoParam(i).setPlatform(b)).setOpCodeSn(basePacket.getOpCodeSn());
            }
            TargetInfoResponse targetInfoResponse = new TargetInfoResponse();
            targetInfoResponse.setRawData(paramData);
            ParseHelper.parseTargetInfo(targetInfoResponse, basePacket);
            GetTargetInfoCmd getTargetInfoCmd = commandBase instanceof GetTargetInfoCmd ? (GetTargetInfoCmd) commandBase : new GetTargetInfoCmd(new GetTargetInfoParam(0).setPlatform((byte) 0));
            getTargetInfoCmd.setOpCodeSn(basePacket.getOpCodeSn());
            getTargetInfoCmd.setStatus(basePacket.getStatus());
            getTargetInfoCmd.setResponse(targetInfoResponse);
            return getTargetInfoCmd;
        }
        return null;
    }
}
