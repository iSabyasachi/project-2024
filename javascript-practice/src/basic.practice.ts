/** Write a function to reverse a given string. */
export function reverseAString(str: string): string {
  return str.split("").reverse().join("");
}

/** Determine if a given string is a palindrome (reads the same forward and backward). */
export function checkForPlaindrome(str: string): boolean {
  // This regex matches any character that is not a lowercase letter or a digit.
  const normalized = str.toLocaleLowerCase().replace(/[^a-z0-9]/g, "");

  return normalized === normalized.split("").reverse().join("");
}

/**Write a function to calculate the sum of numbers in an array. */
export function sumOfAnArray(nums: number[]): number {
  return nums.reduce((acc, num) => acc + num, 0);
}

/**Write a function to count the number of vowels (a, e, i, o, u) in a given string. */
export function countVowelsInAString(str: string): number {
  /*return str.split('').reduce((acc, char) => {
        if(char === 'a' || char === 'e' || char === 'i' || char === 'o' || char === 'u'){
            acc++;
        }
        return acc;
    }, 0);*/

  // / and /: These delimit the start and end of the regular expression.
  // [ and ]: Denote a character set, defining a group of characters to match.
  //Global flag (g): Ensures the regular expression matches all occurrences of vowels in the string, not just the first one.
  //Case-insensitive flag (i): Makes the regex case-insensitive, so it matches both uppercase and lowercase vowels (e.g., ‘A’, ‘E’, ‘I’, ‘O’, ‘U’).

  return str.match(/[aeiou]/gi)?.length || 0;
}

/** Filter out even numbers from an array. */
export function filterEvenNumbers(nums: number[]): number[] {
  return nums.filter((num) => num % 2 === 0);
}
