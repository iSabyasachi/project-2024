"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const easy_practice_1 = require("./easy.practice");
const operators_1 = require("rxjs/operators");
test('replayLastEmittedValueWithShareReplay', (done) => {
    (0, easy_practice_1.replayLastEmittedValueWithShareReplay)();
    done();
});
test('basicTimerWithInterval', async () => {
    const actual = await (0, easy_practice_1.basicTimerWithInterval)();
    expect(actual).toEqual([0, 1, 2, 3]);
});
test('throttleClickEvents', async () => {
    const actual = await (0, easy_practice_1.throttleClickEvents)();
    expect(actual).toEqual([1, 6]);
});
test('usingTakeOperator', async () => {
    const actual = await (0, easy_practice_1.usingTakeOperator)();
    expect(actual).toEqual([1, 2, 3, 4, 5]);
});
test('combiningObservablesWithZip', async () => {
    const actual = await (0, easy_practice_1.combiningObservablesWithZip)();
    expect(actual).toEqual('b2');
});
test('combiningObservablesWithConcat', async () => {
    const actual = await (0, easy_practice_1.combiningObservablesWithConcat)();
    expect(actual).toEqual(['a', 'b', 1, 2]);
});
test('errorHandlingWithCatchError', async () => {
    const actual = await (0, easy_practice_1.errorHandlingWithCatchError)();
    expect(actual).toEqual([1, 2, 3, 4, 5, 'Handled Infinity']);
});
test('filterEvenNumbers', (done) => {
    const result$ = (0, easy_practice_1.filterEvenNumbers)();
    result$.subscribe((value) => {
        expect(value).toEqual([2, 4]);
        done();
    });
});
test('debounceSearchInput', (done) => {
    const actual = (0, easy_practice_1.debounceSearchInput)();
    actual.subscribe((value) => {
        expect(value).toContain('Hel');
        done();
    });
});
test('multipleByTwoObservable', (done) => {
    (0, easy_practice_1.multipleByTwoObservable)().subscribe({
        next: (value) => {
            expect(value).toEqual([2, 4, 6, 8, 10]);
        },
        complete: () => {
            done();
        },
    });
});
describe('Easy: 1. Basic Stream Creation and Subscription', () => {
    test('collectEvenNumbersFromArray', (done) => {
        const actual = (0, easy_practice_1.collectEvenNumbersFromArray)();
        expect(actual).toEqual([2, 4]);
        done();
    });
    test('collectEvenNumbersFromObject', (done) => {
        const actual = (0, easy_practice_1.collectEvenNumbersFromObject)();
        expect(actual).toEqual([2, 4]);
        done();
    });
    test('collectEvenNumbersFromPromise', async () => {
        const actual = await (0, easy_practice_1.collectEvenNumbersFromPromise)();
        expect(actual).toEqual([2, 4]);
    });
});
test('exampleObservable should emit "Hello, RxJS!"', (done) => {
    (0, easy_practice_1.exampleObservable)()
        .pipe((0, operators_1.take)(1))
        .subscribe({
        next: (value) => {
            expect(value).toBe('Hello, RxJS!');
            done();
        },
        error: done,
    });
});
