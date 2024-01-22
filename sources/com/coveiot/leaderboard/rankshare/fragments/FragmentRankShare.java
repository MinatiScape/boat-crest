package com.coveiot.leaderboard.rankshare.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.FragmentRankShareBinding;
import com.coveiot.leaderboard.rankshare.adapters.BadgesAdapter;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.utils.ShareData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ShareScreen;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class FragmentRankShare extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Bitmap bitmap;
    @Nullable
    public FragmentRankShareBinding m;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ShareData n = new ShareData();

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentRankShare newInstance(@NotNull ShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentRankShare fragmentRankShare = new FragmentRankShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable("share_data", shareData);
            fragmentRankShare.setArguments(bundle);
            return fragmentRankShare;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentRankShare newInstance(@NotNull ShareData shareData) {
        return Companion.newInstance(shareData);
    }

    public static final void o(final FragmentRankShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Handler handler = new Handler();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        handler.postDelayed(new Runnable() { // from class: com.coveiot.leaderboard.rankshare.fragments.d
            @Override // java.lang.Runnable
            public final void run() {
                FragmentRankShare.p(FragmentRankShare.this);
            }
        }, 500L);
    }

    public static final void p(FragmentRankShare this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Bitmap saveScreen = ShareScreen.saveScreen(this$0.m().rootLayout, this$0.requireContext());
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(binding.rootLayout, requireContext())");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0.requireContext());
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
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @NotNull
    public final ShareData getShareData() {
        return this.n;
    }

    public final FragmentRankShareBinding m() {
        FragmentRankShareBinding fragmentRankShareBinding = this.m;
        Intrinsics.checkNotNull(fragmentRankShareBinding);
        return fragmentRankShareBinding;
    }

    public final void n() {
        m().tvRsCount.setText(this.n.getRankLevel());
        m().tvRsStepsCount.setText(this.n.getSteps());
        m().tvRsRankScore.setText(this.n.getRank());
        m().tvRsDate.setText(this.n.getRankDate());
        m().tvRsUserName.setText(this.n.getUserName());
        m().tvRsTodayDate.setText(AppUtils.formatDate(Calendar.getInstance().getTime(), "dd MMMM yyyy"));
        int progressStatus = this.n.getProgressStatus();
        m().progressStatusTv.setText(String.valueOf(this.n.getProgressStatus()));
        if (progressStatus >= 0) {
            m().progressStatusTv.setText(String.valueOf(progressStatus));
            m().progressStatusTv.setTextColor(Color.parseColor("#ffffff"));
        } else {
            m().progressStatusTv.setText(String.valueOf(progressStatus * (-1)));
            m().progressStatusTv.setTextColor(Color.parseColor("#ffffff"));
        }
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.leaderboard.rankshare.fragments.FragmentRankShare$initView$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                FragmentRankShareBinding m;
                Bitmap circleBitmap;
                FragmentRankShareBinding m2;
                Intrinsics.checkNotNullParameter(resource, "resource");
                m = FragmentRankShare.this.m();
                if (m.ivRsUserPic == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                m2 = FragmentRankShare.this.m();
                m2.ivRsUserPic.setImageBitmap(circleBitmap);
            }
        });
        m().recyclerviewRs.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        m().recyclerviewRs.setHasFixedSize(true);
        List<MyBadgesModel.DataBean.BadgesBean> myLastNBadges = LeaderBoardDataUtiils.getMyLastNBadges(requireActivity(), 3);
        BadgesAdapter badgesAdapter = new BadgesAdapter(requireActivity());
        badgesAdapter.setData(myLastNBadges);
        m().recyclerviewRs.setAdapter(badgesAdapter);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Serializable serializable = arguments != null ? arguments.getSerializable("share_data") : null;
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.leaderboard.utils.ShareData");
            this.n = (ShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentRankShareBinding.inflate(inflater, viewGroup, false);
        return m().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        n();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView imageView = m().ivRankSharePoweredCove;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivRankSharePoweredCove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, imageView, true);
        ((TextView) requireActivity().findViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.rankshare.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentRankShare.o(FragmentRankShare.this, view2);
            }
        });
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setShareData(@NotNull ShareData shareData) {
        Intrinsics.checkNotNullParameter(shareData, "<set-?>");
        this.n = shareData;
    }
}
