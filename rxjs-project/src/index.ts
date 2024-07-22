// src/index.ts

import { Observable } from 'rxjs';

// Create an Observable
const observable = new Observable(subscriber => {
  subscriber.next('Hello');
  subscriber.next('World');
  subscriber.complete();
});

// Subscribe to the Observable
observable.subscribe({
  next(x) { console.log(x); },
  error(err) { console.error('Something wrong occurred: ' + err); },
  complete() { console.log('Done'); }
});