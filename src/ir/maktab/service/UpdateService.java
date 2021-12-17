package ir.maktab.service;

import ir.maktab.dao.UpdateDao;
import ir.maktab.model.Updates;

/**
 * @author Mahsa Alikhani m-58
 */
public class UpdateService {
    UpdateDao updateDao = new UpdateDao();
    public void addNewUpdate(Updates type) {
        updateDao.saveNewUpdate(type);
    }
}
