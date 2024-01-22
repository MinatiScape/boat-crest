package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.Motion;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class Transition implements TypedValues {
    public static final int END = 1;
    public static final int INTERPOLATED = 2;
    public static final int START = 0;

    /* renamed from: a  reason: collision with root package name */
    public HashMap<Integer, HashMap<String, a>> f900a = new HashMap<>();
    public HashMap<String, b> b = new HashMap<>();
    public TypedBundle c = new TypedBundle();
    public int d = 0;
    public String e = null;
    public Easing f = null;
    public int g = 0;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f901a;
        public float b;
        public float c;

        public a(String str, int i, int i2, float f, float f2) {
            this.f901a = i;
            this.b = f;
            this.c = f2;
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public Motion d;
        public KeyCache h = new KeyCache();

        /* renamed from: a  reason: collision with root package name */
        public WidgetFrame f902a = new WidgetFrame();
        public WidgetFrame b = new WidgetFrame();
        public WidgetFrame c = new WidgetFrame();
        public MotionWidget e = new MotionWidget(this.f902a);
        public MotionWidget f = new MotionWidget(this.b);
        public MotionWidget g = new MotionWidget(this.c);

        public b() {
            Motion motion = new Motion(this.e);
            this.d = motion;
            motion.setStart(this.e);
            this.d.setEnd(this.f);
        }

        public WidgetFrame a(int i) {
            if (i == 0) {
                return this.f902a;
            }
            if (i == 1) {
                return this.b;
            }
            return this.c;
        }

        public void b(int i, int i2, float f, Transition transition) {
            this.d.setup(i, i2, 1.0f, System.nanoTime());
            WidgetFrame.interpolate(i, i2, this.c, this.f902a, this.b, transition, f);
            this.c.interpolatedPos = f;
            this.d.interpolate(this.g, f, System.nanoTime(), this.h);
        }

        public void c(TypedBundle typedBundle) {
            MotionKeyAttributes motionKeyAttributes = new MotionKeyAttributes();
            typedBundle.applyDelta(motionKeyAttributes);
            this.d.addKey(motionKeyAttributes);
        }

        public void d(TypedBundle typedBundle) {
            MotionKeyCycle motionKeyCycle = new MotionKeyCycle();
            typedBundle.applyDelta(motionKeyCycle);
            this.d.addKey(motionKeyCycle);
        }

        public void e(TypedBundle typedBundle) {
            MotionKeyPosition motionKeyPosition = new MotionKeyPosition();
            typedBundle.applyDelta(motionKeyPosition);
            this.d.addKey(motionKeyPosition);
        }

        public void f(ConstraintWidget constraintWidget, int i) {
            if (i == 0) {
                this.f902a.update(constraintWidget);
                this.d.setStart(this.e);
            } else if (i == 1) {
                this.b.update(constraintWidget);
                this.d.setEnd(this.f);
            }
        }
    }

    public static Interpolator getInterpolator(int i, final String str) {
        switch (i) {
            case -1:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.a
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float j;
                        j = Transition.j(str, f);
                        return j;
                    }
                };
            case 0:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.d
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float k;
                        k = Transition.k(f);
                        return k;
                    }
                };
            case 1:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.e
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float l;
                        l = Transition.l(f);
                        return l;
                    }
                };
            case 2:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.b
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float m;
                        m = Transition.m(f);
                        return m;
                    }
                };
            case 3:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.c
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float n;
                        n = Transition.n(f);
                        return n;
                    }
                };
            case 4:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.f
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float q;
                        q = Transition.q(f);
                        return q;
                    }
                };
            case 5:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.h
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float p;
                        p = Transition.p(f);
                        return p;
                    }
                };
            case 6:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.g
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f) {
                        float o;
                        o = Transition.o(f);
                        return o;
                    }
                };
            default:
                return null;
        }
    }

    public static /* synthetic */ float j(String str, float f) {
        return (float) Easing.getInterpolator(str).get(f);
    }

    public static /* synthetic */ float k(float f) {
        return (float) Easing.getInterpolator("standard").get(f);
    }

    public static /* synthetic */ float l(float f) {
        return (float) Easing.getInterpolator("accelerate").get(f);
    }

    public static /* synthetic */ float m(float f) {
        return (float) Easing.getInterpolator("decelerate").get(f);
    }

    public static /* synthetic */ float n(float f) {
        return (float) Easing.getInterpolator(Property.RASTER_RESAMPLING_LINEAR).get(f);
    }

    public static /* synthetic */ float o(float f) {
        return (float) Easing.getInterpolator("anticipate").get(f);
    }

    public static /* synthetic */ float p(float f) {
        return (float) Easing.getInterpolator("overshoot").get(f);
    }

    public static /* synthetic */ float q(float f) {
        return (float) Easing.getInterpolator("spline(0.0, 0.2, 0.4, 0.6, 0.8 ,1.0, 0.8, 1.0, 0.9, 1.0)").get(f);
    }

    public void addCustomColor(int i, String str, String str2, int i2) {
        i(str, null, i).a(i).addCustomColor(str2, i2);
    }

    public void addCustomFloat(int i, String str, String str2, float f) {
        i(str, null, i).a(i).addCustomFloat(str2, f);
    }

    public void addKeyAttribute(String str, TypedBundle typedBundle) {
        i(str, null, 0).c(typedBundle);
    }

    public void addKeyCycle(String str, TypedBundle typedBundle) {
        i(str, null, 0).d(typedBundle);
    }

    public void addKeyPosition(String str, TypedBundle typedBundle) {
        i(str, null, 0).e(typedBundle);
    }

    public void clear() {
        this.b.clear();
    }

    public boolean contains(String str) {
        return this.b.containsKey(str);
    }

    public void fillKeyPositions(WidgetFrame widgetFrame, float[] fArr, float[] fArr2, float[] fArr3) {
        a aVar;
        int i = 0;
        for (int i2 = 0; i2 <= 100; i2++) {
            HashMap<String, a> hashMap = this.f900a.get(Integer.valueOf(i2));
            if (hashMap != null && (aVar = hashMap.get(widgetFrame.widget.stringId)) != null) {
                fArr[i] = aVar.b;
                fArr2[i] = aVar.c;
                fArr3[i] = aVar.f901a;
                i++;
            }
        }
    }

    public a findNextPosition(String str, int i) {
        a aVar;
        while (i <= 100) {
            HashMap<String, a> hashMap = this.f900a.get(Integer.valueOf(i));
            if (hashMap != null && (aVar = hashMap.get(str)) != null) {
                return aVar;
            }
            i++;
        }
        return null;
    }

    public a findPreviousPosition(String str, int i) {
        a aVar;
        while (i >= 0) {
            HashMap<String, a> hashMap = this.f900a.get(Integer.valueOf(i));
            if (hashMap != null && (aVar = hashMap.get(str)) != null) {
                return aVar;
            }
            i--;
        }
        return null;
    }

    public int getAutoTransition() {
        return this.g;
    }

    public WidgetFrame getEnd(String str) {
        b bVar = this.b.get(str);
        if (bVar == null) {
            return null;
        }
        return bVar.b;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return 0;
    }

    public WidgetFrame getInterpolated(String str) {
        b bVar = this.b.get(str);
        if (bVar == null) {
            return null;
        }
        return bVar.c;
    }

    public int getKeyFrames(String str, float[] fArr, int[] iArr, int[] iArr2) {
        return this.b.get(str).d.buildKeyFrames(fArr, iArr, iArr2);
    }

    public Motion getMotion(String str) {
        return i(str, null, 0).d;
    }

    public int getNumberKeyPositions(WidgetFrame widgetFrame) {
        int i = 0;
        for (int i2 = 0; i2 <= 100; i2++) {
            HashMap<String, a> hashMap = this.f900a.get(Integer.valueOf(i2));
            if (hashMap != null && hashMap.get(widgetFrame.widget.stringId) != null) {
                i++;
            }
        }
        return i;
    }

    public float[] getPath(String str) {
        float[] fArr = new float[124];
        this.b.get(str).d.buildPath(fArr, 62);
        return fArr;
    }

    public WidgetFrame getStart(String str) {
        b bVar = this.b.get(str);
        if (bVar == null) {
            return null;
        }
        return bVar.f902a;
    }

    public boolean hasPositionKeyframes() {
        return this.f900a.size() > 0;
    }

    public final b i(String str, ConstraintWidget constraintWidget, int i) {
        b bVar = this.b.get(str);
        if (bVar == null) {
            bVar = new b();
            this.c.applyDelta(bVar.d);
            this.b.put(str, bVar);
            if (constraintWidget != null) {
                bVar.f(constraintWidget, i);
            }
        }
        return bVar;
    }

    public void interpolate(int i, int i2, float f) {
        Easing easing = this.f;
        if (easing != null) {
            f = (float) easing.get(f);
        }
        for (String str : this.b.keySet()) {
            this.b.get(str).b(i, i2, f, this);
        }
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    public void setTransitionProperties(TypedBundle typedBundle) {
        typedBundle.applyDelta(this.c);
        typedBundle.applyDelta(this);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (i == 705) {
            this.e = str;
            this.f = Easing.getInterpolator(str);
            return false;
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, boolean z) {
        return false;
    }

    public void updateFrom(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = children.get(i2);
            i(constraintWidget.stringId, null, i).f(constraintWidget, i);
        }
    }

    public void addKeyPosition(String str, int i, int i2, float f, float f2) {
        TypedBundle typedBundle = new TypedBundle();
        typedBundle.add(510, 2);
        typedBundle.add(100, i);
        typedBundle.add(506, f);
        typedBundle.add(507, f2);
        i(str, null, 0).e(typedBundle);
        a aVar = new a(str, i, i2, f, f2);
        HashMap<String, a> hashMap = this.f900a.get(Integer.valueOf(i));
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.f900a.put(Integer.valueOf(i), hashMap);
        }
        hashMap.put(str, aVar);
    }

    public WidgetFrame getEnd(ConstraintWidget constraintWidget) {
        return i(constraintWidget.stringId, null, 1).b;
    }

    public WidgetFrame getInterpolated(ConstraintWidget constraintWidget) {
        return i(constraintWidget.stringId, null, 2).c;
    }

    public WidgetFrame getStart(ConstraintWidget constraintWidget) {
        return i(constraintWidget.stringId, null, 0).f902a;
    }

    public Interpolator getInterpolator() {
        return getInterpolator(this.d, this.e);
    }
}
