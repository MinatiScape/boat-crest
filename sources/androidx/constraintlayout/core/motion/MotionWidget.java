package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Set;
/* loaded from: classes.dex */
public class MotionWidget implements TypedValues {
    public static final int FILL_PARENT = -1;
    public static final int GONE_UNSET = Integer.MIN_VALUE;
    public static final int INVISIBLE = 0;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int MATCH_PARENT = -1;
    public static final int PARENT_ID = 0;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    public static final int UNSET = -1;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 4;
    public static final int WRAP_CONTENT = -2;

    /* renamed from: a  reason: collision with root package name */
    public WidgetFrame f863a;
    public Motion b;
    public PropertySet c;
    public float d;
    public float e;

    /* loaded from: classes.dex */
    public static class Motion {
        public int mAnimateRelativeTo = -1;
        public int mAnimateCircleAngleTo = 0;
        public String mTransitionEasing = null;
        public int mPathMotionArc = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPolarRelativeTo = -1;
        public float mPathRotate = Float.NaN;
        public float mQuantizeMotionPhase = Float.NaN;
        public int mQuantizeMotionSteps = -1;
        public String mQuantizeInterpolatorString = null;
        public int mQuantizeInterpolatorType = -3;
        public int mQuantizeInterpolatorID = -1;
    }

    /* loaded from: classes.dex */
    public static class PropertySet {
        public int visibility = 4;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;
    }

    public MotionWidget() {
        this.f863a = new WidgetFrame();
        this.b = new Motion();
        this.c = new PropertySet();
    }

    public MotionWidget findViewById(int i) {
        return null;
    }

    public float getAlpha() {
        return this.c.alpha;
    }

    public int getBottom() {
        return this.f863a.bottom;
    }

    public CustomVariable getCustomAttribute(String str) {
        return this.f863a.getCustomAttribute(str);
    }

    public Set<String> getCustomAttributeNames() {
        return this.f863a.getCustomAttributeNames();
    }

    public int getHeight() {
        WidgetFrame widgetFrame = this.f863a;
        return widgetFrame.bottom - widgetFrame.top;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        int id = TypedValues.AttributesType.getId(str);
        return id != -1 ? id : TypedValues.MotionType.getId(str);
    }

    public int getLeft() {
        return this.f863a.left;
    }

    public String getName() {
        return this.f863a.getId();
    }

    public MotionWidget getParent() {
        return null;
    }

    public float getPivotX() {
        return this.f863a.pivotX;
    }

    public float getPivotY() {
        return this.f863a.pivotY;
    }

    public int getRight() {
        return this.f863a.right;
    }

    public float getRotationX() {
        return this.f863a.rotationX;
    }

    public float getRotationY() {
        return this.f863a.rotationY;
    }

    public float getRotationZ() {
        return this.f863a.rotationZ;
    }

    public float getScaleX() {
        return this.f863a.scaleX;
    }

    public float getScaleY() {
        return this.f863a.scaleY;
    }

    public int getTop() {
        return this.f863a.top;
    }

    public float getTranslationX() {
        return this.f863a.translationX;
    }

    public float getTranslationY() {
        return this.f863a.translationY;
    }

    public float getTranslationZ() {
        return this.f863a.translationZ;
    }

    public float getValueAttributes(int i) {
        switch (i) {
            case 303:
                return this.f863a.alpha;
            case 304:
                return this.f863a.translationX;
            case 305:
                return this.f863a.translationY;
            case 306:
                return this.f863a.translationZ;
            case 307:
            default:
                return Float.NaN;
            case 308:
                return this.f863a.rotationX;
            case 309:
                return this.f863a.rotationY;
            case 310:
                return this.f863a.rotationZ;
            case 311:
                return this.f863a.scaleX;
            case 312:
                return this.f863a.scaleY;
            case 313:
                return this.f863a.pivotX;
            case 314:
                return this.f863a.pivotY;
            case 315:
                return this.d;
            case 316:
                return this.e;
        }
    }

    public int getVisibility() {
        return this.c.visibility;
    }

    public WidgetFrame getWidgetFrame() {
        return this.f863a;
    }

    public int getWidth() {
        WidgetFrame widgetFrame = this.f863a;
        return widgetFrame.right - widgetFrame.left;
    }

    public int getX() {
        return this.f863a.left;
    }

    public int getY() {
        return this.f863a.top;
    }

    public void layout(int i, int i2, int i3, int i4) {
        setBounds(i, i2, i3, i4);
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        if (this.f863a == null) {
            this.f863a = new WidgetFrame((ConstraintWidget) null);
        }
        WidgetFrame widgetFrame = this.f863a;
        widgetFrame.top = i2;
        widgetFrame.left = i;
        widgetFrame.right = i3;
        widgetFrame.bottom = i4;
    }

    public void setCustomAttribute(String str, int i, float f) {
        this.f863a.setCustomAttribute(str, i, f);
    }

    public void setInterpolatedValue(CustomAttribute customAttribute, float[] fArr) {
        this.f863a.setCustomAttribute(customAttribute.f858a, TypedValues.Custom.TYPE_FLOAT, fArr[0]);
    }

    public void setPivotX(float f) {
        this.f863a.pivotX = f;
    }

    public void setPivotY(float f) {
        this.f863a.pivotY = f;
    }

    public void setRotationX(float f) {
        this.f863a.rotationX = f;
    }

    public void setRotationY(float f) {
        this.f863a.rotationY = f;
    }

    public void setRotationZ(float f) {
        this.f863a.rotationZ = f;
    }

    public void setScaleX(float f) {
        this.f863a.scaleX = f;
    }

    public void setScaleY(float f) {
        this.f863a.scaleY = f;
    }

    public void setTranslationX(float f) {
        this.f863a.translationX = f;
    }

    public void setTranslationY(float f) {
        this.f863a.translationY = f;
    }

    public void setTranslationZ(float f) {
        this.f863a.translationZ = f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        return setValueAttributes(i, i2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, boolean z) {
        return false;
    }

    public boolean setValueAttributes(int i, float f) {
        switch (i) {
            case 303:
                this.f863a.alpha = f;
                return true;
            case 304:
                this.f863a.translationX = f;
                return true;
            case 305:
                this.f863a.translationY = f;
                return true;
            case 306:
                this.f863a.translationZ = f;
                return true;
            case 307:
            default:
                return false;
            case 308:
                this.f863a.rotationX = f;
                return true;
            case 309:
                this.f863a.rotationY = f;
                return true;
            case 310:
                this.f863a.rotationZ = f;
                return true;
            case 311:
                this.f863a.scaleX = f;
                return true;
            case 312:
                this.f863a.scaleY = f;
                return true;
            case 313:
                this.f863a.pivotX = f;
                return true;
            case 314:
                this.f863a.pivotY = f;
                return true;
            case 315:
                this.d = f;
                return true;
            case 316:
                this.e = f;
                return true;
        }
    }

    public boolean setValueMotion(int i, int i2) {
        switch (i) {
            case 605:
                this.b.mAnimateRelativeTo = i2;
                return true;
            case 606:
                this.b.mAnimateCircleAngleTo = i2;
                return true;
            case 607:
                this.b.mPathMotionArc = i2;
                return true;
            case 608:
                this.b.mDrawPath = i2;
                return true;
            case 609:
                this.b.mPolarRelativeTo = i2;
                return true;
            case 610:
                this.b.mQuantizeMotionSteps = i2;
                return true;
            case 611:
                this.b.mQuantizeInterpolatorType = i2;
                return true;
            case 612:
                this.b.mQuantizeInterpolatorID = i2;
                return true;
            default:
                return false;
        }
    }

    public void setVisibility(int i) {
        this.c.visibility = i;
    }

    public String toString() {
        return this.f863a.left + ", " + this.f863a.top + ", " + this.f863a.right + ", " + this.f863a.bottom;
    }

    public void setCustomAttribute(String str, int i, int i2) {
        this.f863a.setCustomAttribute(str, i, i2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        if (setValueAttributes(i, f)) {
            return true;
        }
        return setValueMotion(i, f);
    }

    public void setCustomAttribute(String str, int i, boolean z) {
        this.f863a.setCustomAttribute(str, i, z);
    }

    public void setCustomAttribute(String str, int i, String str2) {
        this.f863a.setCustomAttribute(str, i, str2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        return setValueMotion(i, str);
    }

    public MotionWidget(WidgetFrame widgetFrame) {
        this.f863a = new WidgetFrame();
        this.b = new Motion();
        this.c = new PropertySet();
        this.f863a = widgetFrame;
    }

    public boolean setValueMotion(int i, String str) {
        if (i == 603) {
            this.b.mTransitionEasing = str;
            return true;
        } else if (i != 604) {
            return false;
        } else {
            this.b.mQuantizeInterpolatorString = str;
            return true;
        }
    }

    public boolean setValueMotion(int i, float f) {
        switch (i) {
            case 600:
                this.b.mMotionStagger = f;
                return true;
            case 601:
                this.b.mPathRotate = f;
                return true;
            case 602:
                this.b.mQuantizeMotionPhase = f;
                return true;
            default:
                return false;
        }
    }
}
