package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class CloveSmaBleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f3416a;

    /* loaded from: classes2.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f3417a;

        BleState(String str) {
            this.f3417a = str;
        }

        public String getStateAsString() {
            return this.f3417a;
        }
    }

    public CloveSmaBleState(BleState bleState) {
        this.f3416a = BleState.DISCONNECTED;
        this.f3416a = bleState;
    }

    public BleState getmState() {
        return this.f3416a;
    }
}
