package com.mappls.sdk.navigation.helpers;

import androidx.core.content.ContextCompat;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.gpx.GPXUtilities;
import java.text.MessageFormat;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class a {
    static {
        Locale locale = Locale.US;
        new MessageFormat("{0, number,#.##} GB", locale);
        new MessageFormat("{0, number,##.#} MB", locale);
    }

    public static String a(String str, String str2) {
        return "<font color=\"" + str + "\">" + str2 + "</font>";
    }

    public static void a(NavigationApplication navigationApplication, GPXUtilities.GPXTrackAnalysis gPXTrackAnalysis) {
        String str;
        String a2 = com.mappls.sdk.navigation.util.a.a(ContextCompat.getColor(navigationApplication, R.color.mappls_gpx_time_span_color));
        String a3 = com.mappls.sdk.navigation.util.a.a(ContextCompat.getColor(navigationApplication, R.color.mappls_gpx_distance_color));
        String a4 = com.mappls.sdk.navigation.util.a.a(ContextCompat.getColor(navigationApplication, R.color.mappls_gpx_speed));
        String a5 = com.mappls.sdk.navigation.util.a.a(ContextCompat.getColor(navigationApplication, R.color.mappls_gpx_altitude_asc));
        String a6 = com.mappls.sdk.navigation.util.a.a(ContextCompat.getColor(navigationApplication, R.color.mappls_gpx_altitude_desc));
        int i = R.string.mappls_gpx_info_distance;
        navigationApplication.getString(i, a(a3, NavigationFormatter.getFormattedDistance(gPXTrackAnalysis.totalDistance, navigationApplication)), a(a3, gPXTrackAnalysis.points + ""));
        if (gPXTrackAnalysis.totalTracks > 1) {
            int i2 = R.string.mappls_gpx_info_subtracks;
            navigationApplication.getString(i2, a(a4, gPXTrackAnalysis.totalTracks + ""));
        }
        if (gPXTrackAnalysis.wptPoints > 0) {
            int i3 = R.string.mappls_gpx_info_waypoints;
            navigationApplication.getString(i3, a(a4, gPXTrackAnalysis.wptPoints + ""));
        }
        if (gPXTrackAnalysis.isTimeSpecified()) {
            navigationApplication.getString(R.string.mappls_gpx_info_start_time, Long.valueOf(gPXTrackAnalysis.startTime));
            navigationApplication.getString(R.string.mappls_gpx_info_end_time, Long.valueOf(gPXTrackAnalysis.endTime));
        }
        long j = gPXTrackAnalysis.timeSpan;
        if (j > 0) {
            long j2 = j / 1000;
            str = "";
            if (j2 != gPXTrackAnalysis.timeMoving / 1000) {
                navigationApplication.getString(R.string.mappls_gpx_timespan, a(a2, com.mappls.sdk.navigation.util.a.a((int) j2, true)));
            }
        } else {
            str = "";
        }
        if (gPXTrackAnalysis.isTimeMoving()) {
            navigationApplication.getString(R.string.mappls_gpx_timemoving, a(a2, com.mappls.sdk.navigation.util.a.a((int) (gPXTrackAnalysis.timeMoving / 1000), true)));
            a(a3, NavigationFormatter.getFormattedDistance(gPXTrackAnalysis.totalDistanceMoving, navigationApplication));
        }
        if (gPXTrackAnalysis.isElevationSpecified()) {
            navigationApplication.getString(R.string.mappls_gpx_info_avg_altitude, a(a4, NavigationFormatter.getFormattedAlt(gPXTrackAnalysis.avgElevation, navigationApplication)));
            String a7 = a(a6, NavigationFormatter.getFormattedAlt(gPXTrackAnalysis.minElevation, navigationApplication));
            String a8 = a(a5, NavigationFormatter.getFormattedAlt(gPXTrackAnalysis.maxElevation, navigationApplication));
            String a9 = a(a5, NavigationFormatter.getFormattedAlt(gPXTrackAnalysis.diffElevationUp, navigationApplication));
            String a10 = a(a6, NavigationFormatter.getFormattedAlt(gPXTrackAnalysis.diffElevationDown, navigationApplication));
            int i4 = R.string.mappls_gpx_info_diff_altitude;
            navigationApplication.getString(i4, a7 + " - " + a8);
            int i5 = R.string.mappls_gpx_info_asc_altitude;
            navigationApplication.getString(i5, "↓ " + a10 + "   ↑ " + a9 + str);
        }
        if (gPXTrackAnalysis.isSpeedSpecified()) {
            String a11 = a(a4, NavigationFormatter.getFormattedSpeed(gPXTrackAnalysis.avgSpeed, navigationApplication));
            String a12 = a(a5, NavigationFormatter.getFormattedSpeed(gPXTrackAnalysis.maxSpeed, navigationApplication));
            navigationApplication.getString(R.string.mappls_gpx_info_average_speed, a11);
            navigationApplication.getString(R.string.mappls_gpx_info_maximum_speed, a12);
        }
    }

    public static String b(String str, String str2) {
        return a(str, str2);
    }
}
