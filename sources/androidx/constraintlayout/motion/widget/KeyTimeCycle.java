package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class KeyTimeCycle extends Key {
    public static final int KEY_TYPE = 3;
    public static final int SHAPE_BOUNCE = 6;
    public static final int SHAPE_COS_WAVE = 5;
    public static final int SHAPE_REVERSE_SAW_WAVE = 4;
    public static final int SHAPE_SAW_WAVE = 3;
    public static final int SHAPE_SIN_WAVE = 0;
    public static final int SHAPE_SQUARE_WAVE = 1;
    public static final int SHAPE_TRIANGLE_WAVE = 2;
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_SHAPE = "waveShape";
    public String e;
    public String t;
    public int f = -1;
    public float g = Float.NaN;
    public float h = Float.NaN;
    public float i = Float.NaN;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public int s = 0;
    public float u = Float.NaN;
    public float v = 0.0f;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f944a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f944a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTimeCycle_android_alpha, 1);
            f944a.append(R.styleable.KeyTimeCycle_android_elevation, 2);
            f944a.append(R.styleable.KeyTimeCycle_android_rotation, 4);
            f944a.append(R.styleable.KeyTimeCycle_android_rotationX, 5);
            f944a.append(R.styleable.KeyTimeCycle_android_rotationY, 6);
            f944a.append(R.styleable.KeyTimeCycle_android_scaleX, 7);
            f944a.append(R.styleable.KeyTimeCycle_transitionPathRotate, 8);
            f944a.append(R.styleable.KeyTimeCycle_transitionEasing, 9);
            f944a.append(R.styleable.KeyTimeCycle_motionTarget, 10);
            f944a.append(R.styleable.KeyTimeCycle_framePosition, 12);
            f944a.append(R.styleable.KeyTimeCycle_curveFit, 13);
            f944a.append(R.styleable.KeyTimeCycle_android_scaleY, 14);
            f944a.append(R.styleable.KeyTimeCycle_android_translationX, 15);
            f944a.append(R.styleable.KeyTimeCycle_android_translationY, 16);
            f944a.append(R.styleable.KeyTimeCycle_android_translationZ, 17);
            f944a.append(R.styleable.KeyTimeCycle_motionProgress, 18);
            f944a.append(R.styleable.KeyTimeCycle_wavePeriod, 20);
            f944a.append(R.styleable.KeyTimeCycle_waveOffset, 21);
            f944a.append(R.styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void a(KeyTimeCycle keyTimeCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f944a.get(index)) {
                    case 1:
                        keyTimeCycle.g = typedArray.getFloat(index, keyTimeCycle.g);
                        break;
                    case 2:
                        keyTimeCycle.h = typedArray.getDimension(index, keyTimeCycle.h);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f944a.get(index));
                        break;
                    case 4:
                        keyTimeCycle.i = typedArray.getFloat(index, keyTimeCycle.i);
                        break;
                    case 5:
                        keyTimeCycle.j = typedArray.getFloat(index, keyTimeCycle.j);
                        break;
                    case 6:
                        keyTimeCycle.k = typedArray.getFloat(index, keyTimeCycle.k);
                        break;
                    case 7:
                        keyTimeCycle.m = typedArray.getFloat(index, keyTimeCycle.m);
                        break;
                    case 8:
                        keyTimeCycle.l = typedArray.getFloat(index, keyTimeCycle.l);
                        break;
                    case 9:
                        keyTimeCycle.e = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyTimeCycle.b);
                            keyTimeCycle.b = resourceId;
                            if (resourceId == -1) {
                                keyTimeCycle.c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyTimeCycle.c = typedArray.getString(index);
                            break;
                        } else {
                            keyTimeCycle.b = typedArray.getResourceId(index, keyTimeCycle.b);
                            break;
                        }
                    case 12:
                        keyTimeCycle.f939a = typedArray.getInt(index, keyTimeCycle.f939a);
                        break;
                    case 13:
                        keyTimeCycle.f = typedArray.getInteger(index, keyTimeCycle.f);
                        break;
                    case 14:
                        keyTimeCycle.n = typedArray.getFloat(index, keyTimeCycle.n);
                        break;
                    case 15:
                        keyTimeCycle.o = typedArray.getDimension(index, keyTimeCycle.o);
                        break;
                    case 16:
                        keyTimeCycle.p = typedArray.getDimension(index, keyTimeCycle.p);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            keyTimeCycle.q = typedArray.getDimension(index, keyTimeCycle.q);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        keyTimeCycle.r = typedArray.getFloat(index, keyTimeCycle.r);
                        break;
                    case 19:
                        if (typedArray.peekValue(index).type == 3) {
                            keyTimeCycle.t = typedArray.getString(index);
                            keyTimeCycle.s = 7;
                            break;
                        } else {
                            keyTimeCycle.s = typedArray.getInt(index, keyTimeCycle.s);
                            break;
                        }
                    case 20:
                        keyTimeCycle.u = typedArray.getFloat(index, keyTimeCycle.u);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            keyTimeCycle.v = typedArray.getDimension(index, keyTimeCycle.v);
                            break;
                        } else {
                            keyTimeCycle.v = typedArray.getFloat(index, keyTimeCycle.v);
                            break;
                        }
                }
            }
        }
    }

    public KeyTimeCycle() {
        this.mType = 3;
        this.d = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0089, code lost:
        if (r1.equals("scaleY") == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addTimeValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r11) {
        /*
            Method dump skipped, instructions count: 604
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTimeCycle.addTimeValues(java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, ViewSpline> hashMap) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key) {
        super.copy(key);
        KeyTimeCycle keyTimeCycle = (KeyTimeCycle) key;
        this.e = keyTimeCycle.e;
        this.f = keyTimeCycle.f;
        this.s = keyTimeCycle.s;
        this.u = keyTimeCycle.u;
        this.v = keyTimeCycle.v;
        this.r = keyTimeCycle.r;
        this.g = keyTimeCycle.g;
        this.h = keyTimeCycle.h;
        this.i = keyTimeCycle.i;
        this.l = keyTimeCycle.l;
        this.j = keyTimeCycle.j;
        this.k = keyTimeCycle.k;
        this.m = keyTimeCycle.m;
        this.n = keyTimeCycle.n;
        this.o = keyTimeCycle.o;
        this.p = keyTimeCycle.p;
        this.q = keyTimeCycle.q;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.g)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.h)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.i)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("progress");
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTimeCycle));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.f == -1) {
            return;
        }
        if (!Float.isNaN(this.g)) {
            hashMap.put("alpha", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.h)) {
            hashMap.put("elevation", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.i)) {
            hashMap.put(Key.ROTATION, Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.j)) {
            hashMap.put("rotationX", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.k)) {
            hashMap.put("rotationY", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.o)) {
            hashMap.put("translationX", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.p)) {
            hashMap.put("translationY", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.q)) {
            hashMap.put("translationZ", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.l)) {
            hashMap.put("transitionPathRotate", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.m)) {
            hashMap.put("scaleX", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.m)) {
            hashMap.put("scaleY", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.r)) {
            hashMap.put("progress", Integer.valueOf(this.f));
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + it.next(), Integer.valueOf(this.f));
            }
        }
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
            case 1532805160:
                if (str.equals("waveShape")) {
                    c = 16;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.r = c(obj);
                return;
            case 1:
                this.e = obj.toString();
                return;
            case 2:
                this.j = c(obj);
                return;
            case 3:
                this.k = c(obj);
                return;
            case 4:
                this.o = c(obj);
                return;
            case 5:
                this.p = c(obj);
                return;
            case 6:
                this.q = c(obj);
                return;
            case 7:
                this.m = c(obj);
                return;
            case '\b':
                this.n = c(obj);
                return;
            case '\t':
                this.i = c(obj);
                return;
            case '\n':
                this.h = c(obj);
                return;
            case 11:
                this.l = c(obj);
                return;
            case '\f':
                this.g = c(obj);
                return;
            case '\r':
                this.v = c(obj);
                return;
            case 14:
                this.u = c(obj);
                return;
            case 15:
                this.f = d(obj);
                return;
            case 16:
                if (obj instanceof Integer) {
                    this.s = d(obj);
                    return;
                }
                this.s = 7;
                obj.toString();
                return;
            default:
                return;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo7clone() {
        return new KeyTimeCycle().copy(this);
    }
}
