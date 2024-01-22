package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class InactiveNodeList implements Incomplete {
    @NotNull
    public final NodeList h;

    public InactiveNodeList(@NotNull NodeList nodeList) {
        this.h = nodeList;
    }

    @Override // kotlinx.coroutines.Incomplete
    @NotNull
    public NodeList getList() {
        return this.h;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return false;
    }

    @NotNull
    public String toString() {
        return DebugKt.getDEBUG() ? getList().getString("New") : super.toString();
    }
}
