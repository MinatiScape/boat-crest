package com.coveiot.android.watchfaceui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.adapter.WatchFaceCategoryAdapter;
import com.coveiot.android.watchfaceui.databinding.ListWfCategoryItemBinding;
import com.coveiot.android.watchfaceui.model.Categories;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class WatchFaceCategoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6120a;
    @NotNull
    public final CategoryClickListener b;
    @NotNull
    public List<Categories> c;
    public int d;
    public int e;

    /* loaded from: classes8.dex */
    public interface CategoryClickListener {
        void categoryClick(@NotNull Categories categories, int i);
    }

    /* loaded from: classes8.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListWfCategoryItemBinding f6121a;
        public final /* synthetic */ WatchFaceCategoryAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull WatchFaceCategoryAdapter watchFaceCategoryAdapter, ListWfCategoryItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = watchFaceCategoryAdapter;
            this.f6121a = binding;
        }

        public static final void b(WatchFaceCategoryAdapter this$0, ViewHolder this$1, Categories categoryData, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(categoryData, "$categoryData");
            this$0.e = this$0.d;
            this$0.d = this$1.getAbsoluteAdapterPosition();
            this$0.notifyItemChanged(this$0.e);
            this$0.notifyItemChanged(this$0.d);
            this$0.getListener().categoryClick(categoryData, this$1.getAbsoluteAdapterPosition());
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final Categories categoryData) {
            Intrinsics.checkNotNullParameter(categoryData, "categoryData");
            ListWfCategoryItemBinding listWfCategoryItemBinding = this.f6121a;
            final WatchFaceCategoryAdapter watchFaceCategoryAdapter = this.b;
            listWfCategoryItemBinding.setCloudCategoryData(categoryData);
            if (watchFaceCategoryAdapter.d == getAbsoluteAdapterPosition()) {
                this.itemView.setBackgroundResource(R.drawable.background_cloud_tab_new);
                listWfCategoryItemBinding.tvCategory.setTextAppearance(R.style.selectedTabFont);
            } else {
                this.itemView.setBackgroundResource(R.color.transparent);
                listWfCategoryItemBinding.tvCategory.setTextAppearance(R.style.unSelectedTabFontOnBoarding);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.adapter.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WatchFaceCategoryAdapter.ViewHolder.b(WatchFaceCategoryAdapter.this, this, categoryData, view);
                }
            });
        }
    }

    public WatchFaceCategoryAdapter(@NotNull Context context, @NotNull CategoryClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f6120a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f6120a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final CategoryClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setCategoryList(@NotNull List<Categories> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.c = TypeIntrinsics.asMutableList(list);
        notifyDataSetChanged();
    }

    public final void setSelectedCategory(int i) {
        this.d = i;
        this.e = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListWfCategoryItemBinding inflate = ListWfCategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
