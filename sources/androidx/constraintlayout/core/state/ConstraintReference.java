package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class ConstraintReference implements Reference {
    public float C;
    public float D;
    public Dimension F;
    public Dimension G;
    public Object H;
    public ConstraintWidget I;
    public HashMap<String, Integer> J;
    public HashMap<String, Float> K;

    /* renamed from: a  reason: collision with root package name */
    public Object f894a;
    public final State b;
    public String c = null;
    public Facade d = null;
    public int e = 0;
    public int f = 0;
    public float g = -1.0f;
    public float h = -1.0f;
    public float i = 0.5f;
    public float j = 0.5f;
    public int mMarginLeft = 0;
    public int mMarginRight = 0;
    public int mMarginStart = 0;
    public int mMarginEnd = 0;
    public int mMarginTop = 0;
    public int mMarginBottom = 0;
    public int mMarginLeftGone = 0;
    public int mMarginRightGone = 0;
    public int mMarginStartGone = 0;
    public int mMarginEndGone = 0;
    public int mMarginTopGone = 0;
    public int mMarginBottomGone = 0;
    public int k = 0;
    public int l = 0;
    public float m = Float.NaN;
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
    public int x = 0;
    public Object mLeftToLeft = null;
    public Object mLeftToRight = null;
    public Object mRightToLeft = null;
    public Object mRightToRight = null;
    public Object mStartToStart = null;
    public Object mStartToEnd = null;
    public Object mEndToStart = null;
    public Object mEndToEnd = null;
    public Object mTopToTop = null;
    public Object mTopToBottom = null;
    public Object mBottomToTop = null;
    public Object mBottomToBottom = null;
    public Object y = null;
    public Object z = null;
    public Object A = null;
    public Object B = null;
    public State.Constraint E = null;

    /* loaded from: classes.dex */
    public interface ConstraintReferenceFactory {
        ConstraintReference create(State state);
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f895a;

        static {
            int[] iArr = new int[State.Constraint.values().length];
            f895a = iArr;
            try {
                iArr[State.Constraint.LEFT_TO_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f895a[State.Constraint.LEFT_TO_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f895a[State.Constraint.RIGHT_TO_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f895a[State.Constraint.RIGHT_TO_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f895a[State.Constraint.START_TO_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f895a[State.Constraint.START_TO_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f895a[State.Constraint.END_TO_START.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f895a[State.Constraint.END_TO_END.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f895a[State.Constraint.TOP_TO_TOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f895a[State.Constraint.TOP_TO_BOTTOM.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f895a[State.Constraint.BOTTOM_TO_TOP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f895a[State.Constraint.BOTTOM_TO_BOTTOM.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f895a[State.Constraint.BASELINE_TO_BOTTOM.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f895a[State.Constraint.BASELINE_TO_TOP.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f895a[State.Constraint.BASELINE_TO_BASELINE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f895a[State.Constraint.CIRCULAR_CONSTRAINT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f895a[State.Constraint.CENTER_HORIZONTALLY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f895a[State.Constraint.CENTER_VERTICALLY.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b extends Exception {
        private final ArrayList<String> mErrors;

        public b(ArrayList<String> arrayList) {
            this.mErrors = arrayList;
        }

        public ArrayList<String> getErrors() {
            return this.mErrors;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return "IncorrectConstraintException: " + this.mErrors.toString();
        }
    }

    public ConstraintReference(State state) {
        Object obj = Dimension.WRAP_DIMENSION;
        this.F = Dimension.Fixed(obj);
        this.G = Dimension.Fixed(obj);
        this.J = new HashMap<>();
        this.K = new HashMap<>();
        this.b = state;
    }

    public final void a(ConstraintWidget constraintWidget, Object obj, State.Constraint constraint) {
        ConstraintWidget d = d(obj);
        if (d == null) {
            return;
        }
        int[] iArr = a.f895a;
        int i = iArr[constraint.ordinal()];
        switch (iArr[constraint.ordinal()]) {
            case 1:
                ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                constraintWidget.getAnchor(type).connect(d.getAnchor(type), this.mMarginLeft, this.mMarginLeftGone, false);
                return;
            case 2:
                constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).connect(d.getAnchor(ConstraintAnchor.Type.RIGHT), this.mMarginLeft, this.mMarginLeftGone, false);
                return;
            case 3:
                constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(d.getAnchor(ConstraintAnchor.Type.LEFT), this.mMarginRight, this.mMarginRightGone, false);
                return;
            case 4:
                ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
                constraintWidget.getAnchor(type2).connect(d.getAnchor(type2), this.mMarginRight, this.mMarginRightGone, false);
                return;
            case 5:
                ConstraintAnchor.Type type3 = ConstraintAnchor.Type.LEFT;
                constraintWidget.getAnchor(type3).connect(d.getAnchor(type3), this.mMarginStart, this.mMarginStartGone, false);
                return;
            case 6:
                constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).connect(d.getAnchor(ConstraintAnchor.Type.RIGHT), this.mMarginStart, this.mMarginStartGone, false);
                return;
            case 7:
                constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).connect(d.getAnchor(ConstraintAnchor.Type.LEFT), this.mMarginEnd, this.mMarginEndGone, false);
                return;
            case 8:
                ConstraintAnchor.Type type4 = ConstraintAnchor.Type.RIGHT;
                constraintWidget.getAnchor(type4).connect(d.getAnchor(type4), this.mMarginEnd, this.mMarginEndGone, false);
                return;
            case 9:
                ConstraintAnchor.Type type5 = ConstraintAnchor.Type.TOP;
                constraintWidget.getAnchor(type5).connect(d.getAnchor(type5), this.mMarginTop, this.mMarginTopGone, false);
                return;
            case 10:
                constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).connect(d.getAnchor(ConstraintAnchor.Type.BOTTOM), this.mMarginTop, this.mMarginTopGone, false);
                return;
            case 11:
                constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(d.getAnchor(ConstraintAnchor.Type.TOP), this.mMarginBottom, this.mMarginBottomGone, false);
                return;
            case 12:
                ConstraintAnchor.Type type6 = ConstraintAnchor.Type.BOTTOM;
                constraintWidget.getAnchor(type6).connect(d.getAnchor(type6), this.mMarginBottom, this.mMarginBottomGone, false);
                return;
            case 13:
                constraintWidget.immediateConnect(ConstraintAnchor.Type.BASELINE, d, ConstraintAnchor.Type.BOTTOM, this.k, this.l);
                return;
            case 14:
                constraintWidget.immediateConnect(ConstraintAnchor.Type.BASELINE, d, ConstraintAnchor.Type.TOP, this.k, this.l);
                return;
            case 15:
                ConstraintAnchor.Type type7 = ConstraintAnchor.Type.BASELINE;
                constraintWidget.immediateConnect(type7, d, type7, this.k, this.l);
                return;
            case 16:
                constraintWidget.connectCircularConstraint(d, this.C, (int) this.D);
                return;
            default:
                return;
        }
    }

    public void addCustomColor(String str, int i) {
        this.J.put(str, Integer.valueOf(i));
    }

    public void addCustomFloat(String str, float f) {
        if (this.K == null) {
            this.K = new HashMap<>();
        }
        this.K.put(str, Float.valueOf(f));
    }

    public ConstraintReference alpha(float f) {
        this.u = f;
        return this;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void apply() {
        if (this.I == null) {
            return;
        }
        Facade facade = this.d;
        if (facade != null) {
            facade.apply();
        }
        this.F.apply(this.b, this.I, 0);
        this.G.apply(this.b, this.I, 1);
        b();
        a(this.I, this.mLeftToLeft, State.Constraint.LEFT_TO_LEFT);
        a(this.I, this.mLeftToRight, State.Constraint.LEFT_TO_RIGHT);
        a(this.I, this.mRightToLeft, State.Constraint.RIGHT_TO_LEFT);
        a(this.I, this.mRightToRight, State.Constraint.RIGHT_TO_RIGHT);
        a(this.I, this.mStartToStart, State.Constraint.START_TO_START);
        a(this.I, this.mStartToEnd, State.Constraint.START_TO_END);
        a(this.I, this.mEndToStart, State.Constraint.END_TO_START);
        a(this.I, this.mEndToEnd, State.Constraint.END_TO_END);
        a(this.I, this.mTopToTop, State.Constraint.TOP_TO_TOP);
        a(this.I, this.mTopToBottom, State.Constraint.TOP_TO_BOTTOM);
        a(this.I, this.mBottomToTop, State.Constraint.BOTTOM_TO_TOP);
        a(this.I, this.mBottomToBottom, State.Constraint.BOTTOM_TO_BOTTOM);
        a(this.I, this.y, State.Constraint.BASELINE_TO_BASELINE);
        a(this.I, this.z, State.Constraint.BASELINE_TO_TOP);
        a(this.I, this.A, State.Constraint.BASELINE_TO_BOTTOM);
        a(this.I, this.B, State.Constraint.CIRCULAR_CONSTRAINT);
        int i = this.e;
        if (i != 0) {
            this.I.setHorizontalChainStyle(i);
        }
        int i2 = this.f;
        if (i2 != 0) {
            this.I.setVerticalChainStyle(i2);
        }
        float f = this.g;
        if (f != -1.0f) {
            this.I.setHorizontalWeight(f);
        }
        float f2 = this.h;
        if (f2 != -1.0f) {
            this.I.setVerticalWeight(f2);
        }
        this.I.setHorizontalBiasPercent(this.i);
        this.I.setVerticalBiasPercent(this.j);
        ConstraintWidget constraintWidget = this.I;
        WidgetFrame widgetFrame = constraintWidget.frame;
        widgetFrame.pivotX = this.m;
        widgetFrame.pivotY = this.n;
        widgetFrame.rotationX = this.o;
        widgetFrame.rotationY = this.p;
        widgetFrame.rotationZ = this.q;
        widgetFrame.translationX = this.r;
        widgetFrame.translationY = this.s;
        widgetFrame.translationZ = this.t;
        widgetFrame.scaleX = this.v;
        widgetFrame.scaleY = this.w;
        widgetFrame.alpha = this.u;
        int i3 = this.x;
        widgetFrame.visibility = i3;
        constraintWidget.setVisibility(i3);
        HashMap<String, Integer> hashMap = this.J;
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                this.I.frame.setCustomAttribute(str, TypedValues.Custom.TYPE_COLOR, this.J.get(str).intValue());
            }
        }
        HashMap<String, Float> hashMap2 = this.K;
        if (hashMap2 != null) {
            for (String str2 : hashMap2.keySet()) {
                this.I.frame.setCustomAttribute(str2, TypedValues.Custom.TYPE_FLOAT, this.K.get(str2).floatValue());
            }
        }
    }

    public final void b() {
        this.mLeftToLeft = c(this.mLeftToLeft);
        this.mLeftToRight = c(this.mLeftToRight);
        this.mRightToLeft = c(this.mRightToLeft);
        this.mRightToRight = c(this.mRightToRight);
        this.mStartToStart = c(this.mStartToStart);
        this.mStartToEnd = c(this.mStartToEnd);
        this.mEndToStart = c(this.mEndToStart);
        this.mEndToEnd = c(this.mEndToEnd);
        this.mTopToTop = c(this.mTopToTop);
        this.mTopToBottom = c(this.mTopToBottom);
        this.mBottomToTop = c(this.mBottomToTop);
        this.mBottomToBottom = c(this.mBottomToBottom);
        this.y = c(this.y);
        this.z = c(this.z);
        this.A = c(this.A);
    }

    public ConstraintReference baseline() {
        this.E = State.Constraint.BASELINE_TO_BASELINE;
        return this;
    }

    public ConstraintReference baselineToBaseline(Object obj) {
        this.E = State.Constraint.BASELINE_TO_BASELINE;
        this.y = obj;
        return this;
    }

    public ConstraintReference baselineToBottom(Object obj) {
        this.E = State.Constraint.BASELINE_TO_BOTTOM;
        this.A = obj;
        return this;
    }

    public ConstraintReference baselineToTop(Object obj) {
        this.E = State.Constraint.BASELINE_TO_TOP;
        this.z = obj;
        return this;
    }

    public ConstraintReference bias(float f) {
        State.Constraint constraint = this.E;
        if (constraint == null) {
            return this;
        }
        int i = a.f895a[constraint.ordinal()];
        if (i != 17) {
            if (i != 18) {
                switch (i) {
                }
                return this;
            }
            this.j = f;
            return this;
        }
        this.i = f;
        return this;
    }

    public ConstraintReference bottom() {
        if (this.mBottomToTop != null) {
            this.E = State.Constraint.BOTTOM_TO_TOP;
        } else {
            this.E = State.Constraint.BOTTOM_TO_BOTTOM;
        }
        return this;
    }

    public ConstraintReference bottomToBottom(Object obj) {
        this.E = State.Constraint.BOTTOM_TO_BOTTOM;
        this.mBottomToBottom = obj;
        return this;
    }

    public ConstraintReference bottomToTop(Object obj) {
        this.E = State.Constraint.BOTTOM_TO_TOP;
        this.mBottomToTop = obj;
        return this;
    }

    public final Object c(Object obj) {
        if (obj == null) {
            return null;
        }
        return !(obj instanceof ConstraintReference) ? this.b.b(obj) : obj;
    }

    public ConstraintReference centerHorizontally(Object obj) {
        Object c = c(obj);
        this.mStartToStart = c;
        this.mEndToEnd = c;
        this.E = State.Constraint.CENTER_HORIZONTALLY;
        this.i = 0.5f;
        return this;
    }

    public ConstraintReference centerVertically(Object obj) {
        Object c = c(obj);
        this.mTopToTop = c;
        this.mBottomToBottom = c;
        this.E = State.Constraint.CENTER_VERTICALLY;
        this.j = 0.5f;
        return this;
    }

    public ConstraintReference circularConstraint(Object obj, float f, float f2) {
        this.B = c(obj);
        this.C = f;
        this.D = f2;
        this.E = State.Constraint.CIRCULAR_CONSTRAINT;
        return this;
    }

    public ConstraintReference clear() {
        State.Constraint constraint = this.E;
        if (constraint != null) {
            switch (a.f895a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.mLeftToLeft = null;
                    this.mLeftToRight = null;
                    this.mMarginLeft = 0;
                    this.mMarginLeftGone = 0;
                    break;
                case 3:
                case 4:
                    this.mRightToLeft = null;
                    this.mRightToRight = null;
                    this.mMarginRight = 0;
                    this.mMarginRightGone = 0;
                    break;
                case 5:
                case 6:
                    this.mStartToStart = null;
                    this.mStartToEnd = null;
                    this.mMarginStart = 0;
                    this.mMarginStartGone = 0;
                    break;
                case 7:
                case 8:
                    this.mEndToStart = null;
                    this.mEndToEnd = null;
                    this.mMarginEnd = 0;
                    this.mMarginEndGone = 0;
                    break;
                case 9:
                case 10:
                    this.mTopToTop = null;
                    this.mTopToBottom = null;
                    this.mMarginTop = 0;
                    this.mMarginTopGone = 0;
                    break;
                case 11:
                case 12:
                    this.mBottomToTop = null;
                    this.mBottomToBottom = null;
                    this.mMarginBottom = 0;
                    this.mMarginBottomGone = 0;
                    break;
                case 15:
                    this.y = null;
                    break;
                case 16:
                    this.B = null;
                    break;
            }
        } else {
            this.mLeftToLeft = null;
            this.mLeftToRight = null;
            this.mMarginLeft = 0;
            this.mRightToLeft = null;
            this.mRightToRight = null;
            this.mMarginRight = 0;
            this.mStartToStart = null;
            this.mStartToEnd = null;
            this.mMarginStart = 0;
            this.mEndToStart = null;
            this.mEndToEnd = null;
            this.mMarginEnd = 0;
            this.mTopToTop = null;
            this.mTopToBottom = null;
            this.mMarginTop = 0;
            this.mBottomToTop = null;
            this.mBottomToBottom = null;
            this.mMarginBottom = 0;
            this.y = null;
            this.B = null;
            this.i = 0.5f;
            this.j = 0.5f;
            this.mMarginLeftGone = 0;
            this.mMarginRightGone = 0;
            this.mMarginStartGone = 0;
            this.mMarginEndGone = 0;
            this.mMarginTopGone = 0;
            this.mMarginBottomGone = 0;
        }
        return this;
    }

    public ConstraintReference clearHorizontal() {
        start().clear();
        end().clear();
        left().clear();
        right().clear();
        return this;
    }

    public ConstraintReference clearVertical() {
        top().clear();
        baseline().clear();
        bottom().clear();
        return this;
    }

    public ConstraintWidget createConstraintWidget() {
        return new ConstraintWidget(getWidth().a(), getHeight().a());
    }

    public final ConstraintWidget d(Object obj) {
        if (obj instanceof Reference) {
            return ((Reference) obj).getConstraintWidget();
        }
        return null;
    }

    public ConstraintReference end() {
        if (this.mEndToStart != null) {
            this.E = State.Constraint.END_TO_START;
        } else {
            this.E = State.Constraint.END_TO_END;
        }
        return this;
    }

    public ConstraintReference endToEnd(Object obj) {
        this.E = State.Constraint.END_TO_END;
        this.mEndToEnd = obj;
        return this;
    }

    public ConstraintReference endToStart(Object obj) {
        this.E = State.Constraint.END_TO_START;
        this.mEndToStart = obj;
        return this;
    }

    public float getAlpha() {
        return this.u;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if (this.I == null) {
            ConstraintWidget createConstraintWidget = createConstraintWidget();
            this.I = createConstraintWidget;
            createConstraintWidget.setCompanionWidget(this.H);
        }
        return this.I;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public Facade getFacade() {
        return this.d;
    }

    public Dimension getHeight() {
        return this.G;
    }

    public int getHorizontalChainStyle() {
        return this.e;
    }

    public float getHorizontalChainWeight() {
        return this.g;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public Object getKey() {
        return this.f894a;
    }

    public float getPivotX() {
        return this.m;
    }

    public float getPivotY() {
        return this.n;
    }

    public float getRotationX() {
        return this.o;
    }

    public float getRotationY() {
        return this.p;
    }

    public float getRotationZ() {
        return this.q;
    }

    public float getScaleX() {
        return this.v;
    }

    public float getScaleY() {
        return this.w;
    }

    public String getTag() {
        return this.c;
    }

    public float getTranslationX() {
        return this.r;
    }

    public float getTranslationY() {
        return this.s;
    }

    public float getTranslationZ() {
        return this.t;
    }

    public int getVerticalChainStyle(int i) {
        return this.f;
    }

    public float getVerticalChainWeight() {
        return this.h;
    }

    public Object getView() {
        return this.H;
    }

    public Dimension getWidth() {
        return this.F;
    }

    public ConstraintReference height(Dimension dimension) {
        return setHeight(dimension);
    }

    public ConstraintReference horizontalBias(float f) {
        this.i = f;
        return this;
    }

    public ConstraintReference left() {
        if (this.mLeftToLeft != null) {
            this.E = State.Constraint.LEFT_TO_LEFT;
        } else {
            this.E = State.Constraint.LEFT_TO_RIGHT;
        }
        return this;
    }

    public ConstraintReference leftToLeft(Object obj) {
        this.E = State.Constraint.LEFT_TO_LEFT;
        this.mLeftToLeft = obj;
        return this;
    }

    public ConstraintReference leftToRight(Object obj) {
        this.E = State.Constraint.LEFT_TO_RIGHT;
        this.mLeftToRight = obj;
        return this;
    }

    public ConstraintReference margin(Object obj) {
        return margin(this.b.convertDimension(obj));
    }

    public ConstraintReference marginGone(Object obj) {
        return marginGone(this.b.convertDimension(obj));
    }

    public ConstraintReference pivotX(float f) {
        this.m = f;
        return this;
    }

    public ConstraintReference pivotY(float f) {
        this.n = f;
        return this;
    }

    public ConstraintReference right() {
        if (this.mRightToLeft != null) {
            this.E = State.Constraint.RIGHT_TO_LEFT;
        } else {
            this.E = State.Constraint.RIGHT_TO_RIGHT;
        }
        return this;
    }

    public ConstraintReference rightToLeft(Object obj) {
        this.E = State.Constraint.RIGHT_TO_LEFT;
        this.mRightToLeft = obj;
        return this;
    }

    public ConstraintReference rightToRight(Object obj) {
        this.E = State.Constraint.RIGHT_TO_RIGHT;
        this.mRightToRight = obj;
        return this;
    }

    public ConstraintReference rotationX(float f) {
        this.o = f;
        return this;
    }

    public ConstraintReference rotationY(float f) {
        this.p = f;
        return this;
    }

    public ConstraintReference rotationZ(float f) {
        this.q = f;
        return this;
    }

    public ConstraintReference scaleX(float f) {
        this.v = f;
        return this;
    }

    public ConstraintReference scaleY(float f) {
        this.w = f;
        return this;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return;
        }
        this.I = constraintWidget;
        constraintWidget.setCompanionWidget(this.H);
    }

    public void setFacade(Facade facade) {
        this.d = facade;
        if (facade != null) {
            setConstraintWidget(facade.getConstraintWidget());
        }
    }

    public ConstraintReference setHeight(Dimension dimension) {
        this.G = dimension;
        return this;
    }

    public void setHorizontalChainStyle(int i) {
        this.e = i;
    }

    public void setHorizontalChainWeight(float f) {
        this.g = f;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void setKey(Object obj) {
        this.f894a = obj;
    }

    public void setTag(String str) {
        this.c = str;
    }

    public void setVerticalChainStyle(int i) {
        this.f = i;
    }

    public void setVerticalChainWeight(float f) {
        this.h = f;
    }

    public void setView(Object obj) {
        this.H = obj;
        ConstraintWidget constraintWidget = this.I;
        if (constraintWidget != null) {
            constraintWidget.setCompanionWidget(obj);
        }
    }

    public ConstraintReference setWidth(Dimension dimension) {
        this.F = dimension;
        return this;
    }

    public ConstraintReference start() {
        if (this.mStartToStart != null) {
            this.E = State.Constraint.START_TO_START;
        } else {
            this.E = State.Constraint.START_TO_END;
        }
        return this;
    }

    public ConstraintReference startToEnd(Object obj) {
        this.E = State.Constraint.START_TO_END;
        this.mStartToEnd = obj;
        return this;
    }

    public ConstraintReference startToStart(Object obj) {
        this.E = State.Constraint.START_TO_START;
        this.mStartToStart = obj;
        return this;
    }

    public ConstraintReference top() {
        if (this.mTopToTop != null) {
            this.E = State.Constraint.TOP_TO_TOP;
        } else {
            this.E = State.Constraint.TOP_TO_BOTTOM;
        }
        return this;
    }

    public ConstraintReference topToBottom(Object obj) {
        this.E = State.Constraint.TOP_TO_BOTTOM;
        this.mTopToBottom = obj;
        return this;
    }

    public ConstraintReference topToTop(Object obj) {
        this.E = State.Constraint.TOP_TO_TOP;
        this.mTopToTop = obj;
        return this;
    }

    public ConstraintReference translationX(float f) {
        this.r = f;
        return this;
    }

    public ConstraintReference translationY(float f) {
        this.s = f;
        return this;
    }

    public ConstraintReference translationZ(float f) {
        this.t = f;
        return this;
    }

    public void validate() throws b {
        ArrayList arrayList = new ArrayList();
        if (this.mLeftToLeft != null && this.mLeftToRight != null) {
            arrayList.add("LeftToLeft and LeftToRight both defined");
        }
        if (this.mRightToLeft != null && this.mRightToRight != null) {
            arrayList.add("RightToLeft and RightToRight both defined");
        }
        if (this.mStartToStart != null && this.mStartToEnd != null) {
            arrayList.add("StartToStart and StartToEnd both defined");
        }
        if (this.mEndToStart != null && this.mEndToEnd != null) {
            arrayList.add("EndToStart and EndToEnd both defined");
        }
        if ((this.mLeftToLeft != null || this.mLeftToRight != null || this.mRightToLeft != null || this.mRightToRight != null) && (this.mStartToStart != null || this.mStartToEnd != null || this.mEndToStart != null || this.mEndToEnd != null)) {
            arrayList.add("Both left/right and start/end constraints defined");
        }
        if (arrayList.size() > 0) {
            throw new b(arrayList);
        }
    }

    public ConstraintReference verticalBias(float f) {
        this.j = f;
        return this;
    }

    public ConstraintReference visibility(int i) {
        this.x = i;
        return this;
    }

    public ConstraintReference width(Dimension dimension) {
        return setWidth(dimension);
    }

    public ConstraintReference margin(int i) {
        State.Constraint constraint = this.E;
        if (constraint != null) {
            switch (a.f895a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.mMarginLeft = i;
                    break;
                case 3:
                case 4:
                    this.mMarginRight = i;
                    break;
                case 5:
                case 6:
                    this.mMarginStart = i;
                    break;
                case 7:
                case 8:
                    this.mMarginEnd = i;
                    break;
                case 9:
                case 10:
                    this.mMarginTop = i;
                    break;
                case 11:
                case 12:
                    this.mMarginBottom = i;
                    break;
                case 13:
                case 14:
                case 15:
                    this.k = i;
                    break;
                case 16:
                    this.D = i;
                    break;
            }
        } else {
            this.mMarginLeft = i;
            this.mMarginRight = i;
            this.mMarginStart = i;
            this.mMarginEnd = i;
            this.mMarginTop = i;
            this.mMarginBottom = i;
        }
        return this;
    }

    public ConstraintReference marginGone(int i) {
        State.Constraint constraint = this.E;
        if (constraint != null) {
            switch (a.f895a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.mMarginLeftGone = i;
                    break;
                case 3:
                case 4:
                    this.mMarginRightGone = i;
                    break;
                case 5:
                case 6:
                    this.mMarginStartGone = i;
                    break;
                case 7:
                case 8:
                    this.mMarginEndGone = i;
                    break;
                case 9:
                case 10:
                    this.mMarginTopGone = i;
                    break;
                case 11:
                case 12:
                    this.mMarginBottomGone = i;
                    break;
                case 13:
                case 14:
                case 15:
                    this.l = i;
                    break;
            }
        } else {
            this.mMarginLeftGone = i;
            this.mMarginRightGone = i;
            this.mMarginStartGone = i;
            this.mMarginEndGone = i;
            this.mMarginTopGone = i;
            this.mMarginBottomGone = i;
        }
        return this;
    }
}
