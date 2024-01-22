package com.mappls.sdk.navigation.ui.navigation.infobar;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.mappls.sdk.navigation.ui.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes11.dex */
public abstract class BaseInfobarBottomSheet extends CoordinatorLayout {
    private int layoutMargin;
    private int layoutMarginBottom;
    private int layoutMarginEnd;
    private int layoutMarginStart;
    private int layoutMarginTop;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseInfobarBottomSheet(@NotNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setClickable(true);
        setLayoutTransition(new LayoutTransition());
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetInfoBar, i, i2);
            setBackgroundResource(obtainStyledAttributes.getResourceId(R.styleable.BottomSheetInfoBar_android_background, 0));
            this.layoutMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetInfoBar_android_layout_margin, -1);
            this.layoutMarginTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetInfoBar_android_layout_marginTop, 0);
            this.layoutMarginBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetInfoBar_android_layout_marginBottom, 0);
            this.layoutMarginStart = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetInfoBar_android_layout_marginStart, 0);
            this.layoutMarginEnd = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetInfoBar_android_layout_marginEnd, 0);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.ViewGroup, android.view.View
    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int i = this.layoutMargin;
        if (i >= 0) {
            marginLayoutParams.setMargins(i, i, i, i);
        } else {
            marginLayoutParams.setMargins(this.layoutMarginStart, this.layoutMarginTop, this.layoutMarginEnd, this.layoutMarginBottom);
        }
    }
}
