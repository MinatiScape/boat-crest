package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.fragments.SyncedContactsFragment;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySyncContacts extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void s(ActivitySyncContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void t(ActivitySyncContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.addContacts();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void addContacts() {
        startActivity(new Intent(this, AddSyncedContactsActivity.class));
    }

    public final void initToolbar() {
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(R.string.your_synced_contacts);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ej
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySyncContacts.s(ActivitySyncContacts.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.fj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySyncContacts.t(ActivitySyncContacts.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sync_contacts);
        u();
        initToolbar();
    }

    public final void u() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, SyncedContactsFragment.Companion.newInstance()).commit();
    }
}
