package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_bt_ota.interfaces.IBluetoothCallback;
import com.jieli.jl_bt_ota.model.BleScanMessage;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.base.CommandBase;
/* loaded from: classes11.dex */
public class BtEventCallbackHelper extends BaseCallbackHelper<IBluetoothCallback> implements IBluetoothCallback {
    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onA2dpStatus(final BluetoothDevice bluetoothDevice, final int i) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.d
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onA2dpStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onAdapterStatus(final boolean z, final boolean z2) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.k
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onAdapterStatus(z, z2);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onBleDataBlockChanged(final BluetoothDevice bluetoothDevice, final int i, final int i2) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.g
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onBleDataBlockChanged(bluetoothDevice, i, i2);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onBtDeviceConnection(final BluetoothDevice bluetoothDevice, final int i) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.c
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onBtDeviceConnection(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onConnection(final BluetoothDevice bluetoothDevice, final int i) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.f
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onConnection(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onDiscovery(final BluetoothDevice bluetoothDevice, final BleScanMessage bleScanMessage) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.h
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onDiscovery(bluetoothDevice, bleScanMessage);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onDiscoveryStatus(final boolean z, final boolean z2) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.b
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onDiscoveryStatus(z, z2);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onError(final BaseError baseError) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.j
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onError(BaseError.this);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onHfpStatus(final BluetoothDevice bluetoothDevice, final int i) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.e
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onHfpStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onMandatoryUpgrade(final BluetoothDevice bluetoothDevice) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.a
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onMandatoryUpgrade(bluetoothDevice);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
    public void onReceiveCommand(final BluetoothDevice bluetoothDevice, final CommandBase commandBase) {
        callbackEvent(new ICallbackHandler() { // from class: com.jieli.jl_bt_ota.tool.i
            @Override // com.jieli.jl_bt_ota.tool.ICallbackHandler
            public final void onHandle(Object obj) {
                ((IBluetoothCallback) obj).onReceiveCommand(bluetoothDevice, commandBase);
            }
        });
    }
}
