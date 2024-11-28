import { getData, getDataAndHandleError, getDataWithObservable } from "./promise.practice";

test('getData', async () => {
    const actual = await getData();
    
    expect(actual.includes('Example Domain')).toBeTruthy();
})

test('getDataAndHandleError', async () => {
    const actual = await getDataAndHandleError('https://example.com');

    expect(actual.includes('Example Domain')).toBeTruthy();
});

test('getDataAndHandleError should reject with error message', async () => {
    await expect(getDataAndHandleError('https://eeeexample.com')).rejects.toEqual('TypeError: fetch failed');
});

test('getDataWithObservable', (done) => {
    const actual = getDataWithObservable('https://example.com');
    actual.subscribe(value => {
        expect(value.includes('Example Domain')).toBeTruthy();
        done();
    });
});