package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DidClickForHardPermissionListener;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView;
import com.clevertap.android.sdk.customviews.VerticalSpaceItemDecoration;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInboxListViewFragment extends Fragment {
    public CleverTapInstanceConfig h;
    public LinearLayout k;
    public MediaPlayerRecyclerView l;
    public RecyclerView m;
    public j n;
    public CTInboxStyleConfig o;
    public WeakReference<b> q;
    public int r;
    public DidClickForHardPermissionListener s;
    public boolean i = Utils.haveVideoPlayerSupport;
    public ArrayList<CTInboxMessage> j = new ArrayList<>();
    public boolean p = true;

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CTInboxListViewFragment.this.l.playVideo();
        }
    }

    /* loaded from: classes2.dex */
    public interface b {
        void messageDidClick(Context context, int i, CTInboxMessage cTInboxMessage, Bundle bundle, HashMap<String, String> hashMap, int i2);

        void messageDidShow(Context context, CTInboxMessage cTInboxMessage, Bundle bundle);
    }

    public void a(Bundle bundle, int i, int i2, HashMap<String, String> hashMap, int i3) {
        b e = e();
        if (e != null) {
            e.messageDidClick(getActivity().getBaseContext(), i2, this.j.get(i), bundle, hashMap, i3);
        }
    }

    public void b(Bundle bundle, int i) {
        b e = e();
        if (e != null) {
            Logger.v("CTInboxListViewFragment:didShow() called with: data = [" + bundle + "], position = [" + i + "]");
            e.messageDidShow(getActivity().getBaseContext(), this.j.get(i), bundle);
        }
    }

    public final ArrayList<CTInboxMessage> c(ArrayList<CTInboxMessage> arrayList, String str) {
        ArrayList<CTInboxMessage> arrayList2 = new ArrayList<>();
        Iterator<CTInboxMessage> it = arrayList.iterator();
        while (it.hasNext()) {
            CTInboxMessage next = it.next();
            if (next.getTags() != null && next.getTags().size() > 0) {
                for (String str2 : next.getTags()) {
                    if (str2.equalsIgnoreCase(str)) {
                        arrayList2.add(next);
                    }
                }
            }
        }
        return arrayList2;
    }

    public void d(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str.replace("\n", "").replace("\r", "")));
            if (getActivity() != null) {
                Utils.setPackageNameFromResolveInfoList(getActivity(), intent);
            }
            startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    public b e() {
        b bVar;
        try {
            bVar = this.q.get();
        } catch (Throwable unused) {
            bVar = null;
        }
        if (bVar == null) {
            Logger.v("InboxListener is null for messages");
        }
        return bVar;
    }

    public MediaPlayerRecyclerView f() {
        return this.l;
    }

    public void g(int i, int i2, String str, JSONObject jSONObject, HashMap<String, String> hashMap, int i3) {
        try {
            if (jSONObject != null) {
                String linktype = this.j.get(i).getInboxMessageContents().get(0).getLinktype(jSONObject);
                if (linktype.equalsIgnoreCase("url")) {
                    String linkUrl = this.j.get(i).getInboxMessageContents().get(0).getLinkUrl(jSONObject);
                    if (linkUrl != null) {
                        d(linkUrl);
                    }
                } else if (linktype.contains(Constants.KEY_REQUEST_FOR_NOTIFICATION_PERMISSION) && this.s != null) {
                    this.s.didClickForHardPermissionWithFallbackSettings(this.j.get(i).getInboxMessageContents().get(0).isFallbackSettingsEnabled(jSONObject));
                }
            } else {
                String actionUrl = this.j.get(i).getInboxMessageContents().get(0).getActionUrl();
                if (actionUrl != null) {
                    d(actionUrl);
                }
            }
            Bundle bundle = new Bundle();
            JSONObject wzrkParams = this.j.get(i).getWzrkParams();
            Iterator<String> keys = wzrkParams.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.startsWith(Constants.WZRK_PREFIX)) {
                    bundle.putString(next, wzrkParams.getString(next));
                }
            }
            if (str != null && !str.isEmpty()) {
                bundle.putString(Constants.KEY_C2A, str);
            }
            a(bundle, i, i2, hashMap, i3);
        } catch (Throwable th) {
            Logger.d("Error handling notification button click: " + th.getCause());
        }
    }

    public void h(int i, int i2) {
        try {
            Bundle bundle = new Bundle();
            JSONObject wzrkParams = this.j.get(i).getWzrkParams();
            Iterator<String> keys = wzrkParams.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.startsWith(Constants.WZRK_PREFIX)) {
                    bundle.putString(next, wzrkParams.getString(next));
                }
            }
            a(bundle, i, i2, null, -1);
            d(this.j.get(i).getInboxMessageContents().get(i2).getActionUrl());
        } catch (Throwable th) {
            Logger.d("Error handling notification button click: " + th.getCause());
        }
    }

    public void i(b bVar) {
        this.q = new WeakReference<>(bVar);
    }

    public void j(MediaPlayerRecyclerView mediaPlayerRecyclerView) {
        this.l = mediaPlayerRecyclerView;
    }

    public final boolean k() {
        return this.r <= 0;
    }

    public final void l() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        String string = arguments.getString("filter", null);
        CleverTapAPI instanceWithConfig = CleverTapAPI.instanceWithConfig(getActivity(), this.h);
        if (instanceWithConfig != null) {
            Logger.v("CTInboxListViewFragment:onAttach() called with: tabPosition = [" + this.r + "], filter = [" + string + "]");
            ArrayList<CTInboxMessage> allInboxMessages = instanceWithConfig.getAllInboxMessages();
            if (string != null) {
                allInboxMessages = c(allInboxMessages, string);
            }
            this.j = allInboxMessages;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.h = (CleverTapInstanceConfig) arguments.getParcelable(Constants.KEY_CONFIG);
            this.o = (CTInboxStyleConfig) arguments.getParcelable("styleConfig");
            this.r = arguments.getInt(DeviceKey.position, -1);
            l();
            if (context instanceof CTInboxActivity) {
                i((b) getActivity());
            }
            if (context instanceof DidClickForHardPermissionListener) {
                this.s = (DidClickForHardPermissionListener) context;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.inbox_list_view, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.list_view_linear_layout);
        this.k = linearLayout;
        linearLayout.setBackgroundColor(Color.parseColor(this.o.getInboxBackgroundColor()));
        TextView textView = (TextView) inflate.findViewById(R.id.list_view_no_message_view);
        if (this.j.size() <= 0) {
            textView.setVisibility(0);
            textView.setText(this.o.getNoMessageViewText());
            textView.setTextColor(Color.parseColor(this.o.getNoMessageViewTextColor()));
            return inflate;
        }
        textView.setVisibility(8);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.n = new j(this.j, this);
        if (this.i) {
            MediaPlayerRecyclerView mediaPlayerRecyclerView = new MediaPlayerRecyclerView(getActivity());
            this.l = mediaPlayerRecyclerView;
            j(mediaPlayerRecyclerView);
            this.l.setVisibility(0);
            this.l.setLayoutManager(linearLayoutManager);
            this.l.addItemDecoration(new VerticalSpaceItemDecoration(18));
            this.l.setItemAnimator(new DefaultItemAnimator());
            this.l.setAdapter(this.n);
            this.n.notifyDataSetChanged();
            this.k.addView(this.l);
            if (this.p && k()) {
                new Handler(Looper.getMainLooper()).postDelayed(new a(), 1000L);
                this.p = false;
            }
        } else {
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.list_view_recycler_view);
            this.m = recyclerView;
            recyclerView.setVisibility(0);
            this.m.setLayoutManager(linearLayoutManager);
            this.m.addItemDecoration(new VerticalSpaceItemDecoration(18));
            this.m.setItemAnimator(new DefaultItemAnimator());
            this.m.setAdapter(this.n);
            this.n.notifyDataSetChanged();
        }
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.l;
        if (mediaPlayerRecyclerView != null) {
            mediaPlayerRecyclerView.release();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.l;
        if (mediaPlayerRecyclerView != null) {
            mediaPlayerRecyclerView.onPausePlayer();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.l;
        if (mediaPlayerRecyclerView != null) {
            mediaPlayerRecyclerView.onRestartPlayer();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.l;
        if (mediaPlayerRecyclerView != null && mediaPlayerRecyclerView.getLayoutManager() != null) {
            bundle.putParcelable("recyclerLayoutState", this.l.getLayoutManager().onSaveInstanceState());
        }
        RecyclerView recyclerView = this.m;
        if (recyclerView == null || recyclerView.getLayoutManager() == null) {
            return;
        }
        bundle.putParcelable("recyclerLayoutState", this.m.getLayoutManager().onSaveInstanceState());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("recyclerLayoutState");
            MediaPlayerRecyclerView mediaPlayerRecyclerView = this.l;
            if (mediaPlayerRecyclerView != null && mediaPlayerRecyclerView.getLayoutManager() != null) {
                this.l.getLayoutManager().onRestoreInstanceState(parcelable);
            }
            RecyclerView recyclerView = this.m;
            if (recyclerView == null || recyclerView.getLayoutManager() == null) {
                return;
            }
            this.m.getLayoutManager().onRestoreInstanceState(parcelable);
        }
    }
}
