package com.jieli.jl_rcsp.model.command.speech;

import com.jieli.jl_rcsp.model.base.CommandWithParamAndResponse;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.model.parameter.StopSpeechParam;
/* loaded from: classes11.dex */
public class StopSpeechCmd extends CommandWithParamAndResponse<StopSpeechParam, CommonResponse> {
    public StopSpeechCmd(StopSpeechParam stopSpeechParam) {
        super(5, StopSpeechCmd.class.getSimpleName(), stopSpeechParam);
    }
}
