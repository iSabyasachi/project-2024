import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { filter, forkJoin, from, map, mergeMap, Observable, switchMap, toArray } from 'rxjs';
import { dealService, orderService, portfolioService, potentialInvestmentService, securityService } from './service/financials.service';
import { DealModel, FinalResultModel, OrderModel, PortfolioModel, PotentialInvestmentModel, PotentialInvestmentResultModel } from './model/financials.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'my-financials-app';

  // Usage
  ngOnInit(){
    const finalResultModel$: Observable<FinalResultModel> = buildPotentialInvestments()
    resultValue(finalResultModel$);
  }
}

function resultValue(finalResultModel$: Observable<FinalResultModel>): void{
  finalResultModel$.subscribe(
    (result: FinalResultModel) => {
      console.log("potentialInvestmentModel> ",JSON.stringify(result.potentialInvestmentModel));
      console.log("\n", "dealModels> ",JSON.stringify(result.dealModels));
      console.log("\n", "securityMap> ",JSON.stringify(result.securityMap));
    }
  );
}

function buildPotentialInvestments(): Observable<FinalResultModel>{

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
                  from(securityService(dealModel.dealId)).pipe(
                      map(securityModel => ({ dealModel, securityModel }))
                  )
              ),
              toArray(), // Collect all mapped items
              map(mappedItems => {
                  const securityMap = new Map<number, number>();
                  mappedItems.forEach(({ dealModel, securityModel }) => {
                      if(!securityModel?.instrumentId) return;
                      securityMap.set(dealModel.salesforceInstrumentId, securityModel?.instrumentId);
                  });

                  return { mappedItems, securityMap };
              })
          )
      ),
      switchMap(({ mappedItems, securityMap }) =>
          potentialInvestment$.pipe(
              map(potentialInvestment => (<FinalResultModel>{
                  potentialInvestmentModel: potentialInvestment,
                  dealModels: mappedItems.map(mappedItem => mappedItem.dealModel),
                  securityMap: securityMap,
              }))
          )
      )
  );
}


