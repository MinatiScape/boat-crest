package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.boat.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ViewUtilsKt {

    /* loaded from: classes5.dex */
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

    public static final void disable(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<this>");
        button.setAlpha(0.5f);
        button.setClickable(false);
        button.setEnabled(false);
    }

    public static final void drawableEnd(@NotNull TextView textView, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    public static final void drawableStart(@NotNull TextView textView, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
    }

    public static final void emptyDrawable(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public static final void enable(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<this>");
        button.setAlpha(1.0f);
        button.setClickable(true);
        button.setEnabled(true);
    }

    public static final void gone(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public static final void goneIF(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 8 : 0);
    }

    public static final void loadImagesWithGlideExt(@NotNull ImageView imageView, @Nullable String str) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Glide.with(imageView).m30load(str).centerCrop().error(R.drawable.steps_health).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.steps_health).into(imageView);
    }

    public static final void setBackgroundExt(@NotNull View view, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setBackgroundResource(i);
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

    public static final void visibleIf(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 0 : 8);
    }

    public static final void disable(@NotNull RadioButton radioButton) {
        Intrinsics.checkNotNullParameter(radioButton, "<this>");
        radioButton.setAlpha(0.5f);
        radioButton.setClickable(false);
        radioButton.setEnabled(false);
    }

    public static final void enable(@NotNull RadioButton radioButton) {
        Intrinsics.checkNotNullParameter(radioButton, "<this>");
        radioButton.setAlpha(1.0f);
        radioButton.setClickable(true);
        radioButton.setEnabled(true);
    }
}
