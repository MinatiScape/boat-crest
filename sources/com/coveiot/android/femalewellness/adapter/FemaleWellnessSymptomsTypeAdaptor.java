package com.coveiot.android.femalewellness.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.adapter.FemaleWellnessSymptomsAdaptor;
import com.coveiot.android.femalewellness.model.FemaleWellnessSymptomsType;
import com.coveiot.android.femalewellness.utils.ViewUtilsKt;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FemaleWellnessSymptomsTypeAdaptor extends RecyclerView.Adapter<FemaleWellnessSymptomsHolder> implements FemaleWellnessSymptomsAdaptor.onItemClickListener {
    @NotNull
    public final Context h;
    @NotNull
    public final Activity i;
    @NotNull
    public List<FemaleWellnessSymptomsType> j;
    @NotNull
    public final onItemClickListener k;
    public final boolean l;
    @Nullable
    public onItemClickListener m;
    public FemaleWellnessSymptomsAdaptor symptomsAdapter;

    /* loaded from: classes4.dex */
    public static final class FemaleWellnessSymptomsHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4389a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final RecyclerView d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FemaleWellnessSymptomsHolder(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            TextView textView = (TextView) view.findViewById(R.id.tvSymptomType);
            Intrinsics.checkNotNullExpressionValue(textView, "view.tvSymptomType");
            this.f4389a = textView;
            TextView textView2 = (TextView) view.findViewById(R.id.tvRecordedDate);
            Intrinsics.checkNotNullExpressionValue(textView2, "view.tvRecordedDate");
            this.b = textView2;
            TextView textView3 = (TextView) view.findViewById(R.id.tvSymptomTypeInfo);
            Intrinsics.checkNotNullExpressionValue(textView3, "view.tvSymptomTypeInfo");
            this.c = textView3;
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvSymptoms);
            Intrinsics.checkNotNullExpressionValue(recyclerView, "view.rvSymptoms");
            this.d = recyclerView;
        }

        @NotNull
        public final RecyclerView getRvSymptoms() {
            return this.d;
        }

        @NotNull
        public final TextView getTvRecordedDate() {
            return this.b;
        }

        @NotNull
        public final TextView getTvSymptomType() {
            return this.f4389a;
        }

        @NotNull
        public final TextView getTvSymptomTypeInfo() {
            return this.c;
        }
    }

    /* loaded from: classes4.dex */
    public interface onItemClickListener {
        void onTypeItemClick(int i, @NotNull String str, @NotNull String str2);
    }

    public /* synthetic */ FemaleWellnessSymptomsTypeAdaptor(Context context, Activity activity, List list, onItemClickListener onitemclicklistener, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, activity, list, onitemclicklistener, (i & 16) != 0 ? false : z);
    }

    @NotNull
    public final Activity getActivity() {
        return this.i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.j.size();
    }

    @Nullable
    public final onItemClickListener getListener() {
        return this.m;
    }

    @NotNull
    public final List<FemaleWellnessSymptomsType> getSymptomTypeList() {
        return this.j;
    }

    @NotNull
    public final FemaleWellnessSymptomsAdaptor getSymptomsAdapter() {
        FemaleWellnessSymptomsAdaptor femaleWellnessSymptomsAdaptor = this.symptomsAdapter;
        if (femaleWellnessSymptomsAdaptor != null) {
            return femaleWellnessSymptomsAdaptor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symptomsAdapter");
        return null;
    }

    @Override // com.coveiot.android.femalewellness.adapter.FemaleWellnessSymptomsAdaptor.onItemClickListener
    public void onItemClick(int i, @NotNull String symptomType, @NotNull String symptomValue) {
        Intrinsics.checkNotNullParameter(symptomType, "symptomType");
        Intrinsics.checkNotNullParameter(symptomValue, "symptomValue");
        onItemClickListener onitemclicklistener = this.m;
        if (onitemclicklistener != null) {
            onitemclicklistener.onTypeItemClick(i, symptomType, symptomValue);
        }
    }

    public final void setListener(@Nullable onItemClickListener onitemclicklistener) {
        this.m = onitemclicklistener;
    }

    public final void setSymptomTypeList(@NotNull List<FemaleWellnessSymptomsType> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.j = list;
    }

    public final void setSymptomsAdapter(@NotNull FemaleWellnessSymptomsAdaptor femaleWellnessSymptomsAdaptor) {
        Intrinsics.checkNotNullParameter(femaleWellnessSymptomsAdaptor, "<set-?>");
        this.symptomsAdapter = femaleWellnessSymptomsAdaptor;
    }

    public FemaleWellnessSymptomsTypeAdaptor(@NotNull Context mContext, @NotNull Activity activity, @NotNull List<FemaleWellnessSymptomsType> symptomTypeList, @NotNull onItemClickListener itemListener, boolean z) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(symptomTypeList, "symptomTypeList");
        Intrinsics.checkNotNullParameter(itemListener, "itemListener");
        this.h = mContext;
        this.i = activity;
        this.j = symptomTypeList;
        this.k = itemListener;
        this.l = z;
        this.m = itemListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull FemaleWellnessSymptomsHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvSymptomType().setText(this.j.get(i).getSymptomType());
        holder.getTvRecordedDate().setText(this.j.get(i).getSymptomRecordedDate());
        holder.getTvSymptomTypeInfo().setText(this.j.get(i).getSymptomTypeInfo());
        if (m.equals(this.j.get(i).getSymptomType(), Constants.SYMPTOMS.getValue(), true)) {
            ViewUtilsKt.gone(holder.getTvRecordedDate());
            RecyclerView rvSymptoms = holder.getRvSymptoms();
            Intrinsics.checkNotNull(rvSymptoms);
            rvSymptoms.setLayoutManager(new GridLayoutManager(this.h, 3));
            setSymptomsAdapter(new FemaleWellnessSymptomsAdaptor(this.h, this.i, this.j.get(i).getSymptomType(), this.j.get(i).getSymptomsList(), this, this.l));
            RecyclerView rvSymptoms2 = holder.getRvSymptoms();
            Intrinsics.checkNotNull(rvSymptoms2);
            rvSymptoms2.setAdapter(getSymptomsAdapter());
            ViewUtilsKt.gone(holder.getTvSymptomTypeInfo());
            return;
        }
        ViewUtilsKt.visible(holder.getTvSymptomTypeInfo());
        ViewUtilsKt.visible(holder.getTvRecordedDate());
        RecyclerView rvSymptoms3 = holder.getRvSymptoms();
        Intrinsics.checkNotNull(rvSymptoms3);
        rvSymptoms3.setLayoutManager(new LinearLayoutManager(this.h, 0, false));
        setSymptomsAdapter(new FemaleWellnessSymptomsAdaptor(this.h, this.i, this.j.get(i).getSymptomType(), this.j.get(i).getSymptomsList(), this, this.l));
        RecyclerView rvSymptoms4 = holder.getRvSymptoms();
        Intrinsics.checkNotNull(rvSymptoms4);
        rvSymptoms4.setAdapter(getSymptomsAdapter());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FemaleWellnessSymptomsHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_female_wellness_header, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(viewGroup.getContex…le_wellness_header, null)");
        return new FemaleWellnessSymptomsHolder(inflate);
    }
}
