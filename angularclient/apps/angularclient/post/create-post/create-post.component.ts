import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { Modals } from '../../modals';
import { PostService } from '../service/post.service';
import { TopicModel } from '../../topic/topic-model';
import { TopicService } from '../../topic/topic.service';
import { PostRequest } from '../post-request';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css'],
})
export class CreatePostComponent implements OnInit {
  createPostForm: FormGroup;
  topics: Array<TopicModel> = [];
  postRequest: PostRequest;

  constructor(
    private router: Router,
    private topicService: TopicService,
    private postService: PostService,
    private modals: Modals
  ) {
    this.createPostForm = new FormGroup({
      title: new FormControl('', Validators.required),
      url: new FormControl(''),
      topicName: new FormControl(''),
      content: new FormControl(''),
    });
    this.postRequest = {
      title: '',
      content: '',
      topicName: '',
    };
  }

  ngOnInit(): void {
    this.topicService.getAllTopics().subscribe({
      next: (data) => {
        this.topics = data;
      },
      error: (error) => throwError(() => error),
    });
  }
  discardPost() {
    this.router.navigateByUrl('/home');
  }
  createPost() {
    this.postRequest.title = this.createPostForm.get('title')!.value;
    this.postRequest.content = this.createPostForm.get('content')!.value;
    this.postRequest.topicName = this.createPostForm.get('topicName')!.value;

    this.postService.createPost(this.postRequest).subscribe({
      next: (data) => {
        this.router.navigateByUrl('/home');
        console.log(data);
      },
      error: (error) => {
        this.modals.errorNotification(error.error);
      },
    });
  }
}
