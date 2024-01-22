package com.coveiot.android.tappy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.TransactionBeanInfo;
import com.coveiot.android.tappy.utils.Utils;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TransactionHistoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6007a;
    @NotNull
    public final List<TransactionBeanInfo> b;
    @Nullable
    public ItemClickListener c;
    public int d;
    public int e;

    /* loaded from: classes7.dex */
    public interface ItemClickListener {
        void onViewDetail(@NotNull TransactionBeanInfo transactionBeanInfo, int i);
    }

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f6008a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final ConstraintLayout e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull TransactionHistoryAdapter transactionHistoryAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.tv_dt);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.tv_dt)");
            this.f6008a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.tvPaidTo);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.tvPaidTo)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.tv_transactionStatus);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.tv_transactionStatus)");
            this.c = (TextView) findViewById3;
            View findViewById4 = view.findViewById(R.id.tv_transactionAmount);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.tv_transactionAmount)");
            this.d = (TextView) findViewById4;
            View findViewById5 = view.findViewById(R.id.clRoot);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.clRoot)");
            this.e = (ConstraintLayout) findViewById5;
        }

        @NotNull
        public final ConstraintLayout getClRoot() {
            return this.e;
        }

        @NotNull
        public final TextView getTv_MerchantName() {
            return this.b;
        }

        @NotNull
        public final TextView getTv_amount() {
            return this.d;
        }

        @NotNull
        public final TextView getTv_dt() {
            return this.f6008a;
        }

        @NotNull
        public final TextView getTv_transactionStatus() {
            return this.c;
        }
    }

    public TransactionHistoryAdapter(@NotNull Context context, @NotNull List<TransactionBeanInfo> dataList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        this.f6007a = context;
        this.b = dataList;
        this.d = -1;
        this.e = -1;
    }

    public static final void b(TransactionHistoryAdapter this$0, int i, ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        ItemClickListener itemClickListener = this$0.c;
        if (itemClickListener != null) {
            List<TransactionBeanInfo> list = this$0.b;
            TransactionBeanInfo transactionBeanInfo = list != null ? list.get(i) : null;
            Intrinsics.checkNotNull(transactionBeanInfo);
            itemClickListener.onViewDetail(transactionBeanInfo, i);
        }
        this$0.e = this$0.d;
        this$0.d = holder.getAbsoluteAdapterPosition();
        this$0.notifyItemChanged(this$0.e);
        this$0.notifyItemChanged(this$0.d);
    }

    @NotNull
    public final Context getContext() {
        return this.f6007a;
    }

    @NotNull
    public final List<TransactionBeanInfo> getDataList() {
        return this.b;
    }

    @Nullable
    public final ItemClickListener getItemClickListener() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("check");
        List<TransactionBeanInfo> list = this.b;
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        sb.append(valueOf.intValue());
        Log.i("TransactionHistoryAdapter", sb.toString());
        List<TransactionBeanInfo> list2 = this.b;
        Integer valueOf2 = list2 != null ? Integer.valueOf(list2.size()) : null;
        Intrinsics.checkNotNull(valueOf2);
        return valueOf2.intValue();
    }

    public final int getLastSelectedPosition() {
        return this.e;
    }

    public final int getSelectedPosition() {
        return this.d;
    }

    public final void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.c = itemClickListener;
    }

    public final void setLastSelectedPosition(int i) {
        this.e = i;
    }

    public final void setSelectedPosition(int i) {
        this.d = i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ViewHolder holder, final int i) {
        TransactionBeanInfo transactionBeanInfo;
        TransactionBeanInfo transactionBeanInfo2;
        TransactionBeanInfo transactionBeanInfo3;
        TransactionBeanInfo transactionBeanInfo4;
        TransactionBeanInfo transactionBeanInfo5;
        TransactionBeanInfo transactionBeanInfo6;
        TransactionBeanInfo transactionBeanInfo7;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Calendar calendar = Calendar.getInstance();
        List<TransactionBeanInfo> list = this.b;
        String str = null;
        Long transactionDate = (list == null || (transactionBeanInfo7 = list.get(i)) == null) ? null : transactionBeanInfo7.getTransactionDate();
        Intrinsics.checkNotNull(transactionDate);
        calendar.setTimeInMillis(transactionDate.longValue());
        TextView tv_dt = holder.getTv_dt();
        Utils.Companion companion = Utils.Companion;
        Context context = this.f6007a;
        List<TransactionBeanInfo> list2 = this.b;
        Long transactionDate2 = (list2 == null || (transactionBeanInfo6 = list2.get(i)) == null) ? null : transactionBeanInfo6.getTransactionDate();
        Intrinsics.checkNotNull(transactionDate2);
        tv_dt.setText(companion.formatTimestamp(context, transactionDate2.longValue(), true, true));
        TextView tv_transactionStatus = holder.getTv_transactionStatus();
        List<TransactionBeanInfo> list3 = this.b;
        tv_transactionStatus.setText((list3 == null || (transactionBeanInfo5 = list3.get(i)) == null) ? null : transactionBeanInfo5.getTransactionStatus());
        TextView tv_amount = holder.getTv_amount();
        StringBuilder sb = new StringBuilder();
        String currency = this.b.get(i).getCurrency();
        sb.append(currency != null ? companion.getCurrencySymbol(currency) : null);
        List<TransactionBeanInfo> list4 = this.b;
        sb.append((list4 == null || (transactionBeanInfo4 = list4.get(i)) == null) ? null : transactionBeanInfo4.getAmount());
        tv_amount.setText(sb.toString());
        TextView tv_MerchantName = holder.getTv_MerchantName();
        List<TransactionBeanInfo> list5 = this.b;
        tv_MerchantName.setText((list5 == null || (transactionBeanInfo3 = list5.get(i)) == null) ? null : transactionBeanInfo3.getMerchantName());
        List<TransactionBeanInfo> list6 = this.b;
        String transactionStatus = (list6 == null || (transactionBeanInfo2 = list6.get(i)) == null) ? null : transactionBeanInfo2.getTransactionStatus();
        if (!(transactionStatus == null || transactionStatus.length() == 0)) {
            List<TransactionBeanInfo> list7 = this.b;
            if (list7 != null && (transactionBeanInfo = list7.get(i)) != null) {
                str = transactionBeanInfo.getTransactionStatus();
            }
            if (str != null) {
                switch (str.hashCode()) {
                    case -644370343:
                        if (str.equals("Settled")) {
                            holder.getTv_transactionStatus().setText("Settled");
                            break;
                        }
                        break;
                    case -199856670:
                        if (str.equals("Reversed")) {
                            holder.getTv_transactionStatus().setText("Reversed");
                            break;
                        }
                        break;
                    case 632840270:
                        if (str.equals("Declined")) {
                            holder.getTv_transactionStatus().setText(this.f6007a.getString(R.string.declined));
                            break;
                        }
                        break;
                    case 982065527:
                        if (str.equals("Pending")) {
                            holder.getTv_transactionStatus().setText(this.f6007a.getString(R.string.in_progress));
                            break;
                        }
                        break;
                }
            }
            holder.getTv_transactionStatus().setVisibility(8);
        } else {
            holder.getTv_transactionStatus().setVisibility(8);
        }
        holder.getClRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.adapter.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TransactionHistoryAdapter.b(TransactionHistoryAdapter.this, i, holder, view);
            }
        });
        if (this.d == holder.getAbsoluteAdapterPosition()) {
            holder.getClRoot().setBackgroundResource(R.drawable.rounded_dialog_background_transaction_ff3038);
        } else {
            holder.getClRoot().setBackgroundResource(R.drawable.rounded_dialog_background_transaction);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Log.i("TransactionHistoryAdapter", "check open");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_item_new, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
