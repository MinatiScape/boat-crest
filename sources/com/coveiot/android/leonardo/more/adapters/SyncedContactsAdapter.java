package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.SyncedContactsAdapter;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SyncedContactsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<CoveContact> f5089a;
    @NotNull
    public final ContactListener b;
    @NotNull
    public final Context c;

    /* loaded from: classes5.dex */
    public interface ContactListener {
        void onContactDelete(@NotNull CoveContact coveContact);

        void onSOSCLicked();
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f5090a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ImageView d;
        @NotNull
        public final TextView e;
        public final /* synthetic */ SyncedContactsAdapter f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SyncedContactsAdapter syncedContactsAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f = syncedContactsAdapter;
            View findViewById = itemView.findViewById(R.id.buddies_display_pic);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.buddies_display_pic)");
            this.f5090a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.display_name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.display_name)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.display_number);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.display_number)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.delete_contact);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.delete_contact)");
            this.d = (ImageView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.tvSOS);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.tvSOS)");
            this.e = (TextView) findViewById5;
        }

        public static final void f() {
        }

        public static final void g(SyncedContactsAdapter this$0, CoveContact coveContact, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(coveContact, "$coveContact");
            this$0.getContactListener().onContactDelete(coveContact);
        }

        public static final void h(SyncedContactsAdapter this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getContactListener().onSOSCLicked();
        }

        public final void bindView(@NotNull final CoveContact coveContact) {
            Intrinsics.checkNotNullParameter(coveContact, "coveContact");
            CoveContact sOSContact = SessionManager.getInstance(this.f.getContext()).getSOSContact();
            if (sOSContact != null) {
                if (sOSContact.getPhoneNumber().equals(coveContact.getPhoneNumber())) {
                    this.e.setVisibility(0);
                } else {
                    this.e.setVisibility(8);
                }
            } else {
                this.e.setVisibility(8);
            }
            Uri photoUri = coveContact.getPhotoUri(this.itemView.getContext());
            if (photoUri != null) {
                GlideUtils.loadCircularImage(this.itemView.getContext(), photoUri.toString(), this.f5090a, new ImageLodeListener() { // from class: com.coveiot.android.leonardo.more.adapters.k0
                    @Override // com.coveiot.utils.utility.ImageLodeListener
                    public final void onImageLoaded() {
                        SyncedContactsAdapter.ViewHolder.f();
                    }
                });
            } else {
                this.f5090a.setImageResource(2131231665);
            }
            this.b.setText(coveContact.getName());
            this.c.setText(coveContact.getPhoneNumber());
            ImageView imageView = this.d;
            final SyncedContactsAdapter syncedContactsAdapter = this.f;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.j0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SyncedContactsAdapter.ViewHolder.g(SyncedContactsAdapter.this, coveContact, view);
                }
            });
            TextView textView = this.e;
            final SyncedContactsAdapter syncedContactsAdapter2 = this.f;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.i0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SyncedContactsAdapter.ViewHolder.h(SyncedContactsAdapter.this, view);
                }
            });
        }
    }

    public SyncedContactsAdapter(@NotNull ArrayList<CoveContact> syncedContacts, @NotNull ContactListener contactListener, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(syncedContacts, "syncedContacts");
        Intrinsics.checkNotNullParameter(contactListener, "contactListener");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5089a = syncedContacts;
        this.b = contactListener;
        this.c = context;
    }

    @NotNull
    public final ContactListener getContactListener() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5089a.size();
    }

    @NotNull
    public final ArrayList<CoveContact> getSyncedContacts() {
        return this.f5089a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        CoveContact coveContact = this.f5089a.get(i);
        Intrinsics.checkNotNullExpressionValue(coveContact, "syncedContacts[position]");
        holder.bindView(coveContact);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…tact_item, parent, false)");
        return new ViewHolder(this, inflate);
    }
}
