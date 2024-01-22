package com.goodix.ble.gr.libdfu.dfu;

import com.goodix.ble.libcomx.transceiver.IFrameSender;
import com.goodix.ble.libcomx.transceiver.Transceiver;
/* loaded from: classes5.dex */
public class DfuTxRxFactory {
    public Transceiver create() {
        return new Transceiver(2560, new DfuCmdDetector(), new DfuCmdParser(), new DfuCmdBuilder(), (IFrameSender) null);
    }
}
