package okio;

import java.io.Closeable;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final /* synthetic */ class d {
    @JvmName(name = "blackhole")
    @NotNull
    public static final Sink a() {
        return new a();
    }

    @NotNull
    public static final BufferedSink b(@NotNull Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return new RealBufferedSink(sink);
    }

    @NotNull
    public static final BufferedSource c(@NotNull Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return new RealBufferedSource(source);
    }

    public static final <T extends Closeable, R> R d(T t, @NotNull Function1<? super T, ? extends R> block) {
        R r;
        Intrinsics.checkNotNullParameter(block, "block");
        Throwable th = null;
        try {
            r = block.invoke(t);
        } catch (Throwable th2) {
            th = th2;
            r = null;
        }
        if (t != null) {
            try {
                t.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                } else {
                    kotlin.a.addSuppressed(th, th3);
                }
            }
        }
        if (th == null) {
            Intrinsics.checkNotNull(r);
            return r;
        }
        throw th;
    }
}
