package com.airbnb.lottie.model.layer;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class Layer {

    /* renamed from: a  reason: collision with root package name */
    public final List<ContentModel> f2055a;
    public final LottieComposition b;
    public final String c;
    public final long d;
    public final LayerType e;
    public final long f;
    @Nullable
    public final String g;
    public final List<Mask> h;
    public final AnimatableTransform i;
    public final int j;
    public final int k;
    public final int l;
    public final float m;
    public final float n;
    public final float o;
    public final float p;
    @Nullable
    public final AnimatableTextFrame q;
    @Nullable
    public final AnimatableTextProperties r;
    @Nullable
    public final AnimatableFloatValue s;
    public final List<Keyframe<Float>> t;
    public final MatteType u;
    public final boolean v;
    @Nullable
    public final BlurEffect w;
    @Nullable
    public final DropShadowEffect x;

    /* loaded from: classes.dex */
    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    /* loaded from: classes.dex */
    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        LUMA,
        LUMA_INVERTED,
        UNKNOWN
    }

    public Layer(List<ContentModel> list, LottieComposition lottieComposition, String str, long j, LayerType layerType, long j2, @Nullable String str2, List<Mask> list2, AnimatableTransform animatableTransform, int i, int i2, int i3, float f, float f2, float f3, float f4, @Nullable AnimatableTextFrame animatableTextFrame, @Nullable AnimatableTextProperties animatableTextProperties, List<Keyframe<Float>> list3, MatteType matteType, @Nullable AnimatableFloatValue animatableFloatValue, boolean z, @Nullable BlurEffect blurEffect, @Nullable DropShadowEffect dropShadowEffect) {
        this.f2055a = list;
        this.b = lottieComposition;
        this.c = str;
        this.d = j;
        this.e = layerType;
        this.f = j2;
        this.g = str2;
        this.h = list2;
        this.i = animatableTransform;
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.m = f;
        this.n = f2;
        this.o = f3;
        this.p = f4;
        this.q = animatableTextFrame;
        this.r = animatableTextProperties;
        this.t = list3;
        this.u = matteType;
        this.s = animatableFloatValue;
        this.v = z;
        this.w = blurEffect;
        this.x = dropShadowEffect;
    }

    public LottieComposition a() {
        return this.b;
    }

    public List<Keyframe<Float>> b() {
        return this.t;
    }

    public List<Mask> c() {
        return this.h;
    }

    public MatteType d() {
        return this.u;
    }

    public long e() {
        return this.f;
    }

    public float f() {
        return this.p;
    }

    public float g() {
        return this.o;
    }

    @Nullable
    public BlurEffect getBlurEffect() {
        return this.w;
    }

    @Nullable
    public DropShadowEffect getDropShadowEffect() {
        return this.x;
    }

    public long getId() {
        return this.d;
    }

    public LayerType getLayerType() {
        return this.e;
    }

    public String getName() {
        return this.c;
    }

    @Nullable
    public String getRefId() {
        return this.g;
    }

    public List<ContentModel> h() {
        return this.f2055a;
    }

    public int i() {
        return this.l;
    }

    public boolean isHidden() {
        return this.v;
    }

    public int j() {
        return this.k;
    }

    public int k() {
        return this.j;
    }

    public float l() {
        return this.n / this.b.getDurationFrames();
    }

    @Nullable
    public AnimatableTextFrame m() {
        return this.q;
    }

    @Nullable
    public AnimatableTextProperties n() {
        return this.r;
    }

    @Nullable
    public AnimatableFloatValue o() {
        return this.s;
    }

    public float p() {
        return this.m;
    }

    public AnimatableTransform q() {
        return this.i;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(getName());
        sb.append("\n");
        Layer layerModelForId = this.b.layerModelForId(e());
        if (layerModelForId != null) {
            sb.append("\t\tParents: ");
            sb.append(layerModelForId.getName());
            Layer layerModelForId2 = this.b.layerModelForId(layerModelForId.e());
            while (layerModelForId2 != null) {
                sb.append("->");
                sb.append(layerModelForId2.getName());
                layerModelForId2 = this.b.layerModelForId(layerModelForId2.e());
            }
            sb.append(str);
            sb.append("\n");
        }
        if (!c().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(c().size());
            sb.append("\n");
        }
        if (k() != 0 && j() != 0) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(k()), Integer.valueOf(j()), Integer.valueOf(i())));
        }
        if (!this.f2055a.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (ContentModel contentModel : this.f2055a) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(contentModel);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
