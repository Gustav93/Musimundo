package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.OldPrice;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("oldPriceDao")
@Transactional
public class OldPriceDaoImpl extends AbstractDao<String, OldPrice> {

    public List<OldPrice> findAll()
    {
        Criteria criteria = createEntityCriteria();

        return criteria.list();
    }
}
