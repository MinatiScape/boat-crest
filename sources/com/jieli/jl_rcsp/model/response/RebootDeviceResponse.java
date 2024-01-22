package com.jieli.jl_rcsp.model.response;

import com.jieli.jl_rcsp.model.base.CommonResponse;
/* loaded from: classes11.dex */
public class RebootDeviceResponse extends CommonResponse {
    private final int result;

    public RebootDeviceResponse(int i) {
        this.result = i;
    }

    public int getResult() {
        return this.result;
    }

    @Override // com.jieli.jl_rcsp.model.base.CommonResponse
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RebootDeviceResponse{rawData size =");
        sb.append(getRawData() == null ? 0 : getRawData().length);
        sb.append("\nxmOpCode=");
        sb.append(getXmOpCode());
        sb.append("\nresult=");
        sb.append(this.result);
        sb.append('}');
        return sb.toString();
    }
}
