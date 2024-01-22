package com.coveiot.android.sportsnotification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.adapter.SportSettingAdapter;
import com.coveiot.android.sportsnotification.model.SettingsModel;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportSettingAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<SettingsModel> f5836a;
    @NotNull
    public final Context b;
    @NotNull
    public final SportSelectSelectListener c;
    public int d;
    public boolean e;

    /* loaded from: classes7.dex */
    public interface SportSelectSelectListener {
        void onSportSelect(int i);
    }

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public RadioButton f5837a;
        @NotNull
        public TextView b;
        public final /* synthetic */ SportSettingAdapter c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull final SportSettingAdapter sportSettingAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.c = sportSettingAdapter;
            View findViewById = view.findViewById(R.id.radio_button);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.radio_button)");
            this.f5837a = (RadioButton) findViewById;
            View findViewById2 = view.findViewById(R.id.interval_text);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.interval_text)");
            this.b = (TextView) findViewById2;
            this.f5837a.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.adapter.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SportSettingAdapter.ViewHolder.b(SportSettingAdapter.this, this, view2);
                }
            });
        }

        public static final void b(SportSettingAdapter this$0, ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.d = this$1.getAdapterPosition();
            this$0.notifyDataSetChanged();
            SportSelectSelectListener sportSelectSelectListener = this$0.c;
            Intrinsics.checkNotNull(sportSelectSelectListener);
            sportSelectSelectListener.onSportSelect(this$0.d);
        }

        @NotNull
        public final TextView getInterval_text() {
            return this.b;
        }

        @NotNull
        public final RadioButton getSelectionState() {
            return this.f5837a;
        }

        public final void setInterval_text(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.b = textView;
        }

        public final void setSelectionState(@NotNull RadioButton radioButton) {
            Intrinsics.checkNotNullParameter(radioButton, "<set-?>");
            this.f5837a = radioButton;
        }
    }

    public SportSettingAdapter(@NotNull List<SettingsModel> settingList, @NotNull Context context, @NotNull SportSelectSelectListener selectListener) {
        Intrinsics.checkNotNullParameter(settingList, "settingList");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(selectListener, "selectListener");
        this.f5836a = settingList;
        this.b = context;
        this.c = selectListener;
        this.d = -1;
        this.e = true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5836a.size();
    }

    public final void setEnabled(boolean z) {
        this.e = z;
        notifyDataSetChanged();
    }

    public final void updateSetting(@Nullable String str) {
        Iterator<SettingsModel> it = this.f5836a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SettingsModel next = it.next();
            if (Intrinsics.areEqual(str, next.getName())) {
                this.d = this.f5836a.indexOf(next);
                break;
            }
        }
        notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00c4  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull com.coveiot.android.sportsnotification.adapter.SportSettingAdapter.ViewHolder r8, int r9) {
        /*
            r7 = this;
            java.lang.String r0 = "holder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.util.List<com.coveiot.android.sportsnotification.model.SettingsModel> r0 = r7.f5836a
            java.lang.Object r0 = r0.get(r9)
            com.coveiot.android.sportsnotification.model.SettingsModel r0 = (com.coveiot.android.sportsnotification.model.SettingsModel) r0
            android.widget.RadioButton r1 = r8.getSelectionState()
            java.lang.String r0 = r0.getName()
            r1.setText(r0)
            android.widget.RadioButton r0 = r8.getSelectionState()
            boolean r1 = r7.e
            r0.setEnabled(r1)
            com.coveiot.android.sportsnotification.SportsPreference$Companion r0 = com.coveiot.android.sportsnotification.SportsPreference.Companion
            android.content.Context r1 = r7.b
            com.coveiot.android.sportsnotification.model.SoccerFilterConfig r1 = r0.getSoccerConfig(r1)
            r2 = 0
            if (r1 == 0) goto L6a
            android.content.Context r1 = r7.b
            com.coveiot.android.sportsnotification.model.SoccerFilterConfig r1 = r0.getSoccerConfig(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.util.List r1 = r1.getFilters()
            java.lang.Object r1 = r1.get(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            com.coveiot.android.sportsnotification.model.Filters r1 = (com.coveiot.android.sportsnotification.model.Filters) r1
            java.lang.Integer r1 = r1.getApiHitInterval()
            if (r1 == 0) goto L6a
            android.content.Context r1 = r7.b
            com.coveiot.android.sportsnotification.model.SoccerFilterConfig r0 = r0.getSoccerConfig(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.util.List r0 = r0.getFilters()
            java.lang.Object r0 = r0.get(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            com.coveiot.android.sportsnotification.model.Filters r0 = (com.coveiot.android.sportsnotification.model.Filters) r0
            java.lang.Integer r0 = r0.getApiHitInterval()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r0 = r0.intValue()
            goto L6c
        L6a:
            r0 = 15
        L6c:
            r1 = 1
            if (r9 != 0) goto L79
            android.widget.TextView r0 = r8.getInterval_text()
            r3 = 8
            r0.setVisibility(r3)
            goto Lbc
        L79:
            android.widget.TextView r3 = r8.getInterval_text()
            r3.setVisibility(r2)
            android.widget.TextView r3 = r8.getInterval_text()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            kotlin.jvm.internal.StringCompanionObject r5 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            android.content.Context r5 = r7.b
            int r6 = com.coveiot.android.sportsnotification.R.string.score_will_be_updated_every_15min
            java.lang.String r5 = r5.getString(r6)
            java.lang.String r6 = "context.getString(R.stri…l_be_updated_every_15min)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6[r2] = r0
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r6, r1)
            java.lang.String r0 = java.lang.String.format(r5, r0)
            java.lang.String r5 = "format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
            r4.append(r0)
            java.lang.String r0 = " min"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r3.setText(r0)
        Lbc:
            android.widget.RadioButton r8 = r8.getSelectionState()
            int r0 = r7.d
            if (r0 != r9) goto Lc5
            r2 = r1
        Lc5:
            r8.setChecked(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.adapter.SportSettingAdapter.onBindViewHolder(com.coveiot.android.sportsnotification.adapter.SportSettingAdapter$ViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
