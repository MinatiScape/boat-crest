package com.realsil.sdk.dfu.image;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.k.a;
import com.realsil.sdk.dfu.k.c;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.ImageVersionInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.r.b;
import com.realsil.sdk.dfu.u.f;
import com.realsil.sdk.dfu.u.g;
import com.realsil.sdk.dfu.u.h;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class FirmwareLoaderX extends a {
    public static BinInfo a(LoadParams loadParams) throws LoadFileException {
        OtaDeviceInfo f = loadParams.f();
        int i = f != null ? f.protocolType : 0;
        if (i == 16) {
            return b.a(loadParams);
        }
        if (i != 17) {
            if (i == 20) {
                return com.realsil.sdk.dfu.p.b.a(loadParams);
            }
            return c.a(loadParams);
        } else if (f.specVersion >= 6) {
            if (f.isBankEnabled()) {
                return h.b(loadParams);
            }
            return h.a(loadParams);
        } else {
            return f.a(loadParams);
        }
    }

    public static BinInfo b(LoadParams loadParams) throws LoadFileException {
        OtaDeviceInfo f = loadParams.f();
        int i = loadParams.i();
        int j = loadParams.j();
        if (i == 16) {
            return b.loadImageBinInfo(loadParams);
        }
        if (i != 17) {
            if (i == 20) {
                return com.realsil.sdk.dfu.p.b.loadImageBinInfo(loadParams);
            }
            return c.loadImageBinInfo(loadParams);
        } else if (j >= 6) {
            if (f.isBankEnabled()) {
                if (loadParams.m() == 19) {
                    return h.f(loadParams);
                }
                if (loadParams.m() == 20) {
                    return h.e(loadParams);
                }
                if (loadParams.m() == 23) {
                    return h.d(loadParams);
                }
                return h.c(loadParams);
            }
            return h.loadImageBinInfo(loadParams);
        } else if (j == 5) {
            if (f.isBankEnabled()) {
                if (loadParams.m() == 19) {
                    return g.c(loadParams);
                }
                if (loadParams.m() == 20) {
                    return g.b(loadParams);
                }
                return g.b(loadParams);
            }
            return g.a(loadParams);
        } else {
            return f.loadImageBinInfo(loadParams);
        }
    }

    public static BinInfo loadImageBinInfo(LoadParams loadParams) throws LoadFileException {
        BinInfo b;
        if (loadParams == null) {
            ZLogger.w("loadParams can not be null");
            return null;
        }
        ZLogger.v(loadParams.toString());
        if (loadParams.c() == 1) {
            b = a(loadParams);
        } else {
            b = b(loadParams);
        }
        if (b != null && b.status == 4096) {
            b.status = com.realsil.sdk.dfu.c.a.a(loadParams.f(), b);
        }
        return b;
    }

    public static List<BaseBinInputStream> loadImageFile(LoadParams loadParams) throws LoadFileException {
        BinInfo loadImageBinInfo = loadImageBinInfo(loadParams);
        if (loadImageBinInfo != null) {
            if (loadImageBinInfo.status == 4096) {
                return loadImageBinInfo.supportBinInputStreams;
            }
            return new ArrayList();
        }
        return new ArrayList();
    }

    public int getImageVersion(OtaDeviceInfo otaDeviceInfo, int i) {
        if (otaDeviceInfo == null) {
            return 0;
        }
        int protocolType = otaDeviceInfo.getProtocolType();
        if (protocolType == 0) {
            int i2 = otaDeviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                for (ImageVersionInfo imageVersionInfo : otaDeviceInfo.getExistImageVersionInfos()) {
                    if (otaDeviceInfo.icType <= 3) {
                        if (imageVersionInfo.getBitNumber() == i) {
                            return imageVersionInfo.getVersion();
                        }
                    } else if (imageVersionInfo.getBitNumber() == i || imageVersionInfo.getBitNumber() == i + 16) {
                        return imageVersionInfo.getVersion();
                    }
                }
            }
        } else if (protocolType == 16 || protocolType == 17) {
            for (ImageVersionInfo imageVersionInfo2 : otaDeviceInfo.getExistImageVersionInfos()) {
                if (otaDeviceInfo.icType <= 3) {
                    if (imageVersionInfo2.getBitNumber() == i) {
                        return imageVersionInfo2.getVersion();
                    }
                } else if (imageVersionInfo2.getBitNumber() == i || imageVersionInfo2.getBitNumber() == i + 16) {
                    return imageVersionInfo2.getVersion();
                }
            }
        }
        return 0;
    }
}
