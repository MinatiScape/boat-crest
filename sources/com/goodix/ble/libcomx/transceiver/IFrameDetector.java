package com.goodix.ble.libcomx.transceiver;

import com.goodix.ble.libcomx.transceiver.buffer.ring.IRingBuffer;
/* loaded from: classes5.dex */
public interface IFrameDetector {

    /* loaded from: classes5.dex */
    public static class ResultHolder {
        public int framePos;
        public int frameSize;
        public int frameType;
        public int sduPos;
        public int sduSize;
    }

    boolean detectFrame(IRingBuffer iRingBuffer, ResultHolder resultHolder);
}
