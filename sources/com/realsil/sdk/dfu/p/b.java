package com.realsil.sdk.dfu.p;

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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class b extends com.realsil.sdk.dfu.k.a {

    /* loaded from: classes12.dex */
    public static class a implements Comparator<BaseBinInputStream> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(BaseBinInputStream baseBinInputStream, BaseBinInputStream baseBinInputStream2) {
            return baseBinInputStream2.otaTempBufferCheckOrder - baseBinInputStream.otaTempBufferCheckOrder;
        }
    }

    /* renamed from: com.realsil.sdk.dfu.p.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0724b implements Comparator<BaseBinInputStream> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(BaseBinInputStream baseBinInputStream, BaseBinInputStream baseBinInputStream2) {
            return baseBinInputStream2.otaTempBufferCheckOrder - baseBinInputStream.otaTempBufferCheckOrder;
        }
    }

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

    public static int compareVersion(int i, int i2, int i3, int i4, int i5) {
        ZLogger.v(String.format(Locale.US, "checkVersion: version1=%08X(%d) %s, version2=%08X(%d) %s", Integer.valueOf(i), Integer.valueOf(i), DfuUtils.convertVersion2Str(i2, i, i5), Integer.valueOf(i3), Integer.valueOf(i3), DfuUtils.convertVersion2Str(i4, i3, i5)));
        if (i2 <= 0) {
            return com.realsil.sdk.dfu.d.b.a(i, com.realsil.sdk.dfu.c.c.a(i3, i4, i5));
        }
        if (i5 == 1) {
            int i6 = i & 255;
            int i7 = i3 & 255;
            if (i6 > i7) {
                return 1;
            }
            if (i6 == i7) {
                int i8 = (i >> 8) & 255;
                int i9 = (i3 >> 8) & 255;
                if (i8 > i9) {
                    return 1;
                }
                if (i8 == i9) {
                    int i10 = (i >> 16) & 255;
                    int i11 = (i3 >> 16) & 255;
                    if (i10 > i11) {
                        return 1;
                    }
                    if (i10 == i11) {
                        return com.realsil.sdk.dfu.d.b.a((i >> 24) & 255, (i3 >> 24) & 255);
                    }
                }
            }
            return -1;
        } else if (i5 == 515) {
            int i12 = (i >> 8) & 255;
            int i13 = (i3 >> 8) & 255;
            if (i12 > i13) {
                return 1;
            }
            if (i12 == i13) {
                int i14 = i & 255;
                int i15 = i3 & 255;
                if (i14 > i15) {
                    return 1;
                }
                if (i14 == i15) {
                    return 0;
                }
            }
            return -1;
        } else if (i5 == 2) {
            int i16 = (i >> 24) & 255;
            int i17 = (i3 >> 24) & 255;
            if (i16 > i17) {
                return 1;
            }
            if (i16 == i17) {
                int i18 = (i >> 16) & 255;
                int i19 = (i3 >> 16) & 255;
                if (i18 > i19) {
                    return 1;
                }
                if (i18 == i19) {
                    int i20 = (i >> 8) & 255;
                    int i21 = (i3 >> 8) & 255;
                    if (i20 > i21) {
                        return 1;
                    }
                    if (i20 == i21) {
                        return com.realsil.sdk.dfu.d.b.a(i & 255, i3 & 255);
                    }
                }
            }
            return -1;
        } else if (i5 == 3) {
            int i22 = i & 15;
            int i23 = i3 & 15;
            if (i22 > i23) {
                return 1;
            }
            if (i22 == i23) {
                int i24 = (i >> 4) & 255;
                int i25 = (i3 >> 4) & 255;
                if (i24 > i25) {
                    return 1;
                }
                if (i24 == i25) {
                    int i26 = (i >> 12) & 32767;
                    int i27 = (i3 >> 12) & 32767;
                    if (i26 > i27) {
                        return 1;
                    }
                    if (i26 == i27) {
                        return com.realsil.sdk.dfu.d.b.a((i >> 27) & 31, (i3 >> 27) & 31);
                    }
                }
            }
            return -1;
        } else if (i5 == 5) {
            int i28 = i & 15;
            int i29 = i3 & 15;
            if (i28 > i29) {
                return 1;
            }
            if (i28 == i29) {
                int i30 = (i >> 4) & 255;
                int i31 = (i3 >> 4) & 255;
                if (i30 > i31) {
                    return 1;
                }
                if (i30 == i31) {
                    int i32 = (i >> 12) & 511;
                    int i33 = (i3 >> 12) & 511;
                    if (i32 > i33) {
                        return 1;
                    }
                    if (i32 == i33) {
                        return com.realsil.sdk.dfu.d.b.a((i >> 21) & 32767, (i3 >> 21) & 32767);
                    }
                }
            }
            return -1;
        } else if (i5 == 4) {
            return com.realsil.sdk.dfu.d.b.a(i, i3);
        } else {
            if (i5 == 7) {
                return com.realsil.sdk.dfu.d.b.a(i, i3);
            }
            if (i5 == 514) {
                int i34 = (i >> 8) & 255;
                int i35 = (i3 >> 8) & 255;
                if (i34 > i35) {
                    return 1;
                }
                if (i34 == i35) {
                    int i36 = i & 255;
                    int i37 = i3 & 255;
                    if (i36 > i37) {
                        return 1;
                    }
                    if (i36 == i37) {
                        int i38 = (i >> 24) & 255;
                        int i39 = (i3 >> 24) & 255;
                        if (i38 > i39) {
                            return 1;
                        }
                        if (i38 == i39) {
                            int i40 = (i >> 16) & 255;
                            int i41 = (i3 >> 16) & 255;
                            if (i40 > i41) {
                                return 1;
                            }
                            if (i40 == i41) {
                                return 0;
                            }
                        }
                    }
                }
                return -1;
            }
            return com.realsil.sdk.dfu.d.b.a(i, i3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.realsil.sdk.dfu.model.BinInfo loadImageBinInfo(com.realsil.sdk.dfu.image.LoadParams r25) throws com.realsil.sdk.dfu.exception.LoadFileException {
        /*
            Method dump skipped, instructions count: 1222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.p.b.loadImageBinInfo(com.realsil.sdk.dfu.image.LoadParams):com.realsil.sdk.dfu.model.BinInfo");
    }

    public static int a(int i, BaseBinInputStream baseBinInputStream, OtaDeviceInfo otaDeviceInfo) {
        if (otaDeviceInfo == null) {
            return 1;
        }
        int imageSize = baseBinInputStream.getImageSize();
        int i2 = otaDeviceInfo.icType;
        if (i2 != 4 && i2 != 6 && i2 != 7 && i2 != 8 && i2 != 13) {
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
        if (i != 4 && i != 6 && i != 7 && i != 8 && i != 13) {
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
