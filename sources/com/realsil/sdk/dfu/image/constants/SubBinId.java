package com.realsil.sdk.dfu.image.constants;

import com.realsil.sdk.dfu.image.BinIndicator;
/* loaded from: classes12.dex */
public final class SubBinId {
    public static final int UNDEFINED = -1;

    /* loaded from: classes12.dex */
    public static final class BB2 implements Bbpro {
        public static final int PRE_LOW_STACK = 518;
        public static final int PRE_SYSTEM = 517;
        public static final int PRE_UPPER_STACK = 519;
        public static final int USER_DATA_1 = 61441;
        public static final int USER_DATA_2 = 61442;
        public static final int USER_DATA_3 = 61443;
        public static final int USER_DATA_4 = 61444;
        public static final int USER_DATA_5 = 61445;
        public static final int USER_DATA_6 = 61446;
        public static final int USER_DATA_7 = 61447;
        public static final int USER_DATA_8 = 61448;
        public static final int VP_DATA = 520;
    }

    /* loaded from: classes12.dex */
    public interface Bbpro {
        public static final int APP_DATA_PROMPT = 1026;
        public static final int APP_DATA_TONE = 1025;
        public static final int APP_IMAGE = 768;
        public static final int APP_UI_PARAMETER_FILE = 1024;
        public static final int BACKUP_DATA_1 = 2816;
        public static final int BACKUP_DATA_2 = 2817;
        public static final int DSP_APP_IMAGE = 1538;
        public static final int DSP_PATCH = 1537;
        public static final int DSP_SCENARIO2 = 1539;
        public static final int DSP_SYSTEM_IMAGE = 1280;
        public static final int DSP_UI_PARAMETER_FILE = 1040;
        public static final int EXT_IMAGE_0 = 2304;
        public static final int EXT_IMAGE_1 = 2305;
        public static final int EXT_IMAGE_2 = 2306;
        public static final int EXT_IMAGE_3 = 2307;
        public static final int FACTORY_IAMGE = 2560;
        public static final int FRAMEWORK = 516;
        public static final int LOWER_STACK = 514;
        public static final int OTA_HEADER_FILE = 2048;
        public static final int PLATFORM = 513;
        public static final int ROM_PATCH_IMAGE = 512;
        public static final int SECURE_BOOT_LOADER_IMAGE = 1792;
        public static final int SOCV_CONFIG_FILE = 257;
        public static final int SYSTEM_CONFIG_FILE = 256;
        public static final int UPPER_STACK = 515;
    }

    /* loaded from: classes12.dex */
    public interface Bee1 {
    }

    /* loaded from: classes12.dex */
    public interface Bee2 {
        public static final int APP_DATA1_FILE = 2305;
        public static final int APP_DATA2_FILE = 2306;
        public static final int APP_DATA3_FILE = 2307;
        public static final int APP_DATA4_FILE = 2308;
        public static final int APP_DATA5_FILE = 2309;
        public static final int APP_DATA6_FILE = 2310;
        public static final int APP_IMAGE = 768;
        public static final int OTA_HEADER_FILE = 2048;
        public static final int ROM_PATCH_IMAGE = 512;
        public static final int SECURE_BOOT_LOADER_IMAGE = 1792;
        public static final int SOCV_CONFIG_FILE = 257;
        public static final int SYSTEM_CONFIG_FILE = 256;
        public static final int UPPER_STACK = 2560;
    }

    public static String nameOf(int i, int i2) {
        return BinIndicator.parseSubBinId(i, i2);
    }
}
