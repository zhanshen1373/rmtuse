package com.hayden.hap.common.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hayden.hap.common.dao.dBEntity.LoginHistory;

import com.hayden.hap.common.dao.LoginHistoryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig loginHistoryDaoConfig;

    private final LoginHistoryDao loginHistoryDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        loginHistoryDaoConfig = daoConfigMap.get(LoginHistoryDao.class).clone();
        loginHistoryDaoConfig.initIdentityScope(type);

        loginHistoryDao = new LoginHistoryDao(loginHistoryDaoConfig, this);

        registerDao(LoginHistory.class, loginHistoryDao);
    }
    
    public void clear() {
        loginHistoryDaoConfig.clearIdentityScope();
    }

    public LoginHistoryDao getLoginHistoryDao() {
        return loginHistoryDao;
    }

}
