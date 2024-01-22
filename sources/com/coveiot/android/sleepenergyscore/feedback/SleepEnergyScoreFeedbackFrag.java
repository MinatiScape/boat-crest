package com.coveiot.android.sleepenergyscore.feedback;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sleepenergyscore.FitnessComputedDataApiCall;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.QuestionsOptionData;
import com.coveiot.android.sleepenergyscore.feedback.SleepScoreFeedbackRecyclAdap;
import com.coveiot.android.sleepenergyscore.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.utils.CoveEventBusManager;
import com.squareup.otto.Subscribe;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SleepEnergyScoreFeedbackFrag extends BaseFragment implements SleepScoreFeedbackRecyclAdap.onItemClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FeedbackQuetionnarieModel m;
    public SleepEnergyScoreFeedbackViewModel n;
    public int o;
    public SleepScoreFeedbackRecyclAdap sleepScoreFeedbackRecyclAdap;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SleepEnergyScoreFeedbackFrag newInstance(@NotNull FeedbackQuetionnarieModel feedbackQuetionnarieModelData) {
            Intrinsics.checkNotNullParameter(feedbackQuetionnarieModelData, "feedbackQuetionnarieModelData");
            SleepEnergyScoreFeedbackFrag sleepEnergyScoreFeedbackFrag = new SleepEnergyScoreFeedbackFrag();
            sleepEnergyScoreFeedbackFrag.setFeedbackQuetionnarieModel(feedbackQuetionnarieModelData);
            return sleepEnergyScoreFeedbackFrag;
        }
    }

    /* loaded from: classes6.dex */
    public final class MarginItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        public final int f5734a;

        public MarginItemDecoration(SleepEnergyScoreFeedbackFrag sleepEnergyScoreFeedbackFrag, int i) {
            this.f5734a = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(outRect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            int i = this.f5734a;
            outRect.top = i;
            outRect.left = i;
            outRect.right = i;
            outRect.bottom = i;
        }
    }

    public static final void l(SleepEnergyScoreFeedbackFrag this$0, FeedbackQuetionnarieModel feedbackQuetionnarieModel, String optionId, String userInput) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(feedbackQuetionnarieModel, "$feedbackQuetionnarieModel");
        Intrinsics.checkNotNullParameter(optionId, "$optionId");
        Intrinsics.checkNotNullParameter(userInput, "$userInput");
        if (this$0.isAdded()) {
            FitnessComputedDataApiCall fitnessComputedDataApiCall = FitnessComputedDataApiCall.INSTANCE;
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String subject = feedbackQuetionnarieModel.getSubject();
            Intrinsics.checkNotNullExpressionValue(subject, "feedbackQuetionnarieModel.subject");
            SleepEnergyScoreFeedbackViewModel sleepEnergyScoreFeedbackViewModel = this$0.n;
            if (sleepEnergyScoreFeedbackViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                sleepEnergyScoreFeedbackViewModel = null;
            }
            fitnessComputedDataApiCall.callSaveApi(requireContext, subject, sleepEnergyScoreFeedbackViewModel.createRequestObject(optionId, userInput, feedbackQuetionnarieModel));
        }
    }

    @JvmStatic
    @NotNull
    public static final SleepEnergyScoreFeedbackFrag newInstance(@NotNull FeedbackQuetionnarieModel feedbackQuetionnarieModel) {
        return Companion.newInstance(feedbackQuetionnarieModel);
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

    @Nullable
    public final FeedbackQuetionnarieModel getFeedbackQuetionnarieModel() {
        return this.m;
    }

    public final int getIndex() {
        return this.o;
    }

    @NotNull
    public final SleepScoreFeedbackRecyclAdap getSleepScoreFeedbackRecyclAdap() {
        SleepScoreFeedbackRecyclAdap sleepScoreFeedbackRecyclAdap = this.sleepScoreFeedbackRecyclAdap;
        if (sleepScoreFeedbackRecyclAdap != null) {
            return sleepScoreFeedbackRecyclAdap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sleepScoreFeedbackRecyclAdap");
        return null;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.sleep_score_feedback_frag3, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onDismissLoader(@NotNull DismissLoader dimissLoader) {
        Intrinsics.checkNotNullParameter(dimissLoader, "dimissLoader");
        dismissProgress();
    }

    @Override // com.coveiot.android.sleepenergyscore.feedback.SleepScoreFeedbackRecyclAdap.onItemClickListener
    public void onItemClick(int i, @NotNull final String optionId, @NotNull final String userInput) {
        final FeedbackQuetionnarieModel feedbackQuetionnarieModel;
        Intrinsics.checkNotNullParameter(optionId, "optionId");
        Intrinsics.checkNotNullParameter(userInput, "userInput");
        if (!isAdded() || (feedbackQuetionnarieModel = this.m) == null) {
            return;
        }
        BaseFragment.showProgress$default(this, false, 1, null);
        this.o = i;
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.feedback.a
            @Override // java.lang.Runnable
            public final void run() {
                SleepEnergyScoreFeedbackFrag.l(SleepEnergyScoreFeedbackFrag.this, feedbackQuetionnarieModel, optionId, userInput);
            }
        }, 3000L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        dismissProgress();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(SleepEnergyScoreFeedbackViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        this.n = (SleepEnergyScoreFeedbackViewModel) viewModel;
        FeedbackQuetionnarieModel feedbackQuetionnarieModel = this.m;
        if (feedbackQuetionnarieModel != null) {
            ((TextView) _$_findCachedViewById(R.id.question_txt)).setText(feedbackQuetionnarieModel.getText());
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            List<QuestionsOptionData> options = feedbackQuetionnarieModel.getOptions();
            Intrinsics.checkNotNullExpressionValue(options, "feedbackQuetionnarieModel.options");
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            setSleepScoreFeedbackRecyclAdap(new SleepScoreFeedbackRecyclAdap(requireActivity, options, requireActivity2));
            getSleepScoreFeedbackRecyclAdap().setOnWatchFaceClickListener(this);
            int i = R.id.recycler_view;
            ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
            ((RecyclerView) _$_findCachedViewById(i)).setAdapter(getSleepScoreFeedbackRecyclAdap());
            ((RecyclerView) _$_findCachedViewById(i)).addItemDecoration(new MarginItemDecoration(this, 20));
        }
    }

    public final void setFeedbackQuetionnarieModel(@Nullable FeedbackQuetionnarieModel feedbackQuetionnarieModel) {
        this.m = feedbackQuetionnarieModel;
    }

    public final void setIndex(int i) {
        this.o = i;
    }

    public final void setSleepScoreFeedbackRecyclAdap(@NotNull SleepScoreFeedbackRecyclAdap sleepScoreFeedbackRecyclAdap) {
        Intrinsics.checkNotNullParameter(sleepScoreFeedbackRecyclAdap, "<set-?>");
        this.sleepScoreFeedbackRecyclAdap = sleepScoreFeedbackRecyclAdap;
    }
}
