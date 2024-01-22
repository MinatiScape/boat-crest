package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Picture;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class PictureKt {
    @NotNull
    public static final Picture record(@NotNull Picture picture, int i, int i2, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(picture, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        Canvas beginRecording = picture.beginRecording(i, i2);
        Intrinsics.checkNotNullExpressionValue(beginRecording, "beginRecording(width, height)");
        try {
            block.invoke(beginRecording);
            return picture;
        } finally {
            InlineMarker.finallyStart(1);
            picture.endRecording();
            InlineMarker.finallyEnd(1);
        }
    }
}
