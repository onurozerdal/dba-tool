package com.databasemanagement.tool.repository;

import com.databasemanagement.tool.model.Tables;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TableRepository extends BaseRepository {

    public List<Tables> list(Tables tables) {
        String name = tables.getName();
        String description = tables.getDescription();

        StringBuilder hql = new StringBuilder();
        hql.append(" select n from Tables n where 1=1");

        if (StringUtils.isNotEmpty(name)) {
            hql.append(" and lower(n.name) like :name");
        }

        if (StringUtils.isNotEmpty(description)) {
            hql.append(" and lower(n.description) like :description");
        }

        Query query = entityManager.createQuery(hql.toString());
        if (StringUtils.isNotEmpty(name)) {
            query.setParameter("name", "%" + name.toLowerCase() + "%");
        }
        if (StringUtils.isNotEmpty(description)) {
            query.setParameter("description", "%" + description.toLowerCase() + "%");
        }
        return query.getResultList();
    }

    public boolean checkDuplicateName(String name) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select n from Tables n where lower(n.name) like :name");

        Query query = entityManager.createQuery(hql.toString());
        query.setParameter("name", "%" + name.toLowerCase() + "%");
        return query.getResultList().size() > 0;
    }
}
