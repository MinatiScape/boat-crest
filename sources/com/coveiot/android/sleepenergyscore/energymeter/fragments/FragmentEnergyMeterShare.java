package com.coveiot.android.sleepenergyscore.energymeter.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.model.ShareEnergyMeterData;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.components.indicators.ImageIndicator;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentEnergyMeterShare extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ShareEnergyMeterData h = new ShareEnergyMeterData();

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentEnergyMeterShare newInstance(@NotNull ShareEnergyMeterData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentEnergyMeterShare fragmentEnergyMeterShare = new FragmentEnergyMeterShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.SHARE_DATA.getValue(), shareData);
            fragmentEnergyMeterShare.setArguments(bundle);
            return fragmentEnergyMeterShare;
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @NotNull
    public final ShareEnergyMeterData getShareData() {
        return this.h;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(Constants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.sleepenergyscore.energymeter.model.ShareEnergyMeterData");
            this.h = (ShareEnergyMeterData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_energy_meter_share_new, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        updateView();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView iv_powered_cove = (ImageView) _$_findCachedViewById(R.id.iv_powered_cove);
        Intrinsics.checkNotNullExpressionValue(iv_powered_cove, "iv_powered_cove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, iv_powered_cove, true);
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        ((TextView) _$_findCachedViewById(R.id.disclaimer_info)).setText(spannableString);
        int i = R.id.speedView_energy_meter_share;
        ((SpeedView) _$_findCachedViewById(i)).makeSections(5, -16711681, Style.BUTT);
        ((SpeedView) _$_findCachedViewById(i)).getSections().get(0).setColor(getResources().getColor(R.color.color_fcc695));
        ((SpeedView) _$_findCachedViewById(i)).getSections().get(1).setColor(getResources().getColor(R.color.color_fda659));
        ((SpeedView) _$_findCachedViewById(i)).getSections().get(2).setColor(getResources().getColor(R.color.color_fc861d));
        ((SpeedView) _$_findCachedViewById(i)).getSections().get(3).setColor(getResources().getColor(R.color.color_f47300));
        ((SpeedView) _$_findCachedViewById(i)).getSections().get(4).setColor(getResources().getColor(R.color.color_fa6400));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        Drawable drawable = getResources().getDrawable(R.drawable.indicator);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.indicator)");
        ((SpeedView) _$_findCachedViewById(i)).setIndicator(new ImageIndicator(requireContext2, drawable));
    }

    public final void setShareData(@NotNull ShareEnergyMeterData shareEnergyMeterData) {
        Intrinsics.checkNotNullParameter(shareEnergyMeterData, "<set-?>");
        this.h = shareEnergyMeterData;
    }

    public final void updateView() {
        ((TextView) _$_findCachedViewById(R.id.user_name)).setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
        if (this.h.getEndEnergyScore() > 0) {
            ((LinearLayout) _$_findCachedViewById(R.id.day_data_lay)).setVisibility(8);
            ((ConstraintLayout) _$_findCachedViewById(R.id.history_data_lay)).setVisibility(8);
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.day_data_lay)).setVisibility(8);
            ((ConstraintLayout) _$_findCachedViewById(R.id.history_data_lay)).setVisibility(8);
        }
        ((TextView) _$_findCachedViewById(R.id.tv_energy_meter)).setText(String.valueOf(this.h.getStartEnergyScore()));
        ((SpeedView) _$_findCachedViewById(R.id.speedView_energy_meter_share)).speedTo(this.h.getStartEnergyScore(), 0L);
        ((TextView) _$_findCachedViewById(R.id.start_score_val)).setText(String.valueOf(this.h.getStartEnergyScore()));
        ((TextView) _$_findCachedViewById(R.id.end_score_val)).setText(String.valueOf(this.h.getEndEnergyScore()));
        ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.FragmentEnergyMeterShare$updateView$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap circleBitmap;
                Intrinsics.checkNotNullParameter(resource, "resource");
                FragmentEnergyMeterShare fragmentEnergyMeterShare = FragmentEnergyMeterShare.this;
                int i = R.id.user_pic;
                if (((ImageView) fragmentEnergyMeterShare._$_findCachedViewById(i)) == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                ((ImageView) FragmentEnergyMeterShare.this._$_findCachedViewById(i)).setImageBitmap(circleBitmap);
            }
        });
    }
}
