package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
/* loaded from: classes.dex */
public class KeyPosition extends androidx.constraintlayout.motion.widget.a {
    public static final String DRAWPATH = "drawPath";
    public static final String PERCENT_HEIGHT = "percentHeight";
    public static final String PERCENT_WIDTH = "percentWidth";
    public static final String PERCENT_X = "percentX";
    public static final String PERCENT_Y = "percentY";
    public static final String SIZE_PERCENT = "sizePercent";
    public static final String TRANSITION_EASING = "transitionEasing";
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;
    public String f = null;
    public int g = Key.UNSET;
    public int h = 0;
    public float i = Float.NaN;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public int o = 0;
    public float p = Float.NaN;
    public float q = Float.NaN;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f943a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f943a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyPosition_motionTarget, 1);
            f943a.append(R.styleable.KeyPosition_framePosition, 2);
            f943a.append(R.styleable.KeyPosition_transitionEasing, 3);
            f943a.append(R.styleable.KeyPosition_curveFit, 4);
            f943a.append(R.styleable.KeyPosition_drawPath, 5);
            f943a.append(R.styleable.KeyPosition_percentX, 6);
            f943a.append(R.styleable.KeyPosition_percentY, 7);
            f943a.append(R.styleable.KeyPosition_keyPositionType, 9);
            f943a.append(R.styleable.KeyPosition_sizePercent, 8);
            f943a.append(R.styleable.KeyPosition_percentWidth, 11);
            f943a.append(R.styleable.KeyPosition_percentHeight, 12);
            f943a.append(R.styleable.KeyPosition_pathMotionArc, 10);
        }

        public static void b(KeyPosition keyPosition, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f943a.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyPosition.b);
                            keyPosition.b = resourceId;
                            if (resourceId == -1) {
                                keyPosition.c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyPosition.c = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.b = typedArray.getResourceId(index, keyPosition.b);
                            break;
                        }
                    case 2:
                        keyPosition.f939a = typedArray.getInt(index, keyPosition.f939a);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            keyPosition.f = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.f = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        keyPosition.e = typedArray.getInteger(index, keyPosition.e);
                        break;
                    case 5:
                        keyPosition.h = typedArray.getInt(index, keyPosition.h);
                        break;
                    case 6:
                        keyPosition.k = typedArray.getFloat(index, keyPosition.k);
                        break;
                    case 7:
                        keyPosition.l = typedArray.getFloat(index, keyPosition.l);
                        break;
                    case 8:
                        float f = typedArray.getFloat(index, keyPosition.j);
                        keyPosition.i = f;
                        keyPosition.j = f;
                        break;
                    case 9:
                        keyPosition.o = typedArray.getInt(index, keyPosition.o);
                        break;
                    case 10:
                        keyPosition.g = typedArray.getInt(index, keyPosition.g);
                        break;
                    case 11:
                        keyPosition.i = typedArray.getFloat(index, keyPosition.i);
                        break;
                    case 12:
                        keyPosition.j = typedArray.getFloat(index, keyPosition.j);
                        break;
                    default:
                        Log.e(TypedValues.PositionType.NAME, "unused attribute 0x" + Integer.toHexString(index) + "   " + f943a.get(index));
                        break;
                }
            }
            if (keyPosition.f939a == -1) {
                Log.e(TypedValues.PositionType.NAME, "no frame position");
            }
        }
    }

    public KeyPosition() {
        this.mType = 2;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, ViewSpline> hashMap) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key) {
        super.copy(key);
        KeyPosition keyPosition = (KeyPosition) key;
        this.f = keyPosition.f;
        this.g = keyPosition.g;
        this.h = keyPosition.h;
        this.i = keyPosition.i;
        this.j = Float.NaN;
        this.k = keyPosition.k;
        this.l = keyPosition.l;
        this.m = keyPosition.m;
        this.n = keyPosition.n;
        this.p = keyPosition.p;
        this.q = keyPosition.q;
        return this;
    }

    public final void e(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float f7 = Float.isNaN(this.k) ? 0.0f : this.k;
        float f8 = Float.isNaN(this.n) ? 0.0f : this.n;
        float f9 = Float.isNaN(this.l) ? 0.0f : this.l;
        this.p = (int) (f + (f7 * f5) + ((Float.isNaN(this.m) ? 0.0f : this.m) * f6));
        this.q = (int) (f2 + (f5 * f8) + (f6 * f9));
    }

    public final void f(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float f7 = this.k;
        float f8 = this.l;
        this.p = f + (f5 * f7) + ((-f6) * f8);
        this.q = f2 + (f6 * f7) + (f5 * f8);
    }

    public void g(int i, int i2, float f, float f2, float f3, float f4) {
        int i3 = this.o;
        if (i3 == 1) {
            f(f, f2, f3, f4);
        } else if (i3 != 2) {
            e(f, f2, f3, f4);
        } else {
            h(i, i2);
        }
    }

    public final void h(int i, int i2) {
        float f = this.k;
        float f2 = 0;
        this.p = ((i - 0) * f) + f2;
        this.q = ((i2 - 0) * f) + f2;
    }

    public void i(RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        if (strArr[0] != null) {
            if ("percentX".equals(strArr[0])) {
                fArr[0] = (f - centerX) / centerX2;
                fArr[1] = (f2 - centerY) / centerY2;
                return;
            }
            fArr[1] = (f - centerX) / centerX2;
            fArr[0] = (f2 - centerY) / centerY2;
            return;
        }
        strArr[0] = "percentX";
        fArr[0] = (f - centerX) / centerX2;
        strArr[1] = "percentY";
        fArr[1] = (f2 - centerY) / centerY2;
    }

    @Override // androidx.constraintlayout.motion.widget.a
    public boolean intersects(int i, int i2, RectF rectF, RectF rectF2, float f, float f2) {
        g(i, i2, rectF.centerX(), rectF.centerY(), rectF2.centerX(), rectF2.centerY());
        return Math.abs(f - this.p) < 20.0f && Math.abs(f2 - this.q) < 20.0f;
    }

    public void j(RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        float hypot = (float) Math.hypot(centerX2, centerY2);
        if (hypot < 1.0E-4d) {
            System.out.println("distance ~ 0");
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            return;
        }
        float f3 = centerX2 / hypot;
        float f4 = centerY2 / hypot;
        float f5 = f2 - centerY;
        float f6 = f - centerX;
        float f7 = ((f3 * f5) - (f6 * f4)) / hypot;
        float f8 = ((f3 * f6) + (f4 * f5)) / hypot;
        if (strArr[0] != null) {
            if ("percentX".equals(strArr[0])) {
                fArr[0] = f8;
                fArr[1] = f7;
                return;
            }
            return;
        }
        strArr[0] = "percentX";
        strArr[1] = "percentY";
        fArr[0] = f8;
        fArr[1] = f7;
    }

    public void k(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        rectF.centerX();
        rectF.centerY();
        rectF2.centerX();
        rectF2.centerY();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (strArr[0] != null) {
            if ("percentX".equals(strArr[0])) {
                fArr[0] = f / width;
                fArr[1] = f2 / height;
                return;
            }
            fArr[1] = f / width;
            fArr[0] = f2 / height;
            return;
        }
        strArr[0] = "percentX";
        fArr[0] = f / width;
        strArr[1] = "percentY";
        fArr[1] = f2 / height;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyPosition));
    }

    @Override // androidx.constraintlayout.motion.widget.a
    public void positionAttributes(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        int i = this.o;
        if (i == 1) {
            j(rectF, rectF2, f, f2, strArr, fArr);
        } else if (i != 2) {
            i(rectF, rectF2, f, f2, strArr, fArr);
        } else {
            k(view, rectF, rectF2, f, f2, strArr, fArr);
        }
    }

    public void setType(int i) {
        this.o = i;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c = 0;
                    break;
                }
                break;
            case -1127236479:
                if (str.equals("percentWidth")) {
                    c = 1;
                    break;
                }
                break;
            case -1017587252:
                if (str.equals("percentHeight")) {
                    c = 2;
                    break;
                }
                break;
            case -827014263:
                if (str.equals("drawPath")) {
                    c = 3;
                    break;
                }
                break;
            case -200259324:
                if (str.equals("sizePercent")) {
                    c = 4;
                    break;
                }
                break;
            case 428090547:
                if (str.equals("percentX")) {
                    c = 5;
                    break;
                }
                break;
            case 428090548:
                if (str.equals("percentY")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.f = obj.toString();
                return;
            case 1:
                this.i = c(obj);
                return;
            case 2:
                this.j = c(obj);
                return;
            case 3:
                this.h = d(obj);
                return;
            case 4:
                float c2 = c(obj);
                this.i = c2;
                this.j = c2;
                return;
            case 5:
                this.k = c(obj);
                return;
            case 6:
                this.l = c(obj);
                return;
            default:
                return;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo7clone() {
        return new KeyPosition().copy(this);
    }
}
