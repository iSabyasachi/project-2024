"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const promise_practice_1 = require("./promise.practice");
test('getData', async () => {
    const actual = await (0, promise_practice_1.getData)();
    expect(actual.includes('Example Domain')).toBeTruthy();
});
test('getDataAndHandleError', async () => {
    const actual = await (0, promise_practice_1.getDataAndHandleError)('https://example.com');
    expect(actual.includes('Example Domain')).toBeTruthy();
});
// test('getDataAndHandleError should reject with error message', async () => {
//   await expect(getDataAndHandleError('https://eeeexample.com')).rejects.toEqual(
//     'TypeError: fetch failed'
//   );
// });
test('getDataWithObservable', (done) => {
    const actual = (0, promise_practice_1.getDataWithObservable)('https://example.com');
    actual.subscribe((value) => {
        expect(value.includes('Example Domain')).toBeTruthy();
        done();
    });
});
