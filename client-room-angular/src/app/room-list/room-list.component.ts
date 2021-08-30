import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from '../room';
import { RoomService } from './../room.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {
  //rooms: Room[] = [];
  rooms?: Observable<Room[]>;

  constructor(private roomService: RoomService,
              private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  updateRoom(id: any) {
    this.router.navigate(['update', id]);
  }

  roomDetails(id: any) {
    this.router.navigate(['details', id]);
  }

  deleteRoom(id: any) {
    this.roomService.deleteRoom(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error)
      );
  }

  private reloadData() {
    this.rooms = this.roomService.getRoomsList();
  }

}
