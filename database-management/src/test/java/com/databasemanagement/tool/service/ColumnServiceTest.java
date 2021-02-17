package com.databasemanagement.tool.service;

import com.databasemanagement.tool.model.Columns;
import com.databasemanagement.tool.model.Tables;
import com.databasemanagement.tool.repository.ColumnRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ColumnServiceTest {

    @Mock
    private ColumnRepository columnsRepository;

    @InjectMocks
    private ColumnService columnservice;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Columns createColumn() {
        Columns columns = new Columns();
        columns.setName("columnName");
        columns.setTag("desc");
        columns.setDataType(0);
        columns.setTables(createTable());
        return columns;
    }

    private Tables createTable() {
        Tables tables = new Tables();
        tables.setId("id");
        tables.setName("tableName");
        tables.setDescription("desc");
        return tables;
    }

    @Test
    public void save_shouldBeError_NameIsNull() {
        expectedException.expect(Exception.class);
        columnservice.save(new Columns());
    }

    @Test
    public void save_shouldBeError_TableIsNull() {
        Columns columns = createColumn();
        columns.setTables(null);
        expectedException.expect(Exception.class);
        columnservice.save(columns);
    }

    @Test
    public void save_shouldBeError_TableIdIsNull() {
        Columns columns = createColumn();
        columns.getTables().setId(null);
        expectedException.expect(Exception.class);
        columnservice.save(columns);
    }

    @Test
    public void save_shouldBeError_DataTypeIsNull() {
        Columns columns = createColumn();
        columns.setDataType(null);
        expectedException.expect(Exception.class);
        columnservice.save(columns);
    }

    @Test
    public void save_shouldBeError_DuplicateName() {
        Columns columns = createColumn();
        expectedException.expect(Exception.class);
        doReturn(true).when(columnsRepository).checkDuplicateColumnName(columns.getName(), columns.getTables().getId());
        columnservice.save(columns);
    }

    @Test
    public void save_shouldBeSuccess() {
        Columns columns = createColumn();
        doReturn(false).when(columnsRepository).checkDuplicateColumnName(anyString(), anyString());
        doReturn(columns).when(columnsRepository).saveOrUpdate(any());
        Columns result = columnservice.save(columns);
        assertEquals(columns.getName(), result.getName());
    }

    @Test
    public void update_shouldBeError_IdIsNull() {
        Columns columns = createColumn();
        columns.setId(null);
        expectedException.expect(Exception.class);
        columnservice.update(columns);
    }

    @Test
    public void update_shouldBeError_NameIsNull() {
        Columns columns = createColumn();
        columns.setId("1");
        columns.setName(null);
        expectedException.expect(Exception.class);
        columnservice.update(columns);
    }

    @Test
    public void update_shouldBeError_TableIsNull() {
        Columns columns = createColumn();
        columns.setId("1");
        columns.setTables(null);
        expectedException.expect(Exception.class);
        columnservice.update(columns);
    }

    @Test
    public void update_shouldBeError_TableIdIsNull() {
        Columns columns = createColumn();
        columns.setId("1");
        columns.getTables().setId(null);
        expectedException.expect(Exception.class);
        columnservice.update(columns);
    }

    @Test
    public void update_shouldBeError_DataTypeIsNull() {
        Columns columns = createColumn();
        columns.setId("1");
        columns.setDataType(null);
        expectedException.expect(Exception.class);
        columnservice.update(columns);
    }

    @Test
    public void update_shouldBeError_NotFound() {
        Columns columns = createColumn();
        columns.setId("1");
        expectedException.expect(Exception.class);
        doReturn(null).when(columnsRepository).get(anyString(), any());
        columnservice.update(columns);
    }

    @Test
    public void update_shouldBeError_DuplicateName() {
        Columns columns = createColumn();
        columns.setId("1");

        Columns columns2 = createColumn();
        columns2.setId("2");
        columns2.setName("name2");
        expectedException.expect(Exception.class);
        doReturn(columns2).when(columnsRepository).get(anyString(), any());
        doReturn(true).when(columnsRepository).checkDuplicateColumnName(anyString(), anyString());
        columnservice.update(columns);
    }

    @Test
    public void update_shouldBeSuccess() {
        Columns columns = createColumn();
        columns.setId("1");

        Columns columns2 = createColumn();
        columns2.setId("2");
        columns2.setName("name2");
        doReturn(columns2).when(columnsRepository).get(anyString(), any());
        doReturn(false).when(columnsRepository).checkDuplicateColumnName(anyString(), anyString());
        doReturn(columns).when(columnsRepository).saveOrUpdate(any());
        Columns result = columnservice.update(columns);
        assertEquals(columns.getName(), result.getName());
    }

    @Test
    public void get_shouldBeError_IdNotFound() {
        expectedException.expect(Exception.class);
        columnservice.get(null);
    }

    @Test
    public void get_shouldBeSuccess() {
        Columns columns = createColumn();
        columns.setId("1");
        doReturn(columns).when(columnsRepository).get(anyString(), any());
        Columns result = columnservice.get(columns.getId());
        assertEquals(columns.getName(), result.getName());
    }

    @Test
    public void delete_shouldBeError_IdNotFound() {
        expectedException.expect(Exception.class);
        columnservice.delete(null);
    }

    @Test
    public void delete_shouldBeError_NotFound() {
        Columns columns = createColumn();
        columns.setId("1");
        expectedException.expect(Exception.class);
        doReturn(null).when(columnsRepository).get(anyString(), any());
        columnservice.delete(columns.getId());
    }

    @Test
    public void delete_shouldBeSuccess() {
        Columns columns = createColumn();
        columns.setId("1");
        doReturn(columns).when(columnsRepository).get(anyString(), any());
        boolean result = columnservice.delete(columns.getId());
        assertTrue(result);
    }

    @Test
    public void list_shouldBeSuccess() {
        Columns columns = createColumn();
        columns.setId("1");
        doReturn(new ArrayList<>()).when(columnsRepository).list(any());
        List<Columns> result = columnservice.list(columns);
        assertNotNull(result);
    }

    @Test
    public void list_shouldBeError_TableIsNull() {
        Columns columns = createColumn();
        columns.setTables(null);
        expectedException.expect(Exception.class);
        columnservice.list(columns);
    }

    @Test
    public void list_shouldBeError_TableIdIsNull() {
        Columns columns = createColumn();
        columns.getTables().setId(null);
        expectedException.expect(Exception.class);
        columnservice.list(columns);
    }
}