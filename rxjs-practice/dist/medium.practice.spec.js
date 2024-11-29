"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const medium_practice_1 = require("./medium.practice");
test('autoCompleteSearchWithSwitchMap', (done) => {
    const actual$ = (0, medium_practice_1.autoCompleteSearchWithSwitchMap)();
    actual$.subscribe((value) => {
        expect(value).toEqual(['abc-result']);
        done();
    });
});
