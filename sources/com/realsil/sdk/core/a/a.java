package com.realsil.sdk.core.a;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import java.util.UUID;
/* loaded from: classes12.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final UUID f13544a;
    public BluetoothDevice b;
    public BluetoothSocket c;
    public UUID d;

    static {
        UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        f13544a = UUID.fromString("6A24EEAB-4B65-4693-986B-3C26C352264F");
    }

    public a(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid, boolean z) {
        this.b = bluetoothDevice;
        this.c = bluetoothSocket;
        this.d = uuid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SppConnParameters{\n");
        if (this.b != null) {
            sb.append("\n\tdevice:");
            sb.append(BluetoothHelper.formatAddress(this.b.getAddress(), true));
        }
        if (this.d != null) {
            sb.append("\n\tuuid:");
            sb.append(this.d.toString());
        }
        sb.append("\n\tfreshUuid:");
        sb.append(false);
        sb.append("\n}");
        return sb.toString();
    }
}
