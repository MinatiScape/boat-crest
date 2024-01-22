package com.coveiot.android.sos.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sos.R;
import com.coveiot.android.sos.databinding.SosContactItemSelectBinding;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ShowContactsAdapterNew extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Activity f5779a;
    @NotNull
    public final List<CoveContact> b;
    @Nullable
    public final List<CoveContact> c;
    @NotNull
    public final OnContactSelectedListener d;
    @Nullable
    public final SOSData e;
    @NotNull
    public List<CoveContact> f;
    public boolean g;
    public int h;
    public int i;

    /* loaded from: classes7.dex */
    public interface OnContactSelectedListener {
        void onContactSelected(@NotNull CoveContact coveContact);

        void onSyncInfoClicked();
    }

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final SosContactItemSelectBinding f5780a;
        public final /* synthetic */ ShowContactsAdapterNew b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ShowContactsAdapterNew showContactsAdapterNew, SosContactItemSelectBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = showContactsAdapterNew;
            this.f5780a = binding;
        }

        public static final void h(ShowContactsAdapterNew this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getItemClickInterface().onSyncInfoClicked();
        }

        public static final void i(ShowContactsAdapterNew this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.b();
        }

        public static final void j() {
        }

        public static final void k(ViewHolder this$0, ShowContactsAdapterNew this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            int absoluteAdapterPosition = this$0.getAbsoluteAdapterPosition();
            if (absoluteAdapterPosition != -1) {
                this$1.i = this$1.h;
                this$1.h = absoluteAdapterPosition;
                this$1.notifyDataSetChanged();
                this$0.l();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x00c9  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00e1  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00fc  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void bind(@org.jetbrains.annotations.NotNull com.coveiot.utils.model.CoveContact r10) {
            /*
                Method dump skipped, instructions count: 260
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sos.adapter.ShowContactsAdapterNew.ViewHolder.bind(com.coveiot.utils.model.CoveContact):void");
        }

        public final void l() {
            this.f5780a.ivContactSelect.setImageResource(getAbsoluteAdapterPosition() == this.b.h ? R.drawable.radio_button_selected : R.drawable.radio_button_unselected);
            if (getAbsoluteAdapterPosition() == this.b.h) {
                LogHelper.d("Selected Position", "last " + this.b.i + ", " + this.b.h);
                this.b.getItemClickInterface().onContactSelected((CoveContact) this.b.f.get(this.b.h));
            }
        }
    }

    public ShowContactsAdapterNew(@NotNull Activity context, @NotNull List<CoveContact> contacts, @Nullable List<CoveContact> list, @NotNull OnContactSelectedListener itemClickInterface, @Nullable SOSData sOSData) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        this.f5779a = context;
        this.b = contacts;
        this.c = list;
        this.d = itemClickInterface;
        this.e = sOSData;
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        this.h = -1;
        this.i = -1;
        arrayList.addAll(contacts);
    }

    public final void b() {
        View currentFocus = this.f5779a.getCurrentFocus();
        if (currentFocus != null) {
            Object systemService = this.f5779a.getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public final String c(String str) {
        String replace$default = m.replace$default(str, "\\s+", "", false, 4, (Object) null);
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

    public final void filter(@Nullable String str) {
        if (str != null && str.length() > 0) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            this.f.clear();
            for (CoveContact coveContact : this.b) {
                if (coveContact.getName() != null) {
                    String name = coveContact.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "contact.name");
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                    String lowerCase2 = name.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.f.add(coveContact);
                    }
                }
                if (coveContact.getPhoneNumber() != null) {
                    String phoneNumber = coveContact.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
                    if (StringsKt__StringsKt.contains$default((CharSequence) c(phoneNumber), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.f.add(coveContact);
                    }
                }
            }
        } else {
            this.f.clear();
            this.f.addAll(this.b);
        }
        notifyDataSetChanged();
    }

    @NotNull
    public final List<CoveContact> getContacts() {
        return this.b;
    }

    @NotNull
    public final Activity getContext() {
        return this.f5779a;
    }

    @NotNull
    public final OnContactSelectedListener getItemClickInterface() {
        return this.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f.size();
    }

    @Nullable
    public final SOSData getSosData() {
        return this.e;
    }

    @Nullable
    public final List<CoveContact> getSyncedContacts() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.f.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SosContactItemSelectBinding inflate = SosContactItemSelectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
