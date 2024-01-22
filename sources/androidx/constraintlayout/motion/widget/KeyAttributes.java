package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class KeyAttributes extends Key {
    public static final int KEY_TYPE = 1;
    public String e;
    public int f = -1;
    public boolean g = false;
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
    public float s = Float.NaN;
    public float t = Float.NaN;
    public float u = Float.NaN;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f940a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f940a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyAttribute_android_alpha, 1);
            f940a.append(R.styleable.KeyAttribute_android_elevation, 2);
            f940a.append(R.styleable.KeyAttribute_android_rotation, 4);
            f940a.append(R.styleable.KeyAttribute_android_rotationX, 5);
            f940a.append(R.styleable.KeyAttribute_android_rotationY, 6);
            f940a.append(R.styleable.KeyAttribute_android_transformPivotX, 19);
            f940a.append(R.styleable.KeyAttribute_android_transformPivotY, 20);
            f940a.append(R.styleable.KeyAttribute_android_scaleX, 7);
            f940a.append(R.styleable.KeyAttribute_transitionPathRotate, 8);
            f940a.append(R.styleable.KeyAttribute_transitionEasing, 9);
            f940a.append(R.styleable.KeyAttribute_motionTarget, 10);
            f940a.append(R.styleable.KeyAttribute_framePosition, 12);
            f940a.append(R.styleable.KeyAttribute_curveFit, 13);
            f940a.append(R.styleable.KeyAttribute_android_scaleY, 14);
            f940a.append(R.styleable.KeyAttribute_android_translationX, 15);
            f940a.append(R.styleable.KeyAttribute_android_translationY, 16);
            f940a.append(R.styleable.KeyAttribute_android_translationZ, 17);
            f940a.append(R.styleable.KeyAttribute_motionProgress, 18);
        }

        public static void a(KeyAttributes keyAttributes, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f940a.get(index)) {
                    case 1:
                        keyAttributes.h = typedArray.getFloat(index, keyAttributes.h);
                        break;
                    case 2:
                        keyAttributes.i = typedArray.getDimension(index, keyAttributes.i);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f940a.get(index));
                        break;
                    case 4:
                        keyAttributes.j = typedArray.getFloat(index, keyAttributes.j);
                        break;
                    case 5:
                        keyAttributes.k = typedArray.getFloat(index, keyAttributes.k);
                        break;
                    case 6:
                        keyAttributes.l = typedArray.getFloat(index, keyAttributes.l);
                        break;
                    case 7:
                        keyAttributes.p = typedArray.getFloat(index, keyAttributes.p);
                        break;
                    case 8:
                        keyAttributes.o = typedArray.getFloat(index, keyAttributes.o);
                        break;
                    case 9:
                        keyAttributes.e = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyAttributes.b);
                            keyAttributes.b = resourceId;
                            if (resourceId == -1) {
                                keyAttributes.c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyAttributes.c = typedArray.getString(index);
                            break;
                        } else {
                            keyAttributes.b = typedArray.getResourceId(index, keyAttributes.b);
                            break;
                        }
                    case 12:
                        keyAttributes.f939a = typedArray.getInt(index, keyAttributes.f939a);
                        break;
                    case 13:
                        keyAttributes.f = typedArray.getInteger(index, keyAttributes.f);
                        break;
                    case 14:
                        keyAttributes.q = typedArray.getFloat(index, keyAttributes.q);
                        break;
                    case 15:
                        keyAttributes.r = typedArray.getDimension(index, keyAttributes.r);
                        break;
                    case 16:
                        keyAttributes.s = typedArray.getDimension(index, keyAttributes.s);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            keyAttributes.t = typedArray.getDimension(index, keyAttributes.t);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        keyAttributes.u = typedArray.getFloat(index, keyAttributes.u);
                        break;
                    case 19:
                        keyAttributes.m = typedArray.getDimension(index, keyAttributes.m);
                        break;
                    case 20:
                        keyAttributes.n = typedArray.getDimension(index, keyAttributes.n);
                        break;
                }
            }
        }
    }

    public KeyAttributes() {
        this.mType = 1;
        this.d = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
        if (r1.equals("scaleY") == false) goto L12;
     */
    @Override // androidx.constraintlayout.motion.widget.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7) {
        /*
            Method dump skipped, instructions count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.addValues(java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key) {
        super.copy(key);
        KeyAttributes keyAttributes = (KeyAttributes) key;
        this.f = keyAttributes.f;
        this.g = keyAttributes.g;
        this.h = keyAttributes.h;
        this.i = keyAttributes.i;
        this.j = keyAttributes.j;
        this.k = keyAttributes.k;
        this.l = keyAttributes.l;
        this.m = keyAttributes.m;
        this.n = keyAttributes.n;
        this.o = keyAttributes.o;
        this.p = keyAttributes.p;
        this.q = keyAttributes.q;
        this.r = keyAttributes.r;
        this.s = keyAttributes.s;
        this.t = keyAttributes.t;
        this.u = keyAttributes.u;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.h)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.i)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add(Key.PIVOT_X);
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add(Key.PIVOT_Y);
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.u)) {
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
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyAttribute));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.f == -1) {
            return;
        }
        if (!Float.isNaN(this.h)) {
            hashMap.put("alpha", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.i)) {
            hashMap.put("elevation", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.j)) {
            hashMap.put(Key.ROTATION, Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.k)) {
            hashMap.put("rotationX", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.l)) {
            hashMap.put("rotationY", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.m)) {
            hashMap.put(Key.PIVOT_X, Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.n)) {
            hashMap.put(Key.PIVOT_Y, Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.r)) {
            hashMap.put("translationX", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.s)) {
            hashMap.put("translationY", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.t)) {
            hashMap.put("translationZ", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.o)) {
            hashMap.put("transitionPathRotate", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.p)) {
            hashMap.put("scaleX", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.q)) {
            hashMap.put("scaleY", Integer.valueOf(this.f));
        }
        if (!Float.isNaN(this.u)) {
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
            case -760884510:
                if (str.equals(Key.PIVOT_X)) {
                    c = '\t';
                    break;
                }
                break;
            case -760884509:
                if (str.equals(Key.PIVOT_Y)) {
                    c = '\n';
                    break;
                }
                break;
            case -40300674:
                if (str.equals(Key.ROTATION)) {
                    c = 11;
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c = '\f';
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c = '\r';
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
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
            case 1941332754:
                if (str.equals("visibility")) {
                    c = 16;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.u = c(obj);
                return;
            case 1:
                obj.toString();
                return;
            case 2:
                this.k = c(obj);
                return;
            case 3:
                this.l = c(obj);
                return;
            case 4:
                this.r = c(obj);
                return;
            case 5:
                this.s = c(obj);
                return;
            case 6:
                this.t = c(obj);
                return;
            case 7:
                this.p = c(obj);
                return;
            case '\b':
                this.q = c(obj);
                return;
            case '\t':
                this.m = c(obj);
                return;
            case '\n':
                this.n = c(obj);
                return;
            case 11:
                this.j = c(obj);
                return;
            case '\f':
                this.i = c(obj);
                return;
            case '\r':
                this.o = c(obj);
                return;
            case 14:
                this.h = c(obj);
                return;
            case 15:
                this.f = d(obj);
                return;
            case 16:
                this.g = b(obj);
                return;
            default:
                return;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo7clone() {
        return new KeyAttributes().copy(this);
    }
}
