package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class CommandCache {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, CommandBase> f12359a = new HashMap<>();

    public static String getCacheCommandKey(BluetoothDevice bluetoothDevice, int i, int i2) {
        return getCacheCommandKey(bluetoothDevice == null ? "00:00:00:00:00:00" : bluetoothDevice.getAddress(), i, i2);
    }

    public CommandBase getCommand(BluetoothDevice bluetoothDevice, int i, int i2) {
        return this.f12359a.get(getCacheCommandKey(bluetoothDevice, i, i2));
    }

    public void putCommandBase(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        if (commandBase != null) {
            if (commandBase.getType() == 2 || commandBase.getType() == 3) {
                this.f12359a.put(getCacheCommandKey(bluetoothDevice, commandBase.getId(), commandBase.getOpCodeSn()), commandBase);
            }
        }
    }

    public void release() {
        this.f12359a.clear();
    }

    public void removeCommandBase(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        if (basePacket != null) {
            removeCommandBase(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
        }
    }

    public void removeCommandBase(BluetoothDevice bluetoothDevice, int i, int i2) {
        this.f12359a.remove(getCacheCommandKey(bluetoothDevice, i, i2));
    }

    public static String getCacheCommandKey(String str, int i, int i2) {
        return String.format(Locale.getDefault(), "%s-%s-%s", str, Integer.valueOf(i), Integer.valueOf(i2));
    }
}
