package okhttp3.internal.concurrent;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* loaded from: classes12.dex */
public final class TaskQueue$execute$1 extends Task {
    public final /* synthetic */ Function0<Unit> e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskQueue$execute$1(String str, boolean z, Function0<Unit> function0) {
        super(str, z);
        this.e = function0;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        this.e.invoke();
        return -1L;
    }
}
