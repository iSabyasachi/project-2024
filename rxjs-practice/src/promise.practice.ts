import { from, Observable } from 'rxjs';

export async function getData(): Promise<string> {
  const response = await fetch('https://example.com');
  return response.text();
}

export function getDataAndHandleError(url: string): Promise<string> {
  return new Promise<string>((resolve, reject) => {
    try {
      setTimeout(async () => {
        const response = await fetch(url);
        return resolve(await response.text());
      }, 1000);
    } catch (error) {
      return reject('' + error);
    }
  });
}

export function getDataWithObservable(url: string): Observable<string> {
  const response = getDataAndHandleError(url);

  return from(response);
}
