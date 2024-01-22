package com.coveiot.android.leonardo.dashboard.feedback;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.coveiot.android.leonardo.dashboard.feedback.fragment.FeedbackHolderFrag;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class PagerAdapterFeedbackGeneric extends FragmentStatePagerAdapter {
    @NotNull
    public ArrayList<FeedbackQuetionnarieModel> j;
    @NotNull
    public Context k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerAdapterFeedbackGeneric(@NotNull Context context, @NotNull FragmentManager fm, @NotNull ArrayList<FeedbackQuetionnarieModel> questionList) {
        super(fm);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(questionList, "questionList");
        this.j = questionList;
        this.k = context;
    }

    @NotNull
    public final ArrayList<FeedbackQuetionnarieModel> getArrayOfFeedbacks() {
        return this.j;
    }

    @NotNull
    public final Context getContext() {
        return this.k;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.j.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(@NotNull Object object) {
        Intrinsics.checkNotNullParameter(object, "object");
        return -2;
    }

    public final void setArrayOfFeedbacks(@NotNull ArrayList<FeedbackQuetionnarieModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.j = arrayList;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.k = context;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    @NotNull
    public FeedbackHolderFrag getItem(int i) {
        FeedbackHolderFrag.Companion companion = FeedbackHolderFrag.Companion;
        FeedbackQuetionnarieModel feedbackQuetionnarieModel = this.j.get(i);
        Intrinsics.checkNotNullExpressionValue(feedbackQuetionnarieModel, "arrayOfFeedbacks[position]");
        return companion.newInstance(feedbackQuetionnarieModel);
    }
}
