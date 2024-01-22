package com.coveiot.android.healthbuddies.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.coveaccess.healthbuddies.HealthBuddy;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ManageHealthBuddiesAdapterNew extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public final String f4570a;
    @NotNull
    public List<String> b;
    @Nullable
    public HashMap<String, List<HealthBuddy>> c;
    @NotNull
    public OnOptionSelectedListener d;
    @NotNull
    public Context e;

    /* loaded from: classes3.dex */
    public interface OnOptionSelectedListener {
        void onCancel(int i);

        void onDelete(int i);

        void onReInvite(int i, @Nullable String str, @Nullable String str2);
    }

    /* loaded from: classes3.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f4571a;
        @NotNull
        public ImageView b;
        @NotNull
        public TextView c;
        @NotNull
        public TextView d;
        @NotNull
        public Button e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View baseLayout) {
            super(baseLayout);
            Intrinsics.checkNotNullParameter(baseLayout, "baseLayout");
            this.f4571a = baseLayout;
            View findViewById = baseLayout.findViewById(R.id.user_pic);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
            this.b = (ImageView) findViewById;
            View findViewById2 = this.f4571a.findViewById(R.id.user_name);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.c = (TextView) findViewById2;
            View findViewById3 = this.f4571a.findViewById(R.id.number);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.d = (TextView) findViewById3;
            View findViewById4 = this.f4571a.findViewById(R.id.btnRevoke);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.Button");
            this.e = (Button) findViewById4;
        }

        @NotNull
        public final View getBaseLayout() {
            return this.f4571a;
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
        public final Button getRevokeBtn() {
            return this.e;
        }

        public final void setBaseLayout(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f4571a = view;
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

        public final void setRevokeBtn(@NotNull Button button) {
            Intrinsics.checkNotNullParameter(button, "<set-?>");
            this.e = button;
        }
    }

    public ManageHealthBuddiesAdapterNew(@NotNull Context context, @Nullable HashMap<String, List<HealthBuddy>> hashMap, @NotNull List<String> titles, @NotNull OnOptionSelectedListener itemClickInterface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(titles, "titles");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        this.f4570a = ManageHealthBuddiesAdapterNew.class.getSimpleName();
        this.b = new ArrayList();
        this.c = hashMap;
        this.b = titles;
        this.d = itemClickInterface;
        this.e = context;
    }

    public static final void d() {
    }

    public static final void e(ManageHealthBuddiesAdapterNew this$0, HealthBuddy buddyInfo, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(buddyInfo, "$buddyInfo");
        OnOptionSelectedListener onOptionSelectedListener = this$0.d;
        Integer num = buddyInfo.userId;
        Intrinsics.checkNotNullExpressionValue(num, "buddyInfo.userId");
        onOptionSelectedListener.onDelete(num.intValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        HashMap<String, List<HealthBuddy>> hashMap = this.c;
        Intrinsics.checkNotNull(hashMap);
        List<HealthBuddy> list = hashMap.get(this.b.get(0));
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        String str = this.f4570a;
        StringBuilder sb = new StringBuilder();
        sb.append("onBindViewHolder: ");
        HashMap<String, List<HealthBuddy>> hashMap = this.c;
        Intrinsics.checkNotNull(hashMap);
        List<HealthBuddy> list = hashMap.get(this.b.get(0));
        Intrinsics.checkNotNull(list);
        sb.append(list.get(i).name);
        Log.d(str, sb.toString());
        HashMap<String, List<HealthBuddy>> hashMap2 = this.c;
        Intrinsics.checkNotNull(hashMap2);
        List<HealthBuddy> list2 = hashMap2.get(this.b.get(0));
        Intrinsics.checkNotNull(list2);
        final HealthBuddy healthBuddy = list2.get(i);
        String str2 = healthBuddy.dpUrl;
        if (str2 != null) {
            Intrinsics.checkNotNullExpressionValue(str2, "buddyInfo.dpUrl");
            if (!kotlin.text.m.isBlank(str2)) {
                GlideUtils.loadCircularImage(this.e, healthBuddy.dpUrl, holder.getContactPhotoIv(), new ImageLodeListener() { // from class: com.coveiot.android.healthbuddies.adapters.q
                    @Override // com.coveiot.utils.utility.ImageLodeListener
                    public final void onImageLoaded() {
                        ManageHealthBuddiesAdapterNew.d();
                    }
                });
                holder.getContactNameTv().setText(healthBuddy.name);
                holder.getContactPhoneNumberTv().setText(healthBuddy.mobileNumber);
                holder.getRevokeBtn().setText(this.e.getString(R.string.revoke));
                holder.getRevokeBtn().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.p
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ManageHealthBuddiesAdapterNew.e(ManageHealthBuddiesAdapterNew.this, healthBuddy, view);
                    }
                });
            }
        }
        holder.getContactPhotoIv().setImageResource(R.drawable.default_avatar);
        holder.getContactNameTv().setText(healthBuddy.name);
        holder.getContactPhoneNumberTv().setText(healthBuddy.mobileNumber);
        holder.getRevokeBtn().setText(this.e.getString(R.string.revoke));
        holder.getRevokeBtn().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageHealthBuddiesAdapterNew.e(ManageHealthBuddiesAdapterNew.this, healthBuddy, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_health_buddies_list_item_new, parent, false);
        Intrinsics.checkNotNullExpressionValue(v, "v");
        return new ViewHolder(v);
    }
}
