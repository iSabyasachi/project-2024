import { of, from, Observable, forkJoin } from 'rxjs';
import { map, mergeMap, switchMap, filter, toArray, tap } from 'rxjs/operators';
//Model
interface DealModel{
    dealId: number;
    salesforceInstrumentId: number;
}

interface PotentialInvestmentModel{
    potentialInvestmentId: number;
    dealId: number;
    orderId: number;
    fundId: number;
}

interface SecurityModel{
    instrumentId: number;
    salesforceInstrumentId: number;
}

interface PortfolioModel{
    fundId: number;
    fundName: string;
}

interface OrderModel{
    orderId: number;
    orderStatus: string;
}

interface PotentialInvestmentResultModel{
    potentialInvestments: PotentialInvestmentModel[],
    portfolios: PortfolioModel[],
    orders: OrderModel[],
}

interface FinalResultModel{
    potentialInvestmentModel: PotentialInvestmentResultModel;
    dealModels: DealModel[];
    securityMap: {[key: number]: number};
}

// Mocked service data
const mockDealModels: DealModel[] = [
    {
        dealId: 1,
        salesforceInstrumentId: 1001,
    },
    {
        dealId: 1,
        salesforceInstrumentId: 1002,
    },
];

const mockPotentialInvestmentModels: PotentialInvestmentModel[] = [
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

const mockSecurityModels: SecurityModel[] = [
    {
        instrumentId: 101,
        salesforceInstrumentId: 1001,
    },
    {
        instrumentId: 102,
        salesforceInstrumentId: 1002,
    }
];

const mockPortfolioModels: PortfolioModel[] = [
    {
        fundId: 1,
        fundName: 'Fund1'
    }
];

const mockOrderModels: OrderModel[] = [
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
function dealService(sourceId: number): Observable<DealModel[]>{
    return of(mockDealModels);
    //return of(mockDealModels.filter(mockDealModel => mockDealModel.dealId === sourceId));
}

function potentialInvestmentService(sourceIds: number[]): Observable<PotentialInvestmentModel[]>{
    return of(mockPotentialInvestmentModels);
    /*const results = sourceIds
    .map(sourceId => {
        const res = mockPotentialInvestmentModels.find(pi => pi.dealId === sourceId);
        if(!res) return;
        return res;
    })
    .filter(ele => ele !== undefined);
    return of(results);*/
}

function securityService(id: number): Observable<SecurityModel | undefined>{
    return of(mockSecurityModels
        .find(mockSecurityModel => mockSecurityModel.salesforceInstrumentId === id));
}

function portfolioService(ids: number[]): Observable<PortfolioModel[]>{
    return of(mockPortfolioModels);
    /*const results = ids
    .map(fundId => mockPortfolioModels.find(portfolio => portfolio.fundId === fundId))
    .filter(ele => ele !== undefined);

    return of(results);*/
}

function orderService(ids: number[]): Observable<OrderModel[]>{
    return of(mockOrderModels);
    /*const results = ids
    .map(orderId => mockOrderModels.find(order => order.orderId === orderId))
    .filter(ele => ele !== undefined);

    return of(results);*/
}
// method1 implementation
function buildPotentialInvestments(){
    const dealService$: Observable<DealModel[]> = from(dealService(1));
    const potentialInvestmentService$: Observable<PotentialInvestmentModel[]> = from(potentialInvestmentService([1]));

    const potentialInvestment$: Observable<PotentialInvestmentResultModel> = potentialInvestmentService$.pipe(
        switchMap(potentialInvestments =>
            {   
                const portfolioService$: Observable<PortfolioModel[]> = from(portfolioService([1]));
                const orderService$: Observable<OrderModel[]> = from(orderService([1, 2]));
                return forkJoin([portfolioService$, orderService$]).pipe(
                    map(([portfolios, orders]) => {
                        const potentialInvestmentResultModel: PotentialInvestmentResultModel = {
                            potentialInvestments: potentialInvestments,
                            portfolios: portfolios,
                            orders: orders,
                        };
                        return potentialInvestmentResultModel;
                    })
                )
            }
        )
    );

    return dealService$.pipe(
        filter(dealArray => !!dealArray),
        switchMap(dealArray =>
            from(dealArray).pipe(
                mergeMap(dealModel =>
                    from(securityService(dealModel.salesforceInstrumentId)).pipe(
                        map(securityModel => ({ dealModel, securityModel }))
                    )
                ),
                toArray(), // Collect all mapped items
                map(mappedItems => {
                    const securityMap =
                    mappedItems.reduce((acc: {[key: number]: number}, item) => {
                        if(item.securityModel?.salesforceInstrumentId){
                            acc[item.securityModel?.salesforceInstrumentId] =item.securityModel?.instrumentId;
                        }
                        return acc;
                    }, {});
                    return { mappedItems, securityMap };
                })
            )
        ),
        switchMap(({ mappedItems, securityMap }) =>
            potentialInvestment$.pipe(
                map(potentialInvestment => {
                    return (<FinalResultModel>{
                        potentialInvestmentModel: potentialInvestment,
                        dealModels: mappedItems.map(mappedItem => mappedItem.dealModel),
                        securityMap: securityMap,
                    })
                })
            )
        )
    );
};

// Usage
buildPotentialInvestments().subscribe((result: FinalResultModel) => {
    console.log("potentialInvestmentModel> ",JSON.stringify(result.potentialInvestmentModel,null,'\t'));
    console.log("\n", "dealModels> ",JSON.stringify(result.dealModels,null,'\t'));
    console.log("\n", "securityMap> ",JSON.stringify(result.securityMap,null,'\t'));
});