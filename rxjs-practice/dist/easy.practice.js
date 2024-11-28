"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.replayLastEmittedValueWithShareReplay = replayLastEmittedValueWithShareReplay;
exports.basicTimerWithInterval = basicTimerWithInterval;
exports.throttleClickEvents = throttleClickEvents;
exports.usingTakeOperator = usingTakeOperator;
exports.combiningObservablesWithZip = combiningObservablesWithZip;
exports.combiningObservablesWithConcat = combiningObservablesWithConcat;
exports.errorHandlingWithCatchError = errorHandlingWithCatchError;
exports.filterEvenNumbers = filterEvenNumbers;
exports.debounceSearchInput = debounceSearchInput;
exports.multipleByTwoObservable = multipleByTwoObservable;
exports.collectEvenNumbersFromArray = collectEvenNumbersFromArray;
exports.collectEvenNumbersFromObject = collectEvenNumbersFromObject;
exports.collectEvenNumbersFromPromise = collectEvenNumbersFromPromise;
exports.exampleObservable = exampleObservable;
// src/practice.ts
const rxjs_1 = require("rxjs");
const mock_keystroke_1 = require("./utils/mock.keystroke");
/**
 *
 * Replay Last Emitted Value with shareReplay
    •	Use shareReplay to create a shared observable that replays the last emitted value to new subscribers.
 */
function replayLastEmittedValueWithShareReplay() {
    const shared$ = (0, rxjs_1.interval)(1000).pipe((0, rxjs_1.take)(6), (0, rxjs_1.shareReplay)(3), (0, rxjs_1.toArray)());
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
function basicTimerWithInterval() {
    const numbers = (0, rxjs_1.interval)(1000);
    const takeFourNumbers = numbers.pipe((0, rxjs_1.take)(4), (0, rxjs_1.toArray)());
    return (0, rxjs_1.firstValueFrom)(takeFourNumbers);
}
/**
 * Throttle Click Events
    •	Listen to click events on a button and throttle them to emit one event every 1 second.
*/
function throttleClickEvents() {
    const nums$ = (0, rxjs_1.of)(1, 2, 3, 4, 5);
    // Create an observable that emits 6 after 2 seconds
    const delayed6$ = (0, rxjs_1.of)(6).pipe((0, rxjs_1.tap)(() => {
        console.log('Waiting!!!');
    }), (0, rxjs_1.delay)(2000));
    // Combine the original observable with the delayed value
    const newNums$ = (0, rxjs_1.concat)(nums$, delayed6$);
    const result$ = newNums$.pipe((0, rxjs_1.tap)((num) => {
        console.log(num);
    }), (0, rxjs_1.throttle)(() => (0, rxjs_1.interval)(1000)), (0, rxjs_1.toArray)());
    return (0, rxjs_1.firstValueFrom)(result$);
}
/**
 *
 * Using take Operator
 * Create a stream that emits numbers from 1 to 10 and use take(5) to get only the first five values.
 */
function usingTakeOperator() {
    const nums$ = (0, rxjs_1.of)(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    const result$ = nums$.pipe((0, rxjs_1.tap)((num) => {
        console.log(num);
    }), (0, rxjs_1.take)(5), (0, rxjs_1.toArray)());
    return (0, rxjs_1.firstValueFrom)(result$);
}
/**
 * Combining Observables with zip
    •	Combine two observables using concat to emit values sequentially.
 */
function combiningObservablesWithZip() {
    const letter$ = (0, rxjs_1.of)('a', 'b');
    const num$ = (0, rxjs_1.of)(1, 2);
    const result$ = (0, rxjs_1.zip)(letter$, num$).pipe((0, rxjs_1.tap)(([letter, num]) => {
        console.log('letter ', letter, 'num', num);
    }), (0, rxjs_1.map)(([letter, num]) => {
        return letter + num.toString();
    }));
    return (0, rxjs_1.lastValueFrom)(result$);
}
/**
 * Combining Observables with concat
    •	Combine two observables using concat to emit values sequentially.
 */
function combiningObservablesWithConcat() {
    const letter$ = (0, rxjs_1.of)('a', 'b');
    const num$ = (0, rxjs_1.of)(1, 2);
    const result$ = (0, rxjs_1.concat)(letter$, num$).pipe(
    //tap(ele => console.log(ele)),
    (0, rxjs_1.toArray)());
    return (0, rxjs_1.lastValueFrom)(result$);
}
/**
 * Error Handling with catchError
    •	Create a stream that throws an error and use catchError to handle it gracefully by returning a default value.
*/
function errorHandlingWithCatchError() {
    const nums$ = (0, rxjs_1.of)(1, 2, 3, 4, 5, 6 / 0);
    const result$ = nums$.pipe((0, rxjs_1.map)((num) => {
        if (!isFinite(num)) {
            throw new Error('Infinity detected!');
        }
        return num;
    }), (0, rxjs_1.catchError)((err) => {
        console.error('Error Caught:', err.message);
        return (0, rxjs_1.of)('Handled Infinity');
    }), (0, rxjs_1.toArray)());
    return (0, rxjs_1.firstValueFrom)(result$);
}
/**
 * Filtering Even Numbers
    •	Use filter to emit only even numbers from a stream.
*/
function filterEvenNumbers() {
    const nums$ = (0, rxjs_1.of)(1, 2, 3, 4, 5);
    return nums$.pipe((0, rxjs_1.filter)((num) => num % 2 == 0), (0, rxjs_1.toArray)());
}
/**
 * 3. Easy: Debouncing a Search Input
    •	Implement a debounced search input that emits a search term after a 500ms delay.
 *
*/
function debounceSearchInput() {
    const input = (0, mock_keystroke_1.mockUserInputs)();
    return input.pipe((0, rxjs_1.debounceTime)(500), (0, rxjs_1.distinctUntilChanged)());
}
/**
 * 	2.	Easy: Simple Transformation with map
    •	Use map to multiply each emitted value in a stream by 2.
 *
 */
function multipleByTwoObservable() {
    return (0, rxjs_1.of)([1, 2, 3, 4, 5]).pipe((0, rxjs_1.map)((numArray) => numArray.map((ele) => ele * 2)));
}
function collectEvenNumbersFromArray() {
    const result = [];
    const arr$ = (0, rxjs_1.of)([1, 2, 3, 4, 5]);
    arr$.subscribe((arr) => {
        arr.filter((num) => num % 2 == 0).forEach((num) => result.push(num));
    });
    return result;
}
function collectEvenNumbersFromObject() {
    const result = [];
    const inputObj = {
        arr: [1, 2, 3, 4],
        isEven: true,
    };
    const numObj$ = (0, rxjs_1.of)(inputObj);
    numObj$.subscribe((numObj) => {
        numObj.arr
            .filter((num) => numObj.isEven && num % 2 == 0)
            .forEach((num) => result.push(num));
    });
    return result;
}
function collectEvenNumbersFromPromise() {
    return fetchNumber().then((nums) => nums.filter((num) => num % 2 == 0));
}
async function fetchNumber() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve([1, 2, 3, 4, 5]);
        }, 1000);
    });
}
function exampleObservable() {
    return (0, rxjs_1.of)('Hello, RxJS!');
}
