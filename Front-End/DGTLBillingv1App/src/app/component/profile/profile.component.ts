import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of, BehaviorSubject, map, startWith, catchError } from 'rxjs';
import { DataState } from 'src/app/enum/datastate.enum';
import { Key } from 'src/app/enum/key.enum';
import { CustomHttpResponse, Profile } from 'src/app/interface/appstates';
import { State } from 'src/app/interface/state';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profileState$ : Observable<State<CustomHttpResponse<Profile>>> = of({ dataState: DataState.LOADED });
  private dataSubject = new BehaviorSubject<CustomHttpResponse<Profile>>(null);
  readonly DataState = DataState;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.profileState$ = this.userService
      .profile$()
      .pipe(
        map(response => {
          console.log(response)
            this.dataSubject.next(response);
            return {
              dataState: DataState.LOADED,
              appData: response
            };
          }),
        startWith({ dataState: DataState.LOADING }),
        catchError((error: string) => {
          return of({
            dataState: DataState.ERROR,
            appData: this.dataSubject.value, error
          });
        })
      );
  }
}
