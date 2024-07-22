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