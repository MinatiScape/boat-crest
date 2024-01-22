package com.coveiot.android.tappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.RegCardBeanInfo;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class RegisteredStrapAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6003a;
    @NotNull
    public final StrapItemClickListener b;
    @NotNull
    public final ArrayList<RegStrapBeanInfo> c;

    /* loaded from: classes7.dex */
    public interface StrapItemClickListener {
        void editStrapName(@NotNull RegStrapBeanInfo regStrapBeanInfo, int i);

        void onDeleteClicked(int i);
    }

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f6004a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final Button e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull RegisteredStrapAdapter registeredStrapAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.strap_type_img);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.strap_type_img)");
            this.f6004a = (ImageView) findViewById;
            View findViewById2 = view.findViewById(R.id.strap_type);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.strap_type)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.card_bank_name_txt);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.card_bank_name_txt)");
            this.c = (TextView) findViewById3;
            View findViewById4 = view.findViewById(R.id.card_number_txt);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.card_number_txt)");
            this.d = (TextView) findViewById4;
            View findViewById5 = this.itemView.findViewById(R.id.btnDeleteStrap);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.btnDeleteStrap)");
            this.e = (Button) findViewById5;
        }

        @NotNull
        public final Button getBtnDeleteStrap() {
            return this.e;
        }

        @NotNull
        public final TextView getCardBankName() {
            return this.c;
        }

        @NotNull
        public final TextView getCardNumber() {
            return this.d;
        }

        @NotNull
        public final TextView getStrapTypeColourName() {
            return this.b;
        }

        @NotNull
        public final ImageView getStrapTypeImage() {
            return this.f6004a;
        }
    }

    public RegisteredStrapAdapter(@NotNull Context context, @NotNull StrapItemClickListener clickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        this.f6003a = context;
        this.b = clickListener;
        this.c = new ArrayList<>();
    }

    public static final void c(RegisteredStrapAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.onDeleteClicked(i);
    }

    public static final void d(RegisteredStrapAdapter this$0, RegStrapBeanInfo strap, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(strap, "$strap");
        this$0.b.editStrapName(strap, i);
    }

    @NotNull
    public final StrapItemClickListener getClickListener() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f6003a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<RegStrapBeanInfo> arrayList = this.c;
        Integer valueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        return valueOf.intValue();
    }

    public final void setData(@NotNull ArrayList<RegStrapBeanInfo> regStrapBeanInfoList) {
        Intrinsics.checkNotNullParameter(regStrapBeanInfoList, "regStrapBeanInfoList");
        this.c.clear();
        this.c.addAll(regStrapBeanInfoList);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        RegStrapBeanInfo regStrapBeanInfo = this.c.get(i);
        Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "dataList[position]");
        final RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
        holder.getStrapTypeColourName().setText(regStrapBeanInfo2.getFriendlyName());
        if (regStrapBeanInfo2.isCardAdded()) {
            TextView cardBankName = holder.getCardBankName();
            RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
            Intrinsics.checkNotNull(regCardBeanInfo);
            cardBankName.setText(regCardBeanInfo.getPaymentNetworkName());
            RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo2.getRegCardBeanInfo();
            Intrinsics.checkNotNull(regCardBeanInfo2);
            String string = holder.itemView.getContext().getString(R.string.masked_card_number, regCardBeanInfo2.getLast4());
            Intrinsics.checkNotNullExpressionValue(string, "holder.itemView.context.…d_number, lastFourDigits)");
            holder.getCardNumber().setText(string);
        }
        holder.getBtnDeleteStrap().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.adapter.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RegisteredStrapAdapter.c(RegisteredStrapAdapter.this, i, view);
            }
        });
        holder.getStrapTypeColourName().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.adapter.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RegisteredStrapAdapter.d(RegisteredStrapAdapter.this, regStrapBeanInfo2, i, view);
            }
        });
        Glide.with(this.f6003a).m30load(regStrapBeanInfo2.getImageUrl()).into(holder.getStrapTypeImage());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strap_card_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
