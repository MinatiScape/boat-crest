package com.coveiot.android.spo2sdk;
/* loaded from: classes7.dex */
public class CloveBleStatePC60F {

    /* renamed from: a  reason: collision with root package name */
    public BleState f5791a;

    /* loaded from: classes7.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        
        private final String stateAsString;

        BleState(String str) {
            this.stateAsString = str;
        }

        public String getStateAsString() {
            return this.stateAsString;
        }
    }

    public CloveBleStatePC60F(BleState bleState) {
        this.f5791a = BleState.DISCONNECTED;
        this.f5791a = bleState;
    }

    public BleState getmState() {
        return this.f5791a;
    }
}
