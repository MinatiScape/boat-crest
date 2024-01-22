package androidx.constraintlayout.motion.widget;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import java.io.PrintStream;
import java.util.HashMap;
/* loaded from: classes.dex */
public class DesignTool {
    public static final HashMap<Pair<Integer, Integer>, String> g;
    public static final HashMap<String, String> h;

    /* renamed from: a  reason: collision with root package name */
    public final MotionLayout f938a;
    public MotionScene b;
    public String c = null;
    public String d = null;
    public int e = -1;
    public int f = -1;

    static {
        HashMap<Pair<Integer, Integer>, String> hashMap = new HashMap<>();
        g = hashMap;
        HashMap<String, String> hashMap2 = new HashMap<>();
        h = hashMap2;
        hashMap.put(Pair.create(4, 4), "layout_constraintBottom_toBottomOf");
        hashMap.put(Pair.create(4, 3), "layout_constraintBottom_toTopOf");
        hashMap.put(Pair.create(3, 4), "layout_constraintTop_toBottomOf");
        hashMap.put(Pair.create(3, 3), "layout_constraintTop_toTopOf");
        hashMap.put(Pair.create(6, 6), "layout_constraintStart_toStartOf");
        hashMap.put(Pair.create(6, 7), "layout_constraintStart_toEndOf");
        hashMap.put(Pair.create(7, 6), "layout_constraintEnd_toStartOf");
        hashMap.put(Pair.create(7, 7), "layout_constraintEnd_toEndOf");
        hashMap.put(Pair.create(1, 1), "layout_constraintLeft_toLeftOf");
        hashMap.put(Pair.create(1, 2), "layout_constraintLeft_toRightOf");
        hashMap.put(Pair.create(2, 2), "layout_constraintRight_toRightOf");
        hashMap.put(Pair.create(2, 1), "layout_constraintRight_toLeftOf");
        hashMap.put(Pair.create(5, 5), "layout_constraintBaseline_toBaselineOf");
        hashMap2.put("layout_constraintBottom_toBottomOf", "layout_marginBottom");
        hashMap2.put("layout_constraintBottom_toTopOf", "layout_marginBottom");
        hashMap2.put("layout_constraintTop_toBottomOf", "layout_marginTop");
        hashMap2.put("layout_constraintTop_toTopOf", "layout_marginTop");
        hashMap2.put("layout_constraintStart_toStartOf", "layout_marginStart");
        hashMap2.put("layout_constraintStart_toEndOf", "layout_marginStart");
        hashMap2.put("layout_constraintEnd_toStartOf", "layout_marginEnd");
        hashMap2.put("layout_constraintEnd_toEndOf", "layout_marginEnd");
        hashMap2.put("layout_constraintLeft_toLeftOf", "layout_marginLeft");
        hashMap2.put("layout_constraintLeft_toRightOf", "layout_marginLeft");
        hashMap2.put("layout_constraintRight_toRightOf", "layout_marginRight");
        hashMap2.put("layout_constraintRight_toLeftOf", "layout_marginRight");
    }

    public DesignTool(MotionLayout motionLayout) {
        this.f938a = motionLayout;
    }

    public static void a(int i, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i2, int i3) {
        String str = g.get(Pair.create(Integer.valueOf(i2), Integer.valueOf(i3)));
        String str2 = hashMap.get(str);
        if (str2 != null) {
            String str3 = h.get(str);
            int b = str3 != null ? b(i, hashMap.get(str3)) : 0;
            constraintSet.connect(view.getId(), i2, Integer.parseInt(str2), i3, b);
        }
    }

    public static int b(int i, String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(100)) == -1) {
            return 0;
        }
        return (int) ((Integer.valueOf(str.substring(0, indexOf)).intValue() * i) / 160.0f);
    }

    public static void c(int i, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap) {
        String str = hashMap.get("layout_editor_absoluteX");
        if (str != null) {
            constraintSet.setEditorAbsoluteX(view.getId(), b(i, str));
        }
        String str2 = hashMap.get("layout_editor_absoluteY");
        if (str2 != null) {
            constraintSet.setEditorAbsoluteY(view.getId(), b(i, str2));
        }
    }

    public static void d(ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i) {
        String str = hashMap.get(i == 1 ? "layout_constraintVertical_bias" : "layout_constraintHorizontal_bias");
        if (str != null) {
            if (i == 0) {
                constraintSet.setHorizontalBias(view.getId(), Float.parseFloat(str));
            } else if (i == 1) {
                constraintSet.setVerticalBias(view.getId(), Float.parseFloat(str));
            }
        }
    }

    public static void e(int i, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i2) {
        String str = hashMap.get(i2 == 1 ? "layout_height" : "layout_width");
        if (str != null) {
            int b = str.equalsIgnoreCase("wrap_content") ? -2 : b(i, str);
            if (i2 == 0) {
                constraintSet.constrainWidth(view.getId(), b);
            } else {
                constraintSet.constrainHeight(view.getId(), b);
            }
        }
    }

    public int designAccess(int i, String str, Object obj, float[] fArr, int i2, float[] fArr2, int i3) {
        MotionController motionController;
        View view = (View) obj;
        if (i != 0) {
            MotionLayout motionLayout = this.f938a;
            if (motionLayout.h == null || view == null || (motionController = motionLayout.r.get(view)) == null) {
                return -1;
            }
        } else {
            motionController = null;
        }
        if (i != 0) {
            if (i == 1) {
                int duration = this.f938a.h.getDuration() / 16;
                motionController.c(fArr2, duration);
                return duration;
            } else if (i == 2) {
                int duration2 = this.f938a.h.getDuration() / 16;
                motionController.b(fArr2, null);
                return duration2;
            } else if (i != 3) {
                return -1;
            } else {
                int duration3 = this.f938a.h.getDuration() / 16;
                return motionController.h(str, fArr2, i3);
            }
        }
        return 1;
    }

    public void disableAutoTransition(boolean z) {
        this.f938a.E(z);
    }

    public void dumpConstraintSet(String str) {
        MotionLayout motionLayout = this.f938a;
        if (motionLayout.h == null) {
            motionLayout.h = this.b;
        }
        int O = motionLayout.O(str);
        PrintStream printStream = System.out;
        printStream.println(" dumping  " + str + " (" + O + ")");
        try {
            this.f938a.h.h(O).dump(this.f938a.h, new int[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAnimationKeyFrames(Object obj, float[] fArr) {
        MotionScene motionScene = this.f938a.h;
        if (motionScene == null) {
            return -1;
        }
        int duration = motionScene.getDuration() / 16;
        MotionController motionController = this.f938a.r.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.b(fArr, null);
        return duration;
    }

    public int getAnimationPath(Object obj, float[] fArr, int i) {
        MotionLayout motionLayout = this.f938a;
        if (motionLayout.h == null) {
            return -1;
        }
        MotionController motionController = motionLayout.r.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.c(fArr, i);
        return i;
    }

    public void getAnimationRectangles(Object obj, float[] fArr) {
        MotionScene motionScene = this.f938a.h;
        if (motionScene == null) {
            return;
        }
        int duration = motionScene.getDuration() / 16;
        MotionController motionController = this.f938a.r.get(obj);
        if (motionController == null) {
            return;
        }
        motionController.e(fArr, duration);
    }

    public String getEndState() {
        int endState = this.f938a.getEndState();
        if (this.f == endState) {
            return this.d;
        }
        String K = this.f938a.K(endState);
        if (K != null) {
            this.d = K;
            this.f = endState;
        }
        return K;
    }

    public int getKeyFrameInfo(Object obj, int i, int[] iArr) {
        MotionController motionController = this.f938a.r.get((View) obj);
        if (motionController == null) {
            return 0;
        }
        return motionController.getKeyFrameInfo(i, iArr);
    }

    public float getKeyFramePosition(Object obj, int i, float f, float f2) {
        MotionController motionController;
        if ((obj instanceof View) && (motionController = this.f938a.r.get((View) obj)) != null) {
            return motionController.l(i, f, f2);
        }
        return 0.0f;
    }

    public int getKeyFramePositions(Object obj, int[] iArr, float[] fArr) {
        MotionController motionController = this.f938a.r.get((View) obj);
        if (motionController == null) {
            return 0;
        }
        return motionController.getKeyFramePositions(iArr, fArr);
    }

    public Object getKeyframe(int i, int i2, int i3) {
        MotionLayout motionLayout = this.f938a;
        MotionScene motionScene = motionLayout.h;
        if (motionScene == null) {
            return null;
        }
        return motionScene.m(motionLayout.getContext(), i, i2, i3);
    }

    public Object getKeyframeAtLocation(Object obj, float f, float f2) {
        MotionController motionController;
        View view = (View) obj;
        MotionLayout motionLayout = this.f938a;
        if (motionLayout.h == null) {
            return -1;
        }
        if (view == null || (motionController = motionLayout.r.get(view)) == null) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        return motionController.m(viewGroup.getWidth(), viewGroup.getHeight(), f, f2);
    }

    public Boolean getPositionKeyframe(Object obj, Object obj2, float f, float f2, String[] strArr, float[] fArr) {
        if (obj instanceof a) {
            View view = (View) obj2;
            this.f938a.r.get(view).r(view, (a) obj, f, f2, strArr, fArr);
            this.f938a.rebuildScene();
            this.f938a.z = true;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public float getProgress() {
        return this.f938a.getProgress();
    }

    public String getStartState() {
        int startState = this.f938a.getStartState();
        if (this.e == startState) {
            return this.c;
        }
        String K = this.f938a.K(startState);
        if (K != null) {
            this.c = K;
            this.e = startState;
        }
        return this.f938a.K(startState);
    }

    public String getState() {
        if (this.c != null && this.d != null) {
            float progress = getProgress();
            if (progress <= 0.01f) {
                return this.c;
            }
            if (progress >= 0.99f) {
                return this.d;
            }
        }
        return this.c;
    }

    public long getTransitionTimeMs() {
        return this.f938a.getTransitionTimeMs();
    }

    public boolean isInTransition() {
        return (this.c == null || this.d == null) ? false : true;
    }

    public void setAttributes(int i, String str, Object obj, Object obj2) {
        View view = (View) obj;
        HashMap hashMap = (HashMap) obj2;
        int O = this.f938a.O(str);
        ConstraintSet h2 = this.f938a.h.h(O);
        if (h2 == null) {
            return;
        }
        h2.clear(view.getId());
        e(i, h2, view, hashMap, 0);
        e(i, h2, view, hashMap, 1);
        a(i, h2, view, hashMap, 6, 6);
        a(i, h2, view, hashMap, 6, 7);
        a(i, h2, view, hashMap, 7, 7);
        a(i, h2, view, hashMap, 7, 6);
        a(i, h2, view, hashMap, 1, 1);
        a(i, h2, view, hashMap, 1, 2);
        a(i, h2, view, hashMap, 2, 2);
        a(i, h2, view, hashMap, 2, 1);
        a(i, h2, view, hashMap, 3, 3);
        a(i, h2, view, hashMap, 3, 4);
        a(i, h2, view, hashMap, 4, 3);
        a(i, h2, view, hashMap, 4, 4);
        a(i, h2, view, hashMap, 5, 5);
        d(h2, view, hashMap, 0);
        d(h2, view, hashMap, 1);
        c(i, h2, view, hashMap);
        this.f938a.updateState(O, h2);
        this.f938a.requestLayout();
    }

    public void setKeyFrame(Object obj, int i, String str, Object obj2) {
        MotionScene motionScene = this.f938a.h;
        if (motionScene != null) {
            motionScene.setKeyframe((View) obj, i, str, obj2);
            MotionLayout motionLayout = this.f938a;
            motionLayout.x = i / 100.0f;
            motionLayout.v = 0.0f;
            motionLayout.rebuildScene();
            this.f938a.G(true);
        }
    }

    public boolean setKeyFramePosition(Object obj, int i, int i2, float f, float f2) {
        if (obj instanceof View) {
            MotionLayout motionLayout = this.f938a;
            if (motionLayout.h != null) {
                MotionController motionController = motionLayout.r.get(obj);
                MotionLayout motionLayout2 = this.f938a;
                int i3 = (int) (motionLayout2.u * 100.0f);
                if (motionController != null) {
                    View view = (View) obj;
                    if (motionLayout2.h.z(view, i3)) {
                        float l = motionController.l(2, f, f2);
                        float l2 = motionController.l(5, f, f2);
                        this.f938a.h.setKeyframe(view, i3, "motion:percentX", Float.valueOf(l));
                        this.f938a.h.setKeyframe(view, i3, "motion:percentY", Float.valueOf(l2));
                        this.f938a.rebuildScene();
                        this.f938a.G(true);
                        this.f938a.invalidate();
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public void setKeyframe(Object obj, String str, Object obj2) {
        if (obj instanceof Key) {
            ((Key) obj).setValue(str, obj2);
            this.f938a.rebuildScene();
            this.f938a.z = true;
        }
    }

    public void setState(String str) {
        if (str == null) {
            str = "motion_base";
        }
        if (this.c == str) {
            return;
        }
        this.c = str;
        this.d = null;
        MotionLayout motionLayout = this.f938a;
        if (motionLayout.h == null) {
            motionLayout.h = this.b;
        }
        int O = motionLayout.O(str);
        this.e = O;
        if (O != 0) {
            if (O == this.f938a.getStartState()) {
                this.f938a.setProgress(0.0f);
            } else if (O == this.f938a.getEndState()) {
                this.f938a.setProgress(1.0f);
            } else {
                this.f938a.transitionToState(O);
                this.f938a.setProgress(1.0f);
            }
        }
        this.f938a.requestLayout();
    }

    public void setToolPosition(float f) {
        MotionLayout motionLayout = this.f938a;
        if (motionLayout.h == null) {
            motionLayout.h = this.b;
        }
        motionLayout.setProgress(f);
        this.f938a.G(true);
        this.f938a.requestLayout();
        this.f938a.invalidate();
    }

    public void setTransition(String str, String str2) {
        MotionLayout motionLayout = this.f938a;
        if (motionLayout.h == null) {
            motionLayout.h = this.b;
        }
        int O = motionLayout.O(str);
        int O2 = this.f938a.O(str2);
        this.f938a.setTransition(O, O2);
        this.e = O;
        this.f = O2;
        this.c = str;
        this.d = str2;
    }

    public void setViewDebug(Object obj, int i) {
        MotionController motionController;
        if ((obj instanceof View) && (motionController = this.f938a.r.get(obj)) != null) {
            motionController.setDrawPath(i);
            this.f938a.invalidate();
        }
    }

    public Object getKeyframe(Object obj, int i, int i2) {
        if (this.f938a.h == null) {
            return null;
        }
        int id = ((View) obj).getId();
        MotionLayout motionLayout = this.f938a;
        return motionLayout.h.m(motionLayout.getContext(), i, id, i2);
    }
}
