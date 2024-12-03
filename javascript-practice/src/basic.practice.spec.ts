import {
  checkForPlaindrome,
  countVowelsInAString,
  filterEvenNumbers,
  reverseAString,
  sumOfAnArray,
} from "./basic.practice";

test("reverseAString", () => {
  expect(reverseAString("hello")).toEqual("olleh");
});

test("checkForPlaindrome", () => {
  expect(checkForPlaindrome("hello")).toBeFalsy();
  expect(checkForPlaindrome("Madam")).toBeTruthy();
  expect(checkForPlaindrome("Mada m")).toBeTruthy();
  expect(checkForPlaindrome("Mada,m")).toBeTruthy();
});

test("sumOfAnArray", () => {
  expect(sumOfAnArray([])).toEqual(0);
  expect(sumOfAnArray([1])).toEqual(1);
  expect(sumOfAnArray([1, 2, 3])).toEqual(6);
});

test("countVowelsInAString", () => {
  expect(countVowelsInAString("")).toEqual(0);
  expect(countVowelsInAString("hello")).toEqual(2);
  expect(countVowelsInAString("bcd")).toEqual(0);
});

test("filterEvenNumbers", () => {
  expect(filterEvenNumbers([1, 2, 3, 4])).toEqual([2, 4]);
});
