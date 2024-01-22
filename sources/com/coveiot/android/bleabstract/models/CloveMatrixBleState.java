package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class CloveMatrixBleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f3414a;

    /* loaded from: classes2.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f3415a;

        BleState(String str) {
            this.f3415a = str;
        }

        public String getStateAsString() {
            return this.f3415a;
        }
    }

    public CloveMatrixBleState(BleState bleState) {
        this.f3414a = BleState.DISCONNECTED;
        this.f3414a = bleState;
    }

    public BleState getmState() {
        return this.f3414a;
    }
}
