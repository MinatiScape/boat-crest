package com.szabh.smable3.watchface;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import kotlin.collections.b;
import kotlin.collections.c;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Element {
    private final int bottomOffset;
    private final int gravity;
    private final int h;
    private final int hasAlpha;
    private final int ignoreBlack;
    @NotNull
    private final byte[][] imageBuffers;
    private final int leftOffset;
    private final int type;
    private final int w;
    private final int x;
    private final int y;

    public Element() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 2047, null);
    }

    public Element(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, @NotNull byte[][] imageBuffers) {
        Intrinsics.checkNotNullParameter(imageBuffers, "imageBuffers");
        this.type = i;
        this.hasAlpha = i2;
        this.w = i3;
        this.h = i4;
        this.gravity = i5;
        this.ignoreBlack = i6;
        this.x = i7;
        this.y = i8;
        this.bottomOffset = i9;
        this.leftOffset = i10;
        this.imageBuffers = imageBuffers;
    }

    public final int component1() {
        return this.type;
    }

    public final int component10() {
        return this.leftOffset;
    }

    @NotNull
    public final byte[][] component11() {
        return this.imageBuffers;
    }

    public final int component2() {
        return this.hasAlpha;
    }

    public final int component3() {
        return this.w;
    }

    public final int component4() {
        return this.h;
    }

    public final int component5() {
        return this.gravity;
    }

    public final int component6() {
        return this.ignoreBlack;
    }

    public final int component7() {
        return this.x;
    }

    public final int component8() {
        return this.y;
    }

    public final int component9() {
        return this.bottomOffset;
    }

    @NotNull
    public final Element copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, @NotNull byte[][] imageBuffers) {
        Intrinsics.checkNotNullParameter(imageBuffers, "imageBuffers");
        return new Element(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, imageBuffers);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(Element.class, obj != null ? obj.getClass() : null)) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.szabh.smable3.watchface.Element");
            Element element = (Element) obj;
            return this.type == element.type && this.hasAlpha == element.hasAlpha && this.w == element.w && this.h == element.h && this.gravity == element.gravity && this.ignoreBlack == element.ignoreBlack && this.x == element.x && this.y == element.y && this.bottomOffset == element.bottomOffset && this.leftOffset == element.leftOffset && c.contentDeepEquals(this.imageBuffers, element.imageBuffers);
        }
        return false;
    }

    public final int getBottomOffset() {
        return this.bottomOffset;
    }

    public final int getGravity() {
        return this.gravity;
    }

    public final int getH() {
        return this.h;
    }

    public final int getHasAlpha() {
        return this.hasAlpha;
    }

    public final int getIgnoreBlack() {
        return this.ignoreBlack;
    }

    @NotNull
    public final byte[][] getImageBuffers() {
        return this.imageBuffers;
    }

    public final int getLeftOffset() {
        return this.leftOffset;
    }

    public final int getType() {
        return this.type;
    }

    public final int getW() {
        return this.w;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public int hashCode() {
        return (((((((((((((((((((this.type * 31) + this.hasAlpha) * 31) + this.w) * 31) + this.h) * 31) + this.gravity) * 31) + this.ignoreBlack) * 31) + this.x) * 31) + this.y) * 31) + this.bottomOffset) * 31) + this.leftOffset) * 31) + b.contentDeepHashCode(this.imageBuffers);
    }

    @NotNull
    public String toString() {
        return "Element(type=" + this.type + ", hasAlpha=" + this.hasAlpha + ", w=" + this.w + ", h=" + this.h + ", gravity=" + this.gravity + ", ignoreBlack=" + this.ignoreBlack + ", x=" + this.x + ", y=" + this.y + ", bottomOffset=" + this.bottomOffset + ", leftOffset=" + this.leftOffset + ", imageBuffers=" + Arrays.toString(this.imageBuffers) + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ Element(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, byte[][] bArr, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this((i11 & 1) != 0 ? 0 : i, (i11 & 2) != 0 ? 0 : i2, (i11 & 4) != 0 ? 0 : i3, (i11 & 8) != 0 ? 0 : i4, (i11 & 16) != 0 ? 0 : i5, (i11 & 32) != 0 ? 0 : i6, (i11 & 64) != 0 ? 0 : i7, (i11 & 128) != 0 ? 0 : i8, (i11 & 256) != 0 ? 0 : i9, (i11 & 512) != 0 ? 0 : i10, (i11 & 1024) != 0 ? new byte[0] : bArr);
    }
}
