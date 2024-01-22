package com.google.android.youtube.player;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.crrepa.r.a;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.youtube.player.internal.z;
import com.szabh.smable3.entity.BleNotification;
import java.util.List;
/* loaded from: classes10.dex */
public final class YouTubeIntents {
    public static Intent a(Intent intent, Context context) {
        intent.putExtra("app_package", context.getPackageName()).putExtra("app_version", z.d(context)).putExtra("client_library_version", z.a());
        return intent;
    }

    public static Uri b(String str) {
        String valueOf = String.valueOf(str);
        return Uri.parse(valueOf.length() != 0 ? "https://www.youtube.com/playlist?list=".concat(valueOf) : new String("https://www.youtube.com/playlist?list="));
    }

    public static String c(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return z.b(packageManager) ? "com.google.android.youtube.tv" : z.a(packageManager) ? "com.google.android.youtube.googletv" : BleNotification.YOUTUBE;
    }

    public static boolean canResolveChannelIntent(Context context) {
        return e(context, Uri.parse("https://www.youtube.com/channel/"));
    }

    public static boolean canResolveOpenPlaylistIntent(Context context) {
        return e(context, Uri.parse("https://www.youtube.com/playlist?list="));
    }

    public static boolean canResolvePlayPlaylistIntent(Context context) {
        int installedYouTubeVersionCode = getInstalledYouTubeVersionCode(context);
        return (!z.a(context.getPackageManager()) ? installedYouTubeVersionCode >= 4000 : installedYouTubeVersionCode >= 4700) && canResolveOpenPlaylistIntent(context);
    }

    public static boolean canResolvePlayVideoIntent(Context context) {
        return e(context, Uri.parse("https://www.youtube.com/watch?v="));
    }

    public static boolean canResolvePlayVideoIntentWithOptions(Context context) {
        int installedYouTubeVersionCode = getInstalledYouTubeVersionCode(context);
        PackageManager packageManager = context.getPackageManager();
        return (z.b(packageManager) || (!z.a(packageManager) ? installedYouTubeVersionCode >= 3300 : installedYouTubeVersionCode >= Integer.MAX_VALUE)) && canResolvePlayVideoIntent(context);
    }

    public static boolean canResolveSearchIntent(Context context) {
        if (!z.a(context.getPackageManager()) || getInstalledYouTubeVersionCode(context) >= 4700) {
            return d(context, new Intent("android.intent.action.SEARCH").setPackage(c(context)));
        }
        return false;
    }

    public static boolean canResolveUploadIntent(Context context) {
        return d(context, new Intent("com.google.android.youtube.intent.action.UPLOAD").setPackage(c(context)).setType(a.f));
    }

    public static boolean canResolveUserIntent(Context context) {
        return e(context, Uri.parse("https://www.youtube.com/user/"));
    }

    public static Intent createChannelIntent(Context context, String str) {
        String valueOf = String.valueOf(str);
        return f(context, Uri.parse(valueOf.length() != 0 ? "https://www.youtube.com/channel/".concat(valueOf) : new String("https://www.youtube.com/channel/")));
    }

    public static Intent createOpenPlaylistIntent(Context context, String str) {
        return a(f(context, b(str)), context);
    }

    public static Intent createPlayPlaylistIntent(Context context, String str) {
        return a(f(context, b(str).buildUpon().appendQueryParameter("playnext", "1").build()), context);
    }

    public static Intent createPlayVideoIntent(Context context, String str) {
        return createPlayVideoIntentWithOptions(context, str, false, false);
    }

    public static Intent createPlayVideoIntentWithOptions(Context context, String str, boolean z, boolean z2) {
        String valueOf = String.valueOf(str);
        return f(context, Uri.parse(valueOf.length() != 0 ? "https://www.youtube.com/watch?v=".concat(valueOf) : new String("https://www.youtube.com/watch?v="))).putExtra("force_fullscreen", z).putExtra("finish_on_ended", z2);
    }

    public static Intent createSearchIntent(Context context, String str) {
        return a(new Intent("android.intent.action.SEARCH").setPackage(c(context)).putExtra(SearchIntents.EXTRA_QUERY, str), context);
    }

    public static Intent createUploadIntent(Context context, Uri uri) throws IllegalArgumentException {
        if (uri != null) {
            if (uri.toString().startsWith("content://media/")) {
                return a(new Intent("com.google.android.youtube.intent.action.UPLOAD").setPackage(c(context)).setDataAndType(uri, a.f), context);
            }
            throw new IllegalArgumentException("videoUri parameter must be a URI pointing to a valid video file. It must begin with \"content://media/\"");
        }
        throw new IllegalArgumentException("videoUri parameter cannot be null.");
    }

    public static Intent createUserIntent(Context context, String str) {
        String valueOf = String.valueOf(str);
        return f(context, Uri.parse(valueOf.length() != 0 ? "https://www.youtube.com/user/".concat(valueOf) : new String("https://www.youtube.com/user/")));
    }

    public static boolean d(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        return (queryIntentActivities == null || queryIntentActivities.isEmpty()) ? false : true;
    }

    public static boolean e(Context context, Uri uri) {
        return d(context, new Intent("android.intent.action.VIEW", uri).setPackage(c(context)));
    }

    public static Intent f(Context context, Uri uri) {
        return a(new Intent("android.intent.action.VIEW", uri).setPackage(c(context)), context);
    }

    public static int getInstalledYouTubeVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(c(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static String getInstalledYouTubeVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(c(context), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static boolean isYouTubeInstalled(Context context) {
        try {
            context.getPackageManager().getApplicationInfo(c(context), 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
