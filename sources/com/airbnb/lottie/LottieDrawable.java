package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class LottieDrawable extends Drawable implements Drawable.Callback, Animatable {
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    @Nullable
    public CompositionLayer A;
    public int B;
    public boolean C;
    public boolean D;
    public boolean E;
    public RenderMode F;
    public boolean G;
    public final Matrix H;
    public Bitmap I;
    public Canvas J;
    public Rect K;
    public RectF L;
    public Paint M;
    public Rect N;
    public Rect O;
    public RectF P;
    public RectF Q;
    public Matrix R;
    public Matrix S;
    public boolean T;
    public LottieComposition h;
    public final LottieValueAnimator i;
    public boolean j;
    public boolean k;
    public boolean l;
    public d m;
    public final ArrayList<c> n;
    public final ValueAnimator.AnimatorUpdateListener o;
    @Nullable
    public ImageAssetManager p;
    @Nullable
    public String q;
    @Nullable
    public ImageAssetDelegate r;
    @Nullable
    public FontAssetManager s;
    @Nullable
    public Map<String, Typeface> t;
    @Nullable
    public String u;
    @Nullable
    public FontAssetDelegate v;
    @Nullable
    public TextDelegate w;
    public boolean x;
    public boolean y;
    public boolean z;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface RepeatMode {
    }

    /* loaded from: classes.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (LottieDrawable.this.A != null) {
                LottieDrawable.this.A.setProgress(LottieDrawable.this.i.getAnimatedValueAbsolute());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class b<T> extends LottieValueCallback<T> {
        public final /* synthetic */ SimpleLottieValueCallback c;

        public b(LottieDrawable lottieDrawable, SimpleLottieValueCallback simpleLottieValueCallback) {
            this.c = simpleLottieValueCallback;
        }

        @Override // com.airbnb.lottie.value.LottieValueCallback
        public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
            return (T) this.c.getValue(lottieFrameInfo);
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(LottieComposition lottieComposition);
    }

    /* loaded from: classes.dex */
    public enum d {
        NONE,
        PLAY,
        RESUME
    }

    public LottieDrawable() {
        LottieValueAnimator lottieValueAnimator = new LottieValueAnimator();
        this.i = lottieValueAnimator;
        this.j = true;
        this.k = false;
        this.l = false;
        this.m = d.NONE;
        this.n = new ArrayList<>();
        a aVar = new a();
        this.o = aVar;
        this.y = false;
        this.z = true;
        this.B = 255;
        this.F = RenderMode.AUTOMATIC;
        this.G = false;
        this.H = new Matrix();
        this.T = false;
        lottieValueAnimator.addUpdateListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback, LottieComposition lottieComposition) {
        addValueCallback(keyPath, (KeyPath) obj, (LottieValueCallback<KeyPath>) lottieValueCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(LottieComposition lottieComposition) {
        playAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G(LottieComposition lottieComposition) {
        resumeAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H(int i, LottieComposition lottieComposition) {
        setFrame(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(int i, LottieComposition lottieComposition) {
        setMaxFrame(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(String str, LottieComposition lottieComposition) {
        setMaxFrame(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(float f, LottieComposition lottieComposition) {
        setMaxProgress(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(int i, int i2, LottieComposition lottieComposition) {
        setMinAndMaxFrame(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(String str, LottieComposition lottieComposition) {
        setMinAndMaxFrame(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(String str, String str2, boolean z, LottieComposition lottieComposition) {
        setMinAndMaxFrame(str, str2, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(float f, float f2, LottieComposition lottieComposition) {
        setMinAndMaxProgress(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(int i, LottieComposition lottieComposition) {
        setMinFrame(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Q(String str, LottieComposition lottieComposition) {
        setMinFrame(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R(float f, LottieComposition lottieComposition) {
        setMinProgress(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S(float f, LottieComposition lottieComposition) {
        setProgress(f);
    }

    public final FontAssetManager A() {
        if (getCallback() == null) {
            return null;
        }
        if (this.s == null) {
            FontAssetManager fontAssetManager = new FontAssetManager(getCallback(), this.v);
            this.s = fontAssetManager;
            String str = this.u;
            if (str != null) {
                fontAssetManager.setDefaultFontFileExtension(str);
            }
        }
        return this.s;
    }

    public final ImageAssetManager B() {
        ImageAssetManager imageAssetManager = this.p;
        if (imageAssetManager != null && !imageAssetManager.hasSameContext(z())) {
            this.p = null;
        }
        if (this.p == null) {
            this.p = new ImageAssetManager(getCallback(), this.q, this.r, this.h.getImages());
        }
        return this.p;
    }

    public final boolean C() {
        Drawable.Callback callback = getCallback();
        if (callback instanceof View) {
            ViewParent parent = ((View) callback).getParent();
            if (Build.VERSION.SDK_INT < 18 || !(parent instanceof ViewGroup)) {
                return false;
            }
            return !((ViewGroup) parent).getClipChildren();
        }
        return false;
    }

    public boolean D() {
        if (isVisible()) {
            return this.i.isRunning();
        }
        d dVar = this.m;
        return dVar == d.PLAY || dVar == d.RESUME;
    }

    public final void T(Canvas canvas, CompositionLayer compositionLayer) {
        if (this.h == null || compositionLayer == null) {
            return;
        }
        y();
        canvas.getMatrix(this.R);
        canvas.getClipBounds(this.K);
        u(this.K, this.L);
        this.R.mapRect(this.L);
        v(this.L, this.K);
        if (this.z) {
            this.Q.set(0.0f, 0.0f, getIntrinsicWidth(), getIntrinsicHeight());
        } else {
            compositionLayer.getBounds(this.Q, null, false);
        }
        this.R.mapRect(this.Q);
        Rect bounds = getBounds();
        float width = bounds.width() / getIntrinsicWidth();
        float height = bounds.height() / getIntrinsicHeight();
        U(this.Q, width, height);
        if (!C()) {
            RectF rectF = this.Q;
            Rect rect = this.K;
            rectF.intersect(rect.left, rect.top, rect.right, rect.bottom);
        }
        int ceil = (int) Math.ceil(this.Q.width());
        int ceil2 = (int) Math.ceil(this.Q.height());
        if (ceil == 0 || ceil2 == 0) {
            return;
        }
        x(ceil, ceil2);
        if (this.T) {
            this.H.set(this.R);
            this.H.preScale(width, height);
            Matrix matrix = this.H;
            RectF rectF2 = this.Q;
            matrix.postTranslate(-rectF2.left, -rectF2.top);
            this.I.eraseColor(0);
            compositionLayer.draw(this.J, this.H, this.B);
            this.R.invert(this.S);
            this.S.mapRect(this.P, this.Q);
            v(this.P, this.O);
        }
        this.N.set(0, 0, ceil, ceil2);
        canvas.drawBitmap(this.I, this.N, this.O, this.M);
    }

    public final void U(RectF rectF, float f, float f2) {
        rectF.set(rectF.left * f, rectF.top * f2, rectF.right * f, rectF.bottom * f2);
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.i.addListener(animatorListener);
    }

    @RequiresApi(api = 19)
    public void addAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.i.addPauseListener(animatorPauseListener);
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.i.addUpdateListener(animatorUpdateListener);
    }

    public <T> void addValueCallback(final KeyPath keyPath, final T t, @Nullable final LottieValueCallback<T> lottieValueCallback) {
        CompositionLayer compositionLayer = this.A;
        if (compositionLayer == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.s
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.E(keyPath, t, lottieValueCallback, lottieComposition);
                }
            });
            return;
        }
        boolean z = true;
        if (keyPath == KeyPath.COMPOSITION) {
            compositionLayer.addValueCallback(t, lottieValueCallback);
        } else if (keyPath.getResolvedElement() != null) {
            keyPath.getResolvedElement().addValueCallback(t, lottieValueCallback);
        } else {
            List<KeyPath> resolveKeyPath = resolveKeyPath(keyPath);
            for (int i = 0; i < resolveKeyPath.size(); i++) {
                resolveKeyPath.get(i).getResolvedElement().addValueCallback(t, lottieValueCallback);
            }
            z = true ^ resolveKeyPath.isEmpty();
        }
        if (z) {
            invalidateSelf();
            if (t == LottieProperty.TIME_REMAP) {
                setProgress(getProgress());
            }
        }
    }

    public void cancelAnimation() {
        this.n.clear();
        this.i.cancel();
        if (isVisible()) {
            return;
        }
        this.m = d.NONE;
    }

    public void clearComposition() {
        if (this.i.isRunning()) {
            this.i.cancel();
            if (!isVisible()) {
                this.m = d.NONE;
            }
        }
        this.h = null;
        this.A = null;
        this.p = null;
        this.i.clearComposition();
        invalidateSelf();
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        L.beginSection("Drawable#draw");
        if (this.l) {
            try {
                if (this.G) {
                    T(canvas, this.A);
                } else {
                    w(canvas);
                }
            } catch (Throwable th) {
                Logger.error("Lottie crashed in draw!", th);
            }
        } else if (this.G) {
            T(canvas, this.A);
        } else {
            w(canvas);
        }
        this.T = false;
        L.endSection("Drawable#draw");
    }

    public boolean enableMergePathsForKitKatAndAbove() {
        return this.x;
    }

    @MainThread
    public void endAnimation() {
        this.n.clear();
        this.i.endAnimation();
        if (isVisible()) {
            return;
        }
        this.m = d.NONE;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.B;
    }

    @Nullable
    public Bitmap getBitmapForId(String str) {
        ImageAssetManager B = B();
        if (B != null) {
            return B.bitmapForId(str);
        }
        return null;
    }

    public boolean getClipToCompositionBounds() {
        return this.z;
    }

    public LottieComposition getComposition() {
        return this.h;
    }

    public int getFrame() {
        return (int) this.i.getFrame();
    }

    @Nullable
    @Deprecated
    public Bitmap getImageAsset(String str) {
        ImageAssetManager B = B();
        if (B != null) {
            return B.bitmapForId(str);
        }
        LottieComposition lottieComposition = this.h;
        LottieImageAsset lottieImageAsset = lottieComposition == null ? null : lottieComposition.getImages().get(str);
        if (lottieImageAsset != null) {
            return lottieImageAsset.getBitmap();
        }
        return null;
    }

    @Nullable
    public String getImageAssetsFolder() {
        return this.q;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().width();
    }

    @Nullable
    public LottieImageAsset getLottieImageAssetForId(String str) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            return null;
        }
        return lottieComposition.getImages().get(str);
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.y;
    }

    public float getMaxFrame() {
        return this.i.getMaxFrame();
    }

    public float getMinFrame() {
        return this.i.getMinFrame();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Nullable
    public PerformanceTracker getPerformanceTracker() {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition != null) {
            return lottieComposition.getPerformanceTracker();
        }
        return null;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.i.getAnimatedValueAbsolute();
    }

    public RenderMode getRenderMode() {
        return this.G ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    public int getRepeatCount() {
        return this.i.getRepeatCount();
    }

    @SuppressLint({"WrongConstant"})
    public int getRepeatMode() {
        return this.i.getRepeatMode();
    }

    public float getSpeed() {
        return this.i.getSpeed();
    }

    @Nullable
    public TextDelegate getTextDelegate() {
        return this.w;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Typeface getTypeface(Font font) {
        Map<String, Typeface> map = this.t;
        if (map != null) {
            String family = font.getFamily();
            if (map.containsKey(family)) {
                return map.get(family);
            }
            String name = font.getName();
            if (map.containsKey(name)) {
                return map.get(name);
            }
            String str = font.getFamily() + "-" + font.getStyle();
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        FontAssetManager A = A();
        if (A != null) {
            return A.getTypeface(font);
        }
        return null;
    }

    public boolean hasMasks() {
        CompositionLayer compositionLayer = this.A;
        return compositionLayer != null && compositionLayer.hasMasks();
    }

    public boolean hasMatte() {
        CompositionLayer compositionLayer = this.A;
        return compositionLayer != null && compositionLayer.hasMatte();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.T) {
            return;
        }
        this.T = true;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public boolean isAnimating() {
        LottieValueAnimator lottieValueAnimator = this.i;
        if (lottieValueAnimator == null) {
            return false;
        }
        return lottieValueAnimator.isRunning();
    }

    public boolean isApplyingOpacityToLayersEnabled() {
        return this.E;
    }

    public boolean isLooping() {
        return this.i.getRepeatCount() == -1;
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.x;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return isAnimating();
    }

    @Deprecated
    public void loop(boolean z) {
        this.i.setRepeatCount(z ? -1 : 0);
    }

    public void pauseAnimation() {
        this.n.clear();
        this.i.pauseAnimation();
        if (isVisible()) {
            return;
        }
        this.m = d.NONE;
    }

    @MainThread
    public void playAnimation() {
        if (this.A == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.x
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.F(lottieComposition);
                }
            });
            return;
        }
        t();
        if (r() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.i.playAnimation();
                this.m = d.NONE;
            } else {
                this.m = d.PLAY;
            }
        }
        if (r()) {
            return;
        }
        setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
        this.i.endAnimation();
        if (isVisible()) {
            return;
        }
        this.m = d.NONE;
    }

    public final boolean r() {
        return this.j || this.k;
    }

    public void removeAllAnimatorListeners() {
        this.i.removeAllListeners();
    }

    public void removeAllUpdateListeners() {
        this.i.removeAllUpdateListeners();
        this.i.addUpdateListener(this.o);
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.i.removeListener(animatorListener);
    }

    @RequiresApi(api = 19)
    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.i.removePauseListener(animatorPauseListener);
    }

    public void removeAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.i.removeUpdateListener(animatorUpdateListener);
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        if (this.A == null) {
            Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.A.resolveKeyPath(keyPath, 0, arrayList, new KeyPath(new String[0]));
        return arrayList;
    }

    @MainThread
    public void resumeAnimation() {
        if (this.A == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.q
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.G(lottieComposition);
                }
            });
            return;
        }
        t();
        if (r() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.i.resumeAnimation();
                this.m = d.NONE;
            } else {
                this.m = d.RESUME;
            }
        }
        if (r()) {
            return;
        }
        setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
        this.i.endAnimation();
        if (isVisible()) {
            return;
        }
        this.m = d.NONE;
    }

    public void reverseAnimationSpeed() {
        this.i.reverseAnimationSpeed();
    }

    public final void s() {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            return;
        }
        CompositionLayer compositionLayer = new CompositionLayer(this, LayerParser.parse(lottieComposition), lottieComposition.getLayers(), lottieComposition);
        this.A = compositionLayer;
        if (this.D) {
            compositionLayer.setOutlineMasksAndMattes(true);
        }
        this.A.setClipToCompositionBounds(this.z);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        this.B = i;
        invalidateSelf();
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.E = z;
    }

    public void setClipToCompositionBounds(boolean z) {
        if (z != this.z) {
            this.z = z;
            CompositionLayer compositionLayer = this.A;
            if (compositionLayer != null) {
                compositionLayer.setClipToCompositionBounds(z);
            }
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        Logger.warning("Use addColorFilter instead.");
    }

    public boolean setComposition(LottieComposition lottieComposition) {
        if (this.h == lottieComposition) {
            return false;
        }
        this.T = true;
        clearComposition();
        this.h = lottieComposition;
        s();
        this.i.setComposition(lottieComposition);
        setProgress(this.i.getAnimatedFraction());
        Iterator it = new ArrayList(this.n).iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar != null) {
                cVar.a(lottieComposition);
            }
            it.remove();
        }
        this.n.clear();
        lottieComposition.setPerformanceTrackingEnabled(this.C);
        t();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ImageView imageView = (ImageView) callback;
            imageView.setImageDrawable(null);
            imageView.setImageDrawable(this);
        }
        return true;
    }

    public void setDefaultFontFileExtension(String str) {
        this.u = str;
        FontAssetManager A = A();
        if (A != null) {
            A.setDefaultFontFileExtension(str);
        }
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate) {
        this.v = fontAssetDelegate;
        FontAssetManager fontAssetManager = this.s;
        if (fontAssetManager != null) {
            fontAssetManager.setDelegate(fontAssetDelegate);
        }
    }

    public void setFontMap(@Nullable Map<String, Typeface> map) {
        if (map == this.t) {
            return;
        }
        this.t = map;
        invalidateSelf();
    }

    public void setFrame(final int i) {
        if (this.h == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.d0
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.H(i, lottieComposition);
                }
            });
        } else {
            this.i.setFrame(i);
        }
    }

    public void setIgnoreDisabledSystemAnimations(boolean z) {
        this.k = z;
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate) {
        this.r = imageAssetDelegate;
        ImageAssetManager imageAssetManager = this.p;
        if (imageAssetManager != null) {
            imageAssetManager.setDelegate(imageAssetDelegate);
        }
    }

    public void setImagesAssetsFolder(@Nullable String str) {
        this.q = str;
    }

    public void setMaintainOriginalImageBounds(boolean z) {
        this.y = z;
    }

    public void setMaxFrame(final int i) {
        if (this.h == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.e0
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.I(i, lottieComposition);
                }
            });
        } else {
            this.i.setMaxFrame(i + 0.99f);
        }
    }

    public void setMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) final float f) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.y
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.K(f, lottieComposition2);
                }
            });
        } else {
            this.i.setMaxFrame(MiscUtils.lerp(lottieComposition.getStartFrame(), this.h.getEndFrame(), f));
        }
    }

    public void setMinAndMaxFrame(final String str) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.v
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.M(str, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            int i = (int) marker.startFrame;
            setMinAndMaxFrame(i, ((int) marker.durationFrames) + i);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void setMinAndMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) final float f, @FloatRange(from = 0.0d, to = 1.0d) final float f2) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.b0
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.O(f, f2, lottieComposition2);
                }
            });
        } else {
            setMinAndMaxFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.h.getEndFrame(), f), (int) MiscUtils.lerp(this.h.getStartFrame(), this.h.getEndFrame(), f2));
        }
    }

    public void setMinFrame(final int i) {
        if (this.h == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.c0
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.P(i, lottieComposition);
                }
            });
        } else {
            this.i.setMinFrame(i);
        }
    }

    public void setMinProgress(final float f) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.z
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.R(f, lottieComposition2);
                }
            });
        } else {
            setMinFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.h.getEndFrame(), f));
        }
    }

    public void setOutlineMasksAndMattes(boolean z) {
        if (this.D == z) {
            return;
        }
        this.D = z;
        CompositionLayer compositionLayer = this.A;
        if (compositionLayer != null) {
            compositionLayer.setOutlineMasksAndMattes(z);
        }
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.C = z;
        LottieComposition lottieComposition = this.h;
        if (lottieComposition != null) {
            lottieComposition.setPerformanceTrackingEnabled(z);
        }
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) final float f) {
        if (this.h == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.a0
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.S(f, lottieComposition);
                }
            });
            return;
        }
        L.beginSection("Drawable#setProgress");
        this.i.setFrame(this.h.getFrameForProgress(f));
        L.endSection("Drawable#setProgress");
    }

    public void setRenderMode(RenderMode renderMode) {
        this.F = renderMode;
        t();
    }

    public void setRepeatCount(int i) {
        this.i.setRepeatCount(i);
    }

    public void setRepeatMode(int i) {
        this.i.setRepeatMode(i);
    }

    public void setSafeMode(boolean z) {
        this.l = z;
    }

    public void setSpeed(float f) {
        this.i.setSpeed(f);
    }

    public void setSystemAnimationsAreEnabled(Boolean bool) {
        this.j = bool.booleanValue();
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.w = textDelegate;
    }

    public void setUseCompositionFrameRate(boolean z) {
        this.i.setUseCompositionFrameRate(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean z3 = !isVisible();
        boolean visible = super.setVisible(z, z2);
        if (z) {
            d dVar = this.m;
            if (dVar == d.PLAY) {
                playAnimation();
            } else if (dVar == d.RESUME) {
                resumeAnimation();
            }
        } else if (this.i.isRunning()) {
            pauseAnimation();
            this.m = d.RESUME;
        } else if (!z3) {
            this.m = d.NONE;
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable
    @MainThread
    public void start() {
        Drawable.Callback callback = getCallback();
        if ((callback instanceof View) && ((View) callback).isInEditMode()) {
            return;
        }
        playAnimation();
    }

    @Override // android.graphics.drawable.Animatable
    @MainThread
    public void stop() {
        endAnimation();
    }

    public final void t() {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            return;
        }
        this.G = this.F.useSoftwareRendering(Build.VERSION.SDK_INT, lottieComposition.hasDashPattern(), lottieComposition.getMaskAndMatteCount());
    }

    public final void u(Rect rect, RectF rectF) {
        rectF.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }

    @Nullable
    public Bitmap updateBitmap(String str, @Nullable Bitmap bitmap) {
        ImageAssetManager B = B();
        if (B == null) {
            Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return null;
        }
        Bitmap updateBitmap = B.updateBitmap(str, bitmap);
        invalidateSelf();
        return updateBitmap;
    }

    public boolean useTextGlyphs() {
        return this.t == null && this.w == null && this.h.getCharacters().size() > 0;
    }

    public final void v(RectF rectF, Rect rect) {
        rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
    }

    public final void w(Canvas canvas) {
        CompositionLayer compositionLayer = this.A;
        LottieComposition lottieComposition = this.h;
        if (compositionLayer == null || lottieComposition == null) {
            return;
        }
        this.H.reset();
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            this.H.preScale(bounds.width() / lottieComposition.getBounds().width(), bounds.height() / lottieComposition.getBounds().height());
            this.H.preTranslate(bounds.left, bounds.top);
        }
        compositionLayer.draw(canvas, this.H, this.B);
    }

    public final void x(int i, int i2) {
        Bitmap bitmap = this.I;
        if (bitmap != null && bitmap.getWidth() >= i && this.I.getHeight() >= i2) {
            if (this.I.getWidth() > i || this.I.getHeight() > i2) {
                Bitmap createBitmap = Bitmap.createBitmap(this.I, 0, 0, i, i2);
                this.I = createBitmap;
                this.J.setBitmap(createBitmap);
                this.T = true;
                return;
            }
            return;
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        this.I = createBitmap2;
        this.J.setBitmap(createBitmap2);
        this.T = true;
    }

    public final void y() {
        if (this.J != null) {
            return;
        }
        this.J = new Canvas();
        this.Q = new RectF();
        this.R = new Matrix();
        this.S = new Matrix();
        this.K = new Rect();
        this.L = new RectF();
        this.M = new LPaint();
        this.N = new Rect();
        this.O = new Rect();
        this.P = new RectF();
    }

    @Nullable
    public final Context z() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    public void enableMergePathsForKitKatAndAbove(boolean z) {
        if (this.x == z) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            Logger.warning("Merge paths are not supported pre-Kit Kat.");
            return;
        }
        this.x = z;
        if (this.h != null) {
            s();
        }
    }

    public void setMaxFrame(final String str) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.u
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.J(str, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            setMaxFrame((int) (marker.startFrame + marker.durationFrames));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void setMinFrame(final String str) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.t
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.Q(str, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            setMinFrame((int) marker.startFrame);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void setMinAndMaxFrame(final String str, final String str2, final boolean z) {
        LottieComposition lottieComposition = this.h;
        if (lottieComposition == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.w
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.N(str, str2, z, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            int i = (int) marker.startFrame;
            Marker marker2 = this.h.getMarker(str2);
            if (marker2 != null) {
                setMinAndMaxFrame(i, (int) (marker2.startFrame + (z ? 1.0f : 0.0f)));
                return;
            }
            throw new IllegalArgumentException("Cannot find marker with name " + str2 + ".");
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void draw(Canvas canvas, Matrix matrix) {
        CompositionLayer compositionLayer = this.A;
        LottieComposition lottieComposition = this.h;
        if (compositionLayer == null || lottieComposition == null) {
            return;
        }
        if (this.G) {
            canvas.save();
            canvas.concat(matrix);
            T(canvas, compositionLayer);
            canvas.restore();
        } else {
            compositionLayer.draw(canvas, matrix, this.B);
        }
        this.T = false;
    }

    public <T> void addValueCallback(KeyPath keyPath, T t, SimpleLottieValueCallback<T> simpleLottieValueCallback) {
        addValueCallback(keyPath, (KeyPath) t, (LottieValueCallback<KeyPath>) new b(this, simpleLottieValueCallback));
    }

    public void setMinAndMaxFrame(final int i, final int i2) {
        if (this.h == null) {
            this.n.add(new c() { // from class: com.airbnb.lottie.f0
                @Override // com.airbnb.lottie.LottieDrawable.c
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.L(i, i2, lottieComposition);
                }
            });
        } else {
            this.i.setMinAndMaxFrames(i, i2 + 0.99f);
        }
    }
}
