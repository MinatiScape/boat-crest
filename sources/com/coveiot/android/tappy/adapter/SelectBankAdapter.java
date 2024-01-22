package com.coveiot.android.tappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.SupportedBank;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SelectBankAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6005a;
    @NotNull
    public final List<SupportedBank> b;
    @NotNull
    public OnBankSelectedListener c;

    /* loaded from: classes7.dex */
    public interface OnBankSelectedListener {
        void onBankSelected(@NotNull SupportedBank supportedBank);
    }

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f6006a;
        @NotNull
        public TextView b;
        @NotNull
        public ConstraintLayout c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SelectBankAdapter selectBankAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.settings_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.settings_icon)");
            this.f6006a = (ImageView) findViewById;
            View findViewById2 = view.findViewById(R.id.settings_name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.settings_name)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.clRoot);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.clRoot)");
            this.c = (ConstraintLayout) findViewById3;
        }

        @NotNull
        public final ImageView getBankIcon() {
            return this.f6006a;
        }

        @NotNull
        public final TextView getBankName() {
            return this.b;
        }

        @NotNull
        public final ConstraintLayout getSelectBankICon() {
            return this.c;
        }

        public final void setBankName(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.b = textView;
        }

        public final void setSelectBankICon(@NotNull ConstraintLayout constraintLayout) {
            Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
            this.c = constraintLayout;
        }
    }

    public SelectBankAdapter(@NotNull Context context, @NotNull List<SupportedBank> dataList, @NotNull OnBankSelectedListener itemClickInterface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        this.f6005a = context;
        this.b = dataList;
        this.c = itemClickInterface;
    }

    public static final void b(SelectBankAdapter this$0, SupportedBank item, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.c.onBankSelected(item);
    }

    @NotNull
    public final Context getContext() {
        return this.f6005a;
    }

    @NotNull
    public final List<SupportedBank> getDataList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final SupportedBank supportedBank = this.b.get(i);
        holder.getBankName().setText(supportedBank.getBankName());
        holder.getBankIcon().setImageResource(supportedBank.getBankIcon());
        holder.getSelectBankICon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.adapter.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectBankAdapter.b(SelectBankAdapter.this, supportedBank, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_design_without_icon_padding, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
