package com.mappls.sdk.category.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.category.R;
import com.mappls.sdk.category.fragment.CategorySearchFragment;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.collections.e;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
@SourceDebugExtension({"SMAP\nCategorySearchAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CategorySearchAdapter.kt\ncom/mappls/sdk/category/adapters/CategorySearchAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,228:1\n1#2:229\n766#3:230\n857#3,2:231\n*S KotlinDebug\n*F\n+ 1 CategorySearchAdapter.kt\ncom/mappls/sdk/category/adapters/CategorySearchAdapter\n*L\n149#1:230\n149#1:231,2\n*E\n"})
/* loaded from: classes11.dex */
public final class a extends RecyclerView.Adapter<b> implements Filterable {
    public final int h;
    @NotNull
    public final SearchCategoryUIOption i;
    @NotNull
    public final InterfaceC0609a j;
    public boolean k;
    @NotNull
    public List<? extends CategoryCode> l;
    @NotNull
    public List<? extends CategoryCode> m;
    public final int n;
    public int o;

    /* renamed from: com.mappls.sdk.category.adapters.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0609a {
        void a(@NotNull List<? extends CategoryCode> list);
    }

    /* loaded from: classes11.dex */
    public final class b extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f12537a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(@NotNull a aVar, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.mappls_category_icon_image_view);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.m…category_icon_image_view)");
            ImageView imageView = (ImageView) findViewById;
            this.f12537a = imageView;
            View findViewById2 = view.findViewById(R.id.mappls_category_name_text_view);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.m…_category_name_text_view)");
            TextView textView = (TextView) findViewById2;
            this.b = textView;
            View findViewById3 = view.findViewById(R.id.mappls_category_icon_selected_image_view);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.m…icon_selected_image_view)");
            ImageView imageView2 = (ImageView) findViewById3;
            this.c = imageView2;
            Integer iconTintColor = com.mappls.sdk.category.a.d().iconTintColor();
            Intrinsics.checkNotNullExpressionValue(iconTintColor, "MapplsCategoryWidget.sea…yUIOption.iconTintColor()");
            imageView.setColorFilter(iconTintColor.intValue(), PorterDuff.Mode.SRC_IN);
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), R.drawable.mappls_category_shape_circle);
            if (drawable != null) {
                Integer iconTintColor2 = aVar.i.iconTintColor();
                Intrinsics.checkNotNullExpressionValue(iconTintColor2, "uiOption.iconTintColor()");
                drawable.setColorFilter(new PorterDuffColorFilter(iconTintColor2.intValue(), PorterDuff.Mode.SRC_IN));
            }
            imageView.setBackground(drawable);
            Integer iconTintColor3 = aVar.i.iconTintColor();
            Intrinsics.checkNotNullExpressionValue(iconTintColor3, "uiOption.iconTintColor()");
            imageView2.setColorFilter(iconTintColor3.intValue(), PorterDuff.Mode.SRC_IN);
            Integer itemTextColor = aVar.i.itemTextColor();
            Intrinsics.checkNotNullExpressionValue(itemTextColor, "uiOption.itemTextColor()");
            textView.setTextColor(itemTextColor.intValue());
        }

        @NotNull
        public final ImageView a() {
            return this.f12537a;
        }

        @NotNull
        public final TextView b() {
            return this.b;
        }

        @NotNull
        public final ImageView c() {
            return this.c;
        }
    }

    /* loaded from: classes11.dex */
    public static final class c extends Filter {
        public c() {
        }

        @Override // android.widget.Filter
        @NotNull
        public final Filter.FilterResults performFiltering(@NotNull CharSequence constraint) {
            List list;
            Intrinsics.checkNotNullParameter(constraint, "constraint");
            String obj = constraint.toString();
            if (obj.length() > 0) {
                a.this.o = 101;
            }
            a aVar = a.this;
            if (obj.length() == 0) {
                list = a.this.l;
            } else {
                ArrayList arrayList = new ArrayList();
                for (CategoryCode categoryCode : a.this.l) {
                    String category = categoryCode.getCategory();
                    Boolean bool = null;
                    if (category != null) {
                        Locale locale = Locale.ROOT;
                        String lowerCase = category.toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        if (lowerCase != null) {
                            String lowerCase2 = obj.toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            bool = Boolean.valueOf(StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null));
                        }
                    }
                    Intrinsics.checkNotNull(bool);
                    if (bool.booleanValue()) {
                        arrayList.add(categoryCode);
                    }
                }
                list = arrayList;
            }
            aVar.m = list;
            Filter.FilterResults filterResults = new Filter.FilterResults();
            filterResults.values = a.this.m;
            return filterResults;
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0022, code lost:
            if ((r3.length() > 0) == false) goto L11;
         */
        @Override // android.widget.Filter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void publishResults(@org.jetbrains.annotations.Nullable java.lang.CharSequence r3, @org.jetbrains.annotations.NotNull android.widget.Filter.FilterResults r4) {
            /*
                r2 = this;
                java.lang.String r0 = "results"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                com.mappls.sdk.category.adapters.a r0 = com.mappls.sdk.category.adapters.a.this
                java.lang.Object r4 = r4.values
                java.lang.String r1 = "null cannot be cast to non-null type java.util.ArrayList<com.mappls.sdk.nearby.plugin.CategoryCode>"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r1)
                java.util.ArrayList r4 = (java.util.ArrayList) r4
                com.mappls.sdk.category.adapters.a.a(r0, r4)
                com.mappls.sdk.category.adapters.a r4 = com.mappls.sdk.category.adapters.a.this
                r0 = 0
                r1 = 1
                if (r3 == 0) goto L24
                int r3 = r3.length()
                if (r3 <= 0) goto L21
                r3 = r1
                goto L22
            L21:
                r3 = r0
            L22:
                if (r3 != 0) goto L25
            L24:
                r0 = r1
            L25:
                com.mappls.sdk.category.adapters.a.a(r4, r0)
                com.mappls.sdk.category.adapters.a r3 = com.mappls.sdk.category.adapters.a.this
                r3.notifyDataSetChanged()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.category.adapters.a.c.publishResults(java.lang.CharSequence, android.widget.Filter$FilterResults):void");
        }
    }

    public a(int i, @NotNull SearchCategoryUIOption uiOption, @NotNull CategorySearchFragment.b categorySelection) {
        Intrinsics.checkNotNullParameter(uiOption, "uiOption");
        Intrinsics.checkNotNullParameter(categorySelection, "categorySelection");
        this.h = i;
        this.i = uiOption;
        this.j = categorySelection;
        this.k = true;
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.n = 4;
        this.o = 100;
    }

    public static final void b(a this$0, b holder, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        if (this$0.getItemViewType(holder.getAdapterPosition()) == 2) {
            this$0.o = this$0.o == 100 ? 101 : 100;
            this$0.notifyDataSetChanged();
        } else if (this$0.h == 1) {
            this$0.j.a(e.listOf(this$0.m.get(i)));
        } else if (this$0.m.get(holder.getAdapterPosition()).isSelected() || this$0.a().size() <= this$0.h - 1) {
            this$0.m.get(holder.getAdapterPosition()).setSelected(!this$0.m.get(holder.getAdapterPosition()).isSelected());
            this$0.notifyItemChanged(holder.getAdapterPosition());
        } else {
            Context context = holder.itemView.getContext();
            Toast.makeText(context, "Max " + this$0.h + " categories are allowed", 0).show();
        }
    }

    @NotNull
    public final ArrayList a() {
        List<? extends CategoryCode> list = this.m;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((CategoryCode) obj).isSelected()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a */
    public final void onBindViewHolder(@NotNull final b holder, final int i) {
        ImageView a2;
        int i2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (getItemViewType(i) == 2) {
            if (this.o == 100) {
                holder.b().setText("More");
                a2 = holder.a();
                i2 = R.drawable.mappls_category_icon_more;
            } else {
                holder.b().setText("Less");
                a2 = holder.a();
                i2 = R.drawable.mappls_category_icon_less;
            }
            a2.setImageResource(i2);
            holder.c().setVisibility(8);
            if (this.k) {
                holder.itemView.setVisibility(0);
            } else {
                holder.itemView.setVisibility(8);
            }
        } else {
            CategoryCode categoryCode = this.m.get(i);
            holder.b().setText(categoryCode.getCategory());
            if (categoryCode.getBitmapIcon() != null) {
                holder.a().setImageBitmap(categoryCode.getBitmapIcon());
            } else {
                try {
                    holder.a().setImageResource(categoryCode.getIcon());
                } catch (Exception e) {
                    e.printStackTrace();
                    holder.a().setImageResource(0);
                }
            }
            if (categoryCode.isSelected()) {
                holder.c().setVisibility(0);
            } else {
                holder.c().setVisibility(8);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.adapters.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                a.b(a.this, holder, i, view);
            }
        });
    }

    public final void a(@NotNull List<? extends CategoryCode> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.l = list;
        this.m = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Filterable
    @NotNull
    public final Filter getFilter() {
        return new c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        if (this.o == 100) {
            int size = this.m.size();
            int i = this.n;
            return size > i ? i : this.m.size();
        }
        return this.m.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        int i2 = i + 1;
        if (this.o == 100) {
            if (i2 < this.n || i2 != getItemCount()) {
                return 1;
            }
        } else if (i2 < getItemCount()) {
            return 1;
        }
        return 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final b onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.mappls_category_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…          false\n        )");
        return new b(this, inflate);
    }
}
