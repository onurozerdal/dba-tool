import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ColumnService} from "../../services/column.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'column-details',
  templateUrl: './column-details.component.html',
  styleUrls: ['./column-details.component.css']
})
export class ColumnDetailsComponent implements OnInit {
  currentColumn = null;

  tablesFormGroup = new FormGroup({
    id: new FormControl('', Validators.required)
  });

  form = new FormGroup({
    id: new FormControl('', Validators.required),
    name: new FormControl('', Validators.required),
    dataType: new FormControl('', Validators.required),
    tag: new FormControl('', Validators.required),
    tables: this.tablesFormGroup
  });

  constructor(
    private columnService: ColumnService,
    @Inject('DataTypes') public dataTypes: any[],
    private route: ActivatedRoute,
    private toastrService: ToastrService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.getColumn(this.route.snapshot.paramMap.get('id'));
  }

  getColumn(id): void {
    this.columnService.get(id)
      .subscribe(
        data => {
          this.currentColumn = data;
          this.form.patchValue(data);
        },
        error => {
          this.toastrService.error("There is a problem.");
        });
  }

  updateColumn(): void {
    this.columnService.update(this.form.value)
      .subscribe(
        response => {
          this.toastrService.success("Successfully updated.");
          this.router.navigate(['/columns']);
        },
        error => {
          this.toastrService.error(error.error.message);
        });
  }
}
