"use strict";
// src/index.ts
Object.defineProperty(exports, "__esModule", { value: true });
const rxjs_1 = require("rxjs");
// Create an Observable
const observable = new rxjs_1.Observable(subscriber => {
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
