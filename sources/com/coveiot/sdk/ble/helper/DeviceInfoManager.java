package com.coveiot.sdk.ble.helper;

import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.utils.CommandClass;
import com.coveiot.sdk.ble.utils.CommandNames;
/* loaded from: classes9.dex */
public class DeviceInfoManager {

    /* renamed from: a  reason: collision with root package name */
    public static DeviceInfoManager f7572a;

    public static DeviceInfoManager getInstance() {
        if (f7572a == null) {
            f7572a = new DeviceInfoManager();
        }
        return f7572a;
    }

    public void geFirmwareVersion() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.GET_FIRMWARE_VERSION));
    }

    public void getBatteryLevel() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.GET_BATTERY_LEVEL));
    }

    public void getBluetoothDevice() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.GET_BLUETOOTH_DEVICE));
    }

    public void getDeviceConnectionStatus() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.GET_CONNECTION_STATUS));
    }

    public void getDeviceInfo() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.DEVICE_INFO));
    }

    public void getDeviceMode() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.GET_DEVICE_MODE));
    }

    public void getDeviceName() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.GET_DEVICE_NAME));
    }

    public void getHardwareVersion() {
        BleEventBusManager.getInstance().getEventBus().post(new CommandClass(CommandNames.GET_HARDWARE_VERSION));
    }
}
