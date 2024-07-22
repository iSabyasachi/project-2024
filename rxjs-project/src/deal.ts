import { of, from, Observable, forkJoin } from 'rxjs';
import { map, mergeMap, switchMap, filter, toArray } from 'rxjs/operators';

// Model definitions
interface DealModel {
    dealId: number;
    salesforceInstrumentId: number;
    instrumentId?: number;
}

interface PotentialInvestmentModel {
    potentialInvestmentId: number;
    dealId: number;
    orderId: number;
    orderStatus?: string;
    fundId: number;
    fundName?: string;
    salesforceInstrumentId?: number;
    instrumentId?: number;
}

interface SecurityModel {
    instrumentId: number;
    salesforceInstrumentId: number;
}

interface PortfolioModel {
    fundId: number;
    fundName: string;
}

interface OrderModel {
    orderId: number;
    orderStatus: string;
}

interface PotentialInvestmentResultModel {
    potentialInvestments: PotentialInvestmentModel[];
}

interface FinalResultModel {
    potentialInvestmentModels: PotentialInvestmentModel[];
    dealModels: DealModel[];
}

// Mocked service data
const mockDealModels: DealModel[] = [
    { dealId: 1, salesforceInstrumentId: 1001 },
    { dealId: 1, salesforceInstrumentId: 1002 },
];

const mockPotentialInvestmentModels: PotentialInvestmentModel[] = [
    { potentialInvestmentId: 1, dealId: 1, orderId: 1, fundId: 1 },
    { potentialInvestmentId: 2, dealId: 1, orderId: 2, fundId: 1 },
];

const mockSecurityModels: SecurityModel[] = [
    { instrumentId: 101, salesforceInstrumentId: 1001 },
    { instrumentId: 102, salesforceInstrumentId: 1002 },
];

const mockPortfolioModels: PortfolioModel[] = [
    { fundId: 1, fundName: 'Fund1' },
];

const mockOrderModels: OrderModel[] = [
    { orderId: 1, orderStatus: 'DRAFT' },
    { orderId: 2, orderStatus: 'COMPLETE' },
];

// Services
const dealService = (sourceId: number): Observable<DealModel[]> => of(mockDealModels);
const potentialInvestmentService = (sourceIds: number[]): Observable<PotentialInvestmentModel[]> => of(mockPotentialInvestmentModels);
const securityService = (id: number): Observable<SecurityModel | undefined> => of(mockSecurityModels.find(mockSecurityModel => mockSecurityModel.salesforceInstrumentId === id));
const portfolioService = (ids: number[]): Observable<PortfolioModel[]> => of(mockPortfolioModels);
const orderService = (ids: number[]): Observable<OrderModel[]> => of(mockOrderModels);

// Build potential investments
const buildPotentialInvestments = (): Observable<FinalResultModel> => {
    const dealService$ = dealService(1);
    const potentialInvestmentService$ = potentialInvestmentService([1]);

    const potentialInvestment$ = potentialInvestmentService$.pipe(
        switchMap(potentialInvestments =>
            forkJoin({
                portfolios: portfolioService([1]),
                orders: orderService([1, 2])
            }).pipe(
                map(({ portfolios, orders }) => {
                    const portfolioMap = portfolios.reduce((acc: {[key: number]: string}, portfolio) => {
                        acc[portfolio.fundId] = portfolio.fundName;
                        return acc;
                    }, {});
                    const orderMap = orders.reduce((acc: {[key: number]: string}, order) => {
                        acc[order.orderId] = order.orderStatus;
                        return acc;
                    }, {});
                    potentialInvestments.forEach(investment => {
                        investment.fundName = portfolioMap[investment.fundId];
                        investment.orderStatus = orderMap[investment.orderId];
                    });
                    return potentialInvestments;
                })
            )
        ),
    );

    const dealData$ =  dealService$.pipe(
        filter(dealArray => dealArray.length > 0),
        switchMap(dealArray =>
            forkJoin(
                dealArray.map(dealModel =>
                    securityService(dealModel.salesforceInstrumentId).pipe(
                        map(securityModel => {
                            dealModel.instrumentId = securityModel?.instrumentId;
                            return dealModel;
                        })
                    )
                )
            )
        ))
    return forkJoin([dealData$, potentialInvestment$]).pipe(
        map(([deals, potentialInvestments]) => <FinalResultModel>{
            dealModels: deals,
            potentialInvestmentModels: potentialInvestments
        })
    );
     
};

// Usage
buildPotentialInvestments().subscribe((result: FinalResultModel) => {
    console.log("potentialInvestmentModel> ", JSON.stringify(result.potentialInvestmentModels, null, '\t'));
    console.log("\n", "dealModels> ", JSON.stringify(result.dealModels, null, '\t'));
});