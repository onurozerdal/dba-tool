import {Component, OnInit} from '@angular/core';
import {TableService} from "../../services/table.service";
import {Router} from "@angular/router";
import {TableModel} from "../../model/TableModel";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent implements OnInit {

  tables: TableModel[];
  currentTable = null;
  currentIndex = -1;
  name = '';

  constructor(private tableService: TableService,
              private router: Router,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.tableService.getAll()
      .subscribe(
        data => {
          this.tables = data;
        },
        error => {
        });
  }

  refreshList(): void {
    this.retrieveTables();
    this.currentTable = null;
    this.currentIndex = -1;
  }

  setActiveTables(table, index): void {
    this.currentTable = table;
    this.currentIndex = index;
  }

  searchName(): void {
    this.tableService.findByName(this.name)
      .subscribe(
        data => {
          this.tables = data;
        },
        error => {
        });
  }

  deleteTable(): void {
    this.tableService.delete(this.currentTable.id)
      .subscribe(
        response => {
          this.router.navigate(['/tables']);
          this.toastrService.success("Succesfully deleted");
          this.refreshList();
        },
        error => {
          this.toastrService.error(error.error.message);
        });
  }
}
