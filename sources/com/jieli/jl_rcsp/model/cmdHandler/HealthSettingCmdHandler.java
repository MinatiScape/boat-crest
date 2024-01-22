package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.constant.RcspConstant;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.BaseResponse;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.watch.HealthSettingCmd;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.tool.datahandles.ParseHelper;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
/* loaded from: classes11.dex */
public class HealthSettingCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        BaseResponse response;
        HealthSettingCmd.Param getParam;
        if (basePacket != null && basePacket.getOpCode() == 165) {
            int i = basePacket.getHasResponse() == 1 ? 2 : 1;
            byte[] paramData = basePacket.getParamData();
            if (basePacket.getType() == 1) {
                byte b = paramData[0];
                if (b == 0) {
                    getParam = new HealthSettingCmd.GetParam(CHexConver.bytesToInt(paramData, 1, 4));
                } else if (b == 1) {
                    getParam = new HealthSettingCmd.SetParam(ParseHelper.coverParamDataToAttrBeans(new byte[paramData.length - 1]));
                } else if (b == 2) {
                    getParam = new HealthSettingCmd.UpdateParam(ParseHelper.coverParamDataToAttrBeans(new byte[paramData.length - 1]));
                } else {
                    throw new RuntimeException("invalid health setting cmd op");
                }
                HealthSettingCmd healthSettingCmd = new HealthSettingCmd(i, getParam);
                healthSettingCmd.setOpCodeSn(basePacket.getOpCodeSn());
                return healthSettingCmd;
            }
            CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
            if (command == null) {
                JL_Log.e(RcspConstant.DEBUG_LOG_TAG, "invalid health setting cmd response");
                return null;
            }
            HealthSettingCmd healthSettingCmd2 = (HealthSettingCmd) command;
            healthSettingCmd2.setOpCodeSn(basePacket.getOpCodeSn());
            healthSettingCmd2.setStatus(basePacket.getStatus());
            if (basePacket.getStatus() != 0) {
                JL_Log.w(RcspConstant.DEBUG_LOG_TAG, "health setting cmd send fail ,status = " + basePacket.getStatus());
                healthSettingCmd2.setResponse(new HealthSettingCmd.Response());
                return healthSettingCmd2;
            }
            if (healthSettingCmd2.getParam() instanceof HealthSettingCmd.GetParam) {
                response = new HealthSettingCmd.GetResponse(ParseHelper.coverParamDataToAttrBeans(paramData));
            } else {
                response = new HealthSettingCmd.Response();
            }
            response.setRawData(paramData);
            healthSettingCmd2.setResponse(response);
            return healthSettingCmd2;
        }
        return null;
    }
}
