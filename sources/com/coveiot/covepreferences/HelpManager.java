package com.coveiot.covepreferences;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes8.dex */
public class HelpManager {
    public static final String KEY_ACHIEVEMENTS_HELP = "achievements_page";
    public static final String KEY_BAND = "band";
    public static final String KEY_BUDDIES_HELP = "buddies_page";
    public static final String KEY_FASTLANE_HELP = "fastlane_page";
    public static final String KEY_HOME_HELP = "home_page";
    public static final String KEY_MORE_HELP = "more_page";
    public static final String KEY_ONBOARDING = "onboarding";
    public static final String KEY_SHARE = "share";

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f7003a;
    public static SharedPreferences.Editor b;
    public static int c;
    public static HelpManager d;

    public HelpManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HELP_SCREEN", c);
        f7003a = sharedPreferences;
        b = sharedPreferences.edit();
    }

    public static HelpManager getInstance(Context context) {
        if (d == null) {
            d = new HelpManager(context);
        }
        return d;
    }

    public void achievementsHelpPage() {
        b.putBoolean(KEY_ACHIEVEMENTS_HELP, true);
        b.commit();
    }

    public boolean achievementsVisited() {
        return f7003a.getBoolean(KEY_ACHIEVEMENTS_HELP, false);
    }

    public void bandClicked() {
        b.putBoolean(KEY_BAND, true);
        b.commit();
    }

    public void buddiesHelpPage() {
        b.putBoolean(KEY_BUDDIES_HELP, true);
        b.commit();
    }

    public boolean buddiesVisited() {
        return f7003a.getBoolean(KEY_BUDDIES_HELP, false);
    }

    public void clearPreferences(Context context) {
        for (String str : f7003a.getAll().keySet()) {
            remove(context, str);
        }
    }

    public void fastlaneHelpPage() {
        b.putBoolean(KEY_FASTLANE_HELP, true);
        b.commit();
    }

    public boolean fastlaneVisited() {
        return f7003a.getBoolean(KEY_FASTLANE_HELP, false);
    }

    public void homeHelpPage() {
        b.putBoolean(KEY_HOME_HELP, true);
        b.commit();
    }

    public boolean homeVisited() {
        return f7003a.getBoolean(KEY_HOME_HELP, false);
    }

    public boolean isBandClicked() {
        return f7003a.getBoolean(KEY_BAND, false);
    }

    public void moreHelpPage() {
        b.putBoolean(KEY_MORE_HELP, true);
        b.commit();
    }

    public boolean moreVisited() {
        return f7003a.getBoolean(KEY_MORE_HELP, false);
    }

    public void onboardingPage() {
        b.putBoolean(KEY_ONBOARDING, true);
        b.commit();
    }

    public boolean onboardingVisited() {
        return f7003a.getBoolean(KEY_ONBOARDING, false);
    }

    public void remove(Context context, String str) {
        if (f7003a.contains(str)) {
            b.remove(str);
            b.commit();
        }
    }

    public void sharePage() {
        b.putBoolean("share", true);
        b.commit();
    }

    public boolean shareVisited() {
        return f7003a.getBoolean("share", false);
    }
}
