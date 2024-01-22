package com.coveiot.android.leonardo.onboarding.phonevalidation.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.utils.model.CountryCodeModel;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class CountryAdapter extends RecyclerView.Adapter<CountryHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<CountryCodeModel> f5279a;
    @NotNull
    public Context b;
    @NotNull
    public OnCountryClick c;

    /* loaded from: classes5.dex */
    public static final class CountryHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public TextView f5280a;
        @NotNull
        public TextView b;
        @NotNull
        public ImageView c;
        @NotNull
        public LinearLayout d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountryHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.countryName);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
            this.f5280a = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.countryCode);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.flag);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.ImageView");
            this.c = (ImageView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.root_layout);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.d = (LinearLayout) findViewById4;
        }

        @NotNull
        public final TextView getCountryCode$app_prodRelease() {
            return this.b;
        }

        @NotNull
        public final TextView getCountryName$app_prodRelease() {
            return this.f5280a;
        }

        @NotNull
        public final ImageView getFlag$app_prodRelease() {
            return this.c;
        }

        @NotNull
        public final LinearLayout getRootLayout$app_prodRelease() {
            return this.d;
        }

        public final void setCountryCode$app_prodRelease(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.b = textView;
        }

        public final void setCountryName$app_prodRelease(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.f5280a = textView;
        }

        public final void setFlag$app_prodRelease(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.c = imageView;
        }

        public final void setRootLayout$app_prodRelease(@NotNull LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.d = linearLayout;
        }
    }

    /* loaded from: classes5.dex */
    public interface OnCountryClick {
        void onCountryClick(@NotNull String str, @NotNull String str2);
    }

    public CountryAdapter(@NotNull ArrayList<CountryCodeModel> list, @NotNull Context context, @NotNull OnCountryClick onCountryClick) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onCountryClick, "onCountryClick");
        this.f5279a = list;
        this.b = context;
        this.c = onCountryClick;
    }

    public static final void b(CountryAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnCountryClick onCountryClick = this$0.c;
        String isoCode = this$0.f5279a.get(i).getIsoCode();
        Intrinsics.checkNotNullExpressionValue(isoCode, "list[position].isoCode");
        String countryCode = this$0.f5279a.get(i).getCountryCode();
        Intrinsics.checkNotNullExpressionValue(countryCode, "list[position].countryCode");
        onCountryClick.onCountryClick(isoCode, countryCode);
    }

    @NotNull
    public final Context getContext$app_prodRelease() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5279a.size();
    }

    @NotNull
    public final ArrayList<CountryCodeModel> getList$app_prodRelease() {
        return this.f5279a;
    }

    @NotNull
    public final OnCountryClick getOnCountryClick$app_prodRelease() {
        return this.c;
    }

    public final void setContext$app_prodRelease(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.b = context;
    }

    public final void setList$app_prodRelease(@NotNull ArrayList<CountryCodeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f5279a = arrayList;
    }

    public final void setOnCountryClick$app_prodRelease(@NotNull OnCountryClick onCountryClick) {
        Intrinsics.checkNotNullParameter(onCountryClick, "<set-?>");
        this.c = onCountryClick;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull CountryHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getCountryCode$app_prodRelease().setTextColor(Color.parseColor("#B0B0B0"));
        Locale locale = new Locale(Locale.ENGLISH.getLanguage(), this.f5279a.get(i).getCountryCode());
        TextView countryName$app_prodRelease = holder.getCountryName$app_prodRelease();
        countryName$app_prodRelease.setText("" + locale.getDisplayCountry());
        TextView countryCode$app_prodRelease = holder.getCountryCode$app_prodRelease();
        countryCode$app_prodRelease.setText('+' + this.f5279a.get(i).getIsoCode());
        Context context = this.b;
        String countryCode = this.f5279a.get(i).getCountryCode();
        Intrinsics.checkNotNullExpressionValue(countryCode, "list[position].countryCode");
        String lowerCase = countryCode.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        holder.getFlag$app_prodRelease().setImageDrawable(AppUtils.retriveCountryFlag(context, lowerCase));
        holder.getRootLayout$app_prodRelease().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.adapters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CountryAdapter.b(CountryAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public CountryHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new CountryHolder(view);
    }
}
