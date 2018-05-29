package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.OldProduct;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("dao")
@Transactional
public class OldProductDaoImpl extends AbstractDao<String, OldProduct> {

    public List<OldProduct> findAll()
    {
        Criteria criteria = createEntityCriteria();

        return criteria.list();
    }
}
