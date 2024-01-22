package com.coveiot.android.fitnessbuddies.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes4.dex */
public class ManageBuddiesAdapter extends BaseExpandableListAdapter {

    /* renamed from: a  reason: collision with root package name */
    public Context f4429a;
    public List<String> b;
    public HashMap<String, List<Requests>> c;
    public OnOptionSelectedListener d;

    /* loaded from: classes4.dex */
    public interface OnOptionSelectedListener {
        void onAccept(int i);

        void onDelete(int i);

        void onReject(int i);

        void onResend(int i, String str, String str2, Requests requests);

        void onUnfriend(int i);
    }

    /* loaded from: classes4.dex */
    public class a implements ImageLodeListener {
        public a(ManageBuddiesAdapter manageBuddiesAdapter) {
        }

        @Override // com.coveiot.utils.utility.ImageLodeListener
        public void onImageLoaded() {
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        public final /* synthetic */ Requests h;

        public b(Requests requests) {
            this.h = requests;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ManageBuddiesAdapter.this.d.onAccept(this.h.requestId);
        }
    }

    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        public final /* synthetic */ Requests h;

        public c(Requests requests) {
            this.h = requests;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ManageBuddiesAdapter.this.d.onReject(this.h.requestId);
        }
    }

    /* loaded from: classes4.dex */
    public class d implements ImageLodeListener {
        public d(ManageBuddiesAdapter manageBuddiesAdapter) {
        }

        @Override // com.coveiot.utils.utility.ImageLodeListener
        public void onImageLoaded() {
        }
    }

    /* loaded from: classes4.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toast.makeText(ManageBuddiesAdapter.this.f4429a, ManageBuddiesAdapter.this.f4429a.getString(R.string.invite_recieved_by_buddy), 1).show();
        }
    }

    /* loaded from: classes4.dex */
    public class f implements View.OnClickListener {
        public final /* synthetic */ Requests h;

        public f(Requests requests) {
            this.h = requests;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BUDDY_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUDDY_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.REINVITE_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            OnOptionSelectedListener onOptionSelectedListener = ManageBuddiesAdapter.this.d;
            Requests requests = this.h;
            onOptionSelectedListener.onResend(requests.requestId, requests.toUserMobileNumber, requests.toCloverName, requests);
        }
    }

    /* loaded from: classes4.dex */
    public class g implements View.OnClickListener {
        public final /* synthetic */ Requests h;

        public g(Requests requests) {
            this.h = requests;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BUDDY_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUDDY_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DELETE_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            ManageBuddiesAdapter.this.d.onDelete(this.h.requestId);
        }
    }

    /* loaded from: classes4.dex */
    public class h implements ImageLodeListener {
        public h(ManageBuddiesAdapter manageBuddiesAdapter) {
        }

        @Override // com.coveiot.utils.utility.ImageLodeListener
        public void onImageLoaded() {
        }
    }

    /* loaded from: classes4.dex */
    public class i implements View.OnClickListener {
        public final /* synthetic */ Requests h;

        public i(Requests requests) {
            this.h = requests;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BUDDY_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUDDY_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.UN_FRIEND.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            ManageBuddiesAdapter.this.d.onUnfriend(this.h.id);
        }
    }

    public ManageBuddiesAdapter(Context context, OnOptionSelectedListener onOptionSelectedListener, List<String> list, HashMap<String, List<Requests>> hashMap) {
        this.f4429a = context;
        this.b = list;
        this.c = hashMap;
        this.d = onOptionSelectedListener;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i2, int i3) {
        return i3;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i2, int i3, boolean z, View view, ViewGroup viewGroup) {
        View view2;
        Requests child = getChild(i2, i3);
        if (view == null) {
            view2 = ((LayoutInflater) this.f4429a.getSystemService("layout_inflater")).inflate(R.layout.buddies_list_item, viewGroup, false);
            View view3 = (View) view2.getParent();
            if (view3 != null) {
                view3.setBackgroundResource(R.drawable.bottom_corners_rounded_grey);
            }
        } else {
            view2 = view;
        }
        TextView textView = (TextView) view2.findViewById(R.id.name);
        TextView textView2 = (TextView) view2.findViewById(R.id.reinvite_info_icon);
        TextView textView3 = (TextView) view2.findViewById(R.id.phone);
        ImageView imageView = (ImageView) view2.findViewById(R.id.image);
        TextView textView4 = (TextView) view2.findViewById(R.id.positive_button);
        TextView textView5 = (TextView) view2.findViewById(R.id.negative_button);
        View findViewById = view2.findViewById(R.id.list_item_divider);
        RelativeLayout relativeLayout = (RelativeLayout) view2.findViewById(R.id.rl_main_layout);
        if (child != null) {
            if (getGroup(i2).equalsIgnoreCase(this.f4429a.getResources().getString(R.string.invites_received))) {
                textView.setText(child.fromUserName);
                textView3.setText(child.fromUserMobileNumber);
                GlideUtils.loadCircularImage(this.f4429a, child.fromUserDpUrl, imageView, new a(this));
                textView4.setText(this.f4429a.getString(R.string.accept));
                textView5.setText(this.f4429a.getString(R.string.reject));
                textView4.setVisibility(0);
                textView4.setOnClickListener(new b(child));
                textView5.setOnClickListener(new c(child));
                textView2.setVisibility(8);
            } else if (getGroup(i2).equalsIgnoreCase(this.f4429a.getResources().getString(R.string.invites_sent))) {
                if (!TextUtils.isEmpty(child.toUserName)) {
                    textView.setText(child.toUserName);
                } else {
                    textView.setText(child.toCloverName);
                }
                textView3.setText(child.toUserMobileNumber);
                String str = child.toUserDpUrl;
                if (str == null) {
                    Bitmap contactPhotoUri = CoveUtils.INSTANCE.getContactPhotoUri(child.toUserMobileNumber, this.f4429a);
                    if (contactPhotoUri != null) {
                        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.f4429a.getResources(), contactPhotoUri);
                        create.setCircular(true);
                        imageView.setImageDrawable(create);
                    } else {
                        imageView.setImageResource(R.drawable.default_avatar);
                    }
                } else {
                    GlideUtils.loadCircularImage(this.f4429a, str, imageView, new d(this));
                }
                textView4.setVisibility(0);
                textView4.setText(this.f4429a.getString(R.string.re_invite));
                textView5.setText(this.f4429a.getString(R.string.delete));
                if (child.inviteLocally) {
                    textView2.setVisibility(0);
                } else {
                    textView2.setVisibility(8);
                }
                textView2.setOnClickListener(new e());
                textView4.setOnClickListener(new f(child));
                textView5.setOnClickListener(new g(child));
            } else {
                textView.setText(child.name);
                textView3.setText(child.mobileNumber);
                GlideUtils.loadCircularImage(this.f4429a, child.dpUrl, imageView, new h(this));
                textView2.setVisibility(8);
                textView4.setVisibility(8);
                textView5.setText(this.f4429a.getString(R.string.un_friend));
                textView5.setOnClickListener(new i(child));
            }
        }
        if (z) {
            findViewById.setVisibility(8);
            relativeLayout.setBackgroundResource(R.drawable.bottom_corners_rounded_grey);
        } else {
            findViewById.setVisibility(0);
            relativeLayout.setBackgroundResource(R.color.color_333131);
        }
        return view2;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i2) {
        HashMap<String, List<Requests>> hashMap = this.c;
        if (hashMap == null || hashMap.get(this.b.get(i2)) == null) {
            return 0;
        }
        return this.c.get(this.b.get(i2)).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.b.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i2, boolean z, View view, ViewGroup viewGroup) {
        String group = getGroup(i2);
        if (view == null) {
            view = ((LayoutInflater) this.f4429a.getSystemService("layout_inflater")).inflate(R.layout.buddies_list_group, (ViewGroup) null);
        }
        TextView textView = (TextView) view.findViewById(R.id.notification_count);
        ImageView imageView = (ImageView) view.findViewById(R.id.arrow);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_buddies_group);
        ((TextView) view.findViewById(R.id.title)).setText(group);
        if (z) {
            imageView.setRotation(180.0f);
            if (getChildrenCount(i2) > 0) {
                relativeLayout.setBackgroundResource(R.drawable.rounded_top_corners_rounded_grey);
            } else {
                relativeLayout.setBackgroundResource(R.drawable.list_item_background_new);
            }
        } else {
            imageView.setRotation(0.0f);
            relativeLayout.setBackgroundResource(R.drawable.list_item_background_new);
        }
        if (getChildrenCount(i2) > 0) {
            textView.setVisibility(0);
            textView.setText(String.valueOf(getChildrenCount(i2)));
        } else {
            textView.setVisibility(8);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i2, int i3) {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public Requests getChild(int i2, int i3) {
        HashMap<String, List<Requests>> hashMap = this.c;
        if (hashMap == null || hashMap.isEmpty() || this.b.get(i2) == null || this.b.get(i2).isEmpty() || this.c.get(this.b.get(i2)) == null || this.c.get(this.b.get(i2)).isEmpty() || this.c.get(this.b.get(i2)).get(i3) == null) {
            return null;
        }
        return this.c.get(this.b.get(i2)).get(i3);
    }

    @Override // android.widget.ExpandableListAdapter
    public String getGroup(int i2) {
        return this.b.get(i2);
    }
}
