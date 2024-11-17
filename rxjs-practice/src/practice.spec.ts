
import { exampleObservable } from './practice';
import { take } from 'rxjs/operators';

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