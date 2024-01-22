package okhttp3.internal.concurrent;

import kotlin.jvm.functions.Function0;
/* loaded from: classes12.dex */
public final class TaskQueue$schedule$2 extends Task {
    public final /* synthetic */ Function0<Long> e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskQueue$schedule$2(String str, Function0<Long> function0) {
        super(str, false, 2, null);
        this.e = function0;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        return this.e.invoke().longValue();
    }
}
