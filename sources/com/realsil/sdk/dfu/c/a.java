package com.realsil.sdk.dfu.c;

import com.realsil.sdk.dfu.image.pack.SubFileInfo;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.util.List;
/* loaded from: classes12.dex */
public final class a {
    public static int a(OtaDeviceInfo otaDeviceInfo, BinInfo binInfo) {
        int i;
        if (otaDeviceInfo == null || binInfo == null || !((i = otaDeviceInfo.icType) == 5 || i == 9 || i == 12)) {
            return 4096;
        }
        if (otaDeviceInfo.isBankEnabled()) {
            if (binInfo.isPackFile) {
                List<SubFileInfo> subFileInfos = binInfo.getSubFileInfos(otaDeviceInfo.getInactiveBank());
                if (subFileInfos == null || subFileInfos.size() <= 0) {
                    return 4110;
                }
                for (SubFileInfo subFileInfo : subFileInfos) {
                    if (subFileInfo.binId == 2048) {
                        return 4096;
                    }
                }
                return 4111;
            }
            return 4108;
        } else if (!binInfo.isPackFile) {
            return binInfo.getBinInputStreamByBinId(2048) != null ? 4112 : 4096;
        } else {
            List<SubFileInfo> subFileInfos2 = binInfo.getSubFileInfos(otaDeviceInfo.getInactiveBank());
            if (subFileInfos2 == null || subFileInfos2.size() <= 0) {
                return 4110;
            }
            for (SubFileInfo subFileInfo2 : subFileInfos2) {
                if (subFileInfo2.binId == 2048) {
                    return 4112;
                }
            }
            return 4096;
        }
    }
}
