package com.github.anastr.speedviewlib.components.note;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.anastr.speedviewlib.components.note.Note;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 2*\u0010\b\u0000\u0010\u0001 \u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u0002:\u0003324B\u0011\b\u0004\u0012\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b0\u00101J\u000e\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J \u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H$J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH&J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0004J\u001e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003J\u0015\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0003J\u0015\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u0015\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001e\u001a\u00020\u001c¢\u0006\u0004\b\u001f\u0010 J\u0006\u0010\"\u001a\u00020!J\u0015\u0010$\u001a\u00028\u00002\u0006\u0010#\u001a\u00020!¢\u0006\u0004\b$\u0010%J-\u0010*\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u0003¢\u0006\u0004\b*\u0010+R\u0013\u0010\u0015\u001a\u00020\f8F@\u0006¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u00065"}, d2 = {"Lcom/github/anastr/speedviewlib/components/note/Note;", "N", "", "", RsaJsonWebKey.FIRST_FACTOR_CRT_EXPONENT_MEMBER_NAME, "dpTOpx", "Landroid/graphics/Canvas;", "canvas", "leftX", "topY", "", "drawContains", "", "viewWidth", "build", "containsW", "containsH", "noticeContainsSizeChange", "posX", "posY", "draw", "backgroundColor", "setBackgroundColor", "(I)Lcom/github/anastr/speedviewlib/components/note/Note;", "getCornersRound", "cornersRound", "setCornersRound", "(F)Lcom/github/anastr/speedviewlib/components/note/Note;", "Lcom/github/anastr/speedviewlib/components/note/Note$Align;", "getAlign", "align", "setAlign", "(Lcom/github/anastr/speedviewlib/components/note/Note$Align;)Lcom/github/anastr/speedviewlib/components/note/Note;", "Lcom/github/anastr/speedviewlib/components/note/Note$Position;", "getPosition", DeviceKey.position, "setPosition", "(Lcom/github/anastr/speedviewlib/components/note/Note$Position;)Lcom/github/anastr/speedviewlib/components/note/Note;", "left", "top", "right", "bottom", "setPadding", "(FFFF)Lcom/github/anastr/speedviewlib/components/note/Note;", "getBackgroundColor", "()I", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "Align", "Position", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public abstract class Note<N extends Note<? extends N>> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int INFINITE = -1;

    /* renamed from: a  reason: collision with root package name */
    public final float f7906a;
    @NotNull
    public final Paint b;
    @NotNull
    public final Paint c;
    public float d;
    public float e;
    public float f;
    public float g;
    @NotNull
    public Bitmap h;
    @NotNull
    public Position i;
    @NotNull
    public Align j;
    public int k;
    public int l;
    public int m;
    public int n;
    public float o;
    public float p;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/github/anastr/speedviewlib/components/note/Note$Align;", "", "<init>", "(Ljava/lang/String;I)V", "Left", "Top", "Right", "Bottom", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public enum Align {
        Left,
        Top,
        Right,
        Bottom
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/github/anastr/speedviewlib/components/note/Note$Companion;", "", "", "INFINITE", "I", "<init>", "()V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/github/anastr/speedviewlib/components/note/Note$Position;", "", "<init>", "(Ljava/lang/String;I)V", "TopIndicator", "CenterIndicator", "BottomIndicator", "TopSpeedometer", "CenterSpeedometer", "QuarterSpeedometer", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public enum Position {
        TopIndicator,
        CenterIndicator,
        BottomIndicator,
        TopSpeedometer,
        CenterSpeedometer,
        QuarterSpeedometer
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Align.values().length];
            iArr[Align.Left.ordinal()] = 1;
            iArr[Align.Top.ordinal()] = 2;
            iArr[Align.Right.ordinal()] = 3;
            iArr[Align.Bottom.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public Note(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7906a = context.getResources().getDisplayMetrics().density;
        this.b = new Paint(1);
        Paint paint = new Paint(1);
        this.c = paint;
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(1, 1, Bitmap.Config.ARGB_8888)");
        this.h = createBitmap;
        this.i = Position.CenterIndicator;
        this.j = Align.Top;
        this.o = 5.0f;
        this.p = dpTOpx(12.0f);
        paint.setColor(-2697257);
        setPadding(dpTOpx(7.0f), dpTOpx(7.0f), dpTOpx(7.0f), dpTOpx(7.0f));
    }

    public final void a(Canvas canvas) {
        RectF rectF = new RectF(0.0f, this.p + 0.0f, this.k, this.l);
        Path path = new Path();
        path.moveTo(this.k / 2.0f, 0.0f);
        float f = 1;
        path.lineTo((this.k / 2.0f) - dpTOpx(9.0f), rectF.top + f);
        path.lineTo((this.k / 2.0f) + dpTOpx(9.0f), rectF.top + f);
        canvas.drawPath(path, this.c);
        float f2 = this.o;
        canvas.drawRoundRect(rectF, f2, f2, this.c);
    }

    public final void b(Canvas canvas) {
        RectF rectF = new RectF(0.0f, 0.0f, this.k - this.p, this.l);
        Path path = new Path();
        path.moveTo(this.k, this.l / 2.0f);
        float f = 1;
        path.lineTo(rectF.right - f, (this.l / 2.0f) - dpTOpx(9.0f));
        path.lineTo(rectF.right - f, (this.l / 2.0f) + dpTOpx(9.0f));
        canvas.drawPath(path, this.c);
        float f2 = this.o;
        canvas.drawRoundRect(rectF, f2, f2, this.c);
    }

    public abstract void build(int i);

    public final void c(Canvas canvas) {
        RectF rectF = new RectF(this.p + 0.0f, 0.0f, this.k, this.l);
        Path path = new Path();
        path.moveTo(0.0f, this.l / 2.0f);
        float f = 1;
        path.lineTo(rectF.left + f, (this.l / 2.0f) - dpTOpx(9.0f));
        path.lineTo(rectF.left + f, (this.l / 2.0f) + dpTOpx(9.0f));
        canvas.drawPath(path, this.c);
        float f2 = this.o;
        canvas.drawRoundRect(rectF, f2, f2, this.c);
    }

    public final void d(Canvas canvas) {
        RectF rectF = new RectF(0.0f, 0.0f, this.k, this.l - this.p);
        Path path = new Path();
        path.moveTo(this.k / 2.0f, this.l);
        float f = 1;
        path.lineTo((this.k / 2.0f) - dpTOpx(9.0f), rectF.bottom - f);
        path.lineTo((this.k / 2.0f) + dpTOpx(9.0f), rectF.bottom - f);
        canvas.drawPath(path, this.c);
        float f2 = this.o;
        canvas.drawRoundRect(rectF, f2, f2, this.c);
    }

    public final float dpTOpx(float f) {
        return f * this.f7906a;
    }

    public final void draw(@NotNull Canvas canvas, float f, float f2) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int i = WhenMappings.$EnumSwitchMapping$0[this.j.ordinal()];
        if (i == 1) {
            canvas.drawBitmap(this.h, f - this.k, f2 - (this.l / 2.0f), this.b);
            drawContains(canvas, (f - this.k) + this.d, (f2 - (this.l / 2.0f)) + this.e);
        } else if (i == 2) {
            canvas.drawBitmap(this.h, f - (this.k / 2.0f), f2 - this.l, this.b);
            drawContains(canvas, f - (this.m / 2.0f), (f2 - this.l) + this.e);
        } else if (i == 3) {
            canvas.drawBitmap(this.h, f, f2 - (this.l / 2.0f), this.b);
            drawContains(canvas, f + this.p + this.d, (f2 - (this.l / 2.0f)) + this.e);
        } else if (i != 4) {
        } else {
            canvas.drawBitmap(this.h, f - (this.k / 2.0f), f2, this.b);
            drawContains(canvas, f - (this.m / 2.0f), f2 + this.p + this.e);
        }
    }

    public abstract void drawContains(@NotNull Canvas canvas, float f, float f2);

    public final void e() {
        int i;
        int i2 = this.k;
        if (i2 <= 0 || (i = this.l) <= 0) {
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(noteW, note… Bitmap.Config.ARGB_8888)");
        this.h = createBitmap;
        Canvas canvas = new Canvas(this.h);
        int i3 = WhenMappings.$EnumSwitchMapping$0[this.j.ordinal()];
        if (i3 == 1) {
            b(canvas);
        } else if (i3 == 2) {
            d(canvas);
        } else if (i3 == 3) {
            c(canvas);
        } else if (i3 != 4) {
        } else {
            a(canvas);
        }
    }

    @NotNull
    public final Align getAlign() {
        return this.j;
    }

    public final int getBackgroundColor() {
        return this.c.getColor();
    }

    public final float getCornersRound() {
        return this.o;
    }

    @NotNull
    public final Position getPosition() {
        return this.i;
    }

    public final void noticeContainsSizeChange(int i, int i2) {
        this.m = i;
        this.n = i2;
        Align align = this.j;
        if (align != Align.Top && align != Align.Bottom) {
            this.k = (int) (i + this.d + this.f + this.p);
            this.l = (int) (i2 + this.e + this.g);
        } else {
            this.k = (int) (i + this.d + this.f);
            this.l = (int) (i2 + this.e + this.g + this.p);
        }
        e();
    }

    @NotNull
    public final N setAlign(@NotNull Align align) {
        Intrinsics.checkNotNullParameter(align, "align");
        this.j = align;
        return this;
    }

    @NotNull
    public final N setBackgroundColor(int i) {
        this.c.setColor(i);
        return this;
    }

    @NotNull
    public final N setCornersRound(float f) {
        if (f >= 0.0f) {
            this.o = f;
            return this;
        }
        throw new IllegalArgumentException("cornersRound cannot be negative".toString());
    }

    @NotNull
    public final N setPadding(float f, float f2, float f3, float f4) {
        this.d = f;
        this.e = f2;
        this.f = f3;
        this.g = f4;
        noticeContainsSizeChange(this.m, this.n);
        return this;
    }

    @NotNull
    public final N setPosition(@NotNull Position position) {
        Intrinsics.checkNotNullParameter(position, "position");
        this.i = position;
        return this;
    }
}
