package com.coveiot.android.activitymodes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.ActivityFeedbackAdapter;
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
public final class ActivityFeedbackAdapter extends RecyclerView.Adapter<ActivityFeedbackHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2759a;
    @Nullable
    public AnswerFeedbackInterface b;
    @Nullable
    public List<QuestionModel> c;

    /* loaded from: classes2.dex */
    public final class ActivityFeedbackHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f2760a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityFeedbackHolder(@NotNull ActivityFeedbackAdapter activityFeedbackAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.tv_q);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tv_q)");
            this.f2760a = (TextView) findViewById;
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
            return this.f2760a;
        }
    }

    /* loaded from: classes2.dex */
    public interface AnswerFeedbackInterface {
        void onAnswer(@NotNull AnswerModel answerModel);
    }

    public ActivityFeedbackAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2759a = context;
    }

    public static final void e(ActivityFeedbackHolder holder, ActivityFeedbackAdapter this$0, QuestionModel question, List options, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        holder.getBtn1().setBackgroundResource(R.drawable.enable_button_background);
        holder.getBtn1().setTextColor(this$0.f2759a.getColor(R.color.main_text_color));
        TextView btn2 = holder.getBtn2();
        int i = R.drawable.disable_button_background_transparent_bg;
        btn2.setBackgroundResource(i);
        TextView btn22 = holder.getBtn2();
        Context context = this$0.f2759a;
        int i2 = R.color.text_color_primary;
        btn22.setTextColor(context.getColor(i2));
        holder.getBtn3().setBackgroundResource(i);
        holder.getBtn3().setTextColor(this$0.f2759a.getColor(i2));
        holder.getBtn4().setBackgroundResource(i);
        holder.getBtn4().setTextColor(this$0.f2759a.getColor(i2));
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(0)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(0)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.b;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    public static final void f(ActivityFeedbackHolder holder, ActivityFeedbackAdapter this$0, QuestionModel question, List options, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        TextView btn1 = holder.getBtn1();
        int i = R.drawable.disable_button_background_transparent_bg;
        btn1.setBackgroundResource(i);
        TextView btn12 = holder.getBtn1();
        Context context = this$0.f2759a;
        int i2 = R.color.text_color_primary;
        btn12.setTextColor(context.getColor(i2));
        holder.getBtn2().setBackgroundResource(R.drawable.enable_button_background);
        holder.getBtn2().setTextColor(this$0.f2759a.getColor(R.color.main_text_color));
        holder.getBtn3().setBackgroundResource(i);
        holder.getBtn3().setTextColor(this$0.f2759a.getColor(i2));
        holder.getBtn4().setBackgroundResource(i);
        holder.getBtn4().setTextColor(this$0.f2759a.getColor(i2));
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(1)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(1)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.b;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    public static final void g(ActivityFeedbackHolder holder, ActivityFeedbackAdapter this$0, QuestionModel question, List options, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        TextView btn1 = holder.getBtn1();
        int i = R.drawable.disable_button_background_transparent_bg;
        btn1.setBackgroundResource(i);
        TextView btn12 = holder.getBtn1();
        Context context = this$0.f2759a;
        int i2 = R.color.text_color_primary;
        btn12.setTextColor(context.getColor(i2));
        holder.getBtn2().setBackgroundResource(i);
        holder.getBtn2().setTextColor(this$0.f2759a.getColor(i2));
        holder.getBtn3().setBackgroundResource(R.drawable.enable_button_background);
        holder.getBtn3().setTextColor(this$0.f2759a.getColor(R.color.main_text_color));
        holder.getBtn4().setBackgroundResource(i);
        holder.getBtn4().setTextColor(this$0.f2759a.getColor(i2));
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(2)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(2)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.b;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    public static final void h(ActivityFeedbackHolder holder, ActivityFeedbackAdapter this$0, QuestionModel question, List options, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(question, "$question");
        Intrinsics.checkNotNullParameter(options, "$options");
        TextView btn1 = holder.getBtn1();
        int i = R.drawable.disable_button_background_transparent_bg;
        btn1.setBackgroundResource(i);
        TextView btn12 = holder.getBtn1();
        Context context = this$0.f2759a;
        int i2 = R.color.text_color_primary;
        btn12.setTextColor(context.getColor(i2));
        holder.getBtn2().setBackgroundResource(i);
        holder.getBtn2().setTextColor(this$0.f2759a.getColor(i2));
        holder.getBtn3().setBackgroundResource(i);
        holder.getBtn3().setTextColor(this$0.f2759a.getColor(i2));
        holder.getBtn4().setBackgroundResource(R.drawable.enable_button_background);
        holder.getBtn4().setTextColor(this$0.f2759a.getColor(R.color.main_text_color));
        AnswerModel answerModel = new AnswerModel();
        answerModel.setQuestionId(question.getQuestionId());
        String text = ((OptionModel) options.get(3)).getText();
        if (!(text == null || text.length() == 0)) {
            String optionId = ((OptionModel) options.get(3)).getOptionId();
            Intrinsics.checkNotNull(optionId);
            answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
        }
        AnswerFeedbackInterface answerFeedbackInterface = this$0.b;
        if (answerFeedbackInterface != null) {
            answerFeedbackInterface.onAnswer(answerModel);
        }
    }

    @Nullable
    public final AnswerFeedbackInterface getAnswerFeedbackListener() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<QuestionModel> list = this.c;
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
        return this.c;
    }

    public final void setAnswerFeedbackListener(@Nullable AnswerFeedbackInterface answerFeedbackInterface) {
        this.b = answerFeedbackInterface;
    }

    public final void setData(@NotNull List<QuestionModel> questions) {
        Intrinsics.checkNotNullParameter(questions, "questions");
        this.c = questions;
        notifyDataSetChanged();
    }

    public final void setQuestions(@Nullable List<QuestionModel> list) {
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ActivityFeedbackHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<QuestionModel> list = this.c;
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
            holder.getBtn1().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapter.e(ActivityFeedbackAdapter.ActivityFeedbackHolder.this, this, questionModel, options, view);
                }
            });
            holder.getBtn2().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapter.f(ActivityFeedbackAdapter.ActivityFeedbackHolder.this, this, questionModel, options, view);
                }
            });
            holder.getBtn3().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapter.g(ActivityFeedbackAdapter.ActivityFeedbackHolder.this, this, questionModel, options, view);
                }
            });
            holder.getBtn4().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.adapters.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFeedbackAdapter.h(ActivityFeedbackAdapter.ActivityFeedbackHolder.this, this, questionModel, options, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityFeedbackHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_auto_detect_feedback_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new ActivityFeedbackHolder(this, inflate);
    }
}
