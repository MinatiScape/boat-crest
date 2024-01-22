package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public class ForwardingTimeout extends Timeout {
    @NotNull
    public Timeout d;

    public ForwardingTimeout(@NotNull Timeout delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.d = delegate;
    }

    @Override // okio.Timeout
    @NotNull
    public Timeout clearDeadline() {
        return this.d.clearDeadline();
    }

    @Override // okio.Timeout
    @NotNull
    public Timeout clearTimeout() {
        return this.d.clearTimeout();
    }

    @Override // okio.Timeout
    public long deadlineNanoTime() {
        return this.d.deadlineNanoTime();
    }

    @JvmName(name = "delegate")
    @NotNull
    public final Timeout delegate() {
        return this.d;
    }

    @Override // okio.Timeout
    public boolean hasDeadline() {
        return this.d.hasDeadline();
    }

    /* renamed from: setDelegate  reason: collision with other method in class */
    public final /* synthetic */ void m939setDelegate(Timeout timeout) {
        Intrinsics.checkNotNullParameter(timeout, "<set-?>");
        this.d = timeout;
    }

    @Override // okio.Timeout
    public void throwIfReached() throws IOException {
        this.d.throwIfReached();
    }

    @Override // okio.Timeout
    @NotNull
    public Timeout timeout(long j, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return this.d.timeout(j, unit);
    }

    @Override // okio.Timeout
    public long timeoutNanos() {
        return this.d.timeoutNanos();
    }

    @Override // okio.Timeout
    @NotNull
    public Timeout deadlineNanoTime(long j) {
        return this.d.deadlineNanoTime(j);
    }

    @NotNull
    public final ForwardingTimeout setDelegate(@NotNull Timeout delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.d = delegate;
        return this;
    }
}
