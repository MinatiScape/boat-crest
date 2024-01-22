package com.jieli.jl_rcsp.model.parameter;

import com.jieli.jl_rcsp.model.base.BaseParameter;
/* loaded from: classes11.dex */
public class StopSpeechParam extends BaseParameter {
    private byte reason;

    @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
    public byte[] getParamData() {
        return new byte[]{this.reason};
    }

    public byte getReason() {
        return this.reason;
    }

    public StopSpeechParam setReason(byte b) {
        this.reason = b;
        return this;
    }
}
