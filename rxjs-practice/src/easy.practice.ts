// src/practice.ts
import {
  firstValueFrom,
  catchError,
  debounceTime,
  distinctUntilChanged,
  filter,
  map,
  Observable,
  of,
  Subject,
  toArray,
  concat,
  lastValueFrom,
  tap,
  zip,
  take,
  throttle,
  interval,
  delay,
  shareReplay,
} from 'rxjs';
import { mockUserInputs } from './utils/mock.keystroke';

/**
 * 
 * Replay Last Emitted Value with shareReplay
	•	Use shareReplay to create a shared observable that replays the last emitted value to new subscribers.
 */
export function replayLastEmittedValueWithShareReplay() {
  const shared$ = interval(1000).pipe(take(6), shareReplay(3), toArray());

  shared$.subscribe({
    next: (x) => console.log('sub A: ', x),
    complete: () => console.log('sub A completed!'),
  });

  shared$.subscribe({
    next: (y) => console.log('sub B: ', y),
    complete: () => console.log('sub B completed!'),
  });

  setTimeout(() => {
    shared$.subscribe({
      next: (z) => console.log('sub C: ', z),
      complete: () => console.log('sub C completed!'),
    });
  }, 11000);
}

/**
 * Basic Timer with interval
	•	Use interval to create a timer that emits values every second.
 * 
 */
export function basicTimerWithInterval(): Promise<number[]> {
  const numbers = interval(1000);
  const takeFourNumbers = numbers.pipe(take(4), toArray());

  return firstValueFrom(takeFourNumbers);
}

/**
 * Throttle Click Events
	•	Listen to click events on a button and throttle them to emit one event every 1 second.
*/
export function throttleClickEvents(): Promise<number[]> {
  const nums$ = of(1, 2, 3, 4, 5);

  // Create an observable that emits 6 after 2 seconds
  const delayed6$ = of(6).pipe(
    tap(() => {
      console.log('Waiting!!!');
    }),
    delay(2000)
  );

  // Combine the original observable with the delayed value
  const newNums$ = concat(nums$, delayed6$);

  const result$ = newNums$.pipe(
    tap((num) => {
      console.log(num);
    }),
    throttle(() => interval(1000)),
    toArray()
  );

  return firstValueFrom(result$);
}

/**
 *
 * Using take Operator
 * Create a stream that emits numbers from 1 to 10 and use take(5) to get only the first five values.
 */
export function usingTakeOperator(): Promise<number[]> {
  const nums$ = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  const result$ = nums$.pipe(
    tap((num) => {
      console.log(num);
    }),
    take(5),
    toArray()
  );

  return firstValueFrom(result$);
}

/**
 * Combining Observables with zip
	•	Combine two observables using concat to emit values sequentially.
 */
export function combiningObservablesWithZip(): Promise<string> {
  const letter$ = of('a', 'b');
  const num$ = of(1, 2);

  const result$ = zip(letter$, num$).pipe(
    tap(([letter, num]) => {
      console.log('letter ', letter, 'num', num);
    }),
    map(([letter, num]) => {
      return letter + num.toString();
    })
  );

  return lastValueFrom(result$);
}
/**
 * Combining Observables with concat
	•	Combine two observables using concat to emit values sequentially.
 */
export function combiningObservablesWithConcat(): Promise<(string | number)[]> {
  const letter$ = of('a', 'b');
  const num$ = of(1, 2);

  const result$ = concat(letter$, num$).pipe(
    //tap(ele => console.log(ele)),
    toArray()
  );

  return lastValueFrom(result$);
}

/** 
 * Error Handling with catchError
	•	Create a stream that throws an error and use catchError to handle it gracefully by returning a default value.
*/
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
      return of('Handled Infinity');
    }),
    toArray()
  );
  return firstValueFrom(result$);
}

/** 
 * Filtering Even Numbers
	•	Use filter to emit only even numbers from a stream.
*/
export function filterEvenNumbers(): Observable<number[]> {
  const nums$ = of(1, 2, 3, 4, 5);

  return nums$.pipe(
    filter((num) => num % 2 == 0),
    toArray()
  );
}

/** 
 * 3. Easy: Debouncing a Search Input
	•	Implement a debounced search input that emits a search term after a 500ms delay.
 * 
*/
export function debounceSearchInput(): Observable<string> {
  const input: Subject<string> = mockUserInputs();

  return input.pipe(debounceTime(500), distinctUntilChanged());
}

/**
 * 	2.	Easy: Simple Transformation with map
	•	Use map to multiply each emitted value in a stream by 2.
 * 
 */
export function multipleByTwoObservable(): Observable<number[]> {
  return of([1, 2, 3, 4, 5]).pipe(
    map((numArray) => numArray.map((ele) => ele * 2))
  );
}

/**
 * 1. Easy: Basic Stream Creation and Subscription
 * Create an observable from an array, object, and promise. Log each emitted value to the console.
 */
type NumObj = {
  arr: number[];
  isEven: boolean;
};
export function collectEvenNumbersFromArray(): number[] {
  const result: number[] = [];
  const arr$ = of([1, 2, 3, 4, 5]);
  arr$.subscribe((arr) => {
    arr.filter((num) => num % 2 == 0).forEach((num) => result.push(num));
  });
  return result;
}

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

export function collectEvenNumbersFromPromise(): Promise<number[]> {
  return fetchNumber().then((nums) => nums.filter((num) => num % 2 == 0));
}

async function fetchNumber(): Promise<number[]> {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve([1, 2, 3, 4, 5]);
    }, 1000);
  });
}

export function exampleObservable() {
  return of('Hello, RxJS!');
}
