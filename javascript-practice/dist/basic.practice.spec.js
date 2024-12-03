"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const basic_practice_1 = require("./basic.practice");
test("reverseAString", () => {
    expect((0, basic_practice_1.reverseAString)("hello")).toEqual("olleh");
});
test("checkForPlaindrome", () => {
    expect((0, basic_practice_1.checkForPlaindrome)("hello")).toBeFalsy();
    expect((0, basic_practice_1.checkForPlaindrome)("Madam")).toBeTruthy();
    expect((0, basic_practice_1.checkForPlaindrome)("Mada m")).toBeTruthy();
    expect((0, basic_practice_1.checkForPlaindrome)("Mada,m")).toBeTruthy();
});
test("sumOfAnArray", () => {
    expect((0, basic_practice_1.sumOfAnArray)([])).toEqual(0);
    expect((0, basic_practice_1.sumOfAnArray)([1])).toEqual(1);
    expect((0, basic_practice_1.sumOfAnArray)([1, 2, 3])).toEqual(6);
});
test("countVowelsInAString", () => {
    expect((0, basic_practice_1.countVowelsInAString)("")).toEqual(0);
    expect((0, basic_practice_1.countVowelsInAString)("hello")).toEqual(2);
    expect((0, basic_practice_1.countVowelsInAString)("bcd")).toEqual(0);
});
test("filterEvenNumbers", () => {
    expect((0, basic_practice_1.filterEvenNumbers)([1, 2, 3, 4])).toEqual([2, 4]);
});
