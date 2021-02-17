package com.databasemanagement.tool.service;

import com.databasemanagement.tool.BaseIntegration;
import com.databasemanagement.tool.model.Tables;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TableServiceIT extends BaseIntegration {

    @Autowired
    private TableService tableService;

    private Tables createTable() {
        Tables tables = new Tables();
        tables.setName("tableName");
        tables.setDescription("desc");
        return tables;
    }

    @Test
    public void save() {
        Tables table = createTable();

        Tables savedTable = tableService.save(table);
        assertNotNull(savedTable.getId());
        assertNotNull(savedTable.getName());
    }

    @Test
    public void get() {
        Tables table = createTable();
        Tables savedTable = tableService.save(table);

        Tables result = tableService.get(savedTable.getId());

        assertEquals(savedTable.getId(), result.getId());
        assertEquals(savedTable.getName(), result.getName());
    }

    @Test
    public void list() {
        Tables table = createTable();
        Tables savedTable = tableService.save(table);

        List<Tables> result = tableService.list(savedTable);

        assertNotNull(result);
        AtomicBoolean checkExist = new AtomicBoolean(false);
        result.forEach(item -> {
            if (item.getId().equals(savedTable.getId())) {
                checkExist.set(true);
            }
        });
        assertTrue(checkExist.get());
    }

    @Test
    public void delete() {
        Tables table = createTable();
        Tables savedTable = tableService.save(table);

        boolean result = tableService.delete(savedTable.getId());

        assertTrue(result);
    }
}