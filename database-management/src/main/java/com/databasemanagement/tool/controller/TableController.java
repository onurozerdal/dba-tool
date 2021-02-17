package com.databasemanagement.tool.controller;

import com.databasemanagement.tool.model.Tables;
import com.databasemanagement.tool.service.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/")
    public ResponseEntity<Tables> saveTable(@RequestBody Tables tables) {
        return ResponseEntity.ok(tableService.save(tables));
    }

    @PutMapping("/")
    public ResponseEntity<Tables> updateTable(@RequestBody Tables tables) {
        return ResponseEntity.ok(tableService.update(tables));
    }

    @GetMapping("/get")
    public ResponseEntity<Tables> getTable(String id) {
        return ResponseEntity.ok(tableService.get(id));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<Tables>> list(@RequestBody Tables tables) {
        return ResponseEntity.ok(tableService.list(tables));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteTable(String id) {
        return ResponseEntity.ok(tableService.delete(id));
    }
}
