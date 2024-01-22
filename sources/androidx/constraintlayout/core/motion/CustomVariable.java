package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class CustomVariable {

    /* renamed from: a  reason: collision with root package name */
    public String f860a;
    public int b;
    public int c;
    public float d;
    public String e;
    public boolean f;

    public CustomVariable(CustomVariable customVariable) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = customVariable.f860a;
        this.b = customVariable.b;
        this.c = customVariable.c;
        this.d = customVariable.d;
        this.e = customVariable.e;
        this.f = customVariable.f;
    }

    public static int a(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static String colorString(int i) {
        String str = "00000000" + Integer.toHexString(i);
        return MqttTopic.MULTI_LEVEL_WILDCARD + str.substring(str.length() - 8);
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

    public static int rgbaTocColor(float f, float f2, float f3, float f4) {
        int a2 = a((int) (f * 255.0f));
        int a3 = a((int) (f2 * 255.0f));
        return (a2 << 16) | (a((int) (f4 * 255.0f)) << 24) | (a3 << 8) | a((int) (f3 * 255.0f));
    }

    public void applyToWidget(MotionWidget motionWidget) {
        int i = this.b;
        switch (i) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                motionWidget.setCustomAttribute(this.f860a, i, this.c);
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                motionWidget.setCustomAttribute(this.f860a, i, this.d);
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                motionWidget.setCustomAttribute(this.f860a, i, this.e);
                return;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                motionWidget.setCustomAttribute(this.f860a, i, this.f);
                return;
            default:
                return;
        }
    }

    public CustomVariable copy() {
        return new CustomVariable(this);
    }

    public boolean diff(CustomVariable customVariable) {
        int i;
        if (customVariable == null || (i = this.b) != customVariable.b) {
            return false;
        }
        switch (i) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                return this.c == customVariable.c;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                return this.d == customVariable.d;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                return this.c == customVariable.c;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                return this.c == customVariable.c;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                return this.f == customVariable.f;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                return this.d == customVariable.d;
            default:
                return false;
        }
    }

    public boolean getBooleanValue() {
        return this.f;
    }

    public int getColorValue() {
        return this.c;
    }

    public float getFloatValue() {
        return this.d;
    }

    public int getIntegerValue() {
        return this.c;
    }

    public int getInterpolatedColor(float[] fArr) {
        int a2 = a((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f));
        int a3 = a((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f));
        return (a((int) (fArr[3] * 255.0f)) << 24) | (a2 << 16) | (a3 << 8) | a((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f));
    }

    public String getName() {
        return this.f860a;
    }

    public String getStringValue() {
        return this.e;
    }

    public int getType() {
        return this.b;
    }

    public float getValueToInterpolate() {
        switch (this.b) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                return this.c;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                return this.d;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                throw new RuntimeException("Cannot interpolate String");
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                return this.f ? 1.0f : 0.0f;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                return this.d;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (this.b) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                fArr[0] = this.c;
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                fArr[0] = this.d;
                return;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                int i = this.c;
                float pow = (float) Math.pow(((i >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((i >> 8) & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = (float) Math.pow((i & 255) / 255.0f, 2.2d);
                fArr[3] = ((i >> 24) & 255) / 255.0f;
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                throw new RuntimeException("Cannot interpolate String");
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                fArr[0] = this.f ? 1.0f : 0.0f;
                return;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                fArr[0] = this.d;
                return;
            default:
                return;
        }
    }

    public boolean isContinuous() {
        int i = this.b;
        return (i == 903 || i == 904 || i == 906) ? false : true;
    }

    public int numberOfInterpolatedValues() {
        return this.b != 902 ? 1 : 4;
    }

    public void setBooleanValue(boolean z) {
        this.f = z;
    }

    public void setFloatValue(float f) {
        this.d = f;
    }

    public void setIntValue(int i) {
        this.c = i;
    }

    public void setInterpolatedValue(MotionWidget motionWidget, float[] fArr) {
        int i = this.b;
        switch (i) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                motionWidget.setCustomAttribute(this.f860a, i, (int) fArr[0]);
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                motionWidget.setCustomAttribute(this.f860a, i, fArr[0]);
                return;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                int a2 = a((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f));
                int a3 = a((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f));
                motionWidget.setCustomAttribute(this.f860a, this.b, (a((int) (fArr[3] * 255.0f)) << 24) | (a2 << 16) | (a3 << 8) | a((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f)));
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                throw new RuntimeException("unable to interpolate " + this.f860a);
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                motionWidget.setCustomAttribute(this.f860a, i, fArr[0] > 0.5f);
                return;
            default:
                return;
        }
    }

    public void setStringValue(String str) {
        this.e = str;
    }

    public void setValue(float[] fArr) {
        switch (this.b) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                this.c = (int) fArr[0];
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                this.d = fArr[0];
                return;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                this.c = ((Math.round(fArr[3] * 255.0f) & 255) << 24) | ((Math.round(((float) Math.pow(fArr[0], 0.5d)) * 255.0f) & 255) << 16) | ((Math.round(((float) Math.pow(fArr[1], 0.5d)) * 255.0f) & 255) << 8) | (Math.round(((float) Math.pow(fArr[2], 0.5d)) * 255.0f) & 255);
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                throw new RuntimeException("Cannot interpolate String");
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                this.f = ((double) fArr[0]) > 0.5d;
                return;
            default:
                return;
        }
    }

    public String toString() {
        String str = this.f860a + ':';
        switch (this.b) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                return str + this.c;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                return str + this.d;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                return str + colorString(this.c);
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                return str + this.e;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                return str + Boolean.valueOf(this.f);
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                return str + this.d;
            default:
                return str + "????";
        }
    }

    public CustomVariable(String str, int i, String str2) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = str;
        this.b = i;
        this.e = str2;
    }

    public void setValue(Object obj) {
        switch (this.b) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                this.c = ((Integer) obj).intValue();
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                this.d = ((Float) obj).floatValue();
                return;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                this.c = ((Integer) obj).intValue();
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                this.e = (String) obj;
                return;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                this.f = ((Boolean) obj).booleanValue();
                return;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                this.d = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }

    public CustomVariable(String str, int i, int i2) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = str;
        this.b = i;
        if (i == 901) {
            this.d = i2;
        } else {
            this.c = i2;
        }
    }

    public CustomVariable(String str, int i, float f) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = str;
        this.b = i;
        this.d = f;
    }

    public CustomVariable(String str, int i, boolean z) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = str;
        this.b = i;
        this.f = z;
    }

    public CustomVariable(String str, int i) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = str;
        this.b = i;
    }

    public CustomVariable(String str, int i, Object obj) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = str;
        this.b = i;
        setValue(obj);
    }

    public CustomVariable(CustomVariable customVariable, Object obj) {
        this.c = Integer.MIN_VALUE;
        this.d = Float.NaN;
        this.e = null;
        this.f860a = customVariable.f860a;
        this.b = customVariable.b;
        setValue(obj);
    }
}
