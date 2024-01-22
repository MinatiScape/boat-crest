package com.coveiot.android.activitymodes.workoutVideos.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.databinding.WorkoutVideosFeedbackItemBinding;
import com.coveiot.android.activitymodes.feedback.OptionModel;
import com.coveiot.android.activitymodes.workoutVideos.adapters.WorkoutVideosFeedbackAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutVideosFeedbackAdapter extends RecyclerView.Adapter<FeedBackHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2897a;
    @NotNull
    public List<OptionModel> b;
    @NotNull
    public Activity c;
    public int d;
    @Nullable
    public onItemClickListener e;
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class FeedBackHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final WorkoutVideosFeedbackItemBinding f2898a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FeedBackHolder(@NotNull WorkoutVideosFeedbackItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f2898a = binding;
        }

        @NotNull
        public final WorkoutVideosFeedbackItemBinding getBinding() {
            return this.f2898a;
        }
    }

    /* loaded from: classes2.dex */
    public interface onItemClickListener {
        void onItemClick(int i, @NotNull String str);
    }

    public WorkoutVideosFeedbackAdapter(@NotNull Context mContext, @NotNull List<OptionModel> optionList, @NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(optionList, "optionList");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.f2897a = mContext;
        this.b = optionList;
        this.c = activity;
        this.d = -1;
        this.f = true;
    }

    public static final void b(WorkoutVideosFeedbackAdapter this$0, int i, View view) {
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
    public final List<OptionModel> getOptionList() {
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

    public final void setOnFeedBackClickListener(@Nullable onItemClickListener onitemclicklistener) {
        this.e = onitemclicklistener;
    }

    public final void setOptionList(@NotNull List<OptionModel> list) {
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
    public void onBindViewHolder(@NotNull final FeedBackHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Glide.with(this.f2897a).asBitmap().m21load(this.b.get(i).getIconUrl()).skipMemoryCache(true).into((RequestBuilder) new CustomTarget<Bitmap>() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.WorkoutVideosFeedbackAdapter$onBindViewHolder$1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(@Nullable Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                WorkoutVideosFeedbackAdapter.FeedBackHolder.this.getBinding().imageFeedback.setImageBitmap(resource);
            }
        });
        holder.getBinding().textFeedback.setText(this.b.get(i).getText());
        if (this.d == i) {
            Glide.with(this.f2897a).asBitmap().m21load(this.b.get(i).getActiveIconUrl()).skipMemoryCache(true).into((RequestBuilder) new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.WorkoutVideosFeedbackAdapter$onBindViewHolder$2
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
                    WorkoutVideosFeedbackAdapter.FeedBackHolder.this.getBinding().imageFeedback.setImageBitmap(resource);
                    TextView textView = WorkoutVideosFeedbackAdapter.FeedBackHolder.this.getBinding().textFeedback;
                    context = this.f2897a;
                    textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    if (this.getListener() != null) {
                        WorkoutVideosFeedbackAdapter.onItemClickListener listener = this.getListener();
                        Intrinsics.checkNotNull(listener);
                        int i2 = i;
                        String optionId = this.getOptionList().get(i).getOptionId();
                        Intrinsics.checkNotNull(optionId);
                        listener.onItemClick(i2, optionId);
                    }
                    this.setSelectAnswer(true);
                }
            });
        } else {
            Glide.with(this.f2897a).asBitmap().m21load(this.b.get(i).getIconUrl()).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.WorkoutVideosFeedbackAdapter$onBindViewHolder$3
                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    WorkoutVideosFeedbackAdapter.FeedBackHolder.this.getBinding().imageFeedback.setImageBitmap(resource);
                }
            });
            holder.getBinding().textFeedback.setTextColor(this.f2897a.getResources().getColor(R.color.secondary_text_color));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WorkoutVideosFeedbackAdapter.b(WorkoutVideosFeedbackAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FeedBackHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        WorkoutVideosFeedbackItemBinding binding = (WorkoutVideosFeedbackItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.workout_videos_feedback_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new FeedBackHolder(binding);
    }
}
