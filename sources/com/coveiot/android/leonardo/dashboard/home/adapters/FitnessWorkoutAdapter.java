package com.coveiot.android.leonardo.dashboard.home.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean;
import com.coveiot.android.leonardo.dashboard.home.adapters.FitnessWorkoutAdapter;
import com.coveiot.android.theme.databinding.ListItemWorkoutVideosLayoutBinding;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FitnessWorkoutAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4777a;
    @NotNull
    public final List<WorkoutVideosBean> b;
    @NotNull
    public final ItemClickListener c;

    /* loaded from: classes3.dex */
    public interface ItemClickListener {
        void onVideosItemClick(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable List<String> list);
    }

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4778a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final ImageView f;
        @NotNull
        public final ConstraintLayout g;
        @NotNull
        public final ConstraintLayout h;
        @NotNull
        public final View i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessWorkoutAdapter fitnessWorkoutAdapter, ListItemWorkoutVideosLayoutBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = itemView.tvWorkoutTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tvWorkoutTitle");
            this.f4778a = textView;
            TextView textView2 = itemView.tvWorkoutDuration;
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.tvWorkoutDuration");
            this.b = textView2;
            ImageView imageView = itemView.ivVideo;
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.ivVideo");
            this.c = imageView;
            TextView textView3 = itemView.tvWorkoutTitle0;
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.tvWorkoutTitle0");
            this.d = textView3;
            TextView textView4 = itemView.tvWorkoutDuration0;
            Intrinsics.checkNotNullExpressionValue(textView4, "itemView.tvWorkoutDuration0");
            this.e = textView4;
            ImageView imageView2 = itemView.ivVideo0;
            Intrinsics.checkNotNullExpressionValue(imageView2, "itemView.ivVideo0");
            this.f = imageView2;
            ConstraintLayout constraintLayout = itemView.clWorkoutVideo0;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.clWorkoutVideo0");
            this.g = constraintLayout;
            ConstraintLayout constraintLayout2 = itemView.clMain;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "itemView.clMain");
            this.h = constraintLayout2;
            View view = itemView.view;
            Intrinsics.checkNotNullExpressionValue(view, "itemView.view");
            this.i = view;
        }

        @NotNull
        public final ConstraintLayout getClMain() {
            return this.h;
        }

        @NotNull
        public final ConstraintLayout getClWorkoutVideo0() {
            return this.g;
        }

        @NotNull
        public final ImageView getIvVideo() {
            return this.c;
        }

        @NotNull
        public final ImageView getIvVideo0() {
            return this.f;
        }

        @NotNull
        public final TextView getTvWorkoutDuration() {
            return this.b;
        }

        @NotNull
        public final TextView getTvWorkoutDuration0() {
            return this.e;
        }

        @NotNull
        public final TextView getTvWorkoutTitle() {
            return this.f4778a;
        }

        @NotNull
        public final TextView getTvWorkoutTitle0() {
            return this.d;
        }

        @NotNull
        public final View getView() {
            return this.i;
        }
    }

    public FitnessWorkoutAdapter(@NotNull Context mContext, @NotNull List<WorkoutVideosBean> fitnessWorkoutList, @NotNull ItemClickListener itemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(fitnessWorkoutList, "fitnessWorkoutList");
        Intrinsics.checkNotNullParameter(itemClickListener, "itemClickListener");
        this.f4777a = mContext;
        this.b = fitnessWorkoutList;
        this.c = itemClickListener;
    }

    public static final void b(FitnessWorkoutAdapter this$0, String ytVideoId, String videoId, String str, Integer num, List list, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ytVideoId, "$ytVideoId");
        Intrinsics.checkNotNullParameter(videoId, "$videoId");
        ItemClickListener itemClickListener = this$0.c;
        if (itemClickListener != null) {
            Intrinsics.checkNotNull(itemClickListener);
            itemClickListener.onVideosItemClick(ytVideoId, videoId, str, num, list);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (i == 0) {
            holder.getClMain().setVisibility(8);
            holder.getView().setVisibility(8);
            holder.getClWorkoutVideo0().setVisibility(0);
            holder.getTvWorkoutTitle0().setText(this.b.get(i).getTitle());
            TextView tvWorkoutDuration0 = holder.getTvWorkoutDuration0();
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            Integer duration = this.b.get(i).getDuration();
            Intrinsics.checkNotNull(duration);
            tvWorkoutDuration0.setText(workoutUtils.getFormattedDuration(duration.intValue()));
            RequestBuilder<Bitmap> asBitmap = Glide.with(this.f4777a).asBitmap();
            String thumbnailUrl = this.b.get(i).getThumbnailUrl();
            Intrinsics.checkNotNull(thumbnailUrl);
            asBitmap.m21load(thumbnailUrl).skipMemoryCache(true).into((RequestBuilder) new CustomTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.home.adapters.FitnessWorkoutAdapter$onBindViewHolder$1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    FitnessWorkoutAdapter.ViewHolder.this.getIvVideo0().setImageBitmap(resource);
                }
            });
        } else {
            List<WorkoutVideosBean> list = this.b;
            Intrinsics.checkNotNull(list);
            if (CollectionsKt__CollectionsKt.getLastIndex(list) == i) {
                holder.getView().setVisibility(8);
            } else {
                holder.getView().setVisibility(0);
            }
            holder.getClMain().setVisibility(0);
            holder.getClWorkoutVideo0().setVisibility(8);
            holder.getTvWorkoutTitle().setText(this.b.get(i).getTitle());
            TextView tvWorkoutDuration = holder.getTvWorkoutDuration();
            WorkoutUtils workoutUtils2 = WorkoutUtils.INSTANCE;
            Integer duration2 = this.b.get(i).getDuration();
            Intrinsics.checkNotNull(duration2);
            tvWorkoutDuration.setText(workoutUtils2.getFormattedDuration(duration2.intValue()));
            RequestBuilder<Bitmap> asBitmap2 = Glide.with(this.f4777a).asBitmap();
            String thumbnailUrl2 = this.b.get(i).getThumbnailUrl();
            Intrinsics.checkNotNull(thumbnailUrl2);
            asBitmap2.m21load(thumbnailUrl2).skipMemoryCache(true).into((RequestBuilder) new CustomTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.home.adapters.FitnessWorkoutAdapter$onBindViewHolder$2
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    FitnessWorkoutAdapter.ViewHolder.this.getIvVideo().setImageBitmap(resource);
                }
            });
        }
        final String youTubeVideoId = this.b.get(i).getYouTubeVideoId();
        final String videoId = this.b.get(i).getVideoId();
        final String title = this.b.get(i).getTitle();
        final Integer duration3 = this.b.get(i).getDuration();
        final List<String> categoryIds = this.b.get(i).getCategoryIds();
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.adapters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessWorkoutAdapter.b(FitnessWorkoutAdapter.this, youTubeVideoId, videoId, title, duration3, categoryIds, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemWorkoutVideosLayoutBinding inflate = ListItemWorkoutVideosLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
