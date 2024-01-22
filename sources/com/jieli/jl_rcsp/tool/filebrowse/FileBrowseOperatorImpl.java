package com.jieli.jl_rcsp.tool.filebrowse;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_filebrowse.bean.PathData;
import com.jieli.jl_filebrowse.interfaces.FileBrowseOperator;
import com.jieli.jl_filebrowse.interfaces.OperatCallback;
import com.jieli.jl_rcsp.impl.RcspOpImpl;
import com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.file_op.DelDevFileCmd;
import com.jieli.jl_rcsp.model.command.file_op.DeviceFormatCmd;
import com.jieli.jl_rcsp.model.parameter.DelDevFileParam;
import com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl;
import com.jieli.jl_rcsp.util.CommandBuilder;
import com.jieli.jl_rcsp.util.JL_Log;
/* loaded from: classes11.dex */
public class FileBrowseOperatorImpl implements FileBrowseOperator {
    private final RcspOpImpl b;

    /* renamed from: a  reason: collision with root package name */
    private final String f12504a = getClass().getSimpleName();
    private final Handler c = new Handler(Looper.getMainLooper());

    /* renamed from: com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 implements RcspCommandCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PathData f12505a;
        public final /* synthetic */ byte[] b;
        public final /* synthetic */ OperatCallback c;

        public AnonymousClass1(PathData pathData, byte[] bArr, OperatCallback operatCallback) {
            this.f12505a = pathData;
            this.b = bArr;
            this.c = operatCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(PathData pathData, byte[] bArr, OperatCallback operatCallback) {
            FileBrowseOperatorImpl.this.sendPathDataCmd(pathData, bArr, operatCallback);
        }

        @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
        public void onCommandResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
            if (commandBase.getStatus() == 3 && this.f12505a.getRepeatTimes() > 0) {
                PathData pathData = this.f12505a;
                pathData.setRepeatTimes(pathData.getRepeatTimes() - 1);
                Handler handler = FileBrowseOperatorImpl.this.c;
                final PathData pathData2 = this.f12505a;
                final byte[] bArr = this.b;
                final OperatCallback operatCallback = this.c;
                handler.postDelayed(new Runnable() { // from class: com.jieli.jl_rcsp.tool.filebrowse.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        FileBrowseOperatorImpl.AnonymousClass1.this.a(pathData2, bArr, operatCallback);
                    }
                }, 500L);
            } else if (commandBase.getStatus() == 0) {
                this.c.onSuccess();
            } else {
                OperatCallback operatCallback2 = this.c;
                if (operatCallback2 != null) {
                    operatCallback2.onError(160);
                }
            }
        }

        @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
        public void onErrCode(BluetoothDevice bluetoothDevice, BaseError baseError) {
            OperatCallback operatCallback = this.c;
            if (operatCallback != null) {
                operatCallback.onError(baseError.getCode());
            }
        }
    }

    public FileBrowseOperatorImpl(RcspOpImpl rcspOpImpl) {
        this.b = rcspOpImpl;
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileBrowseOperator
    public boolean dataHasPacket() {
        return false;
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileBrowseOperator
    public void deleteFile(final int i, final byte b, final int i2, final boolean z, boolean z2, final OperatCallback operatCallback) {
        if (z2) {
            a((byte) 1, new OperatCallback() { // from class: com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl.2
                @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
                public void onError(int i3) {
                    operatCallback.onError(i3);
                }

                @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
                public void onSuccess() {
                    FileBrowseOperatorImpl.this.a(i, b, i2, z, operatCallback);
                }
            });
        } else {
            a(i, b, i2, z, operatCallback);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileBrowseOperator
    public void formatDevice(final int i, final OperatCallback operatCallback) {
        a((byte) 2, new OperatCallback() { // from class: com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl.3
            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onError(int i2) {
                OperatCallback operatCallback2 = operatCallback;
                if (operatCallback2 != null) {
                    operatCallback2.onError(i2);
                }
            }

            @Override // com.jieli.jl_filebrowse.interfaces.OperatCallback
            public void onSuccess() {
                JL_Log.i(FileBrowseOperatorImpl.this.f12504a, "开始格式化");
                FileBrowseOperatorImpl.this.b.sendCommandAsync(FileBrowseOperatorImpl.this.b.getTargetDevice(), new DeviceFormatCmd(new DeviceFormatCmd.Param(i)), 30000, new RcspCommandCallback() { // from class: com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl.3.1
                    @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
                    public void onCommandResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
                        String str = FileBrowseOperatorImpl.this.f12504a;
                        JL_Log.i(str, "格式化结束--->" + commandBase.getStatus());
                        if (operatCallback == null) {
                            return;
                        }
                        if (commandBase.getStatus() != 0) {
                            operatCallback.onError(commandBase.getStatus());
                        } else {
                            operatCallback.onSuccess();
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
        });
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileBrowseOperator
    public void sendPathDataCmd(PathData pathData, byte[] bArr, OperatCallback operatCallback) {
        CommandBase buildSendPathDataCmd = CommandBuilder.buildSendPathDataCmd(pathData);
        RcspOpImpl rcspOpImpl = this.b;
        rcspOpImpl.sendCommandAsync(rcspOpImpl.getTargetDevice(), buildSendPathDataCmd, 2000, new AnonymousClass1(pathData, bArr, operatCallback));
    }

    private void a(byte b, final OperatCallback operatCallback) {
        RcspOpImpl rcspOpImpl = this.b;
        rcspOpImpl.sendCommandAsync(rcspOpImpl.getTargetDevice(), CommandBuilder.buildNotifyPrepareEnvCmd(b), 2000, new RcspCommandCallback() { // from class: com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl.4
            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onCommandResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
                if (commandBase.getStatus() == 0) {
                    operatCallback.onSuccess();
                } else {
                    operatCallback.onError(commandBase.getStatus());
                }
            }

            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onErrCode(BluetoothDevice bluetoothDevice, BaseError baseError) {
                operatCallback.onError(baseError.getCode());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, byte b, int i2, boolean z, final OperatCallback operatCallback) {
        DelDevFileCmd delDevFileCmd = new DelDevFileCmd(new DelDevFileParam(i, b, i2, z));
        RcspOpImpl rcspOpImpl = this.b;
        rcspOpImpl.sendCommandAsync(rcspOpImpl.getTargetDevice(), delDevFileCmd, 2000, new RcspCommandCallback() { // from class: com.jieli.jl_rcsp.tool.filebrowse.FileBrowseOperatorImpl.5
            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onCommandResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
                if (operatCallback == null) {
                    return;
                }
                if (commandBase.getStatus() != 0) {
                    operatCallback.onError(commandBase.getStatus());
                } else {
                    operatCallback.onSuccess();
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
