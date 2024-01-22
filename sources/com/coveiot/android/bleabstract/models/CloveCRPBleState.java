package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class CloveCRPBleState {

    /* renamed from: a  reason: collision with root package name */
    public BleState f3412a;

    /* loaded from: classes2.dex */
    public enum BleState {
        CONNECTED("CONNECTED"),
        CONNECTING("CONNECTING"),
        DISCONNECTED("DIS-CONNECTED"),
        SCANNING("SCANNING"),
        DISCOVERING_SERVICES("DISCOVERING SERVICES");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f3413a;

        BleState(String str) {
            this.f3413a = str;
        }

        public String getStateAsString() {
            return this.f3413a;
        }
    }

    public CloveCRPBleState(BleState bleState) {
        this.f3412a = BleState.DISCONNECTED;
        this.f3412a = bleState;
    }

    public BleState getmState() {
        return this.f3412a;
    }
}
