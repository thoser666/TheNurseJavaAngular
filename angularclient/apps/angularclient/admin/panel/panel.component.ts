import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faTriangleExclamation } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../../auth/service/auth.service';
import { PostModel } from '../../post/post-model';
import { ReportedPostModel } from '../../post/reported-post-model';
import { PostService } from '../../post/service/post.service';
import { ReportService } from '../../report/report.service';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css'],
})
export class PanelComponent implements OnInit {
  active = 1;

  constructor() {}

  ngOnInit(): void {}
}
