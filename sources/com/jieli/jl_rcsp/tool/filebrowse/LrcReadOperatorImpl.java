package com.jieli.jl_rcsp.tool.filebrowse;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_filebrowse.interfaces.OperatCallback;
import com.jieli.jl_filebrowse.interfaces.lrc.LrcReadOperator;
import com.jieli.jl_rcsp.impl.RcspOpImpl;
import com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.file_op.StartLrcGetCmd;
/* loaded from: classes11.dex */
public class LrcReadOperatorImpl implements LrcReadOperator {

    /* renamed from: a  reason: collision with root package name */
    private final RcspOpImpl f12511a;

    public LrcReadOperatorImpl(RcspOpImpl rcspOpImpl) {
        this.f12511a = rcspOpImpl;
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.LrcReadOperator
    public void startLrcRead(final OperatCallback operatCallback) {
        RcspOpImpl rcspOpImpl = this.f12511a;
        rcspOpImpl.sendCommandAsync(rcspOpImpl.getTargetDevice(), new StartLrcGetCmd(), 2000, new RcspCommandCallback() { // from class: com.jieli.jl_rcsp.tool.filebrowse.LrcReadOperatorImpl.1

            /* renamed from: a  reason: collision with root package name */
            private int f12512a = 0;

            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onCommandResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
                int i;
                if (commandBase.getStatus() == 3 && (i = this.f12512a) < 3) {
                    this.f12512a = i + 1;
                    LrcReadOperatorImpl.this.f12511a.sendCommandAsync(bluetoothDevice, new StartLrcGetCmd(), 2000, this);
                } else if (commandBase.getStatus() == 0) {
                    operatCallback.onSuccess();
                } else {
                    OperatCallback operatCallback2 = operatCallback;
                    if (operatCallback2 != null) {
                        operatCallback2.onError(commandBase.getStatus());
                    }
                }
            }

            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onErrCode(BluetoothDevice bluetoothDevice, BaseError baseError) {
                OperatCallback operatCallback2 = operatCallback;
                if (operatCallback2 != null) {
                    operatCallback2.onError(baseError.getCode());
                }
            }
        });
    }
}
