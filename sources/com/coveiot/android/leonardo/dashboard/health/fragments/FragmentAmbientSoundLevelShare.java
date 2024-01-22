package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentAmbientSoundLevelShare extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public String h;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ShareData i = new ShareData();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentAmbientSoundLevelShare newInstance(@NotNull ShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentAmbientSoundLevelShare fragmentAmbientSoundLevelShare = new FragmentAmbientSoundLevelShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.SHARE_DATA.getValue(), shareData);
            fragmentAmbientSoundLevelShare.setArguments(bundle);
            return fragmentAmbientSoundLevelShare;
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

    @Nullable
    public final String getDateString() {
        return this.h;
    }

    @NotNull
    public final ShareData getShareData() {
        return this.i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(AppConstants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.ShareData");
            this.i = (ShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_share_ambientsound, viewGroup, false);
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
    }

    public final void setDateString(@Nullable String str) {
        this.h = str;
    }

    public final void setShareData(@NotNull ShareData shareData) {
        Intrinsics.checkNotNullParameter(shareData, "<set-?>");
        this.i = shareData;
    }

    public final void updateView() {
        TextView textView;
        this.h = this.i.getDwmValue();
        if (m.equals(this.i.getGraphType(), getString(R.string.share_daily), true)) {
            ((TextView) _$_findCachedViewById(R.id.tv_insight_dt)).setText(getResources().getString(R.string.ambient_insights) + AppConstants.EMPTY_SPACE.getValue() + this.h);
        } else if (m.equals(this.i.getGraphType(), getString(R.string.share_weekly), true)) {
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_insight_dt);
            if (textView2 != null) {
                textView2.setText(getResources().getString(R.string.weekly_ambient_insights) + AppConstants.EMPTY_SPACE.getValue() + this.h);
            }
        } else if (m.equals(this.i.getGraphType(), getString(R.string.share_monthly), true) && (textView = (TextView) _$_findCachedViewById(R.id.tv_insight_dt)) != null) {
            textView.setText(getResources().getString(R.string.monthly_ambient_insights) + AppConstants.EMPTY_SPACE.getValue() + this.h);
        }
        ((TextView) _$_findCachedViewById(R.id.textViewPersonName)).setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tv_ambient_sound_value);
        if (textView3 != null) {
            String avg_ambient_sound = this.i.getAvg_ambient_sound();
            Intrinsics.checkNotNull(avg_ambient_sound);
            textView3.setText(avg_ambient_sound);
        }
        String avg_ambient_sound2 = this.i.getAvg_ambient_sound();
        Intrinsics.checkNotNull(avg_ambient_sound2);
        String string = getString(R.string.decible_unit);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.decible_unit)");
        if (StringsKt__StringsKt.contains((CharSequence) avg_ambient_sound2, (CharSequence) string, true)) {
            String string2 = getString(R.string.decible_unit);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.decible_unit)");
            String replace$default = m.replace$default(avg_ambient_sound2, string2, "", false, 4, (Object) null);
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.tv_ambient_sound_status);
            if (textView4 != null) {
                int parseInt = Integer.parseInt(StringsKt__StringsKt.trim(replace$default).toString());
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                textView4.setText(PayUtils.getAmbientSoundInfo(parseInt, requireContext));
            }
        }
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevelShare$updateView$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap circleBitmap;
                Intrinsics.checkNotNullParameter(resource, "resource");
                FragmentAmbientSoundLevelShare fragmentAmbientSoundLevelShare = FragmentAmbientSoundLevelShare.this;
                int i = R.id.imageViewPersonImage;
                if (((ImageView) fragmentAmbientSoundLevelShare._$_findCachedViewById(i)) == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                ((ImageView) FragmentAmbientSoundLevelShare.this._$_findCachedViewById(i)).setImageBitmap(circleBitmap);
            }
        });
    }
}
