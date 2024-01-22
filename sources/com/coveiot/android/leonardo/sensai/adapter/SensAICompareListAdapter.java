package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ItemSensAiCompareListBinding;
import com.coveiot.android.leonardo.sensai.model.SensAICompareListItem;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covepreferences.UserDataManager;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SensAICompareListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5365a;
    @NotNull
    public final List<SensAICompareListItem> b;

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemSensAiCompareListBinding f5366a;
        public final /* synthetic */ SensAICompareListAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAICompareListAdapter sensAICompareListAdapter, ItemSensAiCompareListBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = sensAICompareListAdapter;
            this.f5366a = binding;
        }

        public final void bind(@NotNull SensAICompareListItem compareListItem) {
            String sb;
            Resources resources;
            Resources resources2;
            Intrinsics.checkNotNullParameter(compareListItem, "compareListItem");
            ItemSensAiCompareListBinding itemSensAiCompareListBinding = this.f5366a;
            SensAICompareListAdapter sensAICompareListAdapter = this.b;
            itemSensAiCompareListBinding.setCompareData(compareListItem);
            Glide.with(sensAICompareListAdapter.f5365a).m28load(compareListItem.getImage()).into(itemSensAiCompareListBinding.ivCompareImage);
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(sensAICompareListAdapter.f5365a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(mContext).isDistanceUnitInMile");
            if (isDistanceUnitInMile.booleanValue()) {
                StringBuilder sb2 = new StringBuilder();
                Context context = sensAICompareListAdapter.f5365a;
                sb2.append((context == null || (resources2 = context.getResources()) == null) ? null : resources2.getString(R.string.mil_per_hours));
                sb2.append(' ');
                sb = sb2.toString();
            } else {
                StringBuilder sb3 = new StringBuilder();
                Context context2 = sensAICompareListAdapter.f5365a;
                sb3.append((context2 == null || (resources = context2.getResources()) == null) ? null : resources.getString(R.string.km_per_hours));
                sb3.append(' ');
                sb = sb3.toString();
            }
            if (!m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.duration), false, 2, null)) {
                if (!m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.hit_perc), false, 2, null)) {
                    if (!m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.max_hand_speed), false, 2, null) && !m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.min_arm_speed), false, 2, null) && !m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.max_arm_speed), false, 2, null) && !m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.avg_arm_speed), false, 2, null) && !m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.avg_hand_speed), false, 2, null)) {
                        if (m.equals$default(compareListItem.getName(), sensAICompareListAdapter.f5365a.getString(R.string.avg_hr), false, 2, null)) {
                            TextView textView = itemSensAiCompareListBinding.tvCompareValue1;
                            StringBuilder sb4 = new StringBuilder();
                            Integer value1 = compareListItem.getValue1();
                            Intrinsics.checkNotNull(value1);
                            sb4.append(value1.intValue());
                            sb4.append(' ');
                            sb4.append(sensAICompareListAdapter.f5365a.getString(R.string.avg_bpm));
                            textView.setText(sb4.toString());
                            TextView textView2 = itemSensAiCompareListBinding.tvCompareValue2;
                            StringBuilder sb5 = new StringBuilder();
                            Integer value2 = compareListItem.getValue2();
                            Intrinsics.checkNotNull(value2);
                            sb5.append(value2.intValue());
                            sb5.append(' ');
                            sb5.append(sensAICompareListAdapter.f5365a.getString(R.string.avg_bpm));
                            textView2.setText(sb5.toString());
                        } else {
                            itemSensAiCompareListBinding.tvCompareValue1.setText(String.valueOf(compareListItem.getValue1()));
                            itemSensAiCompareListBinding.tvCompareValue2.setText(String.valueOf(compareListItem.getValue2()));
                        }
                    } else {
                        TextView textView3 = itemSensAiCompareListBinding.tvCompareValue1;
                        StringBuilder sb6 = new StringBuilder();
                        Integer value12 = compareListItem.getValue1();
                        Intrinsics.checkNotNull(value12);
                        sb6.append(value12.intValue());
                        sb6.append(' ');
                        sb6.append(sb);
                        textView3.setText(sb6.toString());
                        TextView textView4 = itemSensAiCompareListBinding.tvCompareValue2;
                        StringBuilder sb7 = new StringBuilder();
                        Integer value22 = compareListItem.getValue2();
                        Intrinsics.checkNotNull(value22);
                        sb7.append(value22.intValue());
                        sb7.append(' ');
                        sb7.append(sb);
                        textView4.setText(sb7.toString());
                    }
                } else {
                    TextView textView5 = itemSensAiCompareListBinding.tvCompareValue1;
                    StringBuilder sb8 = new StringBuilder();
                    Integer value13 = compareListItem.getValue1();
                    Intrinsics.checkNotNull(value13);
                    sb8.append(value13.intValue());
                    sb8.append(' ');
                    sb8.append(sensAICompareListAdapter.f5365a.getString(R.string.percentage_sign));
                    textView5.setText(sb8.toString());
                    TextView textView6 = itemSensAiCompareListBinding.tvCompareValue2;
                    StringBuilder sb9 = new StringBuilder();
                    Integer value23 = compareListItem.getValue2();
                    Intrinsics.checkNotNull(value23);
                    sb9.append(value23.intValue());
                    sb9.append(' ');
                    sb9.append(sensAICompareListAdapter.f5365a.getString(R.string.percentage_sign));
                    textView6.setText(sb9.toString());
                }
            } else {
                TextView textView7 = itemSensAiCompareListBinding.tvCompareValue1;
                StringBuilder sb10 = new StringBuilder();
                PayUtils payUtils = PayUtils.INSTANCE;
                Integer value14 = compareListItem.getValue1();
                Intrinsics.checkNotNull(value14);
                sb10.append(payUtils.formatSecondsInHHMMSS(value14.intValue()));
                sb10.append(' ');
                sb10.append(sensAICompareListAdapter.f5365a.getString(R.string.hrs));
                textView7.setText(sb10.toString());
                TextView textView8 = itemSensAiCompareListBinding.tvCompareValue2;
                StringBuilder sb11 = new StringBuilder();
                Integer value24 = compareListItem.getValue2();
                Intrinsics.checkNotNull(value24);
                sb11.append(payUtils.formatSecondsInHHMMSS(value24.intValue()));
                sb11.append(' ');
                sb11.append(sensAICompareListAdapter.f5365a.getString(R.string.hrs));
                textView8.setText(sb11.toString());
            }
            Integer value15 = compareListItem.getValue1();
            Intrinsics.checkNotNull(value15);
            int intValue = value15.intValue();
            Integer value25 = compareListItem.getValue2();
            Intrinsics.checkNotNull(value25);
            if (intValue > value25.intValue()) {
                itemSensAiCompareListBinding.tvCompareValue1.setTextColor(sensAICompareListAdapter.f5365a.getColor(R.color.color_039268));
                itemSensAiCompareListBinding.tvCompareValue2.setTextColor(sensAICompareListAdapter.f5365a.getColor(R.color.main_text_color));
                return;
            }
            itemSensAiCompareListBinding.tvCompareValue1.setTextColor(sensAICompareListAdapter.f5365a.getColor(R.color.main_text_color));
            itemSensAiCompareListBinding.tvCompareValue2.setTextColor(sensAICompareListAdapter.f5365a.getColor(R.color.color_039268));
        }
    }

    public SensAICompareListAdapter(@NotNull Context mContext, @NotNull List<SensAICompareListItem> compareListItem) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(compareListItem, "compareListItem");
        this.f5365a = mContext;
        this.b = compareListItem;
    }

    @NotNull
    public final List<SensAICompareListItem> getCompareListItem() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSensAiCompareListBinding inflate = ItemSensAiCompareListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
