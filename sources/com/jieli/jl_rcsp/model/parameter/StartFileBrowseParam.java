package com.jieli.jl_rcsp.model.parameter;

import com.jieli.jl_filebrowse.bean.PathData;
import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class StartFileBrowseParam extends BaseParameter {
    private PathData pathData;

    public StartFileBrowseParam(PathData pathData) {
        this.pathData = pathData;
    }

    @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
    public byte[] getParamData() {
        return this.pathData.toParamData();
    }

    public PathData getPathData() {
        return this.pathData;
    }

    public StartFileBrowseParam setPathData(PathData pathData) {
        this.pathData = pathData;
        return this;
    }

    @Override // com.jieli.jl_rcsp.model.base.BaseParameter
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StartFileBrowseParam{pathData=");
        PathData pathData = this.pathData;
        sb.append(pathData != null ? CHexConver.byte2HexStr(pathData.toParamData()) : "");
        sb.append('}');
        return sb.toString();
    }
}
