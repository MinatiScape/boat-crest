package com.mappls.sdk.navigation.ui.navigation.instructioncontainer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutInstructionPipBinding;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes11.dex */
public class InstructionContainrPIPView extends BaseInstructionContainerView implements LifecycleObserver {
    private NavigationApplication app;
    private LayoutInstructionPipBinding binding;
    private LifecycleOwner lifecycleOwner;
    private List<NavigationStep> mAdvises;
    private com.mappls.sdk.navigation.ui.navigation.a navigationViewModel;

    /* loaded from: classes11.dex */
    public class a implements Observer<com.mappls.sdk.navigation.ui.navigation.instructioncontainer.a> {
        public a() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(com.mappls.sdk.navigation.ui.navigation.instructioncontainer.a aVar) {
            InstructionContainrPIPView.this.setAdviseInfo(aVar);
        }
    }

    public InstructionContainrPIPView(@NotNull Context context) {
        this(context, null);
    }

    public InstructionContainrPIPView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.instructionContainerStyle);
    }

    public InstructionContainrPIPView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.InstructionContainerStyle);
    }

    public InstructionContainrPIPView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.binding = LayoutInstructionPipBinding.inflate(LayoutInflater.from(getContext()), this, true);
        context.obtainStyledAttributes(attributeSet, R.styleable.InstructionContainer, i, i2);
    }

    private void toggleTheme() {
        this.binding.maneuverImageView.setPrimaryColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewBannerManeuverPrimary));
        this.binding.maneuverImageView.setSecondaryColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewBannerManeuverSecondary));
        this.binding.stripItemContainer.setBackgroundResource(com.mappls.sdk.navigation.ui.theme.a.e(getContext(), R.attr.navigationViewBannerBackgroundSelected));
        this.binding.navigationStripDist.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewBannerSecondaryText));
        this.binding.navigationStripText.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewBannerPrimaryText));
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.instructioncontainer.BaseInstructionContainerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        toggleTheme();
    }

    public void setAdviseInfo(com.mappls.sdk.navigation.ui.navigation.instructioncontainer.a aVar) {
        ManeuverView maneuverView;
        float b;
        AdviseInfo a2 = aVar.a();
        if (a2.isRouteBeingRecalculated() && !a2.isOnRoute()) {
            this.binding.otherInfoTextView.setVisibility(0);
            this.binding.stripItemContainer.setVisibility(8);
            return;
        }
        this.binding.otherInfoTextView.setVisibility(8);
        this.binding.stripItemContainer.setVisibility(0);
        this.binding.navigationStripText.setText(a2.getText());
        if (this.app != null) {
            this.binding.navigationStripDist.setText(NavigationFormatter.getFormattedDistance(a2.getDistanceToNextAdvise(), this.app));
        }
        if (a2.getInfo() instanceof LegStep) {
            LegStep legStep = (LegStep) a2.getInfo();
            this.binding.maneuverImageView.setManeuverTypeAndModifier(legStep.maneuver().type(), legStep.maneuver().modifier());
            if (legStep.maneuver().type().equalsIgnoreCase("roundabout") || legStep.maneuver().type().equalsIgnoreCase("rotary")) {
                if (legStep.maneuver().degree() != null) {
                    maneuverView = this.binding.maneuverImageView;
                    b = legStep.maneuver().degree().floatValue();
                } else {
                    List<NavigationStep> list = this.mAdvises;
                    if (list != null && list.size() > a2.getPosition() + 1) {
                        float a3 = com.mappls.sdk.navigation.ui.utils.d.a(legStep, (LegStep) this.mAdvises.get(a2.getPosition() + 1).getExtraInfo());
                        maneuverView = this.binding.maneuverImageView;
                        b = com.mappls.sdk.navigation.ui.utils.d.b(a3);
                    }
                }
                maneuverView.setRoundaboutAngle(b);
            }
            if (a2.isOnRoute()) {
                this.binding.otherInfoTextView.setVisibility(8);
                this.binding.stripItemContainer.setVisibility(0);
            }
        }
    }

    public void setDataContainer(NavigationApplication navigationApplication) {
        this.app = navigationApplication;
        this.binding.otherInfoTextView.setVisibility(8);
        this.binding.stripItemContainer.setVisibility(0);
        this.mAdvises = navigationApplication.getRouteDirections();
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.instructioncontainer.BaseInstructionContainerView
    public void setLeftButtonIcon(int i) {
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.instructioncontainer.BaseInstructionContainerView
    public void setRightButtonIcon(int i) {
    }

    public void subscribe(LifecycleOwner lifecycleOwner, com.mappls.sdk.navigation.ui.navigation.a aVar) {
        this.lifecycleOwner = lifecycleOwner;
        lifecycleOwner.getLifecycle().addObserver(this);
        this.navigationViewModel = aVar;
        aVar.b.observe(this.lifecycleOwner, new a());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void unsubscribe() {
        com.mappls.sdk.navigation.ui.navigation.a aVar = this.navigationViewModel;
        if (aVar != null) {
            aVar.b.removeObservers(this.lifecycleOwner);
        }
    }
}
