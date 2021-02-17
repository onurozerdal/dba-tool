import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const baseUrl = 'http://localhost:8080/table/';

@Injectable({
  providedIn: 'root'
})
export class TableService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.post(`${baseUrl}list`, {});
  }

  get(id): Observable<any> {
    return this.http.get(`${baseUrl}get?id=${id}`);
  }

  create(data): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(data): Observable<any> {
    return this.http.put(baseUrl, data);
  }

  delete(id): Observable<any> {
    return this.http.delete(`${baseUrl}delete?id=${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByName(name): Observable<any> {
    return this.http.post(`${baseUrl}list`, {name: name});
  }
}
