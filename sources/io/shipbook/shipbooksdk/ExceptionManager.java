package io.shipbook.shipbooksdk;

import io.shipbook.shipbooksdk.Models.Exception;
import io.shipbook.shipbooksdk.Util.ListStackTraceElementExtKt;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/ExceptionManager;", "", "", "hasException", "", "start", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ExceptionManager {
    public static final ExceptionManager INSTANCE = new ExceptionManager();

    /* renamed from: a */
    public static final String f14019a = ExceptionManager.class.getSimpleName();

    /* loaded from: classes12.dex */
    public static final class a implements Thread.UncaughtExceptionHandler {

        /* renamed from: a */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f14020a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f14020a = uncaughtExceptionHandler;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable throwable) {
            InnerLog innerLog = InnerLog.INSTANCE;
            String TAG = ExceptionManager.access$getTAG$p(ExceptionManager.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            InnerLog.e$default(innerLog, TAG, "catch uncaught exception", null, 4, null);
            Intrinsics.checkExpressionValueIsNotNull(throwable, "throwable");
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "throwable.stackTrace");
            LogManager.INSTANCE.push(new Exception(throwable.getClass().getName(), throwable.getMessage(), ListStackTraceElementExtKt.toInternal(stackTrace), 0, null, null, 56, null));
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f14020a;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, throwable);
            } else {
                System.exit(1);
            }
        }
    }

    public static final /* synthetic */ String access$getTAG$p(ExceptionManager exceptionManager) {
        return f14019a;
    }

    public static /* synthetic */ void start$default(ExceptionManager exceptionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        exceptionManager.start(z);
    }

    public final void start(boolean z) {
        if (z) {
            Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        }
    }
}
