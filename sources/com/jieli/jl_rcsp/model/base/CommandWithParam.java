package com.jieli.jl_rcsp.model.base;

import com.jieli.jl_rcsp.model.base.BaseParameter;
/* loaded from: classes11.dex */
public class CommandWithParam<P extends BaseParameter> extends CommandBase<P, CommonResponse> {
    public CommandWithParam(int i, String str, P p) {
        super(i, str, 1);
        setParam(p);
    }
}