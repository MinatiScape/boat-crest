package com.jieli.jl_rcsp.model.cmdHandler;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.constant.RcspConstant;
import com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.BaseResponse;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.watch.SportsInfoStatusSyncCmd;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.util.JL_Log;
import java.nio.ByteBuffer;
/* loaded from: classes11.dex */
public class SportsInfoStatusCmdHandler implements ICmdHandler {
    @Override // com.jieli.jl_rcsp.interfaces.cmd.ICmdHandler
    public CommandBase parseDataToCmd(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        BaseResponse response;
        SportsInfoStatusSyncCmd.Param startSportsParam;
        if (basePacket != null && basePacket.getOpCode() == 166) {
            byte[] paramData = basePacket.getParamData();
            int i = basePacket.getHasResponse() == 1 ? 2 : 1;
            if (basePacket.getType() == 1) {
                switch (paramData[0]) {
                    case 0:
                    case 2:
                    case 6:
                        throw new RuntimeException("OP_READ_EXERCISE_INFO 不支持 固件--->App方式");
                    case 1:
                        startSportsParam = new SportsInfoStatusSyncCmd.StartSportsParam(paramData[1]);
                        break;
                    case 3:
                        startSportsParam = new SportsInfoStatusSyncCmd.FirmwareStopSportsParam(ByteBuffer.allocate(paramData.length - 1).put(paramData, 1, paramData.length - 1).array());
                        break;
                    case 4:
                        startSportsParam = new SportsInfoStatusSyncCmd.PauseSportsParam();
                        break;
                    case 5:
                        startSportsParam = new SportsInfoStatusSyncCmd.ResumeSportsParam();
                        break;
                    default:
                        throw new RuntimeException("invalid sports info status sync cmd op");
                }
                SportsInfoStatusSyncCmd sportsInfoStatusSyncCmd = new SportsInfoStatusSyncCmd(i, startSportsParam);
                sportsInfoStatusSyncCmd.setOpCodeSn(basePacket.getOpCodeSn());
                return sportsInfoStatusSyncCmd;
            }
            CommandBase command = CommandHelper.getInstance().getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
            if (command == null) {
                JL_Log.e(RcspConstant.DEBUG_LOG_TAG, "invalid sports info status sync cmd response");
                return null;
            }
            SportsInfoStatusSyncCmd sportsInfoStatusSyncCmd2 = (SportsInfoStatusSyncCmd) command;
            sportsInfoStatusSyncCmd2.setOpCodeSn(basePacket.getOpCodeSn());
            sportsInfoStatusSyncCmd2.setStatus(basePacket.getStatus());
            if (basePacket.getStatus() != 0) {
                JL_Log.w(RcspConstant.DEBUG_LOG_TAG, "sports info status sync cmd send fail ,status = " + basePacket.getStatus());
                sportsInfoStatusSyncCmd2.setResponse(new SportsInfoStatusSyncCmd.Response());
                return sportsInfoStatusSyncCmd2;
            }
            if (sportsInfoStatusSyncCmd2.getParam() instanceof SportsInfoStatusSyncCmd.ReadSportsInfoParam) {
                response = new SportsInfoStatusSyncCmd.ReadSportsInfoResponse(ByteBuffer.allocate(paramData.length - 1).put(paramData, 1, paramData.length - 1).array());
            } else if (sportsInfoStatusSyncCmd2.getParam() instanceof SportsInfoStatusSyncCmd.AppStopSportsParam) {
                response = new SportsInfoStatusSyncCmd.AppStopSportsResponse(ByteBuffer.allocate(paramData.length - 1).put(paramData, 1, paramData.length - 1).array());
            } else if (sportsInfoStatusSyncCmd2.getParam() instanceof SportsInfoStatusSyncCmd.ReadRealDataParam) {
                response = new SportsInfoStatusSyncCmd.ReadRealDataResponse(ByteBuffer.allocate(paramData.length - 1).put(paramData, 1, paramData.length - 1).array());
            } else {
                response = new SportsInfoStatusSyncCmd.Response();
            }
            response.setRawData(paramData);
            sportsInfoStatusSyncCmd2.setResponse(response);
            return sportsInfoStatusSyncCmd2;
        }
        return null;
    }
}
