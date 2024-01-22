package com.mappls.sdk.direction.ui.adapters;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryHeaderBinding;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryItemBinding;
import com.mappls.sdk.services.api.event.route.ReportCriteria;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class e extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public final List<com.mappls.sdk.direction.ui.event.d> f12572a;
    public b b;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ com.mappls.sdk.direction.ui.event.c h;

        public a(com.mappls.sdk.direction.ui.event.c cVar) {
            this.h = cVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (e.this.b != null) {
                e.this.b.a(this.h.b(), this.h.d());
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(String str, ArrayList<ReportDetails> arrayList);
    }

    /* loaded from: classes11.dex */
    public class c extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public MapplsDirectionRouteSummaryHeaderBinding f12573a;

        public c(@NonNull MapplsDirectionRouteSummaryHeaderBinding mapplsDirectionRouteSummaryHeaderBinding) {
            super(mapplsDirectionRouteSummaryHeaderBinding.getRoot());
            this.f12573a = mapplsDirectionRouteSummaryHeaderBinding;
        }
    }

    /* loaded from: classes11.dex */
    public class d extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public MapplsDirectionRouteSummaryItemBinding f12574a;

        public d(@NonNull MapplsDirectionRouteSummaryItemBinding mapplsDirectionRouteSummaryItemBinding) {
            super(mapplsDirectionRouteSummaryItemBinding.getRoot());
            this.f12574a = mapplsDirectionRouteSummaryItemBinding;
        }
    }

    public e(ArrayList arrayList) {
        this.f12572a = arrayList;
    }

    public final void a(b bVar) {
        this.b = bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<com.mappls.sdk.direction.ui.event.d> list = this.f12572a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return com.mappls.sdk.direction.ui.event.a.a(this.f12572a.get(i).a());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView;
        StringBuilder sb;
        String addedByName;
        TextView textView2;
        int i2 = 0;
        if (viewHolder instanceof c) {
            com.mappls.sdk.direction.ui.event.c cVar = (com.mappls.sdk.direction.ui.event.c) this.f12572a.get(i);
            c cVar2 = (c) viewHolder;
            Glide.with(cVar2.f12573a.mapplsDirectionImageEventType).m30load(cVar.c()).placeholder(R.drawable.mappls_direction_report_placeholder_48_px).into(cVar2.f12573a.mapplsDirectionImageEventType);
            cVar2.f12573a.textReportSummaryEventName.setText(cVar.b());
            cVar2.f12573a.textReportSummaryEventCount.setText(cVar.e() + "");
            if (cVar.f()) {
                textView2 = cVar2.f12573a.textReportSummaryViewAll;
            } else {
                textView2 = cVar2.f12573a.textReportSummaryViewAll;
                i2 = 8;
            }
            textView2.setVisibility(i2);
            cVar2.f12573a.textReportSummaryViewAll.setOnClickListener(new a(cVar));
        } else if (viewHolder instanceof d) {
            com.mappls.sdk.direction.ui.event.b bVar = (com.mappls.sdk.direction.ui.event.b) this.f12572a.get(i);
            d dVar = (d) viewHolder;
            Glide.with(dVar.f12574a.imageRouteSummaryEventType).m30load(bVar.b().getReportIcon(ReportCriteria.ICON_48_PX)).placeholder(R.drawable.mappls_direction_report_placeholder_48_px).into(dVar.f12574a.imageRouteSummaryEventType);
            TextView textView3 = dVar.f12574a.title;
            String childCategory = bVar.b().getChildCategory();
            String str = " at " + bVar.b().getAddress();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            SpannableString spannableString = new SpannableString(childCategory);
            spannableString.setSpan(new StyleSpan(1), 0, childCategory.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString);
            SpannableString spannableString2 = new SpannableString(str);
            spannableString2.setSpan(new StyleSpan(0), 0, str.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString2);
            textView3.setText(spannableStringBuilder);
            if (bVar.b().getUsersCount() != null && bVar.b().getUsersCount().longValue() > 1) {
                textView = dVar.f12574a.subTitle;
                sb = new StringBuilder("Reported By: ");
                sb.append(bVar.b().getUsersCount());
                addedByName = " People";
            } else if (bVar.b().getAddedByName() == null) {
                return;
            } else {
                textView = dVar.f12574a.subTitle;
                sb = new StringBuilder("Reported By: ");
                addedByName = bVar.b().getAddedByName();
            }
            sb.append(addedByName);
            textView.setText(sb.toString());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return i == com.mappls.sdk.direction.ui.event.a.a(1) ? new c((MapplsDirectionRouteSummaryHeaderBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_route_summary_header, viewGroup, false)) : i == com.mappls.sdk.direction.ui.event.a.a(2) ? new d((MapplsDirectionRouteSummaryItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_route_summary_item, viewGroup, false)) : new d((MapplsDirectionRouteSummaryItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_route_summary_item, viewGroup, false));
    }
}
