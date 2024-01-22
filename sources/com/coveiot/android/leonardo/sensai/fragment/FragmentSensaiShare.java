package com.coveiot.android.leonardo.sensai.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentSensaiShareBinding;
import com.coveiot.android.leonardo.sensai.adapter.SensAIShareCardAdapter;
import com.coveiot.android.leonardo.sensai.model.SensAIShareList;
import com.coveiot.android.leonardo.sensai.model.SummaryShareData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import com.coveiot.utils.utility.ShareScreen;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentSensaiShare extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Bitmap bitmap;
    public FragmentSensaiShareBinding m;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public SummaryShareData n = new SummaryShareData();
    @NotNull
    public List<SensAIShareList> o = new ArrayList();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSensaiShare newInstance(@NotNull SummaryShareData summaryShareData) {
            Intrinsics.checkNotNullParameter(summaryShareData, "summaryShareData");
            FragmentSensaiShare fragmentSensaiShare = new FragmentSensaiShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable("share_data", summaryShareData);
            fragmentSensaiShare.setArguments(bundle);
            return fragmentSensaiShare;
        }
    }

    public static final void o(final FragmentSensaiShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Handler handler = new Handler();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.sensai.fragment.h0
            @Override // java.lang.Runnable
            public final void run() {
                FragmentSensaiShare.p(FragmentSensaiShare.this);
            }
        }, 500L);
    }

    public static final void p(FragmentSensaiShare this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Bitmap saveScreen = ShareScreen.saveScreen(this$0.n().rootLayout, this$0.requireContext());
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(binding.rootLayout, requireContext())");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0.requireContext());
    }

    public static final void r() {
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
    public final List<SensAIShareList> getContentList() {
        return this.o;
    }

    @NotNull
    public final SummaryShareData getSummaryShareData() {
        return this.n;
    }

    public final FragmentSensaiShareBinding n() {
        FragmentSensaiShareBinding fragmentSensaiShareBinding = this.m;
        if (fragmentSensaiShareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentSensaiShareBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable("share_data");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.sensai.model.SummaryShareData");
            this.n = (SummaryShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSensaiShareBinding inflate = FragmentSensaiShareBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return n().getRoot();
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
        q();
        n().contentRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        List<SensAIShareList> list = this.o;
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.sensai.model.SensAIShareList>");
        n().contentRecyclerView.setAdapter(new SensAIShareCardAdapter(requireContext, (ArrayList) list));
        ((TextView) requireActivity().findViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSensaiShare.o(FragmentSensaiShare.this, view2);
            }
        });
    }

    public final void q() {
        String sb;
        Resources resources;
        String string;
        Resources resources2;
        Resources resources3;
        Resources resources4;
        Resources resources5;
        Resources resources6;
        Resources resources7;
        Resources resources8;
        Resources resources9;
        Resources resources10;
        Resources resources11;
        Resources resources12;
        Resources resources13;
        Resources resources14;
        Resources resources15;
        Resources resources16;
        Resources resources17;
        Resources resources18;
        Resources resources19;
        Resources resources20;
        Resources resources21;
        Resources resources22;
        Resources resources23;
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
        TextView textView = n().dateTimeText;
        SummaryShareData summaryShareData = this.n;
        textView.setText(simpleDateFormat.format(summaryShareData != null ? new Date(summaryShareData.getTimeStamp()) : null));
        n().tvName.setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
        if (SessionManager.getInstance(getContext()).getUserDetails().getDpUrl() != null) {
            GlideUtils.loadCircularImage(getContext(), SessionManager.getInstance(getContext()).getUserDetails().getDpUrl(), n().imgProfile, new ImageLodeListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.g0
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    FragmentSensaiShare.r();
                }
            });
        } else {
            n().imgProfile.setImageResource(R.drawable.default_profile_grey);
        }
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            StringBuilder sb2 = new StringBuilder();
            Context context = getContext();
            sb2.append((context == null || (resources23 = context.getResources()) == null) ? null : resources23.getString(R.string.mil_per_hours));
            sb2.append(' ');
            sb = sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            Context context2 = getContext();
            sb3.append((context2 == null || (resources = context2.getResources()) == null) ? null : resources.getString(R.string.km_per_hours));
            sb3.append(' ');
            sb = sb3.toString();
        }
        if (this.n.getActivityType() == 1) {
            TextView textView2 = n().titleText;
            if (textView2 != null) {
                textView2.setText(SessionManager.getInstance(getContext()).getUserDetails().getName() + "'s new Cricket(Batting) record");
            }
            if (this.n.getHandType() == 0) {
                TextView textView3 = n().typeText;
                Context context3 = getContext();
                textView3.setText(String.valueOf(context3 != null ? context3.getString(R.string.cricket_right) : null));
                ImageView imageView = n().imgActivity;
                Context context4 = getContext();
                imageView.setImageDrawable((context4 == null || (resources22 = context4.getResources()) == null) ? null : resources22.getDrawable(R.drawable.ic_share_right_hand_batting));
            } else {
                TextView textView4 = n().typeText;
                Context context5 = getContext();
                textView4.setText(String.valueOf(context5 != null ? context5.getString(R.string.cricket_left) : null));
                ImageView imageView2 = n().imgActivity;
                Context context6 = getContext();
                imageView2.setImageDrawable((context6 == null || (resources12 = context6.getResources()) == null) ? null : resources12.getDrawable(R.drawable.ic_left_hand_batting));
            }
            List<SensAIShareList> list = this.o;
            Context context7 = getContext();
            list.add(new SensAIShareList((context7 == null || (resources21 = context7.getResources()) == null) ? null : resources21.getString(R.string.duration), PayUtils.INSTANCE.formatSecondsInHHMMSS(this.n.getDuration())));
            List<SensAIShareList> list2 = this.o;
            Context context8 = getContext();
            list2.add(new SensAIShareList((context8 == null || (resources20 = context8.getResources()) == null) ? null : resources20.getString(R.string.total_shots), String.valueOf(this.n.getTotalShots())));
            List<SensAIShareList> list3 = this.o;
            Context context9 = getContext();
            list3.add(new SensAIShareList((context9 == null || (resources19 = context9.getResources()) == null) ? null : resources19.getString(R.string.played), String.valueOf(this.n.getPlayed())));
            List<SensAIShareList> list4 = this.o;
            Context context10 = getContext();
            String string2 = (context10 == null || (resources18 = context10.getResources()) == null) ? null : resources18.getString(R.string.avg_hand_speed);
            list4.add(new SensAIShareList(string2, this.n.getAvgHandSpeed() + ' ' + sb));
            List<SensAIShareList> list5 = this.o;
            Context context11 = getContext();
            String string3 = (context11 == null || (resources17 = context11.getResources()) == null) ? null : resources17.getString(R.string.max_hand_speed);
            list5.add(new SensAIShareList(string3, this.n.getMaxHandSpeed() + ' ' + sb));
            List<SensAIShareList> list6 = this.o;
            Context context12 = getContext();
            String string4 = (context12 == null || (resources16 = context12.getResources()) == null) ? null : resources16.getString(R.string.hit_perc);
            list6.add(new SensAIShareList(string4, this.n.getHitPercentage() + " %"));
            List<SensAIShareList> list7 = this.o;
            Context context13 = getContext();
            list7.add(new SensAIShareList((context13 == null || (resources15 = context13.getResources()) == null) ? null : resources15.getString(R.string.calories), String.valueOf(kotlin.math.c.roundToInt(this.n.getCalories()))));
            List<SensAIShareList> list8 = this.o;
            Context context14 = getContext();
            list8.add(new SensAIShareList((context14 == null || (resources14 = context14.getResources()) == null) ? null : resources14.getString(R.string.total_steps), String.valueOf(this.n.getSteps())));
            List<SensAIShareList> list9 = this.o;
            Context context15 = getContext();
            String string5 = (context15 == null || (resources13 = context15.getResources()) == null) ? null : resources13.getString(R.string.avg_heart_rage);
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.n.getAvgHeartRate());
            sb4.append(' ');
            Context context16 = getContext();
            sb4.append(context16 != null ? context16.getString(R.string.bpm_small) : null);
            list9.add(new SensAIShareList(string5, sb4.toString()));
            return;
        }
        TextView textView5 = n().titleText;
        if (textView5 != null) {
            textView5.setText(SessionManager.getInstance(getContext()).getUserDetails().getName() + "'s new Cricket(Bowling) record");
        }
        if (this.n.getHandType() == 0) {
            Context context17 = getContext();
            string = context17 != null ? context17.getString(R.string.right_hand) : null;
            ImageView imageView3 = n().imgActivity;
            Context context18 = getContext();
            imageView3.setImageDrawable((context18 == null || (resources11 = context18.getResources()) == null) ? null : resources11.getDrawable(R.drawable.ic_share_right_hand_spin));
        } else {
            Context context19 = getContext();
            string = context19 != null ? context19.getString(R.string.left_hand) : null;
            ImageView imageView4 = n().imgActivity;
            Context context20 = getContext();
            imageView4.setImageDrawable((context20 == null || (resources2 = context20.getResources()) == null) ? null : resources2.getDrawable(R.drawable.ic_left_hand_bowling));
        }
        if (this.n.getBowlingType() == 0) {
            TextView textView6 = n().typeText;
            textView6.setText("Cricket(" + string + ' ' + AppConstants.BOWLING_FAST.getValue() + HexStringBuilder.COMMENT_END_CHAR);
        } else if (this.n.getBowlingType() == 1) {
            TextView textView7 = n().typeText;
            textView7.setText("Cricket(" + string + ' ' + AppConstants.BOWLING_MEDIUM_PACE.getValue() + HexStringBuilder.COMMENT_END_CHAR);
        } else {
            TextView textView8 = n().typeText;
            textView8.setText("Cricket(" + string + ' ' + AppConstants.BOWLING_SPIN.getValue() + HexStringBuilder.COMMENT_END_CHAR);
        }
        List<SensAIShareList> list10 = this.o;
        Context context21 = getContext();
        list10.add(new SensAIShareList((context21 == null || (resources10 = context21.getResources()) == null) ? null : resources10.getString(R.string.duration), PayUtils.INSTANCE.formatSecondsInHHMMSS(this.n.getDuration())));
        List<SensAIShareList> list11 = this.o;
        Context context22 = getContext();
        list11.add(new SensAIShareList((context22 == null || (resources9 = context22.getResources()) == null) ? null : resources9.getString(R.string.total_balls_bowled), String.valueOf(this.n.getTotalBallsBowled())));
        List<SensAIShareList> list12 = this.o;
        Context context23 = getContext();
        String string6 = (context23 == null || (resources8 = context23.getResources()) == null) ? null : resources8.getString(R.string.avg_arm_speed);
        list12.add(new SensAIShareList(string6, this.n.getAvgArmSpeed() + ' ' + sb));
        List<SensAIShareList> list13 = this.o;
        Context context24 = getContext();
        String string7 = (context24 == null || (resources7 = context24.getResources()) == null) ? null : resources7.getString(R.string.max_arm_speed);
        list13.add(new SensAIShareList(string7, this.n.getMaxArmSpeed() + ' ' + sb));
        List<SensAIShareList> list14 = this.o;
        Context context25 = getContext();
        String string8 = (context25 == null || (resources6 = context25.getResources()) == null) ? null : resources6.getString(R.string.min_arm_speed);
        list14.add(new SensAIShareList(string8, this.n.getMinArmSpeed() + ' ' + sb));
        List<SensAIShareList> list15 = this.o;
        Context context26 = getContext();
        list15.add(new SensAIShareList((context26 == null || (resources5 = context26.getResources()) == null) ? null : resources5.getString(R.string.calories), String.valueOf(kotlin.math.c.roundToInt(this.n.getCalories()))));
        List<SensAIShareList> list16 = this.o;
        Context context27 = getContext();
        list16.add(new SensAIShareList((context27 == null || (resources4 = context27.getResources()) == null) ? null : resources4.getString(R.string.total_steps), String.valueOf(this.n.getSteps())));
        List<SensAIShareList> list17 = this.o;
        Context context28 = getContext();
        String string9 = (context28 == null || (resources3 = context28.getResources()) == null) ? null : resources3.getString(R.string.avg_heart_rage);
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.n.getAvgHeartRate());
        sb5.append(' ');
        Context context29 = getContext();
        sb5.append(context29 != null ? context29.getString(R.string.bpm_small) : null);
        list17.add(new SensAIShareList(string9, sb5.toString()));
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setContentList(@NotNull List<SensAIShareList> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.o = list;
    }

    public final void setSummaryShareData(@NotNull SummaryShareData summaryShareData) {
        Intrinsics.checkNotNullParameter(summaryShareData, "<set-?>");
        this.n = summaryShareData;
    }
}
