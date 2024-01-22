package com.crrepa.w;

import com.crrepa.ble.trans.upgrade.bean.HSFirmwareInfo;
import java.io.File;
/* loaded from: classes9.dex */
public class a {
    public HSFirmwareInfo a(File file, boolean z, boolean z2) {
        File a2 = b.a(file);
        if (a2 == null || !a2.isDirectory()) {
            return null;
        }
        HSFirmwareInfo hSFirmwareInfo = new HSFirmwareInfo();
        b(hSFirmwareInfo, a2, z, z2);
        return hSFirmwareInfo;
    }

    public final void b(HSFirmwareInfo hSFirmwareInfo, File file, boolean z, boolean z2) {
        File[] listFiles;
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                b(hSFirmwareInfo, file2, z, z2);
            } else {
                c(hSFirmwareInfo, file2, z, z2);
            }
        }
    }

    public final void c(HSFirmwareInfo hSFirmwareInfo, File file, boolean z, boolean z2) {
        String name = file.getName();
        String path = file.getPath();
        if (name.contains("app")) {
            hSFirmwareInfo.setAppFilePath(path);
        } else if (z && name.contains("usr")) {
            hSFirmwareInfo.setUserFilePath(path);
            hSFirmwareInfo.setUserStartAddress("23000");
        } else if (z2 && name.contains("patch")) {
            hSFirmwareInfo.setPatchFilePath(path);
        }
    }
}
