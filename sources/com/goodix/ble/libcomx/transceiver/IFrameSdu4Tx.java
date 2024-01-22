package com.goodix.ble.libcomx.transceiver;

import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public interface IFrameSdu4Tx {
    int getSduSize();

    void serialize(HexBuilder hexBuilder);
}
