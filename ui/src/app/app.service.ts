import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Student } from './model/student';

@Injectable({
    providedIn: 'root'
})
export class AppService {

    students: Student[] = [];

    constructor(private httpClient: HttpClient) {
        this.reload();
    }

    getStudents(): Observable<Student[]> {
        return this.httpClient.get<Student[]>('/api/students', {
            observe: 'body',
            responseType: 'json'
        });
    }

    reload() {
        this.getStudents().subscribe(data => this.students = data);
    }

    addStudent(newStudent: Student) {
        this.httpClient.post('/api/students', newStudent).subscribe(() => this.reload());
    }

    deleteStudent(id: number) {
        this.httpClient.delete(`/api/students/${id}`).subscribe(() => this.reload());
    }

    logout() {
        this.httpClient.post('/logout', {}).subscribe();
    }

}
