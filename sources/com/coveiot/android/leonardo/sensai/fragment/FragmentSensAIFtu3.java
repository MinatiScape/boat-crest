package com.coveiot.android.leonardo.sensai.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentSensAiFtu3Binding;
import com.coveiot.android.leonardo.more.activities.ActivityEditProfile;
import com.coveiot.android.leonardo.more.activities.ActivityLiftWristToView;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleOneImage;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentSensAIFtu3 extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentSensAiFtu3Binding m;
    @Nullable
    public BottomSheetDialogOneButtonTitleOneImage n;
    @NotNull
    public ActivityResultLauncher<Intent> o;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSensAIFtu3 newInstance() {
            return new FragmentSensAIFtu3();
        }
    }

    public FragmentSensAIFtu3() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.sensai.fragment.e0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FragmentSensAIFtu3.u(FragmentSensAIFtu3.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…setData()\n        }\n    }");
        this.o = registerForActivityResult;
    }

    public static final void r(FragmentSensAIFtu3 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PreferenceManager.saveSensAIFTUShown(this$0.requireContext(), true);
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.navigateToSensAIHomeScreen(requireContext);
        this$0.requireActivity().finish();
    }

    public static final void s(FragmentSensAIFtu3 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o.launch(new Intent(this$0.requireActivity(), ActivityEditProfile.class));
    }

    public static final void t(FragmentSensAIFtu3 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o.launch(new Intent(this$0.requireActivity(), ActivityLiftWristToView.class));
    }

    public static final void u(FragmentSensAIFtu3 this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            this$0.v();
        }
    }

    public static final void x(FragmentSensAIFtu3 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
    }

    public static final void z(FragmentSensAIFtu3 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleOneImage bottomSheetDialogOneButtonTitleOneImage = this$0.n;
        if (bottomSheetDialogOneButtonTitleOneImage != null) {
            bottomSheetDialogOneButtonTitleOneImage.dismiss();
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

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSensAiFtu3Binding inflate = FragmentSensAiFtu3Binding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return q().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        w();
        v();
        q().btnNext.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSensAIFtu3.r(FragmentSensAIFtu3.this, view2);
            }
        });
        q().clProfileEdit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSensAIFtu3.s(FragmentSensAIFtu3.this, view2);
            }
        });
        q().clWakeGesture.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSensAIFtu3.t(FragmentSensAIFtu3.this, view2);
            }
        });
    }

    public final FragmentSensAiFtu3Binding q() {
        FragmentSensAiFtu3Binding fragmentSensAiFtu3Binding = this.m;
        if (fragmentSensAiFtu3Binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentSensAiFtu3Binding;
    }

    public final void v() {
        ProfileData userDetails = SessionManager.getInstance(requireActivity()).getUserDetails();
        if ((userDetails != null ? userDetails.getGender() : null) != null) {
            TextView textView = q().tvGenderValue;
            PayUtils payUtils = PayUtils.INSTANCE;
            String gender = userDetails != null ? userDetails.getGender() : null;
            Intrinsics.checkNotNull(gender);
            textView.setText(payUtils.capatalizeFirstLetter(gender));
        } else {
            q().tvGenderValue.setText("--");
        }
        if ((userDetails != null ? userDetails.getDob() : null) != null) {
            TextView textView2 = q().tvAgeValue;
            StringBuilder sb = new StringBuilder();
            sb.append(AppUtils.getAge(userDetails != null ? userDetails.getDob() : null));
            sb.append(" Y");
            textView2.setText(sb.toString());
        } else {
            q().tvAgeValue.setText("--");
        }
        if ((userDetails != null ? userDetails.getWeight() : null) != null) {
            TextView textView3 = q().tvWeightValue;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userDetails != null ? userDetails.getWeight() : null);
            sb2.append(' ');
            sb2.append(requireContext().getString(R.string.kg_caps));
            textView3.setText(sb2.toString());
        } else {
            q().tvWeightValue.setText("--");
        }
        if ((userDetails != null ? userDetails.getHeight() : null) != null) {
            TextView textView4 = q().tvHeightValue;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(userDetails != null ? userDetails.getHeight() : null);
            sb3.append(' ');
            sb3.append(requireContext().getString(R.string.cm_caps));
            textView4.setText(sb3.toString());
            return;
        }
        q().tvHeightValue.setText("--");
    }

    public final void w() {
        q().tvProfile.setText(String.valueOf(SessionManager.getInstance(requireActivity()).getUserDetails().getName()));
        GlideUtils.loadScaledImage(requireActivity(), SessionManager.getInstance(requireActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentSensAIFtu3$setProfileDetails$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                FragmentSensAiFtu3Binding q;
                Bitmap circleBitmap;
                FragmentSensAiFtu3Binding q2;
                Intrinsics.checkNotNullParameter(resource, "resource");
                q = FragmentSensAIFtu3.this.q();
                if (q.ivProfile == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                q2 = FragmentSensAIFtu3.this.q();
                q2.ivProfile.setImageBitmap(circleBitmap);
            }
        });
        q().ivInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentSensAIFtu3.x(FragmentSensAIFtu3.this, view);
            }
        });
    }

    public final void y() {
        BottomSheetDialogOneButtonTitleOneImage bottomSheetDialogOneButtonTitleOneImage;
        BottomSheetDialogOneButtonTitleOneImage bottomSheetDialogOneButtonTitleOneImage2 = this.n;
        if (bottomSheetDialogOneButtonTitleOneImage2 != null) {
            boolean z = true;
            if (bottomSheetDialogOneButtonTitleOneImage2 == null || !bottomSheetDialogOneButtonTitleOneImage2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetDialogOneButtonTitleOneImage = this.n) != null) {
                bottomSheetDialogOneButtonTitleOneImage.dismiss();
            }
            this.n = null;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        Drawable drawable = requireContext().getDrawable(R.drawable.rounded_top_white_shiny_border_grey_gradient_background);
        Intrinsics.checkNotNull(drawable);
        String string = requireContext().getString(R.string.sens_ai_profile_info);
        Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ing.sens_ai_profile_info)");
        Drawable drawable2 = requireContext().getDrawable(R.drawable.sens_ai_profile_info);
        Intrinsics.checkNotNull(drawable2);
        BottomSheetDialogOneButtonTitleOneImage bottomSheetDialogOneButtonTitleOneImage3 = new BottomSheetDialogOneButtonTitleOneImage(requireActivity, drawable, string, drawable2);
        this.n = bottomSheetDialogOneButtonTitleOneImage3;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleOneImage3);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleOneImage3.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentSensAIFtu3.z(FragmentSensAIFtu3.this, view);
            }
        });
        BottomSheetDialogOneButtonTitleOneImage bottomSheetDialogOneButtonTitleOneImage4 = this.n;
        if (bottomSheetDialogOneButtonTitleOneImage4 != null) {
            bottomSheetDialogOneButtonTitleOneImage4.show();
        }
    }
}
