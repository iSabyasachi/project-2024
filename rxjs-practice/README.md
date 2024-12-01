## Table of Contents

- [Table of Contents](#table-of-contents)
- [Installation](#installation)
- [Concepts](#concepts)
  - [Operator:](#operator)
- [Problems](#problems)
  - [RxJS Problem Challenges](#rxjs-problem-challenges)
    - [Easy](#easy)
      - [Code Implementation for Collect Even Numbers from Array](#code-implementation-for-collect-even-numbers-from-array)
      - [Code Implementation for Collect Even Numbers from Object](#code-implementation-for-collect-even-numbers-from-object)
      - [Code Implementation for Collect Even Numbers from Promise](#code-implementation-for-collect-even-numbers-from-promise)
      - [Code Implementation for Simple Transformation with `map`](#code-implementation-for-simple-transformation-with-map)
      - [Code Implementation for Debouncing a Search Input](#code-implementation-for-debouncing-a-search-input)
      - [Code Implementation for Filtering Even Numbers](#code-implementation-for-filtering-even-numbers)
      - [Code Implementation for Error Handling with `catchError`](#code-implementation-for-error-handling-with-catcherror)
      - [Code Implementation for Combining Observables with `concat`](#code-implementation-for-combining-observables-with-concat)
      - [Code Implementation for Using `take` Operator](#code-implementation-for-using-take-operator)
      - [Code Implementation for Throttle Click Events](#code-implementation-for-throttle-click-events)
      - [Code Implementation for Basic Timer with `interval`](#code-implementation-for-basic-timer-with-interval)
    - [Medium](#medium)
    - [Hard](#hard)

## Installation

1. Clone the repository: `git clone https://github.com/iSabyasachi/project-2024.git`
2. Navigate to the project directory: `cd rxjs-practice`
3. Install dependencies: `npm install`
4. Build project: `npm run build`
5. Start project: `npm start`
6. Test project: `npm test`
7. Format project: `npm run format`
8. Run lint: `npm run lint -- --fix`

## Concepts

### Operator:

- **switchMap**:  
  `switchMap` is an RxJS operator that maps each emitted value from an observable into a new observable, subscribes to the latest one, and automatically cancels any previous subscriptions, ensuring only the latest observable’s emissions are processed.

- **mergeMap**:
  - Use when transforming one Observable’s emissions into another Observable and flattening the result.
  - Suitable when the **order of emissions doesn’t matter**.
  - Provides flexibility in handling **concurrency**.
- **shareReplay**:  
  The `shareReplay` operator shares a source observable among multiple subscribers and replays the specified number of last emitted values to new subscribers.

- **interval**:  
  The `interval` operator creates an observable that emits sequential numbers at specified time intervals.

- **take**:

  - The `take` operator limits the number of values emitted from an observable to a specified count.
  - The `take` operator emits only the first specified number of values from an observable and then completes.

- **throttle**:  
  The `throttle` operator limits the rate at which values are emitted from an observable by emitting the first value and ignoring subsequent values for a specified duration.

- **concat**:

  - The `concat` operator sequentially combines observables, emitting all values from the first observable before proceeding to the next.
  - The `concat` operator combines multiple observables by emitting all values from the first observable, then all values from the second, and so on, in sequence. It ensures that the previous observable completes before starting the next.

- **catchError**:  
  The `catchError` operator handles errors in an observable sequence and allows you to recover gracefully by returning a fallback observable or value.

- **filter**:  
  The `filter` operator emits values from the source observable that satisfy a specified condition, discarding those that do not.

- **debounceTime**:  
  `debounceTime` delays the emission of an observable value by a specified time duration, emitting only the most recent value if the source observable emits multiple values within that duration.

- **distinctUntilChanged**:  
  `distinctUntilChanged` suppresses duplicate consecutive values, ensuring that only unique values are emitted.

- **retry**:  
  `retry` is an RxJS operator that retries a failed observable sequence a specified number of times before propagating the error.

- **catchError**:  
  `catchError` is used to handle errors in an observable sequence and return a fallback observable or value.

- **tap**:  
  `tap` allows you to perform side effects, such as logging errors or tracking retry attempts, without modifying the observable stream.

- **takeWhile**:  
  `takeWhile` is an RxJS operator that emits values from an observable until a specified condition evaluates to `false`. Once the condition fails, the observable completes, and no further values are emitted.

## Problems

### RxJS Problem Challenges

Here are **20 combinations** of **easy**, **medium**, and **hard** RxJS problems to help build competency progressively. These challenges cover a wide range of RxJS concepts and usage patterns:

---

#### Easy

1. **Basic Stream Creation and Subscription**  
   Create an observable from an array, object, and promise. Log each emitted value to the console.

   ##### Code Implementation for Collect Even Numbers from Array

   This implementation uses an RxJS observable to collect even numbers from an array:

   ```typescript
   import { of } from 'rxjs';

   export function collectEvenNumbersFromArray(): number[] {
     const result: number[] = [];
     const arr$ = of([1, 2, 3, 4, 5]);

     arr$.subscribe((arr) => {
       arr.filter((num) => num % 2 == 0).forEach((num) => result.push(num));
     });

     return result;
   }
   ```

   ##### Code Implementation for Collect Even Numbers from Object

   This implementation processes an object with an array property and a flag to determine whether to filter for even numbers:

   ```typescript
   import { of } from 'rxjs';

   type NumObj = {
     arr: number[];
     isEven: boolean;
   };

   export function collectEvenNumbersFromObject(): number[] {
     const result: number[] = [];
     const inputObj: NumObj = {
       arr: [1, 2, 3, 4],
       isEven: true,
     };
     const numObj$ = of(inputObj);

     numObj$.subscribe((numObj) => {
       numObj.arr
         .filter((num) => numObj.isEven && num % 2 == 0)
         .forEach((num) => result.push(num));
     });

     return result;
   }
   ```

   ##### Code Implementation for Collect Even Numbers from Promise

   This implementation demonstrates how to process even numbers from a promise:

   ```typescript
   export function collectEvenNumbersFromPromise(): Promise<number[]> {
     return fetchNumber().then((nums) => nums.filter((num) => num % 2 == 0));
   }

   async function fetchNumber(): Promise<number[]> {
     return new Promise((resolve) => {
       setTimeout(() => {
         resolve([1, 2, 3, 4, 5]);
       }, 1000); // Simulates an async operation
     });
   }

   // Test Case
   import {
     collectEvenNumbersFromArray,
     collectEvenNumbersFromObject,
     collectEvenNumbersFromPromise,
   } from './easy.practice';

   describe('Easy: Basic Stream Creation and Subscription', () => {
     test('collectEvenNumbersFromArray', (done) => {
       const actual = collectEvenNumbersFromArray();

       expect(actual).toEqual([2, 4]); // Expect only even numbers from the array
       done();
     });

     test('collectEvenNumbersFromObject', (done) => {
       const actual = collectEvenNumbersFromObject();

       expect(actual).toEqual([2, 4]); // Expect only even numbers based on the object properties
       done();
     });

     test('collectEvenNumbersFromPromise', async () => {
       const actual = await collectEvenNumbersFromPromise();

       expect(actual).toEqual([2, 4]); // Expect only even numbers from the promise
     });
   });
   ```

2. **Simple Transformation with `map`**  
   Use `map` to multiply each emitted value in a stream by 2.

   ##### Code Implementation for Simple Transformation with `map`

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
   import { multipleByTwoObservable } from './easy.practice';

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

   ##### Code Implementation for Debouncing a Search Input

   The following implementation demonstrates the use of `debounceTime` and `distinctUntilChanged` to handle user inputs efficiently by reducing the frequency of emitted values:

   ```typescript
   import { Subject, Observable } from 'rxjs';
   import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

   export function debounceSearchInput(): Observable<string> {
     const input: Subject<string> = mockUserInputs(); // Mock function to simulate user input stream

     return input.pipe(
       debounceTime(500), // Emit value only if 500ms have passed without another emission
       distinctUntilChanged() // Emit only if the current value is different from the previous value
     );
   }

   // Mock function to simulate user input
   function mockUserInputs(): Subject<string> {
     const subject = new Subject<string>();
     setTimeout(() => subject.next('H'), 100); // User types 'H'
     setTimeout(() => subject.next('He'), 200); // User types 'He'
     setTimeout(() => subject.next('Hel'), 800); // User types 'Hel' after a pause
     setTimeout(() => subject.complete(), 1000); // Input stream ends
     return subject;
   }

   //Test case for the `debounceSearchInput` function.
   import { debounceSearchInput } from './easy.practice';

   test('debounceSearchInput', (done) => {
     const actual = debounceSearchInput();
     actual.subscribe((value) => {
       expect(value).toContain('Hel'); // Expect the final emitted value to be 'Hel'
       done();
     });
   });
   ```

4. **Filtering Even Numbers**  
   Use `filter` to emit only even numbers from a stream.

   ##### Code Implementation for Filtering Even Numbers

   The following implementation demonstrates the use of `filter` to emit only even numbers from a stream:

   ```typescript
   import { of, Observable } from 'rxjs';
   import { filter, toArray } from 'rxjs/operators';

   export function filterEvenNumbers(): Observable<number[]> {
     const nums$ = of(1, 2, 3, 4, 5);

     return nums$.pipe(
       filter((num) => num % 2 === 0), // Emit only even numbers
       toArray() // Collect emitted values into an array
     );
   }

   //Test case for the `filterEvenNumbers` function.
   import { filterEvenNumbers } from './easy.practice';

   test('filterEvenNumbers', (done) => {
     const result$ = filterEvenNumbers();
     result$.subscribe((value) => {
       expect(value).toEqual([2, 4]); // Expect the filtered array to contain only even numbers
       done();
     });
   });
   ```

5. **Error Handling with `catchError`**  
   Create a stream that throws an error and use `catchError` to handle it gracefully by returning a default value.

   ##### Code Implementation for Error Handling with `catchError`

   The following implementation demonstrates the use of `catchError` to handle errors in a stream:

   ```typescript
   import { of, firstValueFrom } from 'rxjs';
   import { map, catchError, toArray } from 'rxjs/operators';

   export function errorHandlingWithCatchError(): Promise<(number | string)[]> {
     const nums$ = of(1, 2, 3, 4, 5, 6 / 0);

     const result$ = nums$.pipe(
       map((num) => {
         if (!isFinite(num)) {
           throw new Error('Infinity detected!');
         }
         return num;
       }),
       catchError((err) => {
         console.error('Error Caught:', err.message);
         return of('Handled Infinity'); // Fallback value when an error is caught
       }),
       toArray() // Collect emitted values into an array
     );

     return firstValueFrom(result$); // Convert the observable to a promise
   }

   //Test case for the `errorHandlingWithCatchError` function.

   import { errorHandlingWithCatchError } from './easy.practice';

   test('errorHandlingWithCatchError', async () => {
     const actual = await errorHandlingWithCatchError();

     expect(actual).toEqual([1, 2, 3, 4, 5, 'Handled Infinity']); // Expect fallback value for infinity
   });
   ```

6. **Combining Observables with `concat`**  
   Combine two observables using `concat` to emit values sequentially.

   ##### Code Implementation for Combining Observables with `concat`

   The following implementation demonstrates how to combine multiple observables sequentially using `concat`:

   ```typescript
   import { of, concat, lastValueFrom } from 'rxjs';
   import { toArray } from 'rxjs/operators';

   export function combiningObservablesWithConcat(): Promise<
     (string | number)[]
   > {
     const letter$ = of('a', 'b'); // Emits letters
     const num$ = of(1, 2); // Emits numbers

     const result$ = concat(letter$, num$).pipe(
       // Combine the emissions of the two observables sequentially
       toArray() // Collect all emitted values into an array
     );

     return lastValueFrom(result$); // Convert the observable to a promise
   }

   //Test case for the `combiningObservablesWithConcat` function.

   import { combiningObservablesWithConcat } from './easy.practice';

   test('combiningObservablesWithConcat', async () => {
     const actual = await combiningObservablesWithConcat();

     expect(actual).toEqual(['a', 'b', 1, 2]); // Expect the sequential combination of both observables
   });
   ```

7. **Using `take` Operator**  
   Create a stream that emits numbers from 1 to 10 and use `take(5)` to get only the first five values.

   ##### Code Implementation for Using `take` Operator

   The following implementation demonstrates how to use the `take` operator to limit the number of emitted values:

   ```typescript
   import { of, firstValueFrom } from 'rxjs';
   import { tap, take, toArray } from 'rxjs/operators';

   export function usingTakeOperator(): Promise<number[]> {
     const nums$ = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

     const result$ = nums$.pipe(
       tap((num) => {
         console.log(num); // Logs each emitted number
       }),
       take(5), // Take only the first 5 numbers
       toArray() // Collect the emitted values into an array
     );

     return firstValueFrom(result$); // Convert the observable to a promise
   }

   //Test case for the `usingTakeOperator` function.
   import { usingTakeOperator } from './easy.practice';

   test('usingTakeOperator', async () => {
     const actual = await usingTakeOperator();

     expect(actual).toEqual([1, 2, 3, 4, 5]); // Expect the first 5 numbers from the sequence
   });
   ```

8. **Throttle Click Events**  
   Listen to click events on a button and throttle them to emit one event every 1 second.

   ##### Code Implementation for Throttle Click Events

   The following implementation demonstrates how to use `throttle` to limit the rate at which values are emitted from a stream:

   ```typescript
   import { of, concat, interval, firstValueFrom } from 'rxjs';
   import { tap, delay, throttle, toArray } from 'rxjs/operators';

   export function throttleClickEvents(): Promise<number[]> {
     const nums$ = of(1, 2, 3, 4, 5);

     // Create an observable that emits 6 after 2 seconds
     const delayed6$ = of(6).pipe(
       tap(() => {
         console.log('Waiting!!!'); // Log message when waiting
       }),
       delay(2000) // Delay the emission of 6 by 2 seconds
     );

     // Combine the original observable with the delayed value
     const newNums$ = concat(nums$, delayed6$);

     const result$ = newNums$.pipe(
       tap((num) => {
         console.log(num); // Log each emitted number
       }),
       throttle(() => interval(1000)), // Emit the first value, then throttle subsequent values for 1 second
       toArray() // Collect the emitted values into an array
     );

     return firstValueFrom(result$); // Convert the observable to a promise
   }

   //Test case for the `throttleClickEvents` function.

   import { throttleClickEvents } from './easy.practice';

   test('throttleClickEvents', async () => {
     const actual = await throttleClickEvents();

     expect(actual).toEqual([1, 6]); // Expect the throttled output to include only the first emitted value and 6
   });
   ```

9. **Basic Timer with `interval`**  
   Use `interval` to create a timer that emits values every second.

   ##### Code Implementation for Basic Timer with `interval`

   The following implementation demonstrates how to use `interval` to create a timer that emits sequential numbers at 1-second intervals:

   ```typescript
   import { interval, firstValueFrom } from 'rxjs';
   import { take, toArray } from 'rxjs/operators';

   export function basicTimerWithInterval(): Promise<number[]> {
     const numbers = interval(1000); // Emits numbers sequentially every 1 second
     const takeFourNumbers = numbers.pipe(
       take(4), // Take only the first 4 values
       toArray() // Collect the emitted values into an array
     );

     return firstValueFrom(takeFourNumbers); // Convert the observable to a promise
   }

   //Test case for the `basicTimerWithInterval` function.
   import { basicTimerWithInterval } from './easy.practice';

   test('basicTimerWithInterval', async () => {
     const actual = await basicTimerWithInterval();

     expect(actual).toEqual([0, 1, 2, 3]); // Expect the first 4 numbers emitted by the timer
   });
   ```

10. **Replay Last Emitted Value with `shareReplay`**  
    Use `shareReplay` to create a shared observable that replays the last emitted value to new subscribers.

    ##### Code Implementation for Replay Last Emitted Value with `shareReplay`

    The following implementation demonstrates how to use `shareReplay` to share an observable among multiple subscribers while replaying the last three emitted values:

    ```typescript
    import { interval } from 'rxjs';
    import { take, shareReplay, toArray } from 'rxjs/operators';

    export function replayLastEmittedValueWithShareReplay() {
      const shared$ = interval(1000).pipe(
        take(6), // Take the first 6 values (0 to 5)
        shareReplay(3), // Replay the last 3 emitted values to new subscribers
        toArray()
      );

      // First subscription
      shared$.subscribe({
        next: (x) => console.log('sub A: ', x),
        complete: () => console.log('sub A completed!'),
      });

      // Second subscription
      shared$.subscribe({
        next: (y) => console.log('sub B: ', y),
        complete: () => console.log('sub B completed!'),
      });

      // Third subscription after a delay
      setTimeout(() => {
        shared$.subscribe({
          next: (z) => console.log('sub C: ', z),
          complete: () => console.log('sub C completed!'),
        });
      }, 11000); // Subscribe after all emissions are complete
    }

    //Test case for the `replayLastEmittedValueWithShareReplay` function.
    import { replayLastEmittedValueWithShareReplay } from './easy.practice';

    test('replayLastEmittedValueWithShareReplay', (done) => {
      replayLastEmittedValueWithShareReplay();
      done();
    });
    ```

---

#### Medium

11. **Implement an Autocomplete Search with `switchMap`**  
    Build an autocomplete search component that uses `switchMap` to cancel previous requests when a new search term is entered.

    ##### Code Implementation for Implement an Autocomplete Search with `switchMap`

    The following implementation demonstrates an autocomplete search functionality using `switchMap` to handle user input and simulate HTTP requests:

    ```typescript
    import { from, of, Observable } from 'rxjs';
    import {
      delay,
      debounceTime,
      distinctUntilChanged,
      switchMap,
    } from 'rxjs/operators';

    export function autoCompleteSearchWithSwitchMap(): Observable<string[]> {
      // Mock user input stream
      const mockUserInput$ = from(['a', 'ab', 'abc']).pipe(delay(300));

      // Mock HTTP request
      const mockHttprequest = (input: string) => {
        console.log(`Sending Request for input: ${input}`);

        return of([`${input}-result`]).pipe(delay(500));
      };

      return mockUserInput$.pipe(
        debounceTime(300), // Debounce to simulate user pausing typing
        distinctUntilChanged(), // Avoid duplicate requests for the same term
        switchMap((input) => mockHttprequest(input))
      );
    }

    // Test case for the `autoCompleteSearchWithSwitchMap` function.
    import { autoCompleteSearchWithSwitchMap } from './medium.practice';

    test('autoCompleteSearchWithSwitchMap', (done) => {
      const actual$ = autoCompleteSearchWithSwitchMap();

      actual$.subscribe((value) => {
        expect(value).toEqual(['abc-result']); // Expect only the latest search term's result
        done();
      });
    });
    ```

12. **Buffer Click Events**  
    Use `bufferCount` to collect a certain number of click events before emitting them as an array.

    ##### Code Implementation for Buffer Click Events

    The following implementation uses the `bufferCount` operator to group emitted values into arrays based on a count and a skip value:

    ```typescript
    import { of, lastValueFrom } from 'rxjs';
    import { bufferCount, toArray } from 'rxjs/operators';

    export function bufferClickEvents(): Promise<string[][]> {
      const clicks$ = of('a', 'b', 'c', 'd', 'e', 'f');

      const result$ = clicks$.pipe(
        bufferCount(3, 2), // Groups values into buffers of 3, starting every 2 values
        toArray() // Collects all buffered results into a single array
      );

      return lastValueFrom(result$);
    }

    // Test case for the `bufferClickEvents` function.
    import { bufferClickEvents } from './medium.practice';

    test('bufferClickEvents', async () => {
      const actual = await bufferClickEvents();

      expect(actual).toEqual([
        ['a', 'b', 'c'],
        ['c', 'd', 'e'],
        ['e', 'f'],
      ]);
    });
    ```

13. **Conditional Emission with `takeWhile`**  
    Emit values from an observable until a certain condition is met using `takeWhile`.

    ##### Code Implementation for Buffer Click EventsConditional Emission with `takeWhile`

    The following implementation demonstrates the use of `takeWhile` to emit numbers from a stream until a specified condition is met:

    ```typescript
    import { of, firstValueFrom } from 'rxjs';
    import { takeWhile, toArray } from 'rxjs/operators';

    export function conditionalEmissionWithTakeWhile(): Promise<number[]> {
      const nums$ = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

      const result$ = nums$.pipe(
        takeWhile((num) => num < 6), // Emit values while they are less than 6
        toArray() // Collect emitted values into an array
      );

      return firstValueFrom(result$); // Convert the observable into a promise
    }

    // Test case for the `conditionalEmissionWithTakeWhile` function.
    import { conditionalEmissionWithTakeWhile } from './medium.practice';

    test('conditionalEmissionWithTakeWhile', async () => {
      const actual = await conditionalEmissionWithTakeWhile();

      expect(actual).toEqual([1, 2, 3, 4, 5]); // Expect only numbers less than 6
    });
    ```

14. **Retry on Error**  
    Create a stream that simulates an API request, with a 50% chance of failing. Use `retry` to retry the request up to three times.

    ##### Code Implementation for Retry on Error

    The following implementation simulates an API request with a 50% chance of failure. It retries the request up to 3 times before handling the error:

    ```typescript
    import { of, throwError, timer } from 'rxjs';
    import { mergeMap, retry, catchError, tap } from 'rxjs/operators';

    export function retryOnError() {
      // Create a stream that simulates an API request with a 50% chance of failing.
      const request$ = timer(1000).pipe(
        // Simulate a 1-second delay for the API
        mergeMap(() => {
          const isSuccess = Math.random() > 0.5;
          return isSuccess
            ? of({ data: 'API request succeeded!' })
            : throwError(() => new Error('API request failed'));
        })
      );

      // Counter to track retry attempts
      let retryCount = 0;

      const result$ = request$.pipe(
        tap({
          error: () => {
            retryCount++;
            console.log(`Retry attempt: ${retryCount}`);
          },
        }),
        retry(3), // Retry up to 3 times before failing
        catchError((err) => {
          console.error('Final Error: ', err.message);
          return of({ error: 'All API requests failed' }); // Return fallback value on final failure
        })
      );

      result$.subscribe({
        next: (result) => {
          console.log(result); // Log successful response or fallback value
        },
        error: (error) => {
          console.error(error); // Log any unexpected errors
        },
      });
    }
    ```

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

#### Hard

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
