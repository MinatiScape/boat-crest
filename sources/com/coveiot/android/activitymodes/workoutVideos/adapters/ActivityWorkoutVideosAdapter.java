package com.coveiot.android.activitymodes.workoutVideos.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.databinding.ActivityWorkoutVideosItemBinding;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosAdapter;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityWorkoutVideosAdapter extends PagingDataAdapter<WorkoutVideosBean, ActivityHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final ActivityWorkoutVideosAdapter$Companion$WORKOUT_VIDEOS_COMPARATOR$1 g = new DiffUtil.ItemCallback<WorkoutVideosBean>() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosAdapter$Companion$WORKOUT_VIDEOS_COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(@NotNull WorkoutVideosBean oldItem, @NotNull WorkoutVideosBean newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(@NotNull WorkoutVideosBean oldItem, @NotNull WorkoutVideosBean newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getVideoId(), newItem.getVideoId());
        }
    };
    @NotNull
    public final Context e;
    @Nullable
    public ItemClickListener f;

    /* loaded from: classes2.dex */
    public final class ActivityHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ActivityWorkoutVideosItemBinding f2894a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityHolder(@NotNull ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter, ActivityWorkoutVideosItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f2894a = binding;
        }

        @NotNull
        public final ActivityWorkoutVideosItemBinding getBinding() {
            return this.f2894a;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onVideosItemClick(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable List<String> list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityWorkoutVideosAdapter(@NotNull Context context) {
        super(g, null, null, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        this.e = context;
    }

    public static final void c(ActivityWorkoutVideosAdapter this$0, String ytVideoId, String videoId, String str, Integer num, List list, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ytVideoId, "$ytVideoId");
        Intrinsics.checkNotNullParameter(videoId, "$videoId");
        ItemClickListener itemClickListener = this$0.f;
        if (itemClickListener != null) {
            Intrinsics.checkNotNull(itemClickListener);
            itemClickListener.onVideosItemClick(ytVideoId, videoId, str, num, list);
        }
    }

    public final void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.f = itemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ActivityHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        WorkoutVideosBean item = getItem(i);
        if (item != null) {
            final String youTubeVideoId = item.getYouTubeVideoId();
            final String videoId = item.getVideoId();
            final String title = item.getTitle();
            final Integer duration = item.getDuration();
            final List<String> categoryIds = item.getCategoryIds();
            holder.getBinding().videoTitle.setText(title);
            RequestBuilder<Bitmap> asBitmap = Glide.with(this.e).asBitmap();
            String thumbnailUrl = item.getThumbnailUrl();
            Intrinsics.checkNotNull(thumbnailUrl);
            asBitmap.m21load(thumbnailUrl).skipMemoryCache(true).into((RequestBuilder) new CustomTarget<Bitmap>() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosAdapter$onBindViewHolder$1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    ActivityWorkoutVideosAdapter.ActivityHolder.this.getBinding().videoThumbnail.setImageBitmap(resource);
                }
            });
            TextView textView = holder.getBinding().videoMinutes;
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            Integer duration2 = item.getDuration();
            Intrinsics.checkNotNull(duration2);
            textView.setText(workoutUtils.getFormattedDuration(duration2.intValue()));
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.adapters.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityWorkoutVideosAdapter.c(ActivityWorkoutVideosAdapter.this, youTubeVideoId, videoId, title, duration, categoryIds, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ActivityWorkoutVideosItemBinding binding = (ActivityWorkoutVideosItemBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.activity_workout_videos_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new ActivityHolder(this, binding);
    }
}
