import {Component, Inject, OnInit} from '@angular/core';
import {ColumnService} from "../../services/column.service";
import {TableModel} from "../../model/TableModel";
import {TableService} from "../../services/table.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'column-create',
  templateUrl: './column-create.component.html',
  styleUrls: ['./column-create.component.css']
})
export class ColumnCreateComponent implements OnInit {
  column = {
    name: '',
    tag: '',
    dataType: '',
    tableId: ''
  };
  tables: TableModel[];
  form = new FormGroup({
    name: new FormControl('', Validators.required),
    tag: new FormControl('', Validators.required),
    dataType: new FormControl('', Validators.required),
    tableId: new FormControl('', Validators.required)
  });

  constructor(private columnService: ColumnService,
              @Inject('DataTypes') public dataTypes: any[],
              private tableService: TableService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.tableService.getAll().subscribe(data => {
      this.tables = data;
    });
  }

  saveColumn(): void {
    if (this.form.value.name === undefined || this.form.value.name === '') {
      this.toastrService.error("Name should not be empty.");
      return;
    }
    if (this.form.value.dataType === undefined || this.form.value.dataType === '') {
      this.toastrService.error("Data Type should not be empty.");
      return;
    }
    if (this.form.value.tableId === undefined || this.form.value.tableId === '') {
      this.toastrService.error("TableId should not be empty.");
      return;
    }
    const data = {
      name: this.form.value.name,
      tag: this.form.value.tag,
      dataType: this.form.value.dataType,
      tables: {
        id: this.form.value.tableId
      }
    };

    this.columnService.create(data)
      .subscribe(
        response => {
          this.toastrService.success("Successfully created.");
          this.router.navigate(['/columns']);
        },
        error => {
          this.toastrService.error(error.error.message);
        });
  }

}
