import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../interfaces/user.interface';
import { SessionService } from '../../../../services/session.service';
import { UserService } from '../../services/user.service';
import { HomeService } from 'src/app/services/home.service';
import { TopicService } from 'src/app/features/topic/services/topic.service';
import { Topic } from 'src/app/features/topic/interfaces/Topic';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public userForm: FormGroup | undefined;
  public user: User | undefined;
  public topicSubscribed$: Observable<Topic[]> = this.topicService.getTopicSubscribed();

  constructor(private router: Router,
              private fb: FormBuilder,
              private sessionService: SessionService,
              private homeService: HomeService,
              private topicService: TopicService,
              private userService: UserService) {
  }

  public ngOnInit(): void {
    this.userService
      .me()
      .subscribe((user: User) => {this.user = user;})

      this.initForm();
  }

  private initForm(user?: User): void {
    this.userForm = this.fb.group({
      username: [
        user ? user.username : '',
        [Validators.required]
      ],
      email: [
        user ? user.email : '',
        [Validators.required,
        Validators.email]
      ],
    });
  }

  public submit(): void {
    const userF = this.userForm?.value as User;
    userF.id = this.user!.id;
    this.userService
      .updateInfo(userF)
      .subscribe(() => console.log("mis à jour du compte"));
  }

  public logOut() {
    this.sessionService.logOut();
    this.router.navigate(['']);
    this.homeService.goHome();
  }

  public unsubscribeTopic(topicId: number) {
    this.topicService.unsubscribeTopic(String(topicId)).subscribe(() => {
      this.topicSubscribed$ = this.topicService.getTopicSubscribed();
    });
  }

}