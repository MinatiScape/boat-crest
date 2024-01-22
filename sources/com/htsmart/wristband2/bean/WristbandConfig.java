package com.htsmart.wristband2.bean;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.bean.config.BloodPressureConfig;
import com.htsmart.wristband2.bean.config.BrightnessVibrateConfig;
import com.htsmart.wristband2.bean.config.DrinkWaterConfig;
import com.htsmart.wristband2.bean.config.FunctionConfig;
import com.htsmart.wristband2.bean.config.HandWashingReminderConfig;
import com.htsmart.wristband2.bean.config.HealthyConfig;
import com.htsmart.wristband2.bean.config.NotDisturbConfig;
import com.htsmart.wristband2.bean.config.NotificationConfig;
import com.htsmart.wristband2.bean.config.PageConfig;
import com.htsmart.wristband2.bean.config.ProtectionReminderConfig;
import com.htsmart.wristband2.bean.config.SedentaryConfig;
import com.htsmart.wristband2.bean.config.TurnWristLightingConfig;
import com.htsmart.wristband2.bean.config.WarnBloodPressureConfig;
import com.htsmart.wristband2.bean.config.WarnHeartRateConfig;
import com.htsmart.wristband2.bean.config.WomenHealthyConfig;
/* loaded from: classes11.dex */
public class WristbandConfig implements BytesEnabled {

    /* renamed from: a  reason: collision with root package name */
    public WristbandVersion f11968a;
    public NotificationConfig b;
    public PageConfig c;
    public FunctionConfig d;
    public HealthyConfig e;
    public SedentaryConfig f;
    public DrinkWaterConfig g;
    public BloodPressureConfig h;
    public TurnWristLightingConfig i;
    public WarnHeartRateConfig j;
    public WarnBloodPressureConfig k;
    public NotDisturbConfig l;
    public WomenHealthyConfig m;
    public ProtectionReminderConfig n;
    public HandWashingReminderConfig o;
    public BrightnessVibrateConfig p;
    public byte[] q;

    public WristbandConfig(byte[] bArr) throws IllegalArgumentException {
        this.q = bArr;
        byte b = bArr[0];
        int i = bArr[1] & 255;
        if (b != 17 || i < 38) {
            throw new IllegalArgumentException();
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 2, bArr2, 0, i);
        this.f11968a = WristbandVersion.newInstance(bArr2);
        int i2 = i + 2;
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        int i4 = i3 + 1;
        int i5 = bArr[i3] & 255;
        if (b2 != 20 || i5 < 4) {
            throw new IllegalArgumentException();
        }
        byte[] bArr3 = new byte[4];
        System.arraycopy(bArr, i4, bArr3, 0, 4);
        this.b = new NotificationConfig(bArr3);
        int i6 = i4 + i5;
        int i7 = i6 + 1;
        byte b3 = bArr[i6];
        int i8 = i7 + 1;
        int i9 = bArr[i7] & 255;
        if (b3 != 23 || i9 < 2) {
            throw new IllegalArgumentException();
        }
        byte[] bArr4 = new byte[2];
        System.arraycopy(bArr, i8, bArr4, 0, 2);
        this.c = new PageConfig(bArr4);
        int i10 = i8 + i9;
        int i11 = i10 + 1;
        byte b4 = bArr[i10];
        int i12 = i11 + 1;
        int i13 = bArr[i11] & 255;
        if (b4 != 26 || i13 < 2) {
            throw new IllegalArgumentException();
        }
        byte[] bArr5 = new byte[2];
        System.arraycopy(bArr, i12, bArr5, 0, 2);
        this.d = new FunctionConfig(bArr5);
        int i14 = i12 + i13;
        int i15 = i14 + 1;
        byte b5 = bArr[i14];
        int i16 = i15 + 1;
        int i17 = bArr[i15] & 255;
        if (b5 != 36) {
            throw new IllegalArgumentException();
        }
        byte[] bArr6 = new byte[i17];
        System.arraycopy(bArr, i16, bArr6, 0, i17);
        this.e = new HealthyConfig(bArr6);
        int i18 = i16 + i17;
        int i19 = i18 + 1;
        byte b6 = bArr[i18];
        int i20 = i19 + 1;
        int i21 = bArr[i19] & 255;
        if (b6 != 39) {
            throw new IllegalArgumentException();
        }
        byte[] bArr7 = new byte[i21];
        System.arraycopy(bArr, i20, bArr7, 0, i21);
        this.f = new SedentaryConfig(bArr7);
        int i22 = i20 + i21;
        int i23 = i22 + 1;
        byte b7 = bArr[i22];
        int i24 = i23 + 1;
        int i25 = bArr[i23] & 255;
        if (b7 != 42 || i25 < 9) {
            throw new IllegalArgumentException();
        }
        byte[] bArr8 = new byte[9];
        System.arraycopy(bArr, i24, bArr8, 0, 9);
        this.g = new DrinkWaterConfig(bArr8);
        int i26 = i24 + i25;
        int i27 = i26 + 1;
        byte b8 = bArr[i26];
        int i28 = i27 + 1;
        int i29 = bArr[i27] & 255;
        if (b8 != 48 || i29 < 10) {
            throw new IllegalArgumentException();
        }
        byte[] bArr9 = new byte[10];
        System.arraycopy(bArr, i28, bArr9, 0, 10);
        this.h = new BloodPressureConfig(bArr9);
        int i30 = i28 + i29;
        int i31 = i30 + 1;
        byte b9 = bArr[i30];
        int i32 = i31 + 1;
        int i33 = bArr[i31] & 255;
        if (b9 != 45 || i33 < 9) {
            throw new IllegalArgumentException();
        }
        byte[] bArr10 = new byte[9];
        System.arraycopy(bArr, i32, bArr10, 0, 9);
        this.i = new TurnWristLightingConfig(bArr10);
        int i34 = i32 + i33;
        while (true) {
            int i35 = i34 + 1;
            if (bArr.length <= i35) {
                return;
            }
            byte b10 = bArr[i34];
            int i36 = i35 + 1;
            int i37 = bArr[i35] & 255;
            if (i37 > 0 && bArr.length >= i36 + i37) {
                byte[] bArr11 = new byte[i37];
                System.arraycopy(bArr, i36, bArr11, 0, i37);
                a(b10, bArr11);
            }
            i34 = i36 + i37;
        }
    }

    @Nullable
    public static WristbandConfig newInstance(byte[] bArr) {
        if (bArr != null && bArr.length >= 56) {
            try {
                return new WristbandConfig(bArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final void a(byte b, byte[] bArr) {
        if (b == 72) {
            if (bArr.length >= 4) {
                this.j = new WarnHeartRateConfig(bArr);
            }
        } else if (b == 75) {
            if (bArr.length >= 5) {
                this.k = new WarnBloodPressureConfig(bArr);
            }
        } else if (b == 78) {
            if (bArr.length >= 6) {
                this.l = new NotDisturbConfig(bArr);
            }
        } else if (b == 89) {
            if (bArr.length >= 10) {
                this.m = new WomenHealthyConfig(bArr);
            }
        } else if (b == 92) {
            if (bArr.length >= 7) {
                this.n = new ProtectionReminderConfig(bArr);
            }
        } else if (b == 100) {
            if (bArr.length >= 7) {
                this.o = new HandWashingReminderConfig(bArr);
            }
        } else if (b != 122 || bArr.length < 38) {
        } else {
            this.p = new BrightnessVibrateConfig(bArr);
        }
    }

    public BloodPressureConfig getBloodPressureConfig() {
        return this.h;
    }

    public BrightnessVibrateConfig getBrightnessVibrateConfig() {
        return this.p;
    }

    @Override // com.htsmart.wristband2.bean.BytesEnabled
    @Deprecated
    public byte[] getBytes() {
        return this.q;
    }

    public DrinkWaterConfig getDrinkWaterConfig() {
        return this.g;
    }

    public FunctionConfig getFunctionConfig() {
        return this.d;
    }

    public HandWashingReminderConfig getHandWashingReminderConfig() {
        return this.o;
    }

    public HealthyConfig getHealthyConfig() {
        return this.e;
    }

    public NotDisturbConfig getNotDisturbConfig() {
        return this.l;
    }

    public NotificationConfig getNotificationConfig() {
        return this.b;
    }

    public PageConfig getPageConfig() {
        return this.c;
    }

    public ProtectionReminderConfig getProtectionReminderConfig() {
        return this.n;
    }

    public SedentaryConfig getSedentaryConfig() {
        return this.f;
    }

    public TurnWristLightingConfig getTurnWristLightingConfig() {
        return this.i;
    }

    public WarnBloodPressureConfig getWarnBloodPressureConfig() {
        return this.k;
    }

    public WarnHeartRateConfig getWarnHeartRateConfig() {
        return this.j;
    }

    public WomenHealthyConfig getWomenHealthyConfig() {
        return this.m;
    }

    public WristbandVersion getWristbandVersion() {
        return this.f11968a;
    }
}
