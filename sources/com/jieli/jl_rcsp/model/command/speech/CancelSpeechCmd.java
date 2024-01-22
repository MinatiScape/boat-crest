package com.jieli.jl_rcsp.model.command.speech;

import com.jieli.jl_rcsp.constant.Command;
import com.jieli.jl_rcsp.model.base.CommandWithResponse;
import com.jieli.jl_rcsp.model.base.CommonResponse;
/* loaded from: classes11.dex */
public class CancelSpeechCmd extends CommandWithResponse<CommonResponse> {
    public CancelSpeechCmd() {
        super(Command.CMD_RECEIVE_SPEECH_CANCEL, CancelSpeechCmd.class.getSimpleName());
    }
}
