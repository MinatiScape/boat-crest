package com.mappls.sdk.navigation.ui.navigation.nextinstruction;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.annotation.Keep;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutNextInstructionViewBinding;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes11.dex */
public class NextInstructionView extends a {
    public LayoutNextInstructionViewBinding binding;

    public NextInstructionView(@NotNull Context context) {
        this(context, null);
    }

    public NextInstructionView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.soundController);
    }

    public NextInstructionView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.SoundControllerStyle);
    }

    public NextInstructionView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.binding = LayoutNextInstructionViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.nextinstruction.a, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        toggleTheme();
    }

    public void setDurationText(String str) {
        this.binding.nextAdviceDuration.setText(str);
    }

    public void setNextManeuverTypeAndModifier(String str, String str2) {
        this.binding.nextInstructionImageView.setManeuverTypeAndModifier(str, str2);
    }

    public void setRoundaboutAngle(float f) {
        this.binding.nextInstructionImageView.setRoundaboutAngle(f);
    }

    public void toggleTheme() {
        this.binding.nextAdviseContainer.setBackgroundResource(com.mappls.sdk.navigation.ui.theme.a.e(getContext(), R.attr.navigationViewNextBannerBackground));
        this.binding.nextInstructionImageView.setPrimaryColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewNextBannerManeuverPrimary));
        this.binding.nextInstructionImageView.setSecondaryColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewNextBannerManeuverSecondary));
        TextView textView = this.binding.tvThen;
        Context context = getContext();
        int i = R.attr.navigationViewNextBannerTextColor;
        textView.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(context, i));
        this.binding.nextAdviceDuration.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i));
    }
}
