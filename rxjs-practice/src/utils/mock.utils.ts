import { delay, from, of } from 'rxjs';

// Mock user input stream
export const simulatedUserInput$ = from(['a', 'ab', 'abc']).pipe(
  // Simulate user typing with some delay between inputs
  delay(300) // Simulates time between keypresses
);

export const mockHttpRequest = (query: string) => {
  console.log(`Sending request for: ${query}`);
  return of([`${query}-result1`, `${query}-result2`, `${query}-result3`]).pipe(
    delay(500) // Simulate network latency
  );
};
