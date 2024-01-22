package com.realsil.sdk.dfu.k;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.FileUtils;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.exception.LoadFileException;
import com.realsil.sdk.dfu.h.d;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.image.wrapper.BinImageWrapper;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.utils.DfuUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
/* loaded from: classes12.dex */
public class a {
    public static final int COMPARE_VERSION_EQUAL = 0;
    public static final int COMPARE_VERSION_LOW = -1;
    public static final int ERR_EQUAL_VERSION = 0;
    public static final int ERR_IMAGE_NOT_DEFINED = 3;
    public static final int ERR_LOW_VERSION = -1;
    public static final int ERR_SECTION_SIZE_EXCEED_LIMIT = 2;
    public static final int ERR_SUCCESS = 1;
    public static final String FILE_SUFFIX = "BIN";

    /* renamed from: a  reason: collision with root package name */
    public static boolean f13612a = true;
    public static boolean b = RtkDfu.VDBG;

    public static BinInfo a(String str, String str2) throws LoadFileException {
        if (!TextUtils.isEmpty(str)) {
            String suffix = FileUtils.getSuffix(str);
            if (suffix != null && suffix.equalsIgnoreCase(str2)) {
                File file = new File(str);
                if (file.exists()) {
                    BinInfo binInfo = new BinInfo();
                    binInfo.path = file.getPath();
                    binInfo.fileName = file.getName();
                    binInfo.fileSize = file.length();
                    return binInfo;
                }
                throw new LoadFileException("image file not exist", 4100);
            }
            ZLogger.w("invalid suffix: " + suffix);
            throw new LoadFileException("invalid suffix", 4099);
        }
        throw new LoadFileException("invalid path: ", 4098);
    }

    public static boolean a(int i, int i2) {
        return i2 <= 3 ? i <= 3 : (i2 == 4 || i2 == 8 || i2 == 6) ? i == 4 || i == 8 || i == 6 : i2 == i;
    }

    public static BinInfo b(String str, String str2) throws LoadFileException {
        if (!TextUtils.isEmpty(str)) {
            String suffix = FileUtils.getSuffix(str);
            if (suffix != null && suffix.equalsIgnoreCase(str2)) {
                BinInfo binInfo = new BinInfo();
                binInfo.path = str;
                binInfo.fileName = DfuUtils.getAssetsFileName(str);
                return binInfo;
            }
            ZLogger.w("the file suffix is not right, suffix=" + suffix);
            throw new LoadFileException("invalid suffix", 4099);
        }
        throw new LoadFileException("invalid path", 4098);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x023f, code lost:
        if (r11 != 21) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d3, code lost:
        if (r11 != 21) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int checkPackImageVersion(int r11, com.realsil.sdk.dfu.image.stream.BaseBinInputStream r12, com.realsil.sdk.dfu.model.OtaDeviceInfo r13) {
        /*
            Method dump skipped, instructions count: 736
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.k.a.checkPackImageVersion(int, com.realsil.sdk.dfu.image.stream.BaseBinInputStream, com.realsil.sdk.dfu.model.OtaDeviceInfo):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
        if (r6.versionCheckEnabled != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006d, code lost:
        if (r6.versionCheckEnabled != false) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int checkSingleImageVersion(com.realsil.sdk.dfu.image.stream.BaseBinInputStream r16, com.realsil.sdk.dfu.model.OtaDeviceInfo r17) {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.k.a.checkSingleImageVersion(com.realsil.sdk.dfu.image.stream.BaseBinInputStream, com.realsil.sdk.dfu.model.OtaDeviceInfo):int");
    }

    public static int compareVersion(int i, int i2, int i3, int i4, int i5) {
        ZLogger.v(String.format(Locale.US, "checkVersion: bin=%08X(%d) %s, soc=%08X(%d) %s", Integer.valueOf(i), Integer.valueOf(i), DfuUtils.convertVersion2Str(i2, i, i5), Integer.valueOf(i3), Integer.valueOf(i3), DfuUtils.convertVersion2Str(i4, i3, i5)));
        if (i2 <= 0) {
            return com.realsil.sdk.dfu.d.b.a(i, com.realsil.sdk.dfu.c.c.a(i3, i4, i5));
        }
        if (i4 <= 0) {
            return com.realsil.sdk.dfu.d.b.a(com.realsil.sdk.dfu.c.c.a(i, i2, i5), i3);
        }
        if (i5 == 1) {
            return new com.realsil.sdk.dfu.c.b(i & 255, (i >> 8) & 255, (i >> 16) & 255, (i >> 24) & 255).a(new com.realsil.sdk.dfu.c.b(i3 & 255, (i3 >> 8) & 255, (i3 >> 16) & 255, (i3 >> 24) & 255));
        }
        if (i5 == 515) {
            return new com.realsil.sdk.dfu.c.b((i >> 24) & 255, (i >> 16) & 255, (i >> 8) & 255, i & 255).a(new com.realsil.sdk.dfu.c.b((i3 >> 24) & 255, (i3 >> 16) & 255, (i3 >> 8) & 255, i3 & 255));
        }
        if (i5 == 2) {
            return new com.realsil.sdk.dfu.c.b((i >> 24) & 255, (i >> 16) & 255, (i >> 8) & 255, i & 255).a(new com.realsil.sdk.dfu.c.b((i3 >> 24) & 255, (i3 >> 16) & 255, (i3 >> 8) & 255, i3 & 255));
        }
        if (i5 == 3) {
            return new com.realsil.sdk.dfu.c.b(i & 15, (i >> 4) & 255, (i >> 12) & 32767, (i >> 27) & 31).a(new com.realsil.sdk.dfu.c.b(i3 & 15, (i3 >> 4) & 255, (i3 >> 12) & 32767, (i3 >> 27) & 31));
        }
        if (i5 == 5) {
            return new com.realsil.sdk.dfu.c.b(i & 15, (i >> 4) & 255, (i >> 12) & 511, (i >> 21) & 32767).a(new com.realsil.sdk.dfu.c.b(i3 & 15, (i3 >> 4) & 255, (i3 >> 12) & 511, (i3 >> 21) & 32767));
        }
        if (i5 == 4) {
            return com.realsil.sdk.dfu.d.b.a(i, i3);
        }
        if (i5 == 7) {
            return com.realsil.sdk.dfu.d.b.a(i, i3);
        }
        if (i5 == 514) {
            return new com.realsil.sdk.dfu.c.b((i >> 8) & 255, i & 255, (i >> 24) & 255, (i >> 16) & 255).a(new com.realsil.sdk.dfu.c.b((i3 >> 8) & 255, i3 & 255, (i3 >> 24) & 255, (i3 >> 16) & 255));
        }
        return com.realsil.sdk.dfu.d.b.a(i, i3);
    }

    public static int compareVersion2(int i, int i2, int i3, int i4, int i5) {
        ZLogger.v(String.format(Locale.US, "checkVersion: version1=%08X(%d) %s, version2=%08X(%d) %s", Integer.valueOf(i), Integer.valueOf(i), DfuUtils.convertVersion2Str(i2, i, i5), Integer.valueOf(i3), Integer.valueOf(i3), DfuUtils.convertVersion2Str(i4, i3, i5)));
        return new BinImageWrapper.Builder().setOtaVersion(i2).imageVersion(i, 3, i5).build().compare(new BinImageWrapper.Builder().setOtaVersion(i4).imageVersion(i3, 3, i5).build());
    }

    public static com.realsil.sdk.dfu.c.b getBinImageVersion(int i, int i2, int i3, int i4) {
        ZLogger.v(String.format(Locale.US, "checkVersion: bin=%08X(%d) %s", Integer.valueOf(i2), Integer.valueOf(i2), DfuUtils.convertVersion2Str(i3, i2, i4)));
        if (i3 == 2 && i == 1040) {
            i4 = 516;
        }
        if (i3 <= 0) {
            i4 = 7;
        }
        return com.realsil.sdk.dfu.c.b.a(i4, i2);
    }

    public static com.realsil.sdk.dfu.c.b getSocImageVersion(int i, int i2, int i3, int i4, int i5, int i6) {
        ZLogger.v(String.format(Locale.US, "checkVersion: soc=%08X(%d) %s", Integer.valueOf(i5), Integer.valueOf(i5), DfuUtils.convertVersion2Str(i3, i5, i6)));
        if (i == 11 || i == 13 || i == 10) {
            if (i4 == 1040) {
                i6 = 516;
            }
        } else if (i2 == 20) {
            i6 = 514;
        } else if (i3 <= 0) {
            i6 = 7;
        }
        return com.realsil.sdk.dfu.c.b.a(i6, i5);
    }

    public static BaseBinInputStream openAssetsInputStream(Context context, int i, String str, long j) throws IOException {
        ZLogger.v(b, String.format(Locale.US, "fileName=%s, icType=%d", str, Integer.valueOf(i)));
        AssetManager assets = context.getAssets();
        if (assets != null) {
            InputStream open = assets.open(str);
            if (open == null) {
                ZLogger.d("open asset file failed");
                return null;
            }
            return a(i, open, j);
        }
        ZLogger.w("assetManager is null");
        return null;
    }

    public static BaseBinInputStream openFileInputStream(int i, String str, long j) throws IOException {
        return a(i, new FileInputStream(str), j);
    }

    public static int wrapperBitNumber(int i, int i2) {
        return i2 == 0 ? i : i % i2;
    }

    public static int wrapperBitNumber(int i, int i2, int i3) {
        int i4 = (i2 >> (i * 2)) & 3;
        if (i4 == 0) {
            if (i3 != 2) {
                return i;
            }
        } else if (i4 != 1) {
            return i;
        }
        return i + 16;
    }

    public static int wrapperBitNumber2(int i, int i2, int i3) {
        return i3 == 0 ? i : i % i3;
    }

    public static BaseBinInputStream a(int i, InputStream inputStream, long j) throws IOException {
        if (b) {
            ZLogger.v(String.format(Locale.US, "icType=0x%02X, skipOffset=%d", Integer.valueOf(i), Long.valueOf(j)));
        }
        inputStream.skip(j);
        if (i <= 3) {
            return new com.realsil.sdk.dfu.h.c(inputStream);
        }
        if (i == 4 || i == 6 || i == 7 || i == 8) {
            return new com.realsil.sdk.dfu.h.b(inputStream);
        }
        if (i == 11 || i == 10 || i == 13) {
            return new com.realsil.sdk.dfu.h.a(inputStream);
        }
        if (i == 5 || i == 9 || i == 12) {
            return new d(inputStream);
        }
        ZLogger.v(String.format("not support icType=0x%02X", Integer.valueOf(i)));
        return null;
    }

    public static int compareVersion(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return getBinImageVersion(i2, i3, i4, i8).a(getSocImageVersion(i, i5, i7, i2, i6, i8));
    }
}
