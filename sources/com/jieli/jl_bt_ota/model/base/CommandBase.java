package com.jieli.jl_bt_ota.model.base;

import com.jieli.jl_bt_ota.model.base.BaseParameter;
import com.jieli.jl_bt_ota.model.base.CommonResponse;
/* loaded from: classes11.dex */
public class CommandBase<P extends BaseParameter, R extends CommonResponse> {
    public static final int FLAG_HAVE_PARAMETER_AND_RESPONSE = 2;
    public static final int FLAG_HAVE_PARAMETER_NO_RESPONSE = 1;
    public static final int FLAG_NO_PARAMETER_AND_RESPONSE = 0;
    public static final int FLAG_NO_PARAMETER_HAVE_RESPONSE = 3;
    private int OpCodeSn;
    private final String name;
    private final int opCode;
    private P param;
    private R response;
    private int status;
    private final int type;

    public CommandBase(int i, String str) {
        this(i, str, 0);
    }

    public boolean equal(CommandBase commandBase) {
        return commandBase != null && this.OpCodeSn == commandBase.OpCodeSn && this.opCode == commandBase.opCode;
    }

    public int getId() {
        return this.opCode;
    }

    public String getName() {
        return this.name;
    }

    public int getOpCodeSn() {
        return this.OpCodeSn;
    }

    public P getParam() {
        return this.param;
    }

    public R getResponse() {
        return this.response;
    }

    public int getStatus() {
        return this.status;
    }

    public int getType() {
        return this.type;
    }

    public CommandBase setOpCodeSn(int i) {
        this.OpCodeSn = i;
        return this;
    }

    public CommandBase setParam(P p) {
        this.param = p;
        return this;
    }

    public CommandBase setResponse(R r) {
        this.response = r;
        return this;
    }

    public CommandBase setStatus(int i) {
        this.status = i;
        return this;
    }

    public String toString() {
        return "CommandBase{OpCodeSn=" + this.OpCodeSn + ", opCode=" + this.opCode + ", name='" + this.name + "', type=" + this.type + ", status=" + this.status + ", param=" + this.param + ", response=" + this.response + '}';
    }

    public CommandBase(int i, String str, int i2) {
        this.opCode = i;
        this.name = str;
        this.type = i2;
    }
}
