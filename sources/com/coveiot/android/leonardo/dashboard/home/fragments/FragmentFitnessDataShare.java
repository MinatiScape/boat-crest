package com.coveiot.android.leonardo.dashboard.home.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentFitnessDataShareBinding;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.home.adapters.FitnessGoalAdapter;
import com.coveiot.android.leonardo.more.models.GoalsModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentFitnessDataShare extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentFitnessDataShareBinding m;
    @Nullable
    public FitnessGoalAdapter p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String n = "FragmentFitnessDataShare";
    @NotNull
    public ShareData o = new ShareData();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentFitnessDataShare newInstance(@NotNull ShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentFitnessDataShare fragmentFitnessDataShare = new FragmentFitnessDataShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.SHARE_DATA.getValue(), shareData);
            fragmentFitnessDataShare.setArguments(bundle);
            return fragmentFitnessDataShare;
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
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
    public final String fromMinutesToHHmm(int i) {
        long j = i;
        long hours = TimeUnit.MINUTES.toHours(j);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(hours), Long.valueOf(j - TimeUnit.HOURS.toMinutes(hours))}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @NotNull
    public final ShareData getShareData() {
        return this.o;
    }

    @NotNull
    public final String getTAG() {
        return this.n;
    }

    public final FragmentFitnessDataShareBinding k() {
        FragmentFitnessDataShareBinding fragmentFitnessDataShareBinding = this.m;
        Intrinsics.checkNotNull(fragmentFitnessDataShareBinding);
        return fragmentFitnessDataShareBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(AppConstants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.ShareData");
            this.o = (ShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentFitnessDataShareBinding.inflate(inflater, viewGroup, false);
        return k().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Integer num;
        Integer num2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView imageView = k().ivPoweredCove;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivPoweredCove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, imageView, true);
        CircularArcProgressBar circularArcProgressBar = k().arcProgressBar.circularArcProgressBarTop;
        Intrinsics.checkNotNullExpressionValue(circularArcProgressBar, "binding.arcProgressBar.circularArcProgressBarTop");
        visible(circularArcProgressBar);
        CircularArcProgressBar circularArcProgressBar2 = k().arcProgressBar.circularArcProgressBarDummy;
        Intrinsics.checkNotNullExpressionValue(circularArcProgressBar2, "binding.arcProgressBar.circularArcProgressBarDummy");
        gone(circularArcProgressBar2);
        k().userName.setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
        ((TextView) _$_findCachedViewById(R.id.week)).setText(this.o.getDwmValue());
        TextView textView = k().tvProgressValue;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.goal_achieved_count);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.goal_achieved_count)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{this.o.getData()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView.setText(Html.fromHtml(format), TextView.BufferType.SPANNABLE);
        String string2 = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string2);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        k().disclaimerInfo.setText(spannableString);
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitnessDataShare$onViewCreated$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                FragmentFitnessDataShareBinding k;
                Bitmap circleBitmap;
                FragmentFitnessDataShareBinding k2;
                Intrinsics.checkNotNullParameter(resource, "resource");
                k = FragmentFitnessDataShare.this.k();
                if (k.userPic == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                k2 = FragmentFitnessDataShare.this.k();
                k2.userPic.setImageBitmap(circleBitmap);
            }
        });
        List<GoalsModel> goalsModel = this.o.getGoalsModel();
        if (goalsModel != null) {
            int i = 100;
            if (goalsModel.get(0) != null) {
                Drawable drawable = requireContext().getDrawable(2131232553);
                Intrinsics.checkNotNull(drawable);
                goalsModel.get(0).setImage(drawable);
                try {
                    float goalsValue = goalsModel.get(0).getGoalsValue();
                    FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
                    int roundToInt = kotlin.math.c.roundToInt((goalsValue / ((fitnessGoalStepsData == null || (num2 = fitnessGoalStepsData.target) == null) ? 10000 : num2.intValue())) * 100);
                    if (roundToInt >= 100) {
                        roundToInt = 100;
                    }
                    k().arcProgressBar.circularArcProgressBarTop.setProgress(roundToInt);
                } catch (IllegalArgumentException unused) {
                    k().arcProgressBar.circularArcProgressBarTop.setProgress(0);
                }
            }
            if (goalsModel.get(1) != null) {
                Drawable drawable2 = requireContext().getDrawable(R.drawable.ic_sleep);
                Intrinsics.checkNotNull(drawable2);
                goalsModel.get(1).setImage(drawable2);
                try {
                    float goalsValue2 = goalsModel.get(1).getGoalsValue();
                    FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData();
                    int roundToInt2 = kotlin.math.c.roundToInt((goalsValue2 / ((fitnessGoalSleepData == null || (num = fitnessGoalSleepData.target) == null) ? 480 : num.intValue())) * 100);
                    if (roundToInt2 < 100) {
                        i = roundToInt2;
                    }
                    k().arcProgressBar.circularArcProgressBar.setProgress(i);
                } catch (IllegalArgumentException unused2) {
                    k().arcProgressBar.circularArcProgressBar.setProgress(0);
                }
            }
        }
        k().rvGoals.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.p = new FitnessGoalAdapter(requireContext2, goalsModel);
        k().rvGoals.setAdapter(this.p);
    }

    public final void setShareData(@NotNull ShareData shareData) {
        Intrinsics.checkNotNullParameter(shareData, "<set-?>");
        this.o = shareData;
    }
}
