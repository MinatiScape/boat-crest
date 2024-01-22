package com.goodix.ble.libble.v2.profile;

import com.goodix.ble.libble.BleUuid;
import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic;
import com.goodix.ble.libble.v2.gb.gatt.GBGattService;
import com.goodix.ble.libble.v2.gb.gatt.profile.GBGattProfile;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureRead;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import java.util.UUID;
/* loaded from: classes5.dex */
public class BatteryService implements IEventListener, GBGattProfile {
    public static final int EVT_BATTERY_UPDATE = 745;
    public static final UUID m = BleUuid.from(6159);
    public static final UUID n = BleUuid.from(10777);
    public GBGattService h;
    public GBGattCharacteristic i;
    public boolean j;
    public int k;
    public Event<Integer> l;

    @Override // com.goodix.ble.libble.v2.gb.gatt.profile.GBGattProfile
    public boolean bindTo(GBRemoteDevice gBRemoteDevice) {
        if (gBRemoteDevice == null) {
            return false;
        }
        GBGattCharacteristic gBGattCharacteristic = this.i;
        if (gBGattCharacteristic != null) {
            gBGattCharacteristic.evtNotify().remove(this);
            this.i.evtRead().clear(this);
        }
        GBGattService requireService = gBRemoteDevice.requireService(m, false);
        this.h = requireService;
        GBGattCharacteristic requireCharacteristic = requireService.requireCharacteristic(n, true, false, true);
        this.i = requireCharacteristic;
        requireCharacteristic.evtNotify().register(this);
        return true;
    }

    public Event<Integer> evtUpdate() {
        if (this.l == null) {
            this.l = new Event<>(this, EVT_BATTERY_UPDATE);
        }
        this.k = -1;
        return this.l;
    }

    public int getLastLevel() {
        return this.k;
    }

    public GBGattCharacteristic getLevel() {
        return this.i;
    }

    public GBGattService getService() {
        return this.h;
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        if (obj != this.i || i != 55) {
            if (i == 342 && (obj instanceof GBGattProcedureRead)) {
                onEvent(this.i, 55, ((GBGattProcedureRead) obj).getValue());
                return;
            }
            return;
        }
        byte[] bArr = (byte[]) obj2;
        Event<Integer> event = this.l;
        if (event == null || bArr == null || bArr.length != 1) {
            return;
        }
        int i2 = bArr[0] & 255;
        if (this.j && this.k == i2) {
            return;
        }
        this.k = i2;
        event.postEvent(this, EVT_BATTERY_UPDATE, Integer.valueOf(i2));
    }

    public void requestUpdate() {
        GBGattProcedureRead read = this.i.read();
        read.evtFinished().register(this);
        read.startProcedure();
    }

    public void setNoRepeat() {
        this.j = true;
    }
}
