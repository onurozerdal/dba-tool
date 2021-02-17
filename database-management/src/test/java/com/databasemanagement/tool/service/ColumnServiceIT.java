package com.databasemanagement.tool.service;

import com.databasemanagement.tool.BaseIntegration;
import com.databasemanagement.tool.model.Columns;
import com.databasemanagement.tool.model.Tables;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ColumnServiceIT extends BaseIntegration {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private TableService tableService;

    private Columns createColumn() {
        Columns columns = new Columns();
        columns.setName("tableName");
        columns.setTag("desc");
        columns.setDataType(0);
        columns.setTables(createTable());
        return columns;
    }

    private Tables createTable() {
        Tables tables = new Tables();
        tables.setName("tableName");
        tables.setDescription("desc");
        return tableService.save(tables);
    }

    @Test
    public void save() {
        Columns column = createColumn();

        Columns savedColumn = columnService.save(column);
        assertNotNull(savedColumn.getId());
        assertNotNull(savedColumn.getName());
    }

    @Test
    public void get() {
        Columns column = createColumn();
        Columns savedColumn = columnService.save(column);

        Columns result = columnService.get(savedColumn.getId());

        assertEquals(savedColumn.getId(), result.getId());
        assertEquals(savedColumn.getName(), result.getName());
    }

    @Test
    public void list() {
        Columns column = createColumn();
        Columns savedColumn = columnService.save(column);

        List<Columns> result = columnService.list(savedColumn);

        assertNotNull(result);
        AtomicBoolean checkExist = new AtomicBoolean(false);
        result.forEach(item -> {
            if (item.getId().equals(savedColumn.getId())) {
                checkExist.set(true);
            }
        });
        assertTrue(checkExist.get());
    }

    @Test
    public void delete() {
        Columns column = createColumn();
        Columns savedColumn = columnService.save(column);

        boolean result = columnService.delete(savedColumn.getId());

        assertTrue(result);
    }
}