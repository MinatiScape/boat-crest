package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class Clove1790BleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f3404a;

    /* loaded from: classes2.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f3405a;

        BleState(String str) {
            this.f3405a = str;
        }

        public String getStateAsString() {
            return this.f3405a;
        }
    }

    public Clove1790BleState(BleState bleState) {
        this.f3404a = BleState.DISCONNECTED;
        this.f3404a = bleState;
    }

    public BleState getmState() {
        return this.f3404a;
    }
}
