import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../models/comment.interface';

@Injectable({
    providedIn: 'root'
  })
  export class CommentService {
  
    private pathService = 'api/comment';
  
    constructor(private httpClient: HttpClient) { }
  
    public get(id: string): Observable<Comment[]> {
        return this.httpClient.get<Comment[]>(`${this.pathService}/${id}`);
    }

    public create(commentary: Comment): Observable<any> {
        return this.httpClient.post<any>(`${this.pathService}/add`, commentary);
    }
  }