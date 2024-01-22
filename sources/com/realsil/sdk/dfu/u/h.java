package com.realsil.sdk.dfu.u;

import android.content.Context;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class h extends com.realsil.sdk.dfu.k.a {

    /* loaded from: classes12.dex */
    public static class a implements Comparator<BaseBinInputStream> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(BaseBinInputStream baseBinInputStream, BaseBinInputStream baseBinInputStream2) {
            return baseBinInputStream.versionCheckOrder - baseBinInputStream2.versionCheckOrder;
        }
    }

    /* loaded from: classes12.dex */
    public static class b implements Comparator<BaseBinInputStream> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(BaseBinInputStream baseBinInputStream, BaseBinInputStream baseBinInputStream2) {
            return baseBinInputStream.versionCheckOrder - baseBinInputStream2.versionCheckOrder;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(com.realsil.sdk.dfu.image.stream.BaseBinInputStream r16, com.realsil.sdk.dfu.model.OtaDeviceInfo r17, int r18) {
        /*
            r0 = r16
            r1 = r17
            int r10 = r0.imageVersion
            int r11 = r0.otaVersion
            int r2 = r16.getImageId()
            com.realsil.sdk.dfu.model.ImageVersionInfo r12 = r1.getActiveImageVersionInfoByImageId(r2)
            r13 = 2
            r14 = 0
            r15 = -1
            r9 = 1
            if (r12 == 0) goto L7a
            int r2 = r12.getVersion()
            if (r2 != r15) goto L2e
            java.lang.Object[] r2 = new java.lang.Object[r9]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)
            r2[r14] = r3
            java.lang.String r3 = "invalid active version:0x%04X, no need to check"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            com.realsil.sdk.core.logger.ZLogger.v(r2)
            goto L7a
        L2e:
            int r2 = r1.icType
            int r3 = r16.getBinId()
            int r6 = r1.protocolType
            int r7 = r12.getVersion()
            int r8 = r1.specVersion
            r4 = r10
            r5 = r11
            r9 = r18
            int r2 = com.realsil.sdk.dfu.k.a.compareVersion(r2, r3, r4, r5, r6, r7, r8, r9)
            if (r2 != r15) goto L65
            java.util.Locale r0 = java.util.Locale.US
            java.lang.Object[] r1 = new java.lang.Object[r13]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            r1[r14] = r2
            int r2 = r12.getVersion()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r8 = 1
            r1[r8] = r2
            java.lang.String r2 = "active image: file(%08X)<device(%08X)"
            java.lang.String r0 = java.lang.String.format(r0, r2, r1)
            com.realsil.sdk.core.logger.ZLogger.v(r0)
            return r15
        L65:
            r8 = 1
            boolean r2 = com.realsil.sdk.dfu.k.a.f13612a
            java.lang.Object[] r3 = new java.lang.Object[r8]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r3[r14] = r4
            java.lang.String r4 = "active version validate ok :0x%04X"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            com.realsil.sdk.core.logger.ZLogger.v(r2, r3)
            goto L7b
        L7a:
            r8 = r9
        L7b:
            int r2 = r16.getImageId()
            com.realsil.sdk.dfu.model.ImageVersionInfo r9 = r1.getInActiveImageVersionInfoByImageId(r2)
            if (r9 == 0) goto Le8
            int r2 = r9.getVersion()
            if (r2 != r15) goto L9d
            java.lang.Object[] r0 = new java.lang.Object[r8]
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)
            r0[r14] = r1
            java.lang.String r1 = "invalid inactive version:0x%04X, no need to check"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            com.realsil.sdk.core.logger.ZLogger.v(r0)
            goto Le8
        L9d:
            int r2 = r1.icType
            int r3 = r16.getBinId()
            int r4 = r1.protocolType
            int r5 = r9.getVersion()
            int r6 = r1.specVersion
            r0 = r2
            r1 = r3
            r2 = r10
            r3 = r11
            r7 = r18
            int r0 = com.realsil.sdk.dfu.k.a.compareVersion(r0, r1, r2, r3, r4, r5, r6, r7)
            if (r0 != r15) goto Ld5
            java.util.Locale r0 = java.util.Locale.US
            java.lang.Object[] r1 = new java.lang.Object[r13]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            r1[r14] = r2
            int r2 = r9.getVersion()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r8] = r2
            java.lang.String r2 = "inactive image:  file(%08X)<device(%08X)"
            java.lang.String r0 = java.lang.String.format(r0, r2, r1)
            com.realsil.sdk.core.logger.ZLogger.v(r0)
            return r15
        Ld5:
            boolean r0 = com.realsil.sdk.dfu.k.a.f13612a
            java.lang.Object[] r1 = new java.lang.Object[r8]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            r1[r14] = r2
            java.lang.String r2 = "inactive version validate ok :0x%04X"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            com.realsil.sdk.core.logger.ZLogger.v(r0, r1)
        Le8:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.u.h.a(com.realsil.sdk.dfu.image.stream.BaseBinInputStream, com.realsil.sdk.dfu.model.OtaDeviceInfo, int):int");
    }

    public static BinInfo b(LoadParams loadParams) throws LoadFileException {
        Context context;
        Iterator<SubFileInfo> it;
        int i;
        boolean z;
        com.realsil.sdk.dfu.f.a aVar;
        int i2;
        int activeCompareVersionFlag;
        int activeCompareVersionFlag2;
        Context a2 = loadParams.a();
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b2 = loadParams.b();
        OtaDeviceInfo f = loadParams.f();
        boolean q = loadParams.q();
        boolean v = loadParams.v();
        int k = loadParams.k();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        BinInfo b3 = com.realsil.sdk.dfu.k.a.b(d, e);
        b3.updateBank = loadParams.k();
        try {
            com.realsil.sdk.dfu.f.a b4 = com.realsil.sdk.dfu.e.b.b(loadParams);
            ZLogger.v(b4.toString());
            b3.isPackFile = true;
            b3.icType = b4.b();
            b3.subFileInfos = b4.c(0);
            b3.subFileInfos1 = b4.c(1);
            if (q && !com.realsil.sdk.dfu.k.a.a(b3.icType, h)) {
                ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(b3.icType)));
                b3.updateEnabled = false;
                b3.status = 4101;
                return b3;
            } else if (b4.a(2048) == null) {
                ZLogger.w("OtaHeader Miss");
                b3.updateEnabled = false;
                b3.status = 4115;
                return b3;
            } else if (!b4.a(com.realsil.sdk.dfu.k.a.wrapperBitNumber(2, b4.a()), b3.updateBank)) {
                ZLogger.w("OtaHeader Miss");
                b3.updateEnabled = false;
                b3.status = 4115;
                return b3;
            } else {
                Iterator<SubFileInfo> it2 = b4.c(k).iterator();
                int i3 = 0;
                int i4 = 0;
                while (it2.hasNext()) {
                    SubFileInfo next = it2.next();
                    if (!BinIndicator.isIndicatorEnabled(b2, next.wrapperBitNumber())) {
                        ZLogger.v("prohibit upgrade image_id=" + next.imageId);
                    } else {
                        BinIndicator byBitNumber = BinIndicator.getByBitNumber(BinIndicator.BBPRO, next.wrapperBitNumber());
                        if (byBitNumber != null) {
                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, byBitNumber.toString());
                            BaseBinInputStream assetsBinInputStream = next.getAssetsBinInputStream(a2, b3.icType);
                            if (assetsBinInputStream == null) {
                                ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, "not find image: " + byBitNumber.imageId);
                            } else {
                                if (f != null) {
                                    ImageVersionInfo activeImageVersionInfoByImageId = f.getActiveImageVersionInfoByImageId(assetsBinInputStream.getImageId());
                                    context = a2;
                                    if (activeImageVersionInfoByImageId != null) {
                                        it = it2;
                                        if (activeImageVersionInfoByImageId.getVersion() == -1) {
                                            i = b2;
                                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("invalid active version:0x%04X, no need to check", Integer.valueOf(activeImageVersionInfoByImageId.getVersion())));
                                            assetsBinInputStream.setActiveCompareVersionFlag(1);
                                            z = v;
                                            aVar = b4;
                                            i2 = 1;
                                        } else {
                                            i = b2;
                                            z = v;
                                            aVar = b4;
                                            int compareVersion = com.realsil.sdk.dfu.k.a.compareVersion(b3.icType, assetsBinInputStream.getBinId(), assetsBinInputStream.imageVersion, assetsBinInputStream.otaVersion, f.protocolType, activeImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            i2 = 1;
                                            ZLogger.v(String.format(Locale.US, "compare active image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion)));
                                            assetsBinInputStream.setActiveCompareVersionFlag(compareVersion);
                                        }
                                    } else {
                                        it = it2;
                                        i = b2;
                                        z = v;
                                        aVar = b4;
                                        i2 = 1;
                                        ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("not find active image, imageId=0x%04X", Integer.valueOf(assetsBinInputStream.getImageId())));
                                        assetsBinInputStream.setActiveCompareVersionFlag(1);
                                    }
                                    ImageVersionInfo inActiveImageVersionInfoByImageId = f.getInActiveImageVersionInfoByImageId(assetsBinInputStream.getImageId());
                                    if (inActiveImageVersionInfoByImageId != null) {
                                        if (inActiveImageVersionInfoByImageId.getVersion() == -1) {
                                            boolean z2 = com.realsil.sdk.dfu.k.a.f13612a;
                                            Object[] objArr = new Object[i2];
                                            objArr[0] = Integer.valueOf(inActiveImageVersionInfoByImageId.getVersion());
                                            ZLogger.v(z2, String.format("invalid inactive version:0x%04X, no need to check", objArr));
                                            assetsBinInputStream.setInactiveCompareVersionFlag(i2);
                                        } else {
                                            int compareVersion2 = com.realsil.sdk.dfu.k.a.compareVersion(b3.icType, assetsBinInputStream.getBinId(), assetsBinInputStream.imageVersion, assetsBinInputStream.otaVersion, f.protocolType, inActiveImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            ZLogger.v(String.format(Locale.US, "compare inactive image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion2)));
                                            assetsBinInputStream.setInactiveCompareVersionFlag(compareVersion2);
                                        }
                                    } else {
                                        int i5 = i2;
                                        boolean z3 = com.realsil.sdk.dfu.k.a.f13612a;
                                        Object[] objArr2 = new Object[i5];
                                        objArr2[0] = Integer.valueOf(assetsBinInputStream.getImageId());
                                        ZLogger.v(z3, String.format("not find inactive image, imageId=0x%04X", objArr2));
                                        assetsBinInputStream.setInactiveCompareVersionFlag(i5);
                                    }
                                    if (com.realsil.sdk.dfu.d.a.a(next.wrapperBitNumber())) {
                                        if (i3 > 0) {
                                            if (assetsBinInputStream.getActiveCompareVersionFlag() < 0) {
                                                activeCompareVersionFlag2 = assetsBinInputStream.getActiveCompareVersionFlag();
                                                i3 = activeCompareVersionFlag2;
                                            }
                                        } else if (i3 == 0) {
                                            activeCompareVersionFlag2 = assetsBinInputStream.getActiveCompareVersionFlag();
                                            i3 = activeCompareVersionFlag2;
                                        }
                                    } else if (i4 > 0) {
                                        if (assetsBinInputStream.getActiveCompareVersionFlag() < 0) {
                                            activeCompareVersionFlag = assetsBinInputStream.getActiveCompareVersionFlag();
                                            i4 = activeCompareVersionFlag;
                                        }
                                    } else if (i4 == 0) {
                                        activeCompareVersionFlag = assetsBinInputStream.getActiveCompareVersionFlag();
                                        i4 = activeCompareVersionFlag;
                                    }
                                } else {
                                    context = a2;
                                    it = it2;
                                    i = b2;
                                    z = v;
                                    aVar = b4;
                                }
                                arrayList2.add(assetsBinInputStream);
                                arrayList3.add(assetsBinInputStream);
                                arrayList.add(next);
                                a2 = context;
                                it2 = it;
                                b2 = i;
                                v = z;
                                b4 = aVar;
                            }
                        }
                    }
                }
                boolean z4 = v;
                try {
                    b4.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
                if (z4) {
                    ZLogger.v(String.format(Locale.US, "nonConfigVersionFlag=%d, configVersionFlag=%d", Integer.valueOf(i3), Integer.valueOf(i4)));
                    if (i3 <= 0) {
                        if (i3 != 0) {
                            ZLogger.w("all code image version must >= active image version");
                            b3.updateEnabled = false;
                            b3.status = 4114;
                            return b3;
                        } else if (i4 <= 0) {
                            ZLogger.d("there must be at least one data image version> active image version");
                            b3.updateEnabled = false;
                            b3.status = 4113;
                            return b3;
                        }
                    }
                }
                b3.lowVersionExist = false;
                b3.subBinInputStreams = arrayList2;
                b3.supportBinInputStreams = arrayList3;
                b3.supportSubFileInfos = arrayList;
                return b3;
            }
        } catch (LoadFileException e3) {
            b3.updateEnabled = false;
            b3.status = e3.getErrCode();
            return b3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:92:0x0342, code lost:
        r12 = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.realsil.sdk.dfu.model.BinInfo c(com.realsil.sdk.dfu.image.LoadParams r30) throws com.realsil.sdk.dfu.exception.LoadFileException {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.u.h.c(com.realsil.sdk.dfu.image.LoadParams):com.realsil.sdk.dfu.model.BinInfo");
    }

    public static BinInfo d(LoadParams loadParams) throws LoadFileException {
        boolean z;
        boolean z2;
        Iterator<SubFileInfo> it;
        int i;
        boolean z3;
        boolean z4;
        int i2;
        int activeCompareVersionFlag;
        int activeCompareVersionFlag2;
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b2 = loadParams.b();
        boolean q = loadParams.q();
        boolean v = loadParams.v();
        int k = loadParams.k();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        a2.updateBank = loadParams.k();
        try {
            com.realsil.sdk.dfu.f.a c = com.realsil.sdk.dfu.e.b.c(loadParams);
            int i3 = 1;
            a2.isPackFile = true;
            a2.icType = c.b();
            a2.subFileInfos = c.c(0);
            a2.subFileInfos1 = c.c(1);
            if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                a2.updateEnabled = false;
                a2.status = 4101;
                return a2;
            } else if (!c.a(com.realsil.sdk.dfu.k.a.wrapperBitNumber(2, c.a()), a2.updateBank)) {
                ZLogger.w("OtaHeader Miss");
                a2.updateEnabled = false;
                a2.status = 4115;
                return a2;
            } else {
                Iterator<SubFileInfo> it2 = c.c(k).iterator();
                int i4 = 0;
                int i5 = 0;
                boolean z5 = false;
                while (it2.hasNext()) {
                    SubFileInfo next = it2.next();
                    int wrapperBitNumber = next.wrapperBitNumber();
                    if (!BinIndicator.isIndicatorEnabled(b2, wrapperBitNumber)) {
                        ZLogger.v("prohibit upgrade image_id=" + next.imageId);
                    } else {
                        BinIndicator byBitNumber = BinIndicator.getByBitNumber(BinIndicator.BBPRO, wrapperBitNumber);
                        int i6 = byBitNumber != null ? byBitNumber.versionFormat : i3;
                        BaseBinInputStream binInputStream = next.getBinInputStream(a2.icType);
                        if (binInputStream == null) {
                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, "invalid stream: " + next.toString());
                        } else {
                            if (next.binId == 1024) {
                                z5 = true;
                            }
                            binInputStream.versionCheckOrder = com.realsil.sdk.dfu.d.b.a(binInputStream.getImageId());
                            OtaDeviceInfo f = loadParams.f();
                            if (f != null) {
                                ImageVersionInfo activeImageVersionInfoByImageId = f.getActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                it = it2;
                                if (activeImageVersionInfoByImageId != null) {
                                    i = b2;
                                    if (activeImageVersionInfoByImageId.getVersion() == -1) {
                                        z4 = z5;
                                        i2 = 1;
                                        ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("invalid active version:0x%04X, no need to check", Integer.valueOf(activeImageVersionInfoByImageId.getVersion())));
                                        binInputStream.setActiveCompareVersionFlag(1);
                                        z3 = v;
                                    } else {
                                        z4 = z5;
                                        z3 = v;
                                        int compareVersion = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, activeImageVersionInfoByImageId.getVersion(), f.specVersion, i6);
                                        i2 = 1;
                                        ZLogger.v(String.format(Locale.US, "compare active image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion)));
                                        binInputStream.setActiveCompareVersionFlag(compareVersion);
                                    }
                                } else {
                                    i = b2;
                                    z3 = v;
                                    z4 = z5;
                                    i2 = 1;
                                    ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("not find active image, imageId=0x%04X", Integer.valueOf(binInputStream.getImageId())));
                                    binInputStream.setActiveCompareVersionFlag(1);
                                }
                                ImageVersionInfo inActiveImageVersionInfoByImageId = f.getInActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                if (inActiveImageVersionInfoByImageId != null) {
                                    if (inActiveImageVersionInfoByImageId.getVersion() == -1) {
                                        boolean z6 = com.realsil.sdk.dfu.k.a.f13612a;
                                        Object[] objArr = new Object[i2];
                                        objArr[0] = Integer.valueOf(inActiveImageVersionInfoByImageId.getVersion());
                                        ZLogger.v(z6, String.format("invalid inactive version:0x%04X, no need to check", objArr));
                                        binInputStream.setInactiveCompareVersionFlag(i2);
                                    } else {
                                        int compareVersion2 = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, inActiveImageVersionInfoByImageId.getVersion(), f.specVersion, i6);
                                        ZLogger.v(String.format(Locale.US, "compare inactive image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion2)));
                                        binInputStream.setInactiveCompareVersionFlag(compareVersion2);
                                    }
                                } else {
                                    int i7 = i2;
                                    boolean z7 = com.realsil.sdk.dfu.k.a.f13612a;
                                    Object[] objArr2 = new Object[i7];
                                    objArr2[0] = Integer.valueOf(binInputStream.getImageId());
                                    ZLogger.v(z7, String.format("not find inactive image, imageId=0x%04X", objArr2));
                                    binInputStream.setInactiveCompareVersionFlag(i7);
                                }
                                if (com.realsil.sdk.dfu.d.a.a(next.wrapperBitNumber())) {
                                    if (i4 > 0) {
                                        if (binInputStream.getActiveCompareVersionFlag() < 0) {
                                            activeCompareVersionFlag2 = binInputStream.getActiveCompareVersionFlag();
                                            i4 = activeCompareVersionFlag2;
                                        }
                                    } else if (i4 == 0) {
                                        activeCompareVersionFlag2 = binInputStream.getActiveCompareVersionFlag();
                                        i4 = activeCompareVersionFlag2;
                                    }
                                } else if (i5 > 0) {
                                    if (binInputStream.getActiveCompareVersionFlag() < 0) {
                                        activeCompareVersionFlag = binInputStream.getActiveCompareVersionFlag();
                                        i5 = activeCompareVersionFlag;
                                    }
                                } else if (i5 == 0) {
                                    activeCompareVersionFlag = binInputStream.getActiveCompareVersionFlag();
                                    i5 = activeCompareVersionFlag;
                                }
                            } else {
                                it = it2;
                                i = b2;
                                z3 = v;
                                z4 = z5;
                            }
                            arrayList2.add(binInputStream);
                            arrayList3.add(binInputStream);
                            arrayList.add(next);
                            it2 = it;
                            b2 = i;
                            z5 = z4;
                            v = z3;
                            i3 = 1;
                        }
                    }
                }
                boolean z8 = v;
                try {
                    c.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
                if (z8) {
                    if (loadParams.l() == 0) {
                        ZLogger.v(String.format(Locale.US, "nonConfigVersionFlag=%d, configVersionFlag=%d", Integer.valueOf(i4), Integer.valueOf(i5)));
                        if (i4 <= 0) {
                            if (i4 != 0) {
                                ZLogger.w("all code image version must >= active image version");
                                a2.updateEnabled = false;
                                a2.status = 4114;
                                return a2;
                            } else if (i5 < 0) {
                                ZLogger.d("there must be at least one data image version >= active image version");
                                a2.updateEnabled = false;
                                a2.status = 4113;
                                return a2;
                            } else if (i5 == 0 && !z5) {
                                ZLogger.d("there must be at least one data image version> active image version");
                                a2.updateEnabled = false;
                                a2.status = 4113;
                                return a2;
                            } else {
                                z = false;
                            }
                        }
                    } else {
                        Collections.sort(arrayList2, new b());
                        Iterator it3 = arrayList2.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                z = false;
                                break;
                            }
                            BaseBinInputStream baseBinInputStream = (BaseBinInputStream) it3.next();
                            if (baseBinInputStream.versionCheckOrder != 254) {
                                if (baseBinInputStream.getActiveCompareVersionFlag() <= 0) {
                                    if (baseBinInputStream.getActiveCompareVersionFlag() != 0) {
                                        z = false;
                                        ZLogger.d(String.format(Locale.US, "low version image: 0x%04X", Integer.valueOf(baseBinInputStream.getImageId())));
                                        break;
                                    }
                                } else {
                                    z = false;
                                    z2 = true;
                                    break;
                                }
                            }
                        }
                        z2 = z;
                        if (!z2) {
                            a2.updateEnabled = z;
                            a2.status = 4114;
                            return a2;
                        }
                    }
                    a2.lowVersionExist = z;
                    a2.subBinInputStreams = arrayList2;
                    a2.supportBinInputStreams = arrayList3;
                    a2.supportSubFileInfos = arrayList;
                    return a2;
                }
                z = false;
                a2.lowVersionExist = z;
                a2.subBinInputStreams = arrayList2;
                a2.supportBinInputStreams = arrayList3;
                a2.supportSubFileInfos = arrayList;
                return a2;
            }
        } catch (LoadFileException e3) {
            a2.updateEnabled = false;
            a2.status = e3.getErrCode();
            return a2;
        }
    }

    public static BinInfo e(LoadParams loadParams) throws LoadFileException {
        boolean z;
        SparseIntArray sparseIntArray;
        Iterator<SubFileInfo> it;
        int i;
        boolean z2;
        SparseIntArray sparseIntArray2;
        com.realsil.sdk.dfu.f.a aVar;
        int i2;
        int activeCompareVersionFlag;
        int activeCompareVersionFlag2;
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b2 = loadParams.b();
        boolean q = loadParams.q();
        boolean v = loadParams.v();
        int k = loadParams.k();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        a2.updateBank = loadParams.k();
        try {
            com.realsil.sdk.dfu.f.a c = com.realsil.sdk.dfu.e.b.c(loadParams);
            a2.isPackFile = true;
            a2.icType = c.b();
            a2.subFileInfos = c.c(0);
            a2.subFileInfos1 = c.c(1);
            if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                a2.updateEnabled = false;
                a2.status = 4101;
                return a2;
            } else if (!c.a(com.realsil.sdk.dfu.k.a.wrapperBitNumber(2, c.a()), a2.updateBank)) {
                ZLogger.w("OtaHeader Miss");
                a2.updateEnabled = false;
                a2.status = 4115;
                return a2;
            } else {
                Iterator<SubFileInfo> it2 = c.c(k).iterator();
                int i3 = 0;
                int i4 = 0;
                while (it2.hasNext()) {
                    SubFileInfo next = it2.next();
                    if (!BinIndicator.isIndicatorEnabled(b2, next.wrapperBitNumber())) {
                        ZLogger.v("prohibit upgrade image_id=" + next.imageId);
                    } else {
                        BinIndicator byBitNumber = BinIndicator.getByBitNumber(BinIndicator.BBPRO, next.wrapperBitNumber());
                        if (byBitNumber != null) {
                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, byBitNumber.toString());
                            BaseBinInputStream binInputStream = next.getBinInputStream(a2.icType);
                            if (binInputStream == null) {
                                ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, "not find image: " + byBitNumber.imageId);
                                if (loadParams.o() && com.realsil.sdk.dfu.d.a.b(next.wrapperBitNumber())) {
                                    sparseIntArray3.append(byBitNumber.imageId, next.wrapperBitNumber());
                                }
                            } else {
                                OtaDeviceInfo f = loadParams.f();
                                if (f != null) {
                                    ImageVersionInfo activeImageVersionInfoByImageId = f.getActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                    it = it2;
                                    if (activeImageVersionInfoByImageId != null) {
                                        i = b2;
                                        if (activeImageVersionInfoByImageId.getVersion() == -1) {
                                            sparseIntArray2 = sparseIntArray3;
                                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("invalid active version:0x%04X, no need to check", Integer.valueOf(activeImageVersionInfoByImageId.getVersion())));
                                            binInputStream.setActiveCompareVersionFlag(1);
                                            z2 = v;
                                            aVar = c;
                                            i2 = 1;
                                        } else {
                                            sparseIntArray2 = sparseIntArray3;
                                            z2 = v;
                                            aVar = c;
                                            int compareVersion = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, activeImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            i2 = 1;
                                            ZLogger.v(String.format(Locale.US, "compare active image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion)));
                                            binInputStream.setActiveCompareVersionFlag(compareVersion);
                                        }
                                    } else {
                                        i = b2;
                                        z2 = v;
                                        sparseIntArray2 = sparseIntArray3;
                                        aVar = c;
                                        i2 = 1;
                                        ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("not find active image, imageId=0x%04X", Integer.valueOf(binInputStream.getImageId())));
                                        binInputStream.setActiveCompareVersionFlag(1);
                                    }
                                    ImageVersionInfo inActiveImageVersionInfoByImageId = f.getInActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                    if (inActiveImageVersionInfoByImageId != null) {
                                        if (inActiveImageVersionInfoByImageId.getVersion() == -1) {
                                            boolean z3 = com.realsil.sdk.dfu.k.a.f13612a;
                                            Object[] objArr = new Object[i2];
                                            objArr[0] = Integer.valueOf(inActiveImageVersionInfoByImageId.getVersion());
                                            ZLogger.v(z3, String.format("invalid inactive version:0x%04X, no need to check", objArr));
                                            binInputStream.setInactiveCompareVersionFlag(i2);
                                        } else {
                                            int compareVersion2 = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, inActiveImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            ZLogger.v(String.format(Locale.US, "compare inactive image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion2)));
                                            binInputStream.setInactiveCompareVersionFlag(compareVersion2);
                                        }
                                    } else {
                                        boolean z4 = com.realsil.sdk.dfu.k.a.f13612a;
                                        Object[] objArr2 = new Object[i2];
                                        objArr2[0] = Integer.valueOf(binInputStream.getImageId());
                                        ZLogger.v(z4, String.format("not find inactive image, imageId=0x%04X", objArr2));
                                        binInputStream.setInactiveCompareVersionFlag(i2);
                                    }
                                    if (com.realsil.sdk.dfu.d.a.a(next.wrapperBitNumber())) {
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
                                    it = it2;
                                    i = b2;
                                    z2 = v;
                                    sparseIntArray2 = sparseIntArray3;
                                    aVar = c;
                                }
                                arrayList2.add(binInputStream);
                                arrayList3.add(binInputStream);
                                arrayList.add(next);
                                it2 = it;
                                b2 = i;
                                sparseIntArray3 = sparseIntArray2;
                                v = z2;
                                c = aVar;
                            }
                        }
                    }
                }
                boolean z5 = v;
                SparseIntArray sparseIntArray4 = sparseIntArray3;
                try {
                    c.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
                if (z5) {
                    ZLogger.v(String.format(Locale.US, "nonConfigVersionFlag=%d, configVersionFlag=%d", Integer.valueOf(i3), Integer.valueOf(i4)));
                    if (i3 > 0) {
                        sparseIntArray = sparseIntArray4;
                        z = false;
                        a2.forceCopyImages = sparseIntArray;
                        a2.lowVersionExist = z;
                        a2.subBinInputStreams = arrayList2;
                        a2.supportBinInputStreams = arrayList3;
                        a2.supportSubFileInfos = arrayList;
                        return a2;
                    } else if (i3 != 0) {
                        ZLogger.w("all code image version must >= active image version");
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
                a2.subBinInputStreams = arrayList2;
                a2.supportBinInputStreams = arrayList3;
                a2.supportSubFileInfos = arrayList;
                return a2;
            }
        } catch (LoadFileException e3) {
            a2.updateEnabled = false;
            a2.status = e3.getErrCode();
            return a2;
        }
    }

    public static BinInfo f(LoadParams loadParams) throws LoadFileException {
        Iterator<SubFileInfo> it;
        int i;
        boolean z;
        com.realsil.sdk.dfu.f.a aVar;
        int i2;
        int activeCompareVersionFlag;
        int activeCompareVersionFlag2;
        boolean z2;
        BaseBinInputStream openFileInputStream;
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b2 = loadParams.b();
        OtaDeviceInfo f = loadParams.f();
        boolean v = loadParams.v();
        boolean q = loadParams.q();
        int k = loadParams.k();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        a2.updateBank = loadParams.k();
        try {
            com.realsil.sdk.dfu.f.a c = com.realsil.sdk.dfu.e.b.c(loadParams);
            if (c == null) {
                try {
                    openFileInputStream = com.realsil.sdk.dfu.k.a.openFileInputStream(h, d, 0L);
                } catch (IOException e2) {
                    ZLogger.v(e2.toString());
                }
                if (openFileInputStream != null) {
                    arrayList2.add(openFileInputStream);
                    a2.icType = openFileInputStream.getIcType();
                    a2.version = openFileInputStream.getImageVersion();
                    if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                        ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                        a2.updateEnabled = false;
                        a2.status = 4101;
                        return a2;
                    } else if (!v || 1 == com.realsil.sdk.dfu.k.a.checkSingleImageVersion(openFileInputStream, f)) {
                        arrayList3.add(openFileInputStream);
                    } else {
                        z2 = true;
                        a2.lowVersionExist = z2;
                        a2.subBinInputStreams = arrayList2;
                        a2.supportBinInputStreams = arrayList3;
                        a2.supportSubFileInfos = arrayList;
                        return a2;
                    }
                }
            } else {
                ZLogger.v(c.toString());
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
                Iterator<SubFileInfo> it2 = c.c(k).iterator();
                int i3 = 0;
                int i4 = 0;
                while (it2.hasNext()) {
                    SubFileInfo next = it2.next();
                    if (!BinIndicator.isIndicatorEnabled(b2, next.wrapperBitNumber())) {
                        ZLogger.v("prohibit upgrade image_id=" + next.imageId);
                    } else {
                        BinIndicator byBitNumber = BinIndicator.getByBitNumber(BinIndicator.BBPRO, next.wrapperBitNumber());
                        if (byBitNumber != null) {
                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, byBitNumber.toString());
                            BaseBinInputStream binInputStream = next.getBinInputStream(a2.icType);
                            if (binInputStream == null) {
                                ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, "not find image: " + byBitNumber.imageId);
                            } else {
                                if (f != null) {
                                    ImageVersionInfo activeImageVersionInfoByImageId = f.getActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                    if (activeImageVersionInfoByImageId != null) {
                                        it = it2;
                                        if (activeImageVersionInfoByImageId.getVersion() == -1) {
                                            i = b2;
                                            ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("invalid active version:0x%04X, no need to check", Integer.valueOf(activeImageVersionInfoByImageId.getVersion())));
                                            binInputStream.setActiveCompareVersionFlag(1);
                                            z = v;
                                            aVar = c;
                                            i2 = 1;
                                        } else {
                                            i = b2;
                                            z = v;
                                            aVar = c;
                                            int compareVersion = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, activeImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            i2 = 1;
                                            ZLogger.v(String.format(Locale.US, "compare active image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion)));
                                            binInputStream.setActiveCompareVersionFlag(compareVersion);
                                        }
                                    } else {
                                        it = it2;
                                        i = b2;
                                        z = v;
                                        aVar = c;
                                        i2 = 1;
                                        ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, String.format("not find active image, imageId=0x%04X", Integer.valueOf(binInputStream.getImageId())));
                                        binInputStream.setActiveCompareVersionFlag(1);
                                    }
                                    ImageVersionInfo inActiveImageVersionInfoByImageId = f.getInActiveImageVersionInfoByImageId(binInputStream.getImageId());
                                    if (inActiveImageVersionInfoByImageId != null) {
                                        if (inActiveImageVersionInfoByImageId.getVersion() == -1) {
                                            boolean z3 = com.realsil.sdk.dfu.k.a.f13612a;
                                            Object[] objArr = new Object[i2];
                                            objArr[0] = Integer.valueOf(inActiveImageVersionInfoByImageId.getVersion());
                                            ZLogger.v(z3, String.format("invalid inactive version:0x%04X, no need to check", objArr));
                                            binInputStream.setInactiveCompareVersionFlag(i2);
                                        } else {
                                            int compareVersion2 = com.realsil.sdk.dfu.k.a.compareVersion(a2.icType, binInputStream.getBinId(), binInputStream.imageVersion, binInputStream.otaVersion, f.protocolType, inActiveImageVersionInfoByImageId.getVersion(), f.specVersion, byBitNumber.versionFormat);
                                            ZLogger.v(String.format(Locale.US, "compare inactive image, bitNumber=%d, compare=%d ", Integer.valueOf(next.bitNumber), Integer.valueOf(compareVersion2)));
                                            binInputStream.setInactiveCompareVersionFlag(compareVersion2);
                                        }
                                    } else {
                                        boolean z4 = com.realsil.sdk.dfu.k.a.f13612a;
                                        Object[] objArr2 = new Object[i2];
                                        objArr2[0] = Integer.valueOf(binInputStream.getImageId());
                                        ZLogger.v(z4, String.format("not find inactive image, imageId=0x%04X", objArr2));
                                        binInputStream.setInactiveCompareVersionFlag(i2);
                                    }
                                    if (com.realsil.sdk.dfu.d.a.a(next.wrapperBitNumber())) {
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
                                    it = it2;
                                    i = b2;
                                    z = v;
                                    aVar = c;
                                }
                                arrayList2.add(binInputStream);
                                if (next.wrapperBitNumber() == 2) {
                                    ZLogger.v("ignore OTA_HEADER_FILE");
                                } else if (next.wrapperBitNumber() == 1) {
                                    ZLogger.v("ignore SYSTEM_CONFIG_FILE");
                                } else {
                                    arrayList3.add(binInputStream);
                                    arrayList.add(next);
                                }
                                it2 = it;
                                b2 = i;
                                v = z;
                                c = aVar;
                            }
                        }
                    }
                }
                boolean z5 = v;
                try {
                    c.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                    ZLogger.e(e3.toString());
                }
                if (z5) {
                    ZLogger.v(String.format(Locale.US, "nonConfigVersionFlag=%d, configVersionFlag=%d", Integer.valueOf(i3), Integer.valueOf(i4)));
                    if (i3 <= 0) {
                        if (i3 != 0) {
                            ZLogger.w("all code image version must >= active image version");
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
                }
            }
            z2 = false;
            a2.lowVersionExist = z2;
            a2.subBinInputStreams = arrayList2;
            a2.supportBinInputStreams = arrayList3;
            a2.supportSubFileInfos = arrayList;
            return a2;
        } catch (LoadFileException e4) {
            a2.updateEnabled = false;
            a2.status = e4.getErrCode();
            return a2;
        }
    }

    public static BinInfo loadImageBinInfo(LoadParams loadParams) throws LoadFileException {
        ArrayList arrayList;
        boolean z;
        int i;
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b2 = loadParams.b();
        OtaDeviceInfo f = loadParams.f();
        boolean v = loadParams.v();
        boolean q = loadParams.q();
        boolean s = loadParams.s();
        int k = loadParams.k();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        BinInfo a2 = com.realsil.sdk.dfu.k.a.a(d, e);
        a2.updateBank = loadParams.k();
        try {
            com.realsil.sdk.dfu.f.a c = com.realsil.sdk.dfu.e.b.c(loadParams);
            if (c != null) {
                a2.isPackFile = true;
                a2.icType = c.b();
                a2.subFileInfos = c.c(0);
                a2.subFileInfos1 = c.c(1);
                if (q && !com.realsil.sdk.dfu.k.a.a(a2.icType, h)) {
                    ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                    a2.updateEnabled = false;
                    a2.status = 4101;
                    return a2;
                }
                SubFileInfo b3 = c.b(com.realsil.sdk.dfu.k.a.wrapperBitNumber(2, c.a()));
                if (b3 != null) {
                    BaseBinInputStream binInputStream = b3.getBinInputStream(a2.icType);
                    if (s && binInputStream != null) {
                        if (1 != a(2, binInputStream, f)) {
                            ZLogger.w("ota header section size check failed: ");
                            a2.updateEnabled = false;
                            a2.status = 4109;
                            return a2;
                        }
                        ZLogger.d("preVerify OTA_HEADER_FILE ok, no need to check section size");
                        s = false;
                    }
                }
                z = false;
                for (SubFileInfo subFileInfo : c.c(k)) {
                    int wrapperBitNumber = subFileInfo.wrapperBitNumber();
                    if (BinIndicator.isIndicatorEnabled(b2, wrapperBitNumber)) {
                        BinIndicator byBitNumber = BinIndicator.getByBitNumber(BinIndicator.BBPRO, wrapperBitNumber);
                        if (byBitNumber != null) {
                            ZLogger.v(com.realsil.sdk.dfu.k.a.b, byBitNumber.toString());
                            BaseBinInputStream binInputStream2 = subFileInfo.getBinInputStream(a2.icType);
                            if (binInputStream2 == null) {
                                ZLogger.v(com.realsil.sdk.dfu.k.a.f13612a, "not find image: " + subFileInfo.imageId);
                            } else {
                                if (v) {
                                    i = 1;
                                    if (1 != a(binInputStream2, f, byBitNumber.versionFormat)) {
                                        z = true;
                                    }
                                } else {
                                    i = 1;
                                }
                                if (!s || i == a(wrapperBitNumber, binInputStream2, f)) {
                                    arrayList3.add(binInputStream2);
                                    arrayList4.add(binInputStream2);
                                    arrayList2.add(subFileInfo);
                                }
                            }
                        }
                    } else {
                        ZLogger.v("prohibit upgrade image_id=" + subFileInfo.imageId);
                    }
                }
                try {
                    c.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
                arrayList = arrayList2;
            } else {
                a2.icType = h;
                byte[] bArr = null;
                arrayList = arrayList2;
                try {
                    BaseBinInputStream openFileInputStream = com.realsil.sdk.dfu.k.a.openFileInputStream(h, d, 0L);
                    if (openFileInputStream != null) {
                        openFileInputStream.parseImageHeaderEx();
                        byte[] sha256 = openFileInputStream.getSha256();
                        try {
                            openFileInputStream.close();
                        } catch (Exception e3) {
                            ZLogger.w(e3.toString());
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
                            ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(a2.icType)));
                            a2.updateEnabled = false;
                            a2.status = 4101;
                            return a2;
                        } else if (v && 1 != com.realsil.sdk.dfu.k.a.checkSingleImageVersion(openFileInputStream2, f)) {
                            z = true;
                        } else if (!s) {
                            arrayList4.add(openFileInputStream2);
                        } else if (1 == a(openFileInputStream2, f)) {
                            arrayList4.add(openFileInputStream2);
                        } else {
                            a2.updateEnabled = false;
                            a2.status = 4109;
                            return a2;
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
            a2.supportBinInputStreams = arrayList4;
            a2.supportSubFileInfos = arrayList;
            if (v && z && arrayList4.size() < 1) {
                a2.updateEnabled = false;
                a2.status = 4104;
            }
            return a2;
        } catch (LoadFileException e5) {
            a2.updateEnabled = false;
            a2.status = e5.getErrCode();
            return a2;
        }
    }

    public static BinInfo a(LoadParams loadParams) throws LoadFileException {
        boolean z;
        Context a2 = loadParams.a();
        int h = loadParams.h();
        String d = loadParams.d();
        String e = loadParams.e();
        int b2 = loadParams.b();
        OtaDeviceInfo f = loadParams.f();
        boolean v = loadParams.v();
        boolean q = loadParams.q();
        if (a2 != null) {
            BinInfo b3 = com.realsil.sdk.dfu.k.a.b(d, e);
            b3.updateBank = loadParams.k();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Locale locale = Locale.US;
            ZLogger.v(String.format(locale, "device >> primaryIcType=0x%02X", Integer.valueOf(h)));
            ZLogger.v(String.format(locale, "fileIndicator=0x%08X, filePath=%s, versionCheckEnabled=%b", Integer.valueOf(b2), d, Boolean.valueOf(v)));
            com.realsil.sdk.dfu.f.a b4 = com.realsil.sdk.dfu.e.b.b(loadParams);
            if (b4 != null) {
                b3.isPackFile = true;
                b3.icType = b4.b();
                b3.subFileInfos = b4.c(0);
                b3.subFileInfos1 = b4.c(1);
                if (q && !com.realsil.sdk.dfu.k.a.a(b3.icType, h)) {
                    ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(b3.icType)));
                    b3.updateEnabled = false;
                    b3.status = 4101;
                    return b3;
                }
                z = false;
                for (int i = 0; i < 16; i++) {
                    int wrapperBitNumber = com.realsil.sdk.dfu.k.a.wrapperBitNumber(i, f.imageVersionIndicator, f.updateBankIndicator);
                    if (wrapperBitNumber < 16) {
                        b3.bankIndicator |= 1;
                    } else {
                        b3.bankIndicator |= 2;
                    }
                    if (BinIndicator.isIndicatorEnabled(b2, wrapperBitNumber)) {
                        SubFileInfo b5 = b4.b(wrapperBitNumber);
                        BaseBinInputStream assetsBinInputStream = b5 != null ? b5.getAssetsBinInputStream(a2, b3.icType) : null;
                        if (assetsBinInputStream != null) {
                            arrayList2.add(assetsBinInputStream);
                            if (v) {
                                if (1 == com.realsil.sdk.dfu.k.a.checkPackImageVersion(wrapperBitNumber, assetsBinInputStream, f)) {
                                    arrayList3.add(assetsBinInputStream);
                                    arrayList.add(b5);
                                } else {
                                    z = true;
                                }
                            } else {
                                arrayList3.add(assetsBinInputStream);
                                arrayList.add(b5);
                            }
                        }
                    } else {
                        ZLogger.v("image file disable: bitNumber=" + wrapperBitNumber);
                    }
                }
                try {
                    b4.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    ZLogger.e(e2.toString());
                }
            } else {
                try {
                    BaseBinInputStream openAssetsInputStream = com.realsil.sdk.dfu.k.a.openAssetsInputStream(a2, h, d, 0L);
                    if (openAssetsInputStream != null) {
                        arrayList2.add(openAssetsInputStream);
                        b3.icType = openAssetsInputStream.getIcType();
                        b3.version = openAssetsInputStream.getImageVersion();
                        if (q && !com.realsil.sdk.dfu.k.a.a(b3.icType, h)) {
                            ZLogger.w(String.format("ic conflict: 0x%02X, 0x%02X", Integer.valueOf(h), Integer.valueOf(b3.icType)));
                            b3.updateEnabled = false;
                            b3.status = 4101;
                            return b3;
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
            b3.lowVersionExist = z;
            b3.subBinInputStreams = arrayList2;
            b3.supportBinInputStreams = arrayList3;
            b3.supportSubFileInfos = arrayList;
            if (v && z && arrayList3.size() < 1) {
                b3.updateEnabled = false;
                b3.status = 4104;
            }
            return b3;
        }
        throw new LoadFileException("invalid context", 4097);
    }

    public static int a(int i, BaseBinInputStream baseBinInputStream, OtaDeviceInfo otaDeviceInfo) {
        if (otaDeviceInfo == null) {
            return 1;
        }
        int imageSize = baseBinInputStream.getImageSize();
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
                    ZLogger.v("section validate ok: " + imageSize);
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
                        ZLogger.v("section validate ok: " + imageSize);
                    }
                }
            }
        }
        return 1;
    }
}
