import {Component, OnInit} from '@angular/core';
import {TableService} from "../../services/table.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'table-create',
  templateUrl: './table-create.component.html',
  styleUrls: ['./table-create.component.css']
})
export class TableCreateComponent implements OnInit {
  table = {
    name: '',
    description: ''
  };

  constructor(private tableService: TableService,
              private router: Router,
              private toastrService:ToastrService) {
  }

  ngOnInit(): void {
  }

  saveTable(): void {
    if (!this.table.name || this.table.name == '') {
      this.toastrService.error("Name should not be empty!");
      return;
    }
    const data = {
      name: this.table.name,
      description: this.table.description
    };

    this.tableService.create(data)
      .subscribe(
        response => {
          this.toastrService.success("Successfully created.");
          this.router.navigate(['/tables']);
        },
        error => {
          this.toastrService.error(error.error.message);
        });
  }

}
