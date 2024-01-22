package com.mappls.sdk.nearby.plugin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyCategoryAdapterBinding;
import com.mappls.sdk.nearby.plugin.view.NearbyView;
import com.mappls.sdk.nearby.plugin.view.NearbyViewOption;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class a extends RecyclerView.Adapter<b> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final NearbyViewOption f13056a;
    @Nullable
    public List<? extends CategoryCode> b;
    @Nullable
    public InterfaceC0652a c;

    /* renamed from: com.mappls.sdk.nearby.plugin.adapter.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public interface InterfaceC0652a {
        void a();
    }

    /* loaded from: classes10.dex */
    public static final class b extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final MapplsNearbyCategoryAdapterBinding f13057a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(@NotNull MapplsNearbyCategoryAdapterBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f13057a = binding;
        }

        @NotNull
        public final MapplsNearbyCategoryAdapterBinding a() {
            return this.f13057a;
        }
    }

    public a(@NotNull NearbyViewOption options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.f13056a = options;
    }

    public static final void b(a this$0, int i, View view) {
        CategoryCode categoryCode;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<? extends CategoryCode> list = this$0.b;
        Boolean bool = null;
        CategoryCode categoryCode2 = list != null ? list.get(i) : null;
        if (categoryCode2 != null) {
            List<? extends CategoryCode> list2 = this$0.b;
            if (list2 != null && (categoryCode = list2.get(i)) != null) {
                bool = Boolean.valueOf(categoryCode.isSelected());
            }
            Intrinsics.checkNotNull(bool);
            categoryCode2.setSelected(!bool.booleanValue());
        }
        InterfaceC0652a interfaceC0652a = this$0.c;
        if (interfaceC0652a != null) {
            interfaceC0652a.a();
        }
        this$0.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a */
    public final void onBindViewHolder(@NotNull b holder, final int i) {
        CategoryCode categoryCode;
        ImageView imageView;
        Integer categoryTintColor;
        String str;
        CategoryCode categoryCode2;
        CategoryCode categoryCode3;
        CategoryCode categoryCode4;
        CategoryCode categoryCode5;
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<? extends CategoryCode> list = this.b;
        Boolean bool = null;
        if (((list == null || (categoryCode5 = list.get(i)) == null) ? null : categoryCode5.getBitmapIcon()) != null) {
            ImageView imageView2 = holder.a().mapplsNearbyCategoryIcon;
            List<? extends CategoryCode> list2 = this.b;
            imageView2.setImageBitmap((list2 == null || (categoryCode4 = list2.get(i)) == null) ? null : categoryCode4.getBitmapIcon());
        } else {
            ImageView imageView3 = holder.a().mapplsNearbyCategoryIcon;
            Context context = holder.a().getRoot().getContext();
            List<? extends CategoryCode> list3 = this.b;
            Integer valueOf = (list3 == null || (categoryCode = list3.get(i)) == null) ? null : Integer.valueOf(categoryCode.getIcon());
            Intrinsics.checkNotNull(valueOf);
            imageView3.setImageDrawable(ContextCompat.getDrawable(context, valueOf.intValue()));
        }
        TextView textView = holder.a().mapplsNearbyKeyword;
        List<? extends CategoryCode> list4 = this.b;
        String category = (list4 == null || (categoryCode3 = list4.get(i)) == null) ? null : categoryCode3.getCategory();
        Intrinsics.checkNotNull(category);
        textView.setText(category);
        List<? extends CategoryCode> list5 = this.b;
        if (list5 != null && (categoryCode2 = list5.get(i)) != null) {
            bool = Boolean.valueOf(categoryCode2.isSelected());
        }
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            CardView cardView = holder.a().mapplsNearbyCategoryBackground;
            Integer selectedCategoryBackgroundColor = this.f13056a.selectedCategoryBackgroundColor();
            Intrinsics.checkNotNullExpressionValue(selectedCategoryBackgroundColor, "options.selectedCategoryBackgroundColor()");
            cardView.setCardBackgroundColor(selectedCategoryBackgroundColor.intValue());
            TextView textView2 = holder.a().mapplsNearbyKeyword;
            Integer selectedCategoryTextColor = this.f13056a.selectedCategoryTextColor();
            Intrinsics.checkNotNullExpressionValue(selectedCategoryTextColor, "options.selectedCategoryTextColor()");
            textView2.setTextColor(selectedCategoryTextColor.intValue());
            imageView = holder.a().mapplsNearbyCategoryIcon;
            categoryTintColor = this.f13056a.selectedCategoryTintColor();
            str = "options.selectedCategoryTintColor()";
        } else {
            CardView cardView2 = holder.a().mapplsNearbyCategoryBackground;
            Integer categoryBackgroundColor = this.f13056a.categoryBackgroundColor();
            Intrinsics.checkNotNullExpressionValue(categoryBackgroundColor, "options.categoryBackgroundColor()");
            cardView2.setCardBackgroundColor(categoryBackgroundColor.intValue());
            TextView textView3 = holder.a().mapplsNearbyKeyword;
            Integer categoryTextColor = this.f13056a.categoryTextColor();
            Intrinsics.checkNotNullExpressionValue(categoryTextColor, "options.categoryTextColor()");
            textView3.setTextColor(categoryTextColor.intValue());
            imageView = holder.a().mapplsNearbyCategoryIcon;
            categoryTintColor = this.f13056a.categoryTintColor();
            str = "options.categoryTintColor()";
        }
        Intrinsics.checkNotNullExpressionValue(categoryTintColor, str);
        imageView.setColorFilter(categoryTintColor.intValue());
        holder.a().getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.adapter.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                a.b(a.this, i, view);
            }
        });
    }

    public final void a(@NotNull NearbyView.a onCategorySelect) {
        Intrinsics.checkNotNullParameter(onCategorySelect, "onCategorySelect");
        this.c = onCategorySelect;
    }

    public final void a(@NotNull List<? extends CategoryCode> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.b = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<? extends CategoryCode> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final b onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.mappls_nearby_category_adapter, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…y_adapter, parent, false)");
        return new b((MapplsNearbyCategoryAdapterBinding) inflate);
    }
}
