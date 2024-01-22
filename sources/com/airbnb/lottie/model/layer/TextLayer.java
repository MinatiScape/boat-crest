package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class TextLayer extends BaseLayer {
    public final StringBuilder D;
    public final RectF E;
    public final Matrix F;
    public final Paint G;
    public final Paint H;
    public final Map<FontCharacter, List<ContentGroup>> I;
    public final LongSparseArray<String> J;
    public final List<d> K;
    public final TextKeyframeAnimation L;
    public final LottieDrawable M;
    public final LottieComposition N;
    @Nullable
    public BaseKeyframeAnimation<Integer, Integer> O;
    @Nullable
    public BaseKeyframeAnimation<Integer, Integer> P;
    @Nullable
    public BaseKeyframeAnimation<Integer, Integer> Q;
    @Nullable
    public BaseKeyframeAnimation<Integer, Integer> R;
    @Nullable
    public BaseKeyframeAnimation<Float, Float> S;
    @Nullable
    public BaseKeyframeAnimation<Float, Float> T;
    @Nullable
    public BaseKeyframeAnimation<Float, Float> U;
    @Nullable
    public BaseKeyframeAnimation<Float, Float> V;
    @Nullable
    public BaseKeyframeAnimation<Float, Float> W;
    @Nullable
    public BaseKeyframeAnimation<Typeface, Typeface> X;

    /* loaded from: classes.dex */
    public class a extends Paint {
        public a(TextLayer textLayer, int i) {
            super(i);
            setStyle(Paint.Style.FILL);
        }
    }

    /* loaded from: classes.dex */
    public class b extends Paint {
        public b(TextLayer textLayer, int i) {
            super(i);
            setStyle(Paint.Style.STROKE);
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2056a;

        static {
            int[] iArr = new int[DocumentData.Justification.values().length];
            f2056a = iArr;
            try {
                iArr[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2056a[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2056a[DocumentData.Justification.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public TextLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableColorValue animatableColorValue;
        AnimatableColorValue animatableColorValue2;
        this.D = new StringBuilder(2);
        this.E = new RectF();
        this.F = new Matrix();
        this.G = new a(this, 1);
        this.H = new b(this, 1);
        this.I = new HashMap();
        this.J = new LongSparseArray<>();
        this.K = new ArrayList();
        this.M = lottieDrawable;
        this.N = layer.a();
        TextKeyframeAnimation createAnimation = layer.m().createAnimation();
        this.L = createAnimation;
        createAnimation.addUpdateListener(this);
        addAnimation(createAnimation);
        AnimatableTextProperties n = layer.n();
        if (n != null && (animatableColorValue2 = n.color) != null) {
            BaseKeyframeAnimation<Integer, Integer> createAnimation2 = animatableColorValue2.createAnimation();
            this.O = createAnimation2;
            createAnimation2.addUpdateListener(this);
            addAnimation(this.O);
        }
        if (n != null && (animatableColorValue = n.stroke) != null) {
            BaseKeyframeAnimation<Integer, Integer> createAnimation3 = animatableColorValue.createAnimation();
            this.Q = createAnimation3;
            createAnimation3.addUpdateListener(this);
            addAnimation(this.Q);
        }
        if (n != null && (animatableFloatValue2 = n.strokeWidth) != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation4 = animatableFloatValue2.createAnimation();
            this.S = createAnimation4;
            createAnimation4.addUpdateListener(this);
            addAnimation(this.S);
        }
        if (n == null || (animatableFloatValue = n.tracking) == null) {
            return;
        }
        BaseKeyframeAnimation<Float, Float> createAnimation5 = animatableFloatValue.createAnimation();
        this.U = createAnimation5;
        createAnimation5.addUpdateListener(this);
        addAnimation(this.U);
    }

    public final void A(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    public final void B(FontCharacter fontCharacter, float f, DocumentData documentData, Canvas canvas) {
        List<ContentGroup> J = J(fontCharacter);
        for (int i = 0; i < J.size(); i++) {
            Path path = J.get(i).getPath();
            path.computeBounds(this.E, false);
            this.F.reset();
            this.F.preTranslate(0.0f, (-documentData.baselineShift) * Utils.dpScale());
            this.F.preScale(f, f);
            path.transform(this.F);
            if (documentData.strokeOverFill) {
                E(path, this.G, canvas);
                E(path, this.H, canvas);
            } else {
                E(path, this.H, canvas);
                E(path, this.G, canvas);
            }
        }
    }

    public final void C(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.strokeOverFill) {
            A(str, this.G, canvas);
            A(str, this.H, canvas);
            return;
        }
        A(str, this.H, canvas);
        A(str, this.G, canvas);
    }

    public final void D(String str, DocumentData documentData, Canvas canvas, float f) {
        int i = 0;
        while (i < str.length()) {
            String y = y(str, i);
            i += y.length();
            C(y, documentData, canvas);
            canvas.translate(this.G.measureText(y) + f, 0.0f);
        }
    }

    public final void E(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    public final void F(String str, DocumentData documentData, Font font, Canvas canvas, float f, float f2, float f3) {
        for (int i = 0; i < str.length(); i++) {
            FontCharacter fontCharacter = this.N.getCharacters().get(FontCharacter.hashFor(str.charAt(i), font.getFamily(), font.getStyle()));
            if (fontCharacter != null) {
                B(fontCharacter, f2, documentData, canvas);
                canvas.translate((((float) fontCharacter.getWidth()) * f2 * Utils.dpScale()) + f3, 0.0f);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void G(com.airbnb.lottie.model.DocumentData r19, com.airbnb.lottie.model.Font r20, android.graphics.Canvas r21) {
        /*
            r18 = this;
            r7 = r18
            r8 = r19
            r9 = r20
            r10 = r21
            android.graphics.Typeface r0 = r7.L(r9)
            if (r0 != 0) goto Lf
            return
        Lf:
            java.lang.String r1 = r8.text
            com.airbnb.lottie.LottieDrawable r2 = r7.M
            com.airbnb.lottie.TextDelegate r2 = r2.getTextDelegate()
            if (r2 == 0) goto L21
            java.lang.String r3 = r18.getName()
            java.lang.String r1 = r2.getTextInternal(r3, r1)
        L21:
            android.graphics.Paint r2 = r7.G
            r2.setTypeface(r0)
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r0 = r7.W
            if (r0 == 0) goto L35
            java.lang.Object r0 = r0.getValue()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L37
        L35:
            float r0 = r8.size
        L37:
            android.graphics.Paint r2 = r7.G
            float r3 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r0
            r2.setTextSize(r3)
            android.graphics.Paint r2 = r7.H
            android.graphics.Paint r3 = r7.G
            android.graphics.Typeface r3 = r3.getTypeface()
            r2.setTypeface(r3)
            android.graphics.Paint r2 = r7.H
            android.graphics.Paint r3 = r7.G
            float r3 = r3.getTextSize()
            r2.setTextSize(r3)
            int r2 = r8.tracking
            float r2 = (float) r2
            r3 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 / r3
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r3 = r7.V
            if (r3 == 0) goto L6d
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
        L6b:
            float r2 = r2 + r3
            goto L7c
        L6d:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r3 = r7.U
            if (r3 == 0) goto L7c
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            goto L6b
        L7c:
            float r3 = com.airbnb.lottie.utils.Utils.dpScale()
            float r2 = r2 * r3
            float r2 = r2 * r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r11 = r2 / r0
            java.util.List r12 = r7.K(r1)
            int r13 = r12.size()
            r0 = -1
            r14 = 0
            r15 = r0
            r6 = r14
        L92:
            if (r6 >= r13) goto Ldd
            java.lang.Object r0 = r12.get(r6)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r8.boxSize
            if (r0 != 0) goto La1
            r0 = 0
            goto La3
        La1:
            float r0 = r0.x
        La3:
            r2 = r0
            r4 = 0
            r16 = 0
            r0 = r18
            r3 = r20
            r5 = r11
            r17 = r6
            r6 = r16
            java.util.List r0 = r0.O(r1, r2, r3, r4, r5, r6)
            r1 = r14
        Lb5:
            int r2 = r0.size()
            if (r1 >= r2) goto Lda
            java.lang.Object r2 = r0.get(r1)
            com.airbnb.lottie.model.layer.TextLayer$d r2 = (com.airbnb.lottie.model.layer.TextLayer.d) r2
            int r15 = r15 + 1
            r21.save()
            float r3 = com.airbnb.lottie.model.layer.TextLayer.d.a(r2)
            r7.N(r10, r8, r15, r3)
            java.lang.String r2 = com.airbnb.lottie.model.layer.TextLayer.d.b(r2)
            r7.D(r2, r8, r10, r11)
            r21.restore()
            int r1 = r1 + 1
            goto Lb5
        Lda:
            int r6 = r17 + 1
            goto L92
        Ldd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.G(com.airbnb.lottie.model.DocumentData, com.airbnb.lottie.model.Font, android.graphics.Canvas):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void H(com.airbnb.lottie.model.DocumentData r21, android.graphics.Matrix r22, com.airbnb.lottie.model.Font r23, android.graphics.Canvas r24) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r0 = r8.W
            if (r0 == 0) goto L13
            java.lang.Object r0 = r0.getValue()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L15
        L13:
            float r0 = r9.size
        L15:
            r1 = 1120403456(0x42c80000, float:100.0)
            float r10 = r0 / r1
            float r11 = com.airbnb.lottie.utils.Utils.getScale(r22)
            java.lang.String r0 = r9.text
            java.util.List r12 = r8.K(r0)
            int r13 = r12.size()
            int r0 = r9.tracking
            float r0 = (float) r0
            r1 = 1092616192(0x41200000, float:10.0)
            float r0 = r0 / r1
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r1 = r8.V
            if (r1 == 0) goto L3d
            java.lang.Object r1 = r1.getValue()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
        L3b:
            float r0 = r0 + r1
            goto L4c
        L3d:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r1 = r8.U
            if (r1 == 0) goto L4c
            java.lang.Object r1 = r1.getValue()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            goto L3b
        L4c:
            r14 = r0
            r0 = -1
            r15 = 0
            r7 = r0
            r6 = r15
        L51:
            if (r6 >= r13) goto Lb1
            java.lang.Object r0 = r12.get(r6)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r9.boxSize
            if (r0 != 0) goto L60
            r0 = 0
            goto L62
        L60:
            float r0 = r0.x
        L62:
            r2 = r0
            r16 = 1
            r0 = r20
            r3 = r23
            r4 = r10
            r5 = r14
            r17 = r6
            r6 = r16
            java.util.List r6 = r0.O(r1, r2, r3, r4, r5, r6)
            r5 = r15
        L74:
            int r0 = r6.size()
            if (r5 >= r0) goto Lae
            java.lang.Object r0 = r6.get(r5)
            com.airbnb.lottie.model.layer.TextLayer$d r0 = (com.airbnb.lottie.model.layer.TextLayer.d) r0
            int r7 = r7 + 1
            r24.save()
            float r1 = com.airbnb.lottie.model.layer.TextLayer.d.a(r0)
            r4 = r24
            r8.N(r4, r9, r7, r1)
            java.lang.String r1 = com.airbnb.lottie.model.layer.TextLayer.d.b(r0)
            r0 = r20
            r2 = r21
            r3 = r23
            r16 = r5
            r5 = r11
            r18 = r6
            r6 = r10
            r19 = r7
            r7 = r14
            r0.F(r1, r2, r3, r4, r5, r6, r7)
            r24.restore()
            int r5 = r16 + 1
            r6 = r18
            r7 = r19
            goto L74
        Lae:
            int r6 = r17 + 1
            goto L51
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.H(com.airbnb.lottie.model.DocumentData, android.graphics.Matrix, com.airbnb.lottie.model.Font, android.graphics.Canvas):void");
    }

    public final d I(int i) {
        for (int size = this.K.size(); size < i; size++) {
            this.K.add(new d(null));
        }
        return this.K.get(i - 1);
    }

    public final List<ContentGroup> J(FontCharacter fontCharacter) {
        if (this.I.containsKey(fontCharacter)) {
            return this.I.get(fontCharacter);
        }
        List<ShapeGroup> shapes = fontCharacter.getShapes();
        int size = shapes.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new ContentGroup(this.M, this, shapes.get(i), this.N));
        }
        this.I.put(fontCharacter, arrayList);
        return arrayList;
    }

    public final List<String> K(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll("\u0003", "\r").replaceAll("\n", "\r").split("\r"));
    }

    @Nullable
    public final Typeface L(Font font) {
        Typeface value;
        BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation = this.X;
        if (baseKeyframeAnimation == null || (value = baseKeyframeAnimation.getValue()) == null) {
            Typeface typeface = this.M.getTypeface(font);
            return typeface != null ? typeface : font.getTypeface();
        }
        return value;
    }

    public final boolean M(int i) {
        return Character.getType(i) == 16 || Character.getType(i) == 27 || Character.getType(i) == 6 || Character.getType(i) == 28 || Character.getType(i) == 8 || Character.getType(i) == 19;
    }

    public final void N(Canvas canvas, DocumentData documentData, int i, float f) {
        PointF pointF = documentData.boxPosition;
        PointF pointF2 = documentData.boxSize;
        float dpScale = Utils.dpScale();
        float f2 = (i * documentData.lineHeight * dpScale) + (pointF == null ? 0.0f : (documentData.lineHeight * dpScale) + pointF.y);
        float f3 = pointF == null ? 0.0f : pointF.x;
        float f4 = pointF2 != null ? pointF2.x : 0.0f;
        int i2 = c.f2056a[documentData.justification.ordinal()];
        if (i2 == 1) {
            canvas.translate(f3, f2);
        } else if (i2 == 2) {
            canvas.translate((f3 + f4) - f, f2);
        } else if (i2 != 3) {
        } else {
            canvas.translate((f3 + (f4 / 2.0f)) - (f / 2.0f), f2);
        }
    }

    public final List<d> O(String str, float f, Font font, float f2, float f3, boolean z) {
        float measureText;
        String substring;
        String trim;
        String substring2;
        String trim2;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        int i = 0;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        for (int i4 = 0; i4 < str.length(); i4++) {
            char charAt = str.charAt(i4);
            if (z) {
                FontCharacter fontCharacter = this.N.getCharacters().get(FontCharacter.hashFor(charAt, font.getFamily(), font.getStyle()));
                if (fontCharacter != null) {
                    measureText = ((float) fontCharacter.getWidth()) * f2 * Utils.dpScale();
                }
            } else {
                measureText = this.G.measureText(str.substring(i4, i4 + 1));
            }
            float f7 = measureText + f3;
            if (charAt == ' ') {
                z2 = true;
                f6 = f7;
            } else if (z2) {
                z2 = false;
                i3 = i4;
                f5 = f7;
            } else {
                f5 += f7;
            }
            f4 += f7;
            if (f > 0.0f && f4 >= f && charAt != ' ') {
                i++;
                d I = I(i);
                if (i3 == i2) {
                    I.c(str.substring(i2, i4).trim(), (f4 - f7) - ((trim2.length() - substring2.length()) * f6));
                    i2 = i4;
                    i3 = i2;
                    f4 = f7;
                    f5 = f4;
                } else {
                    I.c(str.substring(i2, i3 - 1).trim(), ((f4 - f5) - ((substring.length() - trim.length()) * f6)) - f6);
                    f4 = f5;
                    i2 = i3;
                }
            }
        }
        if (f4 > 0.0f) {
            i++;
            I(i).c(str.substring(i2), f4);
        }
        return this.K.subList(0, i);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t, lottieValueCallback);
        if (t == LottieProperty.COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.P;
            if (baseKeyframeAnimation != null) {
                removeAnimation(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.P = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.P = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            addAnimation(this.P);
        } else if (t == LottieProperty.STROKE_COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.R;
            if (baseKeyframeAnimation2 != null) {
                removeAnimation(baseKeyframeAnimation2);
            }
            if (lottieValueCallback == null) {
                this.R = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.R = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            addAnimation(this.R);
        } else if (t == LottieProperty.STROKE_WIDTH) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.T;
            if (baseKeyframeAnimation3 != null) {
                removeAnimation(baseKeyframeAnimation3);
            }
            if (lottieValueCallback == null) {
                this.T = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.T = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.addUpdateListener(this);
            addAnimation(this.T);
        } else if (t == LottieProperty.TEXT_TRACKING) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation4 = this.V;
            if (baseKeyframeAnimation4 != null) {
                removeAnimation(baseKeyframeAnimation4);
            }
            if (lottieValueCallback == null) {
                this.V = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.V = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.addUpdateListener(this);
            addAnimation(this.V);
        } else if (t == LottieProperty.TEXT_SIZE) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.W;
            if (baseKeyframeAnimation5 != null) {
                removeAnimation(baseKeyframeAnimation5);
            }
            if (lottieValueCallback == null) {
                this.W = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation5 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.W = valueCallbackKeyframeAnimation5;
            valueCallbackKeyframeAnimation5.addUpdateListener(this);
            addAnimation(this.W);
        } else if (t == LottieProperty.TYPEFACE) {
            BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation6 = this.X;
            if (baseKeyframeAnimation6 != null) {
                removeAnimation(baseKeyframeAnimation6);
            }
            if (lottieValueCallback == null) {
                this.X = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation6 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.X = valueCallbackKeyframeAnimation6;
            valueCallbackKeyframeAnimation6.addUpdateListener(this);
            addAnimation(this.X);
        } else if (t == LottieProperty.TEXT) {
            this.L.setStringValueCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void drawLayer(Canvas canvas, Matrix matrix, int i) {
        DocumentData value = this.L.getValue();
        Font font = this.N.getFonts().get(value.fontName);
        if (font == null) {
            return;
        }
        canvas.save();
        canvas.concat(matrix);
        z(value, matrix);
        if (this.M.useTextGlyphs()) {
            H(value, matrix, font, canvas);
        } else {
            G(value, font, canvas);
        }
        canvas.restore();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        rectF.set(0.0f, 0.0f, this.N.getBounds().width(), this.N.getBounds().height());
    }

    public final String y(String str, int i) {
        int codePointAt = str.codePointAt(i);
        int charCount = Character.charCount(codePointAt) + i;
        while (charCount < str.length()) {
            int codePointAt2 = str.codePointAt(charCount);
            if (!M(codePointAt2)) {
                break;
            }
            charCount += Character.charCount(codePointAt2);
            codePointAt = (codePointAt * 31) + codePointAt2;
        }
        long j = codePointAt;
        if (this.J.containsKey(j)) {
            return this.J.get(j);
        }
        this.D.setLength(0);
        while (i < charCount) {
            int codePointAt3 = str.codePointAt(i);
            this.D.appendCodePoint(codePointAt3);
            i += Character.charCount(codePointAt3);
        }
        String sb = this.D.toString();
        this.J.put(j, sb);
        return sb;
    }

    public final void z(DocumentData documentData, Matrix matrix) {
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.P;
        if (baseKeyframeAnimation != null) {
            this.G.setColor(baseKeyframeAnimation.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.O;
            if (baseKeyframeAnimation2 != null) {
                this.G.setColor(baseKeyframeAnimation2.getValue().intValue());
            } else {
                this.G.setColor(documentData.color);
            }
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation3 = this.R;
        if (baseKeyframeAnimation3 != null) {
            this.H.setColor(baseKeyframeAnimation3.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation4 = this.Q;
            if (baseKeyframeAnimation4 != null) {
                this.H.setColor(baseKeyframeAnimation4.getValue().intValue());
            } else {
                this.H.setColor(documentData.strokeColor);
            }
        }
        int intValue = ((this.x.getOpacity() == null ? 100 : this.x.getOpacity().getValue().intValue()) * 255) / 100;
        this.G.setAlpha(intValue);
        this.H.setAlpha(intValue);
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.T;
        if (baseKeyframeAnimation5 != null) {
            this.H.setStrokeWidth(baseKeyframeAnimation5.getValue().floatValue());
            return;
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation6 = this.S;
        if (baseKeyframeAnimation6 != null) {
            this.H.setStrokeWidth(baseKeyframeAnimation6.getValue().floatValue());
        } else {
            this.H.setStrokeWidth(documentData.strokeWidth * Utils.dpScale());
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public String f2057a;
        public float b;

        public d() {
            this.f2057a = "";
            this.b = 0.0f;
        }

        public void c(String str, float f) {
            this.f2057a = str;
            this.b = f;
        }

        public /* synthetic */ d(a aVar) {
            this();
        }
    }
}
