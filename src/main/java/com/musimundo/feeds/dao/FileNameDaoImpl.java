package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.FileName;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("fileName")
public class FileNameDaoImpl extends AbstractDao<Integer, FileName> implements FileNameDao {


    @Override
    public FileName findById(int id) {
        return getByKey(id);
    }

    @Override
    public FileName findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        FileName res = (FileName) criteria.uniqueResult();
        return res;
    }

    @Override
    public void save(FileName fileName) {
        persist(fileName);
    }
}
