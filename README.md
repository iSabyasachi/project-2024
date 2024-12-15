# project-2024

```
import { DateTime } from 'luxon';

/**ONLY FOR TESTING */
export function compare(first: DateTime, second: DateTime) {
  return first.equals(second);
}

type ComparisonFunction = (first: DateTime, second: DateTime) => boolean;

interface CompareOptions {
  default?: boolean; // Whether to adjust to UTC and startOf('day')
  strict?: boolean; // Whether to compare the objects as is (no adjustments)
  formatter?: string; // Formatter for parsing string inputs (default: 'YYYY-MM-DD')
  locale?: string; // Add locale option
}

/**
 * Compares two DateTime, Date, or string objects using a provided comparison function.
 * 
 * This utility function normalizes the input objects into Luxon `DateTime` objects based on 
 * the provided options (`opts`). By default, it adjusts the inputs to UTC and to the start 
 * of the day to ensure consistent comparisons, regardless of timezones or partial timestamps. 
 * The function is flexible and supports various comparison operations like equality, 
 * greater-than, less-than, etc., through the `compareFunc` parameter.
 * 
 * **Supported Input Types:**
 * - `DateTime` (Luxon)
 * - `Date` (JavaScript Date object)
 * - `string` (parsed using the specified `formatter` in `opts`, default: `'yyyy-MM-dd'`)
 * 
 * **Options (`opts`):**
 * - `default` (boolean, default: `true`): Normalizes the inputs to UTC and adjusts them to 
 *   the start of the day before comparison.
 * - `strict` (boolean, default: `false`): Compares the inputs as-is, without any adjustments.
 *   Cannot be used together with `default`.
 * - `formatter` (string, default: `'yyyy-MM-dd'`): Specifies the format to parse string inputs. 
 * **Defaults:** The function uses the `en-US` locale if no locale is explicitly set.
 * @param {CompareFunc} func - A callable function that defines the comparison logic.
Accepted values include:
- `eq` (equal)
- `ge` (greater than or equal)
- `gt` (greater than)
- `le` (less than or equal)
- `lt` (less than)
 * @param {DateTime | Date | string} first - The first input to be compared.
 * @param {DateTime | Date | string} second - The second input to be compared.
 * @param {CompareOptions} [opts] - Options to control normalization and parsing behavior:
- `default` (boolean): Normalize to UTC and adjust to the start of the day (default: `true`).
- `strict` (boolean): Compare inputs as-is, without adjustments (default: `false`).
- `formatter` (string): Specifies the format to parse string inputs (default: `'yyyy-MM-dd'`).
 * @returns {boolean} - Returns `true` if the comparison condition is met, otherwise `false`.
 * @throws {TypeError} - Throws an error if the input type is invalid (not `DateTime`, `Date`, or `string`).
 * @throws {Error} - Throws an error if both `default` and `strict` options are set to `true`.
 * @example 
const first = DateTime.now().setZone("America/New_York").minus({ weeks: 1 }).startOf("day");
const second = DateTime.now().setZone("America/New_York").minus({ weeks: 1 });

// Default behavior: Adjust to UTC and start of the day
const isEqual = compareDate(eq, first, second);
console.log(isEqual); // true

// Strict comparison: No adjustments
const isStrictEqual = compareDate(eq, first, second, { strict: true });
console.log(isStrictEqual); // false

// Custom formatter for string inputs
const isFormattedEqual = compareDate(eq, "14-12-2024", "14-12-2024", { 
  formatter: "dd-MM-yyyy"
});
console.log(isFormattedEqual); // true

// Custom behavior: Adjust to UTC but not start of the day
const isAdjustedEqual = compareDate(eq, first, second, { default: false, strict: false });
console.log(isAdjustedEqual); // false
 */
export function compareDateTime(
  callbackfn: ComparisonFunction,
  first: DateTime | Date | string,
  second: DateTime | Date | string,
  opts: CompareOptions = { default: true, formatter: 'yyyy-MM-dd' } // Default comparison uses UTC startOf('day')
): boolean {
  const {
    default: useDefault = !opts.strict,
    strict = false,
    formatter = 'yyyy-MM-dd',
    locale,
  } = opts;

  // Helper function to normalize inputs
  const normalizeToDateTime = (input: DateTime | Date | string): DateTime => {
    if (input instanceof DateTime) return input;
    else if (input instanceof Date) return DateTime.fromJSDate(input);
    else if (typeof input === 'string') {
      const parsedDate = DateTime.fromFormat(input, formatter, {
        locale: opts.locale || 'en-US',
      });
      if (!parsedDate.isValid) {
        throw new Error(
          `Invalid date string: "${input}". Expected format: "${formatter}".`
        );
      }
      return parsedDate;
    } else
      throw new Error(
        'Invalid input type: Input must be DateTime, Date, or string.'
      );
  };

  const firstDateTime = normalizeToDateTime(first);
  const secondDateTime = normalizeToDateTime(second);

  // Validate options to avoid conflicting flags
  if (useDefault && strict) {
    throw new Error("Options 'default' and 'strict' cannot both be true.");
  }

  // Adjust inputs based on options
  const adjustDateTime = (dt: DateTime): DateTime => {
    if (strict) return dt;
    return useDefault ? dt.toUTC().startOf('day') : dt.toUTC();
  };

  const adjustedFirst = adjustDateTime(firstDateTime);
  const adjustedSecond = adjustDateTime(secondDateTime);

  // Perform the comparison
  return callbackfn(adjustedFirst, adjustedSecond);
}

/**
 * `eq` (equal)
 * @param first
 * @param second
 * @returns
 * */
export const eq: ComparisonFunction = (
  first: DateTime,
  second: DateTime
): boolean => {
  return first.equals(second);
};

/**
 * `ge` (greater than or equal)
 * @param first
 * @param second
 * @returns
 */
export const ge: ComparisonFunction = (
  first: DateTime,
  second: DateTime
): boolean => {
  return first >= second;
};

/**
 * `gt` (greater than)
 * @param first
 * @param second
 * @returns
 */
export const gt: ComparisonFunction = (
  first: DateTime,
  second: DateTime
): boolean => {
  return first > second;
};

/**
 * `le` (less than or equal)
 * @param first
 * @param second
 * @returns
 */
export const le: ComparisonFunction = (
  first: DateTime,
  second: DateTime
): boolean => {
  return first <= second;
};

/**
 * `lt` (less than)
 * @param first
 * @param second
 * @returns
 */
export const lt: ComparisonFunction = (
  first: DateTime,
  second: DateTime
): boolean => {
  return first < second;
};

```
```
import { DateTime } from 'luxon';
import { compare, compareDateTime, eq, ge, gt, le, lt } from './datetime.util';

describe('compare date and time', () => {
  test('should return false without adjusting the date', () => {
    const first = DateTime.now()
      .setZone('America/New_York')
      .minus({ weeks: 1 })
      .startOf('day'); //DateTime { ts: 2024-12-07T00:00:00.000-05:00, zone: America/New_York, locale: en-US }
    const second = DateTime.now()
      .setZone('America/New_York')
      .minus({ weeks: 1 }); //DateTime { ts: 2024-12-07T21:23:28.324-05:00, zone: America/New_York, locale: en-US }

    expect(compare(first, second)).toEqual(false);
  });

  test('should return true adjusting the date using compareDateTime function', () => {
    const first = DateTime.now()
      .setZone('America/New_York')
      .minus({ weeks: 1 })
      .startOf('day');
    const second = DateTime.now()
      .setZone('America/New_York')
      .minus({ weeks: 1 });
    console.log('first ', first); //DateTime { ts: 2024-12-07T00:00:00.000-05:00, zone: America/New_York, locale: en-US }
    console.log('second ', second); //DateTime { ts: 2024-12-07T21:23:28.324-05:00, zone: America/New_York, locale: en-US }

    expect(compareDateTime(eq, first, second)).toEqual(true);
  });

  test('should handle different operations', () => {
    const first = DateTime.now()
      .setZone('America/New_York')
      .minus({ weeks: 1 })
      .startOf('day');
    const second = DateTime.now()
      .setZone('America/New_York')
      .minus({ weeks: 1 });
    const earlier = DateTime.now().minus({ days: 1 });
    const later = DateTime.now();

    expect(compareDateTime(eq, first, second)).toEqual(true); // Adjusted comparison to UTC's startOf('day')
    expect(compareDateTime(ge, later, earlier)).toEqual(true); // greater or equal
    expect(compareDateTime(gt, later, earlier)).toEqual(true); // strictly greater
    expect(compareDateTime(le, earlier, later)).toEqual(true); // lesser or equal
    expect(compareDateTime(lt, earlier, later)).toEqual(true); // strictly lesser
  });

  describe('compare date and time with default option', () => {
    test('should handle comparing date with default specified', () => {
      const first = DateTime.now()
        .setZone('America/New_York')
        .minus({ weeks: 1 })
        .startOf('day');
      const second = DateTime.now()
        .setZone('America/New_York')
        .minus({ weeks: 1 });

      expect(compareDateTime(eq, first, second, { default: true })).toEqual(
        true
      ); // Adjusted comparison to UTC's startOf('day')
    });
    test('should handle comparing date without default specified', () => {
      const first = DateTime.now()
        .setZone('America/New_York')
        .minus({ weeks: 1 })
        .startOf('day');
      const second = DateTime.now()
        .setZone('America/New_York')
        .minus({ weeks: 1 });

      expect(compareDateTime(eq, first, second)).toEqual(true); // Adjusted comparison to UTC's startOf('day')
    });
  });

  describe('compare date and time with strict option', () => {
    test('should return false when comparing date with strict options', () => {
      const first = DateTime.now()
        .setZone('America/New_York')
        .minus({ weeks: 1 })
        .startOf('day');
      const second = DateTime.now()
        .setZone('America/New_York')
        .minus({ weeks: 1 });

      expect(compareDateTime(eq, first, second, { strict: true })).toEqual(
        false
      ); // Adjusted comparison to UTC's startOf('day')
    });
    test('should return true when DateTime objects are identical and strict is true', () => {
      const first = DateTime.now();
      const second = first; // Exact same DateTime object

      const result = compareDateTime(eq, first, second, { strict: true });
      expect(result).toBe(true);
    });

    test('should return false when DateTime objects differ by milliseconds with strict option', () => {
      const first = DateTime.now();
      const second = first.plus({ milliseconds: 1 });

      const result = compareDateTime(eq, first, second, { strict: true });
      expect(result).toBe(false);
    });

    test("should not adjust DateTime objects to startOf('day') in strict mode", () => {
      const first = DateTime.now().startOf('day');
      const second = DateTime.now(); // Not adjusted to startOf('day')

      const result = compareDateTime(eq, first, second, { strict: true });
      expect(result).toBe(false); // Should fail because no adjustments occur
    });

    test('should throw error when both strict and default are true', () => {
      const first = DateTime.now();
      const second = DateTime.now();

      const options = { strict: true, default: true };

      expect(() => {
        compareDateTime(eq, first, second, options);
      }).toThrow("Options 'default' and 'strict' cannot both be true.");
    });

    test('should adjust DateTime to only zone "UTC" when both strict and default are false', () => {
      const first = DateTime.now();
      const second = first.plus({ milliseconds: 1 });

      const result = compareDateTime(eq, first, second, {
        default: false,
        strict: false,
      });
      expect(result).toBe(false);
    });

    test('should handle strict mode correctly with other comparison functions', () => {
      const first = DateTime.now();
      const second = first.minus({ days: 1 });

      // Test greater than
      const isGreater = compareDateTime((a, b) => a > b, first, second, {
        strict: true,
      });
      expect(isGreater).toBe(true);

      // Test less than
      const isLess = compareDateTime((a, b) => a < b, second, first, {
        strict: true,
      });
      expect(isLess).toBe(true);
    });
  });

  describe('compare date and time with JavaScript Date and Luxon DateTime', () => {
    test('should handle JavaScript Date objects', () => {
      const first = new Date('2024-12-14T23:59:59.999Z');
      const second = new Date('2024-12-14T12:00:00.000Z');

      const result = compareDateTime(eq, first, second);
      expect(result).toBe(true); // Same day when normalized to startOf('day')
    });

    test('should handle mixed input types (DateTime and Date)', () => {
      const first = DateTime.fromISO('2024-12-14T12:00:00.000Z');
      const second = new Date('2024-12-14T23:59:59.999Z');

      const result = compareDateTime(eq, first, second);
      expect(result).toBe(true); // Same day when normalized to startOf('day')
    });

    test('should handle strict comparisons with mixed input types', () => {
      const first = DateTime.fromISO('2024-12-14T12:00:00.000Z');
      const second = new Date('2024-12-14T23:59:59.999Z');

      const result = compareDateTime(eq, first, second, { strict: true });
      expect(result).toBe(false); // Exact timestamps differ
    });
  });

  describe('compare date and time with string input type', () => {
    test('should handle string inputs with default formatter', () => {
      const first = '2024-12-14';
      const second = '2024-12-14';

      const result = compareDateTime(eq, first, second);
      expect(result).toBe(true); // Same day
    });

    test('should handle mixed input types (DateTime, Date, and string)', () => {
      const first = DateTime.fromISO('2024-12-14');
      const second = new Date('2024-12-14T00:00:00.000Z');
      const third = '2024-12-14';

      // Mixed types
      expect(compareDateTime(eq, first, second)).toBe(true);
      expect(compareDateTime(eq, first, third)).toBe(true);
    });

    test('should handle custom formatter for string inputs', () => {
      const first = '14-12-2024'; // Custom format
      const second = '14-12-2024';

      const result = compareDateTime(eq, first, second, {
        default: true,
        formatter: 'dd-MM-yyyy', // Custom formatter
      });
      expect(result).toBe(true);
    });

    test('should throw error when invalid input string provided', () => {
      expect(() => {
        compareDateTime(eq, '', '');
      }).toThrow('Invalid date string: "". Expected format: "yyyy-MM-dd".');
    });
  });
});

```
