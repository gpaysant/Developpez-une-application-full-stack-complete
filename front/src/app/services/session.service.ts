import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../features/user/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged = false;
  public user: User | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public $isLogged(): Observable<boolean> {
    this.checkIfLogged();
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: User): void {
    this.user = user;
    this.isLogged = true;
    this.next();
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this.user = undefined;
    this.isLogged = false;
    this.next();
  }

  public checkIfLogged() : boolean {
    if (!this.isLogged) {
      const token = localStorage.getItem('token');
      if (token != undefined) {
        this.isLogged = true;
      }
    }
    this.next();
    return this.isLogged;
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}