package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MotionKeyTimeCycle extends MotionKey {
    public static final int KEY_TYPE = 3;
    public String c;
    public int d = -1;
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
    public int q = 0;
    public float r = Float.NaN;
    public float s = 0.0f;

    public MotionKeyTimeCycle() {
        this.mType = 3;
        this.mCustom = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0089, code lost:
        if (r1.equals("scaleX") == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addTimeValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r11) {
        /*
            Method dump skipped, instructions count: 604
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle.addTimeValues(java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.e)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.g)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.h)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.i)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("translationZ");
        }
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return TypedValues.CycleType.getId(str);
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        if (i == 100) {
            this.mFramePosition = i2;
            return true;
        } else if (i != 421) {
            return super.setValue(i, i2);
        } else {
            this.q = i2;
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: clone */
    public MotionKey mo6clone() {
        return new MotionKeyTimeCycle().copy((MotionKey) this);
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKeyTimeCycle copy(MotionKey motionKey) {
        super.copy(motionKey);
        MotionKeyTimeCycle motionKeyTimeCycle = (MotionKeyTimeCycle) motionKey;
        this.c = motionKeyTimeCycle.c;
        this.d = motionKeyTimeCycle.d;
        this.q = motionKeyTimeCycle.q;
        this.r = motionKeyTimeCycle.r;
        this.s = motionKeyTimeCycle.s;
        this.p = motionKeyTimeCycle.p;
        this.e = motionKeyTimeCycle.e;
        this.f = motionKeyTimeCycle.f;
        this.g = motionKeyTimeCycle.g;
        this.j = motionKeyTimeCycle.j;
        this.h = motionKeyTimeCycle.h;
        this.i = motionKeyTimeCycle.i;
        this.k = motionKeyTimeCycle.k;
        this.l = motionKeyTimeCycle.l;
        this.m = motionKeyTimeCycle.m;
        this.n = motionKeyTimeCycle.n;
        this.o = motionKeyTimeCycle.o;
        return this;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        if (i == 315) {
            this.p = a(Float.valueOf(f));
            return true;
        } else if (i == 401) {
            this.d = b(Float.valueOf(f));
            return true;
        } else if (i == 403) {
            this.e = f;
            return true;
        } else if (i == 416) {
            this.j = a(Float.valueOf(f));
            return true;
        } else if (i == 423) {
            this.r = a(Float.valueOf(f));
            return true;
        } else if (i != 424) {
            switch (i) {
                case 304:
                    this.m = a(Float.valueOf(f));
                    return true;
                case 305:
                    this.n = a(Float.valueOf(f));
                    return true;
                case 306:
                    this.o = a(Float.valueOf(f));
                    return true;
                case 307:
                    this.f = a(Float.valueOf(f));
                    return true;
                case 308:
                    this.h = a(Float.valueOf(f));
                    return true;
                case 309:
                    this.i = a(Float.valueOf(f));
                    return true;
                case 310:
                    this.g = a(Float.valueOf(f));
                    return true;
                case 311:
                    this.k = a(Float.valueOf(f));
                    return true;
                case 312:
                    this.l = a(Float.valueOf(f));
                    return true;
                default:
                    return super.setValue(i, f);
            }
        } else {
            this.s = a(Float.valueOf(f));
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (i == 420) {
            this.c = str;
            return true;
        } else if (i != 421) {
            return super.setValue(i, str);
        } else {
            this.q = 7;
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, boolean z) {
        return super.setValue(i, z);
    }
}
