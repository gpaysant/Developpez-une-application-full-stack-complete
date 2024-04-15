import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { SessionService } from './services/session.service';
import { HomeService } from './services/home.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {
  isUserLoggedIn = false;
  isConnectingHome$ = this.homeService.$isEnteringHome();

  constructor(
    private router: Router,
    private sessionService: SessionService,
    private homeService: HomeService) {
  }

  ngOnInit() : void {
    this.sessionService.$isLogged().subscribe(isLogged => {
      this.isUserLoggedIn = isLogged;
    });
  }

}