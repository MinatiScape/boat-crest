package com.htsmart.wristband2.bean.config;
/* loaded from: classes11.dex */
public class BrightnessVibrateConfig extends AbstractConfig {
    public static final int PACKET_LENGTH = 38;

    /* loaded from: classes11.dex */
    public class AlwaysBright {
        public AlwaysBright() {
        }

        public boolean isEnabled() {
            return (BrightnessVibrateConfig.this.values[23] & 255) > 0;
        }

        public boolean isSupport() {
            return (BrightnessVibrateConfig.this.values[22] & 255) > 0;
        }

        public void setEnabled(boolean z) {
            BrightnessVibrateConfig.this.values[23] = z ? (byte) 1 : (byte) 0;
        }
    }

    /* loaded from: classes11.dex */
    public class BrightDuration extends b {
        public BrightDuration(BrightnessVibrateConfig brightnessVibrateConfig) {
            super(0, brightnessVibrateConfig.values);
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int[] getItems() {
            return super.getItems();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int getSelectPosition() {
            return super.getSelectPosition();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ void setSelectPosition(int i) {
            super.setSelectPosition(i);
        }
    }

    /* loaded from: classes11.dex */
    public class Brightness extends b {
        public Brightness(BrightnessVibrateConfig brightnessVibrateConfig) {
            super(24, brightnessVibrateConfig.values);
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int[] getItems() {
            return super.getItems();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int getSelectPosition() {
            return super.getSelectPosition();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ void setSelectPosition(int i) {
            super.setSelectPosition(i);
        }
    }

    /* loaded from: classes11.dex */
    public class LongTimeBrightDuration {

        /* renamed from: a  reason: collision with root package name */
        public final int[] f11973a;
        public int b;
        public boolean c;

        public LongTimeBrightDuration() {
            byte[] bArr = BrightnessVibrateConfig.this.values;
            int i = bArr[15] & 255;
            this.f11973a = new int[i];
            if (i > 0) {
                int i2 = bArr[14] & 255;
                for (int i3 = 0; i3 < i; i3++) {
                    int[] iArr = this.f11973a;
                    iArr[i3] = BrightnessVibrateConfig.this.values[i3 + 14 + 3] & 255;
                    if (iArr[i3] == i2) {
                        this.b = i3;
                    }
                }
            }
            this.c = (BrightnessVibrateConfig.this.values[16] & 255) > 0;
        }

        public int[] getItems() {
            return (int[]) this.f11973a.clone();
        }

        public int getSelectPosition() {
            return this.b;
        }

        public boolean isEnabled() {
            return this.c;
        }

        public void setEnabled(boolean z) {
            this.c = z;
            BrightnessVibrateConfig.this.values[16] = z ? (byte) 1 : (byte) 0;
        }

        public void setSelectPosition(int i) {
            this.b = i;
            BrightnessVibrateConfig.this.values[14] = (byte) this.f11973a[i];
        }
    }

    /* loaded from: classes11.dex */
    public class TurnWristBrightDuration extends b {
        public TurnWristBrightDuration(BrightnessVibrateConfig brightnessVibrateConfig) {
            super(7, brightnessVibrateConfig.values);
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int[] getItems() {
            return super.getItems();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int getSelectPosition() {
            return super.getSelectPosition();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ void setSelectPosition(int i) {
            super.setSelectPosition(i);
        }
    }

    /* loaded from: classes11.dex */
    public class Vibrate extends b {
        public Vibrate(BrightnessVibrateConfig brightnessVibrateConfig) {
            super(31, brightnessVibrateConfig.values);
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int[] getItems() {
            return super.getItems();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ int getSelectPosition() {
            return super.getSelectPosition();
        }

        @Override // com.htsmart.wristband2.bean.config.BrightnessVibrateConfig.b
        public /* bridge */ /* synthetic */ void setSelectPosition(int i) {
            super.setSelectPosition(i);
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f11974a;
        public final byte[] b;
        public final int[] c;
        public int d;

        public b(int i, byte[] bArr) {
            this.f11974a = i;
            this.b = bArr;
            int i2 = bArr[i + 1] & 255;
            this.c = new int[i2];
            if (i2 > 0) {
                int i3 = bArr[i] & 255;
                for (int i4 = 0; i4 < i2; i4++) {
                    int[] iArr = this.c;
                    iArr[i4] = bArr[i + i4 + 2] & 255;
                    if (iArr[i4] == i3) {
                        this.d = i4;
                    }
                }
            }
        }

        public int[] getItems() {
            return (int[]) this.c.clone();
        }

        public int getSelectPosition() {
            return this.d;
        }

        public void setSelectPosition(int i) {
            this.d = i;
            this.b[this.f11974a] = (byte) this.c[i];
        }
    }

    public BrightnessVibrateConfig() {
    }

    public BrightnessVibrateConfig(byte[] bArr) {
        super(bArr);
    }

    public AlwaysBright alwaysBright() {
        return new AlwaysBright();
    }

    public BrightDuration brightDuration() {
        return new BrightDuration();
    }

    public Brightness brightness() {
        return new Brightness(this);
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 38;
    }

    public LongTimeBrightDuration longTimeBrightDuration() {
        return new LongTimeBrightDuration();
    }

    public TurnWristBrightDuration turnWristBrightDuration() {
        return new TurnWristBrightDuration();
    }

    public Vibrate vibrate() {
        return new Vibrate(this);
    }
}
