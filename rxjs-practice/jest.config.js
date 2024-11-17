// /** @type {import('ts-jest').JestConfigWithTsJest} **/
// module.exports = {
//   testEnvironment: "node",
//   transform: {
//     "^.+.tsx?$": ["ts-jest",{}],
//   },
// };

module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'node',
  testMatch: ['**/*.spec.ts'],
};