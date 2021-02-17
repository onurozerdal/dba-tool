package com.databasemanagement.tool.service;

import com.databasemanagement.tool.exception.ExceptionFactory;
import com.databasemanagement.tool.model.Columns;
import com.databasemanagement.tool.model.Tables;
import com.databasemanagement.tool.repository.ColumnRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ColumnService {

    private final ColumnRepository columnRepository;

    public ColumnService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @Transactional
    public Columns save(Columns columns) {
        if (StringUtils.isEmpty(columns.getName())) {
            ExceptionFactory.throwException("Name should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (columns.getTables() == null) {
            ExceptionFactory.throwException("TableId should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(columns.getTables().getId())) {
            ExceptionFactory.throwException("TableId should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (columns.getDataType() == null) {
            ExceptionFactory.throwException("DataType should not be empty!", HttpStatus.BAD_REQUEST);
        }
        checkDuplicateColumnName(columns.getName(), columns.getTables().getId());
        return columnRepository.saveOrUpdate(columns);
    }

    private void checkDuplicateColumnName(String name, String tableId) {
        boolean existName = columnRepository.checkDuplicateColumnName(name, tableId);
        if (existName) {
            ExceptionFactory.throwException("There is a column record with this name.", HttpStatus.CONFLICT);
        }
    }

    @Transactional
    public Columns update(Columns columns) {
        if (StringUtils.isEmpty(columns.getId())) {
            ExceptionFactory.throwException("Id should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(columns.getName())) {
            ExceptionFactory.throwException("Name should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (columns.getTables() == null) {
            ExceptionFactory.throwException("TableId should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(columns.getTables().getId())) {
            ExceptionFactory.throwException("TableId should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (columns.getDataType() == null) {
            ExceptionFactory.throwException("DataType should not be empty!", HttpStatus.BAD_REQUEST);
        }

        Columns existColumn = columnRepository.get(columns.getId(), Columns.class);
        if (existColumn == null) {
            ExceptionFactory.throwException("Column not found.", HttpStatus.NOT_FOUND);
        }

        if (!existColumn.getName().equals(columns.getName())) {
            checkDuplicateColumnName(columns.getName(), columns.getTables().getId());
        }

        return columnRepository.saveOrUpdate(columns);
    }

    public Columns get(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionFactory.throwException("Id should not be empty!", HttpStatus.BAD_REQUEST);
        }
        return columnRepository.get(id, Columns.class);
    }

    @Transactional
    public boolean delete(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionFactory.throwException("Id should not be empty!", HttpStatus.BAD_REQUEST);
        }
        Columns columns = columnRepository.get(id, Columns.class);
        if (columns == null) {
            ExceptionFactory.throwException("Column not found.", HttpStatus.NOT_FOUND);
        }
        columnRepository.hardDelete(columns);
        return true;
    }

    public List<Columns> list(Columns columns) {
        if (columns.getTables() == null) {
            ExceptionFactory.throwException("TableId should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(columns.getTables().getId())) {
            ExceptionFactory.throwException("TableId should not be empty!", HttpStatus.BAD_REQUEST);
        }
        return columnRepository.list(columns);
    }
}
