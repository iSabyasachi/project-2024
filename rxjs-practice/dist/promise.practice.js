"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getData = getData;
exports.getDataAndHandleError = getDataAndHandleError;
exports.getDataWithObservable = getDataWithObservable;
const rxjs_1 = require("rxjs");
async function getData() {
    const response = await fetch('https://example.com');
    return response.text();
}
async function getDataAndHandleError(url) {
    return new Promise(async (resolve, reject) => {
        try {
            setTimeout(async () => {
                const response = await fetch(url);
                return resolve(await response.text());
            }, 1000);
        }
        catch (error) {
            return reject('' + error);
        }
    });
}
function getDataWithObservable(url) {
    const response = getDataAndHandleError(url);
    return (0, rxjs_1.from)(response);
}
