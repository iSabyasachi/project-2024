
import { error } from 'console';
import { exampleObservable, collectEvenNumbersFromArray, collectEvenNumbersFromObject, collectEvenNumbersFromPromise, multipleByTwoObservable, debounceSearchInput, filterEvenNumbers, errorHandlingWithCatchError, combiningObservablesWithConcat, combiningObservablesWithZip, usingTakeOperator, throttleClickEvents, basicTimerWithInterval, replayLastEmittedValueWithShareReplay } from './easy.practice';
import { take, toArray } from 'rxjs/operators';

test('replayLastEmittedValueWithShareReplay', (done) => {
  replayLastEmittedValueWithShareReplay();
  done()
})

test('basicTimerWithInterval', async () => {
  const actual = await basicTimerWithInterval();

  expect(actual).toEqual([0, 1, 2, 3]);
})

test('throttleClickEvents', async () => {
  const actual = await throttleClickEvents();

  expect(actual).toEqual([1, 6])
});

test('usingTakeOperator', async () => {
  const actual = await usingTakeOperator();

  expect(actual).toEqual([1, 2, 3, 4, 5]);
});

test('combiningObservablesWithZip', async () => {
  const actual = await combiningObservablesWithZip();
  
  expect(actual).toEqual('b2');
});

test('combiningObservablesWithConcat', async () => {
  const actual = await combiningObservablesWithConcat();
  
  expect(actual).toEqual([ 'a', 'b', 1, 2 ]);
});

test('errorHandlingWithCatchError', async () => {
  const actual = await errorHandlingWithCatchError();

  expect(actual).toEqual([1, 2, 3, 4, 5, 'Handled Infinity']);
});
test("filterEvenNumbers", (done) => {
  const result$ = filterEvenNumbers();
  result$.subscribe((value) => {
    expect(value).toEqual([2, 4]);
    done();
  });
});

test('debounceSearchInput', (done) => {
  const actual = debounceSearchInput();
  actual.subscribe(value => {
    expect(value).toContain('Hel');
    done();
  });

});

test('multipleByTwoObservable', (done) => {
  multipleByTwoObservable().subscribe(
    {
      next: (value) => {
        expect(value).toEqual([2, 4, 6, 8, 10])
      },
      complete: () => {
        done();
      }
    }
  );
});

describe('Easy: 1. Basic Stream Creation and Subscription', () => {
  test('collectEvenNumbersFromArray', (done) => {
    const actual = collectEvenNumbersFromArray();
  
    expect(actual).toEqual([2, 4]);
    done();
  });

  test('collectEvenNumbersFromObject', (done) => {
    const actual = collectEvenNumbersFromObject();
  
    expect(actual).toEqual([2, 4]);
    done();
  });
  
  test('collectEvenNumbersFromPromise', async () => {
    const actual = await collectEvenNumbersFromPromise();
  
    expect(actual).toEqual([2, 4]);
  });
});


test('exampleObservable should emit "Hello, RxJS!"', (done) => {
  exampleObservable()
    .pipe(take(1))
    .subscribe({
      next: (value) => {
        expect(value).toBe('Hello, RxJS!');
        done();
      },
      error: done,
    });
});