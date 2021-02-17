import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TableCreateComponent} from "./components/table-create/table-create.component";
import {TableDetailsComponent} from "./components/table-detail/table-details.component";
import {ColumnCreateComponent} from "./components/column-create/column-create.component";
import {ColumnDetailsComponent} from "./components/column-detail/column-details.component";
import {TableListComponent} from "./components/table-list/table-list.component";
import {ColumnListComponent} from "./components/column-list/column-list.component";
import { ToastrModule } from "ngx-toastr";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { dataTypes } from './model/DataTypes';

@NgModule({
  declarations: [
    AppComponent,
    TableCreateComponent,
    TableDetailsComponent,
    TableListComponent,
    ColumnCreateComponent,
    ColumnDetailsComponent,
    ColumnListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [{ provide: 'DataTypes', useValue: dataTypes }  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
