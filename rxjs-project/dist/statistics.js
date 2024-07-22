"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const rxjs_1 = require("rxjs");
function numbersService() {
    return (0, rxjs_1.of)([1, 2, 3, 4, 5]);
}
function sqreOfNumbers(nums$) {
    return nums$.pipe((0, rxjs_1.switchMap)(nums => {
        const sqreOfNums = nums.map(num => num * num);
        return (0, rxjs_1.of)(sqreOfNums);
    }));
}
function sumOfNumbers(numbers) {
    return (0, rxjs_1.of)(numbers.reduce((acc, num) => acc + num, 0));
}
function avgOfNumbers(numbers) {
    return (0, rxjs_1.of)(numbers.reduce((acc, num) => acc + num, 0) / numbers.length);
}
const unsubscribe$ = new rxjs_1.Subject();
main();
function main() {
    const statistics$ = prepareStatistics();
    statistics$.subscribe(statistics => {
        console.log("Nums: ", JSON.stringify(statistics.nums), ", Sum: ", statistics.sum, ", Average: ", statistics.average, ", Square of Nums: ", statistics.sqreOfNums);
    });
}
function prepareStatistics() {
    return numbersService().pipe((0, rxjs_1.takeUntil)(unsubscribe$), (0, rxjs_1.switchMap)(nums => {
        return (0, rxjs_1.forkJoin)({
            sum: sumOfNumbers(nums),
            avg: avgOfNumbers(nums),
            sqreOfNumbers: sqreOfNumbers((0, rxjs_1.of)(nums))
        }).pipe((0, rxjs_1.take)(1), (0, rxjs_1.switchMap)(result => {
            return (0, rxjs_1.of)({
                nums: nums,
                sum: result.sum,
                average: result.avg,
                sqreOfNums: result.sqreOfNumbers
            });
        }), (0, rxjs_1.catchError)((err) => {
            console.error(`Caught error: ${err.message}`);
            return (0, rxjs_1.of)();
        }));
    }));
}
