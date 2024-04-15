import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostCreate } from '../../interfaces/postCreate.interface';
import { PostService } from '../../services/post.service';
import { TopicService } from 'src/app/features/topic/services/topic.service';
import {  Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss'
})
export class FormComponent implements OnInit {
  public postForm: FormGroup | undefined;

  public topics$ = this.topicService.all();

  constructor(
    private fb: FormBuilder,
    private matSnackBar: MatSnackBar,
    private postService: PostService,
    private topicService: TopicService,
    private router: Router
  ) {
  }

  public ngOnInit(): void {
    this.initForm();
  }


  private initForm(post?: PostCreate): void {
    this.postForm = this.fb.group({
      topicId: [
        post ? post.topicId : '',
        [Validators.required]
      ],
      title: [
        post ? post.title : '',
        [Validators.required]
      ],
      content: [
        post ? post.content : '',
        [
          Validators.required,
          Validators.max(2000)
        ]
      ],
    });
  }

  public submit(): void {
    const post = this.postForm?.value as PostCreate;
    this.postService
      .create(post)
      .subscribe(() => this.exitPage('Article créé !'));
  }

  private exitPage(message: string): void {
    this.matSnackBar.open(message, 'Close', { duration: 3000 });
    this.router.navigate(['post']);
  }

}
