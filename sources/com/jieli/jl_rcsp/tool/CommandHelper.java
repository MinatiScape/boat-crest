package com.jieli.jl_rcsp.tool;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import java.util.HashMap;
/* loaded from: classes11.dex */
public class CommandHelper {
    private static volatile CommandHelper b;

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, CommandBase> f12439a = new HashMap<>();

    @SuppressLint({"UseSparseArrays"})
    private CommandHelper() {
    }

    public static String getCacheCommandKey(BluetoothDevice bluetoothDevice, int i, int i2) {
        String str;
        if (bluetoothDevice != null) {
            str = bluetoothDevice.getAddress() + "-";
        } else {
            str = "";
        }
        String str2 = str + String.valueOf(i);
        if (i2 >= 0) {
            return str2 + "-" + i2;
        }
        return str2;
    }

    public static CommandHelper getInstance() {
        if (b == null) {
            synchronized (CommandHelper.class) {
                if (b == null) {
                    b = new CommandHelper();
                }
            }
        }
        return b;
    }

    public CommandBase getCommand(BluetoothDevice bluetoothDevice, int i, int i2) {
        return this.f12439a.get(getCacheCommandKey(bluetoothDevice, i, i2));
    }

    public void putCommandBase(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        if (commandBase != null) {
            if (commandBase.getType() == 2 || commandBase.getType() == 3) {
                this.f12439a.put(getCacheCommandKey(bluetoothDevice, commandBase.getId(), commandBase.getOpCodeSn()), commandBase);
            }
        }
    }

    public void release() {
        this.f12439a.clear();
        b = null;
    }

    public void removeCommandBase(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        if (basePacket != null) {
            removeCommandBase(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
        }
    }

    public void removeCommandBase(BluetoothDevice bluetoothDevice, int i, int i2) {
        this.f12439a.remove(getCacheCommandKey(bluetoothDevice, i, i2));
    }
}
