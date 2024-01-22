package com.coveiot.android.leonardo.dashboard.feedback.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.feedback.FeedbackRecyclAdap;
import com.coveiot.android.leonardo.dashboard.feedback.viewmodel.FeedbackQuestionerViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.QuestionsOptionData;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.repository.datasync.domainlogic.APIResponseListner;
import com.coveiot.utils.CoveEventBusManager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FeedbackHolderFrag extends BaseFragment implements FeedbackRecyclAdap.onItemClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FeedbackRecyclAdap feedbackRecyclAdap;
    @Nullable
    public FeedbackQuetionnarieModel m;
    public FeedbackQuestionerViewModel n;
    public int o;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FeedbackHolderFrag newInstance(@NotNull FeedbackQuetionnarieModel feedbackQuetionnarieModelData) {
            Intrinsics.checkNotNullParameter(feedbackQuetionnarieModelData, "feedbackQuetionnarieModelData");
            FeedbackHolderFrag feedbackHolderFrag = new FeedbackHolderFrag();
            feedbackHolderFrag.setFeedbackQuetionnarieModel(feedbackQuetionnarieModelData);
            return feedbackHolderFrag;
        }
    }

    /* loaded from: classes2.dex */
    public final class MarginItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        public final int f4717a;

        public MarginItemDecoration(FeedbackHolderFrag feedbackHolderFrag, int i) {
            this.f4717a = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(outRect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            int i = this.f4717a;
            outRect.top = i;
            outRect.left = i;
            outRect.right = i;
            outRect.bottom = i;
        }
    }

    public static final void l(final FeedbackHolderFrag this$0, String optionId, String userInput, FeedbackQuetionnarieModel feedbackQuetionnarieModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(optionId, "$optionId");
        Intrinsics.checkNotNullParameter(userInput, "$userInput");
        Intrinsics.checkNotNullParameter(feedbackQuetionnarieModel, "$feedbackQuetionnarieModel");
        FeedbackQuestionerViewModel feedbackQuestionerViewModel = this$0.n;
        FeedbackQuestionerViewModel feedbackQuestionerViewModel2 = null;
        if (feedbackQuestionerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            feedbackQuestionerViewModel = null;
        }
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FeedbackQuestionerViewModel feedbackQuestionerViewModel3 = this$0.n;
        if (feedbackQuestionerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            feedbackQuestionerViewModel2 = feedbackQuestionerViewModel3;
        }
        String subject = feedbackQuetionnarieModel.getSubject();
        Intrinsics.checkNotNullExpressionValue(subject, "feedbackQuetionnarieModel.subject");
        SaveFeedbackQuestionarieRequest createRequestObject = feedbackQuestionerViewModel2.createRequestObject(optionId, userInput, feedbackQuetionnarieModel, subject);
        String subject2 = feedbackQuetionnarieModel.getSubject();
        Intrinsics.checkNotNullExpressionValue(subject2, "feedbackQuetionnarieModel.subject");
        feedbackQuestionerViewModel.saveFeedbackToServer(requireContext, createRequestObject, subject2, new APIResponseListner() { // from class: com.coveiot.android.leonardo.dashboard.feedback.fragment.FeedbackHolderFrag$onItemClick$1$1$1
            @Override // com.coveiot.repository.datasync.domainlogic.APIResponseListner
            public void onFailure(@Nullable String str) {
                FeedbackHolderFrag.this.dismissProgress();
            }

            @Override // com.coveiot.repository.datasync.domainlogic.APIResponseListner
            public void onSuccess(@Nullable Object obj) {
            }
        });
    }

    @JvmStatic
    @NotNull
    public static final FeedbackHolderFrag newInstance(@NotNull FeedbackQuetionnarieModel feedbackQuetionnarieModel) {
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

    @NotNull
    public final FeedbackRecyclAdap getFeedbackRecyclAdap() {
        FeedbackRecyclAdap feedbackRecyclAdap = this.feedbackRecyclAdap;
        if (feedbackRecyclAdap != null) {
            return feedbackRecyclAdap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("feedbackRecyclAdap");
        return null;
    }

    public final int getIndex() {
        return this.o;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.generic_feedback_frag3, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.dashboard.feedback.FeedbackRecyclAdap.onItemClickListener
    public void onItemClick(int i, @NotNull final String optionId, @NotNull final String userInput) {
        final FeedbackQuetionnarieModel feedbackQuetionnarieModel;
        Intrinsics.checkNotNullParameter(optionId, "optionId");
        Intrinsics.checkNotNullParameter(userInput, "userInput");
        if (!isAdded() || (feedbackQuetionnarieModel = this.m) == null) {
            return;
        }
        if (getActivity() instanceof BaseActivity) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
            BaseActivity baseActivity = (BaseActivity) activity;
            if (baseActivity != null) {
                baseActivity.showProgress();
            }
        }
        this.o = i;
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.feedback.fragment.a
            @Override // java.lang.Runnable
            public final void run() {
                FeedbackHolderFrag.l(FeedbackHolderFrag.this, optionId, userInput, feedbackQuetionnarieModel);
            }
        }, 1000L);
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
        this.n = (FeedbackQuestionerViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FeedbackQuestionerViewModel.class);
        FeedbackQuetionnarieModel feedbackQuetionnarieModel = this.m;
        if (feedbackQuetionnarieModel != null) {
            ((TextView) _$_findCachedViewById(R.id.question_txt)).setText(feedbackQuetionnarieModel.getText());
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            List<QuestionsOptionData> options = feedbackQuetionnarieModel.getOptions();
            Intrinsics.checkNotNullExpressionValue(options, "feedbackQuetionnarieModel.options");
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            setFeedbackRecyclAdap(new FeedbackRecyclAdap(requireActivity, options, requireActivity2));
            getFeedbackRecyclAdap().setOnWatchFaceClickListener(this);
            int i = R.id.recycler_view;
            ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
            ((RecyclerView) _$_findCachedViewById(i)).setAdapter(getFeedbackRecyclAdap());
            ((RecyclerView) _$_findCachedViewById(i)).addItemDecoration(new MarginItemDecoration(this, 20));
        }
    }

    public final void setFeedbackQuetionnarieModel(@Nullable FeedbackQuetionnarieModel feedbackQuetionnarieModel) {
        this.m = feedbackQuetionnarieModel;
    }

    public final void setFeedbackRecyclAdap(@NotNull FeedbackRecyclAdap feedbackRecyclAdap) {
        Intrinsics.checkNotNullParameter(feedbackRecyclAdap, "<set-?>");
        this.feedbackRecyclAdap = feedbackRecyclAdap;
    }

    public final void setIndex(int i) {
        this.o = i;
    }
}
