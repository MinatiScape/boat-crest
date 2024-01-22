package com.github.anastr.speedviewlib.components.note;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.mappls.sdk.maps.style.layers.Property;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B-\b\u0007\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013B\u0019\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0014\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0015B)\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0014¨\u0006\u0017"}, d2 = {"Lcom/github/anastr/speedviewlib/components/note/ImageNote;", "Lcom/github/anastr/speedviewlib/components/note/Note;", "", "viewWidth", "", "build", "Landroid/graphics/Canvas;", "canvas", "", "leftX", "topY", "drawContains", "Landroid/content/Context;", "context", "Landroid/graphics/Bitmap;", "image", Property.ICON_TEXT_FIT_WIDTH, Property.ICON_TEXT_FIT_HEIGHT, "<init>", "(Landroid/content/Context;Landroid/graphics/Bitmap;II)V", "resource", "(Landroid/content/Context;I)V", "(Landroid/content/Context;III)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class ImageNote extends Note<ImageNote> {
    @NotNull
    public final Bitmap q;
    public final int r;
    public final int s;
    @NotNull
    public final RectF t;
    @NotNull
    public final Paint u;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ImageNote(@NotNull Context context, @NotNull Bitmap image) {
        this(context, image, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(image, "image");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ImageNote(@NotNull Context context, @NotNull Bitmap image, int i) {
        this(context, image, i, 0, 8, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(image, "image");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ImageNote(@NotNull Context context, @NotNull Bitmap image, int i, int i2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(image, "image");
        this.q = image;
        this.r = i;
        this.s = i2;
        this.t = new RectF();
        this.u = new Paint(1);
        if (!(i > 0)) {
            throw new IllegalArgumentException("width must be bigger than 0".toString());
        }
        if (!(i2 > 0)) {
            throw new IllegalArgumentException("height must be bigger than 0".toString());
        }
    }

    @Override // com.github.anastr.speedviewlib.components.note.Note
    public void build(int i) {
        noticeContainsSizeChange(this.r, this.s);
    }

    @Override // com.github.anastr.speedviewlib.components.note.Note
    public void drawContains(@NotNull Canvas canvas, float f, float f2) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.t.set(f, f2, this.r + f, this.s + f2);
        canvas.drawBitmap(this.q, (Rect) null, this.t, this.u);
    }

    public /* synthetic */ ImageNote(Context context, Bitmap bitmap, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, bitmap, (i3 & 4) != 0 ? bitmap.getWidth() : i, (i3 & 8) != 0 ? bitmap.getHeight() : i2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ImageNote(@org.jetbrains.annotations.NotNull android.content.Context r9, int r10) {
        /*
            r8 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            android.content.res.Resources r0 = r9.getResources()
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeResource(r0, r10)
            java.lang.String r10 = "decodeResource(context.resources, resource)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r10)
            r4 = 0
            r5 = 0
            r6 = 12
            r7 = 0
            r1 = r8
            r2 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.anastr.speedviewlib.components.note.ImageNote.<init>(android.content.Context, int):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ImageNote(@org.jetbrains.annotations.NotNull android.content.Context r2, int r3, int r4, int r5) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            android.content.res.Resources r0 = r2.getResources()
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeResource(r0, r3)
            java.lang.String r0 = "decodeResource(context.resources, resource)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            r1.<init>(r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.anastr.speedviewlib.components.note.ImageNote.<init>(android.content.Context, int, int, int):void");
    }
}
