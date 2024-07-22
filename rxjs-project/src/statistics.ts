import { catchError, forkJoin, Observable, of, Subject, switchMap, take, takeUntil, tap } from "rxjs";

interface Statistics {
    nums: number[];
    sum: number;
    average: number;
    sqreOfNums: number[];
} 

function numbersService(): Observable<number[]>{
    return of([1, 2, 3, 4, 5]);
}

function sqreOfNumbers(nums$: Observable<number[]>): Observable<number[]>{
    return nums$.pipe(
        switchMap(nums => {
            const sqreOfNums = nums.map(num => num * num);
            return of(sqreOfNums);
        }));
}

function sumOfNumbers(numbers: number[]): Observable<number>{
    return of(numbers.reduce((acc, num) => acc + num, 0));
}

function avgOfNumbers(numbers: number[]): Observable<number>{
    return of(numbers.reduce((acc, num) => acc + num, 0) / numbers.length);
}
const unsubscribe$ = new Subject<void>();

main();

function main(){
    const statistics$ = prepareStatistics();
    statistics$.subscribe(statistics => {
        console.log("Nums: ", JSON.stringify(statistics.nums), 
        ", Sum: ", statistics.sum, ", Average: ", statistics.average,
        ", Square of Nums: ", statistics.sqreOfNums
    );
    });
}

function prepareStatistics(): Observable<Statistics>{
    return numbersService().pipe(
        takeUntil(unsubscribe$),
        switchMap(nums => {
            return forkJoin({
                sum: sumOfNumbers(nums),
                avg: avgOfNumbers(nums),
                sqreOfNumbers: sqreOfNumbers(of(nums))
            }).pipe(
                take(1),
                switchMap(result => {
                    return of(<Statistics>{
                        nums: nums,
                        sum: result.sum,
                        average: result.avg,
                        sqreOfNums: result.sqreOfNumbers
                    });
                }),
                catchError((err) => {
                    console.error(`Caught error: ${err.message}`);
                    return of();
                })
            );
        }),
    );

}
