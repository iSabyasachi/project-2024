"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.autoCompleteSearchWithSwitchMap = autoCompleteSearchWithSwitchMap;
const rxjs_1 = require("rxjs");
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
