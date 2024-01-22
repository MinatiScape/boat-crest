package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class AtomicDesc {
    public AtomicOp<?> atomicOp;

    public abstract void complete(@NotNull AtomicOp<?> atomicOp, @Nullable Object obj);

    @NotNull
    public final AtomicOp<?> getAtomicOp() {
        AtomicOp<?> atomicOp = this.atomicOp;
        if (atomicOp != null) {
            return atomicOp;
        }
        Intrinsics.throwUninitializedPropertyAccessException("atomicOp");
        return null;
    }

    @Nullable
    public abstract Object prepare(@NotNull AtomicOp<?> atomicOp);

    public final void setAtomicOp(@NotNull AtomicOp<?> atomicOp) {
        this.atomicOp = atomicOp;
    }
}
