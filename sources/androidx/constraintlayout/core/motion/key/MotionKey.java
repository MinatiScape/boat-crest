package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes.dex */
public abstract class MotionKey implements TypedValues {
    public static final String ALPHA = "alpha";
    public static final String CUSTOM = "CUSTOM";
    public static final String ELEVATION = "elevation";
    public static final String ROTATION = "rotationZ";
    public static final String ROTATION_X = "rotationX";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSITION_PATH_ROTATE = "transitionPathRotate";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";
    public static int UNSET = -1;
    public static final String VISIBILITY = "visibility";

    /* renamed from: a  reason: collision with root package name */
    public int f864a;
    public String b;
    public HashMap<String, CustomVariable> mCustom;
    public int mFramePosition;
    public int mType;

    public MotionKey() {
        int i = UNSET;
        this.mFramePosition = i;
        this.f864a = i;
        this.b = null;
    }

    public float a(Object obj) {
        return obj instanceof Float ? ((Float) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    public abstract void addValues(HashMap<String, SplineSet> hashMap);

    public int b(Object obj) {
        return obj instanceof Integer ? ((Integer) obj).intValue() : Integer.parseInt(obj.toString());
    }

    @Override // 
    /* renamed from: clone */
    public abstract MotionKey mo6clone();

    public MotionKey copy(MotionKey motionKey) {
        this.mFramePosition = motionKey.mFramePosition;
        this.f864a = motionKey.f864a;
        this.b = motionKey.b;
        this.mType = motionKey.mType;
        return this;
    }

    public abstract void getAttributeNames(HashSet<String> hashSet);

    public int getFramePosition() {
        return this.mFramePosition;
    }

    public void setCustomAttribute(String str, int i, float f) {
        this.mCustom.put(str, new CustomVariable(str, i, f));
    }

    public void setFramePosition(int i) {
        this.mFramePosition = i;
    }

    public void setInterpolation(HashMap<String, Integer> hashMap) {
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        if (i != 100) {
            return false;
        }
        this.mFramePosition = i2;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, boolean z) {
        return false;
    }

    public MotionKey setViewId(int i) {
        this.f864a = i;
        return this;
    }

    public void setCustomAttribute(String str, int i, int i2) {
        this.mCustom.put(str, new CustomVariable(str, i, i2));
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (i != 101) {
            return false;
        }
        this.b = str;
        return true;
    }

    public void setCustomAttribute(String str, int i, boolean z) {
        this.mCustom.put(str, new CustomVariable(str, i, z));
    }

    public void setCustomAttribute(String str, int i, String str2) {
        this.mCustom.put(str, new CustomVariable(str, i, str2));
    }
}
