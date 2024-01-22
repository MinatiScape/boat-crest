package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
/* loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public static final int MATCH_ID = 3;
    public static final int MATCH_INSTANCE = 1;
    public static final int MATCH_ITEM_ID = 4;
    public static final int MATCH_NAME = 2;
    public static final int[] N = {2, 1, 3, 4};
    public static final PathMotion O = new a();
    public static ThreadLocal<ArrayMap<Animator, d>> P = new ThreadLocal<>();
    public ArrayList<TransitionValues> A;
    public ArrayList<TransitionValues> B;
    public TransitionPropagation J;
    public EpicenterCallback K;
    public ArrayMap<String, String> L;
    public String h = getClass().getName();
    public long i = -1;
    public long j = -1;
    public TimeInterpolator k = null;
    public ArrayList<Integer> l = new ArrayList<>();
    public ArrayList<View> m = new ArrayList<>();
    public ArrayList<String> n = null;
    public ArrayList<Class<?>> o = null;
    public ArrayList<Integer> p = null;
    public ArrayList<View> q = null;
    public ArrayList<Class<?>> r = null;
    public ArrayList<String> s = null;
    public ArrayList<Integer> t = null;
    public ArrayList<View> u = null;
    public ArrayList<Class<?>> v = null;
    public q w = new q();
    public q x = new q();
    public TransitionSet y = null;
    public int[] z = N;
    public boolean C = false;
    public ArrayList<Animator> D = new ArrayList<>();
    public int E = 0;
    public boolean F = false;
    public boolean G = false;
    public ArrayList<TransitionListener> H = null;
    public ArrayList<Animator> I = new ArrayList<>();
    public PathMotion M = O;

    /* loaded from: classes.dex */
    public static abstract class EpicenterCallback {
        public abstract Rect onGetEpicenter(@NonNull Transition transition);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface MatchOrder {
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionCancel(@NonNull Transition transition);

        void onTransitionEnd(@NonNull Transition transition);

        void onTransitionPause(@NonNull Transition transition);

        void onTransitionResume(@NonNull Transition transition);

        void onTransitionStart(@NonNull Transition transition);
    }

    /* loaded from: classes.dex */
    public static class a extends PathMotion {
        @Override // androidx.transition.PathMotion
        public Path getPath(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ ArrayMap h;

        public b(ArrayMap arrayMap) {
            this.h = arrayMap;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.remove(animator);
            Transition.this.D.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            Transition.this.D.add(animator);
        }
    }

    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Transition.this.end();
            animator.removeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public View f1702a;
        public String b;
        public TransitionValues c;
        public k0 d;
        public Transition e;

        public d(View view, String str, Transition transition, k0 k0Var, TransitionValues transitionValues) {
            this.f1702a = view;
            this.b = str;
            this.c = transitionValues;
            this.d = k0Var;
            this.e = transition;
        }
    }

    /* loaded from: classes.dex */
    public static class e {
        public static <T> ArrayList<T> a(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            if (!arrayList.contains(t)) {
                arrayList.add(t);
            }
            return arrayList;
        }

        public static <T> ArrayList<T> b(ArrayList<T> arrayList, T t) {
            if (arrayList != null) {
                arrayList.remove(t);
                if (arrayList.isEmpty()) {
                    return null;
                }
                return arrayList;
            }
            return arrayList;
        }
    }

    public Transition() {
    }

    public static void b(q qVar, View view, TransitionValues transitionValues) {
        qVar.f1721a.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (qVar.b.indexOfKey(id) >= 0) {
                qVar.b.put(id, null);
            } else {
                qVar.b.put(id, view);
            }
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (qVar.d.containsKey(transitionName)) {
                qVar.d.put(transitionName, null);
            } else {
                qVar.d.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (qVar.c.indexOfKey(itemIdAtPosition) >= 0) {
                    View view2 = qVar.c.get(itemIdAtPosition);
                    if (view2 != null) {
                        ViewCompat.setHasTransientState(view2, false);
                        qVar.c.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                ViewCompat.setHasTransientState(view, true);
                qVar.c.put(itemIdAtPosition, view);
            }
        }
    }

    public static boolean c(int[] iArr, int i) {
        int i2 = iArr[i];
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    public static <T> ArrayList<T> i(ArrayList<T> arrayList, T t, boolean z) {
        if (t != null) {
            if (z) {
                return e.a(arrayList, t);
            }
            return e.b(arrayList, t);
        }
        return arrayList;
    }

    public static ArrayMap<Animator, d> n() {
        ArrayMap<Animator, d> arrayMap = P.get();
        if (arrayMap == null) {
            ArrayMap<Animator, d> arrayMap2 = new ArrayMap<>();
            P.set(arrayMap2);
            return arrayMap2;
        }
        return arrayMap;
    }

    public static boolean o(int i) {
        return i >= 1 && i <= 4;
    }

    public static boolean q(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.values.get(str);
        Object obj2 = transitionValues2.values.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public static int[] w(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, Constants.SEPARATOR_COMMA);
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if ("id".equalsIgnoreCase(trim)) {
                iArr[i] = 3;
            } else if ("instance".equalsIgnoreCase(trim)) {
                iArr[i] = 1;
            } else if (AppMeasurementSdk.ConditionalUserProperty.NAME.equalsIgnoreCase(trim)) {
                iArr[i] = 2;
            } else if ("itemId".equalsIgnoreCase(trim)) {
                iArr[i] = 4;
            } else if (trim.isEmpty()) {
                int[] iArr2 = new int[iArr.length - 1];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                i--;
                iArr = iArr2;
            } else {
                throw new InflateException("Unknown match type in matchOrder: '" + trim + "'");
            }
            i++;
        }
        return iArr;
    }

    public Transition A(ViewGroup viewGroup) {
        return this;
    }

    public String B(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.j != -1) {
            str2 = str2 + "dur(" + this.j + ") ";
        }
        if (this.i != -1) {
            str2 = str2 + "dly(" + this.i + ") ";
        }
        if (this.k != null) {
            str2 = str2 + "interp(" + this.k + ") ";
        }
        if (this.l.size() > 0 || this.m.size() > 0) {
            String str3 = str2 + "tgts(";
            if (this.l.size() > 0) {
                for (int i = 0; i < this.l.size(); i++) {
                    if (i > 0) {
                        str3 = str3 + ", ";
                    }
                    str3 = str3 + this.l.get(i);
                }
            }
            if (this.m.size() > 0) {
                for (int i2 = 0; i2 < this.m.size(); i2++) {
                    if (i2 > 0) {
                        str3 = str3 + ", ";
                    }
                    str3 = str3 + this.m.get(i2);
                }
            }
            return str3 + ")";
        }
        return str2;
    }

    public final void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        for (int i = 0; i < arrayMap.size(); i++) {
            TransitionValues valueAt = arrayMap.valueAt(i);
            if (p(valueAt.view)) {
                this.A.add(valueAt);
                this.B.add(null);
            }
        }
        for (int i2 = 0; i2 < arrayMap2.size(); i2++) {
            TransitionValues valueAt2 = arrayMap2.valueAt(i2);
            if (p(valueAt2.view)) {
                this.B.add(valueAt2);
                this.A.add(null);
            }
        }
    }

    @NonNull
    public Transition addListener(@NonNull TransitionListener transitionListener) {
        if (this.H == null) {
            this.H = new ArrayList<>();
        }
        this.H.add(transitionListener);
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull View view) {
        this.m.add(view);
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay() + animator.getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new c());
        animator.start();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void cancel() {
        for (int size = this.D.size() - 1; size >= 0; size--) {
            this.D.get(size).cancel();
        }
        ArrayList<TransitionListener> arrayList = this.H;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.H.clone();
        int size2 = arrayList2.size();
        for (int i = 0; i < size2; i++) {
            ((TransitionListener) arrayList2.get(i)).onTransitionCancel(this);
        }
    }

    public abstract void captureEndValues(@NonNull TransitionValues transitionValues);

    public abstract void captureStartValues(@NonNull TransitionValues transitionValues);

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void createAnimators(ViewGroup viewGroup, q qVar, q qVar2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        Animator createAnimator;
        int i;
        int i2;
        View view;
        Animator animator;
        TransitionValues transitionValues;
        Animator animator2;
        TransitionValues transitionValues2;
        ArrayMap<Animator, d> n = n();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        long j = Long.MAX_VALUE;
        int i3 = 0;
        while (i3 < size) {
            TransitionValues transitionValues3 = arrayList.get(i3);
            TransitionValues transitionValues4 = arrayList2.get(i3);
            if (transitionValues3 != null && !transitionValues3.f1705a.contains(this)) {
                transitionValues3 = null;
            }
            if (transitionValues4 != null && !transitionValues4.f1705a.contains(this)) {
                transitionValues4 = null;
            }
            if (transitionValues3 != null || transitionValues4 != null) {
                if ((transitionValues3 == null || transitionValues4 == null || isTransitionRequired(transitionValues3, transitionValues4)) && (createAnimator = createAnimator(viewGroup, transitionValues3, transitionValues4)) != null) {
                    if (transitionValues4 != null) {
                        view = transitionValues4.view;
                        String[] transitionProperties = getTransitionProperties();
                        if (transitionProperties != null && transitionProperties.length > 0) {
                            transitionValues2 = new TransitionValues(view);
                            i = size;
                            TransitionValues transitionValues5 = qVar2.f1721a.get(view);
                            if (transitionValues5 != null) {
                                int i4 = 0;
                                while (i4 < transitionProperties.length) {
                                    transitionValues2.values.put(transitionProperties[i4], transitionValues5.values.get(transitionProperties[i4]));
                                    i4++;
                                    i3 = i3;
                                    transitionValues5 = transitionValues5;
                                }
                            }
                            i2 = i3;
                            int size2 = n.size();
                            int i5 = 0;
                            while (true) {
                                if (i5 >= size2) {
                                    animator2 = createAnimator;
                                    break;
                                }
                                d dVar = n.get(n.keyAt(i5));
                                if (dVar.c != null && dVar.f1702a == view && dVar.b.equals(getName()) && dVar.c.equals(transitionValues2)) {
                                    animator2 = null;
                                    break;
                                }
                                i5++;
                            }
                        } else {
                            i = size;
                            i2 = i3;
                            animator2 = createAnimator;
                            transitionValues2 = null;
                        }
                        animator = animator2;
                        transitionValues = transitionValues2;
                    } else {
                        i = size;
                        i2 = i3;
                        view = transitionValues3.view;
                        animator = createAnimator;
                        transitionValues = null;
                    }
                    if (animator != null) {
                        TransitionPropagation transitionPropagation = this.J;
                        if (transitionPropagation != null) {
                            long startDelay = transitionPropagation.getStartDelay(viewGroup, this, transitionValues3, transitionValues4);
                            sparseIntArray.put(this.I.size(), (int) startDelay);
                            j = Math.min(startDelay, j);
                        }
                        n.put(animator, new d(view, getName(), this, b0.d(viewGroup), transitionValues));
                        this.I.add(animator);
                        j = j;
                    }
                    i3 = i2 + 1;
                    size = i;
                }
            }
            i = size;
            i2 = i3;
            i3 = i2 + 1;
            size = i;
        }
        if (sparseIntArray.size() != 0) {
            for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
                Animator animator3 = this.I.get(sparseIntArray.keyAt(i6));
                animator3.setStartDelay((sparseIntArray.valueAt(i6) - j) + animator3.getStartDelay());
            }
        }
    }

    public final void d(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.p;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.q;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.r;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i = 0; i < size; i++) {
                        if (this.r.get(i).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    TransitionValues transitionValues = new TransitionValues(view);
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.f1705a.add(this);
                    e(transitionValues);
                    if (z) {
                        b(this.w, view, transitionValues);
                    } else {
                        b(this.x, view, transitionValues);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.t;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.u;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.v;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    if (this.v.get(i2).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                                d(viewGroup.getChildAt(i3), z);
                            }
                        }
                    }
                }
            }
        }
    }

    public void e(TransitionValues transitionValues) {
        String[] propagationProperties;
        if (this.J == null || transitionValues.values.isEmpty() || (propagationProperties = this.J.getPropagationProperties()) == null) {
            return;
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= propagationProperties.length) {
                z = true;
                break;
            } else if (!transitionValues.values.containsKey(propagationProperties[i])) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            return;
        }
        this.J.captureValues(transitionValues);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void end() {
        int i = this.E - 1;
        this.E = i;
        if (i == 0) {
            ArrayList<TransitionListener> arrayList = this.H;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.H.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((TransitionListener) arrayList2.get(i2)).onTransitionEnd(this);
                }
            }
            for (int i3 = 0; i3 < this.w.c.size(); i3++) {
                View valueAt = this.w.c.valueAt(i3);
                if (valueAt != null) {
                    ViewCompat.setHasTransientState(valueAt, false);
                }
            }
            for (int i4 = 0; i4 < this.x.c.size(); i4++) {
                View valueAt2 = this.x.c.valueAt(i4);
                if (valueAt2 != null) {
                    ViewCompat.setHasTransientState(valueAt2, false);
                }
            }
            this.G = true;
        }
    }

    @NonNull
    public Transition excludeChildren(@NonNull View view, boolean z) {
        this.u = k(this.u, view, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        this.q = k(this.q, view, z);
        return this;
    }

    public void f(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        ArrayMap<String, String> arrayMap;
        g(z);
        if ((this.l.size() <= 0 && this.m.size() <= 0) || (((arrayList = this.n) != null && !arrayList.isEmpty()) || ((arrayList2 = this.o) != null && !arrayList2.isEmpty()))) {
            d(viewGroup, z);
        } else {
            for (int i = 0; i < this.l.size(); i++) {
                View findViewById = viewGroup.findViewById(this.l.get(i).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues(findViewById);
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.f1705a.add(this);
                    e(transitionValues);
                    if (z) {
                        b(this.w, findViewById, transitionValues);
                    } else {
                        b(this.x, findViewById, transitionValues);
                    }
                }
            }
            for (int i2 = 0; i2 < this.m.size(); i2++) {
                View view = this.m.get(i2);
                TransitionValues transitionValues2 = new TransitionValues(view);
                if (z) {
                    captureStartValues(transitionValues2);
                } else {
                    captureEndValues(transitionValues2);
                }
                transitionValues2.f1705a.add(this);
                e(transitionValues2);
                if (z) {
                    b(this.w, view, transitionValues2);
                } else {
                    b(this.x, view, transitionValues2);
                }
            }
        }
        if (z || (arrayMap = this.L) == null) {
            return;
        }
        int size = arrayMap.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList3.add(this.w.d.remove(this.L.keyAt(i3)));
        }
        for (int i4 = 0; i4 < size; i4++) {
            View view2 = (View) arrayList3.get(i4);
            if (view2 != null) {
                this.w.d.put(this.L.valueAt(i4), view2);
            }
        }
    }

    public void g(boolean z) {
        if (z) {
            this.w.f1721a.clear();
            this.w.b.clear();
            this.w.c.clear();
            return;
        }
        this.x.f1721a.clear();
        this.x.b.clear();
        this.x.c.clear();
    }

    public long getDuration() {
        return this.j;
    }

    @Nullable
    public Rect getEpicenter() {
        EpicenterCallback epicenterCallback = this.K;
        if (epicenterCallback == null) {
            return null;
        }
        return epicenterCallback.onGetEpicenter(this);
    }

    @Nullable
    public EpicenterCallback getEpicenterCallback() {
        return this.K;
    }

    @Nullable
    public TimeInterpolator getInterpolator() {
        return this.k;
    }

    @NonNull
    public String getName() {
        return this.h;
    }

    @NonNull
    public PathMotion getPathMotion() {
        return this.M;
    }

    @Nullable
    public TransitionPropagation getPropagation() {
        return this.J;
    }

    public long getStartDelay() {
        return this.i;
    }

    @NonNull
    public List<Integer> getTargetIds() {
        return this.l;
    }

    @Nullable
    public List<String> getTargetNames() {
        return this.n;
    }

    @Nullable
    public List<Class<?>> getTargetTypes() {
        return this.o;
    }

    @NonNull
    public List<View> getTargets() {
        return this.m;
    }

    @Nullable
    public String[] getTransitionProperties() {
        return null;
    }

    @Nullable
    public TransitionValues getTransitionValues(@NonNull View view, boolean z) {
        TransitionSet transitionSet = this.y;
        if (transitionSet != null) {
            return transitionSet.getTransitionValues(view, z);
        }
        return (z ? this.w : this.x).f1721a.get(view);
    }

    public final ArrayList<Integer> h(ArrayList<Integer> arrayList, int i, boolean z) {
        if (i > 0) {
            if (z) {
                return e.a(arrayList, Integer.valueOf(i));
            }
            return e.b(arrayList, Integer.valueOf(i));
        }
        return arrayList;
    }

    public boolean isTransitionRequired(@Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties != null) {
            for (String str : transitionProperties) {
                if (!q(transitionValues, transitionValues2, str)) {
                }
            }
            return false;
        }
        for (String str2 : transitionValues.values.keySet()) {
            if (q(transitionValues, transitionValues2, str2)) {
            }
        }
        return false;
        return true;
    }

    public final ArrayList<Class<?>> j(ArrayList<Class<?>> arrayList, Class<?> cls, boolean z) {
        if (cls != null) {
            if (z) {
                return e.a(arrayList, cls);
            }
            return e.b(arrayList, cls);
        }
        return arrayList;
    }

    public final ArrayList<View> k(ArrayList<View> arrayList, View view, boolean z) {
        if (view != null) {
            if (z) {
                return e.a(arrayList, view);
            }
            return e.b(arrayList, view);
        }
        return arrayList;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void l(ViewGroup viewGroup) {
        ArrayMap<Animator, d> n = n();
        int size = n.size();
        if (viewGroup == null || size == 0) {
            return;
        }
        k0 d2 = b0.d(viewGroup);
        ArrayMap arrayMap = new ArrayMap(n);
        n.clear();
        for (int i = size - 1; i >= 0; i--) {
            d dVar = (d) arrayMap.valueAt(i);
            if (dVar.f1702a != null && d2 != null && d2.equals(dVar.d)) {
                ((Animator) arrayMap.keyAt(i)).end();
            }
        }
    }

    public TransitionValues m(View view, boolean z) {
        TransitionSet transitionSet = this.y;
        if (transitionSet != null) {
            return transitionSet.m(view, z);
        }
        ArrayList<TransitionValues> arrayList = z ? this.A : this.B;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            TransitionValues transitionValues = arrayList.get(i2);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.view == view) {
                i = i2;
                break;
            }
            i2++;
        }
        if (i >= 0) {
            return (z ? this.B : this.A).get(i);
        }
        return null;
    }

    public boolean p(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.p;
        if (arrayList3 == null || !arrayList3.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList4 = this.q;
            if (arrayList4 == null || !arrayList4.contains(view)) {
                ArrayList<Class<?>> arrayList5 = this.r;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    for (int i = 0; i < size; i++) {
                        if (this.r.get(i).isInstance(view)) {
                            return false;
                        }
                    }
                }
                if (this.s == null || ViewCompat.getTransitionName(view) == null || !this.s.contains(ViewCompat.getTransitionName(view))) {
                    if ((this.l.size() == 0 && this.m.size() == 0 && (((arrayList = this.o) == null || arrayList.isEmpty()) && ((arrayList2 = this.n) == null || arrayList2.isEmpty()))) || this.l.contains(Integer.valueOf(id)) || this.m.contains(view)) {
                        return true;
                    }
                    ArrayList<String> arrayList6 = this.n;
                    if (arrayList6 == null || !arrayList6.contains(ViewCompat.getTransitionName(view))) {
                        if (this.o != null) {
                            for (int i2 = 0; i2 < this.o.size(); i2++) {
                                if (this.o.get(i2).isInstance(view)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void pause(View view) {
        if (this.G) {
            return;
        }
        ArrayMap<Animator, d> n = n();
        int size = n.size();
        k0 d2 = b0.d(view);
        for (int i = size - 1; i >= 0; i--) {
            d valueAt = n.valueAt(i);
            if (valueAt.f1702a != null && d2.equals(valueAt.d)) {
                androidx.transition.a.b(n.keyAt(i));
            }
        }
        ArrayList<TransitionListener> arrayList = this.H;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.H.clone();
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((TransitionListener) arrayList2.get(i2)).onTransitionPause(this);
            }
        }
        this.F = true;
    }

    public final void r(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View valueAt = sparseArray.valueAt(i);
            if (valueAt != null && p(valueAt) && (view = sparseArray2.get(sparseArray.keyAt(i))) != null && p(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.A.add(transitionValues);
                    this.B.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    @NonNull
    public Transition removeListener(@NonNull TransitionListener transitionListener) {
        ArrayList<TransitionListener> arrayList = this.H;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(transitionListener);
        if (this.H.size() == 0) {
            this.H = null;
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull View view) {
        this.m.remove(view);
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void resume(View view) {
        if (this.F) {
            if (!this.G) {
                ArrayMap<Animator, d> n = n();
                int size = n.size();
                k0 d2 = b0.d(view);
                for (int i = size - 1; i >= 0; i--) {
                    d valueAt = n.valueAt(i);
                    if (valueAt.f1702a != null && d2.equals(valueAt.d)) {
                        androidx.transition.a.c(n.keyAt(i));
                    }
                }
                ArrayList<TransitionListener> arrayList = this.H;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.H.clone();
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((TransitionListener) arrayList2.get(i2)).onTransitionResume(this);
                    }
                }
            }
            this.F = false;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void runAnimators() {
        start();
        ArrayMap<Animator, d> n = n();
        Iterator<Animator> it = this.I.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (n.containsKey(next)) {
                start();
                y(next, n);
            }
        }
        this.I.clear();
        end();
    }

    public final void s(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        TransitionValues remove;
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View keyAt = arrayMap.keyAt(size);
            if (keyAt != null && p(keyAt) && (remove = arrayMap2.remove(keyAt)) != null && p(remove.view)) {
                this.A.add(arrayMap.removeAt(size));
                this.B.add(remove);
            }
        }
    }

    @NonNull
    public Transition setDuration(long j) {
        this.j = j;
        return this;
    }

    public void setEpicenterCallback(@Nullable EpicenterCallback epicenterCallback) {
        this.K = epicenterCallback;
    }

    @NonNull
    public Transition setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.k = timeInterpolator;
        return this;
    }

    public void setMatchOrder(int... iArr) {
        if (iArr != null && iArr.length != 0) {
            for (int i = 0; i < iArr.length; i++) {
                if (o(iArr[i])) {
                    if (c(iArr, i)) {
                        throw new IllegalArgumentException("matches contains a duplicate value");
                    }
                } else {
                    throw new IllegalArgumentException("matches contains invalid value");
                }
            }
            this.z = (int[]) iArr.clone();
            return;
        }
        this.z = N;
    }

    public void setPathMotion(@Nullable PathMotion pathMotion) {
        if (pathMotion == null) {
            this.M = O;
        } else {
            this.M = pathMotion;
        }
    }

    public void setPropagation(@Nullable TransitionPropagation transitionPropagation) {
        this.J = transitionPropagation;
    }

    @NonNull
    public Transition setStartDelay(long j) {
        this.i = j;
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void start() {
        if (this.E == 0) {
            ArrayList<TransitionListener> arrayList = this.H;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.H.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList2.get(i)).onTransitionStart(this);
                }
            }
            this.G = false;
        }
        this.E++;
    }

    public final void t(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, LongSparseArray<View> longSparseArray, LongSparseArray<View> longSparseArray2) {
        View view;
        int size = longSparseArray.size();
        for (int i = 0; i < size; i++) {
            View valueAt = longSparseArray.valueAt(i);
            if (valueAt != null && p(valueAt) && (view = longSparseArray2.get(longSparseArray.keyAt(i))) != null && p(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.A.add(transitionValues);
                    this.B.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    public String toString() {
        return B("");
    }

    public final void u(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, ArrayMap<String, View> arrayMap3, ArrayMap<String, View> arrayMap4) {
        View view;
        int size = arrayMap3.size();
        for (int i = 0; i < size; i++) {
            View valueAt = arrayMap3.valueAt(i);
            if (valueAt != null && p(valueAt) && (view = arrayMap4.get(arrayMap3.keyAt(i))) != null && p(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.A.add(transitionValues);
                    this.B.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    public final void v(q qVar, q qVar2) {
        ArrayMap<View, TransitionValues> arrayMap = new ArrayMap<>(qVar.f1721a);
        ArrayMap<View, TransitionValues> arrayMap2 = new ArrayMap<>(qVar2.f1721a);
        int i = 0;
        while (true) {
            int[] iArr = this.z;
            if (i < iArr.length) {
                int i2 = iArr[i];
                if (i2 == 1) {
                    s(arrayMap, arrayMap2);
                } else if (i2 == 2) {
                    u(arrayMap, arrayMap2, qVar.d, qVar2.d);
                } else if (i2 == 3) {
                    r(arrayMap, arrayMap2, qVar.b, qVar2.b);
                } else if (i2 == 4) {
                    t(arrayMap, arrayMap2, qVar.c, qVar2.c);
                }
                i++;
            } else {
                a(arrayMap, arrayMap2);
                return;
            }
        }
    }

    public void x(ViewGroup viewGroup) {
        d dVar;
        this.A = new ArrayList<>();
        this.B = new ArrayList<>();
        v(this.w, this.x);
        ArrayMap<Animator, d> n = n();
        int size = n.size();
        k0 d2 = b0.d(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator keyAt = n.keyAt(i);
            if (keyAt != null && (dVar = n.get(keyAt)) != null && dVar.f1702a != null && d2.equals(dVar.d)) {
                TransitionValues transitionValues = dVar.c;
                View view = dVar.f1702a;
                TransitionValues transitionValues2 = getTransitionValues(view, true);
                TransitionValues m = m(view, true);
                if (transitionValues2 == null && m == null) {
                    m = this.x.f1721a.get(view);
                }
                if (!(transitionValues2 == null && m == null) && dVar.e.isTransitionRequired(transitionValues, m)) {
                    if (!keyAt.isRunning() && !keyAt.isStarted()) {
                        n.remove(keyAt);
                    } else {
                        keyAt.cancel();
                    }
                }
            }
        }
        createAnimators(viewGroup, this.w, this.x, this.A, this.B);
        runAnimators();
    }

    public final void y(Animator animator, ArrayMap<Animator, d> arrayMap) {
        if (animator != null) {
            animator.addListener(new b(arrayMap));
            animate(animator);
        }
    }

    public void z(boolean z) {
        this.C = z;
    }

    @NonNull
    public Transition addTarget(@IdRes int i) {
        if (i != 0) {
            this.l.add(Integer.valueOf(i));
        }
        return this;
    }

    @Override // 
    /* renamed from: clone */
    public Transition mo13clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.I = new ArrayList<>();
            transition.w = new q();
            transition.x = new q();
            transition.A = null;
            transition.B = null;
            return transition;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @NonNull
    public Transition excludeChildren(@IdRes int i, boolean z) {
        this.t = h(this.t, i, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@IdRes int i, boolean z) {
        this.p = h(this.p, i, z);
        return this;
    }

    @NonNull
    public Transition removeTarget(@IdRes int i) {
        if (i != 0) {
            this.l.remove(Integer.valueOf(i));
        }
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull String str) {
        if (this.n == null) {
            this.n = new ArrayList<>();
        }
        this.n.add(str);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@NonNull Class<?> cls, boolean z) {
        this.v = j(this.v, cls, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull String str, boolean z) {
        this.s = i(this.s, str, z);
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull String str) {
        ArrayList<String> arrayList = this.n;
        if (arrayList != null) {
            arrayList.remove(str);
        }
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull Class<?> cls, boolean z) {
        this.r = j(this.r, cls, z);
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull Class<?> cls) {
        ArrayList<Class<?>> arrayList = this.o;
        if (arrayList != null) {
            arrayList.remove(cls);
        }
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull Class<?> cls) {
        if (this.o == null) {
            this.o = new ArrayList<>();
        }
        this.o.add(cls);
        return this;
    }

    @SuppressLint({"RestrictedApi"})
    public Transition(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.c);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (namedInt >= 0) {
            setDuration(namedInt);
        }
        long namedInt2 = TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (namedInt2 > 0) {
            setStartDelay(namedInt2);
        }
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlResourceParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            setInterpolator(AnimationUtils.loadInterpolator(context, namedResourceId));
        }
        String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (namedString != null) {
            setMatchOrder(w(namedString));
        }
        obtainStyledAttributes.recycle();
    }
}
