package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class Clove1860BleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f3408a;

    /* loaded from: classes2.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f3409a;

        BleState(String str) {
            this.f3409a = str;
        }

        public String getStateAsString() {
            return this.f3409a;
        }
    }

    public Clove1860BleState(BleState bleState) {
        this.f3408a = BleState.DISCONNECTED;
        this.f3408a = bleState;
    }

    public BleState getmState() {
        return this.f3408a;
    }
}
