package com.realsil.sdk.dfu.image;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.image.constants.ImageId;
import com.realsil.sdk.dfu.image.constants.SubBinId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes12.dex */
public class BinIndicator {
    public static final ArrayList<BinIndicator> BB2;
    public static final ArrayList<BinIndicator> BBPRO;
    public static final ArrayList<BinIndicator> BEE1;
    public static final ArrayList<BinIndicator> BEE2;
    public static final int INDICATOR_FULL = -1;
    public static final int INDICATOR_MASK = 1;
    public static boolean VDBG = false;
    public int bitNumber;
    public String description;
    public String flashLayoutName;
    public int imageId;
    public boolean isConfigEnabled;
    public int subBinId;
    public boolean versionCheckEnabled;
    public int versionFormat;

    static {
        ArrayList<BinIndicator> arrayList = new ArrayList<>();
        BEE1 = arrayList;
        arrayList.add(new BinIndicator(0, "Patch image (Both MP and OTA)", null, -1, true, 7));
        arrayList.add(new BinIndicator(1, "App bank 0 image (Both MP and OTA)", null, -1, true, 7));
        arrayList.add(new BinIndicator(2, "APP bank 1 image (OTA)", null, -1, true, 7));
        arrayList.add(new BinIndicator(3, "User data (MP)", null, -1, true, 7));
        arrayList.add(new BinIndicator(4, "Patch extension image (Both MP and OTA)", null, -1, true, 7));
        arrayList.add(new BinIndicator(5, "Config file (MP)", null, -1, true, 7));
        arrayList.add(new BinIndicator(6, "External Flash image (MP)", null, -1, true, 7));
        ArrayList<BinIndicator> arrayList2 = new ArrayList<>();
        BEE2 = arrayList2;
        arrayList2.add(new BinIndicator(0, "SOCV Config File", null, 257, true, 3));
        arrayList2.add(new BinIndicator(1, "System Config File", null, 256, true, 3));
        for (int i = 0; i < 2; i++) {
            ArrayList<BinIndicator> arrayList3 = BEE2;
            int i2 = i * 16;
            arrayList3.add(new BinIndicator(i2 + 2, "OTA Header File", null, 2048, true, 2));
            arrayList3.add(new BinIndicator(i2 + 3, "Secure Boot Loader image", null, 1792, true, 3));
            arrayList3.add(new BinIndicator(i2 + 4, "ROM Patch Image", null, 512, true, 3));
            arrayList3.add(new BinIndicator(i2 + 5, "App Image", null, 768, true, 3));
            arrayList3.add(new BinIndicator(i2 + 6, "APP Data1 File", null, 2305, false, 3));
            arrayList3.add(new BinIndicator(i2 + 7, "APP Data2 File", null, 2306, false, 3));
            arrayList3.add(new BinIndicator(i2 + 8, "APP Data3 File", null, 2307, false, 3));
            arrayList3.add(new BinIndicator(i2 + 9, "APP Data4 File", null, SubBinId.Bee2.APP_DATA4_FILE, false, 3));
            arrayList3.add(new BinIndicator(i2 + 10, "APP Data5 File", null, SubBinId.Bee2.APP_DATA5_FILE, false, 3));
            arrayList3.add(new BinIndicator(i2 + 11, "APP Data6 File", null, SubBinId.Bee2.APP_DATA6_FILE, false, 3));
            arrayList3.add(new BinIndicator(i2 + 12, "Upper Stack", null, 2560, true, 3));
        }
        ArrayList<BinIndicator> arrayList4 = new ArrayList<>();
        BBPRO = arrayList4;
        arrayList4.add(new BinIndicator(0, "SOCV Config File", null, 257, false, 1));
        arrayList4.add(new BinIndicator(1, "System Config File", null, 256, true, 1));
        for (int i3 = 0; i3 < 2; i3++) {
            ArrayList<BinIndicator> arrayList5 = BBPRO;
            int i4 = i3 * 16;
            arrayList5.add(new BinIndicator(i4 + 2, "OTA Header File", null, 10128, 2048, true, 1, false));
            arrayList5.add(new BinIndicator(i4 + 3, "Secure Boot Loader image", null, 10129, 1792, true, 3, false));
            arrayList5.add(new BinIndicator(i4 + 4, "ROM Patch Image", null, 10130, 512, true, 3, false));
            arrayList5.add(new BinIndicator(i4 + 5, "App Image", null, 10131, 768, true, 5, false));
            arrayList5.add(new BinIndicator(i4 + 6, "DSP System Image", null, 10132, 1280, true, 515, false));
            arrayList5.add(new BinIndicator(i4 + 7, "DSP APP Image", null, 10133, SubBinId.Bbpro.DSP_APP_IMAGE, true, 515, false));
            arrayList5.add(new BinIndicator(i4 + 8, "DSP Config", null, 10135, SubBinId.Bbpro.DSP_UI_PARAMETER_FILE, true, 514, true));
            arrayList5.add(new BinIndicator(i4 + 9, "APP UI Parameter File", null, 10134, 1024, true, 2, true));
            arrayList5.add(new BinIndicator(i4 + 10, "Ext Image 0", null, 10136, SubBinId.Bbpro.EXT_IMAGE_0, false, 1, true));
            arrayList5.add(new BinIndicator(i4 + 11, "Ext Image 1", null, 10137, 2305, false, 1, false));
            arrayList5.add(new BinIndicator(i4 + 12, "Ext Image 2", null, 10138, 2306, false, 1, false));
            arrayList5.add(new BinIndicator(i4 + 13, "Ext Image 3", null, 10139, 2307, false, 1, false));
            arrayList5.add(new BinIndicator(i4 + 17, "Platform", null, 10140, 513, false, 3, false));
            arrayList5.add(new BinIndicator(i4 + 18, "Lower Stack", null, 10141, 514, false, 3, false));
            arrayList5.add(new BinIndicator(i4 + 19, "Upper Stack", null, 10142, 515, false, 1, false));
            arrayList5.add(new BinIndicator(i4 + 20, "Framework", null, 10143, 516, false, 1, false));
        }
        ArrayList<BinIndicator> arrayList6 = BBPRO;
        arrayList6.add(new BinIndicator(14, "Factory Image", null, 2560, false, 1));
        arrayList6.add(new BinIndicator(15, "Backup Data 1", null, SubBinId.Bbpro.BACKUP_DATA_1, false, 1));
        arrayList6.add(new BinIndicator(16, "Backup Data 2", null, SubBinId.Bbpro.BACKUP_DATA_2, false, 1));
        arrayList6.add(new BinIndicator(24, "Voice Prompt Data Image", null, ImageId.BB2.VP_DATA, 520, false, 2, false));
        arrayList6.add(new BinIndicator(24, "Pre-Sys Patch Image", null, ImageId.BB2.PRE_SYS_PATCH, 517, false, 1, false));
        ArrayList<BinIndicator> arrayList7 = new ArrayList<>();
        BB2 = arrayList7;
        arrayList7.add(new BinIndicator(0, "SOCV Config File", null, 257, false, 1));
        arrayList7.add(new BinIndicator(1, "System Config File", null, 256, true, 1));
        arrayList7.add(new BinIndicator(2, "OTA Header File", null, 10128, 2048, true, 1, false));
        arrayList7.add(new BinIndicator(3, "Secure Boot Loader image", null, 10129, 1792, true, 3, false));
        arrayList7.add(new BinIndicator(4, "ROM Patch Image", null, 10130, 512, true, 3, false));
        arrayList7.add(new BinIndicator(5, "App Image", null, 10131, 768, true, 5, false));
        arrayList7.add(new BinIndicator(6, "DSP System Image", null, 10132, 1280, true, 515, false));
        arrayList7.add(new BinIndicator(7, "DSP APP Image", null, 10133, SubBinId.Bbpro.DSP_APP_IMAGE, true, 515, false));
        arrayList7.add(new BinIndicator(8, "DSP Config", null, 10135, SubBinId.Bbpro.DSP_UI_PARAMETER_FILE, true, 514, true));
        arrayList7.add(new BinIndicator(9, "APP UI Parameter File", null, 10134, 1024, true, 2, true));
        arrayList7.add(new BinIndicator(10, "Ext Image 0", null, 10136, SubBinId.Bbpro.EXT_IMAGE_0, false, 1, true));
        arrayList7.add(new BinIndicator(11, "Ext Image 1", null, 10137, 2305, false, 1, false));
        arrayList7.add(new BinIndicator(12, "Ext Image 2", null, 10138, 2306, false, 1, false));
        arrayList7.add(new BinIndicator(13, "Ext Image 3", null, 10139, 2307, false, 1, false));
        arrayList7.add(new BinIndicator(17, "Platform", null, 10140, 513, false, 3, false));
        arrayList7.add(new BinIndicator(18, "Lower Stack", null, 10141, 514, false, 3, false));
        arrayList7.add(new BinIndicator(19, "Upper Stack", null, 10142, 515, false, 1, false));
        arrayList7.add(new BinIndicator(20, "Framework", null, 10143, 516, false, 1, false));
        arrayList7.add(new BinIndicator(14, "Factory Image", null, 2560, false, 1));
        arrayList7.add(new BinIndicator(15, "Backup Data 1", null, SubBinId.Bbpro.BACKUP_DATA_1, false, 1));
        arrayList7.add(new BinIndicator(16, "Backup Data 2", null, SubBinId.Bbpro.BACKUP_DATA_2, false, 1));
        arrayList7.add(new BinIndicator(24, "Pre-Sys Patch Image", null, ImageId.BB2.PRE_SYS_PATCH, 517, false, 3, false));
        arrayList7.add(new BinIndicator(24, "Pre Low Stack Image", null, 10141, 518, false, 1, false));
        arrayList7.add(new BinIndicator(24, "Pre Upper Stack Image", null, 10142, 519, false, 3, false));
        arrayList7.add(new BinIndicator(24, "Voice Prompt Data Image", null, ImageId.BB2.VP_DATA, 520, false, 2, false));
        arrayList7.add(new BinIndicator(24, "User Data 1 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_1, false, 1, false));
        arrayList7.add(new BinIndicator(24, "User Data 2 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_2, false, 1, false));
        arrayList7.add(new BinIndicator(24, "User Data 3 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_3, false, 1, false));
        arrayList7.add(new BinIndicator(24, "User Data 4 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_4, false, 1, false));
        arrayList7.add(new BinIndicator(24, "User Data 5 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_5, false, 1, false));
        arrayList7.add(new BinIndicator(24, "User Data 6 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_6, false, 1, false));
        arrayList7.add(new BinIndicator(24, "User Data 7 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_7, false, 1, false));
        arrayList7.add(new BinIndicator(24, "User Data 8 Image", null, ImageId.BB2.PRE_SYS_PATCH, SubBinId.BB2.USER_DATA_8, false, 1, false));
    }

    public BinIndicator(int i, String str, String str2, int i2, int i3, boolean z, int i4, boolean z2) {
        this.versionCheckEnabled = true;
        this.versionFormat = 1;
        this.bitNumber = i;
        this.flashLayoutName = str;
        this.description = str2;
        this.imageId = i2;
        this.subBinId = i3;
        this.versionCheckEnabled = z;
        this.versionFormat = i4;
        this.isConfigEnabled = z2;
    }

    public static BinIndicator getByBitNumber(ArrayList<BinIndicator> arrayList, int i) {
        Iterator<BinIndicator> it = arrayList.iterator();
        while (it.hasNext()) {
            BinIndicator next = it.next();
            if (next.bitNumber == i) {
                return next;
            }
        }
        ZLogger.v("undefined indicator, bitNumber=" + i);
        return null;
    }

    public static boolean isIndicatorEnabled(int i, int i2) {
        return i == -1 || ((i >> i2) & 1) != 0;
    }

    public static String parseBitNumber(int i, int i2) {
        if (i <= 3) {
            Iterator<BinIndicator> it = BEE1.iterator();
            while (it.hasNext()) {
                BinIndicator next = it.next();
                if (next.bitNumber == i2) {
                    return next.flashLayoutName;
                }
            }
            return "NA";
        } else if (i == 5 || i == 9 || i == 12) {
            Iterator<BinIndicator> it2 = BEE2.iterator();
            while (it2.hasNext()) {
                BinIndicator next2 = it2.next();
                if (next2.bitNumber == i2) {
                    return next2.flashLayoutName;
                }
            }
            return "NA";
        } else if (i == 4 || i == 6 || i == 7 || i == 8 || i == 10 || i == 11) {
            Iterator<BinIndicator> it3 = BBPRO.iterator();
            while (it3.hasNext()) {
                BinIndicator next3 = it3.next();
                if (next3.bitNumber == i2) {
                    return next3.flashLayoutName;
                }
            }
            return "NA";
        } else {
            return "NA";
        }
    }

    public static String parseImageId(int i, int i2) {
        if (i <= 3) {
            Iterator<BinIndicator> it = BEE1.iterator();
            while (it.hasNext()) {
                BinIndicator next = it.next();
                if (next.imageId == i2) {
                    return next.flashLayoutName;
                }
            }
            return "NA";
        } else if (i == 5 || i == 9 || i == 12) {
            Iterator<BinIndicator> it2 = BEE2.iterator();
            while (it2.hasNext()) {
                BinIndicator next2 = it2.next();
                if (next2.imageId == i2) {
                    return next2.flashLayoutName;
                }
            }
            return "NA";
        } else if (i == 4 || i == 6 || i == 7 || i == 8 || i == 10 || i == 11) {
            Iterator<BinIndicator> it3 = BBPRO.iterator();
            while (it3.hasNext()) {
                BinIndicator next3 = it3.next();
                if (VDBG) {
                    ZLogger.v(String.format("%04x, %04X", Integer.valueOf(next3.imageId), Integer.valueOf(i2)));
                    ZLogger.v(next3.toString());
                }
                if (next3.imageId == i2) {
                    return next3.flashLayoutName;
                }
            }
            return "NA";
        } else {
            return "NA";
        }
    }

    public static String parseSubBinId(int i, int i2) {
        if (i <= 3) {
            Iterator<BinIndicator> it = BEE1.iterator();
            while (it.hasNext()) {
                BinIndicator next = it.next();
                if (next.subBinId == i2) {
                    return next.flashLayoutName;
                }
            }
            return "NA";
        } else if (i == 5 || i == 9 || i == 12) {
            Iterator<BinIndicator> it2 = BEE2.iterator();
            while (it2.hasNext()) {
                BinIndicator next2 = it2.next();
                if (next2.subBinId == i2) {
                    return next2.flashLayoutName;
                }
            }
            return "NA";
        } else if (i == 4 || i == 6 || i == 7 || i == 8 || i == 13 || i == 10) {
            Iterator<BinIndicator> it3 = BBPRO.iterator();
            while (it3.hasNext()) {
                BinIndicator next3 = it3.next();
                if (next3.subBinId == i2) {
                    return next3.flashLayoutName;
                }
            }
            return "NA";
        } else if (i == 11) {
            Iterator<BinIndicator> it4 = BB2.iterator();
            while (it4.hasNext()) {
                BinIndicator next4 = it4.next();
                if (next4.subBinId == i2) {
                    return next4.flashLayoutName;
                }
            }
            return "NA";
        } else {
            return "NA";
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Locale locale = Locale.US;
        sb.append(String.format(locale, "bitNumber=%d, flashLayoutName=%s, imageId=0x%04X, subBinId=0x%04X", Integer.valueOf(this.bitNumber), this.flashLayoutName, Integer.valueOf(this.imageId), Integer.valueOf(this.subBinId)));
        sb.append(String.format(locale, ", versionCheckEnabled=%b, versionFormat=%d, isConfigEnabled=%b", Boolean.valueOf(this.versionCheckEnabled), Integer.valueOf(this.versionFormat), Boolean.valueOf(this.isConfigEnabled)));
        return sb.toString();
    }

    public static BinIndicator getByBitNumber(ArrayList<BinIndicator> arrayList, int i, boolean z) {
        Iterator<BinIndicator> it = arrayList.iterator();
        while (it.hasNext()) {
            BinIndicator next = it.next();
            if (next.bitNumber == i && next.versionCheckEnabled == z) {
                return next;
            }
        }
        ZLogger.v("undefined indicator, bitNumber=" + i);
        return null;
    }

    public BinIndicator(int i, String str, String str2, int i2, boolean z, int i3) {
        this(i, str, str2, 0, i2, z, i3, false);
    }
}
