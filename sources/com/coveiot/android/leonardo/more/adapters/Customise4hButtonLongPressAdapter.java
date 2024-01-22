package com.coveiot.android.leonardo.more.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.Item4hButtonViewBinding;
import com.coveiot.android.leonardo.more.listeners.Customise4hButtonLongPressListener;
import com.coveiot.android.leonardo.more.models.Customise4hButtonData;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class Customise4hButtonLongPressAdapter extends RecyclerView.Adapter<Customise4hButtonLongPressViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Customise4hButtonData> f5046a;
    @NotNull
    public final Customise4hButtonLongPressListener b;
    @Nullable
    public RadioButton c;
    @Nullable
    public Integer d;

    /* loaded from: classes5.dex */
    public final class Customise4hButtonLongPressViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final Item4hButtonViewBinding f5047a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Customise4hButtonLongPressViewHolder(@NotNull Customise4hButtonLongPressAdapter customise4hButtonLongPressAdapter, Item4hButtonViewBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5047a = binding;
        }

        @NotNull
        public final Item4hButtonViewBinding getBinding() {
            return this.f5047a;
        }
    }

    public Customise4hButtonLongPressAdapter(@NotNull ArrayList<Customise4hButtonData> data, @NotNull Customise4hButtonLongPressListener listener) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5046a = data;
        this.b = listener;
    }

    public static final void b(Customise4hButtonLongPressAdapter this$0, int i, Customise4hButtonLongPressViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Integer num = this$0.d;
        if (num != null) {
            ArrayList<Customise4hButtonData> arrayList = this$0.f5046a;
            Intrinsics.checkNotNull(num);
            int intValue = num.intValue();
            ArrayList<Customise4hButtonData> arrayList2 = this$0.f5046a;
            Integer num2 = this$0.d;
            Intrinsics.checkNotNull(num2);
            String actionId = arrayList2.get(num2.intValue()).getActionId();
            ArrayList<Customise4hButtonData> arrayList3 = this$0.f5046a;
            Integer num3 = this$0.d;
            Intrinsics.checkNotNull(num3);
            String name = arrayList3.get(num3.intValue()).getName();
            ArrayList<Customise4hButtonData> arrayList4 = this$0.f5046a;
            Integer num4 = this$0.d;
            Intrinsics.checkNotNull(num4);
            arrayList.set(intValue, new Customise4hButtonData(actionId, name, arrayList4.get(num4.intValue()).getFwCode(), true, false));
        }
        ArrayList<Customise4hButtonData> arrayList5 = this$0.f5046a;
        arrayList5.set(i, new Customise4hButtonData(arrayList5.get(i).getActionId(), this$0.f5046a.get(i).getName(), this$0.f5046a.get(i).getFwCode(), true, true));
        Customise4hButtonLongPressListener customise4hButtonLongPressListener = this$0.b;
        Customise4hButtonData customise4hButtonData = this$0.f5046a.get(i);
        Intrinsics.checkNotNullExpressionValue(customise4hButtonData, "data[position]");
        customise4hButtonLongPressListener.longPressSelectedItem(customise4hButtonData, this$0.f5046a, i);
        RadioButton radioButton = this$0.c;
        if (radioButton != null && radioButton != null) {
            radioButton.setChecked(false);
        }
        this$0.c = holder.getBinding().rbtnItems;
        this$0.d = Integer.valueOf(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5046a.size();
    }

    public final void updateData(@NotNull ArrayList<Customise4hButtonData> mData) {
        Intrinsics.checkNotNullParameter(mData, "mData");
        this.f5046a = mData;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final Customise4hButtonLongPressViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().rbtnItems.setText(this.f5046a.get(i).getName());
        if (this.f5046a.get(i).isEnabled() && !this.f5046a.get(i).isChecked()) {
            holder.getBinding().rbtnItems.setChecked(false);
            holder.getBinding().rbtnItems.setAlpha(1.0f);
            holder.getBinding().rbtnItems.setEnabled(true);
            ArrayList<Customise4hButtonData> arrayList = this.f5046a;
            arrayList.set(i, new Customise4hButtonData(arrayList.get(i).getActionId(), this.f5046a.get(i).getName(), this.f5046a.get(i).getFwCode(), true, false));
        } else if (this.f5046a.get(i).isEnabled() && this.f5046a.get(i).isChecked()) {
            holder.getBinding().rbtnItems.setChecked(true);
            this.c = holder.getBinding().rbtnItems;
            this.d = Integer.valueOf(i);
            holder.getBinding().rbtnItems.setAlpha(1.0f);
            holder.getBinding().rbtnItems.setEnabled(true);
            ArrayList<Customise4hButtonData> arrayList2 = this.f5046a;
            arrayList2.set(i, new Customise4hButtonData(arrayList2.get(i).getActionId(), this.f5046a.get(i).getName(), this.f5046a.get(i).getFwCode(), true, true));
        } else if (!this.f5046a.get(i).isEnabled() && !this.f5046a.get(i).isChecked()) {
            holder.getBinding().rbtnItems.setChecked(false);
            holder.getBinding().rbtnItems.setAlpha(0.5f);
            holder.getBinding().rbtnItems.setEnabled(false);
            ArrayList<Customise4hButtonData> arrayList3 = this.f5046a;
            arrayList3.set(i, new Customise4hButtonData(arrayList3.get(i).getActionId(), this.f5046a.get(i).getName(), this.f5046a.get(i).getFwCode(), false, false));
        }
        holder.getBinding().rbtnItems.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Customise4hButtonLongPressAdapter.b(Customise4hButtonLongPressAdapter.this, i, holder, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public Customise4hButtonLongPressViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Item4hButtonViewBinding inflate = Item4hButtonViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new Customise4hButtonLongPressViewHolder(this, inflate);
    }
}
