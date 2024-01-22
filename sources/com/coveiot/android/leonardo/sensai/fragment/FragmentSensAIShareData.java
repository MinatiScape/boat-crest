package com.coveiot.android.leonardo.sensai.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentSensAiShareDataBinding;
import com.coveiot.android.leonardo.sensai.model.SummaryShareData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentSensAIShareData extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Bitmap bitmap;
    @Nullable
    public FragmentSensAiShareDataBinding m;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String n = "FragmentSensAIShare";
    @NotNull
    public SummaryShareData o = new SummaryShareData();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSensAIShareData newInstance(@NotNull SummaryShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentSensAIShareData fragmentSensAIShareData = new FragmentSensAIShareData();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.SHARE_DATA.getValue(), shareData);
            fragmentSensAIShareData.setArguments(bundle);
            return fragmentSensAIShareData;
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
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @NotNull
    public final SummaryShareData getShareData() {
        return this.o;
    }

    @NotNull
    public final String getTAG() {
        return this.n;
    }

    public final FragmentSensAiShareDataBinding k() {
        FragmentSensAiShareDataBinding fragmentSensAiShareDataBinding = this.m;
        Intrinsics.checkNotNull(fragmentSensAiShareDataBinding);
        return fragmentSensAiShareDataBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(AppConstants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.sensai.model.SummaryShareData");
            this.o = (SummaryShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentSensAiShareDataBinding.inflate(inflater, viewGroup, false);
        return k().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        String sb;
        Resources resources;
        Resources resources2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView imageView = k().ivPoweredCove;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivPoweredCove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, imageView, true);
        k().userName.setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
        k().week.setText(this.o.getDate());
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        String str = null;
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        k().disclaimerInfo.setText(spannableString);
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentSensAIShareData$onViewCreated$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                FragmentSensAiShareDataBinding k;
                Bitmap circleBitmap;
                FragmentSensAiShareDataBinding k2;
                Intrinsics.checkNotNullParameter(resource, "resource");
                k = FragmentSensAIShareData.this.k();
                if (k.userPic == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                k2 = FragmentSensAIShareData.this.k();
                k2.userPic.setImageBitmap(circleBitmap);
            }
        });
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            StringBuilder sb2 = new StringBuilder();
            Context context2 = getContext();
            if (context2 != null && (resources2 = context2.getResources()) != null) {
                str = resources2.getString(R.string.mil_per_hours);
            }
            sb2.append(str);
            sb2.append(' ');
            sb = sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            Context context3 = getContext();
            if (context3 != null && (resources = context3.getResources()) != null) {
                str = resources.getString(R.string.km_per_hours);
            }
            sb3.append(str);
            sb3.append(' ');
            sb = sb3.toString();
        }
        if (this.o.getShareType().equals(SummaryShareData.OVERALL_STATS)) {
            View view2 = k().view2;
            Intrinsics.checkNotNullExpressionValue(view2, "binding.view2");
            gone(view2);
            TextView textView = k().tvVitals;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvVitals");
            gone(textView);
            LinearLayout linearLayout = k().llVitals;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llVitals");
            gone(linearLayout);
            k().tvActivity.setText(requireContext().getString(R.string.session_details));
            k().ivActivity1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_time_blue));
            k().tvActivity1.setText(PayUtils.INSTANCE.formatSecondsInHHMMSS(this.o.getDuration()));
            k().tvActivity1Value.setText(requireContext().getString(R.string.hrs));
            if (this.o.getActivityType() == 1) {
                k().ivCenterVitalBg.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_right_hand_batting));
                k().tvTitle.setText(requireContext().getString(R.string.batting));
                k().ivActivity2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_right_batting));
                TextView textView2 = k().tvActivity2;
                textView2.setText(this.o.getHitPercentage() + " %");
                k().tvActivity2Value.setText(requireContext().getString(R.string.hit_percentage));
                k().tvHandSpeed.setText(requireContext().getString(R.string.hand_speed));
                k().ivHandSpeed1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_batting_avg_armspeed));
                TextView textView3 = k().tvHandSpeed1;
                textView3.setText(this.o.getAvgHandSpeed() + sb);
                k().tvHandSpeed1Txt.setText(requireContext().getString(R.string.avg_hand_speed));
                k().ivHandSpeed2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_batting_max_armspeed));
                TextView textView4 = k().tvHandSpeed2;
                textView4.setText(this.o.getMaxHandSpeed() + sb);
                k().tvHandSpeed2Txt.setText(requireContext().getString(R.string.max_hand_speed));
                k().clHandSpeed3.setVisibility(8);
            } else {
                k().ivCenterVitalBg.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_right_hand_bowling));
                k().tvTitle.setText(requireContext().getString(R.string.bowling));
                k().ivActivity2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_compare_ball));
                k().tvActivity2.setText(String.valueOf(this.o.getTotalBallsBowled()));
                k().tvActivity2Value.setText(requireContext().getString(R.string.balls_bowled));
                k().tvHandSpeed.setText(requireContext().getString(R.string.arm_speed));
                k().ivHandSpeed1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_bowlling_avg_armspeed));
                TextView textView5 = k().tvHandSpeed1;
                textView5.setText(this.o.getAvgArmSpeed() + sb);
                k().tvHandSpeed1Txt.setText(requireContext().getString(R.string.avg_arm_speed));
                k().ivHandSpeed2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_bowlling_max_armspeed));
                TextView textView6 = k().tvHandSpeed2;
                textView6.setText(this.o.getMaxArmSpeed() + sb);
                k().tvHandSpeed2Txt.setText(requireContext().getString(R.string.max_arm_speed));
                ConstraintLayout constraintLayout = k().clHandSpeed3;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clHandSpeed3");
                gone(constraintLayout);
            }
            k().ivActivity3.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_share_sessions_completed));
            k().tvActivity3.setText(String.valueOf(this.o.getTotalSessions()));
            k().tvActivity3Value.setText(requireContext().getString(R.string.sessions_completed));
            k().ivActivity4.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_share_goal));
            k().tvActivity4.setText(String.valueOf(this.o.getTotalTargetAchieved()));
            k().tvActivity4Value.setText(requireContext().getString(R.string.goals_achieved));
            return;
        }
        k().tvActivity.setText(requireContext().getString(R.string.activity));
        if (this.o.getActivityType() == 1) {
            k().ivCenterVitalBg.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_right_hand_batting));
            k().tvTitle.setText(requireContext().getString(R.string.batting));
            k().ivActivity1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_time_blue));
            k().tvActivity1.setText(PayUtils.INSTANCE.formatSecondsInHHMMSS(this.o.getDuration()));
            k().tvActivity1Value.setText(requireContext().getString(R.string.hrs));
            k().ivActivity2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_right_batting));
            TextView textView7 = k().tvActivity2;
            textView7.setText(this.o.getHitPercentage() + " %");
            k().tvActivity2Value.setText(requireContext().getString(R.string.hit_percentage));
            k().ivActivity3.setImageDrawable(requireContext().getDrawable(R.drawable.ic_compare_ball));
            k().tvActivity3.setText(String.valueOf(this.o.getPlayed()));
            k().tvActivity3Value.setText(requireContext().getString(R.string.balls_played));
            k().ivActivity4.setImageDrawable(requireContext().getDrawable(R.drawable.ic_compare_bat));
            k().tvActivity4.setText(String.valueOf(this.o.getTotalShots()));
            k().tvActivity4Value.setText(requireContext().getString(R.string.total_swings));
            k().tvHandSpeed.setText(requireContext().getString(R.string.hand_speed));
            k().ivHandSpeed1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_batting_avg_armspeed));
            TextView textView8 = k().tvHandSpeed1;
            textView8.setText(this.o.getAvgHandSpeed() + sb);
            k().tvHandSpeed1Txt.setText(requireContext().getString(R.string.avg_hand_speed));
            k().ivHandSpeed2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_batting_max_armspeed));
            TextView textView9 = k().tvHandSpeed2;
            textView9.setText(this.o.getMaxHandSpeed() + sb);
            k().tvHandSpeed2Txt.setText(requireContext().getString(R.string.max_hand_speed));
            k().clHandSpeed3.setVisibility(8);
        } else {
            k().ivCenterVitalBg.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_right_hand_bowling));
            k().tvTitle.setText(requireContext().getString(R.string.bowling));
            k().ivActivity1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_time_blue));
            k().tvActivity1.setText(PayUtils.INSTANCE.formatSecondsInHHMMSS(this.o.getDuration()));
            k().tvActivity1Value.setText(requireContext().getString(R.string.hrs));
            k().ivActivity2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_compare_ball));
            k().tvActivity2.setText(String.valueOf(this.o.getTotalBallsBowled()));
            k().tvActivity2Value.setText(requireContext().getString(R.string.balls_bowled));
            k().tvActivity2Value.setPadding(0, 0, 0, 24);
            ConstraintLayout constraintLayout2 = k().clActivity3;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clActivity3");
            gone(constraintLayout2);
            ConstraintLayout constraintLayout3 = k().clActivity4;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clActivity4");
            gone(constraintLayout3);
            View view3 = k().viewActivity;
            Intrinsics.checkNotNullExpressionValue(view3, "binding.viewActivity");
            gone(view3);
            k().tvHandSpeed.setText(requireContext().getString(R.string.arm_speed));
            k().ivHandSpeed1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_share_bowling_min));
            TextView textView10 = k().tvHandSpeed1;
            textView10.setText(this.o.getMinArmSpeed() + sb);
            k().tvHandSpeed1Txt.setText(requireContext().getString(R.string.min_arm_speed));
            k().ivHandSpeed2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_left_bowling));
            TextView textView11 = k().tvHandSpeed2;
            textView11.setText(this.o.getAvgArmSpeed() + sb);
            k().tvHandSpeed2Txt.setText(requireContext().getString(R.string.avg_arm_speed));
            k().ivHandSpeed3.setImageDrawable(requireContext().getDrawable(R.drawable.ic_share_bowling_max));
            TextView textView12 = k().tvHandSpeed3;
            textView12.setText(this.o.getMaxArmSpeed() + sb);
            k().tvHandSpeed3Txt.setText(requireContext().getString(R.string.max_arm_speed));
        }
        k().tvVitals.setText(requireContext().getString(R.string.vitals));
        k().ivVitals1.setImageDrawable(requireContext().getDrawable(R.drawable.ic_calories));
        k().tvVitals1.setText(String.valueOf(this.o.getCalories()));
        k().tvVitals1Txt.setText(requireContext().getString(R.string.cal));
        k().ivVitals2.setImageDrawable(requireContext().getDrawable(R.drawable.ic_heart));
        k().tvVitals2.setText(String.valueOf(this.o.getAvgHeartRate()));
        k().tvVitals2Txt.setText(requireContext().getString(R.string.avg_bpm));
        k().ivVitals3.setImageDrawable(requireContext().getDrawable(2131232553));
        k().tvVitals3.setText(String.valueOf(this.o.getSteps()));
        k().tvVitals3Txt.setText(requireContext().getString(R.string.total_steps));
    }

    @Nullable
    public final Bitmap saveScreen() {
        int height = k().sensAiShareView.getChildAt(0).getHeight();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 30) {
            Display display = requireContext().getDisplay();
            if (display != null) {
                display.getRealMetrics(displayMetrics);
            }
        } else {
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        k().sensAiShareView.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, 1073741824), height);
        k().sensAiShareView.layout(0, 0, displayMetrics.widthPixels, height);
        Bitmap createBitmap = Bitmap.createBitmap(k().sensAiShareView.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
        k().sensAiShareView.draw(new Canvas(createBitmap));
        Bitmap.createScaledBitmap(createBitmap, displayMetrics.widthPixels, height, true);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(requireContext().getFilesDir().getAbsolutePath().toString(), "my_screen.jpg"));
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return createBitmap;
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setShareData(@NotNull SummaryShareData summaryShareData) {
        Intrinsics.checkNotNullParameter(summaryShareData, "<set-?>");
        this.o = summaryShareData;
    }
}
