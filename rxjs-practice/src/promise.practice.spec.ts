import {
  getData,
  getDataAndHandleError,
  getDataWithObservable,
} from './promise.practice';

test('getData', async () => {
  const actual = await getData();

  expect(actual.includes('Example Domain')).toBeTruthy();
});

test('getDataAndHandleError', async () => {
  const actual = await getDataAndHandleError('https://example.com');

  expect(actual.includes('Example Domain')).toBeTruthy();
});

test('getDataAndHandleError should reject with error message', (done) => {
  const actual = getDataAndHandleError('https://eeeexample.com');

  actual.catch((err) => {
    expect(err).toEqual('TypeError: fetch failed');
  });
  done();
});

test('getDataWithObservable', (done) => {
  const actual = getDataWithObservable('https://example.com');
  actual.subscribe((value) => {
    expect(value.includes('Example Domain')).toBeTruthy();
  });
  done();
});
