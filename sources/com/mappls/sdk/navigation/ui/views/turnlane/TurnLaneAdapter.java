package com.mappls.sdk.navigation.ui.views.turnlane;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.services.api.directions.models.IntersectionLanes;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class TurnLaneAdapter extends RecyclerView.Adapter<d> {

    /* renamed from: a  reason: collision with root package name */
    public List<IntersectionLanes> f13039a = new ArrayList();
    public String b;

    public void addTurnLanes(List<IntersectionLanes> list, String str) {
        this.b = str;
        this.f13039a.clear();
        this.f13039a = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f13039a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(d dVar, int i) {
        dVar.f13041a.updateLaneView(this.f13039a.get(i), this.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.turn_lane_listitem_layout, viewGroup, false));
    }
}
