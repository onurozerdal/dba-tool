package com.databasemanagement.tool.repository;

import com.databasemanagement.tool.model.Columns;
import com.databasemanagement.tool.model.Tables;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ColumnRepository extends BaseRepository {

    public List<Columns> list(Columns columns) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select n from Columns n where n.tables.id =:tableId");

        Query query = entityManager.createQuery(hql.toString());
        query.setParameter("tableId", columns.getTables().getId());
        return query.getResultList();
    }

    public boolean checkDuplicateColumnName(String name, String tableId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" select n from Columns n where n.tables.id =:tableId and n.name =:name");

        Query query = entityManager.createQuery(hql.toString());
        query.setParameter("tableId", tableId);
        query.setParameter("name", name);
        return query.getResultList().size() > 0;
    }
}
