package com.coveiot.android.leonardo.rateus;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.AdapterRatingInfo;
import com.coveiot.android.leonardo.more.models.RatingInfo;
import com.coveiot.android.leonardo.more.viewmodel.RateUsViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.ExtensionFuncsKt;
import com.coveiot.android.theme.utils.ReviewAndRateUsConstants;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityRateUs extends BaseActivity implements Observer<ArrayList<RatingInfo>>, AdapterRatingInfo.RatingSelectionListener, RateUsViewModel.ApiResultListener {
    public AdapterRatingInfo adapterRatingInfo;
    public RateUsViewModel p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public String q = "";

    public static final void C(ActivityRateUs this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void D(Button button, View view) {
        if (button != null) {
            button.performClick();
        }
    }

    public static final void E(BottomSheetDialog dialog, ActivityRateUs this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.A();
        this$0.showRatingSubmitSuccess();
    }

    public static final void F(EditText editText, ActivityRateUs this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isEmpty(String.valueOf(editText != null ? editText.getText() : null))) {
            RateUsViewModel rateUsViewModel = this$0.p;
            if (rateUsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rateusViewModel");
                rateUsViewModel = null;
            }
            rateUsViewModel.saveRatingToServer(this$0.getAdapterRatingInfo().getSelectedRatingInfo(), this$0, String.valueOf(editText != null ? editText.getText() : null));
            return;
        }
        String string = this$0.getString(R.string.please_enter_msg);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_enter_msg)");
        ExtensionFuncsKt.toastShort(this$0, string);
    }

    public static final void G(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityRateUs this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void H(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityRateUs this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void y(ActivityRateUs this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PayUtils.INSTANCE.redirectToPlaystore(this$0);
    }

    public static final void z(ActivityRateUs this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.showProgress();
            RateUsViewModel rateUsViewModel = this$0.p;
            if (rateUsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rateusViewModel");
                rateUsViewModel = null;
            }
            RateUsViewModel.saveRatingToServer$default(rateUsViewModel, this$0.getAdapterRatingInfo().getSelectedRatingInfo(), this$0, null, 4, null);
            return;
        }
        String string = this$0.getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_check_your_internet)");
        ExtensionFuncsKt.toastShort(this$0, string);
    }

    public final void A() {
        dismissProgress();
        getAdapterRatingInfo().resetRating();
        ((Button) _$_findCachedViewById(R.id.submit)).setEnabled(false);
    }

    public final void B() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.rate_us));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRateUs.C(ActivityRateUs.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @NotNull
    public final AdapterRatingInfo getAdapterRatingInfo() {
        AdapterRatingInfo adapterRatingInfo = this.adapterRatingInfo;
        if (adapterRatingInfo != null) {
            return adapterRatingInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapterRatingInfo");
        return null;
    }

    @Nullable
    public final String getPreviousScreenName() {
        return this.q;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rate_us);
        Intent intent = getIntent();
        RateUsViewModel rateUsViewModel = null;
        this.q = intent != null ? intent.getStringExtra("cv_prev_screen_name") : null;
        RateUsViewModel rateUsViewModel2 = (RateUsViewModel) ViewModelProviders.of(this).get(RateUsViewModel.class);
        this.p = rateUsViewModel2;
        if (rateUsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rateusViewModel");
            rateUsViewModel2 = null;
        }
        rateUsViewModel2.getRatingInfo().observe(this, this);
        new LinearLayoutManager(this).setOrientation(0);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setJustifyContent(3);
        flexboxLayoutManager.setAlignItems(2);
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setFlexWrap(1);
        ((RecyclerView) _$_findCachedViewById(R.id.rcv_rating)).setLayoutManager(flexboxLayoutManager);
        ((TextView) _$_findCachedViewById(R.id.tv_rate_us_on_playstore)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRateUs.y(ActivityRateUs.this, view);
            }
        });
        B();
        RateUsViewModel rateUsViewModel3 = this.p;
        if (rateUsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rateusViewModel");
        } else {
            rateUsViewModel = rateUsViewModel3;
        }
        rateUsViewModel.loadRatingInfoFromServer();
        ((Button) _$_findCachedViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRateUs.z(ActivityRateUs.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.adapters.AdapterRatingInfo.RatingSelectionListener
    public void onRatingSelected(@NotNull RatingInfo ratingInfo) {
        Intrinsics.checkNotNullParameter(ratingInfo, "ratingInfo");
        ((Button) _$_findCachedViewById(R.id.submit)).setEnabled(true);
    }

    public final void setAdapterRatingInfo(@NotNull AdapterRatingInfo adapterRatingInfo) {
        Intrinsics.checkNotNullParameter(adapterRatingInfo, "<set-?>");
        this.adapterRatingInfo = adapterRatingInfo;
    }

    public final void setPreviousScreenName(@Nullable String str) {
        this.q = str;
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.RateUsViewModel.ApiResultListener
    public void showFailureToast() {
        dismissProgress();
        String string = getString(R.string.some_thing_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
        ExtensionFuncsKt.toastShort(this, string);
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.RateUsViewModel.ApiResultListener
    public void showFeedbackDialog() {
        dismissProgress();
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.DialogTheme);
        bottomSheetDialog.setContentView(R.layout.feedback_bottom_sheet_dialog);
        final EditText editText = (EditText) bottomSheetDialog.findViewById(R.id.edt_feedback);
        final TextView textView = (TextView) bottomSheetDialog.findViewById(R.id.tv_character_count);
        final Button button = (Button) bottomSheetDialog.findViewById(R.id.negative_btn);
        Button button2 = (Button) bottomSheetDialog.findViewById(R.id.positive_btn);
        ImageView imageView = (ImageView) bottomSheetDialog.findViewById(R.id.close_image);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityRateUs.D(button, view);
                }
            });
        }
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.rateus.ActivityRateUs$showFeedbackDialog$2
                @Override // android.text.TextWatcher
                public void afterTextChanged(@Nullable Editable editable) {
                    TextView textView2 = textView;
                    if (textView2 == null) {
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(editable != null ? Integer.valueOf(editable.length()) : null);
                    sb.append("/500");
                    textView2.setText(sb.toString());
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                }
            });
        }
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityRateUs.E(BottomSheetDialog.this, this, view);
                }
            });
        }
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityRateUs.F(editText, this, view);
                }
            });
        }
        bottomSheetDialog.show();
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.RateUsViewModel.ApiResultListener
    public void showFeedbackSubmitSuccess() {
        A();
        Intrinsics.checkNotNull(this);
        String string = getResources().getString(R.string.feedback_submitted);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.feedback_submitted)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …R.string.ok\n            )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRateUs.G(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.RateUsViewModel.ApiResultListener
    public void showPlayStoreReviewDialog() {
        A();
        String str = this.q;
        if (str == null || str.length() == 0) {
            this.q = ReviewAndRateUsConstants.PROFILE.getValue();
        }
        Bundle bundle = new Bundle();
        bundle.putString("cv_prev_screen_name", this.q);
        PlayStoreReviewDialogFragment playStoreReviewDialogFragment = new PlayStoreReviewDialogFragment();
        playStoreReviewDialogFragment.setArguments(bundle);
        playStoreReviewDialogFragment.show(getSupportFragmentManager(), ActivityRateUs.class.getSimpleName());
    }

    public final void showRatingSubmitSuccess() {
        Intrinsics.checkNotNull(this);
        String string = getResources().getString(R.string.rating_submitted);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.rating_submitted)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …    R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRateUs.H(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<RatingInfo> arrayList) {
        if (arrayList != null) {
            setAdapterRatingInfo(new AdapterRatingInfo(arrayList, this));
            ((RecyclerView) _$_findCachedViewById(R.id.rcv_rating)).setAdapter(getAdapterRatingInfo());
        }
    }
}
