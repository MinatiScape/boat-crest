package androidx.paging;

import androidx.annotation.VisibleForTesting;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0081\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001J\u0013\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0006\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0007\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Landroidx/paging/GenerationalViewportHint;", "", "", "component1", "Landroidx/paging/ViewportHint;", "component2", "generationId", "hint", Constants.COPY_TYPE, "", "toString", "hashCode", FitnessActivities.OTHER, "", "equals", "a", "I", "getGenerationId", "()I", "b", "Landroidx/paging/ViewportHint;", "getHint", "()Landroidx/paging/ViewportHint;", "<init>", "(ILandroidx/paging/ViewportHint;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
@VisibleForTesting
/* loaded from: classes.dex */
public final class GenerationalViewportHint {

    /* renamed from: a  reason: collision with root package name */
    public final int f1490a;
    @NotNull
    public final ViewportHint b;

    public GenerationalViewportHint(int i, @NotNull ViewportHint hint) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        this.f1490a = i;
        this.b = hint;
    }

    public static /* synthetic */ GenerationalViewportHint copy$default(GenerationalViewportHint generationalViewportHint, int i, ViewportHint viewportHint, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = generationalViewportHint.f1490a;
        }
        if ((i2 & 2) != 0) {
            viewportHint = generationalViewportHint.b;
        }
        return generationalViewportHint.copy(i, viewportHint);
    }

    public final int component1() {
        return this.f1490a;
    }

    @NotNull
    public final ViewportHint component2() {
        return this.b;
    }

    @NotNull
    public final GenerationalViewportHint copy(int i, @NotNull ViewportHint hint) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        return new GenerationalViewportHint(i, hint);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GenerationalViewportHint) {
            GenerationalViewportHint generationalViewportHint = (GenerationalViewportHint) obj;
            return this.f1490a == generationalViewportHint.f1490a && Intrinsics.areEqual(this.b, generationalViewportHint.b);
        }
        return false;
    }

    public final int getGenerationId() {
        return this.f1490a;
    }

    @NotNull
    public final ViewportHint getHint() {
        return this.b;
    }

    public int hashCode() {
        return (Integer.hashCode(this.f1490a) * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "GenerationalViewportHint(generationId=" + this.f1490a + ", hint=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
