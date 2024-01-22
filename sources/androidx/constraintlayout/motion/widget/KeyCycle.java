package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class KeyCycle extends Key {
    public static final int KEY_TYPE = 4;
    public static final int SHAPE_BOUNCE = 6;
    public static final int SHAPE_COS_WAVE = 5;
    public static final int SHAPE_REVERSE_SAW_WAVE = 4;
    public static final int SHAPE_SAW_WAVE = 3;
    public static final int SHAPE_SIN_WAVE = 0;
    public static final int SHAPE_SQUARE_WAVE = 1;
    public static final int SHAPE_TRIANGLE_WAVE = 2;
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_PHASE = "wavePhase";
    public static final String WAVE_SHAPE = "waveShape";
    public String e = null;
    public int f = 0;
    public int g = -1;
    public String h = null;
    public float i = Float.NaN;
    public float j = 0.0f;
    public float k = 0.0f;
    public float l = Float.NaN;
    public int m = -1;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public float s = Float.NaN;
    public float t = Float.NaN;
    public float u = Float.NaN;
    public float v = Float.NaN;
    public float w = Float.NaN;
    public float x = Float.NaN;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f941a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f941a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyCycle_motionTarget, 1);
            f941a.append(R.styleable.KeyCycle_framePosition, 2);
            f941a.append(R.styleable.KeyCycle_transitionEasing, 3);
            f941a.append(R.styleable.KeyCycle_curveFit, 4);
            f941a.append(R.styleable.KeyCycle_waveShape, 5);
            f941a.append(R.styleable.KeyCycle_wavePeriod, 6);
            f941a.append(R.styleable.KeyCycle_waveOffset, 7);
            f941a.append(R.styleable.KeyCycle_waveVariesBy, 8);
            f941a.append(R.styleable.KeyCycle_android_alpha, 9);
            f941a.append(R.styleable.KeyCycle_android_elevation, 10);
            f941a.append(R.styleable.KeyCycle_android_rotation, 11);
            f941a.append(R.styleable.KeyCycle_android_rotationX, 12);
            f941a.append(R.styleable.KeyCycle_android_rotationY, 13);
            f941a.append(R.styleable.KeyCycle_transitionPathRotate, 14);
            f941a.append(R.styleable.KeyCycle_android_scaleX, 15);
            f941a.append(R.styleable.KeyCycle_android_scaleY, 16);
            f941a.append(R.styleable.KeyCycle_android_translationX, 17);
            f941a.append(R.styleable.KeyCycle_android_translationY, 18);
            f941a.append(R.styleable.KeyCycle_android_translationZ, 19);
            f941a.append(R.styleable.KeyCycle_motionProgress, 20);
            f941a.append(R.styleable.KeyCycle_wavePhase, 21);
        }

        public static void b(KeyCycle keyCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f941a.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyCycle.b);
                            keyCycle.b = resourceId;
                            if (resourceId == -1) {
                                keyCycle.c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyCycle.c = typedArray.getString(index);
                            break;
                        } else {
                            keyCycle.b = typedArray.getResourceId(index, keyCycle.b);
                            break;
                        }
                    case 2:
                        keyCycle.f939a = typedArray.getInt(index, keyCycle.f939a);
                        break;
                    case 3:
                        keyCycle.e = typedArray.getString(index);
                        break;
                    case 4:
                        keyCycle.f = typedArray.getInteger(index, keyCycle.f);
                        break;
                    case 5:
                        if (typedArray.peekValue(index).type == 3) {
                            keyCycle.h = typedArray.getString(index);
                            keyCycle.g = 7;
                            break;
                        } else {
                            keyCycle.g = typedArray.getInt(index, keyCycle.g);
                            break;
                        }
                    case 6:
                        keyCycle.i = typedArray.getFloat(index, keyCycle.i);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            keyCycle.j = typedArray.getDimension(index, keyCycle.j);
                            break;
                        } else {
                            keyCycle.j = typedArray.getFloat(index, keyCycle.j);
                            break;
                        }
                    case 8:
                        keyCycle.m = typedArray.getInt(index, keyCycle.m);
                        break;
                    case 9:
                        keyCycle.n = typedArray.getFloat(index, keyCycle.n);
                        break;
                    case 10:
                        keyCycle.o = typedArray.getDimension(index, keyCycle.o);
                        break;
                    case 11:
                        keyCycle.p = typedArray.getFloat(index, keyCycle.p);
                        break;
                    case 12:
                        keyCycle.r = typedArray.getFloat(index, keyCycle.r);
                        break;
                    case 13:
                        keyCycle.s = typedArray.getFloat(index, keyCycle.s);
                        break;
                    case 14:
                        keyCycle.q = typedArray.getFloat(index, keyCycle.q);
                        break;
                    case 15:
                        keyCycle.t = typedArray.getFloat(index, keyCycle.t);
                        break;
                    case 16:
                        keyCycle.u = typedArray.getFloat(index, keyCycle.u);
                        break;
                    case 17:
                        keyCycle.v = typedArray.getDimension(index, keyCycle.v);
                        break;
                    case 18:
                        keyCycle.w = typedArray.getDimension(index, keyCycle.w);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            keyCycle.x = typedArray.getDimension(index, keyCycle.x);
                            break;
                        } else {
                            break;
                        }
                    case 20:
                        keyCycle.l = typedArray.getFloat(index, keyCycle.l);
                        break;
                    case 21:
                        keyCycle.k = typedArray.getFloat(index, keyCycle.k) / 360.0f;
                        break;
                    default:
                        Log.e(TypedValues.CycleType.NAME, "unused attribute 0x" + Integer.toHexString(index) + "   " + f941a.get(index));
                        break;
                }
            }
        }
    }

    public KeyCycle() {
        this.mType = 4;
        this.d = new HashMap<>();
    }

    public void addCycleValues(HashMap<String, ViewOscillator> hashMap) {
        ViewOscillator viewOscillator;
        ViewOscillator viewOscillator2;
        for (String str : hashMap.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.d.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.getType() == ConstraintAttribute.AttributeType.FLOAT_TYPE && (viewOscillator = hashMap.get(str)) != null) {
                    viewOscillator.setPoint(this.f939a, this.g, this.h, this.m, this.i, this.j, this.k, constraintAttribute.getValueToInterpolate(), constraintAttribute);
                }
            } else {
                float value = getValue(str);
                if (!Float.isNaN(value) && (viewOscillator2 = hashMap.get(str)) != null) {
                    viewOscillator2.setPoint(this.f939a, this.g, this.h, this.m, this.i, this.j, this.k, value);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, ViewSpline> hashMap) {
        Debug.logStack(TypedValues.CycleType.NAME, "add " + hashMap.size() + " values", 2);
        for (String str : hashMap.keySet()) {
            ViewSpline viewSpline = hashMap.get(str);
            if (viewSpline != null) {
                str.hashCode();
                char c = 65535;
                switch (str.hashCode()) {
                    case -1249320806:
                        if (str.equals("rotationX")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1249320805:
                        if (str.equals("rotationY")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1225497657:
                        if (str.equals("translationX")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1225497656:
                        if (str.equals("translationY")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1225497655:
                        if (str.equals("translationZ")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1001078227:
                        if (str.equals("progress")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -908189618:
                        if (str.equals("scaleX")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -908189617:
                        if (str.equals("scaleY")) {
                            c = 7;
                            break;
                        }
                        break;
                    case -40300674:
                        if (str.equals(Key.ROTATION)) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -4379043:
                        if (str.equals("elevation")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case 37232917:
                        if (str.equals("transitionPathRotate")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case 92909918:
                        if (str.equals("alpha")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 156108012:
                        if (str.equals("waveOffset")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case 1530034690:
                        if (str.equals("wavePhase")) {
                            c = '\r';
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        viewSpline.setPoint(this.f939a, this.r);
                        continue;
                    case 1:
                        viewSpline.setPoint(this.f939a, this.s);
                        continue;
                    case 2:
                        viewSpline.setPoint(this.f939a, this.v);
                        continue;
                    case 3:
                        viewSpline.setPoint(this.f939a, this.w);
                        continue;
                    case 4:
                        viewSpline.setPoint(this.f939a, this.x);
                        continue;
                    case 5:
                        viewSpline.setPoint(this.f939a, this.l);
                        continue;
                    case 6:
                        viewSpline.setPoint(this.f939a, this.t);
                        continue;
                    case 7:
                        viewSpline.setPoint(this.f939a, this.u);
                        continue;
                    case '\b':
                        viewSpline.setPoint(this.f939a, this.p);
                        continue;
                    case '\t':
                        viewSpline.setPoint(this.f939a, this.o);
                        continue;
                    case '\n':
                        viewSpline.setPoint(this.f939a, this.q);
                        continue;
                    case 11:
                        viewSpline.setPoint(this.f939a, this.n);
                        continue;
                    case '\f':
                        viewSpline.setPoint(this.f939a, this.j);
                        continue;
                    case '\r':
                        viewSpline.setPoint(this.f939a, this.k);
                        continue;
                    default:
                        if (!str.startsWith("CUSTOM")) {
                            Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                            break;
                        } else {
                            continue;
                        }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key) {
        super.copy(key);
        KeyCycle keyCycle = (KeyCycle) key;
        this.e = keyCycle.e;
        this.f = keyCycle.f;
        this.g = keyCycle.g;
        this.h = keyCycle.h;
        this.i = keyCycle.i;
        this.j = keyCycle.j;
        this.k = keyCycle.k;
        this.l = keyCycle.l;
        this.m = keyCycle.m;
        this.n = keyCycle.n;
        this.o = keyCycle.o;
        this.p = keyCycle.p;
        this.q = keyCycle.q;
        this.r = keyCycle.r;
        this.s = keyCycle.s;
        this.t = keyCycle.t;
        this.u = keyCycle.u;
        this.v = keyCycle.v;
        this.w = keyCycle.w;
        this.x = keyCycle.x;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.n)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.w)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.x)) {
            hashSet.add("translationZ");
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    public float getValue(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1249320806:
                if (str.equals("rotationX")) {
                    c = 0;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c = 1;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 2;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 3;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 4;
                    break;
                }
                break;
            case -1001078227:
                if (str.equals("progress")) {
                    c = 5;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 6;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c = 7;
                    break;
                }
                break;
            case -40300674:
                if (str.equals(Key.ROTATION)) {
                    c = '\b';
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c = '\t';
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c = '\n';
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c = 11;
                    break;
                }
                break;
            case 156108012:
                if (str.equals("waveOffset")) {
                    c = '\f';
                    break;
                }
                break;
            case 1530034690:
                if (str.equals("wavePhase")) {
                    c = '\r';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return this.r;
            case 1:
                return this.s;
            case 2:
                return this.v;
            case 3:
                return this.w;
            case 4:
                return this.x;
            case 5:
                return this.l;
            case 6:
                return this.t;
            case 7:
                return this.u;
            case '\b':
                return this.p;
            case '\t':
                return this.o;
            case '\n':
                return this.q;
            case 11:
                return this.n;
            case '\f':
                return this.j;
            case '\r':
                return this.k;
            default:
                if (str.startsWith("CUSTOM")) {
                    return Float.NaN;
                }
                Log.v("WARNING! KeyCycle", "  UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyCycle));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1913008125:
                if (str.equals(Key.MOTIONPROGRESS)) {
                    c = 0;
                    break;
                }
                break;
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c = 1;
                    break;
                }
                break;
            case -1249320806:
                if (str.equals("rotationX")) {
                    c = 2;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c = 3;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 4;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 5;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 6;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 7;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c = '\b';
                    break;
                }
                break;
            case -40300674:
                if (str.equals(Key.ROTATION)) {
                    c = '\t';
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c = '\n';
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c = 11;
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c = '\f';
                    break;
                }
                break;
            case 156108012:
                if (str.equals("waveOffset")) {
                    c = '\r';
                    break;
                }
                break;
            case 184161818:
                if (str.equals("wavePeriod")) {
                    c = 14;
                    break;
                }
                break;
            case 579057826:
                if (str.equals("curveFit")) {
                    c = 15;
                    break;
                }
                break;
            case 1530034690:
                if (str.equals("wavePhase")) {
                    c = 16;
                    break;
                }
                break;
            case 1532805160:
                if (str.equals("waveShape")) {
                    c = 17;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.l = c(obj);
                return;
            case 1:
                this.e = obj.toString();
                return;
            case 2:
                this.r = c(obj);
                return;
            case 3:
                this.s = c(obj);
                return;
            case 4:
                this.v = c(obj);
                return;
            case 5:
                this.w = c(obj);
                return;
            case 6:
                this.x = c(obj);
                return;
            case 7:
                this.t = c(obj);
                return;
            case '\b':
                this.u = c(obj);
                return;
            case '\t':
                this.p = c(obj);
                return;
            case '\n':
                this.o = c(obj);
                return;
            case 11:
                this.q = c(obj);
                return;
            case '\f':
                this.n = c(obj);
                return;
            case '\r':
                this.j = c(obj);
                return;
            case 14:
                this.i = c(obj);
                return;
            case 15:
                this.f = d(obj);
                return;
            case 16:
                this.k = c(obj);
                return;
            case 17:
                if (obj instanceof Integer) {
                    this.g = d(obj);
                    return;
                }
                this.g = 7;
                this.h = obj.toString();
                return;
            default:
                return;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo7clone() {
        return new KeyCycle().copy(this);
    }
}
