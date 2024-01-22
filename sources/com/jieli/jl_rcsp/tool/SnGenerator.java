package com.jieli.jl_rcsp.tool;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.interfaces.bluetooth.CmdSnGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/* loaded from: classes11.dex */
public class SnGenerator implements CmdSnGenerator {
    private static final SnGenerator c = new SnGenerator();
    private final Map<String, Integer> b = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    private int f12493a = (new Random().nextInt(255) + 1) % 256;

    public static SnGenerator getInstance() {
        return c;
    }

    public synchronized int autoIncSN(BluetoothDevice bluetoothDevice) {
        int cmdSequence;
        cmdSequence = getCmdSequence(bluetoothDevice);
        int i = 0;
        if (bluetoothDevice != null) {
            int i2 = cmdSequence + 1;
            if (i2 < 256) {
                i = i2;
            }
            this.b.put(bluetoothDevice.getAddress(), Integer.valueOf(i));
        } else {
            int i3 = cmdSequence + 1;
            if (i3 < 256) {
                i = i3;
            }
            this.f12493a = i;
        }
        return cmdSequence;
    }

    public void destroy() {
        this.b.clear();
    }

    public int getCmdSequence(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return this.f12493a;
        }
        Integer num = this.b.get(bluetoothDevice.getAddress());
        return num == null ? this.f12493a : num.intValue();
    }

    @Override // com.jieli.jl_rcsp.interfaces.bluetooth.CmdSnGenerator
    public int getRcspCmdSeq(BluetoothDevice bluetoothDevice) {
        return autoIncSN(bluetoothDevice);
    }

    public void removeCmdSequence(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || this.b.isEmpty()) {
            return;
        }
        this.b.remove(bluetoothDevice.getAddress());
    }
}
