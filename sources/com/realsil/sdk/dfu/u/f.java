package com.realsil.sdk.dfu.u;

import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.image.BinIndicator;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.image.pack.SubFileInfo;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.ImageVersionInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class f extends com.realsil.sdk.dfu.k.a {
    public static BinInfo a(LoadParams loadParams) throws LoadFileException {
        boolean z;
        Context a2 = loadParams.a();
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b = loadParams.b();
        OtaDeviceInfo f = loadParams.f();
        boolean v = loadParams.v();
        boolean q = loadParams.q();
        BinInfo b2 = com.realsil.sdk.dfu.k.a.b(d, e);
        b2.updateBank = loadParams.k();
        if (a2 != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i = f != null ? f.specVersion : 1;
            Locale locale = Locale.US;
            ZLogger.v(String.format(locale, "device >> otaVersion=%d, primaryIcType=0x%02X", Integer.valueOf(i), Integer.valueOf(h)));
            ZLogger.v(String.format(locale, "fileIndicator=0x%08X, filePath=%s, versionCheckEnabled=%b", Integer.valueOf(b), d, Boolean.valueOf(v)));
            com.realsil.sdk.dfu.f.a b3 = com.realsil.sdk.dfu.e.b.b(loadParams);
            if (b3 == null) {
                try {
                    BaseBinInputStream openAssetsInputStream = com.realsil.sdk.dfu.k.a.openAssetsInputStream(a2, h, d, 0L);
                    if (openAssetsInputStream != null) {
                        arrayList2.add(openAssetsInputStream);
                        b2.icType = openAssetsInputStream.getIcType();
                        b2.version = openAssetsInputStream.getImageVersion();
                        if (q && !com.realsil.sdk.dfu.k.a.a(b2.icType, h)) {
                            ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(b2.icType)));
                            b2.updateEnabled = false;
                            b2.status = 4101;
                            return b2;
                        } else if (v) {
                            if (1 == com.realsil.sdk.dfu.k.a.checkSingleImageVersion(openAssetsInputStream, f)) {
                                arrayList3.add(openAssetsInputStream);
                            } else {
                                z = true;
                            }
                        } else {
                            arrayList3.add(openAssetsInputStream);
                        }
                    }
                    z = false;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    throw new LoadFileException(e2.getMessage(), 4097);
                }
            } else if (i > 1) {
                b2.isPackFile = true;
                b2.icType = b3.b();
                b2.subFileInfos = b3.c(0);
                b2.subFileInfos1 = b3.c(1);
                if (q && !com.realsil.sdk.dfu.k.a.a(b2.icType, h)) {
                    ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(b2.icType)));
                    b2.updateEnabled = false;
                    b2.status = 4101;
                    return b2;
                }
                boolean z2 = false;
                for (int i2 = 0; i2 < 16; i2++) {
                    int wrapperBitNumber = com.realsil.sdk.dfu.k.a.wrapperBitNumber(i2, f.imageVersionIndicator, f.updateBankIndicator);
                    if (wrapperBitNumber < 16) {
                        b2.bankIndicator |= 1;
                    } else {
                        b2.bankIndicator |= 2;
                    }
                    if (BinIndicator.isIndicatorEnabled(b, wrapperBitNumber)) {
                        SubFileInfo b4 = b3.b(wrapperBitNumber);
                        BaseBinInputStream assetsBinInputStream = b4 != null ? b4.getAssetsBinInputStream(a2, b2.icType) : null;
                        if (assetsBinInputStream != null) {
                            arrayList2.add(assetsBinInputStream);
                            if (v) {
                                if (1 == com.realsil.sdk.dfu.k.a.checkPackImageVersion(wrapperBitNumber, assetsBinInputStream, f)) {
                                    arrayList3.add(assetsBinInputStream);
                                    arrayList.add(b4);
                                } else {
                                    z2 = true;
                                }
                            } else {
                                arrayList3.add(assetsBinInputStream);
                                arrayList.add(b4);
                            }
                        }
                    } else {
                        ZLogger.v("image file disable: bitNumber=" + wrapperBitNumber);
                    }
                }
                try {
                    b3.close();
                } catch (IOException e3) {
                    ZLogger.e(e3.toString());
                }
                z = z2;
            } else {
                throw new LoadFileException("not support pack file", 4107);
            }
            b2.lowVersionExist = z;
            b2.subBinInputStreams = arrayList2;
            b2.supportBinInputStreams = arrayList3;
            b2.supportSubFileInfos = arrayList;
            if (v && z && arrayList3.size() < 1) {
                b2.updateEnabled = false;
                b2.status = 4104;
            }
            return b2;
        }
        throw new LoadFileException("invalid context", 4097);
    }

    public static BinInfo loadImageBinInfo(LoadParams loadParams) throws LoadFileException {
        byte[] bArr;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        SubFileInfo b;
        loadParams.a();
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b2 = loadParams.b();
        OtaDeviceInfo f = loadParams.f();
        boolean v = loadParams.v();
        boolean q = loadParams.q();
        boolean s = loadParams.s();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        a2.updateBank = loadParams.k();
        com.realsil.sdk.dfu.f.a c = com.realsil.sdk.dfu.e.b.c(loadParams);
        if (c != null) {
            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, c.toString());
            a2.isPackFile = true;
            a2.icType = c.b();
            a2.subFileInfos = c.c(0);
            a2.subFileInfos1 = c.c(1);
            if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                ZLogger.d(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                a2.updateEnabled = false;
                a2.status = 4101;
                return a2;
            }
            if (!s || (b = c.b(com.realsil.sdk.dfu.k.a.wrapperBitNumber(2, f.imageVersionIndicator, f.updateBankIndicator))) == null) {
                z2 = true;
                z3 = false;
            } else {
                BaseBinInputStream binInputStream = b.getBinInputStream(a2.icType);
                if (binInputStream == null || 1 == a(2, binInputStream, f)) {
                    z2 = true;
                    z3 = true;
                } else {
                    z3 = true;
                    z2 = false;
                }
            }
            if (z2) {
                z4 = false;
                for (int i = 0; i < 16; i++) {
                    int wrapperBitNumber = com.realsil.sdk.dfu.k.a.wrapperBitNumber(i, f.imageVersionIndicator, f.updateBankIndicator);
                    if (wrapperBitNumber < 16) {
                        a2.bankIndicator |= 1;
                    } else {
                        a2.bankIndicator |= 2;
                    }
                    SubFileInfo b3 = c.b(wrapperBitNumber);
                    BaseBinInputStream binInputStream2 = b3 != null ? b3.getBinInputStream(a2.icType) : null;
                    if (binInputStream2 != null) {
                        arrayList2.add(binInputStream2);
                        if (!BinIndicator.isIndicatorEnabled(b2, wrapperBitNumber)) {
                            ZLogger.v("image file disable: bitNumber=" + wrapperBitNumber);
                        } else if (!v || 1 == com.realsil.sdk.dfu.k.a.checkPackImageVersion(i, binInputStream2, f)) {
                            if (s) {
                                if (z3) {
                                    ZLogger.d("preVerify OTA_HEADER_FILE ok, no need to check section size");
                                } else if (1 != a(i, binInputStream2, f)) {
                                    a2.updateEnabled = false;
                                    a2.status = 4109;
                                    return a2;
                                }
                            }
                            arrayList3.add(binInputStream2);
                            arrayList.add(b3);
                        } else {
                            z4 = true;
                        }
                    }
                }
            } else {
                ZLogger.w("pre verify failed");
                z4 = false;
            }
            try {
                c.close();
            } catch (IOException e2) {
                ZLogger.e(e2.toString());
            }
            z = z4;
        } else {
            try {
                BaseBinInputStream openFileInputStream = com.realsil.sdk.dfu.k.a.openFileInputStream(h, d, 0L);
                if (openFileInputStream != null) {
                    openFileInputStream.parseImageHeaderEx();
                    bArr = openFileInputStream.getSha256();
                    try {
                        openFileInputStream.close();
                    } catch (Exception e3) {
                        ZLogger.e(e3.toString());
                    }
                } else {
                    bArr = null;
                }
                BaseBinInputStream openFileInputStream2 = com.realsil.sdk.dfu.k.a.openFileInputStream(h, d, 0L);
                if (openFileInputStream2 != null) {
                    openFileInputStream2.setSha256(bArr);
                    arrayList2.add(openFileInputStream2);
                    a2.icType = openFileInputStream2.getIcType();
                    a2.version = openFileInputStream2.getImageVersion();
                    if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                        ZLogger.d(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                        a2.updateEnabled = false;
                        a2.status = 4101;
                        return a2;
                    } else if (v && 1 != com.realsil.sdk.dfu.k.a.checkSingleImageVersion(openFileInputStream2, f)) {
                        z = true;
                    } else if (!s) {
                        arrayList3.add(openFileInputStream2);
                    } else if (1 == a(openFileInputStream2, f)) {
                        arrayList3.add(openFileInputStream2);
                    }
                }
                z = false;
            } catch (IOException e4) {
                e4.printStackTrace();
                throw new LoadFileException(e4.getMessage(), 4097);
            }
        }
        a2.lowVersionExist = z;
        a2.subBinInputStreams = arrayList2;
        a2.supportBinInputStreams = arrayList3;
        a2.supportSubFileInfos = arrayList;
        if (v && z && arrayList3.size() < 1) {
            a2.updateEnabled = false;
            a2.status = 4104;
        }
        return a2;
    }

    public static int a(int i, BaseBinInputStream baseBinInputStream, OtaDeviceInfo otaDeviceInfo) {
        if (otaDeviceInfo == null) {
            return 1;
        }
        int imageSize = baseBinInputStream.getImageSize();
        int i2 = otaDeviceInfo.icType;
        if (i2 != 4 && i2 != 6 && i2 != 7 && i2 != 8 && i2 != 10) {
            boolean z = com.realsil.sdk.dfu.k.a.f13612a;
            ZLogger.v(z, "not support section size check for ic:" + otaDeviceInfo.icType);
        } else {
            List<ImageVersionInfo> imageVersionInfos = otaDeviceInfo.getImageVersionInfos();
            if (imageVersionInfos != null && imageVersionInfos.size() > 0) {
                Iterator<ImageVersionInfo> it = imageVersionInfos.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ImageVersionInfo next = it.next();
                    if (next.getBitNumber() == i) {
                        if (next.getSectionSize() > 0 && imageSize > next.getSectionSize()) {
                            ZLogger.w(String.format(Locale.US, "image size is %d exceed the limit of the section size %d", Integer.valueOf(imageSize), Integer.valueOf(next.getSectionSize())));
                            return 2;
                        }
                        ZLogger.v("section size validate ok: " + imageSize);
                    }
                }
            }
        }
        return 1;
    }

    public static int a(BaseBinInputStream baseBinInputStream, OtaDeviceInfo otaDeviceInfo) {
        if (otaDeviceInfo == null) {
            return 1;
        }
        int binId = baseBinInputStream.getBinId();
        int imageSize = baseBinInputStream.getImageSize();
        BinIndicator binIndicator = null;
        int i = otaDeviceInfo.icType;
        if (i != 4 && i != 6 && i != 7 && i != 8 && i != 10) {
            boolean z = com.realsil.sdk.dfu.k.a.f13612a;
            ZLogger.v(z, "not support section size check for ic:" + otaDeviceInfo.icType);
        } else {
            Iterator<BinIndicator> it = BinIndicator.BBPRO.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BinIndicator next = it.next();
                if (next.subBinId == binId) {
                    if (next.versionCheckEnabled) {
                        binIndicator = next;
                    }
                }
            }
            if (binIndicator != null) {
                ZLogger.d(binIndicator.toString());
                List<ImageVersionInfo> imageVersionInfos = otaDeviceInfo.getImageVersionInfos();
                if (imageVersionInfos != null && imageVersionInfos.size() > 0) {
                    Iterator<ImageVersionInfo> it2 = imageVersionInfos.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        ImageVersionInfo next2 = it2.next();
                        if (next2.getBitNumber() == binIndicator.bitNumber) {
                            if (next2.getSectionSize() > 0 && imageSize > next2.getSectionSize()) {
                                ZLogger.w(String.format(Locale.US, "image size is %d exceed the limit of the section size %d", Integer.valueOf(imageSize), Integer.valueOf(next2.getSectionSize())));
                                return 2;
                            }
                            ZLogger.v("version validate ok: " + imageSize);
                        }
                    }
                }
            }
        }
        return 1;
    }
}
