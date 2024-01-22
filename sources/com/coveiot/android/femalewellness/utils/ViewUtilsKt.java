package com.coveiot.android.femalewellness.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class ViewUtilsKt {

    /* loaded from: classes4.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public final /* synthetic */ Function1<View, Unit> $onSafeClick;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(Function1<? super View, Unit> function1) {
            super(1);
            this.$onSafeClick = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            this.$onSafeClick.invoke(it);
        }
    }

    public static final void gone(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public static final void setSafeOnClickListener(@NotNull View view, @NotNull Function1<? super View, Unit> onSafeClick) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(onSafeClick, "onSafeClick");
        view.setOnClickListener(new SafeClickListener(0, new a(onSafeClick), 1, null));
    }

    public static final void toast(@NotNull Context context, @NotNull CharSequence message) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(context, message, 1).show();
    }

    public static final void visible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }
}
