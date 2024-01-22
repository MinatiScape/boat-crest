package com.coveiot.sdk.ble;
/* loaded from: classes9.dex */
public class CloveBleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f7468a;

    /* loaded from: classes9.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        private final String f7469a;

        BleState(String str) {
            this.f7469a = str;
        }

        public String getStateAsString() {
            return this.f7469a;
        }
    }

    public CloveBleState(BleState bleState) {
        this.f7468a = BleState.DISCONNECTED;
        this.f7468a = bleState;
    }

    public BleState getmState() {
        return this.f7468a;
    }
}
