import {
  debounceTime,
  delay,
  distinctUntilChanged,
  from,
  Observable,
  of,
  switchMap,
} from 'rxjs';

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
