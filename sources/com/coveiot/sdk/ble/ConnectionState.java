package com.coveiot.sdk.ble;
/* loaded from: classes9.dex */
public class ConnectionState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f7470a;

    /* loaded from: classes9.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        DISCONNECTED("DIS-CONNECTED"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        private final String f7471a;

        BleState(String str) {
            this.f7471a = str;
        }

        public String getStateAsString() {
            return this.f7471a;
        }
    }

    public ConnectionState(BleState bleState) {
        this.f7470a = BleState.DISCONNECTED;
        this.f7470a = bleState;
    }

    public BleState getmState() {
        return this.f7470a;
    }
}
