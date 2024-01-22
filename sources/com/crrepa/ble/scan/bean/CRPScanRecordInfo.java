package com.crrepa.ble.scan.bean;
/* loaded from: classes9.dex */
public class CRPScanRecordInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f7682a;
    public McuPlatform b;
    public int c;
    public BandFunction d;

    /* loaded from: classes9.dex */
    public enum BandFunction {
        FUNC_NORMAL(0),
        FUNC_TALK(32768),
        FUNC_GPS(16384),
        FUNC_MUSIC(8192),
        FUNC_LYRIC(4096);
        
        private int value;

        BandFunction(int i) {
            this.value = i;
        }

        public static BandFunction getInstance(int i) {
            return i != 4096 ? i != 8192 ? i != 16384 ? i != 32768 ? FUNC_NORMAL : FUNC_TALK : FUNC_GPS : FUNC_MUSIC : FUNC_LYRIC;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes9.dex */
    public enum GoodixChip {
        GR_NONE(0),
        GR_5515(1);
        
        private int value;

        GoodixChip(int i) {
            this.value = i;
        }

        public static GoodixChip getInstance(int i) {
            return i != 1 ? GR_NONE : GR_5515;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes9.dex */
    public enum HuntersunChip {
        HS_NONE(0),
        HS_6620D_A3(1),
        HS_6620D_A4(2),
        HS_6621(3);
        
        private int value;

        HuntersunChip(int i) {
            this.value = i;
        }

        public static HuntersunChip getInstance(int i) {
            return i != 1 ? i != 2 ? i != 3 ? HS_NONE : HS_6621 : HS_6620D_A4 : HS_6620D_A3;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes9.dex */
    public enum McuPlatform {
        PLATFORM_NONE(0),
        PLATFORM_NORDIC(1),
        PLATFORM_HUNTERSUN(2),
        PLATFORM_REALTEK(3),
        PLATFORM_GOODIX(4);
        
        private int value;

        McuPlatform(int i) {
            this.value = i;
        }

        public static McuPlatform getInstance(int i) {
            return i != 1 ? i != 2 ? i != 3 ? i != 4 ? PLATFORM_NONE : PLATFORM_GOODIX : PLATFORM_REALTEK : PLATFORM_HUNTERSUN : PLATFORM_NORDIC;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes9.dex */
    public enum NordicChip {
        NRF_NONE(0),
        NRF_51822(1),
        NRF_52832(2),
        NRF_52810(3),
        NRF_52840(4);
        
        private int value;

        NordicChip(int i) {
            this.value = i;
        }

        public static NordicChip getInstance(int i) {
            return i != 1 ? i != 2 ? i != 3 ? i != 4 ? NRF_NONE : NRF_52840 : NRF_52810 : NRF_52832 : NRF_51822;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes9.dex */
    public enum RealtekChip {
        RTL_NONE(0),
        RTL_8762C(1),
        RTL_8762D(2);
        
        private int value;

        RealtekChip(int i) {
            this.value = i;
        }

        public static RealtekChip getInstance(int i) {
            return i != 1 ? i != 2 ? RTL_NONE : RTL_8762D : RTL_8762C;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getChipId() {
        return this.c;
    }

    public String getFirmwareType() {
        return this.f7682a;
    }

    public BandFunction getFunction() {
        return this.d;
    }

    public McuPlatform getPlatform() {
        return this.b;
    }

    public void setChipId(int i) {
        this.c = i;
    }

    public void setFirmwareType(String str) {
        this.f7682a = str;
    }

    public void setFunction(BandFunction bandFunction) {
        this.d = bandFunction;
    }

    public void setPlatform(McuPlatform mcuPlatform) {
        this.b = mcuPlatform;
    }
}
