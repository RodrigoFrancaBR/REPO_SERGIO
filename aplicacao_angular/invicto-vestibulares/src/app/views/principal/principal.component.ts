import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {
  userName: string;
  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.userName = this.activatedRoute.snapshot.params.userName;
  }

}
