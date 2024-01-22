package androidx.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.RequiresApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class PipHintTrackerKt {

    /* loaded from: classes.dex */
    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ Activity h;

        public a(Activity activity) {
            this.h = activity;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        @Nullable
        /* renamed from: a */
        public final Object emit(@NotNull Rect rect, @NotNull Continuation<? super Unit> continuation) {
            Api26Impl.INSTANCE.setPipParamsSourceRectHint(this.h, rect);
            return Unit.INSTANCE;
        }
    }

    public static final Rect a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect;
    }

    @RequiresApi(26)
    @ExperimentalCoroutinesApi
    @Nullable
    public static final Object trackPipAnimationHintView(@NotNull Activity activity, @NotNull View view, @NotNull Continuation<? super Unit> continuation) {
        Object collect = FlowKt.callbackFlow(new PipHintTrackerKt$trackPipAnimationHintView$flow$1(view, null)).collect(new a(activity), continuation);
        return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
