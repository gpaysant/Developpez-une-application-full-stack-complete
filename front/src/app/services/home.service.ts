import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  public isGoingHome = true;

  private isOnPageHomeSubject = new BehaviorSubject<boolean>(this.isGoingHome);

  public $isEnteringHome(): Observable<boolean> {
    return this.isOnPageHomeSubject.asObservable();
  }

  public leaveHome(): void {
    this.isGoingHome = false;
    this.next();
  }

  public goHome(): void {
    this.isGoingHome = true;
    this.next();
  }

  private next(): void {
    this.isOnPageHomeSubject.next(this.isGoingHome);
  }

}
