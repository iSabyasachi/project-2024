## Table of Contents

- [Table of Contents](#table-of-contents)
- [Installation](#installation)
- [Concept](#concept)
- [Problems](#problems)

## Installation

1. Clone the repository: `git clone https://github.com/username/MyProject.git`
2. Navigate to the project directory: `cd MyProject`
3. Install dependencies: `npm install`
4. Build project: `npm run build`
5. Start project: `npm start`
6. Test project: `npm test`
7. Format project: `npm run format`
8. Run lint: `npm run lint -- --fix`

## Concept

### Operator:

- **switchMap**:  
  `switchMap` is an RxJS operator that maps each emitted value from an observable into a new observable, subscribes to the latest one, and automatically cancels any previous subscriptions, ensuring only the latest observable’s emissions are processed.

- **mergeMap**:  
  - Use when transforming one Observable’s emissions into another Observable and flattening the result.  
  - Suitable when the **order of emissions doesn’t matter**.  
  - Provides flexibility in handling **concurrency**.

## Problems

# RxJS Problem Challenges

Here are **20 combinations** of **easy**, **medium**, and **hard** RxJS problems to help build competency progressively. These challenges cover a wide range of RxJS concepts and usage patterns:

---

## Easy

1. **Basic Stream Creation and Subscription**  
   Create an observable from an array, object, and promise. Log each emitted value to the console.

2. **Simple Transformation with `map`**  
   Use `map` to multiply each emitted value in a stream by 2.

   #### Code Implementation

   ```typescript
   import { of, Observable } from 'rxjs';
   import { map } from 'rxjs/operators';

   export function multipleByTwoObservable(): Observable<number[]> {
     return of([1, 2, 3, 4, 5]).pipe(
       map((numArray) => numArray.map((ele) => ele * 2))
     );
   }

   // Test case for the `multipleByTwoObservable` function.
   // This verifies that the emitted array has all elements multiplied by 2.
   import { multipleByTwoObservable } from './your-file-path';

   test('multipleByTwoObservable', (done) => {
     multipleByTwoObservable().subscribe({
       next: (value) => {
         expect(value).toEqual([2, 4, 6, 8, 10]);
       },
       complete: () => {
         done();
       },
     });
   });
   ```

3. **Debouncing a Search Input**  
   Implement a debounced search input that emits a search term after a 500ms delay.

4. **Filtering Even Numbers**  
   Use `filter` to emit only even numbers from a stream.

5. **Error Handling with `catchError`**  
   Create a stream that throws an error and use `catchError` to handle it gracefully by returning a default value.

6. **Combining Observables with `concat`**  
   Combine two observables using `concat` to emit values sequentially.

7. **Using `take` Operator**  
   Create a stream that emits numbers from 1 to 10 and use `take(5)` to get only the first five values.

8. **Throttle Click Events**  
   Listen to click events on a button and throttle them to emit one event every 1 second.

9. **Basic Timer with `interval`**  
   Use `interval` to create a timer that emits values every second.

10. **Replay Last Emitted Value with `shareReplay`**  
    Use `shareReplay` to create a shared observable that replays the last emitted value to new subscribers.

---

## Medium

11. **Implement an Autocomplete Search with `switchMap`**  
    Build an autocomplete search component that uses `switchMap` to cancel previous requests when a new search term is entered.

12. **Buffer Click Events**  
    Use `bufferCount` to collect a certain number of click events before emitting them as an array.

13. **Conditional Emission with `takeWhile`**  
    Emit values from an observable until a certain condition is met using `takeWhile`.

14. **Retry on Error**  
    Create a stream that simulates an API request, with a 50% chance of failing. Use `retry` to retry the request up to three times.

15. **Countdown Timer with `timer` and `scan`**  
    Implement a countdown timer that counts down from 10 to 0, emitting each second.

16. **Chaining Multiple Operators**  
    Create a stream that emits numbers, use `map` to double them, `filter` to only emit values greater than 10, and log each value.

17. **Using `mergeMap` for Concurrent Requests**  
    Given an array of URLs, fetch data from each URL concurrently using `mergeMap`.

18. **Complex Timer using `combineLatest`**  
    Create two streams, one emitting values every 500ms and the other every second. Use `combineLatest` to output the combined values.

19. **Simulating API Request with Backoff Strategy**  
    Create a stream that makes an API request and uses `retryWhen` with an exponential backoff strategy.

20. **Sliding Window with `bufferTime`**  
    Use `bufferTime` to emit an array of events that occurred within the last 2 seconds in a sliding window.

---

## Hard

21. **Custom Operator Implementation**  
    Create a custom operator that emits only values that are prime numbers from a given stream.

22. **Dynamic Polling Based on Response**  
    Implement a polling mechanism that adjusts its interval based on the response of the previous request.

23. **Rate Limiting API Calls**  
    Create an observable that listens to an API call button and limits the number of calls to one per second using `throttleTime` and `switchMap`.

24. **Real-time Stock Price Updates**  
    Use `webSocket` or `ajax` to fetch stock prices and update them in real-time, simulating a live feed with `switchMap`.

25. **Infinite Scroll with `mergeMap` and `takeUntil`**  
    Implement infinite scroll with RxJS to fetch new data only when the user scrolls to the bottom of the page, stopping the stream on a certain condition.

26. **Implementing a Retry with Dynamic Delay Strategy**  
    Write a function that retries a failing request with an increasing delay using `retryWhen` and `delay`.

27. **Complex Transformation with `reduce`**  
    Create a stream that emits numbers and calculates their cumulative sum using `reduce`, then emits the total when the stream completes.

28. **Combining Multiple Sources with `zip` and `combineLatest`**  
    Use `zip` and `combineLatest` together to combine data from multiple sources and emit them together in a specific pattern.

29. **Chained Requests with `concatMap`**  
    Simulate an API that requires the result of one request to be the input of the next. Use `concatMap` to chain requests sequentially.

30. **Building a Real-time Chat Room with `Subject`**  
    Use `Subject` to create a real-time chat room where users can send and receive messages simultaneously.

---

These exercises will cover a wide array of RxJS features, from basics to more complex concepts like retry mechanisms, custom operators, and real-time data handling. Completing them will provide a strong foundation in RxJS.
