import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TableService} from "../../services/table.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'table-details',
  templateUrl: './table-details.component.html',
  styleUrls: ['./table-details.component.css']
})
export class TableDetailsComponent implements OnInit {
  currentTable = null;

  constructor(
    private tableService: TableService,
    private route: ActivatedRoute,
    private toastrService: ToastrService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.getTable(this.route.snapshot.paramMap.get('id'));
  }

  getTable(id): void {
    this.tableService.get(id)
      .subscribe(
        data => {
          this.currentTable = data;
        },
        error => {
        });
  }

  updateTable(): void {
    this.tableService.update(this.currentTable)
      .subscribe(
        response => {
          this.toastrService.success("Successfully updated.");
          this.router.navigate(['/tables']);
        },
        error => {
          this.toastrService.error(error.error.message);
        });
  }
}
