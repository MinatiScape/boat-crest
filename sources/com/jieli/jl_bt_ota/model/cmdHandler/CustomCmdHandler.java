package com.jieli.jl_bt_ota.model.cmdHandler;

import com.jieli.jl_bt_ota.interfaces.command.ICmdHandler;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.command.CustomCmd;
import com.jieli.jl_bt_ota.model.parameter.CustomParam;
import com.jieli.jl_bt_ota.model.response.CustomResponse;
import java.io.ByteArrayOutputStream;
/* loaded from: classes11.dex */
public class CustomCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_bt_ota.interfaces.command.ICmdHandler
    public CommandBase parseDataToCmd(BasePacket basePacket, CommandBase commandBase) {
        if (basePacket != null && basePacket.getOpCode() == 255) {
            byte[] paramData = basePacket.getParamData();
            int i = basePacket.getHasResponse() == 1 ? 2 : 1;
            if (basePacket.getType() == 1) {
                byte[] bArr = new byte[0];
                if (paramData == null || paramData.length <= 0) {
                    paramData = bArr;
                }
                CustomParam customParam = new CustomParam(paramData);
                customParam.setXmOpCode(basePacket.getXmOpCode());
                CustomCmd customCmd = new CustomCmd(i, customParam);
                customCmd.setOpCodeSn(basePacket.getOpCodeSn());
                return customCmd;
            }
            CustomResponse customResponse = new CustomResponse();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(basePacket.getXmOpCode());
            try {
                byteArrayOutputStream.write(paramData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            customResponse.setRawData(byteArrayOutputStream.toByteArray());
            customResponse.setData(paramData);
            customResponse.setXmOpCode(basePacket.getXmOpCode());
            CustomCmd customCmd2 = commandBase instanceof CustomCmd ? (CustomCmd) commandBase : new CustomCmd(i, new CustomParam(new byte[0]));
            customCmd2.setOpCodeSn(basePacket.getOpCodeSn());
            customCmd2.setStatus(basePacket.getStatus());
            customCmd2.setResponse(customResponse);
            return customCmd2;
        }
        return null;
    }
}
