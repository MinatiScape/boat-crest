package com.jieli.jl_rcsp.model.command.file_op;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandWithParamAndResponse;
import com.jieli.jl_rcsp.model.base.CommonResponse;
/* loaded from: classes11.dex */
public class DeleteFileByNameCmd extends CommandWithParamAndResponse<Param, Response> {

    /* loaded from: classes11.dex */
    public static class Param extends BaseParameter {
        public String name;

        public Param(String str) {
            this.name = "";
            this.name = str == null ? "" : str;
        }

        @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
        public byte[] getParamData() {
            int length = this.name.length() + 1;
            byte[] bArr = new byte[length];
            System.arraycopy(this.name.getBytes(), 0, bArr, 1, length - 1);
            return bArr;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    /* loaded from: classes11.dex */
    public static class Response extends CommonResponse {
    }

    public DeleteFileByNameCmd(Param param) {
        super(35, DeleteFileByNameCmd.class.getCanonicalName(), param);
    }
}
