package com.goodix.ble.libcomx.transceiver;

import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexReader;
/* loaded from: classes5.dex */
public class EmptySdu implements IFrameSdu4Tx, IFrameSdu4Rx {

    /* renamed from: a  reason: collision with root package name */
    public static final EmptySdu f8044a = new EmptySdu();

    public static EmptySdu getInstance() {
        return f8044a;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx
    public void deserialize(HexReader hexReader) {
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        return 0;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
    }
}
