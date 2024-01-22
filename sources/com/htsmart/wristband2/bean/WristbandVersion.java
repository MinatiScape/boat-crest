package com.htsmart.wristband2.bean;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.Utils;
import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes11.dex */
public class WristbandVersion implements BytesEnabled {
    public static final int PACKET_LENGTH = 38;
    public static final int PACKET_LENGTH_STR = 76;
    public final boolean A;
    public final boolean A0;
    public final boolean B;
    public String B0;
    public final boolean C;
    public byte[] C0;
    public final boolean D;
    public final boolean E;
    public final boolean F;
    public boolean G;
    public boolean H;
    public final boolean I;
    public final boolean J;
    public final boolean K;
    public final boolean L;
    public final boolean M;
    public final boolean N;
    public final boolean O;
    public final boolean P;
    public final boolean Q;
    public final boolean R;
    public final boolean S;
    public final boolean T;
    public final boolean U;
    public final boolean V;
    public final boolean W;
    public final boolean X;
    public final boolean Y;
    public final boolean Z;

    /* renamed from: a  reason: collision with root package name */
    public final String f11971a;
    public final boolean a0;
    public final String b;
    public final boolean b0;
    public final int c;
    public final boolean c0;
    public final String d;
    public final boolean d0;
    public final String e;
    public final boolean e0;
    public final String f;
    public final boolean f0;
    public final String g;
    public final boolean g0;
    public final String h;
    public final boolean h0;
    public final boolean i;
    public final boolean i0;
    public final boolean j;
    public final boolean j0;
    public final boolean k;
    public final boolean k0;
    public final boolean l;
    public final boolean l0;
    public final boolean m;
    public final boolean m0;
    public final boolean n;
    public final boolean n0;
    public final boolean o;
    public final boolean o0;
    public final boolean p;
    public final boolean p0;
    public final boolean q;
    public final boolean q0;
    public final boolean r;
    public final boolean r0;
    public final boolean s;
    public final boolean s0;
    public final boolean t;
    public final boolean t0;
    public final boolean u;
    public final boolean u0;
    public final boolean v;
    public final boolean v0;
    public final boolean w;
    public final boolean w0;
    public final boolean x;
    public final boolean x0;
    public final boolean y;
    public final boolean y0;
    public final boolean z;
    public final boolean z0;

    public WristbandVersion(byte[] bArr) {
        this.C0 = bArr;
        boolean z = false;
        this.B0 = a(bArr, 0, bArr.length);
        this.f11971a = a(bArr, 0, 6);
        this.b = a(bArr, 6, 4);
        byte b = bArr[9];
        this.i = (b & 1) > 0;
        this.j = (b & 2) > 0;
        this.k = (b & 4) > 0;
        this.l = (b & 8) > 0;
        this.m = (b & 16) > 0;
        this.n = (b & 32) > 0;
        this.o = (b & 64) > 0;
        this.p = (b & 128) > 0;
        byte b2 = bArr[8];
        this.q = (b2 & 1) > 0;
        this.r = (b2 & 2) > 0;
        this.s = (b2 & 4) > 0;
        this.t = (b2 & 8) > 0;
        this.u = (b2 & 32) > 0;
        this.v = (b2 & 64) > 0;
        this.w = (b2 & 128) > 0;
        byte b3 = bArr[7];
        this.x = (b3 & 1) > 0;
        this.y = (b3 & 2) > 0;
        this.z = (b3 & 4) <= 0;
        this.A = (b3 & 8) > 0;
        this.B = (b3 & 16) > 0;
        this.C = (b3 & 32) > 0;
        this.D = (b3 & 64) > 0;
        this.E = (bArr[6] & 1) > 0;
        this.c = BytesUtil.bytes2Int(bArr, 10, 4, true);
        this.d = a(bArr, 14, 6);
        this.e = a(bArr, 20, 4);
        this.f = a(bArr, 24, 4);
        this.g = a(bArr, 28, 4);
        this.h = a(bArr, 32, 6);
        byte b4 = bArr[37];
        this.F = (b4 & 1) > 0;
        this.G = (b4 & 2) > 0;
        this.H = (b4 & 4) > 0;
        this.I = (b4 & 8) > 0;
        this.J = (b4 & 16) > 0;
        this.K = (b4 & 32) > 0;
        boolean z2 = (b4 & 64) > 0;
        this.L = z2;
        this.M = (b4 & 128) > 0;
        byte b5 = bArr[36];
        this.N = (b5 & 1) > 0;
        this.O = (b5 & 2) > 0;
        this.P = (b5 & 4) > 0;
        this.Q = (b5 & 8) > 0;
        this.R = (b5 & 16) > 0;
        this.S = (b5 & 32) > 0;
        this.T = (b5 & 64) > 0;
        this.U = (b5 & 128) > 0;
        byte b6 = bArr[35];
        this.V = (b6 & 1) > 0;
        this.W = (b6 & 2) > 0;
        this.X = (b6 & 4) > 0;
        this.Y = (b6 & 8) > 0;
        this.Z = (b6 & 16) > 0;
        this.a0 = (b6 & 32) > 0;
        this.b0 = (b6 & 64) > 0;
        this.c0 = (b6 & 128) > 0;
        byte b7 = bArr[34];
        this.d0 = (b7 & 1) > 0;
        this.e0 = (b7 & 2) > 0;
        this.f0 = (b7 & 4) > 0;
        this.g0 = (b7 & 8) > 0;
        this.h0 = (b7 & 16) > 0;
        this.i0 = (b7 & 32) > 0;
        this.j0 = (b7 & 64) > 0;
        this.k0 = (b7 & 128) > 0;
        byte b8 = bArr[33];
        this.l0 = (b8 & 1) > 0;
        this.m0 = (b8 & 2) > 0;
        this.n0 = (b8 & 4) > 0;
        this.o0 = (b8 & 8) > 0;
        this.p0 = (b8 & 16) > 0;
        this.q0 = (b8 & 32) > 0;
        this.r0 = (b8 & 64) <= 0;
        this.s0 = (b8 & 128) > 0;
        byte b9 = bArr[32];
        this.t0 = (b9 & 1) > 0;
        this.u0 = (b9 & 2) > 0;
        this.v0 = (b9 & 8) > 0;
        this.w0 = (b9 & 16) > 0;
        this.x0 = (b9 & 32) > 0;
        this.y0 = (b9 & 64) > 0;
        this.z0 = (b9 & 128) > 0;
        if (bArr.length > 39 && (bArr[39] & 1) > 0) {
            z = true;
        }
        this.A0 = z;
        if (z2) {
            this.G = true;
            this.H = true;
        }
    }

    public static String a(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3++) {
            String hexString = Integer.toHexString(bArr[i3] & 255);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String get_version_app(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(48, 56);
    }

    public static String get_version_extension(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(64, 76);
    }

    public static String get_version_flash(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(40, 48);
    }

    public static String get_version_hardware(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(12, 20);
    }

    public static String get_version_page_support(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(20, 28);
    }

    public static String get_version_patch(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(28, 40);
    }

    public static String get_version_project(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(0, 12);
    }

    public static String get_version_serial(String str) {
        return (str == null || str.length() < 76) ? "" : str.substring(56, 64);
    }

    public static boolean is_version_update_enabled(String str, String str2) {
        if (str == null || str.length() < 76 || str2 == null || str2.length() < 76) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        String str3 = get_version_patch(lowerCase) + get_version_app(lowerCase);
        StringBuilder sb = new StringBuilder();
        sb.append(get_version_patch(lowerCase2));
        sb.append(get_version_app(lowerCase2));
        return str3.compareTo(sb.toString()) < 0;
    }

    @Nullable
    public static WristbandVersion newInstance(String str) {
        if (str == null || str.length() < 76) {
            return null;
        }
        return new WristbandVersion(BytesUtil.hexStr2Bytes(str));
    }

    @Nullable
    public static WristbandVersion newInstance(byte[] bArr) {
        if (bArr == null || bArr.length < 38) {
            return null;
        }
        return new WristbandVersion(bArr);
    }

    public String getApp() {
        return this.f;
    }

    @Override // com.htsmart.wristband2.bean.BytesEnabled
    public byte[] getBytes() {
        return this.C0;
    }

    public String getExtension() {
        return this.h;
    }

    public String getFlash() {
        return this.e;
    }

    public String getHardware() {
        return this.b;
    }

    public int getPageSupport() {
        return this.c;
    }

    public String getPatch() {
        return this.d;
    }

    public String getProject() {
        return this.f11971a;
    }

    public String getRawVersion() {
        return this.B0;
    }

    public String getSerial() {
        return this.g;
    }

    public boolean isAirPumpBloodPressure() {
        return this.C;
    }

    public boolean isBloodPressureEnabled() {
        return this.k;
    }

    public boolean isBrightnessVibrateEnabled() {
        return this.y;
    }

    public boolean isDynamicHeartRateEnabled() {
        return this.r;
    }

    public boolean isEcgEnabled() {
        return this.n;
    }

    public boolean isExtAliAgent() {
        return this.v0;
    }

    public boolean isExtAncsAppleMusicZoomTikTokTelephoneMissed() {
        return this.l0;
    }

    public boolean isExtAncsEmail() {
        return this.G;
    }

    public boolean isExtAncsExtra1() {
        return this.L;
    }

    public boolean isExtAncsHikeYouTube() {
        return this.g0;
    }

    public boolean isExtAncsViberTelegram() {
        return this.H;
    }

    public boolean isExtBusinessCard() {
        return this.p0;
    }

    public boolean isExtChangeConfigItself() {
        return this.S;
    }

    public boolean isExtCollectionCode() {
        return this.o0;
    }

    public boolean isExtContacts() {
        return this.U;
    }

    public boolean isExtCustomLabel() {
        return this.w0;
    }

    public boolean isExtDialComponent() {
        return this.i0;
    }

    public boolean isExtDialCustom() {
        return this.z;
    }

    public boolean isExtDialMultiple() {
        return this.h0;
    }

    public boolean isExtDialUpgrade() {
        return this.M;
    }

    public boolean isExtFindPhoneAudio() {
        return this.e0;
    }

    public boolean isExtGUI() {
        return this.j0;
    }

    public boolean isExtGameSkin() {
        return this.k0;
    }

    public boolean isExtGetSupportQrCode() {
        return this.x0;
    }

    public boolean isExtHabit() {
        return this.t0;
    }

    public boolean isExtHandWashingReminder() {
        return this.a0;
    }

    public boolean isExtHealthyConfigInterval() {
        return this.X;
    }

    public boolean isExtHidePageConfig() {
        return this.F;
    }

    public boolean isExtLatestHealthy() {
        return this.O;
    }

    public boolean isExtLcdShape() {
        return this.n0;
    }

    public boolean isExtLockScreen() {
        return this.d0;
    }

    public boolean isExtMockEcg() {
        return this.T;
    }

    public boolean isExtNewNotificationFormat() {
        return this.Q;
    }

    public boolean isExtNewSleepFormat() {
        return this.R;
    }

    public boolean isExtNotDisturb() {
        return this.N;
    }

    public boolean isExtNucleicAcidCode() {
        return this.s0;
    }

    public boolean isExtPhotovoltaic() {
        return this.A0;
    }

    public boolean isExtPowerSaveMode() {
        return this.y0;
    }

    public boolean isExtPowerSavePeriod() {
        return this.z0;
    }

    public boolean isExtProtectionReminder() {
        return this.W;
    }

    public boolean isExtQRCodeConnect() {
        return this.m0;
    }

    public boolean isExtQrCodeExtra1() {
        return this.u0;
    }

    public boolean isExtSchedule() {
        return this.f0;
    }

    public boolean isExtSedentaryConfigInterval() {
        return this.Y;
    }

    public boolean isExtSetDeviceName() {
        return this.b0;
    }

    public boolean isExtSettingDialComponent() {
        return this.r0;
    }

    public boolean isExtSingleGameRecord() {
        return this.q0;
    }

    public boolean isExtStepExtra() {
        return this.I;
    }

    public boolean isExtTiEcg() {
        return this.V;
    }

    public boolean isExtTpUpgrade() {
        return this.P;
    }

    public boolean isExtWarnBloodPressure() {
        return this.K;
    }

    public boolean isExtWarnHeartRate() {
        return this.J;
    }

    public boolean isExtWeatherForecast() {
        return this.Z;
    }

    public boolean isExtWeatherSwitch() {
        return this.c0;
    }

    public boolean isGameEnabled() {
        return this.v;
    }

    public boolean isGpsEnabled() {
        return this.E;
    }

    public boolean isHeartRateEnabled() {
        return this.i;
    }

    public boolean isMeasureDataSyncable() {
        return this.D;
    }

    public boolean isOxygenEnabled() {
        return this.j;
    }

    public boolean isPageSupport(int i) {
        return Utils.isFlagEnable(this.c, 1 << i);
    }

    public boolean isPlatform8762CEnabled() {
        return this.q;
    }

    public boolean isPlatformNordicEnabled() {
        return this.w;
    }

    public boolean isPressureEnabled() {
        return this.u;
    }

    public boolean isRespiratoryRateEnabled() {
        return this.l;
    }

    public boolean isSportConnectivity() {
        return this.B;
    }

    public boolean isSportEnabled() {
        return this.o;
    }

    public boolean isSportPushEnabled() {
        return this.A;
    }

    public boolean isTemperatureEnabled() {
        return this.s;
    }

    public boolean isUpgradeFirmwareSilent() {
        return this.x;
    }

    public boolean isWeatherEnabled() {
        return this.m;
    }

    public boolean isWechatSportEnabled() {
        return this.p;
    }

    public boolean isWomenHealthyEnabled() {
        return this.t;
    }

    public String toString() {
        return "================================\nproject:" + this.f11971a + "\nhardware:" + this.b + "\npageSupport:" + this.c + "\npatch:" + this.d + "\nflash:" + this.e + "\napp:" + this.f + "\nserial:" + this.g + "\nextension:" + this.h + "\n================================\nheartRateEnable:" + this.i + "\noxygenEnable:" + this.j + "\nbloodPressureEnable:" + this.k + "\nrespiratoryRateEnable:" + this.l + "\nweatherEnable:" + this.m + "\necgEnable:" + this.n + "\nsportEnabled:" + this.o + "\nwechatSportEnabled:" + this.p + "\nplatform8762CEnabled:" + this.q + "\ndynamicHeartRateEnabled:" + this.r + "\ntemperatureEnabled:" + this.s + "\nwomenHealthyEnabled:" + this.t + "\npressureEnabled:" + this.u + "\ngameEnabled:" + this.v + "\nplatformNordicEnabled:" + this.w + "\n================================\nextHidePageConfig:" + this.F + "\nextAncsEmail:" + this.G + "\nextAncsViberTelegram:" + this.H + "\nextStepExtra:" + this.I + "\nextWarnHeartRate:" + this.J + "\nextWarnBloodPressure:" + this.K + "\nextAncsExtra1:" + this.L + "\nextDialUpgrade:" + this.M + "\nextNotDisturb:" + this.N + "\nextLatestHealthy:" + this.O + "\nextTpUpgrade:" + this.P + "\nextNewNotificationFormat:" + this.Q + "\nextNewSleepFormat:" + this.R + "\nextChangeConfigItself:" + this.S + "\nextMockEcg:" + this.T + "\nextContacts:" + this.U + "\nextTiEcg:" + this.V + "\nextProtectionReminder:" + this.W + "\nextHealthyConfigInterval:" + this.X + "\nextSedentaryConfigInterval:" + this.Y + "\nextWeatherForecast:" + this.Z + "\nextHandWashingReminder:" + this.a0 + "\nextSetDeviceName:" + this.b0 + "\nextWeatherSwitch:" + this.c0 + "\nextFindPhoneAudio:" + this.e0 + "\nextLockScreen:" + this.d0 + "\nextSchedule:" + this.f0 + "\nextAncsHikeYouTube:" + this.g0 + "\nextDialMultiple:" + this.h0 + "\nextDialComponent:" + this.i0 + "\nextGUI:" + this.j0 + "\nextGameSkin:" + this.k0 + "\nextAncsAppleMusicZoomTikTokTelephoneMissed:" + this.l0 + "\nextQRCodeConnect:" + this.m0 + "\nextLcdShape:" + this.n0 + "\n================================\n";
    }
}
