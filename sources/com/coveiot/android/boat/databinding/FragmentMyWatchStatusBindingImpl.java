package com.coveiot.android.boat.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.boat.R;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderFlexConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLeapCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallConnectAceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCometBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarEmbraceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarFitBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarSeekBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarVelocityBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderMatrixBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderMercuryBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp1WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp2WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp3WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp4WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPrimiaAceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPrimiaBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPs1EnigmaOasisBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormCallProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormConnectPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStrideVoiceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaVogueBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderVertexBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveArmour2Binding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveArmourBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveBeatBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveBeatCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveCallPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveConnectPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveElevateProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveEliteBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveForce2Binding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveForceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveGenesisProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveGloryProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveLynkVoiceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveMagmaBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveNeoBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePlayBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePrimeBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePrimeTalkBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSelectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSmartBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSmartCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSpectraBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveStyleBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveStyleCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavefitBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendCallPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendSportsBinding;
import com.google.common.primitives.Longs;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* loaded from: classes3.dex */
public class FragmentMyWatchStatusBindingImpl extends FragmentMyWatchStatusBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    public long h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(94);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"watch_face_holder_storm_pro", "watch_face_holder_lunar_call_pro", "watch_face_holder_mercury", "watch_face_holder_matrix", "watch_face_holder_wave_select", "watch_face_holder_wave_prime_talk", "watch_face_holder_lunar_call", "watch_face_holder_ultima_connect", "watch_face_holder_wave_play", "watch_face_holder_wave_smart_call", "watch_face_holder_lunar_call_connect_ace", "watch_face_holder_vertex", "watch_face_holder_xtend_sports", "watch_face_holder_wave_elite", "watch_face_holder_lunar_connect", "watch_face_holder_wave_force", "watch_face_holder_wave_smart", "watch_face_holder_wave_connect", "watch_face_holder_wave_pro", "watch_face_holder_wave_prime", "watch_face_holder_storm_call_pro", "watch_face_holder_lunar_connect_pro", "watch_face_holder_lunar_call_plus", "watch_face_holder_wave_armour", "watch_face_holder_wave_beat_call", "watch_face_holder_xtend_call_plus", "watch_face_holder_wavefit", "watch_face_holder_cosmos", "watch_face_holder_wave_call_plus", "watch_face_holder_lunar_connect_plus", "watch_face_holder_wave_style", "watch_face_holder_wave_style_call", "watch_face_holder_storm_connect_plus", "watch_face_holder_primia", "watch_face_holder_xtend_pro", "watch_face_holder_cosmos_pro", "watch_face_holder_wave_connect_plus", "watch_face_holder_ultima_call", "watch_face_holder_wave_beat", "watch_face_holder_wave_lynk_voice", "watch_face_holder_primia_ace", "watch_face_holder_cosmos_plus", "watch_face_holder_flex_connect", "watch_face_holder_leap_call", "watch_face_holder_storm_plus", "watch_face_holder_stride_voice", "watch_face_holder_xtend_plus", "watch_face_holder_opp1_watches", "watch_face_holder_opp2_watches", "watch_face_holder_opp3_watches", "watch_face_holder_opp4_watches", "watch_face_holder_wave_armour2", "watch_face_holder_wave_force2", "watch_face_holder_lunar_fit", "watch_face_holder_wave_genesis_pro", "watch_face_holder_wave_elevate_pro", "watch_face_holder_wave_glory_pro", "watch_face_holder_ultima_vogue", "watch_face_holder_lunar_seek", "watch_face_holder_lunar_comet", "watch_face_holder_lunar_velocity", "watch_face_holder_wave_neo", "watch_face_holder_wave_magma", "watch_face_holder_lunar_embrace", "watch_face_holder_wave_spectra", "watch_face_holder_ps1_enigma_oasis"}, new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67}, new int[]{R.layout.watch_face_holder_storm_pro, R.layout.watch_face_holder_lunar_call_pro, R.layout.watch_face_holder_mercury, R.layout.watch_face_holder_matrix, R.layout.watch_face_holder_wave_select, R.layout.watch_face_holder_wave_prime_talk, R.layout.watch_face_holder_lunar_call, R.layout.watch_face_holder_ultima_connect, R.layout.watch_face_holder_wave_play, R.layout.watch_face_holder_wave_smart_call, R.layout.watch_face_holder_lunar_call_connect_ace, R.layout.watch_face_holder_vertex, R.layout.watch_face_holder_xtend_sports, R.layout.watch_face_holder_wave_elite, R.layout.watch_face_holder_lunar_connect, R.layout.watch_face_holder_wave_force, R.layout.watch_face_holder_wave_smart, R.layout.watch_face_holder_wave_connect, R.layout.watch_face_holder_wave_pro, R.layout.watch_face_holder_wave_prime, R.layout.watch_face_holder_storm_call_pro, R.layout.watch_face_holder_lunar_connect_pro, R.layout.watch_face_holder_lunar_call_plus, R.layout.watch_face_holder_wave_armour, R.layout.watch_face_holder_wave_beat_call, R.layout.watch_face_holder_xtend_call_plus, R.layout.watch_face_holder_wavefit, R.layout.watch_face_holder_cosmos, R.layout.watch_face_holder_wave_call_plus, R.layout.watch_face_holder_lunar_connect_plus, R.layout.watch_face_holder_wave_style, R.layout.watch_face_holder_wave_style_call, R.layout.watch_face_holder_storm_connect_plus, R.layout.watch_face_holder_primia, R.layout.watch_face_holder_xtend_pro, R.layout.watch_face_holder_cosmos_pro, R.layout.watch_face_holder_wave_connect_plus, R.layout.watch_face_holder_ultima_call, R.layout.watch_face_holder_wave_beat, R.layout.watch_face_holder_wave_lynk_voice, R.layout.watch_face_holder_primia_ace, R.layout.watch_face_holder_cosmos_plus, R.layout.watch_face_holder_flex_connect, R.layout.watch_face_holder_leap_call, R.layout.watch_face_holder_storm_plus, R.layout.watch_face_holder_stride_voice, R.layout.watch_face_holder_xtend_plus, R.layout.watch_face_holder_opp1_watches, R.layout.watch_face_holder_opp2_watches, R.layout.watch_face_holder_opp3_watches, R.layout.watch_face_holder_opp4_watches, R.layout.watch_face_holder_wave_armour2, R.layout.watch_face_holder_wave_force2, R.layout.watch_face_holder_lunar_fit, R.layout.watch_face_holder_wave_genesis_pro, R.layout.watch_face_holder_wave_elevate_pro, R.layout.watch_face_holder_wave_glory_pro, R.layout.watch_face_holder_ultima_vogue, R.layout.watch_face_holder_lunar_seek, R.layout.watch_face_holder_lunar_comet, R.layout.watch_face_holder_lunar_velocity, R.layout.watch_face_holder_wave_neo, R.layout.watch_face_holder_wave_magma, R.layout.watch_face_holder_lunar_embrace, R.layout.watch_face_holder_wave_spectra, R.layout.watch_face_holder_ps1_enigma_oasis});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.clGuestUser, 68);
        sparseIntArray.put(R.id.ivGuestWatch, 69);
        sparseIntArray.put(R.id.tvWatch, 70);
        sparseIntArray.put(R.id.viewOne, 71);
        sparseIntArray.put(R.id.tvBattery, 72);
        sparseIntArray.put(R.id.viewTwo, 73);
        sparseIntArray.put(R.id.tvRefresh, 74);
        sparseIntArray.put(R.id.viewThree, 75);
        sparseIntArray.put(R.id.tvCall, 76);
        sparseIntArray.put(R.id.llWatchStatus, 77);
        sparseIntArray.put(R.id.clConnectionStatus, 78);
        sparseIntArray.put(R.id.btStatusImgV, 79);
        sparseIntArray.put(R.id.btStatusTv, 80);
        sparseIntArray.put(R.id.clBatteryStatus, 81);
        sparseIntArray.put(R.id.batteryStatusImgV, 82);
        sparseIntArray.put(R.id.batteryProgressBar, 83);
        sparseIntArray.put(R.id.batteryStatusTv, 84);
        sparseIntArray.put(R.id.view2, 85);
        sparseIntArray.put(R.id.clSyncStatus, 86);
        sparseIntArray.put(R.id.syncStatusImgV, 87);
        sparseIntArray.put(R.id.syncStatusTv, 88);
        sparseIntArray.put(R.id.view3, 89);
        sparseIntArray.put(R.id.clBTCallingStatus, 90);
        sparseIntArray.put(R.id.btCallStatusImgV, 91);
        sparseIntArray.put(R.id.btCallStatusTv, 92);
        sparseIntArray.put(R.id.view, 93);
    }

    public FragmentMyWatchStatusBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 94, j, k));
    }

    public final boolean A(WatchFaceHolderStrideVoiceBinding watchFaceHolderStrideVoiceBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    public final boolean B(WatchFaceHolderUltimaCallBinding watchFaceHolderUltimaCallBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    public final boolean C(WatchFaceHolderUltimaConnectBinding watchFaceHolderUltimaConnectBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 134217728;
            }
            return true;
        }
        return false;
    }

    public final boolean D(WatchFaceHolderUltimaVogueBinding watchFaceHolderUltimaVogueBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 512;
            }
            return true;
        }
        return false;
    }

    public final boolean E(WatchFaceHolderVertexBinding watchFaceHolderVertexBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 281474976710656L;
            }
            return true;
        }
        return false;
    }

    public final boolean F(WatchFaceHolderWaveArmourBinding watchFaceHolderWaveArmourBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            }
            return true;
        }
        return false;
    }

    public final boolean G(WatchFaceHolderWaveArmour2Binding watchFaceHolderWaveArmour2Binding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 2251799813685248L;
            }
            return true;
        }
        return false;
    }

    public final boolean H(WatchFaceHolderWaveBeatBinding watchFaceHolderWaveBeatBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 536870912;
            }
            return true;
        }
        return false;
    }

    public final boolean I(WatchFaceHolderWaveBeatCallBinding watchFaceHolderWaveBeatCallBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 4194304;
            }
            return true;
        }
        return false;
    }

    public final boolean J(WatchFaceHolderWaveCallPlusBinding watchFaceHolderWaveCallPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 64;
            }
            return true;
        }
        return false;
    }

    public final boolean K(WatchFaceHolderWaveConnectBinding watchFaceHolderWaveConnectBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean L(WatchFaceHolderWaveConnectPlusBinding watchFaceHolderWaveConnectPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean M(WatchFaceHolderWaveElevateProBinding watchFaceHolderWaveElevateProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    public final boolean N(WatchFaceHolderWaveEliteBinding watchFaceHolderWaveEliteBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 562949953421312L;
            }
            return true;
        }
        return false;
    }

    public final boolean O(WatchFaceHolderWavefitBinding watchFaceHolderWavefitBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 1024;
            }
            return true;
        }
        return false;
    }

    public final boolean P(WatchFaceHolderWaveForceBinding watchFaceHolderWaveForceBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 70368744177664L;
            }
            return true;
        }
        return false;
    }

    public final boolean Q(WatchFaceHolderWaveForce2Binding watchFaceHolderWaveForce2Binding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= Longs.MAX_POWER_OF_TWO;
            }
            return true;
        }
        return false;
    }

    public final boolean R(WatchFaceHolderWaveGenesisProBinding watchFaceHolderWaveGenesisProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 1099511627776L;
            }
            return true;
        }
        return false;
    }

    public final boolean S(WatchFaceHolderWaveGloryProBinding watchFaceHolderWaveGloryProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 18014398509481984L;
            }
            return true;
        }
        return false;
    }

    public final boolean T(WatchFaceHolderLunarFitBinding watchFaceHolderLunarFitBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
            }
            return true;
        }
        return false;
    }

    public final boolean U(WatchFaceHolderWaveMagmaBinding watchFaceHolderWaveMagmaBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 8589934592L;
            }
            return true;
        }
        return false;
    }

    public final boolean V(WatchFaceHolderWaveNeoBinding watchFaceHolderWaveNeoBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 16777216;
            }
            return true;
        }
        return false;
    }

    public final boolean W(WatchFaceHolderWavePlayBinding watchFaceHolderWavePlayBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean X(WatchFaceHolderWavePrimeBinding watchFaceHolderWavePrimeBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 1073741824;
            }
            return true;
        }
        return false;
    }

    public final boolean Y(WatchFaceHolderWavePrimeTalkBinding watchFaceHolderWavePrimeTalkBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 549755813888L;
            }
            return true;
        }
        return false;
    }

    public final boolean Z(WatchFaceHolderWaveProBinding watchFaceHolderWaveProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 33554432;
            }
            return true;
        }
        return false;
    }

    public final boolean a(WatchFaceHolderCosmosBinding watchFaceHolderCosmosBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 268435456;
            }
            return true;
        }
        return false;
    }

    public final boolean a0(WatchFaceHolderWaveSelectBinding watchFaceHolderWaveSelectBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 36028797018963968L;
            }
            return true;
        }
        return false;
    }

    public final boolean b(WatchFaceHolderCosmosPlusBinding watchFaceHolderCosmosPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            return true;
        }
        return false;
    }

    public final boolean b0(WatchFaceHolderWaveSmartBinding watchFaceHolderWaveSmartBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 8;
            }
            return true;
        }
        return false;
    }

    public final boolean c(WatchFaceHolderCosmosProBinding watchFaceHolderCosmosProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            return true;
        }
        return false;
    }

    public final boolean c0(WatchFaceHolderWaveSmartCallBinding watchFaceHolderWaveSmartCallBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 9007199254740992L;
            }
            return true;
        }
        return false;
    }

    public final boolean d(WatchFaceHolderFlexConnectBinding watchFaceHolderFlexConnectBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 67108864;
            }
            return true;
        }
        return false;
    }

    public final boolean d0(WatchFaceHolderWaveSpectraBinding watchFaceHolderWaveSpectraBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            return true;
        }
        return false;
    }

    public final boolean e(WatchFaceHolderLeapCallBinding watchFaceHolderLeapCallBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= LockFreeTaskQueueCore.FROZEN_MASK;
            }
            return true;
        }
        return false;
    }

    public final boolean e0(WatchFaceHolderWaveStyleBinding watchFaceHolderWaveStyleBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 72057594037927936L;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
            this.i = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.wfStormPro);
        ViewDataBinding.executeBindingsOn(this.wfLunarCallPro);
        ViewDataBinding.executeBindingsOn(this.wfMercury);
        ViewDataBinding.executeBindingsOn(this.wfMatrix);
        ViewDataBinding.executeBindingsOn(this.wfWaveSelect);
        ViewDataBinding.executeBindingsOn(this.wfWavePrimeTalk);
        ViewDataBinding.executeBindingsOn(this.wfLunarCall);
        ViewDataBinding.executeBindingsOn(this.wfUltimaConnect);
        ViewDataBinding.executeBindingsOn(this.wfWavePlay);
        ViewDataBinding.executeBindingsOn(this.wfWaveSmartCall);
        ViewDataBinding.executeBindingsOn(this.wfLunarCallConnectAce);
        ViewDataBinding.executeBindingsOn(this.wfVertex);
        ViewDataBinding.executeBindingsOn(this.wfXtendSport);
        ViewDataBinding.executeBindingsOn(this.wfWaveElite);
        ViewDataBinding.executeBindingsOn(this.wfLunarConnect);
        ViewDataBinding.executeBindingsOn(this.wfWaveForce);
        ViewDataBinding.executeBindingsOn(this.wfWaveSmart);
        ViewDataBinding.executeBindingsOn(this.wfWaveConnect);
        ViewDataBinding.executeBindingsOn(this.wfWavePro);
        ViewDataBinding.executeBindingsOn(this.wfWavePrime);
        ViewDataBinding.executeBindingsOn(this.wfStormCallPro);
        ViewDataBinding.executeBindingsOn(this.wfLunarConnectPro);
        ViewDataBinding.executeBindingsOn(this.wfLunarCallPlus);
        ViewDataBinding.executeBindingsOn(this.wfWaveArmour);
        ViewDataBinding.executeBindingsOn(this.wfWaveBeatCall);
        ViewDataBinding.executeBindingsOn(this.wfXtendCallPlus);
        ViewDataBinding.executeBindingsOn(this.wfWaveFit);
        ViewDataBinding.executeBindingsOn(this.wfCosmos);
        ViewDataBinding.executeBindingsOn(this.wfWaveCallPlus);
        ViewDataBinding.executeBindingsOn(this.wfLunarConnectPlus);
        ViewDataBinding.executeBindingsOn(this.wfWaveStyle);
        ViewDataBinding.executeBindingsOn(this.wfWaveStyleCall);
        ViewDataBinding.executeBindingsOn(this.wfStormConnectPlus);
        ViewDataBinding.executeBindingsOn(this.wfPrimia);
        ViewDataBinding.executeBindingsOn(this.wfXtendPro);
        ViewDataBinding.executeBindingsOn(this.wfCosmosPro);
        ViewDataBinding.executeBindingsOn(this.wfWaveConnectPlus);
        ViewDataBinding.executeBindingsOn(this.wfUltimaCall);
        ViewDataBinding.executeBindingsOn(this.wfWaveBeat);
        ViewDataBinding.executeBindingsOn(this.wfLynkVoice);
        ViewDataBinding.executeBindingsOn(this.wfPrimaAce);
        ViewDataBinding.executeBindingsOn(this.wfCosmosPlus);
        ViewDataBinding.executeBindingsOn(this.wfFlexConnect);
        ViewDataBinding.executeBindingsOn(this.wfLeapCall);
        ViewDataBinding.executeBindingsOn(this.wfStormPlus);
        ViewDataBinding.executeBindingsOn(this.wfStrideVoice);
        ViewDataBinding.executeBindingsOn(this.wfXtendPlus);
        ViewDataBinding.executeBindingsOn(this.wfopp1Watches);
        ViewDataBinding.executeBindingsOn(this.wfopp2Watches);
        ViewDataBinding.executeBindingsOn(this.wfopp3Watches);
        ViewDataBinding.executeBindingsOn(this.wfopp4Watches);
        ViewDataBinding.executeBindingsOn(this.wfWaveArmour2);
        ViewDataBinding.executeBindingsOn(this.wfWaveForce2);
        ViewDataBinding.executeBindingsOn(this.wfWaveLunarFit);
        ViewDataBinding.executeBindingsOn(this.wfWaveGenesisPro);
        ViewDataBinding.executeBindingsOn(this.wfWaveElevatePro);
        ViewDataBinding.executeBindingsOn(this.wfWaveGloryPro);
        ViewDataBinding.executeBindingsOn(this.wfUltimaVogue);
        ViewDataBinding.executeBindingsOn(this.wfLunarSeek);
        ViewDataBinding.executeBindingsOn(this.wfLunarComet);
        ViewDataBinding.executeBindingsOn(this.wfLunarVelocity);
        ViewDataBinding.executeBindingsOn(this.wfWaveNeo);
        ViewDataBinding.executeBindingsOn(this.wfWaveMagma);
        ViewDataBinding.executeBindingsOn(this.wfLunarEmbrace);
        ViewDataBinding.executeBindingsOn(this.wfWaveSpectra);
        ViewDataBinding.executeBindingsOn(this.wfPS1EnigmaOasis);
    }

    public final boolean f(WatchFaceHolderLunarCallBinding watchFaceHolderLunarCallBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= LockFreeTaskQueueCore.CLOSED_MASK;
            }
            return true;
        }
        return false;
    }

    public final boolean f0(WatchFaceHolderWaveStyleCallBinding watchFaceHolderWaveStyleCallBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 288230376151711744L;
            }
            return true;
        }
        return false;
    }

    public final boolean g(WatchFaceHolderLunarCallConnectAceBinding watchFaceHolderLunarCallConnectAceBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 16384;
            }
            return true;
        }
        return false;
    }

    public final boolean g0(WatchFaceHolderXtendCallPlusBinding watchFaceHolderXtendCallPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 2147483648L;
            }
            return true;
        }
        return false;
    }

    public final boolean h(WatchFaceHolderLunarCallPlusBinding watchFaceHolderLunarCallPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= Long.MIN_VALUE;
            }
            return true;
        }
        return false;
    }

    public final boolean h0(WatchFaceHolderXtendPlusBinding watchFaceHolderXtendPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.h == 0 && this.i == 0) {
                return this.wfStormPro.hasPendingBindings() || this.wfLunarCallPro.hasPendingBindings() || this.wfMercury.hasPendingBindings() || this.wfMatrix.hasPendingBindings() || this.wfWaveSelect.hasPendingBindings() || this.wfWavePrimeTalk.hasPendingBindings() || this.wfLunarCall.hasPendingBindings() || this.wfUltimaConnect.hasPendingBindings() || this.wfWavePlay.hasPendingBindings() || this.wfWaveSmartCall.hasPendingBindings() || this.wfLunarCallConnectAce.hasPendingBindings() || this.wfVertex.hasPendingBindings() || this.wfXtendSport.hasPendingBindings() || this.wfWaveElite.hasPendingBindings() || this.wfLunarConnect.hasPendingBindings() || this.wfWaveForce.hasPendingBindings() || this.wfWaveSmart.hasPendingBindings() || this.wfWaveConnect.hasPendingBindings() || this.wfWavePro.hasPendingBindings() || this.wfWavePrime.hasPendingBindings() || this.wfStormCallPro.hasPendingBindings() || this.wfLunarConnectPro.hasPendingBindings() || this.wfLunarCallPlus.hasPendingBindings() || this.wfWaveArmour.hasPendingBindings() || this.wfWaveBeatCall.hasPendingBindings() || this.wfXtendCallPlus.hasPendingBindings() || this.wfWaveFit.hasPendingBindings() || this.wfCosmos.hasPendingBindings() || this.wfWaveCallPlus.hasPendingBindings() || this.wfLunarConnectPlus.hasPendingBindings() || this.wfWaveStyle.hasPendingBindings() || this.wfWaveStyleCall.hasPendingBindings() || this.wfStormConnectPlus.hasPendingBindings() || this.wfPrimia.hasPendingBindings() || this.wfXtendPro.hasPendingBindings() || this.wfCosmosPro.hasPendingBindings() || this.wfWaveConnectPlus.hasPendingBindings() || this.wfUltimaCall.hasPendingBindings() || this.wfWaveBeat.hasPendingBindings() || this.wfLynkVoice.hasPendingBindings() || this.wfPrimaAce.hasPendingBindings() || this.wfCosmosPlus.hasPendingBindings() || this.wfFlexConnect.hasPendingBindings() || this.wfLeapCall.hasPendingBindings() || this.wfStormPlus.hasPendingBindings() || this.wfStrideVoice.hasPendingBindings() || this.wfXtendPlus.hasPendingBindings() || this.wfopp1Watches.hasPendingBindings() || this.wfopp2Watches.hasPendingBindings() || this.wfopp3Watches.hasPendingBindings() || this.wfopp4Watches.hasPendingBindings() || this.wfWaveArmour2.hasPendingBindings() || this.wfWaveForce2.hasPendingBindings() || this.wfWaveLunarFit.hasPendingBindings() || this.wfWaveGenesisPro.hasPendingBindings() || this.wfWaveElevatePro.hasPendingBindings() || this.wfWaveGloryPro.hasPendingBindings() || this.wfUltimaVogue.hasPendingBindings() || this.wfLunarSeek.hasPendingBindings() || this.wfLunarComet.hasPendingBindings() || this.wfLunarVelocity.hasPendingBindings() || this.wfWaveNeo.hasPendingBindings() || this.wfWaveMagma.hasPendingBindings() || this.wfLunarEmbrace.hasPendingBindings() || this.wfWaveSpectra.hasPendingBindings() || this.wfPS1EnigmaOasis.hasPendingBindings();
            }
            return true;
        }
    }

    public final boolean i(WatchFaceHolderLunarCallProBinding watchFaceHolderLunarCallProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 4398046511104L;
            }
            return true;
        }
        return false;
    }

    public final boolean i0(WatchFaceHolderXtendProBinding watchFaceHolderXtendProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 0L;
            this.i = 4L;
        }
        this.wfStormPro.invalidateAll();
        this.wfLunarCallPro.invalidateAll();
        this.wfMercury.invalidateAll();
        this.wfMatrix.invalidateAll();
        this.wfWaveSelect.invalidateAll();
        this.wfWavePrimeTalk.invalidateAll();
        this.wfLunarCall.invalidateAll();
        this.wfUltimaConnect.invalidateAll();
        this.wfWavePlay.invalidateAll();
        this.wfWaveSmartCall.invalidateAll();
        this.wfLunarCallConnectAce.invalidateAll();
        this.wfVertex.invalidateAll();
        this.wfXtendSport.invalidateAll();
        this.wfWaveElite.invalidateAll();
        this.wfLunarConnect.invalidateAll();
        this.wfWaveForce.invalidateAll();
        this.wfWaveSmart.invalidateAll();
        this.wfWaveConnect.invalidateAll();
        this.wfWavePro.invalidateAll();
        this.wfWavePrime.invalidateAll();
        this.wfStormCallPro.invalidateAll();
        this.wfLunarConnectPro.invalidateAll();
        this.wfLunarCallPlus.invalidateAll();
        this.wfWaveArmour.invalidateAll();
        this.wfWaveBeatCall.invalidateAll();
        this.wfXtendCallPlus.invalidateAll();
        this.wfWaveFit.invalidateAll();
        this.wfCosmos.invalidateAll();
        this.wfWaveCallPlus.invalidateAll();
        this.wfLunarConnectPlus.invalidateAll();
        this.wfWaveStyle.invalidateAll();
        this.wfWaveStyleCall.invalidateAll();
        this.wfStormConnectPlus.invalidateAll();
        this.wfPrimia.invalidateAll();
        this.wfXtendPro.invalidateAll();
        this.wfCosmosPro.invalidateAll();
        this.wfWaveConnectPlus.invalidateAll();
        this.wfUltimaCall.invalidateAll();
        this.wfWaveBeat.invalidateAll();
        this.wfLynkVoice.invalidateAll();
        this.wfPrimaAce.invalidateAll();
        this.wfCosmosPlus.invalidateAll();
        this.wfFlexConnect.invalidateAll();
        this.wfLeapCall.invalidateAll();
        this.wfStormPlus.invalidateAll();
        this.wfStrideVoice.invalidateAll();
        this.wfXtendPlus.invalidateAll();
        this.wfopp1Watches.invalidateAll();
        this.wfopp2Watches.invalidateAll();
        this.wfopp3Watches.invalidateAll();
        this.wfopp4Watches.invalidateAll();
        this.wfWaveArmour2.invalidateAll();
        this.wfWaveForce2.invalidateAll();
        this.wfWaveLunarFit.invalidateAll();
        this.wfWaveGenesisPro.invalidateAll();
        this.wfWaveElevatePro.invalidateAll();
        this.wfWaveGloryPro.invalidateAll();
        this.wfUltimaVogue.invalidateAll();
        this.wfLunarSeek.invalidateAll();
        this.wfLunarComet.invalidateAll();
        this.wfLunarVelocity.invalidateAll();
        this.wfWaveNeo.invalidateAll();
        this.wfWaveMagma.invalidateAll();
        this.wfLunarEmbrace.invalidateAll();
        this.wfWaveSpectra.invalidateAll();
        this.wfPS1EnigmaOasis.invalidateAll();
        requestRebind();
    }

    public final boolean j(WatchFaceHolderLunarCometBinding watchFaceHolderLunarCometBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 68719476736L;
            }
            return true;
        }
        return false;
    }

    public final boolean j0(WatchFaceHolderXtendSportsBinding watchFaceHolderXtendSportsBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 4503599627370496L;
            }
            return true;
        }
        return false;
    }

    public final boolean k(WatchFaceHolderLunarConnectBinding watchFaceHolderLunarConnectBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 576460752303423488L;
            }
            return true;
        }
        return false;
    }

    public final boolean k0(WatchFaceHolderOpp1WatchesBinding watchFaceHolderOpp1WatchesBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 137438953472L;
            }
            return true;
        }
        return false;
    }

    public final boolean l(WatchFaceHolderLunarConnectPlusBinding watchFaceHolderLunarConnectPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 8796093022208L;
            }
            return true;
        }
        return false;
    }

    public final boolean l0(WatchFaceHolderOpp2WatchesBinding watchFaceHolderOpp2WatchesBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 16;
            }
            return true;
        }
        return false;
    }

    public final boolean m(WatchFaceHolderLunarConnectProBinding watchFaceHolderLunarConnectProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    public final boolean m0(WatchFaceHolderOpp3WatchesBinding watchFaceHolderOpp3WatchesBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 4294967296L;
            }
            return true;
        }
        return false;
    }

    public final boolean n(WatchFaceHolderLunarEmbraceBinding watchFaceHolderLunarEmbraceBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 35184372088832L;
            }
            return true;
        }
        return false;
    }

    public final boolean n0(WatchFaceHolderOpp4WatchesBinding watchFaceHolderOpp4WatchesBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 4;
            }
            return true;
        }
        return false;
    }

    public final boolean o(WatchFaceHolderLunarSeekBinding watchFaceHolderLunarSeekBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 128;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return L((WatchFaceHolderWaveConnectPlusBinding) obj, i2);
            case 1:
                return W((WatchFaceHolderWavePlayBinding) obj, i2);
            case 2:
                return n0((WatchFaceHolderOpp4WatchesBinding) obj, i2);
            case 3:
                return b0((WatchFaceHolderWaveSmartBinding) obj, i2);
            case 4:
                return l0((WatchFaceHolderOpp2WatchesBinding) obj, i2);
            case 5:
                return w((WatchFaceHolderStormCallProBinding) obj, i2);
            case 6:
                return J((WatchFaceHolderWaveCallPlusBinding) obj, i2);
            case 7:
                return o((WatchFaceHolderLunarSeekBinding) obj, i2);
            case 8:
                return x((WatchFaceHolderStormConnectPlusBinding) obj, i2);
            case 9:
                return D((WatchFaceHolderUltimaVogueBinding) obj, i2);
            case 10:
                return O((WatchFaceHolderWavefitBinding) obj, i2);
            case 11:
                return A((WatchFaceHolderStrideVoiceBinding) obj, i2);
            case 12:
                return M((WatchFaceHolderWaveElevateProBinding) obj, i2);
            case 13:
                return m((WatchFaceHolderLunarConnectProBinding) obj, i2);
            case 14:
                return g((WatchFaceHolderLunarCallConnectAceBinding) obj, i2);
            case 15:
                return B((WatchFaceHolderUltimaCallBinding) obj, i2);
            case 16:
                return h0((WatchFaceHolderXtendPlusBinding) obj, i2);
            case 17:
                return c((WatchFaceHolderCosmosProBinding) obj, i2);
            case 18:
                return T((WatchFaceHolderLunarFitBinding) obj, i2);
            case 19:
                return d0((WatchFaceHolderWaveSpectraBinding) obj, i2);
            case 20:
                return F((WatchFaceHolderWaveArmourBinding) obj, i2);
            case 21:
                return b((WatchFaceHolderCosmosPlusBinding) obj, i2);
            case 22:
                return I((WatchFaceHolderWaveBeatCallBinding) obj, i2);
            case 23:
                return r((WatchFaceHolderMatrixBinding) obj, i2);
            case 24:
                return V((WatchFaceHolderWaveNeoBinding) obj, i2);
            case 25:
                return Z((WatchFaceHolderWaveProBinding) obj, i2);
            case 26:
                return d((WatchFaceHolderFlexConnectBinding) obj, i2);
            case 27:
                return C((WatchFaceHolderUltimaConnectBinding) obj, i2);
            case 28:
                return a((WatchFaceHolderCosmosBinding) obj, i2);
            case 29:
                return H((WatchFaceHolderWaveBeatBinding) obj, i2);
            case 30:
                return X((WatchFaceHolderWavePrimeBinding) obj, i2);
            case 31:
                return g0((WatchFaceHolderXtendCallPlusBinding) obj, i2);
            case 32:
                return m0((WatchFaceHolderOpp3WatchesBinding) obj, i2);
            case 33:
                return U((WatchFaceHolderWaveMagmaBinding) obj, i2);
            case 34:
                return u((WatchFaceHolderPrimiaAceBinding) obj, i2);
            case 35:
                return y((WatchFaceHolderStormPlusBinding) obj, i2);
            case 36:
                return j((WatchFaceHolderLunarCometBinding) obj, i2);
            case 37:
                return k0((WatchFaceHolderOpp1WatchesBinding) obj, i2);
            case 38:
                return s((WatchFaceHolderMercuryBinding) obj, i2);
            case 39:
                return Y((WatchFaceHolderWavePrimeTalkBinding) obj, i2);
            case 40:
                return R((WatchFaceHolderWaveGenesisProBinding) obj, i2);
            case 41:
                return v((WatchFaceHolderPrimiaBinding) obj, i2);
            case 42:
                return i((WatchFaceHolderLunarCallProBinding) obj, i2);
            case 43:
                return l((WatchFaceHolderLunarConnectPlusBinding) obj, i2);
            case 44:
                return t((WatchFaceHolderPs1EnigmaOasisBinding) obj, i2);
            case 45:
                return n((WatchFaceHolderLunarEmbraceBinding) obj, i2);
            case 46:
                return P((WatchFaceHolderWaveForceBinding) obj, i2);
            case 47:
                return p((WatchFaceHolderLunarVelocityBinding) obj, i2);
            case 48:
                return E((WatchFaceHolderVertexBinding) obj, i2);
            case 49:
                return N((WatchFaceHolderWaveEliteBinding) obj, i2);
            case 50:
                return z((WatchFaceHolderStormProBinding) obj, i2);
            case 51:
                return G((WatchFaceHolderWaveArmour2Binding) obj, i2);
            case 52:
                return j0((WatchFaceHolderXtendSportsBinding) obj, i2);
            case 53:
                return c0((WatchFaceHolderWaveSmartCallBinding) obj, i2);
            case 54:
                return S((WatchFaceHolderWaveGloryProBinding) obj, i2);
            case 55:
                return a0((WatchFaceHolderWaveSelectBinding) obj, i2);
            case 56:
                return e0((WatchFaceHolderWaveStyleBinding) obj, i2);
            case 57:
                return q((WatchFaceHolderWaveLynkVoiceBinding) obj, i2);
            case 58:
                return f0((WatchFaceHolderWaveStyleCallBinding) obj, i2);
            case 59:
                return k((WatchFaceHolderLunarConnectBinding) obj, i2);
            case 60:
                return e((WatchFaceHolderLeapCallBinding) obj, i2);
            case 61:
                return f((WatchFaceHolderLunarCallBinding) obj, i2);
            case 62:
                return Q((WatchFaceHolderWaveForce2Binding) obj, i2);
            case 63:
                return h((WatchFaceHolderLunarCallPlusBinding) obj, i2);
            case 64:
                return K((WatchFaceHolderWaveConnectBinding) obj, i2);
            case 65:
                return i0((WatchFaceHolderXtendProBinding) obj, i2);
            default:
                return false;
        }
    }

    public final boolean p(WatchFaceHolderLunarVelocityBinding watchFaceHolderLunarVelocityBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 140737488355328L;
            }
            return true;
        }
        return false;
    }

    public final boolean q(WatchFaceHolderWaveLynkVoiceBinding watchFaceHolderWaveLynkVoiceBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 144115188075855872L;
            }
            return true;
        }
        return false;
    }

    public final boolean r(WatchFaceHolderMatrixBinding watchFaceHolderMatrixBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 8388608;
            }
            return true;
        }
        return false;
    }

    public final boolean s(WatchFaceHolderMercuryBinding watchFaceHolderMercuryBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 274877906944L;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.wfStormPro.setLifecycleOwner(lifecycleOwner);
        this.wfLunarCallPro.setLifecycleOwner(lifecycleOwner);
        this.wfMercury.setLifecycleOwner(lifecycleOwner);
        this.wfMatrix.setLifecycleOwner(lifecycleOwner);
        this.wfWaveSelect.setLifecycleOwner(lifecycleOwner);
        this.wfWavePrimeTalk.setLifecycleOwner(lifecycleOwner);
        this.wfLunarCall.setLifecycleOwner(lifecycleOwner);
        this.wfUltimaConnect.setLifecycleOwner(lifecycleOwner);
        this.wfWavePlay.setLifecycleOwner(lifecycleOwner);
        this.wfWaveSmartCall.setLifecycleOwner(lifecycleOwner);
        this.wfLunarCallConnectAce.setLifecycleOwner(lifecycleOwner);
        this.wfVertex.setLifecycleOwner(lifecycleOwner);
        this.wfXtendSport.setLifecycleOwner(lifecycleOwner);
        this.wfWaveElite.setLifecycleOwner(lifecycleOwner);
        this.wfLunarConnect.setLifecycleOwner(lifecycleOwner);
        this.wfWaveForce.setLifecycleOwner(lifecycleOwner);
        this.wfWaveSmart.setLifecycleOwner(lifecycleOwner);
        this.wfWaveConnect.setLifecycleOwner(lifecycleOwner);
        this.wfWavePro.setLifecycleOwner(lifecycleOwner);
        this.wfWavePrime.setLifecycleOwner(lifecycleOwner);
        this.wfStormCallPro.setLifecycleOwner(lifecycleOwner);
        this.wfLunarConnectPro.setLifecycleOwner(lifecycleOwner);
        this.wfLunarCallPlus.setLifecycleOwner(lifecycleOwner);
        this.wfWaveArmour.setLifecycleOwner(lifecycleOwner);
        this.wfWaveBeatCall.setLifecycleOwner(lifecycleOwner);
        this.wfXtendCallPlus.setLifecycleOwner(lifecycleOwner);
        this.wfWaveFit.setLifecycleOwner(lifecycleOwner);
        this.wfCosmos.setLifecycleOwner(lifecycleOwner);
        this.wfWaveCallPlus.setLifecycleOwner(lifecycleOwner);
        this.wfLunarConnectPlus.setLifecycleOwner(lifecycleOwner);
        this.wfWaveStyle.setLifecycleOwner(lifecycleOwner);
        this.wfWaveStyleCall.setLifecycleOwner(lifecycleOwner);
        this.wfStormConnectPlus.setLifecycleOwner(lifecycleOwner);
        this.wfPrimia.setLifecycleOwner(lifecycleOwner);
        this.wfXtendPro.setLifecycleOwner(lifecycleOwner);
        this.wfCosmosPro.setLifecycleOwner(lifecycleOwner);
        this.wfWaveConnectPlus.setLifecycleOwner(lifecycleOwner);
        this.wfUltimaCall.setLifecycleOwner(lifecycleOwner);
        this.wfWaveBeat.setLifecycleOwner(lifecycleOwner);
        this.wfLynkVoice.setLifecycleOwner(lifecycleOwner);
        this.wfPrimaAce.setLifecycleOwner(lifecycleOwner);
        this.wfCosmosPlus.setLifecycleOwner(lifecycleOwner);
        this.wfFlexConnect.setLifecycleOwner(lifecycleOwner);
        this.wfLeapCall.setLifecycleOwner(lifecycleOwner);
        this.wfStormPlus.setLifecycleOwner(lifecycleOwner);
        this.wfStrideVoice.setLifecycleOwner(lifecycleOwner);
        this.wfXtendPlus.setLifecycleOwner(lifecycleOwner);
        this.wfopp1Watches.setLifecycleOwner(lifecycleOwner);
        this.wfopp2Watches.setLifecycleOwner(lifecycleOwner);
        this.wfopp3Watches.setLifecycleOwner(lifecycleOwner);
        this.wfopp4Watches.setLifecycleOwner(lifecycleOwner);
        this.wfWaveArmour2.setLifecycleOwner(lifecycleOwner);
        this.wfWaveForce2.setLifecycleOwner(lifecycleOwner);
        this.wfWaveLunarFit.setLifecycleOwner(lifecycleOwner);
        this.wfWaveGenesisPro.setLifecycleOwner(lifecycleOwner);
        this.wfWaveElevatePro.setLifecycleOwner(lifecycleOwner);
        this.wfWaveGloryPro.setLifecycleOwner(lifecycleOwner);
        this.wfUltimaVogue.setLifecycleOwner(lifecycleOwner);
        this.wfLunarSeek.setLifecycleOwner(lifecycleOwner);
        this.wfLunarComet.setLifecycleOwner(lifecycleOwner);
        this.wfLunarVelocity.setLifecycleOwner(lifecycleOwner);
        this.wfWaveNeo.setLifecycleOwner(lifecycleOwner);
        this.wfWaveMagma.setLifecycleOwner(lifecycleOwner);
        this.wfLunarEmbrace.setLifecycleOwner(lifecycleOwner);
        this.wfWaveSpectra.setLifecycleOwner(lifecycleOwner);
        this.wfPS1EnigmaOasis.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public final boolean t(WatchFaceHolderPs1EnigmaOasisBinding watchFaceHolderPs1EnigmaOasisBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 17592186044416L;
            }
            return true;
        }
        return false;
    }

    public final boolean u(WatchFaceHolderPrimiaAceBinding watchFaceHolderPrimiaAceBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 17179869184L;
            }
            return true;
        }
        return false;
    }

    public final boolean v(WatchFaceHolderPrimiaBinding watchFaceHolderPrimiaBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 2199023255552L;
            }
            return true;
        }
        return false;
    }

    public final boolean w(WatchFaceHolderStormCallProBinding watchFaceHolderStormCallProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 32;
            }
            return true;
        }
        return false;
    }

    public final boolean x(WatchFaceHolderStormConnectPlusBinding watchFaceHolderStormConnectPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 256;
            }
            return true;
        }
        return false;
    }

    public final boolean y(WatchFaceHolderStormPlusBinding watchFaceHolderStormPlusBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 34359738368L;
            }
            return true;
        }
        return false;
    }

    public final boolean z(WatchFaceHolderStormProBinding watchFaceHolderStormProBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.h |= 1125899906842624L;
            }
            return true;
        }
        return false;
    }

    public FragmentMyWatchStatusBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 66, (ProgressBar) objArr[83], (ImageView) objArr[82], (TextView) objArr[84], (ImageView) objArr[91], (TextView) objArr[92], (ImageView) objArr[79], (TextView) objArr[80], (ConstraintLayout) objArr[90], (ConstraintLayout) objArr[81], (ConstraintLayout) objArr[78], (ConstraintLayout) objArr[68], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[86], (ConstraintLayout) objArr[0], (ImageView) objArr[69], (LinearLayout) objArr[77], (ImageView) objArr[87], (TextView) objArr[88], (TextView) objArr[72], (TextView) objArr[76], (TextView) objArr[74], (TextView) objArr[70], (View) objArr[93], (View) objArr[85], (View) objArr[89], (View) objArr[71], (View) objArr[75], (View) objArr[73], (WatchFaceHolderCosmosBinding) objArr[29], (WatchFaceHolderCosmosPlusBinding) objArr[43], (WatchFaceHolderCosmosProBinding) objArr[37], (WatchFaceHolderFlexConnectBinding) objArr[44], (WatchFaceHolderLeapCallBinding) objArr[45], (WatchFaceHolderLunarCallBinding) objArr[8], (WatchFaceHolderLunarCallConnectAceBinding) objArr[12], (WatchFaceHolderLunarCallPlusBinding) objArr[24], (WatchFaceHolderLunarCallProBinding) objArr[3], (WatchFaceHolderLunarCometBinding) objArr[61], (WatchFaceHolderLunarConnectBinding) objArr[16], (WatchFaceHolderLunarConnectPlusBinding) objArr[31], (WatchFaceHolderLunarConnectProBinding) objArr[23], (WatchFaceHolderLunarEmbraceBinding) objArr[65], (WatchFaceHolderLunarSeekBinding) objArr[60], (WatchFaceHolderLunarVelocityBinding) objArr[62], (WatchFaceHolderWaveLynkVoiceBinding) objArr[41], (WatchFaceHolderMatrixBinding) objArr[5], (WatchFaceHolderMercuryBinding) objArr[4], (WatchFaceHolderPs1EnigmaOasisBinding) objArr[67], (WatchFaceHolderPrimiaAceBinding) objArr[42], (WatchFaceHolderPrimiaBinding) objArr[35], (WatchFaceHolderStormCallProBinding) objArr[22], (WatchFaceHolderStormConnectPlusBinding) objArr[34], (WatchFaceHolderStormPlusBinding) objArr[46], (WatchFaceHolderStormProBinding) objArr[2], (WatchFaceHolderStrideVoiceBinding) objArr[47], (WatchFaceHolderUltimaCallBinding) objArr[39], (WatchFaceHolderUltimaConnectBinding) objArr[9], (WatchFaceHolderUltimaVogueBinding) objArr[59], (WatchFaceHolderVertexBinding) objArr[13], (WatchFaceHolderWaveArmourBinding) objArr[25], (WatchFaceHolderWaveArmour2Binding) objArr[53], (WatchFaceHolderWaveBeatBinding) objArr[40], (WatchFaceHolderWaveBeatCallBinding) objArr[26], (WatchFaceHolderWaveCallPlusBinding) objArr[30], (WatchFaceHolderWaveConnectBinding) objArr[19], (WatchFaceHolderWaveConnectPlusBinding) objArr[38], (WatchFaceHolderWaveElevateProBinding) objArr[57], (WatchFaceHolderWaveEliteBinding) objArr[15], (WatchFaceHolderWavefitBinding) objArr[28], (WatchFaceHolderWaveForceBinding) objArr[17], (WatchFaceHolderWaveForce2Binding) objArr[54], (WatchFaceHolderWaveGenesisProBinding) objArr[56], (WatchFaceHolderWaveGloryProBinding) objArr[58], (WatchFaceHolderLunarFitBinding) objArr[55], (WatchFaceHolderWaveMagmaBinding) objArr[64], (WatchFaceHolderWaveNeoBinding) objArr[63], (WatchFaceHolderWavePlayBinding) objArr[10], (WatchFaceHolderWavePrimeBinding) objArr[21], (WatchFaceHolderWavePrimeTalkBinding) objArr[7], (WatchFaceHolderWaveProBinding) objArr[20], (WatchFaceHolderWaveSelectBinding) objArr[6], (WatchFaceHolderWaveSmartBinding) objArr[18], (WatchFaceHolderWaveSmartCallBinding) objArr[11], (WatchFaceHolderWaveSpectraBinding) objArr[66], (WatchFaceHolderWaveStyleBinding) objArr[32], (WatchFaceHolderWaveStyleCallBinding) objArr[33], (WatchFaceHolderXtendCallPlusBinding) objArr[27], (WatchFaceHolderXtendPlusBinding) objArr[48], (WatchFaceHolderXtendProBinding) objArr[36], (WatchFaceHolderXtendSportsBinding) objArr[14], (WatchFaceHolderOpp1WatchesBinding) objArr[49], (WatchFaceHolderOpp2WatchesBinding) objArr[50], (WatchFaceHolderOpp3WatchesBinding) objArr[51], (WatchFaceHolderOpp4WatchesBinding) objArr[52]);
        this.h = -1L;
        this.i = -1L;
        this.clMainWatch.setTag(null);
        this.clTopWatchStatus.setTag(null);
        setContainedBinding(this.wfCosmos);
        setContainedBinding(this.wfCosmosPlus);
        setContainedBinding(this.wfCosmosPro);
        setContainedBinding(this.wfFlexConnect);
        setContainedBinding(this.wfLeapCall);
        setContainedBinding(this.wfLunarCall);
        setContainedBinding(this.wfLunarCallConnectAce);
        setContainedBinding(this.wfLunarCallPlus);
        setContainedBinding(this.wfLunarCallPro);
        setContainedBinding(this.wfLunarComet);
        setContainedBinding(this.wfLunarConnect);
        setContainedBinding(this.wfLunarConnectPlus);
        setContainedBinding(this.wfLunarConnectPro);
        setContainedBinding(this.wfLunarEmbrace);
        setContainedBinding(this.wfLunarSeek);
        setContainedBinding(this.wfLunarVelocity);
        setContainedBinding(this.wfLynkVoice);
        setContainedBinding(this.wfMatrix);
        setContainedBinding(this.wfMercury);
        setContainedBinding(this.wfPS1EnigmaOasis);
        setContainedBinding(this.wfPrimaAce);
        setContainedBinding(this.wfPrimia);
        setContainedBinding(this.wfStormCallPro);
        setContainedBinding(this.wfStormConnectPlus);
        setContainedBinding(this.wfStormPlus);
        setContainedBinding(this.wfStormPro);
        setContainedBinding(this.wfStrideVoice);
        setContainedBinding(this.wfUltimaCall);
        setContainedBinding(this.wfUltimaConnect);
        setContainedBinding(this.wfUltimaVogue);
        setContainedBinding(this.wfVertex);
        setContainedBinding(this.wfWaveArmour);
        setContainedBinding(this.wfWaveArmour2);
        setContainedBinding(this.wfWaveBeat);
        setContainedBinding(this.wfWaveBeatCall);
        setContainedBinding(this.wfWaveCallPlus);
        setContainedBinding(this.wfWaveConnect);
        setContainedBinding(this.wfWaveConnectPlus);
        setContainedBinding(this.wfWaveElevatePro);
        setContainedBinding(this.wfWaveElite);
        setContainedBinding(this.wfWaveFit);
        setContainedBinding(this.wfWaveForce);
        setContainedBinding(this.wfWaveForce2);
        setContainedBinding(this.wfWaveGenesisPro);
        setContainedBinding(this.wfWaveGloryPro);
        setContainedBinding(this.wfWaveLunarFit);
        setContainedBinding(this.wfWaveMagma);
        setContainedBinding(this.wfWaveNeo);
        setContainedBinding(this.wfWavePlay);
        setContainedBinding(this.wfWavePrime);
        setContainedBinding(this.wfWavePrimeTalk);
        setContainedBinding(this.wfWavePro);
        setContainedBinding(this.wfWaveSelect);
        setContainedBinding(this.wfWaveSmart);
        setContainedBinding(this.wfWaveSmartCall);
        setContainedBinding(this.wfWaveSpectra);
        setContainedBinding(this.wfWaveStyle);
        setContainedBinding(this.wfWaveStyleCall);
        setContainedBinding(this.wfXtendCallPlus);
        setContainedBinding(this.wfXtendPlus);
        setContainedBinding(this.wfXtendPro);
        setContainedBinding(this.wfXtendSport);
        setContainedBinding(this.wfopp1Watches);
        setContainedBinding(this.wfopp2Watches);
        setContainedBinding(this.wfopp3Watches);
        setContainedBinding(this.wfopp4Watches);
        setRootTag(view);
        invalidateAll();
    }
}
