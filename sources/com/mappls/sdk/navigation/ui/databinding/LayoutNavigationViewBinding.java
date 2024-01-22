package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.navigation.alert.NavigationEventAlertView;
import com.mappls.sdk.navigation.ui.navigation.directions.DirectionsListView;
import com.mappls.sdk.navigation.ui.navigation.finished.NavigationFinishedView;
import com.mappls.sdk.navigation.ui.navigation.infobar.InfobarBottomSheetView;
import com.mappls.sdk.navigation.ui.navigation.instructioncontainer.InstructionContainerView;
import com.mappls.sdk.navigation.ui.navigation.instructioncontainer.InstructionContainrPIPView;
import com.mappls.sdk.navigation.ui.navigation.nextinstruction.NextInstructionView;
import com.mappls.sdk.navigation.ui.navigation.recenter.RecenterButton;
import com.mappls.sdk.navigation.ui.navigation.searchalongroute.SearchAlongRouteView;
import com.mappls.sdk.navigation.ui.navigation.settings.SettingView;
import com.mappls.sdk.navigation.ui.navigation.sound.SoundControllerView;
/* loaded from: classes11.dex */
public abstract class LayoutNavigationViewBinding extends ViewDataBinding {
    @NonNull
    public final NavigationEventAlertView alertView;
    @NonNull
    public final ConstraintLayout constraintLayout2;
    @NonNull
    public final DirectionsListView directionList;
    @NonNull
    public final TextView eventsText;
    @NonNull
    public final RecenterButton followButton;
    @NonNull
    public final InfobarBottomSheetView infobarView;
    @NonNull
    public final InstructionContainerView instructionContainer;
    @NonNull
    public final InstructionContainrPIPView instructionContainerPip;
    @NonNull
    public final FrameLayout mapLayout;
    @Nullable
    public final NavigationFinishedView navigationFinishedView;
    @NonNull
    public final NextInstructionView nextAdviseView;
    @NonNull
    public final FloatingActionButton resetBoundsButton;
    @NonNull
    public final ImageView searchAlongRoute;
    @NonNull
    public final SearchAlongRouteView searchAlongRouteView;
    @NonNull
    public final SettingView settingsView;
    @NonNull
    public final SoundControllerView soundView;
    @NonNull
    public final TextView speedWarningButton;
    @NonNull
    public final TextView tvSpeed;
    @NonNull
    public final TextView warningTextView;

    public LayoutNavigationViewBinding(Object obj, View view, int i, NavigationEventAlertView navigationEventAlertView, ConstraintLayout constraintLayout, DirectionsListView directionsListView, TextView textView, RecenterButton recenterButton, InfobarBottomSheetView infobarBottomSheetView, InstructionContainerView instructionContainerView, InstructionContainrPIPView instructionContainrPIPView, FrameLayout frameLayout, NavigationFinishedView navigationFinishedView, NextInstructionView nextInstructionView, FloatingActionButton floatingActionButton, ImageView imageView, SearchAlongRouteView searchAlongRouteView, SettingView settingView, SoundControllerView soundControllerView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.alertView = navigationEventAlertView;
        this.constraintLayout2 = constraintLayout;
        this.directionList = directionsListView;
        this.eventsText = textView;
        this.followButton = recenterButton;
        this.infobarView = infobarBottomSheetView;
        this.instructionContainer = instructionContainerView;
        this.instructionContainerPip = instructionContainrPIPView;
        this.mapLayout = frameLayout;
        this.navigationFinishedView = navigationFinishedView;
        this.nextAdviseView = nextInstructionView;
        this.resetBoundsButton = floatingActionButton;
        this.searchAlongRoute = imageView;
        this.searchAlongRouteView = searchAlongRouteView;
        this.settingsView = settingView;
        this.soundView = soundControllerView;
        this.speedWarningButton = textView2;
        this.tvSpeed = textView3;
        this.warningTextView = textView4;
    }

    public static LayoutNavigationViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutNavigationViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutNavigationViewBinding) ViewDataBinding.bind(obj, view, R.layout.layout_navigation_view);
    }

    @NonNull
    public static LayoutNavigationViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutNavigationViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutNavigationViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutNavigationViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_navigation_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutNavigationViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutNavigationViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_navigation_view, null, false, obj);
    }
}
