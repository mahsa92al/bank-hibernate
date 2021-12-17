package ir.maktab.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Mahsa Alikhani m-58
 */
public class BaseDao {
    static SessionFactory sessionFactory = new Configuration()
            .configure("ir/maktab/hibernateConfig/hibernate.cfg.xml").buildSessionFactory();
}
