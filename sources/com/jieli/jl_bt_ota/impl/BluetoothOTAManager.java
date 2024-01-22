package com.jieli.jl_bt_ota.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.impl.RcspAuth;
import com.jieli.jl_bt_ota.interfaces.IActionCallback;
import com.jieli.jl_bt_ota.interfaces.IUpgradeCallback;
import com.jieli.jl_bt_ota.model.DataInfo;
import com.jieli.jl_bt_ota.model.FileOffset;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.model.ReConnectDevMsg;
import com.jieli.jl_bt_ota.model.ReconnectParam;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.command.EnterUpdateModeCmd;
import com.jieli.jl_bt_ota.model.command.FirmwareUpdateBlockCmd;
import com.jieli.jl_bt_ota.model.command.NotifyUpdateContentSizeCmd;
import com.jieli.jl_bt_ota.model.command.SettingsMtuCmd;
import com.jieli.jl_bt_ota.model.parameter.FirmwareUpdateBlockParam;
import com.jieli.jl_bt_ota.model.parameter.FirmwareUpdateBlockResponseParam;
import com.jieli.jl_bt_ota.model.parameter.NotifyUpdateContentSizeParam;
import com.jieli.jl_bt_ota.model.parameter.SettingsMtuParam;
import com.jieli.jl_bt_ota.model.response.EnterUpdateModeResponse;
import com.jieli.jl_bt_ota.model.response.SettingsMtuResponse;
import com.jieli.jl_bt_ota.model.response.TargetInfoResponse;
import com.jieli.jl_bt_ota.thread.ReadFileThread;
import com.jieli.jl_bt_ota.tool.DataHandler;
import com.jieli.jl_bt_ota.tool.DataHandlerModify;
import com.jieli.jl_bt_ota.tool.DeviceReConnectManager;
import com.jieli.jl_bt_ota.tool.ParseHelper;
import com.jieli.jl_bt_ota.tool.RcspOTA;
import com.jieli.jl_bt_ota.tool.UpgradeCbHelper;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.CommonUtil;
import com.jieli.jl_bt_ota.util.FileUtil;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes11.dex */
public abstract class BluetoothOTAManager extends BluetoothBreProfiles implements RcspAuth.IRcspAuthOp {
    public static boolean IS_SUPPORT_NEW_RECONNECT_WAY = true;
    public static boolean IS_USE_MODIFY_DATA_HANDLER = true;
    private static final long J = 6000;
    private static final long K = 1000;
    private static final long L = 5000;
    private static final int M = 4660;
    private static final int N = 4661;
    private static final int O = 4662;
    private static final int P = 4663;
    private static final int Q = 4664;
    private static final int R = 4665;
    private static final int S = 4672;
    private static final int T = 4673;
    private volatile byte[] A;
    private long B;
    private long C;
    private long D;
    private int E;
    private int F;
    private ReconnectParam G;
    private final Handler H;
    private final RcspAuth.OnRcspAuthListener I;
    private final RcspOTA v;
    private final DeviceReConnectManager w;
    private final RcspAuth x;
    private final UpgradeCbHelper y;
    private volatile boolean z;

    public BluetoothOTAManager(Context context) {
        super(context);
        this.z = false;
        this.B = 20000L;
        this.C = 0L;
        this.D = 0L;
        this.E = 0;
        this.F = 0;
        this.H = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(@NonNull Message message) {
                int i = message.what;
                if (i == BluetoothOTAManager.Q) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                    String str = BluetoothOTAManager.this.TAG;
                    JL_Log.w(str, "MSG_WAIT_EDR_DISCONNECT  ===> " + BluetoothOTAManager.this.printBtDeviceInfo(bluetoothDevice));
                    BluetoothOTAManager.this.h(bluetoothDevice);
                } else if (i == BluetoothOTAManager.R) {
                    JL_Log.w(BluetoothOTAManager.this.TAG, "MSG_WAIT_DEVICE_DISCONNECT :: ");
                    BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                    bluetoothOTAManager.a(bluetoothOTAManager.getConnectedBtDevice(), BluetoothOTAManager.this.G);
                } else if (i == BluetoothOTAManager.S) {
                    BluetoothOTAManager.this.g();
                } else if (i != BluetoothOTAManager.T) {
                    switch (i) {
                        case BluetoothOTAManager.M /* 4660 */:
                            JL_Log.w(BluetoothOTAManager.this.TAG, "-MSG_START_RECEIVE_CMD_TIMEOUT- ");
                            BluetoothOTAManager.this.a("Receive cmd timeout", OTAError.buildError(ErrorCode.SUB_ERR_WAITING_COMMAND_TIMEOUT));
                            break;
                        case BluetoothOTAManager.N /* 4661 */:
                            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
                            String str2 = BluetoothOTAManager.this.TAG;
                            JL_Log.i(str2, "-MSG_CHANGE_BLE_MTU_TIMEOUT- device : " + BluetoothOTAManager.this.printBtDeviceInfo(bluetoothDevice2));
                            if (!BluetoothOTAManager.this.i(bluetoothDevice2)) {
                                BluetoothOTAManager.this.c(bluetoothDevice2, 2);
                                break;
                            } else {
                                BluetoothOTAManager.this.b(bluetoothDevice2, 0);
                                break;
                            }
                        case BluetoothOTAManager.O /* 4662 */:
                            boolean isOTA = BluetoothOTAManager.this.isOTA();
                            BluetoothOTAManager bluetoothOTAManager2 = BluetoothOTAManager.this;
                            boolean isConnectedDevice = bluetoothOTAManager2.isConnectedDevice(bluetoothOTAManager2.getConnectedBtDevice());
                            boolean isWaitingForUpdate = BluetoothOTAManager.this.w.isWaitingForUpdate();
                            String str3 = BluetoothOTAManager.this.TAG;
                            Locale locale = Locale.getDefault();
                            BluetoothOTAManager bluetoothOTAManager3 = BluetoothOTAManager.this;
                            JL_Log.i(str3, String.format(locale, "-MSG_OTA_RECONNECT_DEVICE- device : %s, isOTA = %s, isWaitingForUpdate = %s, isConnectedDevice = %s ReconnectParam = %s", bluetoothOTAManager3.printBtDeviceInfo(bluetoothOTAManager3.getConnectedBtDevice()), Boolean.valueOf(isOTA), Boolean.valueOf(isWaitingForUpdate), Boolean.valueOf(isConnectedDevice), BluetoothOTAManager.this.G));
                            if (isOTA && !isConnectedDevice && isWaitingForUpdate && BluetoothOTAManager.this.G != null) {
                                BluetoothOTAManager bluetoothOTAManager4 = BluetoothOTAManager.this;
                                bluetoothOTAManager4.a(bluetoothOTAManager4.w.getReconnectAddress(), BluetoothOTAManager.this.G.getFlag() == 1);
                                if (BluetoothOTAManager.this.getBluetoothOption().isUseReconnect()) {
                                    BluetoothOTAManager.this.H.sendEmptyMessageDelayed(BluetoothOTAManager.T, DeviceReConnectManager.RECONNECT_TIMEOUT);
                                } else {
                                    BluetoothOTAManager.this.w.startReconnectTask();
                                }
                                BluetoothOTAManager.this.a((ReconnectParam) null);
                                break;
                            }
                            break;
                    }
                } else {
                    JL_Log.w(BluetoothOTAManager.this.TAG, "MSG_RECONNECT_DEVICE_TIMEOUT >>>>>");
                    if (BluetoothOTAManager.this.getConnectedDevice() == null) {
                        BluetoothOTAManager.this.a("MSG_OTA_RECONNECT_DEVICE_TIMEOUT", OTAError.buildError(ErrorCode.SUB_ERR_RECONNECT_TIMEOUT));
                    }
                }
                return true;
            }
        });
        RcspAuth.OnRcspAuthListener onRcspAuthListener = new RcspAuth.OnRcspAuthListener() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.12
            @Override // com.jieli.jl_bt_ota.impl.RcspAuth.OnRcspAuthListener
            public void onAuthFailed(BluetoothDevice bluetoothDevice, int i, String str) {
                JL_Log.w(BluetoothOTAManager.this.TAG, String.format(Locale.getDefault(), "-onAuthFailed- device : %s, code : %d, message : %s", BluetoothOTAManager.this.printBtDeviceInfo(bluetoothDevice), Integer.valueOf(i), str));
                BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceIsAuth(bluetoothDevice, false);
                if (BluetoothOTAManager.this.w.isDeviceReconnecting()) {
                    BluetoothOTAManager.this.c(bluetoothDevice, 2);
                } else {
                    BluetoothOTAManager.this.a(bluetoothDevice, OTAError.buildError(ErrorCode.SUB_ERR_AUTH_DEVICE, i, str));
                }
            }

            @Override // com.jieli.jl_bt_ota.impl.RcspAuth.OnRcspAuthListener
            public void onAuthSuccess(BluetoothDevice bluetoothDevice) {
                BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceIsAuth(bluetoothDevice, true);
                boolean i = BluetoothOTAManager.this.i(bluetoothDevice);
                JL_Log.w(BluetoothOTAManager.this.TAG, String.format(Locale.getDefault(), "-onAuthSuccess- >>> device[%s] auth ok, isBleConnected = %s", BluetoothOTAManager.this.printBtDeviceInfo(bluetoothDevice), Boolean.valueOf(i)));
                if (i) {
                    BluetoothOTAManager.this.l(bluetoothDevice);
                } else {
                    BluetoothOTAManager.this.b(bluetoothDevice, 1);
                }
            }

            @Override // com.jieli.jl_bt_ota.impl.RcspAuth.OnRcspAuthListener
            public void onInitResult(boolean z) {
                String str = BluetoothOTAManager.this.TAG;
                JL_Log.d(str, "-onInitResult- " + z);
            }
        };
        this.I = onRcspAuthListener;
        this.v = new RcspOTA(this);
        this.w = new DeviceReConnectManager(context, this);
        this.x = new RcspAuth(context, this, onRcspAuthListener);
        this.y = new UpgradeCbHelper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        this.y.setUpgradeCallback(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        if (this.H.hasMessages(N)) {
            JL_Log.w(this.TAG, "-startChangeMtu- Adjusting the MTU for BLE");
            return;
        }
        boolean d = (!this.mBluetoothOption.isNeedChangeMtu() || this.mBluetoothOption.getMtu() <= 20) ? false : d(bluetoothDevice, this.mBluetoothOption.getMtu());
        String str = this.TAG;
        JL_Log.d(str, "-startChangeMtu- requestBleMtu : " + d);
        if (d) {
            Handler handler = this.H;
            handler.sendMessageDelayed(handler.obtainMessage(N, bluetoothDevice), 5000L);
            return;
        }
        b(bluetoothDevice, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m() {
        this.y.setUpgradeCallback(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(final BluetoothDevice bluetoothDevice) {
        if (a("upgradeStep01")) {
            return;
        }
        this.v.readUpgradeFileFlag(new IActionCallback<FileOffset>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.6
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep01", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(FileOffset fileOffset) {
                JL_Log.i(BluetoothOTAManager.this.TAG, String.format(Locale.getDefault(), "Step01.获取升级文件信息的偏移地址, %s", fileOffset));
                BluetoothOTAManager.this.a(bluetoothDevice, 0.0f);
                BluetoothOTAManager.this.b(bluetoothDevice, fileOffset.getOffset(), fileOffset.getLen());
            }
        });
    }

    private void o() {
        JL_Log.w(this.TAG, "releaseDataHandler>>>>>>>>>>>>>>>>>");
        if (this.dataHandler != null) {
            this.dataHandler.release();
            this.dataHandler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.w.isWaitingForUpdate()) {
            this.w.setReConnectDevMsg(null);
            this.w.stopReconnectTask();
        }
    }

    private void q() {
        c(false);
        t();
        r();
        d(true);
        a((ReconnectParam) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.F = 0;
        this.E = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (!a("startReceiveCmdTimeout") && this.B > 0) {
            this.H.removeMessages(M);
            this.H.sendEmptyMessageDelayed(M, this.B);
        }
    }

    private void t() {
        this.H.removeMessages(M);
    }

    private void u() {
        if (a("upgradeStep03")) {
            return;
        }
        this.v.enterUpdateMode(new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.9
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep03", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(Integer num) {
                String str = BluetoothOTAManager.this.TAG;
                JL_Log.i(str, "Step3.请求进入升级模式, 结果码: " + num);
                if (num.intValue() == 0) {
                    BluetoothOTAManager.this.s();
                    return;
                }
                onError(OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, "Device is not allowed to enter the upgrade mode : " + num));
            }
        });
    }

    private void v() {
        if (a("upgradeStep05")) {
            return;
        }
        this.v.queryUpdateResult(new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.10
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep05", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(Integer num) {
                BaseError buildError;
                String str = BluetoothOTAManager.this.TAG;
                JL_Log.i(str, "Step05.询问升级状态, 结果码: " + num);
                if (num.intValue() != 0) {
                    if (num.intValue() == 128) {
                        BluetoothOTAManager.this.resetTotalTime();
                        BluetoothOTAManager.this.r();
                        TargetInfoResponse deviceInfo = BluetoothOTAManager.this.getDeviceInfo();
                        String str2 = BluetoothOTAManager.this.TAG;
                        JL_Log.i(str2, "upgradeStep05 :: check device info.\n" + deviceInfo);
                        if (deviceInfo != null && deviceInfo.isSupportDoubleBackup()) {
                            BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                            int intValue = num.intValue();
                            bluetoothOTAManager.a("upgradeStep05", OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, intValue, "Double ota, but get a bad code: " + num));
                            return;
                        }
                        BluetoothOTAManager bluetoothOTAManager2 = BluetoothOTAManager.this;
                        bluetoothOTAManager2.k(bluetoothOTAManager2.getConnectedBtDevice());
                        return;
                    }
                    switch (num.intValue()) {
                        case 1:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_CHECK_RECEIVED_DATA_FAILED);
                            break;
                        case 2:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, "Device return update failed.");
                            break;
                        case 3:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_KEY_NOT_MATCH);
                            break;
                        case 4:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_CHECK_UPGRADE_FILE);
                            break;
                        case 5:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_TYPE_NOT_MATCH);
                            break;
                        case 6:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_DATA_LEN);
                            break;
                        case 7:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_FLASH_READ);
                            break;
                        case 8:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_CMD_TIMEOUT);
                            break;
                        case 9:
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_SAME_FILE);
                            break;
                        default:
                            int intValue2 = num.intValue();
                            buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_UNKNOWN, intValue2, "Device returned to an unknown code : " + num);
                            break;
                    }
                    onError(buildError);
                    return;
                }
                BluetoothOTAManager.this.c(false);
                BluetoothOTAManager.this.H.removeMessages(BluetoothOTAManager.S);
                BluetoothOTAManager.this.H.sendEmptyMessageDelayed(BluetoothOTAManager.S, 500L);
                BluetoothOTAManager.this.v.rebootDevice(new IActionCallback<Boolean>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.10.1
                    @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                    public void onError(BaseError baseError) {
                    }

                    @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                    public void onSuccess(Boolean bool) {
                        BluetoothOTAManager.this.n();
                    }
                });
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void cancelOTA() {
        i();
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void errorEventCallback(BaseError baseError) {
        this.mBtEventCbHelper.onError(baseError);
        a("errorEventCallback", baseError);
    }

    public int getCommunicationMtu(BluetoothDevice bluetoothDevice) {
        return this.mDeviceStatusCache.getMaxCommunicationMtu(bluetoothDevice);
    }

    public TargetInfoResponse getDeviceInfo(BluetoothDevice bluetoothDevice) {
        return this.mDeviceStatusCache.getDeviceInfo(bluetoothDevice);
    }

    public int getReceiveMtu(BluetoothDevice bluetoothDevice) {
        return this.mDeviceStatusCache.getMaxReceiveMtu(bluetoothDevice);
    }

    public long getTimeout_ms() {
        return this.B;
    }

    public long getTotalTime() {
        return this.D;
    }

    public int getUpdateContentSize() {
        return this.E;
    }

    public boolean isOTA() {
        return this.z;
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
        super.onA2dpStatus(bluetoothDevice, i);
        a(bluetoothDevice, 2, i);
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothDiscovery, com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onAdapterStatus(boolean z, boolean z2) {
        super.onAdapterStatus(z, z2);
        if (z) {
            return;
        }
        a("onAdapterStatus", OTAError.buildError(4099));
        BluetoothDevice connectedBtDevice = getConnectedBtDevice();
        if (connectedBtDevice != null) {
            c(connectedBtDevice, 0);
        }
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
        super.onBleDataBlockChanged(bluetoothDevice, i, i2);
        JL_Log.i(this.TAG, String.format(Locale.getDefault(), "-onBleDataBlockChanged- device : %s, block : %d, status : %d", printBtDeviceInfo(bluetoothDevice), Integer.valueOf(i), Integer.valueOf(i2)));
        if (this.H.hasMessages(N)) {
            this.H.removeMessages(N);
            JL_Log.i(this.TAG, "-onBleDataBlockChanged- handleConnectedEvent >>>");
            b(bluetoothDevice, 0);
        }
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onBtDeviceConnection(BluetoothDevice bluetoothDevice, int i) {
        super.onBtDeviceConnection(bluetoothDevice, i);
        String str = this.TAG;
        JL_Log.i(str, "-onBtDeviceConnection- device : " + printBtDeviceInfo(bluetoothDevice) + ", " + i);
        if (i != 3) {
            this.H.removeMessages(T);
        }
        if (i != 1) {
            if (BluetoothUtil.deviceEquals(bluetoothDevice, getConnectedBtDevice())) {
                setConnectedBtDevice(null);
            }
            c(bluetoothDevice, i);
            return;
        }
        if (this.dataHandler == null) {
            this.dataHandler = IS_USE_MODIFY_DATA_HANDLER ? new DataHandlerModify(this) : new DataHandler(this);
        }
        if (!checkDeviceIsCertify(bluetoothDevice)) {
            this.x.stopAuth(bluetoothDevice, false);
            if (this.x.startAuth(bluetoothDevice)) {
                return;
            }
            a(bluetoothDevice, OTAError.buildError(ErrorCode.SUB_ERR_AUTH_DEVICE));
        } else if (i(bluetoothDevice)) {
            l(bluetoothDevice);
        } else {
            b(bluetoothDevice, 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x003e, code lost:
        if (r12 != 2) goto L22;
     */
    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onConnection(android.bluetooth.BluetoothDevice r11, int r12) {
        /*
            r10 = this;
            r0 = 3
            if (r12 == r0) goto Ld7
            boolean r1 = r10.isOTA()
            com.jieli.jl_bt_ota.tool.DeviceReConnectManager r2 = r10.w
            boolean r2 = r2.isWaitingForUpdate()
            java.lang.String r3 = r10.TAG
            java.util.Locale r4 = java.util.Locale.getDefault()
            r5 = 4
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = r10.printBtDeviceInfo(r11)
            r7 = 0
            r5[r7] = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r12)
            r8 = 1
            r5[r8] = r6
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)
            r9 = 2
            r5[r9] = r6
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            r5[r0] = r6
            java.lang.String r0 = "onConnection :: device : %s, status : %d, isOTA = %s, isWaitingForUpdate = %s"
            java.lang.String r0 = java.lang.String.format(r4, r0, r5)
            com.jieli.jl_bt_ota.util.JL_Log.i(r3, r0)
            if (r12 == 0) goto L7d
            if (r12 == r8) goto L42
            if (r12 == r9) goto L7d
            goto Ld7
        L42:
            com.jieli.jl_bt_ota.tool.DeviceStatusCache r0 = r10.mDeviceStatusCache
            boolean r0 = r0.isMandatoryUpgrade(r11)
            java.lang.String r3 = r10.TAG
            java.util.Locale r4 = java.util.Locale.getDefault()
            java.lang.Object[] r5 = new java.lang.Object[r9]
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)
            r5[r7] = r6
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            r5[r8] = r6
            java.lang.String r6 = "onConnection :: connect success, isMandatoryUpgrade = %s, isWaitingForUpdate = %s"
            java.lang.String r4 = java.lang.String.format(r4, r6, r5)
            com.jieli.jl_bt_ota.util.JL_Log.w(r3, r4)
            if (r1 == 0) goto Ld7
            if (r2 == 0) goto Ld7
            r10.p()
            if (r0 == 0) goto Ld7
            java.lang.String r0 = r10.TAG
            java.lang.String r1 = "-wait for update- continue..."
            com.jieli.jl_bt_ota.util.JL_Log.e(r0, r1)
            r0 = 0
            r10.a(r11, r0)
            r10.u()
            goto Ld7
        L7d:
            android.os.Handler r0 = r10.H
            r3 = 4663(0x1237, float:6.534E-42)
            r0.removeMessages(r3)
            if (r1 == 0) goto Ld7
            if (r2 == 0) goto Lc5
            java.lang.String r0 = r10.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onConnection :: device state = "
            r1.append(r2)
            r1.append(r12)
            java.lang.String r2 = ", "
            r1.append(r2)
            com.jieli.jl_bt_ota.model.ReconnectParam r2 = r10.G
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.jieli.jl_bt_ota.util.JL_Log.i(r0, r1)
            android.os.Handler r0 = r10.H
            r1 = 4664(0x1238, float:6.536E-42)
            r0.removeMessages(r1)
            android.os.Handler r0 = r10.H
            r1 = 4665(0x1239, float:6.537E-42)
            r0.removeMessages(r1)
            com.jieli.jl_bt_ota.model.ReconnectParam r0 = r10.G
            if (r0 == 0) goto Ld7
            java.lang.String r11 = r10.TAG
            java.lang.String r12 = "onConnection :: device communication channel is disconnect. ready reconnect task. "
            com.jieli.jl_bt_ota.util.JL_Log.i(r11, r12)
            r10.h()
            return
        Lc5:
            java.lang.String r0 = r10.TAG
            java.lang.String r1 = "onConnection :: ota failed."
            com.jieli.jl_bt_ota.util.JL_Log.i(r0, r1)
            r0 = 4114(0x1012, float:5.765E-42)
            com.jieli.jl_bt_ota.model.base.BaseError r0 = com.jieli.jl_bt_ota.model.OTAError.buildError(r0)
            java.lang.String r1 = "onConnection"
            r10.a(r1, r0)
        Ld7:
            super.onConnection(r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.onConnection(android.bluetooth.BluetoothDevice, int):void");
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
        super.onHfpStatus(bluetoothDevice, i);
        a(bluetoothDevice, 1, i);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (bluetoothGatt == null) {
            return;
        }
        int i3 = i2 == 0 ? i - 3 : 20;
        String str = this.TAG;
        JL_Log.e(str, "--onMtuChanged-- bleMtu : " + i3);
        onBleDataBlockChanged(bluetoothGatt.getDevice(), i3, i2);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onReceiveDeviceData(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bluetoothDevice == null || bArr == null) {
            return;
        }
        String str = this.TAG;
        JL_Log.d(str, "---onReceiveDeviceData-- >>> device : " + printBtDeviceInfo(bluetoothDevice) + ", recv data : " + CHexConver.byte2HexStr(bArr));
        if (!checkDeviceIsCertify(bluetoothDevice)) {
            JL_Log.i(this.TAG, "--onReceiveDeviceData-- >>> handleAuthData ");
            this.x.handleAuthData(bluetoothDevice, bArr);
        } else if (this.dataHandler == null) {
            JL_Log.i(this.TAG, "--onReceiveDeviceData-- >>> dataHandler is null ");
        } else {
            DataInfo recvData = new DataInfo().setType(1).setDevice(bluetoothDevice).setRecvData(bArr);
            this.dataHandler.addRecvData(recvData);
            String str2 = this.TAG;
            JL_Log.d(str2, "--onReceiveDeviceData-- >> addRecvData >>>> " + recvData);
        }
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void queryMandatoryUpdate(final IActionCallback<TargetInfoResponse> iActionCallback) {
        final BluetoothDevice connectedBtDevice = getConnectedBtDevice();
        if (connectedBtDevice == null) {
            JL_Log.w(this.TAG, "-queryMandatoryUpdate- Bluetooth device is disconnected.");
            if (iActionCallback != null) {
                iActionCallback.onError(OTAError.buildError(4114));
                return;
            }
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo(connectedBtDevice);
        String str = this.TAG;
        JL_Log.i(str, "-queryMandatoryUpdate- cache deviceInfo : " + deviceInfo);
        if (deviceInfo != null) {
            if (deviceInfo.isMandatoryUpgrade() || deviceInfo.getRequestOtaFlag() == 1) {
                if (iActionCallback != null) {
                    iActionCallback.onSuccess(deviceInfo);
                }
                this.mBtEventCbHelper.onMandatoryUpgrade(connectedBtDevice);
                return;
            } else if (iActionCallback != null) {
                iActionCallback.onError(OTAError.buildError(0, "Device is connected."));
                return;
            } else {
                return;
            }
        }
        this.v.getDeviceInfo(new IActionCallback<TargetInfoResponse>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.2
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                IActionCallback iActionCallback2 = iActionCallback;
                if (iActionCallback2 != null) {
                    iActionCallback2.onError(baseError);
                }
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(TargetInfoResponse targetInfoResponse) {
                BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceTargetInfo(connectedBtDevice, targetInfoResponse);
                BluetoothOTAManager.this.queryMandatoryUpdate(iActionCallback);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void receiveDataFromDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        ArrayList<BasePacket> findPacketData = ParseHelper.findPacketData(bluetoothDevice, getReceiveMtu(bluetoothDevice), bArr);
        if (findPacketData != null && !findPacketData.isEmpty()) {
            Iterator<BasePacket> it = findPacketData.iterator();
            while (it.hasNext()) {
                BasePacket next = it.next();
                CommandBase convert2Command = ParseHelper.convert2Command(next, getCacheCommand(bluetoothDevice, next));
                if (convert2Command == null) {
                    JL_Log.e(this.TAG, "receiveDataFromDevice :: command is null");
                } else {
                    String str = this.TAG;
                    JL_Log.d(str, "receiveDataFromDevice :: " + convert2Command);
                    if (next.getType() == 1) {
                        onReceiveCommand(bluetoothDevice, convert2Command);
                        a(bluetoothDevice, convert2Command, next.getHasResponse() == 1);
                    } else {
                        a(bluetoothDevice, convert2Command);
                    }
                }
            }
            return;
        }
        JL_Log.w(this.TAG, "receiveDataFromDevice :: not find OTA command.");
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBreProfiles, com.jieli.jl_bt_ota.impl.BluetoothDiscovery, com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void release() {
        super.release();
        cancelOTA();
        q();
        this.w.release();
        this.x.removeListener(this.I);
        this.x.destroy();
        this.y.release();
        this.H.removeCallbacksAndMessages(null);
        o();
        JL_Log.e(this.TAG, "release..........>>>>>>>>>>>>>>>>>");
    }

    public void resetTotalTime() {
        this.D = 0L;
    }

    @Override // com.jieli.jl_bt_ota.impl.RcspAuth.IRcspAuthOp
    public boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        return sendDataToDevice(bluetoothDevice, bArr);
    }

    public void setReconnectAddress(String str) {
        if (this.w.isWaitingForUpdate() && BluetoothAdapter.checkBluetoothAddress(str)) {
            this.w.setReconnectAddress(str);
        }
    }

    public void setTimeout_ms(long j) {
        this.B = j;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void startOTA(IUpgradeCallback iUpgradeCallback) {
        BluetoothDevice connectedBtDevice = getConnectedBtDevice();
        if (connectedBtDevice == null) {
            JL_Log.w(this.TAG, "startOTA : Bluetooth device is disconnected.");
            if (iUpgradeCallback != null) {
                iUpgradeCallback.onError(OTAError.buildError(4114));
            }
        } else if (isOTA()) {
            JL_Log.w(this.TAG, "startOTA : OTA is in progress.");
            if (iUpgradeCallback != null) {
                iUpgradeCallback.onError(OTAError.buildError(ErrorCode.SUB_ERR_OTA_IN_HANDLE));
            }
        } else {
            if (!getBluetoothOption().isUseAuthDevice()) {
                this.mDeviceStatusCache.updateDeviceIsAuth(connectedBtDevice, true);
            }
            c(true);
            this.y.setUpgradeCallback(iUpgradeCallback);
            if (FileUtil.checkFileExist(getBluetoothOption().getFirmwareFilePath())) {
                a(connectedBtDevice, getBluetoothOption().getFirmwareFilePath());
            } else if (getBluetoothOption().getFirmwareFileData() != null && getBluetoothOption().getFirmwareFileData().length > 0) {
                this.A = getBluetoothOption().getFirmwareFileData();
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("startOTA : data size = ");
                sb.append(this.A == null ? 0 : this.A.length);
                JL_Log.d(str, sb.toString());
                f();
                m(connectedBtDevice);
            } else {
                a("startOTA", OTAError.buildError(ErrorCode.SUB_ERR_DATA_NOT_FOUND));
            }
        }
    }

    private boolean j() {
        return getConnectedBtDevice() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(final BluetoothDevice bluetoothDevice) {
        if (a("readyToReconnectDevice")) {
            return;
        }
        int f = f(bluetoothDevice);
        String a2 = a(bluetoothDevice, f);
        boolean z = IS_SUPPORT_NEW_RECONNECT_WAY;
        ReConnectDevMsg reConnectDevMsg = new ReConnectDevMsg(f, a2);
        this.w.setReConnectDevMsg(reConnectDevMsg);
        String str = this.TAG;
        JL_Log.d(str, "readyToReconnectDevice : flag = " + (z ? 1 : 0) + ", " + reConnectDevMsg);
        a(new ReconnectParam(bluetoothDevice.getAddress(), f, a2));
        this.H.removeMessages(R);
        this.H.sendEmptyMessageDelayed(R, J);
        this.v.changeCommunicationWay(f, z ? 1 : 0, new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.8
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("readyToReconnectDevice", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(Integer num) {
                BluetoothOTAManager.this.H.removeMessages(BluetoothOTAManager.R);
                if (BluetoothOTAManager.this.w.isWaitingForUpdate()) {
                    BluetoothOTAManager.this.w.setReconnectUseADV(num.intValue() == 1);
                }
                if (BluetoothOTAManager.this.G != null) {
                    BluetoothOTAManager.this.G.setFlag(num.intValue());
                    BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                    bluetoothOTAManager.a(bluetoothDevice, bluetoothOTAManager.G);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(final BluetoothDevice bluetoothDevice) {
        if (a("upgradePrepare")) {
            return;
        }
        if (getDeviceInfo(bluetoothDevice) == null) {
            this.v.getDeviceInfo(new IActionCallback<TargetInfoResponse>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.5
                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onError(BaseError baseError) {
                    BluetoothOTAManager.this.a("upgradePrepare", baseError);
                }

                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onSuccess(TargetInfoResponse targetInfoResponse) {
                    BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceTargetInfo(bluetoothDevice, targetInfoResponse);
                    BluetoothOTAManager.this.n(bluetoothDevice);
                }
            });
        } else {
            n(bluetoothDevice);
        }
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public TargetInfoResponse getDeviceInfo() {
        return getDeviceInfo(getConnectedBtDevice());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        this.z = z;
    }

    @SuppressLint({"MissingPermission"})
    private boolean d(BluetoothDevice bluetoothDevice, int i) {
        if (!CommonUtil.checkHasConnectPermission(this.context)) {
            JL_Log.e(this.TAG, "--requestBleMtu-- no connect permission");
            return false;
        }
        BluetoothGatt connectedBluetoothGatt = getConnectedBluetoothGatt();
        if (connectedBluetoothGatt != null && BluetoothUtil.deviceEquals(connectedBluetoothGatt.getDevice(), bluetoothDevice)) {
            if (Build.VERSION.SDK_INT >= 21) {
                JL_Log.e(this.TAG, "--requestBleMtu-- requestMtu is started.");
                if (connectedBluetoothGatt.requestMtu(i + 3)) {
                    return true;
                }
                JL_Log.e(this.TAG, "--requestBleMtu-- requestMtu failed. callback old mtu.");
                onBleDataBlockChanged(bluetoothDevice, 20, 4115);
                return false;
            }
            JL_Log.e(this.TAG, "--requestBleMtu-- android sdk not support requestMtu method.");
            onBleDataBlockChanged(bluetoothDevice, 20, 4115);
            return true;
        }
        JL_Log.e(this.TAG, "--requestBleMtu-- device is disconnected.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        JL_Log.w(this.TAG, "callbackCancelOTA : ");
        q();
        this.y.onCancelOTA();
        this.H.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.d
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.k();
            }
        }, 100L);
    }

    private int f(BluetoothDevice bluetoothDevice) {
        int priority = this.mBluetoothOption.getPriority();
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null || deviceInfo.isSupportDoubleBackup()) {
            return priority;
        }
        int singleBackupOtaWay = deviceInfo.getSingleBackupOtaWay();
        if (singleBackupOtaWay != 1) {
            if (singleBackupOtaWay == 2) {
                return 1;
            }
            if (deviceInfo.getSdkType() < 2) {
                return priority;
            }
        }
        return 0;
    }

    @SuppressLint({"MissingPermission"})
    private void g(@NonNull final BluetoothDevice bluetoothDevice) {
        JL_Log.d(this.TAG, "-getDeviceInfoWithConnection- start....");
        this.v.getDeviceInfo(new IActionCallback<TargetInfoResponse>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.4
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a(bluetoothDevice, baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(TargetInfoResponse targetInfoResponse) {
                BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceTargetInfo(bluetoothDevice, targetInfoResponse);
                if (targetInfoResponse.isSupportMD5()) {
                    BluetoothOTAManager.this.v.getMD5(new IActionCallback<String>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.4.1
                        @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                        public void onError(BaseError baseError) {
                            String str = BluetoothOTAManager.this.TAG;
                            JL_Log.i(str, "getDevMD5 failed, " + baseError);
                        }

                        @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                        public void onSuccess(String str) {
                            String str2 = BluetoothOTAManager.this.TAG;
                            JL_Log.i(str2, "getDevMD5 ok, MD5 : " + str);
                            AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                            BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceMD5(bluetoothDevice, str);
                        }
                    });
                }
                if (!targetInfoResponse.isMandatoryUpgrade()) {
                    BluetoothOTAManager.this.p();
                    if (BluetoothOTAManager.this.isOTA()) {
                        BluetoothOTAManager.this.H.sendEmptyMessage(BluetoothOTAManager.S);
                    }
                } else {
                    String str = BluetoothOTAManager.this.TAG;
                    JL_Log.w(str, "getDeviceInfoWithConnection >>>> sdkType : " + targetInfoResponse.getSdkType());
                    BluetoothOTAManager.this.d(bluetoothDevice);
                    if (targetInfoResponse.getSdkType() >= 2 && CommonUtil.checkHasConnectPermission(BluetoothOTAManager.this.context) && Build.VERSION.SDK_INT >= 21 && BluetoothOTAManager.this.getConnectedBluetoothGatt() != null) {
                        boolean requestConnectionPriority = BluetoothOTAManager.this.getConnectedBluetoothGatt().requestConnectionPriority(1);
                        String str2 = BluetoothOTAManager.this.TAG;
                        JL_Log.w(str2, "-getDeviceInfoWithConnection- requestConnectionPriority :: ret : " + requestConnectionPriority);
                    }
                }
                BluetoothOTAManager.this.c(bluetoothDevice);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(BluetoothDevice bluetoothDevice) {
        if (this.w.isWaitingForUpdate()) {
            this.H.removeMessages(Q);
            if (isConnectedDevice(bluetoothDevice)) {
                Handler handler = this.H;
                handler.sendMessageDelayed(handler.obtainMessage(Q, bluetoothDevice), 5000L);
                disconnectBluetoothDevice(bluetoothDevice);
                return;
            }
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i(BluetoothDevice bluetoothDevice) {
        return isConnectedDevice(bluetoothDevice) && getConnectedBluetoothGatt() != null && BluetoothUtil.deviceEquals(getConnectedBluetoothGatt().getDevice(), bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(BluetoothDevice bluetoothDevice) {
        boolean removeBond = BluetoothUtil.removeBond(this.context, bluetoothDevice);
        String str = this.TAG;
        JL_Log.i(str, "-startUpgradeReConnect- removeBond >>> " + removeBond);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.H.hasMessages(S)) {
            this.H.removeMessages(S);
            this.H.sendEmptyMessage(S);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            JL_Log.e(this.TAG, "-handleConnectedEvent- device is null.");
            return;
        }
        JL_Log.d(this.TAG, String.format(Locale.getDefault(), "-handleConnectedEvent- device : %s, way = %d", printBtDeviceInfo(bluetoothDevice), Integer.valueOf(i)));
        if (i == 0) {
            this.H.removeMessages(N);
        }
        setConnectedBtDevice(bluetoothDevice);
        g(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDevice bluetoothDevice, int i) {
        String str = this.TAG;
        JL_Log.i(str, "-notifyConnectionStatus- device : " + printBtDeviceInfo(bluetoothDevice) + ", status : " + i);
        if (i != 3) {
            if (i == 1 || i == 4) {
                JL_Log.i(this.TAG, "-notifyConnectionStatus- handler connected event.");
            } else if (i == 2 || i == 0) {
                JL_Log.w(this.TAG, "-notifyConnectionStatus- handler disconnect event.");
                o();
                t();
                this.mDeviceStatusCache.removeDeviceStatus(bluetoothDevice);
                n();
            }
        }
        onConnection(bluetoothDevice, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        JL_Log.i(this.TAG, "callbackStopOTA : ");
        q();
        this.y.onStopOTA();
        this.H.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.e
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.m();
            }
        }, 100L);
    }

    private void i() {
        if (a("exitUpdateMode")) {
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo();
        if (deviceInfo != null && deviceInfo.isSupportDoubleBackup()) {
            c(false);
            this.v.exitUpdateMode(new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.11
                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onError(BaseError baseError) {
                    BluetoothOTAManager.this.c(true);
                    BluetoothOTAManager.this.a("exitUpdateMode", baseError);
                }

                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onSuccess(Integer num) {
                    String str = BluetoothOTAManager.this.TAG;
                    JL_Log.d(str, "exitUpdateMode : onSuccess >>>" + num);
                    if (num.intValue() == 0) {
                        BluetoothOTAManager.this.e();
                        return;
                    }
                    int intValue = num.intValue();
                    onError(OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, intValue, "Device return a bad code : " + num));
                }
            });
            return;
        }
        JL_Log.i(this.TAG, String.format(Locale.getDefault(), "exitUpdateMode : device[%s] is single flash ota, so ota progress cannot be interrupted.", getConnectedBtDevice()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(BluetoothDevice bluetoothDevice) {
        if (a("checkUpgradeEnvironment")) {
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        JL_Log.d(this.TAG, String.format(Locale.getDefault(), "checkUpgradeEnvironment >> device : %s, deviceInfo ：%s", printBtDeviceInfo(bluetoothDevice), deviceInfo));
        if (deviceInfo == null) {
            a("checkUpgradeEnvironment", OTAError.buildError(4114));
        } else if (deviceInfo.isSupportDoubleBackup()) {
            p();
            u();
        } else if (deviceInfo.isNeedBootLoader()) {
            d(bluetoothDevice);
            s();
        } else if (deviceInfo.isMandatoryUpgrade()) {
            u();
        } else {
            k(bluetoothDevice);
        }
    }

    private void f() {
        JL_Log.i(this.TAG, "callbackStartOTA : ");
        resetTotalTime();
        this.y.onStartOTA();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.y.setUpgradeCallback(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final BluetoothDevice bluetoothDevice, int i, int i2) {
        if (a("upgradeStep02")) {
            return;
        }
        if (i2 >= 0 && i >= 0) {
            this.v.inquiryDeviceCanOTA(i2 > 0 ? a(i, i2) : new byte[]{CHexConver.intToByte(this.mBluetoothOption.getPriority())}, new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.7
                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onError(BaseError baseError) {
                    BluetoothOTAManager.this.a("upgradeStep02", baseError);
                }

                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onSuccess(Integer num) {
                    BaseError buildError;
                    JL_Log.i(BluetoothOTAManager.this.TAG, String.format(Locale.getDefault(), "Step2.发送升级文件校验信息，确认是否可以升级, 结果: %d", num));
                    if (num.intValue() == 0) {
                        BluetoothOTAManager.this.e(bluetoothDevice);
                        return;
                    }
                    int intValue = num.intValue();
                    if (intValue == 1) {
                        buildError = OTAError.buildError(16386);
                    } else if (intValue == 2) {
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_CHECK_UPGRADE_FILE, "Command E2, result = " + num);
                    } else if (intValue == 3) {
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_FILE_VERSION_SAME);
                    } else if (intValue == 4) {
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_TWS_NOT_CONNECT);
                    } else if (intValue != 5) {
                        int intValue2 = num.intValue();
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, intValue2, "upgradeStep2 :: Unknown error : " + num);
                    } else {
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_HEADSET_NOT_IN_CHARGING_BIN);
                    }
                    onError(buildError);
                }
            });
        } else {
            a("upgradeStep02", OTAError.buildError(4097, String.format(Locale.getDefault(), "upgradeStep02: offset = %d, len = %d", Integer.valueOf(i), Integer.valueOf(i2))));
        }
    }

    private void h() {
        this.H.removeMessages(O);
        this.H.sendEmptyMessageDelayed(O, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDevice bluetoothDevice) {
        c(bluetoothDevice, 1);
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null || isOTA()) {
            return;
        }
        if (deviceInfo.isMandatoryUpgrade() || deviceInfo.getRequestOtaFlag() == 1) {
            this.mBtEventCbHelper.onMandatoryUpgrade(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ReconnectParam reconnectParam) {
        this.G = reconnectParam;
    }

    private byte[] a(int i, int i2) {
        if (this.A != null && this.A.length > 0 && i >= 0 && i2 >= 0) {
            byte[] bArr = new byte[i2];
            if (i + i2 <= this.A.length) {
                System.arraycopy(this.A, i, bArr, 0, i2);
                return bArr;
            }
        }
        return new byte[0];
    }

    private void d(boolean z) {
        if (this.C > 0) {
            this.D = CommonUtil.getCurrentTime() - this.C;
            if (z) {
                this.C = 0L;
            }
        }
    }

    private float a(int i) {
        int i2 = this.E;
        if (i2 > 0) {
            float f = (i * 100.0f) / i2;
            if (f >= 100.0f) {
                return 99.9f;
            }
            return f;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(BluetoothDevice bluetoothDevice) {
        if (this.mDeviceStatusCache.getMaxCommunicationMtu(bluetoothDevice) < 530) {
            this.mDeviceStatusCache.updateDeviceMaxCommunicationMtu(bluetoothDevice, 530);
        }
    }

    private void a(final BluetoothDevice bluetoothDevice, String str) {
        if (!j()) {
            a("startReadFileThread", OTAError.buildError(4114));
            return;
        }
        f();
        new ReadFileThread(str, new IActionCallback<byte[]>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.3
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("ReadFileThread", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(byte[] bArr) {
                String str2 = BluetoothOTAManager.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("ReadFileThread >>> onSuccess, length = ");
                sb.append(bArr == null ? 0 : bArr.length);
                JL_Log.i(str2, sb.toString());
                BluetoothOTAManager.this.A = bArr;
                BluetoothOTAManager.this.m(bluetoothDevice);
            }
        }).start();
    }

    private String a(BluetoothDevice bluetoothDevice, int i) {
        String address = bluetoothDevice.getAddress();
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null || deviceInfo.isSupportDoubleBackup()) {
            return address;
        }
        String edrAddr = i == 1 ? deviceInfo.getEdrAddr() : deviceInfo.getBleAddr();
        return (!BluetoothAdapter.checkBluetoothAddress(edrAddr) || edrAddr.equals(address)) ? address : edrAddr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final BluetoothDevice bluetoothDevice, ReconnectParam reconnectParam) {
        JL_Log.d(this.TAG, String.format(Locale.getDefault(), "-startUpgradeReConnect- device : %s, ReconnectParam = %s", printBtDeviceInfo(bluetoothDevice), reconnectParam));
        if (bluetoothDevice == null || reconnectParam == null) {
            return;
        }
        boolean isConnectedDevice = isConnectedDevice(bluetoothDevice);
        String str = this.TAG;
        JL_Log.i(str, "-startUpgradeReConnect- isConnectedDevice = " + isConnectedDevice);
        if (!isConnectedDevice) {
            h();
            return;
        }
        boolean i = i(bluetoothDevice);
        String str2 = this.TAG;
        JL_Log.i(str2, "-startUpgradeReConnect- isBLEConnected = " + i);
        if (i) {
            JL_Log.d(this.TAG, "-startUpgradeReConnect- waiting for ble disconnect... ");
            h();
            return;
        }
        boolean z = isConnectedByProfile(bluetoothDevice) == 2;
        String str3 = this.TAG;
        JL_Log.d(str3, "-startUpgradeReConnect- isEDRConnected : " + z);
        if (!z) {
            JL_Log.d(this.TAG, "-startUpgradeReConnect- disconnectBluetoothDevice >>> ");
            disconnectBluetoothDevice(bluetoothDevice);
            return;
        }
        boolean disconnectByProfiles = disconnectByProfiles(bluetoothDevice);
        String str4 = this.TAG;
        JL_Log.i(str4, "-startUpgradeReConnect- disconnectEdrRet : " + disconnectByProfiles);
        if (disconnectByProfiles) {
            return;
        }
        JL_Log.i(this.TAG, "-startUpgradeReConnect- disconnect edr failed. disconnectSPPDevice.");
        disconnectBluetoothDevice(bluetoothDevice);
        this.H.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.f
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.j(bluetoothDevice);
            }
        }, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, boolean z) {
        if (a("callbackReconnectEvent")) {
            return;
        }
        String str2 = this.TAG;
        JL_Log.i(str2, "callbackReconnectEvent : " + str + ", " + z);
        this.y.onNeedReconnect(str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice, float f) {
        if (a("callbackProgress")) {
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        a((deviceInfo == null || deviceInfo.isNeedBootLoader()) ? 0 : 1, f);
    }

    private void a(int i, float f) {
        if (a("callbackProgress")) {
            return;
        }
        String str = this.TAG;
        JL_Log.d(str, "callbackProgress : type = " + i + ", progress = " + f);
        d(false);
        this.y.onProgress(i, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, BaseError baseError) {
        if (a("callbackError") || baseError == null) {
            return;
        }
        JL_Log.e(this.TAG, String.format(Locale.getDefault(), "callbackError : %s --> %s", str, baseError));
        q();
        this.y.onError(baseError);
        this.H.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.c
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.l();
            }
        }, 100L);
    }

    private void a(BluetoothDevice bluetoothDevice, int i, int i2) {
        if (bluetoothDevice == null || i2 != 0) {
            return;
        }
        boolean z = true;
        if (i != 1 ? !(i != 2 || isConnectedByHfp(bluetoothDevice) == 0) : isConnectedByA2dp(bluetoothDevice) != 0) {
            z = false;
        }
        if (z && this.H.hasMessages(Q)) {
            h(bluetoothDevice);
        }
    }

    private void a(FirmwareUpdateBlockCmd firmwareUpdateBlockCmd, int i, int i2) {
        if (a("upgradeStep04")) {
            return;
        }
        t();
        if (i == 0 && i2 == 0) {
            JL_Log.i(this.TAG, "read data over.");
            firmwareUpdateBlockCmd.setParam(null);
            firmwareUpdateBlockCmd.setStatus(0);
            sendCommandResponse(firmwareUpdateBlockCmd);
            v();
            return;
        }
        byte[] a2 = a(i, i2);
        String str = this.TAG;
        JL_Log.i(str, "read data, offset = " + i + ", length = " + i2 + ", data len = " + a2.length);
        if (a2.length > 0) {
            firmwareUpdateBlockCmd.setParam(new FirmwareUpdateBlockResponseParam(a2));
            firmwareUpdateBlockCmd.setStatus(0);
            sendCommandResponse(firmwareUpdateBlockCmd);
            s();
            return;
        }
        a("upgradeStep04", OTAError.buildError(ErrorCode.SUB_ERR_OFFSET_OVER, "offset = " + i + ", length = " + i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice, BaseError baseError) {
        String str = this.TAG;
        JL_Log.i(str, "-callbackConnectFailed- device ：" + printBtDeviceInfo(bluetoothDevice) + " , error : " + baseError);
        c(bluetoothDevice, 2);
        a("callbackConnectFailed", baseError);
    }

    private boolean a(String str) {
        if (isOTA()) {
            return false;
        }
        String str2 = this.TAG;
        JL_Log.w(str2, str + ": OTA process has exited.");
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(BluetoothDevice bluetoothDevice, CommandBase commandBase, boolean z) {
        int id = commandBase.getId();
        if (id == 194) {
            boolean isOTA = isOTA();
            boolean hasMessages = this.H.hasMessages(P);
            String str = this.TAG;
            JL_Log.d(str, "Receive C2 command : isOTA = " + isOTA + ", hasStopAdvNotify = " + hasMessages);
            if (!isOTA || hasMessages) {
                return;
            }
            this.H.sendEmptyMessageDelayed(P, 3000L);
            this.v.stopADVInfo(null);
        } else if (id == 209) {
            CommandBase commandBase2 = (SettingsMtuCmd) commandBase;
            SettingsMtuParam settingsMtuParam = (SettingsMtuParam) commandBase2.getParam();
            if (settingsMtuParam == null) {
                JL_Log.d(this.TAG, "Receive D1 command : command is error.");
                if (z) {
                    commandBase2.setStatus(1);
                    sendCommandResponse(commandBase2);
                    return;
                }
                return;
            }
            int mtu = settingsMtuParam.getMtu();
            int maxCommunicationMtu = this.mDeviceStatusCache.getMaxCommunicationMtu(bluetoothDevice);
            if (mtu >= 530) {
                this.mDeviceStatusCache.updateDeviceMaxCommunicationMtu(bluetoothDevice, mtu);
            } else {
                mtu = maxCommunicationMtu;
            }
            if (z) {
                settingsMtuParam.setMtu(mtu);
                commandBase2.setStatus(0);
                sendCommandResponse(commandBase2);
            }
        } else if (id == 229) {
            t();
            FirmwareUpdateBlockCmd firmwareUpdateBlockCmd = (FirmwareUpdateBlockCmd) commandBase;
            if (a("Receive E5 command")) {
                firmwareUpdateBlockCmd.setParam(null);
                firmwareUpdateBlockCmd.setStatus(1);
                sendCommandResponse(firmwareUpdateBlockCmd);
                return;
            }
            FirmwareUpdateBlockParam firmwareUpdateBlockParam = (FirmwareUpdateBlockParam) firmwareUpdateBlockCmd.getParam();
            if (firmwareUpdateBlockParam == null) {
                JL_Log.d(this.TAG, "Receive E5 command : command is error.");
                firmwareUpdateBlockCmd.setStatus(1);
                sendCommandResponse(firmwareUpdateBlockCmd);
                a("Receive E5 command", OTAError.buildError(12293, "E5 command"));
                return;
            }
            int nextUpdateBlockOffsetAddr = firmwareUpdateBlockParam.getNextUpdateBlockOffsetAddr();
            int nextUpdateBlockLen = firmwareUpdateBlockParam.getNextUpdateBlockLen();
            if (this.E > 0) {
                int i = this.F + nextUpdateBlockLen;
                this.F = i;
                a(bluetoothDevice, a(i));
            }
            a(firmwareUpdateBlockCmd, nextUpdateBlockOffsetAddr, nextUpdateBlockLen);
        } else if (id != 232) {
        } else {
            CommandBase commandBase3 = (NotifyUpdateContentSizeCmd) commandBase;
            if (a("Receive E8 command ")) {
                commandBase3.setParam(null);
                commandBase3.setStatus(1);
                sendCommandResponse(commandBase3);
                return;
            }
            String str2 = this.TAG;
            JL_Log.e(str2, "Receive E8 command : " + commandBase3);
            NotifyUpdateContentSizeParam notifyUpdateContentSizeParam = (NotifyUpdateContentSizeParam) commandBase3.getParam();
            if (notifyUpdateContentSizeParam == null) {
                JL_Log.d(this.TAG, "Receive E8 command : command is error.");
                commandBase3.setStatus(1);
                sendCommandResponse(commandBase3);
                a("Receive E8 command", OTAError.buildError(12293, "E8 command"));
                return;
            }
            int contentSize = notifyUpdateContentSizeParam.getContentSize();
            if (contentSize >= 0) {
                this.C = CommonUtil.getCurrentTime();
                int currentProgress = notifyUpdateContentSizeParam.getCurrentProgress();
                this.F = currentProgress;
                this.E = contentSize;
                a(bluetoothDevice, a(currentProgress));
                commandBase3.setStatus(0);
                commandBase3.setParam(null);
                sendCommandResponse(commandBase3);
                return;
            }
            String str3 = this.TAG;
            JL_Log.w(str3, "Receive E8 command : length = " + contentSize);
            a("Receive E8 command", OTAError.buildError(4097, "Update content size is error. " + contentSize));
        }
    }

    private void a(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        if (commandBase.getStatus() != 0) {
            return;
        }
        int id = commandBase.getId();
        if (id == 209) {
            SettingsMtuResponse settingsMtuResponse = (SettingsMtuResponse) ((SettingsMtuCmd) commandBase).getResponse();
            if (settingsMtuResponse != null) {
                this.mDeviceStatusCache.updateDeviceMaxCommunicationMtu(bluetoothDevice, settingsMtuResponse.getRealMtu());
            }
        } else if (id != 227) {
            if (id != 231) {
                return;
            }
            JL_Log.e(this.TAG, "handleResponseCommand :: reboot >>> ");
            disconnectBluetoothDevice(bluetoothDevice);
        } else {
            EnterUpdateModeResponse enterUpdateModeResponse = (EnterUpdateModeResponse) ((EnterUpdateModeCmd) commandBase).getResponse();
            if (enterUpdateModeResponse == null || enterUpdateModeResponse.getCanUpdateFlag() != 0) {
                return;
            }
            d(bluetoothDevice);
        }
    }
}
