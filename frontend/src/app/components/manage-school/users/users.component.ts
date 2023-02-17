import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {SubjectService} from "../../../services/subject.service";
import {AlertService} from "../../../services/alert.service";
import {User, UserPage} from "../../../model/user";
import {UserService} from "../../../services/user.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


  //Data
  users$: Observable<UserPage> | undefined
  showCodes = false
  //end data

  //Filter
  showArchived: boolean = false
  userType: string = ""
  //end filter

  //Modals
  deleteModalOpen: boolean = false
  generateCodeModalOpen: boolean = false
  editSubjectModalOpen: boolean = false
  //end modals


  //Data passed to children
  userIdToBeArchived: number | undefined
  editedUser: User | undefined

  //end data


  constructor(private subjectService: SubjectService,
              private alertService: AlertService,
              private userService: UserService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredUsers()
    })
  }

  getFilteredUsers() {
    if (!this.showArchived) {
      switch (this.userType) {
        case "STUDENT": {
          this.users$ = this.userService.getAllActiveStudents(this.pageNumber$.value)
          break
        }
        case "TEACHER": {
          this.users$ = this.userService.getAllActiveTeachers(this.pageNumber$.value)
          break
        }
        case "OFFICE": {
          this.users$ = this.userService.getAllActiveOffices(this.pageNumber$.value)
          break
        }
        case "DIRECTOR": {
          this.users$ = this.userService.getAllActiveDirectors(this.pageNumber$.value)
          break
        }
        default: {
          this.users$ = this.userService.getAllActiveUsers(this.pageNumber$.value)
        }
      }

    } else {
      switch (this.userType) {
        case "STUDENT": {
          this.users$ = this.userService.getAllArchivedUsers(this.pageNumber$.value)
          break
        }
        case "TEACHER": {
          this.users$ = this.userService.getAllArchivedTeachers(this.pageNumber$.value)
          break
        }
        case "OFFICE": {
          this.users$ = this.userService.getAllArchivedOffices(this.pageNumber$.value)
          break
        }
        case "DIRECTOR": {
          this.users$ = this.userService.getAllArchivedDirectors(this.pageNumber$.value)
          break
        }
        default: {
          this.users$ = this.userService.getAllArchivedUsers(this.pageNumber$.value)
        }
      }
    }
  }

  showStudents() {
    this.userType = "STUDENT"
    this.getFilteredUsers()
  }

  showTeachers() {
    this.userType = "TEACHER"
    this.getFilteredUsers()
  }

  showOffices() {
    this.userType = "OFFICE"
    this.getFilteredUsers()
  }

  showDirectors() {
    this.userType = "DIRECTOR"
    this.getFilteredUsers()
  }

  showAll() {
    this.userType = ""
    this.getFilteredUsers()
  }

  showActiveUsers() {
    this.showArchived = false
    this.getFilteredUsers()
  }

  showArchivedUsers() {
    this.showArchived = true
    this.getFilteredUsers()
  }

  previousPage() {
    if (this.pageNumber$.value > 0) {
      this.pageNumber$.next(this.pageNumber$.value - 1)
    }
  }

  nextPage() {
    this.pageNumber$.next(this.pageNumber$.value + 1)
  }

  deleteUser() {
    if (this.userIdToBeArchived) {
      this.userService.archiveUser(this.userIdToBeArchived).subscribe((result) => {
        this.alertService.showAlert("success", "User has been successfully archived.")
        this.getFilteredUsers()
        this.deleteModalOpen = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during archiving user. Try again later.")
      })
    }
  }

  showDeleteModal(id: number) {
    this.userIdToBeArchived = id
    this.deleteModalOpen = true
  }

  showEditModal(user: User) {
    this.editedUser = user
    this.editSubjectModalOpen = true
  }
}
