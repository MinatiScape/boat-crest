package com.jieli.jl_rcsp.model.base;

import com.jieli.jl_rcsp.model.base.CommonResponse;
/* loaded from: classes11.dex */
public class CommandWithResponse<R extends CommonResponse> extends CommandBase<BaseParameter, R> {
    public CommandWithResponse(int i, String str) {
        super(i, str, 3);
    }
}
