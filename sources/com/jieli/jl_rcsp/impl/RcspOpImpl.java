package com.jieli.jl_rcsp.impl;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_rcsp.constant.Command;
import com.jieli.jl_rcsp.constant.RcspErrorCode;
import com.jieli.jl_rcsp.impl.RcspOpImpl;
import com.jieli.jl_rcsp.interfaces.bluetooth.CmdSnGenerator;
import com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy;
import com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp;
import com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback;
import com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener;
import com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback;
import com.jieli.jl_rcsp.model.DataInfo;
import com.jieli.jl_rcsp.model.Option;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.command.SettingsMtuCmd;
import com.jieli.jl_rcsp.model.command.status.GetTargetInfoCmd;
import com.jieli.jl_rcsp.model.command.sys.GetSysInfoCmd;
import com.jieli.jl_rcsp.model.command.sys.UpdateSysInfoCmd;
import com.jieli.jl_rcsp.model.command.watch.HealthSettingCmd;
import com.jieli.jl_rcsp.model.device.DeviceInfo;
import com.jieli.jl_rcsp.model.device.DeviceStatus;
import com.jieli.jl_rcsp.model.parameter.SettingsMtuParam;
import com.jieli.jl_rcsp.model.parameter.UpdateSysInfoParam;
import com.jieli.jl_rcsp.model.response.SettingsMtuResponse;
import com.jieli.jl_rcsp.model.response.SysInfoResponse;
import com.jieli.jl_rcsp.model.response.TargetInfoResponse;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.tool.DataHandlerCache;
import com.jieli.jl_rcsp.tool.DeviceStatusManager;
import com.jieli.jl_rcsp.tool.HealthDataParseHelper;
import com.jieli.jl_rcsp.tool.RcspCallbackManager;
import com.jieli.jl_rcsp.tool.RcspDataHandler;
import com.jieli.jl_rcsp.tool.RcspEventListenerManager;
import com.jieli.jl_rcsp.tool.SnGenerator;
import com.jieli.jl_rcsp.tool.datahandles.DataHandler;
import com.jieli.jl_rcsp.tool.datahandles.DataHandlerModify;
import com.jieli.jl_rcsp.tool.datahandles.DataHandlerOld;
import com.jieli.jl_rcsp.tool.datahandles.ParseHelper;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.CommandBuilder;
import com.jieli.jl_rcsp.util.JL_Log;
import com.jieli.jl_rcsp.util.RcspUtil;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class RcspOpImpl implements IBluetoothProxy, IRcspOp {
    private static final String c = "jl_rcsp";
    public static boolean sUseNewDataHandler = true;

    /* renamed from: a  reason: collision with root package name */
    private BluetoothDevice f12435a;
    private final HealthDataParseHelper b;
    public CmdSnGenerator mCmdSnGenerator;
    public final Handler mHandler;
    public final Option mOption;
    public final RcspCallbackManager mRcspCallbackManager;
    public final RcspDataHandler mRcspDataHandler;
    public final RcspEventListenerManager mRcspEventListenerManager;
    public final DeviceStatusManager mStatusManager;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.jieli.jl_rcsp.impl.RcspOpImpl$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1<T> implements RcspCommandCallback<T> {

        /* renamed from: a  reason: collision with root package name */
        public int f12436a = 0;
        public final /* synthetic */ int b;
        public final /* synthetic */ RcspCommandCallback c;

        public AnonymousClass1(int i, RcspCommandCallback rcspCommandCallback) {
            this.b = i;
            this.c = rcspCommandCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(BluetoothDevice bluetoothDevice, CommandBase commandBase, int i, RcspCommandCallback rcspCommandCallback) {
            RcspOpImpl.this.sendCommandAsync(bluetoothDevice, commandBase, i, rcspCommandCallback);
        }

        /* JADX WARN: Incorrect types in method signature: (Landroid/bluetooth/BluetoothDevice;TT;)V */
        @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
        public void onCommandResponse(final BluetoothDevice bluetoothDevice, final CommandBase commandBase) {
            if (3 == commandBase.getStatus()) {
                int i = this.f12436a + 1;
                this.f12436a = i;
                if (i < 3) {
                    Handler handler = RcspOpImpl.this.mHandler;
                    final int i2 = this.b;
                    handler.postDelayed(new Runnable() { // from class: com.jieli.jl_rcsp.impl.g
                        @Override // java.lang.Runnable
                        public final void run() {
                            RcspOpImpl.AnonymousClass1.this.a(bluetoothDevice, commandBase, i2, this);
                        }
                    }, 500L);
                    return;
                }
            }
            this.f12436a = 0;
            RcspCommandCallback rcspCommandCallback = this.c;
            if (rcspCommandCallback != null) {
                rcspCommandCallback.onCommandResponse(bluetoothDevice, commandBase);
            }
        }

        @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
        public void onErrCode(BluetoothDevice bluetoothDevice, BaseError baseError) {
            this.f12436a = 0;
            RcspCommandCallback rcspCommandCallback = this.c;
            if (rcspCommandCallback != null) {
                rcspCommandCallback.onErrCode(bluetoothDevice, baseError);
            }
        }
    }

    public RcspOpImpl() {
        this(Option.createOption());
    }

    private int b(BluetoothDevice bluetoothDevice) {
        if (this.mCmdSnGenerator == null) {
            this.mCmdSnGenerator = SnGenerator.getInstance();
        }
        return this.mCmdSnGenerator.getRcspCmdSeq(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDevice bluetoothDevice) {
        DeviceStatus deviceStatus = this.mStatusManager.getDeviceStatus(bluetoothDevice);
        DeviceInfo deviceInfo = deviceStatus == null ? null : deviceStatus.getDeviceInfo();
        if (deviceInfo == null) {
            e(bluetoothDevice);
        } else if (deviceInfo.isMandatoryUpgrade()) {
            this.mStatusManager.updateDeviceMaxCommunicationMtu(bluetoothDevice, 530);
            this.mRcspCallbackManager.onMandatoryUpgrade(bluetoothDevice);
        } else {
            this.mRcspCallbackManager.onRcspInit(bluetoothDevice, true);
        }
    }

    private void d(BluetoothDevice bluetoothDevice) {
        if (!RcspUtil.deviceEquals(this.f12435a, bluetoothDevice)) {
            boolean z = this.f12435a != null;
            this.f12435a = bluetoothDevice;
            if (z && bluetoothDevice != null) {
                this.mRcspCallbackManager.onSwitchConnectedDevice(bluetoothDevice);
            }
        }
        a(bluetoothDevice);
    }

    private void e(BluetoothDevice bluetoothDevice) {
        JL_Log.i("jl_rcsp", "-getDeviceInfo- device : " + RcspUtil.printBtDeviceInfo(bluetoothDevice));
        sendRcspCommand(bluetoothDevice, CommandBuilder.buildGetDeviceInfoCmdForAll(), new RcspCommandCallback<GetTargetInfoCmd>() { // from class: com.jieli.jl_rcsp.impl.RcspOpImpl.2
            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onErrCode(BluetoothDevice bluetoothDevice2, BaseError baseError) {
                JL_Log.w("jl_rcsp", "-getDeviceInfo- onErrCode = " + baseError);
                RcspOpImpl.this.a(bluetoothDevice2, baseError);
                RcspOpImpl.this.mRcspCallbackManager.onRcspInit(bluetoothDevice2, false);
            }

            @Override // com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback
            public void onCommandResponse(BluetoothDevice bluetoothDevice2, GetTargetInfoCmd getTargetInfoCmd) {
                JL_Log.i("jl_rcsp", "-getDeviceInfo- cmd = " + getTargetInfoCmd);
                if (getTargetInfoCmd.getStatus() == 0) {
                    TargetInfoResponse targetInfoResponse = (TargetInfoResponse) getTargetInfoCmd.getResponse();
                    if (targetInfoResponse != null) {
                        if (RcspOpImpl.this.mStatusManager.getDeviceInfo(bluetoothDevice2) == null) {
                            RcspOpImpl.this.mStatusManager.updateDeviceTargetInfo(bluetoothDevice2, targetInfoResponse);
                        }
                        RcspOpImpl.this.c(bluetoothDevice2);
                        return;
                    }
                    BaseError baseError = new BaseError(RcspErrorCode.ERR_PARSE_DATA, "Rcsp data format error.");
                    baseError.setOpCode(getTargetInfoCmd.getId());
                    RcspOpImpl.this.a(bluetoothDevice2, baseError);
                } else {
                    BaseError baseError2 = new BaseError(12292, "Device returned a bad status[" + getTargetInfoCmd.getStatus() + "].");
                    baseError2.setOpCode(getTargetInfoCmd.getId());
                    RcspOpImpl.this.a(bluetoothDevice2, baseError2);
                }
                RcspOpImpl.this.mRcspCallbackManager.onRcspInit(bluetoothDevice2, false);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy
    public void callbackErrorEvent(BaseError baseError) {
        this.mRcspCallbackManager.onRcspError(getTargetDevice(), baseError);
    }

    public DeviceInfo getDeviceInfo() {
        return getDeviceInfo(this.f12435a);
    }

    public RcspEventListenerManager getRcspEventListenerManager() {
        return this.mRcspEventListenerManager;
    }

    public BluetoothDevice getTargetDevice() {
        return this.f12435a;
    }

    public void handleRcspResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        SettingsMtuResponse settingsMtuResponse;
        if (commandBase.getStatus() != 0) {
            return;
        }
        int id = commandBase.getId();
        if (id == 3) {
            TargetInfoResponse targetInfoResponse = (TargetInfoResponse) ((GetTargetInfoCmd) commandBase).getResponse();
            if (targetInfoResponse != null) {
                this.mStatusManager.updateDeviceTargetInfo(bluetoothDevice, targetInfoResponse);
            }
        } else if (id == 7) {
            SysInfoResponse sysInfoResponse = (SysInfoResponse) ((GetSysInfoCmd) commandBase).getResponse();
            this.mRcspDataHandler.parseAttrMessage(bluetoothDevice, sysInfoResponse.getFunction(), sysInfoResponse.getAttrs());
        } else if (id != 165) {
            if (id == 209 && (settingsMtuResponse = (SettingsMtuResponse) ((SettingsMtuCmd) commandBase).getResponse()) != null) {
                this.mStatusManager.updateDeviceMaxCommunicationMtu(bluetoothDevice, settingsMtuResponse.getRealMtu());
            }
        } else {
            HealthSettingCmd.Response response = ((HealthSettingCmd) commandBase).getResponse();
            if (response instanceof HealthSettingCmd.GetResponse) {
                this.mRcspDataHandler.parseHealthSetting(bluetoothDevice, ((HealthSettingCmd.GetResponse) response).getList());
            }
        }
    }

    public boolean isRcspInit() {
        return (getConnectedDevice() == null || getDeviceInfo() == null) ? false : true;
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy
    public void notifyBtDeviceConnection(BluetoothDevice bluetoothDevice, int i) {
        JL_Log.d("jl_rcsp", String.format(Locale.getDefault(), "-notifyBtDeviceConnection- device = %s, status = %d", RcspUtil.printBtDeviceInfo(bluetoothDevice), Integer.valueOf(i)));
        a(bluetoothDevice, i);
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy
    public void notifyReceiveDeviceData(BluetoothDevice bluetoothDevice, byte[] bArr) {
        JL_Log.d("jl_rcsp", String.format(Locale.getDefault(), "-notifyReceiveDeviceData- device = %s, data = [%s]", RcspUtil.printBtDeviceInfo(bluetoothDevice), CHexConver.byte2HexStr(bArr)));
        if (bluetoothDevice != null && bArr != null) {
            if (!this.mOption.isMultiDevice() && !RcspUtil.deviceEquals(bluetoothDevice, getTargetDevice())) {
                a(bluetoothDevice, new BaseError(8193, "The device is not currently in use."));
                return;
            }
            DataHandler dataHandler = DataHandlerCache.getInstance().getDataHandler(bluetoothDevice);
            if (dataHandler == null) {
                JL_Log.e("jl_rcsp", "-onReceiveDataFromDevice- mDataHandler is null.");
                a(bluetoothDevice, new BaseError(8192, "Device not connected."));
                return;
            }
            dataHandler.addRecvData(new DataInfo().setDevice(bluetoothDevice).setType(1).setRecvData(bArr));
            return;
        }
        a(bluetoothDevice, new BaseError(4097, "param is error."));
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy
    public void receiveDataFromDevice(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        JL_Log.v("jl_rcsp", String.format(Locale.getDefault(), "-receiveDataFromDevice- device = %s, packet = %s", bluetoothDevice, basePacket));
        if (bluetoothDevice != null && basePacket != null) {
            if (this.mOption.isMultiDevice() || RcspUtil.deviceEquals(bluetoothDevice, getTargetDevice()) || (basePacket.getType() == 1 && basePacket.getOpCode() == 194)) {
                a(bluetoothDevice, basePacket);
                return;
            }
            return;
        }
        a(bluetoothDevice, new BaseError(4097, "param is error."));
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public void registerOnRcspCallback(OnRcspCallback onRcspCallback) {
        DeviceInfo deviceInfo;
        this.mRcspCallbackManager.registerRcspCallback(onRcspCallback);
        BluetoothDevice targetDevice = getTargetDevice();
        if (targetDevice == null || (deviceInfo = this.mStatusManager.getDeviceInfo(targetDevice)) == null || !deviceInfo.isMandatoryUpgrade()) {
            return;
        }
        onRcspCallback.onMandatoryUpgrade(targetDevice);
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public void registerOnRcspEventListener(OnRcspEventListener onRcspEventListener) {
        this.mRcspEventListenerManager.registerRcspEventListener(onRcspEventListener);
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public void release() {
        d(null);
        this.mRcspCallbackManager.release();
        this.mRcspEventListenerManager.release();
        this.mStatusManager.release();
        DataHandlerCache.getInstance().destroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public <T extends CommandBase> void sendCommandAsync(BluetoothDevice bluetoothDevice, CommandBase commandBase, int i, RcspCommandCallback<T> rcspCommandCallback) {
        if (commandBase == null) {
            a(bluetoothDevice, rcspCommandCallback, new BaseError(4097, "Command is null."));
        } else if (isDeviceConnected(bluetoothDevice) && DataHandlerCache.getInstance().getDataHandler(bluetoothDevice) != null) {
            commandBase.setOpCodeSn(b(bluetoothDevice));
            BasePacket convert2BasePacket = ParseHelper.convert2BasePacket(commandBase, 1);
            if (convert2BasePacket == null) {
                BaseError baseError = new BaseError(RcspErrorCode.ERR_PARSE_DATA, "Data conversion error.");
                baseError.setOpCode(commandBase.getId());
                a(bluetoothDevice, rcspCommandCallback, baseError);
                return;
            }
            CommandHelper.getInstance().putCommandBase(bluetoothDevice, commandBase);
            DataInfo rcspCmdCallback = new DataInfo().setDevice(bluetoothDevice).setType(0).setBasePacket(convert2BasePacket).setTimeoutMs(i).setRcspCmdCallback(rcspCommandCallback);
            this.mRcspCallbackManager.onPutDataToDataHandler(bluetoothDevice, ParseHelper.packSendBasePacket(convert2BasePacket));
            DataHandlerCache.getInstance().getDataHandler(bluetoothDevice).addSendData(rcspCmdCallback);
        } else {
            BaseError baseError2 = new BaseError(8192, "The device is not connected.");
            baseError2.setOpCode(commandBase.getId());
            a(bluetoothDevice, rcspCommandCallback, baseError2);
        }
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public <T extends CommandBase> void sendCommandResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase, RcspCommandCallback<T> rcspCommandCallback) {
        if (commandBase == null) {
            a(bluetoothDevice, rcspCommandCallback, new BaseError(4097, "Command is null."));
        } else if (isDeviceConnected(bluetoothDevice) && DataHandlerCache.getInstance().getDataHandler(bluetoothDevice) != null) {
            BasePacket convert2BasePacket = ParseHelper.convert2BasePacket(commandBase, 0);
            if (convert2BasePacket == null) {
                BaseError baseError = new BaseError(RcspErrorCode.ERR_PARSE_DATA, "Data conversion error.");
                baseError.setOpCode(commandBase.getId());
                a(bluetoothDevice, rcspCommandCallback, baseError);
                return;
            }
            DataHandlerCache.getInstance().getDataHandler(bluetoothDevice).addSendData(new DataInfo().setDevice(bluetoothDevice).setType(0).setBasePacket(convert2BasePacket).setRcspCmdCallback(rcspCommandCallback));
        } else {
            BaseError baseError2 = new BaseError(8192, "The device is not connected.");
            baseError2.setOpCode(commandBase.getId());
            a(bluetoothDevice, rcspCommandCallback, baseError2);
        }
    }

    public <T extends CommandBase> void sendRcspCommand(BluetoothDevice bluetoothDevice, CommandBase commandBase, RcspCommandCallback<T> rcspCommandCallback) {
        sendRcspCommand(bluetoothDevice, commandBase, 2000, rcspCommandCallback);
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy
    public void setCmdSnGenerator(CmdSnGenerator cmdSnGenerator) {
        this.mCmdSnGenerator = cmdSnGenerator;
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public void switchConnectedDevice(BluetoothDevice bluetoothDevice) {
        List<BluetoothDevice> deviceList = DataHandlerCache.getInstance().getDeviceList();
        if (bluetoothDevice == null || !deviceList.contains(bluetoothDevice) || RcspUtil.deviceEquals(this.f12435a, bluetoothDevice)) {
            return;
        }
        d(bluetoothDevice);
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public void unregisterOnRcspCallback(OnRcspCallback onRcspCallback) {
        this.mRcspCallbackManager.unregisterRcspCallback(onRcspCallback);
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.IRcspOp
    public void unregisterOnRcspEventListener(OnRcspEventListener onRcspEventListener) {
        this.mRcspEventListenerManager.unregisterRcspEventListener(onRcspEventListener);
    }

    public RcspOpImpl(Option option) {
        this.mHandler = new Handler(Looper.getMainLooper());
        Objects.requireNonNull(option, "Option can not be null.");
        this.mOption = option;
        this.mStatusManager = DeviceStatusManager.getInstance();
        this.mRcspCallbackManager = new RcspCallbackManager();
        RcspEventListenerManager rcspEventListenerManager = new RcspEventListenerManager();
        this.mRcspEventListenerManager = rcspEventListenerManager;
        this.mRcspDataHandler = new RcspDataHandler(this, rcspEventListenerManager);
        this.b = new HealthDataParseHelper(this, rcspEventListenerManager);
    }

    public DeviceInfo getDeviceInfo(BluetoothDevice bluetoothDevice) {
        return this.mStatusManager.getDeviceInfo(bluetoothDevice);
    }

    public <T extends CommandBase> void sendRcspCommand(BluetoothDevice bluetoothDevice, CommandBase commandBase, int i, RcspCommandCallback<T> rcspCommandCallback) {
        sendCommandAsync(bluetoothDevice, commandBase, i, new AnonymousClass1(i, rcspCommandCallback));
    }

    private void a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || DataHandlerCache.getInstance().getDataHandler(bluetoothDevice) != null) {
            return;
        }
        DataHandlerCache.getInstance().addDataHandler(bluetoothDevice, sUseNewDataHandler ? new DataHandlerModify(this) : new DataHandlerOld(this));
    }

    private <T extends CommandBase> void a(final BluetoothDevice bluetoothDevice, final RcspCommandCallback<T> rcspCommandCallback, final BaseError baseError) {
        if (baseError == null) {
            return;
        }
        if (rcspCommandCallback != null) {
            this.mHandler.post(new Runnable() { // from class: com.jieli.jl_rcsp.impl.f
                @Override // java.lang.Runnable
                public final void run() {
                    RcspCommandCallback.this.onErrCode(bluetoothDevice, baseError);
                }
            });
        } else {
            a(bluetoothDevice, baseError);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice, BaseError baseError) {
        JL_Log.e("jl_rcsp", String.format(Locale.getDefault(), "notifyRcspError : %s ---> %s", RcspUtil.printBtDeviceInfo(bluetoothDevice), baseError));
        this.mRcspCallbackManager.onRcspError(bluetoothDevice, baseError);
    }

    private void a(BluetoothDevice bluetoothDevice, int i) {
        this.mRcspCallbackManager.onConnectStateChange(bluetoothDevice, i);
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 4) {
                        return;
                    }
                }
            }
            if (this.f12435a == null) {
                d(bluetoothDevice);
            } else {
                a(bluetoothDevice);
            }
            c(bluetoothDevice);
            return;
        }
        this.mStatusManager.removeDeviceStatus(bluetoothDevice);
        DataHandlerCache.getInstance().removeDataHandler(bluetoothDevice);
        if (RcspUtil.deviceEquals(this.f12435a, bluetoothDevice)) {
            List<BluetoothDevice> deviceList = DataHandlerCache.getInstance().getDeviceList();
            if (deviceList.isEmpty()) {
                d(null);
            } else {
                d(deviceList.get(deviceList.size() - 1));
            }
        }
    }

    private void a(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        int opCode = basePacket.getOpCode();
        boolean z = basePacket.getType() == 1;
        CommandBase convert2Command = ParseHelper.convert2Command(bluetoothDevice, basePacket);
        if (convert2Command == null) {
            BaseError baseError = new BaseError(RcspErrorCode.ERR_PARSE_DATA, "parse data failed.");
            baseError.setOpCode(basePacket.getOpCode());
            a(bluetoothDevice, baseError);
        } else if (1 == opCode) {
            this.mRcspCallbackManager.onRcspDataCmd(bluetoothDevice, convert2Command);
        } else {
            if (z) {
                a(bluetoothDevice, convert2Command, basePacket.getHasResponse());
            } else {
                handleRcspResponse(bluetoothDevice, convert2Command);
            }
            this.b.receiveCmdFromDevice(bluetoothDevice, convert2Command);
        }
    }

    private void a(BluetoothDevice bluetoothDevice, CommandBase commandBase, int i) {
        int id = commandBase.getId();
        if (!Command.isValidCmd(id)) {
            if (i == 1) {
                CommandBase commandBase2 = new CommandBase(id, "unknownCommand");
                commandBase2.setOpCodeSn(commandBase.getOpCodeSn());
                commandBase2.setStatus(2);
                sendCommandResponse(bluetoothDevice, commandBase2, null);
                return;
            }
            return;
        }
        if (id == 9) {
            UpdateSysInfoParam updateSysInfoParam = (UpdateSysInfoParam) ((UpdateSysInfoCmd) commandBase).getParam();
            this.mRcspDataHandler.parseAttrMessage(bluetoothDevice, updateSysInfoParam.getFunction(), updateSysInfoParam.getAttrBeanList());
        } else if (id == 165) {
            HealthSettingCmd.Param param = ((HealthSettingCmd) commandBase).getParam();
            if (param instanceof HealthSettingCmd.UpdateParam) {
                this.mRcspDataHandler.parseHealthSetting(bluetoothDevice, ((HealthSettingCmd.UpdateParam) param).getList());
            }
        } else if (id == 209) {
            SettingsMtuCmd settingsMtuCmd = (SettingsMtuCmd) commandBase;
            SettingsMtuParam settingsMtuParam = (SettingsMtuParam) settingsMtuCmd.getParam();
            if (settingsMtuParam != null) {
                int mtu = settingsMtuParam.getMtu();
                this.mStatusManager.updateDeviceMaxCommunicationMtu(bluetoothDevice, mtu);
                settingsMtuCmd.setResponse(new SettingsMtuResponse(mtu));
            }
            settingsMtuCmd.setStatus(0);
            sendCommandResponse(bluetoothDevice, settingsMtuCmd, null);
        }
        this.mRcspCallbackManager.onRcspCommand(bluetoothDevice, commandBase);
    }
}
