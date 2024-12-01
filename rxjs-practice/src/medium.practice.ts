import {
  bufferCount,
  catchError,
  debounceTime,
  delay,
  distinctUntilChanged,
  firstValueFrom,
  from,
  interval,
  lastValueFrom,
  mergeMap,
  Observable,
  of,
  retry,
  Subject,
  switchMap,
  takeUntil,
  takeWhile,
  tap,
  throwError,
  timer,
  toArray,
} from 'rxjs';

/**
 * 
 * Retry on Error
    •	Create a stream that simulates an API request, with a 50% chance of failing. 
    Use retry to retry the request up to three times.
 */
export function retryOnError() {
  // Create a stream that simulates an API request, with a 50% chance of failing.
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
    retry(3),
    catchError((err) => {
      console.error('Final Error: ', err.message);
      return of({ error: 'All API requests failed' });
    })
  );

  result$.subscribe({
    next: (result) => {
      console.log(result);
    },
    error: (error) => {
      console.error(error);
    },
  });
}

/**
 * Conditional Emission with takeUntil
    •	Emit values from an observable until a certain condition is met using takeUntil.
 */
export function conditionalEmissionWithTakeUntil(): Promise<number[]> {
  const stop$ = new Subject<void>();
  setTimeout(() => {
    console.log('Stop event emitted.');
    stop$.next();
    stop$.complete();
  }, 500);

  const nums$ = interval(100);

  const results$ = nums$.pipe(takeUntil(stop$), toArray());

  return firstValueFrom(results$);
}

/**
 * Conditional Emission with takeWhile
    •	Emit values from an observable until a certain condition is met using takeWhile.
 */
export function conditionalEmissionWithTakeWhile(): Promise<number[]> {
  const nums$ = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

  const result$ = nums$.pipe(
    takeWhile((num) => num < 6),
    toArray()
  );

  return firstValueFrom(result$);
}

/**
 * 
 * Buffer Click Events
    •	Use bufferCount to collect a certain number of click events before emitting them as an array.
 * 
 */
export function bufferClickEvents(): Promise<string[][]> {
  const clicks$ = of('a', 'b', 'c', 'd', 'e', 'f');

  const result$ = clicks$.pipe(bufferCount(3, 2), toArray());

  return lastValueFrom(result$);
}

/**
 * Implement an Autocomplete Search with switchMap
    •	Build an autocomplete search component that uses switchMap to cancel previous requests when 
      a new search term is entered.
    •	switchMap ensures that whenever a new user input (e.g., "a", "ab", "abc") is emitted, 
      the previous pending mock HTTP request is canceled, and only the latest request for the 
      most recent input is processed.
 */
export function autoCompleteSearchWithSwitchMap(): Observable<string[]> {
  // Mock user input stream
  const mockUserInput$ = from(['a', 'ab', 'abc']).pipe(delay(300));

  // Mock Http request
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
