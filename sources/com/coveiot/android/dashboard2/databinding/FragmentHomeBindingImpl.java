package com.coveiot.android.dashboard2.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.coveiot.android.activitymodes.databinding.ActivityHistoryBeanLayoutBinding;
import com.coveiot.android.dashboard2.BR;
import com.coveiot.android.dashboard2.customui.PagerContainer;
import com.coveiot.android.dashboard2.model.state.SyncingStateData;
import com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.BestoffersContainersBinding;
import com.coveiot.android.theme.databinding.ConnectedDeviceInfoCardDashboardBinding;
import com.coveiot.android.theme.databinding.DashboardDynamicWebLayoutBinding;
import com.coveiot.android.theme.databinding.DashboardFitnessCardLayoutBinding;
import com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding;
import com.coveiot.android.theme.databinding.ExclusiveCardBoatCoinsBinding;
import com.coveiot.android.theme.databinding.ExclusiveCardContentHeaderInfoImageTextbuttonBinding;
import com.coveiot.android.theme.databinding.FitnessIndusindWellnessCrewBinding;
import com.coveiot.android.theme.databinding.FitnessVitalCardContainerDashboardBinding;
import com.coveiot.android.theme.databinding.FitnessVitalsCardWithBackgroundBinding;
import com.coveiot.android.theme.databinding.KeepGoingLayoutDashboardBinding;
import com.coveiot.android.theme.databinding.LayoutCultFitFtuCardBinding;
import com.coveiot.android.theme.databinding.LayoutMyBuddiesFtuCardBinding;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding;
import com.coveiot.android.theme.databinding.PersonalizedWatchFaceDashboardBinding;
import com.coveiot.android.theme.databinding.ProfileCompletionCardDashboardBinding;
import com.coveiot.android.theme.databinding.RoundedCardCalendarNavLayoutBinding;
import com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding;
import com.coveiot.android.theme.databinding.RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding;
import com.coveiot.android.theme.databinding.SmallHealthCardInfoBinding;
import com.coveiot.android.theme.databinding.SmallHealthCardInfoWithProgressBinding;
import com.coveiot.android.theme.databinding.SmallRoundedCardIconHeaderStatusBinding;
import com.coveiot.android.theme.databinding.ToolbarGenericDashboardBinding;
import com.coveiot.android.theme.databinding.WatchFaceStudioBigCardDashboardBinding;
import com.coveiot.android.theme.databinding.WatchSettingsBigCardDashboardBinding;
import com.coveiot.android.theme.model.BindingDataModel1;
import com.coveiot.android.theme.model.SmallHealthCardInfo;
import com.coveiot.covepreferences.data.StepsDataModel;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* loaded from: classes4.dex */
public class FragmentHomeBindingImpl extends FragmentHomeBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    public long i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(98);
        k = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"toolbar_generic_dashboard"}, new int[]{35}, new int[]{R.layout.toolbar_generic_dashboard});
        int i = R.layout.small_rounded_card_icon_header_status;
        includedLayouts.setIncludes(1, new String[]{"small_rounded_card_icon_header_status", "small_rounded_card_icon_header_status"}, new int[]{36, 37}, new int[]{i, i});
        includedLayouts.setIncludes(3, new String[]{"connected_device_info_card_dashboard"}, new int[]{38}, new int[]{R.layout.connected_device_info_card_dashboard});
        includedLayouts.setIncludes(4, new String[]{"profile_completion_card_dashboard"}, new int[]{39}, new int[]{R.layout.profile_completion_card_dashboard});
        int i2 = R.layout.watch_face_studio_big_card_dashboard;
        includedLayouts.setIncludes(6, new String[]{"watch_settings_big_card_dashboard", "personalized_watch_face_dashboard", "watch_face_studio_big_card_dashboard"}, new int[]{40, 41, 42}, new int[]{R.layout.watch_settings_big_card_dashboard, R.layout.personalized_watch_face_dashboard, i2});
        includedLayouts.setIncludes(12, new String[]{"rounded_card_calendar_nav_layout", "dashboard_fitness_card_layout"}, new int[]{43, 44}, new int[]{R.layout.rounded_card_calendar_nav_layout, R.layout.dashboard_fitness_card_layout});
        includedLayouts.setIncludes(13, new String[]{"keep_going_layout_dashboard"}, new int[]{45}, new int[]{R.layout.keep_going_layout_dashboard});
        int i3 = R.layout.small_health_card_info;
        includedLayouts.setIncludes(14, new String[]{"small_health_card_info_with_progress", "small_health_card_info", "small_health_card_info"}, new int[]{46, 47, 48}, new int[]{R.layout.small_health_card_info_with_progress, i3, i3});
        int i4 = R.layout.rounded_card_nav_layout;
        includedLayouts.setIncludes(15, new String[]{"rounded_card_nav_layout"}, new int[]{49}, new int[]{i4});
        includedLayouts.setIncludes(16, new String[]{"fitness_vital_card_container_dashboard", "fitness_vitals_card_with_background", "fitness_indusind_wellness_crew"}, new int[]{50, 51, 52}, new int[]{R.layout.fitness_vital_card_container_dashboard, R.layout.fitness_vitals_card_with_background, R.layout.fitness_indusind_wellness_crew});
        includedLayouts.setIncludes(18, new String[]{"activity_history_bean_layout", "rounded_card_nav_layout"}, new int[]{53, 54}, new int[]{com.coveiot.android.activitymodes.R.layout.activity_history_bean_layout, i4});
        includedLayouts.setIncludes(19, new String[]{"rounded_left_top_border_color_background_card_header_info_image", "list_item_week_plan_layout"}, new int[]{55, 56}, new int[]{R.layout.rounded_left_top_border_color_background_card_header_info_image, R.layout.list_item_week_plan_layout});
        includedLayouts.setIncludes(20, new String[]{"exclusive_card_content_header_info_image_textbutton", "exclusive_card_boat_coins", "watch_face_studio_big_card_dashboard"}, new int[]{57, 58, 59}, new int[]{R.layout.exclusive_card_content_header_info_image_textbutton, R.layout.exclusive_card_boat_coins, i2});
        includedLayouts.setIncludes(21, new String[]{"layout_my_buddies_ftu_card"}, new int[]{60}, new int[]{R.layout.layout_my_buddies_ftu_card});
        includedLayouts.setIncludes(24, new String[]{"do_more_with_your_watch_card_container_dashboard"}, new int[]{61}, new int[]{R.layout.do_more_with_your_watch_card_container_dashboard});
        includedLayouts.setIncludes(26, new String[]{"layout_cult_fit_ftu_card"}, new int[]{62}, new int[]{R.layout.layout_cult_fit_ftu_card});
        includedLayouts.setIncludes(29, new String[]{"rounded_card_nav_layout"}, new int[]{64}, new int[]{i4});
        includedLayouts.setIncludes(30, new String[]{"no_challenges_banner"}, new int[]{63}, new int[]{com.coveiot.android.fitnesschallenges.R.layout.no_challenges_banner});
        includedLayouts.setIncludes(31, new String[]{"bestoffers_containers"}, new int[]{65}, new int[]{R.layout.bestoffers_containers});
        int i5 = R.layout.dashboard_dynamic_web_layout;
        includedLayouts.setIncludes(33, new String[]{"dashboard_dynamic_web_layout"}, new int[]{66}, new int[]{i5});
        includedLayouts.setIncludes(34, new String[]{"dashboard_dynamic_web_layout"}, new int[]{67}, new int[]{i5});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.swipeContainer, 68);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.sectionContainer, 69);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.section4, 70);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvSetupYourWatchHeader, 71);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.clTopFeature, 72);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvTopFeaturesTitle, 73);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.rv_top_features, 74);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessOverviewHeader, 75);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.clOtherFitnessData, 76);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessVitals, 77);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvEditVitalCards, 78);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.section9, 79);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvActivityHistoryHeader, 80);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.section10, 81);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessJourneyHeader, 82);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessJourneyViewMore, 83);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvboatExclusiveHeader, 84);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.navigationBanner, 85);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.section12, 86);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessBuddiesHeader, 87);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.section14, 88);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvCultFitHeader, 89);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.challenge_header_section_tv, 90);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessChallengeHeader, 91);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessChallengeViewMore, 92);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.rvFitnessChallenge, 93);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.challengeLinearLayoutDots, 94);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.section17, 95);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.v, 96);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.ivPoweredBy, 97);
    }

    public FragmentHomeBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 98, k, l));
    }

    public final boolean A(BestoffersContainersBinding bestoffersContainersBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 4;
            }
            return true;
        }
        return false;
    }

    public final boolean B(RoundedCardNavLayoutBinding roundedCardNavLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1073741824;
            }
            return true;
        }
        return false;
    }

    public final boolean C(RoundedCardNavLayoutBinding roundedCardNavLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 512;
            }
            return true;
        }
        return false;
    }

    public final boolean D(RoundedCardNavLayoutBinding roundedCardNavLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            return true;
        }
        return false;
    }

    public final boolean E(WatchSettingsBigCardDashboardBinding watchSettingsBigCardDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1024;
            }
            return true;
        }
        return false;
    }

    public final boolean F(WatchFaceStudioBigCardDashboardBinding watchFaceStudioBigCardDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 2147483648L;
            }
            return true;
        }
        return false;
    }

    public final boolean G(WatchFaceStudioBigCardDashboardBinding watchFaceStudioBigCardDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 16;
            }
            return true;
        }
        return false;
    }

    public final boolean a(ExclusiveCardContentHeaderInfoImageTextbuttonBinding exclusiveCardContentHeaderInfoImageTextbuttonBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 134217728;
            }
            return true;
        }
        return false;
    }

    public final boolean b(ActivityHistoryBeanLayoutBinding activityHistoryBeanLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 536870912;
            }
            return true;
        }
        return false;
    }

    public final boolean c(ExclusiveCardBoatCoinsBinding exclusiveCardBoatCoinsBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 128;
            }
            return true;
        }
        return false;
    }

    public final boolean d(RoundedCardCalendarNavLayoutBinding roundedCardCalendarNavLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    public final boolean e(LayoutCultFitFtuCardBinding layoutCultFitFtuCardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 268435456;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x04e2  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x019c  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            Method dump skipped, instructions count: 1580
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.databinding.FragmentHomeBindingImpl.executeBindings():void");
    }

    public final boolean f(DoMoreWithYourWatchCardContainerDashboardBinding doMoreWithYourWatchCardContainerDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            }
            return true;
        }
        return false;
    }

    public final boolean g(DashboardDynamicWebLayoutBinding dashboardDynamicWebLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 16777216;
            }
            return true;
        }
        return false;
    }

    public final boolean h(DashboardDynamicWebLayoutBinding dashboardDynamicWebLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 8388608;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i == 0 && this.j == 0) {
                return this.toolbar.hasPendingBindings() || this.lBleState.hasPendingBindings() || this.lBt3State.hasPendingBindings() || this.syncStatusLayout.hasPendingBindings() || this.profileLayout.hasPendingBindings() || this.watchSettingsBig.hasPendingBindings() || this.personalizedWatchfaceContainer.hasPendingBindings() || this.watchfaceStudioBigTop.hasPendingBindings() || this.calSelectionView.hasPendingBindings() || this.fitnessOverviewCl.hasPendingBindings() || this.progressMotivationalMessage.hasPendingBindings() || this.fitnessSleep.hasPendingBindings() || this.fitnessDistance.hasPendingBindings() || this.fitnessCalorie.hasPendingBindings() || this.viewFitnessDashboardHeader.hasPendingBindings() || this.healthVitalsCardContainer.hasPendingBindings() || this.trackMoreVitals.hasPendingBindings() || this.indusIndWellnessCrew.hasPendingBindings() || this.activtyHistory.hasPendingBindings() || this.viewActivityHistoryHeader.hasPendingBindings() || this.fitnessJourney.hasPendingBindings() || this.fitnessJourneyOngoing.hasPendingBindings() || this.activities700plus.hasPendingBindings() || this.boatCoinsLayout.hasPendingBindings() || this.watchfaceStudioBig.hasPendingBindings() || this.myBuddiesFtu.hasPendingBindings() || this.doMoreWithYourWatchCardContainer.hasPendingBindings() || this.cultFitFtu.hasPendingBindings() || this.noChallengeView.hasPendingBindings() || this.viewFitnessChallengeDashboardHeader.hasPendingBindings() || this.tvBestOffersCardContainer.hasPendingBindings() || this.dynamicWebView.hasPendingBindings() || this.dynamicWebView1.hasPendingBindings();
            }
            return true;
        }
    }

    public final boolean i(SmallHealthCardInfoBinding smallHealthCardInfoBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 4194304;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = LockFreeTaskQueueCore.CLOSED_MASK;
            this.j = 0L;
        }
        this.toolbar.invalidateAll();
        this.lBleState.invalidateAll();
        this.lBt3State.invalidateAll();
        this.syncStatusLayout.invalidateAll();
        this.profileLayout.invalidateAll();
        this.watchSettingsBig.invalidateAll();
        this.personalizedWatchfaceContainer.invalidateAll();
        this.watchfaceStudioBigTop.invalidateAll();
        this.calSelectionView.invalidateAll();
        this.fitnessOverviewCl.invalidateAll();
        this.progressMotivationalMessage.invalidateAll();
        this.fitnessSleep.invalidateAll();
        this.fitnessDistance.invalidateAll();
        this.fitnessCalorie.invalidateAll();
        this.viewFitnessDashboardHeader.invalidateAll();
        this.healthVitalsCardContainer.invalidateAll();
        this.trackMoreVitals.invalidateAll();
        this.indusIndWellnessCrew.invalidateAll();
        this.activtyHistory.invalidateAll();
        this.viewActivityHistoryHeader.invalidateAll();
        this.fitnessJourney.invalidateAll();
        this.fitnessJourneyOngoing.invalidateAll();
        this.activities700plus.invalidateAll();
        this.boatCoinsLayout.invalidateAll();
        this.watchfaceStudioBig.invalidateAll();
        this.myBuddiesFtu.invalidateAll();
        this.doMoreWithYourWatchCardContainer.invalidateAll();
        this.cultFitFtu.invalidateAll();
        this.noChallengeView.invalidateAll();
        this.viewFitnessChallengeDashboardHeader.invalidateAll();
        this.tvBestOffersCardContainer.invalidateAll();
        this.dynamicWebView.invalidateAll();
        this.dynamicWebView1.invalidateAll();
        requestRebind();
    }

    public final boolean j(SmallHealthCardInfoBinding smallHealthCardInfoBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 67108864;
            }
            return true;
        }
        return false;
    }

    public final boolean k(RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding roundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    public final boolean l(ListItemWeekPlanLayoutBinding listItemWeekPlanLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            return true;
        }
        return false;
    }

    public final boolean m(DashboardFitnessCardLayoutBinding dashboardFitnessCardLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 64;
            }
            return true;
        }
        return false;
    }

    public final boolean n(SmallHealthCardInfoWithProgressBinding smallHealthCardInfoWithProgressBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            return true;
        }
        return false;
    }

    public final boolean o(FitnessVitalCardContainerDashboardBinding fitnessVitalCardContainerDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 8;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return x((ConnectedDeviceInfoCardDashboardBinding) obj, i2);
            case 1:
                return p((FitnessIndusindWellnessCrewBinding) obj, i2);
            case 2:
                return A((BestoffersContainersBinding) obj, i2);
            case 3:
                return o((FitnessVitalCardContainerDashboardBinding) obj, i2);
            case 4:
                return G((WatchFaceStudioBigCardDashboardBinding) obj, i2);
            case 5:
                return v((ProfileCompletionCardDashboardBinding) obj, i2);
            case 6:
                return m((DashboardFitnessCardLayoutBinding) obj, i2);
            case 7:
                return c((ExclusiveCardBoatCoinsBinding) obj, i2);
            case 8:
                return u((PersonalizedWatchFaceDashboardBinding) obj, i2);
            case 9:
                return C((RoundedCardNavLayoutBinding) obj, i2);
            case 10:
                return E((WatchSettingsBigCardDashboardBinding) obj, i2);
            case 11:
                return k((RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding) obj, i2);
            case 12:
                return s((LayoutMyBuddiesFtuCardBinding) obj, i2);
            case 13:
                return z((FitnessVitalsCardWithBackgroundBinding) obj, i2);
            case 14:
                return y((ToolbarGenericDashboardBinding) obj, i2);
            case 15:
                return D((RoundedCardNavLayoutBinding) obj, i2);
            case 16:
                return d((RoundedCardCalendarNavLayoutBinding) obj, i2);
            case 17:
                return n((SmallHealthCardInfoWithProgressBinding) obj, i2);
            case 18:
                return w((KeepGoingLayoutDashboardBinding) obj, i2);
            case 19:
                return l((ListItemWeekPlanLayoutBinding) obj, i2);
            case 20:
                return f((DoMoreWithYourWatchCardContainerDashboardBinding) obj, i2);
            case 21:
                return t((NoChallengesBannerBinding) obj, i2);
            case 22:
                return i((SmallHealthCardInfoBinding) obj, i2);
            case 23:
                return h((DashboardDynamicWebLayoutBinding) obj, i2);
            case 24:
                return g((DashboardDynamicWebLayoutBinding) obj, i2);
            case 25:
                return r((SmallRoundedCardIconHeaderStatusBinding) obj, i2);
            case 26:
                return j((SmallHealthCardInfoBinding) obj, i2);
            case 27:
                return a((ExclusiveCardContentHeaderInfoImageTextbuttonBinding) obj, i2);
            case 28:
                return e((LayoutCultFitFtuCardBinding) obj, i2);
            case 29:
                return b((ActivityHistoryBeanLayoutBinding) obj, i2);
            case 30:
                return B((RoundedCardNavLayoutBinding) obj, i2);
            case 31:
                return F((WatchFaceStudioBigCardDashboardBinding) obj, i2);
            case 32:
                return q((SmallRoundedCardIconHeaderStatusBinding) obj, i2);
            default:
                return false;
        }
    }

    public final boolean p(FitnessIndusindWellnessCrewBinding fitnessIndusindWellnessCrewBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean q(SmallRoundedCardIconHeaderStatusBinding smallRoundedCardIconHeaderStatusBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 4294967296L;
            }
            return true;
        }
        return false;
    }

    public final boolean r(SmallRoundedCardIconHeaderStatusBinding smallRoundedCardIconHeaderStatusBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 33554432;
            }
            return true;
        }
        return false;
    }

    public final boolean s(LayoutMyBuddiesFtuCardBinding layoutMyBuddiesFtuCardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setBestOffers(@Nullable Integer num) {
        this.mBestOffers = num;
        synchronized (this) {
            this.i |= 34359738368L;
        }
        notifyPropertyChanged(BR.bestOffers);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setBleConnectionState(@Nullable BindingDataModel1 bindingDataModel1) {
        this.mBleConnectionState = bindingDataModel1;
        synchronized (this) {
            this.i |= 1099511627776L;
        }
        notifyPropertyChanged(BR.bleConnectionState);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setBt3ConnectionState(@Nullable BindingDataModel1 bindingDataModel1) {
        this.mBt3ConnectionState = bindingDataModel1;
        synchronized (this) {
            this.i |= 70368744177664L;
        }
        notifyPropertyChanged(BR.bt3ConnectionState);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setCalorieData(@Nullable SmallHealthCardInfo smallHealthCardInfo) {
        this.mCalorieData = smallHealthCardInfo;
        synchronized (this) {
            this.i |= 274877906944L;
        }
        notifyPropertyChanged(BR.calorieData);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setDistanceData(@Nullable SmallHealthCardInfo smallHealthCardInfo) {
        this.mDistanceData = smallHealthCardInfo;
        synchronized (this) {
            this.i |= 8796093022208L;
        }
        notifyPropertyChanged(BR.distanceData);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setDoMoreWithYourWatchItemCount(@Nullable Integer num) {
        this.mDoMoreWithYourWatchItemCount = num;
        synchronized (this) {
            this.i |= 549755813888L;
        }
        notifyPropertyChanged(BR.doMoreWithYourWatchItemCount);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setIsFitnessPlanOngoing(@Nullable Boolean bool) {
        this.mIsFitnessPlanOngoing = bool;
        synchronized (this) {
            this.i |= 2251799813685248L;
        }
        notifyPropertyChanged(BR.isFitnessPlanOngoing);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setIsSetupYourWatchSettingsCompleted(@Nullable Boolean bool) {
        this.mIsSetupYourWatchSettingsCompleted = bool;
        synchronized (this) {
            this.i |= 4503599627370496L;
        }
        notifyPropertyChanged(BR.isSetupYourWatchSettingsCompleted);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setIsUserProfileCompleted(@Nullable Boolean bool) {
        this.mIsUserProfileCompleted = bool;
        synchronized (this) {
            this.i |= 18014398509481984L;
        }
        notifyPropertyChanged(BR.isUserProfileCompleted);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.toolbar.setLifecycleOwner(lifecycleOwner);
        this.lBleState.setLifecycleOwner(lifecycleOwner);
        this.lBt3State.setLifecycleOwner(lifecycleOwner);
        this.syncStatusLayout.setLifecycleOwner(lifecycleOwner);
        this.profileLayout.setLifecycleOwner(lifecycleOwner);
        this.watchSettingsBig.setLifecycleOwner(lifecycleOwner);
        this.personalizedWatchfaceContainer.setLifecycleOwner(lifecycleOwner);
        this.watchfaceStudioBigTop.setLifecycleOwner(lifecycleOwner);
        this.calSelectionView.setLifecycleOwner(lifecycleOwner);
        this.fitnessOverviewCl.setLifecycleOwner(lifecycleOwner);
        this.progressMotivationalMessage.setLifecycleOwner(lifecycleOwner);
        this.fitnessSleep.setLifecycleOwner(lifecycleOwner);
        this.fitnessDistance.setLifecycleOwner(lifecycleOwner);
        this.fitnessCalorie.setLifecycleOwner(lifecycleOwner);
        this.viewFitnessDashboardHeader.setLifecycleOwner(lifecycleOwner);
        this.healthVitalsCardContainer.setLifecycleOwner(lifecycleOwner);
        this.trackMoreVitals.setLifecycleOwner(lifecycleOwner);
        this.indusIndWellnessCrew.setLifecycleOwner(lifecycleOwner);
        this.activtyHistory.setLifecycleOwner(lifecycleOwner);
        this.viewActivityHistoryHeader.setLifecycleOwner(lifecycleOwner);
        this.fitnessJourney.setLifecycleOwner(lifecycleOwner);
        this.fitnessJourneyOngoing.setLifecycleOwner(lifecycleOwner);
        this.activities700plus.setLifecycleOwner(lifecycleOwner);
        this.boatCoinsLayout.setLifecycleOwner(lifecycleOwner);
        this.watchfaceStudioBig.setLifecycleOwner(lifecycleOwner);
        this.myBuddiesFtu.setLifecycleOwner(lifecycleOwner);
        this.doMoreWithYourWatchCardContainer.setLifecycleOwner(lifecycleOwner);
        this.cultFitFtu.setLifecycleOwner(lifecycleOwner);
        this.noChallengeView.setLifecycleOwner(lifecycleOwner);
        this.viewFitnessChallengeDashboardHeader.setLifecycleOwner(lifecycleOwner);
        this.tvBestOffersCardContainer.setLifecycleOwner(lifecycleOwner);
        this.dynamicWebView.setLifecycleOwner(lifecycleOwner);
        this.dynamicWebView1.setLifecycleOwner(lifecycleOwner);
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setSelectedVitalsCount(@Nullable Integer num) {
        this.mSelectedVitalsCount = num;
        synchronized (this) {
            this.i |= 1125899906842624L;
        }
        notifyPropertyChanged(BR.selectedVitalsCount);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setSetupYourWatchItemCount(@Nullable Integer num) {
        this.mSetupYourWatchItemCount = num;
        synchronized (this) {
            this.i |= 562949953421312L;
        }
        notifyPropertyChanged(BR.setupYourWatchItemCount);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setSetupYourWatchItemSelectedPosition(@Nullable Integer num) {
        this.mSetupYourWatchItemSelectedPosition = num;
        synchronized (this) {
            this.i |= 288230376151711744L;
        }
        notifyPropertyChanged(BR.setupYourWatchItemSelectedPosition);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShow1kFtu(@Nullable Boolean bool) {
        this.mShow1kFtu = bool;
        synchronized (this) {
            this.i |= 576460752303423488L;
        }
        notifyPropertyChanged(BR.show1kFtu);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowActivityHistory(@Nullable Boolean bool) {
        this.mShowActivityHistory = bool;
        synchronized (this) {
            this.i |= 17592186044416L;
        }
        notifyPropertyChanged(BR.showActivityHistory);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowBoatCoins(@Nullable Boolean bool) {
        this.mShowBoatCoins = bool;
        synchronized (this) {
            this.i |= 17179869184L;
        }
        notifyPropertyChanged(BR.showBoatCoins);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowCultFitFTU(@Nullable Boolean bool) {
        this.mShowCultFitFTU = bool;
        synchronized (this) {
            this.i |= 9007199254740992L;
        }
        notifyPropertyChanged(BR.showCultFitFTU);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowDynamicTab(@Nullable Boolean bool) {
        this.mShowDynamicTab = bool;
        synchronized (this) {
            this.i |= 8589934592L;
        }
        notifyPropertyChanged(BR.showDynamicTab);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowFitnessChallenge(@Nullable Boolean bool) {
        this.mShowFitnessChallenge = bool;
        synchronized (this) {
            this.i |= 68719476736L;
        }
        notifyPropertyChanged(BR.showFitnessChallenge);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowMyBuddies(@Nullable Boolean bool) {
        this.mShowMyBuddies = bool;
        synchronized (this) {
            this.i |= 2199023255552L;
        }
        notifyPropertyChanged(BR.showMyBuddies);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowMyBuddiesList(@Nullable Boolean bool) {
        this.mShowMyBuddiesList = bool;
        synchronized (this) {
            this.i |= 4398046511104L;
        }
        notifyPropertyChanged(BR.showMyBuddiesList);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowPersonalizedWatchFace(@Nullable Boolean bool) {
        this.mShowPersonalizedWatchFace = bool;
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowWatchSettingsBigLayout(@Nullable Boolean bool) {
        this.mShowWatchSettingsBigLayout = bool;
        synchronized (this) {
            this.i |= 140737488355328L;
        }
        notifyPropertyChanged(BR.showWatchSettingsBigLayout);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowWatchfaceBigLayout(@Nullable Boolean bool) {
        this.mShowWatchfaceBigLayout = bool;
        synchronized (this) {
            this.i |= LockFreeTaskQueueCore.FROZEN_MASK;
        }
        notifyPropertyChanged(BR.showWatchfaceBigLayout);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowWatchfaceStudioBigLayout(@Nullable Boolean bool) {
        this.mShowWatchfaceStudioBigLayout = bool;
        synchronized (this) {
            this.i |= 35184372088832L;
        }
        notifyPropertyChanged(BR.showWatchfaceStudioBigLayout);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setShowWatchfaceStudioBigLayoutTop(@Nullable Boolean bool) {
        this.mShowWatchfaceStudioBigLayoutTop = bool;
        synchronized (this) {
            this.i |= 281474976710656L;
        }
        notifyPropertyChanged(BR.showWatchfaceStudioBigLayoutTop);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setSleepData(@Nullable SmallHealthCardInfo smallHealthCardInfo) {
        this.mSleepData = smallHealthCardInfo;
        synchronized (this) {
            this.i |= 36028797018963968L;
        }
        notifyPropertyChanged(BR.sleepData);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setStepsDataModel(@Nullable StepsDataModel stepsDataModel) {
        this.mStepsDataModel = stepsDataModel;
        synchronized (this) {
            this.i |= 144115188075855872L;
        }
        notifyPropertyChanged(BR.stepsDataModel);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeBinding
    public void setSyncingStateData(@Nullable SyncingStateData syncingStateData) {
        this.mSyncingStateData = syncingStateData;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.showDynamicTab == i) {
            setShowDynamicTab((Boolean) obj);
        } else if (BR.showBoatCoins == i) {
            setShowBoatCoins((Boolean) obj);
        } else if (BR.bestOffers == i) {
            setBestOffers((Integer) obj);
        } else if (BR.showFitnessChallenge == i) {
            setShowFitnessChallenge((Boolean) obj);
        } else if (BR.showPersonalizedWatchFace == i) {
            setShowPersonalizedWatchFace((Boolean) obj);
        } else if (BR.calorieData == i) {
            setCalorieData((SmallHealthCardInfo) obj);
        } else if (BR.doMoreWithYourWatchItemCount == i) {
            setDoMoreWithYourWatchItemCount((Integer) obj);
        } else if (BR.bleConnectionState == i) {
            setBleConnectionState((BindingDataModel1) obj);
        } else if (BR.showMyBuddies == i) {
            setShowMyBuddies((Boolean) obj);
        } else if (BR.showMyBuddiesList == i) {
            setShowMyBuddiesList((Boolean) obj);
        } else if (BR.distanceData == i) {
            setDistanceData((SmallHealthCardInfo) obj);
        } else if (BR.showActivityHistory == i) {
            setShowActivityHistory((Boolean) obj);
        } else if (BR.showWatchfaceStudioBigLayout == i) {
            setShowWatchfaceStudioBigLayout((Boolean) obj);
        } else if (BR.bt3ConnectionState == i) {
            setBt3ConnectionState((BindingDataModel1) obj);
        } else if (BR.showWatchSettingsBigLayout == i) {
            setShowWatchSettingsBigLayout((Boolean) obj);
        } else if (BR.showWatchfaceStudioBigLayoutTop == i) {
            setShowWatchfaceStudioBigLayoutTop((Boolean) obj);
        } else if (BR.setupYourWatchItemCount == i) {
            setSetupYourWatchItemCount((Integer) obj);
        } else if (BR.selectedVitalsCount == i) {
            setSelectedVitalsCount((Integer) obj);
        } else if (BR.isFitnessPlanOngoing == i) {
            setIsFitnessPlanOngoing((Boolean) obj);
        } else if (BR.isSetupYourWatchSettingsCompleted == i) {
            setIsSetupYourWatchSettingsCompleted((Boolean) obj);
        } else if (BR.showCultFitFTU == i) {
            setShowCultFitFTU((Boolean) obj);
        } else if (BR.isUserProfileCompleted == i) {
            setIsUserProfileCompleted((Boolean) obj);
        } else if (BR.sleepData == i) {
            setSleepData((SmallHealthCardInfo) obj);
        } else if (BR.syncingStateData == i) {
            setSyncingStateData((SyncingStateData) obj);
        } else if (BR.stepsDataModel == i) {
            setStepsDataModel((StepsDataModel) obj);
        } else if (BR.setupYourWatchItemSelectedPosition == i) {
            setSetupYourWatchItemSelectedPosition((Integer) obj);
        } else if (BR.show1kFtu == i) {
            setShow1kFtu((Boolean) obj);
        } else if (BR.showWatchfaceBigLayout != i) {
            return false;
        } else {
            setShowWatchfaceBigLayout((Boolean) obj);
        }
        return true;
    }

    public final boolean t(NoChallengesBannerBinding noChallengesBannerBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            return true;
        }
        return false;
    }

    public final boolean u(PersonalizedWatchFaceDashboardBinding personalizedWatchFaceDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 256;
            }
            return true;
        }
        return false;
    }

    public final boolean v(ProfileCompletionCardDashboardBinding profileCompletionCardDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 32;
            }
            return true;
        }
        return false;
    }

    public final boolean w(KeepGoingLayoutDashboardBinding keepGoingLayoutDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
            }
            return true;
        }
        return false;
    }

    public final boolean x(ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean y(ToolbarGenericDashboardBinding toolbarGenericDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 16384;
            }
            return true;
        }
        return false;
    }

    public final boolean z(FitnessVitalsCardWithBackgroundBinding fitnessVitalsCardWithBackgroundBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        }
        return false;
    }

    public FragmentHomeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 33, (ExclusiveCardContentHeaderInfoImageTextbuttonBinding) objArr[57], (ActivityHistoryBeanLayoutBinding) objArr[53], (ExclusiveCardBoatCoinsBinding) objArr[58], (ConstraintLayout) objArr[21], (RoundedCardCalendarNavLayoutBinding) objArr[43], (ConstraintLayout) objArr[30], (ConstraintLayout) objArr[90], (LinearLayout) objArr[94], (ConstraintLayout) objArr[18], (LinearLayout) objArr[19], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[76], (ConstraintLayout) objArr[72], (LayoutCultFitFtuCardBinding) objArr[62], (ConstraintLayout) objArr[26], (DoMoreWithYourWatchCardContainerDashboardBinding) objArr[61], (RecyclerView) objArr[5], (DashboardDynamicWebLayoutBinding) objArr[66], (DashboardDynamicWebLayoutBinding) objArr[67], (SmallHealthCardInfoBinding) objArr[48], (SmallHealthCardInfoBinding) objArr[47], (RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding) objArr[55], (ListItemWeekPlanLayoutBinding) objArr[56], (DashboardFitnessCardLayoutBinding) objArr[44], (SmallHealthCardInfoWithProgressBinding) objArr[46], (FrameLayout) objArr[28], (FitnessVitalCardContainerDashboardBinding) objArr[50], (ImageView) objArr[9], (ImageView) objArr[10], (ImageView) objArr[11], (FitnessIndusindWellnessCrewBinding) objArr[52], (ImageView) objArr[97], (SmallRoundedCardIconHeaderStatusBinding) objArr[36], (SmallRoundedCardIconHeaderStatusBinding) objArr[37], (LinearLayout) objArr[8], (LinearLayout) objArr[14], (LayoutMyBuddiesFtuCardBinding) objArr[60], (ImageView) objArr[85], (NoChallengesBannerBinding) objArr[63], (PersonalizedWatchFaceDashboardBinding) objArr[41], (View) objArr[2], (ProfileCompletionCardDashboardBinding) objArr[39], (KeepGoingLayoutDashboardBinding) objArr[45], (RecyclerView) objArr[23], (RecyclerView) objArr[93], (RecyclerView) objArr[74], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[81], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[86], (ConstraintLayout) objArr[24], (ConstraintLayout) objArr[88], (ConstraintLayout) objArr[31], (ConstraintLayout) objArr[33], (ConstraintLayout) objArr[95], (ConstraintLayout) objArr[29], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[70], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[16], (ConstraintLayout) objArr[79], (ConstraintLayout) objArr[69], (ConstraintLayout) objArr[34], (PagerContainer) objArr[7], (SwipeRefreshLayout) objArr[68], (ConnectedDeviceInfoCardDashboardBinding) objArr[38], (ToolbarGenericDashboardBinding) objArr[35], (FitnessVitalsCardWithBackgroundBinding) objArr[51], (TextView) objArr[80], (TextView) objArr[32], (BestoffersContainersBinding) objArr[65], (TextView) objArr[89], (TextView) objArr[25], (TextView) objArr[78], (TextView) objArr[87], (TextView) objArr[91], (TextView) objArr[92], (TextView) objArr[82], (TextView) objArr[83], (TextView) objArr[75], (TextView) objArr[77], (TextView) objArr[71], (TextView) objArr[73], (TextView) objArr[84], (View) objArr[96], (RoundedCardNavLayoutBinding) objArr[54], (RoundedCardNavLayoutBinding) objArr[64], (RoundedCardNavLayoutBinding) objArr[49], (TextView) objArr[22], (TextView) objArr[27], (WatchSettingsBigCardDashboardBinding) objArr[40], (WatchFaceStudioBigCardDashboardBinding) objArr[59], (WatchFaceStudioBigCardDashboardBinding) objArr[42]);
        this.i = -1L;
        this.j = -1L;
        setContainedBinding(this.activities700plus);
        setContainedBinding(this.activtyHistory);
        setContainedBinding(this.boatCoinsLayout);
        this.buddiesLayout.setTag(null);
        setContainedBinding(this.calSelectionView);
        this.challengeBannerSectionLayout.setTag(null);
        this.clActivityHistory.setTag(null);
        this.clFitnessJourneyMain.setTag(null);
        this.clFitnessVitalsHeader.setTag(null);
        setContainedBinding(this.cultFitFtu);
        this.cultFitLayout.setTag(null);
        setContainedBinding(this.doMoreWithYourWatchCardContainer);
        this.dynamicTabRecy.setTag(null);
        setContainedBinding(this.dynamicWebView);
        setContainedBinding(this.dynamicWebView1);
        setContainedBinding(this.fitnessCalorie);
        setContainedBinding(this.fitnessDistance);
        setContainedBinding(this.fitnessJourney);
        setContainedBinding(this.fitnessJourneyOngoing);
        setContainedBinding(this.fitnessOverviewCl);
        setContainedBinding(this.fitnessSleep);
        this.fragmentContainerCultFit.setTag(null);
        setContainedBinding(this.healthVitalsCardContainer);
        this.imageViewDot1.setTag(null);
        this.imageViewDot2.setTag(null);
        this.imageViewDot3.setTag(null);
        setContainedBinding(this.indusIndWellnessCrew);
        setContainedBinding(this.lBleState);
        setContainedBinding(this.lBt3State);
        this.linearLayoutDots.setTag(null);
        this.llOtherFitnessData.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.myBuddiesFtu);
        setContainedBinding(this.noChallengeView);
        setContainedBinding(this.personalizedWatchfaceContainer);
        this.placeHolderView1.setTag(null);
        setContainedBinding(this.profileLayout);
        setContainedBinding(this.progressMotivationalMessage);
        this.rcvBuddiesList.setTag(null);
        this.section1.setTag(null);
        this.section11.setTag(null);
        this.section13.setTag(null);
        this.section15.setTag(null);
        this.section16.setTag(null);
        this.section18.setTag(null);
        this.section2.setTag(null);
        this.section3.setTag(null);
        this.section5.setTag(null);
        this.section61.setTag(null);
        this.section62.setTag(null);
        this.section7.setTag(null);
        this.section8.setTag(null);
        this.sectionDynamicTab.setTag(null);
        this.setYourWatchPager.setTag(null);
        setContainedBinding(this.syncStatusLayout);
        setContainedBinding(this.toolbar);
        setContainedBinding(this.trackMoreVitals);
        this.tvBestOffers.setTag(null);
        setContainedBinding(this.tvBestOffersCardContainer);
        this.tvDoMoreWithYourWatch.setTag(null);
        setContainedBinding(this.viewActivityHistoryHeader);
        setContainedBinding(this.viewFitnessChallengeDashboardHeader);
        setContainedBinding(this.viewFitnessDashboardHeader);
        this.viewMoreBuddies.setTag(null);
        this.viewMoreCultFit.setTag(null);
        setContainedBinding(this.watchSettingsBig);
        setContainedBinding(this.watchfaceStudioBig);
        setContainedBinding(this.watchfaceStudioBigTop);
        setRootTag(view);
        invalidateAll();
    }
}
