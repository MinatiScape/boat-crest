package com.coveiot.android.healthbuddies.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddHealthBuddiesAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Activity f4564a;
    @NotNull
    public List<CoveContact> b;
    @NotNull
    public List<CoveContact> c;
    @NotNull
    public OnUserSelectedListener d;
    @Nullable
    public Map<Long, ? extends CoveContact> e;
    @Nullable
    public Map<Long, ? extends CoveContact> f;

    /* loaded from: classes3.dex */
    public interface OnUserSelectedListener {
        void onUserContactSelected(@NotNull CoveContact coveContact);
    }

    /* loaded from: classes3.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f4565a;
        @NotNull
        public ImageView b;
        @NotNull
        public TextView c;
        @NotNull
        public TextView d;
        @NotNull
        public ImageView e;
        @NotNull
        public TextView f;
        @NotNull
        public ConstraintLayout g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f4565a = view;
            View findViewById = view.findViewById(R.id.user_display_pic);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
            this.b = (ImageView) findViewById;
            View findViewById2 = this.f4565a.findViewById(R.id.display_name);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.c = (TextView) findViewById2;
            View findViewById3 = this.f4565a.findViewById(R.id.display_number);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.d = (TextView) findViewById3;
            View findViewById4 = this.f4565a.findViewById(R.id.tvInviteStatus);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
            this.f = (TextView) findViewById4;
            View findViewById5 = this.f4565a.findViewById(R.id.ivPlus);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.ImageView");
            this.e = (ImageView) findViewById5;
            View findViewById6 = this.f4565a.findViewById(R.id.clButton);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
            this.g = (ConstraintLayout) findViewById6;
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
        public final ConstraintLayout getLayout() {
            return this.g;
        }

        @NotNull
        public final TextView getUserSelectedIv() {
            return this.f;
        }

        @NotNull
        public final ImageView getUserText() {
            return this.e;
        }

        @NotNull
        public final View getView() {
            return this.f4565a;
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

        public final void setLayout(@NotNull ConstraintLayout constraintLayout) {
            Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
            this.g = constraintLayout;
        }

        public final void setUserSelectedIv(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.f = textView;
        }

        public final void setUserText(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.e = imageView;
        }

        public final void setView(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f4565a = view;
        }
    }

    public AddHealthBuddiesAdapter(@NotNull Activity mActivity, @NotNull ArrayList<CoveContact> contacts, @NotNull OnUserSelectedListener itemClickInterface) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        this.f4564a = mActivity;
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.b = contacts;
        arrayList.addAll(contacts);
        this.d = itemClickInterface;
    }

    public static final void e(AddHealthBuddiesAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d();
    }

    public static final void f() {
    }

    public static final void g(AddHealthBuddiesAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d.onUserContactSelected(this$0.c.get(i));
        this$0.d();
    }

    public final void d() {
        View currentFocus = this.f4564a.getCurrentFocus();
        if (currentFocus != null) {
            Object systemService = this.f4564a.getSystemService("input_method");
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
            this.c.clear();
            for (CoveContact coveContact : this.b) {
                if (coveContact.getName() != null) {
                    String name = coveContact.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "contact.getName()");
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                    String lowerCase2 = name.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.c.add(coveContact);
                    }
                }
                if (coveContact.getPhoneNumber() != null) {
                    String phoneNumber = coveContact.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.getPhoneNumber()");
                    if (StringsKt__StringsKt.contains$default((CharSequence) h(phoneNumber), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        this.c.add(coveContact);
                    }
                }
            }
        } else {
            this.c.clear();
            this.c.addAll(this.b);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final Activity getMActivity() {
        return this.f4564a;
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

    public final void setSelectedContactsForUsers(@NotNull Map<Long, ? extends CoveContact> existingContacts, @NotNull Map<Long, ? extends CoveContact> selectedContacts, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(existingContacts, "existingContacts");
        Intrinsics.checkNotNullParameter(selectedContacts, "selectedContacts");
        Intrinsics.checkNotNullParameter(context, "context");
        this.e = existingContacts;
        this.f = selectedContacts;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getContactNameTv().setText(this.c.get(i).getName());
        holder.getView().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddHealthBuddiesAdapter.e(AddHealthBuddiesAdapter.this, view);
            }
        });
        holder.getContactPhoneNumberTv().setText(this.c.get(i).getPhoneNumber());
        if (this.c.get(i).getPhotoUri(this.f4564a) != null) {
            GlideUtils.loadCircularImage(this.f4564a, this.c.get(i).getPhotoUri(this.f4564a).toString(), holder.getContactPhotoIv(), new ImageLodeListener() { // from class: com.coveiot.android.healthbuddies.adapters.c
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    AddHealthBuddiesAdapter.f();
                }
            });
        } else {
            holder.getContactPhotoIv().setImageResource(R.drawable.default_avatar);
        }
        holder.getLayout().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddHealthBuddiesAdapter.g(AddHealthBuddiesAdapter.this, i, view);
            }
        });
        holder.getUserSelectedIv().setText("Invite");
        holder.getUserSelectedIv().setVisibility(0);
        if (this.e != null) {
            this.c.get(i).getRunningContactId();
            Map<Long, ? extends CoveContact> map = this.e;
            Intrinsics.checkNotNull(map);
            if (map.get(Long.valueOf(this.c.get(i).getRunningContactId())) != null) {
                holder.getUserText().setVisibility(8);
                holder.getUserSelectedIv().setText("Request sent");
                holder.getLayout().setEnabled(false);
            }
        }
        if (this.f != null) {
            this.c.get(i).getRunningContactId();
            Map<Long, ? extends CoveContact> map2 = this.f;
            Intrinsics.checkNotNull(map2);
            if (map2.get(Long.valueOf(this.c.get(i).getRunningContactId())) != null) {
                holder.getUserText().setVisibility(8);
                holder.getUserSelectedIv().setText("Sending...");
                holder.getLayout().setEnabled(false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_health_buddies_list_item_new, parent, false);
        Intrinsics.checkNotNullExpressionValue(v, "v");
        return new ViewHolder(v);
    }
}
