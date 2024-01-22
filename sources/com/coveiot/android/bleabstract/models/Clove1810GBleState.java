package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class Clove1810GBleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f3406a;

    /* loaded from: classes2.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f3407a;

        BleState(String str) {
            this.f3407a = str;
        }

        public String getStateAsString() {
            return this.f3407a;
        }
    }

    public Clove1810GBleState(BleState bleState) {
        this.f3406a = BleState.DISCONNECTED;
        this.f3406a = bleState;
    }

    public BleState getmState() {
        return this.f3406a;
    }
}
