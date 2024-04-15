import { Component, HostListener, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from '../../services/post.service';
import { Post } from '../../interfaces/post.interface';
import { CommentService } from 'src/app/services/comment.service';
import { Comment } from 'src/app/models/comment.interface';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.scss'
})
export class DetailComponent implements OnInit {

  public post: Post | undefined;
  public postId: string;
  public newComment: any;
  public comments$: Observable<Comment[]>;
  isSmallScreen: boolean = false;

  constructor (
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    ) {
      this.postId = this.route.snapshot.paramMap.get('id')!;
      this.comments$ = this.commentService.get(this.postId);
      this.checkScreenSize();
  }

  public ngOnInit(): void {
    this.fetchPost();
  }

  public back() {
    window.history.back();
  }

  private fetchPost() {
    this.postService
    .detail(this.postId)
    .subscribe((post : Post) => {
      this.post = post;
    });

  }

  public onCommentSave() {
    let commentAdd = { text: String(this.newComment), postId: Number(this.postId), creator: "NoName" } as Comment;

    this.commentService
    .create(commentAdd)
    .subscribe((_: any) => {
      this.comments$ = this.commentService.get(this.postId);
      this.newComment = "";
    });
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.checkScreenSize();
  }

  checkScreenSize() {
      this.isSmallScreen = window.innerWidth < 768;
  }
}
