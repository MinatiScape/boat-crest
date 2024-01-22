package com.jieli.jl_bt_ota.tool;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.interfaces.CommandCallback;
import com.jieli.jl_bt_ota.interfaces.IActionCallback;
import com.jieli.jl_bt_ota.interfaces.rcsp.IHandleResult;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import java.util.Objects;
/* loaded from: classes11.dex */
public class CmdResultCallback<T> implements CommandCallback {
    public final IActionCallback<T> callback;
    public final String funcName;
    public final IHandleResult<T> handle;

    public CmdResultCallback(String str, IActionCallback<T> iActionCallback, IHandleResult<T> iHandleResult) {
        Objects.requireNonNull(iHandleResult, "IHandleResult is null.");
        this.funcName = str;
        this.callback = iActionCallback;
        this.handle = iHandleResult;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.CommandCallback
    public void onCommandResponse(CommandBase commandBase) {
        String str;
        int i;
        if (commandBase.getStatus() == 0) {
            int hasResult = this.handle.hasResult(commandBase);
            if (hasResult == 0) {
                T handleResult = this.handle.handleResult(commandBase);
                IActionCallback<T> iActionCallback = this.callback;
                if (iActionCallback != null) {
                    iActionCallback.onSuccess(handleResult);
                    return;
                }
                return;
            }
            i = ErrorCode.SUB_ERR_RESPONSE_BAD_RESULT;
            str = "result = " + hasResult;
        } else {
            str = "status = " + commandBase.getStatus();
            i = 12296;
        }
        onErrCode(OTAError.buildError(i, str));
    }

    @Override // com.jieli.jl_bt_ota.interfaces.CommandCallback
    public void onErrCode(BaseError baseError) {
        IActionCallback<T> iActionCallback = this.callback;
        if (iActionCallback != null) {
            iActionCallback.onError(baseError);
        }
    }
}
