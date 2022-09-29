import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Route, Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  id: number | undefined;

  constructor(private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id!).subscribe(data => {
      this.employee = data;
    }, error => console.log(error)
    );
  }



  onSubmit() {
    this.employeeService.updateEmployee(this.id!, this.employee).subscribe(data => {
      this.goToEmployeeLits();
    },
      error => console.log(error)
      
    );
  }
  goToEmployeeLits() {
    this.router.navigate(['employees']);
  }

}

