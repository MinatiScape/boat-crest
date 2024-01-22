package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.jieli.jl_bt_ota.model.DeviceStatus;
import com.jieli.jl_bt_ota.model.response.TargetInfoResponse;
import java.util.HashMap;
/* loaded from: classes11.dex */
public final class DeviceStatusCache {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, DeviceStatus> f12370a = new HashMap<>();

    public void clear() {
        this.f12370a.clear();
    }

    public String getDevMD5(BluetoothDevice bluetoothDevice) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus != null) {
            return deviceStatus.getDevMD5();
        }
        return null;
    }

    public int getDeviceConnectStatus(BluetoothDevice bluetoothDevice) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus != null) {
            return deviceStatus.getStatus();
        }
        return 0;
    }

    public TargetInfoResponse getDeviceInfo(BluetoothDevice bluetoothDevice) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus != null) {
            return deviceStatus.getTargetInfo();
        }
        return null;
    }

    public DeviceStatus getDeviceStatus(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        return getDeviceStatus(bluetoothDevice.getAddress());
    }

    public int getMaxCommunicationMtu(BluetoothDevice bluetoothDevice) {
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null || deviceInfo.getCommunicationMtu() <= 0) {
            return 530;
        }
        return deviceInfo.getCommunicationMtu();
    }

    public int getMaxReceiveMtu(BluetoothDevice bluetoothDevice) {
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null || deviceInfo.getReceiveMtu() <= 0) {
            return 530;
        }
        return deviceInfo.getReceiveMtu();
    }

    public boolean isAuthBtDevice(BluetoothDevice bluetoothDevice) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus != null) {
            return deviceStatus.isAuthDevice();
        }
        return false;
    }

    public boolean isDoubleBackupUpgrade(BluetoothDevice bluetoothDevice) {
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        return deviceInfo != null && deviceInfo.isSupportDoubleBackup();
    }

    public boolean isEnterLowPowerMode(BluetoothDevice bluetoothDevice) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus != null) {
            return deviceStatus.isEnterLowPowerMode();
        }
        return false;
    }

    public boolean isMandatoryUpgrade(BluetoothDevice bluetoothDevice) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus != null) {
            return deviceStatus.isMandatoryUpgrade();
        }
        return false;
    }

    public DeviceStatus removeDeviceStatus(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        return removeDeviceStatus(bluetoothDevice.getAddress());
    }

    public void updateDeviceConnectStatus(BluetoothDevice bluetoothDevice, int i) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus == null) {
            deviceStatus = new DeviceStatus();
        }
        deviceStatus.setStatus(i);
        updateDeviceStatus(bluetoothDevice, deviceStatus);
    }

    public void updateDeviceIsAuth(BluetoothDevice bluetoothDevice, boolean z) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus == null) {
            deviceStatus = new DeviceStatus();
        }
        deviceStatus.setAuthDevice(z);
        updateDeviceStatus(bluetoothDevice, deviceStatus);
    }

    public void updateDeviceIsEnterLowPowerMode(BluetoothDevice bluetoothDevice, boolean z) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus == null) {
            deviceStatus = new DeviceStatus();
        }
        deviceStatus.setEnterLowPowerMode(z);
        updateDeviceStatus(bluetoothDevice, deviceStatus);
    }

    public void updateDeviceMD5(BluetoothDevice bluetoothDevice, String str) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        if (deviceStatus == null) {
            deviceStatus = new DeviceStatus();
        }
        deviceStatus.setDevMD5(str);
        updateDeviceStatus(bluetoothDevice, deviceStatus);
    }

    public void updateDeviceMaxCommunicationMtu(BluetoothDevice bluetoothDevice, int i) {
        TargetInfoResponse deviceInfo;
        if (i > 0 && (deviceInfo = getDeviceInfo(bluetoothDevice)) != null) {
            deviceInfo.setCommunicationMtu(i);
            updateDeviceTargetInfo(bluetoothDevice, deviceInfo);
        }
    }

    public void updateDeviceMaxReceiveMtu(BluetoothDevice bluetoothDevice, int i) {
        TargetInfoResponse deviceInfo;
        if (i > 0 && (deviceInfo = getDeviceInfo(bluetoothDevice)) != null) {
            deviceInfo.setReceiveMtu(i);
            updateDeviceTargetInfo(bluetoothDevice, deviceInfo);
        }
    }

    public void updateDeviceStatus(BluetoothDevice bluetoothDevice, DeviceStatus deviceStatus) {
        if (bluetoothDevice != null) {
            updateDeviceStatus(bluetoothDevice.getAddress(), deviceStatus);
        }
    }

    public void updateDeviceTargetInfo(BluetoothDevice bluetoothDevice, TargetInfoResponse targetInfoResponse) {
        DeviceStatus deviceStatus = getDeviceStatus(bluetoothDevice);
        boolean z = targetInfoResponse != null && targetInfoResponse.isMandatoryUpgrade();
        if (deviceStatus == null) {
            deviceStatus = new DeviceStatus();
        }
        deviceStatus.setTargetInfo(targetInfoResponse);
        deviceStatus.setMandatoryUpgrade(z);
        updateDeviceStatus(bluetoothDevice, deviceStatus);
    }

    public DeviceStatus getDeviceStatus(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f12370a.get(str);
    }

    public DeviceStatus removeDeviceStatus(String str) {
        if (BluetoothAdapter.checkBluetoothAddress(str)) {
            return this.f12370a.remove(str);
        }
        return null;
    }

    public void updateDeviceStatus(String str, DeviceStatus deviceStatus) {
        if (!BluetoothAdapter.checkBluetoothAddress(str) || deviceStatus == null) {
            return;
        }
        this.f12370a.put(str, deviceStatus);
    }
}
