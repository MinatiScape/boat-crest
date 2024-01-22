package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.feedback.AnswerModel;
import com.coveiot.android.activitymodes.feedback.OptionModel;
import com.coveiot.android.activitymodes.feedback.QuestionModel;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityFeedbackAdapterNew extends RecyclerView.Adapter<ActivityFeedbackHolder> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public AnswerFeedbackInterface f2761a;
    @Nullable
    public List<QuestionModel> b;

    /* loaded from: classes2.dex */
    public final class ActivityFeedbackHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f2762a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityFeedbackHolder(@NotNull ActivityFeedbackAdapterNew activityFeedbackAdapterNew, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tv_q);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tv_q)");
            this.f2762a = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.btn_1);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.btn_1)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.btn_2);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.btn_2)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.btn_3);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.btn_3)");
            this.d = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.btn_4);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.btn_4)");
            this.e = (TextView) findViewById5;
        }

        @NotNull
        public final TextView getBtn1() {
            return this.b;
        }

        @NotNull
        public final TextView getBtn2() {
            return this.c;
        }

        @NotNull
        public final TextView getBtn3() {
            return this.d;
        }

        @NotNull
        public final TextView getBtn4() {
            return this.e;
        }

        @NotNull
        public final TextView getQuestionTxt() {
            return this.f2762a;
        }
    }

    /* loaded from: classes2.dex */
    public interface AnswerFeedbackInterface {
        void onAnswer(@NotNull AnswerModel answerModel);
    }

    public ActivityFeedbackAdapterNew(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public static final void e(QuestionModel question, List options, ActivityFeedbackAdapterNew this$0, View view) {
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(0)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(0)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.f2761a;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    public static final void f(QuestionModel question, List options, ActivityFeedbackAdapterNew this$0, View view) {
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(1)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(1)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.f2761a;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    public static final void g(QuestionModel question, List options, ActivityFeedbackAdapterNew this$0, View view) {
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(2)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(2)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.f2761a;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    public static final void h(QuestionModel question, List options, ActivityFeedbackAdapterNew this$0, View view) {
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(3)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(3)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.f2761a;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    @Nullable
    public final AnswerFeedbackInterface getAnswerFeedbackListener() {
        return this.f2761a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<QuestionModel> list = this.b;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Nullable
    public final List<QuestionModel> getQuestions() {
        return this.b;
    }

    public final void setAnswerFeedbackListener(@Nullable AnswerFeedbackInterface answerFeedbackInterface) {
        this.f2761a = answerFeedbackInterface;
    }

    public final void setData(@NotNull List<QuestionModel> questions) {
        Intrinsics.checkNotNullParameter(questions, "questions");
        this.b = questions;
        notifyDataSetChanged();
    }

    public final void setQuestions(@Nullable List<QuestionModel> list) {
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityFeedbackHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<QuestionModel> list = this.b;
        final QuestionModel questionModel = list != null ? list.get(i) : null;
        holder.getBtn1().setVisibility(8);
        holder.getBtn2().setVisibility(8);
        holder.getBtn3().setVisibility(8);
        holder.getBtn4().setVisibility(8);
        if (questionModel != null) {
            holder.getQuestionTxt().setText(questionModel.getText());
            final List<OptionModel> options = questionModel.getOptions();
            if (options == null || !(!options.isEmpty())) {
                return;
            }
            int size = options.size();
            if (size == 1) {
                holder.getBtn1().setVisibility(0);
                holder.getBtn1().setText(options.get(0).getText());
            } else if (size == 2) {
                holder.getBtn1().setVisibility(0);
                holder.getBtn2().setVisibility(0);
                holder.getBtn1().setText(options.get(0).getText());
                holder.getBtn2().setText(options.get(1).getText());
            } else if (size == 3) {
                holder.getBtn1().setVisibility(0);
                holder.getBtn2().setVisibility(0);
                holder.getBtn3().setVisibility(0);
                holder.getBtn1().setText(options.get(0).getText());
                holder.getBtn2().setText(options.get(1).getText());
                holder.getBtn3().setText(options.get(2).getText());
            } else if (size != 4) {
                holder.getBtn1().setVisibility(0);
                holder.getBtn2().setVisibility(0);
                holder.getBtn3().setVisibility(0);
                holder.getBtn4().setVisibility(0);
            } else {
                holder.getBtn1().setVisibility(0);
                holder.getBtn2().setVisibility(0);
                holder.getBtn3().setVisibility(0);
                holder.getBtn4().setVisibility(0);
                holder.getBtn1().setText(options.get(0).getText());
                holder.getBtn2().setText(options.get(1).getText());
                holder.getBtn3().setText(options.get(2).getText());
                holder.getBtn4().setText(options.get(3).getText());
            }
            holder.getBtn1().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapterNew.e(QuestionModel.this, options, this, view);
                }
            });
            holder.getBtn2().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapterNew.f(QuestionModel.this, options, this, view);
                }
            });
            holder.getBtn3().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapterNew.g(QuestionModel.this, options, this, view);
                }
            });
            holder.getBtn4().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapterNew.h(QuestionModel.this, options, this, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityFeedbackHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_auto_detect_feedback_item_new, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new ActivityFeedbackHolder(this, inflate);
    }
}
