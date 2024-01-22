package com.jieli.jl_bt_ota.model.cmdHandler;

import com.jieli.jl_bt_ota.interfaces.command.ICmdHandler;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.BaseParameter;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.base.CommandWithParam;
import com.jieli.jl_bt_ota.model.base.CommandWithParamAndResponse;
import com.jieli.jl_bt_ota.model.base.CommandWithResponse;
import com.jieli.jl_bt_ota.model.base.CommonResponse;
import com.jieli.jl_bt_ota.tool.ParseHelper;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.JL_Log;
/* loaded from: classes11.dex */
public class RcspCmdHandler implements ICmdHandler {
    private static final String TAG = "RcspCmdHandler";

    @Override // com.jieli.jl_bt_ota.interfaces.command.ICmdHandler
    public CommandBase parseDataToCmd(BasePacket basePacket, CommandBase commandBase) {
        CommandWithParamAndResponse commandWithParamAndResponse;
        int i = 0;
        boolean z = basePacket.getType() == 1;
        boolean z2 = basePacket.getHasResponse() == 1;
        if (basePacket.getParamData() != null && basePacket.getParamData().length > 0) {
            i = 1;
        }
        if (z) {
            if (z2) {
                i = i != 0 ? 3 : 2;
            }
        } else if (commandBase == null) {
            byte[] packSendBasePacket = ParseHelper.packSendBasePacket(basePacket);
            JL_Log.w(TAG, " unknown data ::::::: " + CHexConver.byte2HexStr(packSendBasePacket));
            return null;
        } else {
            i = commandBase.getType();
        }
        if (i == 1) {
            BaseParameter baseParameter = new BaseParameter();
            baseParameter.setXmOpCode(basePacket.getXmOpCode());
            baseParameter.setParamData(basePacket.getParamData());
            CommandWithParam commandWithParam = new CommandWithParam(basePacket.getOpCode(), CommandWithParam.class.getSimpleName(), baseParameter);
            commandWithParam.setOpCodeSn(basePacket.getOpCodeSn()).setStatus(basePacket.getStatus());
            return commandWithParam;
        } else if (i != 2) {
            if (i != 3) {
                return new CommandBase(basePacket.getOpCode(), CommandBase.class.getSimpleName()).setOpCodeSn(basePacket.getOpCodeSn()).setStatus(basePacket.getStatus());
            }
            CommandWithResponse commandWithResponse = new CommandWithResponse(basePacket.getOpCode(), CommandWithResponse.class.getSimpleName());
            if (!z) {
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setRawData(basePacket.getParamData());
                commonResponse.setXmOpCode(basePacket.getXmOpCode());
                commandWithResponse.setResponse(commonResponse);
            }
            commandWithResponse.setOpCodeSn(basePacket.getOpCodeSn());
            commandWithResponse.setStatus(basePacket.getStatus());
            return commandWithResponse;
        } else {
            BaseParameter baseParameter2 = new BaseParameter();
            baseParameter2.setXmOpCode(basePacket.getXmOpCode());
            if (z) {
                baseParameter2.setParamData(basePacket.getParamData());
                commandWithParamAndResponse = new CommandWithParamAndResponse(basePacket.getOpCode(), CommandWithParamAndResponse.class.getSimpleName(), baseParameter2);
            } else {
                if (commandBase.getParam() != null) {
                    baseParameter2.setParamData(commandBase.getParam().getParamData());
                }
                commandWithParamAndResponse = new CommandWithParamAndResponse(basePacket.getOpCode(), CommandWithParamAndResponse.class.getSimpleName(), baseParameter2);
                CommonResponse commonResponse2 = new CommonResponse();
                commonResponse2.setRawData(basePacket.getParamData());
                commonResponse2.setXmOpCode(basePacket.getXmOpCode());
                commandWithParamAndResponse.setResponse(commonResponse2);
            }
            commandWithParamAndResponse.setOpCodeSn(basePacket.getOpCodeSn());
            commandWithParamAndResponse.setStatus(basePacket.getStatus());
            return commandWithParamAndResponse;
        }
    }
}
