package com.coveiot.android.fitnessbuddies.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.databinding.ContactItemSelectBinding;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ShowContactsAdapterNew extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<CoveContact> f4443a;
    @NotNull
    public List<CoveContact> b;
    @NotNull
    public OnBuddiesSelectedListener c;
    @NotNull
    public final Activity d;
    @Nullable
    public Boolean e;

    /* loaded from: classes4.dex */
    public interface OnBuddiesSelectedListener {
        void onBuddiesContactSelected(@NotNull CoveContact coveContact, boolean z);
    }

    /* loaded from: classes4.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ContactItemSelectBinding f4444a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final ImageView f;
        @NotNull
        public final TextView g;
        @NotNull
        public final RelativeLayout h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ShowContactsAdapterNew showContactsAdapterNew, ContactItemSelectBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4444a = binding;
            ImageView imageView = binding.buddiesDisplayPic;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.buddiesDisplayPic");
            this.b = imageView;
            TextView textView = binding.displayName;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.displayName");
            this.c = textView;
            TextView textView2 = binding.displayNumber;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.displayNumber");
            this.d = textView2;
            TextView textView3 = binding.buddiesText;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.buddiesText");
            this.e = textView3;
            ImageView imageView2 = binding.buddiesIcon;
            Intrinsics.checkNotNullExpressionValue(imageView2, "binding.buddiesIcon");
            this.f = imageView2;
            TextView textView4 = binding.tvBuddiesInvite;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvBuddiesInvite");
            this.g = textView4;
            RelativeLayout relativeLayout = binding.rootLayoutGeneric;
            Intrinsics.checkNotNullExpressionValue(relativeLayout, "binding.rootLayoutGeneric");
            this.h = relativeLayout;
        }

        @NotNull
        public final RelativeLayout getBaseLayout() {
            return this.h;
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

        @NotNull
        public final TextView getTvBuddiesInvite() {
            return this.g;
        }
    }

    public ShowContactsAdapterNew(@NotNull Activity context, @NotNull List<CoveContact> contacts, @NotNull OnBuddiesSelectedListener itemClickInterface, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        this.e = Boolean.FALSE;
        this.d = context;
        this.f4443a = contacts;
        arrayList.addAll(contacts);
        this.c = itemClickInterface;
        this.e = Boolean.valueOf(z);
    }

    public static final void f(ShowContactsAdapterNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.e();
    }

    public static final void g() {
    }

    public static final void h() {
    }

    public static final void i(ShowContactsAdapterNew this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Boolean bool = this$0.e;
        Intrinsics.checkNotNull(bool);
        this$0.c.onBuddiesContactSelected(this$0.b.get(i), bool.booleanValue());
        this$0.e();
    }

    public final void e() {
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
            for (CoveContact coveContact : this.f4443a) {
                if (coveContact.getName() != null) {
                    String name = coveContact.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "contact.name");
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
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
                    if (StringsKt__StringsKt.contains$default((CharSequence) j(phoneNumber), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.b.add(coveContact);
                    }
                }
            }
        } else {
            this.b.clear();
            this.b.addAll(this.f4443a);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final String j(String str) {
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBuddiesSelectedIv().setVisibility(8);
        Boolean bool = this.e;
        Boolean bool2 = Boolean.TRUE;
        if (Intrinsics.areEqual(bool, bool2)) {
            holder.getTvBuddiesInvite().setText(this.d.getString(R.string.add));
        } else {
            holder.getTvBuddiesInvite().setText(this.d.getString(R.string.invite));
        }
        holder.getContactNameTv().setText(this.b.get(i).getName());
        holder.getBaseLayout().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowContactsAdapterNew.f(ShowContactsAdapterNew.this, view);
            }
        });
        holder.getContactPhoneNumberTv().setText(this.b.get(i).getPhoneNumber());
        if (Intrinsics.areEqual(this.e, bool2)) {
            if (this.b.get(i).getDpUrl() != null) {
                GlideUtils.loadCircularImage(this.d, this.b.get(i).getDpUrl(), holder.getContactPhotoIv(), new ImageLodeListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.t
                    @Override // com.coveiot.utils.utility.ImageLodeListener
                    public final void onImageLoaded() {
                        ShowContactsAdapterNew.g();
                    }
                });
            } else {
                holder.getContactPhotoIv().setImageResource(R.drawable.default_avatar);
            }
        } else if (this.b.get(i).getPhotoUri(this.d) != null) {
            GlideUtils.loadCircularImage(this.d, this.b.get(i).getPhotoUri(this.d).toString(), holder.getContactPhotoIv(), new ImageLodeListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.s
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    ShowContactsAdapterNew.h();
                }
            });
        } else {
            holder.getContactPhotoIv().setImageResource(R.drawable.default_avatar);
        }
        if (this.b.get(i).isRequestSent()) {
            holder.getTvBuddiesInvite().setVisibility(8);
            holder.getBuddiesText().setVisibility(0);
            holder.getBuddiesText().setText("Invite sent");
        } else if (this.b.get(i).isRequestReceived()) {
            holder.getTvBuddiesInvite().setVisibility(8);
            holder.getBuddiesText().setVisibility(0);
            holder.getBuddiesText().setText("Invite Received");
        } else if (this.b.get(i).isFitCrew()) {
            holder.getTvBuddiesInvite().setVisibility(8);
            holder.getBuddiesText().setVisibility(0);
            holder.getBuddiesText().setText("Fit Crew");
        } else {
            holder.getTvBuddiesInvite().setVisibility(0);
            holder.getBuddiesText().setVisibility(8);
        }
        holder.getTvBuddiesInvite().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowContactsAdapterNew.i(ShowContactsAdapterNew.this, i, view);
            }
        });
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
