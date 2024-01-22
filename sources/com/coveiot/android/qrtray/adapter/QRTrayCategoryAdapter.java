package com.coveiot.android.qrtray.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.adapter.QRTrayCategoryAdapter;
import com.coveiot.android.qrtray.databinding.ListItemQrTrayCategoryLayoutBinding;
import com.coveiot.android.qrtray.utils.ViewUtilsKt;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class QRTrayCategoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5570a;
    public final boolean b;
    @NotNull
    public final CategoryClickListener c;
    @NotNull
    public List<QRTrayCategoriesRes.QRItem> d;
    public int e;

    /* loaded from: classes5.dex */
    public interface CategoryClickListener {
        void categoryClick(@NotNull QRTrayCategoriesRes.QRItem qRItem, int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemQrTrayCategoryLayoutBinding f5571a;
        public final /* synthetic */ QRTrayCategoryAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull QRTrayCategoryAdapter qRTrayCategoryAdapter, ListItemQrTrayCategoryLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = qRTrayCategoryAdapter;
            this.f5571a = binding;
        }

        public static final void b(QRTrayCategoryAdapter this$0, QRTrayCategoriesRes.QRItem categoryData, ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(categoryData, "$categoryData");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.getListener().categoryClick(categoryData, this$1.getAbsoluteAdapterPosition());
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final QRTrayCategoriesRes.QRItem categoryData) {
            Intrinsics.checkNotNullParameter(categoryData, "categoryData");
            ListItemQrTrayCategoryLayoutBinding listItemQrTrayCategoryLayoutBinding = this.f5571a;
            final QRTrayCategoryAdapter qRTrayCategoryAdapter = this.b;
            listItemQrTrayCategoryLayoutBinding.setQrCategoryData(categoryData);
            ImageView ivCategory = listItemQrTrayCategoryLayoutBinding.ivCategory;
            Intrinsics.checkNotNullExpressionValue(ivCategory, "ivCategory");
            ViewUtilsKt.goneIF(ivCategory, Intrinsics.areEqual(categoryData.getCategoryId(), "ALL"));
            if (qRTrayCategoryAdapter.e == getAbsoluteAdapterPosition()) {
                this.itemView.setBackgroundResource(qRTrayCategoryAdapter.getSelectedBgDrawable());
                TextView textView = listItemQrTrayCategoryLayoutBinding.tvCategory;
                textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.white));
                textView.setTypeface(listItemQrTrayCategoryLayoutBinding.tvCategory.getTypeface(), 1);
            } else {
                this.itemView.setBackgroundResource(qRTrayCategoryAdapter.getUnSelectedBgDrawable());
                TextView textView2 = listItemQrTrayCategoryLayoutBinding.tvCategory;
                textView2.setTextColor(ContextCompat.getColor(textView2.getContext(), R.color.grey_text_color));
                textView2.setTypeface(listItemQrTrayCategoryLayoutBinding.tvCategory.getTypeface(), 0);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.adapter.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QRTrayCategoryAdapter.ViewHolder.b(QRTrayCategoryAdapter.this, categoryData, this, view);
                }
            });
        }
    }

    public QRTrayCategoryAdapter(@NotNull Context context, boolean z, @NotNull CategoryClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5570a = context;
        this.b = z;
        this.c = listener;
        this.d = new ArrayList();
        this.e = -1;
    }

    @NotNull
    public final Context getContext() {
        return this.f5570a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    @NotNull
    public final CategoryClickListener getListener() {
        return this.c;
    }

    public final int getSelectedBgDrawable() {
        if (this.b) {
            return R.drawable.rounded_corner_grey_with_red_border;
        }
        return R.drawable.red_gradient_bottom_border_background;
    }

    public final int getUnSelectedBgDrawable() {
        if (this.b) {
            return R.drawable.rounded_grey_with_border_background_40dp_symptoms;
        }
        return R.color.transparent;
    }

    public final boolean isSaving() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setCategoryList(@NotNull List<? extends QRTrayCategoriesRes.QRItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.d = TypeIntrinsics.asMutableList(list);
        notifyDataSetChanged();
    }

    public final void setSelectedCategory(int i) {
        this.e = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.d.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemQrTrayCategoryLayoutBinding inflate = ListItemQrTrayCategoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
