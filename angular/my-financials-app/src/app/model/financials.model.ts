export interface DealModel{
    dealId: number;
    salesforceInstrumentId: number;
}

export interface PotentialInvestmentModel{
    potentialInvestmentId: number;
    dealId: number;
    orderId: number;
    fundId: number;
}

export interface SecurityModel{
    instrumentId: number;
    salesforceInstrumentId: number;
}

export interface PortfolioModel{
    fundId: number;
    fundName: string;
}

export interface OrderModel{
    orderId: number;
    orderStatus: string;
}

export interface PotentialInvestmentResultModel{
    potentialInvestments: PotentialInvestmentModel[],
    portfolios: PortfolioModel[],
    orders: OrderModel[],
}

export interface FinalResultModel{
    potentialInvestmentModel: PotentialInvestmentResultModel,
    dealModels: DealModel[],
    securityMap: Map<number, number>,
}