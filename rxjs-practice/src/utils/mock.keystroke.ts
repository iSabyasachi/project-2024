import { Subject } from "rxjs";

const searchSubject = new Subject<string>();
export function mockUserInputs(): Subject<string>{
    
    simulateKeyStroke('H');
    setTimeout(()=>simulateKeyStroke('He'), 100);
    setTimeout(()=>simulateKeyStroke('Hel'), 200);
    setTimeout(()=>simulateKeyStroke('Hell'), 300);
    setTimeout(()=>simulateKeyStroke('Hello'), 1000);

    return searchSubject;
}

function simulateKeyStroke(key: string){
    searchSubject.next(key)
}