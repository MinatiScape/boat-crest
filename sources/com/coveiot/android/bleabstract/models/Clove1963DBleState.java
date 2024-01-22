package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class Clove1963DBleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f3410a;

    /* loaded from: classes2.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f3411a;

        BleState(String str) {
            this.f3411a = str;
        }

        public String getStateAsString() {
            return this.f3411a;
        }
    }

    public Clove1963DBleState(BleState bleState) {
        this.f3410a = BleState.DISCONNECTED;
        this.f3410a = bleState;
    }

    public BleState getmState() {
        return this.f3410a;
    }
}
