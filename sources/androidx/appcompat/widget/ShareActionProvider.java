package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.view.ActionProvider;
/* loaded from: classes.dex */
public class ShareActionProvider extends ActionProvider {
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    public int d;
    public final b e;
    public final Context f;
    public String g;
    public OnShareTargetSelectedListener h;
    public ActivityChooserModel.OnChooseActivityListener i;

    /* loaded from: classes.dex */
    public interface OnShareTargetSelectedListener {
        boolean onShareTargetSelected(ShareActionProvider shareActionProvider, Intent intent);
    }

    /* loaded from: classes.dex */
    public class a implements ActivityChooserModel.OnChooseActivityListener {
        public a() {
        }

        @Override // androidx.appcompat.widget.ActivityChooserModel.OnChooseActivityListener
        public boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent) {
            ShareActionProvider shareActionProvider = ShareActionProvider.this;
            OnShareTargetSelectedListener onShareTargetSelectedListener = shareActionProvider.h;
            if (onShareTargetSelectedListener != null) {
                onShareTargetSelectedListener.onShareTargetSelected(shareActionProvider, intent);
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class b implements MenuItem.OnMenuItemClickListener {
        public b() {
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            ShareActionProvider shareActionProvider = ShareActionProvider.this;
            Intent b = ActivityChooserModel.d(shareActionProvider.f, shareActionProvider.g).b(menuItem.getItemId());
            if (b != null) {
                String action = b.getAction();
                if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                    ShareActionProvider.this.b(b);
                }
                ShareActionProvider.this.f.startActivity(b);
                return true;
            }
            return true;
        }
    }

    public ShareActionProvider(Context context) {
        super(context);
        this.d = 4;
        this.e = new b();
        this.g = DEFAULT_SHARE_HISTORY_FILE_NAME;
        this.f = context;
    }

    public final void a() {
        if (this.h == null) {
            return;
        }
        if (this.i == null) {
            this.i = new a();
        }
        ActivityChooserModel.d(this.f, this.g).q(this.i);
    }

    public void b(Intent intent) {
        if (Build.VERSION.SDK_INT >= 21) {
            intent.addFlags(134742016);
        } else {
            intent.addFlags(524288);
        }
    }

    @Override // androidx.core.view.ActionProvider
    public boolean hasSubMenu() {
        return true;
    }

    @Override // androidx.core.view.ActionProvider
    public View onCreateActionView() {
        ActivityChooserView activityChooserView = new ActivityChooserView(this.f);
        if (!activityChooserView.isInEditMode()) {
            activityChooserView.setActivityChooserModel(ActivityChooserModel.d(this.f, this.g));
        }
        TypedValue typedValue = new TypedValue();
        this.f.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, typedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(AppCompatResources.getDrawable(this.f, typedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    @Override // androidx.core.view.ActionProvider
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        ActivityChooserModel d = ActivityChooserModel.d(this.f, this.g);
        PackageManager packageManager = this.f.getPackageManager();
        int f = d.f();
        int min = Math.min(f, this.d);
        for (int i = 0; i < min; i++) {
            ResolveInfo e = d.e(i);
            subMenu.add(0, i, i, e.loadLabel(packageManager)).setIcon(e.loadIcon(packageManager)).setOnMenuItemClickListener(this.e);
        }
        if (min < f) {
            SubMenu addSubMenu = subMenu.addSubMenu(0, min, min, this.f.getString(R.string.abc_activity_chooser_view_see_all));
            for (int i2 = 0; i2 < f; i2++) {
                ResolveInfo e2 = d.e(i2);
                addSubMenu.add(0, i2, i2, e2.loadLabel(packageManager)).setIcon(e2.loadIcon(packageManager)).setOnMenuItemClickListener(this.e);
            }
        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener onShareTargetSelectedListener) {
        this.h = onShareTargetSelectedListener;
        a();
    }

    public void setShareHistoryFileName(String str) {
        this.g = str;
        a();
    }

    public void setShareIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                b(intent);
            }
        }
        ActivityChooserModel.d(this.f, this.g).p(intent);
    }
}
