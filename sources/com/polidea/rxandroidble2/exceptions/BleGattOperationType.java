package com.polidea.rxandroidble2.exceptions;
/* loaded from: classes9.dex */
public class BleGattOperationType {

    /* renamed from: a  reason: collision with root package name */
    public final String f13379a;
    public static final BleGattOperationType CONNECTION_STATE = new BleGattOperationType("CONNECTION_STATE");
    public static final BleGattOperationType SERVICE_DISCOVERY = new BleGattOperationType("SERVICE_DISCOVERY");
    public static final BleGattOperationType CHARACTERISTIC_READ = new BleGattOperationType("CHARACTERISTIC_READ");
    public static final BleGattOperationType CHARACTERISTIC_WRITE = new BleGattOperationType("CHARACTERISTIC_WRITE");
    public static final BleGattOperationType CHARACTERISTIC_LONG_WRITE = new BleGattOperationType("CHARACTERISTIC_LONG_WRITE");
    public static final BleGattOperationType CHARACTERISTIC_CHANGED = new BleGattOperationType("CHARACTERISTIC_CHANGED");
    public static final BleGattOperationType DESCRIPTOR_READ = new BleGattOperationType("DESCRIPTOR_READ");
    public static final BleGattOperationType DESCRIPTOR_WRITE = new BleGattOperationType("DESCRIPTOR_WRITE");
    public static final BleGattOperationType RELIABLE_WRITE_COMPLETED = new BleGattOperationType("RELIABLE_WRITE_COMPLETED");
    public static final BleGattOperationType READ_RSSI = new BleGattOperationType("READ_RSSI");
    public static final BleGattOperationType ON_MTU_CHANGED = new BleGattOperationType("ON_MTU_CHANGED");
    public static final BleGattOperationType CONNECTION_PRIORITY_CHANGE = new BleGattOperationType("CONNECTION_PRIORITY_CHANGE");

    public BleGattOperationType(String str) {
        this.f13379a = str;
    }

    public String toString() {
        return "BleGattOperation{description='" + this.f13379a + "'}";
    }
}