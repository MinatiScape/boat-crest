package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityReArrangeAdapter;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityReArrangeAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<CategoryAndActivityModel> f2740a;
    @NotNull
    public final ItemClickListener b;
    @Nullable
    public List<CategoryAndActivityModel> c;

    /* loaded from: classes2.dex */
    public static final class ActivityViewHolder extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public TextView f2741a;
        @Nullable
        public TextView b;
        @Nullable
        public ImageView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f2741a = (TextView) view.findViewById(R.id.txt_category);
            this.b = (TextView) view.findViewById(R.id.txt_activity);
            this.c = (ImageView) view.findViewById(R.id.img_icon);
        }

        public static final void c(View view) {
        }

        public static final boolean d(ActivityViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Context context = view.getContext();
            Toast.makeText(context, "Position is " + this$0.getAdapterPosition(), 0).show();
            return true;
        }

        public final void bindData(@NotNull CategoryAndActivityModel activityList, @NotNull ItemClickListener listener) {
            Intrinsics.checkNotNullParameter(activityList, "activityList");
            Intrinsics.checkNotNullParameter(listener, "listener");
            if (activityList.getDefaultActivityName() != null) {
                TextView textView = this.f2741a;
                if (textView != null) {
                    textView.setText(activityList.getDefaultActivityName());
                }
            } else {
                TextView textView2 = this.f2741a;
                if (textView2 != null) {
                    textView2.setText(activityList.getTitle());
                }
            }
            TextView textView3 = this.b;
            if (textView3 != null) {
                String titleInMetric = activityList.getTitleInMetric();
                textView3.setText(titleInMetric != null ? kotlin.text.m.capitalize(titleInMetric) : null);
            }
            RequestBuilder<Drawable> m30load = Glide.with(this.itemView.getContext()).m30load(activityList.getIconUrl());
            ImageView imageView = this.c;
            Intrinsics.checkNotNull(imageView);
            m30load.into(imageView);
            TextView textView4 = this.f2741a;
            if (textView4 != null) {
                textView4.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.k
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityReArrangeAdapter.ActivityViewHolder.c(view);
                    }
                });
            }
            TextView textView5 = this.f2741a;
            if (textView5 != null) {
                textView5.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.l
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        boolean d;
                        d = ActivityReArrangeAdapter.ActivityViewHolder.d(ActivityReArrangeAdapter.ActivityViewHolder.this, view);
                        return d;
                    }
                });
            }
        }

        @Nullable
        public final ImageView getImg_icon() {
            return this.c;
        }

        @Nullable
        public final TextView getTxtActivity() {
            return this.b;
        }

        @Nullable
        public final TextView getTxtCategory() {
            return this.f2741a;
        }

        public final void setImg_icon(@Nullable ImageView imageView) {
            this.c = imageView;
        }

        public final void setTxtActivity(@Nullable TextView textView) {
            this.b = textView;
        }

        public final void setTxtCategory(@Nullable TextView textView) {
            this.f2741a = textView;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(int i);

        void onLongClicked(int i);
    }

    public ActivityReArrangeAdapter(@NotNull List<CategoryAndActivityModel> originalList, @NotNull ItemClickListener listener) {
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2740a = originalList;
        this.b = listener;
        Intrinsics.checkNotNull(originalList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel>");
        this.c = (ArrayList) originalList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CategoryAndActivityModel> list = this.c;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @NotNull
    public final ItemClickListener getListener() {
        return this.b;
    }

    @NotNull
    public final List<CategoryAndActivityModel> getOriginalList() {
        return this.f2740a;
    }

    public final void setOriginalList(@NotNull List<CategoryAndActivityModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f2740a = list;
    }

    public final void swapItems(int i, int i2) {
        if (i < i2) {
            int i3 = i2 - 1;
            if (i <= i3) {
                int i4 = i;
                while (true) {
                    List<CategoryAndActivityModel> list = this.c;
                    Intrinsics.checkNotNull(list);
                    List<CategoryAndActivityModel> list2 = this.c;
                    Intrinsics.checkNotNull(list2);
                    int i5 = i4 + 1;
                    List<CategoryAndActivityModel> list3 = this.c;
                    Intrinsics.checkNotNull(list3);
                    list.set(i4, list2.set(i5, list3.get(i4)));
                    if (i4 == i3) {
                        break;
                    }
                    i4 = i5;
                }
            }
        } else {
            int i6 = i2 + 1;
            if (i <= i6) {
                int i7 = i;
                while (true) {
                    List<CategoryAndActivityModel> list4 = this.c;
                    Intrinsics.checkNotNull(list4);
                    List<CategoryAndActivityModel> list5 = this.c;
                    Intrinsics.checkNotNull(list5);
                    List<CategoryAndActivityModel> list6 = this.c;
                    Intrinsics.checkNotNull(list6);
                    list4.set(i7, list5.set(i7 - 1, list6.get(i7)));
                    if (i7 == i6) {
                        break;
                    }
                    i7++;
                }
            }
        }
        notifyItemMoved(i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<CategoryAndActivityModel> list = this.c;
        Intrinsics.checkNotNull(list);
        holder.bindData(list.get(i), this.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rearrange, parent, false));
    }
}
