package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MotionKeyAttributes extends MotionKey {
    public static final int KEY_TYPE = 1;
    public int c = -1;
    public float d = Float.NaN;
    public float e = Float.NaN;
    public float f = Float.NaN;
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

    public MotionKeyAttributes() {
        this.mType = 1;
        this.mCustom = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
        if (r1.equals("pivotX") == false) goto L12;
     */
    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r7) {
        /*
            Method dump skipped, instructions count: 594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.key.MotionKeyAttributes.addValues(java.util.HashMap):void");
    }

    public final float c(int i) {
        if (i != 100) {
            switch (i) {
                case 303:
                    return this.d;
                case 304:
                    return this.n;
                case 305:
                    return this.o;
                case 306:
                    return this.p;
                case 307:
                    return this.e;
                case 308:
                    return this.g;
                case 309:
                    return this.h;
                case 310:
                    return this.f;
                case 311:
                    return this.l;
                case 312:
                    return this.m;
                case 313:
                    return this.i;
                case 314:
                    return this.j;
                case 315:
                    return this.q;
                case 316:
                    return this.k;
                default:
                    return Float.NaN;
            }
        }
        return this.mFramePosition;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: clone */
    public MotionKey mo6clone() {
        return null;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.d)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.e)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.g)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.h)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.i)) {
            hashSet.add("pivotX");
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add("pivotY");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("progress");
        }
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    public int getCurveFit() {
        return this.c;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return TypedValues.AttributesType.getId(str);
    }

    public void printAttributes() {
        HashSet<String> hashSet = new HashSet<>();
        getAttributeNames(hashSet);
        PrintStream printStream = System.out;
        printStream.println(" ------------- " + this.mFramePosition + " -------------");
        String[] strArr = (String[]) hashSet.toArray(new String[0]);
        for (int i = 0; i < strArr.length; i++) {
            int id = TypedValues.AttributesType.getId(strArr[i]);
            PrintStream printStream2 = System.out;
            printStream2.println(strArr[i] + ":" + c(id));
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (!Float.isNaN(this.d)) {
            hashMap.put("alpha", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.e)) {
            hashMap.put("elevation", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.f)) {
            hashMap.put("rotationZ", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.g)) {
            hashMap.put("rotationX", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.h)) {
            hashMap.put("rotationY", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.i)) {
            hashMap.put("pivotX", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.j)) {
            hashMap.put("pivotY", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.n)) {
            hashMap.put("translationX", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.o)) {
            hashMap.put("translationY", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.p)) {
            hashMap.put("translationZ", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.k)) {
            hashMap.put("pathRotate", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.l)) {
            hashMap.put("scaleX", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.m)) {
            hashMap.put("scaleY", Integer.valueOf(this.c));
        }
        if (!Float.isNaN(this.q)) {
            hashMap.put("progress", Integer.valueOf(this.c));
        }
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + it.next(), Integer.valueOf(this.c));
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        if (i == 100) {
            this.mFramePosition = i2;
            return true;
        } else if (i != 301) {
            if (i == 302 || setValue(i, i2)) {
                return true;
            }
            return super.setValue(i, i2);
        } else {
            this.c = i2;
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        if (i != 100) {
            switch (i) {
                case 303:
                    this.d = f;
                    return true;
                case 304:
                    this.n = f;
                    return true;
                case 305:
                    this.o = f;
                    return true;
                case 306:
                    this.p = f;
                    return true;
                case 307:
                    this.e = f;
                    return true;
                case 308:
                    this.g = f;
                    return true;
                case 309:
                    this.h = f;
                    return true;
                case 310:
                    this.f = f;
                    return true;
                case 311:
                    this.l = f;
                    return true;
                case 312:
                    this.m = f;
                    return true;
                case 313:
                    this.i = f;
                    return true;
                case 314:
                    this.j = f;
                    return true;
                case 315:
                    this.q = f;
                    return true;
                case 316:
                    this.k = f;
                    return true;
                default:
                    return super.setValue(i, f);
            }
        }
        this.k = f;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (i == 101) {
            this.b = str;
            return true;
        } else if (i != 317) {
            return super.setValue(i, str);
        } else {
            return true;
        }
    }
}
