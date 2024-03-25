import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError, tap, catchError } from 'rxjs';
import { Topic } from '../models/Topic';

@Injectable({
    providedIn: 'root',
  })
export class TopicService {
    private topicUrl = '/api/topic';
    private topic$ = new BehaviorSubject<Topic[]>([]);

    constructor(private httpClient: HttpClient) {
    }

    public all(): Observable<Topic[]> {
      return this.httpClient.get<Topic[]>(this.topicUrl);
  }
}