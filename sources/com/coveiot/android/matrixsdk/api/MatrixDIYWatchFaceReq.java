package com.coveiot.android.matrixsdk.api;

import android.graphics.Bitmap;
import com.htsmart.wristband2.dial.DialDrawer;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixDIYWatchFaceReq extends MatrixBaseReq {
    @NotNull
    public final File e;
    @NotNull
    public final Bitmap f;
    @NotNull
    public final Bitmap g;
    @NotNull
    public final DialDrawer.Position h;
    public final int i;

    public MatrixDIYWatchFaceReq(@NotNull File basicBinFile, @NotNull Bitmap backgroundBitmap, @NotNull Bitmap styleBitmap, @NotNull DialDrawer.Position position, int i) {
        Intrinsics.checkNotNullParameter(basicBinFile, "basicBinFile");
        Intrinsics.checkNotNullParameter(backgroundBitmap, "backgroundBitmap");
        Intrinsics.checkNotNullParameter(styleBitmap, "styleBitmap");
        Intrinsics.checkNotNullParameter(position, "position");
        this.e = basicBinFile;
        this.f = backgroundBitmap;
        this.g = styleBitmap;
        this.h = position;
        this.i = i;
    }

    @NotNull
    public final Bitmap getBackgroundBitmap() {
        return this.f;
    }

    @NotNull
    public final File getBasicBinFile() {
        return this.e;
    }

    @NotNull
    public final DialDrawer.Position getPosition() {
        return this.h;
    }

    @NotNull
    public final Bitmap getStyleBitmap() {
        return this.g;
    }

    public final int getStylePosition() {
        return this.i;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return false;
    }

    public /* synthetic */ MatrixDIYWatchFaceReq(File file, Bitmap bitmap, Bitmap bitmap2, DialDrawer.Position position, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, bitmap, bitmap2, position, (i2 & 16) != 0 ? 0 : i);
    }
}
