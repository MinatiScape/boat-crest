package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.maps.style.layers.Property;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ConstraintSet {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int CIRCLE_REFERENCE = 8;
    public static final int END = 7;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_GUIDELINE = 0;
    public static final int INVISIBLE = 4;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int VERTICAL = 1;
    public static final int VERTICAL_GUIDELINE = 1;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 0;
    public static final int WRAP_CONTENT = -2;
    public static final int[] d = {0, 4, 8};
    public static SparseIntArray e = new SparseIntArray();
    public static SparseIntArray f = new SparseIntArray();
    public String mIdString;
    public String derivedState = "";
    public int mRotate = 0;

    /* renamed from: a  reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f982a = new HashMap<>();
    public boolean b = true;
    public HashMap<Integer, Constraint> c = new HashMap<>();

    /* loaded from: classes.dex */
    public static class Constraint {

        /* renamed from: a  reason: collision with root package name */
        public int f983a;
        public String b;
        public a c;
        public final PropertySet propertySet = new PropertySet();
        public final Motion motion = new Motion();
        public final Layout layout = new Layout();
        public final Transform transform = new Transform();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();

        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            public int[] f984a = new int[10];
            public int[] b = new int[10];
            public int c = 0;
            public int[] d = new int[10];
            public float[] e = new float[10];
            public int f = 0;
            public int[] g = new int[5];
            public String[] h = new String[5];
            public int i = 0;
            public int[] j = new int[4];
            public boolean[] k = new boolean[4];
            public int l = 0;

            public void a(int i, float f) {
                int i2 = this.f;
                int[] iArr = this.d;
                if (i2 >= iArr.length) {
                    this.d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.e;
                    this.e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.d;
                int i3 = this.f;
                iArr2[i3] = i;
                float[] fArr2 = this.e;
                this.f = i3 + 1;
                fArr2[i3] = f;
            }

            public void b(int i, int i2) {
                int i3 = this.c;
                int[] iArr = this.f984a;
                if (i3 >= iArr.length) {
                    this.f984a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.b;
                    this.b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.f984a;
                int i4 = this.c;
                iArr3[i4] = i;
                int[] iArr4 = this.b;
                this.c = i4 + 1;
                iArr4[i4] = i2;
            }

            public void c(int i, String str) {
                int i2 = this.i;
                int[] iArr = this.g;
                if (i2 >= iArr.length) {
                    this.g = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.h;
                    this.h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.g;
                int i3 = this.i;
                iArr2[i3] = i;
                String[] strArr2 = this.h;
                this.i = i3 + 1;
                strArr2[i3] = str;
            }

            public void d(int i, boolean z) {
                int i2 = this.l;
                int[] iArr = this.j;
                if (i2 >= iArr.length) {
                    this.j = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.k;
                    this.k = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.j;
                int i3 = this.l;
                iArr2[i3] = i;
                boolean[] zArr2 = this.k;
                this.l = i3 + 1;
                zArr2[i3] = z;
            }

            public void e(Constraint constraint) {
                for (int i = 0; i < this.c; i++) {
                    ConstraintSet.u(constraint, this.f984a[i], this.b[i]);
                }
                for (int i2 = 0; i2 < this.f; i2++) {
                    ConstraintSet.t(constraint, this.d[i2], this.e[i2]);
                }
                for (int i3 = 0; i3 < this.i; i3++) {
                    ConstraintSet.v(constraint, this.g[i3], this.h[i3]);
                }
                for (int i4 = 0; i4 < this.l; i4++) {
                    ConstraintSet.w(constraint, this.j[i4], this.k[i4]);
                }
            }

            @SuppressLint({"LogConditional"})
            public void f(String str) {
                Log.v(str, "int");
                for (int i = 0; i < this.c; i++) {
                    Log.v(str, this.f984a[i] + " = " + this.b[i]);
                }
                Log.v(str, TypedValues.Custom.S_FLOAT);
                for (int i2 = 0; i2 < this.f; i2++) {
                    Log.v(str, this.d[i2] + " = " + this.e[i2]);
                }
                Log.v(str, "strings");
                for (int i3 = 0; i3 < this.i; i3++) {
                    Log.v(str, this.g[i3] + " = " + this.h[i3]);
                }
                Log.v(str, "boolean");
                for (int i4 = 0; i4 < this.l; i4++) {
                    Log.v(str, this.j[i4] + " = " + this.k[i4]);
                }
            }
        }

        public void applyDelta(Constraint constraint) {
            a aVar = this.c;
            if (aVar != null) {
                aVar.e(constraint);
            }
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout = this.layout;
            layoutParams.leftToLeft = layout.leftToLeft;
            layoutParams.leftToRight = layout.leftToRight;
            layoutParams.rightToLeft = layout.rightToLeft;
            layoutParams.rightToRight = layout.rightToRight;
            layoutParams.topToTop = layout.topToTop;
            layoutParams.topToBottom = layout.topToBottom;
            layoutParams.bottomToTop = layout.bottomToTop;
            layoutParams.bottomToBottom = layout.bottomToBottom;
            layoutParams.baselineToBaseline = layout.baselineToBaseline;
            layoutParams.baselineToTop = layout.baselineToTop;
            layoutParams.baselineToBottom = layout.baselineToBottom;
            layoutParams.startToEnd = layout.startToEnd;
            layoutParams.startToStart = layout.startToStart;
            layoutParams.endToStart = layout.endToStart;
            layoutParams.endToEnd = layout.endToEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = layout.leftMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = layout.rightMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = layout.topMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = layout.bottomMargin;
            layoutParams.goneStartMargin = layout.goneStartMargin;
            layoutParams.goneEndMargin = layout.goneEndMargin;
            layoutParams.goneTopMargin = layout.goneTopMargin;
            layoutParams.goneBottomMargin = layout.goneBottomMargin;
            layoutParams.horizontalBias = layout.horizontalBias;
            layoutParams.verticalBias = layout.verticalBias;
            layoutParams.circleConstraint = layout.circleConstraint;
            layoutParams.circleRadius = layout.circleRadius;
            layoutParams.circleAngle = layout.circleAngle;
            layoutParams.dimensionRatio = layout.dimensionRatio;
            layoutParams.editorAbsoluteX = layout.editorAbsoluteX;
            layoutParams.editorAbsoluteY = layout.editorAbsoluteY;
            layoutParams.verticalWeight = layout.verticalWeight;
            layoutParams.horizontalWeight = layout.horizontalWeight;
            layoutParams.verticalChainStyle = layout.verticalChainStyle;
            layoutParams.horizontalChainStyle = layout.horizontalChainStyle;
            layoutParams.constrainedWidth = layout.constrainedWidth;
            layoutParams.constrainedHeight = layout.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = layout.widthDefault;
            layoutParams.matchConstraintDefaultHeight = layout.heightDefault;
            layoutParams.matchConstraintMaxWidth = layout.widthMax;
            layoutParams.matchConstraintMaxHeight = layout.heightMax;
            layoutParams.matchConstraintMinWidth = layout.widthMin;
            layoutParams.matchConstraintMinHeight = layout.heightMin;
            layoutParams.matchConstraintPercentWidth = layout.widthPercent;
            layoutParams.matchConstraintPercentHeight = layout.heightPercent;
            layoutParams.orientation = layout.orientation;
            layoutParams.guidePercent = layout.guidePercent;
            layoutParams.guideBegin = layout.guideBegin;
            layoutParams.guideEnd = layout.guideEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = layout.mWidth;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = layout.mHeight;
            String str = layout.mConstraintTag;
            if (str != null) {
                layoutParams.constraintTag = str;
            }
            layoutParams.wrapBehaviorInParent = layout.mWrapBehavior;
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams.setMarginStart(layout.startMargin);
                layoutParams.setMarginEnd(this.layout.endMargin);
            }
            layoutParams.validate();
        }

        public final void h(int i, ConstraintLayout.LayoutParams layoutParams) {
            this.f983a = i;
            Layout layout = this.layout;
            layout.leftToLeft = layoutParams.leftToLeft;
            layout.leftToRight = layoutParams.leftToRight;
            layout.rightToLeft = layoutParams.rightToLeft;
            layout.rightToRight = layoutParams.rightToRight;
            layout.topToTop = layoutParams.topToTop;
            layout.topToBottom = layoutParams.topToBottom;
            layout.bottomToTop = layoutParams.bottomToTop;
            layout.bottomToBottom = layoutParams.bottomToBottom;
            layout.baselineToBaseline = layoutParams.baselineToBaseline;
            layout.baselineToTop = layoutParams.baselineToTop;
            layout.baselineToBottom = layoutParams.baselineToBottom;
            layout.startToEnd = layoutParams.startToEnd;
            layout.startToStart = layoutParams.startToStart;
            layout.endToStart = layoutParams.endToStart;
            layout.endToEnd = layoutParams.endToEnd;
            layout.horizontalBias = layoutParams.horizontalBias;
            layout.verticalBias = layoutParams.verticalBias;
            layout.dimensionRatio = layoutParams.dimensionRatio;
            layout.circleConstraint = layoutParams.circleConstraint;
            layout.circleRadius = layoutParams.circleRadius;
            layout.circleAngle = layoutParams.circleAngle;
            layout.editorAbsoluteX = layoutParams.editorAbsoluteX;
            layout.editorAbsoluteY = layoutParams.editorAbsoluteY;
            layout.orientation = layoutParams.orientation;
            layout.guidePercent = layoutParams.guidePercent;
            layout.guideBegin = layoutParams.guideBegin;
            layout.guideEnd = layoutParams.guideEnd;
            layout.mWidth = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            layout.mHeight = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            layout.leftMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            layout.rightMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            layout.topMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            layout.bottomMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            layout.baselineMargin = layoutParams.baselineMargin;
            layout.verticalWeight = layoutParams.verticalWeight;
            layout.horizontalWeight = layoutParams.horizontalWeight;
            layout.verticalChainStyle = layoutParams.verticalChainStyle;
            layout.horizontalChainStyle = layoutParams.horizontalChainStyle;
            layout.constrainedWidth = layoutParams.constrainedWidth;
            layout.constrainedHeight = layoutParams.constrainedHeight;
            layout.widthDefault = layoutParams.matchConstraintDefaultWidth;
            layout.heightDefault = layoutParams.matchConstraintDefaultHeight;
            layout.widthMax = layoutParams.matchConstraintMaxWidth;
            layout.heightMax = layoutParams.matchConstraintMaxHeight;
            layout.widthMin = layoutParams.matchConstraintMinWidth;
            layout.heightMin = layoutParams.matchConstraintMinHeight;
            layout.widthPercent = layoutParams.matchConstraintPercentWidth;
            layout.heightPercent = layoutParams.matchConstraintPercentHeight;
            layout.mConstraintTag = layoutParams.constraintTag;
            layout.goneTopMargin = layoutParams.goneTopMargin;
            layout.goneBottomMargin = layoutParams.goneBottomMargin;
            layout.goneLeftMargin = layoutParams.goneLeftMargin;
            layout.goneRightMargin = layoutParams.goneRightMargin;
            layout.goneStartMargin = layoutParams.goneStartMargin;
            layout.goneEndMargin = layoutParams.goneEndMargin;
            layout.goneBaselineMargin = layoutParams.goneBaselineMargin;
            layout.mWrapBehavior = layoutParams.wrapBehaviorInParent;
            if (Build.VERSION.SDK_INT >= 17) {
                layout.endMargin = layoutParams.getMarginEnd();
                this.layout.startMargin = layoutParams.getMarginStart();
            }
        }

        public final void i(int i, Constraints.LayoutParams layoutParams) {
            h(i, layoutParams);
            this.propertySet.alpha = layoutParams.alpha;
            Transform transform = this.transform;
            transform.rotation = layoutParams.rotation;
            transform.rotationX = layoutParams.rotationX;
            transform.rotationY = layoutParams.rotationY;
            transform.scaleX = layoutParams.scaleX;
            transform.scaleY = layoutParams.scaleY;
            transform.transformPivotX = layoutParams.transformPivotX;
            transform.transformPivotY = layoutParams.transformPivotY;
            transform.translationX = layoutParams.translationX;
            transform.translationY = layoutParams.translationY;
            transform.translationZ = layoutParams.translationZ;
            transform.elevation = layoutParams.elevation;
            transform.applyElevation = layoutParams.applyElevation;
        }

        public final void j(ConstraintHelper constraintHelper, int i, Constraints.LayoutParams layoutParams) {
            i(i, layoutParams);
            if (constraintHelper instanceof Barrier) {
                Layout layout = this.layout;
                layout.mHelperType = 1;
                Barrier barrier = (Barrier) constraintHelper;
                layout.mBarrierDirection = barrier.getType();
                this.layout.mReferenceIds = barrier.getReferencedIds();
                this.layout.mBarrierMargin = barrier.getMargin();
            }
        }

        public final ConstraintAttribute k(String str, ConstraintAttribute.AttributeType attributeType) {
            if (this.mCustomConstraints.containsKey(str)) {
                ConstraintAttribute constraintAttribute = this.mCustomConstraints.get(str);
                if (constraintAttribute.getType() == attributeType) {
                    return constraintAttribute;
                }
                throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute.getType().name());
            }
            ConstraintAttribute constraintAttribute2 = new ConstraintAttribute(str, attributeType);
            this.mCustomConstraints.put(str, constraintAttribute2);
            return constraintAttribute2;
        }

        public final void l(String str, int i) {
            k(str, ConstraintAttribute.AttributeType.COLOR_TYPE).setColorValue(i);
        }

        public final void m(String str, float f) {
            k(str, ConstraintAttribute.AttributeType.FLOAT_TYPE).setFloatValue(f);
        }

        public final void n(String str, int i) {
            k(str, ConstraintAttribute.AttributeType.INT_TYPE).setIntValue(i);
        }

        public final void o(String str, String str2) {
            k(str, ConstraintAttribute.AttributeType.STRING_TYPE).setStringValue(str2);
        }

        public void printDelta(String str) {
            a aVar = this.c;
            if (aVar != null) {
                aVar.f(str);
            } else {
                Log.v(str, "DELTA IS NULL");
            }
        }

        /* renamed from: clone */
        public Constraint m8clone() {
            Constraint constraint = new Constraint();
            constraint.layout.copyFrom(this.layout);
            constraint.motion.copyFrom(this.motion);
            constraint.propertySet.copyFrom(this.propertySet);
            constraint.transform.copyFrom(this.transform);
            constraint.f983a = this.f983a;
            constraint.c = this.c;
            return constraint;
        }
    }

    /* loaded from: classes.dex */
    public static class Layout {
        public static final int UNSET = -1;
        public static final int UNSET_GONE_MARGIN = Integer.MIN_VALUE;

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f985a;
        public String mConstraintTag;
        public int mHeight;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public boolean mIsGuideline = false;
        public boolean mApply = false;
        public boolean mOverride = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean guidelineUseRtl = true;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int baselineToTop = -1;
        public int baselineToBottom = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public float circleAngle = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = 0;
        public int rightMargin = 0;
        public int topMargin = 0;
        public int bottomMargin = 0;
        public int endMargin = 0;
        public int startMargin = 0;
        public int baselineMargin = 0;
        public int goneLeftMargin = Integer.MIN_VALUE;
        public int goneTopMargin = Integer.MIN_VALUE;
        public int goneRightMargin = Integer.MIN_VALUE;
        public int goneBottomMargin = Integer.MIN_VALUE;
        public int goneEndMargin = Integer.MIN_VALUE;
        public int goneStartMargin = Integer.MIN_VALUE;
        public int goneBaselineMargin = Integer.MIN_VALUE;
        public float verticalWeight = -1.0f;
        public float horizontalWeight = -1.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public int widthDefault = 0;
        public int heightDefault = 0;
        public int widthMax = 0;
        public int heightMax = 0;
        public int widthMin = 0;
        public int heightMin = 0;
        public float widthPercent = 1.0f;
        public float heightPercent = 1.0f;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public int mHelperType = -1;
        public boolean constrainedWidth = false;
        public boolean constrainedHeight = false;
        public boolean mBarrierAllowsGoneWidgets = true;
        public int mWrapBehavior = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f985a = sparseIntArray;
            sparseIntArray.append(R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            f985a.append(R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            f985a.append(R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            f985a.append(R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            f985a.append(R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            f985a.append(R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            f985a.append(R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            f985a.append(R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            f985a.append(R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            f985a.append(R.styleable.Layout_layout_editor_absoluteX, 6);
            f985a.append(R.styleable.Layout_layout_editor_absoluteY, 7);
            f985a.append(R.styleable.Layout_layout_constraintGuide_begin, 17);
            f985a.append(R.styleable.Layout_layout_constraintGuide_end, 18);
            f985a.append(R.styleable.Layout_layout_constraintGuide_percent, 19);
            f985a.append(R.styleable.Layout_guidelineUseRtl, 90);
            f985a.append(R.styleable.Layout_android_orientation, 26);
            f985a.append(R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            f985a.append(R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            f985a.append(R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            f985a.append(R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            f985a.append(R.styleable.Layout_layout_goneMarginLeft, 13);
            f985a.append(R.styleable.Layout_layout_goneMarginTop, 16);
            f985a.append(R.styleable.Layout_layout_goneMarginRight, 14);
            f985a.append(R.styleable.Layout_layout_goneMarginBottom, 11);
            f985a.append(R.styleable.Layout_layout_goneMarginStart, 15);
            f985a.append(R.styleable.Layout_layout_goneMarginEnd, 12);
            f985a.append(R.styleable.Layout_layout_constraintVertical_weight, 38);
            f985a.append(R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            f985a.append(R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            f985a.append(R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            f985a.append(R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            f985a.append(R.styleable.Layout_layout_constraintVertical_bias, 36);
            f985a.append(R.styleable.Layout_layout_constraintDimensionRatio, 5);
            f985a.append(R.styleable.Layout_layout_constraintLeft_creator, 91);
            f985a.append(R.styleable.Layout_layout_constraintTop_creator, 91);
            f985a.append(R.styleable.Layout_layout_constraintRight_creator, 91);
            f985a.append(R.styleable.Layout_layout_constraintBottom_creator, 91);
            f985a.append(R.styleable.Layout_layout_constraintBaseline_creator, 91);
            f985a.append(R.styleable.Layout_android_layout_marginLeft, 23);
            f985a.append(R.styleable.Layout_android_layout_marginRight, 27);
            f985a.append(R.styleable.Layout_android_layout_marginStart, 30);
            f985a.append(R.styleable.Layout_android_layout_marginEnd, 8);
            f985a.append(R.styleable.Layout_android_layout_marginTop, 33);
            f985a.append(R.styleable.Layout_android_layout_marginBottom, 2);
            f985a.append(R.styleable.Layout_android_layout_width, 22);
            f985a.append(R.styleable.Layout_android_layout_height, 21);
            f985a.append(R.styleable.Layout_layout_constraintWidth, 41);
            f985a.append(R.styleable.Layout_layout_constraintHeight, 42);
            f985a.append(R.styleable.Layout_layout_constrainedWidth, 41);
            f985a.append(R.styleable.Layout_layout_constrainedHeight, 42);
            f985a.append(R.styleable.Layout_layout_wrapBehaviorInParent, 76);
            f985a.append(R.styleable.Layout_layout_constraintCircle, 61);
            f985a.append(R.styleable.Layout_layout_constraintCircleRadius, 62);
            f985a.append(R.styleable.Layout_layout_constraintCircleAngle, 63);
            f985a.append(R.styleable.Layout_layout_constraintWidth_percent, 69);
            f985a.append(R.styleable.Layout_layout_constraintHeight_percent, 70);
            f985a.append(R.styleable.Layout_chainUseRtl, 71);
            f985a.append(R.styleable.Layout_barrierDirection, 72);
            f985a.append(R.styleable.Layout_barrierMargin, 73);
            f985a.append(R.styleable.Layout_constraint_referenced_ids, 74);
            f985a.append(R.styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                int i2 = f985a.get(index);
                switch (i2) {
                    case 1:
                        this.baselineToBaseline = ConstraintSet.n(obtainStyledAttributes, index, this.baselineToBaseline);
                        break;
                    case 2:
                        this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                        break;
                    case 3:
                        this.bottomToBottom = ConstraintSet.n(obtainStyledAttributes, index, this.bottomToBottom);
                        break;
                    case 4:
                        this.bottomToTop = ConstraintSet.n(obtainStyledAttributes, index, this.bottomToTop);
                        break;
                    case 5:
                        this.dimensionRatio = obtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                        break;
                    case 7:
                        this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                        break;
                    case 8:
                        if (Build.VERSION.SDK_INT >= 17) {
                            this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        this.endToEnd = ConstraintSet.n(obtainStyledAttributes, index, this.endToEnd);
                        break;
                    case 10:
                        this.endToStart = ConstraintSet.n(obtainStyledAttributes, index, this.endToStart);
                        break;
                    case 11:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 12:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 13:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 14:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 15:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 16:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 17:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 18:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 19:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 20:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 21:
                        this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                        break;
                    case 22:
                        this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                        break;
                    case 23:
                        this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                        break;
                    case 24:
                        this.leftToLeft = ConstraintSet.n(obtainStyledAttributes, index, this.leftToLeft);
                        break;
                    case 25:
                        this.leftToRight = ConstraintSet.n(obtainStyledAttributes, index, this.leftToRight);
                        break;
                    case 26:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 27:
                        this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                        break;
                    case 28:
                        this.rightToLeft = ConstraintSet.n(obtainStyledAttributes, index, this.rightToLeft);
                        break;
                    case 29:
                        this.rightToRight = ConstraintSet.n(obtainStyledAttributes, index, this.rightToRight);
                        break;
                    case 30:
                        if (Build.VERSION.SDK_INT >= 17) {
                            this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                            break;
                        } else {
                            break;
                        }
                    case 31:
                        this.startToEnd = ConstraintSet.n(obtainStyledAttributes, index, this.startToEnd);
                        break;
                    case 32:
                        this.startToStart = ConstraintSet.n(obtainStyledAttributes, index, this.startToStart);
                        break;
                    case 33:
                        this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                        break;
                    case 34:
                        this.topToBottom = ConstraintSet.n(obtainStyledAttributes, index, this.topToBottom);
                        break;
                    case 35:
                        this.topToTop = ConstraintSet.n(obtainStyledAttributes, index, this.topToTop);
                        break;
                    case 36:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 37:
                        this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                        break;
                    case 38:
                        this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                        break;
                    case 39:
                        this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                        break;
                    case 40:
                        this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                        break;
                    case 41:
                        ConstraintSet.o(this, obtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        ConstraintSet.o(this, obtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i2) {
                            case 61:
                                this.circleConstraint = ConstraintSet.n(obtainStyledAttributes, index, this.circleConstraint);
                                continue;
                            case 62:
                                this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                continue;
                            case 63:
                                this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                continue;
                            default:
                                switch (i2) {
                                    case 69:
                                        this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                        continue;
                                    case 70:
                                        this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                        continue;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        continue;
                                    case 72:
                                        this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                        continue;
                                    case 73:
                                        this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                        continue;
                                    case 74:
                                        this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                        continue;
                                    case 75:
                                        this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                        continue;
                                    case 76:
                                        this.mWrapBehavior = obtainStyledAttributes.getInt(index, this.mWrapBehavior);
                                        continue;
                                    case 77:
                                        this.baselineToTop = ConstraintSet.n(obtainStyledAttributes, index, this.baselineToTop);
                                        continue;
                                    case 78:
                                        this.baselineToBottom = ConstraintSet.n(obtainStyledAttributes, index, this.baselineToBottom);
                                        continue;
                                    case 79:
                                        this.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBaselineMargin);
                                        continue;
                                    case 80:
                                        this.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.baselineMargin);
                                        continue;
                                    case 81:
                                        this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                        continue;
                                    case 82:
                                        this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                        continue;
                                    case 83:
                                        this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                        continue;
                                    case 84:
                                        this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                        continue;
                                    case 85:
                                        this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                        continue;
                                    case 86:
                                        this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                        continue;
                                    case 87:
                                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                                        continue;
                                    case 88:
                                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                                        continue;
                                    case 89:
                                        this.mConstraintTag = obtainStyledAttributes.getString(index);
                                        continue;
                                    case 90:
                                        this.guidelineUseRtl = obtainStyledAttributes.getBoolean(index, this.guidelineUseRtl);
                                        continue;
                                    case 91:
                                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f985a.get(index));
                                        continue;
                                    default:
                                        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f985a.get(index));
                                        continue;
                                        continue;
                                }
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Layout layout) {
            this.mIsGuideline = layout.mIsGuideline;
            this.mWidth = layout.mWidth;
            this.mApply = layout.mApply;
            this.mHeight = layout.mHeight;
            this.guideBegin = layout.guideBegin;
            this.guideEnd = layout.guideEnd;
            this.guidePercent = layout.guidePercent;
            this.guidelineUseRtl = layout.guidelineUseRtl;
            this.leftToLeft = layout.leftToLeft;
            this.leftToRight = layout.leftToRight;
            this.rightToLeft = layout.rightToLeft;
            this.rightToRight = layout.rightToRight;
            this.topToTop = layout.topToTop;
            this.topToBottom = layout.topToBottom;
            this.bottomToTop = layout.bottomToTop;
            this.bottomToBottom = layout.bottomToBottom;
            this.baselineToBaseline = layout.baselineToBaseline;
            this.baselineToTop = layout.baselineToTop;
            this.baselineToBottom = layout.baselineToBottom;
            this.startToEnd = layout.startToEnd;
            this.startToStart = layout.startToStart;
            this.endToStart = layout.endToStart;
            this.endToEnd = layout.endToEnd;
            this.horizontalBias = layout.horizontalBias;
            this.verticalBias = layout.verticalBias;
            this.dimensionRatio = layout.dimensionRatio;
            this.circleConstraint = layout.circleConstraint;
            this.circleRadius = layout.circleRadius;
            this.circleAngle = layout.circleAngle;
            this.editorAbsoluteX = layout.editorAbsoluteX;
            this.editorAbsoluteY = layout.editorAbsoluteY;
            this.orientation = layout.orientation;
            this.leftMargin = layout.leftMargin;
            this.rightMargin = layout.rightMargin;
            this.topMargin = layout.topMargin;
            this.bottomMargin = layout.bottomMargin;
            this.endMargin = layout.endMargin;
            this.startMargin = layout.startMargin;
            this.baselineMargin = layout.baselineMargin;
            this.goneLeftMargin = layout.goneLeftMargin;
            this.goneTopMargin = layout.goneTopMargin;
            this.goneRightMargin = layout.goneRightMargin;
            this.goneBottomMargin = layout.goneBottomMargin;
            this.goneEndMargin = layout.goneEndMargin;
            this.goneStartMargin = layout.goneStartMargin;
            this.goneBaselineMargin = layout.goneBaselineMargin;
            this.verticalWeight = layout.verticalWeight;
            this.horizontalWeight = layout.horizontalWeight;
            this.horizontalChainStyle = layout.horizontalChainStyle;
            this.verticalChainStyle = layout.verticalChainStyle;
            this.widthDefault = layout.widthDefault;
            this.heightDefault = layout.heightDefault;
            this.widthMax = layout.widthMax;
            this.heightMax = layout.heightMax;
            this.widthMin = layout.widthMin;
            this.heightMin = layout.heightMin;
            this.widthPercent = layout.widthPercent;
            this.heightPercent = layout.heightPercent;
            this.mBarrierDirection = layout.mBarrierDirection;
            this.mBarrierMargin = layout.mBarrierMargin;
            this.mHelperType = layout.mHelperType;
            this.mConstraintTag = layout.mConstraintTag;
            int[] iArr = layout.mReferenceIds;
            if (iArr != null && layout.mReferenceIdString == null) {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.mReferenceIds = null;
            }
            this.mReferenceIdString = layout.mReferenceIdString;
            this.constrainedWidth = layout.constrainedWidth;
            this.constrainedHeight = layout.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = layout.mBarrierAllowsGoneWidgets;
            this.mWrapBehavior = layout.mWrapBehavior;
        }

        public void dump(MotionScene motionScene, StringBuilder sb) {
            Field[] declaredFields = getClass().getDeclaredFields();
            sb.append("\n");
            for (Field field : declaredFields) {
                String name = field.getName();
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        Object obj = field.get(this);
                        Class<?> type = field.getType();
                        if (type == Integer.TYPE) {
                            Integer num = (Integer) obj;
                            if (num.intValue() != -1) {
                                Object lookUpConstraintName = motionScene.lookUpConstraintName(num.intValue());
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(lookUpConstraintName == null ? num : lookUpConstraintName);
                                sb.append("\"\n");
                            }
                        } else if (type == Float.TYPE) {
                            Float f = (Float) obj;
                            if (f.floatValue() != -1.0f) {
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(f);
                                sb.append("\"\n");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Motion {

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f986a;
        public boolean mApply = false;
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

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f986a = sparseIntArray;
            sparseIntArray.append(R.styleable.Motion_motionPathRotate, 1);
            f986a.append(R.styleable.Motion_pathMotionArc, 2);
            f986a.append(R.styleable.Motion_transitionEasing, 3);
            f986a.append(R.styleable.Motion_drawPath, 4);
            f986a.append(R.styleable.Motion_animateRelativeTo, 5);
            f986a.append(R.styleable.Motion_animateCircleAngleTo, 6);
            f986a.append(R.styleable.Motion_motionStagger, 7);
            f986a.append(R.styleable.Motion_quantizeMotionSteps, 8);
            f986a.append(R.styleable.Motion_quantizeMotionPhase, 9);
            f986a.append(R.styleable.Motion_quantizeMotionInterpolator, 10);
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (f986a.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case 3:
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            this.mTransitionEasing = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            this.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.n(obtainStyledAttributes, index, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mAnimateCircleAngleTo = obtainStyledAttributes.getInteger(index, this.mAnimateCircleAngleTo);
                        break;
                    case 7:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                    case 8:
                        this.mQuantizeMotionSteps = obtainStyledAttributes.getInteger(index, this.mQuantizeMotionSteps);
                        break;
                    case 9:
                        this.mQuantizeMotionPhase = obtainStyledAttributes.getFloat(index, this.mQuantizeMotionPhase);
                        break;
                    case 10:
                        int i2 = obtainStyledAttributes.peekValue(index).type;
                        if (i2 == 1) {
                            int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                            this.mQuantizeInterpolatorID = resourceId;
                            if (resourceId != -1) {
                                this.mQuantizeInterpolatorType = -2;
                                break;
                            } else {
                                break;
                            }
                        } else if (i2 == 3) {
                            String string = obtainStyledAttributes.getString(index);
                            this.mQuantizeInterpolatorString = string;
                            if (string.indexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) > 0) {
                                this.mQuantizeInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                                this.mQuantizeInterpolatorType = -2;
                                break;
                            } else {
                                this.mQuantizeInterpolatorType = -1;
                                break;
                            }
                        } else {
                            this.mQuantizeInterpolatorType = obtainStyledAttributes.getInteger(index, this.mQuantizeInterpolatorID);
                            break;
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Motion motion) {
            this.mApply = motion.mApply;
            this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
            this.mTransitionEasing = motion.mTransitionEasing;
            this.mPathMotionArc = motion.mPathMotionArc;
            this.mDrawPath = motion.mDrawPath;
            this.mPathRotate = motion.mPathRotate;
            this.mMotionStagger = motion.mMotionStagger;
            this.mPolarRelativeTo = motion.mPolarRelativeTo;
        }
    }

    /* loaded from: classes.dex */
    public static class PropertySet {
        public boolean mApply = false;
        public int visibility = 0;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.PropertySet_android_alpha) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == R.styleable.PropertySet_android_visibility) {
                    this.visibility = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = ConstraintSet.d[this.visibility];
                } else if (index == R.styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == R.styleable.PropertySet_motionProgress) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(PropertySet propertySet) {
            this.mApply = propertySet.mApply;
            this.visibility = propertySet.visibility;
            this.alpha = propertySet.alpha;
            this.mProgress = propertySet.mProgress;
            this.mVisibilityMode = propertySet.mVisibilityMode;
        }
    }

    /* loaded from: classes.dex */
    public static class Transform {

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f987a;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public int transformPivotTarget = -1;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f987a = sparseIntArray;
            sparseIntArray.append(R.styleable.Transform_android_rotation, 1);
            f987a.append(R.styleable.Transform_android_rotationX, 2);
            f987a.append(R.styleable.Transform_android_rotationY, 3);
            f987a.append(R.styleable.Transform_android_scaleX, 4);
            f987a.append(R.styleable.Transform_android_scaleY, 5);
            f987a.append(R.styleable.Transform_android_transformPivotX, 6);
            f987a.append(R.styleable.Transform_android_transformPivotY, 7);
            f987a.append(R.styleable.Transform_android_translationX, 8);
            f987a.append(R.styleable.Transform_android_translationY, 9);
            f987a.append(R.styleable.Transform_android_translationZ, 10);
            f987a.append(R.styleable.Transform_android_elevation, 11);
            f987a.append(R.styleable.Transform_transformPivotTarget, 12);
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (f987a.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case 9:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.applyElevation = true;
                            this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        this.transformPivotTarget = ConstraintSet.n(obtainStyledAttributes, index, this.transformPivotTarget);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Transform transform) {
            this.mApply = transform.mApply;
            this.rotation = transform.rotation;
            this.rotationX = transform.rotationX;
            this.rotationY = transform.rotationY;
            this.scaleX = transform.scaleX;
            this.scaleY = transform.scaleY;
            this.transformPivotX = transform.transformPivotX;
            this.transformPivotY = transform.transformPivotY;
            this.transformPivotTarget = transform.transformPivotTarget;
            this.translationX = transform.translationX;
            this.translationY = transform.translationY;
            this.translationZ = transform.translationZ;
            this.applyElevation = transform.applyElevation;
            this.elevation = transform.elevation;
        }
    }

    /* loaded from: classes.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public Writer f988a;
        public Context b;
        public int c = 0;
        public HashMap<Integer, String> d = new HashMap<>();

        public a(Writer writer, ConstraintLayout constraintLayout, int i) throws IOException {
            this.f988a = writer;
            this.b = constraintLayout.getContext();
        }

        public String a(int i) {
            if (this.d.containsKey(Integer.valueOf(i))) {
                return "'" + this.d.get(Integer.valueOf(i)) + "'";
            } else if (i == 0) {
                return "'parent'";
            } else {
                String b = b(i);
                this.d.put(Integer.valueOf(i), b);
                return "'" + b + "'";
            }
        }

        public String b(int i) {
            try {
                if (i != -1) {
                    return this.b.getResources().getResourceEntryName(i);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("unknown");
                int i2 = this.c + 1;
                this.c = i2;
                sb.append(i2);
                return sb.toString();
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("unknown");
                int i3 = this.c + 1;
                this.c = i3;
                sb2.append(i3);
                return sb2.toString();
            }
        }

        public void c(int i, float f, int i2) throws IOException {
            if (i == -1) {
                return;
            }
            this.f988a.write("       circle");
            this.f988a.write(":[");
            this.f988a.write(a(i));
            Writer writer = this.f988a;
            writer.write(", " + f);
            Writer writer2 = this.f988a;
            writer2.write(i2 + "]");
        }

        public void d(String str, int i, String str2, int i2, int i3) throws IOException {
            if (i == -1) {
                return;
            }
            Writer writer = this.f988a;
            writer.write("       " + str);
            this.f988a.write(":[");
            this.f988a.write(a(i));
            this.f988a.write(" , ");
            this.f988a.write(str2);
            if (i2 != 0) {
                Writer writer2 = this.f988a;
                writer2.write(" , " + i2);
            }
            this.f988a.write("],\n");
        }

        public final void e(String str, int i, int i2, float f, int i3, int i4, boolean z) throws IOException {
            if (i != 0) {
                if (i == -2) {
                    Writer writer = this.f988a;
                    writer.write("       " + str + ": 'wrap'\n");
                } else if (i == -1) {
                    Writer writer2 = this.f988a;
                    writer2.write("       " + str + ": 'parent'\n");
                } else {
                    Writer writer3 = this.f988a;
                    writer3.write("       " + str + ": " + i + ",\n");
                }
            } else if (i4 == -1 && i3 == -1) {
                if (i2 == 1) {
                    Writer writer4 = this.f988a;
                    writer4.write("       " + str + ": '???????????',\n");
                } else if (i2 != 2) {
                } else {
                    Writer writer5 = this.f988a;
                    writer5.write("       " + str + ": '" + f + "%',\n");
                }
            } else if (i2 == 0) {
                Writer writer6 = this.f988a;
                writer6.write("       " + str + ": {'spread' ," + i3 + ", " + i4 + "}\n");
            } else if (i2 == 1) {
                Writer writer7 = this.f988a;
                writer7.write("       " + str + ": {'wrap' ," + i3 + ", " + i4 + "}\n");
            } else if (i2 != 2) {
            } else {
                Writer writer8 = this.f988a;
                writer8.write("       " + str + ": {'" + f + "'% ," + i3 + ", " + i4 + "}\n");
            }
        }

        public final void f(int i, int i2, int i3, float f) {
        }

        public void g() throws IOException {
            this.f988a.write("\n'ConstraintSet':{\n");
            for (Integer num : ConstraintSet.this.c.keySet()) {
                String a2 = a(num.intValue());
                Writer writer = this.f988a;
                writer.write(a2 + ":{\n");
                Layout layout = ((Constraint) ConstraintSet.this.c.get(num)).layout;
                e(Property.ICON_TEXT_FIT_HEIGHT, layout.mHeight, layout.heightDefault, layout.heightPercent, layout.heightMin, layout.heightMax, layout.constrainedHeight);
                e(Property.ICON_TEXT_FIT_WIDTH, layout.mWidth, layout.widthDefault, layout.widthPercent, layout.widthMin, layout.widthMax, layout.constrainedWidth);
                d("'left'", layout.leftToLeft, "'left'", layout.leftMargin, layout.goneLeftMargin);
                d("'left'", layout.leftToRight, "'right'", layout.leftMargin, layout.goneLeftMargin);
                d("'right'", layout.rightToLeft, "'left'", layout.rightMargin, layout.goneRightMargin);
                d("'right'", layout.rightToRight, "'right'", layout.rightMargin, layout.goneRightMargin);
                d("'baseline'", layout.baselineToBaseline, "'baseline'", -1, layout.goneBaselineMargin);
                d("'baseline'", layout.baselineToTop, "'top'", -1, layout.goneBaselineMargin);
                d("'baseline'", layout.baselineToBottom, "'bottom'", -1, layout.goneBaselineMargin);
                d("'top'", layout.topToBottom, "'bottom'", layout.topMargin, layout.goneTopMargin);
                d("'top'", layout.topToTop, "'top'", layout.topMargin, layout.goneTopMargin);
                d("'bottom'", layout.bottomToBottom, "'bottom'", layout.bottomMargin, layout.goneBottomMargin);
                d("'bottom'", layout.bottomToTop, "'top'", layout.bottomMargin, layout.goneBottomMargin);
                d("'start'", layout.startToStart, "'start'", layout.startMargin, layout.goneStartMargin);
                d("'start'", layout.startToEnd, "'end'", layout.startMargin, layout.goneStartMargin);
                d("'end'", layout.endToStart, "'start'", layout.endMargin, layout.goneEndMargin);
                d("'end'", layout.endToEnd, "'end'", layout.endMargin, layout.goneEndMargin);
                i("'horizontalBias'", layout.horizontalBias, 0.5f);
                i("'verticalBias'", layout.verticalBias, 0.5f);
                c(layout.circleConstraint, layout.circleAngle, layout.circleRadius);
                f(layout.orientation, layout.guideBegin, layout.guideEnd, layout.guidePercent);
                k("'dimensionRatio'", layout.dimensionRatio);
                j("'barrierMargin'", layout.mBarrierMargin);
                j("'type'", layout.mHelperType);
                k("'ReferenceId'", layout.mReferenceIdString);
                l("'mBarrierAllowsGoneWidgets'", layout.mBarrierAllowsGoneWidgets, true);
                j("'WrapBehavior'", layout.mWrapBehavior);
                h("'verticalWeight'", layout.verticalWeight);
                h("'horizontalWeight'", layout.horizontalWeight);
                j("'horizontalChainStyle'", layout.horizontalChainStyle);
                j("'verticalChainStyle'", layout.verticalChainStyle);
                j("'barrierDirection'", layout.mBarrierDirection);
                int[] iArr = layout.mReferenceIds;
                if (iArr != null) {
                    m("'ReferenceIds'", iArr);
                }
                this.f988a.write("}\n");
            }
            this.f988a.write("}\n");
        }

        public void h(String str, float f) throws IOException {
            if (f == -1.0f) {
                return;
            }
            Writer writer = this.f988a;
            writer.write("       " + str);
            Writer writer2 = this.f988a;
            writer2.write(": " + f);
            this.f988a.write(",\n");
        }

        public void i(String str, float f, float f2) throws IOException {
            if (f == f2) {
                return;
            }
            Writer writer = this.f988a;
            writer.write("       " + str);
            Writer writer2 = this.f988a;
            writer2.write(": " + f);
            this.f988a.write(",\n");
        }

        public void j(String str, int i) throws IOException {
            if (i == 0 || i == -1) {
                return;
            }
            Writer writer = this.f988a;
            writer.write("       " + str);
            this.f988a.write(":");
            Writer writer2 = this.f988a;
            writer2.write(", " + i);
            this.f988a.write("\n");
        }

        public void k(String str, String str2) throws IOException {
            if (str2 == null) {
                return;
            }
            Writer writer = this.f988a;
            writer.write("       " + str);
            this.f988a.write(":");
            Writer writer2 = this.f988a;
            writer2.write(", " + str2);
            this.f988a.write("\n");
        }

        public void l(String str, boolean z, boolean z2) throws IOException {
            if (z == z2) {
                return;
            }
            Writer writer = this.f988a;
            writer.write("       " + str);
            Writer writer2 = this.f988a;
            writer2.write(": " + z);
            this.f988a.write(",\n");
        }

        public void m(String str, int[] iArr) throws IOException {
            if (iArr == null) {
                return;
            }
            Writer writer = this.f988a;
            writer.write("       " + str);
            this.f988a.write(": ");
            int i = 0;
            while (i < iArr.length) {
                Writer writer2 = this.f988a;
                StringBuilder sb = new StringBuilder();
                sb.append(i == 0 ? "[" : ", ");
                sb.append(a(iArr[i]));
                writer2.write(sb.toString());
                i++;
            }
            this.f988a.write("],\n");
        }
    }

    /* loaded from: classes.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public Writer f989a;
        public Context b;
        public int c = 0;
        public HashMap<Integer, String> d = new HashMap<>();

        public b(Writer writer, ConstraintLayout constraintLayout, int i) throws IOException {
            this.f989a = writer;
            this.b = constraintLayout.getContext();
        }

        public String a(int i) {
            if (this.d.containsKey(Integer.valueOf(i))) {
                return "@+id/" + this.d.get(Integer.valueOf(i)) + "";
            } else if (i == 0) {
                return "parent";
            } else {
                String b = b(i);
                this.d.put(Integer.valueOf(i), b);
                return "@+id/" + b + "";
            }
        }

        public String b(int i) {
            try {
                if (i != -1) {
                    return this.b.getResources().getResourceEntryName(i);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("unknown");
                int i2 = this.c + 1;
                this.c = i2;
                sb.append(i2);
                return sb.toString();
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("unknown");
                int i3 = this.c + 1;
                this.c = i3;
                sb2.append(i3);
                return sb2.toString();
            }
        }

        public final void c(String str, int i, int i2) throws IOException {
            if (i != i2) {
                if (i == -2) {
                    Writer writer = this.f989a;
                    writer.write("\n       " + str + "=\"wrap_content\"");
                } else if (i == -1) {
                    Writer writer2 = this.f989a;
                    writer2.write("\n       " + str + "=\"match_parent\"");
                } else {
                    Writer writer3 = this.f989a;
                    writer3.write("\n       " + str + "=\"" + i + "dp\"");
                }
            }
        }

        public final void d(String str, boolean z, boolean z2) throws IOException {
            if (z != z2) {
                Writer writer = this.f989a;
                writer.write("\n       " + str + "=\"" + z + "dp\"");
            }
        }

        public final void e(String str, int i, int i2) throws IOException {
            if (i != i2) {
                Writer writer = this.f989a;
                writer.write("\n       " + str + "=\"" + i + "dp\"");
            }
        }

        public final void f(String str, int i, String[] strArr, int i2) throws IOException {
            if (i != i2) {
                Writer writer = this.f989a;
                writer.write("\n       " + str + "=\"" + strArr[i] + "\"");
            }
        }

        public void g() throws IOException {
            this.f989a.write("\n<ConstraintSet>\n");
            for (Integer num : ConstraintSet.this.c.keySet()) {
                String a2 = a(num.intValue());
                this.f989a.write("  <Constraint");
                Writer writer = this.f989a;
                writer.write("\n       android:id=\"" + a2 + "\"");
                Layout layout = ((Constraint) ConstraintSet.this.c.get(num)).layout;
                c("android:layout_width", layout.mWidth, -5);
                c("android:layout_height", layout.mHeight, -5);
                h("app:layout_constraintGuide_begin", (float) layout.guideBegin, -1.0f);
                h("app:layout_constraintGuide_end", layout.guideEnd, -1.0f);
                h("app:layout_constraintGuide_percent", layout.guidePercent, -1.0f);
                h("app:layout_constraintHorizontal_bias", layout.horizontalBias, 0.5f);
                h("app:layout_constraintVertical_bias", layout.verticalBias, 0.5f);
                j("app:layout_constraintDimensionRatio", layout.dimensionRatio, null);
                l("app:layout_constraintCircle", layout.circleConstraint);
                h("app:layout_constraintCircleRadius", layout.circleRadius, 0.0f);
                h("app:layout_constraintCircleAngle", layout.circleAngle, 0.0f);
                h("android:orientation", layout.orientation, -1.0f);
                h("app:layout_constraintVertical_weight", layout.verticalWeight, -1.0f);
                h("app:layout_constraintHorizontal_weight", layout.horizontalWeight, -1.0f);
                h("app:layout_constraintHorizontal_chainStyle", layout.horizontalChainStyle, 0.0f);
                h("app:layout_constraintVertical_chainStyle", layout.verticalChainStyle, 0.0f);
                h("app:barrierDirection", layout.mBarrierDirection, -1.0f);
                h("app:barrierMargin", layout.mBarrierMargin, 0.0f);
                e("app:layout_marginLeft", layout.leftMargin, 0);
                e("app:layout_goneMarginLeft", layout.goneLeftMargin, Integer.MIN_VALUE);
                e("app:layout_marginRight", layout.rightMargin, 0);
                e("app:layout_goneMarginRight", layout.goneRightMargin, Integer.MIN_VALUE);
                e("app:layout_marginStart", layout.startMargin, 0);
                e("app:layout_goneMarginStart", layout.goneStartMargin, Integer.MIN_VALUE);
                e("app:layout_marginEnd", layout.endMargin, 0);
                e("app:layout_goneMarginEnd", layout.goneEndMargin, Integer.MIN_VALUE);
                e("app:layout_marginTop", layout.topMargin, 0);
                e("app:layout_goneMarginTop", layout.goneTopMargin, Integer.MIN_VALUE);
                e("app:layout_marginBottom", layout.bottomMargin, 0);
                e("app:layout_goneMarginBottom", layout.goneBottomMargin, Integer.MIN_VALUE);
                e("app:goneBaselineMargin", layout.goneBaselineMargin, Integer.MIN_VALUE);
                e("app:baselineMargin", layout.baselineMargin, 0);
                d("app:layout_constrainedWidth", layout.constrainedWidth, false);
                d("app:layout_constrainedHeight", layout.constrainedHeight, false);
                d("app:barrierAllowsGoneWidgets", layout.mBarrierAllowsGoneWidgets, true);
                h("app:layout_wrapBehaviorInParent", layout.mWrapBehavior, 0.0f);
                l("app:baselineToBaseline", layout.baselineToBaseline);
                l("app:baselineToBottom", layout.baselineToBottom);
                l("app:baselineToTop", layout.baselineToTop);
                l("app:layout_constraintBottom_toBottomOf", layout.bottomToBottom);
                l("app:layout_constraintBottom_toTopOf", layout.bottomToTop);
                l("app:layout_constraintEnd_toEndOf", layout.endToEnd);
                l("app:layout_constraintEnd_toStartOf", layout.endToStart);
                l("app:layout_constraintLeft_toLeftOf", layout.leftToLeft);
                l("app:layout_constraintLeft_toRightOf", layout.leftToRight);
                l("app:layout_constraintRight_toLeftOf", layout.rightToLeft);
                l("app:layout_constraintRight_toRightOf", layout.rightToRight);
                l("app:layout_constraintStart_toEndOf", layout.startToEnd);
                l("app:layout_constraintStart_toStartOf", layout.startToStart);
                l("app:layout_constraintTop_toBottomOf", layout.topToBottom);
                l("app:layout_constraintTop_toTopOf", layout.topToTop);
                String[] strArr = {"spread", "wrap", "percent"};
                f("app:layout_constraintHeight_default", layout.heightDefault, strArr, 0);
                h("app:layout_constraintHeight_percent", layout.heightPercent, 1.0f);
                e("app:layout_constraintHeight_min", layout.heightMin, 0);
                e("app:layout_constraintHeight_max", layout.heightMax, 0);
                d("android:layout_constrainedHeight", layout.constrainedHeight, false);
                f("app:layout_constraintWidth_default", layout.widthDefault, strArr, 0);
                h("app:layout_constraintWidth_percent", layout.widthPercent, 1.0f);
                e("app:layout_constraintWidth_min", layout.widthMin, 0);
                e("app:layout_constraintWidth_max", layout.widthMax, 0);
                d("android:layout_constrainedWidth", layout.constrainedWidth, false);
                h("app:layout_constraintVertical_weight", layout.verticalWeight, -1.0f);
                h("app:layout_constraintHorizontal_weight", layout.horizontalWeight, -1.0f);
                i("app:layout_constraintHorizontal_chainStyle", layout.horizontalChainStyle);
                i("app:layout_constraintVertical_chainStyle", layout.verticalChainStyle);
                f("app:barrierDirection", layout.mBarrierDirection, new String[]{"left", "right", "top", "bottom", "start", "end"}, -1);
                j("app:layout_constraintTag", layout.mConstraintTag, null);
                int[] iArr = layout.mReferenceIds;
                if (iArr != null) {
                    k("'ReferenceIds'", iArr);
                }
                this.f989a.write(" />\n");
            }
            this.f989a.write("</ConstraintSet>\n");
        }

        public void h(String str, float f, float f2) throws IOException {
            if (f == f2) {
                return;
            }
            Writer writer = this.f989a;
            writer.write("\n       " + str);
            Writer writer2 = this.f989a;
            writer2.write("=\"" + f + "\"");
        }

        public void i(String str, int i) throws IOException {
            if (i == 0 || i == -1) {
                return;
            }
            Writer writer = this.f989a;
            writer.write("\n       " + str + "=\"" + i + "\"\n");
        }

        public void j(String str, String str2, String str3) throws IOException {
            if (str2 == null || str2.equals(str3)) {
                return;
            }
            Writer writer = this.f989a;
            writer.write("\n       " + str);
            Writer writer2 = this.f989a;
            writer2.write("=\"" + str2 + "\"");
        }

        public void k(String str, int[] iArr) throws IOException {
            if (iArr == null) {
                return;
            }
            Writer writer = this.f989a;
            writer.write("\n       " + str);
            this.f989a.write(":");
            int i = 0;
            while (i < iArr.length) {
                Writer writer2 = this.f989a;
                StringBuilder sb = new StringBuilder();
                sb.append(i == 0 ? "[" : ", ");
                sb.append(a(iArr[i]));
                writer2.write(sb.toString());
                i++;
            }
            this.f989a.write("],\n");
        }

        public void l(String str, int i) throws IOException {
            if (i == -1) {
                return;
            }
            Writer writer = this.f989a;
            writer.write("\n       " + str);
            Writer writer2 = this.f989a;
            writer2.write("=\"" + a(i) + "\"");
        }
    }

    static {
        e.append(R.styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        e.append(R.styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        e.append(R.styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        e.append(R.styleable.Constraint_layout_constraintRight_toRightOf, 30);
        e.append(R.styleable.Constraint_layout_constraintTop_toTopOf, 36);
        e.append(R.styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        e.append(R.styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        e.append(R.styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        e.append(R.styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        e.append(R.styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        e.append(R.styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        e.append(R.styleable.Constraint_layout_editor_absoluteX, 6);
        e.append(R.styleable.Constraint_layout_editor_absoluteY, 7);
        e.append(R.styleable.Constraint_layout_constraintGuide_begin, 17);
        e.append(R.styleable.Constraint_layout_constraintGuide_end, 18);
        e.append(R.styleable.Constraint_layout_constraintGuide_percent, 19);
        e.append(R.styleable.Constraint_guidelineUseRtl, 99);
        e.append(R.styleable.Constraint_android_orientation, 27);
        e.append(R.styleable.Constraint_layout_constraintStart_toEndOf, 32);
        e.append(R.styleable.Constraint_layout_constraintStart_toStartOf, 33);
        e.append(R.styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        e.append(R.styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        e.append(R.styleable.Constraint_layout_goneMarginLeft, 13);
        e.append(R.styleable.Constraint_layout_goneMarginTop, 16);
        e.append(R.styleable.Constraint_layout_goneMarginRight, 14);
        e.append(R.styleable.Constraint_layout_goneMarginBottom, 11);
        e.append(R.styleable.Constraint_layout_goneMarginStart, 15);
        e.append(R.styleable.Constraint_layout_goneMarginEnd, 12);
        e.append(R.styleable.Constraint_layout_constraintVertical_weight, 40);
        e.append(R.styleable.Constraint_layout_constraintHorizontal_weight, 39);
        e.append(R.styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        e.append(R.styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        e.append(R.styleable.Constraint_layout_constraintHorizontal_bias, 20);
        e.append(R.styleable.Constraint_layout_constraintVertical_bias, 37);
        e.append(R.styleable.Constraint_layout_constraintDimensionRatio, 5);
        e.append(R.styleable.Constraint_layout_constraintLeft_creator, 87);
        e.append(R.styleable.Constraint_layout_constraintTop_creator, 87);
        e.append(R.styleable.Constraint_layout_constraintRight_creator, 87);
        e.append(R.styleable.Constraint_layout_constraintBottom_creator, 87);
        e.append(R.styleable.Constraint_layout_constraintBaseline_creator, 87);
        e.append(R.styleable.Constraint_android_layout_marginLeft, 24);
        e.append(R.styleable.Constraint_android_layout_marginRight, 28);
        e.append(R.styleable.Constraint_android_layout_marginStart, 31);
        e.append(R.styleable.Constraint_android_layout_marginEnd, 8);
        e.append(R.styleable.Constraint_android_layout_marginTop, 34);
        e.append(R.styleable.Constraint_android_layout_marginBottom, 2);
        e.append(R.styleable.Constraint_android_layout_width, 23);
        e.append(R.styleable.Constraint_android_layout_height, 21);
        e.append(R.styleable.Constraint_layout_constraintWidth, 95);
        e.append(R.styleable.Constraint_layout_constraintHeight, 96);
        e.append(R.styleable.Constraint_android_visibility, 22);
        e.append(R.styleable.Constraint_android_alpha, 43);
        e.append(R.styleable.Constraint_android_elevation, 44);
        e.append(R.styleable.Constraint_android_rotationX, 45);
        e.append(R.styleable.Constraint_android_rotationY, 46);
        e.append(R.styleable.Constraint_android_rotation, 60);
        e.append(R.styleable.Constraint_android_scaleX, 47);
        e.append(R.styleable.Constraint_android_scaleY, 48);
        e.append(R.styleable.Constraint_android_transformPivotX, 49);
        e.append(R.styleable.Constraint_android_transformPivotY, 50);
        e.append(R.styleable.Constraint_android_translationX, 51);
        e.append(R.styleable.Constraint_android_translationY, 52);
        e.append(R.styleable.Constraint_android_translationZ, 53);
        e.append(R.styleable.Constraint_layout_constraintWidth_default, 54);
        e.append(R.styleable.Constraint_layout_constraintHeight_default, 55);
        e.append(R.styleable.Constraint_layout_constraintWidth_max, 56);
        e.append(R.styleable.Constraint_layout_constraintHeight_max, 57);
        e.append(R.styleable.Constraint_layout_constraintWidth_min, 58);
        e.append(R.styleable.Constraint_layout_constraintHeight_min, 59);
        e.append(R.styleable.Constraint_layout_constraintCircle, 61);
        e.append(R.styleable.Constraint_layout_constraintCircleRadius, 62);
        e.append(R.styleable.Constraint_layout_constraintCircleAngle, 63);
        e.append(R.styleable.Constraint_animateRelativeTo, 64);
        e.append(R.styleable.Constraint_transitionEasing, 65);
        e.append(R.styleable.Constraint_drawPath, 66);
        e.append(R.styleable.Constraint_transitionPathRotate, 67);
        e.append(R.styleable.Constraint_motionStagger, 79);
        e.append(R.styleable.Constraint_android_id, 38);
        e.append(R.styleable.Constraint_motionProgress, 68);
        e.append(R.styleable.Constraint_layout_constraintWidth_percent, 69);
        e.append(R.styleable.Constraint_layout_constraintHeight_percent, 70);
        e.append(R.styleable.Constraint_layout_wrapBehaviorInParent, 97);
        e.append(R.styleable.Constraint_chainUseRtl, 71);
        e.append(R.styleable.Constraint_barrierDirection, 72);
        e.append(R.styleable.Constraint_barrierMargin, 73);
        e.append(R.styleable.Constraint_constraint_referenced_ids, 74);
        e.append(R.styleable.Constraint_barrierAllowsGoneWidgets, 75);
        e.append(R.styleable.Constraint_pathMotionArc, 76);
        e.append(R.styleable.Constraint_layout_constraintTag, 77);
        e.append(R.styleable.Constraint_visibilityMode, 78);
        e.append(R.styleable.Constraint_layout_constrainedWidth, 80);
        e.append(R.styleable.Constraint_layout_constrainedHeight, 81);
        e.append(R.styleable.Constraint_polarRelativeTo, 82);
        e.append(R.styleable.Constraint_transformPivotTarget, 83);
        e.append(R.styleable.Constraint_quantizeMotionSteps, 84);
        e.append(R.styleable.Constraint_quantizeMotionPhase, 85);
        e.append(R.styleable.Constraint_quantizeMotionInterpolator, 86);
        SparseIntArray sparseIntArray = f;
        int i = R.styleable.ConstraintOverride_layout_editor_absoluteY;
        sparseIntArray.append(i, 6);
        f.append(i, 7);
        f.append(R.styleable.ConstraintOverride_android_orientation, 27);
        f.append(R.styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        f.append(R.styleable.ConstraintOverride_layout_goneMarginTop, 16);
        f.append(R.styleable.ConstraintOverride_layout_goneMarginRight, 14);
        f.append(R.styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        f.append(R.styleable.ConstraintOverride_layout_goneMarginStart, 15);
        f.append(R.styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        f.append(R.styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        f.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        f.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        f.append(R.styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        f.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        f.append(R.styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        f.append(R.styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        f.append(R.styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        f.append(R.styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        f.append(R.styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        f.append(R.styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        f.append(R.styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        f.append(R.styleable.ConstraintOverride_android_layout_marginLeft, 24);
        f.append(R.styleable.ConstraintOverride_android_layout_marginRight, 28);
        f.append(R.styleable.ConstraintOverride_android_layout_marginStart, 31);
        f.append(R.styleable.ConstraintOverride_android_layout_marginEnd, 8);
        f.append(R.styleable.ConstraintOverride_android_layout_marginTop, 34);
        f.append(R.styleable.ConstraintOverride_android_layout_marginBottom, 2);
        f.append(R.styleable.ConstraintOverride_android_layout_width, 23);
        f.append(R.styleable.ConstraintOverride_android_layout_height, 21);
        f.append(R.styleable.ConstraintOverride_layout_constraintWidth, 95);
        f.append(R.styleable.ConstraintOverride_layout_constraintHeight, 96);
        f.append(R.styleable.ConstraintOverride_android_visibility, 22);
        f.append(R.styleable.ConstraintOverride_android_alpha, 43);
        f.append(R.styleable.ConstraintOverride_android_elevation, 44);
        f.append(R.styleable.ConstraintOverride_android_rotationX, 45);
        f.append(R.styleable.ConstraintOverride_android_rotationY, 46);
        f.append(R.styleable.ConstraintOverride_android_rotation, 60);
        f.append(R.styleable.ConstraintOverride_android_scaleX, 47);
        f.append(R.styleable.ConstraintOverride_android_scaleY, 48);
        f.append(R.styleable.ConstraintOverride_android_transformPivotX, 49);
        f.append(R.styleable.ConstraintOverride_android_transformPivotY, 50);
        f.append(R.styleable.ConstraintOverride_android_translationX, 51);
        f.append(R.styleable.ConstraintOverride_android_translationY, 52);
        f.append(R.styleable.ConstraintOverride_android_translationZ, 53);
        f.append(R.styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        f.append(R.styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        f.append(R.styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        f.append(R.styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        f.append(R.styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        f.append(R.styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        f.append(R.styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        f.append(R.styleable.ConstraintOverride_layout_constraintCircleAngle, 63);
        f.append(R.styleable.ConstraintOverride_animateRelativeTo, 64);
        f.append(R.styleable.ConstraintOverride_transitionEasing, 65);
        f.append(R.styleable.ConstraintOverride_drawPath, 66);
        f.append(R.styleable.ConstraintOverride_transitionPathRotate, 67);
        f.append(R.styleable.ConstraintOverride_motionStagger, 79);
        f.append(R.styleable.ConstraintOverride_android_id, 38);
        f.append(R.styleable.ConstraintOverride_motionTarget, 98);
        f.append(R.styleable.ConstraintOverride_motionProgress, 68);
        f.append(R.styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        f.append(R.styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        f.append(R.styleable.ConstraintOverride_chainUseRtl, 71);
        f.append(R.styleable.ConstraintOverride_barrierDirection, 72);
        f.append(R.styleable.ConstraintOverride_barrierMargin, 73);
        f.append(R.styleable.ConstraintOverride_constraint_referenced_ids, 74);
        f.append(R.styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        f.append(R.styleable.ConstraintOverride_pathMotionArc, 76);
        f.append(R.styleable.ConstraintOverride_layout_constraintTag, 77);
        f.append(R.styleable.ConstraintOverride_visibilityMode, 78);
        f.append(R.styleable.ConstraintOverride_layout_constrainedWidth, 80);
        f.append(R.styleable.ConstraintOverride_layout_constrainedHeight, 81);
        f.append(R.styleable.ConstraintOverride_polarRelativeTo, 82);
        f.append(R.styleable.ConstraintOverride_transformPivotTarget, 83);
        f.append(R.styleable.ConstraintOverride_quantizeMotionSteps, 84);
        f.append(R.styleable.ConstraintOverride_quantizeMotionPhase, 85);
        f.append(R.styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        f.append(R.styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    public static Constraint buildDelta(Context context, XmlPullParser xmlPullParser) {
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, R.styleable.ConstraintOverride);
        s(context, constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    public static int n(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    public static void o(Object obj, TypedArray typedArray, int i, int i2) {
        if (obj == null) {
            return;
        }
        int i3 = typedArray.peekValue(i).type;
        if (i3 != 3) {
            int i4 = -2;
            boolean z = false;
            if (i3 != 5) {
                int i5 = typedArray.getInt(i, 0);
                if (i5 != -4) {
                    i4 = (i5 == -3 || !(i5 == -2 || i5 == -1)) ? 0 : i5;
                } else {
                    z = true;
                }
            } else {
                i4 = typedArray.getDimensionPixelSize(i, 0);
            }
            if (obj instanceof ConstraintLayout.LayoutParams) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) obj;
                if (i2 == 0) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).width = i4;
                    layoutParams.constrainedWidth = z;
                    return;
                }
                ((ViewGroup.MarginLayoutParams) layoutParams).height = i4;
                layoutParams.constrainedHeight = z;
                return;
            } else if (obj instanceof Layout) {
                Layout layout = (Layout) obj;
                if (i2 == 0) {
                    layout.mWidth = i4;
                    layout.constrainedWidth = z;
                    return;
                }
                layout.mHeight = i4;
                layout.constrainedHeight = z;
                return;
            } else if (obj instanceof Constraint.a) {
                Constraint.a aVar = (Constraint.a) obj;
                if (i2 == 0) {
                    aVar.b(23, i4);
                    aVar.d(80, z);
                    return;
                }
                aVar.b(21, i4);
                aVar.d(81, z);
                return;
            } else {
                return;
            }
        }
        p(obj, typedArray.getString(i), i2);
    }

    public static void p(Object obj, String str, int i) {
        if (str == null) {
            return;
        }
        int indexOf = str.indexOf(61);
        int length = str.length();
        if (indexOf <= 0 || indexOf >= length - 1) {
            return;
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        if (substring2.length() > 0) {
            String trim = substring.trim();
            String trim2 = substring2.trim();
            if ("ratio".equalsIgnoreCase(trim)) {
                if (obj instanceof ConstraintLayout.LayoutParams) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) obj;
                    if (i == 0) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).width = 0;
                    } else {
                        ((ViewGroup.MarginLayoutParams) layoutParams).height = 0;
                    }
                    q(layoutParams, trim2);
                    return;
                } else if (obj instanceof Layout) {
                    ((Layout) obj).dimensionRatio = trim2;
                    return;
                } else if (obj instanceof Constraint.a) {
                    ((Constraint.a) obj).c(5, trim2);
                    return;
                } else {
                    return;
                }
            }
            try {
                if ("weight".equalsIgnoreCase(trim)) {
                    float parseFloat = Float.parseFloat(trim2);
                    if (obj instanceof ConstraintLayout.LayoutParams) {
                        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) obj;
                        if (i == 0) {
                            ((ViewGroup.MarginLayoutParams) layoutParams2).width = 0;
                            layoutParams2.horizontalWeight = parseFloat;
                        } else {
                            ((ViewGroup.MarginLayoutParams) layoutParams2).height = 0;
                            layoutParams2.verticalWeight = parseFloat;
                        }
                    } else if (obj instanceof Layout) {
                        Layout layout = (Layout) obj;
                        if (i == 0) {
                            layout.mWidth = 0;
                            layout.horizontalWeight = parseFloat;
                        } else {
                            layout.mHeight = 0;
                            layout.verticalWeight = parseFloat;
                        }
                    } else if (obj instanceof Constraint.a) {
                        Constraint.a aVar = (Constraint.a) obj;
                        if (i == 0) {
                            aVar.b(23, 0);
                            aVar.a(39, parseFloat);
                        } else {
                            aVar.b(21, 0);
                            aVar.a(40, parseFloat);
                        }
                    }
                } else if (!"parent".equalsIgnoreCase(trim)) {
                } else {
                    float max = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(trim2)));
                    if (obj instanceof ConstraintLayout.LayoutParams) {
                        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) obj;
                        if (i == 0) {
                            ((ViewGroup.MarginLayoutParams) layoutParams3).width = 0;
                            layoutParams3.matchConstraintPercentWidth = max;
                            layoutParams3.matchConstraintDefaultWidth = 2;
                        } else {
                            ((ViewGroup.MarginLayoutParams) layoutParams3).height = 0;
                            layoutParams3.matchConstraintPercentHeight = max;
                            layoutParams3.matchConstraintDefaultHeight = 2;
                        }
                    } else if (obj instanceof Layout) {
                        Layout layout2 = (Layout) obj;
                        if (i == 0) {
                            layout2.mWidth = 0;
                            layout2.widthPercent = max;
                            layout2.widthDefault = 2;
                        } else {
                            layout2.mHeight = 0;
                            layout2.heightPercent = max;
                            layout2.heightDefault = 2;
                        }
                    } else if (obj instanceof Constraint.a) {
                        Constraint.a aVar2 = (Constraint.a) obj;
                        if (i == 0) {
                            aVar2.b(23, 0);
                            aVar2.b(54, 2);
                        } else {
                            aVar2.b(21, 0);
                            aVar2.b(55, 2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    public static void q(ConstraintLayout.LayoutParams layoutParams, String str) {
        float f2 = Float.NaN;
        int i = -1;
        if (str != null) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i2 = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST)) {
                    i = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    i = 1;
                }
                i2 = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            try {
                if (indexOf2 >= 0 && indexOf2 < length - 1) {
                    String substring2 = str.substring(i2, indexOf2);
                    String substring3 = str.substring(indexOf2 + 1);
                    if (substring2.length() > 0 && substring3.length() > 0) {
                        float parseFloat = Float.parseFloat(substring2);
                        float parseFloat2 = Float.parseFloat(substring3);
                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                            if (i == 1) {
                                f2 = Math.abs(parseFloat2 / parseFloat);
                            } else {
                                f2 = Math.abs(parseFloat / parseFloat2);
                            }
                        }
                    }
                } else {
                    String substring4 = str.substring(i2);
                    if (substring4.length() > 0) {
                        f2 = Float.parseFloat(substring4);
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        layoutParams.dimensionRatio = str;
        layoutParams.c = f2;
        layoutParams.d = i;
    }

    public static void s(Context context, Constraint constraint, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        Constraint.a aVar = new Constraint.a();
        constraint.c = aVar;
        constraint.motion.mApply = false;
        constraint.layout.mApply = false;
        constraint.propertySet.mApply = false;
        constraint.transform.mApply = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (f.get(index)) {
                case 2:
                    aVar.b(2, typedArray.getDimensionPixelSize(index, constraint.layout.bottomMargin));
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case 61:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + e.get(index));
                    break;
                case 5:
                    aVar.c(5, typedArray.getString(index));
                    break;
                case 6:
                    aVar.b(6, typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteX));
                    break;
                case 7:
                    aVar.b(7, typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteY));
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        aVar.b(8, typedArray.getDimensionPixelSize(index, constraint.layout.endMargin));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    aVar.b(11, typedArray.getDimensionPixelSize(index, constraint.layout.goneBottomMargin));
                    break;
                case 12:
                    aVar.b(12, typedArray.getDimensionPixelSize(index, constraint.layout.goneEndMargin));
                    break;
                case 13:
                    aVar.b(13, typedArray.getDimensionPixelSize(index, constraint.layout.goneLeftMargin));
                    break;
                case 14:
                    aVar.b(14, typedArray.getDimensionPixelSize(index, constraint.layout.goneRightMargin));
                    break;
                case 15:
                    aVar.b(15, typedArray.getDimensionPixelSize(index, constraint.layout.goneStartMargin));
                    break;
                case 16:
                    aVar.b(16, typedArray.getDimensionPixelSize(index, constraint.layout.goneTopMargin));
                    break;
                case 17:
                    aVar.b(17, typedArray.getDimensionPixelOffset(index, constraint.layout.guideBegin));
                    break;
                case 18:
                    aVar.b(18, typedArray.getDimensionPixelOffset(index, constraint.layout.guideEnd));
                    break;
                case 19:
                    aVar.a(19, typedArray.getFloat(index, constraint.layout.guidePercent));
                    break;
                case 20:
                    aVar.a(20, typedArray.getFloat(index, constraint.layout.horizontalBias));
                    break;
                case 21:
                    aVar.b(21, typedArray.getLayoutDimension(index, constraint.layout.mHeight));
                    break;
                case 22:
                    aVar.b(22, d[typedArray.getInt(index, constraint.propertySet.visibility)]);
                    break;
                case 23:
                    aVar.b(23, typedArray.getLayoutDimension(index, constraint.layout.mWidth));
                    break;
                case 24:
                    aVar.b(24, typedArray.getDimensionPixelSize(index, constraint.layout.leftMargin));
                    break;
                case 27:
                    aVar.b(27, typedArray.getInt(index, constraint.layout.orientation));
                    break;
                case 28:
                    aVar.b(28, typedArray.getDimensionPixelSize(index, constraint.layout.rightMargin));
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        aVar.b(31, typedArray.getDimensionPixelSize(index, constraint.layout.startMargin));
                        break;
                    } else {
                        break;
                    }
                case 34:
                    aVar.b(34, typedArray.getDimensionPixelSize(index, constraint.layout.topMargin));
                    break;
                case 37:
                    aVar.a(37, typedArray.getFloat(index, constraint.layout.verticalBias));
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, constraint.f983a);
                    constraint.f983a = resourceId;
                    aVar.b(38, resourceId);
                    break;
                case 39:
                    aVar.a(39, typedArray.getFloat(index, constraint.layout.horizontalWeight));
                    break;
                case 40:
                    aVar.a(40, typedArray.getFloat(index, constraint.layout.verticalWeight));
                    break;
                case 41:
                    aVar.b(41, typedArray.getInt(index, constraint.layout.horizontalChainStyle));
                    break;
                case 42:
                    aVar.b(42, typedArray.getInt(index, constraint.layout.verticalChainStyle));
                    break;
                case 43:
                    aVar.a(43, typedArray.getFloat(index, constraint.propertySet.alpha));
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        aVar.d(44, true);
                        aVar.a(44, typedArray.getDimension(index, constraint.transform.elevation));
                        break;
                    } else {
                        break;
                    }
                case 45:
                    aVar.a(45, typedArray.getFloat(index, constraint.transform.rotationX));
                    break;
                case 46:
                    aVar.a(46, typedArray.getFloat(index, constraint.transform.rotationY));
                    break;
                case 47:
                    aVar.a(47, typedArray.getFloat(index, constraint.transform.scaleX));
                    break;
                case 48:
                    aVar.a(48, typedArray.getFloat(index, constraint.transform.scaleY));
                    break;
                case 49:
                    aVar.a(49, typedArray.getDimension(index, constraint.transform.transformPivotX));
                    break;
                case 50:
                    aVar.a(50, typedArray.getDimension(index, constraint.transform.transformPivotY));
                    break;
                case 51:
                    aVar.a(51, typedArray.getDimension(index, constraint.transform.translationX));
                    break;
                case 52:
                    aVar.a(52, typedArray.getDimension(index, constraint.transform.translationY));
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        aVar.a(53, typedArray.getDimension(index, constraint.transform.translationZ));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    aVar.b(54, typedArray.getInt(index, constraint.layout.widthDefault));
                    break;
                case 55:
                    aVar.b(55, typedArray.getInt(index, constraint.layout.heightDefault));
                    break;
                case 56:
                    aVar.b(56, typedArray.getDimensionPixelSize(index, constraint.layout.widthMax));
                    break;
                case 57:
                    aVar.b(57, typedArray.getDimensionPixelSize(index, constraint.layout.heightMax));
                    break;
                case 58:
                    aVar.b(58, typedArray.getDimensionPixelSize(index, constraint.layout.widthMin));
                    break;
                case 59:
                    aVar.b(59, typedArray.getDimensionPixelSize(index, constraint.layout.heightMin));
                    break;
                case 60:
                    aVar.a(60, typedArray.getFloat(index, constraint.transform.rotation));
                    break;
                case 62:
                    aVar.b(62, typedArray.getDimensionPixelSize(index, constraint.layout.circleRadius));
                    break;
                case 63:
                    aVar.a(63, typedArray.getFloat(index, constraint.layout.circleAngle));
                    break;
                case 64:
                    aVar.b(64, n(typedArray, index, constraint.motion.mAnimateRelativeTo));
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.c(65, typedArray.getString(index));
                        break;
                    } else {
                        aVar.c(65, Easing.NAMED_EASING[typedArray.getInteger(index, 0)]);
                        break;
                    }
                case 66:
                    aVar.b(66, typedArray.getInt(index, 0));
                    break;
                case 67:
                    aVar.a(67, typedArray.getFloat(index, constraint.motion.mPathRotate));
                    break;
                case 68:
                    aVar.a(68, typedArray.getFloat(index, constraint.propertySet.mProgress));
                    break;
                case 69:
                    aVar.a(69, typedArray.getFloat(index, 1.0f));
                    break;
                case 70:
                    aVar.a(70, typedArray.getFloat(index, 1.0f));
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    aVar.b(72, typedArray.getInt(index, constraint.layout.mBarrierDirection));
                    break;
                case 73:
                    aVar.b(73, typedArray.getDimensionPixelSize(index, constraint.layout.mBarrierMargin));
                    break;
                case 74:
                    aVar.c(74, typedArray.getString(index));
                    break;
                case 75:
                    aVar.d(75, typedArray.getBoolean(index, constraint.layout.mBarrierAllowsGoneWidgets));
                    break;
                case 76:
                    aVar.b(76, typedArray.getInt(index, constraint.motion.mPathMotionArc));
                    break;
                case 77:
                    aVar.c(77, typedArray.getString(index));
                    break;
                case 78:
                    aVar.b(78, typedArray.getInt(index, constraint.propertySet.mVisibilityMode));
                    break;
                case 79:
                    aVar.a(79, typedArray.getFloat(index, constraint.motion.mMotionStagger));
                    break;
                case 80:
                    aVar.d(80, typedArray.getBoolean(index, constraint.layout.constrainedWidth));
                    break;
                case 81:
                    aVar.d(81, typedArray.getBoolean(index, constraint.layout.constrainedHeight));
                    break;
                case 82:
                    aVar.b(82, typedArray.getInteger(index, constraint.motion.mAnimateCircleAngleTo));
                    break;
                case 83:
                    aVar.b(83, n(typedArray, index, constraint.transform.transformPivotTarget));
                    break;
                case 84:
                    aVar.b(84, typedArray.getInteger(index, constraint.motion.mQuantizeMotionSteps));
                    break;
                case 85:
                    aVar.a(85, typedArray.getFloat(index, constraint.motion.mQuantizeMotionPhase));
                    break;
                case 86:
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                        aVar.b(89, constraint.motion.mQuantizeInterpolatorID);
                        Motion motion = constraint.motion;
                        if (motion.mQuantizeInterpolatorID != -1) {
                            motion.mQuantizeInterpolatorType = -2;
                            aVar.b(88, -2);
                            break;
                        } else {
                            break;
                        }
                    } else if (i2 == 3) {
                        constraint.motion.mQuantizeInterpolatorString = typedArray.getString(index);
                        aVar.c(90, constraint.motion.mQuantizeInterpolatorString);
                        if (constraint.motion.mQuantizeInterpolatorString.indexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) > 0) {
                            constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                            aVar.b(89, constraint.motion.mQuantizeInterpolatorID);
                            constraint.motion.mQuantizeInterpolatorType = -2;
                            aVar.b(88, -2);
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorType = -1;
                            aVar.b(88, -1);
                            break;
                        }
                    } else {
                        Motion motion2 = constraint.motion;
                        motion2.mQuantizeInterpolatorType = typedArray.getInteger(index, motion2.mQuantizeInterpolatorID);
                        aVar.b(88, constraint.motion.mQuantizeInterpolatorType);
                        break;
                    }
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + e.get(index));
                    break;
                case 93:
                    aVar.b(93, typedArray.getDimensionPixelSize(index, constraint.layout.baselineMargin));
                    break;
                case 94:
                    aVar.b(94, typedArray.getDimensionPixelSize(index, constraint.layout.goneBaselineMargin));
                    break;
                case 95:
                    o(aVar, typedArray, index, 0);
                    break;
                case 96:
                    o(aVar, typedArray, index, 1);
                    break;
                case 97:
                    aVar.b(97, typedArray.getInt(index, constraint.layout.mWrapBehavior));
                    break;
                case 98:
                    if (MotionLayout.IS_IN_EDIT_MODE) {
                        int resourceId2 = typedArray.getResourceId(index, constraint.f983a);
                        constraint.f983a = resourceId2;
                        if (resourceId2 == -1) {
                            constraint.b = typedArray.getString(index);
                            break;
                        } else {
                            break;
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        constraint.b = typedArray.getString(index);
                        break;
                    } else {
                        constraint.f983a = typedArray.getResourceId(index, constraint.f983a);
                        break;
                    }
                case 99:
                    aVar.d(99, typedArray.getBoolean(index, constraint.layout.guidelineUseRtl));
                    break;
            }
        }
    }

    public static void t(Constraint constraint, int i, float f2) {
        if (i == 19) {
            constraint.layout.guidePercent = f2;
        } else if (i == 20) {
            constraint.layout.horizontalBias = f2;
        } else if (i == 37) {
            constraint.layout.verticalBias = f2;
        } else if (i == 60) {
            constraint.transform.rotation = f2;
        } else if (i == 63) {
            constraint.layout.circleAngle = f2;
        } else if (i == 79) {
            constraint.motion.mMotionStagger = f2;
        } else if (i == 85) {
            constraint.motion.mQuantizeMotionPhase = f2;
        } else if (i != 87) {
            if (i == 39) {
                constraint.layout.horizontalWeight = f2;
            } else if (i != 40) {
                switch (i) {
                    case 43:
                        constraint.propertySet.alpha = f2;
                        return;
                    case 44:
                        Transform transform = constraint.transform;
                        transform.elevation = f2;
                        transform.applyElevation = true;
                        return;
                    case 45:
                        constraint.transform.rotationX = f2;
                        return;
                    case 46:
                        constraint.transform.rotationY = f2;
                        return;
                    case 47:
                        constraint.transform.scaleX = f2;
                        return;
                    case 48:
                        constraint.transform.scaleY = f2;
                        return;
                    case 49:
                        constraint.transform.transformPivotX = f2;
                        return;
                    case 50:
                        constraint.transform.transformPivotY = f2;
                        return;
                    case 51:
                        constraint.transform.translationX = f2;
                        return;
                    case 52:
                        constraint.transform.translationY = f2;
                        return;
                    case 53:
                        constraint.transform.translationZ = f2;
                        return;
                    default:
                        switch (i) {
                            case 67:
                                constraint.motion.mPathRotate = f2;
                                return;
                            case 68:
                                constraint.propertySet.mProgress = f2;
                                return;
                            case 69:
                                constraint.layout.widthPercent = f2;
                                return;
                            case 70:
                                constraint.layout.heightPercent = f2;
                                return;
                            default:
                                Log.w("ConstraintSet", "Unknown attribute 0x");
                                return;
                        }
                }
            } else {
                constraint.layout.verticalWeight = f2;
            }
        }
    }

    public static void u(Constraint constraint, int i, int i2) {
        if (i == 6) {
            constraint.layout.editorAbsoluteX = i2;
        } else if (i == 7) {
            constraint.layout.editorAbsoluteY = i2;
        } else if (i == 8) {
            constraint.layout.endMargin = i2;
        } else if (i == 27) {
            constraint.layout.orientation = i2;
        } else if (i == 28) {
            constraint.layout.rightMargin = i2;
        } else if (i == 41) {
            constraint.layout.horizontalChainStyle = i2;
        } else if (i == 42) {
            constraint.layout.verticalChainStyle = i2;
        } else if (i == 61) {
            constraint.layout.circleConstraint = i2;
        } else if (i == 62) {
            constraint.layout.circleRadius = i2;
        } else if (i == 72) {
            constraint.layout.mBarrierDirection = i2;
        } else if (i != 73) {
            switch (i) {
                case 2:
                    constraint.layout.bottomMargin = i2;
                    return;
                case 11:
                    constraint.layout.goneBottomMargin = i2;
                    return;
                case 12:
                    constraint.layout.goneEndMargin = i2;
                    return;
                case 13:
                    constraint.layout.goneLeftMargin = i2;
                    return;
                case 14:
                    constraint.layout.goneRightMargin = i2;
                    return;
                case 15:
                    constraint.layout.goneStartMargin = i2;
                    return;
                case 16:
                    constraint.layout.goneTopMargin = i2;
                    return;
                case 17:
                    constraint.layout.guideBegin = i2;
                    return;
                case 18:
                    constraint.layout.guideEnd = i2;
                    return;
                case 31:
                    constraint.layout.startMargin = i2;
                    return;
                case 34:
                    constraint.layout.topMargin = i2;
                    return;
                case 38:
                    constraint.f983a = i2;
                    return;
                case 64:
                    constraint.motion.mAnimateRelativeTo = i2;
                    return;
                case 66:
                    constraint.motion.mDrawPath = i2;
                    return;
                case 76:
                    constraint.motion.mPathMotionArc = i2;
                    return;
                case 78:
                    constraint.propertySet.mVisibilityMode = i2;
                    return;
                case 93:
                    constraint.layout.baselineMargin = i2;
                    return;
                case 94:
                    constraint.layout.goneBaselineMargin = i2;
                    return;
                case 97:
                    constraint.layout.mWrapBehavior = i2;
                    return;
                default:
                    switch (i) {
                        case 21:
                            constraint.layout.mHeight = i2;
                            return;
                        case 22:
                            constraint.propertySet.visibility = i2;
                            return;
                        case 23:
                            constraint.layout.mWidth = i2;
                            return;
                        case 24:
                            constraint.layout.leftMargin = i2;
                            return;
                        default:
                            switch (i) {
                                case 54:
                                    constraint.layout.widthDefault = i2;
                                    return;
                                case 55:
                                    constraint.layout.heightDefault = i2;
                                    return;
                                case 56:
                                    constraint.layout.widthMax = i2;
                                    return;
                                case 57:
                                    constraint.layout.heightMax = i2;
                                    return;
                                case 58:
                                    constraint.layout.widthMin = i2;
                                    return;
                                case 59:
                                    constraint.layout.heightMin = i2;
                                    return;
                                default:
                                    switch (i) {
                                        case 82:
                                            constraint.motion.mAnimateCircleAngleTo = i2;
                                            return;
                                        case 83:
                                            constraint.transform.transformPivotTarget = i2;
                                            return;
                                        case 84:
                                            constraint.motion.mQuantizeMotionSteps = i2;
                                            return;
                                        default:
                                            switch (i) {
                                                case 87:
                                                    return;
                                                case 88:
                                                    constraint.motion.mQuantizeInterpolatorType = i2;
                                                    return;
                                                case 89:
                                                    constraint.motion.mQuantizeInterpolatorID = i2;
                                                    return;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x");
                                                    return;
                                            }
                                    }
                            }
                    }
            }
        } else {
            constraint.layout.mBarrierMargin = i2;
        }
    }

    public static void v(Constraint constraint, int i, String str) {
        if (i == 5) {
            constraint.layout.dimensionRatio = str;
        } else if (i == 65) {
            constraint.motion.mTransitionEasing = str;
        } else if (i == 74) {
            Layout layout = constraint.layout;
            layout.mReferenceIdString = str;
            layout.mReferenceIds = null;
        } else if (i == 77) {
            constraint.layout.mConstraintTag = str;
        } else if (i != 87) {
            if (i != 90) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                constraint.motion.mQuantizeInterpolatorString = str;
            }
        }
    }

    public static void w(Constraint constraint, int i, boolean z) {
        if (i == 44) {
            constraint.transform.applyElevation = z;
        } else if (i == 75) {
            constraint.layout.mBarrierAllowsGoneWidgets = z;
        } else if (i != 87) {
            if (i == 80) {
                constraint.layout.constrainedWidth = z;
            } else if (i != 81) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                constraint.layout.constrainedHeight = z;
            }
        }
    }

    public static String[] y(String str) {
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (charArray[i2] == ',' && !z) {
                arrayList.add(new String(charArray, i, i2 - i));
                i = i2 + 1;
            } else if (charArray[i2] == '\"') {
                z = !z;
            }
        }
        arrayList.add(new String(charArray, i, charArray.length - i));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public void addColorAttributes(String... strArr) {
        h(ConstraintAttribute.AttributeType.COLOR_TYPE, strArr);
    }

    public void addFloatAttributes(String... strArr) {
        h(ConstraintAttribute.AttributeType.FLOAT_TYPE, strArr);
    }

    public void addIntAttributes(String... strArr) {
        h(ConstraintAttribute.AttributeType.INT_TYPE, strArr);
    }

    public void addStringAttributes(String... strArr) {
        h(ConstraintAttribute.AttributeType.STRING_TYPE, strArr);
    }

    public void addToHorizontalChain(int i, int i2, int i3) {
        connect(i, 1, i2, i2 == 0 ? 1 : 2, 0);
        connect(i, 2, i3, i3 == 0 ? 2 : 1, 0);
        if (i2 != 0) {
            connect(i2, 2, i, 1, 0);
        }
        if (i3 != 0) {
            connect(i3, 1, i, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int i, int i2, int i3) {
        connect(i, 6, i2, i2 == 0 ? 6 : 7, 0);
        connect(i, 7, i3, i3 == 0 ? 7 : 6, 0);
        if (i2 != 0) {
            connect(i2, 7, i, 6, 0);
        }
        if (i3 != 0) {
            connect(i3, 6, i, 7, 0);
        }
    }

    public void addToVerticalChain(int i, int i2, int i3) {
        connect(i, 3, i2, i2 == 0 ? 3 : 4, 0);
        connect(i, 4, i3, i3 == 0 ? 4 : 3, 0);
        if (i2 != 0) {
            connect(i2, 4, i, 3, 0);
        }
        if (i3 != 0) {
            connect(i3, 3, i, 4, 0);
        }
    }

    public void applyCustomAttributes(ConstraintLayout constraintLayout) {
        Constraint constraint;
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.c.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(childAt));
            } else if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else {
                if (this.c.containsKey(Integer.valueOf(id)) && (constraint = this.c.get(Integer.valueOf(id))) != null) {
                    ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                }
            }
        }
    }

    public void applyDeltaFrom(ConstraintSet constraintSet) {
        for (Constraint constraint : constraintSet.c.values()) {
            if (constraint.c != null) {
                if (constraint.b != null) {
                    for (Integer num : this.c.keySet()) {
                        Constraint constraint2 = getConstraint(num.intValue());
                        String str = constraint2.layout.mConstraintTag;
                        if (str != null && constraint.b.matches(str)) {
                            constraint.c.e(constraint2);
                            constraint2.mCustomConstraints.putAll((HashMap) constraint.mCustomConstraints.clone());
                        }
                    }
                } else {
                    constraint.c.e(getConstraint(constraint.f983a));
                }
            }
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        i(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void applyToHelper(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        Constraint constraint;
        int id = constraintHelper.getId();
        if (this.c.containsKey(Integer.valueOf(id)) && (constraint = this.c.get(Integer.valueOf(id))) != null && (constraintWidget instanceof HelperWidget)) {
            constraintHelper.loadParameters(constraint, (HelperWidget) constraintWidget, layoutParams, sparseArray);
        }
    }

    public void applyToLayoutParams(int i, ConstraintLayout.LayoutParams layoutParams) {
        Constraint constraint;
        if (!this.c.containsKey(Integer.valueOf(i)) || (constraint = this.c.get(Integer.valueOf(i))) == null) {
            return;
        }
        constraint.applyTo(layoutParams);
    }

    public void applyToWithoutCustom(ConstraintLayout constraintLayout) {
        i(constraintLayout, false);
        constraintLayout.setConstraintSet(null);
    }

    public void center(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        if (i4 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (f2 <= 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if (i3 == 1 || i3 == 2) {
            connect(i, 1, i2, i3, i4);
            connect(i, 2, i5, i6, i7);
            Constraint constraint = this.c.get(Integer.valueOf(i));
            if (constraint != null) {
                constraint.layout.horizontalBias = f2;
            }
        } else if (i3 != 6 && i3 != 7) {
            connect(i, 3, i2, i3, i4);
            connect(i, 4, i5, i6, i7);
            Constraint constraint2 = this.c.get(Integer.valueOf(i));
            if (constraint2 != null) {
                constraint2.layout.verticalBias = f2;
            }
        } else {
            connect(i, 6, i2, i3, i4);
            connect(i, 7, i5, i6, i7);
            Constraint constraint3 = this.c.get(Integer.valueOf(i));
            if (constraint3 != null) {
                constraint3.layout.horizontalBias = f2;
            }
        }
    }

    public void centerHorizontally(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(i, 1, i2, i3, i4);
        connect(i, 2, i5, i6, i7);
        Constraint constraint = this.c.get(Integer.valueOf(i));
        if (constraint != null) {
            constraint.layout.horizontalBias = f2;
        }
    }

    public void centerHorizontallyRtl(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(i, 6, i2, i3, i4);
        connect(i, 7, i5, i6, i7);
        Constraint constraint = this.c.get(Integer.valueOf(i));
        if (constraint != null) {
            constraint.layout.horizontalBias = f2;
        }
    }

    public void centerVertically(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(i, 3, i2, i3, i4);
        connect(i, 4, i5, i6, i7);
        Constraint constraint = this.c.get(Integer.valueOf(i));
        if (constraint != null) {
            constraint.layout.verticalBias = f2;
        }
    }

    public void clear(int i) {
        this.c.remove(Integer.valueOf(i));
    }

    public void clone(Context context, int i) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void connect(int i, int i2, int i3, int i4, int i5) {
        if (!this.c.containsKey(Integer.valueOf(i))) {
            this.c.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = this.c.get(Integer.valueOf(i));
        if (constraint == null) {
            return;
        }
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    Layout layout = constraint.layout;
                    layout.leftToLeft = i3;
                    layout.leftToRight = -1;
                } else if (i4 == 2) {
                    Layout layout2 = constraint.layout;
                    layout2.leftToRight = i3;
                    layout2.leftToLeft = -1;
                } else {
                    throw new IllegalArgumentException("Left to " + x(i4) + " undefined");
                }
                constraint.layout.leftMargin = i5;
                return;
            case 2:
                if (i4 == 1) {
                    Layout layout3 = constraint.layout;
                    layout3.rightToLeft = i3;
                    layout3.rightToRight = -1;
                } else if (i4 == 2) {
                    Layout layout4 = constraint.layout;
                    layout4.rightToRight = i3;
                    layout4.rightToLeft = -1;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
                constraint.layout.rightMargin = i5;
                return;
            case 3:
                if (i4 == 3) {
                    Layout layout5 = constraint.layout;
                    layout5.topToTop = i3;
                    layout5.topToBottom = -1;
                    layout5.baselineToBaseline = -1;
                    layout5.baselineToTop = -1;
                    layout5.baselineToBottom = -1;
                } else if (i4 == 4) {
                    Layout layout6 = constraint.layout;
                    layout6.topToBottom = i3;
                    layout6.topToTop = -1;
                    layout6.baselineToBaseline = -1;
                    layout6.baselineToTop = -1;
                    layout6.baselineToBottom = -1;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
                constraint.layout.topMargin = i5;
                return;
            case 4:
                if (i4 == 4) {
                    Layout layout7 = constraint.layout;
                    layout7.bottomToBottom = i3;
                    layout7.bottomToTop = -1;
                    layout7.baselineToBaseline = -1;
                    layout7.baselineToTop = -1;
                    layout7.baselineToBottom = -1;
                } else if (i4 == 3) {
                    Layout layout8 = constraint.layout;
                    layout8.bottomToTop = i3;
                    layout8.bottomToBottom = -1;
                    layout8.baselineToBaseline = -1;
                    layout8.baselineToTop = -1;
                    layout8.baselineToBottom = -1;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
                constraint.layout.bottomMargin = i5;
                return;
            case 5:
                if (i4 == 5) {
                    Layout layout9 = constraint.layout;
                    layout9.baselineToBaseline = i3;
                    layout9.bottomToBottom = -1;
                    layout9.bottomToTop = -1;
                    layout9.topToTop = -1;
                    layout9.topToBottom = -1;
                    return;
                } else if (i4 == 3) {
                    Layout layout10 = constraint.layout;
                    layout10.baselineToTop = i3;
                    layout10.bottomToBottom = -1;
                    layout10.bottomToTop = -1;
                    layout10.topToTop = -1;
                    layout10.topToBottom = -1;
                    return;
                } else if (i4 == 4) {
                    Layout layout11 = constraint.layout;
                    layout11.baselineToBottom = i3;
                    layout11.bottomToBottom = -1;
                    layout11.bottomToTop = -1;
                    layout11.topToTop = -1;
                    layout11.topToBottom = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
            case 6:
                if (i4 == 6) {
                    Layout layout12 = constraint.layout;
                    layout12.startToStart = i3;
                    layout12.startToEnd = -1;
                } else if (i4 == 7) {
                    Layout layout13 = constraint.layout;
                    layout13.startToEnd = i3;
                    layout13.startToStart = -1;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
                constraint.layout.startMargin = i5;
                return;
            case 7:
                if (i4 == 7) {
                    Layout layout14 = constraint.layout;
                    layout14.endToEnd = i3;
                    layout14.endToStart = -1;
                } else if (i4 == 6) {
                    Layout layout15 = constraint.layout;
                    layout15.endToStart = i3;
                    layout15.endToEnd = -1;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
                constraint.layout.endMargin = i5;
                return;
            default:
                throw new IllegalArgumentException(x(i2) + " to " + x(i4) + " unknown");
        }
    }

    public void constrainCircle(int i, int i2, int i3, float f2) {
        Layout layout = m(i).layout;
        layout.circleConstraint = i2;
        layout.circleRadius = i3;
        layout.circleAngle = f2;
    }

    public void constrainDefaultHeight(int i, int i2) {
        m(i).layout.heightDefault = i2;
    }

    public void constrainDefaultWidth(int i, int i2) {
        m(i).layout.widthDefault = i2;
    }

    public void constrainHeight(int i, int i2) {
        m(i).layout.mHeight = i2;
    }

    public void constrainMaxHeight(int i, int i2) {
        m(i).layout.heightMax = i2;
    }

    public void constrainMaxWidth(int i, int i2) {
        m(i).layout.widthMax = i2;
    }

    public void constrainMinHeight(int i, int i2) {
        m(i).layout.heightMin = i2;
    }

    public void constrainMinWidth(int i, int i2) {
        m(i).layout.widthMin = i2;
    }

    public void constrainPercentHeight(int i, float f2) {
        m(i).layout.heightPercent = f2;
    }

    public void constrainPercentWidth(int i, float f2) {
        m(i).layout.widthPercent = f2;
    }

    public void constrainWidth(int i, int i2) {
        m(i).layout.mWidth = i2;
    }

    public void constrainedHeight(int i, boolean z) {
        m(i).layout.constrainedHeight = z;
    }

    public void constrainedWidth(int i, boolean z) {
        m(i).layout.constrainedWidth = z;
    }

    public void create(int i, int i2) {
        Layout layout = m(i).layout;
        layout.mIsGuideline = true;
        layout.orientation = i2;
    }

    public void createBarrier(int i, int i2, int i3, int... iArr) {
        Layout layout = m(i).layout;
        layout.mHelperType = 1;
        layout.mBarrierDirection = i2;
        layout.mBarrierMargin = i3;
        layout.mIsGuideline = false;
        layout.mReferenceIds = iArr;
    }

    public void createHorizontalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        k(i, i2, i3, i4, iArr, fArr, i5, 1, 2);
    }

    public void createHorizontalChainRtl(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        k(i, i2, i3, i4, iArr, fArr, i5, 6, 7);
    }

    public void createVerticalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        if (iArr.length >= 2) {
            if (fArr != null && fArr.length != iArr.length) {
                throw new IllegalArgumentException("must have 2 or more widgets in a chain");
            }
            if (fArr != null) {
                m(iArr[0]).layout.verticalWeight = fArr[0];
            }
            m(iArr[0]).layout.verticalChainStyle = i5;
            connect(iArr[0], 3, i, i2, 0);
            for (int i6 = 1; i6 < iArr.length; i6++) {
                int i7 = iArr[i6];
                int i8 = i6 - 1;
                connect(iArr[i6], 3, iArr[i8], 4, 0);
                connect(iArr[i8], 4, iArr[i6], 3, 0);
                if (fArr != null) {
                    m(iArr[i6]).layout.verticalWeight = fArr[i6];
                }
            }
            connect(iArr[iArr.length - 1], 4, i3, i4, 0);
            return;
        }
        throw new IllegalArgumentException("must have 2 or more widgets in a chain");
    }

    public void dump(MotionScene motionScene, int... iArr) {
        HashSet hashSet;
        Integer[] numArr;
        Set<Integer> keySet = this.c.keySet();
        if (iArr.length != 0) {
            hashSet = new HashSet();
            for (int i : iArr) {
                hashSet.add(Integer.valueOf(i));
            }
        } else {
            hashSet = new HashSet(keySet);
        }
        System.out.println(hashSet.size() + " constraints");
        StringBuilder sb = new StringBuilder();
        for (Integer num : (Integer[]) hashSet.toArray(new Integer[0])) {
            Constraint constraint = this.c.get(num);
            if (constraint != null) {
                sb.append("<Constraint id=");
                sb.append(num);
                sb.append(" \n");
                constraint.layout.dump(motionScene, sb);
                sb.append("/>\n");
            }
        }
        System.out.println(sb.toString());
    }

    public boolean getApplyElevation(int i) {
        return m(i).transform.applyElevation;
    }

    public Constraint getConstraint(int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            return this.c.get(Integer.valueOf(i));
        }
        return null;
    }

    public HashMap<String, ConstraintAttribute> getCustomAttributeSet() {
        return this.f982a;
    }

    public int getHeight(int i) {
        return m(i).layout.mHeight;
    }

    public int[] getKnownIds() {
        Integer[] numArr = (Integer[]) this.c.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public Constraint getParameters(int i) {
        return m(i);
    }

    public int[] getReferencedIds(int i) {
        int[] iArr = m(i).layout.mReferenceIds;
        return iArr == null ? new int[0] : Arrays.copyOf(iArr, iArr.length);
    }

    public int getVisibility(int i) {
        return m(i).propertySet.visibility;
    }

    public int getVisibilityMode(int i) {
        return m(i).propertySet.mVisibilityMode;
    }

    public int getWidth(int i) {
        return m(i).layout.mWidth;
    }

    public final void h(ConstraintAttribute.AttributeType attributeType, String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (this.f982a.containsKey(strArr[i])) {
                ConstraintAttribute constraintAttribute = this.f982a.get(strArr[i]);
                if (constraintAttribute != null && constraintAttribute.getType() != attributeType) {
                    throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute.getType().name());
                }
            } else {
                this.f982a.put(strArr[i], new ConstraintAttribute(strArr[i], attributeType));
            }
        }
    }

    public void i(ConstraintLayout constraintLayout, boolean z) {
        View findViewById;
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.c.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.c.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(childAt));
            } else if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else {
                if (id != -1) {
                    if (this.c.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        Constraint constraint = this.c.get(Integer.valueOf(id));
                        if (constraint != null) {
                            if (childAt instanceof Barrier) {
                                constraint.layout.mHelperType = 1;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(constraint.layout.mBarrierDirection);
                                barrier.setMargin(constraint.layout.mBarrierMargin);
                                barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
                                Layout layout = constraint.layout;
                                int[] iArr = layout.mReferenceIds;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = layout.mReferenceIdString;
                                    if (str != null) {
                                        layout.mReferenceIds = j(barrier, str);
                                        barrier.setReferencedIds(constraint.layout.mReferenceIds);
                                    }
                                }
                            }
                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                            layoutParams.validate();
                            constraint.applyTo(layoutParams);
                            if (z) {
                                ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                            }
                            childAt.setLayoutParams(layoutParams);
                            PropertySet propertySet = constraint.propertySet;
                            if (propertySet.mVisibilityMode == 0) {
                                childAt.setVisibility(propertySet.visibility);
                            }
                            int i2 = Build.VERSION.SDK_INT;
                            if (i2 >= 17) {
                                childAt.setAlpha(constraint.propertySet.alpha);
                                childAt.setRotation(constraint.transform.rotation);
                                childAt.setRotationX(constraint.transform.rotationX);
                                childAt.setRotationY(constraint.transform.rotationY);
                                childAt.setScaleX(constraint.transform.scaleX);
                                childAt.setScaleY(constraint.transform.scaleY);
                                Transform transform = constraint.transform;
                                if (transform.transformPivotTarget != -1) {
                                    if (((View) childAt.getParent()).findViewById(constraint.transform.transformPivotTarget) != null) {
                                        float top = (findViewById.getTop() + findViewById.getBottom()) / 2.0f;
                                        float left = (findViewById.getLeft() + findViewById.getRight()) / 2.0f;
                                        if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                            childAt.setPivotX(left - childAt.getLeft());
                                            childAt.setPivotY(top - childAt.getTop());
                                        }
                                    }
                                } else {
                                    if (!Float.isNaN(transform.transformPivotX)) {
                                        childAt.setPivotX(constraint.transform.transformPivotX);
                                    }
                                    if (!Float.isNaN(constraint.transform.transformPivotY)) {
                                        childAt.setPivotY(constraint.transform.transformPivotY);
                                    }
                                }
                                childAt.setTranslationX(constraint.transform.translationX);
                                childAt.setTranslationY(constraint.transform.translationY);
                                if (i2 >= 21) {
                                    childAt.setTranslationZ(constraint.transform.translationZ);
                                    Transform transform2 = constraint.transform;
                                    if (transform2.applyElevation) {
                                        childAt.setElevation(transform2.elevation);
                                    }
                                }
                            }
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = this.c.get(num);
            if (constraint2 != null) {
                if (constraint2.layout.mHelperType == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    Layout layout2 = constraint2.layout;
                    int[] iArr2 = layout2.mReferenceIds;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = layout2.mReferenceIdString;
                        if (str2 != null) {
                            layout2.mReferenceIds = j(barrier2, str2);
                            barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                        }
                    }
                    barrier2.setType(constraint2.layout.mBarrierDirection);
                    barrier2.setMargin(constraint2.layout.mBarrierMargin);
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                    barrier2.validateParams();
                    constraint2.applyTo(generateDefaultLayoutParams);
                    constraintLayout.addView(barrier2, generateDefaultLayoutParams);
                }
                if (constraint2.layout.mIsGuideline) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                    constraint2.applyTo(generateDefaultLayoutParams2);
                    constraintLayout.addView(guideline, generateDefaultLayoutParams2);
                }
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = constraintLayout.getChildAt(i3);
            if (childAt2 instanceof ConstraintHelper) {
                ((ConstraintHelper) childAt2).applyLayoutFeaturesInConstraintSet(constraintLayout);
            }
        }
    }

    public boolean isForceId() {
        return this.b;
    }

    public final int[] j(View view, String str) {
        int i;
        Object designInformation;
        String[] split = str.split(Constants.SEPARATOR_COMMA);
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < split.length) {
            String trim = split[i2].trim();
            try {
                i = R.id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 0) {
                i = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i = ((Integer) designInformation).intValue();
            }
            iArr[i3] = i;
            i2++;
            i3++;
        }
        return i3 != split.length ? Arrays.copyOf(iArr, i3) : iArr;
    }

    public final void k(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5, int i6, int i7) {
        if (iArr.length >= 2) {
            if (fArr != null && fArr.length != iArr.length) {
                throw new IllegalArgumentException("must have 2 or more widgets in a chain");
            }
            if (fArr != null) {
                m(iArr[0]).layout.horizontalWeight = fArr[0];
            }
            m(iArr[0]).layout.horizontalChainStyle = i5;
            connect(iArr[0], i6, i, i2, -1);
            for (int i8 = 1; i8 < iArr.length; i8++) {
                int i9 = iArr[i8];
                int i10 = i8 - 1;
                connect(iArr[i8], i6, iArr[i10], i7, -1);
                connect(iArr[i10], i7, iArr[i8], i6, -1);
                if (fArr != null) {
                    m(iArr[i8]).layout.horizontalWeight = fArr[i8];
                }
            }
            connect(iArr[iArr.length - 1], i7, i3, i4, -1);
            return;
        }
        throw new IllegalArgumentException("must have 2 or more widgets in a chain");
    }

    public final Constraint l(Context context, AttributeSet attributeSet, boolean z) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R.styleable.ConstraintOverride : R.styleable.Constraint);
        r(context, constraint, obtainStyledAttributes, z);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    public void load(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                    continue;
                } else if (eventType != 2) {
                    continue;
                } else {
                    String name = xml.getName();
                    Constraint l = l(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        l.layout.mIsGuideline = true;
                    }
                    this.c.put(Integer.valueOf(l.f983a), l);
                    continue;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    public final Constraint m(int i) {
        if (!this.c.containsKey(Integer.valueOf(i))) {
            this.c.put(Integer.valueOf(i), new Constraint());
        }
        return this.c.get(Integer.valueOf(i));
    }

    public void parseColorAttributes(Constraint constraint, String str) {
        String[] split = str.split(Constants.SEPARATOR_COMMA);
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length == 2) {
                constraint.l(split2[0], Color.parseColor(split2[1]));
            } else {
                Log.w("ConstraintSet", " Unable to parse " + split[i]);
            }
        }
    }

    public void parseFloatAttributes(Constraint constraint, String str) {
        String[] split = str.split(Constants.SEPARATOR_COMMA);
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length == 2) {
                constraint.m(split2[0], Float.parseFloat(split2[1]));
            } else {
                Log.w("ConstraintSet", " Unable to parse " + split[i]);
            }
        }
    }

    public void parseIntAttributes(Constraint constraint, String str) {
        String[] split = str.split(Constants.SEPARATOR_COMMA);
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length == 2) {
                constraint.m(split2[0], Integer.decode(split2[1]).intValue());
            } else {
                Log.w("ConstraintSet", " Unable to parse " + split[i]);
            }
        }
    }

    public void parseStringAttributes(Constraint constraint, String str) {
        String[] y = y(str);
        for (int i = 0; i < y.length; i++) {
            String[] split = y[i].split("=");
            Log.w("ConstraintSet", " Unable to parse " + y[i]);
            constraint.o(split[0], split[1]);
        }
    }

    public final void r(Context context, Constraint constraint, TypedArray typedArray, boolean z) {
        if (z) {
            s(context, constraint, typedArray);
            return;
        }
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index != R.styleable.Constraint_android_id && R.styleable.Constraint_android_layout_marginStart != index && R.styleable.Constraint_android_layout_marginEnd != index) {
                constraint.motion.mApply = true;
                constraint.layout.mApply = true;
                constraint.propertySet.mApply = true;
                constraint.transform.mApply = true;
            }
            switch (e.get(index)) {
                case 1:
                    Layout layout = constraint.layout;
                    layout.baselineToBaseline = n(typedArray, index, layout.baselineToBaseline);
                    break;
                case 2:
                    Layout layout2 = constraint.layout;
                    layout2.bottomMargin = typedArray.getDimensionPixelSize(index, layout2.bottomMargin);
                    break;
                case 3:
                    Layout layout3 = constraint.layout;
                    layout3.bottomToBottom = n(typedArray, index, layout3.bottomToBottom);
                    break;
                case 4:
                    Layout layout4 = constraint.layout;
                    layout4.bottomToTop = n(typedArray, index, layout4.bottomToTop);
                    break;
                case 5:
                    constraint.layout.dimensionRatio = typedArray.getString(index);
                    break;
                case 6:
                    Layout layout5 = constraint.layout;
                    layout5.editorAbsoluteX = typedArray.getDimensionPixelOffset(index, layout5.editorAbsoluteX);
                    break;
                case 7:
                    Layout layout6 = constraint.layout;
                    layout6.editorAbsoluteY = typedArray.getDimensionPixelOffset(index, layout6.editorAbsoluteY);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        Layout layout7 = constraint.layout;
                        layout7.endMargin = typedArray.getDimensionPixelSize(index, layout7.endMargin);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    Layout layout8 = constraint.layout;
                    layout8.endToEnd = n(typedArray, index, layout8.endToEnd);
                    break;
                case 10:
                    Layout layout9 = constraint.layout;
                    layout9.endToStart = n(typedArray, index, layout9.endToStart);
                    break;
                case 11:
                    Layout layout10 = constraint.layout;
                    layout10.goneBottomMargin = typedArray.getDimensionPixelSize(index, layout10.goneBottomMargin);
                    break;
                case 12:
                    Layout layout11 = constraint.layout;
                    layout11.goneEndMargin = typedArray.getDimensionPixelSize(index, layout11.goneEndMargin);
                    break;
                case 13:
                    Layout layout12 = constraint.layout;
                    layout12.goneLeftMargin = typedArray.getDimensionPixelSize(index, layout12.goneLeftMargin);
                    break;
                case 14:
                    Layout layout13 = constraint.layout;
                    layout13.goneRightMargin = typedArray.getDimensionPixelSize(index, layout13.goneRightMargin);
                    break;
                case 15:
                    Layout layout14 = constraint.layout;
                    layout14.goneStartMargin = typedArray.getDimensionPixelSize(index, layout14.goneStartMargin);
                    break;
                case 16:
                    Layout layout15 = constraint.layout;
                    layout15.goneTopMargin = typedArray.getDimensionPixelSize(index, layout15.goneTopMargin);
                    break;
                case 17:
                    Layout layout16 = constraint.layout;
                    layout16.guideBegin = typedArray.getDimensionPixelOffset(index, layout16.guideBegin);
                    break;
                case 18:
                    Layout layout17 = constraint.layout;
                    layout17.guideEnd = typedArray.getDimensionPixelOffset(index, layout17.guideEnd);
                    break;
                case 19:
                    Layout layout18 = constraint.layout;
                    layout18.guidePercent = typedArray.getFloat(index, layout18.guidePercent);
                    break;
                case 20:
                    Layout layout19 = constraint.layout;
                    layout19.horizontalBias = typedArray.getFloat(index, layout19.horizontalBias);
                    break;
                case 21:
                    Layout layout20 = constraint.layout;
                    layout20.mHeight = typedArray.getLayoutDimension(index, layout20.mHeight);
                    break;
                case 22:
                    PropertySet propertySet = constraint.propertySet;
                    propertySet.visibility = typedArray.getInt(index, propertySet.visibility);
                    PropertySet propertySet2 = constraint.propertySet;
                    propertySet2.visibility = d[propertySet2.visibility];
                    break;
                case 23:
                    Layout layout21 = constraint.layout;
                    layout21.mWidth = typedArray.getLayoutDimension(index, layout21.mWidth);
                    break;
                case 24:
                    Layout layout22 = constraint.layout;
                    layout22.leftMargin = typedArray.getDimensionPixelSize(index, layout22.leftMargin);
                    break;
                case 25:
                    Layout layout23 = constraint.layout;
                    layout23.leftToLeft = n(typedArray, index, layout23.leftToLeft);
                    break;
                case 26:
                    Layout layout24 = constraint.layout;
                    layout24.leftToRight = n(typedArray, index, layout24.leftToRight);
                    break;
                case 27:
                    Layout layout25 = constraint.layout;
                    layout25.orientation = typedArray.getInt(index, layout25.orientation);
                    break;
                case 28:
                    Layout layout26 = constraint.layout;
                    layout26.rightMargin = typedArray.getDimensionPixelSize(index, layout26.rightMargin);
                    break;
                case 29:
                    Layout layout27 = constraint.layout;
                    layout27.rightToLeft = n(typedArray, index, layout27.rightToLeft);
                    break;
                case 30:
                    Layout layout28 = constraint.layout;
                    layout28.rightToRight = n(typedArray, index, layout28.rightToRight);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        Layout layout29 = constraint.layout;
                        layout29.startMargin = typedArray.getDimensionPixelSize(index, layout29.startMargin);
                        break;
                    } else {
                        break;
                    }
                case 32:
                    Layout layout30 = constraint.layout;
                    layout30.startToEnd = n(typedArray, index, layout30.startToEnd);
                    break;
                case 33:
                    Layout layout31 = constraint.layout;
                    layout31.startToStart = n(typedArray, index, layout31.startToStart);
                    break;
                case 34:
                    Layout layout32 = constraint.layout;
                    layout32.topMargin = typedArray.getDimensionPixelSize(index, layout32.topMargin);
                    break;
                case 35:
                    Layout layout33 = constraint.layout;
                    layout33.topToBottom = n(typedArray, index, layout33.topToBottom);
                    break;
                case 36:
                    Layout layout34 = constraint.layout;
                    layout34.topToTop = n(typedArray, index, layout34.topToTop);
                    break;
                case 37:
                    Layout layout35 = constraint.layout;
                    layout35.verticalBias = typedArray.getFloat(index, layout35.verticalBias);
                    break;
                case 38:
                    constraint.f983a = typedArray.getResourceId(index, constraint.f983a);
                    break;
                case 39:
                    Layout layout36 = constraint.layout;
                    layout36.horizontalWeight = typedArray.getFloat(index, layout36.horizontalWeight);
                    break;
                case 40:
                    Layout layout37 = constraint.layout;
                    layout37.verticalWeight = typedArray.getFloat(index, layout37.verticalWeight);
                    break;
                case 41:
                    Layout layout38 = constraint.layout;
                    layout38.horizontalChainStyle = typedArray.getInt(index, layout38.horizontalChainStyle);
                    break;
                case 42:
                    Layout layout39 = constraint.layout;
                    layout39.verticalChainStyle = typedArray.getInt(index, layout39.verticalChainStyle);
                    break;
                case 43:
                    PropertySet propertySet3 = constraint.propertySet;
                    propertySet3.alpha = typedArray.getFloat(index, propertySet3.alpha);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        Transform transform = constraint.transform;
                        transform.applyElevation = true;
                        transform.elevation = typedArray.getDimension(index, transform.elevation);
                        break;
                    } else {
                        break;
                    }
                case 45:
                    Transform transform2 = constraint.transform;
                    transform2.rotationX = typedArray.getFloat(index, transform2.rotationX);
                    break;
                case 46:
                    Transform transform3 = constraint.transform;
                    transform3.rotationY = typedArray.getFloat(index, transform3.rotationY);
                    break;
                case 47:
                    Transform transform4 = constraint.transform;
                    transform4.scaleX = typedArray.getFloat(index, transform4.scaleX);
                    break;
                case 48:
                    Transform transform5 = constraint.transform;
                    transform5.scaleY = typedArray.getFloat(index, transform5.scaleY);
                    break;
                case 49:
                    Transform transform6 = constraint.transform;
                    transform6.transformPivotX = typedArray.getDimension(index, transform6.transformPivotX);
                    break;
                case 50:
                    Transform transform7 = constraint.transform;
                    transform7.transformPivotY = typedArray.getDimension(index, transform7.transformPivotY);
                    break;
                case 51:
                    Transform transform8 = constraint.transform;
                    transform8.translationX = typedArray.getDimension(index, transform8.translationX);
                    break;
                case 52:
                    Transform transform9 = constraint.transform;
                    transform9.translationY = typedArray.getDimension(index, transform9.translationY);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        Transform transform10 = constraint.transform;
                        transform10.translationZ = typedArray.getDimension(index, transform10.translationZ);
                        break;
                    } else {
                        break;
                    }
                case 54:
                    Layout layout40 = constraint.layout;
                    layout40.widthDefault = typedArray.getInt(index, layout40.widthDefault);
                    break;
                case 55:
                    Layout layout41 = constraint.layout;
                    layout41.heightDefault = typedArray.getInt(index, layout41.heightDefault);
                    break;
                case 56:
                    Layout layout42 = constraint.layout;
                    layout42.widthMax = typedArray.getDimensionPixelSize(index, layout42.widthMax);
                    break;
                case 57:
                    Layout layout43 = constraint.layout;
                    layout43.heightMax = typedArray.getDimensionPixelSize(index, layout43.heightMax);
                    break;
                case 58:
                    Layout layout44 = constraint.layout;
                    layout44.widthMin = typedArray.getDimensionPixelSize(index, layout44.widthMin);
                    break;
                case 59:
                    Layout layout45 = constraint.layout;
                    layout45.heightMin = typedArray.getDimensionPixelSize(index, layout45.heightMin);
                    break;
                case 60:
                    Transform transform11 = constraint.transform;
                    transform11.rotation = typedArray.getFloat(index, transform11.rotation);
                    break;
                case 61:
                    Layout layout46 = constraint.layout;
                    layout46.circleConstraint = n(typedArray, index, layout46.circleConstraint);
                    break;
                case 62:
                    Layout layout47 = constraint.layout;
                    layout47.circleRadius = typedArray.getDimensionPixelSize(index, layout47.circleRadius);
                    break;
                case 63:
                    Layout layout48 = constraint.layout;
                    layout48.circleAngle = typedArray.getFloat(index, layout48.circleAngle);
                    break;
                case 64:
                    Motion motion = constraint.motion;
                    motion.mAnimateRelativeTo = n(typedArray, index, motion.mAnimateRelativeTo);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        constraint.motion.mTransitionEasing = typedArray.getString(index);
                        break;
                    } else {
                        constraint.motion.mTransitionEasing = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                        break;
                    }
                case 66:
                    constraint.motion.mDrawPath = typedArray.getInt(index, 0);
                    break;
                case 67:
                    Motion motion2 = constraint.motion;
                    motion2.mPathRotate = typedArray.getFloat(index, motion2.mPathRotate);
                    break;
                case 68:
                    PropertySet propertySet4 = constraint.propertySet;
                    propertySet4.mProgress = typedArray.getFloat(index, propertySet4.mProgress);
                    break;
                case 69:
                    constraint.layout.widthPercent = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    constraint.layout.heightPercent = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    Layout layout49 = constraint.layout;
                    layout49.mBarrierDirection = typedArray.getInt(index, layout49.mBarrierDirection);
                    break;
                case 73:
                    Layout layout50 = constraint.layout;
                    layout50.mBarrierMargin = typedArray.getDimensionPixelSize(index, layout50.mBarrierMargin);
                    break;
                case 74:
                    constraint.layout.mReferenceIdString = typedArray.getString(index);
                    break;
                case 75:
                    Layout layout51 = constraint.layout;
                    layout51.mBarrierAllowsGoneWidgets = typedArray.getBoolean(index, layout51.mBarrierAllowsGoneWidgets);
                    break;
                case 76:
                    Motion motion3 = constraint.motion;
                    motion3.mPathMotionArc = typedArray.getInt(index, motion3.mPathMotionArc);
                    break;
                case 77:
                    constraint.layout.mConstraintTag = typedArray.getString(index);
                    break;
                case 78:
                    PropertySet propertySet5 = constraint.propertySet;
                    propertySet5.mVisibilityMode = typedArray.getInt(index, propertySet5.mVisibilityMode);
                    break;
                case 79:
                    Motion motion4 = constraint.motion;
                    motion4.mMotionStagger = typedArray.getFloat(index, motion4.mMotionStagger);
                    break;
                case 80:
                    Layout layout52 = constraint.layout;
                    layout52.constrainedWidth = typedArray.getBoolean(index, layout52.constrainedWidth);
                    break;
                case 81:
                    Layout layout53 = constraint.layout;
                    layout53.constrainedHeight = typedArray.getBoolean(index, layout53.constrainedHeight);
                    break;
                case 82:
                    Motion motion5 = constraint.motion;
                    motion5.mAnimateCircleAngleTo = typedArray.getInteger(index, motion5.mAnimateCircleAngleTo);
                    break;
                case 83:
                    Transform transform12 = constraint.transform;
                    transform12.transformPivotTarget = n(typedArray, index, transform12.transformPivotTarget);
                    break;
                case 84:
                    Motion motion6 = constraint.motion;
                    motion6.mQuantizeMotionSteps = typedArray.getInteger(index, motion6.mQuantizeMotionSteps);
                    break;
                case 85:
                    Motion motion7 = constraint.motion;
                    motion7.mQuantizeMotionPhase = typedArray.getFloat(index, motion7.mQuantizeMotionPhase);
                    break;
                case 86:
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                        Motion motion8 = constraint.motion;
                        if (motion8.mQuantizeInterpolatorID != -1) {
                            motion8.mQuantizeInterpolatorType = -2;
                            break;
                        } else {
                            break;
                        }
                    } else if (i2 == 3) {
                        constraint.motion.mQuantizeInterpolatorString = typedArray.getString(index);
                        if (constraint.motion.mQuantizeInterpolatorString.indexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) > 0) {
                            constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                            constraint.motion.mQuantizeInterpolatorType = -2;
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorType = -1;
                            break;
                        }
                    } else {
                        Motion motion9 = constraint.motion;
                        motion9.mQuantizeInterpolatorType = typedArray.getInteger(index, motion9.mQuantizeInterpolatorID);
                        break;
                    }
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + e.get(index));
                    break;
                case 88:
                case 89:
                case 90:
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + e.get(index));
                    break;
                case 91:
                    Layout layout54 = constraint.layout;
                    layout54.baselineToTop = n(typedArray, index, layout54.baselineToTop);
                    break;
                case 92:
                    Layout layout55 = constraint.layout;
                    layout55.baselineToBottom = n(typedArray, index, layout55.baselineToBottom);
                    break;
                case 93:
                    Layout layout56 = constraint.layout;
                    layout56.baselineMargin = typedArray.getDimensionPixelSize(index, layout56.baselineMargin);
                    break;
                case 94:
                    Layout layout57 = constraint.layout;
                    layout57.goneBaselineMargin = typedArray.getDimensionPixelSize(index, layout57.goneBaselineMargin);
                    break;
                case 95:
                    o(constraint.layout, typedArray, index, 0);
                    break;
                case 96:
                    o(constraint.layout, typedArray, index, 1);
                    break;
                case 97:
                    Layout layout58 = constraint.layout;
                    layout58.mWrapBehavior = typedArray.getInt(index, layout58.mWrapBehavior);
                    break;
            }
        }
        Layout layout59 = constraint.layout;
        if (layout59.mReferenceIdString != null) {
            layout59.mReferenceIds = null;
        }
    }

    public void readFallback(ConstraintSet constraintSet) {
        for (Integer num : constraintSet.c.keySet()) {
            int intValue = num.intValue();
            Constraint constraint = constraintSet.c.get(num);
            if (!this.c.containsKey(Integer.valueOf(intValue))) {
                this.c.put(Integer.valueOf(intValue), new Constraint());
            }
            Constraint constraint2 = this.c.get(Integer.valueOf(intValue));
            if (constraint2 != null) {
                Layout layout = constraint2.layout;
                if (!layout.mApply) {
                    layout.copyFrom(constraint.layout);
                }
                PropertySet propertySet = constraint2.propertySet;
                if (!propertySet.mApply) {
                    propertySet.copyFrom(constraint.propertySet);
                }
                Transform transform = constraint2.transform;
                if (!transform.mApply) {
                    transform.copyFrom(constraint.transform);
                }
                Motion motion = constraint2.motion;
                if (!motion.mApply) {
                    motion.copyFrom(constraint.motion);
                }
                for (String str : constraint.mCustomConstraints.keySet()) {
                    if (!constraint2.mCustomConstraints.containsKey(str)) {
                        constraint2.mCustomConstraints.put(str, constraint.mCustomConstraints.get(str));
                    }
                }
            }
        }
    }

    public void removeAttribute(String str) {
        this.f982a.remove(str);
    }

    public void removeFromHorizontalChain(int i) {
        Constraint constraint;
        if (!this.c.containsKey(Integer.valueOf(i)) || (constraint = this.c.get(Integer.valueOf(i))) == null) {
            return;
        }
        Layout layout = constraint.layout;
        int i2 = layout.leftToRight;
        int i3 = layout.rightToLeft;
        if (i2 == -1 && i3 == -1) {
            int i4 = layout.startToEnd;
            int i5 = layout.endToStart;
            if (i4 != -1 || i5 != -1) {
                if (i4 != -1 && i5 != -1) {
                    connect(i4, 7, i5, 6, 0);
                    connect(i5, 6, i2, 7, 0);
                } else if (i5 != -1) {
                    int i6 = layout.rightToRight;
                    if (i6 != -1) {
                        connect(i2, 7, i6, 7, 0);
                    } else {
                        int i7 = layout.leftToLeft;
                        if (i7 != -1) {
                            connect(i5, 6, i7, 6, 0);
                        }
                    }
                }
            }
            clear(i, 6);
            clear(i, 7);
            return;
        }
        if (i2 != -1 && i3 != -1) {
            connect(i2, 2, i3, 1, 0);
            connect(i3, 1, i2, 2, 0);
        } else {
            int i8 = layout.rightToRight;
            if (i8 != -1) {
                connect(i2, 2, i8, 2, 0);
            } else {
                int i9 = layout.leftToLeft;
                if (i9 != -1) {
                    connect(i3, 1, i9, 1, 0);
                }
            }
        }
        clear(i, 1);
        clear(i, 2);
    }

    public void removeFromVerticalChain(int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            Constraint constraint = this.c.get(Integer.valueOf(i));
            if (constraint == null) {
                return;
            }
            Layout layout = constraint.layout;
            int i2 = layout.topToBottom;
            int i3 = layout.bottomToTop;
            if (i2 != -1 || i3 != -1) {
                if (i2 != -1 && i3 != -1) {
                    connect(i2, 4, i3, 3, 0);
                    connect(i3, 3, i2, 4, 0);
                } else {
                    int i4 = layout.bottomToBottom;
                    if (i4 != -1) {
                        connect(i2, 4, i4, 4, 0);
                    } else {
                        int i5 = layout.topToTop;
                        if (i5 != -1) {
                            connect(i3, 3, i5, 3, 0);
                        }
                    }
                }
            }
        }
        clear(i, 3);
        clear(i, 4);
    }

    public void setAlpha(int i, float f2) {
        m(i).propertySet.alpha = f2;
    }

    public void setApplyElevation(int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            m(i).transform.applyElevation = z;
        }
    }

    public void setBarrierType(int i, int i2) {
        m(i).layout.mHelperType = i2;
    }

    public void setColorValue(int i, String str, int i2) {
        m(i).l(str, i2);
    }

    public void setDimensionRatio(int i, String str) {
        m(i).layout.dimensionRatio = str;
    }

    public void setEditorAbsoluteX(int i, int i2) {
        m(i).layout.editorAbsoluteX = i2;
    }

    public void setEditorAbsoluteY(int i, int i2) {
        m(i).layout.editorAbsoluteY = i2;
    }

    public void setElevation(int i, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            m(i).transform.elevation = f2;
            m(i).transform.applyElevation = true;
        }
    }

    public void setFloatValue(int i, String str, float f2) {
        m(i).m(str, f2);
    }

    public void setForceId(boolean z) {
        this.b = z;
    }

    public void setGoneMargin(int i, int i2, int i3) {
        Constraint m = m(i);
        switch (i2) {
            case 1:
                m.layout.goneLeftMargin = i3;
                return;
            case 2:
                m.layout.goneRightMargin = i3;
                return;
            case 3:
                m.layout.goneTopMargin = i3;
                return;
            case 4:
                m.layout.goneBottomMargin = i3;
                return;
            case 5:
                m.layout.goneBaselineMargin = i3;
                return;
            case 6:
                m.layout.goneStartMargin = i3;
                return;
            case 7:
                m.layout.goneEndMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setGuidelineBegin(int i, int i2) {
        m(i).layout.guideBegin = i2;
        m(i).layout.guideEnd = -1;
        m(i).layout.guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int i, int i2) {
        m(i).layout.guideEnd = i2;
        m(i).layout.guideBegin = -1;
        m(i).layout.guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int i, float f2) {
        m(i).layout.guidePercent = f2;
        m(i).layout.guideEnd = -1;
        m(i).layout.guideBegin = -1;
    }

    public void setHorizontalBias(int i, float f2) {
        m(i).layout.horizontalBias = f2;
    }

    public void setHorizontalChainStyle(int i, int i2) {
        m(i).layout.horizontalChainStyle = i2;
    }

    public void setHorizontalWeight(int i, float f2) {
        m(i).layout.horizontalWeight = f2;
    }

    public void setIntValue(int i, String str, int i2) {
        m(i).n(str, i2);
    }

    public void setLayoutWrapBehavior(int i, int i2) {
        if (i2 < 0 || i2 > 3) {
            return;
        }
        m(i).layout.mWrapBehavior = i2;
    }

    public void setMargin(int i, int i2, int i3) {
        Constraint m = m(i);
        switch (i2) {
            case 1:
                m.layout.leftMargin = i3;
                return;
            case 2:
                m.layout.rightMargin = i3;
                return;
            case 3:
                m.layout.topMargin = i3;
                return;
            case 4:
                m.layout.bottomMargin = i3;
                return;
            case 5:
                m.layout.baselineMargin = i3;
                return;
            case 6:
                m.layout.startMargin = i3;
                return;
            case 7:
                m.layout.endMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setReferencedIds(int i, int... iArr) {
        m(i).layout.mReferenceIds = iArr;
    }

    public void setRotation(int i, float f2) {
        m(i).transform.rotation = f2;
    }

    public void setRotationX(int i, float f2) {
        m(i).transform.rotationX = f2;
    }

    public void setRotationY(int i, float f2) {
        m(i).transform.rotationY = f2;
    }

    public void setScaleX(int i, float f2) {
        m(i).transform.scaleX = f2;
    }

    public void setScaleY(int i, float f2) {
        m(i).transform.scaleY = f2;
    }

    public void setStringValue(int i, String str, String str2) {
        m(i).o(str, str2);
    }

    public void setTransformPivot(int i, float f2, float f3) {
        Transform transform = m(i).transform;
        transform.transformPivotY = f3;
        transform.transformPivotX = f2;
    }

    public void setTransformPivotX(int i, float f2) {
        m(i).transform.transformPivotX = f2;
    }

    public void setTransformPivotY(int i, float f2) {
        m(i).transform.transformPivotY = f2;
    }

    public void setTranslation(int i, float f2, float f3) {
        Transform transform = m(i).transform;
        transform.translationX = f2;
        transform.translationY = f3;
    }

    public void setTranslationX(int i, float f2) {
        m(i).transform.translationX = f2;
    }

    public void setTranslationY(int i, float f2) {
        m(i).transform.translationY = f2;
    }

    public void setTranslationZ(int i, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            m(i).transform.translationZ = f2;
        }
    }

    public void setValidateOnParse(boolean z) {
    }

    public void setVerticalBias(int i, float f2) {
        m(i).layout.verticalBias = f2;
    }

    public void setVerticalChainStyle(int i, int i2) {
        m(i).layout.verticalChainStyle = i2;
    }

    public void setVerticalWeight(int i, float f2) {
        m(i).layout.verticalWeight = f2;
    }

    public void setVisibility(int i, int i2) {
        m(i).propertySet.visibility = i2;
    }

    public void setVisibilityMode(int i, int i2) {
        m(i).propertySet.mVisibilityMode = i2;
    }

    public void writeState(Writer writer, ConstraintLayout constraintLayout, int i) throws IOException {
        writer.write("\n---------------------------------------------\n");
        if ((i & 1) == 1) {
            new b(writer, constraintLayout, i).g();
        } else {
            new a(writer, constraintLayout, i).g();
        }
        writer.write("\n---------------------------------------------\n");
    }

    public final String x(int i) {
        switch (i) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public void clear(int i, int i2) {
        Constraint constraint;
        if (!this.c.containsKey(Integer.valueOf(i)) || (constraint = this.c.get(Integer.valueOf(i))) == null) {
            return;
        }
        switch (i2) {
            case 1:
                Layout layout = constraint.layout;
                layout.leftToRight = -1;
                layout.leftToLeft = -1;
                layout.leftMargin = -1;
                layout.goneLeftMargin = Integer.MIN_VALUE;
                return;
            case 2:
                Layout layout2 = constraint.layout;
                layout2.rightToRight = -1;
                layout2.rightToLeft = -1;
                layout2.rightMargin = -1;
                layout2.goneRightMargin = Integer.MIN_VALUE;
                return;
            case 3:
                Layout layout3 = constraint.layout;
                layout3.topToBottom = -1;
                layout3.topToTop = -1;
                layout3.topMargin = 0;
                layout3.goneTopMargin = Integer.MIN_VALUE;
                return;
            case 4:
                Layout layout4 = constraint.layout;
                layout4.bottomToTop = -1;
                layout4.bottomToBottom = -1;
                layout4.bottomMargin = 0;
                layout4.goneBottomMargin = Integer.MIN_VALUE;
                return;
            case 5:
                Layout layout5 = constraint.layout;
                layout5.baselineToBaseline = -1;
                layout5.baselineToTop = -1;
                layout5.baselineToBottom = -1;
                layout5.baselineMargin = 0;
                layout5.goneBaselineMargin = Integer.MIN_VALUE;
                return;
            case 6:
                Layout layout6 = constraint.layout;
                layout6.startToEnd = -1;
                layout6.startToStart = -1;
                layout6.startMargin = 0;
                layout6.goneStartMargin = Integer.MIN_VALUE;
                return;
            case 7:
                Layout layout7 = constraint.layout;
                layout7.endToStart = -1;
                layout7.endToEnd = -1;
                layout7.endMargin = 0;
                layout7.goneEndMargin = Integer.MIN_VALUE;
                return;
            case 8:
                Layout layout8 = constraint.layout;
                layout8.circleAngle = -1.0f;
                layout8.circleRadius = -1;
                layout8.circleConstraint = -1;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void clone(ConstraintSet constraintSet) {
        this.c.clear();
        for (Integer num : constraintSet.c.keySet()) {
            Constraint constraint = constraintSet.c.get(num);
            if (constraint != null) {
                this.c.put(num, constraint.m8clone());
            }
        }
    }

    public void centerHorizontally(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(i, i2, 2, 0, i2, 1, 0, 0.5f);
        }
    }

    public void centerHorizontallyRtl(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(i, i2, 7, 0, i2, 6, 0, 0.5f);
        }
    }

    public void centerVertically(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(i, i2, 4, 0, i2, 3, 0, 0.5f);
        }
    }

    public void clone(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.c.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.c.containsKey(Integer.valueOf(id))) {
                this.c.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.c.get(Integer.valueOf(id));
            if (constraint != null) {
                constraint.mCustomConstraints = ConstraintAttribute.extractAttributes(this.f982a, childAt);
                constraint.h(id, layoutParams);
                constraint.propertySet.visibility = childAt.getVisibility();
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 17) {
                    constraint.propertySet.alpha = childAt.getAlpha();
                    constraint.transform.rotation = childAt.getRotation();
                    constraint.transform.rotationX = childAt.getRotationX();
                    constraint.transform.rotationY = childAt.getRotationY();
                    constraint.transform.scaleX = childAt.getScaleX();
                    constraint.transform.scaleY = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        Transform transform = constraint.transform;
                        transform.transformPivotX = pivotX;
                        transform.transformPivotY = pivotY;
                    }
                    constraint.transform.translationX = childAt.getTranslationX();
                    constraint.transform.translationY = childAt.getTranslationY();
                    if (i2 >= 21) {
                        constraint.transform.translationZ = childAt.getTranslationZ();
                        Transform transform2 = constraint.transform;
                        if (transform2.applyElevation) {
                            transform2.elevation = childAt.getElevation();
                        }
                    }
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    constraint.layout.mBarrierAllowsGoneWidgets = barrier.getAllowsGoneWidget();
                    constraint.layout.mReferenceIds = barrier.getReferencedIds();
                    constraint.layout.mBarrierDirection = barrier.getType();
                    constraint.layout.mBarrierMargin = barrier.getMargin();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x01cb, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void load(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instructions count: 560
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public void readFallback(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.c.containsKey(Integer.valueOf(id))) {
                this.c.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.c.get(Integer.valueOf(id));
            if (constraint != null) {
                if (!constraint.layout.mApply) {
                    constraint.h(id, layoutParams);
                    if (childAt instanceof ConstraintHelper) {
                        constraint.layout.mReferenceIds = ((ConstraintHelper) childAt).getReferencedIds();
                        if (childAt instanceof Barrier) {
                            Barrier barrier = (Barrier) childAt;
                            constraint.layout.mBarrierAllowsGoneWidgets = barrier.getAllowsGoneWidget();
                            constraint.layout.mBarrierDirection = barrier.getType();
                            constraint.layout.mBarrierMargin = barrier.getMargin();
                        }
                    }
                    constraint.layout.mApply = true;
                }
                PropertySet propertySet = constraint.propertySet;
                if (!propertySet.mApply) {
                    propertySet.visibility = childAt.getVisibility();
                    constraint.propertySet.alpha = childAt.getAlpha();
                    constraint.propertySet.mApply = true;
                }
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 17) {
                    Transform transform = constraint.transform;
                    if (!transform.mApply) {
                        transform.mApply = true;
                        transform.rotation = childAt.getRotation();
                        constraint.transform.rotationX = childAt.getRotationX();
                        constraint.transform.rotationY = childAt.getRotationY();
                        constraint.transform.scaleX = childAt.getScaleX();
                        constraint.transform.scaleY = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (pivotX != 0.0d || pivotY != 0.0d) {
                            Transform transform2 = constraint.transform;
                            transform2.transformPivotX = pivotX;
                            transform2.transformPivotY = pivotY;
                        }
                        constraint.transform.translationX = childAt.getTranslationX();
                        constraint.transform.translationY = childAt.getTranslationY();
                        if (i2 >= 21) {
                            constraint.transform.translationZ = childAt.getTranslationZ();
                            Transform transform3 = constraint.transform;
                            if (transform3.applyElevation) {
                                transform3.elevation = childAt.getElevation();
                            }
                        }
                    }
                }
            }
        }
    }

    public void clone(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.c.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraints.getChildAt(i);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.c.containsKey(Integer.valueOf(id))) {
                this.c.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.c.get(Integer.valueOf(id));
            if (constraint != null) {
                if (childAt instanceof ConstraintHelper) {
                    constraint.j((ConstraintHelper) childAt, id, layoutParams);
                }
                constraint.i(id, layoutParams);
            }
        }
    }

    public void connect(int i, int i2, int i3, int i4) {
        if (!this.c.containsKey(Integer.valueOf(i))) {
            this.c.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = this.c.get(Integer.valueOf(i));
        if (constraint == null) {
            return;
        }
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    Layout layout = constraint.layout;
                    layout.leftToLeft = i3;
                    layout.leftToRight = -1;
                    return;
                } else if (i4 == 2) {
                    Layout layout2 = constraint.layout;
                    layout2.leftToRight = i3;
                    layout2.leftToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("left to " + x(i4) + " undefined");
                }
            case 2:
                if (i4 == 1) {
                    Layout layout3 = constraint.layout;
                    layout3.rightToLeft = i3;
                    layout3.rightToRight = -1;
                    return;
                } else if (i4 == 2) {
                    Layout layout4 = constraint.layout;
                    layout4.rightToRight = i3;
                    layout4.rightToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
            case 3:
                if (i4 == 3) {
                    Layout layout5 = constraint.layout;
                    layout5.topToTop = i3;
                    layout5.topToBottom = -1;
                    layout5.baselineToBaseline = -1;
                    layout5.baselineToTop = -1;
                    layout5.baselineToBottom = -1;
                    return;
                } else if (i4 == 4) {
                    Layout layout6 = constraint.layout;
                    layout6.topToBottom = i3;
                    layout6.topToTop = -1;
                    layout6.baselineToBaseline = -1;
                    layout6.baselineToTop = -1;
                    layout6.baselineToBottom = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
            case 4:
                if (i4 == 4) {
                    Layout layout7 = constraint.layout;
                    layout7.bottomToBottom = i3;
                    layout7.bottomToTop = -1;
                    layout7.baselineToBaseline = -1;
                    layout7.baselineToTop = -1;
                    layout7.baselineToBottom = -1;
                    return;
                } else if (i4 == 3) {
                    Layout layout8 = constraint.layout;
                    layout8.bottomToTop = i3;
                    layout8.bottomToBottom = -1;
                    layout8.baselineToBaseline = -1;
                    layout8.baselineToTop = -1;
                    layout8.baselineToBottom = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
            case 5:
                if (i4 == 5) {
                    Layout layout9 = constraint.layout;
                    layout9.baselineToBaseline = i3;
                    layout9.bottomToBottom = -1;
                    layout9.bottomToTop = -1;
                    layout9.topToTop = -1;
                    layout9.topToBottom = -1;
                    return;
                } else if (i4 == 3) {
                    Layout layout10 = constraint.layout;
                    layout10.baselineToTop = i3;
                    layout10.bottomToBottom = -1;
                    layout10.bottomToTop = -1;
                    layout10.topToTop = -1;
                    layout10.topToBottom = -1;
                    return;
                } else if (i4 == 4) {
                    Layout layout11 = constraint.layout;
                    layout11.baselineToBottom = i3;
                    layout11.bottomToBottom = -1;
                    layout11.bottomToTop = -1;
                    layout11.topToTop = -1;
                    layout11.topToBottom = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
            case 6:
                if (i4 == 6) {
                    Layout layout12 = constraint.layout;
                    layout12.startToStart = i3;
                    layout12.startToEnd = -1;
                    return;
                } else if (i4 == 7) {
                    Layout layout13 = constraint.layout;
                    layout13.startToEnd = i3;
                    layout13.startToStart = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
            case 7:
                if (i4 == 7) {
                    Layout layout14 = constraint.layout;
                    layout14.endToEnd = i3;
                    layout14.endToStart = -1;
                    return;
                } else if (i4 == 6) {
                    Layout layout15 = constraint.layout;
                    layout15.endToStart = i3;
                    layout15.endToEnd = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + x(i4) + " undefined");
                }
            default:
                throw new IllegalArgumentException(x(i2) + " to " + x(i4) + " unknown");
        }
    }
}
