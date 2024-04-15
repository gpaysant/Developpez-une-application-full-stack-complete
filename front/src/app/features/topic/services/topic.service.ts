import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError, tap, catchError } from 'rxjs';
import { Topic } from '../interfaces/Topic';

@Injectable({
    providedIn: 'root',
  })
export class TopicService {
  [x: string]: any;
  private topicUrl = '/api/topic';

  constructor(private httpClient: HttpClient) {
  }

  public all(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.topicUrl);
  }

  public subscribeTopic(id: string): Observable<void> {
    return this.httpClient.post<void>(`${this.topicUrl}/subscribe/${id}`, null);
  }

  public unsubscribeTopic(id: string): Observable<void> {
    return this.httpClient.post<void>(`${this.topicUrl}/unsubscribe/${id}`, null);
  }

  public getTopicSubscribed(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.topicUrl}/subscribed`);
  }

}