package com.databasemanagement.tool.service;

import com.databasemanagement.tool.exception.ExceptionFactory;
import com.databasemanagement.tool.model.Tables;
import com.databasemanagement.tool.repository.TableRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Transactional
    public Tables save(Tables tables) {
        if (StringUtils.isEmpty(tables.getName())) {
            ExceptionFactory.throwException("Name should not be empty!", HttpStatus.BAD_REQUEST);
        }

        checkDuplicateName(tables.getName());

        return tableRepository.saveOrUpdate(tables);
    }

    private void checkDuplicateName(String name) {
        boolean existName = tableRepository.checkDuplicateName(name);
        if (existName) {
            ExceptionFactory.throwException("There is a table record with this name.", HttpStatus.CONFLICT);
        }
    }

    @Transactional
    public Tables update(Tables tables) {
        if (StringUtils.isEmpty(tables.getId())) {
            ExceptionFactory.throwException("Id should not be empty!", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(tables.getName())) {
            ExceptionFactory.throwException("Name should not be empty!", HttpStatus.BAD_REQUEST);
        }

        Tables existTable = tableRepository.get(tables.getId(), Tables.class);
        if (existTable == null) {
            ExceptionFactory.throwException("Table not found", HttpStatus.NOT_FOUND);
        }

        if (!existTable.getName().equals(tables.getName())) {
            checkDuplicateName(tables.getName());
        }

        return tableRepository.saveOrUpdate(tables);
    }

    public Tables get(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionFactory.throwException("Id should not be empty!", HttpStatus.BAD_REQUEST);
        }
        return tableRepository.get(id, Tables.class);
    }

    @Transactional
    public boolean delete(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionFactory.throwException("Id should not be empty!", HttpStatus.BAD_REQUEST);
        }
        Tables tables = tableRepository.get(id, Tables.class);
        if (tables == null) {
            ExceptionFactory.throwException("Table not found.", HttpStatus.NOT_FOUND);
        }
        tableRepository.hardDelete(tables);
        return true;
    }

    public List<Tables> list(Tables tables) {
        return tableRepository.list(tables);
    }
}
