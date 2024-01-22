package com.coveiot.android.leonardo.dashboard.feedback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.feedback.FeedbackRecyclAdap;
import com.coveiot.android.sleepenergyscore.energymeter.model.QuestionsOptionData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FeedbackRecyclAdap extends RecyclerView.Adapter<WatchImageHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4715a;
    @NotNull
    public List<QuestionsOptionData> b;
    @NotNull
    public Activity c;
    public int d;
    @Nullable
    public onItemClickListener e;
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class WatchImageHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f4716a;
        @NotNull
        public final TextView b;
        @NotNull
        public final LinearLayout c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WatchImageHolder(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.image_feedback);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(com.co…core.R.id.image_feedback)");
            this.f4716a = (ImageView) findViewById;
            View findViewById2 = view.findViewById(R.id.text_feedback);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(com.co…score.R.id.text_feedback)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.linear_lay);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(com.co…rgyscore.R.id.linear_lay)");
            this.c = (LinearLayout) findViewById3;
        }

        @NotNull
        public final ImageView getImageFeedback() {
            return this.f4716a;
        }

        @NotNull
        public final LinearLayout getLinear_lay() {
            return this.c;
        }

        @NotNull
        public final TextView getText_feedback() {
            return this.b;
        }
    }

    /* loaded from: classes2.dex */
    public interface onItemClickListener {
        void onItemClick(int i, @NotNull String str, @NotNull String str2);
    }

    public FeedbackRecyclAdap(@NotNull Context mContext, @NotNull List<QuestionsOptionData> optionList, @NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(optionList, "optionList");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.f4715a = mContext;
        this.b = optionList;
        this.c = activity;
        this.d = -1;
        this.f = true;
    }

    public static final void b(FeedbackRecyclAdap this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.f) {
            this$0.f = false;
            this$0.d = i;
            this$0.notifyDataSetChanged();
        }
    }

    @NotNull
    public final Activity getActivity() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    @Nullable
    public final onItemClickListener getListener() {
        return this.e;
    }

    public final int getMSelectedPosition() {
        return this.d;
    }

    @NotNull
    public final List<QuestionsOptionData> getOptionList() {
        return this.b;
    }

    public final int getSelectedPosition() {
        return this.d;
    }

    public final boolean isSelectAnswer() {
        return this.f;
    }

    public final void setActivity(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.c = activity;
    }

    public final void setListener(@Nullable onItemClickListener onitemclicklistener) {
        this.e = onitemclicklistener;
    }

    public final void setMSelectedPosition(int i) {
        this.d = i;
    }

    public final void setOnWatchFaceClickListener(@Nullable onItemClickListener onitemclicklistener) {
        this.e = onitemclicklistener;
    }

    public final void setOptionList(@NotNull List<QuestionsOptionData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    public final void setSelectAnswer(boolean z) {
        this.f = z;
    }

    public final void setSelectedPosition(int i) {
        this.d = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final WatchImageHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Glide.with(this.f4715a).asBitmap().m21load(this.b.get(i).getIconUrl()).skipMemoryCache(true).into((RequestBuilder) new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.feedback.FeedbackRecyclAdap$onBindViewHolder$1
            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadCleared(@Nullable Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                FeedbackRecyclAdap.WatchImageHolder.this.getImageFeedback().setImageBitmap(resource);
            }
        });
        holder.getText_feedback().setText(this.b.get(i).getText());
        if (this.d == i) {
            Glide.with(this.f4715a).asBitmap().m21load(this.b.get(i).getActiveIconUrl()).skipMemoryCache(true).into((RequestBuilder) new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.feedback.FeedbackRecyclAdap$onBindViewHolder$2
                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable) {
                    this.setSelectAnswer(true);
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Context context;
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    FeedbackRecyclAdap.WatchImageHolder.this.getImageFeedback().setImageBitmap(resource);
                    TextView text_feedback = FeedbackRecyclAdap.WatchImageHolder.this.getText_feedback();
                    context = this.f4715a;
                    text_feedback.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    if (this.getListener() != null) {
                        FeedbackRecyclAdap.onItemClickListener listener = this.getListener();
                        Intrinsics.checkNotNull(listener);
                        int i2 = i;
                        String optionId = this.getOptionList().get(i).getOptionId();
                        Intrinsics.checkNotNullExpressionValue(optionId, "optionList[position].optionId");
                        String text = this.getOptionList().get(i).getText();
                        Intrinsics.checkNotNullExpressionValue(text, "optionList[position].text");
                        listener.onItemClick(i2, optionId, text);
                    }
                    this.setSelectAnswer(true);
                }
            });
        } else {
            Glide.with(this.f4715a).asBitmap().m21load(this.b.get(i).getIconUrl()).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.feedback.FeedbackRecyclAdap$onBindViewHolder$3
                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    FeedbackRecyclAdap.WatchImageHolder.this.getImageFeedback().setImageBitmap(resource);
                }
            });
            holder.getText_feedback().setTextColor(this.f4715a.getResources().getColor(R.color.secondary_text_color));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.feedback.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackRecyclAdap.b(FeedbackRecyclAdap.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public WatchImageHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sleep_score_feedback_item, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(viewGroup.getContex…core_feedback_item, null)");
        return new WatchImageHolder(inflate);
    }
}
