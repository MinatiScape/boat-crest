package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class CompositionLayer extends BaseLayer {
    @Nullable
    public BaseKeyframeAnimation<Float, Float> D;
    public final List<BaseLayer> E;
    public final RectF F;
    public final RectF G;
    public final Paint H;
    @Nullable
    public Boolean I;
    @Nullable
    public Boolean J;
    public boolean K;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2054a;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            f2054a = iArr;
            try {
                iArr[Layer.MatteType.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2054a[Layer.MatteType.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public CompositionLayer(LottieDrawable lottieDrawable, Layer layer, List<Layer> list, LottieComposition lottieComposition) {
        super(lottieDrawable, layer);
        int i;
        BaseLayer baseLayer;
        this.E = new ArrayList();
        this.F = new RectF();
        this.G = new RectF();
        this.H = new Paint();
        this.K = true;
        AnimatableFloatValue o = layer.o();
        if (o != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation = o.createAnimation();
            this.D = createAnimation;
            addAnimation(createAnimation);
            this.D.addUpdateListener(this);
        } else {
            this.D = null;
        }
        LongSparseArray longSparseArray = new LongSparseArray(lottieComposition.getLayers().size());
        int size = list.size() - 1;
        BaseLayer baseLayer2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            Layer layer2 = list.get(size);
            BaseLayer l = BaseLayer.l(this, layer2, lottieDrawable, lottieComposition);
            if (l != null) {
                longSparseArray.put(l.m().getId(), l);
                if (baseLayer2 != null) {
                    baseLayer2.u(l);
                    baseLayer2 = null;
                } else {
                    this.E.add(0, l);
                    int i2 = a.f2054a[layer2.d().ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        baseLayer2 = l;
                    }
                }
            }
            size--;
        }
        for (i = 0; i < longSparseArray.size(); i++) {
            BaseLayer baseLayer3 = (BaseLayer) longSparseArray.get(longSparseArray.keyAt(i));
            if (baseLayer3 != null && (baseLayer = (BaseLayer) longSparseArray.get(baseLayer3.m().e())) != null) {
                baseLayer3.v(baseLayer);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t, lottieValueCallback);
        if (t == LottieProperty.TIME_REMAP) {
            if (lottieValueCallback == null) {
                BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.D;
                if (baseKeyframeAnimation != null) {
                    baseKeyframeAnimation.setValueCallback(null);
                    return;
                }
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.D = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            addAnimation(this.D);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void drawLayer(Canvas canvas, Matrix matrix, int i) {
        L.beginSection("CompositionLayer#draw");
        this.G.set(0.0f, 0.0f, this.q.g(), this.q.f());
        matrix.mapRect(this.G);
        boolean z = this.p.isApplyingOpacityToLayersEnabled() && this.E.size() > 1 && i != 255;
        if (z) {
            this.H.setAlpha(i);
            Utils.saveLayerCompat(canvas, this.G, this.H);
        } else {
            canvas.save();
        }
        if (z) {
            i = 255;
        }
        for (int size = this.E.size() - 1; size >= 0; size--) {
            if (((!this.K && "__container".equals(this.q.getName())) || this.G.isEmpty()) ? true : canvas.clipRect(this.G)) {
                this.E.get(size).draw(canvas, matrix, i);
            }
        }
        canvas.restore();
        L.endSection("CompositionLayer#draw");
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        for (int size = this.E.size() - 1; size >= 0; size--) {
            this.F.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.E.get(size).getBounds(this.F, this.o, true);
            rectF.union(this.F);
        }
    }

    public boolean hasMasks() {
        if (this.J == null) {
            for (int size = this.E.size() - 1; size >= 0; size--) {
                BaseLayer baseLayer = this.E.get(size);
                if (baseLayer instanceof ShapeLayer) {
                    if (baseLayer.n()) {
                        this.J = Boolean.TRUE;
                        return true;
                    }
                } else if ((baseLayer instanceof CompositionLayer) && ((CompositionLayer) baseLayer).hasMasks()) {
                    this.J = Boolean.TRUE;
                    return true;
                }
            }
            this.J = Boolean.FALSE;
        }
        return this.J.booleanValue();
    }

    public boolean hasMatte() {
        if (this.I == null) {
            if (o()) {
                this.I = Boolean.TRUE;
                return true;
            }
            for (int size = this.E.size() - 1; size >= 0; size--) {
                if (this.E.get(size).o()) {
                    this.I = Boolean.TRUE;
                    return true;
                }
            }
            this.I = Boolean.FALSE;
        }
        return this.I.booleanValue();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void resolveChildKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        for (int i2 = 0; i2 < this.E.size(); i2++) {
            this.E.get(i2).resolveKeyPath(keyPath, i, list, keyPath2);
        }
    }

    public void setClipToCompositionBounds(boolean z) {
        this.K = z;
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void setOutlineMasksAndMattes(boolean z) {
        super.setOutlineMasksAndMattes(z);
        for (BaseLayer baseLayer : this.E) {
            baseLayer.setOutlineMasksAndMattes(z);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        super.setProgress(f);
        if (this.D != null) {
            f = ((this.D.getValue().floatValue() * this.q.a().getFrameRate()) - this.q.a().getStartFrame()) / (this.p.getComposition().getDurationFrames() + 0.01f);
        }
        if (this.D == null) {
            f -= this.q.l();
        }
        if (this.q.p() != 0.0f && !"__container".equals(this.q.getName())) {
            f /= this.q.p();
        }
        for (int size = this.E.size() - 1; size >= 0; size--) {
            this.E.get(size).setProgress(f);
        }
    }
}
