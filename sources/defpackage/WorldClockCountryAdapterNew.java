package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covepreferences.data.WorldClockPrefData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import defpackage.WorldClockCountryAdapterNew;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* renamed from: WorldClockCountryAdapterNew  reason: default package */
/* loaded from: classes2.dex */
public final class WorldClockCountryAdapterNew extends RecyclerView.Adapter<CountryViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2a;
    @NotNull
    public final ArrayList<WorldClockPrefData> b;
    @NotNull
    public final OnCountryClick c;
    @NotNull
    public final ArrayList<WorldClockPrefData> d;
    @NotNull
    public final Set<Integer> e;

    /* renamed from: WorldClockCountryAdapterNew$CountryViewHolder */
    /* loaded from: classes2.dex */
    public final class CountryViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f3a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final ConstraintLayout c;
        public final /* synthetic */ WorldClockCountryAdapterNew d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountryViewHolder(@NotNull WorldClockCountryAdapterNew worldClockCountryAdapterNew, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.d = worldClockCountryAdapterNew;
            View findViewById = itemView.findViewById(R.id.countryName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.countryName)");
            this.f3a = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.selected_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.selected_icon)");
            this.b = (ImageView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.root_layout);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.root_layout)");
            this.c = (ConstraintLayout) findViewById3;
        }

        public static final void b(WorldClockCountryAdapterNew this$0, int i, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.b(i);
        }

        public final void bind(@NotNull WorldClockPrefData country, final int i) {
            Intrinsics.checkNotNullParameter(country, "country");
            TextView textView = this.f3a;
            textView.setText(country.getName() + " (" + country.getCountry() + HexStringBuilder.COMMENT_END_CHAR);
            c(i);
            ConstraintLayout constraintLayout = this.c;
            final WorldClockCountryAdapterNew worldClockCountryAdapterNew = this.d;
            constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WorldClockCountryAdapterNew.CountryViewHolder.b(WorldClockCountryAdapterNew.this, i, view);
                }
            });
        }

        public final void c(int i) {
            if (this.d.e.contains(Integer.valueOf(i))) {
                this.b.setImageResource(R.drawable.checkbox_selected);
            } else {
                this.b.setImageResource(R.drawable.checkbox_unselected);
            }
        }
    }

    /* renamed from: WorldClockCountryAdapterNew$OnCountryClick */
    /* loaded from: classes3.dex */
    public interface OnCountryClick {
        void onCountryClick(@NotNull WorldClockPrefData worldClockPrefData);
    }

    public WorldClockCountryAdapterNew(@NotNull Context context, @NotNull ArrayList<WorldClockPrefData> countryList, @NotNull OnCountryClick onCountryClick, @NotNull ArrayList<WorldClockPrefData> updatedCountryList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(countryList, "countryList");
        Intrinsics.checkNotNullParameter(onCountryClick, "onCountryClick");
        Intrinsics.checkNotNullParameter(updatedCountryList, "updatedCountryList");
        this.f2a = context;
        this.b = countryList;
        this.c = onCountryClick;
        this.d = updatedCountryList;
        this.e = new LinkedHashSet();
        int size = countryList.size();
        for (int i = 0; i < size; i++) {
            WorldClockPrefData worldClockPrefData = this.b.get(i);
            Intrinsics.checkNotNullExpressionValue(worldClockPrefData, "countryList[i]");
            if (a(worldClockPrefData)) {
                this.e.add(Integer.valueOf(i));
            }
        }
    }

    public final boolean a(WorldClockPrefData worldClockPrefData) {
        Iterator<WorldClockPrefData> it = this.d.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getId(), worldClockPrefData.getId())) {
                return true;
            }
        }
        return false;
    }

    public final void b(int i) {
        WorldClockPrefData worldClockPrefData = this.b.get(i);
        Intrinsics.checkNotNullExpressionValue(worldClockPrefData, "countryList[position]");
        WorldClockPrefData worldClockPrefData2 = worldClockPrefData;
        int size = this.e.size();
        Context context = this.f2a;
        Integer valueOf = context != null ? Integer.valueOf(PayUtils.INSTANCE.getMaximumWorldClockCount(context)) : null;
        Iterator<WorldClockPrefData> it = this.d.iterator();
        while (it.hasNext()) {
            if (m.equals$default(worldClockPrefData2.getName(), it.next().getName(), false, 2, null)) {
                Context context2 = this.f2a;
                Toast.makeText(context2, context2.getString(R.string.country_added_already), 0).show();
                return;
            }
        }
        if (this.e.contains(Integer.valueOf(i))) {
            this.e.remove(Integer.valueOf(i));
        } else {
            Intrinsics.checkNotNull(valueOf);
            if (size >= valueOf.intValue()) {
                Context context3 = this.f2a;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = context3.getString(R.string.max_ten_city);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.max_ten_city)");
                String format = String.format(string, Arrays.copyOf(new Object[]{valueOf}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                Toast.makeText(context3, format, 0).show();
                return;
            }
            this.e.add(Integer.valueOf(i));
        }
        OnCountryClick onCountryClick = this.c;
        WorldClockPrefData worldClockPrefData3 = this.b.get(i);
        Intrinsics.checkNotNullExpressionValue(worldClockPrefData3, "countryList[position]");
        onCountryClick.onCountryClick(worldClockPrefData3);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull CountryViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        WorldClockPrefData worldClockPrefData = this.b.get(i);
        Intrinsics.checkNotNullExpressionValue(worldClockPrefData, "countryList[position]");
        holder.bind(worldClockPrefData, i);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public CountryViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.world_clock_coutry_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new CountryViewHolder(this, view);
    }
}
