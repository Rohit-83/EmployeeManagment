import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8100/emp/allEmployee"
  private createURL = "http://localhost:8100/emp/addEmployee"
  private byIdURL = "http://localhost:8100/emp/getById"
  private updateURL = "http://localhost:8100/emp/updateEmployee"
  private deleteURL = "http://localhost:8100/emp/deleteEmployee"
  constructor(private httpClient: HttpClient) { }

  getEmployeeList(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseURL}`);
  }

  createEmployee(employee: Employee): Observable<any> {
    return this.httpClient.post(`${this.createURL}`, employee);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(`${this.byIdURL}/${id}`);
  }

  updateEmployee(id: number, employee: Employee): Observable<Object> {
    return this.httpClient.put(`${this.updateURL}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.deleteURL}/${id}`);
  }
}
