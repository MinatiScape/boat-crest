package com.coveiot.android.fitnesschallenges.adpter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.databinding.ContactItemSelectBinding;
import com.coveiot.utils.model.CoveContact;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewAllParticipantsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Activity f4502a;
    @NotNull
    public List<CoveContact> b;
    @NotNull
    public List<CoveContact> c;
    @NotNull
    public List<CoveContact> d;

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ContactItemSelectBinding f4503a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ConstraintLayout d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ViewAllParticipantsAdapter viewAllParticipantsAdapter, ContactItemSelectBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4503a = binding;
            TextView textView = binding.displayName;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.displayName");
            this.b = textView;
            TextView textView2 = binding.displayNumber;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.displayNumber");
            this.c = textView2;
            ConstraintLayout constraintLayout = binding.clEnd;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clEnd");
            this.d = constraintLayout;
        }

        @NotNull
        public final ConstraintLayout getClEnd() {
            return this.d;
        }

        @NotNull
        public final TextView getContactMobileNumTv() {
            return this.c;
        }

        @NotNull
        public final TextView getContactNameTv() {
            return this.b;
        }
    }

    public ViewAllParticipantsAdapter(@NotNull Activity context, @NotNull List<CoveContact> contacts) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        this.f4502a = context;
        this.b = contacts;
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        List<CoveContact> list = this.b;
        this.c = list;
        arrayList.addAll(list);
    }

    public final String a(String str) {
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
            this.d.clear();
            for (CoveContact coveContact : this.c) {
                if (coveContact.getName() != null) {
                    String name = coveContact.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "contact.name");
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                    String lowerCase2 = name.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.d.add(coveContact);
                    }
                }
                if (coveContact.getPhoneNumber() != null) {
                    String phoneNumber = coveContact.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
                    if (StringsKt__StringsKt.contains$default((CharSequence) a(phoneNumber), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.d.add(coveContact);
                    }
                }
            }
        } else {
            this.d.clear();
            this.d.addAll(this.c);
        }
        notifyDataSetChanged();
    }

    @NotNull
    public final List<CoveContact> getContacts() {
        return this.b;
    }

    @NotNull
    public final Activity getContext() {
        return this.f4502a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final void setContacts(@NotNull List<CoveContact> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getClEnd().setVisibility(8);
        holder.getContactNameTv().setText(this.b.get(i).getName());
        holder.getContactMobileNumTv().setText(this.b.get(i).getPhoneNumber());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ContactItemSelectBinding inflate = ContactItemSelectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
