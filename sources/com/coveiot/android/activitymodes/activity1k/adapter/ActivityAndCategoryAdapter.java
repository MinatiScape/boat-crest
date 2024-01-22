package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityAndCategoryAdapter;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityAndCategoryAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<CategoryAndActivityModel> f2734a;
    @NotNull
    public final ItemClickListener b;
    @NotNull
    public Context c;
    @Nullable
    public List<CategoryAndActivityModel> d;
    public int e;

    /* loaded from: classes2.dex */
    public static final class ActivityViewHolder extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public TextView f2735a;
        @Nullable
        public TextView b;
        @Nullable
        public ImageView c;
        @Nullable
        public LinearLayout d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f2735a = (TextView) view.findViewById(R.id.txt_category);
            this.b = (TextView) view.findViewById(R.id.txt_activity);
            this.c = (ImageView) view.findViewById(R.id.img_icon);
            this.d = (LinearLayout) view.findViewById(R.id.card_view);
        }

        public static final void c(ItemClickListener listener, int i, View view) {
            Intrinsics.checkNotNullParameter(listener, "$listener");
            listener.onClicked(i);
        }

        public static final boolean d(ActivityViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Context context = view.getContext();
            Toast.makeText(context, "Position is " + this$0.getAdapterPosition(), 0).show();
            return true;
        }

        public final void bindData(@NotNull CategoryAndActivityModel activityList, @NotNull final ItemClickListener listener, final int i, int i2, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(activityList, "activityList");
            Intrinsics.checkNotNullParameter(listener, "listener");
            Intrinsics.checkNotNullParameter(context, "context");
            TextView textView = this.f2735a;
            if (textView != null) {
                String titleInMetric = activityList.getTitleInMetric();
                textView.setText(titleInMetric != null ? kotlin.text.m.capitalize(titleInMetric) : null);
            }
            TextView textView2 = this.f2735a;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            if (activityList.getDefaultActivityName() != null) {
                TextView textView3 = this.b;
                if (textView3 != null) {
                    textView3.setText(activityList.getDefaultActivityName());
                }
            } else {
                TextView textView4 = this.b;
                if (textView4 != null) {
                    textView4.setText(activityList.getTitleInMetric());
                }
            }
            RequestBuilder<Drawable> m30load = Glide.with(this.itemView.getContext()).m30load(activityList.getIconUrl());
            ImageView imageView = this.c;
            Intrinsics.checkNotNull(imageView);
            m30load.into(imageView);
            LinearLayout linearLayout = this.d;
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.e
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAndCategoryAdapter.ActivityViewHolder.c(ActivityAndCategoryAdapter.ItemClickListener.this, i, view);
                    }
                });
            }
            TextView textView5 = this.f2735a;
            if (textView5 != null) {
                textView5.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.f
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        boolean d;
                        d = ActivityAndCategoryAdapter.ActivityViewHolder.d(ActivityAndCategoryAdapter.ActivityViewHolder.this, view);
                        return d;
                    }
                });
            }
        }

        @Nullable
        public final LinearLayout getCardView() {
            return this.d;
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
            return this.f2735a;
        }

        public final void setCardView(@Nullable LinearLayout linearLayout) {
            this.d = linearLayout;
        }

        public final void setImg_icon(@Nullable ImageView imageView) {
            this.c = imageView;
        }

        public final void setTxtActivity(@Nullable TextView textView) {
            this.b = textView;
        }

        public final void setTxtCategory(@Nullable TextView textView) {
            this.f2735a = textView;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(int i);

        void onLongClicked(int i);
    }

    public ActivityAndCategoryAdapter(@NotNull List<CategoryAndActivityModel> originalList, @NotNull ItemClickListener listener, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2734a = originalList;
        this.b = listener;
        this.c = context;
        Intrinsics.checkNotNull(originalList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel>");
        this.d = (ArrayList) originalList;
        this.e = -1;
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CategoryAndActivityModel> list = this.d;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @NotNull
    public final ItemClickListener getListener() {
        return this.b;
    }

    @NotNull
    public final List<CategoryAndActivityModel> getOriginalList() {
        return this.f2734a;
    }

    public final int getSelectedPosition() {
        return this.e;
    }

    public final void notifyAdapter(int i) {
        this.e = i;
        List<CategoryAndActivityModel> list = this.d;
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel>");
        this.d = TypeIntrinsics.asMutableList(list);
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        String str;
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.d = new ArrayList();
        for (CategoryAndActivityModel categoryAndActivityModel : this.f2734a) {
            if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                String titleInMetric = categoryAndActivityModel.getTitleInMetric();
                if (titleInMetric != null) {
                    str = titleInMetric.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
                } else {
                    str = null;
                }
                Intrinsics.checkNotNull(str);
                String lowerCase = searchStr.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) lowerCase, false, 2, (Object) null)) {
                    List<CategoryAndActivityModel> list = this.d;
                    Intrinsics.checkNotNull(list);
                    list.add(categoryAndActivityModel);
                }
            } else {
                List<CategoryAndActivityModel> list2 = this.d;
                Intrinsics.checkNotNull(list2);
                list2.add(categoryAndActivityModel);
            }
        }
        notifyDataSetChanged();
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.c = context;
    }

    public final void setOriginalList(@NotNull List<CategoryAndActivityModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f2734a = list;
    }

    public final void setSelectedPosition(int i) {
        this.e = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.e == i) {
            LinearLayout cardView = holder.getCardView();
            if (cardView != null) {
                cardView.setBackgroundResource(R.drawable.activity_icon_selected);
            }
        } else {
            LinearLayout cardView2 = holder.getCardView();
            if (cardView2 != null) {
                cardView2.setBackgroundResource(R.drawable.activity_icon_unselected);
            }
        }
        List<CategoryAndActivityModel> list = this.d;
        Intrinsics.checkNotNull(list);
        holder.bindData(list.get(i), this.b, i, this.e, this.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_and_activity, parent, false));
    }
}
