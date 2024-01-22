package com.coveiot.leaderboard.rankshare.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.FragmentBadgeCardShareBinding;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.coveiot.leaderboard.utils.ShareData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ShareScreen;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class FragmentBadgeCardShare extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Bitmap bitmap;
    public Object data;
    @Nullable
    public FragmentBadgeCardShareBinding m;
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

        @NotNull
        public final FragmentBadgeCardShare newInstance(@NotNull ShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentBadgeCardShare fragmentBadgeCardShare = new FragmentBadgeCardShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable("share_data", shareData);
            fragmentBadgeCardShare.setArguments(bundle);
            return fragmentBadgeCardShare;
        }
    }

    public static final void o(final FragmentBadgeCardShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Handler handler = new Handler();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        handler.postDelayed(new Runnable() { // from class: com.coveiot.leaderboard.rankshare.fragments.b
            @Override // java.lang.Runnable
            public final void run() {
                FragmentBadgeCardShare.p(FragmentBadgeCardShare.this);
            }
        }, 500L);
    }

    public static final void p(FragmentBadgeCardShare this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Bitmap saveScreen = ShareScreen.saveScreen(this$0.m().badgeShareConstraintLayout, this$0.requireContext());
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(binding.badge…Layout, requireContext())");
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
    public final Object getData() {
        Object obj = this.data;
        if (obj != null) {
            return obj;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return Unit.INSTANCE;
    }

    @NotNull
    public final ShareData getShareData() {
        return this.n;
    }

    public final FragmentBadgeCardShareBinding m() {
        FragmentBadgeCardShareBinding fragmentBadgeCardShareBinding = this.m;
        Intrinsics.checkNotNull(fragmentBadgeCardShareBinding);
        return fragmentBadgeCardShareBinding;
    }

    public final void n() {
        TextView textView = m().tvBadgeEarnedOn;
        textView.setText(getString(R.string.badge_earned) + ' ' + AppUtils.formatDate(Calendar.getInstance().getTime(), "dd MMM yyyy"));
        m().tvBsUserName.setText(SessionManager.getInstance(getActivity()).getUserDetails().getName());
        MyBadgesModel.DataBean.BadgesBean data = this.n.getData();
        Intrinsics.checkNotNull(data);
        List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels = data.getBadgeLevels();
        if (!(badgeLevels == null || badgeLevels.isEmpty())) {
            MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean = null;
            for (MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean2 : data.getBadgeLevels()) {
                String obtainedDate = badgeLevelsBean2.getObtainedDate();
                if (!(obtainedDate == null || obtainedDate.length() == 0)) {
                    if (badgeLevelsBean != null) {
                        String obtainedDate2 = badgeLevelsBean.getObtainedDate();
                        String obtainedDate3 = badgeLevelsBean2.getObtainedDate();
                        Intrinsics.checkNotNullExpressionValue(obtainedDate3, "levelBean.obtainedDate");
                        if (obtainedDate2.compareTo(obtainedDate3) < 0) {
                        }
                    }
                    badgeLevelsBean = badgeLevelsBean2;
                }
            }
            if (badgeLevelsBean != null) {
                TextView textView2 = m().tvRsBadgeName;
                textView2.setText(data.getBadgeName() + Soundex.SILENT_MARKER + badgeLevelsBean.getLevelName());
                m().tvRsBagdeDesc.setText(badgeLevelsBean.getLevelDescription());
                Glide.with(requireActivity()).m30load(badgeLevelsBean.getLevelImageUrl()).into(m().ivRsBadgeIcon);
                if (badgeLevelsBean.getUserCriteria() == 1) {
                    m().tvRsWhenEarned.setText(getString(R.string.earned_once));
                } else if (badgeLevelsBean.getUserCriteria() == 2) {
                    m().tvRsWhenEarned.setText(getString(R.string.earned_twice));
                } else {
                    TextView textView3 = m().tvRsWhenEarned;
                    textView3.setText(getString(R.string.earned) + ' ' + badgeLevelsBean.getUserCriteria() + ' ' + getString(R.string.times));
                }
                TextView textView4 = m().tvRsEarnedOn;
                textView4.setText(getString(R.string.earned_on_small) + ' ' + LeaderboardUtils.formattedDate(badgeLevelsBean.getObtainedDate(), "dd MMMM"));
            }
        }
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.leaderboard.rankshare.fragments.FragmentBadgeCardShare$initView$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                FragmentBadgeCardShareBinding m;
                Bitmap circleBitmap;
                FragmentBadgeCardShareBinding m2;
                Intrinsics.checkNotNullParameter(resource, "resource");
                m = FragmentBadgeCardShare.this.m();
                if (m.ivBsUserPic == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                m2 = FragmentBadgeCardShare.this.m();
                m2.ivBsUserPic.setImageBitmap(circleBitmap);
            }
        });
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
        this.m = FragmentBadgeCardShareBinding.inflate(inflater, viewGroup, false);
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
        ImageView imageView = m().ivBadgeSharePoweredCove;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivBadgeSharePoweredCove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, imageView, true);
        ((TextView) requireActivity().findViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.rankshare.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentBadgeCardShare.o(FragmentBadgeCardShare.this, view2);
            }
        });
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setData(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.data = obj;
    }

    public final void setShareData(@NotNull ShareData shareData) {
        Intrinsics.checkNotNullParameter(shareData, "<set-?>");
        this.n = shareData;
    }
}
