package com.mappls.sdk.navigation.ui.navigation.sound;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.Keep;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutSoundViewBinding;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes11.dex */
public class SoundControllerView extends BaseSoundView {
    private LayoutSoundViewBinding binding;
    private AnimationSet fadeInSlowOut;
    private com.mappls.sdk.navigation.ui.navigation.sound.a soundControllerCallback;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MapplsNavigationHelper.getInstance().isMute()) {
                SoundControllerView.this.unmute();
            } else {
                SoundControllerView.this.mute();
            }
        }
    }

    public SoundControllerView(@NotNull Context context) {
        this(context, null);
    }

    public SoundControllerView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.soundController);
    }

    public SoundControllerView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.SoundControllerStyle);
    }

    public SoundControllerView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.binding = LayoutSoundViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        initAnimations();
    }

    private void initAnimations() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setDuration(300L);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation2.setInterpolator(new AccelerateInterpolator());
        alphaAnimation2.setStartOffset(1000L);
        alphaAnimation2.setDuration(1000L);
        AnimationSet animationSet = new AnimationSet(false);
        this.fadeInSlowOut = animationSet;
        animationSet.addAnimation(alphaAnimation);
        this.fadeInSlowOut.addAnimation(alphaAnimation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mute() {
        setSoundChipText(getContext().getResources().getString(R.string.muted));
        showSoundChip();
        soundFabOff();
        com.mappls.sdk.navigation.ui.navigation.sound.a aVar = this.soundControllerCallback;
        if (aVar != null) {
            aVar.a(true);
        }
    }

    private void setSoundChipText(String str) {
        this.binding.soundText.setText(str);
    }

    private void showSoundChip() {
        this.binding.soundText.startAnimation(this.fadeInSlowOut);
    }

    private void soundFabOff() {
        this.binding.soundBtn.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSoundOff));
    }

    private void soundFabOn() {
        this.binding.soundBtn.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSoundOn));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unmute() {
        setSoundChipText(getContext().getResources().getString(R.string.unmuted));
        showSoundChip();
        soundFabOn();
        com.mappls.sdk.navigation.ui.navigation.sound.a aVar = this.soundControllerCallback;
        if (aVar != null) {
            aVar.a(false);
        }
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.sound.BaseSoundView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        toggleTheme();
    }

    public void setSoundControll() {
        this.binding.soundBtn.setOnClickListener(new a());
    }

    public void setSoundControllerCallback(com.mappls.sdk.navigation.ui.navigation.sound.a aVar) {
        this.soundControllerCallback = aVar;
    }

    public void toggleTheme() {
        this.binding.soundBtn.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSoundBackground));
        if (MapplsNavigationHelper.getInstance().isMute()) {
            soundFabOff();
        } else {
            soundFabOn();
        }
    }
}
