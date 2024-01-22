package com.coveiot.android.remotecommandframework;

import android.content.Context;
import com.coveiot.covepreferences.SessionManager;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class ConnectedDeviceInfoUtil {
    @NotNull
    public static final ConnectedDeviceInfoUtil INSTANCE = new ConnectedDeviceInfoUtil();

    @JvmStatic
    public static final boolean isCADevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca0), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false);
    }

    @JvmStatic
    public static final boolean isCZDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false);
    }

    @JvmStatic
    public static final boolean isJstyleDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1790_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1810g_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963yh_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963d_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1860_device), false);
    }

    @JvmStatic
    public static final boolean isKaHaDeviceWithRem(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false);
    }

    @JvmStatic
    public static final boolean isMatrixDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.matrix_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force), false);
    }

    @JvmStatic
    public static final boolean isMoyangDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangy20_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangygpf5_device), false);
    }

    @JvmStatic
    public static final boolean isSmaDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smaf2_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smas12_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_genesis_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_elevate_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_wave_glory_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_ultima_vogue), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_seek), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_comet), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.sma_lunar_velocity), false);
    }

    @JvmStatic
    public static final boolean isSmaS12Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smas12_device), false);
    }
}
