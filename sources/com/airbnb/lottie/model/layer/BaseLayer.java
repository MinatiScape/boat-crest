package com.airbnb.lottie.model.layer;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.CallSuper;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    @Nullable
    public Paint A;
    public float B;
    @Nullable
    public BlurMaskFilter C;

    /* renamed from: a  reason: collision with root package name */
    public final Path f2052a = new Path();
    public final Matrix b = new Matrix();
    public final Matrix c = new Matrix();
    public final Paint d = new LPaint(1);
    public final Paint e = new LPaint(1, PorterDuff.Mode.DST_IN);
    public final Paint f = new LPaint(1, PorterDuff.Mode.DST_OUT);
    public final Paint g;
    public final Paint h;
    public final RectF i;
    public final RectF j;
    public final RectF k;
    public final RectF l;
    public final RectF m;
    public final String n;
    public final Matrix o;
    public final LottieDrawable p;
    public final Layer q;
    @Nullable
    public MaskKeyframeAnimation r;
    @Nullable
    public FloatKeyframeAnimation s;
    @Nullable
    public BaseLayer t;
    @Nullable
    public BaseLayer u;
    public List<BaseLayer> v;
    public final List<BaseKeyframeAnimation<?, ?>> w;
    public final TransformKeyframeAnimation x;
    public boolean y;
    public boolean z;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2053a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Mask.MaskMode.values().length];
            b = iArr;
            try {
                iArr[Mask.MaskMode.MASK_MODE_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Layer.LayerType.values().length];
            f2053a = iArr2;
            try {
                iArr2[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2053a[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2053a[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2053a[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2053a[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2053a[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2053a[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public BaseLayer(LottieDrawable lottieDrawable, Layer layer) {
        LPaint lPaint = new LPaint(1);
        this.g = lPaint;
        this.h = new LPaint(PorterDuff.Mode.CLEAR);
        this.i = new RectF();
        this.j = new RectF();
        this.k = new RectF();
        this.l = new RectF();
        this.m = new RectF();
        this.o = new Matrix();
        this.w = new ArrayList();
        this.y = true;
        this.B = 0.0f;
        this.p = lottieDrawable;
        this.q = layer;
        this.n = layer.getName() + "#draw";
        if (layer.d() == Layer.MatteType.INVERT) {
            lPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            lPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        TransformKeyframeAnimation createAnimation = layer.q().createAnimation();
        this.x = createAnimation;
        createAnimation.addListener(this);
        if (layer.c() != null && !layer.c().isEmpty()) {
            MaskKeyframeAnimation maskKeyframeAnimation = new MaskKeyframeAnimation(layer.c());
            this.r = maskKeyframeAnimation;
            for (BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation : maskKeyframeAnimation.getMaskAnimations()) {
                baseKeyframeAnimation.addUpdateListener(this);
            }
            for (BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 : this.r.getOpacityAnimations()) {
                addAnimation(baseKeyframeAnimation2);
                baseKeyframeAnimation2.addUpdateListener(this);
            }
        }
        x();
    }

    @Nullable
    public static BaseLayer l(CompositionLayer compositionLayer, Layer layer, LottieDrawable lottieDrawable, LottieComposition lottieComposition) {
        switch (a.f2053a[layer.getLayerType().ordinal()]) {
            case 1:
                return new ShapeLayer(lottieDrawable, layer, compositionLayer, lottieComposition);
            case 2:
                return new CompositionLayer(lottieDrawable, layer, lottieComposition.getPrecomps(layer.getRefId()), lottieComposition);
            case 3:
                return new SolidLayer(lottieDrawable, layer);
            case 4:
                return new ImageLayer(lottieDrawable, layer);
            case 5:
                return new NullLayer(lottieDrawable, layer);
            case 6:
                return new TextLayer(lottieDrawable, layer);
            default:
                Logger.warning("Unknown layer type " + layer.getLayerType());
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s() {
        w(this.s.getFloatValue() == 1.0f);
    }

    public void addAnimation(@Nullable BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        if (baseKeyframeAnimation == null) {
            return;
        }
        this.w.add(baseKeyframeAnimation);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    @CallSuper
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        this.x.applyValueCallback(t, lottieValueCallback);
    }

    public final void b(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        this.f2052a.set(baseKeyframeAnimation.getValue());
        this.f2052a.transform(matrix);
        this.d.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        canvas.drawPath(this.f2052a, this.d);
    }

    public final void c(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.i, this.e);
        this.f2052a.set(baseKeyframeAnimation.getValue());
        this.f2052a.transform(matrix);
        this.d.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        canvas.drawPath(this.f2052a, this.d);
        canvas.restore();
    }

    public final void d(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.i, this.d);
        canvas.drawRect(this.i, this.d);
        this.f2052a.set(baseKeyframeAnimation.getValue());
        this.f2052a.transform(matrix);
        this.d.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        canvas.drawPath(this.f2052a, this.f);
        canvas.restore();
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int i) {
        Paint paint;
        Integer value;
        L.beginSection(this.n);
        if (this.y && !this.q.isHidden()) {
            j();
            L.beginSection("Layer#parentMatrix");
            this.b.reset();
            this.b.set(matrix);
            for (int size = this.v.size() - 1; size >= 0; size--) {
                this.b.preConcat(this.v.get(size).x.getMatrix());
            }
            L.endSection("Layer#parentMatrix");
            int i2 = 100;
            BaseKeyframeAnimation<?, Integer> opacity = this.x.getOpacity();
            if (opacity != null && (value = opacity.getValue()) != null) {
                i2 = value.intValue();
            }
            int i3 = (int) ((((i / 255.0f) * i2) / 100.0f) * 255.0f);
            if (!o() && !n()) {
                this.b.preConcat(this.x.getMatrix());
                L.beginSection("Layer#drawLayer");
                drawLayer(canvas, this.b, i3);
                L.endSection("Layer#drawLayer");
                t(L.endSection(this.n));
                return;
            }
            L.beginSection("Layer#computeBounds");
            getBounds(this.i, this.b, false);
            q(this.i, matrix);
            this.b.preConcat(this.x.getMatrix());
            p(this.i, this.b);
            this.j.set(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
            canvas.getMatrix(this.c);
            if (!this.c.isIdentity()) {
                Matrix matrix2 = this.c;
                matrix2.invert(matrix2);
                this.c.mapRect(this.j);
            }
            if (!this.i.intersect(this.j)) {
                this.i.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
            L.endSection("Layer#computeBounds");
            if (this.i.width() >= 1.0f && this.i.height() >= 1.0f) {
                L.beginSection("Layer#saveLayer");
                this.d.setAlpha(255);
                Utils.saveLayerCompat(canvas, this.i, this.d);
                L.endSection("Layer#saveLayer");
                k(canvas);
                L.beginSection("Layer#drawLayer");
                drawLayer(canvas, this.b, i3);
                L.endSection("Layer#drawLayer");
                if (n()) {
                    g(canvas, this.b);
                }
                if (o()) {
                    L.beginSection("Layer#drawMatte");
                    L.beginSection("Layer#saveLayer");
                    Utils.saveLayerCompat(canvas, this.i, this.g, 19);
                    L.endSection("Layer#saveLayer");
                    k(canvas);
                    this.t.draw(canvas, matrix, i3);
                    L.beginSection("Layer#restoreLayer");
                    canvas.restore();
                    L.endSection("Layer#restoreLayer");
                    L.endSection("Layer#drawMatte");
                }
                L.beginSection("Layer#restoreLayer");
                canvas.restore();
                L.endSection("Layer#restoreLayer");
            }
            if (this.z && (paint = this.A) != null) {
                paint.setStyle(Paint.Style.STROKE);
                this.A.setColor(-251901);
                this.A.setStrokeWidth(4.0f);
                canvas.drawRect(this.i, this.A);
                this.A.setStyle(Paint.Style.FILL);
                this.A.setColor(1357638635);
                canvas.drawRect(this.i, this.A);
            }
            t(L.endSection(this.n));
            return;
        }
        L.endSection(this.n);
    }

    public abstract void drawLayer(Canvas canvas, Matrix matrix, int i);

    public final void e(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.i, this.e);
        canvas.drawRect(this.i, this.d);
        this.f.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        this.f2052a.set(baseKeyframeAnimation.getValue());
        this.f2052a.transform(matrix);
        canvas.drawPath(this.f2052a, this.f);
        canvas.restore();
    }

    public final void f(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.i, this.f);
        canvas.drawRect(this.i, this.d);
        this.f.setAlpha((int) (baseKeyframeAnimation2.getValue().intValue() * 2.55f));
        this.f2052a.set(baseKeyframeAnimation.getValue());
        this.f2052a.transform(matrix);
        canvas.drawPath(this.f2052a, this.f);
        canvas.restore();
    }

    public final void g(Canvas canvas, Matrix matrix) {
        L.beginSection("Layer#saveLayer");
        Utils.saveLayerCompat(canvas, this.i, this.e, 19);
        if (Build.VERSION.SDK_INT < 28) {
            k(canvas);
        }
        L.endSection("Layer#saveLayer");
        for (int i = 0; i < this.r.getMasks().size(); i++) {
            Mask mask = this.r.getMasks().get(i);
            BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation = this.r.getMaskAnimations().get(i);
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.r.getOpacityAnimations().get(i);
            int i2 = a.b[mask.getMaskMode().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    if (i == 0) {
                        this.d.setColor(ViewCompat.MEASURED_STATE_MASK);
                        this.d.setAlpha(255);
                        canvas.drawRect(this.i, this.d);
                    }
                    if (mask.isInverted()) {
                        f(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                    } else {
                        h(canvas, matrix, baseKeyframeAnimation);
                    }
                } else if (i2 != 3) {
                    if (i2 == 4) {
                        if (mask.isInverted()) {
                            d(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                        } else {
                            b(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                        }
                    }
                } else if (mask.isInverted()) {
                    e(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                } else {
                    c(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                }
            } else if (i()) {
                this.d.setAlpha(255);
                canvas.drawRect(this.i, this.d);
            }
        }
        L.beginSection("Layer#restoreLayer");
        canvas.restore();
        L.endSection("Layer#restoreLayer");
    }

    @Nullable
    public BlurEffect getBlurEffect() {
        return this.q.getBlurEffect();
    }

    public BlurMaskFilter getBlurMaskFilter(float f) {
        if (this.B == f) {
            return this.C;
        }
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(f / 2.0f, BlurMaskFilter.Blur.NORMAL);
        this.C = blurMaskFilter;
        this.B = f;
        return blurMaskFilter;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    @CallSuper
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.i.set(0.0f, 0.0f, 0.0f, 0.0f);
        j();
        this.o.set(matrix);
        if (z) {
            List<BaseLayer> list = this.v;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.o.preConcat(this.v.get(size).x.getMatrix());
                }
            } else {
                BaseLayer baseLayer = this.u;
                if (baseLayer != null) {
                    this.o.preConcat(baseLayer.x.getMatrix());
                }
            }
        }
        this.o.preConcat(this.x.getMatrix());
    }

    @Nullable
    public DropShadowEffect getDropShadowEffect() {
        return this.q.getDropShadowEffect();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.q.getName();
    }

    public final void h(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation) {
        this.f2052a.set(baseKeyframeAnimation.getValue());
        this.f2052a.transform(matrix);
        canvas.drawPath(this.f2052a, this.f);
    }

    public final boolean i() {
        if (this.r.getMaskAnimations().isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.r.getMasks().size(); i++) {
            if (this.r.getMasks().get(i).getMaskMode() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    public final void j() {
        if (this.v != null) {
            return;
        }
        if (this.u == null) {
            this.v = Collections.emptyList();
            return;
        }
        this.v = new ArrayList();
        for (BaseLayer baseLayer = this.u; baseLayer != null; baseLayer = baseLayer.u) {
            this.v.add(baseLayer);
        }
    }

    public final void k(Canvas canvas) {
        L.beginSection("Layer#clearLayer");
        RectF rectF = this.i;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.h);
        L.endSection("Layer#clearLayer");
    }

    public Layer m() {
        return this.q;
    }

    public boolean n() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.r;
        return (maskKeyframeAnimation == null || maskKeyframeAnimation.getMaskAnimations().isEmpty()) ? false : true;
    }

    public boolean o() {
        return this.t != null;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        r();
    }

    public final void p(RectF rectF, Matrix matrix) {
        this.k.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (n()) {
            int size = this.r.getMasks().size();
            for (int i = 0; i < size; i++) {
                Mask mask = this.r.getMasks().get(i);
                Path value = this.r.getMaskAnimations().get(i).getValue();
                if (value != null) {
                    this.f2052a.set(value);
                    this.f2052a.transform(matrix);
                    int i2 = a.b[mask.getMaskMode().ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        return;
                    }
                    if ((i2 == 3 || i2 == 4) && mask.isInverted()) {
                        return;
                    }
                    this.f2052a.computeBounds(this.m, false);
                    if (i == 0) {
                        this.k.set(this.m);
                    } else {
                        RectF rectF2 = this.k;
                        rectF2.set(Math.min(rectF2.left, this.m.left), Math.min(this.k.top, this.m.top), Math.max(this.k.right, this.m.right), Math.max(this.k.bottom, this.m.bottom));
                    }
                }
            }
            if (rectF.intersect(this.k)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final void q(RectF rectF, Matrix matrix) {
        if (o() && this.q.d() != Layer.MatteType.INVERT) {
            this.l.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.t.getBounds(this.l, matrix, true);
            if (rectF.intersect(this.l)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final void r() {
        this.p.invalidateSelf();
    }

    public void removeAnimation(BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.w.remove(baseKeyframeAnimation);
    }

    public void resolveChildKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        BaseLayer baseLayer = this.t;
        if (baseLayer != null) {
            KeyPath addKey = keyPath2.addKey(baseLayer.getName());
            if (keyPath.fullyResolvesTo(this.t.getName(), i)) {
                list.add(addKey.resolve(this.t));
            }
            if (keyPath.propagateToChildren(getName(), i)) {
                this.t.resolveChildKeyPath(keyPath, keyPath.incrementDepthBy(this.t.getName(), i) + i, list, addKey);
            }
        }
        if (keyPath.matches(getName(), i)) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.addKey(getName());
                if (keyPath.fullyResolvesTo(getName(), i)) {
                    list.add(keyPath2.resolve(this));
                }
            }
            if (keyPath.propagateToChildren(getName(), i)) {
                resolveChildKeyPath(keyPath, i + keyPath.incrementDepthBy(getName(), i), list, keyPath2);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
    }

    public void setOutlineMasksAndMattes(boolean z) {
        if (z && this.A == null) {
            this.A = new LPaint();
        }
        this.z = z;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.x.setProgress(f);
        if (this.r != null) {
            for (int i = 0; i < this.r.getMaskAnimations().size(); i++) {
                this.r.getMaskAnimations().get(i).setProgress(f);
            }
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.s;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.setProgress(f);
        }
        BaseLayer baseLayer = this.t;
        if (baseLayer != null) {
            baseLayer.setProgress(f);
        }
        for (int i2 = 0; i2 < this.w.size(); i2++) {
            this.w.get(i2).setProgress(f);
        }
    }

    public final void t(float f) {
        this.p.getComposition().getPerformanceTracker().recordRenderTime(this.q.getName(), f);
    }

    public void u(@Nullable BaseLayer baseLayer) {
        this.t = baseLayer;
    }

    public void v(@Nullable BaseLayer baseLayer) {
        this.u = baseLayer;
    }

    public final void w(boolean z) {
        if (z != this.y) {
            this.y = z;
            r();
        }
    }

    public final void x() {
        if (!this.q.b().isEmpty()) {
            FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.q.b());
            this.s = floatKeyframeAnimation;
            floatKeyframeAnimation.setIsDiscrete();
            this.s.addUpdateListener(new BaseKeyframeAnimation.AnimationListener() { // from class: com.airbnb.lottie.model.layer.a
                @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
                public final void onValueChanged() {
                    BaseLayer.this.s();
                }
            });
            w(this.s.getValue().floatValue() == 1.0f);
            addAnimation(this.s);
            return;
        }
        w(true);
    }
}
