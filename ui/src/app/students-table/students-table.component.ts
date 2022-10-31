import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Student } from '../model/student';

@Component({
  selector: 'app-students-table',
  templateUrl: './students-table.component.html',
  styleUrls: ['./students-table.component.css']
})
export class StudentsTableComponent implements OnInit {

    constructor(public appService: AppService) {}

    ngOnInit(): void {
    }

    onDeleteStudent(id: number) {
        console.log(`studentsTable.onDeleteStudent(${id})`);
        this.appService.deleteStudent(id);
    }

}
