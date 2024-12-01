"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.retryOnError = retryOnError;
exports.conditionalEmissionWithTakeUntil = conditionalEmissionWithTakeUntil;
exports.conditionalEmissionWithTakeWhile = conditionalEmissionWithTakeWhile;
exports.bufferClickEvents = bufferClickEvents;
exports.autoCompleteSearchWithSwitchMap = autoCompleteSearchWithSwitchMap;
const rxjs_1 = require("rxjs");
/**
 *
 * Retry on Error
    •	Create a stream that simulates an API request, with a 50% chance of failing.
    Use retry to retry the request up to three times.
 */
function retryOnError() {
    // Create a stream that simulates an API request, with a 50% chance of failing.
    const request$ = (0, rxjs_1.timer)(1000).pipe(
    // Simulate a 1-second delay for the API
    (0, rxjs_1.mergeMap)(() => {
        const isSuccess = Math.random() > 0.5;
        return isSuccess
            ? (0, rxjs_1.of)({ data: 'API request succeeded!' })
            : (0, rxjs_1.throwError)(() => new Error('API request failed'));
    }));
    // Counter to track retry attempts
    let retryCount = 0;
    const result$ = request$.pipe((0, rxjs_1.tap)({
        error: () => {
            retryCount++;
            console.log(`Retry attempt: ${retryCount}`);
        },
    }), (0, rxjs_1.retry)(3), (0, rxjs_1.catchError)((err) => {
        console.error('Final Error: ', err.message);
        return (0, rxjs_1.of)({ error: 'All API requests failed' });
    }));
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
function conditionalEmissionWithTakeUntil() {
    const stop$ = new rxjs_1.Subject();
    setTimeout(() => {
        console.log('Stop event emitted.');
        stop$.next();
        stop$.complete();
    }, 500);
    const nums$ = (0, rxjs_1.interval)(100);
    const results$ = nums$.pipe((0, rxjs_1.takeUntil)(stop$), (0, rxjs_1.toArray)());
    return (0, rxjs_1.firstValueFrom)(results$);
}
/**
 * Conditional Emission with takeWhile
    •	Emit values from an observable until a certain condition is met using takeWhile.
 */
function conditionalEmissionWithTakeWhile() {
    const nums$ = (0, rxjs_1.of)(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    const result$ = nums$.pipe((0, rxjs_1.takeWhile)((num) => num < 6), (0, rxjs_1.toArray)());
    return (0, rxjs_1.firstValueFrom)(result$);
}
/**
 *
 * Buffer Click Events
    •	Use bufferCount to collect a certain number of click events before emitting them as an array.
 *
 */
function bufferClickEvents() {
    const clicks$ = (0, rxjs_1.of)('a', 'b', 'c', 'd', 'e', 'f');
    const result$ = clicks$.pipe((0, rxjs_1.bufferCount)(3, 2), (0, rxjs_1.toArray)());
    return (0, rxjs_1.lastValueFrom)(result$);
}
/**
 * Implement an Autocomplete Search with switchMap
    •	Build an autocomplete search component that uses switchMap to cancel previous requests when
      a new search term is entered.
    •	switchMap ensures that whenever a new user input (e.g., "a", "ab", "abc") is emitted,
      the previous pending mock HTTP request is canceled, and only the latest request for the
      most recent input is processed.
 */
function autoCompleteSearchWithSwitchMap() {
    // Mock user input stream
    const mockUserInput$ = (0, rxjs_1.from)(['a', 'ab', 'abc']).pipe((0, rxjs_1.delay)(300));
    // Mock Http request
    const mockHttprequest = (input) => {
        console.log(`Sending Request for input: ${input}`);
        return (0, rxjs_1.of)([`${input}-result`]).pipe((0, rxjs_1.delay)(500));
    };
    return mockUserInput$.pipe((0, rxjs_1.debounceTime)(300), // Debounce to simulate user pausing typing
    (0, rxjs_1.distinctUntilChanged)(), // Avoid duplicate requests for the same term
    (0, rxjs_1.switchMap)((input) => mockHttprequest(input)));
}
