"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const medium_practice_1 = require("./medium.practice");
test('conditionalEmissionWithTakeUntil', async () => {
    const actual = await (0, medium_practice_1.conditionalEmissionWithTakeUntil)();
    expect(actual).toEqual([0, 1, 2, 3]);
});
test('conditionalEmissionWithTakeWhile', async () => {
    const actual = await (0, medium_practice_1.conditionalEmissionWithTakeWhile)();
    expect(actual).toEqual([1, 2, 3, 4, 5]);
});
test('bufferClickEvents', async () => {
    const actual = await (0, medium_practice_1.bufferClickEvents)();
    expect(actual).toEqual([
        ['a', 'b', 'c'],
        ['c', 'd', 'e'],
        ['e', 'f'],
    ]);
});
test('autoCompleteSearchWithSwitchMap', (done) => {
    const actual$ = (0, medium_practice_1.autoCompleteSearchWithSwitchMap)();
    actual$.subscribe((value) => {
        expect(value).toEqual(['abc-result']);
        done();
    });
});
