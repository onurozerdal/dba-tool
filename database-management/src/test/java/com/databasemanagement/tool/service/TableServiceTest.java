package com.databasemanagement.tool.service;

import com.databasemanagement.tool.model.Tables;
import com.databasemanagement.tool.repository.TableRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    private TableService tableService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Tables createTable() {
        Tables tables = new Tables();
        tables.setName("tableName");
        tables.setDescription("desc");
        return tables;
    }

    @Test
    public void save_shouldBeError_NameIsNull() {
        expectedException.expect(Exception.class);
        tableService.save(new Tables());
    }

    @Test
    public void save_shouldBeError_DuplicateName() {
        Tables tables = createTable();
        expectedException.expect(Exception.class);
        doReturn(true).when(tableRepository).checkDuplicateName(tables.getName());
        tableService.save(tables);
    }

    @Test
    public void save_shouldBeSuccess() {
        Tables tables = createTable();
        doReturn(false).when(tableRepository).checkDuplicateName(anyString());
        doReturn(tables).when(tableRepository).saveOrUpdate(any());
        Tables result = tableService.save(tables);
        assertEquals(tables.getName(), result.getName());
    }

    @Test
    public void update_shouldBeError_IdIsNull() {
        Tables tables = createTable();
        tables.setId(null);
        expectedException.expect(Exception.class);
        tableService.update(tables);
    }

    @Test
    public void update_shouldBeError_NameIsNull() {
        Tables tables = createTable();
        tables.setId("1");
        tables.setName(null);
        expectedException.expect(Exception.class);
        tableService.update(tables);
    }

    @Test
    public void update_shouldBeError_NotFound() {
        Tables tables = createTable();
        tables.setId("1");
        expectedException.expect(Exception.class);
        doReturn(null).when(tableRepository).get(anyString(), any());
        tableService.update(tables);
    }

    @Test
    public void update_shouldBeError_DuplicateName() {
        Tables tables = createTable();
        tables.setId("1");

        Tables tables2 = createTable();
        tables2.setId("2");
        tables2.setName("name2");
        expectedException.expect(Exception.class);
        doReturn(tables2).when(tableRepository).get(anyString(), any());
        doReturn(true).when(tableRepository).checkDuplicateName(anyString());
        tableService.update(tables);
    }

    @Test
    public void update_shouldBeSuccess() {
        Tables tables = createTable();
        tables.setId("1");

        Tables tables2 = createTable();
        tables2.setId("2");
        tables2.setName("name2");
        doReturn(tables2).when(tableRepository).get(anyString(), any());
        doReturn(false).when(tableRepository).checkDuplicateName(anyString());
        doReturn(tables).when(tableRepository).saveOrUpdate(any());
        Tables result = tableService.update(tables);
        assertEquals(tables.getName(), result.getName());
    }

    @Test
    public void get_shouldBeError_IdNotFound() {
        expectedException.expect(Exception.class);
        tableService.get(null);
    }

    @Test
    public void get_shouldBeSuccess() {
        Tables tables = createTable();
        tables.setId("1");
        doReturn(tables).when(tableRepository).get(anyString(), any());
        Tables result = tableService.get(tables.getId());
        assertEquals(tables.getName(), result.getName());
    }

    @Test
    public void delete_shouldBeError_IdNotFound() {
        expectedException.expect(Exception.class);
        tableService.delete(null);
    }

    @Test
    public void delete_shouldBeError_NotFound() {
        Tables tables = createTable();
        tables.setId("1");
        expectedException.expect(Exception.class);
        doReturn(null).when(tableRepository).get(anyString(), any());
        tableService.delete(tables.getId());
    }

    @Test
    public void delete_shouldBeSuccess() {
        Tables tables = createTable();
        tables.setId("1");
        doReturn(tables).when(tableRepository).get(anyString(), any());
        boolean result = tableService.delete(tables.getId());
        assertTrue(result);
    }

    @Test
    public void list_shouldBeSuccess() {
        Tables tables = createTable();
        tables.setId("1");
        doReturn(new ArrayList<>()).when(tableRepository).list(any());
        List<Tables> result = tableService.list(tables);
        assertNotNull(result);
    }
}