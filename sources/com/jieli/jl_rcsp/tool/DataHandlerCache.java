package com.jieli.jl_rcsp.tool;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_rcsp.tool.datahandles.DataHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class DataHandlerCache {
    private static volatile DataHandlerCache c;

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, DataHandler> f12440a = Collections.synchronizedMap(new HashMap());
    private final List<BluetoothDevice> b = Collections.synchronizedList(new ArrayList());

    private DataHandlerCache() {
    }

    private void a() {
        for (String str : new HashSet(this.f12440a.keySet())) {
            DataHandler dataHandler = this.f12440a.get(str);
            if (dataHandler != null) {
                dataHandler.release();
            }
        }
        this.f12440a.clear();
        this.b.clear();
    }

    public static DataHandlerCache getInstance() {
        if (c == null) {
            synchronized (DataHandlerCache.class) {
                if (c == null) {
                    c = new DataHandlerCache();
                }
            }
        }
        return c;
    }

    public void addDataHandler(BluetoothDevice bluetoothDevice, DataHandler dataHandler) {
        if (bluetoothDevice == null || dataHandler == null) {
            return;
        }
        if (!this.b.contains(bluetoothDevice)) {
            this.b.add(bluetoothDevice);
        }
        if (this.f12440a.containsKey(bluetoothDevice.getAddress())) {
            return;
        }
        this.f12440a.put(bluetoothDevice.getAddress(), dataHandler);
    }

    public void destroy() {
        a();
        c = null;
    }

    public DataHandler getDataHandler(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        return this.f12440a.get(bluetoothDevice.getAddress());
    }

    public List<BluetoothDevice> getDeviceList() {
        return this.b;
    }

    public void removeDataHandler(BluetoothDevice bluetoothDevice) {
        DataHandler remove;
        if (bluetoothDevice == null || (remove = this.f12440a.remove(bluetoothDevice.getAddress())) == null) {
            return;
        }
        this.b.remove(bluetoothDevice);
        remove.release();
    }
}
