import { Component } from '@angular/core';
import { Observable, Subject, combineLatest, map, tap } from 'rxjs';
import { TopicService } from '../../services/topic.service';
import { Topic } from '../../interfaces/Topic';
import { TopicWithSubscription } from '../../interfaces/TopicWithSubscription';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss'
})
export class TopicListComponent {

public topics$: Observable<Topic[]> = this.topicService.all();
public topicSubscribed$: Observable<Topic[]> = this.topicService.getTopicSubscribed();
public topicsWithSubscription$: Observable<TopicWithSubscription[]> | undefined;

constructor(private topicService: TopicService
) {}

ngOnInit() {
  this.fetchTopic();
}

public fetchTopic() {
  this.topicsWithSubscription$ = combineLatest([this.topics$, this.topicSubscribed$]).pipe(
    map(([topics, subscribedTopics]) => {
      return topics.map(topic => {
        const isSubscribed = subscribedTopics.some(subscribedTopic => subscribedTopic.id === topic.id);
        return {
          id: topic.id,
          title: topic.title,
          description: topic.description,
          subscribed: isSubscribed
        };
      });
    })
  );
}

public subscribeTopic(topicId: number): void {
  this.topicService.subscribeTopic(String(topicId)).subscribe(() => {
    this.fetchTopic();
  });
}

}
