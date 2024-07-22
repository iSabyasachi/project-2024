import { Observable, of } from "rxjs";
import { DealModel, PotentialInvestmentModel, SecurityModel, PortfolioModel, OrderModel } from "../model/financials.model";

//Service
export function dealService(sourceId: number): Observable<DealModel[]>{
    return of(mockDealModels);
    //return of(mockDealModels.filter(mockDealModel => mockDealModel.dealId === sourceId));
}

export function potentialInvestmentService(sourceIds: number[]): Observable<PotentialInvestmentModel[]>{
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

export function securityService(id: number): Observable<SecurityModel | undefined>{
    return of(mockSecurityModels
        .find(mockSecurityModel => mockSecurityModel.salesforceInstrumentId === id));
}

export function portfolioService(ids: number[]): Observable<PortfolioModel[]>{
    return of(mockPortfolioModels);
    /*const results = ids
    .map(fundId => mockPortfolioModels.find(portfolio => portfolio.fundId === fundId))
    .filter(ele => ele !== undefined);

    return of(results);*/
}

export function orderService(ids: number[]): Observable<OrderModel[]>{
    return of(mockOrderModels);
    /*const results = ids
    .map(orderId => mockOrderModels.find(order => order.orderId === orderId))
    .filter(ele => ele !== undefined);

    return of(results);*/
}