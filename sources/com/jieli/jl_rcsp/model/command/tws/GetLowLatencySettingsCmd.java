package com.jieli.jl_rcsp.model.command.tws;

import com.jieli.jl_rcsp.constant.Command;
import com.jieli.jl_rcsp.model.base.CommandWithResponse;
import com.jieli.jl_rcsp.model.response.GetLowLatencySettingsResponse;
/* loaded from: classes11.dex */
public class GetLowLatencySettingsCmd extends CommandWithResponse<GetLowLatencySettingsResponse> {
    public GetLowLatencySettingsCmd() {
        super(Command.CMD_GET_LOW_LATENCY_SETTINGS, GetLowLatencySettingsCmd.class.getSimpleName());
    }
}
