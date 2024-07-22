"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const rxjs_1 = require("rxjs");
const operators_1 = require("rxjs/operators");
// Mocked service data
const mockDealModels = [
    {
        dealId: 1,
        salesforceInstrumentId: 1001,
    },
    {
        dealId: 1,
        salesforceInstrumentId: 1002,
    },
];
const mockPotentialInvestmentModels = [
    {
        potentialInvestmentId: 1,
        dealId: 1,
        orderId: 1,
        fundId: 1,
    },
    {
        potentialInvestmentId: 2,
        dealId: 1,
        orderId: 2,
        fundId: 1
    },
];
const mockSecurityModels = [
    {
        instrumentId: 101,
        salesforceInstrumentId: 1001,
    },
    {
        instrumentId: 102,
        salesforceInstrumentId: 1002,
    }
];
const mockPortfolioModels = [
    {
        fundId: 1,
        fundName: 'Fund1'
    }
];
const mockOrderModels = [
    {
        orderId: 1,
        orderStatus: 'DRAFT'
    },
    {
        orderId: 2,
        orderStatus: 'COMPLETE'
    },
];
//Service
function dealService(sourceId) {
    return (0, rxjs_1.of)(mockDealModels);
    //return of(mockDealModels.filter(mockDealModel => mockDealModel.dealId === sourceId));
}
function potentialInvestmentService(sourceIds) {
    return (0, rxjs_1.of)(mockPotentialInvestmentModels);
    /*const results = sourceIds
    .map(sourceId => {
        const res = mockPotentialInvestmentModels.find(pi => pi.dealId === sourceId);
        if(!res) return;
        return res;
    })
    .filter(ele => ele !== undefined);
    return of(results);*/
}
function securityService(id) {
    return (0, rxjs_1.of)(mockSecurityModels
        .find(mockSecurityModel => mockSecurityModel.salesforceInstrumentId === id));
}
function portfolioService(ids) {
    return (0, rxjs_1.of)(mockPortfolioModels);
    /*const results = ids
    .map(fundId => mockPortfolioModels.find(portfolio => portfolio.fundId === fundId))
    .filter(ele => ele !== undefined);

    return of(results);*/
}
function orderService(ids) {
    return (0, rxjs_1.of)(mockOrderModels);
    /*const results = ids
    .map(orderId => mockOrderModels.find(order => order.orderId === orderId))
    .filter(ele => ele !== undefined);

    return of(results);*/
}
// method1 implementation
function buildPotentialInvestments() {
    const dealService$ = (0, rxjs_1.from)(dealService(1));
    const potentialInvestmentService$ = (0, rxjs_1.from)(potentialInvestmentService([1]));
    const potentialInvestment$ = potentialInvestmentService$.pipe((0, operators_1.switchMap)(potentialInvestments => {
        const portfolioService$ = (0, rxjs_1.from)(portfolioService([1]));
        const orderService$ = (0, rxjs_1.from)(orderService([1, 2]));
        return (0, rxjs_1.forkJoin)([portfolioService$, orderService$]).pipe((0, operators_1.map)(([portfolios, orders]) => {
            const potentialInvestmentResultModel = {
                potentialInvestments: potentialInvestments,
                portfolios: portfolios,
                orders: orders,
            };
            return potentialInvestmentResultModel;
        }));
    }));
    return dealService$.pipe((0, operators_1.filter)(dealArray => !!dealArray), (0, operators_1.switchMap)(dealArray => (0, rxjs_1.from)(dealArray).pipe((0, operators_1.mergeMap)(dealModel => (0, rxjs_1.from)(securityService(dealModel.salesforceInstrumentId)).pipe((0, operators_1.map)(securityModel => ({ dealModel, securityModel })))), (0, operators_1.toArray)(), // Collect all mapped items
    (0, operators_1.map)(mappedItems => {
        const securityMap = mappedItems.reduce((acc, item) => {
            var _a, _b, _c;
            if ((_a = item.securityModel) === null || _a === void 0 ? void 0 : _a.salesforceInstrumentId) {
                acc[(_b = item.securityModel) === null || _b === void 0 ? void 0 : _b.salesforceInstrumentId] = (_c = item.securityModel) === null || _c === void 0 ? void 0 : _c.instrumentId;
            }
            return acc;
        }, {});
        return { mappedItems, securityMap };
    }))), (0, operators_1.switchMap)(({ mappedItems, securityMap }) => potentialInvestment$.pipe((0, operators_1.map)(potentialInvestment => {
        return {
            potentialInvestmentModel: potentialInvestment,
            dealModels: mappedItems.map(mappedItem => mappedItem.dealModel),
            securityMap: securityMap,
        };
    }))));
}
;
// Usage
buildPotentialInvestments().subscribe((result) => {
    console.log("potentialInvestmentModel> ", JSON.stringify(result.potentialInvestmentModel, null, '\t'));
    console.log("\n", "dealModels> ", JSON.stringify(result.dealModels, null, '\t'));
    console.log("\n", "securityMap> ", JSON.stringify(result.securityMap, null, '\t'));
});
