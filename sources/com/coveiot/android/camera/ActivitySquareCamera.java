package com.coveiot.android.camera;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.camera.fragments.GalleryFragment;
import com.coveiot.android.theme.BaseActivity;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivitySquareCamera extends BaseActivity implements GalleryFragment.PhotoSelectionListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final File getOutputDirectory(@NotNull Context context) {
            File file;
            Intrinsics.checkNotNullParameter(context, "context");
            Context applicationContext = context.getApplicationContext();
            File[] externalMediaDirs = context.getExternalMediaDirs();
            Intrinsics.checkNotNullExpressionValue(externalMediaDirs, "context.externalMediaDirs");
            File file2 = (File) ArraysKt___ArraysKt.firstOrNull(externalMediaDirs);
            if (file2 != null) {
                file = new File(file2, applicationContext.getResources().getString(R.string.app_name));
                file.mkdirs();
            } else {
                file = null;
            }
            if (file == null || !file.exists()) {
                File filesDir = applicationContext.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "appContext.filesDir");
                return filesDir;
            }
            return file;
        }
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.take_picture));
        View findViewById = findViewById(R.id.fragment_container);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.fragment_container)");
        FrameLayout frameLayout = (FrameLayout) findViewById;
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(@Nullable Menu menu) {
        getMenuInflater().inflate(R.menu.camera_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, @NotNull KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (i == 25) {
            Intent intent = new Intent(ActivitySquareCameraKt.KEY_EVENT_ACTION);
            intent.putExtra(ActivitySquareCameraKt.KEY_EVENT_EXTRA, i);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            return true;
        }
        return super.onKeyDown(i, event);
    }

    @Override // com.coveiot.android.camera.fragments.GalleryFragment.PhotoSelectionListener
    public void onPhototSelected(@NotNull Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        getIntent().putExtra(ActivitySquareCameraKt.EXTRA_SQUARE_IMAGE_URI, uri);
        setResult(-1, getIntent());
        finish();
    }
}
