package com.mappls.sdk.navigation.ui.navigation.recenter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutRecenterBtnBinding;
import com.mappls.sdk.navigation.ui.theme.a;
@Keep
/* loaded from: classes11.dex */
public class RecenterButton extends LinearLayout {
    public LayoutRecenterBtnBinding binding;
    private Animation slideUpBottom;

    public RecenterButton(Context context) {
        this(context, null);
    }

    public RecenterButton(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public RecenterButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.binding = LayoutRecenterBtnBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    private void initAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 125.0f, 0.0f);
        this.slideUpBottom = translateAnimation;
        translateAnimation.setDuration(300L);
        this.slideUpBottom.setInterpolator(new OvershootInterpolator(2.0f));
    }

    public void hide() {
        setVisibility(4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        toggleTheme();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        initAnimation();
    }

    public void show() {
        setVisibility(0);
        startAnimation(this.slideUpBottom);
    }

    public void toggleTheme() {
        this.binding.recenterLinearLayout.setBackgroundResource(a.e(getContext(), R.attr.navigationRecenterBackgroundDrawable));
        this.binding.recenterTextView.setTextColor(a.b(getContext(), R.attr.navigationTextColorPrimary));
        this.binding.recenterImageView.setImageDrawable(a.d(getContext(), R.attr.navigationViewRecenterDrawable));
    }
}
