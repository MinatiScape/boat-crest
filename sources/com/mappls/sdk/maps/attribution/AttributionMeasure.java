package com.mappls.sdk.maps.attribution;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class AttributionMeasure {

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f12675a;
    public Bitmap b;
    public Bitmap c;
    public TextView d;
    public TextView e;
    public float f;
    public boolean g;

    /* loaded from: classes11.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f12676a;
        public Bitmap b;
        public Bitmap c;
        public TextView d;
        public TextView e;
        public float f;

        @NonNull
        public AttributionMeasure build() {
            return new AttributionMeasure(this.f12676a, this.b, this.c, this.d, this.e, this.f);
        }

        @NonNull
        public Builder setLogo(Bitmap bitmap) {
            this.b = bitmap;
            return this;
        }

        @NonNull
        public Builder setLogoSmall(Bitmap bitmap) {
            this.c = bitmap;
            return this;
        }

        @NonNull
        public Builder setMarginPadding(float f) {
            this.f = f;
            return this;
        }

        @NonNull
        public Builder setSnapshot(Bitmap bitmap) {
            this.f12676a = bitmap;
            return this;
        }

        @NonNull
        public Builder setTextView(TextView textView) {
            this.d = textView;
            return this;
        }

        @NonNull
        public Builder setTextViewShort(TextView textView) {
            this.e = textView;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public interface Command {
        @Nullable
        AttributionLayout execute(AttributionMeasure attributionMeasure);
    }

    /* loaded from: classes11.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public List<Command> f12677a;

        public b(AttributionMeasure attributionMeasure, Command... commandArr) {
            this.f12677a = Arrays.asList(commandArr);
        }

        @Nullable
        public AttributionLayout a(AttributionMeasure attributionMeasure) {
            Iterator<Command> it = this.f12677a.iterator();
            AttributionLayout attributionLayout = null;
            while (it.hasNext() && (attributionLayout = it.next().execute(attributionMeasure)) == null) {
            }
            return attributionLayout;
        }
    }

    /* loaded from: classes11.dex */
    public static class c implements Command {
        public c() {
        }

        @Override // com.mappls.sdk.maps.attribution.AttributionMeasure.Command
        @Nullable
        public AttributionLayout execute(@NonNull AttributionMeasure attributionMeasure) {
            if (attributionMeasure.o() + attributionMeasure.s() <= attributionMeasure.q()) {
                return new AttributionLayout(attributionMeasure.f12675a, AttributionMeasure.n(attributionMeasure.c, attributionMeasure.d, attributionMeasure.f), false);
            }
            return null;
        }
    }

    /* loaded from: classes11.dex */
    public static class d implements Command {
        public d() {
        }

        @Override // com.mappls.sdk.maps.attribution.AttributionMeasure.Command
        @Nullable
        public AttributionLayout execute(@NonNull AttributionMeasure attributionMeasure) {
            if (attributionMeasure.o() + attributionMeasure.t() <= attributionMeasure.r()) {
                return new AttributionLayout(attributionMeasure.f12675a, AttributionMeasure.n(attributionMeasure.c, attributionMeasure.e, attributionMeasure.f), true);
            }
            return null;
        }
    }

    /* loaded from: classes11.dex */
    public static class e implements Command {
        public e() {
        }

        @Override // com.mappls.sdk.maps.attribution.AttributionMeasure.Command
        @Nullable
        public AttributionLayout execute(@NonNull AttributionMeasure attributionMeasure) {
            if (attributionMeasure.s() + attributionMeasure.f <= attributionMeasure.q()) {
                return new AttributionLayout(null, AttributionMeasure.n(attributionMeasure.c, attributionMeasure.d, attributionMeasure.f), false);
            }
            return null;
        }
    }

    /* loaded from: classes11.dex */
    public static class f implements Command {
        public f() {
        }

        @Override // com.mappls.sdk.maps.attribution.AttributionMeasure.Command
        @NonNull
        public AttributionLayout execute(AttributionMeasure attributionMeasure) {
            return new AttributionLayout(null, null, false);
        }
    }

    /* loaded from: classes11.dex */
    public static class g implements Command {
        public g() {
        }

        @Override // com.mappls.sdk.maps.attribution.AttributionMeasure.Command
        @Nullable
        public AttributionLayout execute(@NonNull AttributionMeasure attributionMeasure) {
            if (attributionMeasure.t() + attributionMeasure.f <= attributionMeasure.r()) {
                return new AttributionLayout(null, AttributionMeasure.n(attributionMeasure.c, attributionMeasure.e, attributionMeasure.f), true);
            }
            return null;
        }
    }

    /* loaded from: classes11.dex */
    public static class h implements Command {
        public h() {
        }

        @Override // com.mappls.sdk.maps.attribution.AttributionMeasure.Command
        @Nullable
        public AttributionLayout execute(@NonNull AttributionMeasure attributionMeasure) {
            if (attributionMeasure.p() + attributionMeasure.s() <= attributionMeasure.q()) {
                return new AttributionLayout(attributionMeasure.b, AttributionMeasure.n(attributionMeasure.c, attributionMeasure.d, attributionMeasure.f), false);
            }
            return null;
        }
    }

    /* loaded from: classes11.dex */
    public static class i implements Command {
        public i() {
        }

        @Override // com.mappls.sdk.maps.attribution.AttributionMeasure.Command
        @Nullable
        public AttributionLayout execute(@NonNull AttributionMeasure attributionMeasure) {
            if (attributionMeasure.o() + attributionMeasure.t() <= attributionMeasure.r()) {
                return new AttributionLayout(attributionMeasure.b, AttributionMeasure.n(attributionMeasure.c, attributionMeasure.e, attributionMeasure.f), true);
            }
            return null;
        }
    }

    public AttributionMeasure(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, TextView textView, TextView textView2, float f2) {
        this.c = bitmap;
        this.f12675a = bitmap2;
        this.b = bitmap3;
        this.d = textView;
        this.e = textView2;
        this.f = f2;
    }

    public static PointF n(Bitmap bitmap, TextView textView, float f2) {
        return new PointF((bitmap.getWidth() - textView.getMeasuredWidth()) - f2, (bitmap.getHeight() - f2) - textView.getMeasuredHeight());
    }

    public TextView getTextView() {
        return this.g ? this.e : this.d;
    }

    @Nullable
    public AttributionLayout measure() {
        AttributionLayout a2 = new b(this, new c(), new d(), new h(), new i(), new e(), new g(), new f()).a(this);
        this.g = a2.isShortText();
        return a2;
    }

    public final float o() {
        return this.f12675a.getWidth() + (this.f * 2.0f);
    }

    public final float p() {
        return this.b.getWidth() + (this.f * 2.0f);
    }

    public final float q() {
        return (this.c.getWidth() * 8) / 10;
    }

    public final float r() {
        return this.c.getWidth();
    }

    public final float s() {
        return this.d.getMeasuredWidth() + this.f;
    }

    public final float t() {
        return this.e.getMeasuredWidth() + this.f;
    }
}
