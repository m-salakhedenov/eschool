import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

    firstName = '';
    lastName = '';
    email = '';
    dateOfBirth = '';

    constructor(private appService: AppService) { }

    ngOnInit(): void {
    }

    canAdd() {
        return this.firstName.trim() && this.lastName.trim() && this.email.trim() && this.dateOfBirth.trim();
    }

    onAddStudent() {
        console.log(this.firstName, this.lastName, this.email, this.dateOfBirth);
        this.appService.addStudent({
            id: 0,
            firstName: this.firstName,
            lastName: this.lastName,
            email: this.email,
            dateOfBirth: this.dateOfBirth
        });
    }

}
