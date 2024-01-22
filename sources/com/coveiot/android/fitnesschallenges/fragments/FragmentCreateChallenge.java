package com.coveiot.android.fitnesschallenges.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.AddParticipantsActivity;
import com.coveiot.android.fitnesschallenges.FitnessChallengeDetails;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.ViewAllParticipantsActivity;
import com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter;
import com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleAndMessageWatchFace;
import com.coveiot.android.theme.BottomSheetDialogSuccessImageTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeReq;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import com.google.gson.Gson;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentCreateChallenge extends BaseFragment implements CreateChallengeImageAdapter.OnBackGroundSelectedListener, SuccessResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public boolean A;
    @Nullable
    public BuddiesChallengeDetail C;
    public CreateChallengeViewModel D;
    public int G;
    @Nullable
    public String H;
    @Nullable
    public String I;
    @Nullable
    public BottomSheetDialogSuccessImageTitleMessage J;
    @Nullable
    public BottomSheetDialogImageTitleAndMessageWatchFace M;
    public int N;
    public FragmentCreateChallengeBinding m;
    public RecyclerView n;
    public CreateChallengeImageAdapter o;
    public boolean p;
    public int q;
    public int r;
    public long s;
    public long t;
    public long u;
    public long v;
    public int w;
    public int x;
    public boolean y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int z = 123;
    @NotNull
    public Handler B = new Handler();
    @NotNull
    public List<CreateFitnessChallengeReq.Participant> E = new ArrayList();
    @NotNull
    public ArrayList<CoveContact> F = new ArrayList<>();
    @NotNull
    public final FragmentCreateChallenge$textWatcher$1 K = new TextWatcher() { // from class: com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge$textWatcher$1
        @Override // android.text.TextWatcher
        public void afterTextChanged(@Nullable Editable editable) {
            FragmentCreateChallenge.this.checkIsFilledAllData();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            FragmentCreateChallengeBinding F;
            F = FragmentCreateChallenge.this.F();
            TextView textView = F.tvEditChallengeNameCount;
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
            sb.append("/30");
            textView.setText(sb.toString());
        }
    };
    @NotNull
    public final FragmentCreateChallenge$textWatcherDesc$1 L = new TextWatcher() { // from class: com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge$textWatcherDesc$1
        @Override // android.text.TextWatcher
        public void afterTextChanged(@Nullable Editable editable) {
            FragmentCreateChallenge.this.checkIsFilledAllData();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            FragmentCreateChallengeBinding F;
            F = FragmentCreateChallenge.this.F();
            TextView textView = F.tvChallengeDescCount;
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
            sb.append("/100");
            textView.setText(sb.toString());
        }
    };

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentCreateChallenge newInstance(boolean z, @Nullable BuddiesChallengeDetail buddiesChallengeDetail) {
            FragmentCreateChallenge fragmentCreateChallenge = new FragmentCreateChallenge();
            fragmentCreateChallenge.A = z;
            fragmentCreateChallenge.C = buddiesChallengeDetail;
            return fragmentCreateChallenge;
        }
    }

    public static final void H(FragmentCreateChallenge this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = z;
    }

    public static final void I(FragmentCreateChallenge this$0, View view) {
        BuddiesChallengeDetail buddiesChallengeDetail;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            if (AppUtils.isNetConnected(this$0.getContext())) {
                this$0.E();
                Intent intent = new Intent(this$0.getActivity(), AddParticipantsActivity.class);
                intent.putExtra("selectedList", this$0.F);
                intent.putExtra("createChallengeReq", this$0.E());
                if (this$0.A && (buddiesChallengeDetail = this$0.C) != null) {
                    Intrinsics.checkNotNull(buddiesChallengeDetail);
                    intent.putExtra("challengeId", buddiesChallengeDetail.getChallengeId().toString());
                    intent.putExtra(FitnessChallengeConstants.CHALLENGE_ADD_PARTICIPANT_TYPE, 1);
                } else {
                    intent.putExtra(FitnessChallengeConstants.CHALLENGE_ADD_PARTICIPANT_TYPE, 0);
                }
                this$0.startActivityForResult(intent, this$0.z);
                return;
            }
            String string = this$0.getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ease_check_your_internet)");
            this$0.n0(string);
        }
    }

    public static final void J(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            int i = R.id.btnCreateChallenge;
            ((Button) this$0._$_findCachedViewById(i)).setClickable(false);
            if (AppUtils.isNetConnected(this$0.getContext())) {
                this$0.showProgress(false);
                CreateChallengeViewModel createChallengeViewModel = null;
                if (this$0.A) {
                    if (this$0.C != null) {
                        CreateChallengeViewModel createChallengeViewModel2 = this$0.D;
                        if (createChallengeViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        } else {
                            createChallengeViewModel = createChallengeViewModel2;
                        }
                        BuddiesChallengeDetail buddiesChallengeDetail = this$0.C;
                        Intrinsics.checkNotNull(buddiesChallengeDetail);
                        createChallengeViewModel.editFitnessChallenge(buddiesChallengeDetail.getChallengeId().toString(), this$0.E());
                        return;
                    }
                    return;
                }
                CreateChallengeViewModel createChallengeViewModel3 = this$0.D;
                if (createChallengeViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    createChallengeViewModel = createChallengeViewModel3;
                }
                createChallengeViewModel.createFitnessChallenge(this$0.E());
                return;
            }
            ((Button) this$0._$_findCachedViewById(i)).setClickable(true);
            String string = this$0.getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ease_check_your_internet)");
            this$0.n0(string);
        }
    }

    public static final void K(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            this$0.F().rbCalorieGoal.setChecked(false);
            this$0.F().rbGoalDistance.setChecked(true);
            if (this$0.F().rbGoalDistance.isChecked()) {
                this$0.F().pickerCalorie.setText("");
            }
            this$0.m0();
        }
    }

    public static final void L(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            this$0.F().rbCalorieGoal.setChecked(true);
            this$0.F().rbGoalDistance.setChecked(false);
            if (this$0.F().rbCalorieGoal.isChecked()) {
                this$0.F().pickerDistance.setText("");
            }
            this$0.h0();
        }
    }

    public static final void P() {
    }

    public static final void R(FragmentCreateChallenge this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkIsFilledAllData();
        if (z) {
            this$0.N();
            this$0.F().rbCalorieGoal.setChecked(false);
            this$0.F().rbGoalDistance.setChecked(true);
            if (this$0.F().rbGoalDistance.isChecked()) {
                this$0.F().pickerCalorie.setText("");
            }
        }
    }

    public static final void S(FragmentCreateChallenge this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkIsFilledAllData();
        if (z) {
            this$0.M();
            this$0.F().rbCalorieGoal.setChecked(true);
            this$0.F().rbGoalDistance.setChecked(false);
            if (this$0.F().rbCalorieGoal.isChecked()) {
                this$0.F().pickerDistance.setText("");
            }
        }
    }

    public static final void U(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.getContext(), ViewAllParticipantsActivity.class);
        FitnessChallengeConstants.Companion companion = FitnessChallengeConstants.Companion;
        intent.putExtra(companion.getPARTICIPANTS_LIST(), this$0.F);
        intent.putExtra(companion.getIS_CREATED_CHALLENGE(), true);
        this$0.startActivity(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ad A[LOOP:0: B:19:0x0061->B:31:0x00ad, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0083 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void W(com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge r9, java.util.List r10) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = 1
            r1 = 0
            if (r10 == 0) goto L12
            boolean r2 = r10.isEmpty()
            if (r2 == 0) goto L10
            goto L12
        L10:
            r2 = r1
            goto L13
        L12:
            r2 = r0
        L13:
            if (r2 != 0) goto Lea
            boolean r2 = r9.A
            java.lang.String r3 = "imageAdapter"
            java.lang.String r4 = "it"
            java.lang.String r5 = "requireContext()"
            java.lang.String r6 = "it[0].bannerImageId"
            r7 = 0
            if (r2 == 0) goto Lb0
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r2 = r9.C
            if (r2 == 0) goto Lb0
            com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter r2 = new com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter
            android.content.Context r8 = r9.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r4)
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r4 = r9.C
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r2.<init>(r8, r10, r9, r4)
            r9.o = r2
            java.lang.Object r2 = r10.get(r1)
            com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes$Item r2 = (com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes.Item) r2
            java.lang.Integer r2 = r2.getBannerImageId()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            int r2 = r2.intValue()
            r9.N = r2
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r2 = r9.C
            if (r2 == 0) goto Ld4
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.Integer r2 = r2.getBannerImageId()
            if (r2 == 0) goto Ld4
            int r2 = r10.size()
            r4 = r1
        L61:
            if (r4 >= r2) goto Ld4
            java.lang.Object r5 = r10.get(r4)
            com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes$Item r5 = (com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes.Item) r5
            java.lang.Integer r5 = r5.getBannerImageId()
            if (r5 == 0) goto L80
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r6 = r9.C
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.Integer r6 = r6.getBannerImageId()
            boolean r5 = r5.equals(r6)
            if (r5 != r0) goto L80
            r5 = r0
            goto L81
        L80:
            r5 = r1
        L81:
            if (r5 == 0) goto Lad
            com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter r0 = r9.o
            if (r0 != 0) goto L8b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r0 = r7
        L8b:
            r0.setImageSelectedPosition(r4)
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r0 = r9.F()
            androidx.recyclerview.widget.RecyclerView r0 = r0.imageRecyclerView
            r0.scrollToPosition(r4)
            java.lang.Object r10 = r10.get(r4)
            com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes$Item r10 = (com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes.Item) r10
            java.lang.Integer r10 = r10.getBannerImageId()
            java.lang.String r0 = "it[i].bannerImageId"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)
            int r10 = r10.intValue()
            r9.N = r10
            goto Ld4
        Lad:
            int r4 = r4 + 1
            goto L61
        Lb0:
            java.lang.Object r0 = r10.get(r1)
            com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes$Item r0 = (com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes.Item) r0
            java.lang.Integer r0 = r0.getBannerImageId()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r6)
            int r0 = r0.intValue()
            r9.N = r0
            com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter r0 = new com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter
            android.content.Context r1 = r9.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r4)
            r0.<init>(r1, r10, r9, r7)
            r9.o = r0
        Ld4:
            androidx.recyclerview.widget.RecyclerView r10 = r9.n
            if (r10 != 0) goto Lde
            java.lang.String r10 = "recyclerView"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r10 = r7
        Lde:
            com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter r9 = r9.o
            if (r9 != 0) goto Le6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto Le7
        Le6:
            r7 = r9
        Le7:
            r10.setAdapter(r7)
        Lea:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge.W(com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge, java.util.List):void");
    }

    public static final void Y(final FragmentCreateChallenge this$0, final Ref.ObjectRef selectedStartDate, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(selectedStartDate, "$selectedStartDate");
        if (this$0.isAdded()) {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.x
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    FragmentCreateChallenge.Z(FragmentCreateChallenge.this, selectedStartDate, datePicker, i, i2, i3);
                }
            }, calendar.get(1), calendar.get(2), calendar.get(5));
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Calendar, T] */
    public static final void Z(FragmentCreateChallenge this$0, Ref.ObjectRef selectedStartDate, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(selectedStartDate, "$selectedStartDate");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i, i2, i3);
        this$0.F().tvEndDate.setText("");
        ?? calendar = Calendar.getInstance();
        selectedStartDate.element = calendar;
        Calendar calendar2 = (Calendar) calendar;
        if (calendar2 != null) {
            calendar2.set(i, i2, i3);
        }
        TextView textView = this$0.F().tvStartDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
        this$0.H = ExtensionsKt.toFormattedDateStr(startDate, "YYYY-MM-dd");
        this$0.checkIsFilledAllData();
    }

    public static final void a0(final FragmentCreateChallenge this$0, Ref.ObjectRef selectedStartDate, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(selectedStartDate, "$selectedStartDate");
        if (this$0.isAdded()) {
            CharSequence text = this$0.F().tvStartDate.getText();
            if (!(text == null || text.length() == 0)) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.m
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        FragmentCreateChallenge.b0(FragmentCreateChallenge.this, datePicker, i, i2, i3);
                    }
                }, calendar.get(1), calendar.get(2), calendar.get(5));
                if (selectedStartDate.element != 0) {
                    Calendar calendar2 = Calendar.getInstance();
                    T t = selectedStartDate.element;
                    Intrinsics.checkNotNull(t);
                    calendar2.setTimeInMillis(((Calendar) t).getTimeInMillis());
                    calendar2.add(5, this$0.q);
                    datePickerDialog.getDatePicker().setMinDate(calendar2.getTimeInMillis());
                    Calendar calendar3 = Calendar.getInstance();
                    T t2 = selectedStartDate.element;
                    Intrinsics.checkNotNull(t2);
                    calendar3.setTimeInMillis(((Calendar) t2).getTimeInMillis());
                    calendar3.add(5, this$0.r);
                    datePickerDialog.getDatePicker().setMinDate(calendar2.getTimeInMillis());
                    datePickerDialog.getDatePicker().setMaxDate(calendar3.getTimeInMillis());
                } else {
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                }
                datePickerDialog.show();
                return;
            }
            Toast.makeText(this$0.getContext(), this$0.requireContext().getString(R.string.please_enter_start_date), 0).show();
        }
    }

    public static final void b0(FragmentCreateChallenge this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i, i2, i3);
        TextView textView = this$0.F().tvEndDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
        this$0.I = ExtensionsKt.toFormattedDateStr(startDate, "YYYY-MM-dd");
        this$0.checkIsFilledAllData();
    }

    public static final void d0(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressedCheck();
    }

    public static final void e0(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.M;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
        CreateChallengeViewModel createChallengeViewModel = null;
        if (!this$0.A || this$0.C == null) {
            CreateChallengeViewModel createChallengeViewModel2 = this$0.D;
            if (createChallengeViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                createChallengeViewModel = createChallengeViewModel2;
            }
            createChallengeViewModel.createFitnessChallenge(this$0.E());
            return;
        }
        CreateChallengeViewModel createChallengeViewModel3 = this$0.D;
        if (createChallengeViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            createChallengeViewModel = createChallengeViewModel3;
        }
        BuddiesChallengeDetail buddiesChallengeDetail = this$0.C;
        Intrinsics.checkNotNull(buddiesChallengeDetail);
        createChallengeViewModel.editFitnessChallenge(buddiesChallengeDetail.getChallengeId().toString(), this$0.E());
    }

    public static final void f0(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.M;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
        this$0.requireActivity().onBackPressed();
    }

    public static final void g0(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.M;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
    }

    public static final void j0(FragmentCreateChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage = this$0.J;
        if (bottomSheetDialogSuccessImageTitleMessage != null) {
            bottomSheetDialogSuccessImageTitleMessage.dismiss();
        }
        if (this$0.A) {
            Intent intent = new Intent();
            intent.putExtra(FitnessChallengeConstants.CHALLENGE_SUCCESS, true);
            this$0.requireActivity().setResult(-1, intent);
            this$0.requireActivity().finish();
            return;
        }
        this$0.requireActivity().onBackPressed();
    }

    public static final void l0(FragmentCreateChallenge this$0, CommonMessageDialog commonMessageDialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        ((Button) this$0._$_findCachedViewById(R.id.btnCreateChallenge)).setClickable(true);
        commonMessageDialog.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentCreateChallenge newInstance(boolean z, @Nullable BuddiesChallengeDetail buddiesChallengeDetail) {
        return Companion.newInstance(z, buddiesChallengeDetail);
    }

    public final CreateFitnessChallengeReq E() {
        CreateFitnessChallengeReq createFitnessChallengeReq = new CreateFitnessChallengeReq();
        createFitnessChallengeReq.setName(F().editChallengeName.getText().toString());
        createFitnessChallengeReq.setDescription(F().editDesc.getText().toString());
        if (F().rbGoalDistance.isChecked()) {
            createFitnessChallengeReq.setTarget(Integer.valueOf(this.G * 1000));
            createFitnessChallengeReq.setTargetBaseUnits(FitnessChallengeConstants.METERS);
        } else {
            createFitnessChallengeReq.setTarget(Integer.valueOf(this.G));
            createFitnessChallengeReq.setTargetBaseUnits(FitnessChallengeConstants.CALORIES);
        }
        createFitnessChallengeReq.setTransitive(Boolean.valueOf(F().cbAgreeTerms.isChecked()));
        createFitnessChallengeReq.setStartDate(this.H);
        createFitnessChallengeReq.setEndDate(this.I);
        createFitnessChallengeReq.setBannerImageId(Integer.valueOf(this.N));
        createFitnessChallengeReq.setParticipants(this.E);
        Log.d("challengeCheck", "createNewBuddiesChallenge:" + new Gson().toJson(createFitnessChallengeReq));
        return createFitnessChallengeReq;
    }

    public final FragmentCreateChallengeBinding F() {
        FragmentCreateChallengeBinding fragmentCreateChallengeBinding = this.m;
        if (fragmentCreateChallengeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentCreateChallengeBinding;
    }

    public final void G() {
        HashMap<String, Object> hashMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
        CreateChallengeViewModel createChallengeViewModel = this.D;
        if (createChallengeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            createChallengeViewModel = null;
        }
        if (createChallengeViewModel.getChallengeId() != null) {
            String value = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_ID.getValue();
            CreateChallengeViewModel createChallengeViewModel2 = this.D;
            if (createChallengeViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                createChallengeViewModel2 = null;
            }
            Object challengeId = createChallengeViewModel2.getChallengeId();
            Intrinsics.checkNotNull(challengeId);
            hashMap.put(value, challengeId);
        }
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_NAME.getValue(), F().editChallengeName.getText().toString());
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_DESCRIPTION.getValue(), F().editDesc.getText().toString());
        List<CreateFitnessChallengeReq.Participant> list = this.E;
        boolean z = true;
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT.getValue(), Integer.valueOf(!(list == null || list.isEmpty()) ? this.E.size() : 0));
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CUSTOM.getValue());
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SELF.getValue());
        if (F().rbGoalDistance.isChecked()) {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), Integer.valueOf(this.G));
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        } else {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), Integer.valueOf(this.G));
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        }
        String str = this.H;
        if (!(str == null || str.length() == 0)) {
            String str2 = this.H;
            Date parse = str2 != null ? simpleDateFormat.parse(str2) : null;
            Intrinsics.checkNotNull(parse, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_START_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse, true));
        }
        String str3 = this.I;
        if (str3 != null && str3.length() != 0) {
            z = false;
        }
        if (!z) {
            String str4 = this.I;
            Date parse2 = str4 != null ? simpleDateFormat.parse(str4) : null;
            Intrinsics.checkNotNull(parse2, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_END_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse2, false));
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_CHALLENGE_CREATE_CHALLENGE_SUCCESS.getValue(), hashMap);
    }

    public final void M() {
        TextView textView = F().pickerCalorie;
        textView.setText(this.u + ' ' + requireContext().getString(R.string.cal_unit));
        this.G = (int) this.u;
    }

    public final void N() {
        TextView textView = F().pickerDistance;
        textView.setText(this.s + ' ' + requireContext().getString(R.string.km_unit));
        this.G = (int) this.s;
    }

    public final void O(String str, int i) {
        ImageView imageView;
        Context context = getContext();
        if (i == 0) {
            imageView = F().ivTopParticipant1;
        } else if (i != 1) {
            imageView = F().ivTopParticipant3;
        } else {
            imageView = F().ivTopParticipant2;
        }
        GlideUtils.loadCircularImage(context, str, imageView, new ImageLodeListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.v
            @Override // com.coveiot.utils.utility.ImageLodeListener
            public final void onImageLoaded() {
                FragmentCreateChallenge.P();
            }
        });
    }

    public final void Q() {
        if (!this.A) {
            N();
        }
        F().rbGoalDistance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.s
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FragmentCreateChallenge.R(FragmentCreateChallenge.this, compoundButton, z);
            }
        });
        F().rbCalorieGoal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.t
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FragmentCreateChallenge.S(FragmentCreateChallenge.this, compoundButton, z);
            }
        });
    }

    public final void T(ArrayList<CoveContact> arrayList) {
        ConstraintLayout constraintLayout = F().clTopParticipantLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clTopParticipantLayout");
        visible(constraintLayout);
        TextView textView = F().totalParticipantsTv;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.totalParticipantsTv");
        gone(textView);
        if (this.F.size() > 0) {
            ImageView imageView = F().ivTopParticipant1;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivTopParticipant1");
            visible(imageView);
            if (arrayList.get(0).getDpUrl() != null) {
                String dpUrl = arrayList.get(0).getDpUrl();
                Intrinsics.checkNotNullExpressionValue(dpUrl, "contact[0].dpUrl");
                O(dpUrl, 0);
            }
            if (this.F.size() > 1) {
                ImageView imageView2 = F().ivTopParticipant2;
                Intrinsics.checkNotNullExpressionValue(imageView2, "binding.ivTopParticipant2");
                visible(imageView2);
                if (arrayList.get(1).getDpUrl() != null) {
                    String dpUrl2 = arrayList.get(1).getDpUrl();
                    Intrinsics.checkNotNullExpressionValue(dpUrl2, "contact[1].dpUrl");
                    O(dpUrl2, 1);
                }
                if (this.F.size() > 2) {
                    ImageView imageView3 = F().ivTopParticipant3;
                    Intrinsics.checkNotNullExpressionValue(imageView3, "binding.ivTopParticipant3");
                    visible(imageView3);
                    if (arrayList.get(2).getDpUrl() != null) {
                        String dpUrl3 = arrayList.get(2).getDpUrl();
                        Intrinsics.checkNotNullExpressionValue(dpUrl3, "contact[2].dpUrl");
                        O(dpUrl3, 2);
                    }
                }
            }
        }
        if (this.F.size() > 3) {
            TextView textView2 = F().totalParticipantsTv;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.totalParticipantsTv");
            visible(textView2);
            TextView textView3 = F().totalParticipantsTv;
            textView3.setText("+ " + (this.F.size() - 3));
        }
        F().tvViewAllParticipants.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCreateChallenge.U(FragmentCreateChallenge.this, view);
            }
        });
    }

    public final void V() {
        RecyclerView recyclerView = F().imageRecyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.imageRecyclerView");
        this.n = recyclerView;
        CreateChallengeViewModel createChallengeViewModel = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        CreateChallengeViewModel createChallengeViewModel2 = this.D;
        if (createChallengeViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            createChallengeViewModel = createChallengeViewModel2;
        }
        createChallengeViewModel.getCreateChallengeBannerList().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.u
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentCreateChallenge.W(FragmentCreateChallenge.this, (List) obj);
            }
        });
    }

    public final void X() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        checkIsFilledAllData();
        F().tvStartDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCreateChallenge.Y(FragmentCreateChallenge.this, objectRef, view);
            }
        });
        F().tvEndDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCreateChallenge.a0(FragmentCreateChallenge.this, objectRef, view);
            }
        });
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

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0063, code lost:
        if ((r0 == null || r0.length() == 0) != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        if ((r0 == null || r0.length() == 0) == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0083, code lost:
        if (r3.length() <= 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0085, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0087, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0088, code lost:
        if (r0 == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008e, code lost:
        if (r4.length() <= 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0090, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0092, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0093, code lost:
        if (r0 == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0095, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean areAllFieldsFilled() {
        /*
            r7 = this;
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r0 = r7.F()
            android.widget.EditText r0 = r0.editChallengeName
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r1 = r7.F()
            androidx.appcompat.widget.AppCompatRadioButton r1 = r1.rbGoalDistance
            boolean r1 = r1.isChecked()
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r2 = r7.F()
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.rbCalorieGoal
            boolean r2 = r2.isChecked()
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r3 = r7.F()
            android.widget.TextView r3 = r3.tvStartDate
            java.lang.CharSequence r3 = r3.getText()
            java.lang.String r3 = r3.toString()
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r4 = r7.F()
            android.widget.TextView r4 = r4.tvEndDate
            java.lang.CharSequence r4 = r4.getText()
            java.lang.String r4 = r4.toString()
            int r0 = r0.length()
            r5 = 1
            r6 = 0
            if (r0 <= 0) goto L48
            r0 = r5
            goto L49
        L48:
            r0 = r6
        L49:
            if (r0 == 0) goto L96
            if (r1 == 0) goto L65
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r0 = r7.F()
            android.widget.TextView r0 = r0.pickerDistance
            java.lang.CharSequence r0 = r0.getText()
            if (r0 == 0) goto L62
            int r0 = r0.length()
            if (r0 != 0) goto L60
            goto L62
        L60:
            r0 = r6
            goto L63
        L62:
            r0 = r5
        L63:
            if (r0 == 0) goto L7f
        L65:
            if (r2 == 0) goto L96
            com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBinding r0 = r7.F()
            android.widget.TextView r0 = r0.pickerCalorie
            java.lang.CharSequence r0 = r0.getText()
            if (r0 == 0) goto L7c
            int r0 = r0.length()
            if (r0 != 0) goto L7a
            goto L7c
        L7a:
            r0 = r6
            goto L7d
        L7c:
            r0 = r5
        L7d:
            if (r0 != 0) goto L96
        L7f:
            int r0 = r3.length()
            if (r0 <= 0) goto L87
            r0 = r5
            goto L88
        L87:
            r0 = r6
        L88:
            if (r0 == 0) goto L96
            int r0 = r4.length()
            if (r0 <= 0) goto L92
            r0 = r5
            goto L93
        L92:
            r0 = r6
        L93:
            if (r0 == 0) goto L96
            return r5
        L96:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge.areAllFieldsFilled():boolean");
    }

    public final void c0() {
        FragmentCreateChallengeBinding F = F();
        if (F != null) {
            ((TextView) F.toolbar.findViewById(R.id.toolbar_title)).setText(getString(!this.A ? R.string.create_challenge : R.string.edit_challenge));
            ((TextView) F.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.b0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentCreateChallenge.d0(FragmentCreateChallenge.this, view);
                }
            });
        }
    }

    public final void checkIsFilledAllData() {
        boolean areAllFieldsFilled = areAllFieldsFilled();
        F().btnAddParticipants.setEnabled(areAllFieldsFilled);
        F().btnCreateChallenge.setEnabled(areAllFieldsFilled);
        this.y = areAllFieldsFilled;
    }

    public final int getBannerImageId() {
        return this.N;
    }

    @Nullable
    public final BottomSheetDialogSuccessImageTitleMessage getChallengeSuccessDialog() {
        return this.J;
    }

    @Nullable
    public final BottomSheetDialogImageTitleAndMessageWatchFace getConfirmDialog() {
        return this.M;
    }

    public final void h0() {
        if (isAdded()) {
            checkIsFilledAllData();
            PickerDialog.Companion companion = PickerDialog.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.select_calorie);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_calorie)");
            String string2 = getString(R.string.cal_unit);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cal_unit)");
            companion.fitnessChallengeGoalPicker(requireContext, string, (int) this.u, (int) this.v, 100, string2, this.x, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge$showCaloriePickerDialog$1
                @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
                public void onValueSelected(@NotNull String value) {
                    FragmentCreateChallengeBinding F;
                    Intrinsics.checkNotNullParameter(value, "value");
                    F = FragmentCreateChallenge.this.F();
                    TextView textView = F.pickerCalorie;
                    textView.setText(value + ' ' + FragmentCreateChallenge.this.requireContext().getString(R.string.cal_unit));
                    FragmentCreateChallenge.this.G = Integer.parseInt(value);
                    FragmentCreateChallenge.this.checkIsFilledAllData();
                }
            });
        }
    }

    public final void i0(String str, String str2) {
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage;
        checkIsFilledAllData();
        if (this.J == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = getString(R.string.congratulations_your_challenge_is_set_best_of_luck_on_your_journey);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.congr…_of_luck_on_your_journey)");
            BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage2 = new BottomSheetDialogSuccessImageTitleMessage(requireActivity, str, string);
            this.J = bottomSheetDialogSuccessImageTitleMessage2;
            String string2 = getString(R.string.okay);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.okay)");
            bottomSheetDialogSuccessImageTitleMessage2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.f0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentCreateChallenge.j0(FragmentCreateChallenge.this, view);
                }
            });
        }
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage3 = this.J;
        Boolean valueOf = bottomSheetDialogSuccessImageTitleMessage3 != null ? Boolean.valueOf(bottomSheetDialogSuccessImageTitleMessage3.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue() && (bottomSheetDialogSuccessImageTitleMessage = this.J) != null) {
            bottomSheetDialogSuccessImageTitleMessage.show();
        }
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage4 = this.J;
        if (bottomSheetDialogSuccessImageTitleMessage4 != null) {
            bottomSheetDialogSuccessImageTitleMessage4.setCancelable(false);
        }
    }

    public final void k0(String str) {
        Window window;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(requireContext, str, false, true);
        commonMessageDialog.show(getChildFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        this.B.postDelayed(new Runnable() { // from class: com.coveiot.android.fitnesschallenges.fragments.w
            @Override // java.lang.Runnable
            public final void run() {
                FragmentCreateChallenge.l0(FragmentCreateChallenge.this, commonMessageDialog);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void m0() {
        if (isAdded()) {
            checkIsFilledAllData();
            PickerDialog.Companion companion = PickerDialog.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.select_distance);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_distance)");
            String string2 = getString(R.string.km_small);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.km_small)");
            companion.fitnessChallengeGoalPicker(requireContext, string, (int) this.s, (int) this.t, 5, string2, this.w, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge$showDistancePickerDialog$1
                @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
                public void onValueSelected(@NotNull String value) {
                    FragmentCreateChallengeBinding F;
                    Intrinsics.checkNotNullParameter(value, "value");
                    F = FragmentCreateChallenge.this.F();
                    TextView textView = F.pickerDistance;
                    textView.setText(value + ' ' + FragmentCreateChallenge.this.requireContext().getString(R.string.km));
                    FragmentCreateChallenge.this.G = Integer.parseInt(value);
                    FragmentCreateChallenge.this.checkIsFilledAllData();
                }
            });
        }
    }

    public final void n0(String str) {
        k0(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.z && i2 == -1) {
            Serializable serializableExtra = intent != null ? intent.getSerializableExtra(FitnessChallengeConstants.Companion.getSELECTED_CONTACTS()) : null;
            ArrayList arrayList = serializableExtra instanceof ArrayList ? (ArrayList) serializableExtra : null;
            if (arrayList != null) {
                this.E.clear();
                this.F.clear();
                ImageView imageView = F().ivTopParticipant1;
                Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivTopParticipant1");
                gone(imageView);
                ImageView imageView2 = F().ivTopParticipant2;
                Intrinsics.checkNotNullExpressionValue(imageView2, "binding.ivTopParticipant2");
                gone(imageView2);
                ImageView imageView3 = F().ivTopParticipant3;
                Intrinsics.checkNotNullExpressionValue(imageView3, "binding.ivTopParticipant3");
                gone(imageView3);
                TextView textView = F().totalParticipantsTv;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.totalParticipantsTv");
                gone(textView);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    CoveContact coveContact = (CoveContact) it.next();
                    if (!this.F.contains(coveContact)) {
                        this.F.add(coveContact);
                    }
                    this.E.add(new CreateFitnessChallengeReq.Participant(coveContact.getName(), coveContact.getPhoneNumber()));
                }
                ArrayList<CoveContact> arrayList2 = this.F;
                Intrinsics.checkNotNull(arrayList2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.utils.model.CoveContact>");
                T(arrayList2);
            }
        }
        boolean z = true;
        if (intent == null || !intent.getBooleanExtra(FitnessChallengeConstants.CHALLENGE_SUCCESS, false)) {
            z = false;
        }
        if (z) {
            requireActivity().finish();
        }
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.CreateChallengeImageAdapter.OnBackGroundSelectedListener
    public void onBackGroundSelected(@NotNull BannerImagesRes.Item bannerImagesRes) {
        Intrinsics.checkNotNullParameter(bannerImagesRes, "bannerImagesRes");
        if (bannerImagesRes.getBannerImageId() != null) {
            Integer bannerImageId = bannerImagesRes.getBannerImageId();
            Intrinsics.checkNotNullExpressionValue(bannerImageId, "bannerImagesRes.bannerImageId");
            this.N = bannerImageId.intValue();
        }
    }

    public final void onBackPressedCheck() {
        if (this.y) {
            String string = getString(R.string.if_you_go_back_now);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.if_you_go_back_now)");
            showBottomSheetDialog("", string, false);
        } else if (this.A) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.FitnessChallengeDetails");
            ((FitnessChallengeDetails) activity).onBackPressed();
        } else {
            requireActivity().finish();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentCreateChallengeBinding inflate = FragmentCreateChallengeBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, container, false)");
        this.m = inflate;
        return F().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        dismissProgress();
        if (!isAdded() || str == null) {
            return;
        }
        k0(str);
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        dismissProgress();
        if (isAdded()) {
            if (this.A) {
                String string = getResources().getString(R.string.challenge_edited_sucessfully);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…lenge_edited_sucessfully)");
                String string2 = getResources().getString(R.string.congratulations_your_challenge_is_set_best_of_luck_on_your_journey);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…_of_luck_on_your_journey)");
                i0(string, string2);
                return;
            }
            String string3 = getResources().getString(R.string.challenge_created_sucessfully);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…enge_created_sucessfully)");
            String string4 = getResources().getString(R.string.congratulations_your_challenge_is_set_best_of_luck_on_your_journey);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.st…_of_luck_on_your_journey)");
            i0(string3, string4);
            G();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        CreateChallengeViewModel createChallengeViewModel = new CreateChallengeViewModel(requireContext);
        this.D = createChallengeViewModel;
        createChallengeViewModel.setMListener(this);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        EditText editText = F().editChallengeName;
        Intrinsics.checkNotNullExpressionValue(editText, "binding.editChallengeName");
        themesUtils.disableCopyPasteOperations(editText);
        EditText editText2 = F().editDesc;
        Intrinsics.checkNotNullExpressionValue(editText2, "binding.editDesc");
        themesUtils.disableCopyPasteOperations(editText2);
        if (this.A) {
            F().btnCreateChallenge.setText(getString(R.string.save_challenges_changes));
            if (this.C != null) {
                EditText editText3 = F().editChallengeName;
                BuddiesChallengeDetail buddiesChallengeDetail = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail);
                editText3.setText(buddiesChallengeDetail.getName());
                EditText editText4 = F().editDesc;
                BuddiesChallengeDetail buddiesChallengeDetail2 = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail2);
                editText4.setText(buddiesChallengeDetail2.getDescription());
                BuddiesChallengeDetail buddiesChallengeDetail3 = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail3);
                if (buddiesChallengeDetail3.getTargetBaseUnits().equals(FitnessChallengeConstants.METERS)) {
                    F().rbGoalDistance.setChecked(true);
                    F().rbCalorieGoal.setChecked(false);
                    TextView textView = F().pickerDistance;
                    StringBuilder sb = new StringBuilder();
                    BuddiesChallengeDetail buddiesChallengeDetail4 = this.C;
                    Intrinsics.checkNotNull(buddiesChallengeDetail4);
                    sb.append(buddiesChallengeDetail4.getTarget().intValue() / 1000);
                    sb.append(' ');
                    sb.append(getString(R.string.km_unit));
                    textView.setText(sb.toString());
                    F().rbGoalDistance.setChecked(true);
                    F().rbCalorieGoal.setChecked(false);
                    BuddiesChallengeDetail buddiesChallengeDetail5 = this.C;
                    Intrinsics.checkNotNull(buddiesChallengeDetail5);
                    this.G = buddiesChallengeDetail5.getTarget().intValue() / 1000;
                } else {
                    F().rbGoalDistance.setChecked(false);
                    F().rbCalorieGoal.setChecked(true);
                    TextView textView2 = F().pickerCalorie;
                    StringBuilder sb2 = new StringBuilder();
                    BuddiesChallengeDetail buddiesChallengeDetail6 = this.C;
                    Intrinsics.checkNotNull(buddiesChallengeDetail6);
                    sb2.append(buddiesChallengeDetail6.getTarget());
                    sb2.append(' ');
                    sb2.append(getString(R.string.cal_unit));
                    textView2.setText(sb2.toString());
                    F().rbCalorieGoal.setChecked(true);
                    F().rbGoalDistance.setChecked(false);
                    BuddiesChallengeDetail buddiesChallengeDetail7 = this.C;
                    Intrinsics.checkNotNull(buddiesChallengeDetail7);
                    Integer target = buddiesChallengeDetail7.getTarget();
                    Intrinsics.checkNotNullExpressionValue(target, "buddiesChallengeDetail!!.target");
                    this.G = target.intValue();
                }
                TextView textView3 = F().tvStartDate;
                BuddiesChallengeDetail buddiesChallengeDetail8 = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail8);
                textView3.setText(String.valueOf(themesUtils.formattedDate(buddiesChallengeDetail8.getStartDate(), "dd/MM/YYYY")));
                TextView textView4 = F().tvEndDate;
                BuddiesChallengeDetail buddiesChallengeDetail9 = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail9);
                textView4.setText(String.valueOf(themesUtils.formattedDate(buddiesChallengeDetail9.getEndDate(), "dd/MM/YYYY")));
                BuddiesChallengeDetail buddiesChallengeDetail10 = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail10);
                if (buddiesChallengeDetail10.getTransitive() != null) {
                    CheckBox checkBox = F().cbAgreeTerms;
                    BuddiesChallengeDetail buddiesChallengeDetail11 = this.C;
                    Intrinsics.checkNotNull(buddiesChallengeDetail11);
                    Boolean transitive = buddiesChallengeDetail11.getTransitive();
                    Intrinsics.checkNotNullExpressionValue(transitive, "buddiesChallengeDetail!!.transitive");
                    checkBox.setChecked(transitive.booleanValue());
                }
                BuddiesChallengeDetail buddiesChallengeDetail12 = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail12);
                this.H = String.valueOf(themesUtils.formattedDate(buddiesChallengeDetail12.getStartDate(), "YYYY-MM-dd"));
                BuddiesChallengeDetail buddiesChallengeDetail13 = this.C;
                Intrinsics.checkNotNull(buddiesChallengeDetail13);
                this.I = String.valueOf(themesUtils.formattedDate(buddiesChallengeDetail13.getEndDate(), "YYYY-MM-dd"));
            }
        } else {
            F().btnCreateChallenge.setText(getString(R.string.create_challenge));
        }
        CreateChallengeViewModel createChallengeViewModel2 = this.D;
        if (createChallengeViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            createChallengeViewModel2 = null;
        }
        createChallengeViewModel2.getCreateChallengeBanners();
        FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfig = FitnessChallengeSessionManager.getInstance(getContext()).getFitnessChallengeRemoteConfig();
        Integer min = fitnessChallengeRemoteConfig.getFitness_challenges().getChallenge_duration_days().getMin();
        Intrinsics.checkNotNullExpressionValue(min, "fitnessChallengeConfig.f…allenge_duration_days.min");
        this.q = min.intValue();
        Integer max = fitnessChallengeRemoteConfig.getFitness_challenges().getChallenge_duration_days().getMax();
        Intrinsics.checkNotNullExpressionValue(max, "fitnessChallengeConfig.f…allenge_duration_days.max");
        this.r = max.intValue();
        this.u = fitnessChallengeRemoteConfig.getFitness_challenges().getCalories().getMin().intValue();
        this.v = fitnessChallengeRemoteConfig.getFitness_challenges().getCalories().getMax().intValue();
        this.s = fitnessChallengeRemoteConfig.getFitness_challenges().getDistance().getMin().intValue();
        this.t = fitnessChallengeRemoteConfig.getFitness_challenges().getDistance().getMax().intValue();
        Integer step = fitnessChallengeRemoteConfig.getFitness_challenges().getDistance().getStep();
        Intrinsics.checkNotNullExpressionValue(step, "fitnessChallengeConfig.f…_challenges.distance.step");
        this.w = step.intValue();
        Integer step2 = fitnessChallengeRemoteConfig.getFitness_challenges().getCalories().getStep();
        Intrinsics.checkNotNullExpressionValue(step2, "fitnessChallengeConfig.f…_challenges.calories.step");
        this.x = step2.intValue();
        c0();
        V();
        Q();
        X();
        F().editChallengeName.addTextChangedListener(this.K);
        F().editDesc.addTextChangedListener(this.L);
        F().cbAgreeTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.r
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FragmentCreateChallenge.H(FragmentCreateChallenge.this, compoundButton, z);
            }
        });
        F().btnAddParticipants.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCreateChallenge.I(FragmentCreateChallenge.this, view2);
            }
        });
        F().btnCreateChallenge.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCreateChallenge.J(FragmentCreateChallenge.this, view2);
            }
        });
        TextView textView5 = F().clInfo.tvInfoText2;
        Intrinsics.checkNotNullExpressionValue(textView5, "binding.clInfo.tvInfoText2");
        visible(textView5);
        ImageView imageView = F().clInfo.ivDot1;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.clInfo.ivDot1");
        visible(imageView);
        ImageView imageView2 = F().clInfo.ivDot2;
        Intrinsics.checkNotNullExpressionValue(imageView2, "binding.clInfo.ivDot2");
        visible(imageView2);
        TextView textView6 = F().clInfo.tvInfoText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.challenge_duration_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.challenge_duration_info)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{String.valueOf(this.q), String.valueOf(this.r)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView6.setText(format);
        F().clInfo.tvInfoText2.setText(getString(R.string.challenges_can_be_edited_or_deleted_at_your_discretion_before_the_start_date));
        F().editChallengeName.addTextChangedListener(this.K);
        F().editDesc.addTextChangedListener(this.L);
        F().pickerDistance.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCreateChallenge.K(FragmentCreateChallenge.this, view2);
            }
        });
        F().pickerCalorie.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCreateChallenge.L(FragmentCreateChallenge.this, view2);
            }
        });
    }

    public final void setBannerImageId(int i) {
        this.N = i;
    }

    public final void setChallengeSuccessDialog(@Nullable BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage) {
        this.J = bottomSheetDialogSuccessImageTitleMessage;
    }

    public final void setConfirmDialog(@Nullable BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace) {
        this.M = bottomSheetDialogImageTitleAndMessageWatchFace;
    }

    public final void showBottomSheetDialog(String str, String str2, boolean z) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = new BottomSheetDialogImageTitleAndMessageWatchFace(requireContext, str, str2);
        this.M = bottomSheetDialogImageTitleAndMessageWatchFace;
        if (!z) {
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
            String string = getString(R.string.save);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.save)");
            bottomSheetDialogImageTitleAndMessageWatchFace.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentCreateChallenge.e0(FragmentCreateChallenge.this, view);
                }
            });
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace2 = this.M;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace2);
            String string2 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cancel)");
            bottomSheetDialogImageTitleAndMessageWatchFace2.setNegativeButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.c0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentCreateChallenge.f0(FragmentCreateChallenge.this, view);
                }
            });
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace3 = this.M;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace3);
            bottomSheetDialogImageTitleAndMessageWatchFace3.show();
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace4 = this.M;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace4);
            bottomSheetDialogImageTitleAndMessageWatchFace4.setCancelable(false);
            return;
        }
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        String string3 = getString(R.string.done);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.done)");
        bottomSheetDialogImageTitleAndMessageWatchFace.setDoneButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCreateChallenge.g0(FragmentCreateChallenge.this, view);
            }
        });
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace5 = this.M;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace5);
        bottomSheetDialogImageTitleAndMessageWatchFace5.show();
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace6 = this.M;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace6);
        bottomSheetDialogImageTitleAndMessageWatchFace6.setCancelable(false);
    }
}
