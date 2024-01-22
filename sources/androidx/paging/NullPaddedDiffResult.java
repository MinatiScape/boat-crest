package androidx.paging;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\r\u001a\u00020\b8\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Landroidx/paging/NullPaddedDiffResult;", "", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "a", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "getDiff", "()Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "diff", "", "b", "Z", "getHasOverlap", "()Z", "hasOverlap", "<init>", "(Landroidx/recyclerview/widget/DiffUtil$DiffResult;Z)V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class NullPaddedDiffResult {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final DiffUtil.DiffResult f1509a;
    public final boolean b;

    public NullPaddedDiffResult(@NotNull DiffUtil.DiffResult diff, boolean z) {
        Intrinsics.checkNotNullParameter(diff, "diff");
        this.f1509a = diff;
        this.b = z;
    }

    @NotNull
    public final DiffUtil.DiffResult getDiff() {
        return this.f1509a;
    }

    public final boolean getHasOverlap() {
        return this.b;
    }
}
