package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexReader;
/* loaded from: classes5.dex */
public class XGetInfo implements IFrameSdu4Tx, IFrameSdu4Rx {
    public int sdkBuild;
    public int sdkMajor;
    public int sdkMinor;
    public int sdkSvn;
    public int stackBuild;
    public int stackMajor;
    public int stackMinor;
    public int stackSvn;
    public boolean success;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx
    public void deserialize(HexReader hexReader) {
        boolean z = hexReader.get(1) == 1;
        this.success = z;
        if (z) {
            this.stackMajor = hexReader.get(1);
            this.stackMinor = hexReader.get(1);
            this.stackBuild = hexReader.get(2);
            this.stackSvn = hexReader.get(4);
            this.sdkMajor = hexReader.get(1);
            this.sdkMinor = hexReader.get(1);
            this.sdkBuild = hexReader.get(2);
            this.sdkSvn = hexReader.get(4);
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        return this.success ? 17 : 1;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.success ? 1 : 2, 1);
        if (this.success) {
            hexBuilder.put(this.stackMajor, 1);
            hexBuilder.put(this.stackMinor, 1);
            hexBuilder.put(this.stackBuild, 2);
            hexBuilder.put(this.stackSvn, 4);
            hexBuilder.put(this.sdkMajor, 1);
            hexBuilder.put(this.sdkMinor, 1);
            hexBuilder.put(this.sdkBuild, 2);
            hexBuilder.put(this.sdkSvn, 4);
        }
    }
}
