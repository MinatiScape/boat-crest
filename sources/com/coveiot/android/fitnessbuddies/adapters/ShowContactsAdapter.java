package com.coveiot.android.fitnessbuddies.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ShowContactsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<CoveContact> f4441a;
    @NotNull
    public List<CoveContact> b;
    @NotNull
    public OnBuddiesSelectedListener c;
    @NotNull
    public final Activity d;
    @Nullable
    public List<? extends CoveContact> e;

    /* loaded from: classes4.dex */
    public interface OnBuddiesSelectedListener {
        void onBuddiesContactSelected(@NotNull CoveContact coveContact);
    }

    /* loaded from: classes4.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f4442a;
        @NotNull
        public ImageView b;
        @NotNull
        public TextView c;
        @NotNull
        public TextView d;
        @NotNull
        public TextView e;
        @NotNull
        public TextView f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View baseLayout) {
            super(baseLayout);
            Intrinsics.checkNotNullParameter(baseLayout, "baseLayout");
            this.f4442a = baseLayout;
            View findViewById = baseLayout.findViewById(R.id.buddies_display_pic);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
            this.b = (ImageView) findViewById;
            View findViewById2 = this.f4442a.findViewById(R.id.display_name);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.c = (TextView) findViewById2;
            View findViewById3 = this.f4442a.findViewById(R.id.display_number);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.d = (TextView) findViewById3;
            View findViewById4 = this.f4442a.findViewById(R.id.buddies_icon);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
            this.f = (TextView) findViewById4;
            View findViewById5 = this.f4442a.findViewById(R.id.buddies_text);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
            this.e = (TextView) findViewById5;
        }

        @NotNull
        public final View getBaseLayout() {
            return this.f4442a;
        }

        @NotNull
        public final TextView getBuddiesSelectedIv() {
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
            this.f4442a = view;
        }

        public final void setBuddiesSelectedIv(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.f = textView;
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
        this.f4441a = contacts;
        arrayList.addAll(contacts);
        this.c = itemClickInterface;
    }

    public static final void e(ShowContactsAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d();
    }

    public static final void f() {
    }

    public static final void g(ShowContactsAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onBuddiesContactSelected(this$0.b.get(i));
        this$0.d();
    }

    public final void d() {
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
            for (CoveContact coveContact : this.f4441a) {
                if (coveContact.getName() != null) {
                    String name = coveContact.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "contact.getName()");
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                    String lowerCase2 = name.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.b.add(coveContact);
                    }
                }
                if (coveContact.getPhoneNumber() != null) {
                    String phoneNumber = coveContact.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.getPhoneNumber()");
                    if (StringsKt__StringsKt.contains$default((CharSequence) h(phoneNumber), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.b.add(coveContact);
                    }
                }
            }
        } else {
            this.b.clear();
            this.b.addAll(this.f4441a);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final String h(String str) {
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

    public final void setSelectedContactsForBuddies(@NotNull List<? extends CoveContact> selectedContacts, @NotNull List<? extends CoveContact> existingBuddies, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(selectedContacts, "selectedContacts");
        Intrinsics.checkNotNullParameter(existingBuddies, "existingBuddies");
        Intrinsics.checkNotNullParameter(context, "context");
        this.e = selectedContacts;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getContactNameTv().setText(this.b.get(i).getName());
        holder.getBaseLayout().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowContactsAdapter.e(ShowContactsAdapter.this, view);
            }
        });
        holder.getContactPhoneNumberTv().setText(this.b.get(i).getPhoneNumber());
        if (this.b.get(i).getPhotoUri(this.d) != null) {
            GlideUtils.loadCircularImage(this.d, this.b.get(i).getPhotoUri(this.d).toString(), holder.getContactPhotoIv(), new ImageLodeListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.p
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    ShowContactsAdapter.f();
                }
            });
        } else {
            holder.getContactPhotoIv().setImageResource(R.drawable.default_avatar);
        }
        holder.getBuddiesSelectedIv().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowContactsAdapter.g(ShowContactsAdapter.this, i, view);
            }
        });
        if (this.e != null) {
            List<Requests> fitnessBuddies = PreferenceManager.Companion.getFitnessBuddies(this.d);
            holder.getBuddiesSelectedIv().setVisibility(0);
            holder.getBuddiesText().setVisibility(8);
            List<? extends CoveContact> list = this.e;
            Intrinsics.checkNotNull(list);
            for (CoveContact coveContact : list) {
                if (coveContact.getRunningContactId() == this.b.get(i).getRunningContactId()) {
                    if (!coveContact.isSelected()) {
                        holder.getBuddiesSelectedIv().setVisibility(8);
                        holder.getBuddiesText().setText(this.d.getResources().getString(R.string.request_sent));
                        holder.getBuddiesText().setVisibility(0);
                        if (fitnessBuddies != null) {
                            for (Requests requests : fitnessBuddies) {
                                String phoneNumber = coveContact.getPhoneNumber();
                                Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
                                String replace = new Regex("\\s").replace(phoneNumber, "");
                                String str = requests.mobileNumber;
                                Intrinsics.checkNotNullExpressionValue(str, "buddy.mobileNumber");
                                if (replace.equals(new Regex("\\s").replace(str, ""))) {
                                    holder.getBuddiesText().setText(this.d.getResources().getString(R.string.fitness_buddy));
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    }
                    holder.getBuddiesSelectedIv().setVisibility(0);
                    holder.getBuddiesText().setVisibility(8);
                    return;
                }
            }
        }
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
