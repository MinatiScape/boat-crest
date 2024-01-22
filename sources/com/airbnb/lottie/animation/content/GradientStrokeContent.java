package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;
/* loaded from: classes.dex */
public class GradientStrokeContent extends BaseStrokeContent {
    @Nullable
    public ValueCallbackKeyframeAnimation A;
    public final String q;
    public final boolean r;
    public final LongSparseArray<LinearGradient> s;
    public final LongSparseArray<RadialGradient> t;
    public final RectF u;
    public final GradientType v;
    public final int w;
    public final BaseKeyframeAnimation<GradientColor, GradientColor> x;
    public final BaseKeyframeAnimation<PointF, PointF> y;
    public final BaseKeyframeAnimation<PointF, PointF> z;

    public GradientStrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, GradientStroke gradientStroke) {
        super(lottieDrawable, baseLayer, gradientStroke.getCapType().toPaintCap(), gradientStroke.getJoinType().toPaintJoin(), gradientStroke.getMiterLimit(), gradientStroke.getOpacity(), gradientStroke.getWidth(), gradientStroke.getLineDashPattern(), gradientStroke.getDashOffset());
        this.s = new LongSparseArray<>();
        this.t = new LongSparseArray<>();
        this.u = new RectF();
        this.q = gradientStroke.getName();
        this.v = gradientStroke.getGradientType();
        this.r = gradientStroke.isHidden();
        this.w = (int) (lottieDrawable.getComposition().getDuration() / 32.0f);
        BaseKeyframeAnimation<GradientColor, GradientColor> createAnimation = gradientStroke.getGradientColor().createAnimation();
        this.x = createAnimation;
        createAnimation.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation);
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = gradientStroke.getStartPoint().createAnimation();
        this.y = createAnimation2;
        createAnimation2.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation2);
        BaseKeyframeAnimation<PointF, PointF> createAnimation3 = gradientStroke.getEndPoint().createAnimation();
        this.z = createAnimation3;
        createAnimation3.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t, lottieValueCallback);
        if (t == LottieProperty.GRADIENT_COLOR) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.A;
            if (valueCallbackKeyframeAnimation != null) {
                this.layer.removeAnimation(valueCallbackKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.A = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.A = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            this.layer.addAnimation(this.A);
        }
    }

    public final int[] c(int[] iArr) {
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.A;
        if (valueCallbackKeyframeAnimation != null) {
            Integer[] numArr = (Integer[]) valueCallbackKeyframeAnimation.getValue();
            int i = 0;
            if (iArr.length == numArr.length) {
                while (i < iArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i < numArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            }
        }
        return iArr;
    }

    public final int d() {
        int round = Math.round(this.y.getProgress() * this.w);
        int round2 = Math.round(this.z.getProgress() * this.w);
        int round3 = Math.round(this.x.getProgress() * this.w);
        int i = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i = i * 31 * round2;
        }
        return round3 != 0 ? i * 31 * round3 : i;
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int i) {
        Shader f;
        if (this.r) {
            return;
        }
        getBounds(this.u, matrix, false);
        if (this.v == GradientType.LINEAR) {
            f = e();
        } else {
            f = f();
        }
        f.setLocalMatrix(matrix);
        this.h.setShader(f);
        super.draw(canvas, matrix, i);
    }

    public final LinearGradient e() {
        long d = d();
        LinearGradient linearGradient = this.s.get(d);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF value = this.y.getValue();
        PointF value2 = this.z.getValue();
        GradientColor value3 = this.x.getValue();
        LinearGradient linearGradient2 = new LinearGradient(value.x, value.y, value2.x, value2.y, c(value3.getColors()), value3.getPositions(), Shader.TileMode.CLAMP);
        this.s.put(d, linearGradient2);
        return linearGradient2;
    }

    public final RadialGradient f() {
        float f;
        float f2;
        long d = d();
        RadialGradient radialGradient = this.t.get(d);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF value = this.y.getValue();
        PointF value2 = this.z.getValue();
        GradientColor value3 = this.x.getValue();
        int[] c = c(value3.getColors());
        float[] positions = value3.getPositions();
        RadialGradient radialGradient2 = new RadialGradient(value.x, value.y, (float) Math.hypot(value2.x - f, value2.y - f2), c, positions, Shader.TileMode.CLAMP);
        this.t.put(d, radialGradient2);
        return radialGradient2;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.q;
    }
}
