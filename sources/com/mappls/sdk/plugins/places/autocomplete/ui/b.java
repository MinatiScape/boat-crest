package com.mappls.sdk.plugins.places.autocomplete.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.plugins.places.R;
import com.mappls.sdk.plugins.places.autocomplete.model.d;
import com.mappls.sdk.services.api.autosuggest.model.SuggestedSearchAtlas;
import java.text.DecimalFormat;
import java.util.List;
/* loaded from: classes10.dex */
public final class b extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public final List<d> f13138a;
    public final Context b;
    @Nullable
    public ResultClickCallback c;

    /* loaded from: classes10.dex */
    public static class a extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f13139a;
        public final TextView b;
        public final TextView c;
        public final ImageView d;

        /* renamed from: com.mappls.sdk.plugins.places.autocomplete.ui.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class View$OnClickListenerC0660a implements View.OnClickListener {
            public final /* synthetic */ ResultClickCallback h;
            public final /* synthetic */ d i;

            public View$OnClickListenerC0660a(ResultClickCallback resultClickCallback, d dVar) {
                this.h = resultClickCallback;
                this.i = dVar;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.h.onClick(this.i);
            }
        }

        public a(View view) {
            super(view);
            this.f13139a = (TextView) view.findViewById(R.id.tv_place_name);
            this.b = (TextView) view.findViewById(R.id.tv_address);
            this.c = (TextView) view.findViewById(R.id.tv_distance);
            this.d = (ImageView) view.findViewById(R.id.iv_search_type);
        }

        public final void b(d dVar, ResultClickCallback resultClickCallback) {
            this.itemView.setOnClickListener(new View$OnClickListenerC0660a(resultClickCallback, dVar));
        }
    }

    /* renamed from: com.mappls.sdk.plugins.places.autocomplete.ui.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static class C0661b extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f13140a;

        /* renamed from: com.mappls.sdk.plugins.places.autocomplete.ui.b$b$a */
        /* loaded from: classes10.dex */
        public class a implements View.OnClickListener {
            public final /* synthetic */ ResultClickCallback h;
            public final /* synthetic */ d i;

            public a(ResultClickCallback resultClickCallback, d dVar) {
                this.h = resultClickCallback;
                this.i = dVar;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.h.onClick(this.i);
            }
        }

        public C0661b(View view) {
            super(view);
            this.f13140a = (TextView) view.findViewById(R.id.tv_suggested_name);
        }

        public final void b(d dVar, ResultClickCallback resultClickCallback) {
            this.itemView.setOnClickListener(new a(resultClickCallback, dVar));
        }
    }

    public b(Context context, List<d> list) {
        this.f13138a = list;
        this.b = context;
    }

    public final void a(ResultClickCallback resultClickCallback) {
        this.c = resultClickCallback;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<d> list = this.f13138a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return this.f13138a.get(i).c() == 4 ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ImageView imageView;
        Context context;
        int i2;
        TextView textView;
        String placeAddress;
        StringBuilder sb;
        if (!(viewHolder instanceof a)) {
            SuggestedSearchAtlas b = this.f13138a.get(i).b();
            StringBuilder sb2 = new StringBuilder(b.keyword);
            sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb2.append(b.identifier);
            sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb2.append(b.location);
            C0661b c0661b = (C0661b) viewHolder;
            c0661b.f13140a.setText(sb2);
            if (this.c != null) {
                c0661b.b(this.f13138a.get(i), this.c);
                return;
            }
            return;
        }
        if (this.c != null) {
            ((a) viewHolder).b(this.f13138a.get(i), this.c);
        }
        if (this.f13138a.get(i).c() == 1) {
            a aVar = (a) viewHolder;
            aVar.d.setImageDrawable(ContextCompat.getDrawable(aVar.itemView.getContext(), R.drawable.mappls_search_ic_history));
            aVar.f13139a.setTextColor(ContextCompat.getColor(this.b, R.color.mappls_search_plugins_bright_blue));
        } else {
            if (this.f13138a.get(i).c() == 2) {
                a aVar2 = (a) viewHolder;
                imageView = aVar2.d;
                context = aVar2.itemView.getContext();
                i2 = R.drawable.mappls_search_ic_place;
            } else if (this.f13138a.get(i).c() == 3) {
                a aVar3 = (a) viewHolder;
                imageView = aVar3.d;
                context = aVar3.itemView.getContext();
                i2 = R.drawable.mappls_search_ic_favorite;
            }
            imageView.setImageDrawable(ContextCompat.getDrawable(context, i2));
        }
        if (this.f13138a.get(i).c() != 3) {
            if (this.f13138a.get(i).d().placeName != null) {
                ((a) viewHolder).f13139a.setText(this.f13138a.get(i).d().placeName);
            } else {
                ((a) viewHolder).f13139a.setHeight(0);
            }
            if (this.f13138a.get(i).d().placeAddress != null) {
                textView = ((a) viewHolder).b;
                placeAddress = this.f13138a.get(i).d().placeAddress;
                textView.setText(placeAddress);
            }
            ((a) viewHolder).b.setHeight(0);
        } else {
            if (this.f13138a.get(i).a().getPlaceName() != null) {
                ((a) viewHolder).f13139a.setText(this.f13138a.get(i).a().getPlaceName());
            } else {
                ((a) viewHolder).f13139a.setHeight(0);
            }
            if (this.f13138a.get(i).a().getPlaceAddress() != null) {
                textView = ((a) viewHolder).b;
                placeAddress = this.f13138a.get(i).a().getPlaceAddress();
                textView.setText(placeAddress);
            }
            ((a) viewHolder).b.setHeight(0);
        }
        if (this.f13138a.get(i).c() != 2 || this.f13138a.get(i).d().distance == null || this.f13138a.get(i).d().distance.doubleValue() <= 0.0d) {
            ((a) viewHolder).c.setVisibility(8);
            return;
        }
        a aVar4 = (a) viewHolder;
        aVar4.c.setVisibility(0);
        TextView textView2 = aVar4.c;
        double doubleValue = this.f13138a.get(i).d().distance.doubleValue();
        if (doubleValue < 1000.0d) {
            sb = new StringBuilder();
            sb.append((int) doubleValue);
            sb.append(" mtr");
        } else {
            if (doubleValue < 10000.0d) {
                sb = new StringBuilder();
                sb.append(new DecimalFormat("#.#").format(doubleValue / 1000.0d));
            } else {
                sb = new StringBuilder();
                sb.append((int) (doubleValue / 1000.0d));
            }
            sb.append(" km");
        }
        textView2.setText(sb.toString());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 0 ? new C0661b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mappls_search_suggested_item_result, viewGroup, false)) : new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mappls_search_item_search_result, viewGroup, false));
    }
}
