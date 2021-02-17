import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TableCreateComponent} from "./components/table-create/table-create.component";
import {TableDetailsComponent} from "./components/table-detail/table-details.component";
import {TableListComponent} from "./components/table-list/table-list.component";
import {ColumnCreateComponent} from "./components/column-create/column-create.component";
import {ColumnDetailsComponent} from "./components/column-detail/column-details.component";
import {ColumnListComponent} from "./components/column-list/column-list.component";

const routes: Routes = [
  {path: '', redirectTo: 'tables', pathMatch: 'full'},
  {path: 'tables', component: TableListComponent},
  {path: 'tables/:id', component: TableDetailsComponent},
  {path: 'addTable', component: TableCreateComponent},
  {path: 'addColumn', component: ColumnCreateComponent},
  {path: 'columns/:id', component: ColumnDetailsComponent},
  {path: 'columns', component: ColumnListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
