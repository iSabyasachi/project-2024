import {
  autoCompleteSearchWithSwitchMap,
  bufferClickEvents,
  conditionalEmissionWithTakeUntil,
  conditionalEmissionWithTakeWhile,
} from './medium.practice';

test('conditionalEmissionWithTakeUntil', async () => {
  const actual = await conditionalEmissionWithTakeUntil();

  expect(actual).toEqual([0, 1, 2, 3]);
});

test('conditionalEmissionWithTakeWhile', async () => {
  const actual = await conditionalEmissionWithTakeWhile();

  expect(actual).toEqual([1, 2, 3, 4, 5]);
});

test('bufferClickEvents', async () => {
  const actual = await bufferClickEvents();

  expect(actual).toEqual([
    ['a', 'b', 'c'],
    ['c', 'd', 'e'],
    ['e', 'f'],
  ]);
});

test('autoCompleteSearchWithSwitchMap', (done) => {
  const actual$ = autoCompleteSearchWithSwitchMap();

  actual$.subscribe((value) => {
    expect(value).toEqual(['abc-result']);
    done();
  });
});
