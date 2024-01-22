package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
/* loaded from: classes.dex */
public class ImageLayer extends BaseLayer {
    public final Paint D;
    public final Rect E;
    public final Rect F;
    @Nullable
    public final LottieImageAsset G;
    @Nullable
    public BaseKeyframeAnimation<ColorFilter, ColorFilter> H;
    @Nullable
    public BaseKeyframeAnimation<Bitmap, Bitmap> I;

    public ImageLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.D = new LPaint(3);
        this.E = new Rect();
        this.F = new Rect();
        this.G = lottieDrawable.getLottieImageAssetForId(layer.getRefId());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t, lottieValueCallback);
        if (t == LottieProperty.COLOR_FILTER) {
            if (lottieValueCallback == null) {
                this.H = null;
            } else {
                this.H = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            }
        } else if (t == LottieProperty.IMAGE) {
            if (lottieValueCallback == null) {
                this.I = null;
            } else {
                this.I = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void drawLayer(@NonNull Canvas canvas, Matrix matrix, int i) {
        Bitmap y = y();
        if (y == null || y.isRecycled() || this.G == null) {
            return;
        }
        float dpScale = Utils.dpScale();
        this.D.setAlpha(i);
        BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.H;
        if (baseKeyframeAnimation != null) {
            this.D.setColorFilter(baseKeyframeAnimation.getValue());
        }
        canvas.save();
        canvas.concat(matrix);
        this.E.set(0, 0, y.getWidth(), y.getHeight());
        if (this.p.getMaintainOriginalImageBounds()) {
            this.F.set(0, 0, (int) (this.G.getWidth() * dpScale), (int) (this.G.getHeight() * dpScale));
        } else {
            this.F.set(0, 0, (int) (y.getWidth() * dpScale), (int) (y.getHeight() * dpScale));
        }
        canvas.drawBitmap(y, this.E, this.F, this.D);
        canvas.restore();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        if (this.G != null) {
            float dpScale = Utils.dpScale();
            rectF.set(0.0f, 0.0f, this.G.getWidth() * dpScale, this.G.getHeight() * dpScale);
            this.o.mapRect(rectF);
        }
    }

    @Nullable
    public final Bitmap y() {
        Bitmap value;
        BaseKeyframeAnimation<Bitmap, Bitmap> baseKeyframeAnimation = this.I;
        if (baseKeyframeAnimation == null || (value = baseKeyframeAnimation.getValue()) == null) {
            Bitmap bitmapForId = this.p.getBitmapForId(this.q.getRefId());
            if (bitmapForId != null) {
                return bitmapForId;
            }
            LottieImageAsset lottieImageAsset = this.G;
            if (lottieImageAsset != null) {
                return lottieImageAsset.getBitmap();
            }
            return null;
        }
        return value;
    }
}
