package com.databasemanagement.tool.controller;

import com.databasemanagement.tool.model.Columns;
import com.databasemanagement.tool.model.Tables;
import com.databasemanagement.tool.service.ColumnService;
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
@RequestMapping("/column")
public class ColumnController {

    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PostMapping("/")
    public ResponseEntity<Columns> saveColumn(@RequestBody Columns columns) {
        return ResponseEntity.ok(columnService.save(columns));
    }

    @PutMapping("/")
    public ResponseEntity<Columns> updateColumn(@RequestBody Columns columns) {
        return ResponseEntity.ok(columnService.update(columns));
    }

    @GetMapping("/get")
    public ResponseEntity<Columns> getColumn(String id) {
        return ResponseEntity.ok(columnService.get(id));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<Columns>> list(@RequestBody Columns columns) {
        return ResponseEntity.ok(columnService.list(columns));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteColumn(String id) {
        return ResponseEntity.ok(columnService.delete(id));
    }
}
