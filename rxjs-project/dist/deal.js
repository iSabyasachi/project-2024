"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const rxjs_1 = require("rxjs");
const operators_1 = require("rxjs/operators");
// Mocked service data
const mockDealModels = [
    { dealId: 1, salesforceInstrumentId: 1001 },
    { dealId: 1, salesforceInstrumentId: 1002 },
];
const mockPotentialInvestmentModels = [
    { potentialInvestmentId: 1, dealId: 1, orderId: 1, fundId: 1 },
    { potentialInvestmentId: 2, dealId: 1, orderId: 2, fundId: 1 },
];
const mockSecurityModels = [
    { instrumentId: 101, salesforceInstrumentId: 1001 },
    { instrumentId: 102, salesforceInstrumentId: 1002 },
];
const mockPortfolioModels = [
    { fundId: 1, fundName: 'Fund1' },
];
const mockOrderModels = [
    { orderId: 1, orderStatus: 'DRAFT' },
    { orderId: 2, orderStatus: 'COMPLETE' },
];
// Services
const dealService = (sourceId) => (0, rxjs_1.of)(mockDealModels);
const potentialInvestmentService = (sourceIds) => (0, rxjs_1.of)(mockPotentialInvestmentModels);
const securityService = (id) => (0, rxjs_1.of)(mockSecurityModels.find(mockSecurityModel => mockSecurityModel.salesforceInstrumentId === id));
const portfolioService = (ids) => (0, rxjs_1.of)(mockPortfolioModels);
const orderService = (ids) => (0, rxjs_1.of)(mockOrderModels);
// Build potential investments
const buildPotentialInvestments = () => {
    const dealService$ = dealService(1);
    const potentialInvestmentService$ = potentialInvestmentService([1]);
    const potentialInvestment$ = potentialInvestmentService$.pipe((0, operators_1.switchMap)(potentialInvestments => (0, rxjs_1.forkJoin)({
        portfolios: portfolioService([1]),
        orders: orderService([1, 2])
    }).pipe((0, operators_1.map)(({ portfolios, orders }) => {
        const portfolioMap = portfolios.reduce((acc, portfolio) => {
            acc[portfolio.fundId] = portfolio.fundName;
            return acc;
        }, {});
        const orderMap = orders.reduce((acc, order) => {
            acc[order.orderId] = order.orderStatus;
            return acc;
        }, {});
        potentialInvestments.forEach(investment => {
            investment.fundName = portfolioMap[investment.fundId];
            investment.orderStatus = orderMap[investment.orderId];
        });
        return potentialInvestments;
    }))));
    const dealData$ = dealService$.pipe((0, operators_1.filter)(dealArray => dealArray.length > 0), (0, operators_1.switchMap)(dealArray => (0, rxjs_1.forkJoin)(dealArray.map(dealModel => securityService(dealModel.salesforceInstrumentId).pipe((0, operators_1.map)(securityModel => {
        dealModel.instrumentId = securityModel === null || securityModel === void 0 ? void 0 : securityModel.instrumentId;
        return dealModel;
    }))))
    /*.pipe(
        map(mappedItems => {
            mappedItems.forEach(mappedItem => {
                mappedItem.dealModel.instrumentId = mappedItem.securityModel?.instrumentId;
            });

            //return mappedItems.flatMap(mappedItem => mappedItem.dealModel);
            return mappedItems.map(({ dealModel }) => dealModel );
        })
    )*/
    ));
    return (0, rxjs_1.forkJoin)([dealData$, potentialInvestment$]).pipe((0, operators_1.map)(([deals, potentialInvestments]) => ({
        dealModels: deals,
        potentialInvestmentModels: potentialInvestments
    })));
};
// Usage
buildPotentialInvestments().subscribe((result) => {
    console.log("potentialInvestmentModel> ", JSON.stringify(result.potentialInvestmentModels, null, '\t'));
    console.log("\n", "dealModels> ", JSON.stringify(result.dealModels, null, '\t'));
});
