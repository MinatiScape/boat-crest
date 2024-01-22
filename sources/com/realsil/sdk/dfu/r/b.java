package com.realsil.sdk.dfu.r;

import android.content.Context;
import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.FileUtils;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.image.BinIndicator;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.image.pack.SubFileInfo;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.ImageVersionInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.utils.DfuUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class b extends com.realsil.sdk.dfu.k.a {
    public static BinInfo a(LoadParams loadParams) throws LoadFileException {
        boolean z;
        Context a2 = loadParams.a();
        if (a2 != null) {
            String d = loadParams.d();
            if (!TextUtils.isEmpty(d)) {
                String e = loadParams.e();
                String suffix = FileUtils.getSuffix(d);
                if (suffix != null && suffix.equalsIgnoreCase(e)) {
                    int b = loadParams.b();
                    OtaDeviceInfo f = loadParams.f();
                    boolean v = loadParams.v();
                    boolean q = loadParams.q();
                    int h = loadParams.h();
                    BinInfo binInfo = new BinInfo();
                    binInfo.path = d;
                    binInfo.fileName = DfuUtils.getAssetsFileName(d);
                    binInfo.icType = h;
                    binInfo.updateBank = loadParams.k();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    ZLogger.v(String.format(Locale.US, "fileIndicator=0x%08X, filePath=%s, versionCheckEnabled=%b", Integer.valueOf(b), d, Boolean.valueOf(v)));
                    com.realsil.sdk.dfu.f.a b2 = com.realsil.sdk.dfu.e.b.b(loadParams);
                    if (b2 != null) {
                        binInfo.isPackFile = true;
                        binInfo.icType = b2.b();
                        binInfo.subFileInfos = b2.c(0);
                        binInfo.subFileInfos1 = b2.c(1);
                        if (q && !com.realsil.sdk.dfu.k.a.a(binInfo.icType, h)) {
                            ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(binInfo.icType)));
                            binInfo.updateEnabled = false;
                            binInfo.status = 4101;
                            return binInfo;
                        }
                        z = false;
                        for (int i = 0; i < 16; i++) {
                            int wrapperBitNumber = com.realsil.sdk.dfu.k.a.wrapperBitNumber(i, f.imageVersionIndicator, f.updateBankIndicator);
                            if (wrapperBitNumber < 16) {
                                binInfo.bankIndicator |= 1;
                            } else {
                                binInfo.bankIndicator |= 2;
                            }
                            if (BinIndicator.isIndicatorEnabled(b, wrapperBitNumber)) {
                                SubFileInfo b3 = b2.b(wrapperBitNumber);
                                BaseBinInputStream assetsBinInputStream = b3 != null ? b3.getAssetsBinInputStream(a2, binInfo.icType) : null;
                                if (assetsBinInputStream != null) {
                                    arrayList2.add(assetsBinInputStream);
                                    if (v) {
                                        if (1 == com.realsil.sdk.dfu.k.a.checkPackImageVersion(wrapperBitNumber, assetsBinInputStream, f)) {
                                            arrayList3.add(assetsBinInputStream);
                                            arrayList.add(b3);
                                        } else {
                                            z = true;
                                        }
                                    } else {
                                        arrayList3.add(assetsBinInputStream);
                                        arrayList.add(b3);
                                    }
                                }
                            } else {
                                ZLogger.v("image file disable: bitNumber=" + wrapperBitNumber);
                            }
                        }
                        try {
                            b2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            ZLogger.e(e2.toString());
                        }
                    } else {
                        try {
                            BaseBinInputStream openAssetsInputStream = com.realsil.sdk.dfu.k.a.openAssetsInputStream(a2, binInfo.icType, d, 0L);
                            if (openAssetsInputStream != null) {
                                arrayList2.add(openAssetsInputStream);
                                binInfo.icType = openAssetsInputStream.getIcType();
                                binInfo.version = openAssetsInputStream.getImageVersion();
                                if (q && !com.realsil.sdk.dfu.k.a.a(binInfo.icType, h)) {
                                    ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(binInfo.icType)));
                                    binInfo.updateEnabled = false;
                                    binInfo.status = 4101;
                                    return binInfo;
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
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            throw new LoadFileException(e3.getMessage(), 4097);
                        }
                    }
                    binInfo.lowVersionExist = z;
                    binInfo.subBinInputStreams = arrayList2;
                    binInfo.supportBinInputStreams = arrayList3;
                    binInfo.supportSubFileInfos = arrayList;
                    if (v && z && arrayList3.size() < 1) {
                        binInfo.updateEnabled = false;
                        binInfo.status = 4104;
                    }
                    return binInfo;
                }
                ZLogger.w("the file suffix is not right, suffix=" + suffix);
                throw new LoadFileException("invalid suffix", 4099);
            }
            throw new LoadFileException("invalid path", 4098);
        }
        throw new LoadFileException("invalid context", 4097);
    }

    public static BinInfo loadImageBinInfo(LoadParams loadParams) throws LoadFileException {
        ArrayList arrayList;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        int i2;
        int i3;
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
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        Locale locale = Locale.US;
        ZLogger.v(String.format(locale, "fileIndicator=0x%08X, filePath=%s, versionCheckEnabled=%b, sectionSizeCheckEnabled=%b", Integer.valueOf(b2), d, Boolean.valueOf(v), Boolean.valueOf(s)));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int i4 = f != null ? f.specVersion : 1;
        a2.icType = h;
        a2.updateBank = loadParams.k();
        ZLogger.v(String.format(locale, "device >> specVersion=%d, icType=0x%02X", Integer.valueOf(i4), Integer.valueOf(h)));
        com.realsil.sdk.dfu.f.a c = com.realsil.sdk.dfu.e.b.c(loadParams);
        if (c != null) {
            a2.isPackFile = true;
            a2.icType = c.b();
            a2.subFileInfos = c.c(0);
            a2.subFileInfos1 = c.c(1);
            a2.bankIndicator = 0;
            if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                a2.updateEnabled = false;
                a2.status = 4101;
                return a2;
            }
            if (!s || (!((i3 = a2.icType) == 4 || i3 == 6 || i3 == 7 || i3 == 8 || i3 == 10) || (b = c.b(2)) == null)) {
                z2 = false;
                z3 = true;
            } else {
                BaseBinInputStream binInputStream = b.getBinInputStream(a2.icType);
                z3 = binInputStream == null || 1 == a(2, binInputStream, f);
                z2 = true;
            }
            if (z3) {
                boolean z5 = false;
                int i5 = 0;
                while (i5 < 16) {
                    int wrapperBitNumber = com.realsil.sdk.dfu.k.a.wrapperBitNumber(i5, f.imageVersionIndicator, f.updateBankIndicator);
                    if (wrapperBitNumber < 16) {
                        a2.bankIndicator |= 1;
                    } else {
                        a2.bankIndicator |= 2;
                    }
                    if (!BinIndicator.isIndicatorEnabled(b2, wrapperBitNumber)) {
                        ZLogger.v("image file disable: bitNumber=" + wrapperBitNumber);
                    } else {
                        SubFileInfo b3 = c.b(wrapperBitNumber);
                        BaseBinInputStream binInputStream2 = b3 != null ? b3.getBinInputStream(a2.icType) : null;
                        if (binInputStream2 != null) {
                            arrayList3.add(binInputStream2);
                            if (v) {
                                i = b2;
                                i2 = 1;
                                if (1 != com.realsil.sdk.dfu.k.a.checkPackImageVersion(wrapperBitNumber, binInputStream2, f)) {
                                    z5 = true;
                                    i5++;
                                    b2 = i;
                                }
                            } else {
                                i = b2;
                                i2 = 1;
                            }
                            if (s) {
                                if (z2) {
                                    ZLogger.d("preVerify OTA_HEADER_FILE ok, need to check section size");
                                } else if (i2 != a(wrapperBitNumber, binInputStream2, f)) {
                                    a2.updateEnabled = false;
                                    a2.status = 4109;
                                    return a2;
                                }
                            }
                            arrayList4.add(binInputStream2);
                            arrayList2.add(b3);
                            i5++;
                            b2 = i;
                        }
                    }
                    i = b2;
                    i5++;
                    b2 = i;
                }
                z4 = z5;
            } else {
                ZLogger.w("pre verify failed");
                z4 = false;
            }
            try {
                c.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                ZLogger.e(e2.toString());
            }
            z = z4;
            arrayList = arrayList2;
        } else {
            try {
                arrayList = arrayList2;
                BaseBinInputStream openFileInputStream = com.realsil.sdk.dfu.k.a.openFileInputStream(a2.icType, d, 0L);
                if (openFileInputStream != null) {
                    arrayList3.add(openFileInputStream);
                    a2.icType = openFileInputStream.getIcType();
                    a2.version = openFileInputStream.getImageVersion();
                    if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                        ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                        a2.updateEnabled = false;
                        a2.status = 4101;
                        return a2;
                    } else if (v && 1 != com.realsil.sdk.dfu.k.a.checkSingleImageVersion(openFileInputStream, f)) {
                        z = true;
                    } else if (!s) {
                        arrayList4.add(openFileInputStream);
                    } else if (1 == a(openFileInputStream, f)) {
                        arrayList4.add(openFileInputStream);
                    }
                }
                z = false;
            } catch (IOException e3) {
                e3.printStackTrace();
                throw new LoadFileException(e3.getMessage(), 4097);
            }
        }
        a2.lowVersionExist = z;
        a2.subBinInputStreams = arrayList3;
        a2.supportBinInputStreams = arrayList4;
        a2.supportSubFileInfos = arrayList;
        if (v && z && arrayList4.size() < 1) {
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
        if (i2 != 4 && i2 != 6 && i2 != 7 && i2 != 8) {
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
        if (i != 4 && i != 6 && i != 7 && i != 8) {
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
                            ZLogger.v("section size validate ok: " + imageSize);
                        }
                    }
                }
            }
        }
        return 1;
    }
}
