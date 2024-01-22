package com.coveiot.android.watchfaceui;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.watchfaceui.databinding.ActivityBackgroundWatchFaceBindingImpl;
import com.coveiot.android.watchfaceui.databinding.ActivityWatchFaceCanewBindingImpl;
import com.coveiot.android.watchfaceui.databinding.FragmentMyDesignsBindingImpl;
import com.coveiot.android.watchfaceui.databinding.FragmentWatchFaceCloudNewBindingImpl;
import com.coveiot.android.watchfaceui.databinding.FragmentWatchFaceDefaultNew2BindingImpl;
import com.coveiot.android.watchfaceui.databinding.ListWfCategoryItemBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderFlexConnectBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLeapCallBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallConnectAceBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCometBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarEmbraceBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarFitBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarSeekBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarVelocityBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderMatrixBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderMercuryBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp1WatchesBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp2WatchesBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp3WatchesBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp4WatchesBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPrimiaAceBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPrimiaBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPs1EnigmaOasisBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormCallProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormConnectPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStrideVoiceBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaCallBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaConnectBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaVogueBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderVertexBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveArmour2BindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveArmourBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveBeatBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveBeatCallBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveCallPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveConnectBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveConnectPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveElevateProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveEliteBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveForce2BindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveForceBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveGenesisProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveGloryProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveLynkVoiceBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveMagmaBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveNeoBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePlayBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePrimeBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePrimeTalkBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSelectBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSmartBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSmartCallBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSpectraBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveStyleBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveStyleCallBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavefitBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendCallPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendPlusBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendProBindingImpl;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendSportsBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes8.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYBACKGROUNDWATCHFACE = 1;
    private static final int LAYOUT_ACTIVITYWATCHFACECANEW = 2;
    private static final int LAYOUT_FRAGMENTMYDESIGNS = 3;
    private static final int LAYOUT_FRAGMENTWATCHFACECLOUDNEW = 4;
    private static final int LAYOUT_FRAGMENTWATCHFACEDEFAULTNEW2 = 5;
    private static final int LAYOUT_LISTWFCATEGORYITEM = 6;
    private static final int LAYOUT_WATCHFACEHOLDERCOSMOS = 7;
    private static final int LAYOUT_WATCHFACEHOLDERCOSMOSPLUS = 8;
    private static final int LAYOUT_WATCHFACEHOLDERCOSMOSPRO = 9;
    private static final int LAYOUT_WATCHFACEHOLDERFLEXCONNECT = 10;
    private static final int LAYOUT_WATCHFACEHOLDERLEAPCALL = 11;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCALL = 12;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCALLCONNECTACE = 13;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCALLPLUS = 14;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCALLPRO = 15;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCOMET = 16;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCONNECT = 17;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCONNECTPLUS = 18;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARCONNECTPRO = 19;
    private static final int LAYOUT_WATCHFACEHOLDERLUNAREMBRACE = 20;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARFIT = 21;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARSEEK = 22;
    private static final int LAYOUT_WATCHFACEHOLDERLUNARVELOCITY = 23;
    private static final int LAYOUT_WATCHFACEHOLDERMATRIX = 24;
    private static final int LAYOUT_WATCHFACEHOLDERMERCURY = 25;
    private static final int LAYOUT_WATCHFACEHOLDEROPP1WATCHES = 26;
    private static final int LAYOUT_WATCHFACEHOLDEROPP2WATCHES = 27;
    private static final int LAYOUT_WATCHFACEHOLDEROPP3WATCHES = 28;
    private static final int LAYOUT_WATCHFACEHOLDEROPP4WATCHES = 29;
    private static final int LAYOUT_WATCHFACEHOLDERPRIMIA = 30;
    private static final int LAYOUT_WATCHFACEHOLDERPRIMIAACE = 31;
    private static final int LAYOUT_WATCHFACEHOLDERPS1ENIGMAOASIS = 32;
    private static final int LAYOUT_WATCHFACEHOLDERSTORMCALLPRO = 33;
    private static final int LAYOUT_WATCHFACEHOLDERSTORMCONNECTPLUS = 34;
    private static final int LAYOUT_WATCHFACEHOLDERSTORMPLUS = 35;
    private static final int LAYOUT_WATCHFACEHOLDERSTORMPRO = 36;
    private static final int LAYOUT_WATCHFACEHOLDERSTRIDEVOICE = 37;
    private static final int LAYOUT_WATCHFACEHOLDERULTIMACALL = 38;
    private static final int LAYOUT_WATCHFACEHOLDERULTIMACONNECT = 39;
    private static final int LAYOUT_WATCHFACEHOLDERULTIMAVOGUE = 40;
    private static final int LAYOUT_WATCHFACEHOLDERVERTEX = 41;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEARMOUR = 42;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEARMOUR2 = 43;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEBEAT = 44;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEBEATCALL = 45;
    private static final int LAYOUT_WATCHFACEHOLDERWAVECALLPLUS = 46;
    private static final int LAYOUT_WATCHFACEHOLDERWAVECONNECT = 47;
    private static final int LAYOUT_WATCHFACEHOLDERWAVECONNECTPLUS = 48;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEELEVATEPRO = 49;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEELITE = 50;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEFIT = 68;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEFORCE = 51;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEFORCE2 = 52;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEGENESISPRO = 53;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEGLORYPRO = 54;
    private static final int LAYOUT_WATCHFACEHOLDERWAVELYNKVOICE = 55;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEMAGMA = 56;
    private static final int LAYOUT_WATCHFACEHOLDERWAVENEO = 57;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEPLAY = 58;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEPRIME = 59;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEPRIMETALK = 60;
    private static final int LAYOUT_WATCHFACEHOLDERWAVEPRO = 61;
    private static final int LAYOUT_WATCHFACEHOLDERWAVESELECT = 62;
    private static final int LAYOUT_WATCHFACEHOLDERWAVESMART = 63;
    private static final int LAYOUT_WATCHFACEHOLDERWAVESMARTCALL = 64;
    private static final int LAYOUT_WATCHFACEHOLDERWAVESPECTRA = 65;
    private static final int LAYOUT_WATCHFACEHOLDERWAVESTYLE = 66;
    private static final int LAYOUT_WATCHFACEHOLDERWAVESTYLECALL = 67;
    private static final int LAYOUT_WATCHFACEHOLDERXTENDCALLPLUS = 69;
    private static final int LAYOUT_WATCHFACEHOLDERXTENDPLUS = 70;
    private static final int LAYOUT_WATCHFACEHOLDERXTENDPRO = 71;
    private static final int LAYOUT_WATCHFACEHOLDERXTENDSPORTS = 72;

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f6115a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(20);
            f6115a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "cloudCategoryData");
            sparseArray.put(3, "firstCardType");
            sparseArray.put(4, "fitnessType");
            sparseArray.put(5, "healthInfo");
            sparseArray.put(6, "isDataAvailable");
            sparseArray.put(7, "isFirstCardDataAvailable");
            sparseArray.put(8, "progress");
            sparseArray.put(9, "showAlexaConnect");
            sparseArray.put(10, "showFitnessPlan");
            sparseArray.put(11, "showSOSSettings");
            sparseArray.put(12, "showSportScores");
            sparseArray.put(13, "showTapAndPay");
            sparseArray.put(14, "showWellnessCrew");
            sparseArray.put(15, "stepsDataModel");
            sparseArray.put(16, "stepsGoal");
            sparseArray.put(17, "tipsData");
            sparseArray.put(18, "title");
            sparseArray.put(19, "watchName");
        }
    }

    /* loaded from: classes8.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f6116a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(72);
            f6116a = hashMap;
            hashMap.put("layout/activity_background_watch_face_0", Integer.valueOf(R.layout.activity_background_watch_face));
            hashMap.put("layout/activity_watch_face_canew_0", Integer.valueOf(R.layout.activity_watch_face_canew));
            hashMap.put("layout/fragment_my_designs_0", Integer.valueOf(R.layout.fragment_my_designs));
            hashMap.put("layout/fragment_watch_face_cloud_new_0", Integer.valueOf(R.layout.fragment_watch_face_cloud_new));
            hashMap.put("layout/fragment_watch_face_default_new2_0", Integer.valueOf(R.layout.fragment_watch_face_default_new2));
            hashMap.put("layout/list_wf_category_item_0", Integer.valueOf(R.layout.list_wf_category_item));
            hashMap.put("layout/watch_face_holder_cosmos_0", Integer.valueOf(R.layout.watch_face_holder_cosmos));
            hashMap.put("layout/watch_face_holder_cosmos_plus_0", Integer.valueOf(R.layout.watch_face_holder_cosmos_plus));
            hashMap.put("layout/watch_face_holder_cosmos_pro_0", Integer.valueOf(R.layout.watch_face_holder_cosmos_pro));
            hashMap.put("layout/watch_face_holder_flex_connect_0", Integer.valueOf(R.layout.watch_face_holder_flex_connect));
            hashMap.put("layout/watch_face_holder_leap_call_0", Integer.valueOf(R.layout.watch_face_holder_leap_call));
            hashMap.put("layout/watch_face_holder_lunar_call_0", Integer.valueOf(R.layout.watch_face_holder_lunar_call));
            hashMap.put("layout/watch_face_holder_lunar_call_connect_ace_0", Integer.valueOf(R.layout.watch_face_holder_lunar_call_connect_ace));
            hashMap.put("layout/watch_face_holder_lunar_call_plus_0", Integer.valueOf(R.layout.watch_face_holder_lunar_call_plus));
            hashMap.put("layout/watch_face_holder_lunar_call_pro_0", Integer.valueOf(R.layout.watch_face_holder_lunar_call_pro));
            hashMap.put("layout/watch_face_holder_lunar_comet_0", Integer.valueOf(R.layout.watch_face_holder_lunar_comet));
            hashMap.put("layout/watch_face_holder_lunar_connect_0", Integer.valueOf(R.layout.watch_face_holder_lunar_connect));
            hashMap.put("layout/watch_face_holder_lunar_connect_plus_0", Integer.valueOf(R.layout.watch_face_holder_lunar_connect_plus));
            hashMap.put("layout/watch_face_holder_lunar_connect_pro_0", Integer.valueOf(R.layout.watch_face_holder_lunar_connect_pro));
            hashMap.put("layout/watch_face_holder_lunar_embrace_0", Integer.valueOf(R.layout.watch_face_holder_lunar_embrace));
            hashMap.put("layout/watch_face_holder_lunar_fit_0", Integer.valueOf(R.layout.watch_face_holder_lunar_fit));
            hashMap.put("layout/watch_face_holder_lunar_seek_0", Integer.valueOf(R.layout.watch_face_holder_lunar_seek));
            hashMap.put("layout/watch_face_holder_lunar_velocity_0", Integer.valueOf(R.layout.watch_face_holder_lunar_velocity));
            hashMap.put("layout/watch_face_holder_matrix_0", Integer.valueOf(R.layout.watch_face_holder_matrix));
            hashMap.put("layout/watch_face_holder_mercury_0", Integer.valueOf(R.layout.watch_face_holder_mercury));
            hashMap.put("layout/watch_face_holder_opp1_watches_0", Integer.valueOf(R.layout.watch_face_holder_opp1_watches));
            hashMap.put("layout/watch_face_holder_opp2_watches_0", Integer.valueOf(R.layout.watch_face_holder_opp2_watches));
            hashMap.put("layout/watch_face_holder_opp3_watches_0", Integer.valueOf(R.layout.watch_face_holder_opp3_watches));
            hashMap.put("layout/watch_face_holder_opp4_watches_0", Integer.valueOf(R.layout.watch_face_holder_opp4_watches));
            hashMap.put("layout/watch_face_holder_primia_0", Integer.valueOf(R.layout.watch_face_holder_primia));
            hashMap.put("layout/watch_face_holder_primia_ace_0", Integer.valueOf(R.layout.watch_face_holder_primia_ace));
            hashMap.put("layout/watch_face_holder_ps1_enigma_oasis_0", Integer.valueOf(R.layout.watch_face_holder_ps1_enigma_oasis));
            hashMap.put("layout/watch_face_holder_storm_call_pro_0", Integer.valueOf(R.layout.watch_face_holder_storm_call_pro));
            hashMap.put("layout/watch_face_holder_storm_connect_plus_0", Integer.valueOf(R.layout.watch_face_holder_storm_connect_plus));
            hashMap.put("layout/watch_face_holder_storm_plus_0", Integer.valueOf(R.layout.watch_face_holder_storm_plus));
            hashMap.put("layout/watch_face_holder_storm_pro_0", Integer.valueOf(R.layout.watch_face_holder_storm_pro));
            hashMap.put("layout/watch_face_holder_stride_voice_0", Integer.valueOf(R.layout.watch_face_holder_stride_voice));
            hashMap.put("layout/watch_face_holder_ultima_call_0", Integer.valueOf(R.layout.watch_face_holder_ultima_call));
            hashMap.put("layout/watch_face_holder_ultima_connect_0", Integer.valueOf(R.layout.watch_face_holder_ultima_connect));
            hashMap.put("layout/watch_face_holder_ultima_vogue_0", Integer.valueOf(R.layout.watch_face_holder_ultima_vogue));
            hashMap.put("layout/watch_face_holder_vertex_0", Integer.valueOf(R.layout.watch_face_holder_vertex));
            hashMap.put("layout/watch_face_holder_wave_armour_0", Integer.valueOf(R.layout.watch_face_holder_wave_armour));
            hashMap.put("layout/watch_face_holder_wave_armour2_0", Integer.valueOf(R.layout.watch_face_holder_wave_armour2));
            hashMap.put("layout/watch_face_holder_wave_beat_0", Integer.valueOf(R.layout.watch_face_holder_wave_beat));
            hashMap.put("layout/watch_face_holder_wave_beat_call_0", Integer.valueOf(R.layout.watch_face_holder_wave_beat_call));
            hashMap.put("layout/watch_face_holder_wave_call_plus_0", Integer.valueOf(R.layout.watch_face_holder_wave_call_plus));
            hashMap.put("layout/watch_face_holder_wave_connect_0", Integer.valueOf(R.layout.watch_face_holder_wave_connect));
            hashMap.put("layout/watch_face_holder_wave_connect_plus_0", Integer.valueOf(R.layout.watch_face_holder_wave_connect_plus));
            hashMap.put("layout/watch_face_holder_wave_elevate_pro_0", Integer.valueOf(R.layout.watch_face_holder_wave_elevate_pro));
            hashMap.put("layout/watch_face_holder_wave_elite_0", Integer.valueOf(R.layout.watch_face_holder_wave_elite));
            hashMap.put("layout/watch_face_holder_wave_force_0", Integer.valueOf(R.layout.watch_face_holder_wave_force));
            hashMap.put("layout/watch_face_holder_wave_force2_0", Integer.valueOf(R.layout.watch_face_holder_wave_force2));
            hashMap.put("layout/watch_face_holder_wave_genesis_pro_0", Integer.valueOf(R.layout.watch_face_holder_wave_genesis_pro));
            hashMap.put("layout/watch_face_holder_wave_glory_pro_0", Integer.valueOf(R.layout.watch_face_holder_wave_glory_pro));
            hashMap.put("layout/watch_face_holder_wave_lynk_voice_0", Integer.valueOf(R.layout.watch_face_holder_wave_lynk_voice));
            hashMap.put("layout/watch_face_holder_wave_magma_0", Integer.valueOf(R.layout.watch_face_holder_wave_magma));
            hashMap.put("layout/watch_face_holder_wave_neo_0", Integer.valueOf(R.layout.watch_face_holder_wave_neo));
            hashMap.put("layout/watch_face_holder_wave_play_0", Integer.valueOf(R.layout.watch_face_holder_wave_play));
            hashMap.put("layout/watch_face_holder_wave_prime_0", Integer.valueOf(R.layout.watch_face_holder_wave_prime));
            hashMap.put("layout/watch_face_holder_wave_prime_talk_0", Integer.valueOf(R.layout.watch_face_holder_wave_prime_talk));
            hashMap.put("layout/watch_face_holder_wave_pro_0", Integer.valueOf(R.layout.watch_face_holder_wave_pro));
            hashMap.put("layout/watch_face_holder_wave_select_0", Integer.valueOf(R.layout.watch_face_holder_wave_select));
            hashMap.put("layout/watch_face_holder_wave_smart_0", Integer.valueOf(R.layout.watch_face_holder_wave_smart));
            hashMap.put("layout/watch_face_holder_wave_smart_call_0", Integer.valueOf(R.layout.watch_face_holder_wave_smart_call));
            hashMap.put("layout/watch_face_holder_wave_spectra_0", Integer.valueOf(R.layout.watch_face_holder_wave_spectra));
            hashMap.put("layout/watch_face_holder_wave_style_0", Integer.valueOf(R.layout.watch_face_holder_wave_style));
            hashMap.put("layout/watch_face_holder_wave_style_call_0", Integer.valueOf(R.layout.watch_face_holder_wave_style_call));
            hashMap.put("layout/watch_face_holder_wavefit_0", Integer.valueOf(R.layout.watch_face_holder_wavefit));
            hashMap.put("layout/watch_face_holder_xtend_call_plus_0", Integer.valueOf(R.layout.watch_face_holder_xtend_call_plus));
            hashMap.put("layout/watch_face_holder_xtend_plus_0", Integer.valueOf(R.layout.watch_face_holder_xtend_plus));
            hashMap.put("layout/watch_face_holder_xtend_pro_0", Integer.valueOf(R.layout.watch_face_holder_xtend_pro));
            hashMap.put("layout/watch_face_holder_xtend_sports_0", Integer.valueOf(R.layout.watch_face_holder_xtend_sports));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(72);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_background_watch_face, 1);
        sparseIntArray.put(R.layout.activity_watch_face_canew, 2);
        sparseIntArray.put(R.layout.fragment_my_designs, 3);
        sparseIntArray.put(R.layout.fragment_watch_face_cloud_new, 4);
        sparseIntArray.put(R.layout.fragment_watch_face_default_new2, 5);
        sparseIntArray.put(R.layout.list_wf_category_item, 6);
        sparseIntArray.put(R.layout.watch_face_holder_cosmos, 7);
        sparseIntArray.put(R.layout.watch_face_holder_cosmos_plus, 8);
        sparseIntArray.put(R.layout.watch_face_holder_cosmos_pro, 9);
        sparseIntArray.put(R.layout.watch_face_holder_flex_connect, 10);
        sparseIntArray.put(R.layout.watch_face_holder_leap_call, 11);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_call, 12);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_call_connect_ace, 13);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_call_plus, 14);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_call_pro, 15);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_comet, 16);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_connect, 17);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_connect_plus, 18);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_connect_pro, 19);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_embrace, 20);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_fit, 21);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_seek, 22);
        sparseIntArray.put(R.layout.watch_face_holder_lunar_velocity, 23);
        sparseIntArray.put(R.layout.watch_face_holder_matrix, 24);
        sparseIntArray.put(R.layout.watch_face_holder_mercury, 25);
        sparseIntArray.put(R.layout.watch_face_holder_opp1_watches, 26);
        sparseIntArray.put(R.layout.watch_face_holder_opp2_watches, 27);
        sparseIntArray.put(R.layout.watch_face_holder_opp3_watches, 28);
        sparseIntArray.put(R.layout.watch_face_holder_opp4_watches, 29);
        sparseIntArray.put(R.layout.watch_face_holder_primia, 30);
        sparseIntArray.put(R.layout.watch_face_holder_primia_ace, 31);
        sparseIntArray.put(R.layout.watch_face_holder_ps1_enigma_oasis, 32);
        sparseIntArray.put(R.layout.watch_face_holder_storm_call_pro, 33);
        sparseIntArray.put(R.layout.watch_face_holder_storm_connect_plus, 34);
        sparseIntArray.put(R.layout.watch_face_holder_storm_plus, 35);
        sparseIntArray.put(R.layout.watch_face_holder_storm_pro, 36);
        sparseIntArray.put(R.layout.watch_face_holder_stride_voice, 37);
        sparseIntArray.put(R.layout.watch_face_holder_ultima_call, 38);
        sparseIntArray.put(R.layout.watch_face_holder_ultima_connect, 39);
        sparseIntArray.put(R.layout.watch_face_holder_ultima_vogue, 40);
        sparseIntArray.put(R.layout.watch_face_holder_vertex, 41);
        sparseIntArray.put(R.layout.watch_face_holder_wave_armour, 42);
        sparseIntArray.put(R.layout.watch_face_holder_wave_armour2, 43);
        sparseIntArray.put(R.layout.watch_face_holder_wave_beat, 44);
        sparseIntArray.put(R.layout.watch_face_holder_wave_beat_call, 45);
        sparseIntArray.put(R.layout.watch_face_holder_wave_call_plus, 46);
        sparseIntArray.put(R.layout.watch_face_holder_wave_connect, 47);
        sparseIntArray.put(R.layout.watch_face_holder_wave_connect_plus, 48);
        sparseIntArray.put(R.layout.watch_face_holder_wave_elevate_pro, 49);
        sparseIntArray.put(R.layout.watch_face_holder_wave_elite, 50);
        sparseIntArray.put(R.layout.watch_face_holder_wave_force, 51);
        sparseIntArray.put(R.layout.watch_face_holder_wave_force2, 52);
        sparseIntArray.put(R.layout.watch_face_holder_wave_genesis_pro, 53);
        sparseIntArray.put(R.layout.watch_face_holder_wave_glory_pro, 54);
        sparseIntArray.put(R.layout.watch_face_holder_wave_lynk_voice, 55);
        sparseIntArray.put(R.layout.watch_face_holder_wave_magma, 56);
        sparseIntArray.put(R.layout.watch_face_holder_wave_neo, 57);
        sparseIntArray.put(R.layout.watch_face_holder_wave_play, 58);
        sparseIntArray.put(R.layout.watch_face_holder_wave_prime, 59);
        sparseIntArray.put(R.layout.watch_face_holder_wave_prime_talk, 60);
        sparseIntArray.put(R.layout.watch_face_holder_wave_pro, 61);
        sparseIntArray.put(R.layout.watch_face_holder_wave_select, 62);
        sparseIntArray.put(R.layout.watch_face_holder_wave_smart, 63);
        sparseIntArray.put(R.layout.watch_face_holder_wave_smart_call, 64);
        sparseIntArray.put(R.layout.watch_face_holder_wave_spectra, 65);
        sparseIntArray.put(R.layout.watch_face_holder_wave_style, 66);
        sparseIntArray.put(R.layout.watch_face_holder_wave_style_call, 67);
        sparseIntArray.put(R.layout.watch_face_holder_wavefit, 68);
        sparseIntArray.put(R.layout.watch_face_holder_xtend_call_plus, 69);
        sparseIntArray.put(R.layout.watch_face_holder_xtend_plus, 70);
        sparseIntArray.put(R.layout.watch_face_holder_xtend_pro, 71);
        sparseIntArray.put(R.layout.watch_face_holder_xtend_sports, 72);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 1:
                if ("layout/activity_background_watch_face_0".equals(obj)) {
                    return new ActivityBackgroundWatchFaceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_background_watch_face is invalid. Received: " + obj);
            case 2:
                if ("layout/activity_watch_face_canew_0".equals(obj)) {
                    return new ActivityWatchFaceCanewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_watch_face_canew is invalid. Received: " + obj);
            case 3:
                if ("layout/fragment_my_designs_0".equals(obj)) {
                    return new FragmentMyDesignsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_my_designs is invalid. Received: " + obj);
            case 4:
                if ("layout/fragment_watch_face_cloud_new_0".equals(obj)) {
                    return new FragmentWatchFaceCloudNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_watch_face_cloud_new is invalid. Received: " + obj);
            case 5:
                if ("layout/fragment_watch_face_default_new2_0".equals(obj)) {
                    return new FragmentWatchFaceDefaultNew2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_watch_face_default_new2 is invalid. Received: " + obj);
            case 6:
                if ("layout/list_wf_category_item_0".equals(obj)) {
                    return new ListWfCategoryItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_wf_category_item is invalid. Received: " + obj);
            case 7:
                if ("layout/watch_face_holder_cosmos_0".equals(obj)) {
                    return new WatchFaceHolderCosmosBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_cosmos is invalid. Received: " + obj);
            case 8:
                if ("layout/watch_face_holder_cosmos_plus_0".equals(obj)) {
                    return new WatchFaceHolderCosmosPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_cosmos_plus is invalid. Received: " + obj);
            case 9:
                if ("layout/watch_face_holder_cosmos_pro_0".equals(obj)) {
                    return new WatchFaceHolderCosmosProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_cosmos_pro is invalid. Received: " + obj);
            case 10:
                if ("layout/watch_face_holder_flex_connect_0".equals(obj)) {
                    return new WatchFaceHolderFlexConnectBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_flex_connect is invalid. Received: " + obj);
            case 11:
                if ("layout/watch_face_holder_leap_call_0".equals(obj)) {
                    return new WatchFaceHolderLeapCallBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_leap_call is invalid. Received: " + obj);
            case 12:
                if ("layout/watch_face_holder_lunar_call_0".equals(obj)) {
                    return new WatchFaceHolderLunarCallBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_call is invalid. Received: " + obj);
            case 13:
                if ("layout/watch_face_holder_lunar_call_connect_ace_0".equals(obj)) {
                    return new WatchFaceHolderLunarCallConnectAceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_call_connect_ace is invalid. Received: " + obj);
            case 14:
                if ("layout/watch_face_holder_lunar_call_plus_0".equals(obj)) {
                    return new WatchFaceHolderLunarCallPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_call_plus is invalid. Received: " + obj);
            case 15:
                if ("layout/watch_face_holder_lunar_call_pro_0".equals(obj)) {
                    return new WatchFaceHolderLunarCallProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_call_pro is invalid. Received: " + obj);
            case 16:
                if ("layout/watch_face_holder_lunar_comet_0".equals(obj)) {
                    return new WatchFaceHolderLunarCometBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_comet is invalid. Received: " + obj);
            case 17:
                if ("layout/watch_face_holder_lunar_connect_0".equals(obj)) {
                    return new WatchFaceHolderLunarConnectBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_connect is invalid. Received: " + obj);
            case 18:
                if ("layout/watch_face_holder_lunar_connect_plus_0".equals(obj)) {
                    return new WatchFaceHolderLunarConnectPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_connect_plus is invalid. Received: " + obj);
            case 19:
                if ("layout/watch_face_holder_lunar_connect_pro_0".equals(obj)) {
                    return new WatchFaceHolderLunarConnectProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_connect_pro is invalid. Received: " + obj);
            case 20:
                if ("layout/watch_face_holder_lunar_embrace_0".equals(obj)) {
                    return new WatchFaceHolderLunarEmbraceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_embrace is invalid. Received: " + obj);
            case 21:
                if ("layout/watch_face_holder_lunar_fit_0".equals(obj)) {
                    return new WatchFaceHolderLunarFitBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_fit is invalid. Received: " + obj);
            case 22:
                if ("layout/watch_face_holder_lunar_seek_0".equals(obj)) {
                    return new WatchFaceHolderLunarSeekBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_seek is invalid. Received: " + obj);
            case 23:
                if ("layout/watch_face_holder_lunar_velocity_0".equals(obj)) {
                    return new WatchFaceHolderLunarVelocityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_lunar_velocity is invalid. Received: " + obj);
            case 24:
                if ("layout/watch_face_holder_matrix_0".equals(obj)) {
                    return new WatchFaceHolderMatrixBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_matrix is invalid. Received: " + obj);
            case 25:
                if ("layout/watch_face_holder_mercury_0".equals(obj)) {
                    return new WatchFaceHolderMercuryBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_mercury is invalid. Received: " + obj);
            case 26:
                if ("layout/watch_face_holder_opp1_watches_0".equals(obj)) {
                    return new WatchFaceHolderOpp1WatchesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_opp1_watches is invalid. Received: " + obj);
            case 27:
                if ("layout/watch_face_holder_opp2_watches_0".equals(obj)) {
                    return new WatchFaceHolderOpp2WatchesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_opp2_watches is invalid. Received: " + obj);
            case 28:
                if ("layout/watch_face_holder_opp3_watches_0".equals(obj)) {
                    return new WatchFaceHolderOpp3WatchesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_opp3_watches is invalid. Received: " + obj);
            case 29:
                if ("layout/watch_face_holder_opp4_watches_0".equals(obj)) {
                    return new WatchFaceHolderOpp4WatchesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_opp4_watches is invalid. Received: " + obj);
            case 30:
                if ("layout/watch_face_holder_primia_0".equals(obj)) {
                    return new WatchFaceHolderPrimiaBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_primia is invalid. Received: " + obj);
            case 31:
                if ("layout/watch_face_holder_primia_ace_0".equals(obj)) {
                    return new WatchFaceHolderPrimiaAceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_primia_ace is invalid. Received: " + obj);
            case 32:
                if ("layout/watch_face_holder_ps1_enigma_oasis_0".equals(obj)) {
                    return new WatchFaceHolderPs1EnigmaOasisBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_ps1_enigma_oasis is invalid. Received: " + obj);
            case 33:
                if ("layout/watch_face_holder_storm_call_pro_0".equals(obj)) {
                    return new WatchFaceHolderStormCallProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_storm_call_pro is invalid. Received: " + obj);
            case 34:
                if ("layout/watch_face_holder_storm_connect_plus_0".equals(obj)) {
                    return new WatchFaceHolderStormConnectPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_storm_connect_plus is invalid. Received: " + obj);
            case 35:
                if ("layout/watch_face_holder_storm_plus_0".equals(obj)) {
                    return new WatchFaceHolderStormPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_storm_plus is invalid. Received: " + obj);
            case 36:
                if ("layout/watch_face_holder_storm_pro_0".equals(obj)) {
                    return new WatchFaceHolderStormProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_storm_pro is invalid. Received: " + obj);
            case 37:
                if ("layout/watch_face_holder_stride_voice_0".equals(obj)) {
                    return new WatchFaceHolderStrideVoiceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_stride_voice is invalid. Received: " + obj);
            case 38:
                if ("layout/watch_face_holder_ultima_call_0".equals(obj)) {
                    return new WatchFaceHolderUltimaCallBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_ultima_call is invalid. Received: " + obj);
            case 39:
                if ("layout/watch_face_holder_ultima_connect_0".equals(obj)) {
                    return new WatchFaceHolderUltimaConnectBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_ultima_connect is invalid. Received: " + obj);
            case 40:
                if ("layout/watch_face_holder_ultima_vogue_0".equals(obj)) {
                    return new WatchFaceHolderUltimaVogueBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_ultima_vogue is invalid. Received: " + obj);
            case 41:
                if ("layout/watch_face_holder_vertex_0".equals(obj)) {
                    return new WatchFaceHolderVertexBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_vertex is invalid. Received: " + obj);
            case 42:
                if ("layout/watch_face_holder_wave_armour_0".equals(obj)) {
                    return new WatchFaceHolderWaveArmourBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_armour is invalid. Received: " + obj);
            case 43:
                if ("layout/watch_face_holder_wave_armour2_0".equals(obj)) {
                    return new WatchFaceHolderWaveArmour2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_armour2 is invalid. Received: " + obj);
            case 44:
                if ("layout/watch_face_holder_wave_beat_0".equals(obj)) {
                    return new WatchFaceHolderWaveBeatBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_beat is invalid. Received: " + obj);
            case 45:
                if ("layout/watch_face_holder_wave_beat_call_0".equals(obj)) {
                    return new WatchFaceHolderWaveBeatCallBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_beat_call is invalid. Received: " + obj);
            case 46:
                if ("layout/watch_face_holder_wave_call_plus_0".equals(obj)) {
                    return new WatchFaceHolderWaveCallPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_call_plus is invalid. Received: " + obj);
            case 47:
                if ("layout/watch_face_holder_wave_connect_0".equals(obj)) {
                    return new WatchFaceHolderWaveConnectBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_connect is invalid. Received: " + obj);
            case 48:
                if ("layout/watch_face_holder_wave_connect_plus_0".equals(obj)) {
                    return new WatchFaceHolderWaveConnectPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_connect_plus is invalid. Received: " + obj);
            case 49:
                if ("layout/watch_face_holder_wave_elevate_pro_0".equals(obj)) {
                    return new WatchFaceHolderWaveElevateProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_elevate_pro is invalid. Received: " + obj);
            case 50:
                if ("layout/watch_face_holder_wave_elite_0".equals(obj)) {
                    return new WatchFaceHolderWaveEliteBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_elite is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 51:
                if ("layout/watch_face_holder_wave_force_0".equals(obj)) {
                    return new WatchFaceHolderWaveForceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_force is invalid. Received: " + obj);
            case 52:
                if ("layout/watch_face_holder_wave_force2_0".equals(obj)) {
                    return new WatchFaceHolderWaveForce2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_force2 is invalid. Received: " + obj);
            case 53:
                if ("layout/watch_face_holder_wave_genesis_pro_0".equals(obj)) {
                    return new WatchFaceHolderWaveGenesisProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_genesis_pro is invalid. Received: " + obj);
            case 54:
                if ("layout/watch_face_holder_wave_glory_pro_0".equals(obj)) {
                    return new WatchFaceHolderWaveGloryProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_glory_pro is invalid. Received: " + obj);
            case 55:
                if ("layout/watch_face_holder_wave_lynk_voice_0".equals(obj)) {
                    return new WatchFaceHolderWaveLynkVoiceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_lynk_voice is invalid. Received: " + obj);
            case 56:
                if ("layout/watch_face_holder_wave_magma_0".equals(obj)) {
                    return new WatchFaceHolderWaveMagmaBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_magma is invalid. Received: " + obj);
            case 57:
                if ("layout/watch_face_holder_wave_neo_0".equals(obj)) {
                    return new WatchFaceHolderWaveNeoBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_neo is invalid. Received: " + obj);
            case 58:
                if ("layout/watch_face_holder_wave_play_0".equals(obj)) {
                    return new WatchFaceHolderWavePlayBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_play is invalid. Received: " + obj);
            case 59:
                if ("layout/watch_face_holder_wave_prime_0".equals(obj)) {
                    return new WatchFaceHolderWavePrimeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_prime is invalid. Received: " + obj);
            case 60:
                if ("layout/watch_face_holder_wave_prime_talk_0".equals(obj)) {
                    return new WatchFaceHolderWavePrimeTalkBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_prime_talk is invalid. Received: " + obj);
            case 61:
                if ("layout/watch_face_holder_wave_pro_0".equals(obj)) {
                    return new WatchFaceHolderWaveProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_pro is invalid. Received: " + obj);
            case 62:
                if ("layout/watch_face_holder_wave_select_0".equals(obj)) {
                    return new WatchFaceHolderWaveSelectBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_select is invalid. Received: " + obj);
            case 63:
                if ("layout/watch_face_holder_wave_smart_0".equals(obj)) {
                    return new WatchFaceHolderWaveSmartBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_smart is invalid. Received: " + obj);
            case 64:
                if ("layout/watch_face_holder_wave_smart_call_0".equals(obj)) {
                    return new WatchFaceHolderWaveSmartCallBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_smart_call is invalid. Received: " + obj);
            case 65:
                if ("layout/watch_face_holder_wave_spectra_0".equals(obj)) {
                    return new WatchFaceHolderWaveSpectraBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_spectra is invalid. Received: " + obj);
            case 66:
                if ("layout/watch_face_holder_wave_style_0".equals(obj)) {
                    return new WatchFaceHolderWaveStyleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_style is invalid. Received: " + obj);
            case 67:
                if ("layout/watch_face_holder_wave_style_call_0".equals(obj)) {
                    return new WatchFaceHolderWaveStyleCallBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wave_style_call is invalid. Received: " + obj);
            case 68:
                if ("layout/watch_face_holder_wavefit_0".equals(obj)) {
                    return new WatchFaceHolderWavefitBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_wavefit is invalid. Received: " + obj);
            case 69:
                if ("layout/watch_face_holder_xtend_call_plus_0".equals(obj)) {
                    return new WatchFaceHolderXtendCallPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_xtend_call_plus is invalid. Received: " + obj);
            case 70:
                if ("layout/watch_face_holder_xtend_plus_0".equals(obj)) {
                    return new WatchFaceHolderXtendPlusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_xtend_plus is invalid. Received: " + obj);
            case 71:
                if ("layout/watch_face_holder_xtend_pro_0".equals(obj)) {
                    return new WatchFaceHolderXtendProBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_xtend_pro is invalid. Received: " + obj);
            case 72:
                if ("layout/watch_face_holder_xtend_sports_0".equals(obj)) {
                    return new WatchFaceHolderXtendSportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_holder_xtend_sports is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f6115a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                int i3 = (i2 - 1) / 50;
                if (i3 != 0) {
                    if (i3 != 1) {
                        return null;
                    }
                    return internalGetViewDataBinding1(dataBindingComponent, view, i2, tag);
                }
                return internalGetViewDataBinding0(dataBindingComponent, view, i2, tag);
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f6116a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
