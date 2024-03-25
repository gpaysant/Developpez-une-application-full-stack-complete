import { Component } from "@angular/core";
import { Observable } from 'rxjs';
import { Topic } from "../core/models/Topic";
import { TopicService } from '../core/services/topic.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent {

  public topics$: Observable<Topic[]> = this.topicService.all();

  constructor(
    private topicService: TopicService
  ) { }

}
