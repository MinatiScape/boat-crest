package androidx.constraintlayout.core.motion;

import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class CustomAttribute {

    /* renamed from: a  reason: collision with root package name */
    public String f858a;
    public AttributeType b;
    public int c;
    public float d;
    public boolean e;
    public int f;

    /* loaded from: classes.dex */
    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f859a;

        static {
            int[] iArr = new int[AttributeType.values().length];
            f859a = iArr;
            try {
                iArr[AttributeType.REFERENCE_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f859a[AttributeType.BOOLEAN_TYPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f859a[AttributeType.STRING_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f859a[AttributeType.COLOR_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f859a[AttributeType.COLOR_DRAWABLE_TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f859a[AttributeType.INT_TYPE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f859a[AttributeType.FLOAT_TYPE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f859a[AttributeType.DIMENSION_TYPE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public CustomAttribute(String str, AttributeType attributeType) {
        this.f858a = str;
        this.b = attributeType;
    }

    public static int a(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static int hsvToRgb(float f, float f2, float f3) {
        float f4 = f * 6.0f;
        int i = (int) f4;
        float f5 = f4 - i;
        float f6 = f3 * 255.0f;
        int i2 = (int) (((1.0f - f2) * f6) + 0.5f);
        int i3 = (int) (((1.0f - (f5 * f2)) * f6) + 0.5f);
        int i4 = (int) (((1.0f - ((1.0f - f5) * f2)) * f6) + 0.5f);
        int i5 = (int) (f6 + 0.5f);
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return 0;
                            }
                            return ((i5 << 16) + (i2 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
                        }
                        return ((i4 << 16) + (i2 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
                    }
                    return ((i2 << 16) + (i3 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
                }
                return ((i2 << 16) + (i5 << 8) + i4) | ViewCompat.MEASURED_STATE_MASK;
            }
            return ((i3 << 16) + (i5 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
        }
        return ((i5 << 16) + (i4 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
    }

    public boolean diff(CustomAttribute customAttribute) {
        AttributeType attributeType;
        if (customAttribute == null || (attributeType = this.b) != customAttribute.b) {
            return false;
        }
        switch (a.f859a[attributeType.ordinal()]) {
            case 1:
            case 6:
                return this.c == customAttribute.c;
            case 2:
                return this.e == customAttribute.e;
            case 3:
                return this.c == customAttribute.c;
            case 4:
            case 5:
                return this.f == customAttribute.f;
            case 7:
                return this.d == customAttribute.d;
            case 8:
                return this.d == customAttribute.d;
            default:
                return false;
        }
    }

    public AttributeType getType() {
        return this.b;
    }

    public float getValueToInterpolate() {
        switch (a.f859a[this.b.ordinal()]) {
            case 2:
                return this.e ? 1.0f : 0.0f;
            case 3:
                throw new RuntimeException("Cannot interpolate String");
            case 4:
            case 5:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 6:
                return this.c;
            case 7:
                return this.d;
            case 8:
                return this.d;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (a.f859a[this.b.ordinal()]) {
            case 2:
                fArr[0] = this.e ? 1.0f : 0.0f;
                return;
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
            case 5:
                int i = this.f;
                float pow = (float) Math.pow(((i >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((i >> 8) & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = (float) Math.pow((i & 255) / 255.0f, 2.2d);
                fArr[3] = ((i >> 24) & 255) / 255.0f;
                return;
            case 6:
                fArr[0] = this.c;
                return;
            case 7:
                fArr[0] = this.d;
                return;
            case 8:
                fArr[0] = this.d;
                return;
            default:
                return;
        }
    }

    public boolean isContinuous() {
        int i = a.f859a[this.b.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? false : true;
    }

    public int numberOfInterpolatedValues() {
        int i = a.f859a[this.b.ordinal()];
        return (i == 4 || i == 5) ? 4 : 1;
    }

    public void setColorValue(int i) {
        this.f = i;
    }

    public void setFloatValue(float f) {
        this.d = f;
    }

    public void setIntValue(int i) {
        this.c = i;
    }

    public void setStringValue(String str) {
    }

    public void setValue(float[] fArr) {
        switch (a.f859a[this.b.ordinal()]) {
            case 1:
            case 6:
                this.c = (int) fArr[0];
                return;
            case 2:
                this.e = ((double) fArr[0]) > 0.5d;
                return;
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
            case 5:
                int hsvToRgb = hsvToRgb(fArr[0], fArr[1], fArr[2]);
                this.f = hsvToRgb;
                this.f = (a((int) (fArr[3] * 255.0f)) << 24) | (hsvToRgb & 16777215);
                return;
            case 7:
                this.d = fArr[0];
                return;
            case 8:
                this.d = fArr[0];
                return;
            default:
                return;
        }
    }

    public CustomAttribute(String str, AttributeType attributeType, Object obj, boolean z) {
        this.f858a = str;
        this.b = attributeType;
        setValue(obj);
    }

    public CustomAttribute(CustomAttribute customAttribute, Object obj) {
        this.f858a = customAttribute.f858a;
        this.b = customAttribute.b;
        setValue(obj);
    }

    public void setValue(Object obj) {
        switch (a.f859a[this.b.ordinal()]) {
            case 1:
            case 6:
                this.c = ((Integer) obj).intValue();
                return;
            case 2:
                this.e = ((Boolean) obj).booleanValue();
                return;
            case 3:
                String str = (String) obj;
                return;
            case 4:
            case 5:
                this.f = ((Integer) obj).intValue();
                return;
            case 7:
                this.d = ((Float) obj).floatValue();
                return;
            case 8:
                this.d = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }
}
