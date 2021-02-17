import {Component, Inject, OnInit} from '@angular/core';
import {ColumnService} from "../../services/column.service";
import {Router} from "@angular/router";
import {TableService} from "../../services/table.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TableModel} from "../../model/TableModel";
import {ToastrService} from "ngx-toastr";
import {ColumnModel} from "../../model/ColumnModel";

@Component({
  selector: 'column-list',
  templateUrl: './column-list.component.html',
  styleUrls: ['./column-list.component.css']
})
export class ColumnListComponent implements OnInit {

  columns: any;
  currentColumn = null;
  currentIndex = -1;
  name = '';
  tables: TableModel[];
  table: TableModel;
  form = new FormGroup({
    tableId: new FormControl('', Validators.required)
  });

  constructor(private columnService: ColumnService,
              @Inject('DataTypes') public dataTypes: any[],
              private tableService: TableService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    // this.retrieveColumns();
    this.tableService.getAll().subscribe(data => {
      this.tables = data;
    });
  }

  refreshList(): void {
    this.getColumnsByTableId();
    this.currentColumn = null;
    this.currentIndex = -1;
  }

  setActiveColumns(column, index): void {
    this.currentColumn = column;
    this.currentIndex = index;
  }

  deleteColumn(): void {
    this.columnService.delete(this.currentColumn.id)
      .subscribe(
        response => {
          this.router.navigate(['/columns']);
          this.toastrService.success("Successfully deleted.");
          this.refreshList();
        },
        error => {
          this.toastrService.error(error.error.message);
        });
  }

  getColumnsByTableId() {
    this.columnService.findByTableId(this.form.value.tableId).subscribe(
      data => {
        for (let item of data as ColumnModel[]) {
          for (let dataType of this.dataTypes) {
            if (dataType.value === item.dataType) {
              item.dataTypeText = dataType.key;
            }
          }
        }
        this.columns = data;
      },
      error => {
        this.toastrService.error(error.error.message);
      });
  }
}
