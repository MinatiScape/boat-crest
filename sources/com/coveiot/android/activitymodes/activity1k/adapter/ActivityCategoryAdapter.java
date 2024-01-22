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
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapter;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityCategoryAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Context f2736a;
    @NotNull
    public ArrayList<ActivityCategoriesModel> b;
    @NotNull
    public final ItemClickListener c;
    @Nullable
    public ArrayList<ActivityCategoriesModel> d;
    public int e;

    /* loaded from: classes2.dex */
    public static final class ActivityViewHolder extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public TextView f2737a;
        @Nullable
        public ImageView b;
        @Nullable
        public MaterialCardView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f2737a = (TextView) view.findViewById(R.id.tv_name);
            this.b = (ImageView) view.findViewById(R.id.img_icon);
            this.c = (MaterialCardView) view.findViewById(R.id.card_view);
        }

        public static final void c(ItemClickListener listener, ActivityCategoriesModel activityCategory, int i, View view) {
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(activityCategory, "$activityCategory");
            listener.onClicked(activityCategory, i);
        }

        public static final boolean d(ActivityViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Context context = view.getContext();
            Toast.makeText(context, "Position is " + this$0.getAdapterPosition(), 0).show();
            return true;
        }

        public final void bindData(@NotNull final ActivityCategoriesModel activityCategory, @NotNull final ItemClickListener listener, final int i) {
            Intrinsics.checkNotNullParameter(activityCategory, "activityCategory");
            Intrinsics.checkNotNullParameter(listener, "listener");
            TextView textView = this.f2737a;
            if (textView != null) {
                textView.setText(activityCategory.getTitle());
            }
            RequestBuilder<Drawable> m30load = Glide.with(this.itemView.getContext()).m30load(activityCategory.getIconUrl());
            ImageView imageView = this.b;
            Intrinsics.checkNotNull(imageView);
            m30load.into(imageView);
            TextView textView2 = this.f2737a;
            if (textView2 != null) {
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.g
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityCategoryAdapter.ActivityViewHolder.c(ActivityCategoryAdapter.ItemClickListener.this, activityCategory, i, view);
                    }
                });
            }
            TextView textView3 = this.f2737a;
            if (textView3 != null) {
                textView3.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.h
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        boolean d;
                        d = ActivityCategoryAdapter.ActivityViewHolder.d(ActivityCategoryAdapter.ActivityViewHolder.this, view);
                        return d;
                    }
                });
            }
        }

        @Nullable
        public final MaterialCardView getCardView() {
            return this.c;
        }

        @Nullable
        public final ImageView getImage() {
            return this.b;
        }

        @Nullable
        public final TextView getTvName() {
            return this.f2737a;
        }

        public final void setCardView(@Nullable MaterialCardView materialCardView) {
            this.c = materialCardView;
        }

        public final void setImage(@Nullable ImageView imageView) {
            this.b = imageView;
        }

        public final void setTvName(@Nullable TextView textView) {
            this.f2737a = textView;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(@NotNull ActivityCategoriesModel activityCategoriesModel, int i);

        void onLongClicked(int i);
    }

    public ActivityCategoryAdapter(@Nullable Context context, @NotNull ArrayList<ActivityCategoriesModel> originalList, @NotNull ItemClickListener listener) {
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2736a = context;
        this.b = originalList;
        this.c = listener;
        this.d = originalList;
        this.e = -1;
    }

    @Nullable
    public final Context getContext() {
        return this.f2736a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<ActivityCategoriesModel> arrayList = this.d;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }

    @NotNull
    public final ItemClickListener getListener() {
        return this.c;
    }

    @NotNull
    public final ArrayList<ActivityCategoriesModel> getOriginalList() {
        return this.b;
    }

    public final int getSelectedPosition() {
        return this.e;
    }

    public final void notifyAdapter(int i) {
        this.e = i;
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.d = new ArrayList<>();
        Iterator<ActivityCategoriesModel> it = this.b.iterator();
        while (it.hasNext()) {
            ActivityCategoriesModel next = it.next();
            if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                String title = next.getTitle();
                Intrinsics.checkNotNull(title);
                String lowerCase = title.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String lowerCase2 = searchStr.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                    ArrayList<ActivityCategoriesModel> arrayList = this.d;
                    Intrinsics.checkNotNull(arrayList);
                    arrayList.add(next);
                }
            } else {
                ArrayList<ActivityCategoriesModel> arrayList2 = this.d;
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(next);
            }
        }
        notifyDataSetChanged();
    }

    public final void setOriginalList(@NotNull ArrayList<ActivityCategoriesModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setSelectedPosition(int i) {
        this.e = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.e == i) {
            MaterialCardView cardView = holder.getCardView();
            if (cardView != null) {
                Context context = this.f2736a;
                Intrinsics.checkNotNull(context);
                cardView.setStrokeColor(context.getResources().getColor(R.color.colorAccent));
            }
        } else {
            MaterialCardView cardView2 = holder.getCardView();
            if (cardView2 != null) {
                Context context2 = this.f2736a;
                Intrinsics.checkNotNull(context2);
                cardView2.setStrokeColor(context2.getResources().getColor(R.color.transparent));
            }
        }
        ArrayList<ActivityCategoriesModel> arrayList = this.d;
        Intrinsics.checkNotNull(arrayList);
        ActivityCategoriesModel activityCategoriesModel = arrayList.get(i);
        Intrinsics.checkNotNullExpressionValue(activityCategoriesModel, "categoryList!!.get(position)");
        holder.bindData(activityCategoriesModel, this.c, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }
}
