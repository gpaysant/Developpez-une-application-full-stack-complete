import { HostListener, Input, booleanAttribute } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeService } from 'src/app/services/home.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  @Input({ transform: booleanAttribute }) isUserLoggedIn: boolean = false;
  @Input({ transform: booleanAttribute }) isConnectingHome: boolean = false;



  isSmallScreen: boolean = false;
  constructor(
    private router: Router,
    private sessionService: SessionService,
    private homeService: HomeService) {
  }

  ngOnInit() : void {
    this.checkScreenSize();
  }
  
 

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);
    this.homeService.goHome();
  }

  public leaveHome() : void {
    this.homeService.leaveHome();
  }

  @HostListener('window:resize', ['$event'])
  onResize(event : Event) : void {
      this.checkScreenSize();
  }

  checkScreenSize() {
      this.isSmallScreen = window.innerWidth < 768;
  }

  back() : void {
    this.homeService.goHome();
  }

  isMeActive(): boolean {
    return this.router.url === '/user';
  }
  
}
