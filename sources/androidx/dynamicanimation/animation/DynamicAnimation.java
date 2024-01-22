package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.a;
import java.util.ArrayList;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
/* loaded from: classes.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements a.b {
    public static final float MIN_VISIBLE_CHANGE_ALPHA = 0.00390625f;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = 0.1f;
    public static final float MIN_VISIBLE_CHANGE_SCALE = 0.002f;

    /* renamed from: a  reason: collision with root package name */
    public float f1240a;
    public float b;
    public boolean c;
    public final Object d;
    public final FloatPropertyCompat e;
    public boolean f;
    public float g;
    public float h;
    public long i;
    public float j;
    public final ArrayList<OnAnimationEndListener> k;
    public final ArrayList<OnAnimationUpdateListener> l;
    public static final ViewProperty TRANSLATION_X = new g("translationX");
    public static final ViewProperty TRANSLATION_Y = new h("translationY");
    public static final ViewProperty TRANSLATION_Z = new i("translationZ");
    public static final ViewProperty SCALE_X = new j("scaleX");
    public static final ViewProperty SCALE_Y = new k("scaleY");
    public static final ViewProperty ROTATION = new l(Key.ROTATION);
    public static final ViewProperty ROTATION_X = new m("rotationX");
    public static final ViewProperty ROTATION_Y = new n("rotationY");
    public static final ViewProperty X = new o("x");
    public static final ViewProperty Y = new a(EllipticCurveJsonWebKey.Y_MEMBER_NAME);
    public static final ViewProperty Z = new b("z");
    public static final ViewProperty ALPHA = new c("alpha");
    public static final ViewProperty SCROLL_X = new d("scrollX");
    public static final ViewProperty SCROLL_Y = new e("scrollY");

    /* loaded from: classes.dex */
    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2);
    }

    /* loaded from: classes.dex */
    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2);
    }

    /* loaded from: classes.dex */
    public static abstract class ViewProperty extends FloatPropertyCompat<View> {
        public /* synthetic */ ViewProperty(String str, g gVar) {
            this(str);
        }

        public ViewProperty(String str) {
            super(str);
        }
    }

    /* loaded from: classes.dex */
    public static class a extends ViewProperty {
        public a(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setY(f);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends ViewProperty {
        public b(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return ViewCompat.getZ(view);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            ViewCompat.setZ(view, f);
        }
    }

    /* loaded from: classes.dex */
    public static class c extends ViewProperty {
        public c(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getAlpha();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setAlpha(f);
        }
    }

    /* loaded from: classes.dex */
    public static class d extends ViewProperty {
        public d(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getScrollX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setScrollX((int) f);
        }
    }

    /* loaded from: classes.dex */
    public static class e extends ViewProperty {
        public e(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getScrollY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setScrollY((int) f);
        }
    }

    /* loaded from: classes.dex */
    public class f extends FloatPropertyCompat {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FloatValueHolder f1241a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(DynamicAnimation dynamicAnimation, String str, FloatValueHolder floatValueHolder) {
            super(str);
            this.f1241a = floatValueHolder;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(Object obj) {
            return this.f1241a.getValue();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(Object obj, float f) {
            this.f1241a.setValue(f);
        }
    }

    /* loaded from: classes.dex */
    public static class g extends ViewProperty {
        public g(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getTranslationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setTranslationX(f);
        }
    }

    /* loaded from: classes.dex */
    public static class h extends ViewProperty {
        public h(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getTranslationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setTranslationY(f);
        }
    }

    /* loaded from: classes.dex */
    public static class i extends ViewProperty {
        public i(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return ViewCompat.getTranslationZ(view);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            ViewCompat.setTranslationZ(view, f);
        }
    }

    /* loaded from: classes.dex */
    public static class j extends ViewProperty {
        public j(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getScaleX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setScaleX(f);
        }
    }

    /* loaded from: classes.dex */
    public static class k extends ViewProperty {
        public k(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getScaleY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setScaleY(f);
        }
    }

    /* loaded from: classes.dex */
    public static class l extends ViewProperty {
        public l(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getRotation();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setRotation(f);
        }
    }

    /* loaded from: classes.dex */
    public static class m extends ViewProperty {
        public m(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getRotationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setRotationX(f);
        }
    }

    /* loaded from: classes.dex */
    public static class n extends ViewProperty {
        public n(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getRotationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setRotationY(f);
        }
    }

    /* loaded from: classes.dex */
    public static class o extends ViewProperty {
        public o(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a */
        public float getValue(View view) {
            return view.getX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b */
        public void setValue(View view, float f) {
            view.setX(f);
        }
    }

    /* loaded from: classes.dex */
    public static class p {

        /* renamed from: a  reason: collision with root package name */
        public float f1242a;
        public float b;
    }

    public DynamicAnimation(FloatValueHolder floatValueHolder) {
        this.f1240a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.c = false;
        this.f = false;
        this.g = Float.MAX_VALUE;
        this.h = -Float.MAX_VALUE;
        this.i = 0L;
        this.k = new ArrayList<>();
        this.l = new ArrayList<>();
        this.d = null;
        this.e = new f(this, "FloatValueHolder", floatValueHolder);
        this.j = 1.0f;
    }

    public static <T> void d(ArrayList<T> arrayList, T t) {
        int indexOf = arrayList.indexOf(t);
        if (indexOf >= 0) {
            arrayList.set(indexOf, null);
        }
    }

    public static <T> void e(ArrayList<T> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    public final void a(boolean z) {
        this.f = false;
        androidx.dynamicanimation.animation.a.d().g(this);
        this.i = 0L;
        this.c = false;
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            if (this.k.get(i2) != null) {
                this.k.get(i2).onAnimationEnd(this, z, this.b, this.f1240a);
            }
        }
        e(this.k);
    }

    public T addEndListener(OnAnimationEndListener onAnimationEndListener) {
        if (!this.k.contains(onAnimationEndListener)) {
            this.k.add(onAnimationEndListener);
        }
        return this;
    }

    public T addUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        if (!isRunning()) {
            if (!this.l.contains(onAnimationUpdateListener)) {
                this.l.add(onAnimationUpdateListener);
            }
            return this;
        }
        throw new UnsupportedOperationException("Error: Update listeners must be added beforethe animation.");
    }

    public final float b() {
        return this.e.getValue(this.d);
    }

    public float c() {
        return this.j * 0.75f;
    }

    public void cancel() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (this.f) {
                a(true);
                return;
            }
            return;
        }
        throw new AndroidRuntimeException("Animations may only be canceled on the main thread");
    }

    @Override // androidx.dynamicanimation.animation.a.b
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean doAnimationFrame(long j2) {
        long j3 = this.i;
        if (j3 == 0) {
            this.i = j2;
            f(this.b);
            return false;
        }
        this.i = j2;
        boolean i2 = i(j2 - j3);
        float min = Math.min(this.b, this.g);
        this.b = min;
        float max = Math.max(min, this.h);
        this.b = max;
        f(max);
        if (i2) {
            a(false);
        }
        return i2;
    }

    public void f(float f2) {
        this.e.setValue(this.d, f2);
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (this.l.get(i2) != null) {
                this.l.get(i2).onAnimationUpdate(this, this.b, this.f1240a);
            }
        }
        e(this.l);
    }

    public abstract void g(float f2);

    public float getMinimumVisibleChange() {
        return this.j;
    }

    public final void h() {
        if (this.f) {
            return;
        }
        this.f = true;
        if (!this.c) {
            this.b = b();
        }
        float f2 = this.b;
        if (f2 <= this.g && f2 >= this.h) {
            androidx.dynamicanimation.animation.a.d().a(this, 0L);
            return;
        }
        throw new IllegalArgumentException("Starting value need to be in between min value and max value");
    }

    public abstract boolean i(long j2);

    public boolean isRunning() {
        return this.f;
    }

    public void removeEndListener(OnAnimationEndListener onAnimationEndListener) {
        d(this.k, onAnimationEndListener);
    }

    public void removeUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        d(this.l, onAnimationUpdateListener);
    }

    public T setMaxValue(float f2) {
        this.g = f2;
        return this;
    }

    public T setMinValue(float f2) {
        this.h = f2;
        return this;
    }

    public T setMinimumVisibleChange(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        if (f2 > 0.0f) {
            this.j = f2;
            g(f2 * 0.75f);
            return this;
        }
        throw new IllegalArgumentException("Minimum visible change must be positive.");
    }

    public T setStartValue(float f2) {
        this.b = f2;
        this.c = true;
        return this;
    }

    public T setStartVelocity(float f2) {
        this.f1240a = f2;
        return this;
    }

    public void start() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (this.f) {
                return;
            }
            h();
            return;
        }
        throw new AndroidRuntimeException("Animations may only be started on the main thread");
    }

    public <K> DynamicAnimation(K k2, FloatPropertyCompat<K> floatPropertyCompat) {
        this.f1240a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.c = false;
        this.f = false;
        this.g = Float.MAX_VALUE;
        this.h = -Float.MAX_VALUE;
        this.i = 0L;
        this.k = new ArrayList<>();
        this.l = new ArrayList<>();
        this.d = k2;
        this.e = floatPropertyCompat;
        if (floatPropertyCompat != ROTATION && floatPropertyCompat != ROTATION_X && floatPropertyCompat != ROTATION_Y) {
            if (floatPropertyCompat == ALPHA) {
                this.j = 0.00390625f;
                return;
            } else if (floatPropertyCompat != SCALE_X && floatPropertyCompat != SCALE_Y) {
                this.j = 1.0f;
                return;
            } else {
                this.j = 0.00390625f;
                return;
            }
        }
        this.j = 0.1f;
    }
}
