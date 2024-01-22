package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.gr.libdfu.dfu.entity.ImgInfo;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.util.HexReader;
import java.util.ArrayList;
/* loaded from: classes5.dex */
public class XSystemConfigResponse implements IFrameSdu4Rx {
    public int address;
    public ArrayList<ImgInfo> imgInfos = new ArrayList<>();
    public int length;
    public boolean opEncrypted;
    public boolean opUdated;
    public int response;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx
    public void deserialize(HexReader hexReader) {
        this.response = hexReader.get(1);
        int i = hexReader.get(1);
        int i2 = 0;
        boolean z = (i & 1) != 0;
        this.opUdated = z;
        this.opEncrypted = (i & 16) != 0;
        if (z) {
            return;
        }
        this.address = hexReader.get(4);
        this.length = hexReader.get(2);
        ImgInfo imgInfo = new ImgInfo();
        if (this.length < imgInfo.getSerializeSize()) {
            imgInfo.setPatern(ImgInfo.VALID_PATTERN);
            imgInfo.setVersion(0);
            imgInfo.setComment("BootInfo");
            imgInfo.getBootInfo().deserialize(hexReader);
            this.imgInfos.add(imgInfo);
            return;
        }
        while (i2 < this.length) {
            ImgInfo imgInfo2 = new ImgInfo();
            if (imgInfo2.getSerializeSize() > hexReader.getRemainSize()) {
                return;
            }
            imgInfo2.deserialize(hexReader);
            this.imgInfos.add(imgInfo2);
            i2 += imgInfo2.getSerializeSize();
        }
    }
}
