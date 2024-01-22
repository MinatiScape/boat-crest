package com.coveiot.android.fitnesschallenges.adpter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.databinding.ContactItemSelectBinding;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeShowContactsAdapter;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengeShowContactsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<CoveContact> f4499a;
    @NotNull
    public List<CoveContact> b;
    @NotNull
    public OnBuddiesSelectedListener c;
    @NotNull
    public final Activity d;
    @Nullable
    public Boolean e;
    @Nullable
    public CoveContact f;

    /* loaded from: classes2.dex */
    public interface OnBuddiesSelectedListener {
        void onParticipantSelected(@NotNull CoveContact coveContact, boolean z);
    }

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ContactItemSelectBinding f4500a;
        public final /* synthetic */ FitnessChallengeShowContactsAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter, ContactItemSelectBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessChallengeShowContactsAdapter;
            this.f4500a = binding;
        }

        public static final void g(FitnessChallengeShowContactsAdapter this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.b();
        }

        public static final void h() {
        }

        public static final void i() {
        }

        public static final void j(FitnessChallengeShowContactsAdapter this$0, ViewHolder this$1, ContactItemSelectBinding this_apply, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            if (!((CoveContact) this$0.b.get(this$1.getPosition())).isSelected()) {
                this$0.f = (CoveContact) this$0.b.get(this$1.getPosition());
                ((CoveContact) this$0.b.get(this$1.getPosition())).setSelected(true);
                this_apply.buddiesIcon.setImageResource(R.drawable.checkbox_selected);
            } else {
                ((CoveContact) this$0.b.get(this$1.getPosition())).setSelected(false);
                this_apply.buddiesIcon.setImageResource(R.drawable.checkbox_unselected);
            }
            Boolean bool = this$0.e;
            Intrinsics.checkNotNull(bool);
            this$0.c.onParticipantSelected((CoveContact) this$0.b.get(this$1.getPosition()), bool.booleanValue());
        }

        public final void bind(@NotNull CoveContact contact) {
            ImageView imageView;
            int i;
            Intrinsics.checkNotNullParameter(contact, "contact");
            final ContactItemSelectBinding contactItemSelectBinding = this.f4500a;
            final FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter = this.b;
            contactItemSelectBinding.displayName.setText(contact.getName());
            contactItemSelectBinding.displayNumber.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeShowContactsAdapter.ViewHolder.g(FitnessChallengeShowContactsAdapter.this, view);
                }
            });
            contactItemSelectBinding.displayNumber.setText(contact.getPhoneNumber());
            if (!Intrinsics.areEqual(fitnessChallengeShowContactsAdapter.e, Boolean.TRUE)) {
                if (contact.getPhotoUri(fitnessChallengeShowContactsAdapter.d) != null) {
                    GlideUtils.loadCircularImage(fitnessChallengeShowContactsAdapter.d, contact.getPhotoUri(fitnessChallengeShowContactsAdapter.d).toString(), contactItemSelectBinding.buddiesDisplayPic, new ImageLodeListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.i
                        @Override // com.coveiot.utils.utility.ImageLodeListener
                        public final void onImageLoaded() {
                            FitnessChallengeShowContactsAdapter.ViewHolder.i();
                        }
                    });
                } else {
                    contactItemSelectBinding.buddiesDisplayPic.setImageResource(R.drawable.default_avatar);
                }
            } else if (contact.getDpUrl() != null) {
                GlideUtils.loadCircularImage(fitnessChallengeShowContactsAdapter.d, contact.getDpUrl(), contactItemSelectBinding.buddiesDisplayPic, new ImageLodeListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.h
                    @Override // com.coveiot.utils.utility.ImageLodeListener
                    public final void onImageLoaded() {
                        FitnessChallengeShowContactsAdapter.ViewHolder.h();
                    }
                });
            } else {
                contactItemSelectBinding.buddiesDisplayPic.setImageResource(R.drawable.default_avatar);
            }
            if (((CoveContact) fitnessChallengeShowContactsAdapter.b.get(getPosition())).isSelected()) {
                imageView = contactItemSelectBinding.buddiesIcon;
                i = R.drawable.checkbox_selected;
            } else {
                imageView = contactItemSelectBinding.buddiesIcon;
                i = R.drawable.checkbox_unselected;
            }
            imageView.setImageResource(i);
            contactItemSelectBinding.buddiesIcon.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeShowContactsAdapter.ViewHolder.j(FitnessChallengeShowContactsAdapter.this, this, contactItemSelectBinding, view);
                }
            });
        }
    }

    public FitnessChallengeShowContactsAdapter(@NotNull Activity context, @NotNull List<CoveContact> contacts, @NotNull OnBuddiesSelectedListener itemClickInterface, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        this.e = Boolean.FALSE;
        this.d = context;
        this.f4499a = contacts;
        arrayList.addAll(contacts);
        this.c = itemClickInterface;
        this.e = Boolean.valueOf(z);
    }

    public final void b() {
        View currentFocus = this.d.getCurrentFocus();
        if (currentFocus != null) {
            Object systemService = this.d.getSystemService("input_method");
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

    public final int filter(@Nullable String str) {
        if (str != null && str.length() > 0) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            this.b.clear();
            for (CoveContact coveContact : this.f4499a) {
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
                    if (StringsKt__StringsKt.contains$default((CharSequence) c(phoneNumber), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.b.add(coveContact);
                    }
                }
            }
        } else {
            this.b.clear();
            this.b.addAll(this.f4499a);
        }
        notifyDataSetChanged();
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i));
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
