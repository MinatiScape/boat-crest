package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class QRCodeLoadingDialog extends Dialog {
    @NotNull
    public ImageView h;
    @NotNull
    public ImageView i;
    @NotNull
    public TextView j;

    @DebugMetadata(c = "com.coveiot.android.theme.QRCodeLoadingDialog$1", f = "QRCodeLoadingDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                QRCodeLoadingDialog.this.h.startAnimation(AnimationUtils.loadAnimation(this.$context, R.anim.rotation_anim));
                QRCodeLoadingDialog.this.i.startAnimation(AnimationUtils.loadAnimation(this.$context, R.anim.anti_clockwise_roation));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QRCodeLoadingDialog(@NotNull Context context) {
        super(context, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        requestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawable(new ColorDrawable(0));
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.qr_code_loading_dialog);
        View findViewById = findViewById(R.id.ivInner);
        Intrinsics.checkNotNullExpressionValue(findViewById, "this.findViewById(R.id.ivInner)");
        this.h = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.ivOuter);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "this.findViewById(R.id.ivOuter)");
        this.i = (ImageView) findViewById2;
        View findViewById3 = findViewById(R.id.tvMessage);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "this.findViewById(R.id.tvMessage)");
        this.j = (TextView) findViewById3;
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(context, null), 2, null);
    }

    public final void setText(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.j.setText(message);
    }
}
