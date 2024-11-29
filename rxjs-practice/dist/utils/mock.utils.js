"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.mockHttpRequest = exports.simulatedUserInput$ = void 0;
const rxjs_1 = require("rxjs");
// Mock user input stream
exports.simulatedUserInput$ = (0, rxjs_1.from)(['a', 'ab', 'abc']).pipe(
// Simulate user typing with some delay between inputs
(0, rxjs_1.delay)(300) // Simulates time between keypresses
);
const mockHttpRequest = (query) => {
    console.log(`Sending request for: ${query}`);
    return (0, rxjs_1.of)([`${query}-result1`, `${query}-result2`, `${query}-result3`]).pipe((0, rxjs_1.delay)(500) // Simulate network latency
    );
};
exports.mockHttpRequest = mockHttpRequest;
