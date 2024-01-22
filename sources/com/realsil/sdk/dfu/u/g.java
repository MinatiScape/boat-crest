package com.realsil.sdk.dfu.u;

import android.util.SparseIntArray;
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
public class g extends com.realsil.sdk.dfu.k.a {
    public static BinInfo a(LoadParams loadParams) throws LoadFileException {
        ArrayList arrayList;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Iterator<SubFileInfo> it;
        int i;
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
        ZLogger.v(String.format(Locale.US, "device >> otaVersion=%d, preferredIcType=0x%02X", Integer.valueOf(f != null ? f.specVersion : 1), Integer.valueOf(h)));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        a2.updateBank = loadParams.k();
        com.realsil.sdk.dfu.f.a c = com.realsil.sdk.dfu.e.b.c(loadParams);
        if (c != null) {
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
            if (!s || (b = c.b(com.realsil.sdk.dfu.k.a.wrapperBitNumber(2, c.a()))) == null) {
                z2 = true;
                z3 = false;
            } else {
                BaseBinInputStream binInputStream = b.getBinInputStream(a2.icType);
                if (binInputStream == null || 1 == b(2, binInputStream, f)) {
                    z2 = true;
                    z3 = true;
                } else {
                    z3 = true;
                    z2 = false;
                }
            }
            if (z2) {
                Iterator<SubFileInfo> it2 = c.c(loadParams.k()).iterator();
                z4 = false;
                while (it2.hasNext()) {
                    SubFileInfo next = it2.next();
                    int wrapperBitNumber = next.wrapperBitNumber();
                    if (!BinIndicator.isIndicatorEnabled(b2, wrapperBitNumber)) {
                        ZLogger.v("prohibit upgrade image_id=" + next.imageId);
                    } else {
                        BaseBinInputStream binInputStream2 = next.getBinInputStream(a2.icType);
                        if (binInputStream2 == null) {
                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, "invalid stream: " + next.toString());
                        } else {
                            if (v) {
                                it = it2;
                                i = 1;
                                if (1 != a(wrapperBitNumber, binInputStream2, f)) {
                                    z4 = true;
                                    it2 = it;
                                }
                            } else {
                                it = it2;
                                i = 1;
                            }
                            if (s) {
                                if (z3) {
                                    ZLogger.d("preVerify OTA_HEADER_FILE ok, no need to check section size");
                                } else if (i != b(wrapperBitNumber, binInputStream2, f)) {
                                    a2.updateEnabled = false;
                                    a2.status = 4109;
                                    return a2;
                                }
                            }
                            arrayList4.add(binInputStream2);
                            arrayList2.add(next);
                            it2 = it;
                        }
                    }
                    it = it2;
                    it2 = it;
                }
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
            arrayList = arrayList4;
        } else {
            byte[] bArr = null;
            try {
                arrayList = arrayList4;
                BaseBinInputStream openFileInputStream = com.realsil.sdk.dfu.k.a.openFileInputStream(a2.icType, d, 0L);
                if (openFileInputStream != null) {
                    openFileInputStream.parseImageHeaderEx();
                    byte[] sha256 = openFileInputStream.getSha256();
                    try {
                        openFileInputStream.close();
                    } catch (Exception e3) {
                        ZLogger.e(e3.toString());
                    }
                    bArr = sha256;
                }
                BaseBinInputStream openFileInputStream2 = com.realsil.sdk.dfu.k.a.openFileInputStream(h, d, 0L);
                if (openFileInputStream2 != null) {
                    openFileInputStream2.setSha256(bArr);
                    arrayList3.add(openFileInputStream2);
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
                        arrayList.add(openFileInputStream2);
                    } else if (1 == a(openFileInputStream2, f)) {
                        arrayList.add(openFileInputStream2);
                    }
                }
                z = false;
            } catch (IOException e4) {
                e4.printStackTrace();
                throw new LoadFileException(e4.getMessage(), 4097);
            }
        }
        a2.lowVersionExist = z;
        a2.subBinInputStreams = arrayList3;
        a2.supportBinInputStreams = arrayList;
        a2.supportSubFileInfos = arrayList2;
        if (v && z && arrayList.size() < 1) {
            a2.updateEnabled = false;
            a2.status = 4104;
        }
        return a2;
    }

    public static boolean a(int i) {
        return i == 5 || i == 21 || i == 4 || i == 20 || i == 2 || i == 18 || i == 7 || i == 23 || i == 6 || i == 22;
    }

    public static BinInfo b(LoadParams loadParams) throws LoadFileException {
        boolean z;
        SparseIntArray sparseIntArray;
        Iterator<SubFileInfo> it;
        int i;
        boolean z2;
        ArrayList arrayList;
        SparseIntArray sparseIntArray2;
        com.realsil.sdk.dfu.f.a aVar;
        int i2;
        char c;
        int activeCompareVersionFlag;
        int activeCompareVersionFlag2;
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b = loadParams.b();
        OtaDeviceInfo f = loadParams.f();
        boolean q = loadParams.q();
        boolean v = loadParams.v();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        a2.icType = h;
        a2.updateBank = loadParams.k();
        try {
            com.realsil.sdk.dfu.f.a c2 = com.realsil.sdk.dfu.e.b.c(loadParams);
            ZLogger.v(c2.toString());
            a2.isPackFile = true;
            a2.icType = c2.b();
            a2.subFileInfos = c2.c(0);
            a2.subFileInfos1 = c2.c(1);
            if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                ZLogger.d(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                a2.updateEnabled = false;
                a2.status = 4101;
                return a2;
            } else if (!c2.a(com.realsil.sdk.dfu.k.a.wrapperBitNumber(2, c2.a()), a2.updateBank)) {
                ZLogger.w("OtaHeader Miss");
                a2.updateEnabled = false;
                a2.status = 4115;
                return a2;
            } else {
                Iterator<SubFileInfo> it2 = c2.c(loadParams.k()).iterator();
                int i3 = 0;
                int i4 = 0;
                while (it2.hasNext()) {
                    SubFileInfo next = it2.next();
                    int wrapperBitNumber = next.wrapperBitNumber();
                    if (!BinIndicator.isIndicatorEnabled(b, wrapperBitNumber)) {
                        ZLogger.v("prohibit upgrade image_id=" + next.imageId);
                    } else {
                        BinIndicator byBitNumber = BinIndicator.getByBitNumber(BinIndicator.BBPRO, wrapperBitNumber);
                        if (byBitNumber != null) {
                            BaseBinInputStream binInputStream = next.getBinInputStream(a2.icType);
                            if (binInputStream == null) {
                                boolean z3 = com.realsil.sdk.dfu.k.a.f13612a;
                                StringBuilder sb = new StringBuilder();
                                it = it2;
                                sb.append("not find image: ");
                                sb.append(byBitNumber.toString());
                                ZLogger.v(z3, sb.toString());
                                if (loadParams.o() && com.realsil.sdk.dfu.d.a.b(wrapperBitNumber)) {
                                    sparseIntArray3.append(byBitNumber.imageId, wrapperBitNumber);
                                }
                                it2 = it;
                            } else {
                                Iterator<SubFileInfo> it3 = it2;
                                if (f != null) {
                                    ImageVersionInfo activeImageVersionInfoByImageId = f.getActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                    i = b;
                                    if (activeImageVersionInfoByImageId != null) {
                                        sparseIntArray2 = sparseIntArray3;
                                        if (activeImageVersionInfoByImageId.getVersion() == -1) {
                                            ZLogger.v(String.format("invalid active version:0x%04X, no need to check", Integer.valueOf(activeImageVersionInfoByImageId.getVersion())));
                                            binInputStream.setActiveCompareVersionFlag(1);
                                            z2 = v;
                                            arrayList = arrayList2;
                                            i2 = 1;
                                            aVar = c2;
                                        } else {
                                            z2 = v;
                                            aVar = c2;
                                            arrayList = arrayList2;
                                            int compareVersion = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, activeImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            ZLogger.v(String.format(Locale.US, "compare active image, bitNumber=%d, compare=%d ", Integer.valueOf(wrapperBitNumber), Integer.valueOf(compareVersion)));
                                            binInputStream.setActiveCompareVersionFlag(compareVersion);
                                            i2 = 1;
                                        }
                                    } else {
                                        z2 = v;
                                        arrayList = arrayList2;
                                        sparseIntArray2 = sparseIntArray3;
                                        aVar = c2;
                                        ZLogger.d("not find active image, bitNumber=" + wrapperBitNumber);
                                        i2 = 1;
                                        binInputStream.setActiveCompareVersionFlag(1);
                                    }
                                    ImageVersionInfo inActiveImageVersionInfoByImageId = f.getInActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                    if (inActiveImageVersionInfoByImageId != null) {
                                        if (inActiveImageVersionInfoByImageId.getVersion() == -1) {
                                            Object[] objArr = new Object[i2];
                                            objArr[0] = Integer.valueOf(inActiveImageVersionInfoByImageId.getVersion());
                                            ZLogger.v(String.format("invalid inactive version:0x%04X, no need to check", objArr));
                                            binInputStream.setInactiveCompareVersionFlag(i2);
                                            c = 2;
                                        } else {
                                            int compareVersion2 = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, inActiveImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            c = 2;
                                            ZLogger.v(String.format(Locale.US, "compare inactive image, bitNumber=%d, compare=%d ", Integer.valueOf(wrapperBitNumber), Integer.valueOf(compareVersion2)));
                                            binInputStream.setInactiveCompareVersionFlag(compareVersion2);
                                            i2 = 1;
                                        }
                                    } else {
                                        c = 2;
                                        ZLogger.d("not find inactive image, bitNumber=" + wrapperBitNumber);
                                        i2 = 1;
                                        binInputStream.setInactiveCompareVersionFlag(1);
                                    }
                                    if (a(wrapperBitNumber)) {
                                        if (i3 > 0) {
                                            if (binInputStream.getActiveCompareVersionFlag() < 0) {
                                                activeCompareVersionFlag2 = binInputStream.getActiveCompareVersionFlag();
                                                i3 = activeCompareVersionFlag2;
                                            }
                                        } else if (i3 == 0) {
                                            activeCompareVersionFlag2 = binInputStream.getActiveCompareVersionFlag();
                                            i3 = activeCompareVersionFlag2;
                                        }
                                    } else if (i4 > 0) {
                                        if (binInputStream.getActiveCompareVersionFlag() < 0) {
                                            activeCompareVersionFlag = binInputStream.getActiveCompareVersionFlag();
                                            i4 = activeCompareVersionFlag;
                                        }
                                    } else if (i4 == 0) {
                                        activeCompareVersionFlag = binInputStream.getActiveCompareVersionFlag();
                                        i4 = activeCompareVersionFlag;
                                    }
                                } else {
                                    i = b;
                                    z2 = v;
                                    arrayList = arrayList2;
                                    sparseIntArray2 = sparseIntArray3;
                                    aVar = c2;
                                    i2 = 1;
                                    c = 2;
                                }
                                arrayList3.add(binInputStream);
                                arrayList4.add(binInputStream);
                                ArrayList arrayList5 = arrayList;
                                arrayList5.add(next);
                                arrayList2 = arrayList5;
                                it2 = it3;
                                b = i;
                                sparseIntArray3 = sparseIntArray2;
                                v = z2;
                                c2 = aVar;
                            }
                        }
                    }
                    it = it2;
                    it2 = it;
                }
                boolean z4 = v;
                ArrayList arrayList6 = arrayList2;
                SparseIntArray sparseIntArray4 = sparseIntArray3;
                try {
                    c2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
                if (z4) {
                    if (i3 > 0) {
                        sparseIntArray = sparseIntArray4;
                        z = false;
                        a2.forceCopyImages = sparseIntArray;
                        a2.lowVersionExist = z;
                        a2.subBinInputStreams = arrayList3;
                        a2.supportBinInputStreams = arrayList4;
                        a2.supportSubFileInfos = arrayList6;
                        return a2;
                    } else if (i3 != 0) {
                        ZLogger.d("all code image version must >= active image version");
                        a2.updateEnabled = false;
                        a2.status = 4114;
                        return a2;
                    } else if (i4 <= 0) {
                        ZLogger.d("there must be at least one data image version> active image version");
                        a2.updateEnabled = false;
                        a2.status = 4113;
                        return a2;
                    }
                }
                z = false;
                sparseIntArray = sparseIntArray4;
                a2.forceCopyImages = sparseIntArray;
                a2.lowVersionExist = z;
                a2.subBinInputStreams = arrayList3;
                a2.supportBinInputStreams = arrayList4;
                a2.supportSubFileInfos = arrayList6;
                return a2;
            }
        } catch (LoadFileException e3) {
            a2.updateEnabled = false;
            a2.status = e3.getErrCode();
            return a2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.realsil.sdk.dfu.model.BinInfo c(com.realsil.sdk.dfu.image.LoadParams r31) throws com.realsil.sdk.dfu.exception.LoadFileException {
        /*
            Method dump skipped, instructions count: 880
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.u.g.c(com.realsil.sdk.dfu.image.LoadParams):com.realsil.sdk.dfu.model.BinInfo");
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
                            ZLogger.v("section size validate ok: " + imageSize);
                        }
                    }
                }
            }
        }
        return 1;
    }

    public static int b(int i, BaseBinInputStream baseBinInputStream, OtaDeviceInfo otaDeviceInfo) {
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

    /* JADX WARN: Removed duplicated region for block: B:18:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(int r20, com.realsil.sdk.dfu.image.stream.BaseBinInputStream r21, com.realsil.sdk.dfu.model.OtaDeviceInfo r22) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.u.g.a(int, com.realsil.sdk.dfu.image.stream.BaseBinInputStream, com.realsil.sdk.dfu.model.OtaDeviceInfo):int");
    }
}
