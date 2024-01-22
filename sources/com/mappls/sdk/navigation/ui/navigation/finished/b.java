package com.mappls.sdk.navigation.ui.navigation.finished;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public class b extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<c> f13014a;

    /* loaded from: classes11.dex */
    public static class a extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f13015a;
        public TextView b;
        public ImageView c;
        public View d;

        public a(View view) {
            super(view);
            this.f13015a = (TextView) view.findViewById(R.id.heading_text_view);
            this.b = (TextView) view.findViewById(R.id.value_text_view);
            this.c = (ImageView) view.findViewById(R.id.image_view_logo);
            this.d = view.findViewById(R.id.view_color);
        }
    }

    public b(ArrayList<c> arrayList) {
        this.f13014a = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f13014a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ImageView imageView;
        int i2;
        viewHolder.getAdapterPosition();
        viewHolder.getAdapterPosition();
        a aVar = (a) viewHolder;
        c cVar = this.f13014a.get(i);
        aVar.f13015a.setText(cVar.f13016a);
        aVar.b.setText(cVar.b);
        String str = cVar.c;
        str.getClass();
        char c = 65535;
        switch (str.hashCode()) {
            case -1992012396:
                if (str.equals("duration")) {
                    c = 0;
                    break;
                }
                break;
            case 16775322:
                if (str.equals("avg_speed")) {
                    c = 1;
                    break;
                }
                break;
            case 288459765:
                if (str.equals("distance")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                aVar.d.setBackgroundColor(Color.parseColor("#e7392a"));
                imageView = aVar.c;
                i2 = R.drawable.mappls_navigation_ic_duration;
                break;
            case 1:
                aVar.d.setBackgroundColor(Color.parseColor("#e7392a"));
                imageView = aVar.c;
                i2 = R.drawable.mappls_navigation_ic_average_speed;
                break;
            case 2:
                aVar.d.setBackgroundColor(Color.parseColor("#f86624"));
                imageView = aVar.c;
                i2 = R.drawable.mappls_navigation_ic_distance;
                break;
            default:
                return;
        }
        imageView.setImageResource(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_trip_status_adapter, viewGroup, false));
    }
}
