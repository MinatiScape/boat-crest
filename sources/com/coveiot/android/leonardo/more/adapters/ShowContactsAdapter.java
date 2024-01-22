package com.coveiot.android.leonardo.more.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ShowContactsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<CoveContact> f5085a;
    @NotNull
    public List<CoveContact> b;
    @NotNull
    public OnBuddiesSelectedListener c;
    @NotNull
    public final Activity d;
    @Nullable
    public List<? extends CoveContact> e;

    /* loaded from: classes5.dex */
    public interface OnBuddiesSelectedListener {
        void onBuddiesContactSelected(@NotNull CoveContact coveContact);

        void onSOSInfoClicked();
    }

    /* loaded from: classes5.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f5086a;
        @NotNull
        public ImageView b;
        @NotNull
        public TextView c;
        @NotNull
        public TextView d;
        @NotNull
        public TextView e;
        @NotNull
        public ImageView f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View baseLayout) {
            super(baseLayout);
            Intrinsics.checkNotNullParameter(baseLayout, "baseLayout");
            this.f5086a = baseLayout;
            View findViewById = baseLayout.findViewById(R.id.buddies_display_pic);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
            this.b = (ImageView) findViewById;
            View findViewById2 = this.f5086a.findViewById(R.id.display_name);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.c = (TextView) findViewById2;
            View findViewById3 = this.f5086a.findViewById(R.id.display_number);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.d = (TextView) findViewById3;
            View findViewById4 = this.f5086a.findViewById(R.id.buddies_icon);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
            this.f = (ImageView) findViewById4;
            View findViewById5 = this.f5086a.findViewById(R.id.buddies_text);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
            this.e = (TextView) findViewById5;
        }

        @NotNull
        public final View getBaseLayout() {
            return this.f5086a;
        }

        @NotNull
        public final ImageView getBuddiesSelectedIv() {
            return this.f;
        }

        @NotNull
        public final TextView getBuddiesText() {
            return this.e;
        }

        @NotNull
        public final TextView getContactNameTv() {
            return this.c;
        }

        @NotNull
        public final TextView getContactPhoneNumberTv() {
            return this.d;
        }

        @NotNull
        public final ImageView getContactPhotoIv() {
            return this.b;
        }

        public final void setBaseLayout(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f5086a = view;
        }

        public final void setBuddiesSelectedIv(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.f = imageView;
        }

        public final void setBuddiesText(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.e = textView;
        }

        public final void setContactNameTv(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.c = textView;
        }

        public final void setContactPhoneNumberTv(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.d = textView;
        }

        public final void setContactPhotoIv(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.b = imageView;
        }
    }

    public ShowContactsAdapter(@NotNull Activity context, @NotNull List<CoveContact> contacts, @NotNull OnBuddiesSelectedListener itemClickInterface, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        this.d = context;
        this.f5085a = contacts;
        arrayList.addAll(contacts);
        this.c = itemClickInterface;
    }

    public static final void g(ShowContactsAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.f();
    }

    public static final void h() {
    }

    public static final void i(ShowContactsAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onBuddiesContactSelected(this$0.b.get(i));
        this$0.f();
    }

    public static final void j(ShowContactsAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onSOSInfoClicked();
    }

    public final void f() {
        View currentFocus = this.d.getCurrentFocus();
        if (currentFocus != null) {
            Object systemService = this.d.getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public final void filter(@Nullable String str) {
        if (str != null && str.length() > 0) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            this.b.clear();
            for (CoveContact coveContact : this.f5085a) {
                String name = coveContact.getName();
                Intrinsics.checkNotNullExpressionValue(name, "contact.getName()");
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                String lowerCase2 = name.toLowerCase(locale2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                    this.b.add(coveContact);
                } else {
                    String phoneNumber = coveContact.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.getPhoneNumber()");
                    if (StringsKt__StringsKt.contains$default((CharSequence) k(phoneNumber), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.b.add(coveContact);
                    }
                }
            }
        } else {
            this.b.clear();
            this.b.addAll(this.f5085a);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final String k(String str) {
        String replace$default = kotlin.text.m.replace$default(str, "\\s+", "", false, 4, (Object) null);
        int length = replace$default.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) replace$default.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return replace$default.subSequence(i, length + 1).toString();
    }

    public final void setSelectedContacts(@NotNull List<? extends CoveContact> selectedContacts) {
        Intrinsics.checkNotNullParameter(selectedContacts, "selectedContacts");
        this.e = selectedContacts;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getContactNameTv().setText(this.b.get(i).getName());
        holder.getBaseLayout().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowContactsAdapter.g(ShowContactsAdapter.this, view);
            }
        });
        holder.getContactPhoneNumberTv().setText(this.b.get(i).getPhoneNumber());
        if (this.b.get(i).getPhotoUri(this.d) != null) {
            GlideUtils.loadCircularImage(this.d, this.b.get(i).getPhotoUri(this.d).toString(), holder.getContactPhotoIv(), new ImageLodeListener() { // from class: com.coveiot.android.leonardo.more.adapters.g0
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    ShowContactsAdapter.h();
                }
            });
        } else {
            holder.getContactPhotoIv().setImageResource(2131231665);
        }
        holder.getBuddiesSelectedIv().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowContactsAdapter.i(ShowContactsAdapter.this, i, view);
            }
        });
        if (this.e != null) {
            holder.getBuddiesSelectedIv().setImageResource(R.drawable.checkbox_unselected);
            holder.getBuddiesSelectedIv().setVisibility(0);
            holder.getBuddiesText().setVisibility(8);
            List<? extends CoveContact> list = this.e;
            Intrinsics.checkNotNull(list);
            Iterator<? extends CoveContact> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().getRunningContactId() == this.b.get(i).getRunningContactId()) {
                    holder.getBuddiesSelectedIv().setImageResource(R.drawable.checkbox_selected);
                    holder.getBuddiesSelectedIv().setVisibility(0);
                    holder.getBuddiesText().setVisibility(8);
                    break;
                }
            }
        }
        CoveContact sOSContact = SessionManager.getInstance(this.d).getSOSContact();
        if (BleApiManager.getInstance(this.d).getBleApi().getDeviceSupportedFeatures().isSosSupported() && sOSContact != null) {
            if (sOSContact.getPhoneNumber().equals(this.b.get(i).getPhoneNumber())) {
                holder.getBuddiesText().setText(this.d.getResources().getString(R.string.sos));
                holder.getBuddiesText().setVisibility(0);
                holder.getBuddiesSelectedIv().setVisibility(8);
            } else {
                holder.getBuddiesText().setVisibility(8);
                holder.getBuddiesSelectedIv().setVisibility(0);
            }
        }
        holder.getBuddiesText().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowContactsAdapter.j(ShowContactsAdapter.this, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_select, parent, false);
        Intrinsics.checkNotNullExpressionValue(v, "v");
        return new ViewHolder(v);
    }
}
