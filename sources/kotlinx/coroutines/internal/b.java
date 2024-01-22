package kotlinx.coroutines.internal;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class b {
    @JvmField
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final LockFreeLinkedListNode f14188a;

    public b(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.f14188a = lockFreeLinkedListNode;
    }

    @NotNull
    public String toString() {
        return "Removed[" + this.f14188a + ']';
    }
}
