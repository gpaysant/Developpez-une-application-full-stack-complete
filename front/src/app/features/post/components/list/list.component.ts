import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { PostService } from '../../services/post.service';
import { Post } from '../../interfaces/post.interface';
import {Sort, MatSortModule} from '@angular/material/sort';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {
  sortedPosts: Post[] = [];
  isSortedByDate: boolean = false;
  public posts$: Observable<Post[]>;

  constructor (
    private postService: PostService
  ) {
    this.posts$ = this.postService.all();
    this.sortPostsByDate();
  }

  /**
   * @ngdoc method
   * @name sortPostsByDate
   * 
   * Sort by date if isSortedByDate equals true, else not sorted.
   * 
   * @return void 
   */
  sortPostsByDate(): void {
    this.isSortedByDate = !this.isSortedByDate;
    this.posts$.subscribe(posts => {
      if (this.isSortedByDate) {
        this.sortedPosts = posts.slice().sort((a, b) => {
          return new Date(b.dateCreation).getTime() - new Date(a.dateCreation).getTime();
        });
      } else {
        this.sortedPosts = posts.slice();
      }
    });
  }

}
