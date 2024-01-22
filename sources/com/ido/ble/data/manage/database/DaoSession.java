package com.ido.ble.data.manage.database;

import com.ido.ble.event.stat.one.faildata.FailLogInfoDao;
import com.ido.ble.event.stat.one.faildata.c;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsDao;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.gps.database.HealthGpsItemDao;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
public class DaoSession extends AbstractDaoSession {
    private final FailLogInfoDao failLogInfoDao;
    private final DaoConfig failLogInfoDaoConfig;
    private final HealthActivityDao healthActivityDao;
    private final DaoConfig healthActivityDaoConfig;
    private final HealthBloodPressedDao healthBloodPressedDao;
    private final DaoConfig healthBloodPressedDaoConfig;
    private final HealthBloodPressedItemDao healthBloodPressedItemDao;
    private final DaoConfig healthBloodPressedItemDaoConfig;
    private final HealthGpsDao healthGpsDao;
    private final DaoConfig healthGpsDaoConfig;
    private final HealthGpsItemDao healthGpsItemDao;
    private final DaoConfig healthGpsItemDaoConfig;
    private final HealthHeartRateDao healthHeartRateDao;
    private final DaoConfig healthHeartRateDaoConfig;
    private final HealthHeartRateItemDao healthHeartRateItemDao;
    private final DaoConfig healthHeartRateItemDaoConfig;
    private final HealthHeartRateSecondDao healthHeartRateSecondDao;
    private final DaoConfig healthHeartRateSecondDaoConfig;
    private final HealthPressureDao healthPressureDao;
    private final DaoConfig healthPressureDaoConfig;
    private final HealthPressureItemDao healthPressureItemDao;
    private final DaoConfig healthPressureItemDaoConfig;
    private final HealthSleepDao healthSleepDao;
    private final DaoConfig healthSleepDaoConfig;
    private final HealthSleepItemDao healthSleepItemDao;
    private final DaoConfig healthSleepItemDaoConfig;
    private final HealthSpO2Dao healthSpO2Dao;
    private final DaoConfig healthSpO2DaoConfig;
    private final HealthSpO2ItemDao healthSpO2ItemDao;
    private final DaoConfig healthSpO2ItemDaoConfig;
    private final HealthSportDao healthSportDao;
    private final DaoConfig healthSportDaoConfig;
    private final HealthSportItemDao healthSportItemDao;
    private final DaoConfig healthSportItemDaoConfig;
    private final HealthSwimmingDao healthSwimmingDao;
    private final DaoConfig healthSwimmingDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        DaoConfig clone = map.get(HealthActivityDao.class).clone();
        this.healthActivityDaoConfig = clone;
        clone.initIdentityScope(identityScopeType);
        DaoConfig clone2 = map.get(HealthBloodPressedDao.class).clone();
        this.healthBloodPressedDaoConfig = clone2;
        clone2.initIdentityScope(identityScopeType);
        DaoConfig clone3 = map.get(HealthBloodPressedItemDao.class).clone();
        this.healthBloodPressedItemDaoConfig = clone3;
        clone3.initIdentityScope(identityScopeType);
        DaoConfig clone4 = map.get(HealthHeartRateDao.class).clone();
        this.healthHeartRateDaoConfig = clone4;
        clone4.initIdentityScope(identityScopeType);
        DaoConfig clone5 = map.get(HealthHeartRateItemDao.class).clone();
        this.healthHeartRateItemDaoConfig = clone5;
        clone5.initIdentityScope(identityScopeType);
        DaoConfig clone6 = map.get(HealthHeartRateSecondDao.class).clone();
        this.healthHeartRateSecondDaoConfig = clone6;
        clone6.initIdentityScope(identityScopeType);
        DaoConfig clone7 = map.get(HealthPressureDao.class).clone();
        this.healthPressureDaoConfig = clone7;
        clone7.initIdentityScope(identityScopeType);
        DaoConfig clone8 = map.get(HealthPressureItemDao.class).clone();
        this.healthPressureItemDaoConfig = clone8;
        clone8.initIdentityScope(identityScopeType);
        DaoConfig clone9 = map.get(HealthSleepDao.class).clone();
        this.healthSleepDaoConfig = clone9;
        clone9.initIdentityScope(identityScopeType);
        DaoConfig clone10 = map.get(HealthSleepItemDao.class).clone();
        this.healthSleepItemDaoConfig = clone10;
        clone10.initIdentityScope(identityScopeType);
        DaoConfig clone11 = map.get(HealthSpO2Dao.class).clone();
        this.healthSpO2DaoConfig = clone11;
        clone11.initIdentityScope(identityScopeType);
        DaoConfig clone12 = map.get(HealthSpO2ItemDao.class).clone();
        this.healthSpO2ItemDaoConfig = clone12;
        clone12.initIdentityScope(identityScopeType);
        DaoConfig clone13 = map.get(HealthSportDao.class).clone();
        this.healthSportDaoConfig = clone13;
        clone13.initIdentityScope(identityScopeType);
        DaoConfig clone14 = map.get(HealthSportItemDao.class).clone();
        this.healthSportItemDaoConfig = clone14;
        clone14.initIdentityScope(identityScopeType);
        DaoConfig clone15 = map.get(HealthSwimmingDao.class).clone();
        this.healthSwimmingDaoConfig = clone15;
        clone15.initIdentityScope(identityScopeType);
        DaoConfig clone16 = map.get(FailLogInfoDao.class).clone();
        this.failLogInfoDaoConfig = clone16;
        clone16.initIdentityScope(identityScopeType);
        DaoConfig clone17 = map.get(HealthGpsDao.class).clone();
        this.healthGpsDaoConfig = clone17;
        clone17.initIdentityScope(identityScopeType);
        DaoConfig clone18 = map.get(HealthGpsItemDao.class).clone();
        this.healthGpsItemDaoConfig = clone18;
        clone18.initIdentityScope(identityScopeType);
        HealthActivityDao healthActivityDao = new HealthActivityDao(clone, this);
        this.healthActivityDao = healthActivityDao;
        HealthBloodPressedDao healthBloodPressedDao = new HealthBloodPressedDao(clone2, this);
        this.healthBloodPressedDao = healthBloodPressedDao;
        HealthBloodPressedItemDao healthBloodPressedItemDao = new HealthBloodPressedItemDao(clone3, this);
        this.healthBloodPressedItemDao = healthBloodPressedItemDao;
        HealthHeartRateDao healthHeartRateDao = new HealthHeartRateDao(clone4, this);
        this.healthHeartRateDao = healthHeartRateDao;
        HealthHeartRateItemDao healthHeartRateItemDao = new HealthHeartRateItemDao(clone5, this);
        this.healthHeartRateItemDao = healthHeartRateItemDao;
        HealthHeartRateSecondDao healthHeartRateSecondDao = new HealthHeartRateSecondDao(clone6, this);
        this.healthHeartRateSecondDao = healthHeartRateSecondDao;
        HealthPressureDao healthPressureDao = new HealthPressureDao(clone7, this);
        this.healthPressureDao = healthPressureDao;
        HealthPressureItemDao healthPressureItemDao = new HealthPressureItemDao(clone8, this);
        this.healthPressureItemDao = healthPressureItemDao;
        HealthSleepDao healthSleepDao = new HealthSleepDao(clone9, this);
        this.healthSleepDao = healthSleepDao;
        HealthSleepItemDao healthSleepItemDao = new HealthSleepItemDao(clone10, this);
        this.healthSleepItemDao = healthSleepItemDao;
        HealthSpO2Dao healthSpO2Dao = new HealthSpO2Dao(clone11, this);
        this.healthSpO2Dao = healthSpO2Dao;
        HealthSpO2ItemDao healthSpO2ItemDao = new HealthSpO2ItemDao(clone12, this);
        this.healthSpO2ItemDao = healthSpO2ItemDao;
        HealthSportDao healthSportDao = new HealthSportDao(clone13, this);
        this.healthSportDao = healthSportDao;
        HealthSportItemDao healthSportItemDao = new HealthSportItemDao(clone14, this);
        this.healthSportItemDao = healthSportItemDao;
        HealthSwimmingDao healthSwimmingDao = new HealthSwimmingDao(clone15, this);
        this.healthSwimmingDao = healthSwimmingDao;
        FailLogInfoDao failLogInfoDao = new FailLogInfoDao(clone16, this);
        this.failLogInfoDao = failLogInfoDao;
        HealthGpsDao healthGpsDao = new HealthGpsDao(clone17, this);
        this.healthGpsDao = healthGpsDao;
        HealthGpsItemDao healthGpsItemDao = new HealthGpsItemDao(clone18, this);
        this.healthGpsItemDao = healthGpsItemDao;
        registerDao(HealthActivity.class, healthActivityDao);
        registerDao(HealthBloodPressed.class, healthBloodPressedDao);
        registerDao(HealthBloodPressedItem.class, healthBloodPressedItemDao);
        registerDao(HealthHeartRate.class, healthHeartRateDao);
        registerDao(HealthHeartRateItem.class, healthHeartRateItemDao);
        registerDao(HealthHeartRateSecond.class, healthHeartRateSecondDao);
        registerDao(HealthPressure.class, healthPressureDao);
        registerDao(HealthPressureItem.class, healthPressureItemDao);
        registerDao(HealthSleep.class, healthSleepDao);
        registerDao(HealthSleepItem.class, healthSleepItemDao);
        registerDao(HealthSpO2.class, healthSpO2Dao);
        registerDao(HealthSpO2Item.class, healthSpO2ItemDao);
        registerDao(HealthSport.class, healthSportDao);
        registerDao(HealthSportItem.class, healthSportItemDao);
        registerDao(HealthSwimming.class, healthSwimmingDao);
        registerDao(c.class, failLogInfoDao);
        registerDao(HealthGps.class, healthGpsDao);
        registerDao(HealthGpsItem.class, healthGpsItemDao);
    }

    public void clear() {
        this.healthActivityDaoConfig.clearIdentityScope();
        this.healthBloodPressedDaoConfig.clearIdentityScope();
        this.healthBloodPressedItemDaoConfig.clearIdentityScope();
        this.healthHeartRateDaoConfig.clearIdentityScope();
        this.healthHeartRateItemDaoConfig.clearIdentityScope();
        this.healthHeartRateSecondDaoConfig.clearIdentityScope();
        this.healthPressureDaoConfig.clearIdentityScope();
        this.healthPressureItemDaoConfig.clearIdentityScope();
        this.healthSleepDaoConfig.clearIdentityScope();
        this.healthSleepItemDaoConfig.clearIdentityScope();
        this.healthSpO2DaoConfig.clearIdentityScope();
        this.healthSpO2ItemDaoConfig.clearIdentityScope();
        this.healthSportDaoConfig.clearIdentityScope();
        this.healthSportItemDaoConfig.clearIdentityScope();
        this.healthSwimmingDaoConfig.clearIdentityScope();
        this.failLogInfoDaoConfig.clearIdentityScope();
        this.healthGpsDaoConfig.clearIdentityScope();
        this.healthGpsItemDaoConfig.clearIdentityScope();
    }

    public FailLogInfoDao getFailLogInfoDao() {
        return this.failLogInfoDao;
    }

    public HealthActivityDao getHealthActivityDao() {
        return this.healthActivityDao;
    }

    public HealthBloodPressedDao getHealthBloodPressedDao() {
        return this.healthBloodPressedDao;
    }

    public HealthBloodPressedItemDao getHealthBloodPressedItemDao() {
        return this.healthBloodPressedItemDao;
    }

    public HealthGpsDao getHealthGpsDao() {
        return this.healthGpsDao;
    }

    public HealthGpsItemDao getHealthGpsItemDao() {
        return this.healthGpsItemDao;
    }

    public HealthHeartRateDao getHealthHeartRateDao() {
        return this.healthHeartRateDao;
    }

    public HealthHeartRateItemDao getHealthHeartRateItemDao() {
        return this.healthHeartRateItemDao;
    }

    public HealthHeartRateSecondDao getHealthHeartRateSecondDao() {
        return this.healthHeartRateSecondDao;
    }

    public HealthPressureDao getHealthPressureDao() {
        return this.healthPressureDao;
    }

    public HealthPressureItemDao getHealthPressureItemDao() {
        return this.healthPressureItemDao;
    }

    public HealthSleepDao getHealthSleepDao() {
        return this.healthSleepDao;
    }

    public HealthSleepItemDao getHealthSleepItemDao() {
        return this.healthSleepItemDao;
    }

    public HealthSpO2Dao getHealthSpO2Dao() {
        return this.healthSpO2Dao;
    }

    public HealthSpO2ItemDao getHealthSpO2ItemDao() {
        return this.healthSpO2ItemDao;
    }

    public HealthSportDao getHealthSportDao() {
        return this.healthSportDao;
    }

    public HealthSportItemDao getHealthSportItemDao() {
        return this.healthSportItemDao;
    }

    public HealthSwimmingDao getHealthSwimmingDao() {
        return this.healthSwimmingDao;
    }
}
