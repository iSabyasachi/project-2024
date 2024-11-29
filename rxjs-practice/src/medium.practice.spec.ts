import { autoCompleteSearchWithSwitchMap } from './medium.practice';

test('autoCompleteSearchWithSwitchMap', (done) => {
  const actual$ = autoCompleteSearchWithSwitchMap();

  actual$.subscribe((value) => {
    expect(value).toEqual(['abc-result']);
    done();
  });
});
