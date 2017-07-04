package dbmanager.services;

import dbmanager.MongoDBAsynManager;
import dbmanager.MongodDBManager;

/**
 * Created by cpu11118-local on 04/07/2017.
 */
public abstract class BaseService {
    protected MongodDBManager mongodDBManager;
    private MongoDBAsynManager mongoDBAsynManager;

    public MongodDBManager getMongodDBManager() {
        return mongodDBManager;
    }

    public MongoDBAsynManager getMongoDBAsynManager() {
        return mongoDBAsynManager;
    }

    public BaseService() {
        mongoDBAsynManager = MongoDBAsynManager.newInstance();
        mongodDBManager = MongodDBManager.newInstance();
    }
}
