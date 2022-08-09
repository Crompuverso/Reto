import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  private _greeting = {};
  constructor() {
  }

  ngOnInit(): void {
  }

  public get greeting(): any {
    return this._greeting
  }
}