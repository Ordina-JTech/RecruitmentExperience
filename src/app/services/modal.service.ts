import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor(private dialog: MatDialog) { }

  openDialog() {
    // this.dialog.open()
  }
}
