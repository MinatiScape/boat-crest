package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.gr.libdfu.dfu.entity.ImgInfo;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes5.dex */
public class XSystemConfig implements IFrameSdu4Tx {
    public int address;
    public List<ImgInfo> imgInfos = new ArrayList();
    public int length;
    public boolean opUdate;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        if (this.opUdate) {
            this.length = 0;
            for (ImgInfo imgInfo : this.imgInfos) {
                this.length += imgInfo.getSerializeSize();
            }
            return this.length + 1 + 4 + 2;
        }
        return 7;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.opUdate ? 1 : 0, 1);
        hexBuilder.put(this.address, 4);
        hexBuilder.put(this.length, 2);
        if (this.opUdate) {
            for (ImgInfo imgInfo : this.imgInfos) {
                if (hexBuilder.getRemainSize() < imgInfo.getSerializeSize()) {
                    return;
                }
                imgInfo.serialize(hexBuilder);
            }
        }
    }
}
