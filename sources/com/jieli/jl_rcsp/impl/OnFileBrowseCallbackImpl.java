package com.jieli.jl_rcsp.impl;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_filebrowse.FileBrowseUtil;
import com.jieli.jl_filebrowse.bean.SDCardBean;
import com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback;
import com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback;
import com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback;
import com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.data.DataCmd;
import com.jieli.jl_rcsp.model.command.file_op.StopFileBrowseCmd;
import com.jieli.jl_rcsp.model.command.file_op.StopLrcGetCmd;
import com.jieli.jl_rcsp.model.command.sys.GetSysInfoCmd;
import com.jieli.jl_rcsp.model.command.sys.UpdateSysInfoCmd;
import com.jieli.jl_rcsp.model.device.AttrBean;
import com.jieli.jl_rcsp.model.device.DeviceInfo;
import com.jieli.jl_rcsp.model.parameter.DataParam;
import com.jieli.jl_rcsp.model.parameter.GetSysInfoParam;
import com.jieli.jl_rcsp.model.parameter.StopFileBrowseParam;
import com.jieli.jl_rcsp.model.parameter.UpdateSysInfoParam;
import com.jieli.jl_rcsp.model.response.SysInfoResponse;
import com.jieli.jl_rcsp.tool.DeviceStatusManager;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class OnFileBrowseCallbackImpl extends OnRcspCallback implements OnFileBrowseCallback, OnLrcCallback {
    private static final String d = "OnFileBrowseCallbackImpl";

    /* renamed from: a  reason: collision with root package name */
    private final RcspOpImpl f12430a;
    private final OnFileBrowseCallback b;
    private final OnLrcCallback c;

    public OnFileBrowseCallbackImpl(RcspOpImpl rcspOpImpl, OnFileBrowseCallback onFileBrowseCallback, OnLrcCallback onLrcCallback) {
        this.f12430a = rcspOpImpl;
        this.b = onFileBrowseCallback;
        this.c = onLrcCallback;
    }

    private void b(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        this.f12430a.sendCommandResponse(bluetoothDevice, commandBase, null);
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void OnFlayCallback(boolean z) {
        OnFileBrowseCallback onFileBrowseCallback = this.b;
        if (onFileBrowseCallback != null) {
            onFileBrowseCallback.OnFlayCallback(z);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onBluetoothConnectionChange(BluetoothDevice bluetoothDevice, int i) {
        OnFileBrowseCallback onFileBrowseCallback = this.b;
        if (onFileBrowseCallback != null) {
            onFileBrowseCallback.onBluetoothConnectionChange(bluetoothDevice, i);
        }
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onConnectStateChange(BluetoothDevice bluetoothDevice, int i) {
        super.onConnectStateChange(bluetoothDevice, i);
        onBluetoothConnectionChange(bluetoothDevice, i);
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileDataReceive(byte[] bArr) {
        OnFileBrowseCallback onFileBrowseCallback = this.b;
        if (onFileBrowseCallback != null) {
            onFileBrowseCallback.onFileDataReceive(bArr);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileReadFailed(int i) {
        OnFileBrowseCallback onFileBrowseCallback = this.b;
        if (onFileBrowseCallback != null) {
            onFileBrowseCallback.onFileReadFailed(i);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileReadStart() {
        OnFileBrowseCallback onFileBrowseCallback = this.b;
        if (onFileBrowseCallback != null) {
            onFileBrowseCallback.onFileReadStart();
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onFileReadStop(boolean z) {
        OnFileBrowseCallback onFileBrowseCallback = this.b;
        if (onFileBrowseCallback != null) {
            onFileBrowseCallback.onFileReadStop(z);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    public void onLrcDataReceive(byte[] bArr) {
        OnLrcCallback onLrcCallback = this.c;
        if (onLrcCallback != null) {
            onLrcCallback.onLrcDataReceive(bArr);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    public void onLrcReadFailed(int i) {
        OnLrcCallback onLrcCallback = this.c;
        if (onLrcCallback != null) {
            onLrcCallback.onLrcReadFailed(i);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    public void onLrcReadStart() {
        OnLrcCallback onLrcCallback = this.c;
        if (onLrcCallback != null) {
            onLrcCallback.onLrcReadStart();
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.OnLrcCallback
    public void onLrcReadStop() {
        OnLrcCallback onLrcCallback = this.c;
        if (onLrcCallback != null) {
            onLrcCallback.onLrcReadStop();
        }
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onRcspCommand(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        super.onRcspCommand(bluetoothDevice, commandBase);
        int id = commandBase.getId();
        if (id == 9 || id == 13 || id == 16) {
            a(bluetoothDevice, commandBase);
        }
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onRcspDataCmd(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        super.onRcspDataCmd(bluetoothDevice, commandBase);
        if (commandBase.getId() != 1) {
            return;
        }
        DataParam dataParam = (DataParam) ((DataCmd) commandBase).getParam();
        int xmOpCode = dataParam.getXmOpCode();
        if (xmOpCode == 12) {
            onFileDataReceive(dataParam.getData());
        } else if (xmOpCode != 15) {
        } else {
            onLrcDataReceive(dataParam.getData());
        }
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onRcspInit(BluetoothDevice bluetoothDevice, boolean z) {
        if (z) {
            a(bluetoothDevice);
        }
    }

    @Override // com.jieli.jl_filebrowse.interfaces.OnFileBrowseCallback
    public void onSdCardChange(List<SDCardBean> list) {
        OnFileBrowseCallback onFileBrowseCallback = this.b;
        if (onFileBrowseCallback != null) {
            onFileBrowseCallback.onSdCardChange(list);
        }
    }

    private void a(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        if (commandBase == null) {
            return;
        }
        int id = commandBase.getId();
        if (id == 9) {
            UpdateSysInfoParam updateSysInfoParam = (UpdateSysInfoParam) ((UpdateSysInfoCmd) commandBase).getParam();
            if ((updateSysInfoParam.getFunction() & 255) == 255) {
                for (AttrBean attrBean : updateSysInfoParam.getAttrBeanList()) {
                    if ((attrBean.getType() & 255) == 2) {
                        onSdCardChange(b(attrBean.getAttrData(), bluetoothDevice));
                    }
                }
            }
        } else if (id != 13) {
            if (id != 16) {
                return;
            }
            StopLrcGetCmd stopLrcGetCmd = (StopLrcGetCmd) commandBase;
            int status = stopLrcGetCmd.getStatus();
            if (status != 0) {
                onLrcReadFailed(status);
            }
            stopLrcGetCmd.setStatus(0);
            b(bluetoothDevice, stopLrcGetCmd);
        } else {
            StopFileBrowseCmd stopFileBrowseCmd = (StopFileBrowseCmd) commandBase;
            byte reason = ((StopFileBrowseParam) stopFileBrowseCmd.getParam()).getReason();
            if (reason == 0 || reason == 1) {
                onFileReadStop(reason == 1);
            } else if (reason != 2) {
                onFileReadFailed(reason);
            } else {
                OnFlayCallback(true);
            }
            stopFileBrowseCmd.setStatus(0);
            b(bluetoothDevice, stopFileBrowseCmd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<SDCardBean> b(byte[] bArr, BluetoothDevice bluetoothDevice) {
        ArrayList<SDCardBean> arrayList = new ArrayList();
        if (bArr == null || bArr.length <= 16) {
            return arrayList;
        }
        byte[] bArr2 = {1, 0, 0, 2, 3, 4};
        for (int i = 0; i < 6; i++) {
            SDCardBean sDCardBean = new SDCardBean();
            sDCardBean.setIndex(i);
            sDCardBean.setName(FileBrowseUtil.getDevName(i));
            sDCardBean.setType(bArr2[i]);
            sDCardBean.setOnline(((bArr[0] >> i) & 1) == 1);
            sDCardBean.setDevice(bluetoothDevice);
            if (i < 4) {
                byte[] bArr3 = new byte[4];
                System.arraycopy(bArr, (i * 4) + 1, bArr3, 0, 4);
                sDCardBean.setDevHandler(CHexConver.bytesToInt(bArr3));
                arrayList.add(sDCardBean);
            } else if (i == 5) {
                int i2 = ((i - 1) * 4) + 1;
                if (i2 < bArr.length) {
                    byte[] bArr4 = new byte[4];
                    System.arraycopy(bArr, i2, bArr4, 0, 4);
                    sDCardBean.setDevHandler(CHexConver.bytesToInt(bArr4));
                    arrayList.add(sDCardBean);
                }
            } else {
                arrayList.add(sDCardBean);
            }
        }
        DeviceInfo deviceInfo = DeviceStatusManager.getInstance().getDeviceInfo(bluetoothDevice);
        ArrayList arrayList2 = new ArrayList();
        if (deviceInfo == null) {
            deviceInfo = new DeviceInfo();
        }
        for (SDCardBean sDCardBean2 : arrayList) {
            JL_Log.d(d, "sd--->" + sDCardBean2.toString());
            if (sDCardBean2.getIndex() == 0 && deviceInfo.isSupportUsb()) {
                arrayList2.add(sDCardBean2);
            } else if (sDCardBean2.getIndex() == 1 && deviceInfo.isSupportSd0()) {
                arrayList2.add(sDCardBean2);
            } else if (sDCardBean2.getIndex() == 2 && deviceInfo.isSupportSd1()) {
                arrayList2.add(sDCardBean2);
            } else if (sDCardBean2.getIndex() == 4 && (deviceInfo.getFunctionMask() & 8) == 8) {
                arrayList2.add(sDCardBean2);
            } else if (sDCardBean2.getIndex() == 3) {
                arrayList2.add(sDCardBean2);
            } else if (sDCardBean2.getIndex() == 5) {
                arrayList2.add(sDCardBean2);
            }
        }
        return arrayList2;
    }

    private void a(BluetoothDevice bluetoothDevice) {
        this.f12430a.sendCommandAsync(bluetoothDevice, new GetSysInfoCmd(new GetSysInfoParam((byte) -1, 4)), 2000, new RcspCommandCallback<GetSysInfoCmd>() { // from class: com.jieli.jl_rcsp.impl.OnFileBrowseCallbackImpl.1
            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onErrCode(BluetoothDevice bluetoothDevice2, BaseError baseError) {
            }

            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onCommandResponse(BluetoothDevice bluetoothDevice2, GetSysInfoCmd getSysInfoCmd) {
                SysInfoResponse sysInfoResponse;
                if (getSysInfoCmd.getStatus() != 0 || (sysInfoResponse = (SysInfoResponse) getSysInfoCmd.getResponse()) == null || sysInfoResponse.getAttrs() == null) {
                    return;
                }
                for (AttrBean attrBean : sysInfoResponse.getAttrs()) {
                    if ((attrBean.getType() & 255) == 2) {
                        OnFileBrowseCallbackImpl.this.onSdCardChange(OnFileBrowseCallbackImpl.b(attrBean.getAttrData(), bluetoothDevice2));
                    }
                }
            }
        });
    }
}
