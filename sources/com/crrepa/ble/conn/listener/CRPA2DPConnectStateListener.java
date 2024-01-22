package com.crrepa.ble.conn.listener;
/* loaded from: classes9.dex */
public interface CRPA2DPConnectStateListener {

    /* loaded from: classes9.dex */
    public enum A2DPConnectState {
        NOT_CONNECTED((byte) 0),
        CONNECTED((byte) 1),
        DISCONNECTED((byte) 2);
        
        private byte value;

        A2DPConnectState(byte b) {
            this.value = b;
        }

        public static A2DPConnectState getInstance(byte b) {
            if (b != 0) {
                if (b != 1) {
                    if (b != 2) {
                        return null;
                    }
                    return DISCONNECTED;
                }
                return CONNECTED;
            }
            return NOT_CONNECTED;
        }

        public byte getValue() {
            return this.value;
        }
    }

    void onConnectState(A2DPConnectState a2DPConnectState);
}
