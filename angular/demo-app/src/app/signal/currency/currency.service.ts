import { computed, inject, Injectable, Signal, signal } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { toSignal } from "@angular/core/rxjs-interop";
import { groupBy, Observable } from "rxjs";

export type Currency = 'USD' | 'EUR' | 'GBP';
export type ExchangeRates = Record<Currency, number>;
export interface CurrencyInfo {
    currency: Currency;
    exchangeRate: number;
}

@Injectable({
    providedIn: `root`
})
export class CurrencyService{
    readonly #firstCurrency = signal<Currency>('USD');
    readonly #secondCurrency = signal<Currency>('EUR');
    
    readonly #http = inject(HttpClient);
    readonly #exchangeRates$= <Observable<ExchangeRates>>this.#http.get('https://lp-store-server.vercel.app/rates');
    readonly #exchangeRates = toSignal(this.#exchangeRates$, {initialValue: {EUR:1, GBP:1, USD:1}});
    
    readonly firstCurrencyInfo: Signal<CurrencyInfo> = computed(() => ({
            currency: this.#firstCurrency(),
            exchangeRate: this.#exchangeRates()[this.#firstCurrency()]
    }));

    readonly secondCurrencyInfo: Signal<CurrencyInfo> = computed(() => ({
            currency: this.#secondCurrency(),
            exchangeRate: this.#exchangeRates()[this.#secondCurrency()]
    }));

    setFirstCurrency(firstCurrency: Currency){
        this.#firstCurrency.set(firstCurrency);
    }

    setSecondCurrency(secondCurrency: Currency){
        this.#secondCurrency.set(secondCurrency);
    }
}