import { Injectable } from '@angular/core';
import { MessageType, Message } from '../definitions/message.interface';
import { Observable, ReplaySubject } from 'rxjs';
import {filter} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class MessagingService {

  constructor() { }

  private messages = new ReplaySubject<Message>(null);

  push(type: MessageType): void;
  push(message: Message): void;

  push(message: MessageType | Message): void {
    console.log('push')
    if (typeof message === 'string') {
      this.messages.next({type: message});
    } else {
      this.messages.next(message);
    }
  }

  ofType(type: MessageType): Observable<Message> {
    return this.messages.pipe(filter(message => message.type === type));
  }
}
