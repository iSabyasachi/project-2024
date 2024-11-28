"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.mockUserInputs = mockUserInputs;
const rxjs_1 = require("rxjs");
const searchSubject = new rxjs_1.Subject();
function mockUserInputs() {
    simulateKeyStroke('H');
    setTimeout(() => simulateKeyStroke('He'), 100);
    setTimeout(() => simulateKeyStroke('Hel'), 200);
    setTimeout(() => simulateKeyStroke('Hell'), 300);
    setTimeout(() => simulateKeyStroke('Hello'), 1000);
    return searchSubject;
}
function simulateKeyStroke(key) {
    searchSubject.next(key);
}
