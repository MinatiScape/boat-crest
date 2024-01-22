package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface PushObserver {
    @NotNull
    public static final Companion Companion = Companion.f14287a;
    @JvmField
    @NotNull
    public static final PushObserver CANCEL = new Companion.a();

    /* loaded from: classes12.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f14287a = new Companion();

        /* loaded from: classes12.dex */
        public static final class a implements PushObserver {
            @Override // okhttp3.internal.http2.PushObserver
            public boolean onData(int i, @NotNull BufferedSource source, int i2, boolean z) throws IOException {
                Intrinsics.checkNotNullParameter(source, "source");
                source.skip(i2);
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public boolean onHeaders(int i, @NotNull List<Header> responseHeaders, boolean z) {
                Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public boolean onRequest(int i, @NotNull List<Header> requestHeaders) {
                Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public void onReset(int i, @NotNull ErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            }
        }
    }

    boolean onData(int i, @NotNull BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, @NotNull List<Header> list, boolean z);

    boolean onRequest(int i, @NotNull List<Header> list);

    void onReset(int i, @NotNull ErrorCode errorCode);
}
